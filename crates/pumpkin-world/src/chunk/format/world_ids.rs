//! Keeps the block ids a Pump world saved meaning the same blocks across restarts.
//!
//! Pump stores raw state and block ids. Generated ids only move on a vanilla bump, but a
//! registered block's id is its position in plugin registration order, so adding, removing
//! or reordering a mod would silently turn saved blocks into other blocks.
//!
//! Each world therefore owns an id space of its own, listed in `pumpkin_block_ids.txt` as
//! one `name state_count` line per block, in id order. The list only ever grows: a block
//! seen for the first time is appended, and a block that is gone keeps its line so the
//! ids after it never move. Chunks are written in world ids and read back into whatever
//! ids this run's registry handed out. When the two agree, which is every run in which
//! the block set did not change, nothing is translated.

use std::{collections::HashMap, io, path::Path, sync::OnceLock};

use pumpkin_data::{Block, BlockId};
use tracing::{info, warn};

const FILE_NAME: &str = "pumpkin_block_ids.txt";
const HEADER: &str = "# pumpkin block ids v1";

/// One block as a world or a registry sees it.
#[derive(Clone, Debug, PartialEq, Eq, Hash)]
struct Entry {
    name: String,
    state_count: u32,
}

#[derive(Debug)]
struct Translation {
    state_to_world: Box<[u32]>,
    state_to_runtime: Box<[u32]>,
    block_to_world: Box<[u16]>,
    block_to_runtime: Box<[u16]>,
}

/// `None` inside means the world and the registry agree.
static TRANSLATION: OnceLock<Option<Translation>> = OnceLock::new();

/// Loads, extends and saves the world's id list. Call once, after the block registry froze
/// and before any Pump chunk is read or written.
// ponytail: chunk IO that happens before this runs uses raw ids, as before. That is only
// wrong if the block set changed and something loads chunks while plugins are loading.
pub fn init(world_root: &Path) -> io::Result<()> {
    let runtime = runtime_entries();
    let path = world_root.join(FILE_NAME);
    let saved = match std::fs::read_to_string(&path) {
        Ok(text) => Some(parse(&text)?),
        Err(err) if err.kind() == io::ErrorKind::NotFound => None,
        Err(err) => return Err(err),
    };

    // A world from before this file existed was written in this run's ids, which is the
    // best guess there is, and exactly what reading it raw did.
    let mut world = saved.clone().unwrap_or_else(|| runtime.clone());
    for entry in &runtime {
        if !world.contains(entry) {
            world.push(entry.clone());
        }
    }

    if saved.as_ref() != Some(&world) {
        write(&path, &world)?;
    }

    let translation = build(&world, &runtime)?;
    if translation.is_some() {
        info!("Block registry differs from the one this world was saved with; translating ids");
    }
    let _ = TRANSLATION.set(translation);
    Ok(())
}

fn active() -> Option<&'static Translation> {
    TRANSLATION.get().and_then(Option::as_ref)
}

/// True when saved ids need translating, so a reader can skip the work otherwise.
pub(crate) fn is_translating() -> bool {
    active().is_some()
}

pub(crate) fn state_to_world(id: u32) -> u32 {
    active().map_or(id, |t| lookup(&t.state_to_world, id as usize))
}

pub(crate) fn state_to_runtime(id: u32) -> u32 {
    active().map_or(id, |t| lookup(&t.state_to_runtime, id as usize))
}

pub(crate) fn block_to_world(id: u16) -> u16 {
    active().map_or(id, |t| lookup(&t.block_to_world, id as usize))
}

pub(crate) fn block_to_runtime(id: u16) -> u16 {
    active().map_or(id, |t| lookup(&t.block_to_runtime, id as usize))
}

/// Out-of-range ids become air, as `new_or_air` already treats them.
fn lookup<T: Copy + Default>(table: &[T], id: usize) -> T {
    table.get(id).copied().unwrap_or_default()
}

fn runtime_entries() -> Vec<Entry> {
    (0..BlockId::count())
        .filter_map(BlockId::new)
        .map(|id| {
            let block = Block::from_id(id);
            Entry {
                name: block.name.to_string(),
                state_count: block.states.len() as u32,
            }
        })
        .collect()
}

/// Returns `None` when `world` and `runtime` are the same list.
fn build(world: &[Entry], runtime: &[Entry]) -> io::Result<Option<Translation>> {
    if world == runtime {
        return Ok(None);
    }

    let firsts = |entries: &[Entry]| -> io::Result<Vec<u32>> {
        let mut out = Vec::with_capacity(entries.len());
        let mut next = 0u32;
        for e in entries {
            out.push(next);
            next = next.checked_add(e.state_count).ok_or_else(too_many)?;
        }
        Ok(out)
    };
    let world_first = firsts(world)?;
    let runtime_first = firsts(runtime)?;

    // Names are unique within a registry; a world may hold one name twice if the block's
    // state count changed, and the latest line is the one this registry matches.
    let runtime_by_name: HashMap<&str, usize> = runtime
        .iter()
        .enumerate()
        .map(|(i, e)| (e.name.as_str(), i))
        .collect();
    let world_by_entry: HashMap<&Entry, usize> =
        world.iter().enumerate().map(|(i, e)| (e, i)).collect();

    let state_total = |entries: &[Entry]| entries.iter().map(|e| e.state_count as usize).sum();
    let mut state_to_runtime = vec![0u32; state_total(world)];
    let mut block_to_runtime = vec![0u16; world.len()];
    for (wi, entry) in world.iter().enumerate() {
        let Some(&ri) = runtime_by_name.get(entry.name.as_str()) else {
            warn!(
                "Block {} is no longer registered; its saved blocks load as air",
                entry.name
            );
            continue;
        };
        let current = &runtime[ri];
        if current.state_count != entry.state_count {
            warn!(
                "Block {} now has {} states, not the {} it was saved with; states past the \
                 end load as its default state",
                entry.name, current.state_count, entry.state_count
            );
        }
        let default = Block::from_id(BlockId::new(ri as u16).unwrap_or(BlockId::AIR))
            .default_state
            .id
            .as_u32();
        block_to_runtime[wi] = ri as u16;
        for offset in 0..entry.state_count {
            state_to_runtime[(world_first[wi] + offset) as usize] = if offset < current.state_count
            {
                runtime_first[ri] + offset
            } else {
                default
            };
        }
    }

    let mut state_to_world = vec![0u32; state_total(runtime)];
    let mut block_to_world = vec![0u16; runtime.len()];
    for (ri, entry) in runtime.iter().enumerate() {
        // `init` appended every runtime entry, so the lookup cannot miss.
        let wi = world_by_entry[entry];
        block_to_world[ri] = wi as u16;
        for offset in 0..entry.state_count {
            state_to_world[(runtime_first[ri] + offset) as usize] = world_first[wi] + offset;
        }
    }

    Ok(Some(Translation {
        state_to_world: state_to_world.into(),
        state_to_runtime: state_to_runtime.into(),
        block_to_world: block_to_world.into(),
        block_to_runtime: block_to_runtime.into(),
    }))
}

fn too_many() -> io::Error {
    io::Error::other("world block id list no longer fits in 32-bit state ids")
}

fn parse(text: &str) -> io::Result<Vec<Entry>> {
    text.lines()
        .filter(|line| !line.is_empty() && !line.starts_with('#'))
        .map(|line| {
            let (name, count) = line
                .rsplit_once(' ')
                .ok_or_else(|| io::Error::other(format!("bad {FILE_NAME} line: {line}")))?;
            let state_count = count
                .parse()
                .map_err(|_| io::Error::other(format!("bad {FILE_NAME} line: {line}")))?;
            Ok(Entry {
                name: name.to_string(),
                state_count,
            })
        })
        .collect()
}

fn write(path: &Path, entries: &[Entry]) -> io::Result<()> {
    use std::fmt::Write as _;
    let mut text = format!("{HEADER}\n");
    for e in entries {
        let _ = writeln!(text, "{} {}", e.name, e.state_count);
    }
    // Written beside and renamed over, so a crash leaves the old list or the new one.
    let tmp = path.with_extension("txt.tmp");
    std::fs::write(&tmp, text)?;
    std::fs::rename(tmp, path)
}

#[cfg(test)]
mod tests {
    use super::*;

    fn e(name: &str, state_count: u32) -> Entry {
        Entry {
            name: name.to_string(),
            state_count,
        }
    }

    #[test]
    fn same_list_translates_nothing() {
        let list = vec![e("air", 1), e("a:x", 2)];
        assert!(build(&list, &list).unwrap().is_none());
    }

    #[test]
    fn reordered_registry_keeps_saved_blocks() {
        // Saved as air, a:x (2 states), b:y (3 states); this run registered b:y first.
        let world = vec![e("air", 1), e("a:x", 2), e("b:y", 3)];
        let runtime = vec![e("air", 1), e("b:y", 3), e("a:x", 2)];
        let t = build(&world, &runtime).unwrap().unwrap();

        // world states: air 0 | a:x 1..=2 | b:y 3..=5
        // runtime:      air 0 | b:y 1..=3 | a:x 4..=5
        assert_eq!(&*t.state_to_runtime, &[0, 4, 5, 1, 2, 3]);
        assert_eq!(&*t.block_to_runtime, &[0, 2, 1]);
        for runtime_state in 0..6u32 {
            let w = t.state_to_world[runtime_state as usize];
            assert_eq!(t.state_to_runtime[w as usize], runtime_state);
        }
    }

    #[test]
    fn ids_past_sixteen_bits_translate() {
        let world = vec![e("air", 1), e("a:x", 70_000), e("b:y", 2)];
        let runtime = vec![e("air", 1), e("b:y", 2), e("a:x", 70_000)];
        let t = build(&world, &runtime).unwrap().unwrap();
        // b:y was saved after a:x's 70,000 states and now sits right after air.
        assert_eq!(t.state_to_runtime[70_001], 1);
        assert_eq!(t.state_to_world[1], 70_001);
        assert_eq!(t.state_to_runtime[70_000], 70_002);
    }

    #[test]
    fn removed_block_loads_as_air_and_keeps_its_ids() {
        let world = vec![e("air", 1), e("gone:z", 2), e("a:x", 1)];
        let runtime = vec![e("air", 1), e("a:x", 1)];
        let t = build(&world, &runtime).unwrap().unwrap();
        assert_eq!(&*t.state_to_runtime, &[0, 0, 0, 1]);
        // a:x is still saved at world state 3, after the removed block's two states.
        assert_eq!(&*t.state_to_world, &[0, 3]);
    }

    #[test]
    fn list_round_trips_through_text() {
        let dir = std::env::temp_dir().join(format!("pumpkin-world-ids-{}", std::process::id()));
        std::fs::create_dir_all(&dir).unwrap();
        let path = dir.join(FILE_NAME);
        let list = vec![e("air", 1), e("mekanism:basic_mechanical_pipe", 7)];
        write(&path, &list).unwrap();
        assert_eq!(
            parse(&std::fs::read_to_string(&path).unwrap()).unwrap(),
            list
        );
        std::fs::remove_dir_all(dir).unwrap();
    }
}
