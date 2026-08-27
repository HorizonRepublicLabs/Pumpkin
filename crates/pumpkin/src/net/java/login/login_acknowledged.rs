#[allow(clippy::wildcard_imports)]
use super::*;

impl PendingConnection {
    pub async fn handle_login_acknowledged(
        &mut self,
        server: &Server,
    ) -> Option<PacketHandlerResult> {
        debug!("Handling login acknowledgement");
        if !self.version.load().supports_configuration_state() {
            self.kick(TextComponent::text(
                "Configuration state not supported for this version",
            ))
            .await;
            return Some(PacketHandlerResult::Stop);
        }
        self.connection_state.store(ConnectionState::Config);

        self.offer_network_query(server).await;
        self.send_packet_now(&server.get_branding()).await;

        if server.advanced_config.server_links.enabled
            && self.version.load() >= JavaMinecraftVersion::V_1_21
        {
            let mut links: Vec<Link> = Vec::new();

            let bug_report = &server.advanced_config.server_links.bug_report;
            if !bug_report.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::BugReport), bug_report));
            }

            let support = &server.advanced_config.server_links.support;
            if !support.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::Support), support));
            }

            let status = &server.advanced_config.server_links.status;
            if !status.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::Status), status));
            }

            let feedback = &server.advanced_config.server_links.feedback;
            if !feedback.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::Feedback), feedback));
            }

            let community = &server.advanced_config.server_links.community;
            if !community.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::Community), community));
            }

            let website = &server.advanced_config.server_links.website;
            if !website.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::Website), website));
            }

            let forums = &server.advanced_config.server_links.forums;
            if !forums.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::Forums), forums));
            }

            let news = &server.advanced_config.server_links.news;
            if !news.is_empty() {
                links.push(Link::new(Label::BuiltIn(LinkType::News), news));
            }

            let announcements = &server.advanced_config.server_links.announcements;
            if !announcements.is_empty() {
                links.push(Link::new(
                    Label::BuiltIn(LinkType::Announcements),
                    announcements,
                ));
            }

            for (key, value) in &server.advanced_config.server_links.custom {
                links.push(Link::new(
                    Label::TextComponent(TextComponent::text(key.clone()).into()),
                    value,
                ));
            }

            self.send_packet_now(&CConfigServerLinks::new(&links)).await;
        }

        let resource_config = &server.advanced_config.resource_pack.java;
        if resource_config.enabled {
            let uuid = Uuid::new_v3(&uuid::Uuid::NAMESPACE_DNS, resource_config.url.as_bytes());
            let resource_pack = CConfigAddResourcePack::new(
                &uuid,
                &resource_config.url,
                &resource_config.sha1,
                resource_config.force,
                if resource_config.prompt_message.is_empty() {
                    None
                } else {
                    Some(TextComponent::text(resource_config.prompt_message.clone()))
                },
            );

            self.send_packet_now(&resource_pack).await;
        } else if self.version.load() >= JavaMinecraftVersion::V_1_20_5 {
            // Deliberately not sent yet. A mod loader decides whether the server is modded
            // from what arrives early in configuration, and treats the vanilla known-packs
            // exchange as proof that it is not. Sending it before the modded declaration
            // loses the connection, so it waits for the client's brand — see
            // `PendingConnection::flush_deferred_known_packs`.
            self.known_packs_deferred = true;
        } else {
            self.handle_known_packs(
                SKnownPacks {
                    known_packs: Vec::new(),
                },
                server,
            )
            .await;
        }
        debug!("login acknowledged");
        None
    }

    /// Offers the mod loader's channel list before configuration proper begins.
    ///
    /// A `NeoForge` client waits for this to decide whether the server speaks its protocol,
    /// and gives up if the vanilla configuration starts without it. A vanilla client
    /// ignores a channel it does not know, so it costs nothing to send to everyone.
    async fn offer_network_query(&mut self, server: &Server) {
        use crate::net::java::neoforge::{self, NeoForgeSettings};

        let config = &server.advanced_config.networking.java.neoforge;
        let settings = NeoForgeSettings {
            enabled: config.enabled,
            sync_registries: config.sync_registries,
        };

        if let Some(query) = neoforge::network_query(&settings) {
            self.send_packet_now(&CPluginMessage::new(
                neoforge::NETWORK_QUERY_CHANNEL,
                &query,
            ))
            .await;
        }
    }

    pub async fn send_known_packs(&mut self) {
        if self.known_packs_sent {
            return;
        }
        self.known_packs_sent = true;

        let version_str = self.version.load().to_string();
        self.send_packet_now(&CKnownPacks::new(&[KnownPack {
            namespace: "minecraft",
            id: "core",
            version: &version_str,
        }]))
        .await;
    }

    /// Sends the known-packs exchange that [`Self::handle_login_acknowledged`] held back.
    ///
    /// Called once the client has said enough about itself for a mod loader to have been
    /// answered — its brand, or failing that its settings. Both arrive early in
    /// configuration, and every client sends at least one.
    pub async fn flush_deferred_known_packs(&mut self) {
        if self.known_packs_deferred {
            self.send_known_packs().await;
        }
    }
}
