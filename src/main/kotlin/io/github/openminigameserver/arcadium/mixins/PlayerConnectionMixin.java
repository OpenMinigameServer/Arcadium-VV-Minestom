package io.github.openminigameserver.arcadium.mixins;

import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.play.EntityPropertiesPacket;
import net.minestom.server.network.player.PlayerConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(PlayerConnection.class)
public class PlayerConnectionMixin {
    @Inject(method = "shouldSendPacket", at = @At("HEAD"), cancellable = true)
    public void onSendPacket(ServerPacket serverPacket, CallbackInfoReturnable<Boolean> cir) {
        if (serverPacket instanceof EntityPropertiesPacket) {
            EntityPropertiesPacket entityPropertiesPacket = (EntityPropertiesPacket) serverPacket;
            entityPropertiesPacket.properties =
                    Arrays.stream(entityPropertiesPacket.properties).filter(it -> it.attribute.getKey().startsWith(
                            "generic")).toArray(EntityPropertiesPacket.Property[]::new);
        }
    }
}

