package io.github.openminigameserver.arcadium.mixins;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ServerBootstrap.class)
public interface ServerBootstrapMixin {
    @Accessor("childHandler")
    ChannelHandler getChildHandler();
}
