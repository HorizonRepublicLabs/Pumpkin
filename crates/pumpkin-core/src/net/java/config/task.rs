//! Server-driven work that runs during the configuration phase.
//!
//! Vanilla treats configuration as a single burst: the server sends its registries
//! and immediately finishes. Mod loaders need more than that. `NeoForge` reads the
//! channel list the client advertises on `minecraft:register`, queues only the tasks
//! that client can handle, and runs each as a request the client acknowledges — its
//! registry sync, for instance, is `frozen_registry_sync_start`, then the registries,
//! then a `frozen_registry_sync_completed` reply from the client.
//!
//! Ordering matters as much as the payloads do. `NeoForge` runs its registry sync
//! *before* vanilla's, because the client has to know the server's ids before it can make
//! sense of the registry data that follows. So configuration is not one queue but a
//! sequence of stages with the built-in registry send wedged between them, which is what
//! [`ConfigStage`] names and [`ConfigTaskQueue::next_step`] walks.
//!
//! Tasks run one at a time. A task that expects an answer holds the connection in
//! configuration until the client replies on the channel it named; only once every stage
//! has drained is `CFinishConfig` sent.
//!
//! A stalled client is covered by the handshake idle timeout, which fires when the
//! connection goes quiet, so an unanswered task cannot pin a connection forever.

use std::collections::VecDeque;

use bytes::Bytes;

/// A custom payload sent to the client when a task starts.
pub struct ConfigPayload {
    /// The channel to send on, e.g. `neoforge:frozen_registry_sync_start`.
    pub channel: String,
    /// The payload body.
    pub data: Bytes,
}

/// Where in the configuration sequence a task runs.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub enum ConfigStage {
    /// Before the server sends registries and tags.
    ///
    /// Anything the client needs in order to interpret that data belongs here — id maps
    /// above all.
    BeforeRegistries,
    /// After registries and tags have been sent, before configuration ends.
    AfterRegistries,
}

/// A single unit of configuration-phase work.
pub struct ConfigTask {
    /// Identifier used for logging. Not sent to the client.
    pub id: String,
    /// Payload sent when the task starts. Tasks may exist purely to wait.
    pub payload: Option<ConfigPayload>,
    /// The channel the client must answer on before the next task runs.
    ///
    /// `None` completes the task as soon as its payload has been sent.
    pub ack_channel: Option<String>,
}

impl ConfigTask {
    /// A task that sends a payload and continues immediately.
    #[must_use]
    pub fn fire_and_forget(id: impl Into<String>, channel: impl Into<String>, data: Bytes) -> Self {
        Self {
            id: id.into(),
            payload: Some(ConfigPayload {
                channel: channel.into(),
                data,
            }),
            ack_channel: None,
        }
    }

    /// A task that sends a payload and waits for the client to answer on `ack_channel`.
    #[must_use]
    pub fn awaiting_ack(
        id: impl Into<String>,
        channel: impl Into<String>,
        data: Bytes,
        ack_channel: impl Into<String>,
    ) -> Self {
        Self {
            id: id.into(),
            payload: Some(ConfigPayload {
                channel: channel.into(),
                data,
            }),
            ack_channel: Some(ack_channel.into()),
        }
    }
}

/// What the connection should do next.
pub enum ConfigStep {
    /// Send this payload, if there is one. The queue has already recorded the task as
    /// started, so nothing else is required of the caller.
    Send {
        /// Task identifier, for logging.
        id: String,
        /// The payload to write, if the task has one.
        payload: Option<ConfigPayload>,
    },
    /// Send the built-in registry and tag data.
    SendRegistries,
    /// Send `CFinishConfig`. Returned exactly once.
    Finish,
    /// Nothing to do: either the client owes an acknowledgement, or configuration is over.
    Wait,
}

/// The task the queue is waiting on an answer for.
struct Waiting {
    id: String,
    ack_channel: String,
}

/// Internal cursor over the configuration sequence.
#[derive(Clone, Copy, PartialEq, Eq)]
enum Cursor {
    BeforeRegistries,
    Registries,
    AfterRegistries,
    Finishing,
    Done,
}

/// Ordered configuration work for one connection.
pub struct ConfigTaskQueue {
    before: VecDeque<ConfigTask>,
    after: VecDeque<ConfigTask>,
    cursor: Cursor,
    waiting: Option<Waiting>,
    /// Whether the registry send may proceed. See [`Self::allow_registries`].
    registries_allowed: bool,
}

impl Default for ConfigTaskQueue {
    fn default() -> Self {
        Self {
            before: VecDeque::new(),
            after: VecDeque::new(),
            cursor: Cursor::BeforeRegistries,
            waiting: None,
            registries_allowed: false,
        }
    }
}

impl ConfigTaskQueue {
    #[must_use]
    pub fn new() -> Self {
        Self::default()
    }

    /// Appends a task to a stage.
    ///
    /// A stage that has already been passed will never run the task, so queue work before
    /// configuration starts, or from a task in an earlier stage.
    pub fn push(&mut self, stage: ConfigStage, task: ConfigTask) {
        match stage {
            ConfigStage::BeforeRegistries => self.before.push_back(task),
            ConfigStage::AfterRegistries => self.after.push_back(task),
        }
    }

    /// Whether configuration still has work to do or an answer to wait for.
    #[must_use]
    pub fn is_pending(&self) -> bool {
        self.waiting.is_some() || self.cursor != Cursor::Done
    }

    /// The channel the current task is waiting on, if any.
    #[must_use]
    pub fn awaited_channel(&self) -> Option<&str> {
        self.waiting.as_ref().map(|task| task.ack_channel.as_str())
    }

    /// Lets the sequence move past the early stage into the registry send.
    ///
    /// Held back because the two halves are driven by different things: early tasks go out
    /// as soon as they are queued, since a mod loader decides whether the server is modded
    /// long before configuration ends, while registries wait for the client to answer the
    /// known-packs exchange as vanilla requires.
    pub const fn allow_registries(&mut self) {
        self.registries_allowed = true;
    }

    /// Advances the sequence and reports what the connection should do.
    ///
    /// Call repeatedly until it returns [`ConfigStep::Wait`].
    pub fn next_step(&mut self) -> ConfigStep {
        if self.waiting.is_some() {
            return ConfigStep::Wait;
        }

        loop {
            match self.cursor {
                Cursor::BeforeRegistries => {
                    if let Some(task) = self.before.pop_front() {
                        return self.start(task);
                    }
                    if !self.registries_allowed {
                        // Early tasks are flushed as they are queued; the registry send
                        // still waits for the client's known packs.
                        return ConfigStep::Wait;
                    }
                    self.cursor = Cursor::Registries;
                }
                Cursor::Registries => {
                    self.cursor = Cursor::AfterRegistries;
                    return ConfigStep::SendRegistries;
                }
                Cursor::AfterRegistries => {
                    if let Some(task) = self.after.pop_front() {
                        return self.start(task);
                    }
                    self.cursor = Cursor::Finishing;
                }
                Cursor::Finishing => {
                    self.cursor = Cursor::Done;
                    return ConfigStep::Finish;
                }
                Cursor::Done => return ConfigStep::Wait,
            }
        }
    }

    /// Records a task as started, holding the queue if it expects an answer.
    fn start(&mut self, task: ConfigTask) -> ConfigStep {
        let ConfigTask {
            id,
            payload,
            ack_channel,
        } = task;

        if let Some(ack_channel) = ack_channel {
            self.waiting = Some(Waiting {
                id: id.clone(),
                ack_channel,
            });
        }

        ConfigStep::Send { id, payload }
    }

    /// Completes the waiting task if `channel` is the one it expected.
    ///
    /// Returns the completed task's id, or `None` if the message was unrelated.
    pub fn acknowledge(&mut self, channel: &str) -> Option<String> {
        if self.awaited_channel() == Some(channel) {
            self.waiting.take().map(|task| task.id)
        } else {
            None
        }
    }
}

/// Parses a `minecraft:register` / `minecraft:unregister` payload.
///
/// The body is a list of channel names separated by NUL bytes. Malformed entries are
/// skipped rather than failing the connection, matching how vanilla treats the channel.
#[must_use]
pub fn parse_channel_list(data: &[u8]) -> Vec<String> {
    data.split(|&byte| byte == 0)
        .filter_map(|raw| {
            if raw.is_empty() {
                return None;
            }
            core::str::from_utf8(raw)
                .ok()
                .map(|channel| channel.trim().to_string())
        })
        .filter(|channel| !channel.is_empty())
        .collect()
}

#[cfg(test)]
mod tests {
    use super::*;

    /// Drives the queue to completion, recording what it asked for at each step.
    fn drain(queue: &mut ConfigTaskQueue) -> Vec<String> {
        let mut steps = Vec::new();
        loop {
            match queue.next_step() {
                ConfigStep::Send { id, payload } => {
                    let channel =
                        payload.map_or_else(|| "-".to_string(), |payload| payload.channel);
                    steps.push(format!("send {id} on {channel}"));
                }
                ConfigStep::SendRegistries => steps.push("registries".to_string()),
                ConfigStep::Finish => steps.push("finish".to_string()),
                ConfigStep::Wait => return steps,
            }
        }
    }

    fn task(id: &str) -> ConfigTask {
        ConfigTask::fire_and_forget(id, format!("test:{id}"), Bytes::new())
    }

    #[test]
    fn parses_nul_separated_channels() {
        let payload = b"minecraft:brand\0neoforge:frozen_registry\0";
        assert_eq!(
            parse_channel_list(payload),
            vec![
                "minecraft:brand".to_string(),
                "neoforge:frozen_registry".to_string()
            ]
        );
    }

    #[test]
    fn skips_invalid_utf8_entries() {
        let payload = [b"ok:one".as_slice(), &[0, 0xff, 0xfe, 0], b"ok:two"].concat();
        assert_eq!(
            parse_channel_list(&payload),
            vec!["ok:one".to_string(), "ok:two".to_string()]
        );
    }

    #[test]
    fn empty_queue_sends_registries_then_finishes() {
        let mut queue = ConfigTaskQueue::new();
        queue.allow_registries();
        assert_eq!(drain(&mut queue), vec!["registries", "finish"]);
        assert!(!queue.is_pending());
    }

    /// Early tasks go out as soon as they are queued. The registry send does not, because
    /// vanilla ties it to the known-packs exchange — and a mod loader gives up long before
    /// that, so waiting to flush the two together loses the connection.
    #[test]
    fn early_tasks_flush_before_registries_are_allowed() {
        let mut queue = ConfigTaskQueue::new();
        queue.push(ConfigStage::BeforeRegistries, task("declare"));

        assert_eq!(
            drain(&mut queue),
            vec!["send declare on test:declare"],
            "the early task goes out, the registries wait"
        );
        assert!(queue.is_pending());

        queue.allow_registries();
        assert_eq!(drain(&mut queue), vec!["registries", "finish"]);
    }

    #[test]
    fn finish_is_reported_only_once() {
        let mut queue = ConfigTaskQueue::new();
        queue.allow_registries();
        drain(&mut queue);
        assert!(
            drain(&mut queue).is_empty(),
            "a drained queue must not ask to finish again"
        );
    }

    #[test]
    fn stages_run_on_the_correct_side_of_the_registry_send() {
        let mut queue = ConfigTaskQueue::new();
        queue.allow_registries();
        queue.push(ConfigStage::AfterRegistries, task("late"));
        queue.push(ConfigStage::BeforeRegistries, task("early"));

        assert_eq!(
            drain(&mut queue),
            vec![
                "send early on test:early",
                "registries",
                "send late on test:late",
                "finish"
            ]
        );
    }

    #[test]
    fn a_waiting_task_blocks_the_registry_send_until_acknowledged() {
        let mut queue = ConfigTaskQueue::new();
        queue.allow_registries();
        queue.push(
            ConfigStage::BeforeRegistries,
            ConfigTask::awaiting_ack(
                "sync_registries",
                "neoforge:frozen_registry_sync_start",
                Bytes::new(),
                "neoforge:frozen_registry_sync_completed",
            ),
        );

        assert_eq!(
            drain(&mut queue),
            vec!["send sync_registries on neoforge:frozen_registry_sync_start"],
            "registries must not be sent while the client still owes an answer"
        );
        assert!(queue.is_pending());
        assert_eq!(queue.acknowledge("some:other"), None);

        assert_eq!(
            queue
                .acknowledge("neoforge:frozen_registry_sync_completed")
                .as_deref(),
            Some("sync_registries")
        );
        assert_eq!(drain(&mut queue), vec!["registries", "finish"]);
    }
}
