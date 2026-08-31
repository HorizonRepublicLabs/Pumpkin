use pumpkin_util::text::TextComponent;
use pumpkin_world::world::BlockFlags;

use crate::command::args::ConsumedArgs;
use crate::command::args::block::BlockArgumentConsumer;
use crate::command::args::position_block::BlockPosArgumentConsumer;
use crate::command::tree::CommandTree;
use crate::command::tree::builder::{argument, literal};
use crate::command::{CommandError, CommandExecutor, CommandResult, CommandSender};

const NAMES: [&str; 1] = ["setblock"];

const DESCRIPTION: &str = "Place a block.";

const ARG_BLOCK: &str = "block";
const ARG_BLOCK_POS: &str = "pos";

#[derive(Clone, Copy)]
enum Mode {
    /// with particles + item drops
    Destroy,

    /// only replaces air
    Keep,

    /// default; without particles
    Replace,

    /// places block without triggering updates around it
    Strict,
}

struct Executor(Mode);

impl CommandExecutor for Executor {
    fn execute(
        &self,
        sender: &CommandSender,
        server: &crate::server::Server,
        args: &ConsumedArgs,
    ) -> CommandResult {
        let (_block, block_state_id) = BlockArgumentConsumer::find_state_arg(args, ARG_BLOCK)?;
        let mode = self.0;
        let world = sender
            .world_or_first(server)
            .ok_or(CommandError::InvalidRequirement)?;
        let pos = BlockPosArgumentConsumer::find_loaded_arg(args, ARG_BLOCK_POS, &world)?;

        let success = match mode {
            Mode::Destroy => {
                world.break_block(&pos, None, BlockFlags::SKIP_DROPS | BlockFlags::FORCE_STATE);
                world.set_block_state(
                    &pos,
                    block_state_id,
                    BlockFlags::FORCE_STATE | BlockFlags::NOTIFY_NEIGHBORS,
                );
                true
            }
            Mode::Replace => {
                world.set_block_state(
                    &pos,
                    block_state_id,
                    BlockFlags::FORCE_STATE | BlockFlags::NOTIFY_NEIGHBORS,
                );
                true
            }
            Mode::Keep => {
                let old_state = world.get_block_state(&pos);
                if old_state.is_air() {
                    world.set_block_state(
                        &pos,
                        block_state_id,
                        BlockFlags::FORCE_STATE | BlockFlags::NOTIFY_NEIGHBORS,
                    );
                    true
                } else {
                    false
                }
            }
            Mode::Strict => {
                world.set_block_state(&pos, block_state_id, BlockFlags::SKIP_BLOCK_ADDED_CALLBACK);
                true
            }
        };

        if success {
            // Vanilla creates the block's entity on any placement; player placement
            // routes through PluginBlockBehaviour::player_placed, but a command has no
            // player, so the same dynamic link answers here. Without it a machine set
            // by command has nowhere to keep its contents and never ticks.
            let state = world.get_block_state(&pos);
            let block = world.get_block(&pos);
            let block_entity_type = if state.block_entity_type == u16::MAX {
                pumpkin_data::dynamic::block_entity_type_for_block(block.id.as_u16())
                    .unwrap_or(u16::MAX)
            } else {
                state.block_entity_type
            };
            if block_entity_type != u16::MAX
                && world.get_block_entity(&pos).is_none()
                && let Some(entity) =
                    crate::block::entities::create_block_entity(block_entity_type, pos)
            {
                world.add_block_entity(entity);
            }
            sender.send_message(TextComponent::translate_cross(
                pumpkin_data::translation::java::COMMANDS_SETBLOCK_SUCCESS,
                pumpkin_data::translation::bedrock::COMMANDS_SETBLOCK_SUCCESS,
                [
                    TextComponent::text(pos.0.x.to_string()),
                    TextComponent::text(pos.0.y.to_string()),
                    TextComponent::text(pos.0.z.to_string()),
                ],
            ));
            Ok(1)
        } else {
            Err(CommandError::CommandFailed(TextComponent::translate_cross(
                pumpkin_data::translation::java::COMMANDS_SETBLOCK_FAILED,
                pumpkin_data::translation::bedrock::COMMANDS_SETBLOCK_FAILED,
                [],
            )))
        }
    }
}

pub fn init_command_tree() -> CommandTree {
    CommandTree::new(NAMES, DESCRIPTION).then(
        argument(ARG_BLOCK_POS, BlockPosArgumentConsumer).then(
            argument(ARG_BLOCK, BlockArgumentConsumer)
                .then(literal("replace").execute(Executor(Mode::Replace)))
                .then(literal("destroy").execute(Executor(Mode::Destroy)))
                .then(literal("keep").execute(Executor(Mode::Keep)))
                .then(literal("strict").execute(Executor(Mode::Strict)))
                .execute(Executor(Mode::Replace)),
        ),
    )
}
