#[allow(clippy::wildcard_imports)]
use super::*;

impl JavaClient {
    pub async fn handle_plugin_message(&self, plugin_message: SPluginMessage<'_>) {
        debug!("Handling plugin message");
        if plugin_message.channel.starts_with(BRAND_CHANNEL_PREFIX) {
            debug!("Got a client brand");
            match str::from_utf8(plugin_message.data) {
                Ok(brand) => self.brand.store(Arc::new(Some(brand.to_string()))),
                Err(e) => self.kick(TextComponent::text(e.to_string())).await,
            }
        } else {
            // Unknown channels are ignored, matching vanilla behaviour. Mod loaders such as
            // NeoForge rely on this: a server that announces no modded channels of its own is
            // treated as vanilla, and the client disables the mod-side networking it would
            // otherwise expect.
            debug!(
                "Ignoring plugin message on unhandled channel {} ({} bytes)",
                plugin_message.channel,
                plugin_message.data.len()
            );
        }
    }
}
