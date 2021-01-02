package io.github.openminigameserver.arcadium.via;

import io.netty.util.AttributeKey;
import net.minestom.server.MinecraftServer;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.api.platform.ViaInjector;
import us.myles.viaversion.libs.gson.JsonObject;

public class MinestomViaInjector implements ViaInjector {
    private static final AttributeKey<UserConnection> userConnectionAttribute = AttributeKey.newInstance("viaversion" +
            "-connection");

    public static AttributeKey<UserConnection> getUserConnectionAttribute() {
        return userConnectionAttribute;
    }

    @Override
    public void inject() throws Exception {
    }

    @Override
    public void uninject() throws Exception {
    }

    @Override
    public int getServerProtocolVersion() throws Exception {
        return MinecraftServer.PROTOCOL_VERSION;
    }

    @Override
    public String getEncoderName() {
        return null;
    }

    @Override
    public String getDecoderName() {
        return null;
    }

    @Override
    public JsonObject getDump() {
        return null;
    }
}
