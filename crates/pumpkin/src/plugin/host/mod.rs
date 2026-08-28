//! Host behaviour shared by every plugin loader.
//!
//! The wasm and JVM loaders differ only in how a call arrives. What the call *does* lives
//! here, so the two cannot drift.

pub mod registry;
