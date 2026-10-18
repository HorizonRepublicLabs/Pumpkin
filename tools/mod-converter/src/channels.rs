//! Finds a mod's network channels in its bytecode.
//!
//! A `NeoForge` client refuses a server that does not offer every payload its mods registered
//! as required, so a converted mod has to declare them. They live in code, but in a shape
//! that is regular enough to read without running it: each payload has a
//! `CustomPacketPayload.Type` built from an identifier, almost always in a static
//! initialiser, from string constants:
//!
//! ```java
//! TYPE = new CustomPacketPayload.Type<>(Identifier.fromNamespaceAndPath("ex", "sync"));
//! TYPE = new CustomPacketPayload.Type<>(ExMod.rl("sync"));   // a one-argument helper
//! ```
//!
//! A payload used in a `configuration*` registration call is a configuration channel;
//! everything else is play. The version is the constant passed to
//! `RegisterPayloadHandlersEvent.registrar`, or the mod's own version when it is computed.
//!
//! Finding a type that is never registered is harmless — the server offers its channels as
//! optional, so the client simply does not pick it — while missing one refuses the client.
//! So this errs towards finding too many.

use std::collections::{BTreeMap, BTreeSet};

use crate::classfile::{self, Op};

const IDENTIFIER: &str = "net/minecraft/resources/Identifier";
const PAYLOAD_TYPE: &str = "net/minecraft/network/protocol/common/custom/CustomPacketPayload$Type";
const HELPER_DESC: &str = "(Ljava/lang/String;)Lnet/minecraft/resources/Identifier;";

/// One channel to declare.
#[derive(Debug, PartialEq, Eq, PartialOrd, Ord)]
pub struct Channel {
    pub id: String,
    pub phase: &'static str,
    pub version: String,
}

/// The channels in `classes`, and a line per thing the operator should check.
pub fn extract(classes: &[classfile::Class], mod_version: &str) -> (Vec<Channel>, Vec<String>) {
    let helpers = helpers(classes);

    let mut types: BTreeSet<String> = BTreeSet::new();
    let mut fields: BTreeMap<(String, String), String> = BTreeMap::new();
    let mut versions: BTreeSet<String> = BTreeSet::new();
    let mut computed_version = false;

    for class in classes {
        for method in &class.methods {
            let mut strings: Vec<&str> = Vec::new();
            let mut id: Option<String> = None;
            let mut built_type: Option<String> = None;
            let mut previous: Option<&Op> = None;
            for op in &method.ops {
                match op {
                    Op::Ldc(s) => strings.push(s),
                    Op::Invoke { owner, name, desc } => {
                        if let Some(made) = identifier(owner, name, desc, &strings, &helpers) {
                            id = Some(made);
                        } else if owner == PAYLOAD_TYPE && name == "<init>" {
                            if let Some(made) = id.take() {
                                types.insert(made.clone());
                                built_type = Some(made);
                            }
                        } else if name == "registrar"
                            && owner.ends_with("RegisterPayloadHandlersEvent")
                        {
                            match previous {
                                Some(Op::Ldc(v)) => {
                                    versions.insert(v.clone());
                                }
                                _ => computed_version = true,
                            }
                        } else if desc.starts_with(&format!("(L{IDENTIFIER};"))
                            && (name.starts_with("play") || name.starts_with("configuration"))
                        {
                            // A registration that builds its type itself from an id.
                            if let Some(made) = id.take() {
                                types.insert(made);
                            }
                        }
                    }
                    Op::PutStatic { owner, name } => {
                        if let Some(made) = built_type.take() {
                            fields.insert((owner.clone(), name.clone()), made);
                        }
                    }
                    _ => {}
                }
                previous = Some(op);
            }
        }
    }

    let configuration = configuration_types(classes, &fields);

    let mut report = Vec::new();
    let version = match (versions.len(), computed_version) {
        (1, false) => versions.into_iter().next().unwrap_or_default(),
        (0, false) if types.is_empty() => String::new(),
        (0, _) => {
            report.push(format!(
                "channel version is computed in code; assumed the mod version {mod_version}"
            ));
            mod_version.to_string()
        }
        _ => {
            let first = versions.iter().next().cloned().unwrap_or_default();
            report.push(format!(
                "several channel versions ({}); all channels declared under {first}, check them",
                versions.into_iter().collect::<Vec<_>>().join(", ")
            ));
            first
        }
    };

    let channels = types
        .into_iter()
        .filter(|id| !id.starts_with("minecraft:"))
        .map(|id| Channel {
            phase: if configuration.contains(&id) {
                "configuration"
            } else {
                "play"
            },
            id,
            version: version.clone(),
        })
        .collect();
    (channels, report)
}

/// One-argument helpers that turn a path into an identifier in a fixed namespace, e.g.
/// `Mekanism.rl(String)`, keyed `owner.name`, valued with the namespace.
fn helpers(classes: &[classfile::Class]) -> BTreeMap<String, String> {
    let mut out = BTreeMap::new();
    for class in classes {
        for method in class.methods.iter().filter(|m| m.desc == HELPER_DESC) {
            let mut last: Option<&str> = None;
            for op in &method.ops {
                match op {
                    Op::Ldc(s) => last = Some(s),
                    Op::Invoke { owner, name, .. }
                        if owner == IDENTIFIER && name == "fromNamespaceAndPath" =>
                    {
                        if let Some(ns) = last {
                            out.insert(format!("{}.{}", class.name, method.name), ns.to_string());
                        }
                        break;
                    }
                    _ => {}
                }
            }
        }
    }
    out
}

/// The identifier an invocation builds from the constants loaded before it, if it is one
/// of the ways mods build them.
fn identifier(
    owner: &str,
    name: &str,
    desc: &str,
    strings: &[&str],
    helpers: &BTreeMap<String, String>,
) -> Option<String> {
    let last = strings.last().copied();
    if owner == IDENTIFIER {
        return match name {
            "fromNamespaceAndPath" if strings.len() >= 2 => Some(format!(
                "{}:{}",
                strings[strings.len() - 2],
                strings[strings.len() - 1]
            )),
            "parse" | "tryParse" => last.filter(|s| s.contains(':')).map(str::to_string),
            "withDefaultNamespace" => last.map(|s| format!("minecraft:{s}")),
            _ => None,
        };
    }
    if desc == HELPER_DESC {
        let ns = helpers.get(&format!("{owner}.{name}"))?;
        return last.map(|path| format!("{ns}:{path}"));
    }
    None
}

/// Payload types read shortly before a `configuration*` registration call.
fn configuration_types(
    classes: &[classfile::Class],
    fields: &BTreeMap<(String, String), String>,
) -> BTreeSet<String> {
    let mut out = BTreeSet::new();
    for method in classes.iter().flat_map(|c| &c.methods) {
        let mut pending: Option<(&String, usize)> = None;
        for op in &method.ops {
            if let Op::GetStatic { owner, name } = op
                && let Some(id) = fields.get(&(owner.clone(), name.clone()))
            {
                pending = Some((id, 0));
                continue;
            }
            if let Some((id, seen)) = pending {
                if let Op::Invoke { name, .. } = op
                    && name.starts_with("configuration")
                {
                    out.insert(id.clone());
                    pending = None;
                } else if seen >= 4 {
                    pending = None;
                } else {
                    pending = Some((id, seen + 1));
                }
            }
        }
    }
    out
}

#[cfg(test)]
mod tests {
    use super::*;
    use classfile::{Class, Method};

    fn invoke(owner: &str, name: &str, desc: &str) -> Op {
        Op::Invoke {
            owner: owner.into(),
            name: name.into(),
            desc: desc.into(),
        }
    }

    fn method(name: &str, desc: &str, ops: Vec<Op>) -> Method {
        Method {
            name: name.into(),
            desc: desc.into(),
            ops,
        }
    }

    fn type_init() -> Op {
        invoke(
            PAYLOAD_TYPE,
            "<init>",
            "(Lnet/minecraft/resources/Identifier;)V",
        )
    }

    #[test]
    fn finds_types_built_directly_and_through_a_helper() {
        let classes = vec![
            Class {
                name: "ex/Ex".into(),
                methods: vec![method(
                    "rl",
                    HELPER_DESC,
                    vec![
                        Op::Ldc("ex".into()),
                        invoke(IDENTIFIER, "fromNamespaceAndPath", ""),
                    ],
                )],
            },
            Class {
                name: "ex/net/Sync".into(),
                methods: vec![method(
                    "<clinit>",
                    "()V",
                    vec![
                        Op::New(PAYLOAD_TYPE.into()),
                        Op::Ldc("sync".into()),
                        invoke("ex/Ex", "rl", HELPER_DESC),
                        type_init(),
                        Op::PutStatic {
                            owner: "ex/net/Sync".into(),
                            name: "TYPE".into(),
                        },
                    ],
                )],
            },
            Class {
                name: "ex/net/Hello".into(),
                methods: vec![method(
                    "<clinit>",
                    "()V",
                    vec![
                        Op::Ldc("ex".into()),
                        Op::Ldc("hello".into()),
                        invoke(IDENTIFIER, "fromNamespaceAndPath", ""),
                        type_init(),
                        Op::PutStatic {
                            owner: "ex/net/Hello".into(),
                            name: "TYPE".into(),
                        },
                    ],
                )],
            },
            Class {
                name: "ex/net/Handler".into(),
                methods: vec![method(
                    "register",
                    "()V",
                    vec![
                        Op::Ldc("3".into()),
                        invoke("x/RegisterPayloadHandlersEvent", "registrar", ""),
                        Op::GetStatic {
                            owner: "ex/net/Hello".into(),
                            name: "TYPE".into(),
                        },
                        Op::GetStatic {
                            owner: "ex/net/Hello".into(),
                            name: "CODEC".into(),
                        },
                        invoke("x/PayloadRegistrar", "configurationToClient", ""),
                    ],
                )],
            },
        ];
        let (channels, report) = extract(&classes, "9.9");
        assert!(report.is_empty(), "{report:?}");
        assert_eq!(
            channels,
            vec![
                Channel {
                    id: "ex:hello".into(),
                    phase: "configuration",
                    version: "3".into()
                },
                Channel {
                    id: "ex:sync".into(),
                    phase: "play",
                    version: "3".into()
                },
            ]
        );
    }

    #[test]
    fn a_computed_version_falls_back_to_the_mod_version() {
        let classes = vec![Class {
            name: "ex/A".into(),
            methods: vec![method(
                "<clinit>",
                "()V",
                vec![
                    Op::Ldc("ex:a".into()),
                    invoke(IDENTIFIER, "parse", ""),
                    type_init(),
                    invoke("ex/Version", "toString", "()Ljava/lang/String;"),
                    invoke("x/RegisterPayloadHandlersEvent", "registrar", ""),
                ],
            )],
        }];
        let (channels, report) = extract(&classes, "10.8.0");
        assert_eq!(channels[0].version, "10.8.0");
        assert_eq!(report.len(), 1);
    }
}
