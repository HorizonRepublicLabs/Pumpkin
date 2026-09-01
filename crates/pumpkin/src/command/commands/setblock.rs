use pumpkin_data::translation;
use pumpkin_util::PermissionLvl;
use pumpkin_util::permission::{Permission, PermissionDefault, PermissionRegistry};
use pumpkin_util::text::TextComponent;
use pumpkin_world::world::BlockFlags;

use crate::command::argument_builder::{ArgumentBuilder, argument, command, literal};
use crate::command::argument_types::block::BlockArgumentType;
use crate::command::argument_types::coordinates::block_pos::BlockPosArgumentType;
use crate::command::context::command_context::CommandContext;
use crate::command::errors::error_types::CommandErrorType;
use crate::command::node::dispatcher::CommandDispatcher;
use crate::command::node::{CommandExecutor, CommandExecutorResult};

const DESCRIPTION: &str = "Place a block.";
const PERMISSION: &str = "minecraft:command.setblock";

const ERROR_FAILED: CommandErrorType<0> = CommandErrorType::new(
    translation::java::COMMANDS_SETBLOCK_FAILED,
    translation::java::COMMANDS_SETBLOCK_FAILED,
);

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

struct SetBlockExecutor(Mode);

impl CommandExecutor for SetBlockExecutor {
    fn execute(&self, context: &CommandContext) -> CommandExecutorResult {
        let block = BlockArgumentType::get(context, "block")?;
        let block_state_id = block.default_state.id;
        let mode = self.0;
        let world = context.source.world();
        let pos = BlockPosArgumentType::get_loaded_block_pos(context, "pos")?;

        let success = match mode {
            Mode::Destroy => {
                world.break_block(
                    &pos,
                    None,
                    BlockFlags::SKIP_DROPS | BlockFlags::NOTIFY_ALL | BlockFlags::FORCE_STATE,
                );
                world.set_block_state(
                    &pos,
                    block_state_id,
                    BlockFlags::NOTIFY_ALL | BlockFlags::FORCE_STATE,
                );
                true
            }
            Mode::Replace => {
                world.set_block_state(
                    &pos,
                    block_state_id,
                    BlockFlags::NOTIFY_ALL | BlockFlags::FORCE_STATE,
                );
                true
            }
            Mode::Keep => {
                let old_state = world.get_block_state(&pos);
                if old_state.is_air() {
                    world.set_block_state(
                        &pos,
                        block_state_id,
                        BlockFlags::NOTIFY_ALL | BlockFlags::FORCE_STATE,
                    );
                    true
                } else {
                    false
                }
            }
            Mode::Strict => {
                world.set_block_state(
                    &pos,
                    block_state_id,
                    BlockFlags::NOTIFY_LISTENERS
                        | BlockFlags::SKIP_BLOCK_ADDED_CALLBACK
                        | BlockFlags::FORCE_STATE,
                );
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
            world.flush_block_updates();
            context.source.send_feedback(
                TextComponent::translate_cross(
                    pumpkin_data::translation::java::COMMANDS_SETBLOCK_SUCCESS,
                    pumpkin_data::translation::bedrock::COMMANDS_SETBLOCK_SUCCESS,
                    [
                        TextComponent::text(pos.0.x.to_string()),
                        TextComponent::text(pos.0.y.to_string()),
                        TextComponent::text(pos.0.z.to_string()),
                    ],
                ),
                true,
            );
            Ok(1)
        } else {
            Err(ERROR_FAILED.create_without_context())
        }
    }
}

pub fn register(dispatcher: &mut CommandDispatcher, registry: &PermissionRegistry) {
    registry.register_permission_or_panic(Permission::new(
        PERMISSION,
        DESCRIPTION,
        PermissionDefault::Op(PermissionLvl::Two),
    ));

    dispatcher.register(
        command("setblock", DESCRIPTION).requires(PERMISSION).then(
            argument("pos", BlockPosArgumentType).then(
                argument("block", BlockArgumentType)
                    .executes(SetBlockExecutor(Mode::Replace))
                    .then(literal("destroy").executes(SetBlockExecutor(Mode::Destroy)))
                    .then(literal("keep").executes(SetBlockExecutor(Mode::Keep)))
                    .then(literal("replace").executes(SetBlockExecutor(Mode::Replace)))
                    .then(literal("strict").executes(SetBlockExecutor(Mode::Strict))),
            ),
        ),
    );
}
