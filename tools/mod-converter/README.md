# pumpkin-mod-convert

Converts NeoForge mod jars into content Pumpkin can serve: the blocks, items, recipes,
loot tables, tags and worldgen a mod ships as data. The mod's **code is not converted**:
every block becomes a copy of a vanilla block that should act the same (an ore mines like
an ore, a slab places like a slab, a crop has its ages). Machines, GUIs, energy and custom
recipe types do nothing.

## Use

```sh
# once: the plugin that registers converted content
cargo build -p mod-content-plugin --target wasm32-wasip2 --release
cp target/wasm32-wasip2/release/mod_content_plugin.wasm <server>/plugins/

# per mod
cargo run -p pumpkin-mod-converter -- <server> Cucumber.jar MysticalAgriculture.jar
```

(With a shared cargo `target-dir`, the `.wasm` is under that directory instead.)

For each jar this writes:

| Path | What |
|---|---|
| `plugins/data/mod-content/<mod>.json` | Blocks, items and channels the `mod-content` plugin registers at load |
| `<world>/datapacks/mod_<mod>/` | The jar's `data/` tree: recipes, loot tables, tags, worldgen |

Re-running replaces both. Restart the server afterwards: registration only happens at load.

The plugin needs the `fs.read.data` permission to read its folder. Players need the mod
installed client-side, to have its models and textures.

## What converts, and how

| Mod content | Becomes |
|---|---|
| Block (`assets/<ns>/blockstates/*.json`) | Copy of a vanilla template, chosen from its name and mining tags: `*_ore` → `iron_ore`, `*_stairs` → `stone_stairs`, `*crop*` → `wheat`, axe-mineable → `oak_planks`, … |
| Block states | Properties read from the blockstate file, ordered as Minecraft orders them. Shaped blocks (slabs, stairs, walls, doors, …) use the vanilla shape's states instead, because their files leave out properties such as `waterlogged`. |
| Drops | The block's loot table, reduced to the plain drop and its count. Silk touch and fortune are dropped; a drop gated on a single property (a grown crop) keeps its gate. |
| `needs_*_tool` tags | `requires_tool` |
| Item (`assets/<ns>/items/*.json`) | Copy of `stone` (block items) or a vanilla item by name: `*_sword` → `iron_sword`, `*_seeds` → `wheat_seeds`, otherwise `iron_ingot` |
| Vanilla-typed recipes | Kept |
| Mod variants of crafting (`*:shaped_*`, `*:shapeless_*`) | Rewritten to vanilla crafting; the mod-side extra is lost |
| Mod-typed recipes (machines) | Left out, and counted in the report |
| Tags, loot tables, worldgen | Kept. Only `minecraft:ore`-shaped mod features generate. |

The converter prints one line per guess it could not verify. The main one is an enum
property whose value order comes from the blockstate file. If that order doesn't match the
mod's code, the client draws the wrong state.

## Network channels

A NeoForge client refuses a server that doesn't offer every payload its mods registered as
required. Those are defined in code, so the converter reads them from the jar's bytecode:

- **Ids:** every `CustomPacketPayload.Type` built from an identifier. That covers ids made with
  `Identifier.fromNamespaceAndPath`, `parse`, or a one-argument helper such as
  `Mekanism.rl("…")`.
- **Phase:** configuration if the type is passed to a `configuration*` registration call,
  otherwise play.
- **Version:** the constant passed to `RegisterPayloadHandlersEvent.registrar(…)`. If that's
  computed in code, the mod's own version is used instead, and the report says so.

Checked against the jars' own registration code: MysticalAgriculture 4 of 4, Mekanism 57
of 57 (one of them configuration), MekanismGenerators 2.

The server offers these channels as optional. A client carrying the mod gets them, a client
without it isn't refused because of them, and a type found by mistake can't refuse anyone.
The manifest's `channels` list can still be edited by hand:

```json
"channels": [{ "id": "mekanism:batch_security", "phase": "play", "version": "10.8.0", "serverbound": false }]
```

Mod payloads are agreed but not handled: what a mod sends over them is ignored.
