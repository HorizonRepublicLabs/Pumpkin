package net.neoforged.fml.loading.modscan;

/**
 * FML's annotation-scan record type. Mods touch it only through scan data, which
 * Pumpkin's own scanner builds without this class; it exists so references resolve.
 */
public class ModAnnotation {
    /** How FML spells an enum value inside scanned annotation data. */
    public static class EnumHolder {
        private final String desc;
        private final String value;

        public EnumHolder(String desc, String value) {
            this.desc = desc;
            this.value = value;
        }

        public String desc() {
            return desc;
        }

        public String value() {
            return value;
        }
    }
}
