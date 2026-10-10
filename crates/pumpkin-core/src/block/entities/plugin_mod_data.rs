//! The bridge blob's readable projection.
//!
//! A JVM-hosted mod entity saves itself through the bridge as base64-wrapped JSON (see
//! `PumpkinValueIO` on the Java side, which owns the format). The blob is what Java reads
//! back; this module is what everyone else reads: a structural JSON-to-NBT conversion, so
//! the same fields can ride a chunk packet to a client whose modded renderer looks for
//! them by name.

use base64::Engine;
use pumpkin_nbt::compound::NbtCompound;
use pumpkin_nbt::tag::NbtTag;
use serde_json::Value;

/// The data-bag key the JVM loader stores the blob under.
pub const MOD_DATA_KEY: &str = "pumpkin:mod_data";

/// Decodes a base64 JSON blob into an NBT compound, or `None` when it is not one.
#[must_use]
pub fn decode_blob_to_nbt(blob: &str) -> Option<NbtCompound> {
    let bytes = base64::engine::general_purpose::STANDARD
        .decode(blob)
        .ok()?;
    let value: Value = serde_json::from_slice(&bytes).ok()?;
    match json_to_nbt(&value) {
        NbtTag::Compound(compound) => Some(compound),
        _ => None,
    }
}

fn json_to_nbt(value: &Value) -> NbtTag {
    match value {
        Value::Null => NbtTag::End,
        Value::Bool(flag) => NbtTag::Byte(i8::from(*flag)),
        Value::Number(number) => {
            // Whole numbers ride as ints (the shape stack counts and progress fields
            // use); anything fractional as a double. The blob does not distinguish
            // further, and neither does the client-side codec these fields feed.
            number.as_i64().map_or_else(
                || NbtTag::Double(number.as_f64().unwrap_or(0.0)),
                |whole| i32::try_from(whole).map_or(NbtTag::Long(whole), NbtTag::Int),
            )
        }
        Value::String(text) => NbtTag::String(text.clone().into()),
        Value::Array(entries) => NbtTag::List(entries.iter().map(json_to_nbt).collect()),
        Value::Object(fields) => {
            let mut compound = NbtCompound::new();
            for (key, entry) in fields {
                let tag = json_to_nbt(entry);
                if !matches!(tag, NbtTag::End) {
                    compound.put(key, tag);
                }
            }
            NbtTag::Compound(compound)
        }
    }
}

#[cfg(test)]
mod tests {
    use base64::Engine;

    use super::decode_blob_to_nbt;

    #[test]
    fn a_pedestal_blob_becomes_client_shaped_nbt() {
        let json = r#"{"inventory":{"stacks":[{"id":"examplemod:shard","count":1},{}]}}"#;
        let blob = base64::engine::general_purpose::STANDARD.encode(json);
        let nbt = decode_blob_to_nbt(&blob).expect("decodes");
        let inventory = nbt.get_compound("inventory").expect("inventory");
        let stacks = inventory.get_list("stacks").expect("stacks");
        assert_eq!(stacks.len(), 2);
    }

    #[test]
    fn garbage_is_none() {
        assert!(decode_blob_to_nbt("not base64!").is_none());
        let blob = base64::engine::general_purpose::STANDARD.encode("[1,2,3]");
        assert!(decode_blob_to_nbt(&blob).is_none());
    }
}
