package dev.pumpkin.shimgen;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.Node;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.CompactConstructorDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.EnumConstantDeclaration;
import com.github.javaparser.ast.body.EnumDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.RecordDeclaration;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.comments.Comment;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
import com.github.javaparser.ast.expr.CastExpr;
import com.github.javaparser.ast.expr.CharLiteralExpr;
import com.github.javaparser.ast.expr.DoubleLiteralExpr;
import com.github.javaparser.ast.expr.Expression;
import com.github.javaparser.ast.expr.IntegerLiteralExpr;
import com.github.javaparser.ast.expr.LongLiteralExpr;
import com.github.javaparser.ast.expr.MethodCallExpr;
import com.github.javaparser.ast.expr.NameExpr;
import com.github.javaparser.ast.expr.NullLiteralExpr;
import com.github.javaparser.ast.expr.StringLiteralExpr;
import com.github.javaparser.ast.nodeTypes.NodeWithAnnotations;
import com.github.javaparser.ast.nodeTypes.NodeWithTypeParameters;
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.IfStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.ReferenceType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import com.github.javaparser.ast.Modifier;
import com.github.javaparser.ast.type.WildcardType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Decides each {@code net.minecraft}/{@code net.neoforged} type's {@link Treatment}
 * and, given a {@link UsedSet}, prunes it down to that treatment's shape.
 *
 * <p>Resolution of parameter/field/return types to internal names is syntactic and
 * reuses {@link SupertypeCloser#resolve}: there is no classpath available while
 * generating, so both classes face exactly the same problem, and this one shares that
 * solution rather than carrying a second, independently-drifting guess at the same
 * rules (import, a known {@code java.lang} name, the compilation unit's own package,
 * then {@code java.lang} again as a last resort). A self-reference — a nested type's
 * own unqualified name, or an enclosing type's — is resolved without any lookup at
 * all, from the nesting context {@link #prune} already tracks while recursing.
 *
 * <p>Even so, resolution can still miss (a cross-package Minecraft type used without an
 * import has no signal here to resolve correctly). Every used-member lookup therefore
 * fails toward keeping: if the exact {@code name:descriptor} is not found, any other
 * member of that name at that owner — regardless of descriptor — is enough to keep it.
 * That is not free, though: a member kept this way can carry parameter/return types
 * that were never part of the closure {@link UsedSet} was built from, and those will
 * not have been generated — an eventual {@code :shim:compileJava} failure with no
 * pointer back to why. {@link #keptByFallback()} and {@link #missingTypesInKeptSignatures()}
 * exist so that blast radius is visible at generation time instead.
 */
public final class Pruner {
    private Pruner() {}

    /**
     * Internal names of types that exist in the decompiled sources but in no artifact this
     * build can put on a classpath -- see {@code --absent-type}. Carried in a thread-local
     * rather than threaded through fifteen call sites for one flag; the generator is
     * single-threaded, and {@link #KEPT_BY_FALLBACK} already establishes that this class
     * keeps per-run state.
     */
    private static final ThreadLocal<Set<String>> ABSENT_TYPES = ThreadLocal.withInitial(Set::of);

    /** Whether any of {@code referenced} is an absent type, so the member cannot compile. */
    private static boolean namesAbsentType(Set<String> referenced) {
        Set<String> absent = ABSENT_TYPES.get();
        if (absent.isEmpty()) {
            return false;
        }
        for (String type : referenced) {
            if (absent.contains(type)) {
                return true;
            }
        }
        return false;
    }

    private static final SortedSet<String> KEPT_BY_FALLBACK = Collections.synchronizedSortedSet(new TreeSet<>());
    private static final SortedSet<String> MISSING_TYPES = Collections.synchronizedSortedSet(new TreeSet<>());

    /**
     * Every member key (in {@link Unimplemented#forMember} form, {@code
     * Owner.name:descriptor}) kept without an exact {@code name:descriptor} hit in the
     * used set — i.e. every member whose descriptor this generator is not confident it
     * reproduced correctly. That is the by-name fallback for fields and methods, and
     * for a constructor (which is kept unconditionally) it is simply the absence of a
     * matching entry: either way the key embedded in the emitted {@code
     * forMember(...)} string may not name the member the runtime will look for.
     */
    public static SortedSet<String> keptByFallback() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(KEPT_BY_FALLBACK));
    }

    /**
     * Every shimmed ({@code net/minecraft/**} or {@code net/neoforged/**}) outer class
     * named in some kept member's signature that is absent from the {@link UsedSet}
     * this generator run was given — these will not have a generated file to compile
     * against.
     */
    public static SortedSet<String> missingTypesInKeptSignatures() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(MISSING_TYPES));
    }

    /** Clears both reports. Package-private: test-only by visibility, not by request. */
    static void resetReport() {
        KEPT_BY_FALLBACK.clear();
        MISSING_TYPES.clear();
        DROPPED_FOR_ABSENT_TYPE.clear();
    }

    /**
     * VALUE for an enum or a record, and nothing else. HOLDER for a class whose every
     * declared field is {@code static final} with an initializer and which declares no
     * instance method (a private no-arg constructor does not count against it: {@code
     * Registries}, {@code BlockTags}, {@code ItemTags} and {@code ParticleTypes} all
     * declare one precisely to prevent instantiation). HANDLE otherwise.
     *
     * <p>There used to be a third VALUE arm — a class whose every declared field is a
     * {@code static final} primitive or {@code String} constant — and it is gone. VALUE
     * means the file is copied verbatim, real bodies included, and real bodies name
     * arbitrary types that were never generated; body-stripping is the entire reason
     * the emitted class set is closed at all, so any arm that opts a class out of it
     * has to earn that risk. This one did not: it fired on zero of 277 real classes,
     * and even guarded against instance methods it still admitted a constant holder
     * with only static methods, whose bodies are just as unbounded. A class of pure
     * constants now classifies HOLDER or HANDLE, gets stubbed, and compiles.
     *
     * <p>The HOLDER check deliberately requires the absence of instance methods on top
     * of the field shape: "every declared field is static final with an initializer"
     * alone also matches ordinary behaviour-bearing classes that merely declare their
     * properties as constants (e.g. {@code HorizontalDirectionalBlock}, {@code
     * RuleTest}), and giving those a throwing static initializer would break every mod
     * class that extends them, at class-initialisation, far from the cause.
     */
    public static Treatment treatmentOf(TypeDeclaration<?> type) {
        if (type.isEnumDeclaration() || type.isRecordDeclaration()) {
            return Treatment.VALUE;
        }
        if (type.isClassOrInterfaceDeclaration()) {
            ClassOrInterfaceDeclaration decl = type.asClassOrInterfaceDeclaration();
            if (!decl.isInterface() && isHolder(decl)) {
                return Treatment.HOLDER;
            }
        }
        return Treatment.HANDLE;
    }

    private static boolean isHolder(ClassOrInterfaceDeclaration decl) {
        List<FieldDeclaration> fields = decl.getFields();
        if (fields.isEmpty() || !hasOnlyStaticMethods(decl)) {
            return false;
        }
        for (FieldDeclaration f : fields) {
            if (!f.isStatic() || !f.isFinal()) {
                return false;
            }
            for (VariableDeclarator v : f.getVariables()) {
                if (v.getInitializer().isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /** A private no-arg constructor does not count as an instance method; see {@link #isHolder}. */
    private static boolean hasOnlyStaticMethods(ClassOrInterfaceDeclaration decl) {
        for (MethodDeclaration m : decl.getMethods()) {
            if (!m.isStatic()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Prunes the compilation unit's top-level type in place according to its {@link
     * Treatment}, then recurses into every nested member type and prunes it the same
     * way, keyed on its JVM-style {@code Outer$Nested} internal name.
     *
     * <p>Recursion is not optional: the closure argument behind emitting only 353
     * classes instead of all ~7000 is that stripping bodies down to signature-level
     * references is what stops the reference graph from spreading arbitrarily further
     * — a nested type left whole keeps its real bodies, which reference arbitrary types
     * outside the emitted set, and the closure argument collapses for every class that
     * has one. {@code Item$Properties} is not a hypothetical: it is a heavily-used
     * nested type on real input.
     *
     * <p>A VALUE type is copied whole, nested types included — an enum's ordinals are
     * serialised and a record is a pure data carrier, so nothing under it is safe to
     * touch, let alone recurse into.
     *
     * <p>Deliberately {@code cu.getType(0)}, not {@link CompilationUnit#getPrimaryType()}:
     * the latter matches the type's name against the compilation unit's {@code
     * Storage} (its file name), which is absent for a unit parsed from a string — as
     * every test here, and every unit handed in by {@link SupertypeCloser#parse}, is.
     */
    private static final SortedSet<String> DROPPED_FOR_ABSENT_TYPE =
            Collections.synchronizedSortedSet(new TreeSet<>());

    /**
     * Every member removed because its signature names a type from {@code absentTypes}.
     * Empty unless the caller passed {@code --absent-type}.
     */
    public static SortedSet<String> droppedForAbsentType() {
        return Collections.unmodifiableSortedSet(new TreeSet<>(DROPPED_FOR_ABSENT_TYPE));
    }

    public static void prune(CompilationUnit cu, String internalName, UsedSet used) {
        prune(cu, internalName, used, Set.of(), Set.of());
    }

    public static void prune(CompilationUnit cu, String internalName, UsedSet used,
            Set<String> inheritedAbstracts) {
        prune(cu, internalName, used, inheritedAbstracts, Set.of());
    }

    /**
     * As above, plus the set of {@code name/arity} signatures that are declared abstract
     * somewhere in the generated set. A concrete method matching one of these is kept
     * whether or not a mod calls it: it may be the only implementation of an abstract
     * method its class inherits, and dropping it leaves "X is not abstract and does not
     * override Y" -- 299 of them on the real input, the single largest failure category
     * after bodies.
     *
     * <p>Deliberately keyed on name and arity alone, with no owner. Deciding whether
     * {@code ServerLevel.clockManager()} really implements {@code Level.clockManager()}
     * means resolving a supertype chain across files with no classpath, which is the
     * problem this generator spends most of its guesses on already. Matching by shape
     * over-keeps -- a {@code get()} anywhere is kept everywhere -- and over-keeping costs
     * stubbed methods that compile, while under-keeping costs a class that does not.
     *
     * <p>The {@code @Override} annotation is not enough on its own, which is why this
     * exists: the decompiler omits it on covariant returns, and {@code
     * ServerLevel.clockManager()} returning {@code ServerClockManager} is exactly that
     * case.
     */
    public static void prune(CompilationUnit cu, String internalName, UsedSet used,
            Set<String> inheritedAbstracts, Set<String> absentTypes) {
        if (cu.getTypes().isEmpty()) {
            return;
        }
        ABSENT_TYPES.set(absentTypes);
        pruneType(cu, cu.getType(0), internalName, used, declaredTypes(cu.getType(0), internalName), Map.of(),
                inheritedAbstracts);
        stripAnnotations(cu);
        stripComments(cu);
        stripUnusedImports(cu);
    }

    /**
     * Removes every comment from the unit.
     *
     * <p>Two reasons, and the second is the load-bearing one. A stubbed body carrying the
     * javadoc of the implementation it no longer has is worse than no javadoc. And an
     * import is kept when its simple name still appears in the printed unit -- a {@code
     * {@link LivingDamageEvent}} in a doc comment keeps an import for a class the closure
     * never generated, which fails the compile on a line that is a comment.
     */
    private static void stripComments(CompilationUnit cu) {
        for (Comment comment : List.copyOf(cu.getAllContainedComments())) {
            comment.remove();
        }
        cu.removeComment();
    }

    /**
     * Every {@code name/arity} that some type in {@code cu} declares without a body: its
     * abstract class members and its interface methods. Collected across the whole
     * generated set, this is the input to the four-argument {@link #prune}.
     */
    public static void collectAbstractSignatures(CompilationUnit cu, Set<String> into) {
        for (MethodDeclaration m : cu.findAll(MethodDeclaration.class)) {
            if (m.getBody().isEmpty()) {
                into.add(m.getNameAsString() + "/" + m.getParameters().size());
            }
        }
    }

    /**
     * Abstract method shapes from outside the generated set: the JDK's functional and
     * collection interfaces, and the game libraries the shim compiles against.
     *
     * <p>The generated set's own abstract methods are collected from source, but a
     * generated class also implements {@code Comparable}, {@code Iterator}, DataFixerUpper's
     * {@code Encoder}, Gson's {@code JsonDeserializer} and Netty's {@code
     * SimpleChannelInboundHandler}, whose abstract methods live in a jar this generator
     * never reads. Prune the implementation of one of those and the class stops compiling
     * for a reason nothing in the source tree explains. A fixed list is the crude answer;
     * the honest alternative is loading the libraries and reflecting over them, which
     * would make the generator depend on the very classpath it does not have.
     */
    private static final Set<String> WELL_KNOWN_ABSTRACTS = Set.of(
            "compareTo/1", "compare/2", "equals/1", "hashCode/0", "toString/0", "clone/0",
            "iterator/0", "hasNext/0", "next/0", "remove/0", "forEach/1", "spliterator/0",
            "run/0", "call/0", "get/0", "get/1", "getAsInt/0", "getAsLong/0", "getAsDouble/0",
            "apply/1", "apply/2", "applyAsInt/1", "applyAsLong/1", "applyAsDouble/1",
            "accept/1", "accept/2", "test/1", "test/2", "close/0",
            "size/0", "isEmpty/0", "contains/1", "add/1", "add/2", "set/2", "clear/0",
            "length/0", "charAt/1", "subSequence/2",
            "encode/2", "encode/3", "decode/1", "decode/2", "keys/1",
            "serialize/3", "deserialize/3",
            "channelRead0/2", "channelRead/2", "channelActive/1", "channelInactive/1",
            "exceptionCaught/2", "handlerAdded/1", "handlerRemoved/1",
            "touch/0", "touch/1", "retain/0", "retain/1", "release/0", "release/1", "refCnt/0",
            "writeZero/1", "writeBytes/1", "writeBytes/3", "readBytes/1", "readBytes/3");

    /**
     * The annotations a generated file keeps: the four {@code java.lang.annotation}
     * meta-annotations, which only ever appear on an {@code @interface} declaration and
     * are exactly the part of one that carries meaning at runtime.
     */
    private static final Set<String> KEPT_ANNOTATIONS =
            Set.of("Retention", "Target", "Documented", "Inherited", "Repeatable");

    /**
     * Removes every other annotation from the unit -- {@code @Nullable}, {@code @OnlyIn},
     * {@code @VisibleForTesting} and the rest.
     *
     * <p>None of them carry behaviour the shim needs, and each one is a compile-time
     * dependency on an artifact that has nothing to do with the game: {@code
     * org.jspecify}, {@code org.jetbrains.annotations}, {@code javax.annotation.concurrent},
     * {@code com.google.common.annotations}. Keeping them would mean putting four
     * annotation libraries on the shim's compile classpath to express nothing.
     */
    private static void stripAnnotations(CompilationUnit cu) {
        for (NodeWithAnnotations<?> annotated : cu.findAll(Node.class).stream()
                .filter(n -> n instanceof NodeWithAnnotations<?>)
                .map(n -> (NodeWithAnnotations<?>) n)
                .toList()) {
            annotated.getAnnotations().removeIf(a -> !KEPT_ANNOTATIONS.contains(a.getName().getIdentifier()));
        }
    }

    /**
     * Removes every import whose simple name no longer appears anywhere in the unit.
     *
     * <p>Not cosmetic. Pruning deletes the members that named most of a decompiled file's
     * imports, and javac rejects {@code import com.mojang.blaze3d.systems.RenderSystem;}
     * when that package is not on the classpath whether or not anything uses it. Without
     * this pass the shim could only compile by putting every library Minecraft has ever
     * been built against on its classpath, to satisfy imports for code that is gone.
     *
     * <p>Matching is textual against the printed unit rather than by resolving names: the
     * generator has no classpath, and the failure direction is right either way -- a
     * simple name that shows up only inside a string literal keeps one import too many,
     * which is what the pass started with.
     */
    private static void stripUnusedImports(CompilationUnit cu) {
        if (cu.getImports().isEmpty()) {
            return;
        }
        CompilationUnit withoutImports = cu.clone();
        withoutImports.getImports().clear();
        String body = withoutImports.toString();
        cu.getImports().removeIf(imp -> !imp.isAsterisk() && !mentions(body, simpleNameOf(imp)));
    }

    private static String simpleNameOf(ImportDeclaration imp) {
        String qualified = imp.getNameAsString();
        int dot = qualified.lastIndexOf('.');
        return dot < 0 ? qualified : qualified.substring(dot + 1);
    }

    /** {@code true} if {@code name} appears in {@code text} as a whole identifier. */
    private static boolean mentions(String text, String name) {
        int from = 0;
        while (true) {
            int at = text.indexOf(name, from);
            if (at < 0) {
                return false;
            }
            boolean beforeOk = at == 0 || !Character.isJavaIdentifierPart(text.charAt(at - 1));
            int after = at + name.length();
            boolean afterOk = after >= text.length() || !Character.isJavaIdentifierPart(text.charAt(after));
            if (beforeOk && afterOk) {
                return true;
            }
            from = at + 1;
        }
    }

    /**
     * {@code enclosingTypes} maps every ancestor type's simple name (this type's own
     * enclosing chain, built up while recursing) to its already-known internal name,
     * so a self-reference — {@code Properties} inside {@code Item.Properties}, or
     * {@code Item} referenced back from within it — resolves exactly, with no guess.
     */
    private static void pruneType(CompilationUnit cu, TypeDeclaration<?> type, String internalName, UsedSet used,
            Map<String, String> enclosingTypes, Map<String, String> enclosingTypeParams,
            Set<String> inheritedAbstracts) {
        Map<String, String> selfTypes = new HashMap<>(enclosingTypes);
        selfTypes.put(type.getNameAsString(), internalName);
        // A nested type sees its enclosing type's type parameters as well as its own, and
        // a member signature that names one must not have it resolved as a class: an
        // unknown `T` would otherwise become the top-level name `<this package>/T`, which
        // exists nowhere and would be reported as a class someone has to hand-write.
        Map<String, String> typeParams = new HashMap<>(enclosingTypeParams);
        if (type instanceof NodeWithTypeParameters<?> generic) {
            for (TypeParameter tp : generic.getTypeParameters()) {
                typeParams.put(tp.getNameAsString(), boundOf(cu, tp, selfTypes));
            }
        }

        if (type.isAnnotationDeclaration()) {
            // An annotation carries no bodies to strip and no members a mod calls; its
            // element list *is* its contract. Left exactly as decompiled.
            return;
        }

        reportDeclarationTypes(cu, type, internalName, used, selfTypes, typeParams);
        switch (treatmentOf(type)) {
            case VALUE -> {
                pruneValueShape(cu, type, internalName, used, selfTypes, typeParams);
                pruneMembers(cu, type, internalName, used, selfTypes, typeParams, inheritedAbstracts);
            }
            case HANDLE -> pruneMembers(cu, type, internalName, used, selfTypes, typeParams, inheritedAbstracts);
            case HOLDER -> pruneHolder(cu, type, internalName, used, selfTypes, typeParams);
        }
        for (BodyDeclaration<?> member : type.getMembers()) {
            if (member instanceof TypeDeclaration<?> nested) {
                pruneType(cu, nested, internalName + "$" + nested.getNameAsString(), used, selfTypes, typeParams,
                        inheritedAbstracts);
            }
        }
    }

    /**
     * Reports every type named in the declaration line itself -- the type arguments of an
     * {@code extends} or {@code implements} clause, and the bounds of the type's own type
     * parameters.
     *
     * <p>{@link SupertypeCloser} closes over the raw supertype names, which is what a
     * class needs to exist at all, but {@code implements Codec<Ingredient>} and {@code
     * <T extends Recipe<?>>} name types too, and javac needs those just as much.
     */
    private static void reportDeclarationTypes(CompilationUnit cu, TypeDeclaration<?> type, String internalName,
            UsedSet used, Map<String, String> selfTypes, Map<String, String> typeParams) {
        Set<String> referenced = new TreeSet<>();
        if (type instanceof NodeWithTypeParameters<?> generic) {
            for (TypeParameter tp : generic.getTypeParameters()) {
                for (ClassOrInterfaceType bound : tp.getTypeBound()) {
                    collectReferencedTypes(cu, bound, typeParams, selfTypes, referenced);
                }
            }
        }
        if (type instanceof ClassOrInterfaceDeclaration decl) {
            for (ClassOrInterfaceType parent : decl.getExtendedTypes()) {
                collectReferencedTypes(cu, parent, typeParams, selfTypes, referenced);
            }
            for (ClassOrInterfaceType parent : decl.getImplementedTypes()) {
                collectReferencedTypes(cu, parent, typeParams, selfTypes, referenced);
            }
        }
        if (type instanceof EnumDeclaration decl) {
            for (ClassOrInterfaceType parent : decl.getImplementedTypes()) {
                collectReferencedTypes(cu, parent, typeParams, selfTypes, referenced);
            }
        }
        if (type instanceof RecordDeclaration decl) {
            for (ClassOrInterfaceType parent : decl.getImplementedTypes()) {
                collectReferencedTypes(cu, parent, typeParams, selfTypes, referenced);
            }
        }
        reportKept(internalName + ".<declaration>", true, referenced, used);
    }

    /**
     * Reduces an enum or a record to its shape, so that {@link #pruneMembers} can then
     * strip it like anything else.
     *
     * <p>These two used to be copied verbatim, bodies and all, on the reasoning that an
     * enum's ordinals are serialised and a record is a pure data carrier. The shape is
     * indeed load-bearing and is kept here in full: every constant, in order, and every
     * record component. The <em>bodies</em> were not, and they were the single largest
     * source of compile failures in the first real run -- {@code Enchantment} alone
     * produced 195 errors. A copied-whole body references arbitrary types that were never
     * generated, which is precisely the closure argument that body-stripping exists to
     * preserve; an enum or record was simply a hole in it.
     *
     * <p>What is dropped: an enum constant's constructor arguments (and with them every
     * declared enum constructor, so the implicit no-arg one applies), and a record's
     * explicit canonical constructor (javac regenerates it). A constant's class body is
     * kept, since a per-constant override may be the only implementation of a method the
     * enum declares; {@link #pruneMembers} stubs it out like any other body.
     */
    private static void pruneValueShape(CompilationUnit cu, TypeDeclaration<?> type, String internalName,
            UsedSet used, Map<String, String> selfTypes, Map<String, String> typeParams) {
        if (type instanceof EnumDeclaration decl) {
            for (EnumConstantDeclaration constant : decl.getEntries()) {
                constant.getArguments().clear();
            }
            decl.getMembers().removeIf(BodyDeclaration::isConstructorDeclaration);
        }
        if (type instanceof RecordDeclaration decl) {
            // The component types are part of the emitted source and of every accessor's
            // signature, so they belong to the closure just as a method parameter does.
            Set<String> referenced = new TreeSet<>();
            for (Parameter component : decl.getParameters()) {
                collectReferencedTypes(cu, component.getType(), typeParams, selfTypes, referenced);
            }
            reportKept(internalName + ".<record components>", true, referenced, used);
            List<String> componentTypes = decl.getParameters().stream().map(c -> c.getType().toString()).toList();
            decl.getMembers().removeIf(m -> m instanceof CompactConstructorDeclaration
                    || (m instanceof ConstructorDeclaration c && componentTypes.equals(
                            c.getParameters().stream().map(param -> param.getType().toString()).toList())));
        }
    }

    /**
     * Every type declared anywhere in {@code type}, by simple name, mapped to its internal
     * name -- the top-level type and all its nested types, at any depth.
     *
     * <p>Java lets code inside a top-level type name any of its nested types by simple
     * name, including a sibling's: {@code RegisterEvent}'s own methods refer to {@code
     * RegisterHelper}, which is {@code RegisterEvent$RegisterHelper}. Tracking only the
     * enclosing chain missed exactly those, and the same-package guess turned them into
     * plausible-looking top-level names with no source behind them.
     */
    private static Map<String, String> declaredTypes(TypeDeclaration<?> type, String internalName) {
        Map<String, String> declared = new HashMap<>();
        collectDeclaredTypes(type, internalName, declared);
        return declared;
    }

    private static void collectDeclaredTypes(TypeDeclaration<?> type, String internalName,
            Map<String, String> into) {
        into.put(type.getNameAsString(), internalName);
        for (BodyDeclaration<?> member : type.getMembers()) {
            if (member instanceof TypeDeclaration<?> nested) {
                collectDeclaredTypes(nested, internalName + "$" + nested.getNameAsString(), into);
            }
        }
    }

    /**
     * Strips a type's members to what the mods call: every surviving body becomes a
     * throw, unused fields and methods go, and every constructor stays.
     *
     * <p>Takes a {@link TypeDeclaration}, not a {@link ClassOrInterfaceDeclaration}: an
     * enum and a record get exactly this treatment too, once {@link #pruneValueShape} has
     * reduced them to their shape.
     */
    private static void pruneMembers(CompilationUnit cu, TypeDeclaration<?> decl, String internalName,
            UsedSet used, Map<String, String> selfTypes, Map<String, String> classTypeParams,
            Set<String> inheritedAbstracts) {
        SortedSet<String> usedKeys = used.membersOf(internalName);
        NodeList<BodyDeclaration<?>> members = decl.getMembers();
        List<BodyDeclaration<?>> toRemove = new ArrayList<>();
        boolean[] threw = {false};
        boolean isInterface = decl instanceof ClassOrInterfaceDeclaration c && c.isInterface();
        boolean inheritsFromOutside = inheritsFromOutsideTheSet(cu, decl, used, selfTypes, classTypeParams);

        for (BodyDeclaration<?> member : members) {
            if (member.isInitializerDeclaration()) {
                // A static or instance initializer block is a body like any other, with no
                // signature anything can call it by. Kept, it would be the one place real
                // decompiled code survived into the shim, referencing whatever it liked.
                toRemove.add(member);
            } else if (member.isFieldDeclaration()) {
                pruneField(member.asFieldDeclaration(), isInterface, internalName, usedKeys, used, selfTypes,
                        classTypeParams, toRemove);
            } else if (member.isMethodDeclaration()) {
                MethodDeclaration m = member.asMethodDeclaration();
                if (m.getBody().isEmpty()) {
                    // An abstract method (interface method, or abstract class member)
                    // declared by this very type. There is no body to prune to a
                    // throw, and removing it risks the type no longer satisfying a
                    // contract it declares, so it is always kept untouched -- but its
                    // signature is emitted verbatim and therefore belongs to the closure
                    // exactly as a stubbed method's does. Interfaces are almost entirely
                    // bodyless methods; skipping them here left whole packages
                    // (PackType, LootContext, RandomState) named by emitted source and
                    // never generated.
                    Set<String> abstractRefs = new TreeSet<>();
                    methodDescriptor(cu, m, m.getType(), selfTypes, classTypeParams, abstractRefs);
                    reportKept(internalName + "." + m.getNameAsString(), true, abstractRefs, used);
                    continue;
                }
                String name = m.getNameAsString();
                Set<String> referenced = new TreeSet<>();
                String descriptor = methodDescriptor(cu, m, m.getType(), selfTypes, classTypeParams, referenced);
                String lookupKey = name + ":" + descriptor;
                boolean exact = usedKeys.contains(lookupKey);
                // An @Override method is kept whether or not a mod calls it. Removing one
                // leaves a concrete class not implementing an abstract method it inherits
                // -- "X is not abstract and does not override Y" -- and deciding that any
                // other way needs the supertype's members, which are in another file this
                // pass cannot see. The decompiler annotates every override, so the
                // annotation is exactly the cross-file fact needed, already in the source.
                // Read here, before stripAnnotations removes it.
                String shape = name + "/" + m.getParameters().size();
                boolean overrides = inheritsFromOutside || m.getAnnotationByName("Override").isPresent()
                        || inheritedAbstracts.contains(shape) || WELL_KNOWN_ABSTRACTS.contains(shape);
                if (namesAbsentType(referenced)) {
                    DROPPED_FOR_ABSENT_TYPE.add(internalName + "." + lookupKey);
                    toRemove.add(member);
                } else if (exact || overrides || matchesByName(usedKeys, name)) {
                    String key = internalName + "." + lookupKey;
                    replaceMethodBody(m, key);
                    threw[0] = true;
                    reportKept(key, exact || overrides, referenced, used);
                } else {
                    toRemove.add(member);
                }
            } else if (member.isConstructorDeclaration()) {
                // Constructors are never pruned, used or not: SupertypeCloser closes
                // the used set over classes, never members, so a superclass
                // constructor a kept subclass constructor's preserved super(...) call
                // targets is often simply absent from usedKeys — pruning by usage here
                // would delete the very constructor that call needs to keep compiling.
                // The same gap empties out to the implicit default constructor's
                // super() when every declared constructor gets removed. Keeping every
                // constructor covers both in one rule; its parameter types may still
                // fall outside the closure, which is what missingTypesInKeptSignatures
                // exists to surface.
                ConstructorDeclaration c = member.asConstructorDeclaration();
                Set<String> referenced = new TreeSet<>();
                String descriptor = methodDescriptor(cu, c, null, selfTypes, classTypeParams, referenced);
                String lookupKey = "<init>:" + descriptor;
                // Whether the constructor is *kept* no longer depends on this, but
                // whether its descriptor is trustworthy still does: the same key is
                // embedded in the emitted forMember(...) string, and a guessed-wrong
                // descriptor makes that string wrong. Report the real match mode.
                boolean exact = usedKeys.contains(lookupKey);
                String key = internalName + "." + lookupKey;
                if (namesAbsentType(referenced)) {
                    DROPPED_FOR_ABSENT_TYPE.add(key);
                    toRemove.add(member);
                    continue;
                }
                replaceConstructorBody(c, key);
                threw[0] = true;
                reportKept(key, exact, referenced, used);
            }
        }

        members.removeAll(toRemove);
        if (decl instanceof EnumDeclaration enumDecl && stubConstantBodies(enumDecl, internalName)) {
            threw[0] = true;
        }
        if (decl instanceof RecordDeclaration record) {
            delegateToCanonicalConstructor(record);
        }
        if (decl instanceof ClassOrInterfaceDeclaration classDecl) {
            ensureNoArgConstructor(classDecl, internalName);
            threw[0] = true;
        }
        unseal(decl);
        removeOverrideAnnotations(decl);
        if (threw[0]) {
            addUnimplementedImport(cu);
        }
    }

    /**
     * Whether this type extends a class that is not being generated -- Netty's {@code
     * ByteBuf} under {@code FriendlyByteBuf}, say.
     *
     * <p>When it does, nothing here can enumerate the abstract methods it must implement,
     * and every one that gets pruned is an "is not abstract and does not override" error
     * discovered one at a time. So none of its methods are pruned at all. This is the
     * whole-class version of {@link #WELL_KNOWN_ABSTRACTS}, and it is bounded: only a
     * handful of generated classes extend anything outside the set.
     */
    private static boolean inheritsFromOutsideTheSet(CompilationUnit cu, TypeDeclaration<?> decl, UsedSet used,
            Map<String, String> selfTypes, Map<String, String> typeParams) {
        if (!(decl instanceof ClassOrInterfaceDeclaration type) || type.isInterface()) {
            return false;
        }
        for (ClassOrInterfaceType parent : type.getExtendedTypes()) {
            String internalName = Shimmed.outerOf(resolveClassOrInterfaceType(cu, parent, typeParams, selfTypes));
            if (!used.classes().contains(internalName)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Replaces the body of every method a constant declares in its own class body with a
     * throw, and drops everything else a constant body declares.
     *
     * <p>{@code Direction.Axis.X { public int choose(...) { return x; } }} is the shape:
     * the override is the enum's only implementation of an abstract method, so removing
     * the constant body outright would leave the enum not implementing its own contract.
     *
     * @return whether anything was stubbed, so the caller knows to import {@code Unimplemented}
     */
    private static boolean stubConstantBodies(EnumDeclaration decl, String internalName) {
        boolean stubbed = false;
        for (EnumConstantDeclaration constant : decl.getEntries()) {
            List<BodyDeclaration<?>> drop = new ArrayList<>();
            for (BodyDeclaration<?> member : constant.getClassBody()) {
                if (member.isMethodDeclaration() && member.asMethodDeclaration().getBody().isPresent()) {
                    MethodDeclaration m = member.asMethodDeclaration();
                    replaceMethodBody(m, internalName + "$" + constant.getNameAsString() + "."
                            + m.getNameAsString() + ":()");
                    stubbed = true;
                } else {
                    drop.add(member);
                }
            }
            constant.getClassBody().removeAll(drop);
        }
        return stubbed;
    }

    private static void pruneHolder(CompilationUnit cu, TypeDeclaration<?> decl, String internalName,
            UsedSet used, Map<String, String> selfTypes, Map<String, String> classTypeParams) {
        SortedSet<String> usedKeys = used.membersOf(internalName);
        NodeList<BodyDeclaration<?>> members = decl.getMembers();
        List<BodyDeclaration<?>> toRemove = new ArrayList<>();
        boolean isInterface = decl instanceof ClassOrInterfaceDeclaration c && c.isInterface();

        for (BodyDeclaration<?> member : members) {
            if (member.isFieldDeclaration()) {
                pruneField(member.asFieldDeclaration(), isInterface, internalName, usedKeys, used, selfTypes,
                        classTypeParams, toRemove);
            } else if (member.isMethodDeclaration() || member.isConstructorDeclaration()
                    || member.isInitializerDeclaration()) {
                // Methods and constructors on a holder exist only to build the
                // registry values its fields used to hold; once those initializers
                // are gone, nothing outside the class has any business calling them.
                // Nested member types are left alone here — pruneType recurses into
                // them separately once this level is done.
                toRemove.add(member);
            }
        }
        members.removeAll(toRemove);
        removeOverrideAnnotations(decl);

        // `static { throw ...; }` does not compile: JLS 8.7 requires a static initializer
        // to be able to complete normally, and one whose only statement is a throw cannot.
        // Wrapping it in `if (true)` is the standard way out -- an `if` statement is
        // defined to complete normally whatever its condition, constant or not -- and it
        // still throws on every real class initialisation.
        String key = internalName;
        BlockStmt guarded = new BlockStmt(NodeList.nodeList(
                new IfStmt(new BooleanLiteralExpr(true), new BlockStmt(NodeList.nodeList(throwStatement(key))), null)));
        members.add(new InitializerDeclaration(true, guarded));
        addUnimplementedImport(cu);
    }

    /**
     * Keeps only the {@link VariableDeclarator}s of {@code f} whose {@code
     * name:descriptor} is in {@code usedKeys} (or, failing that, whose name alone
     * matches some used member of {@code internalName}), strips every surviving one's
     * initializer, and replaces it with a default literal when the field is {@code
     * final} (blank finals must be definitely assigned; a non-final field is simply
     * left uninitialized, defaulting to zero/{@code null} per the JVM). Queues {@code
     * f} itself for removal when no variable survives.
     *
     * <p>{@code declaringTypeIsInterface} treats the field as final even when {@link
     * FieldDeclaration#isFinal()} says otherwise: an interface field written {@code int
     * FOO = 5;} carries no explicit {@code final} modifier in source, but is final by
     * JLS regardless. Trusting the syntactic modifier alone would strip its initializer
     * without replacing it, emitting an uncompilable blank {@code int FOO;} inside the
     * interface.
     */
    private static void pruneField(FieldDeclaration f, boolean declaringTypeIsInterface, String internalName,
            SortedSet<String> usedKeys, UsedSet used, Map<String, String> selfTypes,
            Map<String, String> classTypeParams, List<BodyDeclaration<?>> toRemove) {
        boolean isFinal = f.isFinal() || declaringTypeIsInterface;
        CompilationUnit cu = f.findCompilationUnit().orElseThrow();
        NodeList<VariableDeclarator> vars = f.getVariables();
        List<VariableDeclarator> unused = new ArrayList<>();
        List<VariableDeclarator> kept = new ArrayList<>();
        for (VariableDeclarator v : vars) {
            String name = v.getNameAsString();
            String descriptor = typeDescriptor(cu, v.getType(), classTypeParams, selfTypes);
            String lookupKey = name + ":" + descriptor;
            boolean exact = usedKeys.contains(lookupKey);
            Set<String> referenced = new TreeSet<>();
            collectReferencedTypes(cu, v.getType(), classTypeParams, selfTypes, referenced);
            if (namesAbsentType(referenced)) {
                DROPPED_FOR_ABSENT_TYPE.add(internalName + "." + lookupKey);
                unused.add(v);
            } else if (exact || matchesByName(usedKeys, name)) {
                kept.add(v);
                reportKept(internalName + "." + lookupKey, exact, referenced, used);
            } else {
                unused.add(v);
            }
        }
        if (kept.isEmpty()) {
            toRemove.add(f);
            return;
        }
        vars.removeAll(unused);
        for (VariableDeclarator v : kept) {
            if (isFinal) {
                v.setInitializer(defaultLiteralFor(v.getType()));
            } else {
                v.removeInitializer();
            }
        }
    }

    private static Expression defaultLiteralFor(Type type) {
        if (type.isPrimitiveType()) {
            return switch (type.asPrimitiveType().getType()) {
                case BOOLEAN -> new BooleanLiteralExpr(false);
                // The two-character escape as *source text*, not a raw NUL byte: this
                // text is committed and reviewed as a diff, and a literal NUL corrupts
                // diffs and text tooling. Deliberately the String constructor (whose
                // argument is the source form printed between the quotes) and not
                // CharLiteralExpr.escape("\0"): that helper only escapes end-of-line
                // characters (Utils.escapeEndOfLines handles \n and \r and nothing
                // else), so a NUL passes through it unchanged.
                case CHAR -> new CharLiteralExpr("\\0");
                case LONG -> new LongLiteralExpr("0L");
                // Distinct from DOUBLE: "0.0" alone is a double literal, and a final
                // float field initialized with one is "incompatible types: possible
                // lossy conversion from double to float" under javac.
                case FLOAT -> new DoubleLiteralExpr("0.0F");
                case DOUBLE -> new DoubleLiteralExpr("0.0");
                case BYTE, SHORT, INT -> new IntegerLiteralExpr("0");
            };
        }
        return new NullLiteralExpr();
    }

    private static void replaceMethodBody(MethodDeclaration m, String key) {
        m.setBody(new BlockStmt(NodeList.nodeList(throwStatement(key))));
    }

    /**
     * Replaces a constructor's body with a throw and nothing else.
     *
     * <p>The original {@code super(...)}/{@code this(...)} call used to be preserved, on
     * the reasoning that synthesising one would need the superclass's constructors, which
     * are in another file. But preserving it keeps its <em>arguments</em>, and those are
     * real expressions calling real methods on real types -- the one place decompiled
     * bodies survived into the shim. On the first real run they produced "cannot find
     * symbol", "invalid method reference" and, where an argument's type no longer matched
     * an overload, "recursive constructor invocation".
     *
     * <p>Dropping it is safe because {@link #ensureNoArgConstructor} gives every generated
     * class an accessible no-argument constructor, so the implicit {@code super()} this
     * leaves behind always has something to bind to. A record is the exception and is
     * handled by {@link #delegateToCanonicalConstructor}: its non-canonical constructors
     * must delegate explicitly, and its canonical one is known exactly.
     */
    private static void replaceConstructorBody(ConstructorDeclaration c, String key) {
        c.setBody(new BlockStmt(NodeList.nodeList(throwStatement(key))));
    }

    /**
     * Rewrites a record's surviving constructors to {@code this(<defaults>)} followed by a
     * throw. Every non-canonical record constructor must begin with an explicit {@code
     * this(...)}, and the canonical one's parameter types are the component types, so
     * unlike the general case the delegation target and its argument types are both known
     * exactly -- a default literal per component is enough.
     */
    private static void delegateToCanonicalConstructor(RecordDeclaration decl) {
        for (BodyDeclaration<?> member : decl.getMembers()) {
            if (member.isConstructorDeclaration()) {
                ConstructorDeclaration c = member.asConstructorDeclaration();
                NodeList<Expression> arguments = new NodeList<>();
                for (Parameter component : decl.getParameters()) {
                    // Cast, always. A bare `null` makes the call ambiguous between two
                    // constructors of the same arity, and a bare `0` for a `byte`
                    // component is "possible lossy conversion" -- method invocation does
                    // not narrow. The component's own type is exactly the right cast.
                    arguments.add(new CastExpr(component.getType().clone(),
                            defaultLiteralFor(component.getType())));
                }
                NodeList<Statement> statements = new NodeList<>();
                statements.add(new ExplicitConstructorInvocationStmt(true, null, arguments));
                statements.addAll(c.getBody().getStatements());
                c.setBody(new BlockStmt(statements));
            }
        }
    }

    /**
     * Gives a class an accessible no-argument constructor when it declares none.
     *
     * <p>This is what lets {@link #replaceConstructorBody} drop every explicit {@code
     * super(...)}: the implicit {@code super()} that replaces it needs a no-argument
     * constructor on the superclass, and since every generated class gets one, the chain
     * resolves all the way up to {@code Object}. {@code protected}, not private, because
     * the subclass doing the implicit call is usually in another package.
     */
    private static void ensureNoArgConstructor(ClassOrInterfaceDeclaration decl, String internalName) {
        if (decl.isInterface()) {
            return;
        }
        for (ConstructorDeclaration existing : decl.getConstructors()) {
            if (existing.getParameters().isEmpty()) {
                // A private no-arg constructor is not reachable from the subclass whose
                // implicit super() now needs it -- LevelLightEngine declares exactly one,
                // and ThreadedLevelLightEngine sits in a different package. Widen it.
                if (existing.isPrivate()) {
                    existing.setModifier(Modifier.Keyword.PRIVATE, false);
                    existing.setModifier(Modifier.Keyword.PROTECTED, true);
                }
                return;
            }
        }
        // Empty, not throwing. This constructor is not a vanilla member and nothing calls
        // it on purpose: it exists so that a subclass's implicit super() resolves. Making
        // it throw would mean no generated object could ever be constructed, including by
        // the few classes whose real behaviour is re-applied by hand -- and the declared
        // constructor the caller actually named still throws, which is the honest signal.
        ConstructorDeclaration synthesised = decl.addConstructor(Modifier.Keyword.PROTECTED);
        synthesised.setBody(new BlockStmt());
    }

    /**
     * Drops {@code sealed}, {@code non-sealed} and the {@code permits} clause.
     *
     * <p>A permits clause names classes the closure would otherwise have to drag in purely
     * to satisfy it, and a subclass that survives when its sealed parent's permits list
     * did not is a hard error. Sealing constrains who may extend a type; the shim has no
     * stake in that, and mods are not adding subtypes to it either way.
     */
    private static void unseal(TypeDeclaration<?> type) {
        if (type instanceof ClassOrInterfaceDeclaration decl) {
            decl.getPermittedTypes().clear();
        }
        type.getModifiers().removeIf(m -> m.getKeyword() == Modifier.Keyword.SEALED
                || m.getKeyword() == Modifier.Keyword.NON_SEALED);
    }

    private static ThrowStmt throwStatement(String key) {
        MethodCallExpr call = new MethodCallExpr(new NameExpr("Unimplemented"), "forMember",
                NodeList.nodeList(new StringLiteralExpr(key)));
        return new ThrowStmt(call);
    }

    private static void removeOverrideAnnotations(TypeDeclaration<?> decl) {
        for (BodyDeclaration<?> member : decl.getMembers()) {
            if (member instanceof NodeWithAnnotations<?> annotated) {
                annotated.getAnnotations().removeIf(a -> "Override".equals(a.getNameAsString()));
            }
        }
    }

    /**
     * {@code true} if {@code usedKeys} contains any key at all for {@code name}. Used
     * only once the exact {@code name:descriptor} lookup has already missed; it
     * deliberately does not try to pick out "the right" overload once it fires: with
     * the descriptor already shown untrustworthy for this name, keeping every overload
     * declared under it is the failure-toward-keeping choice, not a guess at which one
     * is which.
     */
    private static boolean matchesByName(SortedSet<String> usedKeys, String name) {
        String prefix = name + ":";
        for (String key : usedKeys) {
            if (key.startsWith(prefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Records a kept member in {@link #KEPT_BY_FALLBACK} when it was only kept via the
     * by-name fallback (never for an exact match — that descriptor is trusted), and
     * checks every shimmed type {@code referenced} in its signature against {@code
     * used}'s closed class set, recording any that are missing in {@link
     * #MISSING_TYPES}. Missing-type reporting applies to every kept member, not only
     * fallback ones: an exact match is exact about the *member*, not proof that every
     * type in its signature was part of the closure {@link SupertypeCloser} built.
     */
    private static void reportKept(String key, boolean exact, Set<String> referencedTypes, UsedSet used) {
        if (!exact) {
            KEPT_BY_FALLBACK.add(key);
        }
        for (String referenced : referencedTypes) {
            String outer = Shimmed.outerOf(referenced);
            if (Shimmed.isShimmed(outer) && !used.classes().contains(outer)) {
                MISSING_TYPES.add(outer);
            }
        }
    }

    private static void addUnimplementedImport(CompilationUnit cu) {
        String qualifiedName = "dev.pumpkin.shim.Unimplemented";
        for (ImportDeclaration imp : cu.getImports()) {
            if (!imp.isAsterisk() && qualifiedName.equals(imp.getNameAsString())) {
                return;
            }
        }
        cu.addImport(qualifiedName);
    }

    // --- Descriptor synthesis -------------------------------------------------

    /**
     * Builds the JVM-style descriptor (erased, as a real member descriptor is) while
     * separately collecting every type actually named in the *source* signature —
     * parameters, return type, generic type arguments, and {@code throws} clauses —
     * into {@code referencedTypes}, for {@link #reportKept} to check against the used
     * set's closure. The two are deliberately different walks: a kept {@code
     * List<Ingredient>} erases to {@code Ljava/util/List;} for matching purposes, but
     * {@code Ingredient} is what the emitted source actually writes and needs
     * generated to compile — erasure hides exactly the thing this check exists to
     * catch.
     */
    private static String methodDescriptor(CompilationUnit cu, CallableDeclaration<?> callable, Type returnType,
            Map<String, String> selfTypes, Map<String, String> classTypeParams, Set<String> referencedTypes) {
        Map<String, String> typeParams = new HashMap<>(classTypeParams);
        for (TypeParameter tp : callable.getTypeParameters()) {
            typeParams.put(tp.getNameAsString(), boundOf(cu, tp, selfTypes));
        }
        StringBuilder sb = new StringBuilder("(");
        for (Parameter p : callable.getParameters()) {
            boolean varargs = p.isVarArgs();
            String descriptor = typeDescriptor(cu, p.getType(), typeParams, selfTypes);
            sb.append(varargs ? "[" + descriptor : descriptor);
            collectReferencedTypes(cu, p.getType(), typeParams, selfTypes, referencedTypes);
        }
        sb.append(")");
        sb.append(returnType == null ? "V" : typeDescriptor(cu, returnType, typeParams, selfTypes));
        if (returnType != null) {
            collectReferencedTypes(cu, returnType, typeParams, selfTypes, referencedTypes);
        }
        for (ReferenceType thrown : callable.getThrownExceptions()) {
            collectReferencedTypes(cu, thrown, typeParams, selfTypes, referencedTypes);
        }
        return sb.toString();
    }

    /** A type variable erases to its first bound, or to {@code Object} when it has none. */
    private static String boundOf(CompilationUnit cu, TypeParameter tp, Map<String, String> selfTypes) {
        if (tp.getTypeBound().isEmpty()) {
            return "java/lang/Object";
        }
        ClassOrInterfaceType bound = tp.getTypeBound().get(0);
        String self = selfTypes.get(bound.getNameAsString());
        return self != null && bound.getScope().isEmpty()
                ? self
                : SupertypeCloser.resolveScoped(cu, bound.getNameWithScope());
    }

    /**
     * {@code selfTypes} maps the pruned type's own simple name and every enclosing
     * type's simple name to its already-known internal name; checked before any
     * lookup-based resolution, since a self- or enclosing-reference needs no guess.
     * Erased: generic type arguments are deliberately not walked here, matching how a
     * real JVM descriptor is built. See {@link #collectReferencedTypes} for the
     * unerased walk used for missing-type reporting.
     */
    private static String typeDescriptor(CompilationUnit cu, Type type, Map<String, String> typeParams,
            Map<String, String> selfTypes) {
        if (type.isPrimitiveType()) {
            return type.asPrimitiveType().getType().toDescriptor();
        }
        if (type.isVoidType()) {
            return "V";
        }
        if (type.isArrayType()) {
            ArrayType at = type.asArrayType();
            return "[" + typeDescriptor(cu, at.getComponentType(), typeParams, selfTypes);
        }
        if (type.isClassOrInterfaceType()) {
            return "L" + resolveClassOrInterfaceType(cu, type.asClassOrInterfaceType(), typeParams, selfTypes) + ";";
        }
        return "Ljava/lang/Object;";
    }

    /**
     * Resolves a source type reference to an internal name, honouring whatever scope it
     * was written with: a bare {@code Properties}, an enclosing-class-qualified {@code
     * Item.Properties}, or a fully-qualified {@code java.util.Map.Entry}. Nested segments
     * are joined with {@code $}, which is what makes {@link Shimmed#outerOf} able to find
     * the file that actually declares the type.
     */
    private static String resolveClassOrInterfaceType(CompilationUnit cu, ClassOrInterfaceType cit,
            Map<String, String> typeParams, Map<String, String> selfTypes) {
        List<String> segments = scopeSegments(cit);
        String head = segments.get(0);
        if (segments.size() == 1) {
            String internal = typeParams.get(head);
            if (internal != null) {
                return internal;
            }
            String self = selfTypes.get(head);
            return self != null ? self : SupertypeCloser.resolve(cu, head);
        }
        if (Character.isLowerCase(head.charAt(0))) {
            return SupertypeCloser.internalNameOf(String.join(".", segments));
        }
        String self = selfTypes.get(head);
        StringBuilder sb = new StringBuilder(self != null ? self : SupertypeCloser.resolve(cu, head));
        for (int i = 1; i < segments.size(); i++) {
            sb.append('$').append(segments.get(i));
        }
        return sb.toString();
    }

    /** The scope chain of {@code cit}, outermost segment first. */
    private static List<String> scopeSegments(ClassOrInterfaceType cit) {
        List<String> segments = new ArrayList<>();
        for (ClassOrInterfaceType current = cit; current != null; current = current.getScope().orElse(null)) {
            segments.add(0, current.getNameAsString());
        }
        return segments;
    }

    /**
     * Walks a *source* type — parameters, return type, or a {@code throws} clause
     * entry — recursively through arrays, generic type arguments, and wildcard bounds,
     * recording the internal name of every object type it finds. Unlike {@link
     * #typeDescriptor}, this does not erase: a kept {@code List<Ingredient>} must
     * report {@code Ingredient}, not just the raw {@code List}, because {@code
     * Ingredient} is what the emitted source names and needs generated to compile.
     */
    private static void collectReferencedTypes(CompilationUnit cu, Type type, Map<String, String> typeParams,
            Map<String, String> selfTypes, Set<String> referencedTypes) {
        if (type.isArrayType()) {
            collectReferencedTypes(cu, type.asArrayType().getComponentType(), typeParams, selfTypes, referencedTypes);
            return;
        }
        if (type.isWildcardType()) {
            WildcardType wt = type.asWildcardType();
            wt.getExtendedType().ifPresent(t -> collectReferencedTypes(cu, t, typeParams, selfTypes, referencedTypes));
            wt.getSuperType().ifPresent(t -> collectReferencedTypes(cu, t, typeParams, selfTypes, referencedTypes));
            return;
        }
        if (!type.isClassOrInterfaceType()) {
            return;
        }
        ClassOrInterfaceType cit = type.asClassOrInterfaceType();
        referencedTypes.add(resolveClassOrInterfaceType(cu, cit, typeParams, selfTypes));
        cit.getTypeArguments().ifPresent(args -> {
            for (Type arg : args) {
                collectReferencedTypes(cu, arg, typeParams, selfTypes, referencedTypes);
            }
        });
    }
}
