//! The JVM and the one thread allowed to talk to it.
//!
//! Mod code assumes vanilla's single-threaded world tick, so all of it runs on one thread.
//! That the thread is also where `JNIEnv` is attached is a convenience, not the reason.
//!
//! There is exactly one VM per process for the life of the process: `JNI_CreateJavaVM`
//! fails on a second call and there is no way to unmake one.

use std::{
    path::PathBuf,
    sync::{Mutex, OnceLock, mpsc},
    thread,
};

use jni::{InitArgsBuilder, JNIEnv, JavaVM};
use tracing::info;

/// What can go wrong either side of the boundary.
#[derive(Debug, thiserror::Error)]
pub enum VmError {
    /// The VM could not be created.
    #[error("Failed to start the JVM: {0}")]
    Boot(String),
    /// A call into Java failed, or Java threw.
    #[error("Java call failed: {0}")]
    Java(String),
    /// The mod thread died; nothing further can run.
    #[error("The mod thread is gone")]
    ThreadGone,
}

type Job = Box<dyn FnOnce(&mut JNIEnv) + Send>;

/// A booted VM and the channel to its mod thread.
pub struct ModVm {
    jobs: mpsc::Sender<Job>,
}

static VM: OnceLock<ModVm> = OnceLock::new();

/// Serializes concurrent `boot` callers so only one of them ever drives `JNI_CreateJavaVM`.
///
/// `VM.get()` alone is check-then-act: two threads racing past it before either has
/// finished booting would both spawn a mod thread and both try to create the VM, and the
/// second `JNI_CreateJavaVM` call always fails. Holding this lock for the whole spawn-and-
/// wait sequence makes `boot` safe to call from multiple threads at once, which plugin
/// loading otherwise would do.
static BOOT_LOCK: Mutex<()> = Mutex::new(());

/// Starts the VM, or returns the one already running.
///
/// `classpath` is ignored on any call after the first, because the VM's classpath is fixed
/// at creation. Callers needing more classes add them through a child classloader.
///
/// # Errors
/// Returns [`VmError::Boot`] if the VM cannot be created.
pub fn boot(classpath: &[PathBuf]) -> Result<&'static ModVm, VmError> {
    if let Some(vm) = VM.get() {
        return Ok(vm);
    }

    let _guard = BOOT_LOCK
        .lock()
        .map_err(|_| VmError::Boot("the boot lock was poisoned".to_owned()))?;

    // Another thread may have finished booting while we were waiting for the lock.
    if let Some(vm) = VM.get() {
        return Ok(vm);
    }

    let joined = join_classpath(classpath);
    info!("Starting the JVM with classpath {joined}");

    let (jobs, requests) = mpsc::channel::<Job>();
    let (ready, booted) = mpsc::channel::<Result<(), String>>();

    thread::Builder::new()
        .name("pumpkin-mod-thread".to_owned())
        .spawn(move || mod_thread(&joined, &ready, &requests))
        .map_err(|err| VmError::Boot(err.to_string()))?;

    booted
        .recv()
        .map_err(|_| VmError::Boot("the mod thread died during boot".to_owned()))?
        .map_err(VmError::Boot)?;

    Ok(VM.get_or_init(|| ModVm { jobs }))
}

fn join_classpath(classpath: &[PathBuf]) -> String {
    classpath
        .iter()
        .map(|path| path.to_string_lossy().into_owned())
        .collect::<Vec<_>>()
        .join(":")
}

fn mod_thread(
    classpath: &str,
    ready: &mpsc::Sender<Result<(), String>>,
    requests: &mpsc::Receiver<Job>,
) {
    let args = match InitArgsBuilder::new()
        .version(jni::JNIVersion::V8)
        .option(format!("-Djava.class.path={classpath}"))
        .build()
    {
        Ok(args) => args,
        Err(err) => {
            let _ = ready.send(Err(err.to_string()));
            return;
        }
    };

    let vm = match JavaVM::new(args) {
        Ok(vm) => vm,
        Err(err) => {
            let _ = ready.send(Err(err.to_string()));
            return;
        }
    };

    let mut env = match vm.attach_current_thread_permanently() {
        Ok(env) => env,
        Err(err) => {
            let _ = ready.send(Err(err.to_string()));
            return;
        }
    };

    if ready.send(Ok(())).is_err() {
        return;
    }

    while let Ok(job) = requests.recv() {
        job(&mut env);
    }
}

impl ModVm {
    /// Runs `work` on the mod thread and waits for its result.
    ///
    /// # Errors
    /// Returns [`VmError::ThreadGone`] if the mod thread has stopped, or whatever `work`
    /// returned.
    pub fn call<R, F>(&self, work: F) -> Result<R, VmError>
    where
        F: FnOnce(&mut JNIEnv) -> Result<R, VmError> + Send + 'static,
        R: Send + 'static,
    {
        let (reply, answer) = mpsc::channel();
        self.jobs
            .send(Box::new(move |env| {
                let _ = reply.send(work(env));
            }))
            .map_err(|_| VmError::ThreadGone)?;
        answer.recv().map_err(|_| VmError::ThreadGone)?
    }
}
