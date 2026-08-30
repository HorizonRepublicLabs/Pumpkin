#[allow(clippy::wildcard_imports)]
use super::*;

impl JavaClient {
    pub fn handle_close_container(
        &self,
        player: &Arc<Player>,
        _server: &Server,
        packet: &SCloseContainer,
    ) {
        #[cfg(feature = "jvm-plugins")]
        if crate::plugin::loader::jvm::handle_menu_close(packet.window_id.0) {
            return;
        }
        player.on_handled_screen_closed();
    }
}
