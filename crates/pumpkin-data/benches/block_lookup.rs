#![allow(
    clippy::all,
    clippy::pedantic,
    clippy::nursery,
    clippy::cargo,
    clippy::print_stdout
)]

//! Measures the block lookups that the runtime registry made branch on a base/dynamic
//! split. Bounds are hard-coded so the same benchmark compiles before and after that
//! change, which is what makes the two runs comparable.

use std::hint::black_box;

use criterion::{Criterion, criterion_group, criterion_main};
use pumpkin_data::{Block, BlockId, BlockState, BlockStateId, item::Item};

const BLOCK_COUNT: u16 = 1196;
const STATE_COUNT: u16 = 32366;
const ITEM_COUNT: u16 = 1537;

#[inline(never)]
fn sum_blocks(ids: &[BlockId]) -> u64 {
    let mut acc = 0u64;
    for id in ids {
        acc += u64::from(Block::from_id(black_box(*id)).item_id);
    }
    acc
}

#[inline(never)]
fn sum_states(ids: &[BlockStateId]) -> u64 {
    let mut acc = 0u64;
    for id in ids {
        acc += u64::from(BlockState::from_id(black_box(*id)).luminance);
    }
    acc
}

#[inline(never)]
fn sum_owners(ids: &[BlockStateId]) -> u64 {
    let mut acc = 0u64;
    for id in ids {
        acc += u64::from(BlockId::from_state_id(black_box(*id)).as_u16());
    }
    acc
}

#[inline(never)]
fn sum_items(ids: &[u16]) -> u64 {
    let mut acc = 0u64;
    for id in ids {
        acc += Item::from_id(black_box(*id)).map_or(0, |item| u64::from(item.id));
    }
    acc
}

fn benches(c: &mut Criterion) {
    let block_ids: Vec<BlockId> = (0..BLOCK_COUNT).filter_map(BlockId::new).collect();
    let state_ids: Vec<BlockStateId> = (0..STATE_COUNT).filter_map(BlockStateId::new).collect();

    c.bench_function("Block::from_id", |b| {
        b.iter(|| black_box(sum_blocks(black_box(&block_ids))))
    });
    c.bench_function("BlockState::from_id", |b| {
        b.iter(|| black_box(sum_states(black_box(&state_ids))))
    });
    c.bench_function("BlockId::from_state_id", |b| {
        b.iter(|| black_box(sum_owners(black_box(&state_ids))))
    });

    let item_ids: Vec<u16> = (0..ITEM_COUNT).collect();
    c.bench_function("Item::from_id", |b| {
        b.iter(|| black_box(sum_items(black_box(&item_ids))))
    });
}

criterion_group!(block_lookup, benches);
criterion_main!(block_lookup);
