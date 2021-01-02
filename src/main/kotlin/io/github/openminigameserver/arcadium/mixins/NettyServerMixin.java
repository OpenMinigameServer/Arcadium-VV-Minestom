package io.github.openminigameserver.arcadium.mixins;

import io.github.openminigameserver.arcadium.via.handlers.MinestomChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import net.minestom.server.network.netty.NettyServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(NettyServer.class)
public class NettyServerMixin {

    @Shadow
    private ServerBootstrap bootstrap;

    @Inject(method = "init", at = @At("TAIL"))
    public void onInit(CallbackInfo ci) {
        ServerBootstrapMixin bootstrap = (ServerBootstrapMixin) (this.bootstrap);
        ChannelInitializer<?> original = (ChannelInitializer<?>) bootstrap.getChildHandler();
        this.bootstrap.childHandler(new MinestomChannelInitializer(original, false));
    }

}
