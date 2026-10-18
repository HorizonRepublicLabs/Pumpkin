use pumpkin_data::{Block, BlockStateId};
use pumpkin_macros::{Event, cancellable};
use pumpkin_util::math::position::BlockPos;
use std::sync::Arc;

use crate::entity::player::Player;

use super::BlockEvent;

/// An event that occurs when a block is broken.
///
/// This event contains information about the player breaking the block, the block itself,
/// the experience gained, and whether the block should drop items.
#[cancellable]
#[derive(Event, Clone)]
pub struct BlockBreakEvent {
    /// The player who is breaking the block, if applicable.
    pub player: Option<Arc<Player>>,

    /// The block that is being broken.
    pub block: &'static Block,

    /// The position of the block that is being broken.
    pub block_position: BlockPos,

    /// The state that was broken. Most drops depend on it: a grown crop gives more.
    pub state_id: BlockStateId,

    /// The amount of experience gained from breaking the block.
    pub exp: u32,

    /// A boolean indicating whether the block should drop items.
    pub drop: bool,

    /// Item ids and counts dropped in place of the loot table: a registered block's
    /// declared drops, which a handler may replace. Ignored when `drop` is false.
    pub drops: Option<Vec<(String, u8)>>,
}

impl BlockBreakEvent {
    /// Creates a new instance of `BlockBreakEvent`.
    ///
    /// # Arguments
    /// - `player`: An optional reference to the player breaking the block.
    /// - `block`: The block that is being broken.
    /// - `block_position`: The position of the block that is being broken.
    /// - `state_id`: The state that was broken.
    /// - `exp`: The amount of experience gained from breaking the block.
    /// - `drop`: A boolean indicating whether the block should drop items.
    ///
    /// # Returns
    /// A new instance of `BlockBreakEvent`.
    #[must_use]
    pub const fn new(
        player: Option<Arc<Player>>,
        block: &'static Block,
        block_position: BlockPos,
        state_id: BlockStateId,
        exp: u32,
        drop: bool,
    ) -> Self {
        Self {
            player,
            block,
            block_position,
            state_id,
            exp,
            drop,
            drops: None,
            cancelled: false,
        }
    }
}

impl BlockEvent for BlockBreakEvent {
    fn get_block(&self) -> &Block {
        self.block
    }
}
