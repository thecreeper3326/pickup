package net.johnseagull.pickup;

import net.johnseagull.figManager.FigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public class Pickup implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("Pickup");
    private static int c = 0;

    @Override
    public void onInitialize() {
        FigManager figManager = new FigManager();
        figManager.init("pickup", "1.1", Figs.instance);
        UseEntityCallback.EVENT.register((player, level, hand, entity, hitResult) -> {
            Figs f = (Figs) FigManager.FIGS;
            if (hand.equals(InteractionHand.MAIN_HAND) && f.newBehavior.value) {
                if (entity instanceof ItemEntity item) {
                    player.playSound(SoundEvents.ITEM_PICKUP);
                    if (player.getMainHandItem().isEmpty() && f.useCurrentSlot.value) {
                        entity.discard();
                        if (player.getInventory().hasAnyOf(Set.of(item.getItem().getItem()))) {
                            player.addItem(item.getItem());
                        } else {
                            player.setItemInHand(hand, item.getItem());
                        }
                        player.awardStat(Stats.ITEM_PICKED_UP.get(item.getItem().getItem()), item.getItem().getCount());

                    } else {
                        entity.discard();
                        player.awardStat(Stats.ITEM_PICKED_UP.get(item.getItem().getItem()), item.getItem().getCount());
                        player.addItem(item.getItem());
                    }

                    return InteractionResult.SUCCESS;
                } else {
                    return InteractionResult.CONSUME;
                }
            } else {
                return InteractionResult.CONSUME;
            }
        });
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            Figs f = (Figs) FigManager.FIGS;
            float range = f.overlayRange.value;
            for (ServerPlayer player : server.getPlayerList().getPlayers()) {
                if (f.usePlayerRange.value) {
                    range = (float) player.entityInteractionRange();
                }
                Vec3 eyePos = player.getEyePosition();
                Vec3 end = eyePos.add(player.getLookAngle().scale(range));
                BlockHitResult hit = player.level().clip(new ClipContext(
                        eyePos, end,
                        ClipContext.Block.COLLIDER,
                        ClipContext.Fluid.ANY,
                        player
                ));
                Vec3 newEnd;
                ItemEntity coolITem = null;
                if (hit.getType() == HitResult.Type.BLOCK) {
                    newEnd = hit.getLocation();
                } else if (hit.getType() == HitResult.Type.ENTITY) {
                    newEnd = hit.getLocation();
                } else {
                    newEnd = end;
                }
                List<ItemEntity> items = player.level().getEntitiesOfClass(
                        ItemEntity.class,
                        player.getBoundingBox().expandTowards(player.getLookAngle().scale(range))
                );
                for (ItemEntity item : items) {
                    ((ItemEntityInterface) item).pickup$setPickup(!f.newBehavior.value);
                    ((ItemEntityInterface) item).pickup$setBigHitbox(f.enableModifiedHitbox.value);
                    item.refreshDimensions();

                    if (!item.getBoundingBox().clip(eyePos, newEnd).equals(Optional.empty()) && coolITem == null) {
                        coolITem = item;
                    } else {
                        item.setCustomName(Component.literal(""));
                        item.setCustomNameVisible(false);
                        item.setGlowingTag(false);
                    }
                }
                for (ItemEntity item : items) {
                    if (item == coolITem) {
                        if (f.itemTags.value) {
                            Component name = Component.empty()
                                    .append(item.getItem().getHoverName())
                                    .append(Component.literal(" x" + item.getItem().getCount()).withStyle(ChatFormatting.GRAY));
                            item.setCustomName(name);
                            item.setCustomNameVisible(true);
                        }
                        if (f.itemGlow.value) {
                            item.setGlowingTag(true);
                        }
                    }else {
                        item.setCustomName(Component.literal(""));
                        item.setCustomNameVisible(false);
                        item.setGlowingTag(false);
                    }
                }
            }
        });
    }
}
