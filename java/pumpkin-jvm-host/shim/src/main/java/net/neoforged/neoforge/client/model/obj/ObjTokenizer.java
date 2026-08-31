package net.neoforged.neoforge.client.model.obj;

import java.io.IOException;
import java.io.InputStream;
import dev.pumpkin.shim.Unimplemented;

public class ObjTokenizer implements AutoCloseable {

    public ObjTokenizer(InputStream inputStream) {
    }

    public void close() throws IOException {
        throw Unimplemented.forMember("net/neoforged/neoforge/client/model/obj/ObjTokenizer.close:()V");
    }

    public ObjTokenizer() {
    }
}
