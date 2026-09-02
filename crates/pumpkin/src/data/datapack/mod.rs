pub mod function_loader;
pub mod recipe_loader;

use std::collections::HashMap;
use std::fs;
use std::path::{Path, PathBuf};
use std::sync::{Arc, RwLock};
use tracing::{info, warn};

use pumpkin_protocol::codec::recipe::DynamicRecipe;

use crate::command::context::command_source::CommandSource;
use crate::server::Server;
use crate::server::recipe::RecipeManager;

#[derive(Clone, Debug)]
pub struct LoadedDatapack {
    pub id: String,
    pub name: String,
    pub description: String,
    pub pack_format: u32,
    pub root_path: PathBuf,
    pub recipe_count: usize,
    pub function_count: usize,
}

#[derive(Clone, Debug, PartialEq, Eq)]
pub struct DatapackInfo {
    pub id: String,
    pub name: String,
    pub description: String,
    pub pack_format: u32,
    pub is_enabled: bool,
    pub recipe_count: usize,
    pub function_count: usize,
}

#[derive(Clone, Debug, PartialEq, Eq)]
pub enum DatapackEnablePosition {
    First,
    Last,
    Before(String),
    After(String),
}

pub struct DatapackManager {
    loaded_packs: RwLock<Vec<LoadedDatapack>>,
    functions: RwLock<HashMap<String, Vec<String>>>,
    function_tags: RwLock<HashMap<String, Vec<String>>>,
}

impl Default for DatapackManager {
    fn default() -> Self {
        Self::new()
    }
}

impl DatapackManager {
    #[must_use]
    pub fn new() -> Self {
        Self {
            loaded_packs: RwLock::new(Vec::new()),
            functions: RwLock::new(HashMap::new()),
            function_tags: RwLock::new(HashMap::new()),
        }
    }

    pub fn load_all(
        &self,
        world_path: &Path,
        enabled_packs: &[String],
        recipe_manager: &RecipeManager,
    ) {
        let datapacks_dir = world_path.join("datapacks");
        let mut loaded_packs_vec = Vec::new();
        let mut all_recipes: Vec<DynamicRecipe> = Vec::new();
        let mut all_functions: HashMap<String, Vec<String>> = HashMap::new();
        let mut all_function_tags: HashMap<String, Vec<String>> = HashMap::new();

        if datapacks_dir.is_dir() {
            let Ok(entries) = fs::read_dir(&datapacks_dir) else {
                warn!(
                    "Failed to read datapacks directory: {}",
                    datapacks_dir.display()
                );
                return;
            };

            for entry in entries.flatten() {
                let pack_path = entry.path();
                let file_name = entry.file_name().to_string_lossy().to_string();

                if file_name.starts_with('.') || !pack_path.is_dir() {
                    continue;
                }

                if !pack_is_loadable(&file_name, &pack_path, enabled_packs) {
                    continue;
                }
                let pack_id = format!("file/{file_name}");

                let (description, pack_format) = read_pack_mcmeta(&pack_path);

                let data_dir = pack_path.join("data");
                let mut pack_recipe_count = 0;
                let mut pack_recipe_files_seen = 0;
                let mut pack_function_count = 0;

                if data_dir.is_dir()
                    && let Ok(ns_entries) = fs::read_dir(&data_dir)
                {
                    for ns_entry in ns_entries.flatten() {
                        let ns_path = ns_entry.path();
                        if !ns_path.is_dir() {
                            continue;
                        }
                        let namespace = ns_entry.file_name().to_string_lossy().to_string();

                        // Load recipes
                        for recipe_sub in ["recipe", "recipes"] {
                            let recipe_dir = ns_path.join(recipe_sub);
                            if recipe_dir.is_dir() {
                                load_recipes_from_dir(
                                    &namespace,
                                    &recipe_dir,
                                    &mut all_recipes,
                                    &mut pack_recipe_count,
                                    &mut pack_recipe_files_seen,
                                );
                            }
                        }

                        // Load functions
                        for fn_sub in ["function", "functions"] {
                            let fn_dir = ns_path.join(fn_sub);
                            if fn_dir.is_dir() {
                                let before = all_functions.len();
                                function_loader::load_functions_from_dir(
                                    &namespace,
                                    &fn_dir,
                                    &mut all_functions,
                                );
                                pack_function_count += all_functions.len() - before;
                            }
                        }

                        // Load tags
                        let tags_dir = ns_path.join("tags");
                        if tags_dir.is_dir() {
                            function_loader::load_function_tags_from_dir(
                                &namespace,
                                &tags_dir,
                                &mut all_function_tags,
                            );
                        }
                    }
                }

                info!(
                    "Loaded datapack '{file_name}': {pack_recipe_count} recipe(s), {pack_function_count} function(s)"
                );
                report_skipped_recipes(&file_name, pack_recipe_files_seen, pack_recipe_count);

                loaded_packs_vec.push(LoadedDatapack {
                    id: pack_id,
                    name: file_name,
                    description,
                    pack_format,
                    root_path: pack_path,
                    recipe_count: pack_recipe_count,
                    function_count: pack_function_count,
                });
            }
        }

        recipe_manager.set_recipes(all_recipes);
        load_dynamic_content(&datapacks_dir);
        *self
            .loaded_packs
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = loaded_packs_vec;
        *self
            .functions
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_functions;
        *self
            .function_tags
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_function_tags;
    }

    pub fn get_loaded_packs(&self) -> Vec<LoadedDatapack> {
        self.loaded_packs
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .clone()
    }

    pub fn get_functions(&self) -> HashMap<String, Vec<String>> {
        self.functions
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .clone()
    }

    pub fn get_function_names(&self) -> Vec<String> {
        let fns = self
            .functions
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let tags = self
            .function_tags
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut names = Vec::with_capacity(fns.len() + tags.len());
        names.extend(fns.keys().cloned());
        for tag in tags.keys() {
            names.push(format!("#{tag}"));
        }
        names
    }

    pub fn execute_function(
        &self,
        server: &Arc<Server>,
        source: &CommandSource,
        name: &str,
    ) -> Result<usize, String> {
        let (functions_to_run, is_tag) = if let Some(tag_name) = name.strip_prefix('#') {
            let tags = self
                .function_tags
                .read()
                .unwrap_or_else(std::sync::PoisonError::into_inner);
            let Some(fns) = tags.get(tag_name) else {
                return Err(format!("Unknown function tag: #{tag_name}"));
            };
            (fns.clone(), true)
        } else {
            (vec![name.to_string()], false)
        };

        let all_fns = self
            .functions
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut total_executed = 0;

        for fn_id in functions_to_run {
            let Some(lines) = all_fns.get(&fn_id) else {
                if !is_tag {
                    return Err(format!("Unknown function: {fn_id}"));
                }
                continue;
            };

            for line in lines {
                server
                    .command_dispatcher
                    .load()
                    .handle_command(source, line);
                total_executed += 1;
            }
        }

        Ok(total_executed)
    }

    pub fn get_all_known_packs(server: &Server) -> Vec<String> {
        let mut packs = Vec::new();
        packs.push("vanilla".to_string());

        // Bundled feature packs
        for bundled in [
            "trade_rebalance",
            "minecart_improvements",
            "redstone_experiments",
        ] {
            if !packs.iter().any(|p| p == bundled) {
                packs.push(bundled.to_string());
            }
        }

        // World datapacks directory
        let datapacks_dir = server.basic_config.get_world_path().join("datapacks");
        if let Ok(entries) = fs::read_dir(datapacks_dir) {
            for entry in entries.flatten() {
                let path = entry.path();
                let file_name = entry.file_name().to_string_lossy().to_string();
                if file_name.starts_with('.') {
                    continue;
                }
                if path.is_dir()
                    || path
                        .extension()
                        .is_some_and(|ext| ext.eq_ignore_ascii_case("zip"))
                {
                    let pack_name = format!("file/{file_name}");
                    if !packs.iter().any(|p| p == &pack_name) {
                        packs.push(pack_name);
                    }
                }
            }
        }

        let level_info = server.level_info.load();
        for pack in &level_info.data_packs.enabled {
            if !packs.iter().any(|p| p == pack) {
                packs.push(pack.clone());
            }
        }
        for pack in &level_info.data_packs.disabled {
            if !packs.iter().any(|p| p == pack) {
                packs.push(pack.clone());
            }
        }

        packs
    }

    pub fn get_enabled_packs(server: &Server) -> Vec<String> {
        server.level_info.load().data_packs.enabled.clone()
    }

    pub fn get_available_packs(server: &Server) -> Vec<String> {
        let enabled = Self::get_enabled_packs(server);
        let all = Self::get_all_known_packs(server);
        all.into_iter().filter(|p| !enabled.contains(p)).collect()
    }

    pub fn find_pack_name(server: &Server, input: &str) -> Option<String> {
        let known = Self::get_all_known_packs(server);
        if let Some(p) = known.iter().find(|p| *p == input) {
            return Some(p.clone());
        }
        let file_input = format!("file/{input}");
        if let Some(p) = known.iter().find(|p| **p == file_input) {
            return Some(p.clone());
        }
        if let Some(p) = known
            .iter()
            .find(|p| p.strip_prefix("file/") == Some(input))
        {
            return Some(p.clone());
        }
        None
    }

    pub fn get_pack_info(server: &Server, name_or_id: &str) -> Option<DatapackInfo> {
        let resolved_name = Self::find_pack_name(server, name_or_id)?;
        let enabled_packs = Self::get_enabled_packs(server);
        let is_enabled = enabled_packs.contains(&resolved_name);

        let loaded = server.datapack_manager.get_loaded_packs();
        if let Some(pack) = loaded
            .iter()
            .find(|p| p.id == resolved_name || p.name == resolved_name)
        {
            return Some(DatapackInfo {
                id: pack.id.clone(),
                name: pack.name.clone(),
                description: pack.description.clone(),
                pack_format: pack.pack_format,
                is_enabled,
                recipe_count: pack.recipe_count,
                function_count: pack.function_count,
            });
        }

        let (id, name, description, pack_format) = if resolved_name == "vanilla" {
            (
                "vanilla".to_string(),
                "vanilla".to_string(),
                "The default data pack".to_string(),
                61,
            )
        } else if let Some(stripped) = resolved_name.strip_prefix("file/") {
            let pack_path = server
                .basic_config
                .get_world_path()
                .join("datapacks")
                .join(stripped);
            let (desc, format) = read_pack_mcmeta(&pack_path);
            (resolved_name.clone(), stripped.to_string(), desc, format)
        } else {
            (
                resolved_name.clone(),
                resolved_name.clone(),
                format!("Bundled datapack: {resolved_name}"),
                61,
            )
        };

        Some(DatapackInfo {
            id,
            name,
            description,
            pack_format,
            is_enabled,
            recipe_count: 0,
            function_count: 0,
        })
    }

    pub fn list_all_packs(server: &Server) -> Vec<DatapackInfo> {
        let all = Self::get_all_known_packs(server);
        all.into_iter()
            .filter_map(|p| Self::get_pack_info(server, &p))
            .collect()
    }

    pub fn list_enabled_packs(server: &Server) -> Vec<DatapackInfo> {
        let enabled = Self::get_enabled_packs(server);
        enabled
            .into_iter()
            .filter_map(|p| Self::get_pack_info(server, &p))
            .collect()
    }

    pub fn list_available_packs(server: &Server) -> Vec<DatapackInfo> {
        let available = Self::get_available_packs(server);
        available
            .into_iter()
            .filter_map(|p| Self::get_pack_info(server, &p))
            .collect()
    }

    pub fn is_pack_enabled(server: &Server, name: &str) -> bool {
        let Some(resolved) = Self::find_pack_name(server, name) else {
            return false;
        };
        Self::get_enabled_packs(server).contains(&resolved)
    }

    pub fn enable_pack(
        server: &Arc<Server>,
        name: &str,
        position: DatapackEnablePosition,
    ) -> Result<(), String> {
        let Some(resolved_name) = Self::find_pack_name(server, name) else {
            return Err(format!("Unknown datapack '{name}'"));
        };

        let enabled = Self::get_enabled_packs(server);
        if enabled.contains(&resolved_name) {
            return Err(format!("Datapack '{resolved_name}' is already enabled"));
        }

        let target = resolved_name;
        match position {
            DatapackEnablePosition::First => {
                server.level_info.rcu(|level_info| {
                    let mut new_info = (**level_info).clone();
                    new_info.data_packs.disabled.retain(|p| p != &target);
                    new_info.data_packs.enabled.retain(|p| p != &target);
                    new_info.data_packs.enabled.insert(0, target.clone());
                    new_info
                });
            }
            DatapackEnablePosition::Last => {
                server.level_info.rcu(|level_info| {
                    let mut new_info = (**level_info).clone();
                    new_info.data_packs.disabled.retain(|p| p != &target);
                    new_info.data_packs.enabled.retain(|p| p != &target);
                    new_info.data_packs.enabled.push(target.clone());
                    new_info
                });
            }
            DatapackEnablePosition::Before(existing_name) => {
                let Some(existing_pack) = Self::find_pack_name(server, &existing_name) else {
                    return Err(format!("Unknown existing datapack '{existing_name}'"));
                };
                if !enabled.contains(&existing_pack) {
                    return Err(format!("Datapack '{existing_pack}' is not enabled"));
                }
                server.level_info.rcu(|level_info| {
                    let mut new_info = (**level_info).clone();
                    new_info.data_packs.disabled.retain(|p| p != &target);
                    new_info.data_packs.enabled.retain(|p| p != &target);
                    if let Some(idx) = new_info
                        .data_packs
                        .enabled
                        .iter()
                        .position(|p| p == &existing_pack)
                    {
                        new_info.data_packs.enabled.insert(idx, target.clone());
                    } else {
                        new_info.data_packs.enabled.push(target.clone());
                    }
                    new_info
                });
            }
            DatapackEnablePosition::After(existing_name) => {
                let Some(existing_pack) = Self::find_pack_name(server, &existing_name) else {
                    return Err(format!("Unknown existing datapack '{existing_name}'"));
                };
                if !enabled.contains(&existing_pack) {
                    return Err(format!("Datapack '{existing_pack}' is not enabled"));
                }
                server.level_info.rcu(|level_info| {
                    let mut new_info = (**level_info).clone();
                    new_info.data_packs.disabled.retain(|p| p != &target);
                    new_info.data_packs.enabled.retain(|p| p != &target);
                    if let Some(idx) = new_info
                        .data_packs
                        .enabled
                        .iter()
                        .position(|p| p == &existing_pack)
                    {
                        new_info.data_packs.enabled.insert(idx + 1, target.clone());
                    } else {
                        new_info.data_packs.enabled.push(target.clone());
                    }
                    new_info
                });
            }
        }

        if let Err(err) = server.save_world_info() {
            tracing::error!("Failed to save world info: {err}");
        }

        server.reload_datapacks(server);
        Ok(())
    }

    pub fn disable_pack(server: &Arc<Server>, name: &str) -> Result<(), String> {
        let Some(target_pack) = Self::find_pack_name(server, name) else {
            return Err(format!("Unknown datapack '{name}'"));
        };

        let enabled = Self::get_enabled_packs(server);
        if !enabled.contains(&target_pack) {
            return Err(format!("Datapack '{target_pack}' is not enabled"));
        }

        if target_pack == "vanilla" {
            return Err("Cannot disable the default vanilla datapack".to_string());
        }

        let target = target_pack;
        server.level_info.rcu(|level_info| {
            let mut new_info = (**level_info).clone();
            new_info.data_packs.enabled.retain(|p| p != &target);
            if !new_info.data_packs.disabled.contains(&target) {
                new_info.data_packs.disabled.push(target.clone());
            }
            new_info
        });

        if let Err(err) = server.save_world_info() {
            tracing::error!("Failed to save world info: {err}");
        }

        server.reload_datapacks(server);
        Ok(())
    }

    pub fn reload(server: &Arc<Server>) -> Result<(), String> {
        server.reload_datapacks(server);
        Ok(())
    }

    pub fn execute_function_from_console(
        server: &Arc<Server>,
        name: &str,
    ) -> Result<usize, String> {
        let source = crate::command::CommandSender::Console.into_source(server);
        server
            .datapack_manager
            .execute_function(server, &source, name)
    }
}

pub fn read_pack_mcmeta(pack_path: &Path) -> (String, u32) {
    let mcmeta_path = pack_path.join("pack.mcmeta");
    if let Ok(content) = fs::read_to_string(mcmeta_path)
        && let Ok(val) = serde_json::from_str::<serde_json::Value>(&content)
    {
        let pack = val.get("pack");
        let description = pack
            .and_then(|p| p.get("description"))
            .map(|d| {
                d.as_str()
                    .map_or_else(|| d.to_string(), ToString::to_string)
            })
            .unwrap_or_default();
        let pack_format = pack
            .and_then(|p| p.get("pack_format"))
            .and_then(serde_json::Value::as_u64)
            .unwrap_or(61) as u32;
        return (description, pack_format);
    }
    (String::new(), 61)
}

/// Reads mod ore features out of the extracted `mod_*` datapacks and installs them.
///
/// The trail is the one `NeoForge` walks: a biome modifier JSON names a placed feature and
/// the biomes it belongs in; the placed feature names its configured feature. Only
/// `minecraft:ore`-shaped features are expressible -- see
/// [`dynamic_features`](pumpkin_world::generation::feature::dynamic_features) -- and each
/// one that is not gets its reason said once instead of being half-placed.
/// The dynamic content a datapack load refreshes beyond recipes: worldgen and item tags.
pub(crate) fn load_dynamic_content(datapacks_dir: &Path) {
    load_dynamic_worldgen(datapacks_dir);
    load_dynamic_item_tags(datapacks_dir);
}

/// Reads item tag JSONs out of the extracted `mod_*` datapacks and installs them.
///
/// Nested directories become tag paths (`tags/item/gems/super.json` is
/// `<ns>:gems/super`), matching the datapack convention, and entries are stored as
/// written -- `#references` resolve at query time in the dynamic store.
pub(crate) fn load_dynamic_item_tags(datapacks_dir: &Path) {
    let mut tags: std::collections::HashMap<String, Vec<String>> = std::collections::HashMap::new();
    let Ok(entries) = fs::read_dir(datapacks_dir) else {
        pumpkin_data::dynamic::install_item_tags(tags);
        return;
    };
    for entry in entries.flatten() {
        if !entry.file_name().to_string_lossy().starts_with("mod_") {
            continue;
        }
        let Ok(namespaces) = fs::read_dir(entry.path().join("data")) else {
            continue;
        };
        for namespace_entry in namespaces.flatten() {
            let namespace = namespace_entry.file_name().to_string_lossy().to_string();
            let tag_root = namespace_entry.path().join("tags").join("item");
            if !tag_root.is_dir() {
                continue;
            }
            collect_tag_files(&tag_root, &tag_root, &namespace, &mut tags);
        }
    }
    let count = tags.len();
    if count > 0 {
        info!("Mod item tags: {count} tag(s) loaded");
    }
    pumpkin_data::dynamic::install_item_tags(tags);
}

fn collect_tag_files(
    root: &Path,
    dir: &Path,
    namespace: &str,
    tags: &mut std::collections::HashMap<String, Vec<String>>,
) {
    let Ok(entries) = fs::read_dir(dir) else {
        return;
    };
    for entry in entries.flatten() {
        let path = entry.path();
        if path.is_dir() {
            collect_tag_files(root, &path, namespace, tags);
            continue;
        }
        if path.extension().is_none_or(|ext| ext != "json") {
            continue;
        }
        let Ok(text) = fs::read_to_string(&path) else {
            continue;
        };
        let Ok(json) = serde_json::from_str::<serde_json::Value>(&text) else {
            continue;
        };
        let Some(values) = json.get("values").and_then(serde_json::Value::as_array) else {
            continue;
        };
        let relative = path
            .strip_prefix(root)
            .unwrap_or(&path)
            .with_extension("")
            .to_string_lossy()
            .replace('\\', "/");
        let name = format!("{namespace}:{relative}");
        // Several packs may add to one tag ("replace": false is the convention mods use);
        // appending honours that, and nothing this loader serves uses replace: true.
        let list = tags.entry(name).or_default();
        for value in values {
            let entry = value
                .get("id")
                .and_then(serde_json::Value::as_str)
                .or_else(|| value.as_str());
            if let Some(entry) = entry {
                list.push(entry.to_string());
            }
        }
    }
}

pub(crate) fn load_dynamic_worldgen(datapacks_dir: &Path) {
    use pumpkin_world::generation::feature::dynamic_features;

    // Until the registries are published a mod's ore block cannot be resolved, so this
    // pass refuses everything -- and its refusals mean "not yet", not "no". The run
    // after freeze is the one with the real answer, so only that run reports. Saying it
    // both times printed three failures and a count of zero for ores that do generate.
    let published = pumpkin_data::dynamic::is_frozen();
    let mut features = Vec::new();
    let mut refused = 0usize;

    let Ok(entries) = fs::read_dir(datapacks_dir) else {
        dynamic_features::install_dynamic_features(features);
        return;
    };
    for entry in entries.flatten() {
        if !entry.file_name().to_string_lossy().starts_with("mod_") {
            continue;
        }
        let data_dir = entry.path().join("data");
        let Ok(namespaces) = fs::read_dir(&data_dir) else {
            continue;
        };
        for namespace_entry in namespaces.flatten() {
            let namespace = namespace_entry.file_name().to_string_lossy().to_string();
            let modifiers_dir = namespace_entry
                .path()
                .join("neoforge")
                .join("biome_modifier");
            let Ok(modifiers) = fs::read_dir(&modifiers_dir) else {
                continue;
            };
            for modifier_entry in modifiers.flatten() {
                let Ok(modifier_json) = fs::read_to_string(modifier_entry.path()) else {
                    continue;
                };
                let Ok(modifier) = serde_json::from_str::<serde_json::Value>(&modifier_json) else {
                    continue;
                };
                // The modifier's own type is a codec in the mod's Java half; what this
                // host can honour is the shape every add-features modifier shares: which
                // feature, in which biomes.
                let Some(feature_ref) = modifier.get("feature").and_then(|v| v.as_str()) else {
                    continue;
                };
                let Some(biomes) = modifier.get("biomes").and_then(|v| v.as_str()) else {
                    continue;
                };

                let (feature_ns, feature_path) = feature_ref
                    .split_once(':')
                    .unwrap_or((&namespace, feature_ref));
                let worldgen = data_dir.join(feature_ns).join("worldgen");
                let placed_path = worldgen
                    .join("placed_feature")
                    .join(format!("{feature_path}.json"));
                let Ok(placed_json) = fs::read_to_string(&placed_path) else {
                    if published {
                        warn!(
                            "{feature_ref}: biome modifier names it, but {placed_path:?} is missing"
                        );
                    }
                    refused += 1;
                    continue;
                };
                // The placed feature names its configured feature; by convention they
                // share a path, and the reference inside the JSON is authoritative.
                let configured_ref = serde_json::from_str::<serde_json::Value>(&placed_json)
                    .ok()
                    .and_then(|v| v.get("feature").and_then(|f| f.as_str().map(String::from)))
                    .unwrap_or_else(|| feature_ref.to_string());
                let (configured_ns, configured_path) = configured_ref.split_once(':').map_or_else(
                    || (namespace.clone(), configured_ref.clone()),
                    |(a, b)| (a.to_string(), b.to_string()),
                );
                let configured_file = data_dir
                    .join(&configured_ns)
                    .join("worldgen")
                    .join("configured_feature")
                    .join(format!("{configured_path}.json"));
                let Ok(configured_json) = fs::read_to_string(&configured_file) else {
                    if published {
                        warn!("{feature_ref}: its configured feature {configured_ref} is missing");
                    }
                    refused += 1;
                    continue;
                };

                match dynamic_features::parse_dynamic_ore(
                    feature_ref,
                    &placed_json,
                    &configured_json,
                    biomes,
                ) {
                    Ok(feature) => features.push(feature),
                    Err(reason) => {
                        if published {
                            warn!("{feature_ref}: not placeable by this server: {reason}");
                        }
                        refused += 1;
                    }
                }
            }
        }
    }

    if published && (!features.is_empty() || refused > 0) {
        info!(
            "Mod worldgen: {} ore feature(s) will generate in new chunks; {refused} refused",
            features.len()
        );
    }
    dynamic_features::install_dynamic_features(features);
}

/// Says how many of a pack's recipe files did not load.
///
/// A skipped file is a recipe type the server cannot craft -- a mod machine's input, or a
/// format this loader does not parse. Said out loud so "487 loaded" is never mistaken for
/// "all of them".
fn report_skipped_recipes(file_name: &str, seen: usize, loaded: usize) {
    let skipped = seen.saturating_sub(loaded);
    if skipped > 0 {
        info!(
            "Datapack '{file_name}': {skipped} recipe file(s) skipped (recipe types the \
             server cannot craft, e.g. mod machines)"
        );
    }
}

/// Whether a pack should load: enabled in the level data, or a live mod's data.
///
/// A `mod_*` pack is the data a loaded mod jar carries; the mod being loaded is what
/// enables it, the same way `NeoForge` treats mod data. The marker the extractor writes
/// names the jar, so a pack whose mod has been removed is skipped loudly instead of
/// serving stale content forever.
fn pack_is_loadable(file_name: &str, pack_path: &Path, enabled_packs: &[String]) -> bool {
    if file_name.starts_with("mod_") {
        if mod_pack_jar_is_gone(pack_path) {
            warn!(
                "skipping datapack {file_name}: its mod jar is gone; delete {} to silence \
                 this",
                pack_path.display()
            );
            return false;
        }
        return true;
    }
    let pack_id = format!("file/{file_name}");
    enabled_packs
        .iter()
        .any(|p| p == &pack_id || p == file_name)
}

/// Whether a `mod_*` datapack's source jar has been removed.
///
/// The extractor's marker records the jar's path on its second line. A pack without a
/// readable marker is kept: better to serve data that might be stale than to silently
/// drop a pack over a missing bookkeeping file.
fn mod_pack_jar_is_gone(pack_path: &Path) -> bool {
    let Ok(marker) = fs::read_to_string(pack_path.join(".jar-modified-time")) else {
        return false;
    };
    let Some(jar) = marker.lines().nth(1) else {
        return false;
    };
    !Path::new(jar).exists()
}

fn load_recipes_from_dir(
    namespace: &str,
    dir: &Path,
    all_recipes: &mut Vec<DynamicRecipe>,
    count: &mut usize,
    seen: &mut usize,
) {
    let Ok(entries) = fs::read_dir(dir) else {
        return;
    };
    for entry in entries.flatten() {
        let path = entry.path();
        if path.is_dir() {
            load_recipes_from_dir(namespace, &path, all_recipes, count, seen);
        } else if path
            .extension()
            .is_some_and(|ext| ext.eq_ignore_ascii_case("json"))
        {
            *seen += 1;
            let stem = path
                .file_stem()
                .map(|s| s.to_string_lossy().to_string())
                .unwrap_or_default();
            if let Ok(content) = fs::read_to_string(&path)
                && let Some(recipe) = recipe_loader::parse_recipe(namespace, &stem, &content)
            {
                all_recipes.push(recipe);
                *count += 1;
            }
        }
    }
}

#[cfg(test)]
mod mod_pack_tests {
    use super::mod_pack_jar_is_gone;
    use std::fs;

    #[test]
    fn a_pack_whose_marker_names_a_missing_jar_is_flagged() {
        let dir =
            std::env::temp_dir().join(format!("pumpkin-mod-pack-test-{}", std::process::id()));
        fs::create_dir_all(&dir).unwrap();

        // No marker at all: kept, not flagged.
        assert!(!mod_pack_jar_is_gone(&dir));

        // Marker naming a jar that exists: kept.
        let jar = dir.join("present.jar");
        fs::write(&jar, b"jar").unwrap();
        fs::write(
            dir.join(".jar-modified-time"),
            format!("12345\n{}\n", jar.display()),
        )
        .unwrap();
        assert!(!mod_pack_jar_is_gone(&dir));

        // Marker naming a jar that is gone: flagged.
        fs::write(
            dir.join(".jar-modified-time"),
            format!("12345\n{}\n", dir.join("removed.jar").display()),
        )
        .unwrap();
        assert!(mod_pack_jar_is_gone(&dir));

        fs::remove_dir_all(&dir).unwrap();
    }
}
