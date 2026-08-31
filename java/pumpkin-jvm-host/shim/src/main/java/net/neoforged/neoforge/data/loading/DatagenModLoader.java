package net.neoforged.neoforge.data.loading;

import net.neoforged.neoforge.internal.CommonModLoader;
import dev.pumpkin.shim.Unimplemented;

public class DatagenModLoader extends CommonModLoader {

    public static boolean isRunningDataGen() {
        throw Unimplemented.forMember("net/neoforged/neoforge/data/loading/DatagenModLoader.isRunningDataGen:()Z");
    }

    public DatagenModLoader() {
    }
}
