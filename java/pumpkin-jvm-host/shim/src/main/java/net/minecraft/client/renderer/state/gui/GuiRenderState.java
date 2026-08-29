package net.minecraft.client.renderer.state.gui;

import dev.pumpkin.shim.Unimplemented;

public class GuiRenderState {

    public GuiRenderState() {
    }

    public void reset() {
        throw Unimplemented.forMember("net/minecraft/client/renderer/state/gui/GuiRenderState.reset:()V");
    }

    private static class Node {

        private Node(GuiRenderState.Node parent) {
        }

        protected Node() {
        }
    }

    public enum TraverseRange {

        ALL, BEFORE_BLUR, AFTER_BLUR
    }
}
