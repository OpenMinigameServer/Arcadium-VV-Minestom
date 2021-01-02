package io.github.openminigameserver.arcadium.mixins;

import io.netty.bootstrap.ServerBootstrap;
import net.minestom.server.network.netty.NettyServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(NettyServer.class)
public interface NettyServerAccessor {
    @Accessor("bootstrap")
    ServerBootstrap getServerBootstrap();
}
