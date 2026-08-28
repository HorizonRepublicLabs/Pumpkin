package net.neoforged.neoforge.registries;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import org.junit.jupiter.api.Test;

class DeferredRegisterTest {
    @Test
    void registrationIsDeferredUntilTheEventFires() {
        List<String> registered = new ArrayList<>();
        DeferredRegister.setSink((id, template) -> {
            registered.add(id + " from " + template);
            return registered.size();
        });

        IEventBus bus = new IEventBus();
        DeferredRegister<Block> blocks = DeferredRegister.create(Registries.BLOCK, "testmod");
        DeferredHolder<Block> ruby =
                blocks.register("ruby_block", () -> new Block(BlockBehaviour.Properties.of().pumpkinTemplate("stone")));
        blocks.register(bus);

        assertTrue(registered.isEmpty(), "nothing registers before the event");

        bus.post(new RegisterEvent());

        assertEquals(List.of("testmod:ruby_block from stone"), registered);
        assertEquals("testmod:ruby_block", ruby.getId().toString());
    }
}
