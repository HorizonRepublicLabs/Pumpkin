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
    ('        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.<init>:(Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;Ljava/util/List;Ljava/util/function/Supplier;)V");\n        }\n\n        public T get() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.get:()Ljava/lang/Object;");\n        }',
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
    ('    public int hashCode() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.hashCode:()I");\n    }',
     '    // Pumpkin divergence: real body. A mod keys a map by holder, so this has to answer.\n    // The id is the identity -- two holders naming the same thing are the same handle,\n    // which is what vanilla means by it too, and the resolved value is deliberately not\n    // consulted because reading it would force every deferred registration.\n    @Override\n    public int hashCode() {\n        return pumpkinId.hashCode();\n    }'),
    ('    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<? extends Registry<R>> registryKey, Identifier valueName) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.create:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/registries/DeferredHolder;");\n    }',
     "    // Pumpkin divergence: real body. A mod builds a holder for something another mod\n    // registered -- MysticalAgriculture does this for its own blocks -- and only the value's\n    // name matters here. Which registry it lives in is carried by the caller's own type, and\n    // the flush that reads this holder resolves by name.\n    //\n    // The factory is null: this holder names something it did not create, so get() would\n    // have nothing to call. A mod that asks for the value gets a NullPointerException rather\n    // than a wrong object, which is the honest failure until cross-registry lookup exists.\n    public static <R, T extends R> DeferredHolder<R, T> create(ResourceKey<? extends Registry<R>> registryKey, Identifier valueName) {\n        return new DeferredHolder<>(valueName, null);\n    }"),

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
     '    // Pumpkin divergence: real body. The other way a mod registers content -- straight into\n    // the game during the event, rather than declaring it up front through a\n    // DeferredRegister. MysticalAgriculture uses both.\n    //\n    // The helper routes to the same sink DeferredRegister\'s flush does, so the two paths\n    // cannot drift into registering differently. Only blocks are carried so far, and anything\n    // else stops loudly rather than being dropped -- a silently ignored registration is a mod\n    // whose content simply is not there, with nothing to say why.\n    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Consumer<RegisterHelper<T>> consumer) {\n        consumer.accept((name, value) -> {\n            if (value instanceof net.minecraft.world.level.block.Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else {\n                DeferredRegister.pumpkinWarnUnsupported(registryKey.identifier().toString(), name.toString());\n            }\n        });\n    }'),
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

commit()
