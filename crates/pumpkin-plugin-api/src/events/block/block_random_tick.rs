use crate::wit::pumpkin::plugin::event::{BlockRandomTickEventData, Event, EventType};

use super::super::FromIntoEvent;

/// An event that occurs when a block a plugin registered is chosen for a random tick.
///
/// The associated [`BlockRandomTickEventData`] carries the world, the block, the state it
/// is in and its position. Only registered blocks are reported: a generated one is ticked
/// by the server's own code for it.
///
/// This is where such a block acts on its own — a crop grows here, and nowhere else.
pub struct BlockRandomTickEvent;
impl FromIntoEvent for BlockRandomTickEvent {
    const EVENT_TYPE: EventType = EventType::BlockRandomTickEvent;
    type Data = BlockRandomTickEventData;

    fn data_from_event(event: Event) -> Self::Data {
        match event {
            Event::BlockRandomTickEvent(data) => data,
            _ => panic!("unexpected event"),
        }
    }

    fn data_into_event(data: Self::Data) -> Event {
        Event::BlockRandomTickEvent(data)
    }
}
