//! Registration of blocks, items and entity types that the server does not ship with.
//!
//! Every definition starts from a vanilla template and overrides what it needs, because a
//! block carries dozens of fields and a full set of block states and most custom content
//! wants "stone, but mine" rather than a definition built from nothing.
//!
//! # When to call this
//!
//! Only from [`Plugin::on_load`](crate::Plugin::on_load). The server freezes its registries
//! once every plugin has loaded, and from that point ids are fixed for the lifetime of the
//! process and every function here returns an error. The window does not reopen on a hot
//! reload either, so registered content needs a server restart.
//!
//! # Example
//!
//! ```rust,ignore
//! use pumpkin_plugin_api::registry::{BlockDefinition, register_block};
//!
//! let id = register_block(&BlockDefinition::new("examplemod:ruby_block", "stone")
//!     .hardness(4.0)
//!     .luminance(7))?;
//! ```

pub use crate::wit::pumpkin::plugin::registry::{
    ChannelProtocol, NetworkChannel, declare_network_channel, is_frozen,
    register_block_entity_type, register_entity_type, register_item, register_menu_type,
};

use crate::wit::pumpkin::plugin::registry::{
    self, BlockDefinition as WitBlockDefinition, BlockProperty,
    EntityTypeDefinition as WitEntityTypeDefinition, ItemDefinition as WitItemDefinition,
};

/// A block to add to the server's registry.
#[derive(Clone)]
pub struct BlockDefinition(WitBlockDefinition);

impl BlockDefinition {
    /// Starts a definition that copies `template`, a vanilla block name such as `stone`.
    ///
    /// `id` must be namespaced, e.g. `examplemod:ruby_block`; the `minecraft` namespace is
    /// reserved for the server's own content.
    #[must_use]
    pub fn new(id: impl Into<String>, template: impl Into<String>) -> Self {
        Self(WitBlockDefinition {
            id: id.into(),
            template: template.into(),
            hardness: None,
            blast_resistance: None,
            luminance: None,
            properties: Vec::new(),
            default_state: 0,
            item: None,
        })
    }

    /// Overrides how long the block takes to break.
    #[must_use]
    pub fn hardness(mut self, hardness: f32) -> Self {
        self.0.hardness = Some(hardness);
        self
    }

    /// Overrides the block's resistance to explosions.
    #[must_use]
    pub fn blast_resistance(mut self, blast_resistance: f32) -> Self {
        self.0.blast_resistance = Some(blast_resistance);
        self
    }

    /// Overrides the light the block emits, 0 to 15.
    #[must_use]
    pub fn luminance(mut self, luminance: u8) -> Self {
        self.0.luminance = Some(luminance);
        self
    }

    /// Adds a property the block's states vary over.
    ///
    /// The number of states is the product of the value counts, and it has to match the
    /// client's or the two ends disagree about which state is which. Minecraft sorts a
    /// block's properties by name and treats them as digits with the first varying
    /// slowest, so add them in name order to line up.
    #[must_use]
    pub fn property<I, V>(mut self, name: impl Into<String>, values: I) -> Self
    where
        I: IntoIterator<Item = V>,
        V: Into<String>,
    {
        self.0.properties.push(BlockProperty {
            name: name.into(),
            values: values.into_iter().map(Into::into).collect(),
        });
        self
    }

    /// Sets which state the block is placed in, as an index into the state list its
    /// properties describe.
    #[must_use]
    pub fn default_state(mut self, index: u32) -> Self {
        self.0.default_state = index;
        self
    }

    /// Names the item that places this block. Register the item first.
    ///
    /// Without it nothing can place the block: the item a template carried belongs to the
    /// template, so it is not inherited.
    #[must_use]
    pub fn placed_by(mut self, item: impl Into<String>) -> Self {
        self.0.item = Some(item.into());
        self
    }
}

/// An item to add to the server's registry.
///
/// The template's components carry over, so an item copied from `diamond` behaves like one.
#[must_use]
pub fn item(id: impl Into<String>, template: impl Into<String>) -> WitItemDefinition {
    WitItemDefinition {
        id: id.into(),
        template: template.into(),
    }
}

/// An entity type to add to the server's registry.
#[must_use]
pub fn entity_type(id: impl Into<String>, template: impl Into<String>) -> WitEntityTypeDefinition {
    WitEntityTypeDefinition {
        id: id.into(),
        template: template.into(),
    }
}

/// Registers a block and returns the id it was assigned.
///
/// # Errors
///
/// Returns the server's message if the template is unknown, the id is unusable or already
/// taken, or registration has closed.
pub fn register_block(definition: &BlockDefinition) -> Result<u32, String> {
    registry::register_block(&definition.0)
}

/// Declares a modded network channel, in the phase and version the mod registered it under.
///
/// A mod's client half refuses to join a server that did not negotiate every channel it
/// registered as required — including channels only the client ever sends on, which the
/// server can simply ignore. Declare all of them.
///
/// `version` must match the mod's `event.registrar("...")` argument exactly.
///
/// # Errors
///
/// Returns the server's message if the id is not namespaced, the channel was already
/// declared for that phase, or declaration has closed.
///
/// # Example
///
/// ```rust,ignore
/// use pumpkin_plugin_api::registry::{ChannelProtocol, channel};
///
/// // The server sends this one, so it does not listen on it.
/// channel("mysticalagriculture:experience_pickup", ChannelProtocol::Play, "1", false)?;
/// // The client sends this one, so the server must announce that it listens.
/// channel("mysticalagriculture:update_aoe_offset", ChannelProtocol::Play, "1", true)?;
/// ```
pub fn channel(
    id: impl Into<String>,
    protocol: ChannelProtocol,
    version: impl Into<String>,
    serverbound: bool,
) -> Result<(), String> {
    declare_network_channel(&NetworkChannel {
        id: id.into(),
        protocol,
        version: version.into(),
        serverbound,
    })
}
