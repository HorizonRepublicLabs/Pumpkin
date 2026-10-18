pub mod context_provider_loader;
pub mod damage_type_loader;
pub mod dynamic_registry_loader;
pub mod function_loader;
pub mod loot_table_loader;
pub mod recipe_loader;
pub mod structure_loader;
pub mod test_loader;
pub mod trade_loader;

use std::collections::HashMap;
use std::fs;
use std::path::{Path, PathBuf};
use std::sync::{Arc, RwLock};
use tracing::{info, warn};

use pumpkin_data::damage::DamageType;
use pumpkin_data::registry::RegistryEntryData;
use pumpkin_nbt::tag::NbtTag;
use pumpkin_nbt::{NbtCompound, nbt_compress::read_gzip_compound_tag};
use pumpkin_protocol::codec::recipe::DynamicRecipe;
use pumpkin_util::loot_table::DynamicLootTable;

use crate::command::context::command_source::CommandSource;
use crate::server::Server;
use crate::server::recipe::RecipeManager;
use crate::world::loot::LootTableHandle;

use self::context_provider_loader::ContextProviderRegistry;
use self::damage_type_loader::{DamageTypeDefinition, DamageTypeEntry, DamageTypeRegistry};
use self::test_loader::{
    TestInstance, TestInstanceRegistry, load_test_instances_from_dir, to_registry_entry,
};
use self::trade_loader::{DynamicVillagerTradeSet, TradeRegistry};

#[derive(Clone, Debug, PartialEq, Eq)]
pub struct KnownPackData {
    pub namespace: String,
    pub id: String,
    pub version: String,
}

#[derive(Clone, Debug)]
pub struct LoadedDatapack {
    pub id: String,
    pub name: String,
    pub description: String,
    pub pack_format: u32,
    pub root_path: PathBuf,
    pub recipe_count: usize,
    pub function_count: usize,
    pub known_packs: Vec<KnownPackData>,
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
    functions: RwLock<HashMap<String, Arc<[String]>>>,
    function_tags: RwLock<HashMap<String, Vec<String>>>,
    test_instances: RwLock<TestInstanceRegistry>,
    context_int_providers: RwLock<ContextProviderRegistry>,
    context_float_providers: RwLock<ContextProviderRegistry>,
    damage_types: RwLock<DamageTypeRegistry>,
    loot_tables: RwLock<HashMap<String, Arc<DynamicLootTable>>>,
    dynamic_registries: RwLock<HashMap<String, HashMap<String, RegistryEntryData>>>,
    trade_registry: RwLock<TradeRegistry>,
}

fn share_function_bodies(
    functions: HashMap<String, Vec<String>>,
) -> HashMap<String, Arc<[String]>> {
    functions
        .into_iter()
        .map(|(name, lines)| (name, lines.into()))
        .collect()
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
            test_instances: RwLock::new(HashMap::new()),
            context_int_providers: RwLock::new(HashMap::new()),
            context_float_providers: RwLock::new(HashMap::new()),
            damage_types: RwLock::new(HashMap::new()),
            loot_tables: RwLock::new(HashMap::new()),
            dynamic_registries: RwLock::new(HashMap::new()),
            trade_registry: RwLock::new(TradeRegistry::new()),
        }
    }

    pub fn load_all(
        &self,
        world_path: &Path,
        enabled_packs: &[String],
        recipe_manager: &RecipeManager,
    ) {
        let datapacks_dir = world_path.join("datapacks");
        structure_loader::clear_dynamic_worldgen_data();
        let mut loaded_packs_vec = Vec::new();
        let mut all_recipes: Vec<DynamicRecipe> = Vec::new();
        let mut all_functions: HashMap<String, Vec<String>> = HashMap::new();
        let mut all_function_tags: HashMap<String, Vec<String>> = HashMap::new();
        let mut all_test_instances: TestInstanceRegistry = HashMap::new();
        let mut all_context_int_providers: ContextProviderRegistry = HashMap::new();
        let mut all_context_float_providers: ContextProviderRegistry = HashMap::new();
        let mut all_damage_type_defs: HashMap<String, DamageTypeDefinition> = HashMap::new();
        let mut all_loot_tables: HashMap<String, Arc<DynamicLootTable>> = HashMap::new();

        // Embedded test instances are compile-time constants and always load,
        // so on-disk packs can override them by id.
        let embedded_count = test_loader::load_embedded_test_instances(&mut all_test_instances);
        if embedded_count > 0 {
            info!("Loaded {embedded_count} embedded test instance(s)");
        }
        let (embedded_ints, embedded_floats) =
            context_provider_loader::load_embedded_context_providers(
                &mut all_context_int_providers,
                &mut all_context_float_providers,
            );
        if embedded_ints > 0 || embedded_floats > 0 {
            info!(
                "Loaded {embedded_ints} int and {embedded_floats} float embedded context provider(s)"
            );
        }
        let mut all_dynamic_registries: HashMap<String, HashMap<String, RegistryEntryData>> =
            HashMap::new();
        let mut all_trade_registry = TradeRegistry::new();

        let mut acc = PackContentAccumulators {
            recipes: &mut all_recipes,
            functions: &mut all_functions,
            function_tags: &mut all_function_tags,
            test_instances: &mut all_test_instances,
            damage_types: &mut all_damage_type_defs,
            context_int_providers: &mut all_context_int_providers,
            context_float_providers: &mut all_context_float_providers,
            loot_tables: &mut all_loot_tables,
            dynamic_registries: &mut all_dynamic_registries,
            trade_registry: &mut all_trade_registry,
        };

        scan_datapacks_dir(
            &datapacks_dir,
            enabled_packs,
            &mut acc,
            &mut loaded_packs_vec,
        );

        let damage_type_registry = build_damage_type_registry(all_damage_type_defs);

        recipe_manager.set_recipes(all_recipes);
        load_dynamic_content(&datapacks_dir);
        *self
            .loaded_packs
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = loaded_packs_vec;
        let all_functions = share_function_bodies(all_functions);
        *self
            .functions
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_functions;
        *self
            .function_tags
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_function_tags;
        *self
            .test_instances
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_test_instances;
        *self
            .context_int_providers
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_context_int_providers;
        *self
            .context_float_providers
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_context_float_providers;
        *self
            .damage_types
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = damage_type_registry;
        *self
            .loot_tables
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_loot_tables;
        *self
            .dynamic_registries
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_dynamic_registries;
        *self
            .trade_registry
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner) = all_trade_registry;
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
            .iter()
            .map(|(name, lines)| (name.clone(), lines.to_vec()))
            .collect()
    }

    pub fn get_test_instance(&self, name: &str) -> Option<TestInstance> {
        self.test_instances
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .get(name)
            .cloned()
    }

    pub fn get_test_instance_names(&self) -> Vec<String> {
        let test_instances = self
            .test_instances
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut names: Vec<_> = test_instances.keys().cloned().collect();
        names.sort_unstable();
        names
    }

    #[must_use]
    pub fn get_context_int_provider(&self, id: &str) -> Option<NbtTag> {
        self.context_int_providers
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .get(id)
            .cloned()
    }

    #[must_use]
    pub fn get_context_float_provider(&self, id: &str) -> Option<NbtTag> {
        self.context_float_providers
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .get(id)
            .cloned()
    }

    #[must_use]
    pub fn get_context_int_provider_names(&self) -> Vec<String> {
        let providers = self
            .context_int_providers
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut names: Vec<_> = providers.keys().cloned().collect();
        names.sort_unstable();
        names
    }

    #[must_use]
    pub fn get_context_float_provider_names(&self) -> Vec<String> {
        let providers = self
            .context_float_providers
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut names: Vec<_> = providers.keys().cloned().collect();
        names.sort_unstable();
        names
    }

    pub fn insert_context_int_provider(&self, id: String, tag: NbtTag) {
        self.context_int_providers
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .insert(id, tag);
    }

    pub fn insert_context_float_provider(&self, id: String, tag: NbtTag) {
        self.context_float_providers
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .insert(id, tag);
    }

    #[must_use]
    pub fn get_loot_table(&self, key: &str) -> Option<LootTableHandle> {
        let full_key = if key.contains(':') {
            key.to_string()
        } else {
            format!("minecraft:{key}")
        };

        let guard = self
            .loot_tables
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);

        if let Some(table) = guard.get(&full_key).or_else(|| guard.get(key)) {
            return Some(LootTableHandle::Dynamic(table.clone()));
        }

        pumpkin_data::loot_table::get_loot_table(key)
            .or_else(|| pumpkin_data::loot_table::get_loot_table(&full_key))
            .map(LootTableHandle::Static)
    }

    #[must_use]
    pub fn get_loot_table_names(&self) -> Vec<String> {
        let guard = self
            .loot_tables
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut names: Vec<_> = guard.keys().cloned().collect();
        names.sort_unstable();
        names
    }

    pub fn insert_loot_table(&self, key: String, table: Arc<DynamicLootTable>) {
        self.loot_tables
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .insert(key, table);
    }

    #[must_use]
    pub fn get_damage_type(&self, id: &str) -> Option<DamageType> {
        let guard = self
            .damage_types
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let full_id = if id.contains(':') {
            id.to_string()
        } else {
            format!("minecraft:{id}")
        };
        guard
            .get(&full_id)
            .or_else(|| guard.get(id))
            .map(|e| e.damage_type)
    }

    #[must_use]
    pub fn get_damage_type_names(&self) -> Vec<String> {
        let guard = self
            .damage_types
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut names: Vec<_> = guard.keys().cloned().collect();
        names.sort_unstable();
        names
    }

    #[must_use]
    pub fn get_damage_type_registry_map(&self) -> HashMap<String, DamageTypeEntry> {
        self.damage_types
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .clone()
    }

    #[must_use]
    pub fn get_all_damage_type_registry_entries(&self) -> Vec<RegistryEntryData> {
        let guard = self
            .damage_types
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut entries: Vec<RegistryEntryData> = guard
            .values()
            .map(DamageTypeEntry::to_registry_entry)
            .collect();
        entries.sort_by(|a, b| a.entry_id.cmp(&b.entry_id));
        entries
    }

    #[must_use]
    pub fn merge_damage_type_entries(
        &self,
        vanilla_entries: &[RegistryEntryData],
    ) -> Vec<RegistryEntryData> {
        let guard = self
            .damage_types
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        merge_damage_type_entries(vanilla_entries, &guard)
    }

    #[must_use]
    pub fn merge_registry_entries(
        &self,
        registry_id: &str,
        vanilla_entries: &[RegistryEntryData],
    ) -> Vec<RegistryEntryData> {
        let guard = self
            .dynamic_registries
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let custom = guard
            .get(registry_id)
            .or_else(|| guard.get(&format!("minecraft:{registry_id}")))
            .or_else(|| {
                registry_id
                    .strip_prefix("minecraft:")
                    .and_then(|r| guard.get(r))
            });
        custom.map_or_else(
            || vanilla_entries.iter().map(clone_registry_entry).collect(),
            |custom| {
                dynamic_registry_loader::merge_dynamic_registry_entries(vanilla_entries, custom)
            },
        )
    }

    #[must_use]
    pub fn get_custom_registry_entry(
        &self,
        registry_id: &str,
        entry_id: &str,
    ) -> Option<RegistryEntryData> {
        let guard = self
            .dynamic_registries
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let custom_map = guard
            .get(registry_id)
            .or_else(|| guard.get(&format!("minecraft:{registry_id}")))
            .or_else(|| {
                registry_id
                    .strip_prefix("minecraft:")
                    .and_then(|r| guard.get(r))
            })?;
        custom_map.get(entry_id).map(clone_registry_entry)
    }

    #[must_use]
    pub fn get_custom_registry_entries(&self, registry_id: &str) -> Vec<RegistryEntryData> {
        let guard = self
            .dynamic_registries
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let custom_map = guard
            .get(registry_id)
            .or_else(|| guard.get(&format!("minecraft:{registry_id}")))
            .or_else(|| {
                registry_id
                    .strip_prefix("minecraft:")
                    .and_then(|r| guard.get(r))
            });
        custom_map.map_or_else(Vec::new, |entries| {
            let mut list: Vec<_> = entries.values().map(clone_registry_entry).collect();
            list.sort_by(|a, b| a.entry_id.cmp(&b.entry_id));
            list
        })
    }

    #[must_use]
    pub fn get_dynamic_registry_ids(&self) -> Vec<String> {
        let guard = self
            .dynamic_registries
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut ids: Vec<_> = guard.keys().cloned().collect();
        ids.sort();
        ids
    }

    #[must_use]
    pub fn get_villager_trade_set(
        &self,
        profession: &str,
        level: i32,
    ) -> Option<DynamicVillagerTradeSet> {
        let registry = self
            .trade_registry
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        registry.get_villager_trade_set(profession, level)
    }

    #[must_use]
    pub fn get_wandering_trader_trade_set(&self, tier: &str) -> Option<DynamicVillagerTradeSet> {
        let registry = self
            .trade_registry
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        registry.get_wandering_trader_trade_set(tier)
    }

    #[must_use]
    pub fn get_trade_set(&self, key: &str) -> Option<DynamicVillagerTradeSet> {
        let registry = self
            .trade_registry
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        registry.resolve_trade_set(key)
    }

    /// Returns datapack test instances in the protocol's synced-registry entry format.
    /// The vanilla Test Instance Block renderer resolves required/padding/base rotation
    /// through this registry using the controller's `data.test` resource key.
    pub fn get_test_instance_registry_entries(&self) -> Vec<RegistryEntryData> {
        let test_instances = self
            .test_instances
            .read()
            .unwrap_or_else(std::sync::PoisonError::into_inner);
        let mut entries: Vec<_> = test_instances
            .iter()
            .map(|(id, instance)| to_registry_entry(id.clone(), instance))
            .collect();
        entries.sort_unstable_by(|left, right| left.entry_id.cmp(&right.entry_id));
        entries
    }

    /// Returns the list of known packs for client configuration / pack synchronization,
    /// matching vanilla's `server.getResourceManager().listPacks().flatMap(p -> p.location().knownPackInfo().stream())`.
    pub fn get_known_packs<'a>(
        &self,
        server: &Server,
        server_version: &'a str,
        loaded_packs: &'a [LoadedDatapack],
    ) -> Vec<pumpkin_protocol::KnownPack<'a>> {
        use pumpkin_protocol::KnownPack;

        let mut known_packs = Vec::new();

        // 1. Primary vanilla core pack
        known_packs.push(KnownPack {
            namespace: "minecraft",
            id: "core",
            version: server_version,
        });

        // 2. Built-in feature packs that are enabled
        let enabled_packs = Self::get_enabled_packs(server);
        for pack_name in &enabled_packs {
            let id: &'static str = match pack_name.as_str() {
                "trade_rebalance" => "trade_rebalance",
                "minecart_improvements" => "minecart_improvements",
                "redstone_experiments" => "redstone_experiments",
                "bundle" => "bundle",
                _ => continue,
            };
            let pack = KnownPack {
                namespace: "minecraft",
                id,
                version: server_version,
            };
            if !known_packs
                .iter()
                .any(|p| p.namespace == pack.namespace && p.id == pack.id)
            {
                known_packs.push(pack);
            }
        }

        // 3. Loaded packs with known_pack info from pack.mcmeta
        for pack in loaded_packs {
            for kp in &pack.known_packs {
                let p = KnownPack {
                    namespace: &kp.namespace,
                    id: &kp.id,
                    version: &kp.version,
                };
                if !known_packs.iter().any(|existing| {
                    existing.namespace == p.namespace
                        && existing.id == p.id
                        && existing.version == p.version
                }) {
                    known_packs.push(p);
                }
            }
        }

        known_packs
    }

    /// Returns the enabled world feature flags (e.g. `minecraft:vanilla`, `minecraft:trade_rebalance`,
    /// `minecraft:minecart_improvements`, `minecraft:redstone_experiments`, `minecraft:bundle`).
    #[must_use]
    pub fn get_enabled_features(&self, server: &Server) -> Vec<&'static str> {
        let enabled_packs = Self::get_enabled_packs(server);
        Self::resolve_enabled_features(&enabled_packs)
    }

    /// Resolves enabled feature flag names given a list of enabled pack names.
    #[must_use]
    pub fn resolve_enabled_features(enabled_packs: &[String]) -> Vec<&'static str> {
        let mut features = vec!["minecraft:vanilla"];

        for pack_name in enabled_packs {
            let feature: &'static str = match pack_name.as_str() {
                "trade_rebalance" | "file/trade_rebalance" => "minecraft:trade_rebalance",
                "minecart_improvements" | "file/minecart_improvements" => {
                    "minecraft:minecart_improvements"
                }
                "redstone_experiments" | "file/redstone_experiments" => {
                    "minecraft:redstone_experiments"
                }
                "bundle" | "file/bundle" => "minecraft:bundle",
                _ => {
                    if let Some(stripped) = pack_name.strip_prefix("file/") {
                        match stripped {
                            "trade_rebalance" => "minecraft:trade_rebalance",
                            "minecart_improvements" => "minecraft:minecart_improvements",
                            "redstone_experiments" => "minecraft:redstone_experiments",
                            "bundle" => "minecraft:bundle",
                            _ => continue,
                        }
                    } else {
                        continue;
                    }
                }
            };
            if !features.contains(&feature) {
                features.push(feature);
            }
        }

        features
    }

    #[must_use]
    pub fn is_feature_enabled(&self, server: &Server, feature: &str) -> bool {
        self.get_enabled_features(server).contains(&feature)
    }

    /// Loads a Java Edition structure NBT from the currently enabled datapacks.
    ///
    /// Structure identifiers are resource locations such as
    /// `minecraft:village/plains/houses/plains_small_house_1`. Both the current
    /// `structure` directory and the legacy `structures` directory are checked.
    pub async fn load_structure(&self, resource_location: &str) -> Result<NbtCompound, String> {
        let (namespace, path) = parse_structure_resource_location(resource_location)?;

        let nbt_path = {
            let loaded_packs = self
                .loaded_packs
                .read()
                .unwrap_or_else(std::sync::PoisonError::into_inner);

            let mut nbt_path = None;

            // Runtime datapacks override embedded resources.
            'packs: for pack in loaded_packs.iter().rev() {
                for structure_dir in ["structure", "structures"] {
                    let candidate = pack
                        .root_path
                        .join("data")
                        .join(namespace)
                        .join(structure_dir)
                        .join(format!("{path}.nbt"));

                    if candidate.is_file() {
                        nbt_path = Some(candidate);
                        break 'packs;
                    }
                }
            }

            nbt_path
        };

        if let Some(nbt_path) = nbt_path {
            let display_path = nbt_path.display().to_string();

            return tokio::task::spawn_blocking(move || {
                let file = fs::File::open(&nbt_path).map_err(|error| {
                    format!("Failed to open structure '{display_path}': {error}")
                })?;

                read_gzip_compound_tag(file)
                    .map_err(|error| format!("Failed to parse structure '{display_path}': {error}"))
            })
            .await
            .map_err(|error| format!("Structure loader task failed: {error}"))?;
        }

        // Fall back to compile-time embedded structures.
        let structure_id = format!("{namespace}:{path}");

        if let Some(template) =
            pumpkin_world::generation::structure::template::get_template(&structure_id)
        {
            return Ok(template.save());
        }

        Err(format!(
            "Structure '{resource_location}' was not found in any enabled datapack or embedded resources"
        ))
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
        self.visit_function_lines(name, |line| {
            server
                .command_dispatcher
                .load()
                .handle_command(source, line);
        })
    }

    fn visit_function_lines(
        &self,
        name: &str,
        mut visit: impl FnMut(&str),
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

        let functions = {
            let all_fns = self
                .functions
                .read()
                .unwrap_or_else(std::sync::PoisonError::into_inner);
            let mut functions = Vec::with_capacity(functions_to_run.len());
            for fn_id in functions_to_run {
                let Some(lines) = all_fns.get(&fn_id) else {
                    if !is_tag {
                        return Err(format!("Unknown function: {fn_id}"));
                    }
                    continue;
                };
                functions.push(Arc::clone(lines));
            }
            functions
        };

        let mut total_executed = 0;
        for lines in functions {
            for line in lines.iter() {
                visit(line);
                total_executed += 1;
            }
        }

        Ok(total_executed)
    }

    #[must_use]
    pub fn is_embedded_pack(name: &str) -> bool {
        pumpkin_world::generation::structure::template::all_embedded_datapack_names()
            .contains(&name)
    }

    pub fn get_all_known_packs(server: &Server) -> Vec<String> {
        let mut packs: Vec<String> =
            pumpkin_world::generation::structure::template::all_embedded_datapack_names()
                .iter()
                .map(|name| (*name).to_owned())
                .collect();

        // Bundled feature packs that are known to the server independently of
        // the compile-time structure/test resource embeddings.
        for bundled in [
            "trade_rebalance",
            "minecart_improvements",
            "redstone_experiments",
        ] {
            if !packs.iter().any(|p| p == bundled) {
                packs.push(bundled.to_string());
            }
        }

        // World datapacks directory.
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
        // Compile-time embedded datapacks cannot be disabled at runtime, so
        // expose them as permanently enabled.
        let mut packs: Vec<String> =
            pumpkin_world::generation::structure::template::all_embedded_datapack_names()
                .iter()
                .map(|name| (*name).to_owned())
                .collect();

        for pack in &server.level_info.load().data_packs.enabled {
            if !packs.iter().any(|existing| existing == pack) {
                packs.push(pack.clone());
            }
        }

        packs
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
        } else if Self::is_embedded_pack(&resolved_name) {
            (
                resolved_name.clone(),
                resolved_name.clone(),
                format!("Embedded datapack: {resolved_name}"),
                61,
            )
        } else if let Some(stripped) = resolved_name.strip_prefix("file/") {
            let pack_path = server
                .basic_config
                .get_world_path()
                .join("datapacks")
                .join(stripped);
            let (desc, format, _) = read_pack_mcmeta(&pack_path);
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

        if Self::is_embedded_pack(&target_pack) {
            return Err(format!(
                "Cannot disable embedded datapack '{target_pack}' because it is compiled into the server"
            ));
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

fn parse_structure_resource_location(resource_location: &str) -> Result<(&str, &str), String> {
    let (namespace, raw_path) = resource_location
        .split_once(':')
        .unwrap_or(("minecraft", resource_location));

    if namespace.is_empty()
        || !namespace.bytes().all(|byte| {
            byte.is_ascii_lowercase() || byte.is_ascii_digit() || matches!(byte, b'_' | b'-' | b'.')
        })
    {
        return Err(format!(
            "Invalid structure namespace in '{resource_location}'"
        ));
    }

    let path = raw_path.strip_suffix(".nbt").unwrap_or(raw_path);
    if path.is_empty()
        || path
            .split('/')
            .any(|segment| segment.is_empty() || segment == "." || segment == "..")
        || !path.bytes().all(|byte| {
            byte.is_ascii_lowercase()
                || byte.is_ascii_digit()
                || matches!(byte, b'_' | b'-' | b'.' | b'/')
        })
    {
        return Err(format!("Invalid structure path in '{resource_location}'"));
    }

    Ok((namespace, path))
}

fn build_damage_type_registry(
    mut all_damage_type_defs: HashMap<String, DamageTypeDefinition>,
) -> DamageTypeRegistry {
    let mut damage_type_registry: DamageTypeRegistry = HashMap::new();
    let mut next_custom_id = 51u8;
    let mut static_map: HashMap<String, &'static DamageType> = HashMap::new();
    let mut names = Vec::new();

    let mut sorted_ids: Vec<String> = all_damage_type_defs.keys().cloned().collect();
    sorted_ids.sort();

    for id in sorted_ids {
        let Some(def) = all_damage_type_defs.remove(&id) else {
            continue;
        };
        let path = id.split_once(':').map_or(id.as_str(), |(_, p)| p);
        let numeric_id = pumpkin_data::damage::DamageType::from_name(path)
            .or_else(|| pumpkin_data::damage::DamageType::from_name(&id))
            .map_or_else(
                || {
                    let nid = next_custom_id;
                    next_custom_id = next_custom_id.saturating_add(1);
                    nid
                },
                |vanilla_dt| vanilla_dt.id,
            );

        let dt = damage_type_loader::to_damage_type(numeric_id, &def);
        let nbt_data = damage_type_loader::to_registry_nbt_bytes(&def);
        let leaked_dt = Box::leak(Box::new(dt));
        static_map.insert(id.clone(), leaked_dt);
        static_map.insert(path.to_string(), leaked_dt);
        names.push(id.clone());

        damage_type_registry.insert(
            id.clone(),
            DamageTypeEntry {
                id,
                numeric_id,
                definition: def,
                damage_type: dt,
                nbt_data,
            },
        );
    }

    pumpkin_command::argument_types::resource::register_dynamic_damage_types(
        Box::new(move |req_id: &str| static_map.get(req_id).copied()),
        names,
    );

    damage_type_registry
}

struct PackContentAccumulators<'a> {
    recipes: &'a mut Vec<DynamicRecipe>,
    functions: &'a mut HashMap<String, Vec<String>>,
    function_tags: &'a mut HashMap<String, Vec<String>>,
    test_instances: &'a mut TestInstanceRegistry,
    damage_types: &'a mut HashMap<String, DamageTypeDefinition>,
    context_int_providers: &'a mut ContextProviderRegistry,
    context_float_providers: &'a mut ContextProviderRegistry,
    loot_tables: &'a mut HashMap<String, Arc<DynamicLootTable>>,
    dynamic_registries: &'a mut HashMap<String, HashMap<String, RegistryEntryData>>,
    trade_registry: &'a mut TradeRegistry,
}

#[derive(Default, Clone, Copy)]
struct PackContentCounts {
    recipes: usize,
    recipe_files_seen: usize,
    functions: usize,
    test_instances: usize,
    damage_types: usize,
    int_providers: usize,
    float_providers: usize,
    loot_tables: usize,
    structures: usize,
    template_pools: usize,
    processor_lists: usize,
    dynamic_registries: usize,
    trade_sets: usize,
    trades: usize,
    trade_tags: usize,
}

fn scan_datapacks_dir(
    datapacks_dir: &Path,
    enabled_packs: &[String],
    acc: &mut PackContentAccumulators<'_>,
    loaded_packs_vec: &mut Vec<LoadedDatapack>,
) {
    if !datapacks_dir.is_dir() {
        return;
    }
    let entries = match fs::read_dir(datapacks_dir) {
        Ok(entries) => entries,
        Err(error) => {
            warn!(
                "Failed to read datapacks directory '{}': {error}",
                datapacks_dir.display()
            );
            return;
        }
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

        let (description, pack_format, known_packs) = read_pack_mcmeta(&pack_path);
        let counts = load_pack_contents(&pack_path, acc);

        info!(
            "Loaded datapack '{file_name}': {} recipe(s), {} function(s), {} test instance(s), {} damage type(s), {} int provider(s), {} float provider(s), {} loot table(s), {} structure(s), {} template pool(s), {} processor list(s), {} dynamic registry entry/entries, {} trade set(s), {} trade(s)",
            counts.recipes,
            counts.functions,
            counts.test_instances,
            counts.damage_types,
            counts.int_providers,
            counts.float_providers,
            counts.loot_tables,
            counts.structures,
            counts.template_pools,
            counts.processor_lists,
            counts.dynamic_registries,
            counts.trade_sets,
            counts.trades,
        );
        report_skipped_recipes(&file_name, counts.recipe_files_seen, counts.recipes);

        loaded_packs_vec.push(LoadedDatapack {
            id: pack_id,
            name: file_name,
            description,
            pack_format,
            root_path: pack_path,
            recipe_count: counts.recipes,
            function_count: counts.functions,
            known_packs,
        });
    }
}

/// Loads recipes, functions, function tags, test instances, damage types, context number providers,
/// and loot tables from a single datapack directory, returning per-pack counts as [`PackContentCounts`].
#[allow(clippy::too_many_lines)]
fn load_pack_contents(
    pack_path: &Path,
    acc: &mut PackContentAccumulators<'_>,
) -> PackContentCounts {
    let data_dir = pack_path.join("data");
    let mut counts = PackContentCounts::default();

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
                        acc.recipes,
                        &mut counts.recipes,
                        &mut counts.recipe_files_seen,
                    );
                }
            }

            // Load functions
            for fn_sub in ["function", "functions"] {
                let fn_dir = ns_path.join(fn_sub);
                if fn_dir.is_dir() {
                    let before = acc.functions.len();
                    function_loader::load_functions_from_dir(&namespace, &fn_dir, acc.functions);
                    counts.functions += acc.functions.len() - before;
                }
            }

            // Load tags
            let tags_dir = ns_path.join("tags");
            if tags_dir.is_dir() {
                function_loader::load_function_tags_from_dir(
                    &namespace,
                    &tags_dir,
                    acc.function_tags,
                );
            }
            // Load game test instances
            let test_instance_dir = ns_path.join("test_instance");
            if test_instance_dir.is_dir() {
                counts.test_instances += load_test_instances_from_dir(
                    &namespace,
                    &test_instance_dir,
                    acc.test_instances,
                );
            }

            // Load damage types
            for dt_sub in ["damage_type", "damage_types"] {
                let dt_dir = ns_path.join(dt_sub);
                if dt_dir.is_dir() {
                    counts.damage_types += damage_type_loader::load_damage_types_from_dir(
                        &namespace,
                        &dt_dir,
                        acc.damage_types,
                    );
                }
            }

            // Load context int providers
            for int_sub in ["context_int_provider", "context_int_providers"] {
                let provider_dir = ns_path.join(int_sub);
                if provider_dir.is_dir() {
                    counts.int_providers +=
                        context_provider_loader::load_context_providers_from_dir(
                            &namespace,
                            &provider_dir,
                            acc.context_int_providers,
                        );
                }
            }

            // Load context float providers
            for float_sub in ["context_float_provider", "context_float_providers"] {
                let provider_dir = ns_path.join(float_sub);
                if provider_dir.is_dir() {
                    counts.float_providers +=
                        context_provider_loader::load_context_providers_from_dir(
                            &namespace,
                            &provider_dir,
                            acc.context_float_providers,
                        );
                }
            }

            // Load loot tables
            for lt_sub in ["loot_table", "loot_tables"] {
                let lt_dir = ns_path.join(lt_sub);
                if lt_dir.is_dir() {
                    counts.loot_tables += loot_table_loader::load_loot_tables_from_dir(
                        &namespace,
                        &lt_dir,
                        acc.loot_tables,
                    );
                }
            }

            // Load structure templates
            for st_sub in ["structure", "structures"] {
                let st_dir = ns_path.join(st_sub);
                if st_dir.is_dir() {
                    counts.structures +=
                        structure_loader::load_structures_from_dir(&namespace, &st_dir);
                }
            }

            // Load worldgen template pools and processor lists
            let worldgen_dir = ns_path.join("worldgen");
            if worldgen_dir.is_dir() {
                let pool_dir = worldgen_dir.join("template_pool");
                if pool_dir.is_dir() {
                    counts.template_pools +=
                        structure_loader::load_template_pools_from_dir(&namespace, &pool_dir);
                }
                let proc_dir = worldgen_dir.join("processor_list");
                if proc_dir.is_dir() {
                    counts.processor_lists +=
                        structure_loader::load_processor_lists_from_dir(&namespace, &proc_dir);
                }
            }

            // Load dynamic registry entries (wolf_variant, cat_variant, frog_variant, instrument,
            // trim_material, trim_pattern, banner_pattern, decorated_pot_pattern, chat_type, etc.)
            counts.dynamic_registries += dynamic_registry_loader::load_dynamic_registries_from_ns(
                &namespace,
                &ns_path,
                acc.dynamic_registries,
            );

            // Load trade sets
            for ts_sub in ["trade_set", "trade_sets"] {
                let ts_dir = ns_path.join(ts_sub);
                if ts_dir.is_dir() {
                    counts.trade_sets += trade_loader::load_trade_sets_from_dir(
                        &namespace,
                        &ts_dir,
                        &mut acc.trade_registry.trade_sets,
                    );
                }
            }

            // Load villager trades
            for vt_sub in ["villager_trade", "villager_trades"] {
                let vt_dir = ns_path.join(vt_sub);
                if vt_dir.is_dir() {
                    counts.trades += trade_loader::load_trades_from_dir(
                        &namespace,
                        &vt_dir,
                        &mut acc.trade_registry.trades,
                    );
                }
            }

            // Load villager trade tags
            let tags_dir = ns_path.join("tags");
            if tags_dir.is_dir() {
                for vt_tag_sub in ["villager_trade", "villager_trades"] {
                    let vt_tag_dir = tags_dir.join(vt_tag_sub);
                    if vt_tag_dir.is_dir() {
                        counts.trade_tags += trade_loader::load_trade_tags_from_dir(
                            &namespace,
                            &vt_tag_dir,
                            &mut acc.trade_registry.trade_tags,
                        );
                    }
                }
            }
        }
    }

    counts
}

#[must_use]
pub fn clone_registry_entry(entry: &RegistryEntryData) -> RegistryEntryData {
    RegistryEntryData {
        entry_id: entry.entry_id.clone(),
        data: entry.data.clone(),
    }
}

#[must_use]
pub fn merge_damage_type_entries<S: std::hash::BuildHasher>(
    vanilla_entries: &[RegistryEntryData],
    custom_entries: &HashMap<String, DamageTypeEntry, S>,
) -> Vec<RegistryEntryData> {
    if custom_entries.is_empty() {
        return vanilla_entries.iter().map(clone_registry_entry).collect();
    }

    let mut merged = Vec::with_capacity(vanilla_entries.len() + custom_entries.len());
    let mut overridden = std::collections::HashSet::new();

    for entry in vanilla_entries {
        let full_id = if entry.entry_id.contains(':') {
            entry.entry_id.clone()
        } else {
            format!("minecraft:{}", entry.entry_id)
        };

        if let Some(custom) = custom_entries
            .get(&full_id)
            .or_else(|| custom_entries.get(&entry.entry_id))
        {
            let mut reg_entry = custom.to_registry_entry();
            reg_entry.entry_id.clone_from(&entry.entry_id);
            merged.push(reg_entry);
            overridden.insert(full_id);
            overridden.insert(entry.entry_id.clone());
        } else {
            merged.push(clone_registry_entry(entry));
        }
    }

    let mut sorted_custom: Vec<&DamageTypeEntry> = custom_entries
        .values()
        .filter(|e| !overridden.contains(&e.id))
        .collect();
    sorted_custom.sort_by_key(|e| e.numeric_id);

    for custom in sorted_custom {
        merged.push(custom.to_registry_entry());
    }

    merged
}

fn read_pack_mcmeta(pack_path: &Path) -> (String, u32, Vec<KnownPackData>) {
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

        let mut known_packs = Vec::new();
        let parse_item = |item: &serde_json::Value| -> Option<KnownPackData> {
            let ns = item.get("namespace").and_then(serde_json::Value::as_str)?;
            let id = item.get("id").and_then(serde_json::Value::as_str)?;
            let ver = item.get("version").and_then(serde_json::Value::as_str)?;
            Some(KnownPackData {
                namespace: ns.to_string(),
                id: id.to_string(),
                version: ver.to_string(),
            })
        };

        if let Some(packs_array) = val.get("known_packs").and_then(serde_json::Value::as_array) {
            for item in packs_array {
                if let Some(kp) = parse_item(item) {
                    known_packs.push(kp);
                }
            }
        } else if let Some(item) = val.get("known_pack") {
            if let Some(kp) = parse_item(item) {
                known_packs.push(kp);
            }
        } else if let Some(pack_obj) = pack {
            if let Some(packs_array) = pack_obj
                .get("known_packs")
                .and_then(serde_json::Value::as_array)
            {
                for item in packs_array {
                    if let Some(kp) = parse_item(item) {
                        known_packs.push(kp);
                    }
                }
            } else if let Some(item) = pack_obj.get("known_pack")
                && let Some(kp) = parse_item(item)
            {
                known_packs.push(kp);
            }
        }

        return (description, pack_format, known_packs);
    }
    (String::new(), 61, Vec::new())
}

/// Reads mod ore features out of the extracted `mod_*` datapacks and installs them.
///
/// The trail is the one `NeoForge` walks: a biome modifier JSON names a placed feature and
/// the biomes it belongs in; the placed feature names its configured feature. Only
/// `minecraft:ore`-shaped features are expressible -- see
/// [`dynamic_features`](pumpkin_world::generation::feature::dynamic_features) -- and each
/// one that is not gets its reason said once instead of being half-placed.
/// The dynamic content a datapack load refreshes beyond recipes: worldgen and tags.
pub(crate) fn load_dynamic_content(datapacks_dir: &Path) {
    load_dynamic_worldgen(datapacks_dir);
    load_dynamic_tags(datapacks_dir);
}

/// Reads tag JSONs out of the extracted `mod_*` datapacks and installs them.
///
/// One pass over every registry the protocol syncs tags for, not just items: item tags
/// answer ingredient matching here on the server, and the rest exist because a client
/// that has the mods installed refuses to finish joining when a tag its items name is
/// never mentioned.
///
/// Nested directories become tag paths (`tags/item/gems/super.json` is
/// `<ns>:gems/super`), matching the datapack convention, and entries are stored as
/// written -- `#references` resolve at query time in the dynamic store.
pub(crate) fn load_dynamic_tags(datapacks_dir: &Path) {
    let mut by_registry: pumpkin_data::dynamic::ModTags = std::collections::HashMap::new();
    let Ok(entries) = fs::read_dir(datapacks_dir) else {
        pumpkin_data::dynamic::install_item_tags(std::collections::HashMap::new());
        pumpkin_data::dynamic::install_mod_tags(by_registry);
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
            for &key in pumpkin_data::tag::RegistryKey::NETWORK_KEYS {
                // The identifier is the directory name, `worldgen/biome` included.
                let tag_root = namespace_entry
                    .path()
                    .join("tags")
                    .join(key.identifier_string());
                if !tag_root.is_dir() {
                    continue;
                }
                let tags = by_registry
                    .entry(key.identifier_string().to_string())
                    .or_default();
                collect_tag_files(&tag_root, &tag_root, &namespace, tags);
            }
        }
    }
    let item_tags = by_registry
        .get(pumpkin_data::tag::RegistryKey::Item.identifier_string())
        .cloned()
        .unwrap_or_default();
    let item_count = item_tags.len();
    if item_count > 0 {
        info!("Mod item tags: {item_count} tag(s) loaded");
    }
    let other_count: usize = by_registry
        .iter()
        .filter(|(registry, _)| registry.as_str() != "item")
        .map(|(_, tags)| tags.len())
        .sum();
    if other_count > 0 {
        // Said plainly because these go to the client with their mod-namespaced members
        // dropped -- see `pumpkin_data::dynamic::network_tag_entries`.
        info!(
            "Mod tags for other registries: {other_count} tag(s) loaded; a client is told they exist, with their vanilla members only"
        );
    }
    pumpkin_data::dynamic::install_item_tags(item_tags);
    pumpkin_data::dynamic::install_mod_tags(by_registry);
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
                // 26.3 moved configured features to worldgen/feature; older packs still
                // use worldgen/configured_feature.
                let worldgen = data_dir.join(&configured_ns).join("worldgen");
                let configured_json = ["feature", "configured_feature"].iter().find_map(|dir| {
                    fs::read_to_string(worldgen.join(dir).join(format!("{configured_path}.json")))
                        .ok()
                });
                let Some(configured_json) = configured_json else {
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

fn load_recipes_recursive(
    namespace: &str,
    base_dir: &Path,
    current_dir: &Path,
    all_recipes: &mut Vec<DynamicRecipe>,
    count: &mut usize,
    seen: &mut usize,
) {
    let Ok(entries) = fs::read_dir(current_dir) else {
        return;
    };
    for entry in entries.flatten() {
        let path = entry.path();
        if path.is_dir() {
            load_recipes_recursive(namespace, base_dir, &path, all_recipes, count, seen);
        } else if path
            .extension()
            .is_some_and(|ext| ext.eq_ignore_ascii_case("json"))
        {
            *seen += 1;
            let Ok(relative_path) = path.strip_prefix(base_dir) else {
                continue;
            };

            // The resource id is the path below the recipe root, not the file stem, so
            // nested mod recipes keep distinct ids.
            let recipe_name = relative_path
                .with_extension("")
                .to_string_lossy()
                .replace('\\', "/");

            if let Ok(content) = fs::read_to_string(&path)
                && let Some(recipe) = recipe_loader::parse_recipe(namespace, &recipe_name, &content)
            {
                all_recipes.push(recipe);
                *count += 1;
            }
        }
    }
}

fn load_recipes_from_dir(
    namespace: &str,
    dir: &Path,
    all_recipes: &mut Vec<DynamicRecipe>,
    count: &mut usize,
    seen: &mut usize,
) {
    load_recipes_recursive(namespace, dir, dir, all_recipes, count, seen);
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

#[cfg(test)]
mod tests {
    use super::DatapackManager;

    #[test]
    fn function_dispatch_releases_the_functions_lock() {
        let manager = DatapackManager::new();
        manager
            .functions
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .insert(
                "test:reentrant".to_string(),
                vec!["first command".to_string(), "second command".to_string()].into(),
            );

        let mut visited = Vec::new();
        let executed = manager
            .visit_function_lines("test:reentrant", |line| {
                let functions = manager
                    .functions
                    .try_write()
                    .expect("function dispatch must not hold the functions read lock");
                drop(functions);
                visited.push(line.to_string());
            })
            .expect("execute function lines");

        assert_eq!(executed, 2);
        assert_eq!(visited, ["first command", "second command"]);
    }

    #[test]
    fn function_resolution_preserves_unknown_errors_and_skips_missing_tag_entries() {
        let manager = DatapackManager::new();
        manager
            .functions
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .insert(
                "test:known".to_string(),
                vec!["known command".to_string()].into(),
            );
        manager
            .function_tags
            .write()
            .unwrap_or_else(std::sync::PoisonError::into_inner)
            .insert(
                "test:mixed".to_string(),
                vec!["test:missing".to_string(), "test:known".to_string()],
            );

        let mut visited = Vec::new();
        let executed = manager
            .visit_function_lines("#test:mixed", |line| visited.push(line.to_string()))
            .expect("execute function tag");
        assert_eq!(executed, 1);
        assert_eq!(visited, ["known command"]);

        assert_eq!(
            manager
                .visit_function_lines("test:missing", |_| {})
                .expect_err("unknown function must fail"),
            "Unknown function: test:missing"
        );
        assert_eq!(
            manager
                .visit_function_lines("#test:missing", |_| {})
                .expect_err("unknown function tag must fail"),
            "Unknown function tag: #test:missing"
        );
    }

    #[test]
    fn read_pack_mcmeta_parses_known_packs() {
        let temp_dir = tempfile::tempdir().expect("tempdir");
        let pack_path = temp_dir.path();

        // 1. Test root known_packs array
        let mcmeta_content = r#"{
            "pack": {
                "description": "Test Pack",
                "pack_format": 61
            },
            "known_packs": [
                {
                    "namespace": "example",
                    "id": "content",
                    "version": "1.0.0"
                }
            ]
        }"#;
        std::fs::write(pack_path.join("pack.mcmeta"), mcmeta_content).unwrap();

        let (desc, format, known_packs) = super::read_pack_mcmeta(pack_path);
        assert_eq!(desc, "Test Pack");
        assert_eq!(format, 61);
        assert_eq!(known_packs.len(), 1);
        assert_eq!(known_packs[0].namespace, "example");
        assert_eq!(known_packs[0].id, "content");
        assert_eq!(known_packs[0].version, "1.0.0");

        // 2. Test nested pack.known_packs
        let mcmeta_content_nested = r#"{
            "pack": {
                "description": "Nested Pack",
                "pack_format": 61,
                "known_packs": [
                    {
                        "namespace": "nested",
                        "id": "pack",
                        "version": "2.0.0"
                    }
                ]
            }
        }"#;
        std::fs::write(pack_path.join("pack.mcmeta"), mcmeta_content_nested).unwrap();

        let (_, _, known_packs) = super::read_pack_mcmeta(pack_path);
        assert_eq!(known_packs.len(), 1);
        assert_eq!(known_packs[0].namespace, "nested");
        assert_eq!(known_packs[0].id, "pack");
        assert_eq!(known_packs[0].version, "2.0.0");
    }

    #[test]
    fn resolve_enabled_features_works() {
        // Default only vanilla
        let default_features = DatapackManager::resolve_enabled_features(&[]);
        assert_eq!(default_features, ["minecraft:vanilla"]);

        // With minecart improvements
        let minecart_features =
            DatapackManager::resolve_enabled_features(&["file/minecart_improvements".to_string()]);
        assert_eq!(
            minecart_features,
            ["minecraft:vanilla", "minecraft:minecart_improvements"]
        );

        // Multiple experimental features
        let multi_features = DatapackManager::resolve_enabled_features(&[
            "trade_rebalance".to_string(),
            "redstone_experiments".to_string(),
            "file/bundle".to_string(),
        ]);
        assert_eq!(
            multi_features,
            [
                "minecraft:vanilla",
                "minecraft:trade_rebalance",
                "minecraft:redstone_experiments",
                "minecraft:bundle"
            ]
        );
    }
}
