package dev.pumpkin.shimgen;

import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.ast.ImportDeclaration;
import com.github.javaparser.ast.NodeList;
import com.github.javaparser.ast.body.BodyDeclaration;
import com.github.javaparser.ast.body.CallableDeclaration;
import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.body.ConstructorDeclaration;
import com.github.javaparser.ast.body.FieldDeclaration;
import com.github.javaparser.ast.body.InitializerDeclaration;
import com.github.javaparser.ast.body.MethodDeclaration;
import com.github.javaparser.ast.body.Parameter;
import com.github.javaparser.ast.body.TypeDeclaration;
import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.expr.BooleanLiteralExpr;
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
import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ExplicitConstructorInvocationStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.ThrowStmt;
import com.github.javaparser.ast.type.ArrayType;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import com.github.javaparser.ast.type.PrimitiveType;
import com.github.javaparser.ast.type.Type;
import com.github.javaparser.ast.type.TypeParameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.SortedSet;

/**
 * Decides each {@code net.minecraft}/{@code net.neoforged} type's {@link Treatment}
 * and, given a {@link UsedSet}, prunes it down to that treatment's shape.
 *
 * <p>Resolution of parameter/field/return types to internal names is syntactic and
 * reuses {@link SupertypeCloser#resolve}: there is no classpath available while
 * generating, so both classes face exactly the same problem, and this one shares that
 * solution rather than carrying a second, independently-drifting guess at the same
 * rules (import, then the compilation unit's own package, then {@code java.lang}).
 *
 * <p>That resolution is still a guess — a decompiled file essentially never imports
 * {@code java.lang} explicitly, so an unqualified {@code String} resolves (wrongly) into
 * this file's own package by that algorithm's own rules, and a self-referential nested
 * type name (a builder returning its own enclosing-qualified type) resolves without the
 * {@code Outer$} prefix a real descriptor would carry. Rather than trying to special-case
 * every such miss, every used-member lookup here fails toward keeping: if the exact
 * {@code name:descriptor} is not found, any other member of that name at that owner —
 * regardless of descriptor — is enough to keep it. Over-keeping costs a harmless extra
 * stubbed overload; under-keeping silently deletes a member the mods actually call, which
 * only surfaces later as a link failure naming a method with no pointer to why it
 * vanished.
 */
public final class Pruner {
    private Pruner() {}

    /**
     * VALUE for an enum or a record, and — vanishingly rarely on real Minecraft source
     * — a class whose every declared field is a {@code static final} primitive or
     * {@code String} constant. HOLDER for a class whose every declared field is {@code
     * static final} with an initializer and which declares no instance method (a
     * private no-arg constructor does not count against it: {@code Registries}, {@code
     * BlockTags}, {@code ItemTags} and {@code ParticleTypes} all declare one precisely
     * to prevent instantiation). HANDLE otherwise.
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
            if (!decl.isInterface()) {
                if (isPrimitiveOrStringConstantClass(decl)) {
                    return Treatment.VALUE;
                }
                if (isHolder(decl)) {
                    return Treatment.HOLDER;
                }
            }
        }
        return Treatment.HANDLE;
    }

    private static boolean isPrimitiveOrStringConstantClass(ClassOrInterfaceDeclaration decl) {
        List<FieldDeclaration> fields = decl.getFields();
        if (fields.isEmpty()) {
            return false;
        }
        for (FieldDeclaration f : fields) {
            if (!f.isStatic() || !f.isFinal()) {
                return false;
            }
            for (VariableDeclarator v : f.getVariables()) {
                if (v.getInitializer().isEmpty() || !isPrimitiveOrString(v.getType())) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isPrimitiveOrString(Type type) {
        if (type.isPrimitiveType()) {
            return true;
        }
        return type.isClassOrInterfaceType() && "String".equals(type.asClassOrInterfaceType().getNameAsString());
    }

    private static boolean isHolder(ClassOrInterfaceDeclaration decl) {
        List<FieldDeclaration> fields = decl.getFields();
        if (fields.isEmpty()) {
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
    public static void prune(CompilationUnit cu, String internalName, UsedSet used) {
        if (cu.getTypes().isEmpty()) {
            return;
        }
        pruneType(cu, cu.getType(0), internalName, used);
    }

    private static void pruneType(CompilationUnit cu, TypeDeclaration<?> type, String internalName, UsedSet used) {
        Treatment treatment = treatmentOf(type);
        if (treatment == Treatment.VALUE) {
            return;
        }
        if (!type.isClassOrInterfaceDeclaration()) {
            return;
        }
        ClassOrInterfaceDeclaration decl = type.asClassOrInterfaceDeclaration();
        if (treatment == Treatment.HANDLE) {
            pruneHandle(cu, decl, internalName, used);
        } else {
            pruneHolder(cu, decl, internalName, used);
        }
        for (BodyDeclaration<?> member : decl.getMembers()) {
            if (member instanceof TypeDeclaration<?> nested) {
                pruneType(cu, nested, internalName + "$" + nested.getNameAsString(), used);
            }
        }
    }

    private static void pruneHandle(CompilationUnit cu, ClassOrInterfaceDeclaration decl, String internalName,
            UsedSet used) {
        SortedSet<String> usedKeys = used.membersOf(internalName);
        NodeList<BodyDeclaration<?>> members = decl.getMembers();
        List<BodyDeclaration<?>> toRemove = new ArrayList<>();
        boolean[] threw = {false};

        for (BodyDeclaration<?> member : members) {
            if (member.isFieldDeclaration()) {
                pruneField(member.asFieldDeclaration(), usedKeys, toRemove);
            } else if (member.isMethodDeclaration()) {
                MethodDeclaration m = member.asMethodDeclaration();
                if (m.getBody().isEmpty()) {
                    // An abstract method (interface method, or abstract class member)
                    // declared by this very type. There is no body to prune to a
                    // throw, and removing it risks the type no longer satisfying a
                    // contract it declares, so it is always kept untouched.
                    continue;
                }
                String name = m.getNameAsString();
                String lookupKey = name + ":" + methodDescriptor(cu, decl, m, m.getType());
                if (matchesUsed(usedKeys, name, lookupKey)) {
                    String key = internalName + "." + lookupKey;
                    replaceMethodBody(m, key);
                    threw[0] = true;
                } else {
                    toRemove.add(member);
                }
            } else if (member.isConstructorDeclaration()) {
                ConstructorDeclaration c = member.asConstructorDeclaration();
                String lookupKey = "<init>:" + methodDescriptor(cu, decl, c, null);
                if (matchesUsed(usedKeys, "<init>", lookupKey)) {
                    String key = internalName + "." + lookupKey;
                    replaceConstructorBody(c, key);
                    threw[0] = true;
                } else {
                    toRemove.add(member);
                }
            }
        }

        members.removeAll(toRemove);
        removeOverrideAnnotations(decl);
        if (threw[0]) {
            addUnimplementedImport(cu);
        }
    }

    private static void pruneHolder(CompilationUnit cu, ClassOrInterfaceDeclaration decl, String internalName,
            UsedSet used) {
        SortedSet<String> usedKeys = used.membersOf(internalName);
        NodeList<BodyDeclaration<?>> members = decl.getMembers();
        List<BodyDeclaration<?>> toRemove = new ArrayList<>();

        for (BodyDeclaration<?> member : members) {
            if (member.isFieldDeclaration()) {
                pruneField(member.asFieldDeclaration(), usedKeys, toRemove);
            } else if (member.isMethodDeclaration() || member.isConstructorDeclaration()) {
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

        String key = internalName;
        BlockStmt body = new BlockStmt(NodeList.nodeList(throwStatement(key)));
        members.add(new InitializerDeclaration(true, body));
        addUnimplementedImport(cu);
    }

    /**
     * Keeps only the {@link VariableDeclarator}s of {@code f} whose {@code
     * name:descriptor} is in {@code usedKeys}, strips every surviving one's
     * initializer, and replaces it with a default literal when the field is {@code
     * final} (blank finals must be definitely assigned; a non-final field is simply
     * left uninitialized, defaulting to zero/{@code null} per the JVM). Queues {@code
     * f} itself for removal when no variable survives.
     */
    private static void pruneField(FieldDeclaration f, SortedSet<String> usedKeys, List<BodyDeclaration<?>> toRemove) {
        boolean isFinal = f.isFinal();
        NodeList<VariableDeclarator> vars = f.getVariables();
        List<VariableDeclarator> unused = new ArrayList<>();
        List<VariableDeclarator> kept = new ArrayList<>();
        for (VariableDeclarator v : vars) {
            String name = v.getNameAsString();
            String descriptor = typeDescriptor(f.findCompilationUnit().orElseThrow(), v.getType(), Map.of());
            String lookupKey = name + ":" + descriptor;
            if (matchesUsed(usedKeys, name, lookupKey)) {
                kept.add(v);
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
                case CHAR -> new CharLiteralExpr('\0');
                case LONG -> new LongLiteralExpr("0L");
                case FLOAT, DOUBLE -> new DoubleLiteralExpr("0.0");
                case BYTE, SHORT, INT -> new IntegerLiteralExpr("0");
            };
        }
        return new NullLiteralExpr();
    }

    private static void replaceMethodBody(MethodDeclaration m, String key) {
        m.setBody(new BlockStmt(NodeList.nodeList(throwStatement(key))));
    }

    /**
     * Replaces a constructor's body with a throw, preserving whatever explicit {@code
     * super(...)}/{@code this(...)} call it already had as the first statement (and
     * adding nothing when it had none). Deciding whether to *synthesise* a super call
     * would require resolving the superclass's constructors, which is not available
     * here; preserving what is already there is well-defined without it.
     */
    private static void replaceConstructorBody(ConstructorDeclaration c, String key) {
        NodeList<Statement> original = c.getBody().getStatements();
        ExplicitConstructorInvocationStmt keep = null;
        if (!original.isEmpty() && original.get(0) instanceof ExplicitConstructorInvocationStmt eci) {
            keep = eci;
        }
        NodeList<Statement> newStatements = new NodeList<>();
        if (keep != null) {
            keep.removeForced();
            newStatements.add(keep);
        }
        newStatements.add(throwStatement(key));
        c.setBody(new BlockStmt(newStatements));
    }

    private static ThrowStmt throwStatement(String key) {
        MethodCallExpr call = new MethodCallExpr(new NameExpr("Unimplemented"), "forMember",
                NodeList.nodeList(new StringLiteralExpr(key)));
        return new ThrowStmt(call);
    }

    private static void removeOverrideAnnotations(ClassOrInterfaceDeclaration decl) {
        for (BodyDeclaration<?> member : decl.getMembers()) {
            if (member instanceof NodeWithAnnotations<?> annotated) {
                annotated.getAnnotations().removeIf(a -> "Override".equals(a.getNameAsString()));
            }
        }
    }

    /**
     * {@code true} if {@code usedKeys} contains {@code exactLookupKey} exactly, or —
     * failing that — contains any key at all for {@code name}. The exact match is
     * always tried first and is exact whenever type resolution happened to get every
     * parameter/return/field type right; the by-name fallback is what keeps a member
     * safe when it did not. It deliberately does not try to pick out "the right"
     * overload once it falls back: with the descriptor already shown untrustworthy for
     * this name, keeping every overload declared under it is the failure-toward-keeping
     * choice, not a guess at which one is which.
     */
    private static boolean matchesUsed(SortedSet<String> usedKeys, String name, String exactLookupKey) {
        if (usedKeys.contains(exactLookupKey)) {
            return true;
        }
        String prefix = name + ":";
        for (String key : usedKeys) {
            if (key.startsWith(prefix)) {
                return true;
            }
        }
        return false;
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

    private static String methodDescriptor(CompilationUnit cu, ClassOrInterfaceDeclaration owner,
            CallableDeclaration<?> callable, Type returnType) {
        Map<String, String> typeParams = typeParamBounds(cu, owner, callable);
        StringBuilder sb = new StringBuilder("(");
        List<Parameter> params = callable.getParameters();
        for (int i = 0; i < params.size(); i++) {
            Parameter p = params.get(i);
            boolean varargs = p.isVarArgs();
            String descriptor = typeDescriptor(cu, p.getType(), typeParams);
            sb.append(varargs ? "[" + descriptor : descriptor);
        }
        sb.append(")");
        sb.append(returnType == null ? "V" : typeDescriptor(cu, returnType, typeParams));
        return sb.toString();
    }

    private static Map<String, String> typeParamBounds(CompilationUnit cu, ClassOrInterfaceDeclaration owner,
            CallableDeclaration<?> callable) {
        Map<String, String> map = new HashMap<>();
        for (TypeParameter tp : owner.getTypeParameters()) {
            map.put(tp.getNameAsString(), boundOf(cu, tp));
        }
        for (TypeParameter tp : callable.getTypeParameters()) {
            map.put(tp.getNameAsString(), boundOf(cu, tp));
        }
        return map;
    }

    private static String boundOf(CompilationUnit cu, TypeParameter tp) {
        if (tp.getTypeBound().isEmpty()) {
            return "java/lang/Object";
        }
        return SupertypeCloser.resolve(cu, tp.getTypeBound().get(0).getNameAsString());
    }

    private static String typeDescriptor(CompilationUnit cu, Type type, Map<String, String> typeParams) {
        if (type.isPrimitiveType()) {
            return type.asPrimitiveType().getType().toDescriptor();
        }
        if (type.isVoidType()) {
            return "V";
        }
        if (type.isArrayType()) {
            ArrayType at = type.asArrayType();
            return "[" + typeDescriptor(cu, at.getComponentType(), typeParams);
        }
        if (type.isClassOrInterfaceType()) {
            ClassOrInterfaceType cit = type.asClassOrInterfaceType();
            String simpleName = cit.getNameAsString();
            String bound = typeParams.get(simpleName);
            if (bound != null) {
                return "L" + bound + ";";
            }
            String internal = qualifiedInternalName(cit).orElseGet(() -> SupertypeCloser.resolve(cu, simpleName));
            return "L" + internal + ";";
        }
        return "Ljava/lang/Object;";
    }

    /**
     * When a type is written with an explicit scope in source ({@code java.util.List},
     * not {@code List}), reconstructs the dotted name directly rather than going
     * through import/package resolution. Best-effort: this also matches an
     * outer-class-qualified nested type reference like {@code Map.Entry}, which is not
     * a package path, but real decompiled Minecraft source does not write types that
     * way, so the ambiguity does not arise in practice.
     */
    private static Optional<String> qualifiedInternalName(ClassOrInterfaceType cit) {
        if (cit.getScope().isEmpty()) {
            return Optional.empty();
        }
        StringBuilder sb = new StringBuilder(cit.getNameAsString());
        Optional<ClassOrInterfaceType> scope = cit.getScope();
        while (scope.isPresent()) {
            sb.insert(0, scope.get().getNameAsString() + "/");
            scope = scope.get().getScope();
        }
        return Optional.of(sb.toString());
    }
}
