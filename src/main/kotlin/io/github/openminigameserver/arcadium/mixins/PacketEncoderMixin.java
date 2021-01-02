package io.github.openminigameserver.arcadium.mixins;

import io.github.openminigameserver.arcadium.via.MinestomViaInjector;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.Attribute;
import net.minestom.server.network.netty.codec.PacketEncoder;
import net.minestom.server.network.packet.server.ServerPacket;
import net.minestom.server.network.packet.server.play.EntityPropertiesPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.exception.CancelDecoderException;
import us.myles.ViaVersion.exception.CancelEncoderException;

@Mixin(PacketEncoder.class)
public class PacketEncoderMixin {

    @Inject(method = "encode", at = @At("TAIL"), cancellable = true)
    public void onEncode(ChannelHandlerContext ctx, ServerPacket packet, ByteBuf buf, CallbackInfo ci) {
        Attribute<UserConnection> connectionAttribute =
                ctx.channel().attr(MinestomViaInjector.getUserConnectionAttribute());
        UserConnection info = connectionAttribute.get();

        try {
            if (!info.checkOutgoingPacket()) throw CancelEncoderException.generate(null);
            if (!info.shouldTransformPacket()) {
                return;
            }

            System.out.println(packet.getClass().getSimpleName());
            info.transformOutgoing(buf, CancelEncoderException::new);
        } catch (Exception e) {
            if (e instanceof CancelEncoderException) {
                ci.cancel();
            } else {
                e.printStackTrace();
            }
        }
    }
}
