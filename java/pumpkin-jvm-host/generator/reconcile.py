"""Re-applies the Pumpkin divergences on top of freshly generated shim sources.

Generation overwrites every file under shim/src/main/java, and eight of them carry
behaviour the generator cannot produce: members vanilla has no counterpart for (the
registration sink, the block template), and bodies the pruner stubbed that the mod
loading path actually executes. Each one is marked in the emitted source with the
comment "Pumpkin divergence", saying what it is and why it exists.

This script is how they are re-applied, and it is deliberately brittle: every edit
matches the exact generated text, and a miss aborts the run rather than half-applying.
If the generator's output for one of these members changes, this fails loudly and the
edit gets re-thought, which is the whole point. regen.sh runs it.
"""
import os, re, sys

ROOT = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "shim", "src", "main", "java")

# Every edit is staged here and written only once all of them have succeeded. Applying
# them one file at a time left a half-reconciled tree behind on any failure, and that tree
# does not compile -- which blocks the very regen.sh run that would repair it, since it
# builds the testmod jar it scans from these sources.
PENDING = {}


def edit(path, pairs, drop_imports=()):
    p = os.path.join(ROOT, path)
    s = PENDING.get(p, None)
    if s is None:
        s = open(p).read()
    for a, b in pairs:
        if a not in s:
            sys.exit("MISSING in %s:\n%s" % (path, a[:200]))
        s = s.replace(a, b, 1)
    for imp in drop_imports:
        s = s.replace(imp, "", 1)
    PENDING[p] = s


def commit():
    for p, s in PENDING.items():
        open(p, "w").write(s)
        print("reconciled", os.path.relpath(p, ROOT))

# ---------------------------------------------------------------- Identifier
edit("net/minecraft/resources/Identifier.java", [
("""public final class Identifier implements Comparable<Identifier> {
""",
"""public final class Identifier implements Comparable<Identifier> {

    // Pumpkin divergence from the generated shim: these two fields, and the members below
    // marked the same way, carry real behaviour. Vanilla builds an Identifier out of an
    // interner and a Codec, neither of which the shim has; but an id is a pair of strings,
    // Pumpkin's block registration is keyed on its printed form, and a stub that throws
    // here would stop every mod before it registered anything. Re-apply by hand after any
    // regeneration -- grep for "Pumpkin divergence".
    private final String pumpkinNamespace;

    private final String pumpkinPath;
"""),
("""    private Identifier(String namespace, String path) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.<init>:(Ljava/lang/String;Ljava/lang/String;)V");
    }""",
"""    // Pumpkin divergence: real body.
    private Identifier(String namespace, String path) {
        this.pumpkinNamespace = namespace;
        this.pumpkinPath = path;
    }"""),
("""    public static Identifier fromNamespaceAndPath(String namespace, String path) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.fromNamespaceAndPath:(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }""",
"""    // Pumpkin divergence: real body.
    public static Identifier fromNamespaceAndPath(String namespace, String path) {
        return new Identifier(namespace, path);
    }"""),
("""    public static Identifier parse(String identifier) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.parse:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }""",
"""    // Pumpkin divergence: real body. The same one-line rule vanilla uses -- everything
    // before the colon is the namespace, everything after is the path, and a bare path is
    // "minecraft".
    public static Identifier parse(String identifier) {
        int colon = identifier.indexOf(':');
        return colon < 0
                ? new Identifier("minecraft", identifier)
                : new Identifier(identifier.substring(0, colon), identifier.substring(colon + 1));
    }"""),
("""    public String getPath() {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.getPath:()Ljava/lang/String;");
    }""",
"""    // Pumpkin divergence: real body.
    public String getPath() {
        return pumpkinPath;
    }"""),
("""    public String getNamespace() {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.getNamespace:()Ljava/lang/String;");
    }""",
"""    // Pumpkin divergence: real body.
    public String getNamespace() {
        return pumpkinNamespace;
    }"""),
("""    public Identifier withSuffix(String suffix) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.withSuffix:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }""",
"""    // Pumpkin divergence: real body.
    public Identifier withSuffix(String suffix) {
        return new Identifier(pumpkinNamespace, pumpkinPath + suffix);
    }"""),
("""    public String toString() {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.toString:()Ljava/lang/String;");
    }""",
"""    // Pumpkin divergence: real body. This is the string Pumpkin registers a block under,
    // so DeferredRegister's flush depends on it directly.
    public String toString() {
        return pumpkinNamespace + ":" + pumpkinPath;
    }"""),
("""    public boolean equals(Object o) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.equals:(Ljava/lang/Object;)Z");
    }""",
"""    // Pumpkin divergence: real body. An id is a value and is used as a map key.
    public boolean equals(Object o) {
        return o instanceof Identifier other
                && pumpkinNamespace.equals(other.pumpkinNamespace)
                && pumpkinPath.equals(other.pumpkinPath);
    }"""),
("""    public int hashCode() {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.hashCode:()I");
    }""",
"""    // Pumpkin divergence: real body.
    public int hashCode() {
        return 31 * pumpkinNamespace.hashCode() + pumpkinPath.hashCode();
    }"""),
("""    public int compareTo(Identifier o) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.compareTo:(Lnet/minecraft/resources/Identifier;)I");
    }""",
"""    // Pumpkin divergence: real body.
    public int compareTo(Identifier o) {
        int byPath = pumpkinPath.compareTo(o.pumpkinPath);
        return byPath != 0 ? byPath : pumpkinNamespace.compareTo(o.pumpkinNamespace);
    }"""),
("""    protected Identifier() {
    }""",
"""    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class so a subclass's implicit super() resolves. Identifier is final and has
    // two final fields, so this one has to assign them.
    private Identifier() {
        this("minecraft", "");
    }"""),
])

# --------------------------------------------------------------- ResourceKey
edit("net/minecraft/resources/ResourceKey.java", [
("""    private final Identifier identifier = null;
""",
"""    // Pumpkin divergence from the generated shim: this field and the members below marked
    // the same way carry real behaviour. A ResourceKey is a pair of names, and Registries
    // is nothing but a list of them; a throwing stub here means no mod can name the
    // registry it wants to add to. Re-apply by hand after any regeneration -- grep for
    // "Pumpkin divergence".
    private final Identifier pumpkinRegistryName;

    private final Identifier identifier;
"""),
("""    public static <T> ResourceKey<T> create(ResourceKey<? extends Registry<T>> registryName, Identifier location) {
        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.create:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/resources/ResourceKey;");
    }""",
"""    // Pumpkin divergence: real body.
    public static <T> ResourceKey<T> create(ResourceKey<? extends Registry<T>> registryName, Identifier location) {
        return new ResourceKey<>(registryName.identifier(), location);
    }"""),
("""    private static <T> ResourceKey<T> create(Identifier registryName, Identifier identifier) {
        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.create:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/resources/ResourceKey;");
    }""",
"""    // Pumpkin divergence: real body. Vanilla interns these; the shim does not, and the
    // equals/hashCode contract below makes interning unobservable.
    private static <T> ResourceKey<T> create(Identifier registryName, Identifier identifier) {
        return new ResourceKey<>(registryName, identifier);
    }

    // Pumpkin divergence: no mod calls this, so the pruner drops it, but Registries needs
    // it to name the root registry. Vanilla's own createRegistryKey does exactly this.
    public static <T> ResourceKey<Registry<T>> createRegistryKey(Identifier identifier) {
        return new ResourceKey<>(Identifier.fromNamespaceAndPath("minecraft", "root"), identifier);
    }"""),
("""    private ResourceKey(Identifier registryName, Identifier identifier) {
        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.<init>:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/Identifier;)V");
    }""",
"""    // Pumpkin divergence: real body.
    private ResourceKey(Identifier registryName, Identifier identifier) {
        this.pumpkinRegistryName = registryName;
        this.identifier = identifier;
    }"""),
("""    public String toString() {
        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.toString:()Ljava/lang/String;");
    }""",
"""    // Pumpkin divergence: real body.
    public String toString() {
        return "ResourceKey[" + pumpkinRegistryName + " / " + identifier + "]";
    }"""),
("""    public Identifier identifier() {
        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.identifier:()Lnet/minecraft/resources/Identifier;");
    }""",
"""    // Pumpkin divergence: real body.
    public Identifier identifier() {
        return identifier;
    }"""),
("""    public int compareTo(ResourceKey<?> o) {
        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.compareTo:(Lnet/minecraft/resources/ResourceKey;)I");
    }""",
"""    // Pumpkin divergence: real body.
    public int compareTo(ResourceKey<?> o) {
        int byRegistry = pumpkinRegistryName.compareTo(o.pumpkinRegistryName);
        return byRegistry != 0 ? byRegistry : identifier.compareTo(o.identifier);
    }"""),
("""    protected ResourceKey() {
    }""",
"""    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has two final fields to assign, so it delegates.
    protected ResourceKey() {
        this(Identifier.fromNamespaceAndPath("minecraft", "root"), Identifier.fromNamespaceAndPath("minecraft", ""));
    }"""),
])

# ------------------------------------------------------- BuiltInRegistries
# The pruner already gives every field here a stub, because their types are shimmed
# interfaces -- that rule is general and lives in the generator. What it cannot know is
# which registry each field *is*, and DeferredRegister.create(Registry, String) asks
# exactly that. Upgrading the generated stub with a key() answer is Minecraft knowledge,
# so it lives here with the other divergences rather than in the pruner.
p = os.path.join(ROOT, "net/minecraft/core/registries/BuiltInRegistries.java")
s = open(p).read()

# field -> the vanilla registry name it identifies as. Built inline rather than read from
# Registries: the pruner keeps only the Registries fields the mods name, and two of these
# six did not survive.
IDENTITIES = {
    "SOUND_EVENT": "sound_event",
    "FLUID": "fluid",
    "ENTITY_TYPE": "entity_type",
    "ITEM": "item",
    "RECIPE_TYPE": "recipe_type",
    "RECIPE_SERIALIZER": "recipe_serializer",
}

for field, registry_name in IDENTITIES.items():
    match = re.search(r"( " + field + r" = Stubs\.of\((\w+)\.class, \"([^\"]+)\"\));", s)
    if not match:
        sys.exit("BuiltInRegistries: no generated stub for " + field)
    upgraded = (' %s = Stubs.of(%s.class, "%s", java.util.Map.of("key",'
                ' ResourceKey.createRegistryKey('
                'Identifier.fromNamespaceAndPath("minecraft", "%s"))));'
                % (field, match.group(2), match.group(3), registry_name))
    s = s[:match.start()] + upgraded + s[match.end():]

leftover = re.findall(r"^\s+public static final \w+<[^;]+> (\w+) = null;", s, re.M)
if leftover:
    sys.exit("BuiltInRegistries: still null after reconcile: " + ", ".join(leftover))

s = s.replace("import dev.pumpkin.shim.Unimplemented;",
              "import net.minecraft.resources.Identifier;\n"
              "import net.minecraft.resources.ResourceKey;\n"
              "import dev.pumpkin.shim.Unimplemented;", 1)
PENDING[p] = s

# ---------------------------------------------------------------- Registries
import re
p = os.path.join(ROOT, "net/minecraft/core/registries/Registries.java")
s = open(p).read()
NAMES = {
    "BLOCK_ENTITY_TYPE": "block_entity_type", "BLOCK": "block",
    "CREATIVE_MODE_TAB": "creative_mode_tab", "DATA_COMPONENT_TYPE": "data_component_type",
    "ENTITY_TYPE": "entity_type", "FEATURE": "worldgen/feature", "ITEM": "item",
    "MENU": "menu", "RECIPE_SERIALIZER": "recipe_serializer", "RECIPE_TYPE": "recipe_type",
    "ENCHANTMENT": "enchantment", "RECIPE": "recipe",
}
for field, name in NAMES.items():
    pat = re.compile(r"(public static final ResourceKey<Registry<[^;]*?>> " + field + r") = null;")
    s2 = pat.sub(lambda m: m.group(1) + ' = pumpkinRegistryKey("' + name + '");', s, count=1)
    if s2 == s:
        sys.exit("Registries: no field " + field)
    s = s2
# Every key the pruner emitted must have been claimed above. An unclaimed one is left as
# `= null`, and once the throwing static initializer below is removed, reading it hands a
# mod a null instead of failing -- an NPE at registration, in the mod's code, naming
# nothing. That is the silent-wrong-value failure this shim exists to avoid, so a new
# registry key appearing in the used set has to stop this script rather than pass it.
unclaimed = re.findall(r"ResourceKey<Registry<[^;]*?>> ([A-Z_0-9]+) = null;", s)
if unclaimed:
    sys.exit("Registries: no name known for " + ", ".join(unclaimed)
             + " -- add it to NAMES with its vanilla registry name")

old = """    static {
        if (true) {
            throw Unimplemented.forMember("net/minecraft/core/registries/Registries");
        }
    }
}"""
new = """    // Pumpkin divergence from the generated shim: every key above is initialised, and the
    // throwing static initializer the pruner writes for a constants-holder is gone.
    //
    // The pruner treats Registries as a HOLDER -- a class of static finals whose real
    // initializers call registry code the shim does not have -- and makes touching it fail
    // loudly. That is the right default and the wrong answer here: a registry key is a pair
    // of names and nothing else, so the shim can supply the true value rather than a stub.
    // Without it, reading Registries.BLOCK throws during a mod's static initialisation and
    // no mod ever reaches its first registration.
    //
    // Re-apply by hand after any regeneration -- grep for "Pumpkin divergence".
    private static <T> ResourceKey<Registry<T>> pumpkinRegistryKey(String name) {
        return ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", name));
    }
}"""
if old not in s:
    sys.exit("Registries: static initializer not found")
s = s.replace(old, new, 1)
s = s.replace("import net.minecraft.resources.ResourceKey;",
              "import net.minecraft.resources.Identifier;\nimport net.minecraft.resources.ResourceKey;", 1)
s = s.replace("import dev.pumpkin.shim.Unimplemented;\n", "", 1)
PENDING[p] = s

# --------------------------------------------------------------------- Block
edit("net/minecraft/world/level/block/Block.java", [
("""public class Block extends BlockBehaviour implements ItemLike, IBlockExtension {
""",
"""public class Block extends BlockBehaviour implements ItemLike, IBlockExtension {

    // Pumpkin divergence from the generated shim: this field, the constructor below and
    // pumpkinTemplate() have no vanilla counterpart in this form. Pumpkin registers a block
    // by copying a vanilla one, so a Block has to remember which vanilla block it was built
    // from; the property builder is where a mod says so. Re-apply by hand after any
    // regeneration -- grep for "Pumpkin divergence".
    private final BlockBehaviour.Properties pumpkinProperties;
"""),
("""    public Block(BlockBehaviour.Properties properties) {
        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.<init>:(Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;)V");
    }""",
"""    // Pumpkin divergence: real body. Vanilla's constructor builds a state definition and a
    // registry holder; the shim keeps only the one thing registration reads back.
    public Block(BlockBehaviour.Properties properties) {
        this.pumpkinProperties = properties;
    }

    // Pumpkin divergence: no vanilla counterpart at all. The vanilla block whose definition
    // Pumpkin copies when it registers this one; DeferredRegister's flush passes it to the
    // native registerBlock.
    public String pumpkinTemplate() {
        return pumpkinProperties.template();
    }"""),
("""    protected Block() {
    }""",
"""    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has a final field to assign, so it delegates.
    protected Block() {
        this(BlockBehaviour.Properties.of());
    }"""),
])

# ----------------------------------------------------------- BlockBehaviour
edit("net/minecraft/world/level/block/state/BlockBehaviour.java", [
("""    public static class Properties {
""",
"""    public static class Properties {

        // Pumpkin divergence from the generated shim: this field, pumpkinTemplate(String)
        // and template() have no vanilla counterpart, and of() below has a real body.
        // Pumpkin registers a block by copying a vanilla one, so something has to name the
        // template; a mod that never says gets stone. Re-apply by hand after any
        // regeneration -- grep for "Pumpkin divergence".
        private String pumpkinTemplate = "stone";

        // Pumpkin divergence: no vanilla counterpart. Names the vanilla block to copy.
        public Properties pumpkinTemplate(String template) {
            this.pumpkinTemplate = template;
            return this;
        }

        // Pumpkin divergence: no vanilla counterpart. Read by Block.pumpkinTemplate().
        public String template() {
            return pumpkinTemplate;
        }
"""),
("""        protected Properties() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.<init>:()V");
        }""",
"""        // Pumpkin divergence: real body. of() below is the only way a mod gets one of
        // these, and it has to return something the builder calls can chain off.
        protected Properties() {
        }"""),
("""        public static BlockBehaviour.Properties of() {
            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.of:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");
        }""",
"""        // Pumpkin divergence: real body.
        public static BlockBehaviour.Properties of() {
            return new Properties();
        }"""),
])

# ------------------------------------------------------------ DeferredHolder
edit("net/neoforged/neoforge/registries/DeferredHolder.java", [
("""public class DeferredHolder<R, T extends R> implements Holder<R>, Supplier<T> {
""",
"""public class DeferredHolder<R, T extends R> implements Holder<R>, Supplier<T> {

    // Pumpkin divergence from the generated shim: these three fields, the constructor and
    // get()/getId() below carry real behaviour. A DeferredHolder is the handle a mod keeps
    // onto something it registered, and DeferredRegister's flush reads both the id and the
    // value out of it; a throwing stub here means nothing is ever registered. Re-apply by
    // hand after any regeneration -- grep for "Pumpkin divergence".
    private final Identifier pumpkinId;

    private final Supplier<T> pumpkinFactory;

    private T pumpkinValue;
"""),
("""    protected DeferredHolder(ResourceKey<R> key) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.<init>:(Lnet/minecraft/resources/ResourceKey;)V");
    }""",
"""    // Pumpkin divergence: real body.
    protected DeferredHolder(ResourceKey<R> key) {
        this(key.identifier(), null);
    }

    // Pumpkin divergence: no vanilla counterpart. DeferredRegister.register builds holders
    // through this; vanilla resolves them out of a real registry instead.
    DeferredHolder(Identifier id, Supplier<T> factory) {
        this.pumpkinId = id;
        this.pumpkinFactory = factory;
    }"""),
("""    public T get() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.get:()Lnet/neoforged/neoforge/registries/R;");
    }""",
"""    // Pumpkin divergence: real body. Resolves once, on first use, which is what makes the
    // registration deferred.
    public T get() {
        if (pumpkinValue == null) {
            pumpkinValue = pumpkinFactory.get();
        }
        return pumpkinValue;
    }"""),
("""    public Identifier getId() {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.getId:()Lnet/minecraft/resources/Identifier;");
    }""",
"""    // Pumpkin divergence: real body. Its printed form is the id Pumpkin registers under.
    public Identifier getId() {
        return pumpkinId;
    }"""),
("""    protected DeferredHolder() {
    }""",
"""    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has final fields to assign, so it delegates.
    protected DeferredHolder() {
        this(Identifier.fromNamespaceAndPath("minecraft", "air"), null);
    }"""),
])

# ----------------------------------------------------------- DeferredRegister
edit("net/neoforged/neoforge/registries/DeferredRegister.java", [
    ('    public static <T> DeferredRegister<T> create(Registry<T> registry, String namespace) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.create:(Lnet/minecraft/core/Registry;Ljava/lang/String;)Lnet/neoforged/neoforge/registries/DeferredRegister;");\n    }',
     '    // Pumpkin divergence: real body. A registry knows which registry it is, so this needs\n    // nothing the ResourceKey overload does not already do. The BuiltInRegistries stubs\n    // answer key() for exactly this call.\n    public static <T> DeferredRegister<T> create(Registry<T> registry, String namespace) {\n        return create(registry.key(), namespace);\n    }'),

("""public class DeferredRegister<T> {
""",
"""public class DeferredRegister<T> {

    // Pumpkin divergence from the generated shim: everything in this block, plus the five
    // members below marked the same way, carries real behaviour that vanilla NeoForge has
    // no counterpart for or that the pruner stubbed out.
    //
    // The sink exists so that the shim need not depend on `host`: NeoForge's
    // DeferredRegister writes into the game's own registries, and Pumpkin's live in Rust
    // behind a JNI native. Bootstrap.installDefaultSink points this at
    // PumpkinHost::registerBlock during boot; tests install their own. Without it a
    // registration has nowhere to go.
    //
    // Re-apply by hand after any regeneration -- grep for "Pumpkin divergence".

    /** Where a registration ends up. Returns the assigned id. */
    @FunctionalInterface
    public interface Sink {
        int registerBlock(String id, String template);
    }

    private static Sink pumpkinSink = (id, template) -> {
        throw new IllegalStateException("no registration sink installed for " + id);
    };

    public static void setSink(Sink replacement) {
        pumpkinSink = replacement;
    }

    private ResourceKey<? extends Registry<T>> pumpkinRegistryKey;

    private String pumpkinNamespace;

    private final java.util.List<DeferredHolder<T, ? extends T>> pumpkinPending = new java.util.ArrayList<>();
"""),
("""    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String namespace) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.create:(Lnet/minecraft/resources/ResourceKey;Ljava/lang/String;)Lnet/neoforged/neoforge/registries/DeferredRegister;");
    }""",
"""    // Pumpkin divergence: real body.
    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String namespace) {
        return new DeferredRegister<>(key, namespace);
    }"""),
("""    protected DeferredRegister(ResourceKey<? extends Registry<T>> registryKey, String namespace) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.<init>:(Lnet/minecraft/resources/ResourceKey;Ljava/lang/String;)V");
    }""",
"""    // Pumpkin divergence: real body.
    protected DeferredRegister(ResourceKey<? extends Registry<T>> registryKey, String namespace) {
        this.pumpkinRegistryKey = registryKey;
        this.pumpkinNamespace = namespace;
    }"""),
("""    public <I extends T> DeferredHolder<T, I> register(final String name, final Supplier<? extends I> sup) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.register:(Ljava/lang/String;Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/registries/DeferredHolder;");
    }""",
"""    // Pumpkin divergence: real body. Records the registration; nothing runs until the
    // RegisterEvent fires.
    public <I extends T> DeferredHolder<T, I> register(final String name, final Supplier<? extends I> sup) {
        DeferredHolder<T, I> holder =
                new DeferredHolder<>(Identifier.fromNamespaceAndPath(pumpkinNamespace, name), sup::get);
        pumpkinPending.add(holder);
        return holder;
    }"""),
("""    public void register(IEventBus bus) {
        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.register:(Lnet/neoforged/bus/api/IEventBus;)V");
    }""",
"""    // Pumpkin divergence: real body.
    public void register(IEventBus bus) {
        bus.addListener(RegisterEvent.class, event -> pumpkinFlush());
    }

    // Pumpkin divergence: no vanilla counterpart. Replays every recorded registration into
    // the sink once the event fires. Only blocks are supported so far; anything else stops
    // loudly rather than being silently dropped.
    private void pumpkinFlush() {
        for (DeferredHolder<T, ? extends T> holder : pumpkinPending) {
            Object object = holder.get();
            if (object instanceof Block block) {
                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate());
            } else {
                throw new IllegalStateException("registry " + pumpkinRegistryKey.identifier()
                        + " is not supported yet: " + holder.getId());
            }
        }
    }"""),
])

# -------------------------------------------------------------- RegisterEvent
edit("net/neoforged/neoforge/registries/RegisterEvent.java", [
("""    protected RegisterEvent() {
    }""",
"""    // Pumpkin divergence from the generated shim: public. In NeoForge this event is
    // constructed by the loader, once per registry, and mods only ever receive it. Pumpkin's
    // Bootstrap is the loader here and fires exactly one of these to mean "the server is
    // ready to take registrations", so it has to be able to build one. Re-apply by hand
    // after any regeneration -- grep for "Pumpkin divergence".
    public RegisterEvent() {
    }"""),
])

commit()
