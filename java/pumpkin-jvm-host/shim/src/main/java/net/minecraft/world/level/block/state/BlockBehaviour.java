package net.minecraft.world.level.block.state;

/**
 * Vanilla's block property builder, reduced to what Pumpkin's registration reads.
 *
 * <p>{@code pumpkinTemplate} has no vanilla counterpart. Pumpkin registers a block by
 * copying a vanilla one, so something has to say which; a mod that never calls it gets
 * stone. This is the one place the shim knowingly diverges from vanilla's API.
 */
public class BlockBehaviour {
    public static final class Properties {
        private String template = "stone";

        private Properties() {
        }

        public static Properties of() {
            return new Properties();
        }

        public Properties pumpkinTemplate(String template) {
            this.template = template;
            return this;
        }

        public String template() {
            return template;
        }
    }
}
