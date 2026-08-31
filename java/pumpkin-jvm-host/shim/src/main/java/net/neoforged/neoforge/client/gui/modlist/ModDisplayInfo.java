package net.neoforged.neoforge.client.gui.modlist;

import java.net.URI;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.IExtensionPoint;

public interface ModDisplayInfo extends IExtensionPoint {

    String id();

    Component displayName();

    String version();

    Component authors();

    Component credits();

    Component description();

    Component license();

    ImageResource banner();

    ImageResource icon();

    URI displayUrl();

    URI issuesUrl();
}
