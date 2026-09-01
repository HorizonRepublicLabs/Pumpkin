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
            // A mod record (Mekanism's CapacitorState and kin): its own components, by
            // their own names -- the shape its codec would write, minus codec plumbing.
            if (value.getClass().isRecord()) {
                JsonObject recordJson = new JsonObject();
                // The class name is the reload contract: Input.read rebuilds the record
                // through its canonical constructor by this name.
                recordJson.addProperty("pumpkin:record", value.getClass().getName());
                for (java.lang.reflect.RecordComponent component : value.getClass().getRecordComponents()) {
                    try {
                        Object field = component.getAccessor().invoke(value);
                        if (field instanceof Number number) {
                            recordJson.addProperty(component.getName(), number);
                        } else if (field instanceof Boolean bool) {
                            recordJson.addProperty(component.getName(), bool);
                        } else if (field instanceof String text) {
                            recordJson.addProperty(component.getName(), text);
                        } else if (field instanceof net.neoforged.neoforge.transfer.fluid.FluidResource fluidResource) {
                            String fluid = fluidResource.isEmpty() ? "empty"
                                    : fluidResource.getFluid().pumpkinVanillaName;
                            recordJson.addProperty(component.getName(), fluid == null ? "unknown" : fluid);
                        } else if (field instanceof net.neoforged.neoforge.transfer.item.ItemResource itemResource) {
                            recordJson.addProperty(component.getName(), itemResource.isEmpty()
                                    ? "empty"
                                    : PumpkinInteractions.pumpkinItemId(itemResource.toStack(1)));
                        } else {
                            throw Unimplemented.forMember(
                                    "net/minecraft/world/level/storage/ValueOutput.store (record component "
                                            + component.getName() + " of shape "
                                            + (field == null ? "null" : field.getClass().getName()) + ")");
                        }
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                }
                json.add(name, recordJson);
                return;
            }
            // A codec-wrapped primitive (heat, progress): the value's own shape is the
            // honest serialisation.
            if (value instanceof Number number) {
                json.addProperty(name, number);
                return;
            }
            if (value instanceof Boolean bool) {
                json.addProperty(name, bool);
                return;
            }
            if (value instanceof String text) {
                json.addProperty(name, text);
                return;
            }
            // A mod resource stack (Mekanism's LargeResourceStack and kin): a record of
            // {resource, amount}, reached by reflection because the mod's class is not
            // on this classpath. Item resources save in the same {id, count} shape as
            // stacks; other resource kinds fail loudly below.
            java.lang.reflect.Method resourceAccessor = null;
            java.lang.reflect.Method amountAccessor = null;
            for (java.lang.reflect.Method method : value.getClass().getMethods()) {
                if (method.getParameterCount() != 0) {
                    continue;
                }
                if (method.getName().equals("resource")) {
                    resourceAccessor = method;
                } else if (method.getName().equals("amount")) {
                    amountAccessor = method;
                }
            }
            if (resourceAccessor != null && amountAccessor != null) {
                try {
                    Object resource = resourceAccessor.invoke(value);
                    Object amount = amountAccessor.invoke(value);
                    if (resource instanceof net.neoforged.neoforge.transfer.item.ItemResource itemResource
                            && amount instanceof Long count) {
                        JsonObject stackJson = new JsonObject();
                        if (!itemResource.isEmpty()) {
                            stackJson.addProperty("id", PumpkinInteractions
                                    .pumpkinItemId(itemResource.toStack(1)));
                            stackJson.addProperty("count", count);
                        }
                        json.add(name, stackJson);
                        return;
                    }
                } catch (ReflectiveOperationException ignored) {
                    // Fall through to the loud refusal below.
                }
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
                    "net/minecraft/world/level/storage/ValueOutput.store:(Ljava/lang/String;Lcom/mojang/serialization/Codec;Ljava/lang/Object;)V"
                            + " (value shape " + value.getClass().getName() + ")");
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
            // The mirror of Output.store's record branch for a mod resource stack:
            // {resource, amount}. Which resource kind it is comes from the codec itself
            // -- the mod passes one of LargeResourceStack's helper codecs, and identity
            // against them is the honest answer; an unrecognised codec still refuses.
            if (element.isJsonObject()) {
                JsonObject stored = element.getAsJsonObject();
                if (stored.has("resource") && stored.has("amount")) {
                    try {
                        // The codec object itself is DFU plumbing from the shim's own
                        // classpath; the mod's classes live in the mod-jar loader. The
                        // caller IS mod code, so its loader is the right one.
                        Class<?> caller = StackWalker
                                .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                                .getCallerClass();
                        Class<?> large = Class.forName("mekanism.api.resource.LargeResourceStack",
                                true, caller.getClassLoader());
                        for (String helperName
                                : new String[] {"ITEM_HELPER", "FLUID_HELPER", "CHEMICAL_HELPER"}) {
                            Object helper = large.getField(helperName).get(null);
                            Class<?> helperClass = helper.getClass();
                            if (codec != helperClass.getMethod("codec").invoke(helper)
                                    && codec != helperClass.getMethod("optionalCodec").invoke(helper)
                                    && codec != helperClass.getMethod("orEmptyCodec").invoke(helper)) {
                                continue;
                            }
                            String resourceName = stored.get("resource").getAsString();
                            long amount = stored.get("amount").getAsLong();
                            if (resourceName.equals("empty") || amount <= 0) {
                                return Optional.of((T) helperClass.getMethod("empty").invoke(helper));
                            }
                            Object resource;
                            if (helperName.equals("ITEM_HELPER")) {
                                resource = net.neoforged.neoforge.transfer.item.ItemResource.of(
                                        PumpkinInteractions.pumpkinBuildStack(resourceName, 1));
                            } else if (helperName.equals("FLUID_HELPER")) {
                                net.minecraft.world.level.material.Fluid fluid =
                                        switch (resourceName) {
                                            // Output writes the fluid's vanilla name, which
                                            // carries no namespace ("lava"); accept the
                                            // qualified form too.
                                            case "lava", "minecraft:lava" ->
                                                    net.minecraft.world.level.material.Fluids.LAVA;
                                            case "water", "minecraft:water" ->
                                                    net.minecraft.world.level.material.Fluids.WATER;
                                            default -> null;
                                        };
                                if (fluid == null) {
                                    throw Unimplemented.forMember(
                                            "ValueInput.read (fluid resource " + resourceName + ")");
                                }
                                resource = net.neoforged.neoforge.transfer.fluid.FluidResource.of(fluid);
                            } else {
                                throw Unimplemented.forMember(
                                        "ValueInput.read (chemical resource reload)");
                            }
                            return Optional.of((T) large
                                    .getConstructor(
                                            net.neoforged.neoforge.transfer.resource.Resource.class,
                                            long.class)
                                    .newInstance(resource, amount));
                        }
                    } catch (ClassNotFoundException fallThrough) {
                        // Not called from that mod's code: the refusal below says so.
                    } catch (ReflectiveOperationException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            // The mirror of Output.store's record branch: any record it stamped with
            // its class name comes back through the canonical constructor. Component
            // values reload by their own shapes; a shape with no honest reload refuses.
            if (element.isJsonObject() && element.getAsJsonObject().has("pumpkin:record")) {
                JsonObject stored = element.getAsJsonObject();
                try {
                    Class<?> caller = StackWalker
                            .getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE)
                            .getCallerClass();
                    Class<?> recordClass = Class.forName(
                            stored.get("pumpkin:record").getAsString(), true,
                            caller.getClassLoader());
                    java.lang.reflect.RecordComponent[] components =
                            recordClass.getRecordComponents();
                    Class<?>[] types = new Class<?>[components.length];
                    Object[] values = new Object[components.length];
                    for (int i = 0; i < components.length; i++) {
                        Class<?> type = components[i].getType();
                        types[i] = type;
                        JsonElement field = stored.get(components[i].getName());
                        if (field == null) {
                            throw Unimplemented.forMember("ValueInput.read (record "
                                    + recordClass.getName() + " missing component "
                                    + components[i].getName() + ")");
                        }
                        if (type == double.class || type == Double.class) {
                            values[i] = field.getAsDouble();
                        } else if (type == float.class || type == Float.class) {
                            values[i] = field.getAsFloat();
                        } else if (type == long.class || type == Long.class) {
                            values[i] = field.getAsLong();
                        } else if (type == int.class || type == Integer.class) {
                            values[i] = field.getAsInt();
                        } else if (type == boolean.class || type == Boolean.class) {
                            values[i] = field.getAsBoolean();
                        } else if (type == String.class) {
                            values[i] = field.getAsString();
                        } else if (type == net.neoforged.neoforge.transfer.item.ItemResource.class) {
                            String id = field.getAsString();
                            values[i] = id.equals("empty")
                                    ? net.neoforged.neoforge.transfer.item.ItemResource.EMPTY
                                    : net.neoforged.neoforge.transfer.item.ItemResource
                                            .of(PumpkinInteractions.pumpkinBuildStack(id, 1));
                        } else {
                            throw Unimplemented.forMember("ValueInput.read (record "
                                    + recordClass.getName() + " component "
                                    + components[i].getName() + " of type "
                                    + type.getName() + ")");
                        }
                    }
                    return Optional.of((T) recordClass.getDeclaredConstructor(types)
                            .newInstance(values));
                } catch (ClassNotFoundException fallThrough) {
                    // Not called from that mod's code: the refusal below says so.
                } catch (ReflectiveOperationException e) {
                    throw new RuntimeException(e);
                }
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
            JsonElement element = json.get(name);
            if (element == null || !element.isJsonArray()) {
                return Optional.empty();
            }
            java.util.ArrayList<ValueInput> children = new java.util.ArrayList<>();
            for (JsonElement entry : element.getAsJsonArray()) {
                if (entry.isJsonObject()) {
                    children.add(new Input(entry.getAsJsonObject()));
                }
            }
            return Optional.of(pumpkinListOf(children));
        }

        @Override
        public ValueInput.ValueInputList childrenListOrEmpty(String name) {
            return childrenList(name).orElseGet(() -> pumpkinListOf(java.util.List.of()));
        }

        private static ValueInput.ValueInputList pumpkinListOf(java.util.List<ValueInput> children) {
            return new ValueInput.ValueInputList() {
                @Override
                public boolean isEmpty() {
                    return children.isEmpty();
                }

                @Override
                public java.util.stream.Stream<ValueInput> stream() {
                    return children.stream();
                }

                @Override
                public java.util.Iterator<ValueInput> iterator() {
                    return children.iterator();
                }
            };
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
