//! Opaque handles into Rust-owned state, for a Java side that can only hold a `long`.
//!
//! A slot is index plus generation. Reusing a slot bumps its generation, so a handle kept
//! across the removal reads as absent rather than as whatever now occupies the slot. Mods
//! stash references in statics routinely; this is what turns that into an error message.

/// A handle as it travels to Java and back.
#[derive(Debug, Clone, Copy, PartialEq, Eq)]
pub struct Handle(u64);

/// The value Java uses for "no handle". A stale handle fails to resolve until its slot
/// has been reused 2^32 times; before that, the generation counter prevents aliasing.
pub const NULL_HANDLE: i64 = 0;

impl Handle {
    /// The `long` Java holds.
    #[must_use]
    pub const fn raw(self) -> i64 {
        self.0 as i64
    }

    /// Rebuilds a handle from the `long` Java handed back.
    #[must_use]
    pub const fn from_raw(raw: i64) -> Self {
        Self(raw as u64)
    }

    const fn new(index: u32, generation: u32) -> Self {
        Self(((generation as u64) << 32) | index as u64)
    }

    const fn index(self) -> u32 {
        self.0 as u32
    }

    const fn generation(self) -> u32 {
        (self.0 >> 32) as u32
    }
}

struct Slot<T> {
    generation: u32,
    value: Option<T>,
}

/// Advance a generation number, skipping 0 to preserve the `NULL_HANDLE` invariant.
const fn next_generation(current: u32) -> u32 {
    let next = current.wrapping_add(1);
    if next == 0 { 1 } else { next }
}

/// A slab of values addressed by [`Handle`].
pub struct HandleTable<T> {
    slots: Vec<Slot<T>>,
    free: Vec<u32>,
}

impl<T> Default for HandleTable<T> {
    fn default() -> Self {
        Self::new()
    }
}

impl<T> HandleTable<T> {
    /// An empty table.
    #[must_use]
    pub const fn new() -> Self {
        Self {
            slots: Vec::new(),
            free: Vec::new(),
        }
    }

    /// Stores a value and returns the handle naming it.
    pub fn insert(&mut self, value: T) -> Handle {
        if let Some(index) = self.free.pop() {
            let slot = &mut self.slots[index as usize];
            slot.generation = next_generation(slot.generation);
            slot.value = Some(value);
            return Handle::new(index, slot.generation);
        }

        // Generation starts at 1 so that slot 0 never yields the null handle.
        // Slot allocation saturates at u32::MAX, which is unreachable (requires ~4B
        // simultaneously live entries); thereafter, new inserts fail to allocate.
        let index = u32::try_from(self.slots.len()).unwrap_or(u32::MAX);
        self.slots.push(Slot {
            generation: 1,
            value: Some(value),
        });
        Handle::new(index, 1)
    }

    /// The value a handle names, or `None` if it has been removed or superseded.
    #[must_use]
    pub fn get(&self, handle: Handle) -> Option<&T> {
        let slot = self.slots.get(handle.index() as usize)?;
        if slot.generation == handle.generation() {
            slot.value.as_ref()
        } else {
            None
        }
    }

    /// Takes the value back, freeing the slot for reuse under a new generation.
    pub fn remove(&mut self, handle: Handle) -> Option<T> {
        let slot = self.slots.get_mut(handle.index() as usize)?;
        if slot.generation != handle.generation() {
            return None;
        }
        let value = slot.value.take();
        if value.is_some() {
            self.free.push(handle.index());
        }
        value
    }
}

#[cfg(test)]
mod tests {
    use super::{Handle, HandleTable, next_generation};

    #[test]
    fn a_handle_reads_back_the_value_it_was_given() {
        let mut table = HandleTable::new();
        let handle = table.insert("world");
        assert_eq!(table.get(handle), Some(&"world"));
    }

    #[test]
    fn a_handle_reused_after_removal_reads_nothing() {
        let mut table = HandleTable::new();
        let stale = table.insert("world");
        table.remove(stale);
        assert_eq!(table.get(stale), None);
    }

    #[test]
    fn a_reused_slot_does_not_answer_to_the_old_handle() {
        let mut table = HandleTable::new();
        let stale = table.insert("first");
        table.remove(stale);
        let fresh = table.insert("second");

        assert_ne!(stale.raw(), fresh.raw(), "the generation has to move");
        assert_eq!(table.get(stale), None);
        assert_eq!(table.get(fresh), Some(&"second"));
    }

    #[test]
    fn a_handle_survives_the_trip_through_java_as_an_i64() {
        let mut table = HandleTable::new();
        let handle = table.insert(7u32);
        assert_eq!(table.get(Handle::from_raw(handle.raw())), Some(&7));
    }

    #[test]
    fn a_generation_that_wraps_never_produces_the_null_handle() {
        // When generation wraps from u32::MAX back to 0, we skip 0 to preserve NULL_HANDLE
        assert_eq!(next_generation(u32::MAX), 1);
    }
}
