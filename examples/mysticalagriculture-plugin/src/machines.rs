//! The machines' server half.
//!
//! The mod's Java jar draws the screens on the client; this opens them and will drive what
//! they show. Everything here is dictated by the mod's own code, because the client is the
//! specification and will not bend:
//!
//! - Screens are opened with `neoforge:advanced_open_screen`, not vanilla's open-screen
//!   packet, because every one of the mod's menu constructors reads a `BlockPos` out of the
//!   payload's extra data. Vanilla's packet has nowhere to put it.
//! - Slot order is fixed by the order each container adds its slots.
//! - The data values, their order, and the machines' constants come from the tile entities.
//!
//! All eight machines take the same payload, so one handler covers them; they differ only
//! in which menu they open and what the screen is called.

use pumpkin_plugin_api::{
    Server,
    events::{EventHandler, FromIntoEvent, InteractAction, player::PlayerInteractEvent},
};

/// The event payload, which the API exposes through the event type rather than by name.
type InteractData = <PlayerInteractEvent as FromIntoEvent>::Data;

/// A machine: the block you right-click, the menu it opens, and the screen's title.
///
/// The menu usually shares the block's name. The Seed Reprocessor does not, which is why
/// this is a table rather than a rule.
pub struct Machine {
    /// Block path, without the namespace.
    pub block: &'static str,
    /// Menu path, without the namespace.
    pub menu: &'static str,
    /// What the screen is called, from the mod's own language file.
    pub title: &'static str,
}

/// Every machine with a screen, from the mod's `ModMenuTypes` and its containers.
pub const MACHINES: [Machine; 8] = [
    Machine { block: "enchanter", menu: "enchanter", title: "Enchanter" },
    Machine { block: "furnace", menu: "furnace", title: "Furnace" },
    Machine { block: "harvester", menu: "harvester", title: "Harvester" },
    Machine { block: "ore_infuser", menu: "ore_infuser", title: "Ore Infuser" },
    Machine { block: "seed_reprocessor", menu: "reprocessor", title: "Seed Reprocessor" },
    Machine { block: "soul_extractor", menu: "soul_extractor", title: "Soul Extractor" },
    Machine { block: "soulium_spawner", menu: "soulium_spawner", title: "Soulium Spawner" },
    Machine { block: "tinkering_table", menu: "tinkering_table", title: "Tinkering Table" },
];

/// `NeoForge` opens modded screens over this channel; vanilla's packet cannot carry the
/// block position the mod's menu constructor reads.
pub const OPEN_SCREEN_CHANNEL: &str = "neoforge:advanced_open_screen";

/// The version `NeoForge` registers its own payloads under.
pub const NEOFORGE_CHANNEL_VERSION: &str = "1";

/// The Seed Reprocessor's slot indices, in the order `ReprocessorContainer` adds them.
pub mod slots {
    /// Machine upgrade.
    pub const UPGRADE: usize = 0;
    /// What gets reprocessed.
    pub const INPUT: usize = 1;
    /// Burned for power.
    pub const FUEL: usize = 2;
    /// What comes out.
    pub const OUTPUT: usize = 3;
    /// Where the player's own inventory starts.
    pub const PLAYER_INVENTORY: usize = 4;
    /// Machine slots plus 27 inventory slots plus 9 hotbar slots.
    pub const TOTAL: usize = 40;
}

/// The machine's constants, from `ReprocessorTileEntity`.
pub mod machine {
    /// Ticks of work one operation takes.
    pub const OPERATION_TIME: i32 = 200;
    /// Power drawn per tick while running.
    pub const FUEL_USAGE: i32 = 20;
    /// How much power the machine holds.
    pub const FUEL_CAPACITY: i32 = 80_000;
    /// A fuel item's burn time is multiplied by this to get power.
    pub const FUEL_TICK_MULTIPLIER: i32 = 20;
}

/// The six values the screen reads, in the order `ReprocessorTileEntity` syncs them.
#[derive(Clone, Copy, Default)]
pub struct MachineData {
    /// Power currently stored.
    pub energy: i32,
    /// Power the machine can hold.
    pub capacity: i32,
    /// Ticks of progress into the current operation.
    pub progress: i32,
    /// Ticks one operation takes.
    pub operation_time: i32,
    /// Power left in the fuel item being burned.
    pub fuel_left: i32,
    /// Power the fuel item was worth, for the flame's height.
    pub fuel_item_value: i32,
}

impl MachineData {
    /// A machine that is idle but powered up enough to look alive.
    #[must_use]
    pub fn idle() -> Self {
        Self {
            capacity: machine::FUEL_CAPACITY,
            operation_time: machine::OPERATION_TIME,
            ..Self::default()
        }
    }

    /// The values in the order the screen expects them.
    #[must_use]
    pub fn as_properties(&self) -> [i32; 6] {
        [
            self.energy,
            self.capacity,
            self.progress,
            self.operation_time,
            self.fuel_left,
            self.fuel_item_value,
        ]
    }
}

/// Opens the mod's screens for players standing at machines.
pub struct Machines {
    /// Block name to the menu id the server assigned and the screen's title. The ids come
    /// from registration and reach the client through the registry sync.
    opens: Vec<(String, u32, &'static str)>,
}

impl Machines {
    /// Builds the handler from menus that were registered.
    ///
    /// A machine whose menu is missing is skipped rather than opening the wrong screen.
    #[must_use]
    pub fn new(menu_ids: &[(&str, u32)]) -> Self {
        let opens = MACHINES
            .iter()
            .filter_map(|machine| {
                let id = menu_ids
                    .iter()
                    .find(|(name, _)| *name == machine.menu)
                    .map(|(_, id)| *id)?;
                Some((
                    format!("mysticalagriculture:{}", machine.block),
                    id,
                    machine.title,
                ))
            })
            .collect();

        Self { opens }
    }

    /// How many machines this will open.
    #[must_use]
    pub fn len(&self) -> usize {
        self.opens.len()
    }

    /// Whether no machine can be opened.
    #[must_use]
    pub fn is_empty(&self) -> bool {
        self.opens.is_empty()
    }
}

impl EventHandler<PlayerInteractEvent> for Machines {
    fn handle(&self, _server: Server, event: InteractData) -> InteractData {
        if event.action != InteractAction::RightClickBlock {
            return event;
        }

        let Some((_, menu_type_id, title)) =
            self.opens.iter().find(|(block, _, _)| *block == event.block)
        else {
            return event;
        };

        let Some(pos) = event.clicked_pos else {
            return event;
        };

        // Window ids only have to be distinct from the player's own inventory, which is 0.
        let window_id = 1;
        let payload = open_screen_payload(
            window_id,
            *menu_type_id,
            title,
            &packed_block_pos(pos.x, pos.y, pos.z),
        );

        // Only a Java client has the mod, so a Bedrock player simply gets nothing.
        if let Some(java) = event.player.as_java() {
            java.send_custom_payload(OPEN_SCREEN_CHANNEL, &payload);
            tracing::info!("opened {title} at {},{},{}", pos.x, pos.y, pos.z);
        }

        event
    }
}

/// Encodes `neoforge:advanced_open_screen`.
///
/// Window id, menu type id, the title, then whatever the menu's own constructor reads —
/// here the block position, which is how the screen knows which machine it is showing.
#[must_use]
pub fn open_screen_payload(
    window_id: i32,
    menu_type_id: u32,
    title: &str,
    extra: &[u8],
) -> Vec<u8> {
    let mut buf = Vec::new();
    write_var_int(window_id, &mut buf);
    write_var_int(menu_type_id as i32, &mut buf);
    write_component(title, &mut buf);
    write_var_int(extra.len() as i32, &mut buf);
    buf.extend_from_slice(extra);
    buf
}

/// Packs a block position the way `FriendlyByteBuf::writeBlockPos` does: one big-endian
/// long, 26 bits of x, 26 of z, 12 of y.
#[must_use]
pub fn packed_block_pos(x: i32, y: i32, z: i32) -> [u8; 8] {
    let packed = ((i64::from(x) & 0x3FF_FFFF) << 38)
        | ((i64::from(z) & 0x3FF_FFFF) << 12)
        | (i64::from(y) & 0xFFF);
    packed.to_be_bytes()
}

/// Writes a text component in the network's NBT form.
///
/// A bare string is a valid component, which saves building a compound for a plain title.
fn write_component(text: &str, buf: &mut Vec<u8>) {
    // TAG_String. Network NBT carries no root name.
    buf.push(0x08);
    let bytes = text.as_bytes();
    buf.extend_from_slice(&(bytes.len() as u16).to_be_bytes());
    buf.extend_from_slice(bytes);
}

fn write_var_int(mut value: i32, buf: &mut Vec<u8>) {
    loop {
        let mut byte = (value & 0x7F) as u8;
        value = ((value as u32) >> 7) as i32;
        if value != 0 {
            byte |= 0x80;
        }
        buf.push(byte);
        if value == 0 {
            return;
        }
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn block_positions_pack_the_way_the_client_unpacks_them() {
        // Round-trip the way FriendlyByteBuf does it.
        let unpack = |bytes: [u8; 8]| {
            let v = i64::from_be_bytes(bytes);
            let x = (v << 0) >> 38;
            let y = (v << 52) >> 52;
            let z = (v << 26) >> 38;
            (x as i32, y as i32, z as i32)
        };

        for pos in [(0, 0, 0), (100, 64, -250), (-1, 319, 1), (-30_000, -64, 30_000)] {
            assert_eq!(unpack(packed_block_pos(pos.0, pos.1, pos.2)), pos);
        }
    }

    #[test]
    fn the_payload_lays_out_the_fields_the_client_reads() {
        let payload = open_screen_payload(1, 25, "Hi", &[0xAB, 0xCD]);
        assert_eq!(
            payload,
            vec![
                1, // window id
                25, // menu type id
                0x08, 0x00, 0x02, b'H', b'i', // title, as a bare NBT string
                2, 0xAB, 0xCD, // extra data, length-prefixed
            ]
        );
    }

    #[test]
    fn every_machine_with_a_registered_menu_can_be_opened() {
        let ids: Vec<(&str, u32)> = MACHINES
            .iter()
            .enumerate()
            .map(|(index, machine)| (machine.menu, index as u32))
            .collect();

        let machines = Machines::new(&ids);
        assert_eq!(machines.len(), MACHINES.len());
    }

    #[test]
    fn a_machine_whose_menu_is_missing_is_skipped_rather_than_opening_the_wrong_screen() {
        let machines = Machines::new(&[("reprocessor", 29)]);
        assert_eq!(machines.len(), 1);
    }

    #[test]
    fn the_reprocessors_menu_is_not_named_after_its_block() {
        let reprocessor = MACHINES
            .iter()
            .find(|machine| machine.block == "seed_reprocessor")
            .expect("the reprocessor is in the table");
        assert_eq!(reprocessor.menu, "reprocessor");
    }

    #[test]
    fn machine_data_is_ordered_the_way_the_screen_reads_it() {
        let data = MachineData {
            energy: 1,
            capacity: 2,
            progress: 3,
            operation_time: 4,
            fuel_left: 5,
            fuel_item_value: 6,
        };
        assert_eq!(data.as_properties(), [1, 2, 3, 4, 5, 6]);
    }

    #[test]
    fn an_idle_machine_reports_a_full_size_but_no_power() {
        let idle = MachineData::idle();
        assert_eq!(idle.energy, 0);
        assert_eq!(idle.capacity, machine::FUEL_CAPACITY);
        assert_eq!(idle.operation_time, machine::OPERATION_TIME);
    }
}
