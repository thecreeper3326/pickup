# Pickup

Pickup is a mod that lets players pick up items by right-clicking them. (or whatever their interact key is set to) It works completely server-side so any vanilla client can join. The only client-side feature is the configuration modification system, an extra system for modifying the mod's configuration while the server is running (as opposed to editing the JSON and restarting).

## Configuration

### Core mechanics
* Enable/disable modified pickup behavior - toggles the mod's right-click-to-pick-up feature
* Enable/disable vanilla pickup behavior - toggles Minecraft's walk-over-to-pick-up system

Both of the above can be turned on and off independently of each other.
You can also override the default item entity's hitbox with custom dimensions. This makes targeting easier because the vanilla item entities have a fairly small hitbox. (The hitbox has a maximum limit of 1x1x1 blocks to prevent cramming)

### Extras
* Item name and count overlay - shows the name and amount of the item the player is looking at
* Glowing item overlay - gives the targeted item a glowing effect
* Overlay range - how far the player has to be for the overlays to appear (separate from actually being able to interact with them)
* Use default range - uses the player's `EntityInteractionRange` attribute instead of the provided value
* Pickup to current hand - instead of stacking to the player's hotbar/inventory like normal, a picked up item will be placed in the active slot of the player if it is empty



Coming soon to a Modrinth near you!

