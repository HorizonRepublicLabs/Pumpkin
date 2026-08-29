package net.minecraft.world.attribute;

import com.mojang.serialization.DataResult;

public interface AttributeRange<Value> {

    DataResult<Value> validate(Value value);

    Value sanitize(Value value);
}
