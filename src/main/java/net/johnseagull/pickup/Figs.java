package net.johnseagull.pickup;

import net.johnseagull.figManager.Fig.*;
import net.minecraft.ChatFormatting;

public class Figs {
    public static Figs instance = new Figs();
    public DividerFig divider = new DividerFig("General settings", ChatFormatting.WHITE,true,false,false);
    public BooleanFig newBehavior = new BooleanFig("Enable right click to pickup","Lets players right click to pick items up instead of walking on top of them",true);
    public BooleanFig vanillaBehavior = new BooleanFig("Vanilla pickup behavior","Lets players also pick up items normally",false);
    public BooleanFig useCurrentSlot = new BooleanFig("Pickup to current slot","[If right click pickup is enabled] Picked up items will be placed in the player's main hand if empty. If this is disabled or the player has an item in their hand, it will be picked up as it would be in vanilla", true);
    public DividerFig divider1 = new DividerFig("Visual settings", ChatFormatting.WHITE,true,false,false);
    public BooleanFig itemGlow = new BooleanFig("Item Glow","Makes the targeted item entity have a glowing effect",true);
    public BooleanFig itemTags = new BooleanFig("Item Tags","Shows item details (<name> x<count>) above the targeted item entity",true);
    public FloatFig overlayRange = new FloatFig("Overlay Range","The number of blocks the player will have to be within in order for glow effects/tags to display. Only effective if [Use Player's Range] is disabled",4.5f,-1f,32f);
    public BooleanFig usePlayerRange = new BooleanFig("Use Player's Range","Use the player's entity interaction range instead of the provided fixed value",true);
    public DividerFig divider2 = new DividerFig("Hitbox settings",ChatFormatting.WHITE,true,false,false);
    public BooleanFig enableModifiedHitbox = new BooleanFig("Modified Hitbox","Modify hitboxes of item entities to make interaction easier",true);
    public FloatFig hitboxWidth = new FloatFig("Width [Diameter]","Diameter of the hitbox. Default 0.25F",0.3f,0.1f,1f);
    public FloatFig hitboxHeight = new FloatFig("Height","Height of the hitbox. Default 0.25F",0.5f,0.1f,1f);
}
