# registry-plugin

Registers a block, an item and an entity type at load, to show the content registry working
end to end: plugin registers → server freezes ids → a NeoForge client receives them in the
frozen registry sync.

## Build

```sh
cargo build --release --target wasm32-wasip2
cp target/wasm32-wasip2/release/registry_plugin.wasm ../../plugins/
```

`wasm32-wasip2` emits a component directly, so no `cargo component` is needed.

## Run

The server loads anything ending in `.wasm` from `plugins/`. Unsigned plugins need

```toml
[plugins]
allow_unsigned = true
```

in `pumpkin.toml`. Then start the server and look for the plugin's log lines.
