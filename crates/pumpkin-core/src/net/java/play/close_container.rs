#[allow(clippy::wildcard_imports)]
use super::*;
use pumpkin_protocol::java::server::play::SCloseContainer;

impl JavaClient {
    pub fn handle_close_container(&self, player: &Arc<Player>, packet: &SCloseContainer) {
        #[cfg(feature = "jvm-plugins")]
        if crate::plugin::loader::jvm::handle_menu_close(packet.window_id.0) {
            return;
        }
        #[cfg(not(feature = "jvm-plugins"))]
        let _ = packet;
        player.on_handled_screen_closed();
    }
}
