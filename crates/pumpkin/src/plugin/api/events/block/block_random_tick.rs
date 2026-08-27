use std::sync::Arc;

use pumpkin_data::Block;
use pumpkin_data::BlockStateId;
use pumpkin_macros::{Event, cancellable};
use pumpkin_util::math::position::BlockPos;

use crate::world::World;

use super::BlockEvent;

/// An event that occurs when a block a plugin registered is chosen for a random tick.
///
/// Scope:
/// - Fired only for registered blocks. A generated one is ticked by the server's own code
///   for it, which a plugin does not replace.
/// - Fired at the same rate and under the same rules as any other random tick, so whether
///   the block is a candidate at all comes from the state it was copied from.
///
/// This is where a registered block acts on its own. Without it a crop could be planted
/// and broken but never grow, because nothing else ticks it.
#[cancellable]
#[derive(Event, Clone)]
pub struct BlockRandomTickEvent {
    /// The world the block is in.
    pub world: Arc<World>,

    /// The block being ticked.
    pub block: &'static Block,

    /// The state the block is in.
    pub state_id: BlockStateId,

    /// Where the block is.
    pub block_pos: BlockPos,
}

impl BlockRandomTickEvent {
    /// Creates a new `BlockRandomTickEvent`.
    #[must_use]
    pub const fn new(
        world: Arc<World>,
        block: &'static Block,
        state_id: BlockStateId,
        block_pos: BlockPos,
    ) -> Self {
        Self {
            world,
            block,
            state_id,
            block_pos,
            cancelled: false,
        }
    }
}

impl BlockEvent for BlockRandomTickEvent {
    fn get_block(&self) -> &Block {
        self.block
    }
}
