// Synthetic NeoForge client: handshake, login, brand, then report the configuration-phase
// custom payloads the server sends. Verifies the modded negotiation without a real client.
use std::io::{Read, Write};
use std::net::TcpStream;

fn varint(mut v: i32, out: &mut Vec<u8>) {
    loop {
        let mut b = (v & 0x7F) as u8;
        v = ((v as u32) >> 7) as i32;
        if v != 0 { b |= 0x80; }
        out.push(b);
        if v == 0 { break; }
    }
}
fn string(s: &str, out: &mut Vec<u8>) { varint(s.len() as i32, out); out.extend_from_slice(s.as_bytes()); }

fn send(sock: &mut TcpStream, id: i32, body: &[u8]) -> std::io::Result<()> {
    let mut inner = Vec::new();
    varint(id, &mut inner);
    inner.extend_from_slice(body);
    let mut framed = Vec::new();
    varint(inner.len() as i32, &mut framed);
    framed.extend_from_slice(&inner);
    sock.write_all(&framed)
}

fn read_varint(sock: &mut TcpStream) -> std::io::Result<i32> {
    let (mut v, mut shift) = (0i32, 0u32);
    loop {
        let mut b = [0u8; 1];
        sock.read_exact(&mut b)?;
        v |= ((b[0] & 0x7F) as i32) << shift;
        if b[0] & 0x80 == 0 { return Ok(v); }
        shift += 7;
    }
}

fn read_packet(sock: &mut TcpStream) -> std::io::Result<(i32, Vec<u8>)> {
    let len = read_varint(sock)? as usize;
    let mut buf = vec![0u8; len];
    sock.read_exact(&mut buf)?;
    let mut cur = 0usize;
    let (mut id, mut shift) = (0i32, 0u32);
    loop {
        let b = buf[cur]; cur += 1;
        id |= ((b & 0x7F) as i32) << shift;
        if b & 0x80 == 0 { break; }
        shift += 7;
    }
    Ok((id, buf[cur..].to_vec()))
}

fn read_varint_buf(buf: &[u8], cur: &mut usize) -> i32 {
    let (mut v, mut shift) = (0i32, 0u32);
    loop {
        let b = buf[*cur]; *cur += 1;
        v |= ((b & 0x7F) as i32) << shift;
        if b & 0x80 == 0 { return v; }
        shift += 7;
    }
}

fn read_string(buf: &[u8], cur: &mut usize) -> String {
    let (mut len, mut shift) = (0usize, 0u32);
    loop {
        let b = buf[*cur]; *cur += 1;
        len |= ((b & 0x7F) as usize) << shift;
        if b & 0x80 == 0 { break; }
        shift += 7;
    }
    let s = String::from_utf8_lossy(&buf[*cur..*cur + len]).to_string();
    *cur += len;
    s
}

fn main() -> std::io::Result<()> {
    let args: Vec<String> = std::env::args().skip(1).collect();
    let skip_known_packs = args.iter().any(|a| a == "--no-known-packs");
    let addr = args
        .iter()
        .find(|a| !a.starts_with("--"))
        .cloned()
        .unwrap_or_else(|| "127.0.0.1:25566".into());
    let mut sock = TcpStream::connect(&addr)?;

    // Handshake: protocol 776 (26.2), next state 2 = login.
    let mut b = Vec::new();
    varint(776, &mut b);
    string("127.0.0.1", &mut b);
    b.extend_from_slice(&25566u16.to_be_bytes());
    varint(2, &mut b);
    send(&mut sock, 0, &b)?;

    // Login start.
    let mut b = Vec::new();
    string("ProbeClient", &mut b);
    b.extend_from_slice(&[0u8; 16]);
    send(&mut sock, 0, &b)?;

    loop {
        let (id, body) = read_packet(&mut sock)?;
        match id {
            0 => { let mut c = 0; println!("DISCONNECTED: {}", read_string(&body, &mut c)); return Ok(()); }
            1 => { println!("server wants encryption; run the probe against an offline-mode server"); return Ok(()); }
            2 => { println!("login finished"); break; }
            3 => { println!("server enabled compression; disable it for the probe"); return Ok(()); }
            other => println!("login: unexpected packet {other}"),
        }
    }

    send(&mut sock, 3, &[])?; // login acknowledged

    // Brand, which is what marks us as a NeoForge client.
    let mut b = Vec::new();
    string("minecraft:brand", &mut b);
    string("neoforge", &mut b);
    send(&mut sock, 2, &b)?;

    // Client information, which a real client sends early in configuration.
    let mut b = Vec::new();
    string("en_us", &mut b);
    b.push(8);          // view distance
    varint(0, &mut b);  // chat mode
    b.push(1);          // chat colours
    b.push(0x7F);       // skin parts
    varint(1, &mut b);  // main hand
    b.push(0);          // text filtering
    b.push(1);          // server listing
    varint(0, &mut b);  // particle status
    send(&mut sock, 0, &b)?;

    sock.set_read_timeout(Some(std::time::Duration::from_secs(8)))?;
    let mut seen = Vec::new();
    let enter_play = args.iter().any(|a| a == "--play");
    while let Ok((id, body)) = read_packet(&mut sock) {
        match id {
            1 => {
                let mut c = 0;
                let channel = read_string(&body, &mut c);
                println!("payload {channel} ({} bytes)", body.len() - c);
                if channel == "neoforge:network" {
                    let phases = read_varint_buf(&body, &mut c);
                    for _ in 0..phases {
                        let protocol = read_varint_buf(&body, &mut c);
                        let n = read_varint_buf(&body, &mut c);
                        println!("   protocol {protocol}: {n} channel(s)");
                        for _ in 0..n {
                            let key = read_string(&body, &mut c);
                            let _id = read_string(&body, &mut c);
                            let version = read_string(&body, &mut c);
                            println!("      {key} @ {version}");
                        }
                    }
                }
                if channel == "neoforge:frozen_registry" {
                    let registry = read_string(&body, &mut c);
                    let n = read_varint_buf(&body, &mut c);
                    let mut modded = 0;
                    for _ in 0..n {
                        let _id = read_varint_buf(&body, &mut c);
                        if read_string(&body, &mut c).starts_with("mysticalagriculture:") { modded += 1; }
                    }
                    println!("   {registry}: {n} entries, {modded} from the mod");
                }
                seen.push(channel);
            }
            2 => {
                let mut c = 0;
                println!("DISCONNECTED in config: {}", read_string(&body, &mut c));
                break;
            }
            3 => {
                println!("finish configuration");
                if enter_play {
                    // Ack it and stay in play for a while, so entity spawns land here.
                    send(&mut sock, 3, &[])?;
                    println!("--- entering play ---");
                    sock.set_read_timeout(Some(std::time::Duration::from_secs(20)))?;
                    let mut spawns = 0;
                    while let Ok((pid, pbody)) = read_packet(&mut sock) {
                        if pid == 1 {
                            spawns += 1;
                            let hex: String =
                                pbody.iter().map(|b| format!("{b:02x}")).collect();
                            println!("ADD_ENTITY body_len={} hex={hex}", pbody.len());
                            if spawns >= 5 { break; }
                        }
                    }
                    println!("--- play done, {spawns} add_entity seen ---");
                }
                break;
            }
            14 => {
                // Known packs. A real modded client decides whether the server is modded
                // before answering this, so `--no-known-packs` reproduces that: the modded
                // declaration has to arrive without it.
                if skip_known_packs {
                    println!("known packs -> ignoring");
                } else {
                    println!("known packs -> replying");
                    let mut b = Vec::new();
                    varint(0, &mut b);
                    send(&mut sock, 7, &b)?;
                }
            }
            other => println!("(packet {other}, {} bytes)", body.len()),
        }
    }
    println!("--- {} custom payload(s): {:?}", seen.len(), seen);
    Ok(())
}
