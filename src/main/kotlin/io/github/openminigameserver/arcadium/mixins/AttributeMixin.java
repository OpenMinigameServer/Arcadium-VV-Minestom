package io.github.openminigameserver.arcadium.mixins;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(net.minestom.server.attribute.Attribute.class)
public class AttributeMixin {
    @Inject(method = "isShared", at = @At("HEAD"), cancellable = true)
    public void onIsShared(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
