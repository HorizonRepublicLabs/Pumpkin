//! Behaviour for blocks a plugin registered.
//!
//! Every hook the server has for a block asks the block registry for behaviour by block
//! id, and a registered block had none: the lookup was a map of generated blocks only, so
//! all twenty-six hooks fell through to nothing. A registered block could not be told what
//! to do when broken, used, or placed against, and the silence looked like the server
//! ignoring it rather than there being nowhere to say it.
//!
//! This is what the registry answers with instead. It carries what the registration
//! declared and acts on it; anything a plugin has not declared keeps the trait's default,
//! which is what a plain block does.

use std::sync::Arc;

use pumpkin_data::BlockStateId;
use pumpkin_data::item::Item;
use pumpkin_data::item_stack::ItemStack;
use pumpkin_util::math::position::BlockPos;

use crate::block::{BlockBehaviour, BrokenArgs};
use crate::world::World;

/// One thing a block yields when broken, over the states it applies to.
#[derive(Clone, Debug)]
pub struct BlockDrop {
    /// The item's numeric id, resolved when the block was registered.
    pub item_id: u16,
    /// Fewest dropped.
    pub min: u8,
    /// Most dropped, inclusive.
    pub max: u8,
    /// The first state this applies to, as an offset into the block's own states.
    pub from_state: u32,
    /// The last state this applies to, inclusive.
    pub to_state: u32,
}

impl BlockDrop {
    /// Whether this drop applies to a state, given where that state sits in the block's
    /// own list.
    #[must_use]
    pub const fn covers(&self, offset: u32) -> bool {
        offset >= self.from_state && offset <= self.to_state
    }

    /// How many to drop.
    ///
    /// A fixed count when the bounds agree, and otherwise anything between them. The
    /// caller supplies the roll so this stays testable.
    #[must_use]
    pub const fn count(&self, roll: u8) -> u8 {
        if self.max <= self.min {
            return self.min;
        }
        let span = self.max - self.min + 1;
        self.min + roll % span
    }
}

/// What a registered block does, as far as the server is concerned.
pub struct PluginBlockBehaviour {
    /// The state id the block's own states start at, so a state can be turned back into an
    /// offset into what the registration declared.
    first_state: u16,
    drops: Vec<BlockDrop>,
}

impl PluginBlockBehaviour {
    #[must_use]
    pub const fn new(first_state: u16, drops: Vec<BlockDrop>) -> Self {
        Self { first_state, drops }
    }

    /// The drops that apply to a state.
    fn drops_for(&self, state_id: BlockStateId) -> impl Iterator<Item = &BlockDrop> {
        let offset = state_id.as_u16().saturating_sub(self.first_state);
        self.drops
            .iter()
            .filter(move |drop| drop.covers(u32::from(offset)))
    }

    /// Drops what the state yields at the position it was broken.
    fn drop_all(&self, world: &Arc<World>, position: &BlockPos, state_id: BlockStateId) {
        for drop in self.drops_for(state_id) {
            // Spread over the drop's range without pulling in a generator: the position and
            // the state are what vary between one break and the next.
            let roll = position
                .0
                .x
                .wrapping_mul(31)
                .wrapping_add(position.0.z.wrapping_mul(7))
                .wrapping_add(i32::from(state_id.as_u16()))
                .unsigned_abs() as u8;

            let count = drop.count(roll);
            if count == 0 {
                continue;
            }

            let Some(item) = Item::from_id(drop.item_id) else {
                continue;
            };
            world.drop_stack(position, ItemStack::new(count, item));
        }
    }
}

impl BlockBehaviour for PluginBlockBehaviour {
    fn broken(&self, args: BrokenArgs<'_>) {
        self.drop_all(args.world, args.position, args.state.id);
    }
}

#[cfg(test)]
mod tests {
    use super::BlockDrop;

    fn drop_between(min: u8, max: u8) -> BlockDrop {
        BlockDrop {
            item_id: 1,
            min,
            max,
            from_state: 0,
            to_state: 0,
        }
    }

    #[test]
    fn a_fixed_drop_ignores_the_roll() {
        let drop = drop_between(2, 2);
        assert_eq!(drop.count(0), 2);
        assert_eq!(drop.count(200), 2);
    }

    #[test]
    fn a_ranged_drop_stays_within_its_bounds() {
        let drop = drop_between(1, 3);
        for roll in 0..=255u8 {
            let count = drop.count(roll);
            assert!((1..=3).contains(&count), "{roll} gave {count}");
        }
    }

    #[test]
    fn a_drop_applies_only_to_the_states_it_names() {
        // A crop's essence comes at the last age and nowhere else.
        let essence = BlockDrop {
            item_id: 1,
            min: 1,
            max: 1,
            from_state: 7,
            to_state: 7,
        };
        assert!(essence.covers(7));
        assert!(!essence.covers(6));
        assert!(!essence.covers(0));
    }
}
