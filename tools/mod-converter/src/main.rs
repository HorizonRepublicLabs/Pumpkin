//! `pumpkin-mod-convert [--registries <registry-dump.json>] <server-dir> <mod.jar>...`
//!
//! Converts each jar and installs the result into a Pumpkin server directory:
//!
//! - `plugins/data/mod-content/<mod>.json`, the content the `mod-content` plugin registers;
//! - `<world>/datapacks/mod_<mod>/`, the jar's recipes, loot tables, tags and worldgen.
//!
//! The world folder is read from `default_level_name` in `pumpkin.toml`, else `world`.
//!
//! `--registries` takes the dump `tools/mod-dumper` writes after running the mods on
//! `NeoForge`: the exact blocks, items, states and channels they register. Without it the
//! converter guesses from the jars' data files, which cannot see ids built in code.

// A command-line tool: its output is the point.
#![allow(clippy::print_stdout, clippy::print_stderr)]
#![cfg_attr(test, allow(clippy::unwrap_used, clippy::expect_used, clippy::panic))]

mod channels;
mod classfile;
mod convert;

use std::{
    io::Read,
    path::{Path, PathBuf},
    process::ExitCode,
};

fn main() -> ExitCode {
    let mut args: Vec<String> = std::env::args().skip(1).collect();
    let dump = match args.iter().position(|a| a == "--registries") {
        Some(i) if i + 1 < args.len() => {
            let path = args.remove(i + 1);
            args.remove(i);
            match std::fs::read(&path)
                .map_err(|e| e.to_string())
                .and_then(|b| serde_json::from_slice(&b).map_err(|e| e.to_string()))
            {
                Ok(dump) => Some(dump),
                Err(err) => {
                    eprintln!("{path}: {err}");
                    return ExitCode::FAILURE;
                }
            }
        }
        _ => None,
    };
    let [server, jars @ ..] = args.as_slice() else {
        eprintln!(
            "usage: pumpkin-mod-convert [--registries <dump.json>] <server-dir> <mod.jar>..."
        );
        return ExitCode::FAILURE;
    };
    if jars.is_empty() {
        eprintln!(
            "usage: pumpkin-mod-convert [--registries <dump.json>] <server-dir> <mod.jar>..."
        );
        return ExitCode::FAILURE;
    }

    let server = Path::new(server);
    let mut failed = false;
    for jar in jars {
        match convert_one(server, Path::new(jar), dump.as_ref()) {
            Ok(summary) => println!("{summary}"),
            Err(err) => {
                eprintln!("{jar}: {err}");
                failed = true;
            }
        }
    }
    if failed {
        ExitCode::FAILURE
    } else {
        ExitCode::SUCCESS
    }
}

fn convert_one(
    server: &Path,
    jar_path: &Path,
    dump: Option<&serde_json::Value>,
) -> Result<String, String> {
    let jar = read_jar(jar_path)?;
    let c = convert::convert(&jar, dump)?;

    let content_dir = server.join("plugins/data/mod-content");
    std::fs::create_dir_all(&content_dir).map_err(|e| e.to_string())?;
    let manifest = serde_json::to_vec_pretty(&c.manifest).map_err(|e| e.to_string())?;
    std::fs::write(content_dir.join(format!("{}.json", c.mod_id)), manifest)
        .map_err(|e| e.to_string())?;

    // Replaced whole, so files a newer version of the mod dropped do not linger.
    // `mod_` marks the pack as mod content: the server reads tags and worldgen from those.
    let pack_dir = world_dir(server)
        .join("datapacks")
        .join(format!("mod_{}", c.mod_id));
    if pack_dir.exists() {
        std::fs::remove_dir_all(&pack_dir).map_err(|e| e.to_string())?;
    }
    for (path, bytes) in &c.datapack {
        let target = pack_dir.join(path);
        if let Some(parent) = target.parent() {
            std::fs::create_dir_all(parent).map_err(|e| e.to_string())?;
        }
        std::fs::write(&target, bytes).map_err(|e| e.to_string())?;
    }

    let configs = write_synced_configs(server, &c.mod_id, dump)?;

    let count = |key: &str| c.manifest[key].as_array().map_or(0, Vec::len);
    let mut summary = format!(
        "{}: {} block(s), {} item(s), {} datapack file(s)",
        c.mod_id,
        count("blocks"),
        count("items"),
        c.datapack.len()
    );
    if configs > 0 {
        use std::fmt::Write as _;
        let _ = write!(summary, "\n    {configs} synced config(s) written");
    }
    for line in &c.report {
        summary.push_str("\n    ");
        summary.push_str(line);
    }
    Ok(summary)
}

/// Writes the mod's synced configs, at the defaults the dump recorded, into
/// `<server>/synced-configs/`, which the server sends to every `NeoForge` client: a client
/// throws on reading a synced config that never arrived. A file already there is kept, as
/// it may have been edited. Returns how many were written.
fn write_synced_configs(
    server: &Path,
    mod_id: &str,
    dump: Option<&serde_json::Value>,
) -> Result<usize, String> {
    let Some(files) = dump.and_then(|d| d["synced_configs"][mod_id].as_object()) else {
        return Ok(0);
    };
    let mut written = 0;
    for (name, toml) in files {
        // The name is the client's file name, `Mekanism/gear.toml`; never let it leave the
        // folder.
        let relative = Path::new(name);
        if !relative
            .components()
            .all(|c| matches!(c, std::path::Component::Normal(_)))
        {
            return Err(format!("synced config {name} is not a relative path"));
        }
        let target = server.join("synced-configs").join(relative);
        if target.exists() {
            continue;
        }
        if let Some(parent) = target.parent() {
            std::fs::create_dir_all(parent).map_err(|e| e.to_string())?;
        }
        std::fs::write(&target, toml.as_str().unwrap_or_default()).map_err(|e| e.to_string())?;
        written += 1;
    }
    Ok(written)
}

fn read_jar(path: &Path) -> Result<convert::Jar, String> {
    let file = std::fs::File::open(path).map_err(|e| e.to_string())?;
    let mut archive = zip::ZipArchive::new(file).map_err(|e| e.to_string())?;
    let mut jar = convert::Jar::new();
    for i in 0..archive.len() {
        let mut entry = archive.by_index(i).map_err(|e| e.to_string())?;
        if entry.is_dir() {
            continue;
        }
        let mut bytes = Vec::new();
        entry.read_to_end(&mut bytes).map_err(|e| e.to_string())?;
        let name = entry.name().map_err(|e| e.to_string())?.into_owned();
        jar.insert(name, bytes);
    }
    Ok(jar)
}

/// The server's world folder: `default_level_name` from `pumpkin.toml`, else `world`.
fn world_dir(server: &Path) -> PathBuf {
    let name = std::fs::read_to_string(server.join("pumpkin.toml"))
        .ok()
        .and_then(|toml| {
            toml.lines().find_map(|line| {
                let value = line.trim().strip_prefix("default_level_name")?;
                Some(
                    value
                        .trim_start()
                        .trim_start_matches('=')
                        .trim()
                        .trim_matches('"')
                        .to_string(),
                )
            })
        })
        .unwrap_or_else(|| "world".to_string());
    server.join(name)
}
