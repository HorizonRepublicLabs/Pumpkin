package dev.pumpkin.bridge;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import dev.pumpkin.shim.Unimplemented;
import java.util.Optional;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;

/**
 * The save format a mod's block entity writes through.
 *
 * <p>Backed by JSON rather than vanilla NBT: the blob travels to the Rust side as an
 * opaque string inside Pumpkin's own block entity, so the only contract is that this
 * class can read back what it wrote. A world saved by real NeoForge is not readable here
 * and vice versa -- an accepted boundary, said out loud where the blob is stored.
 *
 * <p>Codec-typed stores carry the one shape mods actually persist -- a list of item
 * stacks -- by recognising the value rather than running the codec, which stays inert.
 * Anything else refuses with a key naming the method, feeding the burndown.
 */
public final class PumpkinValueIO {
    private PumpkinValueIO() {
    }

    public static final class Output implements ValueOutput {
        private final JsonObject json = new JsonObject();

        public JsonObject pumpkinJson() {
            return json;
        }

        @Override
        public <T> void store(String name, Codec<T> codec, T value) {
            // The codec is inert; the value's own shape is what gets recognised. Item
            // stack lists are the one thing the mods this host runs persist this way.
            if (value instanceof NonNullList<?> list) {
                // Written in vanilla's own stack shape -- a list of {id, count} -- so the
                // same fields, converted to NBT, are readable by a modded client's
                // renderer as well as by this class.
                JsonArray items = new JsonArray();
                for (Object element : list) {
                    JsonObject slot = new JsonObject();
                    if (element instanceof ItemStack stack && !stack.isEmpty()) {
                        slot.addProperty("id", PumpkinInteractions.pumpkinItemId(stack));
                        slot.addProperty("count", stack.count());
                    }
                    items.add(slot);
                }
                json.add(name, items);
                return;
            }
            // A recipe id in progress-tracking saves.
            if (value instanceof net.minecraft.resources.Identifier identifier) {
                json.addProperty(name, "pumpkin:identifier/" + identifier);
                return;
            }
            if (value instanceof net.minecraft.resources.ResourceKey<?> key) {
                json.addProperty(name, "pumpkin:identifier/" + key.identifier());
                return;
            }
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueOutput.store:(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V");
        }

        @Override
        public <T> void storeNullable(String name, Codec<T> codec, T value) {
            if (value != null) {
                store(name, codec, value);
            }
        }

        @Override
        public <T> void store(MapCodec<T> codec, T value) {
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueOutput.store:(Lcom/mojang/serialization/MapCodec;Ljava/lang/Object;)V");
        }

        @Override
        public void putBoolean(String name, boolean value) {
            json.addProperty(name, value);
        }

        @Override
        public void putByte(String name, byte value) {
            json.addProperty(name, value);
        }

        @Override
        public void putShort(String name, short value) {
            json.addProperty(name, value);
        }

        @Override
        public void putInt(String name, int value) {
            json.addProperty(name, value);
        }

        @Override
        public void putLong(String name, long value) {
            json.addProperty(name, value);
        }

        @Override
        public void putFloat(String name, float value) {
            json.addProperty(name, value);
        }

        @Override
        public void putDouble(String name, double value) {
            json.addProperty(name, value);
        }

        @Override
        public void putString(String name, String value) {
            json.addProperty(name, value);
        }

        @Override
        public void putIntArray(String name, int[] value) {
            JsonArray array = new JsonArray();
            for (int entry : value) {
                array.add(entry);
            }
            json.add(name, array);
        }

        @Override
        public ValueOutput child(String name) {
            Output child = new Output();
            json.add(name, child.json);
            return child;
        }

        @Override
        public ValueOutput.ValueOutputList childrenList(String name) {
            JsonArray array = new JsonArray();
            json.add(name, array);
            return new ValueOutputList() {
                @Override
                public ValueOutput addChild() {
                    Output child = new Output();
                    array.add(child.json);
                    return child;
                }

                @Override
                public void discardLast() {
                    array.remove(array.size() - 1);
                }

                @Override
                public boolean isEmpty() {
                    return array.isEmpty();
                }
            };
        }

        @Override
        public <T> ValueOutput.TypedOutputList<T> list(String name, Codec<T> codec) {
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueOutput.list:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Lnet/minecraft/world/level/storage/ValueOutput$TypedOutputList;");
        }

        @Override
        public void discard(String name) {
            json.remove(name);
        }

        @Override
        public boolean isEmpty() {
            return json.isEmpty();
        }
    }

    public static final class Input implements ValueInput {
        private final JsonObject json;

        public Input(JsonObject json) {
            this.json = json;
        }

        @SuppressWarnings("unchecked")
        @Override
        public <T> Optional<T> read(String name, Codec<T> codec) {
            JsonElement element = json.get(name);
            if (element == null) {
                return Optional.empty();
            }
            if (element.isJsonPrimitive() && element.getAsJsonPrimitive().isString()
                    && element.getAsString().startsWith("pumpkin:identifier/")) {
                @SuppressWarnings("unchecked")
                T identifier = (T) net.minecraft.resources.Identifier.parse(
                        element.getAsString().substring("pumpkin:identifier/".length()));
                return Optional.of(identifier);
            }
            // The mirror of Output.store: an array of {id, count} slots is a stack list.
            if (element.isJsonArray()) {
                JsonArray items = element.getAsJsonArray();
                NonNullList<ItemStack> stacks =
                        NonNullList.withSize(items.size(), ItemStack.EMPTY);
                for (int i = 0; i < items.size(); i++) {
                    JsonObject slot = items.get(i).getAsJsonObject();
                    if (slot.has("id")) {
                        stacks.set(i, PumpkinInteractions.pumpkinBuildStack(
                                slot.get("id").getAsString(), slot.get("count").getAsInt()));
                    }
                }
                return Optional.of((T) stacks);
            }
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueInput.read:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Ljava/util/Optional;");
        }

        @Override
        public <T> Optional<T> read(MapCodec<T> codec) {
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueInput.read:(Lcom/mojang/serialization/MapCodec;)Ljava/util/Optional;");
        }

        @Override
        public Optional<ValueInput> child(String name) {
            JsonElement element = json.get(name);
            return element != null && element.isJsonObject()
                    ? Optional.of(new Input(element.getAsJsonObject()))
                    : Optional.empty();
        }

        @Override
        public ValueInput childOrEmpty(String name) {
            return child(name).orElseGet(() -> new Input(new JsonObject()));
        }

        @Override
        public Optional<ValueInput.ValueInputList> childrenList(String name) {
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueInput.childrenList:(Ljava/lang/String;)Ljava/util/Optional;");
        }

        @Override
        public ValueInput.ValueInputList childrenListOrEmpty(String name) {
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueInput.childrenListOrEmpty:(Ljava/lang/String;)Lnet/minecraft/world/level/storage/ValueInput$ValueInputList;");
        }

        @Override
        public <T> Optional<ValueInput.TypedInputList<T>> list(String name, Codec<T> codec) {
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueInput.list:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Ljava/util/Optional;");
        }

        @Override
        public <T> ValueInput.TypedInputList<T> listOrEmpty(String name, Codec<T> codec) {
            throw Unimplemented.forMember(
                    "net/minecraft/world/level/storage/ValueInput.listOrEmpty:(Ljava/lang/String;Lcom/mojang/serialization/Codec;)Lnet/minecraft/world/level/storage/ValueInput$TypedInputList;");
        }

        @Override
        public boolean getBooleanOr(String name, boolean defaultValue) {
            return json.has(name) ? json.get(name).getAsBoolean() : defaultValue;
        }

        @Override
        public byte getByteOr(String name, byte defaultValue) {
            return json.has(name) ? json.get(name).getAsByte() : defaultValue;
        }

        @Override
        public int getShortOr(String name, short defaultValue) {
            return json.has(name) ? json.get(name).getAsShort() : defaultValue;
        }

        @Override
        public Optional<Integer> getInt(String name) {
            return json.has(name) ? Optional.of(json.get(name).getAsInt()) : Optional.empty();
        }

        @Override
        public int getIntOr(String name, int defaultValue) {
            return json.has(name) ? json.get(name).getAsInt() : defaultValue;
        }

        @Override
        public long getLongOr(String name, long defaultValue) {
            return json.has(name) ? json.get(name).getAsLong() : defaultValue;
        }

        @Override
        public float getFloatOr(String name, float defaultValue) {
            return json.has(name) ? json.get(name).getAsFloat() : defaultValue;
        }

        @Override
        public double getDoubleOr(String name, double defaultValue) {
            return json.has(name) ? json.get(name).getAsDouble() : defaultValue;
        }

        @Override
        public Optional<Long> getLong(String name) {
            return json.has(name) ? Optional.of(json.get(name).getAsLong()) : Optional.empty();
        }

        @Override
        public Optional<String> getString(String name) {
            return json.has(name) ? Optional.of(json.get(name).getAsString()) : Optional.empty();
        }

        @Override
        public String getStringOr(String name, String defaultValue) {
            return json.has(name) ? json.get(name).getAsString() : defaultValue;
        }

        @Override
        public Optional<int[]> getIntArray(String name) {
            if (!json.has(name)) {
                return Optional.empty();
            }
            JsonArray array = json.getAsJsonArray(name);
            int[] values = new int[array.size()];
            for (int i = 0; i < values.length; i++) {
                values[i] = array.get(i).getAsInt();
            }
            return Optional.of(values);
        }

        @Override
        public net.minecraft.core.HolderLookup.Provider lookup() {
            return dev.pumpkin.shim.Stubs.of(net.minecraft.core.HolderLookup.Provider.class,
                    "net/minecraft/core/HolderLookup$Provider");
        }
    }
}
