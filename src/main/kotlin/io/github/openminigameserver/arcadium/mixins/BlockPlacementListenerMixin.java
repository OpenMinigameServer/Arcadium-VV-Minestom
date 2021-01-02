package io.github.openminigameserver.arcadium.mixins;

import net.minestom.server.entity.Player;
import net.minestom.server.instance.Chunk;
import net.minestom.server.listener.BlockPlacementListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(BlockPlacementListener.class)
public class BlockPlacementListenerMixin {

    @Redirect(method = "listener", at = @At(value = "INVOKE", target = "Lnet/minestom/server/instance/Chunk;" +
            "sendChunkSectionUpdate(ILnet/minestom/server/entity/Player;)V"))
    private static void onSendChunkSection(Chunk chunk, int section, Player player) {
        chunk.sendChunk(player);
    }

}
