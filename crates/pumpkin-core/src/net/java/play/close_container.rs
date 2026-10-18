#[allow(clippy::wildcard_imports)]
use super::*;
use pumpkin_protocol::java::server::play::SCloseContainer;

impl JavaClient {
    pub fn handle_close_container(&self, player: &Arc<Player>, packet: &SCloseContainer) {
        let _ = packet;
        player.on_handled_screen_closed();
    }
}
