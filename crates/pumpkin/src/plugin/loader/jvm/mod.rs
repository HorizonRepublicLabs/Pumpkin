//! Hosting a JVM so mods written in Java can run against Pumpkin.

pub mod handles;
pub mod natives;
pub mod vm;

use std::{
    any::Any,
    path::{Path, PathBuf},
    sync::Arc,
};

use crate::plugin::{
    Context, Plugin, PluginFuture, PluginMetadata,
    loader::{LoaderError, PluginLoadFuture, PluginLoader, PluginUnloadFuture},
};

use vm::{ModVm, VmError};

/// Brings one mod up inside the VM and returns its declared mod id.
///
/// # Errors
/// Returns [`VmError::Java`] if discovery, construction or registration threw.
pub fn load_mod(vm: &'static ModVm, jar: &str) -> Result<String, VmError> {
    let jar = jar.to_owned();
    vm.call(move |env| {
        let path = env
            .new_string(&jar)
            .map_err(|err| VmError::Java(err.to_string()))?;

        let returned = env.call_static_method(
            "dev/pumpkin/jvmhost/Bootstrap",
            "loadAndRegister",
            "(Ljava/lang/String;)Ljava/lang/String;",
            &[(&path).into()],
        );

        // A Java exception leaves the env in a pending state; describe and clear it, or
        // the next call fails for the wrong reason.
        if env.exception_check().unwrap_or(false) {
            let _ = env.exception_describe();
            let _ = env.exception_clear();
            return Err(VmError::Java(format!("the mod at {jar} threw during load")));
        }

        let object = returned
            .and_then(jni::objects::JValueGen::l)
            .map_err(|err| VmError::Java(err.to_string()))?;

        env.get_string(&jni::objects::JString::from(object))
            .map(Into::into)
            .map_err(|err| VmError::Java(err.to_string()))
    })
}

/// A loaded Java mod, seen by Pumpkin as an ordinary plugin.
struct JvmPlugin {
    mod_id: String,
}

impl Plugin for JvmPlugin {
    fn on_load(&self, _context: Arc<Context>) -> PluginFuture<'_, Result<(), String>> {
        // Registration already ran during load: it has to happen before the registries
        // freeze, which is earlier than a plugin's on_load.
        Box::pin(async { Ok(()) })
    }

    fn on_unload(&self, _context: Arc<Context>) -> PluginFuture<'_, Result<(), String>> {
        let mod_id = self.mod_id.clone();
        Box::pin(async move {
            Err(format!(
                "{mod_id} cannot be unloaded: the JVM outlives the server"
            ))
        })
    }

    fn on_ipc_message(
        &self,
        _sender: &str,
        _message: &[u8],
    ) -> PluginFuture<'_, Result<Vec<u8>, String>> {
        Box::pin(async { Err("Java mods do not accept IPC yet".to_owned()) })
    }
}

/// Loads `.jar` mods by starting a JVM and running them against the shim.
pub struct JvmPluginLoader {
    classpath: Vec<PathBuf>,
}

impl JvmPluginLoader {
    /// A loader that boots its VM with `classpath` — the shim, FML and host jars.
    #[must_use]
    pub const fn new(classpath: Vec<PathBuf>) -> Self {
        Self { classpath }
    }
}

impl PluginLoader for JvmPluginLoader {
    fn load<'a>(&'a self, path: &'a Path) -> PluginLoadFuture<'a> {
        Box::pin(async move {
            let vm = vm::boot(&self.classpath)
                .map_err(|err| LoaderError::InitializationFailed(err.to_string()))?;

            let jar = path.to_string_lossy().into_owned();
            let mod_id = load_mod(vm, &jar)
                .map_err(|err| LoaderError::InitializationFailed(err.to_string()))?;

            let metadata = PluginMetadata {
                name: mod_id.clone(),
                version: "0.0.0".to_owned(),
                authors: Vec::new(),
                description: "A NeoForge mod hosted on the JVM".to_owned(),
                dependencies: Vec::new(),
                permissions: Vec::new(),
            };

            Ok((
                Arc::new(JvmPlugin { mod_id }) as Arc<dyn Plugin>,
                metadata,
                Box::new(()) as Box<dyn Any + Send + Sync>,
            ))
        })
    }

    fn can_load(&self, path: &Path) -> bool {
        path.extension()
            .unwrap_or_default()
            .eq_ignore_ascii_case("jar")
    }

    fn unload(&self, _data: Box<dyn Any + Send + Sync>) -> PluginUnloadFuture<'_> {
        Box::pin(async { Ok(()) })
    }

    fn can_unload(&self) -> bool {
        false
    }
}
