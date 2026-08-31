use pumpkin_data::tag::{RegistryKey, get_tag_ids};
use pumpkin_data::{Block, BlockId, translation};
use pumpkin_protocol::java::client::play::{ArgumentType, SuggestionProviders};
use pumpkin_util::text::TextComponent;

use crate::command::args::ConsumeResult;
use crate::{command::dispatcher::CommandError, server::Server};

use super::{
    super::{
        CommandSender,
        args::{ArgumentConsumer, RawArgs},
    },
    Arg, DefaultNameArgConsumer, FindArg, GetClientSideArgParser,
};

pub struct BlockArgumentConsumer;

impl GetClientSideArgParser for BlockArgumentConsumer {
    fn get_client_side_parser(&self) -> ArgumentType {
        ArgumentType::BlockState
    }

    fn get_client_side_suggestion_type_override(&self) -> Option<SuggestionProviders> {
        None
    }
}

impl ArgumentConsumer for BlockArgumentConsumer {
    fn consume<'a>(
        &'a self,
        _sender: &'a CommandSender,
        _server: &'a Server,
        args: &mut RawArgs<'a>,
    ) -> ConsumeResult<'a> {
        args.pop().map(|arg| Arg::Block(arg.value))
    }
}

impl DefaultNameArgConsumer for BlockArgumentConsumer {
    fn default_name(&self) -> &'static str {
        "block"
    }
}

impl BlockArgumentConsumer {
    /// The block and the exact state a `block_id[prop=value,...]` argument names.
    ///
    /// Without a property list this is the block's default state. Properties resolve
    /// through the dynamic registry first -- a runtime-registered crop's states live
    /// there -- and fall back to the generated tables for vanilla blocks.
    pub fn find_state_arg(
        args: &super::ConsumedArgs,
        name: &str,
    ) -> Result<(&'static Block, pumpkin_data::BlockStateId), CommandError> {
        let raw = match args.get(name) {
            Some(Arg::Block(raw)) => *raw,
            _ => return Err(CommandError::InvalidConsumption(Some(name.to_string()))),
        };
        let (base, properties) = match raw.split_once('[') {
            Some((base, rest)) => (base, rest.strip_suffix(']').unwrap_or(rest)),
            None => (raw, ""),
        };
        let Some(block) = Block::from_name(base) else {
            return Err(CommandError::CommandFailed(TextComponent::translate_cross(
                translation::java::ARGUMENT_BLOCK_ID_INVALID,
                translation::java::ARGUMENT_BLOCK_ID_INVALID,
                [TextComponent::text(base.to_string())],
            )));
        };
        if properties.is_empty() {
            return Ok((block, block.default_state.id));
        }
        let values: Vec<(&str, &str)> = properties
            .split(',')
            .filter_map(|pair| pair.split_once('='))
            .map(|(key, value)| (key.trim(), value.trim()))
            .collect();
        let state_id = pumpkin_data::dynamic::block_state_for(block.id, &values)
            .unwrap_or_else(|| block.from_properties(&values).to_state_id(block));
        Ok((block, state_id))
    }
}

impl<'a> FindArg<'a> for BlockArgumentConsumer {
    type Data = &'static Block;

    fn find_arg(args: &'a super::ConsumedArgs, name: &str) -> Result<Self::Data, CommandError> {
        match args.get(name) {
            Some(Arg::Block(name)) => Block::from_name(name).map_or_else(
                || {
                    if name.starts_with("minecraft:") {
                        Err(CommandError::CommandFailed(TextComponent::translate_cross(
                            translation::java::ARGUMENT_BLOCK_ID_INVALID,
                            translation::java::ARGUMENT_BLOCK_ID_INVALID,
                            [TextComponent::text((*name).to_string())],
                        )))
                    } else {
                        Err(CommandError::CommandFailed(TextComponent::translate_cross(
                            translation::java::ARGUMENT_BLOCK_ID_INVALID,
                            translation::java::ARGUMENT_BLOCK_ID_INVALID,
                            [TextComponent::text("minecraft:".to_string() + *name)],
                        )))
                    }
                },
                Result::Ok,
            ),
            _ => Err(CommandError::InvalidConsumption(Some(name.to_string()))),
        }
    }
}

pub struct BlockPredicateArgumentConsumer;
#[derive(Debug)]
pub enum BlockPredicate {
    Tag(Vec<u16>),
    Block(BlockId),
}

impl GetClientSideArgParser for BlockPredicateArgumentConsumer {
    fn get_client_side_parser(&self) -> ArgumentType {
        ArgumentType::BlockPredicate
    }

    fn get_client_side_suggestion_type_override(&self) -> Option<SuggestionProviders> {
        None
    }
}

impl ArgumentConsumer for BlockPredicateArgumentConsumer {
    fn consume<'a>(
        &'a self,
        _sender: &'a CommandSender,
        _server: &'a Server,
        args: &mut RawArgs<'a>,
    ) -> ConsumeResult<'a> {
        args.pop().map(|arg| Arg::BlockPredicate(arg.value))
    }
}

impl DefaultNameArgConsumer for BlockPredicateArgumentConsumer {
    fn default_name(&self) -> &'static str {
        "filter"
    }
}

impl<'a> FindArg<'a> for BlockPredicateArgumentConsumer {
    type Data = Option<BlockPredicate>;

    fn find_arg(args: &'a super::ConsumedArgs, name: &str) -> Result<Self::Data, CommandError> {
        match args.get(name) {
            Some(Arg::BlockPredicate(name)) => name.strip_prefix("#").map_or_else(
                || {
                    Block::from_name(name).map_or_else(
                        || {
                            if name.starts_with("minecraft:") {
                                Err(CommandError::CommandFailed(TextComponent::translate_cross(
                                    translation::java::ARGUMENT_BLOCK_ID_INVALID,
                                    translation::java::ARGUMENT_BLOCK_ID_INVALID,
                                    [TextComponent::text((*name).to_string())],
                                )))
                            } else {
                                Err(CommandError::CommandFailed(TextComponent::translate_cross(
                                    translation::java::ARGUMENT_BLOCK_ID_INVALID,
                                    translation::java::ARGUMENT_BLOCK_ID_INVALID,
                                    [TextComponent::text("minecraft:".to_string() + *name)],
                                )))
                            }
                        },
                        |block| Ok(Some(BlockPredicate::Block(block.id))),
                    )
                },
                |tag| {
                    get_tag_ids(RegistryKey::Block, tag).map_or_else(
                        || {
                            Err(CommandError::CommandFailed(TextComponent::translate_cross(
                                translation::java::ARGUMENTS_BLOCK_TAG_UNKNOWN,
                                translation::java::ARGUMENTS_BLOCK_TAG_UNKNOWN,
                                [TextComponent::text((*tag).to_string())],
                            )))
                        },
                        |blocks| Ok(Some(BlockPredicate::Tag(blocks.to_vec()))),
                    )
                },
            ),
            _ => Ok(None),
        }
    }
}
