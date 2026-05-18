# Pickup

Pickup is a mod that lets players pick up items by right-clicking them. (or whatever their interact key is set to) It works completely server-side so any vanilla client can join. The only client-side feature is the configuration modification system, an extra system for modifying the mod's configuration while the server is running (as opposed to editing the JSON and restarting).

It is also highly customizable. You can change all of the following:
- Enable/disble modified pickup behavior - toggles the mod's right-click-to-pick-up feature
- Enable/disable vanilla pickup behavior - toggles Minecraft's walk-over-to-pick-up system

Both of the above can be turned on and off independantly of each other.

In addition to the new pickup mechanic, the mod also adds some extra QOL features too:
- Item name and count overlay - shows the name and amount of the item the player is looking at
- Glowing item overlay - gives the targetted item a glowing effect
- Overlay range - how far the player has to be for the overlays to appear (seperate from actually being able to interact with them)
- Use deafult range - uses the player's `EntityInteractionRange` attribute instead of the provided value
- Pickup to current hand - instead of stacking to the player's hotbar/inventory like normal, a picked up item will be placed in the active slot of the player if it is empty

Finally, you can override the default item entity's hitbox with custom dimensions. This makes targeting easier because the vanilla item entities have a fairly small hitbox. (The hitbox has a maximum limit of 1x1x1 blocks)

Coming soon to a Modrinth near you!
