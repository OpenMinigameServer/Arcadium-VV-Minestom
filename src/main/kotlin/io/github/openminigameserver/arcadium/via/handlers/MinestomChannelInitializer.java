package io.github.openminigameserver.arcadium.via.handlers;

import io.github.openminigameserver.arcadium.via.MinestomViaInjector;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import org.jetbrains.annotations.NotNull;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.api.protocol.ProtocolPipeline;

import java.lang.reflect.Method;

public class MinestomChannelInitializer extends ChannelInitializer<Channel> {
    private final ChannelInitializer<?> original;
    private final boolean clientSide;
    private static Method initChannel;

    public MinestomChannelInitializer(ChannelInitializer<?> original, boolean clientSide) {
        this.original = original;
        this.clientSide = clientSide;
    }

    static {
        try {
            initChannel = ChannelInitializer.class.getDeclaredMethod("initChannel", Channel.class);
            initChannel.setAccessible(true);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void initChannel(@NotNull Channel channel) throws Exception {
        initChannel.invoke(original, channel);

        UserConnection user = new UserConnection(channel, clientSide);
        new ProtocolPipeline(user);
        channel.attr(MinestomViaInjector.getUserConnectionAttribute()).set(user);
    }
}