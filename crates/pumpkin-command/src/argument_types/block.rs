use crate::{
    argument_types::argument_type::{ArgumentType, JavaClientArgumentType},
    context::command_context::CommandContext,
    errors::command_syntax_error::CommandSyntaxError,
    errors::error_types::CommandErrorType,
    string_reader::StringReader,
    suggestion::suggestions::{Suggestions, SuggestionsBuilder},
};
use pumpkin_data::{Block, BlockStateId, translation};
use pumpkin_util::text::TextComponent;

pub const INVALID_BLOCK_ERROR_TYPE: CommandErrorType<1> = CommandErrorType::new(
    translation::java::ARGUMENT_BLOCK_ID_INVALID,
    translation::java::ARGUMENT_BLOCK_ID_INVALID,
);
const UNKNOWN_PROPERTY: CommandErrorType<2> = CommandErrorType::new(
    translation::java::ARGUMENT_BLOCK_PROPERTY_UNKNOWN,
    translation::java::ARGUMENT_BLOCK_PROPERTY_UNKNOWN,
);
const DUPLICATE_PROPERTY: CommandErrorType<2> = CommandErrorType::new(
    translation::java::ARGUMENT_BLOCK_PROPERTY_DUPLICATE,
    translation::java::ARGUMENT_BLOCK_PROPERTY_DUPLICATE,
);
const INVALID_VALUE: CommandErrorType<3> = CommandErrorType::new(
    translation::java::ARGUMENT_BLOCK_PROPERTY_INVALID,
    translation::java::ARGUMENT_BLOCK_PROPERTY_INVALID,
);
const NO_VALUE: CommandErrorType<2> = CommandErrorType::new(
    translation::java::ARGUMENT_BLOCK_PROPERTY_NOVALUE,
    translation::java::ARGUMENT_BLOCK_PROPERTY_NOVALUE,
);
const UNCLOSED: CommandErrorType<0> = CommandErrorType::new(
    translation::java::ARGUMENT_BLOCK_PROPERTY_UNCLOSED,
    translation::java::ARGUMENT_BLOCK_PROPERTY_UNCLOSED,
);

/// A block and one of its states: `stone`, or `oak_stairs[facing=east,half=top]` with the
/// unnamed properties taken from the default state.
#[derive(Debug, Copy, Clone)]
pub struct BlockStateArgument {
    pub block: &'static Block,
    pub state: BlockStateId,
}

#[derive(Debug, Copy, Clone, PartialEq, Eq, Hash)]
pub struct BlockArgumentType;

fn is_id_char(c: char) -> bool {
    c.is_alphanumeric() || c == '_' || c == ':' || c == '/' || c == '.' || c == '-'
}

/// Reads one property name or value, with the characters vanilla allows in them.
fn read_word(reader: &mut StringReader) -> String {
    let start = reader.cursor();
    while reader
        .peek()
        .is_some_and(|c| c.is_alphanumeric() || c == '_' || c == '-' || c == '.' || c == '+')
    {
        reader.skip();
    }
    reader.string()[start..reader.cursor()].to_string()
}

/// Parses `[name=value,...]` against `block`, with the reader just past the block id.
fn parse_state(
    reader: &mut StringReader,
    block: &'static Block,
    block_id: &str,
) -> Result<BlockStateId, CommandSyntaxError> {
    if reader.peek() != Some('[') {
        return Ok(block.default_state.id);
    }
    reader.skip();

    // Every name and value the block's states use, to tell an unknown property from a bad value.
    let known: Vec<(&'static str, &'static str)> = block
        .states
        .iter()
        .flat_map(|s| block.state_values(s.id))
        .collect();
    let id = || TextComponent::text(block_id.to_string());

    let mut given: Vec<(String, String)> = Vec::new();
    reader.skip_whitespace();
    while reader.peek() != Some(']') {
        let start = reader.cursor();
        let name = read_word(reader);
        if !known.iter().any(|(n, _)| *n == name) {
            reader.set_cursor(start);
            return Err(UNKNOWN_PROPERTY.create(reader, id(), TextComponent::text(name)));
        }
        if given.iter().any(|(n, _)| *n == name) {
            reader.set_cursor(start);
            return Err(DUPLICATE_PROPERTY.create(reader, TextComponent::text(name), id()));
        }
        reader.skip_whitespace();
        if reader.peek() != Some('=') {
            return Err(NO_VALUE.create(reader, TextComponent::text(name), id()));
        }
        reader.skip();
        reader.skip_whitespace();
        let value_start = reader.cursor();
        let value = read_word(reader);
        if !known.contains(&(name.as_str(), value.as_str())) {
            reader.set_cursor(value_start);
            return Err(INVALID_VALUE.create(
                reader,
                id(),
                TextComponent::text(value),
                TextComponent::text(name),
            ));
        }
        given.push((name, value));
        reader.skip_whitespace();
        match reader.peek() {
            Some(',') => {
                reader.skip();
                reader.skip_whitespace();
            }
            Some(']') => {}
            _ => return Err(UNCLOSED.create(reader)),
        }
    }
    reader.skip();

    let values: Vec<(&str, &str)> = given
        .iter()
        .map(|(n, v)| (n.as_str(), v.as_str()))
        .collect();
    // Every name and value was checked above, so a matching state exists.
    Ok(block.state_with(&values).unwrap_or(block.default_state.id))
}

impl<S: crate::source::CommandSource> ArgumentType<S> for BlockArgumentType {
    type Item = BlockStateArgument;

    fn parse(&self, reader: &mut StringReader) -> Result<Self::Item, CommandSyntaxError> {
        let start = reader.cursor();
        while reader.peek().is_some_and(is_id_char) {
            reader.skip();
        }
        let block_name = &reader.string()[start..reader.cursor()];
        let normalized = if block_name.contains(':') {
            block_name.to_string()
        } else {
            format!("minecraft:{block_name}")
        };

        let block = Block::from_name(&normalized).ok_or_else(|| {
            INVALID_BLOCK_ERROR_TYPE.create(reader, TextComponent::text(normalized.clone()))
        })?;
        let state = parse_state(reader, block, &normalized)?;
        Ok(BlockStateArgument { block, state })
    }

    fn client_side_parser(&'_ self) -> JavaClientArgumentType {
        JavaClientArgumentType::BlockState
    }

    fn list_suggestions(
        &self,
        _context: &CommandContext<S>,
        builder: SuggestionsBuilder,
    ) -> Suggestions {
        builder.build()
    }
}

impl BlockArgumentType {
    /// The block named, whatever state was given with it.
    pub fn get<S: crate::source::CommandSource>(
        context: &CommandContext<S>,
        name: &str,
    ) -> Result<&'static Block, CommandSyntaxError> {
        context
            .get_argument::<BlockStateArgument>(name)
            .map(|arg| arg.block)
    }

    /// The state named: the block's default state unless properties were given.
    pub fn get_state<S: crate::source::CommandSource>(
        context: &CommandContext<S>,
        name: &str,
    ) -> Result<BlockStateId, CommandSyntaxError> {
        context
            .get_argument::<BlockStateArgument>(name)
            .map(|arg| arg.state)
    }
}

#[cfg(test)]
mod tests {
    use super::*;

    fn parse(input: &str) -> Result<BlockStateArgument, CommandSyntaxError> {
        let mut reader = StringReader::new(input);
        <BlockArgumentType as ArgumentType<()>>::parse(&BlockArgumentType, &mut reader)
    }

    #[test]
    fn a_bare_id_is_the_default_state() {
        let arg = parse("oak_stairs").unwrap();
        assert_eq!(arg.state, Block::OAK_STAIRS.default_state.id);
    }

    #[test]
    fn named_properties_pick_the_state_and_the_rest_stay_default() {
        let arg = parse("minecraft:oak_stairs[ half = top ,facing=east]").unwrap();
        let values = Block::OAK_STAIRS.state_values(arg.state);
        assert!(values.contains(&("half", "top")));
        assert!(values.contains(&("facing", "east")));
        assert!(
            values.contains(&("shape", "straight")),
            "unnamed shape keeps its default"
        );
        assert!(values.contains(&("waterlogged", "false")));
    }

    #[test]
    fn bad_properties_are_refused() {
        assert!(parse("oak_stairs[colour=red]").is_err(), "unknown property");
        assert!(parse("oak_stairs[half=middle]").is_err(), "invalid value");
        assert!(
            parse("oak_stairs[half=top,half=bottom]").is_err(),
            "duplicate"
        );
        assert!(parse("oak_stairs[half]").is_err(), "no value");
        assert!(parse("oak_stairs[half=top").is_err(), "unclosed");
    }
}
