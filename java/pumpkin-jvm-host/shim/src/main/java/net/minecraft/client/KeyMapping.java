package net.minecraft.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.extensions.IKeyMappingExtension;
import dev.pumpkin.shim.Unimplemented;

public class KeyMapping implements Comparable<KeyMapping>, IKeyMappingExtension {

    public static void set(InputConstants.Key key, boolean state) {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.set:(Lcom/mojang/blaze3d/platform/InputConstants$Key;Z)V");
    }

    public KeyMapping(String name, int keysym, KeyMapping.Category category) {
    }

    public KeyMapping(String name, InputConstants.Type type, int value, KeyMapping.Category category) {
    }

    public KeyMapping(String name, InputConstants.Type type, int value, KeyMapping.Category category, int order) {
    }

    public KeyMapping(String name, net.neoforged.neoforge.client.settings.IKeyConflictContext keyConflictContext, InputConstants.Type inputType, int keyCode, KeyMapping.Category category) {
    }

    public KeyMapping(String name, net.neoforged.neoforge.client.settings.IKeyConflictContext keyConflictContext, InputConstants.Key keyCode, KeyMapping.Category category) {
    }

    public KeyMapping(String name, net.neoforged.neoforge.client.settings.IKeyConflictContext keyConflictContext, net.neoforged.neoforge.client.settings.KeyModifier keyModifier, InputConstants.Type inputType, int keyCode, KeyMapping.Category category) {
    }

    public KeyMapping(String name, net.neoforged.neoforge.client.settings.IKeyConflictContext keyConflictContext, net.neoforged.neoforge.client.settings.KeyModifier keyModifier, InputConstants.Key keyCode, KeyMapping.Category category) {
    }

    public InputConstants.Key getKey() {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.getKey:()Lcom/mojang/blaze3d/platform/InputConstants$Key;");
    }

    public void setKeyConflictContext(net.neoforged.neoforge.client.settings.IKeyConflictContext keyConflictContext) {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.setKeyConflictContext:(Lnet/neoforged/neoforge/client/settings/IKeyConflictContext;)V");
    }

    public net.neoforged.neoforge.client.settings.IKeyConflictContext getKeyConflictContext() {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.getKeyConflictContext:()Lnet/neoforged/neoforge/client/settings/IKeyConflictContext;");
    }

    public net.neoforged.neoforge.client.settings.KeyModifier getDefaultKeyModifier() {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.getDefaultKeyModifier:()Lnet/neoforged/neoforge/client/settings/KeyModifier;");
    }

    public net.neoforged.neoforge.client.settings.KeyModifier getKeyModifier() {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.getKeyModifier:()Lnet/neoforged/neoforge/client/settings/KeyModifier;");
    }

    public void setKeyModifierAndCode(net.neoforged.neoforge.client.settings.KeyModifier keyModifier, InputConstants.Key keyCode) {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.setKeyModifierAndCode:(Lnet/neoforged/neoforge/client/settings/KeyModifier;Lcom/mojang/blaze3d/platform/InputConstants$Key;)V");
    }

    protected void release() {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.release:()V");
    }

    public String getName() {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.getName:()Ljava/lang/String;");
    }

    public InputConstants.Key getDefaultKey() {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.getDefaultKey:()Lcom/mojang/blaze3d/platform/InputConstants$Key;");
    }

    public int compareTo(KeyMapping o) {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.compareTo:(Lnet/minecraft/client/KeyMapping;)I");
    }

    public boolean matches(KeyEvent event) {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.matches:(Lnet/minecraft/client/input/KeyEvent;)Z");
    }

    public boolean matches(InputConstants.Key key) {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.matches:(Lcom/mojang/blaze3d/platform/InputConstants$Key;)Z");
    }

    public static KeyMapping get(String name) {
        throw Unimplemented.forMember("net/minecraft/client/KeyMapping.get:(Ljava/lang/String;)Lnet/minecraft/client/KeyMapping;");
    }

    public record Category(Identifier id) {

        private static KeyMapping.Category register(String name) {
            throw Unimplemented.forMember("net/minecraft/client/KeyMapping$Category.register:(Ljava/lang/String;)Lnet/minecraft/client/KeyMapping$Category;");
        }

        public static KeyMapping.Category register(Identifier id) {
            throw Unimplemented.forMember("net/minecraft/client/KeyMapping$Category.register:(Lnet/minecraft/resources/Identifier;)Lnet/minecraft/client/KeyMapping$Category;");
        }
    }

    public KeyMapping() {
    }
}
