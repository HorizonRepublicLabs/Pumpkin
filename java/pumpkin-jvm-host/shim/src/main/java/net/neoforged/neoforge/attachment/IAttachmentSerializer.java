package net.neoforged.neoforge.attachment;

import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

public interface IAttachmentSerializer<T> {

    T read(IAttachmentHolder holder, ValueInput input);

    boolean write(T attachment, ValueOutput output);
}
