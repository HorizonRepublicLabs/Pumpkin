# examples

Tools that speak the server's protocols, for developing the server itself.

| | |
|---|---|
| `neoforge-probe` | A synthetic NeoForge client. Verifies the modded negotiation without needing a real client and a person at a keyboard. |

Server-side plugins for NeoForge mods live in their own repository:
<https://github.com/HorizonRepublicLabs/pumpkin-mod-plugins>. They track this one through
the published `pumpkin-plugin-api` crate, so they are built and deployed separately — see
that repository's README.
