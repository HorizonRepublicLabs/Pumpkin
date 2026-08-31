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
            # Bridge files survive regeneration untouched, so their recorded edits are
            # already in place on replay; a pair whose replacement is present is done.
            if b in s:
                continue
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

for needed in ("net.minecraft.resources.Identifier", "net.minecraft.resources.ResourceKey"):
    if "import " + needed + ";" not in s:
        s = s.replace("import dev.pumpkin.shim.Stubs;",
                      "import " + needed + ";\nimport dev.pumpkin.shim.Stubs;", 1)
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
    # The Mekanism family references these; names verified against the decompiled
    # vanilla Registries.java, worldgen/ prefixes and all.
    "DATA_COMPONENT_PREDICATE_TYPE": "data_component_predicate_type",
    "FLUID": "fluid", "GAME_EVENT": "game_event",
    "HEIGHT_PROVIDER_TYPE": "height_provider_type",
    "INT_PROVIDER_TYPE": "int_provider_type",
    "LOOT_FUNCTION_TYPE": "loot_function_type", "MOB_EFFECT": "mob_effect",
    "PARTICLE_TYPE": "particle_type",
    "PLACEMENT_MODIFIER_TYPE": "worldgen/placement_modifier_type",
    "RECIPE_DISPLAY": "recipe_display", "SLOT_DISPLAY": "slot_display",
    "SOUND_EVENT": "sound_event", "BIOME": "worldgen/biome",
    "DAMAGE_TYPE": "damage_type", "DIMENSION_TYPE": "dimension_type",
    "PLACED_FEATURE": "worldgen/placed_feature", "STRUCTURE": "worldgen/structure",
    "TRIGGER_TYPE": "trigger_type",
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
        return ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", name));
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
     '    // Pumpkin divergence: real bodies over a copy-on-write property map. Enough for\n    // registration and the mods\' own reads; NOT interned, so vanilla\'s states-are-identity\n    // guarantee does not hold yet -- that arrives with the Rust state binding. A property\n    // never set fails loudly with the property\'s name, not a null.\n    public java.util.Map<Property<?>, Comparable<?>> pumpkinValues = java.util.Map.of();\n\n    @SuppressWarnings("unchecked")\n    public <T extends Comparable<T>> T getValue(Property<T> property) {\n        Comparable<?> value = pumpkinValues.get(property);\n        if (value == null) {\n            throw new IllegalArgumentException("property " + property + " was never set on " + this);\n        }\n        return (T) value;\n    }'),
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
    ('        public <T> T get(DataComponentType<? extends T> type) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.get:(Lnet/minecraft/core/component/DataComponentType;)Ljava/lang/Object;");\n        }',
     '        // Pumpkin divergence: real bodies over a plain LinkedHashMap.\n        final java.util.Map<DataComponentType<?>, Object> pumpkinMap = new java.util.LinkedHashMap<>();\n\n        @SuppressWarnings("unchecked")\n        public <T> T get(DataComponentType<? extends T> type) {\n            return (T) pumpkinMap.get(type);\n        }'),
    ('        public <T> DataComponentMap.Builder set(DataComponentType<T> type, T value) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentMap$Builder;");\n        }',
     '        public <T> DataComponentMap.Builder set(DataComponentType<T> type, T value) {\n            pumpkinMap.put(type, value);\n            return this;\n        }'),
    ('        public DataComponentMap.Builder addAll(DataComponentMap map) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.addAll:(Lnet/minecraft/core/component/DataComponentMap;)Lnet/minecraft/core/component/DataComponentMap$Builder;");\n        }',
     '        // Pumpkin divergence: real body -- copy the other map\'s entries in.\n        public DataComponentMap.Builder addAll(DataComponentMap map) {\n            for (DataComponentType<?> type : map.keySet()) {\n                pumpkinMap.put(type, map.get(type));\n            }\n            return this;\n        }'),
    ('        public DataComponentMap build() {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentMap$Builder.build:()Lnet/minecraft/core/component/DataComponentMap;");\n        }',
     '        public DataComponentMap build() {\n            final java.util.Map<DataComponentType<?>, Object> built =\n                    java.util.Collections.unmodifiableMap(new java.util.LinkedHashMap<>(pumpkinMap));\n            return new DataComponentMap() {\n                @Override\n                @SuppressWarnings("unchecked")\n                public <T> T get(DataComponentType<? extends T> type) {\n                    return (T) built.get(type);\n                }\n\n                @Override\n                public boolean has(DataComponentType<?> type) {\n                    return built.containsKey(type);\n                }\n\n                @Override\n                public Set<DataComponentType<?>> keySet() {\n                    return built.keySet();\n                }\n\n                @Override\n                public boolean isEmpty() {\n                    return built.isEmpty();\n                }\n\n                @Override\n                public int size() {\n                    return built.size();\n                }\n            };\n        }'),
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
     '    // Pumpkin divergence: real body -- TagKey.create over the block registry\'s key.\n    public static TagKey<Block> create(Identifier name) {\n        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "block")), name);\n    }'),
    ('\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/tags/BlockTags");\n        }\n    }\n',
     '\n'),
])

edit('net/minecraft/tags/ItemTags.java', [
    ('    public static TagKey<Item> create(final Identifier name) {\n        throw Unimplemented.forMember("net/minecraft/tags/ItemTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");\n    }',
     '    // Pumpkin divergence: real body -- TagKey.create over the item registry\'s key.\n    public static TagKey<Item> create(final Identifier name) {\n        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "item")), name);\n    }'),
])

edit('net/minecraft/tags/FluidTags.java', [
    ('    public static final TagKey<Fluid> WATER = null;',
     '    // Pumpkin divergence: real value, named as vanilla names it.\n    public static final TagKey<Fluid> WATER = create(Identifier.fromNamespaceAndPath("minecraft", "water"));'),
    ('    public static TagKey<Fluid> create(Identifier name) {\n        throw Unimplemented.forMember("net/minecraft/tags/FluidTags.create:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/tags/TagKey;");\n    }',
     '    // Pumpkin divergence: real body.\n    public static TagKey<Fluid> create(Identifier name) {\n        return TagKey.create(net.minecraft.resources.ResourceKey.createRegistryKey(net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "fluid")), name);\n    }'),
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

# ---------------------------------------------- creative tabs, inert presentation
edit("net/minecraft/world/item/CreativeModeTab.java", [
    ('\n    public static CreativeModeTab.Builder builder() {\n        throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab.builder:()Lnet/minecraft/world/item/CreativeModeTab$Builder;");\n    }',
     "    // Pumpkin divergence: real body. A creative tab is client-side presentation Pumpkin\n    // never renders; the builder exists so a mod's registration completes, and the tab it\n    // yields is inert.\n    public static CreativeModeTab.Builder builder() {\n        return new Builder(null, 0);\n    }"),
    ('\n        public CreativeModeTab.Builder title(Component displayName) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.title:(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");\n        }',
     '\n        // Pumpkin divergence: accepted and dropped -- client-side presentation.\n\n        public CreativeModeTab.Builder title(Component displayName) {\n\n            return this;\n\n        }'),
    ('\n        public CreativeModeTab.Builder icon(Supplier<ItemStack> iconGenerator) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.icon:(Ljava/util/function/Supplier;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");\n        }',
     '\n        // Pumpkin divergence: accepted and dropped -- client-side presentation.\n\n        public CreativeModeTab.Builder icon(Supplier<ItemStack> iconGenerator) {\n\n            return this;\n\n        }'),
    ('\n        public CreativeModeTab.Builder displayItems(CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.displayItems:(Lnet/minecraft/world/item/CreativeModeTab$DisplayItemsGenerator;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");\n        }',
     '\n        // Pumpkin divergence: accepted and dropped -- client-side presentation.\n\n        public CreativeModeTab.Builder displayItems(CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {\n\n            return this;\n\n        }'),
    ('\n        public CreativeModeTab.Builder displayItems(Collection<? extends net.minecraft.core.Holder<? extends ItemLike>> collection) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.displayItems:(Ljava/util/Collection;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");\n        }',
     '\n        // Pumpkin divergence: accepted and dropped -- client-side presentation.\n\n        public CreativeModeTab.Builder displayItems(Collection<? extends net.minecraft.core.Holder<? extends ItemLike>> collection) {\n\n            return this;\n\n        }'),
    ('\n        public CreativeModeTab build() {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.build:()Lnet/minecraft/world/item/CreativeModeTab;");\n        }',
     '        // Pumpkin divergence: real body -- an inert tab; its own methods still throw.\n        public CreativeModeTab build() {\n            return new CreativeModeTab();\n        }'),
])

# ------------------------------------------------------------- components, text
edit("net/minecraft/network/chat/Component.java", [
    ('\n    static MutableComponent literal(String text) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.literal:(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '\n    // Pumpkin divergence: real bodies. A component is text; translation keys stay\n\n    // keys, because the server has no language files and the client translates.\n\n    static MutableComponent literal(String text) {\n\n        return MutableComponent.pumpkinOf(text);\n\n    }'),
    ('\n    static MutableComponent translatable(String key) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.translatable:(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '\n    static MutableComponent translatable(String key) {\n\n        return MutableComponent.pumpkinOf(key);\n\n    }'),
    ('\n    static MutableComponent translatable(String key, Object... args) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.translatable:(Ljava/lang/String;[Ljava/lang/Object;)Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '\n    static MutableComponent translatable(String key, Object... args) {\n\n        return MutableComponent.pumpkinOf(key);\n\n    }'),
    ('\n    static MutableComponent empty() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.empty:()Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '\n    static MutableComponent empty() {\n\n        return MutableComponent.pumpkinOf("");\n\n    }'),
    ('\n    default String getString() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.getString:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: real where the component can answer, loud where it cannot.\n    default String getString() {\n        if (this instanceof MutableComponent mutable) {\n            return mutable.pumpkinText();\n        }\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.getString:()Ljava/lang/String;");\n    }'),
])

edit("net/minecraft/network/chat/MutableComponent.java", [
    ('public final class MutableComponent implements Component {',
     'public final class MutableComponent implements Component {\n\n    // Pumpkin divergence: the text this component carries. Enough for registration-time\n    // titles and tooltips; styling still throws.\n    private String pumpkinText = "";\n\n    public static MutableComponent pumpkinOf(String text) {\n        MutableComponent component = new MutableComponent();\n        component.pumpkinText = text;\n        return component;\n    }\n\n    public String pumpkinText() {\n        return pumpkinText;\n    }'),
    ('\n    public MutableComponent append(String text) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.append:(Ljava/lang/String;)Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '\n    // Pumpkin divergence: real body.\n\n    public MutableComponent append(String text) {\n\n        pumpkinText = pumpkinText + text;\n\n        return this;\n\n    }'),
])

# ------------------------------------------------- stream codec operations, inert

edit('net/minecraft/network/codec/StreamCodec.java', [
    ('\n    default <O> StreamCodec<B, O> apply(StreamCodec.CodecOperation<B, V, O> operation) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.apply:(Lnet/minecraft/network/codec/StreamCodec$CodecOperation;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    // Pumpkin divergence: vanilla body verbatim -- pure composition, no game state.\n    default <O> StreamCodec<B, O> apply(StreamCodec.CodecOperation<B, V, O> operation) {\n        return operation.apply(this);\n    }'),
])


edit('net/minecraft/network/codec/ByteBufCodecs.java', [
    ('\n    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list() {\n        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.list:()Lnet/minecraft/network/codec/StreamCodec$CodecOperation;");\n    }',
     '    // Pumpkin divergence: real-enough body. The operation composes a list codec Pumpkin\n    // never invokes -- nothing serialises yet -- so composition survives and the first\n    // actual encode/decode throws with the interface\'s name.\n\n    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list() {\n        return original -> dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n    }'),
    ('\n    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list(int maxSize) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.list:(I)Lnet/minecraft/network/codec/StreamCodec$CodecOperation;");\n    }',
     '    // Pumpkin divergence: real-enough body. The operation composes a list codec Pumpkin\n    // never invokes -- nothing serialises yet -- so composition survives and the first\n    // actual encode/decode throws with the interface\'s name.\n\n    static <B extends ByteBuf, V> StreamCodec.CodecOperation<B, V, List<V>> list(int maxSize) {\n        return original -> dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n    }'),
])


# ---------------------------------------------------------- feature flags, empty set
edit("net/minecraft/world/flag/FeatureFlagSet.java", [
    ('    public static FeatureFlagSet of() {\n        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.of:()Lnet/minecraft/world/flag/FeatureFlagSet;");\n    }',
     '    // Pumpkin divergence: real bodies for the empty set. Every other way to build a\n    // FeatureFlagSet still throws, so every reachable instance IS the empty set and the\n    // instance methods below can answer honestly.\n    private static final FeatureFlagSet PUMPKIN_EMPTY = new FeatureFlagSet();\n\n    public static FeatureFlagSet of() {\n        return PUMPKIN_EMPTY;\n    }'),
    ('    public boolean contains(FeatureFlag flag) {\n        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.contains:(Lnet/minecraft/world/flag/FeatureFlag;)Z");\n    }',
     '    public boolean contains(FeatureFlag flag) {\n        return false;\n    }'),
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.isEmpty:()Z");\n    }',
     '    public boolean isEmpty() {\n        return true;\n    }'),
    ('    public boolean equals(Object o) {\n        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.equals:(Ljava/lang/Object;)Z");\n    }',
     '    public boolean equals(Object o) {\n        return o instanceof FeatureFlagSet;\n    }'),
    ('    public int hashCode() {\n        throw Unimplemented.forMember("net/minecraft/world/flag/FeatureFlagSet.hashCode:()I");\n    }',
     '    public int hashCode() {\n        return 0;\n    }'),
])


# --------------------------------------------------- string-representable codec, inert
edit("net/minecraft/util/StringRepresentable.java", [
    ('    static <E extends Enum<E> & StringRepresentable> StringRepresentable.EnumCodec<E> fromEnum(Supplier<E[]> values) {\n        throw Unimplemented.forMember("net/minecraft/util/StringRepresentable.fromEnum:(Ljava/util/function/Supplier;)Lnet/minecraft/util/StringRepresentable$EnumCodec;");\n    }',
     '    // Pumpkin divergence: real-enough body. The codec carries serialisation logic Pumpkin\n    // never invokes -- decode/encode still throw with their own member keys -- so building\n    // one at class-initialisation survives.\n    static <E extends Enum<E> & StringRepresentable> StringRepresentable.EnumCodec<E> fromEnum(Supplier<E[]> values) {\n        return new StringRepresentable.EnumCodec<>();\n    }'),
])


# ----------------------------------------------------------- recipe types, vanilla
edit("net/minecraft/world/item/crafting/RecipeType.java", [
    ('    public static <T extends Recipe<?>> RecipeType<T> simple(final Identifier name) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeType.simple:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/world/item/crafting/RecipeType;");\n    }',
     '    // Pumpkin divergence: vanilla body verbatim -- fully self-contained, just a token\n    // whose toString is its id.\n    public static <T extends Recipe<?>> RecipeType<T> simple(final Identifier name) {\n        return new RecipeType<T>() {\n            @Override\n            public String toString() {\n                return name.toString();\n            }\n        };\n    }'),
])


# ------------------------------------------------ byte buf codecs, registry + map inert
edit("net/minecraft/network/codec/ByteBufCodecs.java", [
    ('    static <B extends ByteBuf, K, V, M extends Map<K, V>> StreamCodec<B, M> map(IntFunction<? extends M> constructor, StreamCodec<? super B, K> keyCodec, StreamCodec<? super B, V> valueCodec, int maxSize) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.map:(Ljava/util/function/IntFunction;Lnet/minecraft/network/codec/StreamCodec;Lnet/minecraft/network/codec/StreamCodec;I)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n    // never invokes -- nothing serialises yet -- so composition survives and the first\n    // actual encode/decode throws with the interface\'s name.\n    static <B extends ByteBuf, K, V, M extends Map<K, V>> StreamCodec<B, M> map(IntFunction<? extends M> constructor, StreamCodec<? super B, K> keyCodec, StreamCodec<? super B, V> valueCodec, int maxSize) {\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n    }'),
    ('    private static <T, R> StreamCodec<RegistryFriendlyByteBuf, R> registry(ResourceKey<? extends Registry<T>> registryKey, Function<Registry<T>, IdMap<R>> mapExtractor) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.registry:(Lnet/minecraft/resources/ResourceKey;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n    // never invokes -- nothing serialises yet -- so composition survives and the first\n    // actual encode/decode throws with the interface\'s name.\n    private static <T, R> StreamCodec<RegistryFriendlyByteBuf, R> registry(ResourceKey<? extends Registry<T>> registryKey, Function<Registry<T>, IdMap<R>> mapExtractor) {\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n    }'),
    ('    static <T> StreamCodec<RegistryFriendlyByteBuf, T> registry(ResourceKey<? extends Registry<T>> registryKey) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/ByteBufCodecs.registry:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    // Pumpkin divergence: real-enough body. A stream codec carries wire logic Pumpkin\n    // never invokes -- nothing serialises yet -- so composition survives and the first\n    // actual encode/decode throws with the interface\'s name.\n    static <T> StreamCodec<RegistryFriendlyByteBuf, T> registry(ResourceKey<? extends Registry<T>> registryKey) {\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n    }'),
])


# ---------------------------------------------------------- registry codec, inert
edit("net/minecraft/core/Registry.java", [
    ('    default Codec<T> byNameCodec() {\n        throw Unimplemented.forMember("net/minecraft/core/Registry.byNameCodec:()Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: real-enough body. The codec carries serialisation logic Pumpkin\n    // never invokes -- encode/decode throw with the codec\'s own key -- so a mod composing\n    // registry-keyed codecs at class-initialisation survives.\n    default Codec<T> byNameCodec() {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/Registry.byNameCodec:()Lcom/mojang/serialization/Codec;");\n    }'),
])


# ---------------------------------------------------------- weighted list codecs, inert
edit("net/minecraft/util/random/WeightedList.java", [
    ('    public static <E> Codec<WeightedList<E>> codec(Codec<E> elementCodec) {\n        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.codec:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: real-enough body -- inert codec; encode/decode throw the key.\n    public static <E> Codec<WeightedList<E>> codec(Codec<E> elementCodec) {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/random/WeightedList.codec:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");\n    }'),
    ('    public static <E> Codec<WeightedList<E>> codec(MapCodec<E> elementCodec) {\n        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.codec:(Lcom/mojang/serialization/MapCodec;)Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: real-enough body -- inert codec; encode/decode throw the key.\n    public static <E> Codec<WeightedList<E>> codec(MapCodec<E> elementCodec) {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/random/WeightedList.codec:(Lcom/mojang/serialization/MapCodec;)Lcom/mojang/serialization/Codec;");\n    }'),
    ('    public static <E, B extends ByteBuf> StreamCodec<B, WeightedList<E>> streamCodec(StreamCodec<B, E> elementCodec) {\n        throw Unimplemented.forMember("net/minecraft/util/random/WeightedList.streamCodec:(Lnet/minecraft/network/codec/StreamCodec;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    // Pumpkin divergence: real-enough body -- inert stream codec, same as ByteBufCodecs.\n    public static <E, B extends ByteBuf> StreamCodec<B, WeightedList<E>> streamCodec(StreamCodec<B, E> elementCodec) {\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");\n    }'),
])


# ------------------------------------------------------------------ items, the sink

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        default int registerBlock(String id, String template, Float destroyTime,\n                Float explosionResistance, boolean requiresTool) {\n            return registerBlock(id, template);\n        }\n    }',
     '        default int registerBlock(String id, String template, Float destroyTime,\n                Float explosionResistance, boolean requiresTool) {\n            return registerBlock(id, template);\n        }\n\n        // Pumpkin divergence: items too. Throwing, not dropping, is the default so a\n        // block-only test sink that unexpectedly receives an item fails saying why;\n        // the production sink in Bootstrap overrides this with the real native.\n        default int registerItem(String id, String template) {\n            throw new IllegalStateException("this sink cannot register items: " + id);\n        }\n    }'),
    ('            if (object instanceof Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else {',
     '            if (object instanceof Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else if (object instanceof net.minecraft.world.item.Item item) {\n                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate());\n            } else {'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            if (value instanceof net.minecraft.world.level.block.Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else {',
     '            if (value instanceof net.minecraft.world.level.block.Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else if (value instanceof net.minecraft.world.item.Item item) {\n                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate());\n            } else {'),
])


edit('net/minecraft/world/item/Item.java', [
    ('    public Item(Item.Properties properties) {\n    }',
     '    public Item(Item.Properties properties) {\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. Pumpkin registers an item by copying an\n    // existing one\'s definition, and "stone" is the deliberate default template -- the\n    // same choice Block\'s registration path makes. It is a stand-in, not a guess at the\n    // mod\'s intent: stack size and components come from stone until item behaviour gets\n    // its own slice.\n    public String pumpkinTemplate() {\n        return "stone";\n    }'),
])


# ---------------------------------------------- items, declared properties + block link

edit('net/minecraft/world/item/Item.java', [
    ('    public Item(Item.Properties properties) {\n    }',
     "    // Pumpkin divergence: kept, not discarded. The registration sink reads the declared\n    // stack size and durability off this on the way to Pumpkin.\n    private Item.Properties pumpkinItemProperties;\n\n    public Item(Item.Properties properties) {\n        this.pumpkinItemProperties = properties;\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. -1 means the mod did not say.\n    public int pumpkinMaxStackSize() {\n        return pumpkinItemProperties == null ? -1 : pumpkinItemProperties.pumpkinMaxStackSize();\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. -1 means the mod did not say.\n    public int pumpkinMaxDamage() {\n        return pumpkinItemProperties == null ? -1 : pumpkinItemProperties.pumpkinMaxDamage();\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. The block this item places, or null for\n    // an ordinary item; BlockItem overrides it. Read by the registration sinks so a block\n    // and its item end up linked in Pumpkin's registry.\n    public String pumpkinPlacedBlockId() {\n        return null;\n    }"),
    ('        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties stacksTo(int max) {\n\n            return this;\n\n        }',
     '        // Pumpkin divergence: real body. Recorded so the registration sink can carry it;\n        // -1 means the mod did not say.\n        private int pumpkinMaxStackSize = -1;\n\n        private int pumpkinMaxDamage = -1;\n\n        int pumpkinMaxStackSize() {\n            return pumpkinMaxStackSize;\n        }\n\n        int pumpkinMaxDamage() {\n            return pumpkinMaxDamage;\n        }\n\n        public Item.Properties stacksTo(int max) {\n            this.pumpkinMaxStackSize = max;\n            return this;\n        }'),
    ('        // Pumpkin divergence: real body. Item metadata Pumpkin does not model yet;\n\n        // accepted and dropped, chain returns `this`.\n\n        public Item.Properties durability(int maxDamage) {\n\n            return this;\n\n        }',
     '        // Pumpkin divergence: real body. Recorded so the registration sink can carry it.\n        public Item.Properties durability(int maxDamage) {\n            this.pumpkinMaxDamage = maxDamage;\n            return this;\n        }'),
])


edit('net/minecraft/world/item/BlockItem.java', [
    ('    public BlockItem(Block block, Item.Properties properties) {\n    }',
     '    // Pumpkin divergence: the block is kept, not discarded. pumpkinPlacedBlockId() below\n    // is how the registration sink learns which block this item places.\n    private Block pumpkinBlock;\n\n    public BlockItem(Block block, Item.Properties properties) {\n        super(properties);\n        this.pumpkinBlock = block;\n    }\n\n    @Override\n    public String pumpkinPlacedBlockId() {\n        return pumpkinBlock == null ? null : pumpkinBlock.pumpkinRegisteredId();\n    }'),
])


edit('net/minecraft/world/level/block/Block.java', [
    ('    public String pumpkinTemplate() {\n        return pumpkinProperties.template();\n    }',
     "    public String pumpkinTemplate() {\n        return pumpkinProperties.template();\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. Set by the registration sinks when this\n    // block registers; read back when its BlockItem registers later, so the two can be\n    // linked. Null until then -- an unregistered block's item places nothing.\n    private String pumpkinRegisteredId;\n\n    public void pumpkinSetRegisteredId(String id) {\n        this.pumpkinRegisteredId = id;\n    }\n\n    public String pumpkinRegisteredId() {\n        return pumpkinRegisteredId;\n    }"),
])


edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        default int registerItem(String id, String template) {\n            throw new IllegalStateException("this sink cannot register items: " + id);\n        }',
     '        default int registerItem(String id, String template) {\n            throw new IllegalStateException("this sink cannot register items: " + id);\n        }\n\n        // Pumpkin divergence: the wide path, mirroring the block one above. stacksTo()\n        // and durability() record onto Item.Properties precisely so these can arrive;\n        // blockId links a BlockItem to the block it places. Default drops them so\n        // single-method test sinks keep working -- the production sink overrides it.\n        default int registerItem(String id, String template, int maxStackSize,\n                int maxDamage, String blockId) {\n            return registerItem(id, template);\n        }'),
    ('            if (object instanceof Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else if (object instanceof net.minecraft.world.item.Item item) {\n                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate());\n            } else {',
     '            if (object instanceof Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                // Recorded on the block so its BlockItem, registering later, can name it.\n                block.pumpkinSetRegisteredId(holder.getId().toString());\n                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else if (object instanceof net.minecraft.world.item.Item item) {\n                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate(),\n                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),\n                        item.pumpkinPlacedBlockId());\n            } else {'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            if (value instanceof net.minecraft.world.level.block.Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else if (value instanceof net.minecraft.world.item.Item item) {\n                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate());\n            } else {',
     '            if (value instanceof net.minecraft.world.level.block.Block block) {\n                net.minecraft.world.level.block.state.BlockBehaviour.Properties props = block.pumpkinProperties();\n                // Recorded on the block so its BlockItem, registering later, can name it.\n                block.pumpkinSetRegisteredId(name.toString());\n                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());\n            } else if (value instanceof net.minecraft.world.item.Item item) {\n                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate(),\n                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),\n                        item.pumpkinPlacedBlockId());\n            } else {'),
])


# --------------------------------------------------------- block entity types, the sink

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        default int registerItem(String id, String template, int maxStackSize,\n                int maxDamage, String blockId) {\n            return registerItem(id, template);\n        }',
     '        default int registerItem(String id, String template, int maxStackSize,\n                int maxDamage, String blockId) {\n            return registerItem(id, template);\n        }\n\n        // Pumpkin divergence: block entity types too. Same contract as registerItem\'s\n        // narrow default: throwing, not dropping, so a sink that cannot take one says so.\n        default int registerBlockEntityType(String id) {\n            throw new IllegalStateException("this sink cannot register block entity types: " + id);\n        }'),
    ('            } else if (object instanceof net.minecraft.world.item.Item item) {\n                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate(),\n                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),\n                        item.pumpkinPlacedBlockId());\n            } else {',
     '            } else if (object instanceof net.minecraft.world.item.Item item) {\n                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate(),\n                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),\n                        item.pumpkinPlacedBlockId());\n            } else if (object instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                pumpkinSink.registerBlockEntityType(holder.getId().toString());\n            } else {'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            } else if (value instanceof net.minecraft.world.item.Item item) {\n                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate(),\n                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),\n                        item.pumpkinPlacedBlockId());\n            } else {',
     '            } else if (value instanceof net.minecraft.world.item.Item item) {\n                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate(),\n                        item.pumpkinMaxStackSize(), item.pumpkinMaxDamage(),\n                        item.pumpkinPlacedBlockId());\n            } else if (value instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                DeferredRegister.pumpkinSink().registerBlockEntityType(name.toString());\n            } else {'),
])


# ------------------------------------------------- creative tabs, validated + reported

edit('net/minecraft/world/item/Item.java', [
    ('    public Item asItem() {\n        throw Unimplemented.forMember("net/minecraft/world/item/Item.asItem:()Lnet/minecraft/world/item/Item;");\n    }',
     '    // Pumpkin divergence: vanilla body verbatim.\n    public Item asItem() {\n        return this;\n    }'),
])


edit('net/minecraft/world/item/CreativeModeTab.java', [
    ('        // Pumpkin divergence: accepted and dropped -- client-side presentation.\n\n        public CreativeModeTab.Builder displayItems(CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {\n\n            return this;\n\n        }',
     '        // Pumpkin divergence: recorded, not dropped. The tab itself is client-side\n        // presentation, but running the generator at registration proves every holder the\n        // mod put in its tab actually resolves -- see pumpkinRunDisplayItems().\n        private CreativeModeTab.DisplayItemsGenerator pumpkinDisplayItemsGenerator;\n\n        public CreativeModeTab.Builder displayItems(CreativeModeTab.DisplayItemsGenerator displayItemsGenerator) {\n            this.pumpkinDisplayItemsGenerator = displayItemsGenerator;\n            return this;\n        }'),
    ('        // Pumpkin divergence: real body -- an inert tab; its own methods still throw.\n        public CreativeModeTab build() {\n            return new CreativeModeTab();\n        }',
     '        // Pumpkin divergence: real body -- an inert tab that keeps its generator; its\n        // vanilla methods still throw.\n        public CreativeModeTab build() {\n            CreativeModeTab tab = new CreativeModeTab();\n            tab.pumpkinDisplayItemsGenerator = pumpkinDisplayItemsGenerator;\n            return tab;\n        }'),
    ('    protected CreativeModeTab(CreativeModeTab.Builder builder) {\n    }',
     '    protected CreativeModeTab(CreativeModeTab.Builder builder) {\n    }\n\n    // Pumpkin divergence: no vanilla counterpart in this form. The generator the builder\n    // recorded, if any.\n    private CreativeModeTab.DisplayItemsGenerator pumpkinDisplayItemsGenerator;\n\n    /**\n     * Runs the tab\'s display-items generator against a counting output and returns how\n     * many entries it produced.\n     *\n     * <p>The tab is client-side presentation the server never renders, so the entries are\n     * not kept. Running the generator is still worth doing: it forces every holder the mod\n     * put in its tab to resolve, which catches a broken registration at load time instead\n     * of never.\n     */\n    public int pumpkinRunDisplayItems() {\n        if (pumpkinDisplayItemsGenerator == null) {\n            return 0;\n        }\n        final int[] count = {0};\n        CreativeModeTab.Output collector = new CreativeModeTab.Output() {\n            @Override\n            public void accept(ItemStack stack, CreativeModeTab.TabVisibility tabVisibility) {\n                count[0]++;\n            }\n\n            @Override\n            public void accept(ItemStack stack) {\n                count[0]++;\n            }\n\n            @Override\n            public void accept(ItemLike item, CreativeModeTab.TabVisibility tabVisibility) {\n                java.util.Objects.requireNonNull(item, "a creative tab accepted a null item");\n                count[0]++;\n            }\n\n            @Override\n            public void accept(ItemLike item) {\n                java.util.Objects.requireNonNull(item, "a creative tab accepted a null item");\n                count[0]++;\n            }\n        };\n        // The parameters carry facts the server does not have: no feature flags beyond\n        // vanilla, no permissions, and a holder provider that throws with a named member\n        // if the generator actually reaches for it.\n        pumpkinDisplayItemsGenerator.accept(new CreativeModeTab.ItemDisplayParameters(\n                FeatureFlagSet.of(), false,\n                dev.pumpkin.shim.Stubs.of(net.minecraft.core.HolderLookup.Provider.class,\n                        "net/minecraft/core/HolderLookup$Provider")),\n                collector);\n        return count[0];\n    }'),
])


edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('            } else if (object instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                pumpkinSink.registerBlockEntityType(holder.getId().toString());\n            } else {',
     '            } else if (object instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                pumpkinSink.registerBlockEntityType(holder.getId().toString());\n            } else if (object instanceof net.minecraft.world.item.CreativeModeTab tab) {\n                pumpkinReportCreativeTab(holder.getId().toString(), tab);\n            } else {'),
])


edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    private static final java.util.Set<String> PUMPKIN_UNSUPPORTED_WARNED =\n            java.util.concurrent.ConcurrentHashMap.newKeySet();',
     '    private static final java.util.Set<String> PUMPKIN_UNSUPPORTED_WARNED =\n            java.util.concurrent.ConcurrentHashMap.newKeySet();\n\n    // Pumpkin divergence: no vanilla counterpart. A creative tab is client-side\n    // presentation -- the protocol never carries it, so there is nothing for a server to\n    // store. Running its generator is still real work: every holder the mod put in the\n    // tab must resolve, which catches a broken registration at load time. Package-private\n    // for the same reason as pumpkinSink().\n    static void pumpkinReportCreativeTab(String id, net.minecraft.world.item.CreativeModeTab tab) {\n        int entries = tab.pumpkinRunDisplayItems();\n        System.out.println("[pumpkin] creative tab " + id + ": " + entries\n                + " entries resolved; tabs are client-side presentation, so the server\'s job ends here.");\n    }'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            } else if (value instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                DeferredRegister.pumpkinSink().registerBlockEntityType(name.toString());\n            } else {',
     '            } else if (value instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                DeferredRegister.pumpkinSink().registerBlockEntityType(name.toString());\n            } else if (value instanceof net.minecraft.world.item.CreativeModeTab tab) {\n                DeferredRegister.pumpkinReportCreativeTab(name.toString(), tab);\n            } else {'),
])


# ------------------------------------------------ creative tab output defaults, vanilla
edit("net/minecraft/world/item/CreativeModeTab.java", [
    ('        default void accept(ItemStack stack) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Output.accept:(Lnet/minecraft/world/item/ItemStack;)V");\n        }',
     '        // Pumpkin divergence: vanilla body verbatim -- pure delegation.\n        default void accept(ItemStack stack) {\n            accept(stack, CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);\n        }'),
    ('        default void accept(ItemLike item, CreativeModeTab.TabVisibility tabVisibility) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Output.accept:(Lnet/minecraft/world/level/ItemLike;Lnet/minecraft/world/item/CreativeModeTab$TabVisibility;)V");\n        }',
     '        // Pumpkin divergence: vanilla body verbatim -- pure delegation.\n        default void accept(ItemLike item, CreativeModeTab.TabVisibility tabVisibility) {\n            accept(new ItemStack(item), tabVisibility);\n        }'),
    ('        default void accept(ItemLike item) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Output.accept:(Lnet/minecraft/world/level/ItemLike;)V");\n        }',
     '        // Pumpkin divergence: vanilla body verbatim -- pure delegation.\n        default void accept(ItemLike item) {\n            accept(new ItemStack(item), CreativeModeTab.TabVisibility.PARENT_AND_SEARCH_TABS);\n        }'),
])


# ----------------------------------------------------- item stack components, real map

edit('net/neoforged/neoforge/common/MutableDataComponentHolder.java', [
    ('    default <T> T set(Supplier<? extends DataComponentType<T>> componentType, T value) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/MutableDataComponentHolder.set:(Ljava/util/function/Supplier;Ljava/lang/Object;)Ljava/lang/Object;");\n    }',
     '    // Pumpkin divergence: NeoForge body verbatim -- pure delegation.\n    default <T> T set(Supplier<? extends DataComponentType<T>> componentType, T value) {\n        return set(componentType.get(), value);\n    }'),
])


edit('net/minecraft/world/item/ItemStack.java', [
    ('    public <T> T set(DataComponentType<T> type, T value) {\n        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Ljava/lang/Object;");\n    }',
     '    // Pumpkin divergence: real bodies. A stack\'s own components genuinely live on the\n    // stack, so a mod that sets one and reads it back gets its value. The item\'s base\n    // components are not consulted here -- get() answers only what was set on this stack,\n    // and pumpkinComponents() hands the map to whoever needs the rest of the merge later.\n    private final java.util.Map<DataComponentType<?>, Object> pumpkinComponents =\n            new java.util.HashMap<>();\n\n    @SuppressWarnings("unchecked")\n    public <T> T set(DataComponentType<T> type, T value) {\n        return (T) pumpkinComponents.put(type, value);\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. The components set on this stack.\n    public java.util.Map<DataComponentType<?>, Object> pumpkinComponents() {\n        return pumpkinComponents;\n    }\n\n    @SuppressWarnings("unchecked")\n    @Override\n    public <T> T get(DataComponentType<? extends T> type) {\n        return (T) pumpkinComponents.get(type);\n    }'),
])


# ------------------------------------------------- menus and sound events, the sink

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        default int registerBlockEntityType(String id) {\n            throw new IllegalStateException("this sink cannot register block entity types: " + id);\n        }',
     '        default int registerBlockEntityType(String id) {\n            throw new IllegalStateException("this sink cannot register block entity types: " + id);\n        }\n\n        // Pumpkin divergence: same contract as the two above.\n        default int registerMenuType(String id) {\n            throw new IllegalStateException("this sink cannot register menu types: " + id);\n        }\n\n        default int registerSoundEvent(String id) {\n            throw new IllegalStateException("this sink cannot register sound events: " + id);\n        }'),
    ('            } else if (object instanceof net.minecraft.world.item.CreativeModeTab tab) {\n                pumpkinReportCreativeTab(holder.getId().toString(), tab);\n            } else {',
     '            } else if (object instanceof net.minecraft.world.item.CreativeModeTab tab) {\n                pumpkinReportCreativeTab(holder.getId().toString(), tab);\n            } else if (object instanceof net.minecraft.world.inventory.MenuType) {\n                pumpkinSink.registerMenuType(holder.getId().toString());\n            } else if (object instanceof net.minecraft.sounds.SoundEvent) {\n                pumpkinSink.registerSoundEvent(holder.getId().toString());\n            } else {'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            } else if (value instanceof net.minecraft.world.item.CreativeModeTab tab) {\n                DeferredRegister.pumpkinReportCreativeTab(name.toString(), tab);\n            } else {',
     '            } else if (value instanceof net.minecraft.world.item.CreativeModeTab tab) {\n                DeferredRegister.pumpkinReportCreativeTab(name.toString(), tab);\n            } else if (value instanceof net.minecraft.world.inventory.MenuType) {\n                DeferredRegister.pumpkinSink().registerMenuType(name.toString());\n            } else if (value instanceof net.minecraft.sounds.SoundEvent) {\n                DeferredRegister.pumpkinSink().registerSoundEvent(name.toString());\n            } else {'),
])


# ---------------------------------------------------- data component types, the sink

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        default int registerSoundEvent(String id) {\n            throw new IllegalStateException("this sink cannot register sound events: " + id);\n        }',
     '        default int registerSoundEvent(String id) {\n            throw new IllegalStateException("this sink cannot register sound events: " + id);\n        }\n\n        default int registerDataComponentType(String id) {\n            throw new IllegalStateException("this sink cannot register data component types: " + id);\n        }'),
    ('            } else if (object instanceof net.minecraft.sounds.SoundEvent) {\n                pumpkinSink.registerSoundEvent(holder.getId().toString());\n            } else {',
     '            } else if (object instanceof net.minecraft.sounds.SoundEvent) {\n                pumpkinSink.registerSoundEvent(holder.getId().toString());\n            } else if (object instanceof net.minecraft.core.component.DataComponentType) {\n                pumpkinSink.registerDataComponentType(holder.getId().toString());\n            } else {'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            } else if (value instanceof net.minecraft.sounds.SoundEvent) {\n                DeferredRegister.pumpkinSink().registerSoundEvent(name.toString());\n            } else {',
     '            } else if (value instanceof net.minecraft.sounds.SoundEvent) {\n                DeferredRegister.pumpkinSink().registerSoundEvent(name.toString());\n            } else if (value instanceof net.minecraft.core.component.DataComponentType) {\n                DeferredRegister.pumpkinSink().registerDataComponentType(name.toString());\n            } else {'),
])


# ------------------------------------------- remaining registries, named acknowledgments
edit("net/neoforged/neoforge/registries/DeferredRegister.java", [
    ('    static void pumpkinWarnUnsupported(String registry, String entry) {\n        if (PUMPKIN_UNSUPPORTED_WARNED.add(registry)) {\n            System.err.println("[pumpkin] " + registry + " is not modelled yet; entries like "\n                    + entry + " are accepted so their mod can load, but nothing reads them.");\n        }\n    }',
     '    static void pumpkinWarnUnsupported(String registry, String entry) {\n        if (PUMPKIN_UNSUPPORTED_WARNED.add(registry)) {\n            String explanation = PUMPKIN_ACKNOWLEDGED.get(registry);\n            if (explanation != null) {\n                System.err.println("[pumpkin] " + registry + " (e.g. " + entry + "): " + explanation);\n            } else {\n                System.err.println("[pumpkin] " + registry + " is not modelled yet; entries like "\n                        + entry + " are accepted so their mod can load, but nothing reads them.");\n            }\n        }\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. Registries whose entries the server\n    // understands well enough to say exactly why its job ends at accepting them. Each\n    // message names the missing subsystem, so the generic "not modelled yet" line is\n    // reserved for registries nothing has looked at.\n    private static final java.util.Map<String, String> PUMPKIN_ACKNOWLEDGED = java.util.Map.of(\n            "minecraft:recipe_type",\n            "vanilla-typed recipes from the mod\'s datapack already load and craft; a custom"\n                    + " recipe type is its machine\'s input, and mod machines do not run"\n                    + " server-side yet.",\n            "minecraft:recipe_serializer",\n            "Pumpkin parses vanilla recipe formats itself; a custom serializer\'s format is"\n                    + " skipped and counted when the mod\'s datapack loads.",\n            "minecraft:worldgen/feature",\n            "world generation does not take mod features yet; ores and plants from mods"\n                    + " will not spawn until generation opens up to them.",\n            "neoforge:ingredient_serializer",\n            "reads custom ingredient JSON, which only appears in recipe formats Pumpkin"\n                    + " already skips and counts.",\n            "neoforge:condition_codecs",\n            "datapack load conditions; Pumpkin loads a mod\'s datapack unconditionally, so"\n                    + " a condition meant to disable content is not evaluated.",\n            "neoforge:biome_modifier_serializers",\n            "biome modifiers steer world generation, which does not take mod input yet.");'),
])


# ------------------------------------------- block entity types, valid-block links

edit('net/minecraft/world/level/block/entity/BlockEntityType.java', [
    ('    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks) {\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks, boolean onlyOpCanSetNbt) {\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Block... validBlocks) {\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, boolean onlyOpCanSetNbt, Block... validBlocks) {\n    }',
     '    // Pumpkin divergence: the valid blocks are kept, not discarded. They are how the\n    // registration sink learns which placed block should get this entity -- the type\n    // registers after its blocks, so the link has to travel this way.\n    private final java.util.List<Block> pumpkinValidBlocks = new java.util.ArrayList<>();\n\n    public java.util.List<Block> pumpkinValidBlocks() {\n        return pumpkinValidBlocks;\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks) {\n        pumpkinValidBlocks.addAll(validBlocks);\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks, boolean onlyOpCanSetNbt) {\n        pumpkinValidBlocks.addAll(validBlocks);\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Block... validBlocks) {\n        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, boolean onlyOpCanSetNbt, Block... validBlocks) {\n        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);\n    }'),
])


edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        default int registerDataComponentType(String id) {\n            throw new IllegalStateException("this sink cannot register data component types: " + id);\n        }',
     '        default int registerDataComponentType(String id) {\n            throw new IllegalStateException("this sink cannot register data component types: " + id);\n        }\n\n        // Pumpkin divergence: the wide path for block entity types. The comma-joined\n        // block list is which placed blocks get this entity; namespaced ids cannot\n        // contain commas, so the join is unambiguous. Default drops the list so narrow\n        // test sinks keep working -- the production sink overrides it.\n        default int registerBlockEntityType(String id, String validBlockIds) {\n            return registerBlockEntityType(id);\n        }'),
    ('            } else if (object instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                pumpkinSink.registerBlockEntityType(holder.getId().toString());',
     '            } else if (object instanceof net.minecraft.world.level.block.entity.BlockEntityType<?> type) {\n                pumpkinSink.registerBlockEntityType(holder.getId().toString(),\n                        pumpkinJoinRegisteredBlockIds(type));'),
    ('    private static final java.util.Set<String> PUMPKIN_UNSUPPORTED_WARNED =\n            java.util.concurrent.ConcurrentHashMap.newKeySet();',
     '    private static final java.util.Set<String> PUMPKIN_UNSUPPORTED_WARNED =\n            java.util.concurrent.ConcurrentHashMap.newKeySet();\n\n    // Pumpkin divergence: no vanilla counterpart. The comma-joined registered ids of a\n    // block entity type\'s valid blocks; blocks that never registered are silently\n    // absent, because a link to nothing is not a link. Package-private for\n    // RegisterEvent\'s twin path.\n    static String pumpkinJoinRegisteredBlockIds(\n            net.minecraft.world.level.block.entity.BlockEntityType<?> type) {\n        java.util.List<String> ids = new java.util.ArrayList<>();\n        for (net.minecraft.world.level.block.Block block : type.pumpkinValidBlocks()) {\n            String id = block.pumpkinRegisteredId();\n            if (id != null) {\n                ids.add(id);\n            }\n        }\n        return String.join(",", ids);\n    }'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            } else if (value instanceof net.minecraft.world.level.block.entity.BlockEntityType) {\n                DeferredRegister.pumpkinSink().registerBlockEntityType(name.toString());',
     '            } else if (value instanceof net.minecraft.world.level.block.entity.BlockEntityType<?> type) {\n                DeferredRegister.pumpkinSink().registerBlockEntityType(name.toString(),\n                        DeferredRegister.pumpkinJoinRegisteredBlockIds(type));'),
])


# ------------------------------------------------- interaction bridge, shim surface

edit('net/minecraft/world/item/ItemStack.java', [
    ('    public ItemStack(ItemLike item, int count) {\n    }\n\n    public ItemStack(ItemLike item) {\n    }',
     '    // Pumpkin divergence: a stack really carries its item and count. The interaction\n    // bridge builds these and mods read them back; without real fields every isEmpty()\n    // is a guess.\n    private ItemLike pumpkinItem;\n\n    private int pumpkinCount = 1;\n\n    public ItemLike pumpkinItemLike() {\n        return pumpkinItem;\n    }\n\n    public ItemStack(ItemLike item, int count) {\n        this.pumpkinItem = item;\n        this.pumpkinCount = count;\n    }\n\n    public ItemStack(ItemLike item) {\n        this(item, 1);\n    }'),
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.isEmpty:()Z");\n    }',
     '    // Pumpkin divergence: real body.\n    public boolean isEmpty() {\n        return pumpkinItem == null || pumpkinCount <= 0;\n    }'),
    ('    public Item getItem() {\n        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.getItem:()Lnet/minecraft/world/item/Item;");\n    }',
     '    // Pumpkin divergence: real body.\n    public Item getItem() {\n        return pumpkinItem == null ? null : pumpkinItem.asItem();\n    }'),
    ('    public ItemStack copyWithCount(int count) {\n        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.copyWithCount:(I)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    // Pumpkin divergence: real body.\n    public ItemStack copyWithCount(int count) {\n        return new ItemStack(pumpkinItem, count);\n    }'),
    ('    public int count() {\n        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.count:()I");\n    }',
     '    // Pumpkin divergence: real body.\n    public int count() {\n        return pumpkinCount;\n    }'),
])


edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public static ItemResource of(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    // Pumpkin divergence: real bodies for the stack-shaped subset the interaction path\n    // uses. A resource is an item reference without a count; EMPTY-ness follows the item.\n    private ItemLike pumpkinItem;\n\n    public static ItemResource of(ItemStack stack) {\n        ItemResource resource = new ItemResource();\n        resource.pumpkinItem = stack.isEmpty() ? null : stack.getItem();\n        return resource;\n    }'),
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.isEmpty:()Z");\n    }',
     '    public boolean isEmpty() {\n        return pumpkinItem == null;\n    }'),
    ('    public ItemStack toStack(int count) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.toStack:(I)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    public ItemStack toStack(int count) {\n        return pumpkinItem == null ? new ItemStack((ItemLike) null, 0)\n                : new ItemStack(pumpkinItem, count);\n    }'),
])


edit('net/minecraft/world/entity/item/ItemEntity.java', [
    ('    public ItemEntity(Level level, double x, double y, double z, ItemStack itemStack) {\n    }',
     '    // Pumpkin divergence: the stack is kept so Level.addFreshEntity can hand the drop\n    // back to the server instead of losing it.\n    private ItemStack pumpkinStack;\n\n    public ItemStack pumpkinStack() {\n        return pumpkinStack;\n    }\n\n    public ItemEntity(Level level, double x, double y, double z, ItemStack itemStack) {\n        this.pumpkinStack = itemStack;\n    }'),
])


edit('net/minecraft/world/level/block/entity/BlockEntityType.java', [
    ('    private final java.util.List<Block> pumpkinValidBlocks = new java.util.ArrayList<>();',
     "    private final java.util.List<Block> pumpkinValidBlocks = new java.util.ArrayList<>();\n\n    // Pumpkin divergence: the factory too -- it is how the interaction bridge builds the\n    // mod's own tile entity when its block is used.\n    private BlockEntityType.BlockEntitySupplier<? extends T> pumpkinFactory;\n\n    public T pumpkinCreate(BlockPos worldPosition, BlockState blockState) {\n        return pumpkinFactory.create(worldPosition, blockState);\n    }"),
    ('        pumpkinValidBlocks.addAll(validBlocks);\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks, boolean onlyOpCanSetNbt) {\n        pumpkinValidBlocks.addAll(validBlocks);\n    }',
     '        pumpkinValidBlocks.addAll(validBlocks);\n        this.pumpkinFactory = factory;\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, Set<Block> validBlocks, boolean onlyOpCanSetNbt) {\n        pumpkinValidBlocks.addAll(validBlocks);\n        this.pumpkinFactory = factory;\n    }'),
    ('        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, boolean onlyOpCanSetNbt, Block... validBlocks) {\n        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);\n    }',
     '        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);\n        this.pumpkinFactory = factory;\n    }\n\n    public BlockEntityType(BlockEntityType.BlockEntitySupplier<? extends T> factory, boolean onlyOpCanSetNbt, Block... validBlocks) {\n        java.util.Collections.addAll(pumpkinValidBlocks, validBlocks);\n        this.pumpkinFactory = factory;\n    }'),
])


edit('net/minecraft/world/item/Item.java', [
    ('    // Pumpkin divergence: no vanilla counterpart. -1 means the mod did not say.\n    public int pumpkinMaxStackSize() {',
     '    // Pumpkin divergence: no vanilla counterpart. Set by the registration sinks; the\n    // interaction bridge reads it back to name this item across the JNI boundary.\n    private String pumpkinRegisteredId;\n\n    public void pumpkinSetRegisteredId(String id) {\n        this.pumpkinRegisteredId = id;\n    }\n\n    public String pumpkinRegisteredId() {\n        return pumpkinRegisteredId;\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. -1 means the mod did not say.\n    public int pumpkinMaxStackSize() {'),
])


edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('            } else if (object instanceof net.minecraft.world.item.Item item) {\n                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate(),',
     '            } else if (object instanceof net.minecraft.world.item.Item item) {\n                item.pumpkinSetRegisteredId(holder.getId().toString());\n                pumpkinSink.registerItem(holder.getId().toString(), item.pumpkinTemplate(),'),
])


edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('            } else if (value instanceof net.minecraft.world.item.Item item) {\n                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate(),',
     '            } else if (value instanceof net.minecraft.world.item.Item item) {\n                item.pumpkinSetRegisteredId(name.toString());\n                DeferredRegister.pumpkinSink().registerItem(name.toString(), item.pumpkinTemplate(),'),
])


edit('net/neoforged/neoforge/registries/DeferredHolder.java', [
    ('" + holder.getId(), holder);\n    }',
     '" + holder.getId(), holder);\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. The interaction bridge resolves a\n    // registered value by registry and id -- the same key the record methods write.\n    public static Object pumpkinResolve(String registry, String id) {\n        DeferredHolder<?, ?> holder = PUMPKIN_BY_ID.get(registry + "|" + id);\n        return holder == null ? null : holder.get();\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. The reverse: which id a value was\n    // registered under. A linear scan, used only on cold paths (a recipe type\'s first\n    // lookup) and correct because registration resolved every holder it recorded.\n    public static String pumpkinResolveName(String registry, Object value) {\n        String prefix = registry + "|";\n        for (java.util.Map.Entry<String, DeferredHolder<?, ?>> entry : PUMPKIN_BY_ID.entrySet()) {\n            if (entry.getKey().startsWith(prefix) && entry.getValue().get() == value) {\n                return entry.getKey().substring(prefix.length());\n            }\n        }\n        return null;\n    }'),
])


# ------------------------------------------------------- entity position, bridge-set
edit("net/minecraft/world/entity/Entity.java", [
    ('    public final double getX() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getX:()D");\n    }',
     '    // Pumpkin divergence: real body over the position field the bridge sets.\n    public final double getX() {\n        return position == null ? 0.0 : position.x;\n    }'),
    ('    public final double getY() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getY:()D");\n    }',
     '    // Pumpkin divergence: real body over the position field the bridge sets.\n    public final double getY() {\n        return position == null ? 0.0 : position.y;\n    }'),
    ('    public final double getZ() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.getZ:()D");\n    }',
     '    // Pumpkin divergence: real body over the position field the bridge sets.\n    public final double getZ() {\n        return position == null ? 0.0 : position.z;\n    }'),
    ('    private Vec3 position;',
     '    private Vec3 position;\n\n    // Pumpkin divergence: no vanilla counterpart. The interaction bridge places the\n    // stand-in player without running vanilla movement code.\n    public void pumpkinSetPosition(Vec3 position) {\n        this.position = position;\n    }'),
])


# ----------------------------------------------------------------- vec3, real fields
edit("net/minecraft/world/phys/Vec3.java", [
    ('    public final double x = 0.0;\n\n    public final double y = 0.0;\n\n    public final double z = 0.0;',
     '    // Pumpkin divergence: a vector really carries its coordinates; the pruner had\n    // zeroed them because the originals were assigned in a stripped constructor.\n    public final double x;\n\n    public final double y;\n\n    public final double z;'),
    ('    public Vec3(double x, double y, double z) {\n    }',
     '    public Vec3(double x, double y, double z) {\n        this.x = x;\n        this.y = y;\n        this.z = z;\n    }'),
    ('    public Vec3(Vector3fc vec) {\n    }',
     '    public Vec3(Vector3fc vec) {\n        this.x = 0.0;\n        this.y = 0.0;\n        this.z = 0.0;\n    }'),
    ('    public Vec3(Vec3i vec) {\n    }',
     '    public Vec3(Vec3i vec) {\n        this.x = 0.0;\n        this.y = 0.0;\n        this.z = 0.0;\n    }'),
    ('    public Vec3() {\n    }',
     '    public Vec3() {\n        this.x = 0.0;\n        this.y = 0.0;\n        this.z = 0.0;\n    }'),
])


# ------------------------------------------------------ interaction results, real
edit("net/minecraft/world/InteractionResult.java", [
    ('    InteractionResult.Success SUCCESS = null;',
     "    // Pumpkin divergence: real instances -- vanilla's own values. A null here made every\n    // handler's return indistinguishable from every other.\n    InteractionResult.Success SUCCESS = new Success(SwingSource.CLIENT, new ItemContext(true, null));"),
    ('    InteractionResult.Success SUCCESS_SERVER = null;',
     '    // Pumpkin divergence: real instance, per vanilla -- swing decided server-side.\n    InteractionResult.Success SUCCESS_SERVER = new Success(SwingSource.SERVER, new ItemContext(true, null));'),
    ('    InteractionResult.Success CONSUME = null;',
     '    // Pumpkin divergence: real instance, per vanilla -- success with no swing.\n    InteractionResult.Success CONSUME = new Success(SwingSource.NONE, new ItemContext(true, null));'),
    ('    InteractionResult.Fail FAIL = null;',
     '    InteractionResult.Fail FAIL = new Fail();'),
    ('    InteractionResult.Pass PASS = null;',
     '    InteractionResult.Pass PASS = new Pass();'),
])


# ---------------------------------------------------------------- vec3i, real fields
edit("net/minecraft/core/Vec3i.java", [
    ('    public Vec3i(int x, int y, int z) {\n    }',
     '    // Pumpkin divergence: a vector really carries its coordinates, like Vec3.\n    private int pumpkinX;\n\n    private int pumpkinY;\n\n    private int pumpkinZ;\n\n    public Vec3i(int x, int y, int z) {\n        this.pumpkinX = x;\n        this.pumpkinY = y;\n        this.pumpkinZ = z;\n    }'),
    ('    public int getX() {\n        throw Unimplemented.forMember("net/minecraft/core/Vec3i.getX:()I");\n    }',
     '    public int getX() {\n        return pumpkinX;\n    }'),
    ('    public int getY() {\n        throw Unimplemented.forMember("net/minecraft/core/Vec3i.getY:()I");\n    }',
     '    public int getY() {\n        return pumpkinY;\n    }'),
    ('    public int getZ() {\n        throw Unimplemented.forMember("net/minecraft/core/Vec3i.getZ:()I");\n    }',
     '    public int getZ() {\n        return pumpkinZ;\n    }'),
])


# ------------------------------------------------------------ block pos, real coords
edit("net/minecraft/core/BlockPos.java", [
    ('    public BlockPos(int x, int y, int z) {\n    }',
     "    // Pumpkin divergence: coordinates flow into Vec3i's real fields.\n    public BlockPos(int x, int y, int z) {\n        super(x, y, z);\n    }"),
    ('    public BlockPos(Vec3i vec3i) {\n    }',
     '    public BlockPos(Vec3i vec3i) {\n        super(vec3i.getX(), vec3i.getY(), vec3i.getZ());\n    }'),
])


# ----------------------------------------------------- extra codecs, inert
edit("net/minecraft/util/ExtraCodecs.java", [
    ('    public static <A> Codec<Optional<A>> optionalEmptyMap(Codec<A> codec) {\n        throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs.optionalEmptyMap:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: real-enough body -- inert codec; encode/decode throw the key.\n    public static <A> Codec<Optional<A>> optionalEmptyMap(Codec<A> codec) {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.optionalEmptyMap:(Lcom/mojang/serialization/Codec;)Lcom/mojang/serialization/Codec;");\n    }'),
])


# --------------------------------------------- transfer handlers, real item storage

edit('net/minecraft/world/item/ItemStack.java', [
    ('    public static final ItemStack EMPTY = null;',
     '    // Pumpkin divergence: a real empty stack, because everything compares against it.\n    public static final ItemStack EMPTY = new ItemStack((ItemLike) null, 0);'),
])


edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public static final ItemResource EMPTY = null;',
     '    // Pumpkin divergence: a real empty resource; a null here NPEs every isEmpty check.\n    public static final ItemResource EMPTY = new ItemResource();'),
])


edit('net/minecraft/core/NonNullList.java', [
    ('    protected NonNullList(List<E> list, E defaultValue) {\n    }',
     '    // Pumpkin divergence: really backed by the list it wraps; vanilla is the same thin\n    // wrapper, minus the null checks nothing here needs yet.\n    private List<E> pumpkinBacking;\n\n    protected NonNullList(List<E> list, E defaultValue) {\n        this.pumpkinBacking = list;\n    }'),
    ('    public static <E> NonNullList<E> withSize(int size, E defaultValue) {\n        throw Unimplemented.forMember("net/minecraft/core/NonNullList.withSize:(ILjava/lang/Object;)Lnet/minecraft/core/NonNullList;");\n    }',
     '    public static <E> NonNullList<E> withSize(int size, E defaultValue) {\n        List<E> backing = new java.util.ArrayList<>(size);\n        for (int i = 0; i < size; i++) {\n            backing.add(defaultValue);\n        }\n        return new NonNullList<>(backing, defaultValue);\n    }'),
    ('    public E get(int index) {\n        throw Unimplemented.forMember("net/minecraft/core/NonNullList.get:(I)Ljava/lang/Object;");\n    }',
     '    public E get(int index) {\n        return pumpkinBacking.get(index);\n    }'),
    ('    public E set(int index, E element) {\n        throw Unimplemented.forMember("net/minecraft/core/NonNullList.set:(ILjava/lang/Object;)Ljava/lang/Object;");\n    }',
     '    public E set(int index, E element) {\n        return pumpkinBacking.set(index, element);\n    }'),
    ('    public int size() {\n        throw Unimplemented.forMember("net/minecraft/core/NonNullList.size:()I");\n    }',
     '    public int size() {\n        return pumpkinBacking.size();\n    }'),
])


edit('net/neoforged/neoforge/transfer/StacksResourceHandler.java', [
    ('    protected final S emptyStack = null;\n\n    protected NonNullList<S> stacks;\n\n    protected final Codec<NonNullList<S>> codec = null;\n\n    protected StacksResourceHandler(int size, S emptyStack, Codec<S> stackCodec) {\n    }',
     "    // Pumpkin divergence: real storage. The handler is where a machine's slots live, and\n    // every accessor below reads and writes this list, the same shape vanilla keeps.\n    protected final S emptyStack;\n\n    protected NonNullList<S> stacks;\n\n    protected final Codec<NonNullList<S>> codec = null;\n\n    protected StacksResourceHandler(int size, S emptyStack, Codec<S> stackCodec) {\n        this.emptyStack = emptyStack;\n        this.stacks = NonNullList.withSize(size, emptyStack);\n    }"),
    ('    protected StacksResourceHandler(NonNullList<S> stacks, S emptyStack, Codec<S> stackCodec) {\n    }',
     '    protected StacksResourceHandler(NonNullList<S> stacks, S emptyStack, Codec<S> stackCodec) {\n        this.emptyStack = emptyStack;\n        this.stacks = stacks;\n    }'),
    ('    public void set(int index, T resource, int amount) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.set:(ILnet/neoforged/neoforge/transfer/resource/Resource;I)V");\n    }',
     '    // Pumpkin divergence: vanilla shape -- store the stack, tell the subclass.\n    public void set(int index, T resource, int amount) {\n        S previous = stacks.get(index);\n        stacks.set(index, resource.isEmpty() ? emptyStack : getStackFrom(resource, amount));\n        onContentsChanged(index, previous);\n    }'),
    ('    public int size() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.size:()I");\n    }',
     '    public int size() {\n        return stacks.size();\n    }'),
    ('    public T getResource(int index) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.getResource:(I)Lnet/neoforged/neoforge/transfer/resource/Resource;");\n    }',
     '    public T getResource(int index) {\n        return getResourceFrom(stacks.get(index));\n    }'),
    ('    public long getAmountAsLong(int index) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.getAmountAsLong:(I)J");\n    }',
     '    public long getAmountAsLong(int index) {\n        return getAmountFrom(stacks.get(index));\n    }'),
    ('    protected void onContentsChanged(int index, S previousContents) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.onContentsChanged:(ILjava/lang/Object;)V");\n    }',
     '    // Pumpkin divergence: a notification hook; the base has nothing to notify.\n    protected void onContentsChanged(int index, S previousContents) {\n    }'),
    ('    public StacksResourceHandler() {\n    }',
     '    public StacksResourceHandler() {\n        this.emptyStack = null;\n    }'),
])


edit('net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.java', [
    ('    public ItemStacksResourceHandler(int size) {\n    }',
     '    // Pumpkin divergence: real bodies -- the item flavour of the storage above.\n    public ItemStacksResourceHandler(int size) {\n        super(size, ItemStack.EMPTY, null);\n    }'),
    ('    public ItemResource getResourceFrom(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getResourceFrom:(Lnet/minecraft/world/item/ItemStack;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public ItemResource getResourceFrom(ItemStack stack) {\n        return stack == null || stack.isEmpty() ? ItemResource.EMPTY : ItemResource.of(stack);\n    }'),
    ('    public int getAmountFrom(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getAmountFrom:(Lnet/minecraft/world/item/ItemStack;)I");\n    }',
     '    public int getAmountFrom(ItemStack stack) {\n        return stack == null ? 0 : stack.count();\n    }'),
    ('    protected ItemStack getStackFrom(ItemResource resource, int amount) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.getStackFrom:(Lnet/neoforged/neoforge/transfer/item/ItemResource;I)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    protected ItemStack getStackFrom(ItemResource resource, int amount) {\n        return resource.toStack(amount);\n    }'),
    ('    protected ItemStack copyOf(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemStacksResourceHandler.copyOf:(Lnet/minecraft/world/item/ItemStack;)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    protected ItemStack copyOf(ItemStack stack) {\n        return stack.copyWithCount(stack.count());\n    }'),
])


# ------------------------------------------------ interaction burndown, small keys

edit('net/minecraft/world/level/block/entity/BlockEntity.java', [
    ('    public void setChanged() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.setChanged:()V");\n    }',
     '    // Pumpkin divergence: a real dirty flag. The tick bridge reads and clears it to\n    // decide whether an entity is worth re-serialising -- ticking machines call this\n    // twenty times a second, and serialising unchanged ones would be pure waste.\n    private boolean pumpkinChanged;\n\n    public void setChanged() {\n        pumpkinChanged = true;\n    }\n\n    public boolean pumpkinTakeChanged() {\n        boolean changed = pumpkinChanged;\n        pumpkinChanged = false;\n        return changed;\n    }'),
])


edit('net/neoforged/neoforge/transfer/ResourceHandler.java', [
    ('    default int getAmountAsInt(int index) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandler.getAmountAsInt:(I)I");\n    }',
     '    // Pumpkin divergence: vanilla body verbatim -- pure delegation.\n    default int getAmountAsInt(int index) {\n        return (int) getAmountAsLong(index);\n    }'),
])


# ------------------------------------------------------- block entity level, real
edit('net/minecraft/world/level/block/entity/BlockEntity.java', [
    ('    public Level getLevel() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getLevel:()Lnet/minecraft/world/level/Level;");\n    }',
     "    // Pumpkin divergence: real body over the protected field the bridge sets.\n    public Level getLevel() {\n        return level;\n    }\n\n    // Pumpkin divergence: no vanilla counterpart in this form. The bridge attaches the\n    // level when it creates the mod's entity.\n    public void pumpkinSetLevel(Level level) {\n        this.level = level;\n    }"),
])


# ------------------------------------------- block entity data packet, inert
edit('net/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket.java', [
    ('    public static ClientboundBlockEntityDataPacket create(BlockEntity blockEntity, BiFunction<BlockEntity, RegistryAccess, CompoundTag> updateTagSaver) {\n        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket.create:(Lnet/minecraft/world/level/block/entity/BlockEntity;Ljava/util/function/BiFunction;)Lnet/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket;");\n    }',
     "    // Pumpkin divergence: an inert packet. Syncing a mod entity's data to clients is the\n    // sync slice; the packet is built and dropped so the mark-dirty path survives.\n    public static ClientboundBlockEntityDataPacket create(BlockEntity blockEntity, BiFunction<BlockEntity, RegistryAccess, CompoundTag> updateTagSaver) {\n        return new ClientboundBlockEntityDataPacket();\n    }"),
    ('    public static ClientboundBlockEntityDataPacket create(BlockEntity blockEntity) {\n        throw Unimplemented.forMember("net/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket.create:(Lnet/minecraft/world/level/block/entity/BlockEntity;)Lnet/minecraft/network/protocol/game/ClientboundBlockEntityDataPacket;");\n    }',
     "    // Pumpkin divergence: an inert packet. Syncing a mod entity's data to clients is the\n    // sync slice; the packet is built and dropped so the mark-dirty path survives.\n    public static ClientboundBlockEntityDataPacket create(BlockEntity blockEntity) {\n        return new ClientboundBlockEntityDataPacket();\n    }"),
])


# ---------------------------------------------------- block entity position, real
edit('net/minecraft/world/level/block/entity/BlockEntity.java', [
    ('    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {\n    }',
     '    // Pumpkin divergence: the position is kept; getBlockPos answers with it.\n    private BlockPos pumpkinPosition;\n\n    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {\n        this.pumpkinPosition = worldPosition;\n    }'),
    ('    public BlockPos getBlockPos() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getBlockPos:()Lnet/minecraft/core/BlockPos;");\n    }',
     '    public BlockPos getBlockPos() {\n        return pumpkinPosition;\n    }'),
])


# ---------------------------------------------------- item entity pickup delay, dropped
edit('net/minecraft/world/entity/item/ItemEntity.java', [
    ('    public void setNoPickUpDelay() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/item/ItemEntity.setNoPickUpDelay:()V");\n    }',
     "    // Pumpkin divergence: accepted and dropped. The bridge captures the entity's stack\n    // and hands it to the real world, which applies its own pickup rules; this entity is\n    // never spawned, so it has no delay to clear.\n    public void setNoPickUpDelay() {\n    }"),
])


# ------------------------------------------------- persistence, base save hooks

edit('net/neoforged/neoforge/common/extensions/ValueOutputExtension.java', [
    ('    default void putChild(String key, ValueIOSerializable child) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ValueOutputExtension.putChild:(Ljava/lang/String;Lnet/neoforged/neoforge/common/util/ValueIOSerializable;)V");\n    }',
     '    // Pumpkin divergence: NeoForge body verbatim -- pure delegation. The extension is\n    // mixed into ValueOutput, which owns child().\n    default void putChild(String key, ValueIOSerializable child) {\n        child.serialize(((net.minecraft.world.level.storage.ValueOutput) this).child(key));\n    }'),
])


edit('net/minecraft/world/level/block/entity/BlockEntity.java', [
    ('    protected void loadAdditional(ValueInput input) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.loadAdditional:(Lnet/minecraft/world/level/storage/ValueInput;)V");\n    }',
     "    // Pumpkin divergence: the base writes vanilla bookkeeping (components) the shim does\n    // not model; a subclass's own state is what persistence carries, and it calls super\n    // first. Accepting quietly here is what lets that state through.\n    protected void loadAdditional(ValueInput input) {\n    }"),
    ('    protected void saveAdditional(ValueOutput output) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.saveAdditional:(Lnet/minecraft/world/level/storage/ValueOutput;)V");\n    }',
     '    protected void saveAdditional(ValueOutput output) {\n    }'),
])


# ---------------------------------------------------------- handler setStacks, real
edit('net/neoforged/neoforge/transfer/StacksResourceHandler.java', [
    ('    protected void setStacks(NonNullList<S> stacks) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.setStacks:(Lnet/minecraft/core/NonNullList;)V");\n    }',
     '    // Pumpkin divergence: real body -- this is how deserialized contents land.\n    protected void setStacks(NonNullList<S> stacks) {\n        this.stacks = stacks;\n    }'),
])


# ------------------------------------------------------------- level side, vanilla
edit('net/minecraft/world/level/Level.java', [
    ('    public boolean isClientSide() {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.isClientSide:()Z");\n    }',
     '    // Pumpkin divergence: vanilla body verbatim -- the pruner kept the field.\n    public boolean isClientSide() {\n        return this.isClientSide;\n    }'),
])


# ---------------------------------------------------- enum property identity
edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
])


# --------------------------------------------------- block pos arithmetic, vanilla
edit('net/minecraft/core/BlockPos.java', [
    ('    public BlockPos offset(int x, int y, int z) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(III)Lnet/minecraft/core/BlockPos;");\n    }',
     '    // Pumpkin divergence: vanilla bodies verbatim -- coordinate arithmetic, nothing else.\n    public BlockPos offset(int x, int y, int z) {\n        return new BlockPos(getX() + x, getY() + y, getZ() + z);\n    }'),
    ('    public BlockPos offset(Vec3i vec) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/core/BlockPos;");\n    }',
     '    public BlockPos offset(Vec3i vec) {\n        return offset(vec.getX(), vec.getY(), vec.getZ());\n    }'),
    ('    public BlockPos above() {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.above:()Lnet/minecraft/core/BlockPos;");\n    }',
     '    public BlockPos above() {\n        return offset(0, 1, 0);\n    }'),
    ('    public BlockPos above(int steps) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.above:(I)Lnet/minecraft/core/BlockPos;");\n    }',
     '    public BlockPos above(int steps) {\n        return offset(0, steps, 0);\n    }'),
    ('    public BlockPos below() {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.below:()Lnet/minecraft/core/BlockPos;");\n    }',
     '    public BlockPos below() {\n        return offset(0, -1, 0);\n    }'),
    ('    public BlockPos below(int steps) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.below:(I)Lnet/minecraft/core/BlockPos;");\n    }',
     '    public BlockPos below(int steps) {\n        return offset(0, -steps, 0);\n    }'),
])


# ------------------------------------------------------ crafting input, real grid
edit('net/minecraft/world/item/crafting/CraftingInput.java', [
    ('    public static CraftingInput of(int width, int height, List<ItemStack> items) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.of:(IILjava/util/List;)Lnet/minecraft/world/item/crafting/CraftingInput;");\n    }',
     '    // Pumpkin divergence: real bodies -- an input really carries its grid. This is what\n    // a machine hands to Recipe.matches; nothing here is behaviour, only storage.\n    private int pumpkinWidth;\n\n    private int pumpkinHeight;\n\n    private List<ItemStack> pumpkinItems = List.of();\n\n    public static CraftingInput of(int width, int height, List<ItemStack> items) {\n        CraftingInput input = new CraftingInput();\n        input.pumpkinWidth = width;\n        input.pumpkinHeight = height;\n        input.pumpkinItems = items;\n        return input;\n    }'),
    ('    public ItemStack getItem(int index) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.getItem:(I)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    public ItemStack getItem(int index) {\n        return pumpkinItems.get(index);\n    }'),
    ('    public ItemStack getItem(int x, int y) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.getItem:(II)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    public ItemStack getItem(int x, int y) {\n        return pumpkinItems.get(x + y * pumpkinWidth);\n    }'),
    ('    public int size() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.size:()I");\n    }',
     '    public int size() {\n        return pumpkinItems.size();\n    }'),
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.isEmpty:()Z");\n    }',
     '    public boolean isEmpty() {\n        return pumpkinItems.stream().allMatch(ItemStack::isEmpty);\n    }'),
])


# ------------------------------------------------------------ ingredient, real codec
edit('net/minecraft/world/item/crafting/Ingredient.java', [
    ('    public static final Codec<Ingredient> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/crafting/Ingredient.CODEC");\n\n    private Ingredient(HolderSet<Item> values) {\n    }',
     '    // Pumpkin divergence: a real codec for the shapes mod recipes actually use -- a\n    // plain item id, "#tag", or a list of either. NeoForge custom ingredient maps\n    // (neoforge:ingredient_type) refuse with a reason, so a recipe using one fails its\n    // decode loudly and is counted, never half-matched.\n    public static final Codec<Ingredient> CODEC = new com.mojang.serialization.codecs.PrimitiveCodec<Ingredient>() {\n        @Override\n        public <T> com.mojang.serialization.DataResult<Ingredient> read(\n                com.mojang.serialization.DynamicOps<T> ops, T input) {\n            var asString = ops.getStringValue(input);\n            if (asString.result().isPresent()) {\n                return com.mojang.serialization.DataResult.success(\n                        pumpkinOf(java.util.List.of(asString.result().get())));\n            }\n            var asList = ops.getStream(input);\n            if (asList.result().isPresent()) {\n                java.util.List<String> ids = new java.util.ArrayList<>();\n                for (T entry : asList.result().get().toList()) {\n                    var entryString = ops.getStringValue(entry);\n                    if (entryString.result().isEmpty()) {\n                        return com.mojang.serialization.DataResult.error(\n                                () -> "unsupported ingredient entry (custom ingredient types are not decodable here)");\n                    }\n                    ids.add(entryString.result().get());\n                }\n                return com.mojang.serialization.DataResult.success(pumpkinOf(ids));\n            }\n            return com.mojang.serialization.DataResult.error(\n                    () -> "unsupported ingredient shape (custom ingredient types are not decodable here)");\n        }\n\n        @Override\n        public <T> T write(com.mojang.serialization.DynamicOps<T> ops, Ingredient value) {\n            throw dev.pumpkin.shim.Unimplemented.forMember(\n                    "net/minecraft/world/item/crafting/Ingredient.CODEC.encode");\n        }\n    };\n\n    // Pumpkin divergence: the decoded item ids ("#..." entries are tags, kept but matched\n    // never -- see test()).\n    private java.util.List<String> pumpkinIds = java.util.List.of();\n\n    private static Ingredient pumpkinOf(java.util.List<String> ids) {\n        Ingredient ingredient = new Ingredient((HolderSet<Item>) null);\n        ingredient.pumpkinIds = ids;\n        return ingredient;\n    }\n\n    private Ingredient(HolderSet<Item> values) {\n    }'),
    ('    public boolean test(ItemStack input) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.test:(Lnet/minecraft/world/item/ItemStack;)Z");\n    }',
     '    // Pumpkin divergence: real body over the decoded ids. A tag entry matches nothing\n    // yet -- item tag membership for mod items is its own slice -- and says so once.\n    public boolean test(ItemStack input) {\n        if (input == null || input.isEmpty()) {\n            return false;\n        }\n        String id = dev.pumpkin.bridge.PumpkinInteractions.pumpkinItemId(input);\n        for (String candidate : pumpkinIds) {\n            if (candidate.startsWith("#")) {\n                if (dev.pumpkin.bridge.PumpkinTags.contains(candidate.substring(1), id)) {\n                    return true;\n                }\n                continue;\n            }\n            if (candidate.equals(id)) {\n                return true;\n            }\n        }\n        return false;\n    }'),
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.isEmpty:()Z");\n    }',
     '    public boolean isEmpty() {\n        return pumpkinIds.isEmpty();\n    }'),
])


# --------------------------------------------------- item stack template, real codec
edit('net/minecraft/world/item/ItemStackTemplate.java', [
    ('    public static final Codec<ItemStackTemplate> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/item/ItemStackTemplate.CODEC");',
     '    // Pumpkin divergence: a real codec for the one shape recipe results use -- a map of\n    // {id, optional count}. Components in a result refuse loudly; nothing decodes them\n    // here yet.\n    public static final Codec<ItemStackTemplate> CODEC = new com.mojang.serialization.codecs.PrimitiveCodec<ItemStackTemplate>() {\n        @Override\n        public <T> com.mojang.serialization.DataResult<ItemStackTemplate> read(\n                com.mojang.serialization.DynamicOps<T> ops, T input) {\n            var map = ops.getMap(input);\n            if (map.result().isEmpty()) {\n                return com.mojang.serialization.DataResult.error(() -> "result is not a map");\n            }\n            var like = map.result().get();\n            T idValue = like.get("id");\n            if (idValue == null) {\n                return com.mojang.serialization.DataResult.error(() -> "result has no id");\n            }\n            var id = ops.getStringValue(idValue);\n            if (id.result().isEmpty()) {\n                return com.mojang.serialization.DataResult.error(() -> "result id is not a string");\n            }\n            if (like.get("components") != null) {\n                return com.mojang.serialization.DataResult.error(\n                        () -> "result components are not decodable here");\n            }\n            int count = 1;\n            T countValue = like.get("count");\n            if (countValue != null) {\n                var parsed = ops.getNumberValue(countValue);\n                if (parsed.result().isPresent()) {\n                    count = parsed.result().get().intValue();\n                }\n            }\n            net.minecraft.world.item.ItemStack stack = dev.pumpkin.bridge.PumpkinInteractions\n                    .pumpkinBuildStack(id.result().get(), count);\n            Item item = stack.getItem();\n            @SuppressWarnings("unchecked")\n            Holder<Item> holder = (Holder<Item>) dev.pumpkin.shim.Stubs.of(Holder.class,\n                    "net/minecraft/core/Holder", java.util.Map.of("value", item));\n            return com.mojang.serialization.DataResult.success(\n                    new ItemStackTemplate(holder, count, (DataComponentPatch) null));\n        }\n\n        @Override\n        public <T> T write(com.mojang.serialization.DynamicOps<T> ops, ItemStackTemplate value) {\n            throw dev.pumpkin.shim.Unimplemented.forMember(\n                    "net/minecraft/world/item/ItemStackTemplate.CODEC.encode");\n        }\n    };'),
])


# ------------------------------------------------ crafting input ingredient count
edit('net/minecraft/world/item/crafting/CraftingInput.java', [
    ('    public int ingredientCount() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/CraftingInput.ingredientCount:()I");\n    }',
     '    // Pumpkin divergence: vanilla body -- how many slots actually hold something.\n    public int ingredientCount() {\n        int count = 0;\n        for (ItemStack stack : pumpkinItems) {\n            if (!stack.isEmpty()) {\n                count++;\n            }\n        }\n        return count;\n    }'),
])


# ------------------------------------------------------- non null list create/add
edit('net/minecraft/core/NonNullList.java', [
    ('    public static <E> NonNullList<E> create() {\n        throw Unimplemented.forMember("net/minecraft/core/NonNullList.create:()Lnet/minecraft/core/NonNullList;");\n    }',
     '    // Pumpkin divergence: vanilla shape -- an empty growable list with no default.\n    public static <E> NonNullList<E> create() {\n        return new NonNullList<>(new java.util.ArrayList<>(), null);\n    }'),
    ('    public void add(int index, E element) {\n        throw Unimplemented.forMember("net/minecraft/core/NonNullList.add:(ILjava/lang/Object;)V");\n    }',
     '    public void add(int index, E element) {\n        pumpkinBacking.add(index, element);\n    }'),
])


# ------------------------------------------------------ recipe matcher, real
edit('net/neoforged/neoforge/common/util/RecipeMatcher.java', [
    ('    public static <T> int[] findMatches(List<T> inputs, List<? extends Predicate<T>> tests) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/RecipeMatcher.findMatches:(Ljava/util/List;Ljava/util/List;)[I");\n    }',
     '    // Pumpkin divergence: NeoForge\'s algorithm, reimplemented -- a backtracking perfect\n    // matching of inputs to ingredient tests. Returns which test each input satisfies,\n    // or null when no assignment covers every test, which is what "the recipe does not\n    // match" means for shapeless-style machines.\n    public static <T> int[] findMatches(List<T> inputs, List<? extends Predicate<T>> tests) {\n        if (inputs.size() != tests.size()) {\n            return null;\n        }\n        int size = tests.size();\n        boolean[][] accepts = new boolean[size][size];\n        for (int input = 0; input < size; input++) {\n            for (int test = 0; test < size; test++) {\n                accepts[input][test] = tests.get(test).test(inputs.get(input));\n            }\n        }\n        int[] assigned = new int[size];\n        java.util.Arrays.fill(assigned, -1);\n        boolean[] used = new boolean[size];\n        if (assign(accepts, assigned, used, 0, size)) {\n            return assigned;\n        }\n        return null;\n    }\n\n    private static boolean assign(boolean[][] accepts, int[] assigned, boolean[] used,\n            int input, int size) {\n        if (input == size) {\n            return true;\n        }\n        for (int test = 0; test < size; test++) {\n            if (!used[test] && accepts[input][test]) {\n                used[test] = true;\n                assigned[input] = test;\n                if (assign(accepts, assigned, used, input + 1, size)) {\n                    return true;\n                }\n                used[test] = false;\n                assigned[input] = -1;\n            }\n        }\n        return false;\n    }'),
])


# ---------------------------------------------------- crafting remainder, empty
edit('net/neoforged/neoforge/common/extensions/ItemInstanceExtension.java', [
    ('    default ItemStackTemplate getCraftingRemainder() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ItemInstanceExtension.getCraftingRemainder:()Lnet/minecraft/world/item/ItemStackTemplate;");\n    }',
     '    // Pumpkin divergence: no item this host builds declares a crafting remainder (the\n    // recorder for Properties.craftRemainder does not exist yet), so the truthful answer\n    // is always "nothing stays behind" -- an empty template, which is what vanilla\n    // returns for a remainder-less item.\n    default ItemStackTemplate getCraftingRemainder() {\n        return new ItemStackTemplate((net.minecraft.world.item.Item) null, 0,\n                (net.minecraft.core.component.DataComponentPatch) null);\n    }'),
])


# ---------------------------------------------------------- particle types, defused
edit('net/minecraft/core/particles/ParticleTypes.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/core/particles/ParticleTypes");\n        }\n    }',
     '    // Pumpkin divergence: the throwing clinit is defused. Every field stays null, and the\n    // one consumer this host serves -- a machine spawning decoration -- hands the value\n    // straight to the bridge level, which accepts and drops particles. A machine must not\n    // die over sparkles.\n    static {\n    }'),
])


# ------------------------------------------------- template create + resource item

edit('net/minecraft/world/item/ItemStackTemplate.java', [
    ('    public ItemStack create() {\n        throw Unimplemented.forMember("net/minecraft/world/item/ItemStackTemplate.create:()Lnet/minecraft/world/item/ItemStack;");\n    }',
     "    // Pumpkin divergence: real body -- the template's whole point is making this stack.\n    public ItemStack create() {\n        if (item == null || count <= 0) {\n            return ItemStack.EMPTY;\n        }\n        return new ItemStack(item.value(), count);\n    }"),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public Item getItem() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getItem:()Lnet/minecraft/world/item/Item;");\n    }',
     '    // Pumpkin divergence: real body over the carried item.\n    public Item getItem() {\n        return pumpkinItem == null ? null : pumpkinItem.asItem();\n    }'),
])

# ------------------------------------------------------- menus, slot machinery

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public final Container container = Stubs.of(Container.class, "net/minecraft/world/Container");\n\n    public int index;\n\n    public final int x = 0;\n\n    public final int y = 0;\n\n    public Slot(Container container, int slot, int x, int y) {\n    }',
     '    // Pumpkin divergence: a slot really points at its container and position; the menu\n    // machinery reads all four.\n    public final Container container;\n\n    public int index;\n\n    public final int x;\n\n    public final int y;\n\n    private final int pumpkinContainerSlot;\n\n    public int pumpkinContainerSlot() {\n        return pumpkinContainerSlot;\n    }\n\n    public Slot(Container container, int slot, int x, int y) {\n        this.container = container;\n        this.pumpkinContainerSlot = slot;\n        this.x = x;\n        this.y = y;\n    }'),
])


edit('net/minecraft/world/inventory/Slot.java', [
    ('    public ItemStack getItem() {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.getItem:()Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    // Pumpkin divergence: vanilla body -- read through to the container.\n    public ItemStack getItem() {\n        return container.getItem(pumpkinContainerSlot);\n    }'),
])


edit('net/minecraft/world/inventory/AbstractContainerMenu.java', [
    ('    public final NonNullList<Slot> slots = null;',
     '    // Pumpkin divergence: the slot list is real; every mod menu fills it via addSlot.\n    public final NonNullList<Slot> slots = NonNullList.create();\n\n    private MenuType<?> pumpkinMenuType;\n\n    public int containerId;'),
])


edit('net/minecraft/world/inventory/AbstractContainerMenu.java', [
    ('    protected AbstractContainerMenu(MenuType<?> menuType, int containerId) {\n    }',
     '    protected AbstractContainerMenu(MenuType<?> menuType, int containerId) {\n        this.pumpkinMenuType = menuType;\n        this.containerId = containerId;\n    }'),
])


edit('net/minecraft/world/inventory/AbstractContainerMenu.java', [
    ('    protected Slot addSlot(Slot slot) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.addSlot:(Lnet/minecraft/world/inventory/Slot;)Lnet/minecraft/world/inventory/Slot;");\n    }',
     '    // Pumpkin divergence: vanilla body -- number the slot, keep it.\n    protected Slot addSlot(Slot slot) {\n        slot.index = slots.size();\n        slots.add(slot);\n        return slot;\n    }'),
])


edit('net/minecraft/world/inventory/AbstractContainerMenu.java', [
    ('    public MenuType<?> getType() {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getType:()Lnet/minecraft/world/inventory/MenuType;");\n    }',
     '    public MenuType<?> getType() {\n        return pumpkinMenuType;\n    }'),
])


edit("net/minecraft/world/inventory/Slot.java", [
    ('    public Slot() {\n    }',
     '    public Slot() {\n        this.container = null;\n        this.pumpkinContainerSlot = 0;\n        this.x = 0;\n        this.y = 0;\n    }'),
])

edit('net/minecraft/world/entity/player/Inventory.java', [
    ('    public ItemStack getItem(int slot) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getItem:(I)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    // Pumpkin divergence: a real 41-slot backing (36 inventory + armour + offhand). The\n    // bridge hydrates it from the Rust player when a menu opens; empty until then.\n    private final NonNullList<ItemStack> pumpkinItems =\n            NonNullList.withSize(41, ItemStack.EMPTY);\n\n    public NonNullList<ItemStack> pumpkinItems() {\n        return pumpkinItems;\n    }\n\n    public ItemStack getItem(int index) {\n        return index >= 0 && index < pumpkinItems.size()\n                ? pumpkinItems.get(index) : ItemStack.EMPTY;\n    }'),
])

edit('net/neoforged/neoforge/world/inventory/StackCopySlot.java', [
    ('    public StackCopySlot(int slot, int x, int y) {\n    }',
     "    // Pumpkin divergence: the ctor feeds Slot's real fields; the container is null\n    // because this slot reads through getStackCopy instead.\n    public StackCopySlot(int slot, int x, int y) {\n        super(null, slot, x, y);\n    }"),
    ('    public final ItemStack getItem() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.getItem:()Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    // Pumpkin divergence: NeoForge body -- the whole point of the class.\n    public final ItemStack getItem() {\n        return getStackCopy();\n    }'),
    ('    public final void set(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/world/inventory/StackCopySlot.set:(Lnet/minecraft/world/item/ItemStack;)V");\n    }',
     '    public final void set(ItemStack stack) {\n        setStackCopy(stack);\n    }'),
])

edit('net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.java', [
    ('    public ResourceHandlerSlot(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition) {\n    }',
     "    // Pumpkin divergence: the handler and slot index are kept; the copy accessors below\n    // read and write through them, which is this class's whole job.\n    private ResourceHandler<ItemResource> pumpkinHandler;\n\n    private int pumpkinHandlerSlot;\n\n    public ResourceHandlerSlot(ResourceHandler<ItemResource> handler, IndexModifier<ItemResource> slotModifier, int handlerSlot, int xPosition, int yPosition) {\n        super(handlerSlot, xPosition, yPosition);\n        this.pumpkinHandler = handler;\n        this.pumpkinHandlerSlot = handlerSlot;\n    }"),
    ('    protected ItemStack getStackCopy() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.getStackCopy:()Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    protected ItemStack getStackCopy() {\n        ItemResource resource = pumpkinHandler.getResource(pumpkinHandlerSlot);\n        if (resource == null || resource.isEmpty()) {\n            return ItemStack.EMPTY;\n        }\n        return resource.toStack(pumpkinHandler.getAmountAsInt(pumpkinHandlerSlot));\n    }'),
    ('    protected void setStackCopy(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.setStackCopy:(Lnet/minecraft/world/item/ItemStack;)V");\n    }',
     '    protected void setStackCopy(ItemStack stack) {\n        if (pumpkinHandler instanceof net.neoforged.neoforge.transfer.StacksResourceHandler<?, ItemResource> stacks) {\n            stacks.set(pumpkinHandlerSlot, ItemResource.of(stack), stack.count());\n        } else {\n            throw dev.pumpkin.shim.Unimplemented.forMember(\n                    "net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.setStackCopy (non-stack handler)");\n        }\n    }'),
])

# ------------------------------------------------------- menus S2, click machinery

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public boolean mayPlace(ItemStack itemStack) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.mayPlace:(Lnet/minecraft/world/item/ItemStack;)Z");\n    }',
     "    // Pumpkin divergence: vanilla bodies for the click machinery. mayPlace/mayPickup\n    // default open, exactly as vanilla's base slot does; subclasses narrow them.\n    public boolean mayPlace(ItemStack itemStack) {\n        return true;\n    }\n\n    public boolean mayPickup(net.minecraft.world.entity.player.Player player) {\n        return true;\n    }"),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public boolean hasItem() {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.hasItem:()Z");\n    }',
     '    public boolean hasItem() {\n        return !getItem().isEmpty();\n    }'),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public void set(ItemStack itemStack) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.set:(Lnet/minecraft/world/item/ItemStack;)V");\n    }',
     '    public void set(ItemStack itemStack) {\n        container.setItem(pumpkinContainerSlot, itemStack);\n        setChanged();\n    }'),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public void setChanged() {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.setChanged:()V");\n    }',
     "    public void setChanged() {\n        // Nothing to mark: the click bridge serialises the menu's state after every\n        // click regardless.\n    }"),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public int getMaxStackSize() {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.getMaxStackSize:()I");\n    }',
     '    public int getMaxStackSize() {\n        return 64;\n    }'),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public ItemStack remove(int amount) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.remove:(I)Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    public ItemStack remove(int amount) {\n        ItemStack current = getItem();\n        if (current.isEmpty() || amount <= 0) {\n            return ItemStack.EMPTY;\n        }\n        int taken = Math.min(amount, current.count());\n        ItemStack removed = current.copyWithCount(taken);\n        set(current.count() == taken ? ItemStack.EMPTY\n                : current.copyWithCount(current.count() - taken));\n        return removed;\n    }'),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public void onTake(Player player, ItemStack carried) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.onTake:(Lnet/minecraft/world/entity/player/Player;Lnet/minecraft/world/item/ItemStack;)V");\n    }',
     '    public void onTake(Player player, ItemStack carried) {\n        // Vanilla hooks crafting stats here; the base has nothing to do.\n    }'),
])

edit('net/minecraft/world/inventory/AbstractContainerMenu.java', [
    ('    protected boolean moveItemStackTo(ItemStack itemStack, int startSlot, int endSlot, boolean backwards) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.moveItemStackTo:(Lnet/minecraft/world/item/ItemStack;IIZ)Z");\n    }',
     "    // Pumpkin divergence: vanilla's merge algorithm, the workhorse every mod's\n    // quickMoveStack leans on -- fill matching stacks first, then empty slots.\n    protected boolean moveItemStackTo(ItemStack itemStack, int startSlot, int endSlot, boolean backwards) {\n        boolean moved = false;\n        int index = backwards ? endSlot - 1 : startSlot;\n        while (itemStack.count() > 0 && (backwards ? index >= startSlot : index < endSlot)) {\n            Slot slot = slots.get(index);\n            ItemStack existing = slot.getItem();\n            if (!existing.isEmpty() && existing.getItem() == itemStack.getItem()) {\n                int total = existing.count() + itemStack.count();\n                int max = Math.min(slot.getMaxStackSize(), 64);\n                if (total <= max) {\n                    slot.set(existing.copyWithCount(total));\n                    pumpkinShrink(itemStack, itemStack.count());\n                    moved = true;\n                } else if (existing.count() < max) {\n                    int adding = max - existing.count();\n                    slot.set(existing.copyWithCount(max));\n                    pumpkinShrink(itemStack, adding);\n                    moved = true;\n                }\n            }\n            index += backwards ? -1 : 1;\n        }\n        if (itemStack.count() > 0) {\n            index = backwards ? endSlot - 1 : startSlot;\n            while (backwards ? index >= startSlot : index < endSlot) {\n                Slot slot = slots.get(index);\n                if (!slot.hasItem() && slot.mayPlace(itemStack)) {\n                    int placing = Math.min(itemStack.count(), slot.getMaxStackSize());\n                    slot.set(itemStack.copyWithCount(placing));\n                    pumpkinShrink(itemStack, placing);\n                    moved = true;\n                    if (itemStack.count() <= 0) {\n                        break;\n                    }\n                }\n                index += backwards ? -1 : 1;\n            }\n        }\n        return moved;\n    }\n\n    // ItemStack counts are immutable in this shim (copyWithCount replaces); the caller's\n    // stack is shrunk by swapping its contents through the carried reference the click\n    // bridge owns. Tracked here as a mutable count on the wrapper.\n    private void pumpkinShrink(ItemStack stack, int by) {\n        stack.pumpkinShrink(by);\n    }\n\n    // Pumpkin divergence: the carried stack -- vanilla keeps it on the menu too.\n    private ItemStack pumpkinCarried = ItemStack.EMPTY;\n\n    public ItemStack getCarried() {\n        return pumpkinCarried;\n    }\n\n    public void setCarried(ItemStack stack) {\n        this.pumpkinCarried = stack;\n    }"),
])

edit('net/minecraft/world/inventory/AbstractContainerMenu.java', [
    ('    public Slot getSlot(int index) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getSlot:(I)Lnet/minecraft/world/inventory/Slot;");\n    }',
     '    public Slot getSlot(int index) {\n        return slots.get(index);\n    }'),
])

edit('net/minecraft/world/entity/player/Inventory.java', [
    ('    public void setItem(int slot, ItemStack itemStack) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.setItem:(ILnet/minecraft/world/item/ItemStack;)V");\n    }',
     '    public void setItem(int index, ItemStack stack) {\n        if (index >= 0 && index < pumpkinItems.size()) {\n            pumpkinItems.set(index, stack);\n        }\n    }'),
])

edit('net/neoforged/neoforge/transfer/StacksResourceHandler.java', [
    ('    public boolean isValid(int index, T resource) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/StacksResourceHandler.isValid:(ILnet/neoforged/neoforge/transfer/resource/Resource;)Z");\n    }',
     "    // Pumpkin divergence: vanilla's base answer; subclasses narrow.\n    public boolean isValid(int index, T resource) {\n        return true;\n    }"),
])

edit('net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.java', [
    ('    public boolean mayPlace(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.mayPlace:(Lnet/minecraft/world/item/ItemStack;)Z");\n    }',
     '    // Pumpkin divergence: NeoForge shape -- ask the handler.\n    public boolean mayPlace(ItemStack stack) {\n        return pumpkinHandler.isValid(pumpkinHandlerSlot, ItemResource.of(stack));\n    }'),
])

edit('net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.java', [
    ('    public int getMaxStackSize() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.getMaxStackSize:()I");\n    }',
     '    public int getMaxStackSize() {\n        return 64;\n    }'),
])

edit('net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.java', [
    ('    public int getMaxStackSize(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.getMaxStackSize:(Lnet/minecraft/world/item/ItemStack;)I");\n    }',
     '    public int getMaxStackSize(ItemStack stack) {\n        return 64;\n    }'),
])

edit('net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.java', [
    ('    public boolean mayPickup(Player player) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ResourceHandlerSlot.mayPickup:(Lnet/minecraft/world/entity/player/Player;)Z");\n    }',
     '    public boolean mayPickup(Player player) {\n        return true;\n    }'),
])

edit('net/minecraft/world/item/ItemStack.java', [
    ('    public ItemLike pumpkinItemLike() {\n        return pumpkinItem;\n    }',
     '    public ItemLike pumpkinItemLike() {\n        return pumpkinItem;\n    }\n\n    // Pumpkin divergence: in-place shrink, for the one algorithm (moveItemStackTo) that\n    // vanilla writes against a mutable count.\n    public void pumpkinShrink(int by) {\n        pumpkinCount = Math.max(0, pumpkinCount - by);\n        if (pumpkinCount == 0) {\n            pumpkinItem = null;\n        }\n    }'),
])

edit("net/minecraft/world/inventory/Slot.java", [
    ('    public int getMaxStackSize(ItemStack itemStack) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.getMaxStackSize:(Lnet/minecraft/world/item/ItemStack;)I");\n    }',
     '    public int getMaxStackSize(ItemStack itemStack) {\n        return getMaxStackSize();\n    }'),
])

edit("net/minecraft/world/item/ItemStack.java", [
    ('    public ItemStack copy() {\n        throw Unimplemented.forMember("net/minecraft/world/item/ItemStack.copy:()Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    // Pumpkin divergence: real body.\n    public ItemStack copy() {\n        return copyWithCount(pumpkinCount);\n    }'),
])

# ------------------------------------------------- crop states, property values

edit('net/minecraft/world/level/block/state/properties/Property.java', [
    ('    public String pumpkinName;',
     '    public String pumpkinName;\n\n    // Pumpkin divergence: the possible values, in declaration order -- what registration\n    // sends to the server so a block gets one state per combination.\n    public java.util.List<String> pumpkinPossibleValues = new java.util.ArrayList<>();\n\n    // The typed value each spelling parses to, for rebuilding a state from a string.\n    public java.util.Map<String, Comparable<?>> pumpkinParse = new java.util.HashMap<>();'),
])

edit('net/minecraft/world/level/block/state/properties/IntegerProperty.java', [
    ('    // Pumpkin divergence: real body. The range constrains a file no one writes.\n    public static IntegerProperty create(String name, int min, int max) {\n        IntegerProperty property = new IntegerProperty();\n        property.pumpkinName = name;\n        return property;\n    }',
     '    // Pumpkin divergence: real body, values included -- registration walks them.\n    public static IntegerProperty create(String name, int min, int max) {\n        IntegerProperty property = new IntegerProperty();\n        property.pumpkinName = name;\n        java.util.List<String> values = new java.util.ArrayList<>();\n        java.util.Map<String, Comparable<?>> parse = new java.util.HashMap<>();\n        for (int value = min; value <= max; value++) {\n            values.add(Integer.toString(value));\n            parse.put(Integer.toString(value), value);\n        }\n        property.pumpkinPossibleValues = java.util.List.copyOf(values);\n        property.pumpkinParse = java.util.Map.copyOf(parse);\n        return property;\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/BooleanProperty.java', [
    ('    // Pumpkin divergence: real body -- a named property is just its name here.\n    public static BooleanProperty create(String name) {\n        BooleanProperty property = new BooleanProperty();\n        property.pumpkinName = name;\n        return property;\n    }',
     '    // Pumpkin divergence: real body, values included -- registration walks them.\n    public static BooleanProperty create(String name) {\n        BooleanProperty property = new BooleanProperty();\n        property.pumpkinName = name;\n        property.pumpkinPossibleValues = java.util.List.of("true", "false");\n        property.pumpkinParse = java.util.Map.of("true", Boolean.TRUE, "false", Boolean.FALSE);\n        return property;\n    }'),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('    public Block(BlockBehaviour.Properties properties) {\n        this.pumpkinProperties = properties;\n    }',
     "    // Pumpkin divergence: the declared state properties, collected the way vanilla does\n    // -- by running createBlockStateDefinition from the constructor. (Vanilla's famous\n    // quirk: the subclass override runs before subclass fields initialise; mods are\n    // written to survive it.)\n    private java.util.List<net.minecraft.world.level.block.state.properties.Property<?>> pumpkinDeclaredProperties = java.util.List.of();\n\n    public java.util.List<net.minecraft.world.level.block.state.properties.Property<?>> pumpkinDeclaredProperties() {\n        return pumpkinDeclaredProperties;\n    }\n\n    public Block(BlockBehaviour.Properties properties) {\n        this.pumpkinProperties = properties;\n        net.minecraft.world.level.block.state.StateDefinition.Builder<Block, net.minecraft.world.level.block.state.BlockState> builder =\n                new net.minecraft.world.level.block.state.StateDefinition.Builder<>(this);\n        createBlockStateDefinition(builder);\n        this.pumpkinDeclaredProperties = builder.pumpkinProperties();\n    }"),
])

edit('net/minecraft/world/level/block/state/StateDefinition.java', [
    ('    public static class Builder<O, S extends StateHolder<O, S>> {\n\n        public Builder(O owner) {\n        }\n\n        public StateDefinition.Builder<O, S> add(Property<?>... properties) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/StateDefinition$Builder.add:([Lnet/minecraft/world/level/block/state/properties/Property;)Lnet/minecraft/world/level/block/state/StateDefinition$Builder;");\n        }',
     '    public static class Builder<O, S extends StateHolder<O, S>> {\n\n        // Pumpkin divergence: the builder records what add() declares; registration\n        // reads it back.\n        private final java.util.List<Property<?>> pumpkinProperties = new java.util.ArrayList<>();\n\n        public java.util.List<Property<?>> pumpkinProperties() {\n            return pumpkinProperties;\n        }\n\n        public Builder(O owner) {\n        }\n\n        public StateDefinition.Builder<O, S> add(Property<?>... properties) {\n            java.util.Collections.addAll(pumpkinProperties, properties);\n            return this;\n        }'),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('    public Block(BlockBehaviour.Properties properties) {\n        this.pumpkinProperties = properties;\n        net.minecraft.world.level.block.state.StateDefinition.Builder<Block, net.minecraft.world.level.block.state.BlockState> builder =',
     '    // Pumpkin divergence: the base declaration hook vanilla keeps on BlockBehaviour;\n    // the base declares nothing, subclasses add their properties.\n    protected void createBlockStateDefinition(\n            net.minecraft.world.level.block.state.StateDefinition.Builder<Block, net.minecraft.world.level.block.state.BlockState> builder) {\n    }\n\n    public Block(BlockBehaviour.Properties properties) {\n        this.pumpkinProperties = properties;\n        net.minecraft.world.level.block.state.StateDefinition.Builder<Block, net.minecraft.world.level.block.state.BlockState> builder ='),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: vanilla body -- a crop is its age.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        builder.add(AGE);\n    }'),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    // Pumpkin divergence: no vanilla counterpart. The comma-joined registered ids of a',
     '    // Pumpkin divergence: no vanilla counterpart. The block\'s declared properties in\n    // the sink\'s wire spelling; empty for a block that declares none.\n    static String pumpkinStateProperties(net.minecraft.world.level.block.Block block) {\n        StringBuilder joined = new StringBuilder();\n        for (net.minecraft.world.level.block.state.properties.Property<?> property\n                : block.pumpkinDeclaredProperties()) {\n            if (joined.length() > 0) {\n                joined.append(\';\');\n            }\n            joined.append(property.getName()).append(\':\')\n                    .append(String.join("|", property.pumpkinPossibleValues));\n        }\n        return joined.toString();\n    }\n\n    // Pumpkin divergence: no vanilla counterpart. The comma-joined registered ids of a'),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());',
     '                pumpkinSink.registerBlock(holder.getId().toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool(), pumpkinStateProperties(block));'),
])

edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool());',
     '                DeferredRegister.pumpkinSink().registerBlock(name.toString(), block.pumpkinTemplate(),\n                        props.pumpkinDestroyTime(), props.pumpkinExplosionResistance(),\n                        props.pumpkinRequiresTool(),\n                        DeferredRegister.pumpkinStateProperties(block));'),
])

edit('net/minecraft/world/level/block/FarmlandBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/FarmlandBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: vanilla body -- the properties this block declares.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        builder.add(MOISTURE);\n    }'),
])

edit('net/minecraft/world/level/block/NetherWartBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/NetherWartBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: vanilla body -- the properties this block declares.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        builder.add(AGE);\n    }'),
])

edit('net/minecraft/world/level/block/SaplingBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/SaplingBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/SkullBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/SkullBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/AbstractSkullBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/AbstractSkullBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/DispenserBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/DispenserBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/WallBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/WallBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/LiquidBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/LiquidBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/SlabBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/SlabBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/CampfireBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CampfireBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/StairBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/StairBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: declares nothing here. The vanilla declaration needs\n    // property constants this shim does not carry yet; a subclass registering\n    // through this base gets a single state until they exist, rather than a\n    // constructor crash.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    public List<T> getPossibleValues() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getPossibleValues:()Ljava/util/List;");\n    }',
     '    // Pumpkin divergence: real body -- the enum constants recorded by create().\n    public List<T> getPossibleValues() {\n        return pumpkinValues;\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    public Optional<T> getValue(String name) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getValue:(Ljava/lang/String;)Ljava/util/Optional;");\n    }',
     '    // Pumpkin divergence: real body -- looks up by serialized name, as vanilla does.\n    @SuppressWarnings("unchecked")\n    public Optional<T> getValue(String name) {\n        return Optional.ofNullable((T) pumpkinParse.get(name));\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    public String getName(T value) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.getName:(Ljava/lang/Enum;)Ljava/lang/String;");\n    }',
     "    // Pumpkin divergence: real body -- an enum property value's name is its serialized name.\n    public String getName(T value) {\n        return value.getSerializedName();\n    }"),
])

edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    public boolean equals(Object o) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.equals:(Ljava/lang/Object;)Z");\n    }',
     "    // Pumpkin divergence: identity equality. Vanilla compares name and value list, but every\n    // property a mod hands us is a static singleton, so identity gives the same answer and\n    // needs nothing the shim lacks. StateHolder.setValue's Map.copyOf probes this.\n    public boolean equals(Object o) {\n        return this == o;\n    }"),
])

edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    // Pumpkin divergence: real body -- a named property is its name here, as with\n    // BooleanProperty.create.\n    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz) {\n        EnumProperty<T> property = new EnumProperty<>();\n        property.pumpkinName = name;\n        return property;\n    }',
     '    // Pumpkin divergence: real body -- records the enum constants and their serialized\n    // names so registration can describe every state this property produces.\n    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz) {\n        EnumProperty<T> property = new EnumProperty<>();\n        property.pumpkinName = name;\n        property.pumpkinValues = List.of(clazz.getEnumConstants());\n        for (T value : property.pumpkinValues) {\n            property.pumpkinPossibleValues.add(value.getSerializedName());\n            property.pumpkinParse.put(value.getSerializedName(), value);\n        }\n        return property;\n    }'),
])

edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    public EnumProperty() {\n    }',
     '    public EnumProperty() {\n    }\n\n    // Pumpkin divergence: the constants create() recorded, typed; pumpkinPossibleValues on\n    // Property carries their string forms for registration.\n    private List<T> pumpkinValues = List.of();'),
])

edit('net/minecraft/core/Direction.java', [
    ('    public String getSerializedName() {\n        throw Unimplemented.forMember("net/minecraft/core/Direction.getSerializedName:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: real body -- vanilla serializes a direction as its lowercase\n    // constant name ("down", "up", "north", ...), which is what state property values carry.\n    public String getSerializedName() {\n        return name().toLowerCase(java.util.Locale.ROOT);\n    }'),
])

edit('net/minecraft/core/Direction.java', [
    ('        public String getSerializedName() {\n            throw Unimplemented.forMember("net/minecraft/core/Direction$Axis.getSerializedName:()Ljava/lang/String;");\n        }',
     '        // Pumpkin divergence: real body -- an axis serializes as "x", "y" or "z".\n        public String getSerializedName() {\n            return name().toLowerCase(java.util.Locale.ROOT);\n        }'),
])

edit('net/minecraft/world/level/block/state/BlockBehaviour.java', [
    ('        public static BlockBehaviour.Properties ofFullCopy(BlockBehaviour block) {\n            return new Properties();\n        }',
     "        // Pumpkin divergence: carries the source's template forward. A crop built with\n        // ofFullCopy(Blocks.WHEAT) must register as a wheat copy, not stone -- wheat's\n        // states are what make it randomly tick.\n        public static BlockBehaviour.Properties ofFullCopy(BlockBehaviour block) {\n            Properties properties = new Properties();\n            if (block instanceof net.minecraft.world.level.block.Block source) {\n                properties.pumpkinTemplate = source.pumpkinTemplate();\n            }\n            return properties;\n        }"),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('    public final BlockState defaultBlockState() {\n        if (defaultBlockState == null) {\n            defaultBlockState = new BlockState();\n            defaultBlockState.pumpkinOwner = this;\n        }\n        return defaultBlockState;\n    }',
     '    public final BlockState defaultBlockState() {\n        if (defaultBlockState == null) {\n            defaultBlockState = new BlockState();\n            defaultBlockState.pumpkinOwner = this;\n            // Each declared property starts at its first value, matching how the Rust\n            // side numbers states -- index 0 is all-first-values.\n            java.util.Map<net.minecraft.world.level.block.state.properties.Property<?>, Comparable<?>> values =\n                    new java.util.HashMap<>();\n            for (net.minecraft.world.level.block.state.properties.Property<?> property\n                    : pumpkinDeclaredProperties()) {\n                if (!property.pumpkinPossibleValues.isEmpty()) {\n                    values.put(property, property.pumpkinParse.get(property.pumpkinPossibleValues.get(0)));\n                }\n            }\n            defaultBlockState.pumpkinValues = java.util.Map.copyOf(values);\n        }\n        return defaultBlockState;\n    }'),
])

edit('net/minecraft/world/level/block/Blocks.java', [
    ('    public static final Block FARMLAND = pumpkinVanilla("farmland");',
     '    // Pumpkin divergence: a real FarmlandBlock, not a bare template holder -- crop growth\n    // reads state.getValue(FarmlandBlock.MOISTURE) off the soil, so the soil\'s state has\n    // to declare the property.\n    public static final Block FARMLAND =\n            new FarmlandBlock(BlockBehaviour.Properties.of().pumpkinTemplate("farmland"));'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    public int getMaxAge() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.getMaxAge:()I");\n    }',
     '    // Pumpkin divergence: vanilla body -- crops age 0 to 7.\n    public int getMaxAge() {\n        return 7;\n    }'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    public BlockState getStateForAge(int age) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.getStateForAge:(I)Lnet/minecraft/world/level/block/state/BlockState;");\n    }',
     '    // Pumpkin divergence: vanilla body.\n    public BlockState getStateForAge(int age) {\n        return defaultBlockState().setValue(AGE, age);\n    }'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    public final boolean isMaxAge(BlockState state) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.isMaxAge:(Lnet/minecraft/world/level/block/state/BlockState;)Z");\n    }',
     '    // Pumpkin divergence: vanilla body.\n    public final boolean isMaxAge(BlockState state) {\n        return state.getValue(AGE) >= getMaxAge();\n    }'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    protected boolean isRandomlyTicking(BlockState state) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.isRandomlyTicking:(Lnet/minecraft/world/level/block/state/BlockState;)Z");\n    }',
     '    // Pumpkin divergence: vanilla body -- a full-grown crop stops ticking.\n    protected boolean isRandomlyTicking(BlockState state) {\n        return !isMaxAge(state);\n    }'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.randomTick:(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");\n    }',
     "    // Pumpkin divergence: vanilla body -- light gate, farmland-weighted growth chance,\n    // one age step written back through the level. The level is Pumpkin's stand-in, whose\n    // getBlockState answers from the neighborhood snapshot the random-tick bridge carries.\n    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {\n        if (level.getRawBrightness(pos, 0) >= 9) {\n            int age = state.getValue(AGE);\n            if (age < getMaxAge()) {\n                float speed = pumpkinGrowthSpeed(level, pos);\n                if (random.nextInt((int) (25.0F / speed) + 1) == 0) {\n                    level.setBlock(pos, getStateForAge(age + 1), 2);\n                }\n            }\n        }\n    }\n\n    // Pumpkin divergence: vanilla getGrowthSpeed, private because only randomTick above\n    // calls it. Moist farmland under the crop counts 3, dry 1, diagonals a quarter; crops\n    // of the same kind in a row or touching diagonally halve the total.\n    private float pumpkinGrowthSpeed(Level level, BlockPos pos) {\n        float speed = 1.0F;\n        BlockPos below = pos.below();\n        for (int dx = -1; dx <= 1; dx++) {\n            for (int dz = -1; dz <= 1; dz++) {\n                float gain = 0.0F;\n                BlockState soil = level.getBlockState(below.offset(dx, 0, dz));\n                if (soil.getBlock() instanceof FarmlandBlock) {\n                    gain = 1.0F;\n                    if (soil.getValue(FarmlandBlock.MOISTURE) > 0) {\n                        gain = 3.0F;\n                    }\n                }\n                if (dx != 0 || dz != 0) {\n                    gain /= 4.0F;\n                }\n                speed += gain;\n            }\n        }\n        boolean row = level.getBlockState(pos.offset(-1, 0, 0)).getBlock() == this\n                || level.getBlockState(pos.offset(1, 0, 0)).getBlock() == this;\n        boolean column = level.getBlockState(pos.offset(0, 0, -1)).getBlock() == this\n                || level.getBlockState(pos.offset(0, 0, 1)).getBlock() == this;\n        if (row && column) {\n            speed /= 2.0F;\n        } else {\n            boolean diagonal = level.getBlockState(pos.offset(-1, 0, -1)).getBlock() == this\n                    || level.getBlockState(pos.offset(1, 0, -1)).getBlock() == this\n                    || level.getBlockState(pos.offset(-1, 0, 1)).getBlock() == this\n                    || level.getBlockState(pos.offset(1, 0, 1)).getBlock() == this;\n            if (diagonal) {\n                speed /= 2.0F;\n            }\n        }\n        return speed;\n    }"),
])

edit('net/minecraft/world/level/Level.java', [
    ('    public RandomSource getRandom() {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.getRandom:()Lnet/minecraft/util/RandomSource;");\n    }',
     '    public RandomSource getRandom() {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.getRandom:()Lnet/minecraft/util/RandomSource;");\n    }\n\n    // Pumpkin divergence: vanilla declares this on BlockAndTintGetter; the shim carries it\n    // here so crop growth\'s light gate has a member to override. Still throws for any\n    // level that does not answer it.\n    public int getRawBrightness(net.minecraft.core.BlockPos pos, int amount) {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.getRawBrightness:(Lnet/minecraft/core/BlockPos;I)I");\n    }'),
])

edit('net/minecraft/world/level/block/FarmlandBlock.java', [
    ('public FarmlandBlock(BlockBehaviour.Properties properties) {\n    }',
     "public FarmlandBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/NetherWartBlock.java', [
    ('public NetherWartBlock(BlockBehaviour.Properties properties) {\n    }',
     "public NetherWartBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/BaseEntityBlock.java', [
    ('public BaseEntityBlock(BlockBehaviour.Properties properties) {\n    }',
     "public BaseEntityBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/DispenserBlock.java', [
    ('public DispenserBlock(BlockBehaviour.Properties properties) {\n    }',
     "public DispenserBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/HorizontalDirectionalBlock.java', [
    ('public HorizontalDirectionalBlock(BlockBehaviour.Properties properties) {\n    }',
     "public HorizontalDirectionalBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('public CropBlock(BlockBehaviour.Properties properties) {\n    }',
     "public CropBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/WallBlock.java', [
    ('public WallBlock(BlockBehaviour.Properties properties) {\n    }',
     "public WallBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/HalfTransparentBlock.java', [
    ('public HalfTransparentBlock(BlockBehaviour.Properties properties) {\n    }',
     "public HalfTransparentBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/TransparentBlock.java', [
    ('public TransparentBlock(BlockBehaviour.Properties properties) {\n    }',
     "public TransparentBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/VegetationBlock.java', [
    ('public VegetationBlock(BlockBehaviour.Properties properties) {\n    }',
     "public VegetationBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])

edit('net/minecraft/world/level/block/SlabBlock.java', [
    ('public SlabBlock(BlockBehaviour.Properties properties) {\n    }',
     "public SlabBlock(BlockBehaviour.Properties properties) {\n        // Pumpkin divergence: chains the properties up. Without this the block's\n        // template (and everything else recorded on Properties) silently resets\n        // to the defaults -- a crop built ofFullCopy(WHEAT) registered as stone.\n        super(properties);\n    }"),
])


edit('net/minecraft/world/level/block/state/properties/IntegerProperty.java', [
    ('    public boolean equals(Object o) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/IntegerProperty.equals:(Ljava/lang/Object;)Z");\n    }',
     "    // Pumpkin divergence: identity equality, as with EnumProperty -- every property a mod\n    // hands us is a static singleton, and StateHolder.setValue's Map.copyOf probes this.\n    public boolean equals(Object o) {\n        return this == o;\n    }"),
])

edit('net/minecraft/world/level/storage/loot/parameters/LootContextParams.java', [
    ('    public static final ContextKey<Vec3> ORIGIN = null;',
     "    // Pumpkin divergence: a real key. A mod's getDrops asks the loot builder for the\n    // break position through this; identity is all the lookup needs.\n    public static final ContextKey<Vec3> ORIGIN = new ContextKey<>(null);"),
])

edit('net/minecraft/world/level/storage/loot/parameters/LootContextParams.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/world/level/storage/loot/parameters/LootContextParams");\n        }\n    }',
     '    // Pumpkin divergence: no throwing initializer. ORIGIN above is real, and stopping the\n    // class over its other (still-null) keys would stop a mod that only wanted ORIGIN.'),
    ('import dev.pumpkin.shim.Unimplemented;\n',
     ''),
])

edit('net/minecraft/core/BlockPos.java', [
    ('    public static BlockPos containing(double x, double y, double z) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.containing:(DDD)Lnet/minecraft/core/BlockPos;");\n    }\n\n    public static BlockPos containing(Position pos) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.containing:(Lnet/minecraft/core/Position;)Lnet/minecraft/core/BlockPos;");\n    }',
     '    // Pumpkin divergence: vanilla bodies -- floor each coordinate into the block grid.\n    public static BlockPos containing(double x, double y, double z) {\n        return new BlockPos((int) Math.floor(x), (int) Math.floor(y), (int) Math.floor(z));\n    }\n\n    public static BlockPos containing(Position pos) {\n        return containing(pos.x(), pos.y(), pos.z());\n    }'),
])

edit('net/minecraft/world/phys/Vec3.java', [
    ('    public final double x() {\n        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.x:()D");\n    }\n\n    public final double y() {\n        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.y:()D");\n    }\n\n    public final double z() {\n        throw Unimplemented.forMember("net/minecraft/world/phys/Vec3.z:()D");\n    }',
     '    // Pumpkin divergence: vanilla bodies -- the record-style accessors over the fields.\n    public final double x() {\n        return x;\n    }\n\n    public final double y() {\n        return y;\n    }\n\n    public final double z() {\n        return z;\n    }'),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('    public Holder.Reference<Block> builtInRegistryHolder() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.builtInRegistryHolder:()Lnet/minecraft/core/Holder$Reference;");\n    }',
     '    // Pumpkin divergence: real-enough body. Mods ask a block\'s holder one question --\n    // does it wear this tag -- so the holder answers that from the datapack block tags\n    // and throws for everything else. The block names itself by its registered id, or\n    // by its vanilla template when it stands in for a vanilla block.\n    public Holder.Reference<Block> builtInRegistryHolder() {\n        Block self = this;\n        return new Holder.Reference<>(null, null, null, self) {\n            @Override\n            public boolean is(net.minecraft.tags.TagKey<Block> tag) {\n                String id = self.pumpkinRegisteredId() != null\n                        ? self.pumpkinRegisteredId()\n                        : "minecraft:" + self.pumpkinTemplate();\n                net.minecraft.resources.Identifier location = tag.location();\n                return dev.pumpkin.bridge.PumpkinTags.containsBlock(\n                        location.getNamespace() + ":" + location.getPath(), id);\n            }\n\n            @Override\n            public Block value() {\n                return self;\n            }\n        };\n    }'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.isValidBonemealTarget:(Lnet/minecraft/world/level/LevelReader;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z");\n    }',
     '    // Pumpkin divergence: vanilla body -- a crop takes bonemeal until it is grown.\n    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {\n        return !isMaxAge(state);\n    }'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.isBonemealSuccess:(Lnet/minecraft/world/level/Level;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)Z");\n    }',
     '    // Pumpkin divergence: vanilla body -- crops never fail a bonemeal.\n    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {\n        return true;\n    }'),
])

edit('net/minecraft/world/level/block/CropBlock.java', [
    ('    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/CropBlock.performBonemeal:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;)V");\n    }',
     '    // Pumpkin divergence: vanilla body -- two to five age steps, capped at maturity;\n    // Mth.nextInt(random, 2, 5) spelled out over the RandomSource the level hands us.\n    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {\n        int age = state.getValue(AGE) + random.nextInt(4) + 2;\n        level.setBlock(pos, getStateForAge(Math.min(age, getMaxAge())), 2);\n    }'),
])

edit('net/minecraft/core/BlockPos.java', [
    ('    public static Stream<BlockPos> betweenClosedStream(BlockPos a, BlockPos b) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.betweenClosedStream:(Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/BlockPos;)Ljava/util/stream/Stream;");\n    }',
     "    // Pumpkin divergence: real body -- every position in the closed box, y rising first\n    // so a column scan (the growth accelerator's use) finds the lowest match first.\n    public static Stream<BlockPos> betweenClosedStream(BlockPos a, BlockPos b) {\n        java.util.List<BlockPos> positions = new java.util.ArrayList<>();\n        for (int y = Math.min(a.getY(), b.getY()); y <= Math.max(a.getY(), b.getY()); y++) {\n            for (int x = Math.min(a.getX(), b.getX()); x <= Math.max(a.getX(), b.getX()); x++) {\n                for (int z = Math.min(a.getZ(), b.getZ()); z <= Math.max(a.getZ(), b.getZ()); z++) {\n                    positions.add(new BlockPos(x, y, z));\n                }\n            }\n        }\n        return positions.stream();\n    }"),
])

edit('net/minecraft/world/level/block/state/BlockBehaviour.java', [
    ('        public void randomTick(ServerLevel level, BlockPos pos, RandomSource random) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.randomTick:(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/core/BlockPos;Lnet/minecraft/util/RandomSource;)V");\n        }',
     '        // Pumpkin divergence: vanilla body in spirit -- dispatch to the owning block\'s\n        // randomTick. Reflection because the method is protected in another package;\n        // this is how a growth accelerator forces a tick on the crop above it.\n        public void randomTick(ServerLevel level, BlockPos pos, RandomSource random) {\n            try {\n                java.lang.reflect.Method method = null;\n                for (Class<?> type = getBlock().getClass(); type != null; type = type.getSuperclass()) {\n                    for (java.lang.reflect.Method candidate : type.getDeclaredMethods()) {\n                        if (candidate.getName().equals("randomTick")\n                                && candidate.getParameterCount() == 4) {\n                            method = candidate;\n                            break;\n                        }\n                    }\n                    if (method != null) {\n                        break;\n                    }\n                }\n                if (method == null) {\n                    return;\n                }\n                method.setAccessible(true);\n                method.invoke(getBlock(), this, level, pos, random);\n            } catch (java.lang.reflect.InvocationTargetException e) {\n                if (e.getCause() instanceof RuntimeException cause) {\n                    throw cause;\n                }\n                throw new IllegalStateException(e.getCause());\n            } catch (ReflectiveOperationException e) {\n                throw new IllegalStateException(e);\n            }\n        }'),
])


# ----------------------------------------------- Mekanism regen: dedupe and repair
# The Mekanism family widened the used set, so the generator now emits stubs for
# members this file used to hand-add, and pulled in classes whose supers need real
# constructor chains. Each edit below either deletes the newly generated duplicate
# (the hand-written body elsewhere in the file stands) or completes what the
# generator could not know.

edit('net/minecraft/resources/ResourceKey.java', [
    ('    public static <T> ResourceKey<Registry<T>> createRegistryKey(Identifier identifier) {\n        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.createRegistryKey:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/resources/ResourceKey;");\n    }\n\n',
     ''),
])

edit('net/minecraft/world/inventory/AbstractContainerMenu.java', [
    ('    public final int containerId = 0;\n\n', ''),
    ('    public void setCarried(ItemStack carried) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.setCarried:(Lnet/minecraft/world/item/ItemStack;)V");\n    }\n\n',
     ''),
    ('    public ItemStack getCarried() {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/AbstractContainerMenu.getCarried:()Lnet/minecraft/world/item/ItemStack;");\n    }\n\n',
     ''),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public boolean mayPickup(Player player) {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.mayPickup:(Lnet/minecraft/world/entity/player/Player;)Z");\n    }\n\n',
     ''),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/Block.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }\n\n',
     ''),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        default int registerBlock(String id, String template, Float destroyTime,\n                Float explosionResistance, boolean requiresTool) {\n            return registerBlock(id, template);\n        }',
     '        default int registerBlock(String id, String template, Float destroyTime,\n                Float explosionResistance, boolean requiresTool) {\n            return registerBlock(id, template);\n        }\n\n        // Pumpkin divergence: wider still -- the block\'s declared state properties, as\n        // "name:v|v|v;name:v|v". A block with properties gets one server-side state per\n        // combination, which is what lets a crop\'s age exist at all.\n        default int registerBlock(String id, String template, Float destroyTime,\n                Float explosionResistance, boolean requiresTool, String stateProperties) {\n            return registerBlock(id, template, destroyTime, explosionResistance, requiresTool);\n        }'),
])

edit('net/minecraft/nbt/NbtOps.java', [
    ('    private class NbtRecordBuilder extends AbstractStringBuilder<Tag, CompoundTag> {\n\n        protected NbtRecordBuilder() {\n        }',
     "    private class NbtRecordBuilder extends AbstractStringBuilder<Tag, CompoundTag> {\n\n        // Pumpkin divergence: DFU's AbstractStringBuilder has no no-arg constructor; it\n        // takes the ops it builds against, and the enclosing NbtOps is exactly that.\n        protected NbtRecordBuilder() {\n            super(NbtOps.this);\n        }"),
])

# DynamicOps.remove became load-bearing in the used set; NbtOps must declare it.
_p = os.path.join(ROOT, 'net/minecraft/nbt/NbtOps.java')
_s = PENDING.get(_p) or open(_p).read()
_anchor = '    private class NbtRecordBuilder'
assert _anchor in _s
_stubs = [
    ("Tag empty()", "empty:()Lnet/minecraft/nbt/Tag;"),
    ("<U> U convertTo(com.mojang.serialization.DynamicOps<U> ops, Tag input)",
     "convertTo:(Lcom/mojang/serialization/DynamicOps;Lnet/minecraft/nbt/Tag;)Ljava/lang/Object;"),
    ("com.mojang.serialization.DataResult<Number> getNumberValue(Tag input)",
     "getNumberValue:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;"),
    ("Tag createNumeric(Number value)", "createNumeric:(Ljava/lang/Number;)Lnet/minecraft/nbt/Tag;"),
    ("com.mojang.serialization.DataResult<String> getStringValue(Tag input)",
     "getStringValue:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;"),
    ("Tag createString(String value)", "createString:(Ljava/lang/String;)Lnet/minecraft/nbt/Tag;"),
    ("com.mojang.serialization.DataResult<Tag> mergeToList(Tag list, Tag value)",
     "mergeToList:(Lnet/minecraft/nbt/Tag;Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;"),
    ("com.mojang.serialization.DataResult<Tag> mergeToMap(Tag map, Tag key, Tag value)",
     "mergeToMap:(Lnet/minecraft/nbt/Tag;Lnet/minecraft/nbt/Tag;Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;"),
    ("com.mojang.serialization.DataResult<java.util.stream.Stream<com.mojang.datafixers.util.Pair<Tag, Tag>>> getMapValues(Tag input)",
     "getMapValues:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;"),
    ("Tag createMap(java.util.stream.Stream<com.mojang.datafixers.util.Pair<Tag, Tag>> entries)",
     "createMap:(Ljava/util/stream/Stream;)Lnet/minecraft/nbt/Tag;"),
    ("com.mojang.serialization.DataResult<java.util.stream.Stream<Tag>> getStream(Tag input)",
     "getStream:(Lnet/minecraft/nbt/Tag;)Lcom/mojang/serialization/DataResult;"),
    ("Tag createList(java.util.stream.Stream<Tag> input)",
     "createList:(Ljava/util/stream/Stream;)Lnet/minecraft/nbt/Tag;"),
    ("Tag remove(Tag input, String key)",
     "remove:(Lnet/minecraft/nbt/Tag;Ljava/lang/String;)Lnet/minecraft/nbt/Tag;"),
]
# DynamicOps' abstract surface: the generator emitted NbtOps without it (nothing in the
# used set called these on NbtOps directly), but javac needs the class to be complete.
# Throwing stubs, each naming its member -- real serialisation still routes elsewhere.
_member = ""
for _sig, _key in _stubs:
    _member += ("    public " + _sig + " {\n"
                + '        throw Unimplemented.forMember("net/minecraft/nbt/NbtOps.' + _key + '");\n'
                + "    }\n\n")
PENDING[_p] = _s.replace(_anchor, _member + _anchor, 1)

# CuboidFace is named in kept signatures but was never generated; a minimal class
# lets its siblings compile. Nothing constructs one server-side.
_face = os.path.join(ROOT, 'net/minecraft/client/resources/model/cuboid/CuboidFace.java')
PENDING[_face] = (
    'package net.minecraft.client.resources.model.cuboid;\n\n'
    '// Hand-restored by reconcile: named in kept signatures (CuboidModelElement.faces)\n'
    '// but outside the used set, so the generator never emits it. A model face is\n'
    '// client rendering data; nothing on the server reads one.\n'
    'public class CuboidFace {\n}\n')

edit('net/neoforged/neoforge/common/util/InsertableLinkedOpenCustomHashSet.java', [
    ('    public InsertableLinkedOpenCustomHashSet() {\n    }',
     "    // Pumpkin divergence: the fastutil super has no no-arg constructor; chain the\n    // strategies vanilla would. Identity strategy for the no-arg form, per NeoForge.\n    public InsertableLinkedOpenCustomHashSet() {\n        super(it.unimi.dsi.fastutil.Hash.DEFAULT_INITIAL_SIZE, it.unimi.dsi.fastutil.Hash.DEFAULT_LOAD_FACTOR,\n                new Hash.Strategy<T>() {\n                    public int hashCode(T o) {\n                        return java.util.Objects.hashCode(o);\n                    }\n\n                    public boolean equals(T a, T b) {\n                        return java.util.Objects.equals(a, b);\n                    }\n                });\n    }"),
    ('    public InsertableLinkedOpenCustomHashSet(Hash.Strategy<? super T> strategy) {\n    }',
     '    public InsertableLinkedOpenCustomHashSet(Hash.Strategy<? super T> strategy) {\n        super(strategy);\n    }'),
])

edit('net/neoforged/neoforge/transfer/ResourceHandler.java', [
    ('    static <T extends Resource> Class<ResourceHandler<T>> asClass() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/ResourceHandler.asClass:()Ljava/lang/Class;");\n    }',
     '    // Pumpkin divergence: vanilla body verbatim -- the generically-typed class literal\n    // mods hand to capability registration.\n    @SuppressWarnings("unchecked")\n    static <T extends Resource> Class<ResourceHandler<T>> asClass() {\n        return (Class<ResourceHandler<T>>) (Object) ResourceHandler.class;\n    }'),
])

edit('net/neoforged/neoforge/capabilities/BaseCapability.java', [
    ('    private final Identifier name = null;\n\n    protected BaseCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n    }\n\n    public final Identifier name() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BaseCapability.name:()Lnet/minecraft/resources/Identifier;");\n    }',
     '    // Pumpkin divergence: real fields -- a capability token is its identity, and mods\n    // compare and print these from the moment they are created.\n    private final Identifier name;\n\n    private final Class<T> pumpkinTypeClass;\n\n    protected BaseCapability(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n        this.name = name;\n        this.pumpkinTypeClass = typeClass;\n    }\n\n    public final Identifier name() {\n        return name;\n    }\n\n    public final Class<T> typeClass() {\n        return pumpkinTypeClass;\n    }'),
])

edit('net/neoforged/neoforge/capabilities/EntityCapability.java', [
    ('    public static <T, C extends Object> EntityCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/EntityCapability.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/EntityCapability;");\n    }',
     '    // Pumpkin divergence: real bodies -- interned by name, as NeoForge\'s registry does,\n    // so creating the same capability twice hands back the same token and identity\n    // comparisons hold. Queries against these tokens are a later subsystem; creating\n    // and carrying one is pure identity.\n    private static final java.util.concurrent.ConcurrentHashMap<Identifier, EntityCapability<?, ?>> PUMPKIN_INTERNED =\n            new java.util.concurrent.ConcurrentHashMap<>();\n\n    @SuppressWarnings("unchecked")\n    public static <T, C extends Object> EntityCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n        return (EntityCapability<T, C>) PUMPKIN_INTERNED.computeIfAbsent(name,\n                key -> new EntityCapability<>(key, typeClass, contextClass));\n    }'),
])

edit('net/neoforged/neoforge/capabilities/EntityCapability.java', [
    ('    public static <T> EntityCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/EntityCapability.createVoid:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/EntityCapability;");\n    }',
     '    public static <T> EntityCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        return create(name, typeClass, Void.class);\n    }'),
])

edit('net/neoforged/neoforge/capabilities/ItemCapability.java', [
    ('    public static <T, C extends Object> ItemCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/ItemCapability;");\n    }',
     '    // Pumpkin divergence: real bodies -- interned by name, as NeoForge\'s registry does,\n    // so creating the same capability twice hands back the same token and identity\n    // comparisons hold. Queries against these tokens are a later subsystem; creating\n    // and carrying one is pure identity.\n    private static final java.util.concurrent.ConcurrentHashMap<Identifier, ItemCapability<?, ?>> PUMPKIN_INTERNED =\n            new java.util.concurrent.ConcurrentHashMap<>();\n\n    @SuppressWarnings("unchecked")\n    public static <T, C extends Object> ItemCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n        return (ItemCapability<T, C>) PUMPKIN_INTERNED.computeIfAbsent(name,\n                key -> new ItemCapability<>(key, typeClass, contextClass));\n    }'),
])

edit('net/neoforged/neoforge/capabilities/ItemCapability.java', [
    ('    public static <T> ItemCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/ItemCapability.createVoid:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/ItemCapability;");\n    }',
     '    public static <T> ItemCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        return create(name, typeClass, Void.class);\n    }'),
])

edit('net/neoforged/neoforge/capabilities/BlockCapability.java', [
    ('    public static <T, C extends Object> BlockCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.create:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/BlockCapability;");\n    }',
     '    // Pumpkin divergence: real bodies -- interned by name, as NeoForge\'s registry does,\n    // so creating the same capability twice hands back the same token and identity\n    // comparisons hold. Queries against these tokens are a later subsystem; creating\n    // and carrying one is pure identity.\n    private static final java.util.concurrent.ConcurrentHashMap<Identifier, BlockCapability<?, ?>> PUMPKIN_INTERNED =\n            new java.util.concurrent.ConcurrentHashMap<>();\n\n    @SuppressWarnings("unchecked")\n    public static <T, C extends Object> BlockCapability<T, C> create(Identifier name, Class<T> typeClass, Class<C> contextClass) {\n        return (BlockCapability<T, C>) PUMPKIN_INTERNED.computeIfAbsent(name,\n                key -> new BlockCapability<>(key, typeClass, contextClass));\n    }'),
])

edit('net/neoforged/neoforge/capabilities/BlockCapability.java', [
    ('    public static <T> BlockCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.createVoid:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/BlockCapability;");\n    }',
     '    public static <T> BlockCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        return create(name, typeClass, Void.class);\n    }'),
])

edit('net/neoforged/neoforge/capabilities/BlockCapability.java', [
    ('    public static <T> BlockCapability<T, Direction> createSided(Identifier name, Class<T> typeClass) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/capabilities/BlockCapability.createSided:(Lnet/minecraft/resources/Identifier;Ljava/lang/Class;)Lnet/neoforged/neoforge/capabilities/BlockCapability;");\n    }',
     '    public static <T> BlockCapability<T, Direction> createSided(Identifier name, Class<T> typeClass) {\n        return create(name, typeClass, Direction.class);\n    }'),
])

edit('net/neoforged/neoforge/capabilities/BaseCapability.java', [
    ('    public BaseCapability() {\n    }',
     "    // Pumpkin divergence: the generator's convenience constructor has to satisfy the\n    // real final fields; a token built this way has no identity and says so if asked.\n    public BaseCapability() {\n        this(null, null, null);\n    }"),
])

edit('net/minecraft/resources/Identifier.java', [
    ('    public String toLanguageKey() {\n        throw Unimplemented.forMember("net/minecraft/resources/Identifier.toLanguageKey:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: vanilla bodies verbatim -- string arithmetic over the parts.\n    public String toLanguageKey() {\n        return getNamespace() + "." + getPath();\n    }'),
])

edit('net/minecraft/resources/Identifier.java', [
    ('    public String toLanguageKey(String prefix) {\n        throw Unimplemented.forMember("net/minecraft/resources/Identifier.toLanguageKey:(Ljava/lang/String;)Ljava/lang/String;");\n    }',
     '    public String toLanguageKey(String prefix) {\n        return prefix + "." + toLanguageKey();\n    }'),
])

edit('net/minecraft/resources/Identifier.java', [
    ('    public String toLanguageKey(String prefix, String suffix) {\n        throw Unimplemented.forMember("net/minecraft/resources/Identifier.toLanguageKey:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;");\n    }',
     '    public String toLanguageKey(String prefix, String suffix) {\n        return prefix + "." + toLanguageKey() + "." + suffix;\n    }'),
])

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        public Builder translation(String translationKey) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.translation:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");\n        }',
     '        // Pumpkin divergence: real body. A translation key decorates the config screen,\n        // which a headless server never draws; accepted and dropped, chain returns this.\n        public Builder translation(String translationKey) {\n            return this;\n        }'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    private static ResourceKey<EntityType<?>> create(String name) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypeIds.create:(Ljava/lang/String;)Lnet/minecraft/resources/ResourceKey;");\n    }',
     '    // Pumpkin divergence: vanilla body -- a key under the entity_type registry.\n    private static ResourceKey<EntityType<?>> create(String name) {\n        return ResourceKey.create(net.minecraft.core.registries.Registries.ENTITY_TYPE,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", name));\n    }'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    public static final ResourceKey<EntityType<?>> BOGGED = null;',
     '    public static final ResourceKey<EntityType<?>> BOGGED = create("bogged");'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    public static final ResourceKey<EntityType<?>> CREEPER = null;',
     '    public static final ResourceKey<EntityType<?>> CREEPER = create("creeper");'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    public static final ResourceKey<EntityType<?>> ENDERMAN = null;',
     '    public static final ResourceKey<EntityType<?>> ENDERMAN = create("enderman");'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    public static final ResourceKey<EntityType<?>> PARCHED = null;',
     '    public static final ResourceKey<EntityType<?>> PARCHED = create("parched");'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    public static final ResourceKey<EntityType<?>> SKELETON = null;',
     '    public static final ResourceKey<EntityType<?>> SKELETON = create("skeleton");'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    public static final ResourceKey<EntityType<?>> STRAY = null;',
     '    public static final ResourceKey<EntityType<?>> STRAY = create("stray");'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    public static final ResourceKey<EntityType<?>> WITHER_SKELETON = null;',
     '    public static final ResourceKey<EntityType<?>> WITHER_SKELETON = create("wither_skeleton");'),
])

edit('net/minecraft/world/entity/EntityTypeIds.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/world/entity/EntityTypeIds");\n        }\n    }',
     '    // Pumpkin divergence: no throwing initializer -- every key above is real.'),
])

edit('net/minecraft/resources/Identifier.java', [
    ('    public Identifier withPrefix(String prefix) {\n        throw Unimplemented.forMember("net/minecraft/resources/Identifier.withPrefix:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");\n    }',
     '    // Pumpkin divergence: real body, mirroring withSuffix.\n    public Identifier withPrefix(String prefix) {\n        return new Identifier(pumpkinNamespace, prefix + pumpkinPath);\n    }'),
])

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        public LongValue defineInRange(String path, long defaultValue, long min, long max) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.defineInRange:(Ljava/lang/String;JJJ)Lnet/neoforged/neoforge/common/ModConfigSpec$LongValue;");\n        }',
     '        // Pumpkin divergence: real body. See the int overload -- the value answers the\n        // default the mod declared, and nothing reads a file behind it yet.\n        public LongValue defineInRange(String path, long defaultValue, long min, long max) {\n            return new LongValue(defaultValue);\n        }'),
])

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        public Builder gameRestart() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$Builder.gameRestart:()Lnet/neoforged/neoforge/common/ModConfigSpec$Builder;");\n        }',
     '        // Pumpkin divergence: real body. Restart metadata for a config screen; accepted\n        // and dropped, chain returns this.\n        public Builder gameRestart() {\n            return this;\n        }'),
])

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        LongValue(Builder parent, List<String> path, Supplier<Long> defaultSupplier) {\n        }',
     '        // Pumpkin divergence: real body, mirroring IntValue.\n        LongValue(Long defaultValue) {\n            super(defaultValue);\n        }\n\n        LongValue(Builder parent, List<String> path, Supplier<Long> defaultSupplier) {\n        }'),
])

edit('net/minecraft/util/ByIdMap.java', [
    ('    public static <T> IntFunction<T> continuous(ToIntFunction<T> idGetter, T[] values, ByIdMap.OutOfBoundsStrategy strategy) {\n        throw Unimplemented.forMember("net/minecraft/util/ByIdMap.continuous:(Ljava/util/function/ToIntFunction;[Ljava/lang/Object;Lnet/minecraft/util/ByIdMap$OutOfBoundsStrategy;)Ljava/util/function/IntFunction;");\n    }',
     '    // Pumpkin divergence: vanilla logic, helpers inlined -- sort the values by their\n    // declared ids, then answer lookups per the out-of-bounds strategy.\n    public static <T> IntFunction<T> continuous(ToIntFunction<T> idGetter, T[] values, ByIdMap.OutOfBoundsStrategy strategy) {\n        T[] sorted = values.clone();\n        for (T value : values) {\n            int id = idGetter.applyAsInt(value);\n            if (id < 0 || id >= sorted.length) {\n                throw new IllegalArgumentException("id " + id + " out of a continuous range of " + sorted.length);\n            }\n            sorted[id] = value;\n        }\n        final int length = sorted.length;\n        return switch (strategy) {\n            case ZERO -> id -> id >= 0 && id < length ? sorted[id] : sorted[0];\n            case WRAP -> id -> sorted[((id % length) + length) % length];\n            case CLAMP -> id -> sorted[Math.clamp(id, 0, length - 1)];\n        };\n    }'),
])

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        EnumValue(Builder parent, List<String> path, Supplier<T> defaultSupplier, EnumGetMethod converter, Class<T> clazz) {\n        }',
     '        // Pumpkin divergence: real body, mirroring IntValue.\n        EnumValue(T defaultValue) {\n            super(defaultValue);\n        }\n\n        EnumValue(Builder parent, List<String> path, Supplier<T> defaultSupplier, EnumGetMethod converter, Class<T> clazz) {\n        }'),
])


# -------------------------------------- ModConfigSpec.Builder sweep (Mekanism)
# Mekanism declares hundreds of config values across every define* overload. Each value
# answers the default the mod declared -- the answer NeoForge gives when no file
# overrides it -- and screen-metadata chains return the builder. Programmatic, because
# the overload set is wide and uniform; anything the rules cannot shape keeps its
# throwing stub. Line-based (the head is everything before the parameter list) because
# generic declarations like <V extends Enum<V>> defeat a tidy regex.
_p = os.path.join(ROOT, "net/neoforged/neoforge/common/ModConfigSpec.java")
_s = PENDING.get(_p) or open(_p).read()
_pattern = re.compile(
    r"        public (?P<head>[^\n(]+)\((?P<params>[^)]*)\) \{\n"
    r"            throw Unimplemented\.forMember\(\"net/neoforged/neoforge/common/ModConfigSpec\$Builder\.[^\n]+\n        \}")
def _body_for(ret, params):
    if ret == "Builder":
        return "            return this;"
    args = [a.strip() for a in params.split(",") if a.strip()]
    names = [a.split()[-1] for a in args]
    types = [" ".join(a.split()[:-1]) for a in args]
    default_supplier = next((n for t2, n in zip(types, names)
                             if t2.startswith("Supplier") and "default" in n.lower()), None)
    default_value = next((n for t2, n in zip(types, names)
                          if not t2.startswith("Supplier") and "default" in n.lower()), None)
    value = (default_supplier + ".get()" if default_supplier
             else (default_value if default_value else (names[1] if len(names) >= 2 else None)))
    if value is None:
        return None
    if ret.startswith("EnumValue"):
        return "            return new EnumValue<>(%s);" % value
    if ret.startswith("ConfigValue"):
        # a supplier default stays a supplier -- evaluating it eagerly runs mod code
        # against a config object that is still mid-construction
        if value.endswith(".get()"):
            value = value[:-len(".get()")]
        return "            return new ConfigValue<>(%s);" % value
    if ret in ("IntValue", "LongValue", "DoubleValue", "BooleanValue"):
        return "            return new %s(%s);" % (ret, value)
    return None
def _replace(m):
    head = m.group("head").strip()
    # head = [generic decl] returnType name; the return type is the token before the
    # name, and neither of those two contains a space.
    _tokens = head.split(" ")
    ret = _tokens[-2] if len(_tokens) >= 2 else _tokens[0]
    body = _body_for(ret, m.group("params"))
    if body is None:
        return m.group(0)
    return ("        // Pumpkin divergence: real body -- the declared default answers.\n"
            "        public " + head + "(" + m.group("params") + ") {\n" + body + "\n        }")
PENDING[_p] = _pattern.sub(_replace, _s)

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        private T pumpkinDefault;\n\n        ConfigValue(T defaultValue) {\n            this.pumpkinDefault = defaultValue;\n        }\n\n        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {\n            this(defaultSupplier.get());\n        }',
     "        // Lazy on purpose: Mekanism's default suppliers read *other* config values, and\n        // evaluating them while the config object is still mid-construction NPEs inside\n        // the mod. NeoForge defers exactly the same way.\n        private Supplier<T> pumpkinDefault;\n\n        ConfigValue(T defaultValue) {\n            this.pumpkinDefault = () -> defaultValue;\n        }\n\n        ConfigValue(Supplier<T> defaultSupplier) {\n            this.pumpkinDefault = defaultSupplier;\n        }\n\n        ConfigValue(Builder parent, List<String> path, Supplier<T> defaultSupplier) {\n            this(defaultSupplier);\n        }"),
])

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        public T get() {\n            return pumpkinDefault;\n        }',
     '        public T get() {\n            return pumpkinDefault.get();\n        }'),
])

edit('net/minecraft/resources/Identifier.java', [
    ('    public static Identifier withDefaultNamespace(String path) {\n        throw Unimplemented.forMember("net/minecraft/resources/Identifier.withDefaultNamespace:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");\n    }',
     '    // Pumpkin divergence: vanilla body -- the minecraft namespace.\n    public static Identifier withDefaultNamespace(String path) {\n        return fromNamespaceAndPath("minecraft", path);\n    }'),
])


# ---------------------------------------- ByteBufCodecs sweep (Mekanism)
# Mekanism builds hundreds of stream codecs at class-initialisation. A stream codec
# only matters when a packet is actually encoded, and Pumpkin speaks the protocol from
# the Rust side -- so every factory answers an inert codec that throws its member key
# on first real use, and construction-time composition succeeds.
_p = os.path.join(ROOT, "net/minecraft/network/codec/ByteBufCodecs.java")
_s = PENDING.get(_p) or open(_p).read()
_pattern = re.compile(
    r"    static (?P<head>[^\n(]*?StreamCodec<[^\n(]*) (?P<name>\w+)\((?P<params>[^)]*)\) \{\n"
    r"        throw Unimplemented\.forMember\(\"(?P<key>[^\"]+)\"\);\n    \}")
def _bb_replace(m):
    return ("    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.\n"
            "    static " + m.group("head") + " " + m.group("name") + "(" + m.group("params") + ") {\n"
            "        return dev.pumpkin.shim.Stubs.of(StreamCodec.class, \"" + m.group("key") + "\");\n"
            "    }")
PENDING[_p] = _pattern.sub(_bb_replace, _s)

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    public <I extends T> DeferredHolder<T, I> register(final String name, final Function<Identifier, ? extends I> func) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.register:(Ljava/lang/String;Ljava/util/function/Function;)Lnet/neoforged/neoforge/registries/DeferredHolder;");\n    }',
     '    // Pumpkin divergence: real body -- the function form takes the id it will be\n    // registered under; hand it the id and fall into the supplier path.\n    public <I extends T> DeferredHolder<T, I> register(final String name, final Function<Identifier, ? extends I> func) {\n        return register(name, () -> func.apply(Identifier.fromNamespaceAndPath(pumpkinNamespace, name)));\n    }'),
])

edit('net/neoforged/neoforge/registries/DeferredHolder.java', [
    ('    private final Supplier<T> pumpkinFactory;',
     "    // Not final: a holder built through createHolder -- the factory-method subclasses\n    // like Mekanism's override -- gets its supplier attached right after construction.\n    private Supplier<T> pumpkinFactory;\n\n    void pumpkinSetFactory(Supplier<T> factory) {\n        this.pumpkinFactory = factory;\n    }"),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    public <I extends T> DeferredHolder<T, I> register(final String name, final Supplier<? extends I> sup) {\n        DeferredHolder<T, I> holder =\n                new DeferredHolder<>(Identifier.fromNamespaceAndPath(pumpkinNamespace, name), sup::get);\n        pumpkinPending.add(holder);',
     '    public <I extends T> DeferredHolder<T, I> register(final String name, final Supplier<? extends I> sup) {\n        // Through createHolder, not a constructor: subclasses override it to hand back\n        // their own holder type, and Mekanism casts every registration to its own.\n        DeferredHolder<T, I> holder =\n                createHolder(pumpkinRegistryKey, Identifier.fromNamespaceAndPath(pumpkinNamespace, name));\n        holder.pumpkinSetFactory(sup::get);\n        pumpkinPending.add(holder);'),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    protected <I extends T> DeferredHolder<T, I> createHolder(ResourceKey<? extends Registry<T>> registryKey, Identifier key) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.createHolder:(Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/resources/Identifier;)Lnet/neoforged/neoforge/registries/DeferredHolder;");\n    }',
     '    // Pumpkin divergence: real body -- the factory method subclasses override.\n    protected <I extends T> DeferredHolder<T, I> createHolder(ResourceKey<? extends Registry<T>> registryKey, Identifier key) {\n        return new DeferredHolder<>(key, null);\n    }'),
])

edit('net/neoforged/neoforge/common/NeoForge.java', [
    ('    public static final IEventBus EVENT_BUS = null;\n\n    public NeoForge() {\n    }\n\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/NeoForge");\n        }\n    }',
     "    // Pumpkin divergence: a real bus. Subscriptions land and are kept; the game events\n    // NeoForge would post here fire only as Pumpkin grows senders for them, so a\n    // listener may wait forever -- but the mod's registration itself succeeds and is\n    // inspectable, where a throwing holder stopped construction cold.\n    public static final IEventBus EVENT_BUS = new dev.pumpkin.shim.PumpkinEventBus();\n\n    public NeoForge() {\n    }"),
])


# ------------------------------------------------- ARGB sweep (Mekanism)
# Pure channel arithmetic; every body is vanilla's own expression, written against the
# stub's actual parameter names (they vary: color, lhs/rhs, p0/p1).
_p = os.path.join(ROOT, "net/minecraft/util/ARGB.java")
_s = PENDING.get(_p) or open(_p).read()
_ARGB = {'alpha:(I)I': '        return {0} >>> 24;', 'red:(I)I': '        return {0} >> 16 & 0xFF;', 'green:(I)I': '        return {0} >> 8 & 0xFF;', 'blue:(I)I': '        return {0} & 0xFF;', 'opaque:(I)I': '        return {0} | 0xFF000000;', 'transparent:(I)I': '        return {0} & 0xFFFFFF;', 'white:(F)I': '        return as8BitChannel({0}) << 24 | 0xFFFFFF;', 'colorFromFloat:(FFFF)I': '        return color(as8BitChannel({0}), as8BitChannel({1}), as8BitChannel({2}), as8BitChannel({3}));', 'as8BitChannel:(F)I': '        return (int) Math.floor({0} * 255.0F);', 'multiply:(II)I': '        return color((alpha({0}) * alpha({1})) / 255, (red({0}) * red({1})) / 255, (green({0}) * green({1})) / 255, (blue({0}) * blue({1})) / 255);', 'srgbLerp:(FII)I': '        return color((int) (alpha({1}) + {0} * (alpha({2}) - alpha({1}))), (int) (red({1}) + {0} * (red({2}) - red({1}))), (int) (green({1}) + {0} * (green({2}) - green({1}))), (int) (blue({1}) + {0} * (blue({2}) - blue({1}))));', 'redFloat:(I)F': '        return red({0}) / 255.0F;', 'greenFloat:(I)F': '        return green({0}) / 255.0F;', 'blueFloat:(I)F': '        return blue({0}) / 255.0F;', 'alphaFloat:(I)F': '        return alpha({0}) / 255.0F;'}
_pattern = re.compile(
    r"    public static (?P<head>[\w<>\[\] ]+) (?P<name>\w+)\((?P<params>[^)]*)\) \{\n"
    r"        throw Unimplemented\.forMember\(\"net/minecraft/util/ARGB\.(?P<key>[^\"]+)\"\);\n    \}")
def _argb_replace(m):
    t = _ARGB.get(m.group("key"))
    if t is None:
        return m.group(0)
    names = [a.strip().split()[-1] for a in m.group("params").split(",") if a.strip()]
    body = t.format(*names)
    return ("    // Pumpkin divergence: vanilla arithmetic.\n"
            "    public static " + m.group("head") + " " + m.group("name") + "(" + m.group("params") + ") {\n"
            + body + "\n    }")
PENDING[_p] = _pattern.sub(_argb_replace, _s)

edit('net/minecraft/network/chat/TextColor.java', [
    ('    private TextColor(int value, String name) {\n    }\n\n    private TextColor(int value) {\n    }\n\n    public int getValue() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.getValue:()I");\n    }',
     '    // Pumpkin divergence: a color really carries its value.\n    private int pumpkinValue;\n\n    private TextColor(int value, String name) {\n        this.pumpkinValue = value;\n    }\n\n    private TextColor(int value) {\n        this.pumpkinValue = value;\n    }\n\n    public int getValue() {\n        return pumpkinValue;\n    }'),
])

edit('net/minecraft/network/chat/TextColor.java', [
    ('    public static TextColor fromRgb(int rgb) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.fromRgb:(I)Lnet/minecraft/network/chat/TextColor;");\n    }',
     '    // Pumpkin divergence: vanilla body -- wrap the rgb.\n    public static TextColor fromRgb(int rgb) {\n        return new TextColor(rgb);\n    }'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<EntityType<?>> TELEPORTING_NOT_SUPPORTED = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<EntityType<?>> TELEPORTING_NOT_SUPPORTED = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.ENTITY_TYPE,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "teleporting_not_supported"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<Item> GEMS_LAPIS = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<Item> GEMS_LAPIS = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.ITEM,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "gems/lapis"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<Item> ORES = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<Item> ORES = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.ITEM,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<Item> ORES_COPPER = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<Item> ORES_COPPER = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.ITEM,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores/copper"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<Item> ORES_GOLD = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<Item> ORES_GOLD = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.ITEM,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores/gold"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<Item> ORES_IRON = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<Item> ORES_IRON = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.ITEM,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "ores/iron"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<Fluid> GASEOUS = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<Fluid> GASEOUS = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.FLUID,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "gaseous"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<Fluid> HIDDEN_FROM_RECIPE_VIEWERS = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<Fluid> HIDDEN_FROM_RECIPE_VIEWERS = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.FLUID,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("c", "hidden_from_recipe_viewers"));'),
])

edit('net/neoforged/neoforge/common/Tags.java', [
    ('public static final TagKey<DamageType> IS_TECHNICAL = null;',
     '// Pumpkin divergence: the real key, name read from NeoForge\'s own source.\n        public static final TagKey<DamageType> IS_TECHNICAL = net.minecraft.tags.TagKey.create(\n                net.minecraft.core.registries.Registries.DAMAGE_TYPE,\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "is_technical"));'),
])

edit('net/minecraft/world/item/equipment/ArmorType.java', [
    ('    public String getName() {\n        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getName:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: vanilla body -- the lowercase constant name.\n    public String getName() {\n        return name().toLowerCase(java.util.Locale.ROOT);\n    }'),
])

edit('net/minecraft/world/item/equipment/ArmorType.java', [
    ('    public String getSerializedName() {\n        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorType.getSerializedName:()Ljava/lang/String;");\n    }',
     '    public String getSerializedName() {\n        return getName();\n    }'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('public static final Codec<Integer> RGB_COLOR_CODEC = null;',
     '// Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.\n    public static final Codec<Integer> RGB_COLOR_CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.RGB_COLOR_CODEC");'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('public static final Codec<Integer> ARGB_COLOR_CODEC = null;',
     '// Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.\n    public static final Codec<Integer> ARGB_COLOR_CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.ARGB_COLOR_CODEC");'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('public static final Codec<Integer> STRING_ARGB_COLOR = null;',
     '// Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.\n    public static final Codec<Integer> STRING_ARGB_COLOR =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.STRING_ARGB_COLOR");'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('public static final Codec<Integer> NON_NEGATIVE_INT = null;',
     '// Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.\n    public static final Codec<Integer> NON_NEGATIVE_INT =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.NON_NEGATIVE_INT");'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('public static final Codec<Integer> POSITIVE_INT = null;',
     '// Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.\n    public static final Codec<Integer> POSITIVE_INT =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.POSITIVE_INT");'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('public static final Codec<Long> NON_NEGATIVE_LONG = null;',
     '// Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.\n    public static final Codec<Long> NON_NEGATIVE_LONG =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.NON_NEGATIVE_LONG");'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('public static final Codec<String> NON_EMPTY_STRING = null;',
     '// Pumpkin divergence: inert codec -- composes at class-init, throws its name on use.\n    public static final Codec<String> NON_EMPTY_STRING =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/util/ExtraCodecs.NON_EMPTY_STRING");'),
])

edit('net/minecraft/util/ExtraCodecs.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/util/ExtraCodecs");\n        }\n    }',
     '    // Pumpkin divergence: no throwing initializer -- every field answers inertly.'),
])

edit('net/neoforged/neoforge/registries/DeferredHolder.java', [
    ('    public T value() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.value:()Lnet/neoforged/neoforge/registries/R;");\n    }',
     "    // Pumpkin divergence: real body -- a holder's value is what it resolves to.\n    public T value() {\n        return get();\n    }"),
])


# Tags nested clinits: the classes whose constants are real above lose their throw.
_p = os.path.join(ROOT, "net/neoforged/neoforge/common/Tags.java")
_s = PENDING.get(_p) or open(_p).read()
_clinit = re.compile(r"        static \{\n            if \(true\) \{\n                throw Unimplemented\.forMember\(\"net/neoforged/neoforge/common/Tags\$(?P<cls>\w+)\"\);\n            \}\n        \}\n")
def _tags_drop(m):
    if m.group("cls") in ("EntityTypes", "Items", "Fluids", "DamageTypes"):
        return "        // Pumpkin divergence: no throwing initializer -- the keys above are real.\n"
    return m.group(0)
PENDING[_p] = _clinit.sub(_tags_drop, _s)

edit('net/minecraft/world/entity/ai/attributes/Attribute.java', [
    ('    protected Attribute(String descriptionId, double defaultValue) {\n    }\n\n    public double getDefaultValue() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/ai/attributes/Attribute.getDefaultValue:()D");\n    }',
     '    // Pumpkin divergence: real fields -- an attribute is its id and default.\n    private String pumpkinDescriptionId;\n\n    private double pumpkinDefaultValue;\n\n    protected Attribute(String descriptionId, double defaultValue) {\n        this.pumpkinDescriptionId = descriptionId;\n        this.pumpkinDefaultValue = defaultValue;\n    }\n\n    public double getDefaultValue() {\n        return pumpkinDefaultValue;\n    }'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> ARMOR = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '// Pumpkin divergence: the holder answers value() with a real attribute --\n    // vanilla\'s own id and default, read from NeoForge\'s source. Mekanism sizes\n    // its gear config around these defaults at class-initialisation.\n    @SuppressWarnings("unchecked")\n    public static final Holder<Attribute> ARMOR = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.armor", 0.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> ARMOR_TOUGHNESS = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> ARMOR_TOUGHNESS = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.armor_toughness", 0.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> ATTACK_DAMAGE = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> ATTACK_DAMAGE = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.attack_damage", 2.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> ATTACK_SPEED = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> ATTACK_SPEED = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.attack_speed", 4.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> BLOCK_INTERACTION_RANGE = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> BLOCK_INTERACTION_RANGE = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.block_interaction_range", 4.5)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> FALL_DAMAGE_MULTIPLIER = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> FALL_DAMAGE_MULTIPLIER = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.fall_damage_multiplier", 1.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> KNOCKBACK_RESISTANCE = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> KNOCKBACK_RESISTANCE = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.knockback_resistance", 0.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> MAX_HEALTH = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> MAX_HEALTH = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.max_health", 20.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> MOVEMENT_EFFICIENCY = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> MOVEMENT_EFFICIENCY = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.movement_efficiency", 0.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> MOVEMENT_SPEED = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> MOVEMENT_SPEED = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.movement_speed", 0.7)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> SAFE_FALL_DISTANCE = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> SAFE_FALL_DISTANCE = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.safe_fall_distance", 3.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> SNEAKING_SPEED = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> SNEAKING_SPEED = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.sneaking_speed", 0.3)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> STEP_HEIGHT = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> STEP_HEIGHT = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.step_height", 0.6)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> SUBMERGED_MINING_SPEED = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> SUBMERGED_MINING_SPEED = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.submerged_mining_speed", 0.2)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public static final Holder<Attribute> WATER_MOVEMENT_EFFICIENCY = Stubs.of(Holder.class, "net/minecraft/core/Holder");',
     '@SuppressWarnings("unchecked")\n    public static final Holder<Attribute> WATER_MOVEMENT_EFFICIENCY = Stubs.of(Holder.class,\n            "net/minecraft/core/Holder", java.util.Map.of("value",\n                    new PumpkinAttribute("attribute.name.water_movement_efficiency", 0.0)));'),
])

edit('net/minecraft/world/entity/ai/attributes/Attributes.java', [
    ('public class Attributes {',
     "public class Attributes {\n\n    // Attribute's constructor is protected; this is the smallest door to it.\n    private static final class PumpkinAttribute extends Attribute {\n        PumpkinAttribute(String descriptionId, double defaultValue) {\n            super(descriptionId, defaultValue);\n        }\n    }\n"),
])

edit('net/minecraft/world/item/DyeColor.java', [
    ('    public String getName() {\n        throw Unimplemented.forMember("net/minecraft/world/item/DyeColor.getName:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: vanilla body -- the lowercase constant name.\n    public String getName() {\n        return name().toLowerCase(java.util.Locale.ROOT);\n    }'),
])


# ExtraCodecs statics: every Codec-returning factory answers inertly.
_p = os.path.join(ROOT, "net/minecraft/util/ExtraCodecs.java")
_s = PENDING.get(_p) or open(_p).read()
_pattern = re.compile(
    r"    public static (?P<head>(?:<[^\n{]*?> )?Codec<[^\n(]*) (?P<name>\w+)\((?P<params>[^)]*)\) \{\n"
    r"        throw Unimplemented\.forMember\(\"(?P<key>[^\"]+)\"\);\n    \}")
def _ec_replace(m):
    return ("    // Pumpkin divergence: inert codec -- throws its key on first use.\n"
            "    public static " + m.group("head") + " " + m.group("name") + "(" + m.group("params") + ") {\n"
            "        return dev.pumpkin.shim.Stubs.throwingCodec(\"" + m.group("key") + "\");\n    }")
PENDING[_p] = _pattern.sub(_ec_replace, _s)

# DyeColor: vanilla's dye-to-map-color table, filtered to the constants the pruned
# enum actually declares (the switch must stay exhaustive over what exists).
_p = os.path.join(ROOT, "net/minecraft/world/item/DyeColor.java")
_s = PENDING.get(_p) or open(_p).read()
_dye_vanilla = open(os.path.join(os.path.dirname(ROOT),
    "../../../NeoForge/projects/neoforge/src/main/java/net/minecraft/world/item/DyeColor.java")).read()
_dye_map = re.findall(r"([A-Z_]+)\(\d+, \"[a-z_]+\", \d+, MapColor\.([A-Z_0-9]+),", _dye_vanilla)
_enum = re.search(r"public enum DyeColor[^{]*\{\n\n?    ([A-Z_, \n]+);", _s)
_present = set(re.findall(r"[A-Z_]+", _enum.group(1)))
_cases = "\n".join("            case %s -> net.minecraft.world.level.material.MapColor.%s;" % (d, mc)
                   for d, mc in _dye_map if d in _present)
_old = re.search(r"    public MapColor getMapColor\(\) \{\n        throw Unimplemented[^\n]+\n    \}", _s)
if _old:
    _s = _s.replace(_old.group(0),
        "    // Pumpkin divergence: vanilla's own dye-to-map-color table, over the constants\n"
        "    // this shim's pruned enum carries.\n"
        "    public MapColor getMapColor() {\n        return switch (this) {\n" + _cases + "\n        };\n    }", 1)
PENDING[_p] = _s

edit('net/minecraft/world/item/equipment/ArmorMaterials.java', [
    ('    ArmorMaterial NETHERITE = null;',
     "    // Pumpkin divergence: vanilla's numbers (durability 37, defense 3/6/8/3/19,\n    // enchant 15, toughness 3, knockback resistance 0.1). The sound, repair tag and\n    // asset are identity-bearing references Mekanism's stat reads never touch.\n    ArmorMaterial NETHERITE = new ArmorMaterial(37,\n            java.util.Map.of(ArmorType.BOOTS, 3, ArmorType.LEGGINGS, 6,\n                    ArmorType.CHESTPLATE, 8, ArmorType.HELMET, 3, ArmorType.BODY, 19),\n            15, null, 3.0F, 0.1F, null, null);"),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    public String getNamespace() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.getNamespace:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: real body.\n    public String getNamespace() {\n        return pumpkinNamespace;\n    }'),
])


# MapColor: vanilla (id, col) pairs, read from the decompiled source at reconcile time.
_p = os.path.join(ROOT, "net/minecraft/world/level/material/MapColor.java")
_s = PENDING.get(_p) or open(_p).read()
_vanilla = open(os.path.join(os.path.dirname(ROOT),
    "../../../NeoForge/projects/neoforge/src/main/java/net/minecraft/world/level/material/MapColor.java")).read()
_pairs = dict(re.findall(r"MapColor ([A-Z_0-9]+) = new MapColor\((\d+, -?\d+)\)", _vanilla))
_m = re.search(r"    (?:private |public )?MapColor\(int [\w]+, int [\w]+\) \{\n    \}", _s)
_ctor = """    // Pumpkin divergence: a map color really carries its packed rgb, and the id-indexed
    // table vanilla keeps (NeoForge's access transformer makes it public, and Mekanism
    // reads it directly) fills as the constants construct.
    public static final MapColor[] MATERIAL_COLORS = new MapColor[64];

    public final int col;

    public final int id;

    private MapColor(int id, int col) {
        this.id = id;
        this.col = col;
        MATERIAL_COLORS[id] = this;
    }"""
_s = _s.replace("    public final int col = 0;\n\n", "", 1)
if _m:
    _s = _s.replace(_m.group(0), "", 1)
# the table must initialise before any constant constructs into it -- first thing in
# the class, ahead of the appended constants too.
_s = _s.replace("public class MapColor {", "public class MapColor {\n\n" + _ctor, 1)
for _name, _args in _pairs.items():
    _target = "public static final MapColor %s = null;" % _name
    if _target in _s:
        _s = _s.replace(_target,
                        "public static final MapColor %s = new MapColor(%s);" % (_name, _args), 1)
    elif ("MapColor %s " % _name) not in _s:
        # pruned away entirely, but the dye table below may name it: append after the
        # id table so static initialisation order stays correct.
        _s = _s.replace("        MATERIAL_COLORS[id] = this;\n    }",
                        "        MATERIAL_COLORS[id] = this;\n    }\n\n    public static final MapColor %s = new MapColor(%s);"
                        % (_name, _args), 1)
_s = re.sub(r"    private static final MapColor\[\] MATERIAL_COLORS = null;\n\n?", "", _s)
_s = re.sub(r"    static \{\n        if \(true\) \{\n            throw Unimplemented\.forMember\(\"net/minecraft/world/level/material/MapColor\"\);\n        \}\n    \}\n",
            "    // Pumpkin divergence: no throwing initializer -- the constants are vanilla's values.\n", _s)
_s = _s.replace("    public MapColor() {\n    }", "    public MapColor() {\n        this(0, 0);\n    }")
PENDING[_p] = _s

edit('net/minecraft/world/item/Item.java', [
    ('    // Pumpkin divergence: no vanilla counterpart. Pumpkin registers an item by copying an\n    // existing one\'s definition, and "stone" is the deliberate default template -- the\n    // same choice Block\'s registration path makes. It is a stand-in, not a guess at the\n    // mod\'s intent: stack size and components come from stone until item behaviour gets\n    // its own slice.\n    public String pumpkinTemplate() {\n        return "stone";\n    }',
     '    // Pumpkin divergence: no vanilla counterpart. Pumpkin registers an item by copying an\n    // existing one\'s definition, and "stone" is the deliberate default template -- the\n    // same choice Block\'s registration path makes. A vanilla stand-in from Items sets its\n    // own name here, so identity checks and template copies see the real item.\n    public String pumpkinVanillaName;\n\n    public String pumpkinTemplate() {\n        return pumpkinVanillaName != null ? pumpkinVanillaName : "stone";\n    }'),
])

edit('net/neoforged/neoforge/common/ItemAbility.java', [
    ('    public static ItemAbility get(String name) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/ItemAbility.get:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/ItemAbility;");\n    }\n\n    public String name() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/ItemAbility.name:()Ljava/lang/String;");\n    }\n\n    public String toString() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/ItemAbility.toString:()Ljava/lang/String;");\n    }\n\n    private ItemAbility(String name) {\n    }',
     "    // Pumpkin divergence: real, interned by name -- NeoForge's own contract, and mods\n    // compare abilities by identity.\n    private static final java.util.concurrent.ConcurrentHashMap<String, ItemAbility> PUMPKIN_INTERNED =\n            new java.util.concurrent.ConcurrentHashMap<>();\n\n    public static ItemAbility get(String name) {\n        return PUMPKIN_INTERNED.computeIfAbsent(name, ItemAbility::new);\n    }\n\n    public String name() {\n        return pumpkinName;\n    }\n\n    public String toString() {\n        return pumpkinName;\n    }\n\n    private String pumpkinName;\n\n    private ItemAbility(String name) {\n        this.pumpkinName = name;\n    }"),
])

edit('net/neoforged/neoforge/common/NeoForgeMod.java', [
    ('    public static void enableMilkFluid() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/NeoForgeMod.enableMilkFluid:()V");\n    }',
     '    // Pumpkin divergence: real body -- the flag flips, as in NeoForge. The MILK holder\n    // itself stays null until something actually reads it, and that read will say so.\n    public static void enableMilkFluid() {\n        enableMilkFluid = true;\n    }'),
])

edit('net/neoforged/neoforge/registries/DeferredHolder.java', [
    ('    // Pumpkin divergence: real body.\n    protected DeferredHolder(ResourceKey<R> key) {\n        this(key.identifier(), null);\n    }',
     '    // Pumpkin divergence: real body.\n    private ResourceKey<R> pumpkinKey;\n\n    protected DeferredHolder(ResourceKey<R> key) {\n        this(key.identifier(), null);\n        this.pumpkinKey = key;\n    }'),
])

edit('net/neoforged/neoforge/registries/DeferredHolder.java', [
    ('    public ResourceKey<R> getKey() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredHolder.getKey:()Lnet/minecraft/resources/ResourceKey;");\n    }',
     '    // Pumpkin divergence: real body where the holder was built with its full key; a\n    // holder that never learned its registry still fails loudly by name.\n    public ResourceKey<R> getKey() {\n        if (pumpkinKey == null) {\n            throw Unimplemented.forMember(\n                    "net/neoforged/neoforge/registries/DeferredHolder.getKey (no registry recorded for "\n                            + pumpkinId + ")");\n        }\n        return pumpkinKey;\n    }'),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    // Pumpkin divergence: real body -- the factory method subclasses override.\n    protected <I extends T> DeferredHolder<T, I> createHolder(ResourceKey<? extends Registry<T>> registryKey, Identifier key) {\n        return new DeferredHolder<>(key, null);\n    }',
     "    // Pumpkin divergence: real body -- the factory method subclasses override. Built\n    // with the full key so getKey() has its answer.\n    protected <I extends T> DeferredHolder<T, I> createHolder(ResourceKey<? extends Registry<T>> registryKey, Identifier key) {\n        return new PumpkinKeyedHolder<>(ResourceKey.create(registryKey, key));\n    }\n\n    // DeferredHolder's key-taking constructor is protected; the smallest door to it.\n    private static final class PumpkinKeyedHolder<R, I extends R> extends DeferredHolder<R, I> {\n        PumpkinKeyedHolder(ResourceKey<R> key) {\n            super(key);\n        }\n    }"),
])


# Items: every pruned-null vanilla constant becomes a self-naming stand-in.
_p = os.path.join(ROOT, "net/minecraft/world/item/Items.java")
_s = PENDING.get(_p) or open(_p).read()
for _m in list(re.finditer(r"public static final Item ([A-Z_0-9]+) = null;", _s)):
    _s = _s.replace(_m.group(0),
                    'public static final Item %s = pumpkinVanilla("%s");' % (_m.group(1), _m.group(1).lower()), 1)
_helper = """
    // Pumpkin divergence: a vanilla stand-in carries its own name -- identity-stable, and
    // the registration path can copy the real item's definition from it.
    private static Item pumpkinVanilla(String name) {
        Item item = new Item(new Item.Properties());
        item.pumpkinVanillaName = name;
        return item;
    }
"""
_s = re.sub(r"(public (?:final )?class Items \{)", lambda m2: m2.group(1) + _helper, _s, count=1)
_s = re.sub(r"    static \{\n        if \(true\) \{\n            throw Unimplemented\.forMember\(\"net/minecraft/world/item/Items\"\);\n        \}\n    \}\n",
            "    // Pumpkin divergence: no throwing initializer -- every stand-in above is real.\n", _s)
PENDING[_p] = _s


# ItemAbilities + NeoForgeRegistries.Keys: names from NeoForge's own tables.
_nf = os.path.join(os.path.dirname(ROOT), "../../../NeoForge/src/main/java")
_vanilla = open(os.path.join(_nf, "net/neoforged/neoforge/common/ItemAbilities.java")).read()
_table = dict(re.findall(r"([A-Z_0-9]+) = ItemAbility\.get\(\"([a-z_]+)\"\)", _vanilla))
_p = os.path.join(ROOT, "net/neoforged/neoforge/common/ItemAbilities.java")
_s = PENDING.get(_p) or open(_p).read()
for _m in list(re.finditer(r"public static final ItemAbility ([A-Z_0-9]+) = null;", _s)):
    _name = _table.get(_m.group(1))
    if _name:
        _s = _s.replace(_m.group(0),
                        'public static final ItemAbility %s = ItemAbility.get("%s");' % (_m.group(1), _name), 1)
_s = re.sub(r"    static \{\n        if \(true\) \{\n            throw Unimplemented\.forMember\(\"net/neoforged/neoforge/common/ItemAbilities\"\);\n        \}\n    \}\n",
            "    // Pumpkin divergence: no throwing initializer -- names from NeoForge's own table.\n", _s)
PENDING[_p] = _s
_vanilla = open(os.path.join(_nf, "net/neoforged/neoforge/registries/NeoForgeRegistries.java")).read()
_keys = dict(re.findall(r"([A-Z_0-9]+) = key\(\"([a-z_:]+)\"\)", _vanilla))
_p = os.path.join(ROOT, "net/neoforged/neoforge/registries/NeoForgeRegistries.java")
_s = PENDING.get(_p) or open(_p).read()
for _m in list(re.finditer(r"public static final ResourceKey<([^;=]+?)> ([A-Z_0-9]+) = null;", _s)):
    _name = _keys.get(_m.group(2))
    if _name:
        _s = _s.replace(_m.group(0),
                        'public static final ResourceKey<%s> %s = ResourceKey.createRegistryKey(\n                net.minecraft.resources.Identifier.fromNamespaceAndPath("neoforge", "%s"));'
                        % (_m.group(1), _m.group(2), _name), 1)
_s = re.sub(r"        static \{\n            if \(true\) \{\n                throw Unimplemented\.forMember\(\"net/neoforged/neoforge/registries/NeoForgeRegistries\$Keys\"\);\n            \}\n        \}\n",
            "        // Pumpkin divergence: no throwing initializer -- names from NeoForge's table.\n", _s)
PENDING[_p] = _s

edit('net/minecraft/resources/RegistryFileCodec.java', [
    ('    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec) {\n        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.create:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lnet/minecraft/resources/RegistryFileCodec;");\n    }\n\n    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean allowInline) {\n        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.create:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lnet/minecraft/resources/RegistryFileCodec;");\n    }',
     '    // Pumpkin divergence: real construction, inert behaviour -- the codec exists and\n    // composes; encode/decode below still throw their member keys on first use.\n    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec) {\n        return new RegistryFileCodec<>(registryKey, elementCodec, true);\n    }\n\n    public static <E> RegistryFileCodec<E> create(ResourceKey<? extends Registry<E>> registryKey, Codec<E> elementCodec, boolean allowInline) {\n        return new RegistryFileCodec<>(registryKey, elementCodec, allowInline);\n    }'),
])


# ItemAbilities set constants: NeoForge's own groupings, resolved through get() so a
# pruned singleton constant cannot break the set.
_p = os.path.join(ROOT, "net/neoforged/neoforge/common/ItemAbilities.java")
_s = PENDING.get(_p) or open(_p).read()
_vanilla = open(os.path.join(_nf, "net/neoforged/neoforge/common/ItemAbilities.java")).read()
_sets = dict(re.findall(r"Set<ItemAbility> ([A-Z_0-9]+) = of\(([A-Z_, 0-9]+)\)", _vanilla))
_singles = dict(re.findall(r"([A-Z_0-9]+) = ItemAbility\.get\(\"([a-z_]+)\"\)", _vanilla))
for _m in list(re.finditer(r"public static final Set<ItemAbility> ([A-Z_0-9]+) = null;", _s)):
    _members = _sets.get(_m.group(1))
    if _members:
        _refs = ", ".join('ItemAbility.get("%s")' % _singles[x.strip()] for x in _members.split(","))
        _s = _s.replace(_m.group(0),
                        "public static final Set<ItemAbility> %s = Set.of(%s);" % (_m.group(1), _refs), 1)
PENDING[_p] = _s


# RegistryCodecs: inert codecs, same contract as ExtraCodecs.
_p = os.path.join(ROOT, "net/minecraft/core/RegistryCodecs.java")
_s = PENDING.get(_p) or open(_p).read()
_pattern = re.compile(
    r"    public static (?P<head>[^\n(]+) homogeneousList\((?P<params>[^)]*)\) \{\n"
    r"        throw Unimplemented\.forMember\(\"(?P<key>[^\"]+)\"\);\n    \}")
def _rc_replace(m):
    return ("    // Pumpkin divergence: inert codec -- throws its key on first use.\n"
            "    public static " + m.group("head") + " homogeneousList(" + m.group("params") + ") {\n"
            "        return dev.pumpkin.shim.Stubs.throwingCodec(\"" + m.group("key") + "\");\n    }")
PENDING[_p] = _pattern.sub(_rc_replace, _s)

edit('net/minecraft/resources/RegistryFileCodec.java', [
    ('    public String toString() {\n        throw Unimplemented.forMember("net/minecraft/resources/RegistryFileCodec.toString:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: real body -- DFU prints codecs while composing them.\n    public String toString() {\n        return "RegistryFileCodec[pumpkin inert]";\n    }'),
])

edit('net/minecraft/network/codec/StreamCodec.java', [
    ('    default <O> StreamCodec<B, O> map(Function<? super V, ? extends O> to, Function<? super O, ? extends V> from) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.map:(Ljava/util/function/Function;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     "    // Pumpkin divergence: real composition, as vanilla -- inertness propagates from\n    // the source codec, so a mapped inert codec still throws its origin's key on use.\n    default <O> StreamCodec<B, O> map(Function<? super V, ? extends O> to, Function<? super O, ? extends V> from) {\n        StreamCodec<B, V> self = this;\n        return new StreamCodec<B, O>() {\n            @Override\n            public O decode(B input) {\n                return to.apply(self.decode(input));\n            }\n\n            @Override\n            public void encode(B output, O value) {\n                self.encode(output, from.apply(value));\n            }\n        };\n    }"),
])

edit('net/minecraft/network/codec/StreamCodec.java', [
    ('    static <B, V> StreamCodec<B, V> ofMember(StreamMemberEncoder<B, V> encoder, StreamDecoder<B, V> decoder) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.ofMember:(Lnet/minecraft/network/codec/StreamMemberEncoder;Lnet/minecraft/network/codec/StreamDecoder;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    // Pumpkin divergence: real body, mirroring of() -- the member-encoder spelling.\n    static <B, V> StreamCodec<B, V> ofMember(StreamMemberEncoder<B, V> encoder, StreamDecoder<B, V> decoder) {\n        return new StreamCodec<B, V>() {\n            @Override\n            public V decode(B input) {\n                return decoder.decode(input);\n            }\n\n            @Override\n            public void encode(B output, V value) {\n                encoder.encode(value, output);\n            }\n        };\n    }'),
])

edit('net/neoforged/neoforge/transfer/fluid/FluidResource.java', [
    ('    public static final FluidResource EMPTY = null;\n\n    public static final Codec<FluidResource> CODEC = null;',
     '    // Pumpkin divergence: a real empty instance -- mods null-check it at class-init --\n    // and an inert codec that throws its name on first use.\n    public static final FluidResource EMPTY = new FluidResource();\n\n    public static final Codec<FluidResource> CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/transfer/fluid/FluidResource.CODEC");'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public static final Codec<ItemResource> CODEC = null;\n\n    public static final Codec<ItemResource> OPTIONAL_CODEC = null;',
     '    // Pumpkin divergence: inert codecs -- compose at class-init, throw by name on use.\n    public static final Codec<ItemResource> CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/transfer/item/ItemResource.CODEC");\n\n    public static final Codec<ItemResource> OPTIONAL_CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/neoforged/neoforge/transfer/item/ItemResource.OPTIONAL_CODEC");'),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    public ResourceKey<? extends Registry<T>> getRegistryKey() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.getRegistryKey:()Lnet/minecraft/resources/ResourceKey;");\n    }',
     '    // Pumpkin divergence: real body.\n    public ResourceKey<? extends Registry<T>> getRegistryKey() {\n        return pumpkinRegistryKey;\n    }'),
])

edit('net/neoforged/neoforge/transfer/fluid/FluidResource.java', [
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.isEmpty:()Z");\n    }',
     '    // Pumpkin divergence: real body -- the shared EMPTY instance is the empty one; a\n    // resource built by an of() overload will carry its fluid when those get bodies.\n    private Fluid pumpkinFluid;\n\n    public boolean isEmpty() {\n        return pumpkinFluid == null;\n    }'),
])

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec.isLoaded:()Z");',
     '        // Pumpkin divergence: real answer. No config file ever loads here, and saying so\n        // routes mods to their declared defaults -- the same values our ConfigValues hold.\n        return false;'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public EntityType() {',
     '    // Pumpkin divergence: no vanilla counterpart -- the name a vanilla stand-in from\n    // EntityTypes carries.\n    public String pumpkinVanillaName;\n\n    public EntityType() {'),
])


# EntityTypes: self-naming stand-ins, like Items.
_p = os.path.join(ROOT, "net/minecraft/world/entity/EntityTypes.java")
_s = PENDING.get(_p) or open(_p).read()
for _m in list(re.finditer(r"public static final EntityType<[^>]*>+ ([A-Z_0-9]+) = null;", _s)):
    _ET_CATS = {'ARMOR_STAND': 'MISC', 'BOGGED': 'MONSTER', 'CREEPER': 'MONSTER', 'ENDERMAN': 'MONSTER', 'ITEM': 'MISC', 'LIGHTNING_BOLT': 'MISC', 'PARCHED': 'MONSTER', 'SKELETON': 'MONSTER', 'STRAY': 'MONSTER', 'WITHER_SKELETON': 'MONSTER', 'PLAYER': 'MISC'}
    _cat = _ET_CATS.get(_m.group(1), "MISC")
    _s = _s.replace(_m.group(0), _m.group(0).replace(" = null;", ' = pumpkinVanilla("%s", MobCategory.%s);' % (_m.group(1).lower(), _cat)), 1)
_et_helper = """

    // Pumpkin divergence: a vanilla stand-in carries its own name; nothing constructs
    // entities through it, and anything deeper fails loudly on the member it needs.
    @SuppressWarnings({\"unchecked\", \"rawtypes\"})
    private static <T extends Entity> EntityType<T> pumpkinVanilla(String name, MobCategory category) {
        EntityType type = new EntityType();
        type.pumpkinVanillaName = name;
        type.pumpkinCategory = category;
        return type;
    }
"""
_m2 = re.search(r"(public (?:final )?class EntityTypes \{)", _s)
_s = _s.replace(_m2.group(1), _m2.group(1) + _et_helper, 1)
_s = re.sub(r"    static \{\n        if \(true\) \{\n            throw Unimplemented\.forMember\(\"net/minecraft/world/entity/EntityTypes\"\);\n        \}\n    \}\n",
            "    // Pumpkin divergence: no throwing initializer -- stand-ins above are real.\n", _s)
PENDING[_p] = _s

edit('net/minecraft/network/chat/ComponentSerialization.java', [
    ('    public static final Codec<Component> CODEC = null;',
     '    // Pumpkin divergence: inert codec -- composes at class-init, throws by name on use.\n    public static final Codec<Component> CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/network/chat/ComponentSerialization.CODEC");'),
])

edit('net/minecraft/network/chat/ComponentSerialization.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/network/chat/ComponentSerialization");\n        }\n    }',
     '    // Pumpkin divergence: no throwing initializer -- the fields above answer inertly.'),
])


# NeoForgeExtraCodecs: inert codecs, same contract as ExtraCodecs.
_p = os.path.join(ROOT, "net/neoforged/neoforge/common/util/NeoForgeExtraCodecs.java")
_s = PENDING.get(_p) or open(_p).read()
_pattern = re.compile(
    r"    public static (?P<head>[^\n(]+) (?P<name>\w+)\((?P<params>[^)]*)\) \{\n"
    r"        throw Unimplemented\.forMember\(\"(?P<key>[^\"]+)\"\);\n    \}")
def _nfec_replace(m):
    _ret = m.group("head").strip().split(" ")[-1]
    if _ret.startswith("MapCodec"):
        _f = "throwingMapCodec"
    elif _ret.startswith("Codec"):
        _f = "throwingCodec"
    else:
        return m.group(0)
    return ("    // Pumpkin divergence: inert codec -- throws its key on first use.\n"
            "    public static " + m.group("head") + " " + m.group("name") + "(" + m.group("params") + ") {\n"
            "        return dev.pumpkin.shim.Stubs." + _f + "(\"" + m.group("key") + "\");\n    }")
PENDING[_p] = _pattern.sub(_nfec_replace, _s)

edit('net/minecraft/resources/RegistryFixedCodec.java', [
    ('    public static <E> RegistryFixedCodec<E> create(ResourceKey<? extends Registry<E>> registryKey) {\n        throw Unimplemented.forMember("net/minecraft/resources/RegistryFixedCodec.create:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/resources/RegistryFixedCodec;");\n    }',
     '    // Pumpkin divergence: real construction, inert behaviour -- encode/decode still\n    // throw their member keys on first use.\n    public static <E> RegistryFixedCodec<E> create(ResourceKey<? extends Registry<E>> registryKey) {\n        return new RegistryFixedCodec<>(registryKey);\n    }'),
])

edit('net/minecraft/resources/RegistryFixedCodec.java', [
    ('    public String toString() {\n        throw Unimplemented.forMember("net/minecraft/resources/RegistryFixedCodec.toString:()Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: real body -- DFU prints codecs while composing them.\n    public String toString() {\n        return "RegistryFixedCodec[pumpkin inert]";\n    }'),
])

edit('net/minecraft/core/Registry.java', [
    ('    default Codec<Holder<T>> holderByNameCodec() {\n        throw Unimplemented.forMember("net/minecraft/core/Registry.holderByNameCodec:()Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: inert, like byNameCodec above.\n    default Codec<Holder<T>> holderByNameCodec() {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/Registry.holderByNameCodec:()Lcom/mojang/serialization/Codec;");\n    }'),
])

edit('net/minecraft/resources/ResourceKey.java', [
    ('    public static <T> Codec<ResourceKey<T>> codec(ResourceKey<? extends Registry<T>> registryName) {\n        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.codec:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: inert codec -- composes at class-init, throws by name on use.\n    public static <T> Codec<ResourceKey<T>> codec(ResourceKey<? extends Registry<T>> registryKey) {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/resources/ResourceKey.codec:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");\n    }'),
])

edit('net/minecraft/resources/ResourceKey.java', [
    ('    public static <T> StreamCodec<ByteBuf, ResourceKey<T>> streamCodec(ResourceKey<? extends Registry<T>> registryName) {\n        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.streamCodec:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    // Pumpkin divergence: inert stream codec -- same contract as codec() above.\n    @SuppressWarnings("unchecked")\n    public static <T> StreamCodec<ByteBuf, ResourceKey<T>> streamCodec(ResourceKey<? extends Registry<T>> registryName) {\n        return dev.pumpkin.shim.Stubs.of(net.minecraft.network.codec.StreamCodec.class,\n                "net/minecraft/resources/ResourceKey.streamCodec:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/network/codec/StreamCodec;");\n    }'),
])

edit('net/minecraft/core/UUIDUtil.java', [
    ('public static final Codec<UUID> CODEC = null;',
     'public static final Codec<UUID> CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/UUIDUtil.CODEC");'),
])

edit('net/minecraft/core/UUIDUtil.java', [
    ('public static final Codec<UUID> STRING_CODEC = null;',
     'public static final Codec<UUID> STRING_CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/UUIDUtil.STRING_CODEC");'),
])

edit('net/minecraft/core/UUIDUtil.java', [
    ('public static final Codec<UUID> LENIENT_CODEC = null;',
     'public static final Codec<UUID> LENIENT_CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/core/UUIDUtil.LENIENT_CODEC");'),
])

edit('net/minecraft/core/UUIDUtil.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/core/UUIDUtil");\n        }\n    }',
     '    // Pumpkin divergence: no throwing initializer -- the codecs answer inertly.'),
])


# ByteBufCodecs CodecOperation factories: inert composition.
_p = os.path.join(ROOT, "net/minecraft/network/codec/ByteBufCodecs.java")
_s = PENDING.get(_p) or open(_p).read()
_pattern = re.compile(
    r"    static (?P<head>[^\n(]*?CodecOperation<[^\n(]*) (?P<name>\w+)\((?P<params>[^)]*)\) \{\n"
    r"        throw Unimplemented\.forMember\(\"(?P<key>[^\"]+)\"\);\n    \}")
def _bbop_replace(m):
    return ("    // Pumpkin divergence: an operation whose result is inert -- the composed codec\n"
            "    // throws this key on first encode/decode; the composition itself succeeds.\n"
            "    static " + m.group("head") + " " + m.group("name") + "(" + m.group("params") + ") {\n"
            "        return original -> dev.pumpkin.shim.Stubs.of(StreamCodec.class, \"" + m.group("key") + "\");\n    }")
PENDING[_p] = _pattern.sub(_bbop_replace, _s)

edit('net/minecraft/world/level/block/state/BlockState.java', [
    ('    public static final Codec<BlockState> CODEC = null;',
     '    // Pumpkin divergence: inert codec -- composes at class-init, throws by name on use.\n    public static final Codec<BlockState> CODEC =\n            dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/block/state/BlockState.CODEC");'),
])


# ---------------------------------------------- global null-codec sweep (Mekanism)
# Every pruned Codec/MapCodec constant left as null becomes an inert codec that throws
# its own field name on first use. A null there NPEs inside DataFixerUpper while a mod
# composes codecs at class-initialisation, naming nothing; the inert form survives
# composition and fails on first genuine serialisation, naming the field.
import glob as _glob
for _path in _glob.glob(os.path.join(ROOT, "**/*.java"), recursive=True):
    _s0 = PENDING.get(_path) or open(_path).read()
    _cls = os.path.relpath(_path, ROOT)[:-5].replace(os.sep, ".")
    _count = [0]
    def _codec_repl(m, _cls=_cls, _count=_count):
        _count[0] += 1
        _f = "throwingMapCodec" if m.group("kind") == "MapCodec" else "throwingCodec"
        return ("%spublic static final %s<%s> %s =\n%s        dev.pumpkin.shim.Stubs.%s(\"%s.%s\");"
                % (m.group("indent"), m.group("kind"), m.group("type"), m.group("name"),
                   m.group("indent"), _f, _cls, m.group("name")))
    _s1 = re.sub(
        r"(?P<indent> *)public static final (?P<kind>Codec|MapCodec)<(?P<type>[^;=]+?)> (?P<name>[A-Z_0-9]+) = null;",
        _codec_repl, _s0)
    if _count[0]:
        PENDING[_path] = _s1

edit('net/neoforged/neoforge/capabilities/EntityCapability.java', [
    ('    public static <T> EntityCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        return create(name, typeClass, Void.class);\n    }',
     "    public static <T> EntityCapability<T, Void> createVoid(Identifier name, Class<T> typeClass) {\n        return create(name, typeClass, Void.class);\n    }\n\n    // Real NeoForge member the pruner never saw a direct call to; Capabilities' holders\n    // build through it.\n    public static <T> EntityCapability<T, net.minecraft.core.Direction> createSided(Identifier name, Class<T> typeClass) {\n        return create(name, typeClass, net.minecraft.core.Direction.class);\n    }"),
])


# Capabilities: the standard capability tokens, built exactly as NeoForge builds them.
_p = os.path.join(ROOT, "net/neoforged/neoforge/capabilities/Capabilities.java")
_s = PENDING.get(_p) or open(_p).read()
_CAP_FILLS = {'Energy.BLOCK': 'BlockCapability.createSided(pumpkinName("energy_handler"), EnergyHandler.class)', 'Energy.ENTITY': 'EntityCapability.createSided(pumpkinName("energy_handler"), EnergyHandler.class)', 'Energy.ITEM': 'ItemCapability.create(pumpkinName("energy_handler"), EnergyHandler.class, ItemAccess.class)', 'Fluid.BLOCK': 'BlockCapability.createSided(pumpkinName("fluid_handler"), ResourceHandler.asClass())', 'Fluid.ENTITY': 'EntityCapability.createSided(pumpkinName("fluid_handler"), ResourceHandler.asClass())', 'Fluid.ITEM': 'ItemCapability.create(pumpkinName("fluid_handler"), ResourceHandler.asClass(), ItemAccess.class)', 'Item.BLOCK': 'BlockCapability.createSided(pumpkinName("item_handler"), ResourceHandler.asClass())', 'Item.ENTITY': 'EntityCapability.createVoid(pumpkinName("item_handler"), ResourceHandler.asClass())', 'Item.ITEM': 'ItemCapability.create(pumpkinName("item_handler"), ResourceHandler.asClass(), ItemAccess.class)'}
_out = []
_last = 0
for _m in re.finditer(r"        public static final (\w+)Capability<(?P<type>[^;=]+?)> (?P<name>[A-Z_]+) = null;", _s):
    _cls = re.findall(r"public static final class (\w+) \{", _s[:_m.start()])[-1]
    _fill = _CAP_FILLS.get(_cls + "." + _m.group("name"))
    _out.append(_s[_last:_m.start()])
    if _fill:
        _out.append("        public static final %sCapability<%s> %s =\n                %s;"
                    % (_m.group(1), _m.group("type"), _m.group("name"), _fill))
    else:
        _out.append(_m.group(0))
    _last = _m.end()
_out.append(_s[_last:])
_s = "".join(_out)
_s = _s.replace("public final class Capabilities {", """public final class Capabilities {

    // NeoForge's create(): the neoforge namespace.
    private static net.minecraft.resources.Identifier pumpkinName(String path) {
        return net.minecraft.resources.Identifier.fromNamespaceAndPath(\"neoforge\", path);
    }
""", 1)
_s = re.sub(r"        static \{\n            if \(true\) \{\n                throw Unimplemented\.forMember\(\"net/neoforged/neoforge/capabilities/Capabilities\$\w+\"\);\n            \}\n        \}\n",
            "        // Pumpkin divergence: no throwing initializer -- the tokens above are real.\n", _s)
PENDING[_p] = _s


# Property enums serialize as their lowercase names; BSP constants get vanilla
# definitions; FluidType.Properties chains accept and drop.
import glob as _glob2
for _path in _glob2.glob(os.path.join(ROOT, "net/minecraft/world/level/block/state/properties/*.java")):
    _s0 = PENDING.get(_path) or open(_path).read()
    if "enum " not in _s0:
        continue
    _s1 = re.sub(r"    public String getSerializedName\(\) \{\n        throw Unimplemented[^\n]+\n    \}",
        "    // Pumpkin divergence: vanilla body -- the lowercase constant name.\n"
        "    public String getSerializedName() {\n        return name().toLowerCase(java.util.Locale.ROOT);\n    }", _s0)
    if _s1 != _s0:
        PENDING[_path] = _s1
_p = os.path.join(ROOT, "net/minecraft/world/level/block/state/properties/BlockStateProperties.java")
_s = PENDING.get(_p) or open(_p).read()
_BSP = {
    "OPEN": 'BooleanProperty.create("open")',
    "WATERLOGGED": 'BooleanProperty.create("waterlogged")',
    "FACING": 'EnumProperty.create("facing", Direction.class)',
    "HORIZONTAL_FACING": 'EnumProperty.create("facing", Direction.class, Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST)',
    "DOUBLE_BLOCK_HALF": 'EnumProperty.create("half", DoubleBlockHalf.class)',
    "BED_PART": 'EnumProperty.create("part", BedPart.class)',
}
for _m in list(re.finditer(r"public static final ([A-Za-z<>]+) ([A-Z_0-9]+) = null;", _s)):
    _fill = _BSP.get(_m.group(2))
    if _fill:
        _s = _s.replace(_m.group(0), "public static final %s %s = %s;" % (_m.group(1), _m.group(2), _fill), 1)
if "= null;" not in _s:
    _s = re.sub(r"    static \{\n        if \(true\) \{\n            throw Unimplemented\.forMember\(\"net/minecraft/world/level/block/state/properties/BlockStateProperties\"\);\n        \}\n    \}",
                "    // Pumpkin divergence: no throwing initializer -- vanilla's own definitions above.", _s)
PENDING[_p] = _s
_p = os.path.join(ROOT, "net/neoforged/neoforge/fluids/FluidType.java")
_s = PENDING.get(_p) or open(_p).read()
_s = _s.replace("""        public static Properties create() {
            throw Unimplemented.forMember("net/neoforged/neoforge/fluids/FluidType$Properties.create:()Lnet/neoforged/neoforge/fluids/FluidType$Properties;");
        }""", """        // Pumpkin divergence: real body; the chain methods below accept and drop --
        // fluid presentation is client rendering the server never consults.
        public static Properties create() {
            return new Properties();
        }""", 1)
_s = re.sub(r"        public Properties (\w+)\(([^)]*)\) \{\n            throw Unimplemented[^\n]+\n        \}",
            lambda m2: "        public Properties " + m2.group(1) + "(" + m2.group(2) + ") {\n            return this;\n        }", _s)
PENDING[_p] = _s
# EnumProperty varargs create (real subset) -- applied above as an edit already? ensure:

edit('net/minecraft/world/level/block/state/properties/EnumProperty.java', [
    ('    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz, T... values) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/state/properties/EnumProperty.create:(Ljava/lang/String;Ljava/lang/Class;[Ljava/lang/Enum;)Lnet/minecraft/world/level/block/state/properties/EnumProperty;");\n    }',
     '    // Pumpkin divergence: real body -- an explicit subset, in the order given, which is\n    // the order vanilla numbers the states in.\n    @SafeVarargs\n    public static <T extends Enum<T> & StringRepresentable> EnumProperty<T> create(String name, Class<T> clazz, T... values) {\n        EnumProperty<T> property = new EnumProperty<>();\n        property.pumpkinName = name;\n        property.pumpkinValues = List.of(values);\n        for (T value : property.pumpkinValues) {\n            property.pumpkinPossibleValues.add(value.getSerializedName());\n            property.pumpkinParse.put(value.getSerializedName(), value);\n        }\n        return property;\n    }'),
])

edit('net/neoforged/neoforge/common/SoundAction.java', [
    ('    public static SoundAction get(String name) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/SoundAction.get:(Ljava/lang/String;)Lnet/neoforged/neoforge/common/SoundAction;");\n    }',
     "    // Pumpkin divergence: real, interned by name -- NeoForge's own contract.\n    private static final java.util.concurrent.ConcurrentHashMap<String, SoundAction> PUMPKIN_INTERNED =\n            new java.util.concurrent.ConcurrentHashMap<>();\n\n    private String pumpkinName;\n\n    public static SoundAction get(String name) {\n        SoundAction action = PUMPKIN_INTERNED.computeIfAbsent(name, key -> new SoundAction());\n        action.pumpkinName = name;\n        return action;\n    }"),
])

edit('net/neoforged/neoforge/common/SoundActions.java', [
    ('    public static final SoundAction BUCKET_FILL = null;',
     '    public static final SoundAction BUCKET_FILL = SoundAction.get("bucket_fill");'),
])

edit('net/neoforged/neoforge/common/SoundActions.java', [
    ('    public static final SoundAction BUCKET_EMPTY = null;',
     '    public static final SoundAction BUCKET_EMPTY = SoundAction.get("bucket_empty");'),
])

edit('net/neoforged/neoforge/common/SoundActions.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/SoundActions");\n        }\n    }',
     '    // Pumpkin divergence: no throwing initializer -- the actions above are real.'),
])

edit('net/minecraft/world/phys/shapes/Shapes.java', [
    ('    public static VoxelShape empty() {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/Shapes.empty:()Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '    // Pumpkin divergence: a real shared instance -- geometry lives on the Rust side,\n    // and mods mostly carry these around; anything deeper fails loudly on its member.\n    public static VoxelShape empty() {\n        return PUMPKIN_EMPTY;\n    }'),
])

edit('net/minecraft/world/phys/shapes/Shapes.java', [
    ('public final class Shapes {',
     'public final class Shapes {\n\n    private static final VoxelShape PUMPKIN_EMPTY = new VoxelShape();\n\n    private static final VoxelShape PUMPKIN_BLOCK = new VoxelShape();\n'),
])


# Shapes helpers answer the inert shape; BaseFlowingFluid.Properties chains.
_p = os.path.join(ROOT, "net/minecraft/world/phys/shapes/Shapes.java")
_s = PENDING.get(_p) or open(_p).read()
_s = re.sub(r"    public static VoxelShape (\w+)\(([^)]*)\) \{\n        throw Unimplemented[^\n]+\n    \}",
            lambda m2: "    // Pumpkin divergence: inert shape -- geometry the server never consults here.\n"
                       "    public static VoxelShape " + m2.group(1) + "(" + m2.group(2) + ") {\n"
                       "        return VoxelShape.pumpkinInert();\n    }", _s)
PENDING[_p] = _s
_p = os.path.join(ROOT, "net/neoforged/neoforge/fluids/BaseFlowingFluid.java")
_s = PENDING.get(_p) or open(_p).read()
_s = re.sub(r"        public Properties (\w+)\(([^)]*)\) \{\n            throw Unimplemented[^\n]+\n        \}",
            lambda m2: "        public Properties " + m2.group(1) + "(" + m2.group(2) + ") {\n            return this;\n        }", _s)
PENDING[_p] = _s

edit('net/minecraft/world/phys/shapes/VoxelShape.java', [
    ('    public VoxelShape optimize() {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.optimize:()Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '    // Pumpkin divergence: real-enough body -- optimizing an inert shape is the shape.\n    public VoxelShape optimize() {\n        return this;\n    }'),
])

edit('net/minecraft/world/phys/AABB.java', [
    ('    public final double minX = 0.0;\n\n', ''),
    ('    public final double minY = 0.0;\n\n', ''),
    ('    public final double minZ = 0.0;\n\n', ''),
    ('    public final double maxX = 0.0;\n\n', ''),
    ('    public final double maxY = 0.0;\n\n', ''),
    ('    public final double maxZ = 0.0;\n\n', ''),
])

edit('net/minecraft/world/phys/AABB.java', [
    ('    public AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n    }',
     '    // Pumpkin divergence: real fields -- a box is its bounds.\n    public double minX, minY, minZ, maxX, maxY, maxZ;\n\n    public AABB(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n        this.minX = minX;\n        this.minY = minY;\n        this.minZ = minZ;\n        this.maxX = maxX;\n        this.maxY = maxY;\n        this.maxZ = maxZ;\n    }'),
])

edit('net/minecraft/world/phys/shapes/VoxelShape.java', [
    ('    public List<AABB> toAabbs() {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.toAabbs:()Ljava/util/List;");\n    }',
     '    // Pumpkin divergence: real where the shape was built from boxes -- the mod\'s own\n    // numbers coming back out. A shape with unknown geometry still fails loudly.\n    java.util.List<net.minecraft.world.phys.AABB> pumpkinBoxes;\n\n    public List<AABB> toAabbs() {\n        if (pumpkinBoxes == null) {\n            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.toAabbs:()Ljava/util/List; (a shape with unknown geometry)");\n        }\n        return pumpkinBoxes;\n    }\n\n    // Pumpkin divergence: no vanilla counterpart -- an inert shape that knows its boxes.\n    public static VoxelShape pumpkinOfBoxes(java.util.List<net.minecraft.world.phys.AABB> boxes) {\n        VoxelShape shape = pumpkinInert();\n        shape.pumpkinBoxes = java.util.List.copyOf(boxes);\n        return shape;\n    }'),
])

edit('net/minecraft/world/phys/shapes/Shapes.java', [
    ('    private static final VoxelShape PUMPKIN_EMPTY = VoxelShape.pumpkinInert();\n\n    private static final VoxelShape PUMPKIN_BLOCK = VoxelShape.pumpkinInert();',
     '    private static final VoxelShape PUMPKIN_EMPTY =\n            VoxelShape.pumpkinOfBoxes(java.util.List.of());\n\n    private static final VoxelShape PUMPKIN_BLOCK = VoxelShape.pumpkinOfBoxes(\n            java.util.List.of(new net.minecraft.world.phys.AABB(0.0, 0.0, 0.0, 1.0, 1.0, 1.0)));'),
])

edit('net/minecraft/world/phys/shapes/Shapes.java', [
    ('    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape or(VoxelShape first, VoxelShape second) {\n\n        return VoxelShape.pumpkinInert();\n\n    }',
     '    // Pumpkin divergence: real union where both sides know their boxes -- the union of\n    // box lists is their concatenation (unsimplified, which toAabbs permits). A side\n    // with unknown geometry keeps the result loud.\n    public static VoxelShape or(VoxelShape first, VoxelShape second) {\n        if (first.pumpkinBoxes != null && second.pumpkinBoxes != null) {\n            java.util.List<AABB> joined = new java.util.ArrayList<>(first.pumpkinBoxes);\n            joined.addAll(second.pumpkinBoxes);\n            return VoxelShape.pumpkinOfBoxes(joined);\n        }\n        return VoxelShape.pumpkinInert();\n    }'),
])

edit('net/minecraft/world/phys/shapes/Shapes.java', [
    ('    // Pumpkin divergence: real-enough body -- see VoxelShape.pumpkinInert.\n\n    public static VoxelShape or(VoxelShape first, VoxelShape... tail) {\n\n        return VoxelShape.pumpkinInert();\n\n    }',
     '    public static VoxelShape or(VoxelShape first, VoxelShape... tail) {\n        VoxelShape result = first;\n        for (VoxelShape shape : tail) {\n            result = or(result, shape);\n        }\n        return result;\n    }'),
])

edit('net/minecraft/world/phys/shapes/Shapes.java', [
    ('    // Pumpkin divergence: inert shape -- geometry the server never consults here.\n    public static VoxelShape joinUnoptimized(VoxelShape first, VoxelShape second, BooleanOp op) {\n        return VoxelShape.pumpkinInert();\n    }',
     '    // Pumpkin divergence: the OR case is a real union; any other operation on shapes is\n    // geometry this shim does not compute, and stays loud.\n    public static VoxelShape joinUnoptimized(VoxelShape first, VoxelShape second, BooleanOp op) {\n        if (op == BooleanOp.OR) {\n            return or(first, second);\n        }\n        return VoxelShape.pumpkinInert();\n    }'),
])

edit('net/minecraft/world/level/block/Block.java', [
    ("    // Pumpkin divergence: real-enough body. A collision shape is geometry Pumpkin never\n    // consults -- the server's own collision runs in Rust. Mods build these in statics and\n    // hand them back from getShape; an inert instance satisfies both, and its one abstract\n    // member throws with a name if anything ever reads the geometry.\n    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n        return VoxelShape.pumpkinInert();\n    }",
     "    // Pumpkin divergence: vanilla body -- pixel coordinates over sixteen, carried as a\n    // real box so a mod can decompose and rotate what it built. The server's own\n    // collision still runs in Rust; this exists for the mods' own geometry math.\n    public static VoxelShape box(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {\n        return net.minecraft.world.phys.shapes.Shapes.box(\n                minX / 16.0, minY / 16.0, minZ / 16.0, maxX / 16.0, maxY / 16.0, maxZ / 16.0);\n    }"),
])


# AABB pure math: vanilla arithmetic over the real bounds.
_p = os.path.join(ROOT, "net/minecraft/world/phys/AABB.java")
_s = PENDING.get(_p) or open(_p).read()
_AABB = {'AABB move(double xa, double ya, double za)': 'return new AABB(minX + xa, minY + ya, minZ + za, maxX + xa, maxY + ya, maxZ + za);', 'AABB move(BlockPos pos)': 'return move(pos.getX(), pos.getY(), pos.getZ());', 'AABB move(Vec3 pos)': 'return move(pos.x, pos.y, pos.z);', 'AABB inflate(double xAdd, double yAdd, double zAdd)': 'return new AABB(minX - xAdd, minY - yAdd, minZ - zAdd, maxX + xAdd, maxY + yAdd, maxZ + zAdd);', 'AABB inflate(double amountToAddInAllDirections)': 'return inflate(amountToAddInAllDirections, amountToAddInAllDirections, amountToAddInAllDirections);', 'AABB expandTowards(double xa, double ya, double za)': 'return new AABB(xa < 0.0 ? minX + xa : minX, ya < 0.0 ? minY + ya : minY, za < 0.0 ? minZ + za : minZ, xa > 0.0 ? maxX + xa : maxX, ya > 0.0 ? maxY + ya : maxY, za > 0.0 ? maxZ + za : maxZ);', 'double getXsize()': 'return maxX - minX;', 'double getYsize()': 'return maxY - minY;', 'double getZsize()': 'return maxZ - minZ;', 'Vec3 getCenter()': 'return new Vec3((minX + maxX) / 2.0, (minY + maxY) / 2.0, (minZ + maxZ) / 2.0);', 'boolean intersects(double minX, double minY, double minZ, double maxX, double maxY, double maxZ)': 'return this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY && this.minZ < maxZ && this.maxZ > minZ;', 'boolean intersects(AABB aabb)': 'return intersects(aabb.minX, aabb.minY, aabb.minZ, aabb.maxX, aabb.maxY, aabb.maxZ);', 'boolean contains(double x, double y, double z)': 'return x >= minX && x < maxX && y >= minY && y < maxY && z >= minZ && z < maxZ;'}
for _sig, _body in _AABB.items():
    _m = re.search(r"    public " + re.escape(_sig) + r" \{\n        throw Unimplemented[^\n]+\n    \}", _s)
    if _m:
        _s = _s.replace(_m.group(0),
                        "    // Pumpkin divergence: vanilla arithmetic over the real bounds.\n"
                        "    public " + _sig + " {\n        " + _body + "\n    }", 1)
PENDING[_p] = _s


# Direction pure lookups: vanilla bodies.
_p = os.path.join(ROOT, "net/minecraft/core/Direction.java")
_s = PENDING.get(_p) or open(_p).read()
_DIR = {'Direction getOpposite()': 'return switch (this) {\n            case DOWN -> UP;\n            case UP -> DOWN;\n            case NORTH -> SOUTH;\n            case SOUTH -> NORTH;\n            case WEST -> EAST;\n            case EAST -> WEST;\n        };', 'Direction getClockWise()': 'return switch (this) {\n            case NORTH -> EAST;\n            case EAST -> SOUTH;\n            case SOUTH -> WEST;\n            case WEST -> NORTH;\n            default -> throw new IllegalStateException("no horizontal rotation for " + this);\n        };', 'Direction getCounterClockWise()': 'return switch (this) {\n            case NORTH -> WEST;\n            case WEST -> SOUTH;\n            case SOUTH -> EAST;\n            case EAST -> NORTH;\n            default -> throw new IllegalStateException("no horizontal rotation for " + this);\n        };', 'int getStepX()': 'return switch (this) {\n            case WEST -> -1;\n            case EAST -> 1;\n            default -> 0;\n        };', 'int getStepY()': 'return switch (this) {\n            case DOWN -> -1;\n            case UP -> 1;\n            default -> 0;\n        };', 'int getStepZ()': 'return switch (this) {\n            case NORTH -> -1;\n            case SOUTH -> 1;\n            default -> 0;\n        };'}
for _sig, _body in _DIR.items():
    _m = re.search(r"    public " + re.escape(_sig) + r" \{\n        throw Unimplemented[^\n]+\n    \}", _s)
    if _m:
        _s = _s.replace(_m.group(0),
                        "    // Pumpkin divergence: vanilla body.\n    public " + _sig + " {\n        " + _body + "\n    }", 1)
PENDING[_p] = _s


# LootContextParams: every key is a real identity token, like ORIGIN.
_p = os.path.join(ROOT, "net/minecraft/world/level/storage/loot/parameters/LootContextParams.java")
_s = PENDING.get(_p) or open(_p).read()
for _m in list(re.finditer(r"    public static final ContextKey<(?P<type>[^;=]+?)> (?P<name>[A-Z_0-9]+) = null;", _s)):
    _s = _s.replace(_m.group(0),
                    "    public static final ContextKey<%s> %s = new ContextKey<>(null);" % (_m.group("type"), _m.group("name")), 1)
PENDING[_p] = _s

edit('net/neoforged/neoforge/registries/datamaps/DataMapType.java', [
    ('    public static <T, R> Builder<T, R> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.builder:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lnet/neoforged/neoforge/registries/datamaps/DataMapType$Builder;");\n    }',
     '    // Pumpkin divergence: real construction -- a data map type is its id; the data\n    // itself is datapack content nothing loads yet, and reads fail loudly on Registry.\n    private Identifier pumpkinId;\n\n    public static <T, R> Builder<T, R> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {\n        Builder<T, R> builder = new Builder<>();\n        builder.pumpkinId = id;\n        return builder;\n    }'),
])

edit('net/neoforged/neoforge/registries/datamaps/DataMapType.java', [
    ('    public Identifier id() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType.id:()Lnet/minecraft/resources/Identifier;");\n    }',
     '    public Identifier id() {\n        return pumpkinId;\n    }'),
])

edit('net/neoforged/neoforge/registries/datamaps/DataMapType.java', [
    ('        public Builder<T, R> synced(Codec<T> networkCodec, boolean mandatory) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType$Builder.synced:(Lcom/mojang/serialization/Codec;Z)Lnet/neoforged/neoforge/registries/datamaps/DataMapType$Builder;");\n        }',
     '        Identifier pumpkinId;\n\n        public Builder<T, R> synced(Codec<T> networkCodec, boolean mandatory) {\n            return this;\n        }'),
])

edit('net/neoforged/neoforge/registries/datamaps/DataMapType.java', [
    ('        public DataMapType<R, T> build() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/DataMapType$Builder.build:()Lnet/neoforged/neoforge/registries/datamaps/DataMapType;");\n        }',
     '        public DataMapType<R, T> build() {\n            DataMapType<R, T> type = new DataMapType<>();\n            type.pumpkinId = pumpkinId;\n            return type;\n        }'),
])

edit('net/minecraft/resources/HolderSetCodec.java', [
    ('    public static <E> Codec<HolderSet<E>> create(ResourceKey<? extends Registry<E>> registryKey, Codec<Holder<E>> elementCodec, boolean alwaysUseList) {\n        throw Unimplemented.forMember("net/minecraft/resources/HolderSetCodec.create:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: inert codec -- throws its key on first use.\n    public static <E> Codec<HolderSet<E>> create(ResourceKey<? extends Registry<E>> registryKey, Codec<Holder<E>> elementCodec, boolean alwaysUseList) {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/resources/HolderSetCodec.create:(Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;Z)Lcom/mojang/serialization/Codec;");\n    }'),
])

edit('net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.java', [
    ('    public static <T, R> AdvancedDataMapType.Builder<T, R, DataMapValueRemover.Default<T, R>> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.builder:(Lnet/minecraft/resources/Identifier;Lnet/minecraft/resources/ResourceKey;Lcom/mojang/serialization/Codec;)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");\n    }',
     '    // Pumpkin divergence: real construction, like DataMapType -- the id is the identity.\n    public static <T, R> AdvancedDataMapType.Builder<T, R, DataMapValueRemover.Default<T, R>> builder(Identifier id, ResourceKey<Registry<R>> registry, Codec<T> codec) {\n        Builder<T, R, DataMapValueRemover.Default<T, R>> builder = new Builder<>();\n        builder.pumpkinId = id;\n        return builder;\n    }'),
])

edit('net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.java', [
    ('        public <VR1 extends DataMapValueRemover<R, T>> AdvancedDataMapType.Builder<T, R, VR1> remover(Codec<VR1> remover) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.remover:(Lcom/mojang/serialization/Codec;)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");\n        }',
     '        @SuppressWarnings("unchecked")\n        public <VR1 extends DataMapValueRemover<R, T>> AdvancedDataMapType.Builder<T, R, VR1> remover(Codec<VR1> remover) {\n            return (AdvancedDataMapType.Builder<T, R, VR1>) this;\n        }'),
])

edit('net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.java', [
    ('        public AdvancedDataMapType.Builder<T, R, VR> merger(DataMapValueMerger<R, T> merger) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.merger:(Lnet/neoforged/neoforge/registries/datamaps/DataMapValueMerger;)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");\n        }',
     '        public AdvancedDataMapType.Builder<T, R, VR> merger(DataMapValueMerger<R, T> merger) {\n            return this;\n        }'),
])

edit('net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.java', [
    ('        public AdvancedDataMapType.Builder<T, R, VR> synced(Codec<T> networkCodec, boolean mandatory) {\n            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.synced:(Lcom/mojang/serialization/Codec;Z)Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder;");\n        }',
     '        public AdvancedDataMapType.Builder<T, R, VR> synced(Codec<T> networkCodec, boolean mandatory) {\n            return this;\n        }'),
])

edit('net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.java', [
    ('        public AdvancedDataMapType<R, T, VR> build() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType$Builder.build:()Lnet/neoforged/neoforge/registries/datamaps/AdvancedDataMapType;");\n        }',
     '        public AdvancedDataMapType<R, T, VR> build() {\n            AdvancedDataMapType<R, T, VR> type = new AdvancedDataMapType<>();\n            type.pumpkinAdvancedId = pumpkinId;\n            return type;\n        }'),
])

edit('net/neoforged/neoforge/registries/datamaps/AdvancedDataMapType.java', [
    ('    public AdvancedDataMapType() {',
     '    Identifier pumpkinAdvancedId;\n\n    @Override\n    public Identifier id() {\n        return pumpkinAdvancedId;\n    }\n\n    public AdvancedDataMapType() {'),
])

edit('net/minecraft/tags/TagKey.java', [
    ('    public static <T> Codec<TagKey<T>> codec(ResourceKey<? extends Registry<T>> registryName) {\n        throw Unimplemented.forMember("net/minecraft/tags/TagKey.codec:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");\n    }',
     '    // Pumpkin divergence: inert codec -- throws its key on first use.\n    public static <T> Codec<TagKey<T>> codec(ResourceKey<? extends Registry<T>> registryName) {\n        return dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/tags/TagKey.codec:(Lnet/minecraft/resources/ResourceKey;)Lcom/mojang/serialization/Codec;");\n    }'),
])

edit('net/minecraft/util/RandomSource.java', [
    ('    static RandomSource create() {\n        throw Unimplemented.forMember("net/minecraft/util/RandomSource.create:()Lnet/minecraft/util/RandomSource;");\n    }',
     '    static RandomSource create() {\n        return pumpkinRandom(new java.util.Random());\n    }'),
])

edit('net/minecraft/util/RandomSource.java', [
    ('    static RandomSource createThreadSafe() {\n        throw Unimplemented.forMember("net/minecraft/util/RandomSource.createThreadSafe:()Lnet/minecraft/util/RandomSource;");\n    }',
     '    static RandomSource createThreadSafe() {\n        return pumpkinRandom(new java.util.Random());\n    }'),
])

edit('net/minecraft/util/RandomSource.java', [
    ('    static RandomSource create(long seed) {\n        throw Unimplemented.forMember("net/minecraft/util/RandomSource.create:(J)Lnet/minecraft/util/RandomSource;");\n    }',
     '    static RandomSource create(long seed) {\n        return pumpkinRandom(new java.util.Random(seed));\n    }'),
])

edit('net/minecraft/util/RandomSource.java', [
    ('public interface RandomSource {\n',
     'public interface RandomSource {\n\n    // Pumpkin divergence: a real random over java.util.Random -- the same choice the\n    // interaction bridge\'s level makes. Mods want noise, not stubs, from these.\n    private static RandomSource pumpkinRandom(java.util.Random random) {\n        return new RandomSource() {\n            public RandomSource fork() {\n                return pumpkinRandom(new java.util.Random(random.nextLong()));\n            }\n\n            public net.minecraft.world.level.levelgen.PositionalRandomFactory forkPositional() {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/util/RandomSource.forkPositional:()Lnet/minecraft/world/level/levelgen/PositionalRandomFactory;");\n            }\n\n            public void setSeed(long seed) {\n                random.setSeed(seed);\n            }\n\n            public int nextInt() {\n                return random.nextInt();\n            }\n\n            public int nextInt(int bound) {\n                return random.nextInt(bound);\n            }\n\n            public long nextLong() {\n                return random.nextLong();\n            }\n\n            public boolean nextBoolean() {\n                return random.nextBoolean();\n            }\n\n            public float nextFloat() {\n                return random.nextFloat();\n            }\n\n            public double nextDouble() {\n                return random.nextDouble();\n            }\n\n            public double nextGaussian() {\n                return random.nextGaussian();\n            }\n        };\n    }\n\n'),
])

edit('net/minecraft/core/registries/BuiltInRegistries.java', [
    ('    public static final Registry<BlockEntityType<?>> BLOCK_ENTITY_TYPE = Stubs.of(Registry.class, "net/minecraft/core/Registry");',
     '    public static final Registry<BlockEntityType<?>> BLOCK_ENTITY_TYPE = Stubs.of(Registry.class,\n            "net/minecraft/core/Registry", java.util.Map.of("key",\n                    net.minecraft.resources.ResourceKey.createRegistryKey(\n                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "block_entity_type"))));'),
])

edit('net/minecraft/core/registries/BuiltInRegistries.java', [
    ('    public static final Registry<MenuType<?>> MENU = Stubs.of(Registry.class, "net/minecraft/core/Registry");',
     '    public static final Registry<MenuType<?>> MENU = Stubs.of(Registry.class,\n            "net/minecraft/core/Registry", java.util.Map.of("key",\n                    net.minecraft.resources.ResourceKey.createRegistryKey(\n                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "menu"))));'),
])

edit('net/minecraft/core/registries/BuiltInRegistries.java', [
    ('    public static final Registry<DataComponentType<?>> DATA_COMPONENT_TYPE = Stubs.of(Registry.class, "net/minecraft/core/Registry");',
     '    public static final Registry<DataComponentType<?>> DATA_COMPONENT_TYPE = Stubs.of(Registry.class,\n            "net/minecraft/core/Registry", java.util.Map.of("key",\n                    net.minecraft.resources.ResourceKey.createRegistryKey(\n                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "data_component_type"))));'),
])

edit('net/minecraft/core/registries/BuiltInRegistries.java', [
    ('    public static final Registry<TicketType> TICKET_TYPE = Stubs.of(Registry.class, "net/minecraft/core/Registry");',
     '    public static final Registry<TicketType> TICKET_TYPE = Stubs.of(Registry.class,\n            "net/minecraft/core/Registry", java.util.Map.of("key",\n                    net.minecraft.resources.ResourceKey.createRegistryKey(\n                            net.minecraft.resources.Identifier.fromNamespaceAndPath("minecraft", "ticket_type"))));'),
])


# NeoForgeStreamCodecs: inert stream codecs.
_p = os.path.join(ROOT, "net/neoforged/neoforge/network/codec/NeoForgeStreamCodecs.java")
_s = PENDING.get(_p) or open(_p).read()
_pattern = re.compile(
    r"    public static (?P<head>[^\n(]*?StreamCodec<[^\n(]*) (?P<name>\w+)\((?P<params>[^)]*)\) \{\n"
    r"        throw Unimplemented\.forMember\(\"(?P<key>[^\"]+)\"\);\n    \}")
def _nfsc_replace(m):
    return ("    // Pumpkin divergence: inert codec -- throws its key on first encode/decode.\n"
            "    public static " + m.group("head") + " " + m.group("name") + "(" + m.group("params") + ") {\n"
            "        return dev.pumpkin.shim.Stubs.of(net.minecraft.network.codec.StreamCodec.class, \"" + m.group("key") + "\");\n    }")
PENDING[_p] = _pattern.sub(_nfsc_replace, _s)

edit('net/neoforged/neoforge/common/ModConfigSpec.java', [
    ('        public T getDefault() {\n            throw Unimplemented.forMember("net/neoforged/neoforge/common/ModConfigSpec$ConfigValue.getDefault:()Ljava/lang/Object;");\n        }',
     '        // Pumpkin divergence: real body -- the declared default, same source get() reads.\n        public T getDefault() {\n            return pumpkinDefault.get();\n        }'),
])

edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('    RegisterEvent(ResourceKey<? extends Registry<?>> registryKey, Registry<?> registry) {\n    }',
     '    // Pumpkin divergence: the event carries its registry; Bootstrap posts one event per\n    // known registry, the way NeoForge fires one per real registry.\n    private ResourceKey<? extends Registry<?>> pumpkinRegistryKey;\n\n    RegisterEvent(ResourceKey<? extends Registry<?>> registryKey, Registry<?> registry) {\n        this.pumpkinRegistryKey = registryKey;\n    }\n\n    public RegisterEvent(ResourceKey<? extends Registry<?>> registryKey) {\n        this.pumpkinRegistryKey = registryKey;\n    }\n\n    public ResourceKey<? extends Registry<?>> getRegistryKey() {\n        return pumpkinRegistryKey;\n    }'),
])

edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Consumer<RegisterHelper<T>> consumer) {\n        consumer.accept((name, value) -> {',
     '    public <T> void register(ResourceKey<? extends Registry<T>> registryKey, Consumer<RegisterHelper<T>> consumer) {\n        // With one event per registry, a helper aimed at another registry waits for its\n        // own event -- otherwise every registration would replay once per event.\n        if (pumpkinRegistryKey != null\n                && !registryKey.identifier().toString().equals(pumpkinRegistryKey.identifier().toString())) {\n            return;\n        }\n        consumer.accept((name, value) -> {'),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    // Pumpkin divergence: real body.\n    public void register(IEventBus bus) {\n        bus.addListener(RegisterEvent.class, event -> pumpkinFlush());\n    }',
     "    // Pumpkin divergence: real body. Flush fires on this register's own event only --\n    // Bootstrap posts one RegisterEvent per registry.\n    public void register(IEventBus bus) {\n        bus.addListener(RegisterEvent.class, event -> {\n            if (event.getRegistryKey() == null\n                    || event.getRegistryKey().identifier().toString()\n                            .equals(pumpkinRegistryKey.identifier().toString())) {\n                pumpkinFlush();\n            }\n        });\n    }"),
])


# Item.Properties chain: declared metadata, accepted and dropped.
_p = os.path.join(ROOT, "net/minecraft/world/item/Item.java")
_s = PENDING.get(_p) or open(_p).read()
def _iprop_replace(m2):
    _tokens = m2.group("head").strip().split(" ")
    if not _tokens[-1].endswith("Properties"):
        return m2.group(0)
    return ("        // Pumpkin divergence: declared item metadata, accepted and dropped.\n"
            "        public " + m2.group("head").strip() + " " + m2.group("name") + "(" + m2.group("params") + ") {\n"
            "            return this;\n        }")
_s = re.sub(r"        public (?P<head>[^\n(]+) (?P<name>\w+)\((?P<params>[^)]*)\) \{\n"
            r"            throw Unimplemented\.forMember\(\"net/minecraft/world/item/Item\$Properties\.[^\n]+\n        \}",
            _iprop_replace, _s)
PENDING[_p] = _s

edit('net/neoforged/neoforge/registries/RegisterEvent.java', [
    ('    public ResourceKey<? extends Registry<?>> getRegistryKey() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/RegisterEvent.getRegistryKey:()Lnet/minecraft/resources/ResourceKey;");\n    }\n\n',
     ''),
])

edit('net/neoforged/neoforge/registries/RegistryBuilder.java', [
    ('    // Pumpkin divergence: a stub registry that knows which registry it is -- the one\n    // question registration helpers ask -- and throws by name for everything else.\n    // Entries a mod registers into it flow through DeferredRegister, where unknown\n    // registry kinds are acknowledged and counted on the Rust side.\n    @SuppressWarnings("unchecked")\n    public Registry<T> create() {\n        return dev.pumpkin.shim.Stubs.of(Registry.class, "net/minecraft/core/Registry",\n                java.util.Map.of("key", pumpkinRegistryKey));\n    }',
     '    // Pumpkin divergence: no vanilla counterpart. Every custom registry a mod creates,\n    // so the loader can fire that registry\'s RegisterEvent -- without it the mod\'s\n    // registrations into its own registry would simply never flush.\n    private static final java.util.List<ResourceKey<? extends Registry<?>>> PUMPKIN_CREATED =\n            new java.util.concurrent.CopyOnWriteArrayList<>();\n\n    public static java.util.List<ResourceKey<? extends Registry<?>>> pumpkinCreatedKeys() {\n        return PUMPKIN_CREATED;\n    }\n\n    // Pumpkin divergence: a stub registry that knows which registry it is -- the one\n    // question registration helpers ask -- and throws by name for everything else.\n    // Entries a mod registers into it flow through DeferredRegister, where unknown\n    // registry kinds are acknowledged and counted on the Rust side.\n    @SuppressWarnings("unchecked")\n    public Registry<T> create() {\n        PUMPKIN_CREATED.add(pumpkinRegistryKey);\n        return dev.pumpkin.shim.Stubs.of(Registry.class, "net/minecraft/core/Registry",\n                java.util.Map.of("key", pumpkinRegistryKey));\n    }'),
])

edit('net/minecraft/world/phys/shapes/VoxelShape.java', [
    ('    public VoxelShape move(Vec3 delta) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move:(Lnet/minecraft/world/phys/Vec3;)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '    // Pumpkin divergence: real over known boxes -- shift each; unknown geometry stays loud.\n    public VoxelShape move(Vec3 delta) {\n        if (pumpkinBoxes == null) {\n            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move (a shape with unknown geometry)");\n        }\n        java.util.List<net.minecraft.world.phys.AABB> moved = new java.util.ArrayList<>();\n        for (net.minecraft.world.phys.AABB box : pumpkinBoxes) {\n            moved.add(box.move(delta));\n        }\n        return pumpkinOfBoxes(moved);\n    }'),
])

edit('net/minecraft/world/phys/shapes/VoxelShape.java', [
    ('    public VoxelShape move(Vec3i delta) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move:(Lnet/minecraft/core/Vec3i;)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '    // Pumpkin divergence: real over known boxes -- shift each; unknown geometry stays loud.\n    public VoxelShape move(Vec3i delta) {\n        if (pumpkinBoxes == null) {\n            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move (a shape with unknown geometry)");\n        }\n        java.util.List<net.minecraft.world.phys.AABB> moved = new java.util.ArrayList<>();\n        for (net.minecraft.world.phys.AABB box : pumpkinBoxes) {\n            moved.add(box.move(delta));\n        }\n        return pumpkinOfBoxes(moved);\n    }'),
])

edit('net/minecraft/world/phys/shapes/VoxelShape.java', [
    ('    public VoxelShape move(double dx, double dy, double dz) {\n        throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move:(DDD)Lnet/minecraft/world/phys/shapes/VoxelShape;");\n    }',
     '    // Pumpkin divergence: real over known boxes -- shift each; unknown geometry stays loud.\n    public VoxelShape move(double dx, double dy, double dz) {\n        if (pumpkinBoxes == null) {\n            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move (a shape with unknown geometry)");\n        }\n        java.util.List<net.minecraft.world.phys.AABB> moved = new java.util.ArrayList<>();\n        for (net.minecraft.world.phys.AABB box : pumpkinBoxes) {\n            moved.add(box.move(dx, dy, dz));\n        }\n        return pumpkinOfBoxes(moved);\n    }'),
])

edit('net/minecraft/world/item/component/ItemAttributeModifiers.java', [
    ('    public static ItemAttributeModifiers.Builder builder() {\n        throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers.builder:()Lnet/minecraft/world/item/component/ItemAttributeModifiers$Builder;");\n    }',
     '    // Pumpkin divergence: real chain; the built component is declared item metadata the\n    // Rust side does not consume yet.\n    public static ItemAttributeModifiers.Builder builder() {\n        return new Builder();\n    }'),
])

edit('net/minecraft/world/item/component/ItemAttributeModifiers.java', [
    ('        public ItemAttributeModifiers.Builder add(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {\n            throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Builder.add:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;Lnet/minecraft/world/entity/EquipmentSlotGroup;)Lnet/minecraft/world/item/component/ItemAttributeModifiers$Builder;");\n        }',
     '        public ItemAttributeModifiers.Builder add(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot) {\n            return this;\n        }'),
])

edit('net/minecraft/world/item/component/ItemAttributeModifiers.java', [
    ('        public ItemAttributeModifiers.Builder add(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot, ItemAttributeModifiers.Display display) {\n            throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Builder.add:(Lnet/minecraft/core/Holder;Lnet/minecraft/world/entity/ai/attributes/AttributeModifier;Lnet/minecraft/world/entity/EquipmentSlotGroup;Lnet/minecraft/world/item/component/ItemAttributeModifiers$Display;)Lnet/minecraft/world/item/component/ItemAttributeModifiers$Builder;");\n        }',
     '        public ItemAttributeModifiers.Builder add(Holder<Attribute> attribute, AttributeModifier modifier, EquipmentSlotGroup slot, ItemAttributeModifiers.Display display) {\n            return this;\n        }'),
])

edit('net/minecraft/world/item/component/ItemAttributeModifiers.java', [
    ('        public ItemAttributeModifiers build() {\n            throw Unimplemented.forMember("net/minecraft/world/item/component/ItemAttributeModifiers$Builder.build:()Lnet/minecraft/world/item/component/ItemAttributeModifiers;");\n        }',
     '        public ItemAttributeModifiers build() {\n            return new ItemAttributeModifiers();\n        }'),
])

edit('net/minecraft/world/item/component/ItemAttributeModifiers.java', [
    ('        public ItemAttributeModifiers build() {\n            return new ItemAttributeModifiers();\n        }',
     '        public ItemAttributeModifiers build() {\n            return new ItemAttributeModifiers(java.util.List.of());\n        }'),
])

edit('net/minecraft/world/phys/shapes/VoxelShape.java', [
    ('    // Pumpkin divergence: real over known boxes -- shift each; unknown geometry stays loud.\n    public VoxelShape move(Vec3i delta) {\n        if (pumpkinBoxes == null) {\n            throw Unimplemented.forMember("net/minecraft/world/phys/shapes/VoxelShape.move (a shape with unknown geometry)");\n        }\n        java.util.List<net.minecraft.world.phys.AABB> moved = new java.util.ArrayList<>();\n        for (net.minecraft.world.phys.AABB box : pumpkinBoxes) {\n            moved.add(box.move(delta));\n        }\n        return pumpkinOfBoxes(moved);\n    }',
     '    public VoxelShape move(Vec3i delta) {\n        return move(delta.getX(), delta.getY(), delta.getZ());\n    }'),
])

edit('net/minecraft/world/level/material/Fluid.java', [
    ('public abstract class Fluid implements IFluidExtension {\n',
     'public abstract class Fluid implements IFluidExtension {\n\n    // Pumpkin divergence: no vanilla counterpart. The stand-in Fluids hands out --\n    // identity-stable, self-naming, every behaviour member throwing by name. Fluid\n    // simulation runs on the Rust side; mods carry these tokens around.\n    public String pumpkinVanillaName;\n\n    static Fluid pumpkinInert(String name) {\n        Fluid fluid = new Fluid() {\n            public Item getBucket() {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getBucket (inert stand-in)");\n            }\n\n            protected boolean canBeReplacedWith(FluidState state, BlockGetter level, BlockPos pos, Fluid other, Direction direction) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.canBeReplacedWith (inert stand-in)");\n            }\n\n            protected Vec3 getFlow(BlockGetter level, BlockPos pos, FluidState fluidState) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getFlow (inert stand-in)");\n            }\n\n            public int getTickDelay(LevelReader level) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getTickDelay (inert stand-in)");\n            }\n\n            protected float getExplosionResistance() {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getExplosionResistance (inert stand-in)");\n            }\n\n            public float getHeight(FluidState fluidState, BlockGetter level, BlockPos pos) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getHeight (inert stand-in)");\n            }\n\n            public float getOwnHeight(FluidState fluidState) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getOwnHeight (inert stand-in)");\n            }\n\n            protected BlockState createLegacyBlock(FluidState fluidState) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.createLegacyBlock (inert stand-in)");\n            }\n\n            public boolean isSource(FluidState fluidState) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.isSource (inert stand-in)");\n            }\n\n            public int getAmount(FluidState fluidState) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getAmount (inert stand-in)");\n            }\n\n            public VoxelShape getShape(FluidState state, BlockGetter level, BlockPos pos) {\n                throw dev.pumpkin.shim.Unimplemented.forMember("net/minecraft/world/level/material/Fluid.getShape (inert stand-in)");\n            }\n        };\n        fluid.pumpkinVanillaName = name;\n        return fluid;\n    }\n'),
])


# Fluids: EMPTY is a real inert stand-in; the holder clinit no longer throws.
_p = os.path.join(ROOT, "net/minecraft/world/level/material/Fluids.java")
_s = PENDING.get(_p) or open(_p).read()
_s = _s.replace("    public static final Fluid EMPTY = null;",
                '    // Pumpkin divergence: a real inert stand-in; see Fluid.pumpkinInert.\n'
                '    public static final Fluid EMPTY = Fluid.pumpkinInert("empty");', 1)
_s = re.sub(r"    static \{\n        if \(true\) \{\n            throw Unimplemented\.forMember\(\"net/minecraft/world/level/material/Fluids\"\);\n        \}\n    \}",
            "    // Pumpkin divergence: no throwing initializer; WATER and LAVA stay null and any\n    // read of them will say so by NPE site -- flowing fluids are a wider surface.", _s)
PENDING[_p] = _s

edit('net/minecraft/world/entity/EquipmentSlotGroup.java', [
    ('    public static EquipmentSlotGroup bySlot(EquipmentSlot slot) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EquipmentSlotGroup.bySlot:(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/entity/EquipmentSlotGroup;");\n    }',
     '    // Pumpkin divergence: vanilla mapping -- the group containing exactly that slot.\n    public static EquipmentSlotGroup bySlot(EquipmentSlot slot) {\n        return switch (slot) {\n            case MAINHAND -> MAINHAND;\n            case OFFHAND -> OFFHAND;\n            case FEET -> FEET;\n            case LEGS -> LEGS;\n            case CHEST -> CHEST;\n            case HEAD -> HEAD;\n            case BODY -> BODY;\n            case SADDLE -> SADDLE;\n        };\n    }'),
])

edit('net/minecraft/world/item/equipment/Equippable.java', [
    ('    public static Equippable.Builder builder(EquipmentSlot slot) {\n        throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable.builder:(Lnet/minecraft/world/entity/EquipmentSlot;)Lnet/minecraft/world/item/equipment/Equippable$Builder;");\n    }',
     '    // Pumpkin divergence: real chain -- the built component carries the slot, the one\n    // fact the mod declared; presentation fields stay empty.\n    public static Equippable.Builder builder(EquipmentSlot slot) {\n        Builder builder = new Builder();\n        builder.pumpkinSlot = slot;\n        return builder;\n    }'),
])

edit('net/minecraft/world/item/equipment/Equippable.java', [
    ('        public Equippable.Builder setEquipSound(Holder<SoundEvent> equipSound) {\n            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.setEquipSound:(Lnet/minecraft/core/Holder;)Lnet/minecraft/world/item/equipment/Equippable$Builder;");\n        }',
     '        EquipmentSlot pumpkinSlot;\n\n        public Equippable.Builder setEquipSound(Holder<SoundEvent> equipSound) {\n            return this;\n        }'),
])

edit('net/minecraft/world/item/equipment/Equippable.java', [
    ('        public Equippable.Builder setAsset(ResourceKey<EquipmentAsset> assetId) {\n            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.setAsset:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/equipment/Equippable$Builder;");\n        }',
     '        public Equippable.Builder setAsset(ResourceKey<EquipmentAsset> assetId) {\n            return this;\n        }'),
])

edit('net/minecraft/world/item/equipment/Equippable.java', [
    ('        public Equippable.Builder setDamageOnHurt(boolean damageOnHurt) {\n            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.setDamageOnHurt:(Z)Lnet/minecraft/world/item/equipment/Equippable$Builder;");\n        }',
     '        public Equippable.Builder setDamageOnHurt(boolean damageOnHurt) {\n            return this;\n        }'),
])

edit('net/minecraft/world/item/equipment/Equippable.java', [
    ('        public Equippable build() {\n            throw Unimplemented.forMember("net/minecraft/world/item/equipment/Equippable$Builder.build:()Lnet/minecraft/world/item/equipment/Equippable;");\n        }',
     '        public Equippable build() {\n            return new Equippable(pumpkinSlot, null, Optional.empty(), Optional.empty(),\n                    Optional.empty(), false, false, false, false, false, null);\n        }'),
])


# CreativeModeTab.Builder chains: presentation, accepted and dropped.
_p = os.path.join(ROOT, "net/minecraft/world/item/CreativeModeTab.java")
_s = PENDING.get(_p) or open(_p).read()
_s = re.sub(r"        public (?:CreativeModeTab\.)?Builder (\w+)\(([^)]*)\) \{\n            throw Unimplemented[^\n]+\n        \}",
            lambda m2: "        // Pumpkin divergence: tab presentation, accepted and dropped; chain lives.\n"
                       "        public CreativeModeTab.Builder " + m2.group(1) + "(" + m2.group(2) + ") {\n            return this;\n        }", _s)
PENDING[_p] = _s

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('    public Collection<DeferredHolder<T, ? extends T>> getEntries() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/registries/DeferredRegister.getEntries:()Ljava/util/Collection;");\n    }',
     '    // Pumpkin divergence: real body -- everything this register recorded.\n    public Collection<DeferredHolder<T, ? extends T>> getEntries() {\n        return java.util.Collections.unmodifiableCollection(pumpkinPending);\n    }'),
])

edit('net/minecraft/world/level/block/state/BlockBehaviour.java', [
    ('        private ToIntFunction<BlockState> lightEmission;',
     "        // Pumpkin divergence: public, as NeoForge's access transformer makes it --\n        // Mekanism writes light levels straight onto the field.\n        public ToIntFunction<BlockState> lightEmission;"),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('    protected final StateDefinition<Block, BlockState> stateDefinition = null;',
     '    // Pumpkin divergence: assigned, not null-final -- Mekanism reads the field\n    // directly in its constructors (stateDefinition.any()).\n    protected StateDefinition<Block, BlockState> stateDefinition;'),
])

edit('net/minecraft/world/level/block/Block.java', [
    ('        this.pumpkinDeclaredProperties = builder.pumpkinProperties();\n    }',
     '        this.pumpkinDeclaredProperties = builder.pumpkinProperties();\n        this.stateDefinition = getStateDefinition();\n    }'),
])

edit('net/minecraft/world/level/block/TntBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/TntBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: vanilla body -- tnt is its fuse-stability flag. The constant\n    // is declared here because the pruned BlockStateProperties does not carry it; the\n    // name and values are vanilla\'s own.\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty UNSTABLE =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("unstable");\n\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        builder.add(UNSTABLE);\n    }'),
])

edit('net/neoforged/neoforge/registries/DeferredRegister.java', [
    ('        return joined.toString();\n    }',
     "        // The block's declared default rides behind '@': registerDefaultState picks\n        // non-first values (a machine registers inactive), and without this the server\n        // would place every such block in its all-first-values state.\n        StringBuilder defaults = new StringBuilder();\n        for (java.util.Map.Entry<net.minecraft.world.level.block.state.properties.Property<?>, Comparable<?>> entry\n                : block.defaultBlockState().pumpkinValues.entrySet()) {\n            if (defaults.length() > 0) {\n                defaults.append(',');\n            }\n            Comparable<?> value = entry.getValue();\n            String spelled = value instanceof net.minecraft.util.StringRepresentable representable\n                    ? representable.getSerializedName()\n                    : String.valueOf(value);\n            defaults.append(entry.getKey().pumpkinName).append('=').append(spelled);\n        }\n        if (defaults.length() > 0) {\n            joined.append('@').append(defaults);\n        }\n        return joined.toString();\n    }"),
])

edit('net/minecraft/world/level/block/state/BlockBehaviour.java', [
    ('        public Holder<Block> typeHolder() {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$BlockStateBase.typeHolder:()Lnet/minecraft/core/Holder;");\n        }',
     "        // Pumpkin divergence: real body -- the owning block's holder, whose value() and\n        // is(TagKey) already answer; mods key their attribute maps by it.\n        public Holder<Block> typeHolder() {\n            return getBlock().builtInRegistryHolder();\n        }"),
])

edit('net/neoforged/neoforge/registries/DeferredHolder.java', [
    ('    public T get() {\n        if (pumpkinValue == null) {\n            pumpkinValue = pumpkinFactory.get();\n        }\n        return pumpkinValue;\n    }',
     '    @SuppressWarnings("unchecked")\n    public T get() {\n        if (pumpkinValue == null) {\n            if (pumpkinFactory == null) {\n                // A holder a mod constructed directly, expecting registry lookup: the\n                // target registered through its own holder, recorded under registry|id.\n                if (pumpkinKey == null) {\n                    throw new IllegalStateException(pumpkinId + " has no factory and no registry to look itself up in");\n                }\n                DeferredHolder<?, ?> target = PUMPKIN_BY_ID.get(pumpkinKey.pumpkinRegistry() + "|" + pumpkinId);\n                if (target == null || target == this) {\n                    throw new IllegalStateException(pumpkinId + " was never registered; a holder"\n                            + " created by key can only resolve after its target registers");\n                }\n                return (T) target.get();\n            }\n            pumpkinValue = pumpkinFactory.get();\n        }\n        return pumpkinValue;\n    }'),
])

edit('net/minecraft/core/HolderGetter.java', [
    ('        default <T> Optional<Holder.Reference<T>> get(ResourceKey<T> id) {\n            throw Unimplemented.forMember("net/minecraft/core/HolderGetter$Provider.get:(Lnet/minecraft/resources/ResourceKey;)Ljava/util/Optional;");\n        }',
     "        // Pumpkin divergence: absent, truthfully -- no registry lookup provider exists\n        // on this side, and Optional is the interface's own way to say so. Mods skip\n        // the content they would have resolved (creative-tab decoration, mostly).\n        default <T> Optional<Holder.Reference<T>> get(ResourceKey<T> id) {\n            return java.util.Optional.empty();\n        }"),
])

edit('net/minecraft/world/level/block/state/BlockBehaviour.java', [
    ('        public static BlockBehaviour.Properties ofLegacyCopy(BlockBehaviour block) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/state/BlockBehaviour$Properties.ofLegacyCopy:(Lnet/minecraft/world/level/block/state/BlockBehaviour;)Lnet/minecraft/world/level/block/state/BlockBehaviour$Properties;");\n        }',
     '        // Pumpkin divergence: like ofFullCopy -- the template survives the copy.\n        public static BlockBehaviour.Properties ofLegacyCopy(BlockBehaviour block) {\n            Properties properties = new Properties();\n            if (block instanceof net.minecraft.world.level.block.Block source) {\n                properties.pumpkinTemplate = source.pumpkinTemplate();\n            }\n            return properties;\n        }'),
])

edit('net/minecraft/world/level/block/CrossCollisionBlock.java', [
    ('public abstract class CrossCollisionBlock extends Block implements SimpleWaterloggedBlock {\n',
     'public abstract class CrossCollisionBlock extends Block implements SimpleWaterloggedBlock {\n\n    // Pumpkin divergence: vanilla\'s own connection properties, declared here because\n    // the pruned class dropped them and fences declare through them.\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty NORTH =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("north");\n\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty EAST =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("east");\n\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty SOUTH =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("south");\n\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty WEST =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("west");\n\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty WATERLOGGED =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("waterlogged");\n'),
])

edit('net/minecraft/world/level/block/FenceBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/FenceBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: vanilla body -- the four connections and waterlogging.\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        builder.add(CrossCollisionBlock.NORTH, CrossCollisionBlock.EAST,\n                CrossCollisionBlock.SOUTH, CrossCollisionBlock.WEST,\n                CrossCollisionBlock.WATERLOGGED);\n    }'),
])

edit('net/neoforged/neoforge/common/util/Lazy.java', [
    ('    public static <T> Lazy<T> of(Supplier<T> supplier) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/Lazy.of:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/common/util/Lazy;");\n    }',
     '    // Pumpkin divergence: the real thing -- memoize on first get.\n    public static <T> Lazy<T> of(Supplier<T> supplier) {\n        return new Lazy<T>() {\n            private T value;\n            private boolean resolved;\n\n            @Override\n            public T get() {\n                if (!resolved) {\n                    value = supplier.get();\n                    resolved = true;\n                }\n                return value;\n            }\n        };\n    }'),
])

edit('net/minecraft/world/level/block/FenceGateBlock.java', [
    ('    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/FenceGateBlock.createBlockStateDefinition:(Lnet/minecraft/world/level/block/state/StateDefinition$Builder;)V");\n    }',
     '    // Pumpkin divergence: vanilla\'s declarations, constants local because the pruned\n    // shared holders dropped them.\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty OPEN =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("open");\n\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty POWERED =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("powered");\n\n    public static final net.minecraft.world.level.block.state.properties.BooleanProperty IN_WALL =\n            net.minecraft.world.level.block.state.properties.BooleanProperty.create("in_wall");\n\n    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {\n        builder.add(net.minecraft.world.level.block.state.properties.BlockStateProperties.HORIZONTAL_FACING,\n                OPEN, POWERED, IN_WALL);\n    }'),
])

edit('net/neoforged/neoforge/common/util/Lazy.java', [
    ('    // Pumpkin divergence: the real thing -- memoize on first get.\n    public static <T> Lazy<T> of(Supplier<T> supplier) {\n        return new Lazy<T>() {\n            private T value;\n            private boolean resolved;\n\n            @Override\n            public T get() {\n                if (!resolved) {\n                    value = supplier.get();\n                    resolved = true;\n                }\n                return value;\n            }\n        };\n    }',
     '    // Pumpkin divergence: the real thing -- memoize on first get.\n    private Supplier<T> pumpkinSupplier;\n\n    private T pumpkinValue;\n\n    private boolean pumpkinResolved;\n\n    public static <T> Lazy<T> of(Supplier<T> supplier) {\n        Lazy<T> lazy = new Lazy<>();\n        lazy.pumpkinSupplier = supplier;\n        return lazy;\n    }'),
    ('    public T get() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/util/Lazy.get:()Ljava/lang/Object;");\n    }',
     '    public T get() {\n        if (!pumpkinResolved) {\n            pumpkinValue = pumpkinSupplier.get();\n            pumpkinResolved = true;\n        }\n        return pumpkinValue;\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public MobCategory getCategory() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getCategory:()Lnet/minecraft/world/entity/MobCategory;");\n    }',
     '    // Pumpkin divergence: real field -- set by the constructors and by EntityTypes\'\n    // stand-ins, whose categories are vanilla\'s own.\n    public MobCategory pumpkinCategory;\n\n    public MobCategory getCategory() {\n        if (pumpkinCategory == null) {\n            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getCategory (no category recorded)");\n        }\n        return pumpkinCategory;\n    }'),
])


# EntityType constructors record the category.
_p = os.path.join(ROOT, "net/minecraft/world/entity/EntityType.java")
_s = PENDING.get(_p) or open(_p).read()
_s = re.sub(r"(public EntityType\(EntityType\.EntityFactory<T> factory, MobCategory category[^)]*\) \{)\n(\s*\})",
            r"\1\n        this.pumpkinCategory = category;\n\2", _s)
PENDING[_p] = _s

edit('net/minecraft/world/entity/EntityType.java', [
    ('        public static <T extends Entity> EntityType.Builder<T> of(EntityType.EntityFactory<T> factory, MobCategory category) {\n            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.of:(Lnet/minecraft/world/entity/EntityType$EntityFactory;Lnet/minecraft/world/entity/MobCategory;)Lnet/minecraft/world/entity/EntityType$Builder;");\n        }',
     '        // Pumpkin divergence: real chain -- the category is the fact registration\n        // reads back; presentation knobs accept and drop.\n        MobCategory pumpkinCategory;\n\n        public static <T extends Entity> EntityType.Builder<T> of(EntityType.EntityFactory<T> factory, MobCategory category) {\n            Builder<T> builder = new Builder<>();\n            builder.pumpkinCategory = category;\n            return builder;\n        }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('        public EntityType<T> build(ResourceKey<EntityType<?>> name) {\n            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType$Builder.build:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/entity/EntityType;");\n        }',
     '        @SuppressWarnings({"unchecked", "rawtypes"})\n        public EntityType<T> build(ResourceKey<EntityType<?>> name) {\n            EntityType type = new EntityType();\n            type.pumpkinCategory = pumpkinCategory;\n            return type;\n        }'),
])

edit('net/minecraft/world/item/equipment/ArmorMaterial.java', [
    ('    public ItemAttributeModifiers createAttributes(ArmorType type) {\n        throw Unimplemented.forMember("net/minecraft/world/item/equipment/ArmorMaterial.createAttributes:(Lnet/minecraft/world/item/equipment/ArmorType;)Lnet/minecraft/world/item/component/ItemAttributeModifiers;");\n    }',
     '    // Pumpkin divergence: the built component is declared metadata the Rust side does\n    // not consume; the mod only needs the call to complete while registering items.\n    public ItemAttributeModifiers createAttributes(ArmorType type) {\n        return ItemAttributeModifiers.builder().build();\n    }'),
])


# EntityType.Builder chains: presentation knobs accept and drop.
_p = os.path.join(ROOT, "net/minecraft/world/entity/EntityType.java")
_s = PENDING.get(_p) or open(_p).read()
_s = re.sub(r"        public EntityType\.Builder<T> (\w+)\(([^)]*)\) \{\n            throw Unimplemented[^\n]+\n        \}",
            lambda m2: "        public EntityType.Builder<T> " + m2.group(1) + "(" + m2.group(2) + ") {\n            return this;\n        }", _s)
PENDING[_p] = _s

edit('net/minecraft/network/codec/StreamCodec.java', [
    ('    default <U> StreamCodec<B, U> dispatch(Function<? super U, ? extends V> type, Function<? super V, ? extends StreamCodec<? super B, ? extends U>> codec) {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.dispatch:(Ljava/util/function/Function;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    default <U> StreamCodec<B, U> dispatch(Function<? super U, ? extends V> type, Function<? super V, ? extends StreamCodec<? super B, ? extends U>> codec) {\n        // Pumpkin divergence: composes inert -- Pumpkin never encodes packets through\n        // mod stream codecs, so the composed codec throws its member key on first use.\n        return dev.pumpkin.shim.Stubs.of(StreamCodec.class,\n            "net/minecraft/network/codec/StreamCodec.dispatch:(Ljava/util/function/Function;Ljava/util/function/Function;)Lnet/minecraft/network/codec/StreamCodec;");\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public boolean canSerialize() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.canSerialize:()Z");\n    }',
     "    // Pumpkin divergence: truthful -- vanilla's flag is Builder.noSave(); the builder\n    // records it and build() carries it here.\n    public boolean pumpkinSerialize = true;\n\n    public boolean canSerialize() {\n        return pumpkinSerialize;\n    }"),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('        public EntityType.Builder<T> noSave() {\n            return this;\n        }',
     '        boolean pumpkinSerialize = true;\n\n        public EntityType.Builder<T> noSave() {\n            pumpkinSerialize = false;\n            return this;\n        }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('            type.pumpkinCategory = pumpkinCategory;',
     '            type.pumpkinCategory = pumpkinCategory;\n            type.pumpkinSerialize = pumpkinSerialize;'),
])

edit('net/minecraft/network/codec/StreamCodec.java', [
    ('    default <S extends B> StreamCodec<S, V> cast() {\n        throw Unimplemented.forMember("net/minecraft/network/codec/StreamCodec.cast:()Lnet/minecraft/network/codec/StreamCodec;");\n    }',
     '    @SuppressWarnings("unchecked")\n    default <S extends B> StreamCodec<S, V> cast() {\n        return (StreamCodec<S, V>) this;\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public boolean canSummon() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.canSummon:()Z");\n    }',
     '    public boolean pumpkinSummon = true;\n\n    public boolean canSummon() {\n        return pumpkinSummon;\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('        public EntityType.Builder<T> noSummon() {\n            return this;\n        }',
     '        boolean pumpkinSummon = true;\n\n        public EntityType.Builder<T> noSummon() {\n            pumpkinSummon = false;\n            return this;\n        }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('            type.pumpkinSerialize = pumpkinSerialize;',
     '            type.pumpkinSerialize = pumpkinSerialize;\n            type.pumpkinSummon = pumpkinSummon;'),
])

edit('net/minecraft/stats/Stats.java', [
    ('public class Stats {\n\n    public static final StatType<Item> ITEM_CRAFTED = null;\n\n    public static final StatType<Item> ITEM_USED = null;\n\n    public static final StatType<Identifier> CUSTOM = null;\n\n    public static final Identifier DAMAGE_BLOCKED_BY_SHIELD = null;\n\n    public static final Identifier FILL_CAULDRON = null;\n\n    public static final Identifier USE_CAULDRON = null;\n\n    public static final Identifier OPEN_CHEST = null;\n\n    public static final Identifier OPEN_BARREL = null;\n\n    public Stats() {\n    }\n\n    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/stats/Stats");\n        }\n    }\n}\n',
     'public class Stats {\n\n    // Pumpkin divergence: StatType instances are inert stand-ins (every method throws\n    // by member key); the Identifier constants are the real vanilla stat names.\n    public static final StatType<Item> ITEM_CRAFTED = new StatType<>();\n\n    public static final StatType<Item> ITEM_USED = new StatType<>();\n\n    public static final StatType<Identifier> CUSTOM = new StatType<>();\n\n    public static final Identifier DAMAGE_BLOCKED_BY_SHIELD = Identifier.withDefaultNamespace("damage_blocked_by_shield");\n\n    public static final Identifier FILL_CAULDRON = Identifier.withDefaultNamespace("fill_cauldron");\n\n    public static final Identifier USE_CAULDRON = Identifier.withDefaultNamespace("use_cauldron");\n\n    public static final Identifier OPEN_CHEST = Identifier.withDefaultNamespace("open_chest");\n\n    public static final Identifier OPEN_BARREL = Identifier.withDefaultNamespace("open_barrel");\n\n    public Stats() {\n    }\n}\n'),
    ('import dev.pumpkin.shim.Unimplemented;\n', ''),
])

edit('net/minecraft/network/chat/Style.java', [
    ('    public static final Style EMPTY = null;\n\n    private Style(TextColor color, Integer shadowColor, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough, Boolean obfuscated, ClickEvent clickEvent, HoverEvent hoverEvent, String insertion, FontDescription font) {\n    }',
     '    // Pumpkin divergence: a style really is just data -- the ctor stores it and the\n    // getters answer from it.\n    final TextColor pumpkinColor;\n    final Integer pumpkinShadowColor;\n    final Boolean pumpkinBold;\n    final Boolean pumpkinItalic;\n    final Boolean pumpkinUnderlined;\n    final Boolean pumpkinStrikethrough;\n    final Boolean pumpkinObfuscated;\n    final ClickEvent pumpkinClickEvent;\n    final HoverEvent pumpkinHoverEvent;\n    final String pumpkinInsertion;\n    final FontDescription pumpkinFont;\n\n    public static final Style EMPTY = new Style(null, null, null, null, null, null, null, null, null, null, null);\n\n    private Style(TextColor color, Integer shadowColor, Boolean bold, Boolean italic, Boolean underlined, Boolean strikethrough, Boolean obfuscated, ClickEvent clickEvent, HoverEvent hoverEvent, String insertion, FontDescription font) {\n        this.pumpkinColor = color;\n        this.pumpkinShadowColor = shadowColor;\n        this.pumpkinBold = bold;\n        this.pumpkinItalic = italic;\n        this.pumpkinUnderlined = underlined;\n        this.pumpkinStrikethrough = strikethrough;\n        this.pumpkinObfuscated = obfuscated;\n        this.pumpkinClickEvent = clickEvent;\n        this.pumpkinHoverEvent = hoverEvent;\n        this.pumpkinInsertion = insertion;\n        this.pumpkinFont = font;\n    }'),
    ('    public TextColor getColor() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.getColor:()Lnet/minecraft/network/chat/TextColor;");\n    }',
     '    public TextColor getColor() {\n        return pumpkinColor;\n    }'),
    ('    public boolean isBold() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isBold:()Z");\n    }',
     '    public boolean isBold() {\n        return pumpkinBold == Boolean.TRUE;\n    }'),
    ('    public boolean isItalic() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isItalic:()Z");\n    }',
     '    public boolean isItalic() {\n        return pumpkinItalic == Boolean.TRUE;\n    }'),
    ('    public boolean isStrikethrough() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isStrikethrough:()Z");\n    }',
     '    public boolean isStrikethrough() {\n        return pumpkinStrikethrough == Boolean.TRUE;\n    }'),
    ('    public boolean isUnderlined() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isUnderlined:()Z");\n    }',
     '    public boolean isUnderlined() {\n        return pumpkinUnderlined == Boolean.TRUE;\n    }'),
    ('    public boolean isObfuscated() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isObfuscated:()Z");\n    }',
     '    public boolean isObfuscated() {\n        return pumpkinObfuscated == Boolean.TRUE;\n    }'),
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.isEmpty:()Z");\n    }',
     '    public boolean isEmpty() {\n        return this.equals(EMPTY);\n    }'),
    ('    public ClickEvent getClickEvent() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.getClickEvent:()Lnet/minecraft/network/chat/ClickEvent;");\n    }',
     '    public ClickEvent getClickEvent() {\n        return pumpkinClickEvent;\n    }'),
    ('    public HoverEvent getHoverEvent() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.getHoverEvent:()Lnet/minecraft/network/chat/HoverEvent;");\n    }',
     '    public HoverEvent getHoverEvent() {\n        return pumpkinHoverEvent;\n    }'),
    ('    public Style withColor(TextColor color) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withColor:(Lnet/minecraft/network/chat/TextColor;)Lnet/minecraft/network/chat/Style;");\n    }',
     '    public Style withColor(TextColor color) {\n        return new Style(color, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, pumpkinClickEvent, pumpkinHoverEvent, pumpkinInsertion, pumpkinFont);\n    }'),
    ('    public Style withColor(int color) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withColor:(I)Lnet/minecraft/network/chat/Style;");\n    }',
     '    public Style withColor(int color) {\n        return withColor(TextColor.fromRgb(color));\n    }'),
    ('    public Style withClickEvent(ClickEvent clickEvent) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withClickEvent:(Lnet/minecraft/network/chat/ClickEvent;)Lnet/minecraft/network/chat/Style;");\n    }',
     '    public Style withClickEvent(ClickEvent clickEvent) {\n        return new Style(pumpkinColor, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, clickEvent, pumpkinHoverEvent, pumpkinInsertion, pumpkinFont);\n    }'),
    ('    public Style withHoverEvent(HoverEvent hoverEvent) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.withHoverEvent:(Lnet/minecraft/network/chat/HoverEvent;)Lnet/minecraft/network/chat/Style;");\n    }',
     '    public Style withHoverEvent(HoverEvent hoverEvent) {\n        return new Style(pumpkinColor, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, pumpkinClickEvent, hoverEvent, pumpkinInsertion, pumpkinFont);\n    }'),
    ('    public boolean equals(Object o) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.equals:(Ljava/lang/Object;)Z");\n    }',
     '    public boolean equals(Object o) {\n        if (this == o) {\n            return true;\n        }\n        if (!(o instanceof Style other)) {\n            return false;\n        }\n        return java.util.Objects.equals(pumpkinColor, other.pumpkinColor)\n            && java.util.Objects.equals(pumpkinShadowColor, other.pumpkinShadowColor)\n            && java.util.Objects.equals(pumpkinBold, other.pumpkinBold)\n            && java.util.Objects.equals(pumpkinItalic, other.pumpkinItalic)\n            && java.util.Objects.equals(pumpkinUnderlined, other.pumpkinUnderlined)\n            && java.util.Objects.equals(pumpkinStrikethrough, other.pumpkinStrikethrough)\n            && java.util.Objects.equals(pumpkinObfuscated, other.pumpkinObfuscated)\n            && java.util.Objects.equals(pumpkinClickEvent, other.pumpkinClickEvent)\n            && java.util.Objects.equals(pumpkinHoverEvent, other.pumpkinHoverEvent)\n            && java.util.Objects.equals(pumpkinInsertion, other.pumpkinInsertion)\n            && java.util.Objects.equals(pumpkinFont, other.pumpkinFont);\n    }'),
    ('    public int hashCode() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Style.hashCode:()I");\n    }',
     '    public int hashCode() {\n        return java.util.Objects.hash(pumpkinColor, pumpkinShadowColor, pumpkinBold, pumpkinItalic, pumpkinUnderlined, pumpkinStrikethrough, pumpkinObfuscated, pumpkinClickEvent, pumpkinHoverEvent, pumpkinInsertion, pumpkinFont);\n    }'),
    ('    public Style() {\n    }',
     '    public Style() {\n        this(null, null, null, null, null, null, null, null, null, null, null);\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    private final boolean fireImmune = false;',
     '    boolean fireImmune = false;'),
    ('    public boolean fireImmune() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.fireImmune:()Z");\n    }',
     '    public boolean fireImmune() {\n        return fireImmune;\n    }'),
    ('        public EntityType.Builder<T> fireImmune() {\n            return this;\n        }',
     '        public EntityType.Builder<T> fireImmune() {\n            fireImmune = true;\n            return this;\n        }'),
    ('            type.pumpkinSummon = pumpkinSummon;',
     '            type.pumpkinSummon = pumpkinSummon;\n            type.fireImmune = fireImmune;'),
])

edit('net/minecraft/world/entity/EntityTypes.java', [
    ('    public static final EntityType<WitherSkeleton> WITHER_SKELETON = pumpkinVanilla("wither_skeleton", MobCategory.MONSTER);',
     '    public static final EntityType<WitherSkeleton> WITHER_SKELETON = pumpkinVanillaFireImmune("wither_skeleton", MobCategory.MONSTER);\n\n    // Pumpkin divergence: vanilla fact -- these mobs are fire immune.\n    private static <T extends Entity> EntityType<T> pumpkinVanillaFireImmune(String name, MobCategory category) {\n        EntityType<T> type = pumpkinVanilla(name, category);\n        type.fireImmune = true;\n        return type;\n    }'),
])

edit('net/minecraft/network/chat/MutableComponent.java', [
    ('    private String pumpkinText = "";',
     '    private String pumpkinText = "";\n\n    // Pumpkin divergence: the style is data the component carries; nothing renders it\n    // server-side, but mods compose and re-read it while building names.\n    private Style pumpkinStyle = Style.EMPTY;'),
    ('    public MutableComponent setStyle(Style style) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.setStyle:(Lnet/minecraft/network/chat/Style;)Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '    public MutableComponent setStyle(Style style) {\n        this.pumpkinStyle = style;\n        return this;\n    }'),
    ('    public Style getStyle() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.getStyle:()Lnet/minecraft/network/chat/Style;");\n    }',
     '    public Style getStyle() {\n        return pumpkinStyle;\n    }'),
    ('    public MutableComponent append(Component component) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.append:(Lnet/minecraft/network/chat/Component;)Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     "    // Pumpkin divergence: appends the sibling's text; the sibling's own style is\n    // presentation the flat text cannot carry -- dropped, not misread.\n    public MutableComponent append(Component component) {\n        if (component instanceof MutableComponent mutable) {\n            pumpkinText = pumpkinText + mutable.pumpkinText;\n            return this;\n        }\n        pumpkinText = pumpkinText + component.getString();\n        return this;\n    }"),
    ('    public MutableComponent withStyle(Style patch) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/MutableComponent.withStyle:(Lnet/minecraft/network/chat/Style;)Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '    // Pumpkin divergence: vanilla applies the patch only where this style is unset;\n    // with color the sole style fact Pumpkin stores, that is what this implements.\n    public MutableComponent withStyle(Style patch) {\n        if (pumpkinStyle.getColor() == null && patch.getColor() != null) {\n            pumpkinStyle = pumpkinStyle.withColor(patch.getColor());\n        }\n        return this;\n    }'),
])

edit('net/minecraft/network/chat/Component.java', [
    ('    default MutableComponent copy() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.copy:()Lnet/minecraft/network/chat/MutableComponent;");\n    }',
     '    // Pumpkin divergence: real for the text-carrying components Pumpkin builds;\n    // anything else has no data to copy and fails loudly.\n    default MutableComponent copy() {\n        if (this instanceof MutableComponent mutable) {\n            MutableComponent copy = MutableComponent.pumpkinOf(mutable.pumpkinText());\n            copy.setStyle(mutable.getStyle());\n            return copy;\n        }\n        throw Unimplemented.forMember("net/minecraft/network/chat/Component.copy:()Lnet/minecraft/network/chat/MutableComponent;");\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    private final boolean canSpawnFarFromPlayer = false;',
     '    boolean canSpawnFarFromPlayer;'),
    ('    public boolean canSpawnFarFromPlayer() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.canSpawnFarFromPlayer:()Z");\n    }',
     '    public boolean canSpawnFarFromPlayer() {\n        return canSpawnFarFromPlayer;\n    }'),
    ('            builder.pumpkinCategory = category;\n            return builder;',
     '            builder.pumpkinCategory = category;\n            builder.canSpawnFarFromPlayer = category == MobCategory.CREATURE || category == MobCategory.MISC;\n            return builder;'),
    ('        public EntityType.Builder<T> canSpawnFarFromPlayer() {\n            return this;\n        }',
     '        public EntityType.Builder<T> canSpawnFarFromPlayer() {\n            canSpawnFarFromPlayer = true;\n            return this;\n        }'),
    ('            type.fireImmune = fireImmune;',
     '            type.fireImmune = fireImmune;\n            type.canSpawnFarFromPlayer = canSpawnFarFromPlayer;'),
])

edit('net/minecraft/world/entity/EntityTypes.java', [
    ('        type.pumpkinCategory = category;\n        return type;',
     '        type.pumpkinCategory = category;\n        type.canSpawnFarFromPlayer = category == MobCategory.CREATURE || category == MobCategory.MISC;\n        return type;'),
])

edit('net/minecraft/network/chat/TextColor.java', [
    ('    public boolean equals(Object o) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.equals:(Ljava/lang/Object;)Z");\n    }',
     '    public boolean equals(Object o) {\n        return o instanceof TextColor other && other.pumpkinValue == pumpkinValue;\n    }'),
    ('    public int hashCode() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.hashCode:()I");\n    }',
     '    public int hashCode() {\n        return Integer.hashCode(pumpkinValue);\n    }'),
    ('    public String toString() {\n        throw Unimplemented.forMember("net/minecraft/network/chat/TextColor.toString:()Ljava/lang/String;");\n    }',
     '    public String toString() {\n        return String.format("#%06X", pumpkinValue);\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public boolean onlyOpCanSetNbt() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.onlyOpCanSetNbt:()Z");\n    }',
     '    public boolean pumpkinOnlyOpCanSetNbt;\n\n    public boolean onlyOpCanSetNbt() {\n        return pumpkinOnlyOpCanSetNbt;\n    }'),
    ('        public EntityType.Builder<T> setOnlyOpCanSetNbt(boolean onlyOpCanSetNbt) {\n            return this;\n        }',
     '        boolean pumpkinOnlyOpCanSetNbt;\n\n        public EntityType.Builder<T> setOnlyOpCanSetNbt(boolean onlyOpCanSetNbt) {\n            pumpkinOnlyOpCanSetNbt = onlyOpCanSetNbt;\n            return this;\n        }'),
    ('            type.canSpawnFarFromPlayer = canSpawnFarFromPlayer;',
     '            type.canSpawnFarFromPlayer = canSpawnFarFromPlayer;\n            type.pumpkinOnlyOpCanSetNbt = pumpkinOnlyOpCanSetNbt;'),
])

edit('net/minecraft/world/item/component/Consumable.java', [
    ('    public static class Builder {\n\n        protected Builder() {\n        }\n\n        public Consumable build() {\n            throw Unimplemented.forMember("net/minecraft/world/item/component/Consumable$Builder.build:()Lnet/minecraft/world/item/component/Consumable;");\n        }\n    }',
     "    public static class Builder {\n\n        // Pumpkin divergence: real data builder over the record's own fields. The sound\n        // holder stays null until a mod sets one; the record carries what was declared.\n        float pumpkinConsumeSeconds = 1.6F;\n        ItemUseAnimation pumpkinAnimation = ItemUseAnimation.EAT;\n        Holder<SoundEvent> pumpkinSound;\n        boolean pumpkinHasConsumeParticles = true;\n        final java.util.ArrayList<ConsumeEffect> pumpkinEffects = new java.util.ArrayList<>();\n\n        protected Builder() {\n        }\n\n        public Builder consumeSeconds(float seconds) {\n            pumpkinConsumeSeconds = seconds;\n            return this;\n        }\n\n        public Builder animation(ItemUseAnimation animation) {\n            pumpkinAnimation = animation;\n            return this;\n        }\n\n        public Builder sound(Holder<SoundEvent> sound) {\n            pumpkinSound = sound;\n            return this;\n        }\n\n        public Builder soundAfterConsume(Holder<SoundEvent> sound) {\n            return this;\n        }\n\n        public Builder hasConsumeParticles(boolean hasConsumeParticles) {\n            pumpkinHasConsumeParticles = hasConsumeParticles;\n            return this;\n        }\n\n        public Builder onConsume(ConsumeEffect effect) {\n            pumpkinEffects.add(effect);\n            return this;\n        }\n\n        public Consumable build() {\n            return new Consumable(pumpkinConsumeSeconds, pumpkinAnimation, pumpkinSound, pumpkinHasConsumeParticles, List.copyOf(pumpkinEffects));\n        }\n    }"),
])

edit('net/minecraft/world/item/component/Consumables.java', [
    ('    public static Consumable.Builder defaultDrink() {\n        throw Unimplemented.forMember("net/minecraft/world/item/component/Consumables.defaultDrink:()Lnet/minecraft/world/item/component/Consumable$Builder;");\n    }',
     '    // Pumpkin divergence: the vanilla drink defaults, minus the sound holder --\n    // Pumpkin has no SoundEvents.GENERIC_DRINK stand-in yet; null stays null rather\n    // than inventing one.\n    public static Consumable.Builder defaultDrink() {\n        return Consumable.builder().consumeSeconds(1.6F).animation(net.minecraft.world.item.ItemUseAnimation.DRINK).hasConsumeParticles(false);\n    }'),
])

edit('net/minecraft/world/item/component/Consumable.java', [
    ('    public static class Builder {\n\n        // Pumpkin divergence:',
     '    public static Consumable.Builder builder() {\n        return new Builder();\n    }\n\n    public static class Builder {\n\n        // Pumpkin divergence:'),
])

edit('net/minecraft/core/component/DataComponentType.java', [
    ('        public DataComponentType.Builder<T> cacheEncoding() {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentType$Builder.cacheEncoding:()Lnet/minecraft/core/component/DataComponentType$Builder;");\n        }',
     '        // Pumpkin divergence: an encode-speed hint; Pumpkin never encodes mod\n        // components, so there is nothing to cache. Accept and drop.\n        public DataComponentType.Builder<T> cacheEncoding() {\n            return this;\n        }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public boolean isAllowedInPeaceful() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.isAllowedInPeaceful:()Z");\n    }',
     '    public boolean pumpkinAllowedInPeaceful = true;\n\n    public boolean isAllowedInPeaceful() {\n        return pumpkinAllowedInPeaceful;\n    }'),
    ('        public EntityType.Builder<T> notInPeaceful() {\n            return this;\n        }',
     '        boolean pumpkinAllowedInPeaceful = true;\n\n        public EntityType.Builder<T> notInPeaceful() {\n            pumpkinAllowedInPeaceful = false;\n            return this;\n        }'),
    ('            type.pumpkinOnlyOpCanSetNbt = pumpkinOnlyOpCanSetNbt;',
     '            type.pumpkinOnlyOpCanSetNbt = pumpkinOnlyOpCanSetNbt;\n            type.pumpkinAllowedInPeaceful = pumpkinAllowedInPeaceful;'),
])

edit('net/minecraft/core/registries/Registries.java', [
    ('    public static final ResourceKey<Registry<EntityType<?>>> ENTITY_TYPE = pumpkinRegistryKey("entity_type");',
     '    public static final ResourceKey<Registry<EntityType<?>>> ENTITY_TYPE = pumpkinRegistryKey("entity_type");\n\n    public static final ResourceKey<Registry<net.minecraft.world.level.storage.loot.LootTable>> LOOT_TABLE = pumpkinRegistryKey("loot_table");'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public Optional<ResourceKey<LootTable>> getDefaultLootTable() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDefaultLootTable:()Ljava/util/Optional;");\n    }',
     '    // Pumpkin divergence: vanilla derives entities/<path> from the type\'s own key;\n    // builder-built types carry theirs from build(key), vanilla stand-ins from their\n    // name. A type with neither has no truthful answer and fails loudly.\n    public Optional<ResourceKey<LootTable>> pumpkinLootTable;\n\n    public Optional<ResourceKey<LootTable>> getDefaultLootTable() {\n        if (pumpkinLootTable != null) {\n            return pumpkinLootTable;\n        }\n        if (pumpkinVanillaName != null) {\n            return Optional.of(net.minecraft.resources.ResourceKey.create(\n                net.minecraft.core.registries.Registries.LOOT_TABLE,\n                net.minecraft.resources.Identifier.withDefaultNamespace("entities/" + pumpkinVanillaName)));\n        }\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDefaultLootTable:()Ljava/util/Optional;");\n    }'),
    ('        public EntityType.Builder<T> noLootTable() {\n            return this;\n        }',
     '        boolean pumpkinNoLootTable;\n\n        public EntityType.Builder<T> noLootTable() {\n            pumpkinNoLootTable = true;\n            return this;\n        }'),
    ('            type.pumpkinAllowedInPeaceful = pumpkinAllowedInPeaceful;',
     '            type.pumpkinAllowedInPeaceful = pumpkinAllowedInPeaceful;\n            if (pumpkinNoLootTable) {\n                type.pumpkinLootTable = Optional.empty();\n            } else if (name != null) {\n                type.pumpkinLootTable = Optional.of(net.minecraft.resources.ResourceKey.create(\n                    net.minecraft.core.registries.Registries.LOOT_TABLE,\n                    net.minecraft.resources.Identifier.fromNamespaceAndPath(\n                        name.identifier().getNamespace(), "entities/" + name.identifier().getPath())));\n            }'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public static ItemResource of(Holder<Item> holder) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/core/Holder;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public static ItemResource of(Holder<Item> holder) {\n        ItemResource resource = new ItemResource();\n        resource.pumpkinItem = holder.value();\n        return resource;\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public FeatureFlagSet requiredFeatures() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.requiredFeatures:()Lnet/minecraft/world/flag/FeatureFlagSet;");\n    }',
     '    // Pumpkin divergence: every reachable FeatureFlagSet is the empty set (see that\n    // class); the answer is the one set that exists.\n    public FeatureFlagSet requiredFeatures() {\n        return FeatureFlagSet.of();\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('        private FeatureFlagSet requiredFeatures;',
     '        // Pumpkin divergence: NeoForge access-transforms this field public.\n        public FeatureFlagSet requiredFeatures;'),
])

edit('net/neoforged/neoforge/transfer/access/ItemAccess.java', [
    ('    default <T> T getCapability(ItemCapability<T, ItemAccess> capability) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/access/ItemAccess.getCapability:(Lnet/neoforged/neoforge/capabilities/ItemCapability;)Ljava/lang/Object;");\n    }',
     '    // Pumpkin divergence: truthful absence -- no capability provider is ever\n    // registered with Pumpkin (RegisterCapabilitiesEvent does not accept them), so\n    // every lookup answers null, the NeoForge contract for "no provider".\n    default <T> T getCapability(ItemCapability<T, ItemAccess> capability) {\n        return null;\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    private final TagKey<Block> immuneTo = null;',
     '    // Pumpkin divergence: NeoForge access-transforms this field public.\n    public TagKey<Block> immuneTo;'),
    ('        public EntityType.Builder<T> immuneTo(TagKey<Block> tag) {\n            return this;\n        }',
     '        public EntityType.Builder<T> immuneTo(TagKey<Block> tag) {\n            immuneTo = tag;\n            return this;\n        }'),
    ('            type.pumpkinAllowedInPeaceful = pumpkinAllowedInPeaceful;\n            if (pumpkinNoLootTable) {',
     '            type.pumpkinAllowedInPeaceful = pumpkinAllowedInPeaceful;\n            type.immuneTo = immuneTo;\n            if (pumpkinNoLootTable) {'),
])

edit('net/neoforged/neoforge/transfer/fluid/FluidResource.java', [
    ('    public static FluidResource of(Fluid fluid) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.of:(Lnet/minecraft/world/level/material/Fluid;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");\n    }',
     '    public static FluidResource of(Fluid fluid) {\n        FluidResource resource = new FluidResource();\n        resource.pumpkinFluid = fluid;\n        return resource;\n    }'),
    ('    public static FluidResource of(Holder<Fluid> fluid) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/fluid/FluidResource.of:(Lnet/minecraft/core/Holder;)Lnet/neoforged/neoforge/transfer/fluid/FluidResource;");\n    }',
     '    public static FluidResource of(Holder<Fluid> fluid) {\n        return of(fluid.value());\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    private final int clientTrackingRange = 0;\n\n    private final int updateInterval = 0;\n\n    private final float spawnDimensionsScale = 0.0F;',
     '    // Pumpkin divergence: NeoForge access-transforms these fields public; the\n    // initial values are the vanilla builder defaults.\n    public int clientTrackingRange = 5;\n\n    public int updateInterval = 3;\n\n    public float spawnDimensionsScale = 1.0F;'),
    ('        public EntityType.Builder<T> spawnDimensionsScale(float scale) {\n            return this;\n        }',
     '        public EntityType.Builder<T> spawnDimensionsScale(float scale) {\n            spawnDimensionsScale = scale;\n            return this;\n        }'),
    ('        public EntityType.Builder<T> clientTrackingRange(int clientChunkRange) {\n            return this;\n        }',
     '        public EntityType.Builder<T> clientTrackingRange(int clientChunkRange) {\n            clientTrackingRange = clientChunkRange;\n            return this;\n        }'),
    ('        public EntityType.Builder<T> updateInterval(int updateInterval) {\n            return this;\n        }',
     '        public EntityType.Builder<T> updateInterval(int updateInterval) {\n            this.updateInterval = updateInterval;\n            return this;\n        }'),
    ('        private int clientTrackingRange;\n\n        private int updateInterval;\n\n        private float spawnDimensionsScale;',
     '        private int clientTrackingRange = 5;\n\n        private int updateInterval = 3;\n\n        private float spawnDimensionsScale = 1.0F;'),
    ('            type.immuneTo = immuneTo;',
     '            type.immuneTo = immuneTo;\n            type.clientTrackingRange = clientTrackingRange;\n            type.updateInterval = updateInterval;\n            type.spawnDimensionsScale = spawnDimensionsScale;'),
])

edit('net/minecraft/core/HolderLookup.java', [
    ('        default <T> HolderLookup.RegistryLookup<T> lookupOrThrow(ResourceKey<? extends Registry<? extends T>> key) {\n            throw Unimplemented.forMember("net/minecraft/core/HolderLookup$Provider.lookupOrThrow:(Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/core/HolderLookup$RegistryLookup;");\n        }',
     '        // Pumpkin divergence: composes inert -- the lookup itself carries no data, so\n        // the member actually consulted throws by name on first use.\n        @SuppressWarnings("unchecked")\n        default <T> HolderLookup.RegistryLookup<T> lookupOrThrow(ResourceKey<? extends Registry<? extends T>> key) {\n            return dev.pumpkin.shim.Stubs.of(HolderLookup.RegistryLookup.class,\n                "net/minecraft/core/HolderLookup$RegistryLookup(" + key.identifier() + ") via HolderLookup$Provider.lookupOrThrow");\n        }'),
])

edit('dev/pumpkin/shim/Stubs.java', [
    ('                    Object answer = answers.get(method.getName());\n                    if (answer != null) {\n                        return answer;\n                    }',
     '                    Object answer = answers.get(method.getName());\n                    if (answer instanceof Dynamic dynamic) {\n                        return dynamic.answer(args);\n                    }\n                    if (answer != null) {\n                        return answer;\n                    }'),
    ('    public static <T> T of(Class<T> iface, String owner) {\n        return of(iface, owner, java.util.Map.of());\n    }',
     '    public static <T> T of(Class<T> iface, String owner) {\n        return of(iface, owner, java.util.Map.of());\n    }\n\n    /**\n     * An answer computed on every call, for values that cannot be shared across calls --\n     * a {@link java.util.stream.Stream} is one-shot, so a stored one would break the\n     * second caller.\n     */\n    public interface Dynamic {\n        Object answer(Object[] args);\n    }'),
])

edit('net/minecraft/core/Holder.java', [
    ('        protected Reference(Holder.Reference.Type type, HolderOwner<T> owner, ResourceKey<T> key, T value) {\n        }\n\n        public ResourceKey<T> key() {\n            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.key:()Lnet/minecraft/resources/ResourceKey;");\n        }\n\n        public T value() {\n            throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.value:()Ljava/lang/Object;");\n        }',
     '        // Pumpkin divergence: a reference really carries its key and value.\n        protected Reference(Holder.Reference.Type type, HolderOwner<T> owner, ResourceKey<T> key, T value) {\n            this.key = key;\n            this.value = value;\n        }\n\n        public static <T> Holder.Reference<T> pumpkinOf(ResourceKey<T> key, T value) {\n            return new Reference<>(null, null, key, value);\n        }\n\n        public ResourceKey<T> key() {\n            return key;\n        }\n\n        public T value() {\n            if (value == null) {\n                throw Unimplemented.forMember("net/minecraft/core/Holder$Reference.value:()Ljava/lang/Object;");\n            }\n            return value;\n        }'),
])

edit('net/neoforged/neoforge/registries/DeferredHolder.java', [
    ('    private static final java.util.Map<String, DeferredHolder<?, ?>> PUMPKIN_BY_ID =',
     '    public static java.util.List<DeferredHolder<?, ?>> pumpkinAllFor(String registry) {\n        String prefix = registry + "|";\n        java.util.ArrayList<DeferredHolder<?, ?>> all = new java.util.ArrayList<>();\n        for (java.util.Map.Entry<String, DeferredHolder<?, ?>> entry : PUMPKIN_BY_ID.entrySet()) {\n            if (entry.getKey().startsWith(prefix)) {\n                all.add(entry.getValue());\n            }\n        }\n        return all;\n    }\n\n    private static final java.util.Map<String, DeferredHolder<?, ?>> PUMPKIN_BY_ID ='),
])

edit('net/minecraft/core/HolderLookup.java', [
    ('        // Pumpkin divergence: composes inert -- the lookup itself carries no data, so\n        // the member actually consulted throws by name on first use.\n        @SuppressWarnings("unchecked")\n        default <T> HolderLookup.RegistryLookup<T> lookupOrThrow(ResourceKey<? extends Registry<? extends T>> key) {\n            return dev.pumpkin.shim.Stubs.of(HolderLookup.RegistryLookup.class,\n                "net/minecraft/core/HolderLookup$RegistryLookup(" + key.identifier() + ") via HolderLookup$Provider.lookupOrThrow");\n        }',
     '        // Pumpkin divergence: answers key() and listElements() from what actually\n        // registered under that registry; every other member throws by name on use.\n        @SuppressWarnings({"unchecked", "rawtypes"})\n        default <T> HolderLookup.RegistryLookup<T> lookupOrThrow(ResourceKey<? extends Registry<? extends T>> key) {\n            return dev.pumpkin.shim.Stubs.of(HolderLookup.RegistryLookup.class,\n                "net/minecraft/core/HolderLookup$RegistryLookup(" + key.identifier() + ") via HolderLookup$Provider.lookupOrThrow",\n                java.util.Map.of(\n                    "key", key,\n                    "listElements", (dev.pumpkin.shim.Stubs.Dynamic) args ->\n                        net.neoforged.neoforge.registries.DeferredHolder.pumpkinAllFor(key.identifier().toString())\n                            .stream()\n                            .map(holder -> Holder.Reference.pumpkinOf((ResourceKey) holder.getKey(), holder.get()))));\n        }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public boolean trackDeltas() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.trackDeltas:()Z");\n    }',
     '    public boolean pumpkinTrackDeltas = true;\n\n    public boolean trackDeltas() {\n        return pumpkinTrackDeltas;\n    }'),
    ('        public EntityType.Builder<T> setShouldReceiveVelocityUpdates(boolean value) {\n            return this;\n        }',
     '        boolean pumpkinTrackDeltas = true;\n\n        public EntityType.Builder<T> setShouldReceiveVelocityUpdates(boolean value) {\n            pumpkinTrackDeltas = value;\n            return this;\n        }'),
    ('            type.spawnDimensionsScale = spawnDimensionsScale;',
     '            type.spawnDimensionsScale = spawnDimensionsScale;\n            type.pumpkinTrackDeltas = pumpkinTrackDeltas;'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public int clientTrackingRange() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.clientTrackingRange:()I");\n    }',
     '    public int clientTrackingRange() {\n        return clientTrackingRange;\n    }'),
    ('    public int updateInterval() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.updateInterval:()I");\n    }',
     '    public int updateInterval() {\n        return updateInterval;\n    }'),
])

edit('net/minecraft/core/component/DataComponentPatch.java', [
    ('    public static final DataComponentPatch EMPTY = null;',
     '    public static final DataComponentPatch EMPTY = new DataComponentPatch(null);'),
    ('    DataComponentPatch(Reference2ObjectMap<DataComponentType<?>, Optional<?>> map) {\n    }',
     '    // Pumpkin divergence: a patch really is a map -- Optional.of(value) sets,\n    // Optional.empty() removes.\n    public final java.util.LinkedHashMap<DataComponentType<?>, Optional<?>> pumpkinMap = new java.util.LinkedHashMap<>();\n\n    DataComponentPatch(Reference2ObjectMap<DataComponentType<?>, Optional<?>> map) {\n    }'),
    ('    public static DataComponentPatch.Builder builder() {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.builder:()Lnet/minecraft/core/component/DataComponentPatch$Builder;");\n    }',
     '    public static DataComponentPatch.Builder builder() {\n        return new Builder();\n    }'),
    ('    public Set<Entry<DataComponentType<?>, Optional<?>>> entrySet() {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.entrySet:()Ljava/util/Set;");\n    }',
     '    public Set<Entry<DataComponentType<?>, Optional<?>>> entrySet() {\n        return pumpkinMap.entrySet();\n    }'),
    ('    public int size() {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.size:()I");\n    }',
     '    public int size() {\n        return pumpkinMap.size();\n    }'),
    ('    public boolean isEmpty() {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.isEmpty:()Z");\n    }',
     '    public boolean isEmpty() {\n        return pumpkinMap.isEmpty();\n    }'),
    ('    public boolean equals(Object obj) {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.equals:(Ljava/lang/Object;)Z");\n    }',
     '    public boolean equals(Object obj) {\n        return obj instanceof DataComponentPatch other && pumpkinMap.equals(other.pumpkinMap);\n    }'),
    ('    public int hashCode() {\n        throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch.hashCode:()I");\n    }',
     '    public int hashCode() {\n        return pumpkinMap.hashCode();\n    }'),
    ('        public <T> DataComponentPatch.Builder set(DataComponentType<T> type, T value) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.set:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");\n        }',
     '        final DataComponentPatch pumpkinPatch = new DataComponentPatch(null);\n\n        public <T> DataComponentPatch.Builder set(DataComponentType<T> type, T value) {\n            pumpkinPatch.pumpkinMap.put(type, Optional.of(value));\n            return this;\n        }'),
    ('        public <T> DataComponentPatch.Builder remove(DataComponentType<T> type) {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.remove:(Lnet/minecraft/core/component/DataComponentType;)Lnet/minecraft/core/component/DataComponentPatch$Builder;");\n        }',
     '        public <T> DataComponentPatch.Builder remove(DataComponentType<T> type) {\n            pumpkinPatch.pumpkinMap.put(type, Optional.empty());\n            return this;\n        }'),
    ('        public DataComponentPatch build() {\n            throw Unimplemented.forMember("net/minecraft/core/component/DataComponentPatch$Builder.build:()Lnet/minecraft/core/component/DataComponentPatch;");\n        }',
     '        public DataComponentPatch build() {\n            return pumpkinPatch;\n        }'),
])

edit('net/minecraft/network/chat/contents/TranslatableContents.java', [
    ('    public static boolean isAllowedPrimitiveArgument(Object object) {\n        throw Unimplemented.forMember("net/minecraft/network/chat/contents/TranslatableContents.isAllowedPrimitiveArgument:(Ljava/lang/Object;)Z");\n    }',
     '    // Pumpkin divergence: vanilla body.\n    public static boolean isAllowedPrimitiveArgument(Object object) {\n        return object instanceof Number || object instanceof Boolean || object instanceof String;\n    }'),
])

edit('net/minecraft/world/entity/EntityDimensions.java', [
    ('    public EntityDimensions scale(float scaleFactor) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityDimensions.scale:(F)Lnet/minecraft/world/entity/EntityDimensions;");\n    }\n\n    public EntityDimensions scale(float widthScaleFactor, float heightScaleFactor) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityDimensions.scale:(FF)Lnet/minecraft/world/entity/EntityDimensions;");\n    }',
     '    // Pumpkin divergence: vanilla math -- eyeHeight is the LivingEntity default\n    // (0.85 * height); attachments stay null, the one part Pumpkin does not model.\n    public static EntityDimensions scalable(float width, float height) {\n        return new EntityDimensions(width, height, height * 0.85F, null, false);\n    }\n\n    public EntityDimensions scale(float scaleFactor) {\n        return scale(scaleFactor, scaleFactor);\n    }\n\n    public EntityDimensions scale(float widthScaleFactor, float heightScaleFactor) {\n        if (fixed || (widthScaleFactor == 1.0F && heightScaleFactor == 1.0F)) {\n            return this;\n        }\n        return new EntityDimensions(width * widthScaleFactor, height * heightScaleFactor, eyeHeight * heightScaleFactor, null, false);\n    }'),
])

edit('net/minecraft/world/entity/EntityType.java', [
    ('    public EntityDimensions getDimensions() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDimensions:()Lnet/minecraft/world/entity/EntityDimensions;");\n    }',
     '    public EntityDimensions pumpkinDimensions;\n\n    public EntityDimensions getDimensions() {\n        if (pumpkinDimensions == null) {\n            throw Unimplemented.forMember("net/minecraft/world/entity/EntityType.getDimensions:()Lnet/minecraft/world/entity/EntityDimensions;");\n        }\n        return pumpkinDimensions;\n    }'),
    ('        public EntityType.Builder<T> sized(float width, float height) {\n            return this;\n        }',
     '        EntityDimensions pumpkinDimensions = EntityDimensions.scalable(0.6F, 1.8F);\n\n        public EntityType.Builder<T> sized(float width, float height) {\n            pumpkinDimensions = EntityDimensions.scalable(width, height);\n            return this;\n        }'),
    ('            type.pumpkinTrackDeltas = pumpkinTrackDeltas;',
     '            type.pumpkinTrackDeltas = pumpkinTrackDeltas;\n            type.pumpkinDimensions = pumpkinDimensions;'),
])

edit('net/minecraft/world/entity/EntityTypes.java', [
    ('    public static final EntityType<Bogged> BOGGED = pumpkinVanilla("bogged", MobCategory.MONSTER);',
     '    public static final EntityType<Bogged> BOGGED = pumpkinVanillaSized("bogged", MobCategory.MONSTER, 0.6F, 1.99F);\n\n    // Pumpkin divergence: vanilla fact -- the mob\'s real hitbox size.\n    private static <T extends Entity> EntityType<T> pumpkinVanillaSized(String name, MobCategory category, float width, float height) {\n        EntityType<T> type = pumpkinVanilla(name, category);\n        type.pumpkinDimensions = EntityDimensions.scalable(width, height);\n        return type;\n    }'),
    ('    public static final EntityType<Creeper> CREEPER = pumpkinVanilla("creeper", MobCategory.MONSTER);',
     '    public static final EntityType<Creeper> CREEPER = pumpkinVanillaSized("creeper", MobCategory.MONSTER, 0.6F, 1.7F);'),
    ('    public static final EntityType<EnderMan> ENDERMAN = pumpkinVanilla("enderman", MobCategory.MONSTER);',
     '    public static final EntityType<EnderMan> ENDERMAN = pumpkinVanillaSized("enderman", MobCategory.MONSTER, 0.6F, 2.9F);'),
    ('    public static final EntityType<Parched> PARCHED = pumpkinVanilla("parched", MobCategory.MONSTER);',
     '    public static final EntityType<Parched> PARCHED = pumpkinVanillaSized("parched", MobCategory.MONSTER, 0.6F, 1.99F);'),
    ('    public static final EntityType<Skeleton> SKELETON = pumpkinVanilla("skeleton", MobCategory.MONSTER);',
     '    public static final EntityType<Skeleton> SKELETON = pumpkinVanillaSized("skeleton", MobCategory.MONSTER, 0.6F, 1.99F);'),
    ('    public static final EntityType<Stray> STRAY = pumpkinVanilla("stray", MobCategory.MONSTER);',
     '    public static final EntityType<Stray> STRAY = pumpkinVanillaSized("stray", MobCategory.MONSTER, 0.6F, 1.99F);'),
    ('    public static final EntityType<WitherSkeleton> WITHER_SKELETON = pumpkinVanillaFireImmune("wither_skeleton", MobCategory.MONSTER);',
     '    public static final EntityType<WitherSkeleton> WITHER_SKELETON = pumpkinVanillaFireImmune("wither_skeleton", MobCategory.MONSTER);\n    static {\n        WITHER_SKELETON.pumpkinDimensions = EntityDimensions.scalable(0.7F, 2.4F);\n    }'),
])

edit('net/minecraft/world/entity/EntityAttachments.java', [
    ('    private final Map<EntityAttachment, List<Vec3>> attachments = null;\n\n    private EntityAttachments(Map<EntityAttachment, List<Vec3>> attachments) {\n    }',
     '    // Pumpkin divergence: NeoForge access-transforms this field public; the map is\n    // real, and an EntityAttachments built without points carries the empty map.\n    public final Map<EntityAttachment, List<Vec3>> attachments;\n\n    private EntityAttachments(Map<EntityAttachment, List<Vec3>> attachments) {\n        this.attachments = attachments;\n    }\n\n    public static EntityAttachments pumpkinEmpty() {\n        return new EntityAttachments(Map.of());\n    }'),
    ('    public EntityAttachments() {\n    }',
     '    public EntityAttachments() {\n        this(Map.of());\n    }'),
])

edit('net/minecraft/world/entity/EntityDimensions.java', [
    ('    // Pumpkin divergence: vanilla math -- eyeHeight is the LivingEntity default\n    // (0.85 * height); attachments stay null, the one part Pumpkin does not model.\n    public static EntityDimensions scalable(float width, float height) {\n        return new EntityDimensions(width, height, height * 0.85F, null, false);\n    }',
     '    // Pumpkin divergence: vanilla math -- eyeHeight is the LivingEntity default\n    // (0.85 * height); attachment points are not modeled, so the map is real but empty.\n    public static EntityDimensions scalable(float width, float height) {\n        return new EntityDimensions(width, height, height * 0.85F, EntityAttachments.pumpkinEmpty(), false);\n    }'),
    ('        return new EntityDimensions(width * widthScaleFactor, height * heightScaleFactor, eyeHeight * heightScaleFactor, null, false);',
     '        return new EntityDimensions(width * widthScaleFactor, height * heightScaleFactor, eyeHeight * heightScaleFactor, EntityAttachments.pumpkinEmpty(), false);'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public static ItemResource of(ItemLike item) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/world/level/ItemLike;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public static ItemResource of(ItemLike item) {\n        ItemResource resource = new ItemResource();\n        resource.pumpkinItem = item;\n        return resource;\n    }'),
])

edit('net/minecraft/world/item/CreativeModeTab.java', [
    ('        public final CreativeModeTab.Builder withTabsBefore(net.minecraft.resources.ResourceKey<CreativeModeTab>... tabs) {\n            throw Unimplemented.forMember("net/minecraft/world/item/CreativeModeTab$Builder.withTabsBefore:([Lnet/minecraft/resources/ResourceKey;)Lnet/minecraft/world/item/CreativeModeTab$Builder;");\n        }',
     '        // Pumpkin divergence: tab presentation, accepted and dropped; chain lives.\n        public final CreativeModeTab.Builder withTabsBefore(net.minecraft.resources.ResourceKey<CreativeModeTab>... tabs) {\n            return this;\n        }'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    private ItemLike pumpkinItem;',
     '    private ItemLike pumpkinItem;\n\n    // Pumpkin divergence: the component patch is data the resource carries.\n    private DataComponentPatch pumpkinPatch = DataComponentPatch.EMPTY;\n\n    private ItemResource pumpkinWith(DataComponentPatch patch) {\n        ItemResource resource = new ItemResource();\n        resource.pumpkinItem = pumpkinItem;\n        resource.pumpkinPatch = patch;\n        return resource;\n    }'),
    ('    public static ItemResource of(ItemLike item, DataComponentPatch patch) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/world/level/ItemLike;Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public static ItemResource of(ItemLike item, DataComponentPatch patch) {\n        return of(item).pumpkinWith(patch);\n    }'),
    ('    public static ItemResource of(Holder<Item> holder, DataComponentPatch patch) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.of:(Lnet/minecraft/core/Holder;Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public static ItemResource of(Holder<Item> holder, DataComponentPatch patch) {\n        return of(holder).pumpkinWith(patch);\n    }'),
    ('    public boolean isComponentsPatchEmpty() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.isComponentsPatchEmpty:()Z");\n    }',
     '    public boolean isComponentsPatchEmpty() {\n        return pumpkinPatch.isEmpty();\n    }'),
    ('    public ItemResource withMergedPatch(DataComponentPatch patch) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.withMergedPatch:(Lnet/minecraft/core/component/DataComponentPatch;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public ItemResource withMergedPatch(DataComponentPatch patch) {\n        DataComponentPatch.Builder merged = DataComponentPatch.builder();\n        DataComponentPatch built = merged.build();\n        built.pumpkinMap.putAll(pumpkinPatch.pumpkinMap);\n        built.pumpkinMap.putAll(patch.pumpkinMap);\n        return pumpkinWith(built);\n    }'),
    ('    public <D> ItemResource with(DataComponentType<D> type, D data) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.with:(Lnet/minecraft/core/component/DataComponentType;Ljava/lang/Object;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public <D> ItemResource with(DataComponentType<D> type, D data) {\n        return withMergedPatch(DataComponentPatch.builder().set(type, data).build());\n    }'),
    ('    public <D> ItemResource with(Supplier<? extends DataComponentType<D>> type, D data) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.with:(Ljava/util/function/Supplier;Ljava/lang/Object;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public <D> ItemResource with(Supplier<? extends DataComponentType<D>> type, D data) {\n        return with(type.get(), data);\n    }'),
    ('    public ItemResource without(DataComponentType<?> type) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.without:(Lnet/minecraft/core/component/DataComponentType;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public ItemResource without(DataComponentType<?> type) {\n        return withMergedPatch(DataComponentPatch.builder().remove(type).build());\n    }'),
    ('    public ItemResource without(Supplier<? extends DataComponentType<?>> type) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.without:(Ljava/util/function/Supplier;)Lnet/neoforged/neoforge/transfer/item/ItemResource;");\n    }',
     '    public ItemResource without(Supplier<? extends DataComponentType<?>> type) {\n        return without(type.get());\n    }'),
    ('    public DataComponentPatch getComponentsPatch() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getComponentsPatch:()Lnet/minecraft/core/component/DataComponentPatch;");\n    }',
     '    public DataComponentPatch getComponentsPatch() {\n        return pumpkinPatch;\n    }'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public ItemStack toStack() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.toStack:()Lnet/minecraft/world/item/ItemStack;");\n    }',
     '    public ItemStack toStack() {\n        return toStack(1);\n    }'),
])

edit('net/minecraft/world/level/levelgen/blockpredicates/BlockPredicate.java', [
    ('    Codec<BlockPredicate> CODEC = null;',
     '    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it at\n    // class-init; it throws by name on first real use.\n    Codec<BlockPredicate> CODEC = dev.pumpkin.shim.Stubs.throwingCodec("net/minecraft/world/level/levelgen/blockpredicates/BlockPredicate.CODEC");'),
])

edit('net/minecraft/util/valueproviders/IntProviders.java', [
    ('    static {\n        if (true) {\n            throw Unimplemented.forMember("net/minecraft/util/valueproviders/IntProviders");\n        }\n    }\n',
     '    // Pumpkin divergence: no throwing clinit -- the one static is already an inert\n    // throwing codec, so the class composes and fails by name on first real use.\n'),
    ('import dev.pumpkin.shim.Unimplemented;\n', ''),
])

edit('net/minecraft/core/component/DataComponentMap.java', [
    ('    Codec<DataComponentMap> CODEC = null;',
     "    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it\n    // at class-init; it throws by name on first real use.\n    Codec<DataComponentMap> CODEC = dev.pumpkin.shim.Stubs.throwingCodec(\"net/minecraft/core/component/DataComponentMap.CODEC\");"),
])

edit('net/minecraft/core/component/predicates/DataComponentPredicate.java', [
    ('    Codec<Map<DataComponentPredicate.Type<?>, DataComponentPredicate>> CODEC = null;',
     "    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it\n    // at class-init; it throws by name on first real use.\n    Codec<Map<DataComponentPredicate.Type<?>, DataComponentPredicate>> CODEC = dev.pumpkin.shim.Stubs.throwingCodec(\"net/minecraft/core/component/predicates/DataComponentPredicate.CODEC\");"),
])

edit('net/minecraft/world/item/crafting/Recipe.java', [
    ('    Codec<Recipe<?>> CODEC = null;',
     "    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it\n    // at class-init; it throws by name on first real use.\n    Codec<Recipe<?>> CODEC = dev.pumpkin.shim.Stubs.throwingCodec(\"net/minecraft/world/item/crafting/Recipe.CODEC\");"),
])

edit('net/minecraft/world/item/crafting/display/SlotDisplay.java', [
    ('    Codec<SlotDisplay> CODEC = null;',
     "    // Pumpkin divergence: a throwing codec, not null -- DFU composes through it\n    // at class-init; it throws by name on first real use.\n    Codec<SlotDisplay> CODEC = dev.pumpkin.shim.Stubs.throwingCodec(\"net/minecraft/world/item/crafting/display/SlotDisplay.CODEC\");"),
])

edit('net/minecraft/core/BlockPos.java', [
    ('    public static int getX(long blockNode) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.getX:(J)I");\n    }',
     '    // Pumpkin divergence: the vanilla 26/12/26 bit layout, real math throughout.\n    public static int getX(long blockNode) {\n        return (int) (blockNode << 0 >> 38);\n    }'),
    ('    public long asLong() {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.asLong:()J");\n    }',
     '    public long asLong() {\n        return asLong(getX(), getY(), getZ());\n    }'),
    ('    public static long asLong(int x, int y, int z) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.asLong:(III)J");\n    }',
     '    public static long asLong(int x, int y, int z) {\n        return ((long) (x & 0x3FFFFFF) << 38) | ((long) (z & 0x3FFFFFF) << 12) | (y & 0xFFF);\n    }'),
])

edit('net/minecraft/core/BlockPos.java', [
    ('    public static int getY(long blockNode) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.getY:(J)I");\n    }',
     '    public static int getY(long blockNode) {\n        return (int) (blockNode << 52 >> 52);\n    }'),
    ('    public static int getZ(long blockNode) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.getZ:(J)I");\n    }',
     '    public static int getZ(long blockNode) {\n        return (int) (blockNode << 26 >> 38);\n    }'),
    ('    public static BlockPos of(long blockNode) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.of:(J)Lnet/minecraft/core/BlockPos;");\n    }',
     '    public static BlockPos of(long blockNode) {\n        return new BlockPos(getX(blockNode), getY(blockNode), getZ(blockNode));\n    }'),
    ('    public static long offset(long blockNode, int stepX, int stepY, int stepZ) {\n        throw Unimplemented.forMember("net/minecraft/core/BlockPos.offset:(JIII)J");\n    }',
     '    public static long offset(long blockNode, int stepX, int stepY, int stepZ) {\n        return asLong(getX(blockNode) + stepX, getY(blockNode) + stepY, getZ(blockNode) + stepZ);\n    }'),
])

edit('net/minecraft/world/InteractionResult.java', [
    ('    InteractionResult.TryEmptyHandInteraction TRY_WITH_EMPTY_HAND = null;',
     '    // Pumpkin divergence: real instance -- the default useItemOn answer must be\n    // distinguishable so the bridge can fall through to useWithoutItem.\n    InteractionResult.TryEmptyHandInteraction TRY_WITH_EMPTY_HAND = new TryEmptyHandInteraction();'),
])

edit('dev/pumpkin/bridge/PumpkinInteractions.java', [
    ('        Method method = findMethod(block.getClass(), "useItemOn", 7);\n        method.setAccessible(true);\n        Object result = method.invoke(block, held, state, level, pos, player,\n                InteractionHand.MAIN_HAND, hit);\n',
     '        Method method = findMethod(block.getClass(), "useItemOn", 7);\n        method.setAccessible(true);\n        Object result = method.invoke(block, held, state, level, pos, player,\n                InteractionHand.MAIN_HAND, hit);\n        // Vanilla fallthrough: TRY_WITH_EMPTY_HAND means "retry without the item" --\n        // machine GUIs live in useWithoutItem.\n        if (result instanceof InteractionResult.TryEmptyHandInteraction) {\n            Method withoutItem = findMethod(block.getClass(), "useWithoutItem", 5);\n            withoutItem.setAccessible(true);\n            result = withoutItem.invoke(block, state, level, pos, player, hit);\n        }\n'),
])

edit('net/minecraft/world/level/Level.java', [
    ('    public boolean isInWorldBounds(BlockPos pos) {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.isInWorldBounds:(Lnet/minecraft/core/BlockPos;)Z");\n    }',
     '    // Pumpkin divergence: vanilla logic -- inside the height range and the 30M border.\n    public boolean isInWorldBounds(BlockPos pos) {\n        return pos.getY() >= getMinY() && pos.getY() <= getMaxY()\n                && Math.abs(pos.getX()) < 30000000 && Math.abs(pos.getZ()) < 30000000;\n    }'),
])

edit('net/minecraft/world/level/LevelHeightAccessor.java', [
    ('    default int getMaxY() {\n        throw Unimplemented.forMember("net/minecraft/world/level/LevelHeightAccessor.getMaxY:()I");\n    }',
     '    // Pumpkin divergence: vanilla derivation from the two abstract facts.\n    default int getMaxY() {\n        return getMinY() + getHeight() - 1;\n    }'),
])

edit('dev/pumpkin/bridge/PumpkinLevel.java', [
    ('    public int getHeight() {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.getHeight");\n    }',
     "    // Pumpkin divergence: the overworld's real height range -- Pumpkin only routes\n    // overworld interactions through this level today.\n    public int getHeight() {\n        return 384;\n    }"),
    ('    public int getMinY() {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.getMinY");\n    }',
     '    public int getMinY() {\n        return -64;\n    }'),
])

edit('net/minecraft/core/SectionPos.java', [
    ('    public static int blockToSectionCoord(int blockCoord) {\n        throw Unimplemented.forMember("net/minecraft/core/SectionPos.blockToSectionCoord:(I)I");\n    }',
     '    // Pumpkin divergence: vanilla bodies -- section coordinate arithmetic.\n    public static int blockToSectionCoord(int blockCoord) {\n        return blockCoord >> 4;\n    }'),
    ('    public static int blockToSectionCoord(double coord) {\n        throw Unimplemented.forMember("net/minecraft/core/SectionPos.blockToSectionCoord:(D)I");\n    }',
     '    public static int blockToSectionCoord(double coord) {\n        return blockToSectionCoord((int) Math.floor(coord));\n    }'),
    ('    public static int sectionRelative(int blockCoord) {\n        throw Unimplemented.forMember("net/minecraft/core/SectionPos.sectionRelative:(I)I");\n    }',
     '    public static int sectionRelative(int blockCoord) {\n        return blockCoord & 15;\n    }'),
    ('    public static int sectionToBlockCoord(int sectionCoord) {\n        throw Unimplemented.forMember("net/minecraft/core/SectionPos.sectionToBlockCoord:(I)I");\n    }',
     '    public static int sectionToBlockCoord(int sectionCoord) {\n        return sectionCoord << 4;\n    }'),
    ('    public static int sectionToBlockCoord(int sectionCoord, int offset) {\n        throw Unimplemented.forMember("net/minecraft/core/SectionPos.sectionToBlockCoord:(II)I");\n    }',
     '    public static int sectionToBlockCoord(int sectionCoord, int offset) {\n        return sectionToBlockCoord(sectionCoord) + offset;\n    }'),
])

edit('dev/pumpkin/bridge/PumpkinLevel.java', [
    ('    public boolean hasChunk(int chunkX, int chunkZ) {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.hasChunk");\n    }',
     '    // Pumpkin divergence: truthful for this stand-in -- the bridge only runs for\n    // interactions on loaded blocks, so the neighborhood the mod asks about is loaded.\n    public boolean hasChunk(int chunkX, int chunkZ) {\n        return true;\n    }'),
])

edit('dev/pumpkin/bridge/PumpkinInteractions.java', [
    ('    public static String useBlockOn(String blockId, String entityTypeId, int x, int y, int z,\n            String heldItemId, int heldCount, String savedData, boolean hasSignal)\n            throws Exception {\n        PumpkinLevel.pumpkinSetSignal(hasSignal);',
     '    public static String useBlockOn(String blockId, String entityTypeId, int x, int y, int z,\n            String heldItemId, int heldCount, String savedData, boolean hasSignal,\n            boolean sneaking) throws Exception {\n        PumpkinLevel.pumpkinSetSignal(hasSignal);'),
    ('        PumpkinPlayer player = new PumpkinPlayer(held, x + 0.5, y + 1.0, z + 0.5);',
     '        PumpkinPlayer player = new PumpkinPlayer(held, x + 0.5, y + 1.0, z + 0.5);\n        player.pumpkinSetSneaking(sneaking);'),
])

edit('dev/pumpkin/bridge/PumpkinPlayer.java', [
    ('    public PumpkinPlayer(ItemStack held, double x, double y, double z) {',
     '    // Pumpkin divergence: the real sneak state of the interacting player, carried\n    // over the bridge. Crouching and shift answer the same fact here: the stand-in\n    // has no pose model to separate them.\n    private boolean pumpkinSneaking;\n\n    public void pumpkinSetSneaking(boolean sneaking) {\n        this.pumpkinSneaking = sneaking;\n    }\n\n    public boolean isShiftKeyDown() {\n        return pumpkinSneaking;\n    }\n\n    public boolean isCrouching() {\n        return pumpkinSneaking;\n    }\n\n    public PumpkinPlayer(ItemStack held, double x, double y, double z) {'),
])

edit('dev/pumpkin/bridge/PumpkinPlayer.java', [
    ('    public PumpkinPlayer(ItemStack held, double x, double y, double z) {',
     '    // Pumpkin divergence: the player lives in the shared one-interaction level.\n    public net.minecraft.world.level.Level level() {\n        return PumpkinInteractions.pumpkinLevel();\n    }\n\n    public PumpkinPlayer(ItemStack held, double x, double y, double z) {'),
])

edit('dev/pumpkin/bridge/PumpkinInteractions.java', [
    ('    public static String useBlockOn(String blockId, String entityTypeId, int x, int y, int z,\n            String heldItemId, int heldCount, String savedData, boolean hasSignal,\n            boolean sneaking) throws Exception {',
     '    public static String useBlockOn(String blockId, String entityTypeId, int x, int y, int z,\n            String heldItemId, int heldCount, String savedData, boolean hasSignal,\n            boolean sneaking, String playerUuid) throws Exception {'),
    ('        player.pumpkinSetSneaking(sneaking);',
     '        player.pumpkinSetSneaking(sneaking);\n        if (!playerUuid.isEmpty()) {\n            player.pumpkinSetUuid(java.util.UUID.fromString(playerUuid));\n        }'),
])

edit('dev/pumpkin/bridge/PumpkinPlayer.java', [
    ('    // Pumpkin divergence: the player lives in the shared one-interaction level.',
     '    // Pumpkin divergence: the real UUID of the interacting player, carried over the\n    // bridge -- mod machines record it as the owner.\n    private java.util.UUID pumpkinUuid;\n\n    public void pumpkinSetUuid(java.util.UUID uuid) {\n        this.pumpkinUuid = uuid;\n    }\n\n    public java.util.UUID getUUID() {\n        if (pumpkinUuid == null) {\n            throw dev.pumpkin.shim.Unimplemented.forMember(\n                "net/minecraft/world/entity/Entity.getUUID:()Ljava/util/UUID; (no player on this interaction)");\n        }\n        return pumpkinUuid;\n    }\n\n    // Pumpkin divergence: the player lives in the shared one-interaction level.'),
])

edit('net/minecraft/world/level/block/entity/BlockEntity.java', [
    ('    // Pumpkin divergence: the position is kept; getBlockPos answers with it.\n    private BlockPos pumpkinPosition;\n\n    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {\n        this.pumpkinPosition = worldPosition;\n    }',
     '    // Pumpkin divergence: position and state are kept; the getters answer with them.\n    private BlockPos pumpkinPosition;\n\n    private BlockState pumpkinBlockState;\n\n    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {\n        this.pumpkinPosition = worldPosition;\n        this.pumpkinBlockState = blockState;\n    }'),
    ('    public BlockState getBlockState() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getBlockState:()Lnet/minecraft/world/level/block/state/BlockState;");\n    }',
     '    public BlockState getBlockState() {\n        if (pumpkinBlockState == null) {\n            throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.getBlockState:()Lnet/minecraft/world/level/block/state/BlockState; (entity built without a state)");\n        }\n        return pumpkinBlockState;\n    }'),
    ('    public void setBlockState(BlockState blockState) {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.setBlockState:(Lnet/minecraft/world/level/block/state/BlockState;)V");\n    }',
     '    public void setBlockState(BlockState blockState) {\n        this.pumpkinBlockState = blockState;\n    }'),
    ('    public boolean hasLevel() {\n        throw Unimplemented.forMember("net/minecraft/world/level/block/entity/BlockEntity.hasLevel:()Z");\n    }',
     '    public boolean hasLevel() {\n        return level != null;\n    }'),
])

edit('dev/pumpkin/bridge/PumpkinBlockEntities.java', [
    ('    public static BlockEntity getOrCreate(BlockEntityType<?> type, int x, int y, int z) {\n        return BY_POSITION.computeIfAbsent(key(x, y, z), ignored -> {\n            BlockEntity entity = type.pumpkinCreate(new BlockPos(x, y, z), null);\n            entity.pumpkinSetLevel(PumpkinInteractions.pumpkinLevel());\n            return entity;\n        });\n    }',
     "    public static BlockEntity getOrCreate(BlockEntityType<?> type, int x, int y, int z) {\n        return getOrCreate(type, x, y, z, null);\n    }\n\n    // Pumpkin divergence: the block's state travels in so the entity can answer\n    // getBlockState() -- mod machines read their own facing/active from it.\n    public static BlockEntity getOrCreate(BlockEntityType<?> type, int x, int y, int z,\n            net.minecraft.world.level.block.state.BlockState state) {\n        return BY_POSITION.computeIfAbsent(key(x, y, z), ignored -> {\n            BlockEntity entity = type.pumpkinCreate(new BlockPos(x, y, z), state);\n            entity.pumpkinSetLevel(PumpkinInteractions.pumpkinLevel());\n            return entity;\n        });\n    }"),
])

edit('dev/pumpkin/bridge/PumpkinInteractions.java', [
    ('        net.minecraft.world.level.block.entity.BlockEntity blockEntity = null;\n        if (!entityTypeId.isEmpty()\n                && DeferredHolder.pumpkinResolve("minecraft:block_entity_type", entityTypeId)\n                        instanceof BlockEntityType<?> type) {\n            boolean existed = PumpkinBlockEntities.exists(x, y, z);\n            blockEntity = PumpkinBlockEntities.getOrCreate(type, x, y, z);',
     '        net.minecraft.world.level.block.entity.BlockEntity blockEntity = null;\n        if (!entityTypeId.isEmpty()\n                && DeferredHolder.pumpkinResolve("minecraft:block_entity_type", entityTypeId)\n                        instanceof BlockEntityType<?> type) {\n            boolean existed = PumpkinBlockEntities.exists(x, y, z);\n            blockEntity = PumpkinBlockEntities.getOrCreate(type, x, y, z, block.defaultBlockState());'),
])

edit('net/neoforged/neoforge/common/extensions/ILevelExtension.java', [
    ('    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, C context) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Ljava/lang/Object;)Ljava/lang/Object;");\n    }',
     '    // Pumpkin divergence: truthful absence -- no capability provider is ever\n    // registered with Pumpkin (RegisterCapabilitiesEvent does not accept them), so\n    // every lookup answers null, the NeoForge contract for "no provider".\n    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, C context) {\n        return null;\n    }'),
    ('    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, BlockState state, BlockEntity blockEntity, C context) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ILevelExtension.getCapability:(Lnet/neoforged/neoforge/capabilities/BlockCapability;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/block/entity/BlockEntity;Ljava/lang/Object;)Ljava/lang/Object;");\n    }',
     '    default <T, C extends Object> T getCapability(BlockCapability<T, C> cap, BlockPos pos, BlockState state, BlockEntity blockEntity, C context) {\n        return null;\n    }'),
])

edit('shim/src/main/java/dev/pumpkin/bridge/PumpkinPlayer.java', [
    ('    // Pumpkin divergence: the player lives in the shared one-interaction level.',
     '    // Pumpkin divergence: the hands hold what the bridge was told -- the real held\n    // stack in the main hand, nothing in the off hand.\n    public ItemStack getMainHandItem() {\n        return held;\n    }\n\n    public ItemStack getOffhandItem() {\n        return ItemStack.EMPTY;\n    }\n\n    // Pumpkin divergence: the player lives in the shared one-interaction level.'),
])

edit('net/minecraft/world/Nameable.java', [
    ('    default String getPlainTextName() {\n        throw Unimplemented.forMember("net/minecraft/world/Nameable.getPlainTextName:()Ljava/lang/String;");\n    }\n\n    default boolean hasCustomName() {\n        throw Unimplemented.forMember("net/minecraft/world/Nameable.hasCustomName:()Z");\n    }\n\n    default Component getDisplayName() {\n        throw Unimplemented.forMember("net/minecraft/world/Nameable.getDisplayName:()Lnet/minecraft/network/chat/Component;");\n    }\n\n    default Component getCustomName() {\n        throw Unimplemented.forMember("net/minecraft/world/Nameable.getCustomName:()Lnet/minecraft/network/chat/Component;");\n    }',
     '    // Pumpkin divergence: the vanilla default bodies -- no custom name unless a\n    // subclass carries one.\n    default String getPlainTextName() {\n        return getName().getString();\n    }\n\n    default boolean hasCustomName() {\n        return getCustomName() != null;\n    }\n\n    default Component getDisplayName() {\n        return getName();\n    }\n\n    default Component getCustomName() {\n        return null;\n    }'),
])

edit('net/minecraft/util/Util.java', [
    ('    public static String makeDescriptionId(String prefix, Identifier location) {\n        throw Unimplemented.forMember("net/minecraft/util/Util.makeDescriptionId:(Ljava/lang/String;Lnet/minecraft/resources/Identifier;)Ljava/lang/String;");\n    }',
     '    // Pumpkin divergence: vanilla body.\n    public static String makeDescriptionId(String prefix, Identifier location) {\n        return location == null\n                ? prefix + ".unregistered_sadface"\n                : prefix + "." + location.getNamespace() + "." + location.getPath().replace(\'/\', \'.\');\n    }'),
])

edit('shim/src/main/java/dev/pumpkin/bridge/PumpkinPlayer.java'.split('java/',1)[1], [
    ('    public java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider provider,\n            net.minecraft.core.BlockPos pos) {',
     "    public java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider provider,\n            java.util.function.Consumer<net.minecraft.network.RegistryFriendlyByteBuf> extraData) {\n        // NeoForge's extra-data overload: the buffer feeds the client-side menu ctor,\n        // which Pumpkin never runs -- the server menu is what matters here.\n        return openMenu(provider);\n    }\n\n    public java.util.OptionalInt openMenu(net.minecraft.world.MenuProvider provider,\n            net.minecraft.core.BlockPos pos) {"),
])

edit('net/minecraft/world/entity/player/Inventory.java', [
    ('    public final Player player = null;\n\n    public Inventory(Player player, EntityEquipment equipment) {',
     '    public final Player player;\n\n    public Inventory(Player player, EntityEquipment equipment) {\n        this.player = player;'),
])

edit('net/minecraft/world/entity/player/Inventory.java', [
    ('    public Inventory() {\n    }',
     '    public Inventory() {\n        this(null, null);\n    }'),
])

edit('dev/pumpkin/bridge/PumpkinPlayer.java', [
    ('    private final net.minecraft.world.entity.player.Inventory inventory =\n            new net.minecraft.world.entity.player.Inventory();',
     '    // Pumpkin divergence: the inventory really belongs to this player -- mod menus\n    // reach the level through inv.player.\n    private final net.minecraft.world.entity.player.Inventory inventory =\n            new net.minecraft.world.entity.player.Inventory(this, null);'),
])

edit('net/minecraft/world/entity/player/Inventory.java', [
    ('    public int getSelectedSlot() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getSelectedSlot:()I");\n    }',
     '    // Pumpkin divergence: the bridge models the held stack in hotbar slot 0.\n    public int getSelectedSlot() {\n        return 0;\n    }'),
    ('    public static int getSelectionSize() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getSelectionSize:()I");\n    }',
     '    // Pumpkin divergence: vanilla fact -- the hotbar is 9 wide.\n    public static int getSelectionSize() {\n        return 9;\n    }'),
    ('    public NonNullList<ItemStack> getNonEquipmentItems() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getNonEquipmentItems:()Lnet/minecraft/core/NonNullList;");\n    }',
     '    public NonNullList<ItemStack> getNonEquipmentItems() {\n        return pumpkinItems;\n    }'),
    ('    public int getContainerSize() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.getContainerSize:()I");\n    }',
     '    public int getContainerSize() {\n        return pumpkinItems.size();\n    }'),
    ('    public boolean stillValid(Player player) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/player/Inventory.stillValid:(Lnet/minecraft/world/entity/player/Player;)Z");\n    }',
     '    public boolean stillValid(Player player) {\n        return true;\n    }'),
])

edit('net/minecraft/world/entity/Entity.java', [
    ('    public boolean equals(Object obj) {\n        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.equals:(Ljava/lang/Object;)Z");\n    }\n\n    public int hashCode() {\n        throw Unimplemented.forMember("net/minecraft/world/entity/Entity.hashCode:()I");\n    }',
     '    // Pumpkin divergence: identity semantics -- vanilla keys on the entity id, which\n    // stand-ins do not carry; identity is the honest equivalent.\n    public boolean equals(Object obj) {\n        return this == obj;\n    }\n\n    public int hashCode() {\n        return System.identityHashCode(this);\n    }'),
])

edit('net/neoforged/neoforge/common/extensions/ItemInstanceExtension.java', [
    ('    default boolean canPerformAction(ItemAbility itemAbility) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ItemInstanceExtension.canPerformAction:(Lnet/neoforged/neoforge/common/ItemAbility;)Z");\n    }',
     '    // Pumpkin divergence: NeoForge\'s own default -- the stack asks its item, so a\n    // mod tool that overrides Item.canPerformAction still answers for itself. Only\n    // ItemStack carries an item here; any other carrier fails loudly.\n    default boolean canPerformAction(ItemAbility itemAbility) {\n        if (this instanceof net.minecraft.world.item.ItemStack self) {\n            return self.getItem().canPerformAction(self, itemAbility);\n        }\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/ItemInstanceExtension.canPerformAction:(Lnet/neoforged/neoforge/common/ItemAbility;)Z");\n    }'),
])

edit('net/neoforged/neoforge/common/extensions/IItemExtension.java', [
    ('    default boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/extensions/IItemExtension.canPerformAction:(Lnet/minecraft/world/item/ItemInstance;Lnet/neoforged/neoforge/common/ItemAbility;)Z");\n    }',
     "    // Pumpkin divergence: NeoForge's own default -- a plain item performs no action.\n    default boolean canPerformAction(ItemInstance stack, ItemAbility itemAbility) {\n        return false;\n    }"),
])

edit('net/minecraft/world/item/ItemStack.java', [
    ('public final class ItemStack implements DataComponentHolder, ItemInstance, IItemStackExtension, MutableDataComponentHolder {',
     'public final class ItemStack implements DataComponentHolder, ItemInstance, IItemStackExtension, MutableDataComponentHolder {\n\n    // Pumpkin divergence: tag membership answered from the real tag tables (mod\n    // datapacks + vanilla, via PumpkinTags); an unregistered item wears no tags.\n    @Override\n    public boolean is(net.minecraft.tags.TagKey<Item> tag) {\n        Item item = getItem();\n        String id = item == null ? null : item.pumpkinRegisteredId();\n        return id != null && dev.pumpkin.bridge.PumpkinTags.contains(tag.location().toString(), id);\n    }'),
])

edit('net/minecraft/world/inventory/Slot.java', [
    ('    public int getSlotIndex() {\n        throw Unimplemented.forMember("net/minecraft/world/inventory/Slot.getSlotIndex:()I");\n    }',
     "    // Pumpkin divergence: NeoForge's own accessor -- the index inside the backing\n    // container, which the ctor stored.\n    public int getSlotIndex() {\n        return pumpkinContainerSlot;\n    }"),
])

edit('net/neoforged/neoforge/transfer/TransferPreconditions.java', [
    ('    public static void checkNonEmpty(Resource resource) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.checkNonEmpty:(Lnet/neoforged/neoforge/transfer/resource/Resource;)V");\n    }\n\n    public static void checkNonNegative(int value) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.checkNonNegative:(I)V");\n    }\n\n    public static void checkNonEmptyNonNegative(Resource resource, int value) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/TransferPreconditions.checkNonEmptyNonNegative:(Lnet/neoforged/neoforge/transfer/resource/Resource;I)V");\n    }',
     '    // Pumpkin divergence: NeoForge\'s own bodies -- argument validation, nothing else.\n    public static void checkNonEmpty(Resource resource) {\n        if (resource.isEmpty()) {\n            throw new IllegalArgumentException("Resource may not be empty");\n        }\n    }\n\n    public static void checkNonNegative(int value) {\n        if (value < 0) {\n            throw new IllegalArgumentException("Value may not be negative: " + value);\n        }\n    }\n\n    public static void checkNonEmptyNonNegative(Resource resource, int value) {\n        checkNonEmpty(resource);\n        checkNonNegative(value);\n    }'),
    ('import dev.pumpkin.shim.Unimplemented;\n', ''),
])

edit('net/minecraft/world/item/ItemStack.java', [
    ('    @SuppressWarnings("unchecked")\n    @Override\n    public <T> T get(DataComponentType<? extends T> type) {\n        return (T) pumpkinComponents.get(type);\n    }',
     '    @SuppressWarnings("unchecked")\n    @Override\n    public <T> T get(DataComponentType<? extends T> type) {\n        return (T) pumpkinComponents.get(type);\n    }\n\n    @Override\n    public boolean has(DataComponentType<?> type) {\n        return pumpkinComponents.containsKey(type);\n    }\n\n    @Override\n    public <T> T getOrDefault(DataComponentType<? extends T> type, T defaultValue) {\n        T value = get(type);\n        return value == null ? defaultValue : value;\n    }'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public DataComponentPatch getComponentsPatch() {\n        return pumpkinPatch;\n    }',
     '    public DataComponentPatch getComponentsPatch() {\n        return pumpkinPatch;\n    }\n\n    // Pumpkin divergence: component questions answered from the patch this resource\n    // carries -- the same divergence ItemStack documents: only what was set is seen.\n    @Override\n    public boolean has(DataComponentType<?> type) {\n        java.util.Optional<?> entry = pumpkinPatch.pumpkinMap.get(type);\n        return entry != null && entry.isPresent();\n    }\n\n    @SuppressWarnings("unchecked")\n    @Override\n    public <T> T get(DataComponentType<? extends T> type) {\n        java.util.Optional<?> entry = pumpkinPatch.pumpkinMap.get(type);\n        return entry == null ? null : (T) entry.orElse(null);\n    }\n\n    @Override\n    public <T> T getOrDefault(DataComponentType<? extends T> type, T defaultValue) {\n        T value = get(type);\n        return value == null ? defaultValue : value;\n    }'),
])

edit('dev/pumpkin/jvmhost/PumpkinHost.java', [
    ('    public static native String blockTagValues(String tag);',
     '    public static native String blockTagValues(String tag);\n\n    /**\n     * The vanilla cooking recipes of one kind ("smelting", ...) as newline-separated\n     * {@code id|ingredient|result:count} lines; see the Rust native for the grammar.\n     */\n    public static native String vanillaCookingRecipes(String kind);'),
])

edit('net/minecraft/world/item/crafting/RecipeType.java', [
    ('    RecipeType<CraftingRecipe> CRAFTING = Stubs.of(RecipeType.class, "net/minecraft/world/item/crafting/RecipeType");\n\n    RecipeType<SmeltingRecipe> SMELTING = Stubs.of(RecipeType.class, "net/minecraft/world/item/crafting/RecipeType");',
     '    // Pumpkin divergence: the vanilla constants really are simple() tokens whose\n    // toString is their id -- that is vanilla\'s own shape.\n    RecipeType<CraftingRecipe> CRAFTING = simple(Identifier.parse("minecraft:crafting"));\n\n    RecipeType<SmeltingRecipe> SMELTING = simple(Identifier.parse("minecraft:smelting"));'),
])

edit('net/minecraft/world/item/crafting/Ingredient.java', [
    ('    private static Ingredient pumpkinOf(java.util.List<String> ids) {',
     '    // Pumpkin divergence: no vanilla counterpart -- the bridge synthesizes vanilla\n    // cooking recipes and needs an ingredient over plain ids.\n    public static Ingredient pumpkinOfIds(java.util.List<String> ids) {\n        return pumpkinOf(ids);\n    }\n\n    private static Ingredient pumpkinOf(java.util.List<String> ids) {'),
])

edit('net/minecraft/world/item/crafting/SingleItemRecipe.java', [
    ('    private final Ingredient input = null;\n\n    private final ItemStackTemplate result = null;\n\n    public SingleItemRecipe(Recipe.CommonInfo commonInfo, Ingredient input, ItemStackTemplate result) {\n    }',
     '    // Pumpkin divergence: the recipe really carries its input and its result\n    // ("namespace:path:count"); matches/assemble answer from them.\n    Ingredient pumpkinInput;\n\n    String pumpkinResult;\n\n    public SingleItemRecipe(Recipe.CommonInfo commonInfo, Ingredient input, ItemStackTemplate result) {\n        this.pumpkinInput = input;\n    }'),
    ('    public boolean matches(SingleRecipeInput input, Level level) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.matches:(Lnet/minecraft/world/item/crafting/SingleRecipeInput;Lnet/minecraft/world/level/Level;)Z");\n    }',
     '    public boolean matches(SingleRecipeInput input, Level level) {\n        return pumpkinInput != null && pumpkinInput.test(input.item());\n    }'),
    ('    public Ingredient input() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.input:()Lnet/minecraft/world/item/crafting/Ingredient;");\n    }',
     '    public Ingredient input() {\n        if (pumpkinInput == null) {\n            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.input:()Lnet/minecraft/world/item/crafting/Ingredient; (recipe built without one)");\n        }\n        return pumpkinInput;\n    }'),
    ('    public ItemStack assemble(SingleRecipeInput input) {',
     '    public ItemStack pumpkinAssemble() {\n        if (pumpkinResult == null) {\n            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.assemble (recipe built without a result)");\n        }\n        int colon = pumpkinResult.lastIndexOf(\':\');\n        return dev.pumpkin.bridge.PumpkinInteractions.pumpkinBuildStack(\n                pumpkinResult.substring(0, colon), Integer.parseInt(pumpkinResult.substring(colon + 1)));\n    }\n\n    public ItemStack assemble(SingleRecipeInput input) {'),
])

edit('net/minecraft/world/item/crafting/SmeltingRecipe.java', [
    ('    public SmeltingRecipe() {\n    }',
     '    public SmeltingRecipe() {\n    }\n\n    // Pumpkin divergence: no vanilla counterpart -- a real recipe object synthesized\n    // from the vanilla cooking tables the Rust side carries.\n    public static SmeltingRecipe pumpkinVanilla(java.util.List<String> ingredientIds, String result) {\n        SmeltingRecipe recipe = new SmeltingRecipe();\n        recipe.pumpkinInput = Ingredient.pumpkinOfIds(ingredientIds);\n        recipe.pumpkinResult = result;\n        return recipe;\n    }'),
])

edit('net/minecraft/world/item/crafting/RecipeMap.java', [
    ('    public static final RecipeMap EMPTY = null;',
     '    // Pumpkin divergence: function-backed view over whatever resolver built it;\n    // EMPTY is a real empty one. byType/getRecipesFor answer from the resolver.\n    private java.util.function.Function<RecipeType<?>, Collection<RecipeHolder<?>>> pumpkinResolver;\n\n    public static RecipeMap pumpkinOf(java.util.function.Function<RecipeType<?>, Collection<RecipeHolder<?>>> resolver) {\n        RecipeMap map = new RecipeMap(null, null);\n        map.pumpkinResolver = resolver;\n        return map;\n    }\n\n    public static final RecipeMap EMPTY = pumpkinOf(type -> java.util.List.of());'),
    ('    public <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> byType(RecipeType<T> type) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.byType:(Lnet/minecraft/world/item/crafting/RecipeType;)Ljava/util/Collection;");\n    }',
     '    @SuppressWarnings({"unchecked", "rawtypes"})\n    public <I extends RecipeInput, T extends Recipe<I>> Collection<RecipeHolder<T>> byType(RecipeType<T> type) {\n        if (pumpkinResolver == null) {\n            throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.byType:(Lnet/minecraft/world/item/crafting/RecipeType;)Ljava/util/Collection;");\n        }\n        return (Collection) pumpkinResolver.apply(type);\n    }'),
    ('    public <I extends RecipeInput, T extends Recipe<I>> Stream<RecipeHolder<T>> getRecipesFor(RecipeType<T> type, I container, Level level) {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/RecipeMap.getRecipesFor:(Lnet/minecraft/world/item/crafting/RecipeType;Lnet/minecraft/world/item/crafting/RecipeInput;Lnet/minecraft/world/level/Level;)Ljava/util/stream/Stream;");\n    }',
     '    public <I extends RecipeInput, T extends Recipe<I>> Stream<RecipeHolder<T>> getRecipesFor(RecipeType<T> type, I container, Level level) {\n        return this.<I, T>byType(type).stream()\n                .filter(holder -> holder.value().matches(container, level));\n    }'),
])

edit('dev/pumpkin/bridge/PumpkinRecipes.java', [
    ('    private static final class Manager extends RecipeManager {\n        private Manager() {\n            super(null);\n        }\n',
     '    private static final class Manager extends RecipeManager {\n        private Manager() {\n            super(null);\n        }\n\n        @Override\n        public net.minecraft.world.item.crafting.RecipeMap recipeMap() {\n            // Mekanism walks recipeAccess().recipeMap().byType(type); the view resolves\n            // a type to its registered name (mod types) or its own toString (vanilla\n            // simple() tokens) and answers from the same per-type cache as getRecipeFor.\n            return net.minecraft.world.item.crafting.RecipeMap.pumpkinOf(type -> {\n                String typeName = DeferredHolder.pumpkinResolveName("minecraft:recipe_type", type);\n                if (typeName == null) {\n                    String token = type.toString();\n                    typeName = token.contains(":") ? token : null;\n                }\n                if (typeName == null) {\n                    return java.util.List.of();\n                }\n                return BY_TYPE.computeIfAbsent(typeName, PumpkinRecipes::load);\n            });\n        }\n'),
    ("    /** Every recipe JSON of one type, decoded through the type's registered serializer. */\n    private static List<RecipeHolder<?>> load(String typeName) {\n        List<RecipeHolder<?>> recipes = new ArrayList<>();\n        Path root = datapacksDir;\n        if (root == null || !Files.isDirectory(root)) {\n            return recipes;\n        }",
     '    /** Every recipe JSON of one type, decoded through the type\'s registered serializer. */\n    private static List<RecipeHolder<?>> load(String typeName) {\n        List<RecipeHolder<?>> recipes = new ArrayList<>();\n        // The vanilla furnace type answers with real recipe objects synthesized from\n        // the cooking tables the Rust side carries -- mod machines that wrap the\n        // furnace (Mekanism\'s energized smelter) see what a furnace would.\n        if (typeName.equals("minecraft:smelting")) {\n            String lines;\n            // Reflection because the host jar sits above this one in the build graph but\n            // below it at runtime -- the same route PumpkinTags takes.\n            try {\n                lines = (String) Class.forName("dev.pumpkin.jvmhost.PumpkinHost")\n                        .getMethod("vanillaCookingRecipes", String.class)\n                        .invoke(null, "smelting");\n            } catch (ReflectiveOperationException e) {\n                lines = null;\n                System.err.println("[pumpkin] minecraft:smelting: vanilla tables unreachable: " + e);\n            }\n            for (String line : lines == null ? new String[0] : lines.split("\\n")) {\n                if (line.isEmpty()) {\n                    continue;\n                }\n                String[] parts = line.split("\\\\|");\n                java.util.List<String> ingredientIds = java.util.List.of(parts[1].split(";"));\n                recipes.add(new RecipeHolder<>(\n                        net.minecraft.resources.ResourceKey.create(\n                                net.minecraft.resources.ResourceKey.createRegistryKey(\n                                        Identifier.parse("minecraft:recipe")),\n                                Identifier.parse(parts[0])),\n                        net.minecraft.world.item.crafting.SmeltingRecipe\n                                .pumpkinVanilla(ingredientIds, parts[2])));\n            }\n            System.err.println("[pumpkin] minecraft:smelting: " + recipes.size()\n                    + " vanilla recipe(s) synthesized.");\n        }\n        Path root = datapacksDir;\n        if (root == null || !Files.isDirectory(root)) {\n            return recipes;\n        }'),
])

PENDING[os.path.join(ROOT, 'net/neoforged/neoforge/transfer/transaction/Transaction.java')] = 'package net.neoforged.neoforge.transfer.transaction;\n\n// Pumpkin divergence: a real implementation. The transaction system is pure library\n// logic -- a per-thread stack of scopes, each holding the first snapshot every journal\n// took inside it; closing without commit reverts them, committing hands them to the\n// parent scope (or fires onRootCommit at the root).\npublic final class Transaction implements AutoCloseable, TransactionContext {\n\n    private static final ThreadLocal<java.util.ArrayDeque<Transaction>> STACK =\n            ThreadLocal.withInitial(java.util.ArrayDeque::new);\n\n    final java.util.LinkedHashMap<SnapshotJournal<?>, Object> pumpkinSnapshots =\n            new java.util.LinkedHashMap<>();\n\n    private final int pumpkinDepth;\n\n    private boolean committed;\n\n    public static Transaction openRoot() {\n        java.util.ArrayDeque<Transaction> stack = STACK.get();\n        if (!stack.isEmpty()) {\n            throw new IllegalStateException(\n                    "A transaction is already open on this thread; use open(parent).");\n        }\n        Transaction transaction = new Transaction(null, 0, null);\n        stack.push(transaction);\n        return transaction;\n    }\n\n    public static Transaction open(TransactionContext parent) {\n        java.util.ArrayDeque<Transaction> stack = STACK.get();\n        if (stack.isEmpty() || stack.peek() != parent) {\n            throw new IllegalStateException("Parent is not the current open transaction.");\n        }\n        Transaction transaction = new Transaction(null, parent.depth() + 1, null);\n        stack.push(transaction);\n        return transaction;\n    }\n\n    public static TransactionContext getCurrentOpenedTransaction() {\n        return STACK.get().peek();\n    }\n\n    public void commit() {\n        committed = true;\n    }\n\n    @Override\n    public void close() {\n        java.util.ArrayDeque<Transaction> stack = STACK.get();\n        if (stack.peek() != this) {\n            throw new IllegalStateException("Closing a transaction that is not the innermost.");\n        }\n        stack.pop();\n        if (!committed) {\n            java.util.ArrayList<java.util.Map.Entry<SnapshotJournal<?>, Object>> entries =\n                    new java.util.ArrayList<>(pumpkinSnapshots.entrySet());\n            for (int i = entries.size() - 1; i >= 0; i--) {\n                pumpkinRevert(entries.get(i).getKey(), entries.get(i).getValue());\n            }\n            return;\n        }\n        Transaction parent = stack.peek();\n        if (parent != null) {\n            // The parent keeps its own older snapshot where it has one; otherwise it\n            // inherits ours, so an abort above still reverts to the true original.\n            for (var entry : pumpkinSnapshots.entrySet()) {\n                parent.pumpkinSnapshots.putIfAbsent(entry.getKey(), entry.getValue());\n            }\n            return;\n        }\n        for (var entry : pumpkinSnapshots.entrySet()) {\n            pumpkinRootCommit(entry.getKey(), entry.getValue());\n        }\n    }\n\n    @SuppressWarnings("unchecked")\n    private static <T> void pumpkinRevert(SnapshotJournal<T> journal, Object snapshot) {\n        journal.revertToSnapshot((T) snapshot);\n    }\n\n    @SuppressWarnings("unchecked")\n    private static <T> void pumpkinRootCommit(SnapshotJournal<T> journal, Object snapshot) {\n        journal.onRootCommit((T) snapshot);\n    }\n\n    @Override\n    public int depth() {\n        return pumpkinDepth;\n    }\n\n    @Override\n    public String toString() {\n        return "Transaction(depth=" + pumpkinDepth + ", committed=" + committed + ")";\n    }\n\n    boolean open;\n\n    Transaction(TransactionManager manager, int depth, Class<?> callerClass) {\n        this.pumpkinDepth = depth;\n    }\n\n    public enum Lifecycle {\n\n        NONE, OPEN, CLOSING, ROOT_CLOSING\n    }\n\n    public Transaction() {\n        this(null, 0, null);\n    }\n}\n'

edit('net/neoforged/neoforge/transfer/transaction/SnapshotJournal.java', [
    ('    protected void onRootCommit(T originalState) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/SnapshotJournal.onRootCommit:(Ljava/lang/Object;)V");\n    }',
     "    // Pumpkin divergence: NeoForge's own default -- a hook, empty unless overridden.\n    protected void onRootCommit(T originalState) {\n    }"),
    ('    public void updateSnapshots(TransactionContext transaction) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/transaction/SnapshotJournal.updateSnapshots:(Lnet/neoforged/neoforge/transfer/transaction/TransactionContext;)V");\n    }',
     "    // Pumpkin divergence: real -- the first update inside a transaction snapshots\n    // this journal's state into that transaction's scope; later updates are no-ops.\n    public void updateSnapshots(TransactionContext transaction) {\n        if (transaction instanceof Transaction scope) {\n            scope.pumpkinSnapshots.computeIfAbsent(this, journal -> createSnapshot());\n        }\n    }"),
])

edit('net/minecraft/world/item/crafting/SingleItemRecipe.java', [
    ('    protected ItemStackTemplate result() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.result:()Lnet/minecraft/world/item/ItemStackTemplate;");\n    }',
     '    // Pumpkin divergence: NeoForge access-transforms this public; built from the\n    // carried result fact, the same holder shape the template codec produces.\n    @SuppressWarnings("unchecked")\n    public ItemStackTemplate result() {\n        if (pumpkinResult == null) {\n            throw Unimplemented.forMember("net/minecraft/world/item/crafting/SingleItemRecipe.result:()Lnet/minecraft/world/item/ItemStackTemplate; (recipe built without one)");\n        }\n        int colon = pumpkinResult.lastIndexOf(\':\');\n        int count = Integer.parseInt(pumpkinResult.substring(colon + 1));\n        net.minecraft.world.item.ItemStack stack = dev.pumpkin.bridge.PumpkinInteractions\n                .pumpkinBuildStack(pumpkinResult.substring(0, colon), count);\n        net.minecraft.core.Holder<net.minecraft.world.item.Item> holder =\n                (net.minecraft.core.Holder<net.minecraft.world.item.Item>) dev.pumpkin.shim.Stubs.of(\n                        net.minecraft.core.Holder.class, "net/minecraft/core/Holder",\n                        java.util.Map.of("value", stack.getItem()));\n        return new ItemStackTemplate(holder, count, null);\n    }'),
])

edit('net/minecraft/resources/ResourceKey.java', [
    ('    public ResourceKey<Registry<T>> registryKey() {\n        throw Unimplemented.forMember("net/minecraft/resources/ResourceKey.registryKey:()Lnet/minecraft/resources/ResourceKey;");\n    }',
     '    // Pumpkin divergence: vanilla body -- the registry half as a registry key.\n    public ResourceKey<Registry<T>> registryKey() {\n        return createRegistryKey(pumpkinRegistryName);\n    }'),
])

edit('net/neoforged/neoforge/common/crafting/SizedIngredient.java', [
    ('    private final Ingredient ingredient = null;\n\n    private final int count = 0;\n\n    public SizedIngredient(Ingredient ingredient, int count) {\n    }\n\n    public Ingredient ingredient() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.ingredient:()Lnet/minecraft/world/item/crafting/Ingredient;");\n    }\n\n    public int count() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.count:()I");\n    }\n\n    public boolean test(ItemStack stack) {\n        throw Unimplemented.forMember("net/neoforged/neoforge/common/crafting/SizedIngredient.test:(Lnet/minecraft/world/item/ItemStack;)Z");\n    }',
     "    // Pumpkin divergence: really an ingredient plus a count; NeoForge's own bodies.\n    private final Ingredient ingredient;\n\n    private final int count;\n\n    public SizedIngredient(Ingredient ingredient, int count) {\n        this.ingredient = ingredient;\n        this.count = count;\n    }\n\n    public Ingredient ingredient() {\n        return ingredient;\n    }\n\n    public int count() {\n        return count;\n    }\n\n    public boolean test(ItemStack stack) {\n        return ingredient.test(stack) && stack.count() >= count;\n    }"),
])

edit('net/neoforged/neoforge/common/crafting/SizedIngredient.java', [
    ('    public SizedIngredient() {\n    }\n}',
     '    public SizedIngredient() {\n        this(null, 0);\n    }\n}'),
])

edit('net/minecraft/world/item/crafting/Ingredient.java', [
    ('    public Ingredient(net.neoforged.neoforge.common.crafting.ICustomIngredient customIngredient) {\n    }',
     '    private boolean pumpkinCustom;\n\n    public Ingredient(net.neoforged.neoforge.common.crafting.ICustomIngredient customIngredient) {\n        this.pumpkinCustom = true;\n    }'),
    ('    public boolean isSimple() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.isSimple:()Z");\n    }',
     "    // Pumpkin divergence: NeoForge's own meaning -- simple unless custom logic hides\n    // behind it.\n    public boolean isSimple() {\n        return !pumpkinCustom;\n    }"),
])

edit('net/minecraft/core/HolderSet.java', [
    ('    static <T> HolderSet.Direct<T> direct(Holder<T>... values) {\n        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:([Lnet/minecraft/core/Holder;)Lnet/minecraft/core/HolderSet$Direct;");\n    }\n\n    static <T> HolderSet.Direct<T> direct(List<? extends Holder<T>> values) {\n        throw Unimplemented.forMember("net/minecraft/core/HolderSet.direct:(Ljava/util/List;)Lnet/minecraft/core/HolderSet$Direct;");\n    }',
     '    // Pumpkin divergence: vanilla bodies over a really-stored list.\n    @SafeVarargs\n    static <T> HolderSet.Direct<T> direct(Holder<T>... values) {\n        return direct(List.of(values));\n    }\n\n    @SuppressWarnings("unchecked")\n    static <T> HolderSet.Direct<T> direct(List<? extends Holder<T>> values) {\n        return new Direct<>((List<Holder<T>>) List.copyOf(values));\n    }'),
    ('    final class Direct<T> extends HolderSet.ListBacked<T> {\n\n        private Direct(List<Holder<T>> contents) {\n        }',
     '    final class Direct<T> extends HolderSet.ListBacked<T> {\n\n        private List<Holder<T>> pumpkinContents = List.of();\n\n        private Direct(List<Holder<T>> contents) {\n            this.pumpkinContents = contents;\n        }\n\n        @Override\n        protected List<Holder<T>> contents() {\n            return pumpkinContents;\n        }'),
    ('        public int size() {\n            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.size:()I");\n        }\n\n        public Spliterator<Holder<T>> spliterator() {\n            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.spliterator:()Ljava/util/Spliterator;");\n        }\n\n        public Iterator<Holder<T>> iterator() {\n            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.iterator:()Ljava/util/Iterator;");\n        }\n\n        public Stream<Holder<T>> stream() {\n            throw Unimplemented.forMember("net/minecraft/core/HolderSet$ListBacked.stream:()Ljava/util/stream/Stream;");\n        }',
     '        public int size() {\n            return contents().size();\n        }\n\n        public Spliterator<Holder<T>> spliterator() {\n            return contents().spliterator();\n        }\n\n        public Iterator<Holder<T>> iterator() {\n            return contents().iterator();\n        }\n\n        public Stream<Holder<T>> stream() {\n            return contents().stream();\n        }'),
])

edit('dev/pumpkin/bridge/PumpkinTags.java', [
    ('    /** Whether the item wears the tag; {@code tag} has no leading {@code #}. */\n    public static boolean contains(String tag, String itemId) {\n        return members("item", tag, new HashSet<>()).contains(itemId);\n    }',
     '    /** Whether the item wears the tag; {@code tag} has no leading {@code #}. */\n    public static boolean contains(String tag, String itemId) {\n        return members("item", tag, new HashSet<>()).contains(itemId);\n    }\n\n    /** Every item wearing the tag; same walk as {@link #contains}. */\n    public static Set<String> itemMembers(String tag) {\n        return members("item", tag, new HashSet<>());\n    }'),
])

edit('net/minecraft/world/item/crafting/Ingredient.java', [
    ('    public HolderSet<Item> getValues() {\n        throw Unimplemented.forMember("net/minecraft/world/item/crafting/Ingredient.getValues:()Lnet/minecraft/core/HolderSet;");\n    }',
     '    // Pumpkin divergence: real holders over the decoded ids, tag entries expanded\n    // through the same tag tables test() consults.\n    @SuppressWarnings("unchecked")\n    public HolderSet<Item> getValues() {\n        java.util.ArrayList<Holder<Item>> holders = new java.util.ArrayList<>();\n        java.util.LinkedHashSet<String> ids = new java.util.LinkedHashSet<>();\n        for (String candidate : pumpkinIds) {\n            if (candidate.startsWith("#")) {\n                ids.addAll(dev.pumpkin.bridge.PumpkinTags.itemMembers(candidate.substring(1)));\n            } else {\n                ids.add(candidate);\n            }\n        }\n        for (String id : ids) {\n            Item item = dev.pumpkin.bridge.PumpkinInteractions.pumpkinBuildStack(id, 1).getItem();\n            holders.add((Holder<Item>) dev.pumpkin.shim.Stubs.of(Holder.class,\n                    "net/minecraft/core/Holder(" + id + ")", java.util.Map.of("value", item)));\n        }\n        return HolderSet.direct(holders);\n    }'),
])

edit('net/minecraft/core/HolderSet.java', [
    ('        @Override\n        protected List<Holder<T>> contents() {\n            return pumpkinContents;\n        }\n\n        protected List<Holder<T>> contents() {\n            throw Unimplemented.forMember("net/minecraft/core/HolderSet$Direct.contents:()Ljava/util/List;");\n        }',
     '        @Override\n        protected List<Holder<T>> contents() {\n            return pumpkinContents;\n        }'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public Holder<Item> typeHolder() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.typeHolder:()Lnet/minecraft/core/Holder;");\n    }',
     '    // Pumpkin divergence: a real holder over the carried item, the same shape the\n    // ingredient values use.\n    @SuppressWarnings("unchecked")\n    public Holder<Item> typeHolder() {\n        return (Holder<Item>) dev.pumpkin.shim.Stubs.of(Holder.class,\n                "net/minecraft/core/Holder(ItemResource)", java.util.Map.of("value", getItem()));\n    }'),
])

edit('net/neoforged/neoforge/transfer/item/ItemResource.java', [
    ('    public int getMaxStackSize() {\n        throw Unimplemented.forMember("net/neoforged/neoforge/transfer/item/ItemResource.getMaxStackSize:()I");\n    }',
     "    // Pumpkin divergence: the item's declared max stack size; 64, the vanilla\n    // default, when the mod did not say.\n    public int getMaxStackSize() {\n        Item item = getItem();\n        int declared = item == null ? -1 : item.pumpkinMaxStackSize();\n        return declared > 0 ? declared : 64;\n    }"),
])

edit('shim/src/main/java/dev/pumpkin/bridge/PumpkinInteractions.java'.split('java/',1)[1], [
    ('        // A vanilla item has no holder here; a synthetic Item carrying the id is enough\n        // for the mod to store and hand back, and the id survives the round trip.\n        Item stand_in = new Item(new Item.Properties());\n        stand_in.pumpkinSetRegisteredId(itemId);\n        return new ItemStack(stand_in, count);\n    }',
     '        // A vanilla item has no holder here; a synthetic Item carrying the id is enough\n        // for the mod to store and hand back, and the id survives the round trip.\n        // Interned: vanilla items are singletons, and mod recipe caches key on item\n        // identity -- two stand-ins for one id must be the same object.\n        return new ItemStack(VANILLA_STAND_INS.computeIfAbsent(itemId, id -> {\n            Item item = new Item(new Item.Properties());\n            item.pumpkinSetRegisteredId(id);\n            return item;\n        }), count);\n    }\n\n    private static final java.util.concurrent.ConcurrentHashMap<String, Item> VANILLA_STAND_INS =\n            new java.util.concurrent.ConcurrentHashMap<>();'),
])

edit('net/minecraft/world/level/LevelAccessor.java', [
    ('    default long getGameTime() {\n        throw Unimplemented.forMember("net/minecraft/world/level/LevelAccessor.getGameTime:()J");\n    }',
     "    // Pumpkin divergence: a real monotonic clock in tick units. The stand-in level\n    // does not tick, but mods stamp caches and cooldowns with this; wall time over\n    // 50ms is the truthful equivalent of the server's tick counter.\n    default long getGameTime() {\n        return System.nanoTime() / 50_000_000L;\n    }"),
])

edit('net/minecraft/world/level/block/entity/BlockEntity.java', [
    ('    protected final BlockPos worldPosition = null;',
     '    // Pumpkin divergence: real -- subclasses read the field directly.\n    protected BlockPos worldPosition;'),
    ('    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {\n        this.pumpkinPosition = worldPosition;\n        this.pumpkinBlockState = blockState;\n    }',
     '    public BlockEntity(BlockEntityType<?> type, BlockPos worldPosition, BlockState blockState) {\n        this.pumpkinPosition = worldPosition;\n        this.worldPosition = worldPosition;\n        this.pumpkinBlockState = blockState;\n    }'),
])

edit('net/minecraft/world/level/chunk/LevelChunk.java', [
    ('    public void markUnsaved() {\n        throw Unimplemented.forMember("net/minecraft/world/level/chunk/LevelChunk.markUnsaved:()V");\n    }',
     '    // Pumpkin divergence: dirty-marking is persistence bookkeeping the bridge does\n    // itself -- entity data rides every interaction reply -- so there is nothing to mark.\n    public void markUnsaved() {\n    }'),
])

edit('dev/pumpkin/bridge/PumpkinLevel.java', [
    ('    public ChunkAccess getChunk(final int chunkX, final int chunkZ, final ChunkStatus targetStatus, final boolean loadOrGenerate) {\n        throw Unimplemented.forMember("net/minecraft/world/level/Level.getChunk");\n    }',
     "    // Pumpkin divergence: one shared stand-in chunk; the only fact mods want from it\n    // here is markUnsaved, which the bridge's own persistence makes a no-op.\n    private static final net.minecraft.world.level.chunk.LevelChunk PUMPKIN_CHUNK =\n            new net.minecraft.world.level.chunk.LevelChunk(null, null);\n\n    @Override\n    public ChunkAccess getChunk(final int chunkX, final int chunkZ, final ChunkStatus targetStatus, final boolean loadOrGenerate) {\n        return PUMPKIN_CHUNK;\n    }"),
])

edit('shim/src/main/java/dev/pumpkin/bridge/PumpkinValueIO.java'.split('java/',1)[1], [
    ('            // A recipe id in progress-tracking saves.\n            if (value instanceof net.minecraft.resources.Identifier identifier) {',
     '            // A mod resource stack (Mekanism\'s LargeResourceStack and kin): a record of\n            // {resource, amount}, reached by reflection because the mod\'s class is not\n            // on this classpath. Item resources save in the same {id, count} shape as\n            // stacks; other resource kinds fail loudly below.\n            java.lang.reflect.Method resourceAccessor = null;\n            java.lang.reflect.Method amountAccessor = null;\n            for (java.lang.reflect.Method method : value.getClass().getMethods()) {\n                if (method.getParameterCount() != 0) {\n                    continue;\n                }\n                if (method.getName().equals("resource")) {\n                    resourceAccessor = method;\n                } else if (method.getName().equals("amount")) {\n                    amountAccessor = method;\n                }\n            }\n            if (resourceAccessor != null && amountAccessor != null) {\n                try {\n                    Object resource = resourceAccessor.invoke(value);\n                    Object amount = amountAccessor.invoke(value);\n                    if (resource instanceof net.neoforged.neoforge.transfer.item.ItemResource itemResource\n                            && amount instanceof Long count) {\n                        JsonObject stackJson = new JsonObject();\n                        if (!itemResource.isEmpty()) {\n                            stackJson.addProperty("id", PumpkinInteractions\n                                    .pumpkinItemId(itemResource.toStack(1)));\n                            stackJson.addProperty("count", count);\n                        }\n                        json.add(name, stackJson);\n                        return;\n                    }\n                } catch (ReflectiveOperationException ignored) {\n                    // Fall through to the loud refusal below.\n                }\n            }\n            // A recipe id in progress-tracking saves.\n            if (value instanceof net.minecraft.resources.Identifier identifier) {'),
])

commit()
