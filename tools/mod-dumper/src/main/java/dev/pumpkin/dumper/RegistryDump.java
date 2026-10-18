package dev.pumpkin.dumper;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.network.ConnectionProtocol;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import net.neoforged.neoforge.network.registration.NetworkRegistry;
import net.neoforged.neoforge.network.registration.PayloadRegistration;

/**
 * Writes {@code registry-dump.json}: what the other loaded mods registered, after their code
 * ran. pumpkin-mod-convert reads it instead of guessing from data files, which cannot tell a
 * mod's real blocks and items from the model templates it ships, nor the order its block
 * states are numbered in.
 */
@Mod("pumpkin_registry_dump")
public final class RegistryDump {
    public RegistryDump(IEventBus modBus) {
        modBus.addListener(GatherDataEvent.Client.class, event -> event.createProvider(Provider::new));
    }

    private record Provider(PackOutput output) implements DataProvider {
        @Override
        public CompletableFuture<?> run(CachedOutput cache) {
            var dump = new JsonObject();
            dump.addProperty("format", 1);
            dump.add("blocks", blocks());
            dump.add("items", items());
            dump.add("block_entity_types", ids(BuiltInRegistries.BLOCK_ENTITY_TYPE));
            dump.add("menus", ids(BuiltInRegistries.MENU));
            dump.add("entity_types", ids(BuiltInRegistries.ENTITY_TYPE));
            dump.add("channels", channels());
            var mysticalAgriculture = MysticalAgricultureData.dump();
            if (mysticalAgriculture != null) {
                dump.add("mysticalagriculture", mysticalAgriculture);
            }
            // Through the cache: a file written around it is deleted afterwards as stale.
            return DataProvider.saveStable(cache, dump, this.output.getOutputFolder().resolve("registry-dump.json"));
        }

        @Override
        public String getName() {
            return "Pumpkin registry dump";
        }
    }

    /** Content the server must add: anything outside minecraft and NeoForge itself. */
    private static boolean modded(Identifier id) {
        return !id.getNamespace().equals("minecraft") && !id.getNamespace().equals("neoforge");
    }

    private static JsonObject blocks() {
        // Which block entity type each block creates: the type lists its blocks, not the reverse.
        var blockEntities = new java.util.HashMap<Block, Identifier>();
        for (var type : BuiltInRegistries.BLOCK_ENTITY_TYPE) {
            var typeId = BuiltInRegistries.BLOCK_ENTITY_TYPE.getKey(type);
            if (modded(typeId)) {
                type.getValidBlocks().forEach(valid -> blockEntities.put(valid, typeId));
            }
        }

        var out = new JsonObject();
        for (var block : BuiltInRegistries.BLOCK) {
            var id = BuiltInRegistries.BLOCK.getKey(block);
            if (!modded(id)) {
                continue;
            }
            var entry = new JsonObject();
            // Properties in the order the state definition numbers states by (sorted by name,
            // the first varying slowest), values in the property's own order.
            var properties = new JsonArray();
            for (Property<?> property : block.getStateDefinition().getProperties()) {
                properties.add(property(property));
            }
            entry.add("properties", properties);
            entry.addProperty("default_state", block.getStateDefinition().getPossibleStates().indexOf(block.defaultBlockState()));
            entry.addProperty("hardness", block.defaultDestroyTime());
            entry.addProperty("blast_resistance", block.getExplosionResistance());
            entry.addProperty("luminance", block.defaultBlockState().getLightEmission());
            entry.addProperty("requires_tool", block.defaultBlockState().requiresCorrectToolForDrops());
            entry.addProperty("has_block_entity", block instanceof EntityBlock);
            if (blockEntities.containsKey(block)) {
                entry.addProperty("block_entity", blockEntities.get(block).toString());
            }
            var item = BuiltInRegistries.ITEM.getKey(block.asItem());
            if (modded(item)) {
                entry.addProperty("item", item.toString());
            }
            out.add(id.toString(), entry);
        }
        return out;
    }

    private static <T extends Comparable<T>> JsonObject property(Property<T> property) {
        var out = new JsonObject();
        out.addProperty("name", property.getName());
        var values = new JsonArray();
        for (T value : property.getPossibleValues()) {
            values.add(property.getName(value));
        }
        out.add("values", values);
        return out;
    }

    private static JsonObject items() {
        var out = new JsonObject();
        for (var item : BuiltInRegistries.ITEM) {
            var id = BuiltInRegistries.ITEM.getKey(item);
            if (!modded(id)) {
                continue;
            }
            var entry = new JsonObject();
            // Components (stack size and the rest) are not bound in a data run.
            if (item instanceof BlockItem blockItem) {
                entry.addProperty("block", BuiltInRegistries.BLOCK.getKey(blockItem.getBlock()).toString());
            }
            out.add(id.toString(), entry);
        }
        return out;
    }

    private static JsonArray ids(Registry<?> registry) {
        var out = new JsonArray();
        registry.keySet().stream().filter(RegistryDump::modded).map(Identifier::toString).sorted().forEach(out::add);
        return out;
    }

    /** The mods' payloads, as NeoForge negotiates them. A data run never sets the network up itself. */
    @SuppressWarnings("unchecked")
    private static JsonArray channels() {
        var out = new JsonArray();
        try {
            NetworkRegistry.setup();
            Field field = NetworkRegistry.class.getDeclaredField("PAYLOAD_REGISTRATIONS");
            field.setAccessible(true);
            var registrations = (Map<ConnectionProtocol, Map<Identifier, PayloadRegistration<?>>>) field.get(null);
            for (var phase : registrations.entrySet()) {
                for (var registration : phase.getValue().values()) {
                    if (!modded(registration.id())) {
                        continue;
                    }
                    var entry = new JsonObject();
                    entry.addProperty("id", registration.id().toString());
                    entry.addProperty("phase", phase.getKey() == ConnectionProtocol.CONFIGURATION ? "configuration" : "play");
                    entry.addProperty("version", registration.version());
                    registration.flow().ifPresent(flow -> entry.addProperty("flow", flow.name().toLowerCase()));
                    entry.addProperty("optional", registration.optional());
                    out.add(entry);
                }
            }
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("cannot read NeoForge's payload registrations", e);
        }
        return out;
    }
}
