use crate::entity::player::Player;
use base64::{Engine as _, engine::general_purpose};
use core::error;
use pumpkin_config::BasicConfiguration;
use pumpkin_data::packet::{CURRENT_MC_VERSION, LOWEST_SUPPORTED_MC_VERSION};
use pumpkin_protocol::{
    Players, Sample, StatusResponse, Version,
    java::client::{config::CPluginMessage, status::CStatusResponse},
    ser::NetworkWriteExt,
};
use pumpkin_util::text::TextComponent;
use std::{fs, path::Path};
use tracing::{debug, info, warn};
use uuid::Uuid;

const DEFAULT_ICON: &[u8] = include_bytes!("../../../../assets/default_icon.png");
const MAX_SAMPLE_PLAYERS: usize = 12;

fn load_icon_from_file<P: AsRef<Path>>(path: P) -> Result<String, Box<dyn error::Error>> {
    let buf = fs::read(path)?;
    if buf.len() >= 24 {
        let width = u32::from_be_bytes([buf[16], buf[17], buf[18], buf[19]]);
        let height = u32::from_be_bytes([buf[20], buf[21], buf[22], buf[23]]);

        if width != 64 || height != 64 {
            return Err("Invalid favicon dimensions (must be 64x64)".into());
        }
    }
    Ok(load_icon_from_bytes(&buf))
}

fn load_icon_from_bytes(png_data: &[u8]) -> String {
    assert!(!png_data.is_empty(), "PNG data is empty");
    let mut result = "data:image/png;base64,".to_owned();
    general_purpose::STANDARD.encode_string(png_data, &mut result);
    result
}

pub struct CachedStatus {
    pub status_response: StatusResponse,
    player_samples: Vec<(Uuid, String)>,
}

pub struct CachedBranding {
    /// Cached server brand buffer so we don't have to rebuild them every time a player joins
    cached_server_brand: Box<[u8]>,
}

impl Default for CachedBranding {
    fn default() -> Self {
        Self::new(Self::DEFAULT_BRAND)
    }
}

impl CachedBranding {
    pub const DEFAULT_BRAND: &'static str = "Pumpkin";

    /// Builds the cached `minecraft:brand` payload for the configured brand.
    ///
    /// Falls back to [`Self::DEFAULT_BRAND`] if the brand is empty or cannot be encoded.
    #[must_use]
    pub fn new(brand: &str) -> Self {
        let brand = if brand.is_empty() {
            Self::DEFAULT_BRAND
        } else {
            brand
        };

        Self {
            cached_server_brand: Self::encode_brand(brand)
                .unwrap_or_else(|| Self::encode_brand(Self::DEFAULT_BRAND).unwrap_or_default()),
        }
    }

    fn encode_brand(brand: &str) -> Option<Box<[u8]>> {
        let mut buf = Vec::with_capacity(brand.len() + 3);
        buf.write_string(brand).ok()?;
        Some(buf.into_boxed_slice())
    }

    #[must_use]
    pub fn get_branding(&self) -> CPluginMessage<'_> {
        CPluginMessage::new("minecraft:brand", &self.cached_server_brand)
    }
}

impl CachedStatus {
    #[must_use]
    pub fn new(config: &BasicConfiguration, motd: &str, max_players: u32) -> Self {
        let status_response = Self::build_response(config, motd, max_players);

        Self {
            status_response,
            player_samples: Vec::new(),
        }
    }

    pub fn get_status_response(&self, client_protocol: i32) -> StatusResponse {
        let mut response = self.status_response.clone();

        let supported_min = LOWEST_SUPPORTED_MC_VERSION.protocol_version();
        let supported_max = CURRENT_MC_VERSION.protocol_version();

        if client_protocol >= supported_min
            && client_protocol <= supported_max
            && let Some(version) = &mut response.version
        {
            version.protocol = client_protocol as u32;
        }

        response
    }

    pub fn get_status_packet(&self, client_protocol: i32) -> CStatusResponse {
        let response = self.get_status_response(client_protocol);
        let json = serde_json::to_string(&response).unwrap_or_default();
        CStatusResponse::new(json)
    }

    fn build_sample_list(&self) -> Vec<Sample> {
        self.player_samples
            .iter()
            .take(MAX_SAMPLE_PLAYERS)
            .map(|(id, name)| Sample {
                name: name.clone(),
                id: id.to_string(),
            })
            .collect()
    }

    pub fn add_player(&mut self, player: &Player) {
        let player_id = player.gameprofile.id;
        let player_name = player.gameprofile.name.clone();

        if !self.player_samples.iter().any(|(id, _)| *id == player_id) {
            self.player_samples.push((player_id, player_name));
            let sample = self.build_sample_list();

            if let Some(players) = &mut self.status_response.players {
                players.online = players.online.saturating_add(1);
                players.sample = sample;
            }
        }
    }

    pub fn remove_player(&mut self, player: &Player) {
        let player_id = player.gameprofile.id;

        if self.player_samples.iter().any(|(id, _)| *id == player_id) {
            self.player_samples.retain(|(id, _)| *id != player_id);
            let sample = self.build_sample_list();

            if let Some(players) = &mut self.status_response.players {
                players.online = players.online.saturating_sub(1);
                players.sample = sample;
            }
        }
    }

    pub fn build_response(
        config: &BasicConfiguration,
        motd: &str,
        max_players: u32,
    ) -> StatusResponse {
        let favicon = if config.use_favicon {
            config.favicon_path.as_ref().map_or_else(
                || {
                    debug!("Loading default icon");

                    // Attempt to load default icon
                    Some(load_icon_from_bytes(DEFAULT_ICON))
                },
                |icon_path| {
                    if !std::path::Path::new(icon_path)
                        .extension()
                        .is_some_and(|ext| ext.eq_ignore_ascii_case("png"))
                    {
                        warn!("Favicon is not a PNG-image, using default.");
                        return Some(load_icon_from_bytes(DEFAULT_ICON));
                    }
                    debug!("Attempting to load server favicon from '{icon_path}'");

                    match load_icon_from_file(icon_path) {
                        Ok(icon) => Some(icon),
                        Err(e) => {
                            let error_message = e.downcast_ref::<std::io::Error>().map_or_else(
                                || format!("other error: {e}; using default."),
                                |io_err| {
                                    if io_err.kind() == std::io::ErrorKind::NotFound {
                                        "not found; using default.".to_string()
                                    } else {
                                        format!("I/O error: {io_err}; using default.")
                                    }
                                },
                            );
                            warn!("Failed to load favicon from '{icon_path}': {error_message}");

                            Some(load_icon_from_bytes(DEFAULT_ICON))
                        }
                    }
                },
            )
        } else {
            info!("Favicon usage is disabled.");
            None
        };

        StatusResponse {
            version: Some(Version {
                name: format!("{LOWEST_SUPPORTED_MC_VERSION}-{CURRENT_MC_VERSION}"),
                protocol: LOWEST_SUPPORTED_MC_VERSION.protocol_version() as u32,
            }),
            players: Some(Players {
                max: max_players,
                online: 0,
                sample: vec![],
            }),
            description: TextComponent::text(motd.to_string()),
            favicon,
            // This should stay true even when reports are disabled.
            // It prevents the annoying popup when joining the server.
            enforce_secure_chat: true,
        }
    }
}

impl Default for CachedStatus {
    fn default() -> Self {
        Self::new(
            &BasicConfiguration::default(),
            "A blazingly fast Pumpkin server!",
            1000,
        )
    }
}
