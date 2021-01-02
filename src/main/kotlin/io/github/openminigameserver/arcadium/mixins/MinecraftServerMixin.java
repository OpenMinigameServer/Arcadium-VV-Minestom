package io.github.openminigameserver.arcadium.mixins;

import net.minestom.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {
    @Inject(method = "hasGroupedPacket", at = @At("HEAD"), cancellable = true)
    private static void hasGroupedPacket(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }

    @Inject(method = "hasPacketCaching", at = @At("HEAD"), cancellable = true)
    private static void hasPacketCaching(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
