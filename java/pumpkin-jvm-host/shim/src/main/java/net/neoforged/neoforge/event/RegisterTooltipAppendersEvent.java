package net.neoforged.neoforge.event;

import com.google.common.graph.MutableGraph;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.core.component.DataComponentType;
import net.neoforged.bus.api.Event;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.neoforge.common.tooltip.TooltipAppender;
import net.neoforged.neoforge.common.tooltip.TooltipLocation;
import dev.pumpkin.shim.Unimplemented;

public final class RegisterTooltipAppendersEvent extends Event implements IModBusEvent {

    public RegisterTooltipAppendersEvent(Map<TooltipLocation, List<TooltipAppender>> appenders, Map<DataComponentType<?>, TooltipAppender> componentAppenders, MutableGraph<DataComponentType<?>> componentGraph, DataComponentType<?> firstVanillaType, DataComponentType<?> lastVanillaType) {
    }

    public void registerComponentAppenderBeforeAll(Supplier<? extends DataComponentType<?>> type, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderBeforeAll:(Ljava/util/function/Supplier;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public void registerComponentAppenderBeforeAll(DataComponentType<?> type, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderBeforeAll:(Lnet/minecraft/core/component/DataComponentType;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public void registerComponentAppenderBefore(Supplier<? extends DataComponentType<?>> type, DataComponentType<?> otherType, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderBefore:(Ljava/util/function/Supplier;Lnet/minecraft/core/component/DataComponentType;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public void registerComponentAppenderBefore(DataComponentType<?> type, DataComponentType<?> otherType, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderBefore:(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/core/component/DataComponentType;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public void registerComponentAppenderAfter(Supplier<? extends DataComponentType<?>> type, DataComponentType<?> otherType, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderAfter:(Ljava/util/function/Supplier;Lnet/minecraft/core/component/DataComponentType;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public void registerComponentAppenderAfter(DataComponentType<?> type, DataComponentType<?> otherType, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderAfter:(Lnet/minecraft/core/component/DataComponentType;Lnet/minecraft/core/component/DataComponentType;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public void registerComponentAppenderAfterAll(Supplier<? extends DataComponentType<?>> type, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderAfterAll:(Ljava/util/function/Supplier;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public void registerComponentAppenderAfterAll(DataComponentType<?> type, TooltipAppender appender) {
        throw Unimplemented.forMember("net/neoforged/neoforge/event/RegisterTooltipAppendersEvent.registerComponentAppenderAfterAll:(Lnet/minecraft/core/component/DataComponentType;Lnet/neoforged/neoforge/common/tooltip/TooltipAppender;)V");
    }

    public RegisterTooltipAppendersEvent() {
    }
}
