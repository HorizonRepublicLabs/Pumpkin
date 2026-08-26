#[allow(clippy::wildcard_imports)]
use super::*;

impl PendingConnection {
    pub async fn handle_known_packs(&mut self, _packet: SKnownPacks<'_>, server: &Server) {
        // Last chance to queue mod-loader work: after this the sequence starts running and
        // the stage that would carry it has passed.
        self.try_queue_mod_loader_tasks(server);

        // The registry send is a step in the configuration sequence rather than something
        // done here directly: mod loaders need to run their own id sync before it.
        self.progress_config_tasks().await;
    }

    /// Sends the registries and tags every client needs.
    pub(in crate::net::java) async fn send_registry_data(&mut self) {
        let version = self.version.load();
        if version.supports_configuration_state() {
            self.send_packet_now(&CFeatureFlags::new(&["minecraft:vanilla".to_string()]))
                .await;
            let registry = pumpkin_data::registry::Registry::get_synced(version);
            for reg in &registry {
                self.send_packet_now(&CRegistryData::new(&reg.registry_id, &reg.registry_entries))
                    .await;
            }
        }
        let mut tags = Vec::new();
        for &key in pumpkin_data::tag::RegistryKey::NETWORK_KEYS {
            if pumpkin_data::tag::get_registry_key_tags(version, key)
                .is_some_and(|map| !map.is_empty())
            {
                tags.push(key);
            }
        }
        self.send_packet_now(&CUpdateTags::new(&tags)).await;
    }
}
