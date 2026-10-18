//! Channel negotiation, as `NeoForge`'s server runs it.
//!
//! The client answers the server's `neoforge:register` query with every payload it
//! registered: id, version, an optional packet flow, and whether it is optional. The server
//! compares that against its own and either sends the agreed set on `neoforge:network` or
//! refuses the connection. The client does not check the agreed set — it trusts it — so
//! these rules are the whole of the compatibility check, on the server alone.
//!
//! [`negotiate`] ports `NetworkComponentNegotiator.negotiate` from `NeoForge` 26.3, with one
//! deliberate difference: a server component with no flow accepts the client's. `NeoForge`
//! fails a match where only one side names a flow, but Pumpkin's channels come from plugin
//! declarations that do not carry one, and refusing every flowed channel a mod has would
//! refuse every modded client.

use bytes::Buf;
use pumpkin_protocol::codec::var_int::VarInt;

use super::channels::ChannelProtocol;

/// Which way a payload travels. Wire values are vanilla's `PacketFlow` ordinals.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum Flow {
    Serverbound,
    Clientbound,
}

impl Flow {
    const fn name(self) -> &'static str {
        match self {
            Self::Serverbound => "SERVERBOUND",
            Self::Clientbound => "CLIENTBOUND",
        }
    }
}

/// One side's offer for one channel.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct Component {
    pub id: String,
    pub version: String,
    pub flow: Option<Flow>,
    pub optional: bool,
}

/// Why a channel could not be agreed, as the translation `NeoForge`'s client shows.
#[derive(Debug, Clone, PartialEq, Eq)]
pub struct Failure {
    pub id: String,
    pub key: String,
    pub args: Vec<String>,
}

/// Agrees a channel set, or explains which channels made that impossible.
///
/// On success, returns each agreed channel with the server's version (equal to the
/// client's, or both are empty).
///
/// # Errors
/// Every channel that failed, with the reason the client will display.
pub fn negotiate(
    server: &[Component],
    client: &[Component],
) -> Result<Vec<Component>, Vec<Failure>> {
    let has = |side: &[Component], id: &str| side.iter().any(|c| c.id == id);

    // An optional channel the other side lacks is simply not used.
    let client: Vec<&Component> = client
        .iter()
        .filter(|c| !c.optional || has(server, &c.id))
        .collect();
    let server: Vec<&Component> = server
        .iter()
        .filter(|s| !s.optional || client.iter().any(|c| c.id == s.id))
        .collect();

    let missing = |side: &[&Component], other: &[&Component], key: &str| -> Vec<Failure> {
        side.iter()
            .filter(|c| !other.iter().any(|o| o.id == c.id))
            .map(|c| Failure {
                id: c.id.clone(),
                key: key.to_string(),
                args: Vec::new(),
            })
            .collect()
    };
    let failures = missing(
        &client,
        &server,
        "neoforge.network.negotiation.failure.missing.client.server",
    );
    if !failures.is_empty() {
        return Err(failures);
    }
    let failures = missing(
        &server,
        &client,
        "neoforge.network.negotiation.failure.missing.server.client",
    );
    if !failures.is_empty() {
        return Err(failures);
    }

    let mut agreed = Vec::new();
    let mut failures = Vec::new();
    for s in &server {
        // Present on both sides: the missing checks above guarantee the match.
        let Some(c) = client.iter().find(|c| c.id == s.id) else {
            continue;
        };
        match validate(s, c) {
            Some(failure) => failures.push(failure),
            None => agreed.push((*s).clone()),
        }
    }

    if failures.is_empty() {
        Ok(agreed)
    } else {
        Err(failures)
    }
}

/// `NeoForge`'s `validateComponent`, run both ways, flows first.
fn validate(server: &Component, client: &Component) -> Option<Failure> {
    let failure = |key: String, args: Vec<String>| Failure {
        id: server.id.clone(),
        key,
        args,
    };

    if let Some(server_flow) = server.flow {
        match client.flow {
            None => {
                return Some(failure(
                    "neoforge.network.negotiation.failure.flow.client.missing".into(),
                    vec![server_flow.name().into()],
                ));
            }
            Some(client_flow) if client_flow != server_flow => {
                return Some(failure(
                    "neoforge.network.negotiation.failure.flow.client.mismatch".into(),
                    vec![server_flow.name().into(), client_flow.name().into()],
                ));
            }
            Some(_) => {}
        }
    }
    // The other direction would fail a client flow the server left unset; see the module
    // docs for why Pumpkin accepts it instead.

    if server.version != client.version {
        return Some(failure(
            "neoforge.network.negotiation.failure.version.mismatch".into(),
            vec![client.version.clone(), server.version.clone()],
        ));
    }
    None
}

/// Upper bound on components in one query reply. `NeoForge` caps ad-hoc channels at 2048
/// by default, and a client's registered payloads are far fewer; this only bounds memory.
const MAX_COMPONENTS: usize = 4096;

/// Parses the client's `neoforge:register` answer into components per protocol phase.
///
/// Phases other than play and configuration are skipped, as `NeoForge` negotiates only
/// the phases it has registrations for.
///
/// # Errors
/// A description of the first malformed field.
pub fn parse_query(mut data: &[u8]) -> Result<Vec<(ChannelProtocol, Vec<Component>)>, String> {
    let buf = &mut data;
    let phases = read_len(buf)?;
    let mut out = Vec::new();
    let mut total = 0usize;
    for _ in 0..phases {
        let ordinal = read_var_int(buf)?;
        let count = read_len(buf)?;
        total += count;
        if total > MAX_COMPONENTS {
            return Err(format!("more than {MAX_COMPONENTS} channels"));
        }
        let mut components = Vec::with_capacity(count);
        for _ in 0..count {
            let id = read_string(buf)?;
            let version = read_string(buf)?;
            let flow = if read_bool(buf)? {
                Some(match read_var_int(buf)? {
                    0 => Flow::Serverbound,
                    1 => Flow::Clientbound,
                    other => return Err(format!("unknown packet flow {other}")),
                })
            } else {
                None
            };
            let optional = read_bool(buf)?;
            components.push(Component {
                id,
                version,
                flow,
                optional,
            });
        }
        let protocol = match ordinal {
            o if o == ChannelProtocol::Play.ordinal() => ChannelProtocol::Play,
            o if o == ChannelProtocol::Configuration.ordinal() => ChannelProtocol::Configuration,
            _ => continue,
        };
        out.push((protocol, components));
    }
    if buf.has_remaining() {
        return Err("trailing bytes".into());
    }
    Ok(out)
}

pub(super) fn read_var_int(buf: &mut &[u8]) -> Result<i32, String> {
    VarInt::decode(buf)
        .map(|v| v.0)
        .map_err(|err| format!("bad VarInt: {err}"))
}

pub(super) fn read_len(buf: &mut &[u8]) -> Result<usize, String> {
    let len = usize::try_from(read_var_int(buf)?).map_err(|_| "negative length".to_string())?;
    // Every entry takes at least one byte, so a longer list cannot be real.
    if len > buf.remaining() {
        return Err("length runs past the payload".into());
    }
    Ok(len)
}

pub(super) fn read_bool(buf: &mut &[u8]) -> Result<bool, String> {
    if !buf.has_remaining() {
        return Err("unexpected end of payload".into());
    }
    Ok(buf.get_u8() != 0)
}

pub(super) fn read_string(buf: &mut &[u8]) -> Result<String, String> {
    let len = read_len(buf)?;
    let text = std::str::from_utf8(&buf[..len]).map_err(|_| "string is not UTF-8".to_string())?;
    buf.advance(len);
    Ok(text.to_string())
}

#[cfg(test)]
mod tests {
    use super::*;

    fn c(id: &str, version: &str, flow: Option<Flow>, optional: bool) -> Component {
        Component {
            id: id.into(),
            version: version.into(),
            flow,
            optional,
        }
    }

    fn keys(result: Result<Vec<Component>, Vec<Failure>>) -> Vec<String> {
        result
            .expect_err("should fail")
            .into_iter()
            .map(|f| f.key)
            .collect()
    }

    #[test]
    fn matching_required_channels_agree_on_the_servers_version() {
        let agreed =
            negotiate(&[c("a:x", "1", None, false)], &[c("a:x", "1", None, false)]).unwrap();
        assert_eq!(agreed, vec![c("a:x", "1", None, false)]);
    }

    #[test]
    fn optional_channels_missing_on_the_other_side_are_dropped() {
        let agreed = negotiate(
            &[c("a:x", "1", None, false), c("s:opt", "1", None, true)],
            &[c("a:x", "1", None, false), c("c:opt", "1", None, true)],
        )
        .unwrap();
        assert_eq!(agreed, vec![c("a:x", "1", None, false)]);
    }

    #[test]
    fn a_required_client_channel_the_server_lacks_fails() {
        assert_eq!(
            keys(negotiate(&[], &[c("mod:x", "1", None, false)])),
            ["neoforge.network.negotiation.failure.missing.client.server"]
        );
    }

    #[test]
    fn a_required_server_channel_the_client_lacks_fails() {
        assert_eq!(
            keys(negotiate(&[c("mod:x", "1", None, false)], &[])),
            ["neoforge.network.negotiation.failure.missing.server.client"]
        );
    }

    #[test]
    fn versions_must_match() {
        let failure = negotiate(&[c("a:x", "2", None, false)], &[c("a:x", "1", None, false)])
            .expect_err("mismatch");
        assert_eq!(
            failure[0].key,
            "neoforge.network.negotiation.failure.version.mismatch"
        );
        assert_eq!(
            failure[0].args,
            ["1", "2"],
            "client version first, then the server's"
        );
    }

    #[test]
    fn a_server_flow_must_be_matched_by_the_client() {
        let server = [c("a:x", "1", Some(Flow::Clientbound), false)];
        assert_eq!(
            keys(negotiate(&server, &[c("a:x", "1", None, false)])),
            ["neoforge.network.negotiation.failure.flow.client.missing"]
        );
        assert_eq!(
            keys(negotiate(
                &server,
                &[c("a:x", "1", Some(Flow::Serverbound), false)]
            )),
            ["neoforge.network.negotiation.failure.flow.client.mismatch"]
        );
        assert!(negotiate(&server, &[c("a:x", "1", Some(Flow::Clientbound), false)]).is_ok());
    }

    #[test]
    fn a_server_without_a_flow_accepts_the_clients() {
        // NeoForge would fail this; see the module docs.
        assert!(
            negotiate(
                &[c("a:x", "1", None, false)],
                &[c("a:x", "1", Some(Flow::Serverbound), false)]
            )
            .is_ok()
        );
    }

    fn encode(phases: &[(i32, Vec<Component>)]) -> Vec<u8> {
        use pumpkin_protocol::ser::NetworkWriteExt;
        let mut buf = Vec::new();
        buf.write_var_int(&VarInt(phases.len() as i32)).unwrap();
        for (ordinal, components) in phases {
            buf.write_var_int(&VarInt(*ordinal)).unwrap();
            buf.write_var_int(&VarInt(components.len() as i32)).unwrap();
            for comp in components {
                buf.write_string(&comp.id).unwrap();
                buf.write_string(&comp.version).unwrap();
                buf.write_bool(comp.flow.is_some()).unwrap();
                if let Some(flow) = comp.flow {
                    buf.write_var_int(&VarInt(flow as i32)).unwrap();
                }
                buf.write_bool(comp.optional).unwrap();
            }
        }
        buf
    }

    #[test]
    fn parses_what_neoforge_encodes() {
        let play = vec![
            c("a:x", "1", Some(Flow::Clientbound), true),
            c("b:y", "", None, false),
        ];
        let config = vec![c(
            "neoforge:config_file",
            "1",
            Some(Flow::Clientbound),
            true,
        )];
        let parsed = parse_query(&encode(&[
            (1, play.clone()),
            (4, config.clone()),
            (0, vec![]),
        ]))
        .unwrap();
        assert_eq!(
            parsed,
            vec![
                (ChannelProtocol::Play, play),
                (ChannelProtocol::Configuration, config)
            ]
        );
    }

    #[test]
    fn rejects_lengths_that_run_past_the_payload() {
        let mut bytes = encode(&[(1, vec![c("a:x", "1", None, false)])]);
        bytes.truncate(bytes.len() - 2);
        assert!(parse_query(&bytes).is_err());
        assert!(parse_query(&[0x7f]).is_err(), "127 phases in one byte");
    }
}
