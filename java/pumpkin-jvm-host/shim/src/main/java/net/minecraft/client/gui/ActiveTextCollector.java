package net.minecraft.client.gui;

import net.minecraft.client.gui.navigation.ScreenRectangle;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.joml.Matrix3x2fc;
import dev.pumpkin.shim.Unimplemented;

public interface ActiveTextCollector {

    ActiveTextCollector.Parameters defaultParameters();

    void defaultParameters(ActiveTextCollector.Parameters newParameters);

    default void accept(int x, int y, FormattedCharSequence text) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector.accept:(IILnet/minecraft/util/FormattedCharSequence;)V");
    }

    default void accept(int x, int y, Component text) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector.accept:(IILnet/minecraft/network/chat/Component;)V");
    }

    default void accept(TextAlignment alignment, int anchorX, int y, ActiveTextCollector.Parameters parameters, Component text) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector.accept:(Lnet/minecraft/client/gui/TextAlignment;IILnet/minecraft/client/gui/ActiveTextCollector$Parameters;Lnet/minecraft/network/chat/Component;)V");
    }

    void accept(TextAlignment alignment, int anchorX, int y, ActiveTextCollector.Parameters parameters, FormattedCharSequence text);

    default void accept(TextAlignment alignment, int anchorX, int y, Component text) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector.accept:(Lnet/minecraft/client/gui/TextAlignment;IILnet/minecraft/network/chat/Component;)V");
    }

    default void accept(TextAlignment alignment, int anchorX, int y, FormattedCharSequence text) {
        throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector.accept:(Lnet/minecraft/client/gui/TextAlignment;IILnet/minecraft/util/FormattedCharSequence;)V");
    }

    void acceptScrolling(Component message, int centerX, int left, int right, int top, int bottom, ActiveTextCollector.Parameters parameters);

    class ClickableStyleFinder implements ActiveTextCollector {

        public ClickableStyleFinder(Font font, int testX, int testY) {
            throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector$ClickableStyleFinder.<init>:(Lnet/minecraft/client/gui/Font;II)V");
        }

        public ActiveTextCollector.Parameters defaultParameters() {
            throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector$ClickableStyleFinder.defaultParameters:()Lnet/minecraft/client/gui/ActiveTextCollector$Parameters;");
        }

        public void defaultParameters(ActiveTextCollector.Parameters newParameters) {
            throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector$ClickableStyleFinder.defaultParameters:(Lnet/minecraft/client/gui/ActiveTextCollector$Parameters;)V");
        }

        public void accept(TextAlignment alignment, int anchorX, int y, ActiveTextCollector.Parameters parameters, FormattedCharSequence text) {
            throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector$ClickableStyleFinder.accept:(Lnet/minecraft/client/gui/TextAlignment;IILnet/minecraft/client/gui/ActiveTextCollector$Parameters;Lnet/minecraft/util/FormattedCharSequence;)V");
        }

        public void acceptScrolling(Component message, int centerX, int left, int right, int top, int bottom, ActiveTextCollector.Parameters parameters) {
            throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector$ClickableStyleFinder.acceptScrolling:(Lnet/minecraft/network/chat/Component;IIIIILnet/minecraft/client/gui/ActiveTextCollector$Parameters;)V");
        }

        public Style result() {
            throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector$ClickableStyleFinder.result:()Lnet/minecraft/network/chat/Style;");
        }

        protected ClickableStyleFinder() {
        }
    }

    record Parameters(Matrix3x2fc pose, float opacity, ScreenRectangle scissor) {

        public Parameters(Matrix3x2fc pose) {
            this((Matrix3x2fc) null, (float) 0.0F, (ScreenRectangle) null);
            throw Unimplemented.forMember("net/minecraft/client/gui/ActiveTextCollector$Parameters.<init>:(Lorg/joml/Matrix3x2fc;)V");
        }
    }
}
