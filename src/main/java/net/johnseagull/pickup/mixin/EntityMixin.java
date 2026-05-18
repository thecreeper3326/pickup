package net.johnseagull.pickup.mixin;

import net.johnseagull.figManager.FigManager;
import net.johnseagull.pickup.Figs;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Inject(method = "isPickable", at = @At("HEAD"), cancellable = true)
    private void itemPickup(CallbackInfoReturnable<Boolean> cir) {
        if ((Entity) (Object) this instanceof ItemEntity) {
            cir.setReturnValue(true);
        }
    }
    @Inject(method = "getDimensions", at = @At("HEAD"), cancellable = true)
    private void itemDementia(CallbackInfoReturnable<EntityDimensions> cir) {
        if (((Entity) (Object) this) instanceof ItemEntity) {
            if (((Figs)FigManager.FIGS).enableModifiedHitbox.value){
                cir.setReturnValue(EntityDimensions.scalable(((Figs)FigManager.FIGS).hitboxWidth.value,((Figs)FigManager.FIGS).hitboxHeight.value));
            }
        }
    }
    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At("TAIL"))
    private void init(EntityType<?> type, Level level, CallbackInfo ci) {
        if ((Object) this instanceof ItemEntity) {
            ((ItemEntity) (Object) this).refreshDimensions();
        }
    }

}