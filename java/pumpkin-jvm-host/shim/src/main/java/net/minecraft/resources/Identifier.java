package net.minecraft.resources;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import dev.pumpkin.shim.Stubs;
import dev.pumpkin.shim.Unimplemented;

public final class Identifier implements Comparable<Identifier> {

    // Pumpkin divergence from the generated shim: these two fields, and the members below
    // marked the same way, carry real behaviour. Vanilla builds an Identifier out of an
    // interner and a Codec, neither of which the shim has; but an id is a pair of strings,
    // Pumpkin's block registration is keyed on its printed form, and a stub that throws
    // here would stop every mod before it registered anything. Re-apply by hand after any
    // regeneration -- grep for "Pumpkin divergence".
    private final String pumpkinNamespace;

    private final String pumpkinPath;

    // Pumpkin divergence: real value. Codec is DataFixerUpper, a real library on the
    // classpath, and parse/toString carry real behaviour here -- so this is vanilla's own
    // codec, not a stub. It was null, and the first consumer was not a mod calling it but
    // DFU's RecordCodecBuilder dereferencing it inside Cucumber's recipe conditions: an NPE
    // deep in library code, with nothing naming the missing piece. The exact silent-null
    // failure the holder rules exist to prevent, arrived through a side door.
    public static final Codec<Identifier> CODEC =
            Codec.STRING.xmap(Identifier::parse, Identifier::toString).stable();

    public static final StreamCodec<ByteBuf, Identifier> STREAM_CODEC = Stubs.of(StreamCodec.class, "net/minecraft/network/codec/StreamCodec");

    // Pumpkin divergence: real body.
    private Identifier(String namespace, String path) {
        this.pumpkinNamespace = namespace;
        this.pumpkinPath = path;
    }

    // Pumpkin divergence: real body.
    public static Identifier fromNamespaceAndPath(String namespace, String path) {
        return new Identifier(namespace, path);
    }

    // Pumpkin divergence: real body. The same one-line rule vanilla uses -- everything
    // before the colon is the namespace, everything after is the path, and a bare path is
    // "minecraft".
    public static Identifier parse(String identifier) {
        int colon = identifier.indexOf(':');
        return colon < 0
                ? new Identifier("minecraft", identifier)
                : new Identifier(identifier.substring(0, colon), identifier.substring(colon + 1));
    }

    // Pumpkin divergence: vanilla body -- the minecraft namespace.
    public static Identifier withDefaultNamespace(String path) {
        return fromNamespaceAndPath("minecraft", path);
    }

    public static Identifier tryParse(String identifier) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.tryParse:(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }

    public static Identifier tryBuild(String namespace, String path) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.tryBuild:(Ljava/lang/String;Ljava/lang/String;)Lnet/minecraft/resources/Identifier;");
    }

    public static DataResult<Identifier> read(String input) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.read:(Ljava/lang/String;)Lcom/mojang/serialization/DataResult;");
    }

    // Pumpkin divergence: real body.
    public String getPath() {
        return pumpkinPath;
    }

    // Pumpkin divergence: real body.
    public String getNamespace() {
        return pumpkinNamespace;
    }

    // Pumpkin divergence: real body, mirroring withSuffix.
    public Identifier withPrefix(String prefix) {
        return new Identifier(pumpkinNamespace, prefix + pumpkinPath);
    }

    // Pumpkin divergence: real body.
    public Identifier withSuffix(String suffix) {
        return new Identifier(pumpkinNamespace, pumpkinPath + suffix);
    }

    // Pumpkin divergence: real body. This is the string Pumpkin registers a block under,
    // so DeferredRegister's flush depends on it directly.
    public String toString() {
        return pumpkinNamespace + ":" + pumpkinPath;
    }

    // Pumpkin divergence: real body. An id is a value and is used as a map key.
    public boolean equals(Object o) {
        return o instanceof Identifier other
                && pumpkinNamespace.equals(other.pumpkinNamespace)
                && pumpkinPath.equals(other.pumpkinPath);
    }

    // Pumpkin divergence: real body.
    public int hashCode() {
        return 31 * pumpkinNamespace.hashCode() + pumpkinPath.hashCode();
    }

    public int compareNamespaced(Identifier o) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.compareNamespaced:(Lnet/minecraft/resources/Identifier;)I");
    }

    // Pumpkin divergence: real body.
    public int compareTo(Identifier o) {
        int byPath = pumpkinPath.compareTo(o.pumpkinPath);
        return byPath != 0 ? byPath : pumpkinNamespace.compareTo(o.pumpkinNamespace);
    }

    public String toDebugFileName() {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.toDebugFileName:()Ljava/lang/String;");
    }

    // Pumpkin divergence: vanilla bodies verbatim -- string arithmetic over the parts.
    public String toLanguageKey() {
        return getNamespace() + "." + getPath();
    }

    public String toLanguageKey(String prefix) {
        return prefix + "." + toLanguageKey();
    }

    public String toLanguageKey(String prefix, String suffix) {
        return prefix + "." + toLanguageKey() + "." + suffix;
    }

    public static Identifier read(StringReader reader) throws CommandSyntaxException {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.read:(Lcom/mojang/brigadier/StringReader;)Lnet/minecraft/resources/Identifier;");
    }

    public static boolean isAllowedInIdentifier(char c) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.isAllowedInIdentifier:(C)Z");
    }

    public static boolean isValidNamespace(String namespace) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.isValidNamespace:(Ljava/lang/String;)Z");
    }

    public static boolean validPathChar(char c) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.validPathChar:(C)Z");
    }

    public static boolean validNamespaceChar(char c) {
        throw Unimplemented.forMember("net/minecraft/resources/Identifier.validNamespaceChar:(C)Z");
    }

    // Pumpkin divergence: the generator synthesises an empty no-argument constructor for
    // every class so a subclass's implicit super() resolves. Identifier is final and has
    // two final fields, so this one has to assign them.
    private Identifier() {
        this("minecraft", "");
    }
}
