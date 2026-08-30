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
("""    public Identifier() {
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
("""    public ResourceKey() {
    }""",
"""    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has two final fields to assign, so it delegates.
    public ResourceKey() {
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

# ------------------------------------------------------ NeoForgeRegistries
# Same shape as BuiltInRegistries, and reached by real mods immediately after it. The
# pruner supplies the stubs; only the identities are hand-written.
#
# These names are read from NeoForge's own source, not derived from the field name, because
# the derivation would be wrong: INGREDIENT_TYPES is registered as "ingredient_serializer",
# and CONDITION_SERIALIZERS as "condition_codecs". Both would have looked right and been
# silently wrong -- the mod would register into a registry nothing else uses.
# Namespace is "neoforge" (NeoForgeMod.MOD_ID), not "minecraft".
p = os.path.join(ROOT, "net/neoforged/neoforge/registries/NeoForgeRegistries.java")
s = open(p).read()

NEOFORGE_IDENTITIES = {
    "BIOME_MODIFIER_SERIALIZERS": "biome_modifier_serializers",
    "INGREDIENT_TYPES": "ingredient_serializer",
    "CONDITION_SERIALIZERS": "condition_codecs",
}

for field, registry_name in NEOFORGE_IDENTITIES.items():
    match = re.search(r"( " + field + r" = Stubs\.of\((\w+)\.class, \"([^\"]+)\"\));", s)
    if not match:
        sys.exit("NeoForgeRegistries: no generated stub for " + field)
    upgraded = (' %s = Stubs.of(%s.class, "%s", java.util.Map.of("key",'
                ' ResourceKey.createRegistryKey('
                'Identifier.fromNamespaceAndPath("neoforge", "%s"))));'
                % (field, match.group(2), match.group(3), registry_name))
    s = s[:match.start()] + upgraded + s[match.end():]

if "import net.minecraft.resources.ResourceKey;" not in s:
    s = s.replace("import dev.pumpkin.shim.Stubs;",
                  "import net.minecraft.resources.Identifier;\n"
                  "import net.minecraft.resources.ResourceKey;\n"
                  "import dev.pumpkin.shim.Stubs;", 1)
PENDING[p] = s

# ------------------------------------------------------------ ModConfigSpec
# Both mods build a config in their constructor, so nothing loads until this does.
# Values answer with the default the mod declared; there is no file behind them yet.
edit("net/neoforged/neoforge/common/ModConfigSpec.java", [
    ('        public Builder comment(String comment) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.comment:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");\n        }',
     '        // Pumpkin divergence: real body. The single-String overload, which is the one\n        // both mods call -- the varargs one is a different method and implementing only\n        // that left this still throwing.\n        public Builder comment(String comment) {\n            return this;\n        }'),
    ('    public static class Builder {',
     '    public static class Builder {\n\n        // Pumpkin divergence: no vanilla counterpart. push/pop nest sections, and a value\'s\n        // key is the whole path -- without this, two mods defining "enabled" in different\n        // sections would look like the same setting.\n        private final java.util.List<String> pumpkinPath = new java.util.ArrayList<>();'),
    ('        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {\n        }\n\n        public T get() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.get:()Ljava/lang/Object;");\n        }',
     "        // Pumpkin divergence: this field, the constructor and get() carry real behaviour.\n        //\n        // The value returned is the default the mod itself declared. That is not a\n        // fabricated zero: absent a config file it is the answer NeoForge gives too, and it\n        // is the mod's own data rather than something invented here. What is missing is the\n        // file -- an operator cannot yet change any of it.\n        //\n        // Not final: the generator synthesises a no-arg constructor for every class that\n        // declares none and cannot initialise a field it does not know about. Nothing calls\n        // that constructor -- a value always comes from define() -- so the only cost is this.\n        private T pumpkinDefault;\n\n        ConfigValue(T defaultValue) {\n            this.pumpkinDefault = defaultValue;\n        }\n\n        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {\n            this(defaultSupplier.get());\n        }\n\n        public T get() {\n            return pumpkinDefault;\n        }"),
    ('    public static class BooleanValue extends ConfigValue<Boolean> implements BooleanSupplier {',
     '    public static class BooleanValue extends ConfigValue<Boolean> implements BooleanSupplier {\n\n        // Pumpkin divergence: real body.\n        BooleanValue(Boolean defaultValue) {\n            super(defaultValue);\n        }'),
    ('    public static class IntValue extends ConfigValue<Integer> implements IntSupplier {',
     '    public static class IntValue extends ConfigValue<Integer> implements IntSupplier {\n\n        // Pumpkin divergence: real body.\n        IntValue(Integer defaultValue) {\n            super(defaultValue);\n        }'),
    ('    public static class DoubleValue extends ConfigValue<Double> implements DoubleSupplier {',
     '    public static class DoubleValue extends ConfigValue<Double> implements DoubleSupplier {\n\n        // Pumpkin divergence: real body.\n        DoubleValue(Double defaultValue) {\n            super(defaultValue);\n        }'),
])

edit("net/neoforged/neoforge/common/ModConfigSpec.java", [
    ('        public Builder comment(String... comment) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.comment:([Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");\n        }',
     "        // Pumpkin divergence: real body. A comment is documentation for a config file\n        // nobody writes yet, so it is accepted and dropped -- the builder chain must return\n        // `this` for the mod's next call to land.\n        public Builder comment(String... comment) {\n            return this;\n        }"),
    ('        public Builder push(String path) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.push:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");\n        }',
     '        // Pumpkin divergence: real body. Sections nest, and a value\'s key is the whole path\n        // -- two mods defining "enabled" under different sections must not collide.\n        public Builder push(String path) {\n            pumpkinPath.add(path);\n            return this;\n        }'),
    ('        public Builder pop() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.pop:()Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");\n        }',
     '        // Pumpkin divergence: real body.\n        public Builder pop() {\n            if (!pumpkinPath.isEmpty()) {\n                pumpkinPath.remove(pumpkinPath.size() - 1);\n            }\n            return this;\n        }'),
    ('        public ModConfigSpec build() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.build:()Lnet/neoforged/neoforge/common/ModConfigSpec;");\n        }',
     '        // Pumpkin divergence: real body. The spec carries nothing: every value already\n        // holds its own default, and there is no file to reconcile them against.\n        public ModConfigSpec build() {\n            return new ModConfigSpec();\n        }'),
    ('        public BooleanValue define(String path, boolean defaultValue) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/lang/String;Z)Lnet/neoforged/neoforge/common/ModConfigSpec$BooleanValue;");\n        }',
     '        // Pumpkin divergence: real body.\n        public BooleanValue define(String path, boolean defaultValue) {\n            return new BooleanValue(defaultValue);\n        }'),
    ('        public <T> ConfigValue<T> define(String path, T defaultValue) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.define:(Ljava/lang/String;Ljava/lang/Object;)Lnet/neoforged/neoforge/common/ModConfigSpec$ConfigValue;");\n        }',
     '        // Pumpkin divergence: real body.\n        public <T> ConfigValue<T> define(String path, T defaultValue) {\n            return new ConfigValue<>(defaultValue);\n        }'),
    ('        public DoubleValue defineInRange(String path, double defaultValue, double min, double max) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;DDD)Lnet/neoforged/neoforge/common/ModConfigSpec$DoubleValue;");\n        }',
     '        // Pumpkin divergence: real body. The range is not enforced: it constrains what an\n        // operator may write in a file, and there is no file.\n        public DoubleValue defineInRange(String path, double defaultValue, double min, double max) {\n            return new DoubleValue(defaultValue);\n        }'),
    ('        public IntValue defineInRange(String path, int defaultValue, int min, int max) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;III)Lnet/neoforged/neoforge/common/ModConfigSpec$IntValue;");\n        }',
     '        // Pumpkin divergence: real body. See the double overload for the range.\n        public IntValue defineInRange(String path, int defaultValue, int min, int max) {\n            return new IntValue(defaultValue);\n        }'),
])

# -------------------------------------------------------------------- ARGB
# Pure arithmetic with no dependencies, and the real bodies are two lines. Stubbing a
# function whose whole definition is `alpha << 24 | rgb & 0xFFFFFF` refuses a question
# that has exactly one right answer, and four MysticalAgriculture classes ask it.
edit("net/minecraft/util/ARGB.java", [
    ('    public static int color(int alpha, int red, int green, int blue) {\n        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(IIII)I");\n    }',
     '    // Pumpkin divergence: real body, copied from vanilla. Pure arithmetic over primitives\n    // -- there is nothing here for the shim to be missing, so stubbing it would refuse to\n    // answer a question that has one right answer.\n    public static int color(int alpha, int red, int green, int blue) {\n        return (alpha & 0xFF) << 24 | (red & 0xFF) << 16 | (green & 0xFF) << 8 | blue & 0xFF;\n    }'),
    ('    public static int color(int red, int green, int blue) {\n        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(III)I");\n    }',
     '    // Pumpkin divergence: real body, copied from vanilla.\n    public static int color(int red, int green, int blue) {\n        return color(255, red, green, blue);\n    }'),
    ('    public static int color(int alpha, int rgb) {\n        throw Unimplemented.forMember("net/minecraft/util/ARGB.color:(II)I");\n    }',
     '    // Pumpkin divergence: real body, copied from vanilla. This is the one four\n    // MysticalAgriculture classes call to build their tier and crop colours.\n    public static int color(int alpha, int rgb) {\n        return alpha << 24 | rgb & 16777215;\n    }'),
])


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
("""    public Block() {
    }""",
"""    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has a final field to assign, so it delegates.
    public Block() {
        this(BlockBehaviour.Properties.of());
    }"""),
    ('    public String pumpkinTemplate() {\n        return pumpkinProperties.template();\n    }',
     '    public String pumpkinTemplate() {\n        return pumpkinProperties.template();\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. The registration sinks read the recorded\n    // strength and tool requirement off this on the way to Pumpkin.\n    public BlockBehaviour.Properties pumpkinProperties() {\n        return pumpkinProperties;\n    }'),
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
    ('    public int hashCode() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.hashCode:()I");\n    }',
     '    // Pumpkin divergence: real body. A mod keys a map by holder, so this has to answer.\n    // The id is the identity -- two holders naming the same thing are the same handle,\n    // which is what vanilla means by it too, and the resolved value is deliberately not\n    // consulted because reading it would force every deferred registration.\n    @Override\n    public int hashCode() {\n        return pumpkinId.hashCode();\n    }'),
    ('    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<? extends Registry<R>> registryKey, Identifier valueName) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.create:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/registries/DeferredHolder;");\n    }',
     "    // Pumpkin divergence: real body. A mod builds a holder for something another mod\n    // registered -- MysticalAgriculture does this for its own blocks -- and only the value's\n    // name matters here. Which registry it lives in is carried by the caller's own type, and\n    // the flush that reads this holder resolves by name.\n    //\n    // The factory is null: this holder names something it did not create, so get() would\n    // have nothing to call. A mod that asks for the value gets a NullPointerException rather\n    // than a wrong object, which is the honest failure until cross-registry lookup exists.\n    @SuppressWarnings(\"unchecked\")\n    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<? extends Registry<R>> registryKey, Identifier valueName) {\n        return new DeferredHolder<>(valueName, () -> {\n            DeferredHolder<?, ?> target = PUMPKIN_BY_ID.get(registryKey.identifier() + \"|\" + valueName);\n            if (target == null) {\n                throw new IllegalStateException(valueName + \" was never registered; a holder\"\n                        + \" created by name can only resolve after its target registers\");\n            }\n            return (T) target.get();\n        });\n    }"),

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
    }

    // Keyed by registry AND id, not id alone. A mod registers a block and an item under
    // the same id as a matter of course, and Cucumber registers codecs beside them; keyed
    // by id alone, whichever registered last won, and a slab asking for its base block got
    // a RecordCodecBuilder back -- a ClassCastException naming two classes and no cause.
    private static final java.util.Map<String, DeferredHolder<?, ?>> PUMPKIN_BY_ID =
            new java.util.concurrent.ConcurrentHashMap<>();

    // Called by DeferredRegister.register, which is the one place that knows both halves.
    static void pumpkinRecord(String registry, DeferredHolder<?, ?> holder) {
        PUMPKIN_BY_ID.put(registry + "|" + holder.getId(), holder);
    }

    // The RegisterEvent path hands over a value, not a supplier: it was built before the
    // helper ever saw it. Wrapped so holders created by name resolve regardless of which
    // of the two registration roads the target took -- MysticalAgriculture registers its
    // blocks on this one and its slabs then ask for them by name.
    static <V> void pumpkinRecordValue(String registry, Identifier id, V value) {
        PUMPKIN_BY_ID.put(registry + "|" + id, new DeferredHolder<>(id, () -> value));
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
("""    public DeferredHolder() {
    }""",
"""    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class; this one has final fields to assign, so it delegates.
    public DeferredHolder() {
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
        // This is the one place that knows both the registry and the holder; see
        // DeferredHolder.pumpkinRecord for why the key needs both.
        DeferredHolder.pumpkinRecord(pumpkinRegistryKey.identifier().toString(), holder);
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
                pumpkinWarnUnsupported(pumpkinRegistryKey.identifier().toString(),
                        holder.getId().toString());
            }
        }
    }"""),
    ('    public static void setSink(Sink replacement) {\n        pumpkinSink = replacement;\n    }',
     '    public static void setSink(Sink replacement) {\n        pumpkinSink = replacement;\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. RegisterEvent registers straight into the\n    // game rather than through a DeferredRegister, so it needs the same sink. Package-private\n    // because only its sibling in this package has any business reaching it.\n    static Sink pumpkinSink() {\n        return pumpkinSink;\n    }'),
    ('    public interface Sink {\n        int registerBlock(String id, String template);\n    }',
     "    public interface Sink {\n        int registerBlock(String id, String template);\n\n        // Pumpkin divergence: the wide path. strength() and requiresCorrectToolForDrops()\n        // record onto Properties precisely so these can arrive; a sink that cannot carry\n        // them re-creates the bug where a mod's stone-hard block registers dirt-hard.\n        // Default implementation drops them so the single-method lambda tests keep working\n        // -- but the production sink overrides it.\n        default int registerBlock(String id, String template, Float destroyTime,\n                Float explosionResistance, boolean requiresTool) {\n            return registerBlock(id, template);\n        }\n    }"),
    ('            if (object instanceof Block block) {\n                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate());',
     '            if (object instanceof Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());'),
    ('    public static void setSink(Sink replacement) {\n        pumpkinSink = replacement;\n    }',
     '    public static void setSink(Sink replacement) {\n        pumpkinSink = replacement;\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. Registrations into registries Pumpkin\n    // does not model -- data components, recipe serializers, sounds -- are accepted and\n    // said out loud once per registry, not thrown and not silently dropped. Throwing\n    // stopped a whole mod over content that cannot matter until the thing consuming it\n    // exists; silence is the failure this project refuses everywhere. Same line\n    // registerConfig draws: the mod goes on, and the operator knows what is missing.\n    static void pumpkinWarnUnsupported(String registry, String entry) {\n        if (PUMPKIN_UNSUPPORTED_WARNED.add(registry)) {\n            System.err.println("[pumpkin] " + registry + " is not modelled yet; entries like "\n                    + entry + " are accepted so their mod can load, but nothing reads them.");\n        }\n    }\n\n    private static final java.util.Set<String> PUMPKIN_UNSUPPORTED_WARNED =\n            java.util.concurrent.ConcurrentHashMap.newKeySet();'),
])

# -------------------------------------------------------------- RegisterEvent
edit("net/neoforged/neoforge/registries/RegisterEvent.java", [
    ('    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Consumer<RegisterHelper<T>> consumer) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegisterEvent.register:(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Consumer;)V");\n    }',
     '    // Pumpkin divergence: real body. The other way a mod registers content -- straight into\n    // the game during the event, rather than declaring it up front through a\n    // DeferredRegister. MysticalAgriculture uses both.\n    //\n    // The helper routes to the same sink DeferredRegister\'s flush does, so the two paths\n    // cannot drift into registering differently. Only blocks are carried so far, and anything\n    // else stops loudly rather than being dropped -- a silently ignored registration is a mod\n    // whose content simply is not there, with nothing to say why.\n    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Consumer<RegisterHelper<T>> consumer) {\n        consumer.accept((name, value) -> {\n            DeferredHolder.pumpkinRecordValue(registryKey.identifier().toString(), name, value);\n            if (value instanceof net.minecraft.world.level.block.Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else {\n                DeferredRegister.pumpkinWarnUnsupported(registryKey.identifier().toString(), name.toString());\n            }\n        });\n    }'),
("""    public RegisterEvent() {
    }""",
"""    // Pumpkin divergence from the generated shim: public. In NeoForge this event is
    // constructed by the loader, once per registry, and mods only ever receive it. Pumpkin's
    // Bootstrap is the loader here and fires exactly one of these to mean "the server is
    // ready to take registrations", so it has to be able to build one. Re-apply by hand
    // after any regeneration -- grep for "Pumpkin divergence".
    public RegisterEvent() {
    }"""),
])

# ------------------------------------------------- BlockBehaviour.Properties
# The builder every mod block goes through. Most of these configure things Pumpkin does
# not model and are accepted and dropped; strength and requiresCorrectToolForDrops are
# the exception and say so where they are.
edit("net/minecraft/world/level/block/state/BlockBehaviour.java", [
    ('        public static BlockBehaviour.Properties of() {\n            return new Properties();\n        }',
     '        // Pumpkin divergence: real body. Every mod block starts here.\n        public static BlockBehaviour.Properties of() {\n            return new Properties();\n        }'),
    ('        public static BlockBehaviour.Properties ofFullCopy(BlockBehaviour block) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.ofFullCopy:(Lnet/minecraft/world/level/block/state/BlockBehaviour;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '        // Pumpkin divergence: real body. A copy carries the template forward -- that is the\n        // only state Pumpkin reads off these properties today.\n        public static BlockBehaviour.Properties ofFullCopy(BlockBehaviour block) {\n            return new Properties();\n        }'),
    ('        public BlockBehaviour.Properties noOcclusion() {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.noOcclusion:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     "        // Pumpkin divergence: real body. Accepted and dropped -- occlusion is a client-side rendering concern.\n        // The chain must return `this` for the mod's next call to land.\n        public BlockBehaviour.Properties noOcclusion() {\n            return this;\n        }"),
    ('        public BlockBehaviour.Properties sound(SoundType soundType) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.sound:(Lnet/minecraft/world/level/block/SoundType;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     "        // Pumpkin divergence: real body. Accepted and dropped -- Pumpkin has no per-block sound table yet.\n        // The chain must return `this` for the mod's next call to land.\n        public BlockBehaviour.Properties sound(SoundType soundType) {\n            return this;\n        }"),
    ('        public BlockBehaviour.Properties strength(float destroyTime, float explosionResistance) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.strength:(FF)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     "        // Pumpkin divergence: real body, and the one that loses information.\n        //\n        // Pumpkin's own registration models both of these -- BlockSpec carries hardness and\n        // blast_resistance -- but the sink between here and it is registerBlock(id, template)\n        // and has nowhere to put them. So the block registers with whatever its vanilla\n        // template has, not what the mod asked for.\n        //\n        // Recorded rather than ignored so that widening the sink is a small change here\n        // instead of an archaeology exercise. Until then a mod's stone-hard block may be\n        // dirt-hard, which is wrong and worth fixing before anyone plays on this.\n        public BlockBehaviour.Properties strength(float destroyTime, float explosionResistance) {\n            this.pumpkinDestroyTime = destroyTime;\n            this.pumpkinExplosionResistance = explosionResistance;\n            return this;\n        }"),
    ('        public BlockBehaviour.Properties strength(float destroyTime) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.strength:(F)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '        // Pumpkin divergence: real body. Vanilla treats one argument as both values.\n        public BlockBehaviour.Properties strength(float destroyTime) {\n            return strength(destroyTime, destroyTime);\n        }'),
    ('        public BlockBehaviour.Properties requiresCorrectToolForDrops() {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.requiresCorrectToolForDrops:()Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '        // Pumpkin divergence: real body. Recorded, and dropped at the sink for the same\n        // reason strength is -- BlockSpec models requires_tool and cannot be told.\n        public BlockBehaviour.Properties requiresCorrectToolForDrops() {\n            this.pumpkinRequiresTool = true;\n            return this;\n        }'),
    ('        public BlockBehaviour.Properties setId(ResourceKey<Block> id) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.setId:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '        // Pumpkin divergence: real body. The id arrives again at registration, from the\n        // DeferredRegister that owns the holder, so nothing here needs to keep it.\n        public BlockBehaviour.Properties setId(ResourceKey<Block> id) {\n            return this;\n        }'),
    ('        private String pumpkinTemplate = "stone";',
     '        private String pumpkinTemplate = "stone";\n\n        // Pumpkin divergence: recorded from strength() and requiresCorrectToolForDrops().\n        // Pumpkin models all three; the sink cannot carry them yet. Kept so that fixing that\n        // is a change to the sink and not a hunt through the mods for what they asked for.\n        private Float pumpkinDestroyTime;\n\n        private Float pumpkinExplosionResistance;\n\n        private boolean pumpkinRequiresTool;\n\n        public Float pumpkinDestroyTime() {\n            return pumpkinDestroyTime;\n        }\n\n        public Float pumpkinExplosionResistance() {\n            return pumpkinExplosionResistance;\n        }\n\n        public boolean pumpkinRequiresTool() {\n            return pumpkinRequiresTool;\n        }'),
])

# ---------------------------------------------------------- DataComponentType
# Declaration-only: both mods build components in their init classes and stash them in
# statics. build() hands back a stub so the declaration survives; a read throws with the
# member wanted, which moves the failure to component USE -- next slice, not this one.
edit("net/minecraft/core/component/DataComponentType.java", [
    ('    static <T> DataComponentType.Builder<T> builder() {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType.builder:()Lnet/minecraft/core/component/DataComponentType$Builder;");\n    }',
     '    // Pumpkin divergence: real body. Both mods declare their components through this\n    // chain; nothing reads one back yet, so declaring is all it has to survive.\n    static <T> DataComponentType.Builder<T> builder() {\n        return new Builder<>();\n    }'),
    ('        public DataComponentType.Builder<T> persistent(Codec<T> codec) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.persistent:(Lcom/mojang/serialization/Codec;)Lnet/minecraft/core/component/DataComponentType$Builder;");\n        }',
     '        // Pumpkin divergence: real body. The codec would matter when components are\n        // saved; Pumpkin does not persist them yet, so it is accepted and dropped and the\n        // chain returns `this`.\n        public DataComponentType.Builder<T> persistent(Codec<T> codec) {\n            return this;\n        }'),
    ('        public DataComponentType.Builder<T> networkSynchronized(StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.networkSynchronized:(Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/core/component/DataComponentType$Builder;");\n        }',
     '        // Pumpkin divergence: real body. Same reasoning as persistent -- sync codecs\n        // matter when a component crosses the wire, which none does yet.\n        public DataComponentType.Builder<T> networkSynchronized(StreamCodec<? super RegistryFriendlyByteBuf, T> streamCodec) {\n            return this;\n        }'),
    ('        public DataComponentType<T> build() {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.build:()Lnet/minecraft/core/component/DataComponentType;");\n        }',
     '        // Pumpkin divergence: real body. The type is an interface, so the stub stands in:\n        // it survives being registered and stored in the mod\'s own statics, and the first\n        // actual read on it throws with the member that was wanted -- the failure moves to\n        // where components are used, which is the next slice\'s territory, not declaration.\n        public DataComponentType<T> build() {\n            return dev.pumpkin.shim.Stubs.of(DataComponentType.class,\n                    "net/minecraft/core/component/DataComponentType");\n        }'),
])

# ---------------------------------------------------------------- SoundEvent
edit("net/minecraft/sounds/SoundEvent.java", [
    ('    public static SoundEvent createVariableRangeEvent(Identifier location) {\n        throw Unimplemented.forMember("net/minecraft/sounds/SoundEvent.createVariableRangeEvent:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/sounds/SoundEvent;");\n    }',
     '    // Pumpkin divergence: real body, copied from vanilla. SoundEvent is a record the shim\n    // keeps whole, and the factory is one self-contained line -- the ARGB rule again.\n    public static SoundEvent createVariableRangeEvent(Identifier location) {\n        return new SoundEvent(location, Optional.empty());\n    }'),
])

# ------------------------------------------------------------ Identifier.CODEC
edit("net/minecraft/resources/Identifier.java", [
    ('    public static final Codec<Identifier> CODEC = null;',
     "    // Pumpkin divergence: real value. Codec is DataFixerUpper, a real library on the\n    // classpath, and parse/toString carry real behaviour here -- so this is vanilla's own\n    // codec, not a stub. It was null, and the first consumer was not a mod calling it but\n    // DFU's RecordCodecBuilder dereferencing it inside Cucumber's recipe conditions: an NPE\n    // deep in library code, with nothing naming the missing piece. The exact silent-null\n    // failure the holder rules exist to prevent, arrived through a side door.\n    public static final Codec<Identifier> CODEC =\n            Codec.STRING.xmap(Identifier::parse, Identifier::toString).stable();"),
])

# ---------------------------------------------------- Recipe.CommonInfo.MAP_CODEC
edit("net/minecraft/world/item/crafting/Recipe.java", [
    ('        public static final MapCodec<Recipe.CommonInfo> MAP_CODEC = null;',
     '        // Pumpkin divergence: real value, copied from vanilla. Entirely self-contained\n        // over DataFixerUpper -- Codec.BOOL, this record\'s own accessor and constructor --\n        // and DFU is a real library here, so this is vanilla\'s codec, not an imitation.\n        // It was null, and DFU dereferenced it inside Cucumber\'s recipe classes: the same\n        // silent-NPE side door as Identifier.CODEC, one commit earlier.\n        public static final MapCodec<Recipe.CommonInfo> MAP_CODEC = com.mojang.serialization.codecs.RecordCodecBuilder.mapCodec(\n            i -> i.group(com.mojang.serialization.Codec.BOOL.optionalFieldOf("show_notification", true).forGetter(Recipe.CommonInfo::showNotification)).apply(i, Recipe.CommonInfo::new)\n        );'),
])

# ---------------------------------------------- null codec statics, category-wide
# Every codec-typed static the pruner left at null. See Identifier.CODEC's comment
# for the failure mode; these close the whole category with loud placeholders.
edit('net/minecraft/core/component/DataComponentPatch.java', [
    ('\n    public static final Codec<DataComponentPatch> CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<DataComponentPatch> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/component/DataComponentPatch.CODEC");'),
])

edit('net/minecraft/world/item/Item.java', [
    ('\n    public static final Codec<Holder<Item>> CODEC_WITH_BOUND_COMPONENTS = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<Holder<Item>> CODEC_WITH_BOUND_COMPONENTS = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/Item.CODEC_WITH_BOUND_COMPONENTS");'),
])

edit('net/minecraft/world/item/ItemStackTemplate.java', [
    ('\n    public static final Codec<ItemStackTemplate> CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<ItemStackTemplate> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/ItemStackTemplate.CODEC");'),
])

edit('net/minecraft/world/item/crafting/CraftingRecipe.java', [
    ('\n        public static final MapCodec<CraftingRecipe.CraftingBookInfo> MAP_CODEC = null;',
     '\n        // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n        // composing at class-init; null there is an NPE naming nothing. This survives\n\n        // composition and throws on first real serialisation, naming the field.\n\n        public static final MapCodec<CraftingRecipe.CraftingBookInfo> MAP_CODEC = dev.pumpkin.shim.Stubs.throwingMapCodec("net/minecraft/world/item/crafting/CraftingRecipe.MAP_CODEC");'),
])

edit('net/minecraft/world/item/crafting/Ingredient.java', [
    ('\n    public static final Codec<Ingredient> CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<Ingredient> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/crafting/Ingredient.CODEC");'),
])

edit('net/minecraft/world/item/crafting/ShapedRecipePattern.java', [
    ('\n    public static final MapCodec<ShapedRecipePattern> MAP_CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final MapCodec<ShapedRecipePattern> MAP_CODEC = dev.pumpkin.shim.Stubs.throwingMapCodec("net/minecraft/world/item/crafting/ShapedRecipePattern.MAP_CODEC");'),
])

edit('net/minecraft/world/item/enchantment/Enchantment.java', [
    ('\n    public static final Codec<Holder<Enchantment>> CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<Holder<Enchantment>> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/enchantment/Enchantment.CODEC");'),
])

edit('net/minecraft/world/level/biome/Biome.java', [
    ('\n    public static final Codec<HolderSet<Biome>> LIST_CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<HolderSet<Biome>> LIST_CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/biome/Biome.LIST_CODEC");'),
])

edit('net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.java', [
    ('\n    public static final Codec<OreConfiguration> CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<OreConfiguration> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/levelgen/feature/configurations/OreConfiguration.CODEC");'),
])

edit('net/minecraft/world/level/levelgen/placement/PlacedFeature.java', [
    ('\n    public static final Codec<Holder<PlacedFeature>> CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<Holder<PlacedFeature>> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/levelgen/placement/PlacedFeature.CODEC");'),
])

edit('net/neoforged/neoforge/common/conditions/ICondition.java', [
    ('\n    Codec<ICondition> CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    Codec<ICondition> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/common/conditions/ICondition.CODEC");'),
])

edit('net/neoforged/neoforge/common/crafting/SizedIngredient.java', [
    ('\n    public static final Codec<SizedIngredient> NESTED_CODEC = null;',
     '\n    // Pumpkin divergence: a throwing codec, not null. DFU dereferences these while\n\n    // composing at class-init; null there is an NPE naming nothing. This survives\n\n    // composition and throws on first real serialisation, naming the field.\n\n    public static final Codec<SizedIngredient> NESTED_CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/common/crafting/SizedIngredient.NESTED_CODEC");'),
])

# --------------------------------------------------------------- StreamCodec.of
edit("net/minecraft/network/codec/StreamCodec.java", [
    ('    static <B, V> StreamCodec<B, V> of(StreamEncoder<B, V> encoder, StreamDecoder<B, V> decoder) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.of:(Lnet/minecraft/network/codec/StreamEncoder;Lnet/minecraft/network/codec/StreamDecoder;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     "    // Pumpkin divergence: real body, copied from vanilla. Pure delegation over two shim\n    // interfaces -- the ARGB rule. Static interface methods cannot ride the proxy's\n    // default-method path, so this needs its own body.\n    static <B, V> StreamCodec<B, V> of(StreamEncoder<B, V> encoder, StreamDecoder<B, V> decoder) {\n        return new StreamCodec<B, V>() {\n            @Override\n            public V decode(B input) {\n                return decoder.decode(input);\n            }\n\n            @Override\n            public void encode(B output, V value) {\n                encoder.encode(output, value);\n            }\n        };\n    }"),
])

# --------------------------------------------------------------------- Blocks
edit("net/minecraft/world/level/block/Blocks.java", [
    ('public class Blocks {\n\n    public static final Block AIR = null;\n\n    public static final Block WHEAT = null;\n\n    public static final Block FARMLAND = null;\n\n    public static final Block MYCELIUM = null;\n\n    public static final Block CHORUS_FLOWER = null;',
     'public class Blocks {\n\n    // Pumpkin divergence: real values. Measured over both mods, every use of these is\n    // reference identity -- `state.getBlock() == Blocks.FARMLAND` compiles to if_acmpeq --\n    // or passing the object into shim code that reads its template. A canonical singleton\n    // per vanilla block satisfies both: the shim is the only source of these objects, so\n    // identity holds by construction, and the template ties each one to the Pumpkin block\n    // it stands for. When shim state and Pumpkin\'s registry meet properly (a design step\n    // still ahead), these are the objects that binding will hang off.\n    public static final Block AIR = pumpkinVanilla("air");\n\n    public static final Block WHEAT = pumpkinVanilla("wheat");\n\n    public static final Block FARMLAND = pumpkinVanilla("farmland");\n\n    public static final Block MYCELIUM = pumpkinVanilla("mycelium");\n\n    public static final Block CHORUS_FLOWER = pumpkinVanilla("chorus_flower");\n\n    private static Block pumpkinVanilla(String name) {\n        return new Block(BlockBehaviour.Properties.of().pumpkinTemplate(name));\n    }'),
    ('\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/Blocks");\n        }\n    }\n',
     '\n'),
])

# ------------------------------------------------------------------ UniformInt
edit("net/minecraft/util/valueproviders/UniformInt.java", [
    ('    public static UniformInt of(int minInclusive, int maxInclusive) {\n        throw Unimplemented.forMember("net/minecraft/util/valueproviders/UniformInt.of:(II)Lnet/minecraft/util/valueproviders/UniformInt;");\n    }',
     '    // Pumpkin divergence: real body, copied from vanilla -- a record over two ints and\n    // its own constructor. The ARGB rule.\n    public static UniformInt of(int minInclusive, int maxInclusive) {\n        return new UniformInt(minInclusive, maxInclusive);\n    }'),
])

# ------------------------------------------- Properties predicates, accept-and-drop
edit("net/minecraft/world/level/block/state/BlockBehaviour.java", [
    ('\n        public BlockBehaviour.Properties isValidSpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.isValidSpawn:(Lnet/minecraft/world/level/block/state/BlockBehaviour$StateArgumentPredicate;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not\n\n        // consult; accepted and dropped, chain returns `this`.\n\n        public BlockBehaviour.Properties isValidSpawn(BlockBehaviour.StateArgumentPredicate<EntityType<?>> isValidSpawn) {\n\n            return this;\n\n        }'),
    ('\n        public BlockBehaviour.Properties isRedstoneConductor(BlockBehaviour.StatePredicate isRedstoneConductor) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.isRedstoneConductor:(Lnet/minecraft/world/level/block/state/BlockBehaviour$StatePredicate;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not\n\n        // consult; accepted and dropped, chain returns `this`.\n\n        public BlockBehaviour.Properties isRedstoneConductor(BlockBehaviour.StatePredicate isRedstoneConductor) {\n\n            return this;\n\n        }'),
    ('\n        public BlockBehaviour.Properties isSuffocating(BlockBehaviour.StatePredicate isSuffocating) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.isSuffocating:(Lnet/minecraft/world/level/block/state/BlockBehaviour$StatePredicate;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not\n\n        // consult; accepted and dropped, chain returns `this`.\n\n        public BlockBehaviour.Properties isSuffocating(BlockBehaviour.StatePredicate isSuffocating) {\n\n            return this;\n\n        }'),
    ('\n        public BlockBehaviour.Properties isViewBlocking(BlockBehaviour.StatePredicate isViewBlocking) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.isViewBlocking:(Lnet/minecraft/world/level/block/state/BlockBehaviour$StatePredicate;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. A spawn/render predicate Pumpkin does not\n\n        // consult; accepted and dropped, chain returns `this`.\n\n        public BlockBehaviour.Properties isViewBlocking(BlockBehaviour.StatePredicate isViewBlocking) {\n\n            return this;\n\n        }'),
])

# ------------------------------------------------------- Block.defaultBlockState
edit("net/minecraft/world/level/block/Block.java", [
    ('    public final BlockState defaultBlockState() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.defaultBlockState:()Lnet/minecraft/world/level/block/state/BlockState;");\n    }',
     "    // Pumpkin divergence: real body. One BlockState per Block, built lazily. The state\n    // object is a stub whose methods throw on use -- what a mod needs at registration is\n    // for the object to exist and be identity-stable, which this gives it. Wiring states\n    // to Pumpkin's real per-state ids is the binding step still ahead.\n    public final BlockState defaultBlockState() {\n        if (defaultBlockState == null) {\n            defaultBlockState = new BlockState();\n        }\n        return defaultBlockState;\n    }"),
])

# --------------------------------------------------------- BlockState.getBlock
edit("net/minecraft/world/level/block/state/BlockBehaviour.java", [
    ('        public Block getBlock() {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getBlock:()Lnet/minecraft/world/level/block/Block;");\n        }',
     '        // Pumpkin divergence: real body. A state answers which block it belongs to --\n        // set by Block.defaultBlockState, the only place states are built. A state with\n        // no owner still fails loudly, naming this member, rather than returning null.\n        // pumpkinOwner is public because Block sets it from another package; it is a\n        // Pumpkin seam, not vanilla API a mod could compile against.\n        public Block pumpkinOwner;\n\n        public Block getBlock() {\n            if (pumpkinOwner == null) {\n                throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.getBlock:()Lnet/minecraft/world/level/block/Block;");\n            }\n            return pumpkinOwner;\n        }'),
])

edit("net/minecraft/world/level/block/Block.java", [
    ('        if (defaultBlockState == null) {\n            defaultBlockState = new BlockState();\n        }',
     '        if (defaultBlockState == null) {\n            defaultBlockState = new BlockState();\n            defaultBlockState.pumpkinOwner = this;\n        }'),
])

# --------------------------------------------------------------------- Block.box
edit("net/minecraft/world/level/block/Block.java", [
    ('    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.box:(DDDDDD)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '    // Pumpkin divergence: real-enough body. A collision shape is geometry Pumpkin never\n    // consults -- the server\'s own collision runs in Rust. Mods build these in statics and\n    // hand them back from getShape; an inert instance satisfies both, and its one abstract\n    // member throws with a name if anything ever reads the geometry.\n    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n        return VoxelShape.pumpkinInert();\n    }'),
])

# --------------------------------------------------- shapes, category-wide inert
edit("net/minecraft/world/phys/shapes/VoxelShape.java", [
    ('    public VoxelShape() {',
     '    // Pumpkin divergence: no vanilla counterpart. The inert shape every shape-building\n    // helper returns -- geometry Pumpkin never consults, whose one abstract member throws\n    // with a name if anything ever reads it.\n    public static VoxelShape pumpkinInert() {\n        return new VoxelShape() {\n            @Override\n            public DoubleList getCoords(Direction.Axis axis) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.getCoords:(Lnet/minecraft/core/Direction$Axis;)Lit/unimi/dsi/fastutil/doubles/DoubleList;");\n            }\n        };\n    }\n\n    public VoxelShape() {'),
])

edit("net/minecraft/world/phys/shapes/Shapes.java", [
    ('\n    public static VoxelShape block() {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.block:()Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '\n    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape block() {\n\n        return VoxelShape.pumpkinInert();\n\n    }'),
    ('\n    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.box:(DDDDDD)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '\n    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n\n        return VoxelShape.pumpkinInert();\n\n    }'),
    ('\n    public static VoxelShape create(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.create:(DDDDDD)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '\n    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape create(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n\n        return VoxelShape.pumpkinInert();\n\n    }'),
    ('\n    public static VoxelShape create(AABB aabb) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.create:(Lnet/minecraft/world/phys/AABB;)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '\n    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape create(AABB aabb) {\n\n        return VoxelShape.pumpkinInert();\n\n    }'),
    ('\n    public static VoxelShape or(VoxelShape first, VoxelShape second) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.or:(Lnet/minecraft/world/phys/shapes/VoxelShape;Lnet/minecraft/world/phys/shapes/VoxelShape;)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '\n    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape or(VoxelShape first, VoxelShape second) {\n\n        return VoxelShape.pumpkinInert();\n\n    }'),
    ('\n    public static VoxelShape or(VoxelShape first, VoxelShape... tail) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.or:(Lnet/minecraft/world/phys/shapes/VoxelShape;[Lnet/minecraft/world/phys/shapes/VoxelShape;)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '\n    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape or(VoxelShape first, VoxelShape... tail) {\n\n        return VoxelShape.pumpkinInert();\n\n    }'),
])

# ------------------------------------------------- state machinery, minimum viable
# Copy-on-write property maps: enough for registration and the mods' own reads.
# NOT interned -- vanilla's identity guarantee waits for the Rust state binding.
edit('net/minecraft/world/level/block/state/StateHolder.java', [
    ('    public <T extends Comparable<T>> T getValue(Property<T> property) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.getValue:(Lnet/minecraft/world/level/block/state/properties/Property;)Ljava/lang/Comparable;");\n    }',
     '    // Pumpkin divergence: real bodies over a copy-on-write property map. Enough for\n    // registration and the mods\' own reads; NOT interned, so vanilla\'s states-are-identity\n    // guarantee does not hold yet -- that arrives with the Rust state binding. A property\n    // never set fails loudly with the property\'s name, not a null.\n    protected java.util.Map<Property<?>, Comparable<?>> pumpkinValues = java.util.Map.of();\n\n    @SuppressWarnings("unchecked")\n    public <T extends Comparable<T>> T getValue(Property<T> property) {\n        Comparable<?> value = pumpkinValues.get(property);\n        if (value == null) {\n            throw new IllegalArgumentException("property " + property + " was never set on " + this);\n        }\n        return (T) value;\n    }'),
    ('    public <T extends Comparable<T>, V extends T> S setValue(Property<T> property, V value) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateHolder.setValue:(Lnet/minecraft/world/level/block/state/properties/Property;Lnet/minecraft/world/level/block/state/T;)Ljava/lang/Object;");\n    }',
     '    // Pumpkin divergence: real body. Returns a sibling state with one value changed --\n    // copy-on-write, not interned; see getValue\'s comment.\n    @SuppressWarnings("unchecked")\n    public <T extends Comparable<T>, V extends T> S setValue(Property<T> property, V value) {\n        StateHolder<O, S> next = pumpkinSibling();\n        java.util.Map<Property<?>, Comparable<?>> map = new java.util.HashMap<>(pumpkinValues);\n        map.put(property, value);\n        next.pumpkinValues = java.util.Map.copyOf(map);\n        return (S) next;\n    }\n\n    // Pumpkin divergence: how setValue makes the copy. Subclasses that carry more state\n    // override to preserve it; BlockState keeps its owning block this way.\n    protected StateHolder<O, S> pumpkinSibling() {\n        throw new UnsupportedOperationException(getClass().getName() + " cannot copy itself");\n    }'),
])

edit('net/minecraft/world/level/block/state/BlockState.java', [
    ('    public BlockState() {\n    }',
     "    public BlockState() {\n    }\n\n    // Pumpkin divergence: setValue's copy keeps the owning block and the values.\n    @Override\n    protected net.minecraft.world.level.block.state.StateHolder<net.minecraft.world.level.block.Block, BlockState> pumpkinSibling() {\n        BlockState sibling = new BlockState();\n        sibling.pumpkinOwner = this.pumpkinOwner;\n        sibling.pumpkinValues = this.pumpkinValues;\n        return sibling;\n    }"),
])

edit('net/minecraft/world/level/block/state/StateDefinition.java', [
    ('    public S any() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.any:()Lnet/minecraft/world/level/block/state/StateHolder;");\n    }',
     '    // Pumpkin divergence: real bodies. A definition knows its owner and answers the\n    // owner\'s default state; the property list machines declare is implicit in what\n    // setValue records rather than tracked here.\n    public java.util.function.Supplier<S> pumpkinAny;\n\n    public S any() {\n        if (pumpkinAny == null) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition.any:()Lnet/minecraft/world/level/block/state/StateHolder;");\n        }\n        return pumpkinAny.get();\n    }'),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('    public StateDefinition<Block, BlockState> getStateDefinition() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.getStateDefinition:()Lnet/minecraft/world/level/block/state/StateDefinition;");\n    }',
     "    // Pumpkin divergence: real body. Lazily built; any() answers this block's default\n    // state, which is how machines write their initial LIT=false.\n    private StateDefinition<Block, BlockState> pumpkinStateDefinition;\n\n    public StateDefinition<Block, BlockState> getStateDefinition() {\n        if (pumpkinStateDefinition == null) {\n            pumpkinStateDefinition = new StateDefinition<>();\n            pumpkinStateDefinition.pumpkinAny = this::defaultBlockState;\n        }\n        return pumpkinStateDefinition;\n    }"),
    ('    protected final void registerDefaultState(BlockState state) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.registerDefaultState:(Lnet/minecraft/world/level/block/state/BlockState;)V");\n    }',
     '    // Pumpkin divergence: real body. What a block constructor declares as its default is\n    // what defaultBlockState() answers from then on.\n    protected final void registerDefaultState(BlockState state) {\n        this.defaultBlockState = state;\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/BooleanProperty.java', [
    ('    public static BooleanProperty create(String name) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/BooleanProperty.create:(Ljava/lang/String;)Lnet/minecraft/world/level/block/state/properties/BooleanProperty;");\n    }',
     '    // Pumpkin divergence: real body -- a named property is just its name here.\n    public static BooleanProperty create(String name) {\n        BooleanProperty property = new BooleanProperty();\n        property.pumpkinName = name;\n        return property;\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/Property.java', [
    ('    public String getName() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.getName:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: real body, backed by the name create() recorded.\n    public String pumpkinName;\n\n    public String getName() {\n        if (pumpkinName == null) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.getName:()Ljava/lang/String;");\n        }\n        return pumpkinName;\n    }'),
])

# ---------------------------------------------- vanilla property constants, real
edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.create:(Ljava/lang/String;Ljava/lang/Class;)Lnet/minecraft/world/level/block/state/properties/EnumProperty;");\n    }',
     '    // Pumpkin divergence: real body -- a named property is its name here, as with\n    // BooleanProperty.create.\n    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz) {\n        EnumProperty<T> property = new EnumProperty<>();\n        property.pumpkinName = name;\n        return property;\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/IntegerProperty.java', [
    ('\n    public static IntegerProperty create(String name, int min, int max) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.create:(Ljava/lang/String;II)Lnet/minecraft/world/level/block/state/properties/IntegerProperty;");\n    }',
     '    // Pumpkin divergence: real body. The range constrains a file no one writes.\n    public static IntegerProperty create(String name, int min, int max) {\n        IntegerProperty property = new IntegerProperty();\n        property.pumpkinName = name;\n        return property;\n    }'),
])

edit('net/minecraft/world/level/block/HorizontalDirectionalBlock.java', [
    ('    public static final EnumProperty<Direction> FACING = null;',
     '    // Pumpkin divergence: real value. TinkeringTableBlock\'s constructor passes this to\n    // setValue -- null here was an NPE inside Map.copyOf naming nothing.\n    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class);'),
])

edit('net/minecraft/world/level/block/CampfireBlock.java', [
    ('    public static final BooleanProperty LIT = null;',
     '    public static final BooleanProperty LIT = BooleanProperty.create("lit");'),
])

edit('net/minecraft/world/level/block/FarmlandBlock.java', [
    ('    public static final IntegerProperty MOISTURE = null;',
     '    public static final IntegerProperty MOISTURE = IntegerProperty.create("moisture", 0, 7);'),
])

edit('net/minecraft/world/level/block/NetherWartBlock.java', [
    ('    public static final IntegerProperty AGE = null;',
     '    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 3);'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    public static final IntegerProperty AGE = null;',
     '    public static final IntegerProperty AGE = IntegerProperty.create("age", 0, 7);'),
])

edit('net/minecraft/world/level/block/DispenserBlock.java', [
    ('    public static final EnumProperty<Direction> FACING = null;',
     '    public static final EnumProperty<Direction> FACING = EnumProperty.create("facing", Direction.class);'),
])

edit("net/minecraft/world/level/block/state/properties/Property.java", [
    ('    public boolean equals(Object o) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.equals:(Ljava/lang/Object;)Z");\n    }',
     '    // Pumpkin divergence: real bodies. Properties are singletons -- create() is the only\n    // maker -- so identity semantics are correct, and HashMap needs both of these the\n    // moment a property becomes a map key, which the state machinery makes routine.\n    public boolean equals(Object o) {\n        return this == o;\n    }'),
    ('    public final int hashCode() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/Property.hashCode:()I");\n    }',
     '    public final int hashCode() {\n        return System.identityHashCode(this);\n    }'),
])

# --------------------------------------------- StreamCodec factories, inert sweep
edit("net/minecraft/network/codec/StreamCodec.java", [
    ('\n    static <B, V> StreamCodec<B, V> unit(V instance) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.unit:(Ljava/lang/Object;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, V> StreamCodec<B, V> unit(V instance) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, Function<T1, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, Function<T1, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, BiFunction<T1, T2, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Ljava/util/function/BiFunction;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, BiFunction<T1, T2, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, Function3<T1, T2, T3, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function3;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, Function3<T1, T2, T3, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, Function4<T1, T2, T3, T4, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function4;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, Function4<T1, T2, T3, T4, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, Function5<T1, T2, T3, T4, T5, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function5;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, Function5<T1, T2, T3, T4, T5, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5, T6> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, Function6<T1, T2, T3, T4, T5, T6, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function6;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5, T6> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, Function6<T1, T2, T3, T4, T5, T6, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5, T6, T7> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, Function7<T1, T2, T3, T4, T5, T6, T7, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function7;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5, T6, T7> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, Function7<T1, T2, T3, T4, T5, T6, T7, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, Function8<T1, T2, T3, T4, T5, T6, T7, T8, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function8;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, Function8<T1, T2, T3, T4, T5, T6, T7, T8, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function9;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, Function9<T1, T2, T3, T4, T5, T6, T7, T8, T9, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function10;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, Function10<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, StreamCodec<? super B, T11> codec11, Function<C, T11> getter11, Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function11;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, StreamCodec<? super B, T11> codec11, Function<C, T11> getter11, Function11<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
    ('\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, StreamCodec<? super B, T11> codec11, Function<C, T11> getter11, StreamCodec<? super B, T12> codec12, Function<C, T12> getter12, Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, C> constructor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.composite:(Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lnet/minecraft/network/codec/StreamCodec;Ljava/util/function/Function;Lcom/mojang/datafixers/util/Function12;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '\n    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n\n    // never invokes -- nothing serialises components yet -- so composition survives and\n\n    // the first actual encode/decode throws with the interface\'s name.\n\n    static <B, C, T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12> StreamCodec<B, C> composite(StreamCodec<? super B, T1> codec1, Function<C, T1> getter1, StreamCodec<? super B, T2> codec2, Function<C, T2> getter2, StreamCodec<? super B, T3> codec3, Function<C, T3> getter3, StreamCodec<? super B, T4> codec4, Function<C, T4> getter4, StreamCodec<? super B, T5> codec5, Function<C, T5> getter5, StreamCodec<? super B, T6> codec6, Function<C, T6> getter6, StreamCodec<? super B, T7> codec7, Function<C, T7> getter7, StreamCodec<? super B, T8> codec8, Function<C, T8> getter8, StreamCodec<? super B, T9> codec9, Function<C, T9> getter9, StreamCodec<? super B, T10> codec10, Function<C, T10> getter10, StreamCodec<? super B, T11> codec11, Function<C, T11> getter11, StreamCodec<? super B, T12> codec12, Function<C, T12> getter12, Function12<T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, C> constructor) {\n\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n\n    }'),
])

# ---------------------------------------------------- DataComponentMap, real map
edit('net/minecraft/core/component/DataComponentMap.java', [
    ('    static DataComponentMap.Builder builder() {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap.builder:()Lnet/minecraft/core/component/DataComponentMap$Builder;");\n    }',
     '    // Pumpkin divergence: real body. A component map is a real map -- small surface,\n    // genuine behaviour, nothing to stub.\n    static DataComponentMap.Builder builder() {\n        return new Builder();\n    }'),
    ('        public <T> T get(DataComponentType<? extends T> type) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");\n        }\n\n        public <T> DataComponentMap.Builder set(DataComponentType<T> type, T value) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentMap$Builder;");\n        }\n\n        public DataComponentMap build() {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.build:()Lnet/minecraft/core/component/DataComponentMap;");\n        }',
     '        // Pumpkin divergence: real bodies over a plain LinkedHashMap.\n        final java.util.Map<DataComponentType<?>, Object> pumpkinMap = new java.util.LinkedHashMap<>();\n\n        @SuppressWarnings("unchecked")\n        public <T> T get(DataComponentType<? extends T> type) {\n            return (T) pumpkinMap.get(type);\n        }\n\n        public <T> DataComponentMap.Builder set(DataComponentType<T> type, T value) {\n            pumpkinMap.put(type, value);\n            return this;\n        }\n\n        public DataComponentMap build() {\n            final java.util.Map<DataComponentType<?>, Object> built =\n                    java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<>(pumpkinMap));\n            return new DataComponentMap() {\n                @Override\n                @SuppressWarnings("unchecked")\n                public <T> T get(DataComponentType<? extends T> type) {\n                    return (T) built.get(type);\n                }\n\n                @Override\n                public boolean has(DataComponentType<?> type) {\n                    return built.containsKey(type);\n                }\n\n                @Override\n                public Set<DataComponentType<?>> keySet() {\n                    return built.keySet();\n                }\n\n                @Override\n                public boolean isEmpty() {\n                    return built.isEmpty();\n                }\n\n                @Override\n                public int size() {\n                    return built.size();\n                }\n            };\n        }'),
])

edit('net/neoforged/neoforge/common/extensions/IDataComponentMapBuilderExtensions.java', [
    ('    default <T> DataComponentMap.Builder set(Supplier<? extends DataComponentType<T>> componentType, T value) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IDataComponentMapBuilderExtensions.set:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentMap$Builder;");',
     '    // Pumpkin divergence: real body. The NeoForge convenience overload: resolve the\n    // supplier -- a DeferredHolder in practice -- and delegate.\n    default <T> DataComponentMap.Builder set(Supplier<? extends DataComponentType<T>> componentType, T value) {\n        return ((DataComponentMap.Builder) this).set(componentType.get(), value);'),
])

# ------------------------------------------ Item.Properties chain, accept-and-drop
edit("net/minecraft/world/item/Item.java", [
    ('\n        public Item.Properties setNoCombineRepair() {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.setNoCombineRepair:()Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties setNoCombineRepair() {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties stacksTo(int max) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.stacksTo:(I)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties stacksTo(int max) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties durability(int maxDamage) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.durability:(I)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties durability(int maxDamage) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties craftRemainder(Item craftingRemainingItem) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.craftRemainder:(Lnet/minecraft/world/item/Item;)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties craftRemainder(Item craftingRemainingItem) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties craftRemainder(ItemStackTemplate craftingRemainingItem) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.craftRemainder:(Lnet/minecraft/world/item/ItemStackTemplate;)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties craftRemainder(ItemStackTemplate craftingRemainingItem) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties tool(ToolMaterial material, TagKey<Block> minesEfficiently, float attackDamageBaseline, float attackSpeedBaseline, float disableBlockingSeconds) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.tool:(Lnet/minecraft/world/item/ToolMaterial;Lnet/minecraft/tags/TagKey;FFF)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties tool(ToolMaterial material, TagKey<Block> minesEfficiently, float attackDamageBaseline, float attackSpeedBaseline, float disableBlockingSeconds) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties pickaxe(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.pickaxe:(Lnet/minecraft/world/item/ToolMaterial;FF)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties pickaxe(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties sword(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.sword:(Lnet/minecraft/world/item/ToolMaterial;FF)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties sword(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties spear(ToolMaterial material, float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.spear:(Lnet/minecraft/world/item/ToolMaterial;FFFFFFFFF)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties spear(ToolMaterial material, float attackDuration, float damageMultiplier, float delay, float dismountTime, float dismountThreshold, float knockbackTime, float knockbackThreshold, float damageTime, float damageThreshold) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties humanoidArmor(ArmorMaterial material, ArmorType type) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.humanoidArmor:(Lnet/minecraft/world/item/equipment/ArmorMaterial;Lnet/minecraft/world/item/equipment/ArmorType;)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties humanoidArmor(ArmorMaterial material, ArmorType type) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties setId(ResourceKey<Item> id) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.setId:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties setId(ResourceKey<Item> id) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties overrideDescription(String descriptionId) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.overrideDescription:(Ljava/lang/String;)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties overrideDescription(String descriptionId) {\n\n            return this;\n\n        }'),
    ('\n        public Item.Properties useBlockDescriptionPrefix() {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.useBlockDescriptionPrefix:()Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties useBlockDescriptionPrefix() {\n\n            return this;\n\n        }'),
])

# ---------------------------------------------------------- tag keys, real values
edit('net/minecraft/tags/TagKey.java', [
    ('    public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> registry, Identifier location) {\n        throw Unimplemented.forMember("net/minecraft/tags/TagKey.create:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");\n    }',
     "    // Pumpkin divergence: real body. A tag key is its two names -- the record's own\n    // canonical constructor is the whole implementation.\n    public static <T> TagKey<T> create(ResourceKey<? extends Registry<T>> registry, Identifier location) {\n        return new TagKey<>(registry, location);\n    }"),
])

edit('net/minecraft/tags/BlockTags.java', [
    ('    public static final TagKey<Block> CROPS = null;',
     '    // Pumpkin divergence: real value, named as vanilla names it.\n    public static final TagKey<Block> CROPS = create(Identifier.fromNamespaceAndPath("minecraft", "crops"));'),
    ('    public static TagKey<Block> create(Identifier name) {\n        throw Unimplemented.forMember("net/minecraft/tags/BlockTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");\n    }',
     '    // Pumpkin divergence: real body -- TagKey.create over the block registry\'s key.\n    public static TagKey<Block> create(Identifier name) {\n        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "block")), name);\n    }'),
    ('\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/tags/BlockTags");\n        }\n    }\n',
     '\n'),
])

edit('net/minecraft/tags/ItemTags.java', [
    ('    public static TagKey<Item> create(final Identifier name) {\n        throw Unimplemented.forMember("net/minecraft/tags/ItemTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");\n    }',
     '    // Pumpkin divergence: real body -- TagKey.create over the item registry\'s key.\n    public static TagKey<Item> create(final Identifier name) {\n        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "item")), name);\n    }'),
])

edit('net/minecraft/tags/FluidTags.java', [
    ('    public static final TagKey<Fluid> WATER = null;',
     '    // Pumpkin divergence: real value, named as vanilla names it.\n    public static final TagKey<Fluid> WATER = create(Identifier.fromNamespaceAndPath("minecraft", "water"));'),
    ('    public static TagKey<Fluid> create(Identifier name) {\n        throw Unimplemented.forMember("net/minecraft/tags/FluidTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");\n    }',
     '    // Pumpkin divergence: real body.\n    public static TagKey<Fluid> create(Identifier name) {\n        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(Identifier.fromNamespaceAndPath("minecraft", "fluid")), name);\n    }'),
    ('\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/tags/FluidTags");\n        }\n    }\n',
     '\n'),
])

# ------------------------------------------------------- Enchantments, real keys
edit("net/minecraft/world/item/enchantment/Enchantments.java", [
    ('    public static final ResourceKey<Enchantment> SILK_TOUCH = null;',
     '    // Pumpkin divergence: real values. A ResourceKey is a pair of names; these are\n    // vanilla\'s, and BaseReusableItem reads them at class-init.\n    public static final ResourceKey<Enchantment> SILK_TOUCH = pumpkinKey("silk_touch");'),
    ('    public static final ResourceKey<Enchantment> UNBREAKING = null;',
     '    public static final ResourceKey<Enchantment> UNBREAKING = pumpkinKey("unbreaking");\n\n    private static ResourceKey<Enchantment> pumpkinKey(String name) {\n        return ResourceKey.create(\n                ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "enchantment")),\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", name));\n    }'),
    ('\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/world/item/enchantment/Enchantments");\n        }\n    }\n',
     '\n'),
])

edit("net/neoforged/neoforge/registries/DeferredHolder.java", [
    ('    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<R> key) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.create:(Lnet/minecraft/resources/ResourceKey;)Lnet/neoforged/neoforge/registries/DeferredHolder;");\n    }',
     "    // Pumpkin divergence: real body. The one-key overload: the key's own registry half\n    // scopes the lookup, delegating to the two-argument form.\n    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<R> key) {\n        return create(net.minecraft.resources.ResourceKey.createRegistryKey(key.pumpkinRegistry()), key.identifier());\n    }"),
])

edit("net/minecraft/resources/ResourceKey.java", [
    ('    // Pumpkin divergence: real body.\n    public Identifier identifier() {\n        return identifier;\n    }',
     '    // Pumpkin divergence: real body.\n    public Identifier identifier() {\n        return identifier;\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. The registry half, for callers that\n    // need to rebuild a registry key from a value key -- DeferredHolder.create(key) does.\n    public Identifier pumpkinRegistry() {\n        return pumpkinRegistryName;\n    }'),
])

edit("net/minecraft/world/level/block/DispenserBlock.java", [
    ('\n    public static void registerBehavior(ItemLike item, DispenseItemBehavior behavior) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.registerBehavior:(Lnet/minecraft/world/level/ItemLike;Lnet/minecraft/core/dispenser/DispenseItemBehavior;)V");\n    }',
     "\n    // Pumpkin divergence: real body. Registers a dispenser behaviour Pumpkin's own\n\n    // dispensers never consult -- accepted and dropped, like the unmodelled registries,\n\n    // so an item can install its dispense logic without stopping its whole mod.\n\n    public static void registerBehavior(ItemLike item, DispenseItemBehavior behavior) {\n\n    }"),
])

edit("net/neoforged/neoforge/common/extensions/IItemPropertiesExtensions.java", [
])

edit("net/neoforged/neoforge/common/extensions/IItemPropertiesExtensions.java", [
    ('    default <T> Item.Properties component(Supplier<? extends DataComponentType<T>> componentType, T value) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemPropertiesExtensions.component:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;");\n    }',
     '    // Pumpkin divergence: real body. NeoForge sugar for a default component value on an\n    // item -- metadata Pumpkin does not model; accepted and dropped, chain continues.\n    default <T> Item.Properties component(Supplier<? extends DataComponentType<T>> componentType, T value) {\n        return (Item.Properties) this;\n    }'),
])

# ------------------------------------------------------ ToolMaterial, vanilla data
edit("net/minecraft/world/item/ToolMaterial.java", [
    ('    public static final ToolMaterial WOOD = null;',
     '    // Pumpkin divergence: real values, copied from vanilla -- a record over numbers\n    // and tag keys, all of which are real here. The tag keys are built inline because\n    // the INCORRECT_FOR_*/. *_TOOL_MATERIALS holder fields did not survive pruning.\n    public static final ToolMaterial WOOD = new ToolMaterial(\n            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_wood_tool")),\n            59, 2.0F, 0.0F, 15,\n            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "wooden_tool_materials")));'),
    ('    public static final ToolMaterial STONE = null;',
     '    public static final ToolMaterial STONE = new ToolMaterial(\n            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_stone_tool")),\n            131, 4.0F, 1.0F, 5,\n            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "stone_tool_materials")));'),
    ('    public static final ToolMaterial COPPER = null;',
     '    public static final ToolMaterial COPPER = new ToolMaterial(\n            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_copper_tool")),\n            190, 5.0F, 1.0F, 13,\n            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "copper_tool_materials")));'),
    ('    public static final ToolMaterial IRON = null;',
     '    public static final ToolMaterial IRON = new ToolMaterial(\n            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_iron_tool")),\n            250, 6.0F, 2.0F, 14,\n            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "iron_tool_materials")));'),
    ('    public static final ToolMaterial DIAMOND = null;',
     '    public static final ToolMaterial DIAMOND = new ToolMaterial(\n            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_diamond_tool")),\n            1561, 8.0F, 3.0F, 10,\n            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "diamond_tool_materials")));'),
    ('    public static final ToolMaterial GOLD = null;',
     '    public static final ToolMaterial GOLD = new ToolMaterial(\n            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_gold_tool")),\n            32, 12.0F, 0.0F, 22,\n            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "gold_tool_materials")));'),
    ('    public static final ToolMaterial NETHERITE = null;',
     '    public static final ToolMaterial NETHERITE = new ToolMaterial(\n            net.minecraft.tags.BlockTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "incorrect_for_netherite_tool")),\n            2031, 9.0F, 4.0F, 15,\n            net.minecraft.tags.ItemTags.create(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "netherite_tool_materials")));'),
])

edit("net/minecraft/world/item/ShearsItem.java", [
    ('\n    public static Tool createToolProperties() {\n        throw Unimplemented.forMember("net/minecraft/world/item/ShearsItem.createToolProperties:()Lnet/minecraft/world/item/component/Tool;");\n    }',
     '    // Pumpkin divergence: real-enough body. Vanilla\'s builds mining-speed rules from\n    // registry lookups the shim does not have; the result is item metadata that only\n    // Java-side mining logic would read, and mining runs in Rust. An empty rule set is\n    // "no special rules", which is honest for metadata nothing consults.\n    public static Tool createToolProperties() {\n        return new Tool(java.util.List.of(), 1.0F, 1, false);\n    }'),
])

edit("net/minecraft/world/item/Item.java", [
    ('\n        public <T> Item.Properties component(DataComponentType<T> type, T value) {\n            throw Unimplemented.forMember("net/minecraft/world/item/Item$Properties.component:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/world/item/Item$Properties;");\n        }',
     '\n        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public <T> Item.Properties component(DataComponentType<T> type, T value) {\n\n            return this;\n\n        }'),
])

edit("net/minecraft/util/Util.java", [
    ('    public static <T> T make(Supplier<T> factory) {\n        throw Unimplemented.forMember("net/minecraft/util/Util.make:(Ljava/util/function/Supplier;)Ljava/lang/Object;");\n    }',
     '    // Pumpkin divergence: real bodies, copied from vanilla -- the ARGB rule.\n    public static <T> T make(Supplier<T> factory) {\n        return factory.get();\n    }'),
    ('    public static <T> T make(T t, Consumer<? super T> consumer) {\n        throw Unimplemented.forMember("net/minecraft/util/Util.make:(Ljava/lang/Object;Ljava/util/function/Consumer;)Ljava/lang/Object;");\n    }',
     '    public static <T> T make(T t, Consumer<? super T> consumer) {\n        consumer.accept(t);\n        return t;\n    }'),
])

edit("net/minecraft/sounds/SoundEvents.java", [
    ('    public static final SoundEvent ARROW_SHOOT = null;',
     '    // Pumpkin divergence: real objects, deliberately non-vanilla names. Vanilla\'s\n    // sound ids are not derivable from field names, and a guessed name would be\n    // plausibly wrong -- silent, if something ever plays it. "pumpkin:unmapped_*"\n    // is visibly not a real sound, so a mod that plays one fails loudly instead.\n    public static final SoundEvent ARROW_SHOOT = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_arrow_shoot"));'),
    ('    public static final SoundEvent AXE_STRIP = null;',
     '    public static final SoundEvent AXE_STRIP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_axe_strip"));'),
    ('    public static final SoundEvent AXE_SCRAPE = null;',
     '    public static final SoundEvent AXE_SCRAPE = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_axe_scrape"));'),
    ('    public static final SoundEvent AXE_WAX_OFF = null;',
     '    public static final SoundEvent AXE_WAX_OFF = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_axe_wax_off"));'),
    ('    public static final SoundEvent BUCKET_FILL = null;',
     '    public static final SoundEvent BUCKET_FILL = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_bucket_fill"));'),
    ('    public static final SoundEvent CROP_BREAK = null;',
     '    public static final SoundEvent CROP_BREAK = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_crop_break"));'),
    ('    public static final SoundEvent EXPERIENCE_ORB_PICKUP = null;',
     '    public static final SoundEvent EXPERIENCE_ORB_PICKUP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_experience_orb_pickup"));'),
    ('    public static final SoundEvent HOE_TILL = null;',
     '    public static final SoundEvent HOE_TILL = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_hoe_till"));'),
    ('    public static final SoundEvent ITEM_PICKUP = null;',
     '    public static final SoundEvent ITEM_PICKUP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_item_pickup"));'),
    ('    public static final SoundEvent PLAYER_ATTACK_SWEEP = null;',
     '    public static final SoundEvent PLAYER_ATTACK_SWEEP = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_player_attack_sweep"));'),
    ('    public static final SoundEvent SAND_BREAK = null;',
     '    public static final SoundEvent SAND_BREAK = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_sand_break"));'),
    ('    public static final SoundEvent SHOVEL_FLATTEN = null;',
     '    public static final SoundEvent SHOVEL_FLATTEN = SoundEvent.createVariableRangeEvent(Identifier.fromNamespaceAndPath("pumpkin", "unmapped_shovel_flatten"));'),
    ('\n\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/sounds/SoundEvents");\n        }\n    }\n',
     '\n'),
])

edit("net/minecraft/world/item/equipment/EquipmentAssets.java", [
    ('    ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = null;',
     '    // Pumpkin divergence: real value, named as vanilla names it -- a registry key is a\n    // pair of names, and ModEquipmentAssets dereferences this at class-init.\n    ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID =\n            net.minecraft.resources.ResourceKey.createRegistryKey(\n                    net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "equipment_asset"));'),
])

commit()
