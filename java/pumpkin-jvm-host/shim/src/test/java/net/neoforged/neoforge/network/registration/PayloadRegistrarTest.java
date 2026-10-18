package net.neoforged.neoforge.network.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import org.junit.jupiter.api.Test;

class PayloadRegistrarTest {
    @Test
    void eachRegistrationIsDeclaredWithItsPhaseVersionAndDirection() {
        List<String> declared = new ArrayList<>();
        PayloadRegistrar.setSink((id, phase, version, serverbound) ->
                declared.add(id + " " + phase + " " + version + " " + serverbound));

        PayloadRegistrar registrar = new RegisterPayloadHandlersEvent().registrar("7");
        registrar.playToClient(new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("testmod", "down")), null)
                .playToServer(new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("testmod", "up")), null, null)
                .configurationToClient(new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("testmod", "cfg")), null);

        assertEquals(List.of(
                "testmod:down play 7 false",
                "testmod:up play 7 true",
                "testmod:cfg configuration 7 false"), declared);
    }
}
