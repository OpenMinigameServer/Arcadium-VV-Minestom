package io.github.openminigameserver.arcadium.mixins;

import io.github.openminigameserver.arcadium.via.MinestomViaInjector;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import net.minestom.server.network.netty.codec.PacketDecoder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.exception.CancelDecoderException;

import java.util.List;

@Mixin(PacketDecoder.class)
public class PacketDecoderMixin {

    @Inject(method = "decode", at = @At("HEAD"), cancellable = true)
    public void onDecode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> list, CallbackInfo ci) {
        UserConnection info = ctx.channel().attr(MinestomViaInjector.getUserConnectionAttribute()).get();
        try {
            if (!info.checkIncomingPacket()) throw CancelDecoderException.generate(null);
            if (!info.shouldTransformPacket()) {
                return;
            }
            info.transformIncoming(buf, CancelDecoderException::new);
        } catch (Exception e) {
            if (e instanceof CancelDecoderException) {
                ci.cancel();
            } else {
                e.printStackTrace();
            }
        }
    }

}
