//! The JVM and the one thread allowed to talk to it.
//!
//! Mod code assumes vanilla's single-threaded world tick, so all of it runs on one thread.
//! That the thread is also where `JNIEnv` is attached is a convenience, not the reason.
//!
//! There is exactly one VM per process for the life of the process: `JNI_CreateJavaVM`
//! fails on a second call and there is no way to unmake one.

use std::{
    cell::Cell,
    path::PathBuf,
    sync::{Mutex, OnceLock, mpsc},
    thread::{self, ThreadId},
};

use jni::{InitArgsBuilder, JNIEnv, JavaVM, sys};
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
    /// The mod thread's id, so [`ModVm::call`] can tell when it is already running on it.
    mod_thread: ThreadId,
}

thread_local! {
    /// The raw `JNIEnv` pointer currently borrowed by the job running on the mod thread,
    /// if any. Set by [`mod_thread`] for the duration of each job it runs.
    ///
    /// This is what lets a re-entrant [`ModVm::call`] — Java calling back into a native
    /// method that calls back into a plugin, say — run inline instead of queuing a job:
    /// the mod thread is the only thread that ever services the job queue, so a job
    /// already running on it can never wait for itself to finish. Rebuilding the `JNIEnv`
    /// from this pointer, rather than threading it through as a parameter, is what makes
    /// that possible without changing every call site's signature.
    static CURRENT_ENV: Cell<Option<*mut sys::JNIEnv>> = const { Cell::new(None) };
}

static VM: OnceLock<Result<ModVm, String>> = OnceLock::new();

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
/// `classpath` is ignored on any call after the first *successful* boot, because the VM's
/// classpath is fixed at creation. Callers needing more classes add them through a child
/// classloader.
///
/// A boot failure is sticky, not retried: `JavaVM` has no `Drop` (only an `unsafe fn
/// destroy`), so a failure partway through boot — the VM created but the mod thread never
/// finishing attachment, say — leaves a live, unreachable VM behind. Retrying would call
/// `JNI_CreateJavaVM` a second time and fail forever with an opaque error, so instead the
/// first failure is recorded once and handed back to every later caller unchanged.
///
/// # Errors
/// Returns [`VmError::Boot`] if the VM cannot be created, or if a previous boot attempt in
/// this process already failed. Also returns it if the boot lock itself was poisoned by a
/// panicking boot attempt — deliberately: the "one JVM per process" contract makes it
/// unsafe to guess whether the panicking attempt reached `JNI_CreateJavaVM` before it died,
/// so failing closed for the rest of the process is safer than risking a second call.
pub fn boot(classpath: &[PathBuf]) -> Result<&'static ModVm, VmError> {
    if let Some(outcome) = VM.get() {
        return as_vm_result(outcome);
    }

    let _guard = BOOT_LOCK.lock().map_err(|_| {
        VmError::Boot(
            "the boot lock was poisoned by a panicking boot attempt; this process can never \
             start the JVM now"
                .to_owned(),
        )
    })?;

    // Another thread may have finished booting (successfully or not) while we waited.
    if let Some(outcome) = VM.get() {
        return as_vm_result(outcome);
    }

    let joined = join_classpath(classpath);
    info!("Starting the JVM with classpath {joined}");

    let (jobs, requests) = mpsc::channel::<Job>();
    let (ready, booted) = mpsc::channel::<Result<(), String>>();

    let outcome = match thread::Builder::new()
        .name("pumpkin-mod-thread".to_owned())
        .spawn(move || mod_thread(&joined, &ready, &requests))
    {
        Ok(handle) => {
            let mod_thread = handle.thread().id();
            booted
                .recv()
                .map_err(|_| "the mod thread died during boot".to_owned())
                .and_then(std::convert::identity)
                .map(|()| ModVm { jobs, mod_thread })
        }
        Err(err) => Err(err.to_string()),
    };

    as_vm_result(VM.get_or_init(|| outcome))
}

/// Turns a stored boot outcome into what callers see, cloning the message on failure so a
/// second caller after a failed boot gets the original failure, not a fresh attempt.
fn as_vm_result(outcome: &'static Result<ModVm, String>) -> Result<&'static ModVm, VmError> {
    outcome
        .as_ref()
        .map_err(|message| VmError::Boot(message.clone()))
}

fn join_classpath(classpath: &[PathBuf]) -> String {
    // Java's classpath separator is platform-specific: `:` everywhere but Windows, which
    // uses `;`. Getting this wrong doesn't error here — it silently glues two paths into
    // one bogus entry, which only surfaces later as a `ClassNotFoundException`.
    let separator = if cfg!(windows) { ";" } else { ":" };
    classpath
        .iter()
        .map(|path| path.to_string_lossy().into_owned())
        .collect::<Vec<_>>()
        .join(separator)
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

    if let Err(err) = super::natives::bind(&mut env) {
        let _ = ready.send(Err(err.to_string()));
        return;
    }

    if ready.send(Ok(())).is_err() {
        return;
    }

    while let Ok(job) = requests.recv() {
        CURRENT_ENV.with(|cell| cell.set(Some(env.get_raw())));
        job(&mut env);
        CURRENT_ENV.with(|cell| cell.set(None));
    }
}

impl ModVm {
    /// Runs `work` and waits for its result.
    ///
    /// If called from the mod thread itself — Java calling back into Rust that calls back
    /// into a plugin, for instance — `work` runs inline on the `JNIEnv` already attached to
    /// this thread instead of being queued. Queuing it would deadlock: the only thread that
    /// could ever service the queue is the one that would be blocked waiting on it.
    ///
    /// # Errors
    /// Returns [`VmError::ThreadGone`] if the mod thread has stopped, or whatever `work`
    /// returned.
    pub fn call<R, F>(&self, work: F) -> Result<R, VmError>
    where
        F: FnOnce(&mut JNIEnv) -> Result<R, VmError> + Send + 'static,
        R: Send + 'static,
    {
        if thread::current().id() == self.mod_thread {
            let ptr = CURRENT_ENV.with(Cell::get).ok_or(VmError::ThreadGone)?;
            // SAFETY: `ptr` was obtained from `JNIEnv::get_raw` on this same thread, set
            // by the job currently executing on it. That job's stack frame — and the
            // `JNIEnv` it borrowed from — is still below us on this thread, so the
            // pointer is still valid for the duration of this call.
            let mut env =
                unsafe { JNIEnv::from_raw(ptr) }.map_err(|err| VmError::Java(err.to_string()))?;
            return work(&mut env);
        }

        let (reply, answer) = mpsc::channel();
        self.jobs
            .send(Box::new(move |env| {
                let _ = reply.send(work(env));
            }))
            .map_err(|_| VmError::ThreadGone)?;
        answer.recv().map_err(|_| VmError::ThreadGone)?
    }
}
