use pumpkin_data::{BlockId, BlockStateId};
use pumpkin_util::random::{RandomGenerator, RandomImpl};

pub struct RandomBlockMatchRuleTest {
    pub block: BlockId,
    pub probability: f32,
}

impl RandomBlockMatchRuleTest {
    pub fn test(&self, state: BlockStateId, random: &mut RandomGenerator) -> bool {
        state.to_block_id() == self.block && random.next_f32() < self.probability
    }
}
