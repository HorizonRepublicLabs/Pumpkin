package net.minecraft.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.Reader;
import dev.pumpkin.shim.Unimplemented;

public class GsonHelper {

    public static String convertToString(JsonElement element, String name) {
        throw Unimplemented.forMember("net/minecraft/util/GsonHelper.convertToString:(Lcom/google/gson/JsonElement;Ljava/lang/String;)Ljava/lang/String;");
    }

    public static JsonObject getAsJsonObject(JsonObject object, String name) {
        throw Unimplemented.forMember("net/minecraft/util/GsonHelper.getAsJsonObject:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonObject;");
    }

    public static JsonObject getAsJsonObject(JsonObject object, String name, JsonObject def) {
        throw Unimplemented.forMember("net/minecraft/util/GsonHelper.getAsJsonObject:(Lcom/google/gson/JsonObject;Ljava/lang/String;Lcom/google/gson/JsonObject;)Lcom/google/gson/JsonObject;");
    }

    public static JsonArray getAsJsonArray(JsonObject object, String name) {
        throw Unimplemented.forMember("net/minecraft/util/GsonHelper.getAsJsonArray:(Lcom/google/gson/JsonObject;Ljava/lang/String;)Lcom/google/gson/JsonArray;");
    }

    public static JsonArray getAsJsonArray(JsonObject object, String name, JsonArray def) {
        throw Unimplemented.forMember("net/minecraft/util/GsonHelper.getAsJsonArray:(Lcom/google/gson/JsonObject;Ljava/lang/String;Lcom/google/gson/JsonArray;)Lcom/google/gson/JsonArray;");
    }

    public static JsonObject parse(String string) {
        throw Unimplemented.forMember("net/minecraft/util/GsonHelper.parse:(Ljava/lang/String;)Lcom/google/gson/JsonObject;");
    }

    public static JsonObject parse(Reader reader) {
        throw Unimplemented.forMember("net/minecraft/util/GsonHelper.parse:(Ljava/io/Reader;)Lcom/google/gson/JsonObject;");
    }

    private static class CountedAppendable implements Appendable {

        public CountedAppendable(int limit) {
        }

        public Appendable append(CharSequence csq) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper$CountedAppendable.append:(Ljava/lang/CharSequence;)Ljava/lang/Appendable;");
        }

        public Appendable append(CharSequence csq, int start, int end) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper$CountedAppendable.append:(Ljava/lang/CharSequence;II)Ljava/lang/Appendable;");
        }

        public Appendable append(char c) {
            throw Unimplemented.forMember("net/minecraft/util/GsonHelper$CountedAppendable.append:(C)Ljava/lang/Appendable;");
        }

        protected CountedAppendable() {
        }
    }

    public GsonHelper() {
    }
}
