package io.github.openminigameserver.arcadium.via;


import io.netty.buffer.ByteBuf;
import org.apache.commons.lang3.NotImplementedException;
import us.myles.ViaVersion.api.Via;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.boss.BossBar;
import us.myles.ViaVersion.api.boss.BossColor;
import us.myles.ViaVersion.api.boss.BossStyle;
import us.myles.ViaVersion.api.data.UserConnection;
import us.myles.ViaVersion.api.protocol.ProtocolRegistry;
import us.myles.ViaVersion.protocols.base.ProtocolInfo;

import java.util.SortedSet;
import java.util.UUID;

public class MinestomViaAPI implements ViaAPI<Void> {
    MinestomViaAPI() {
    }

    @Override
    public int getPlayerVersion(Void aVoid) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int getPlayerVersion(UUID uuid) {
        throw new UnsupportedOperationException();    }

    @Override
    public boolean isInjected(UUID uuid) {
        return true;
    }

    @Override
    public String getVersion() {
        return Via.getPlatform().getPluginVersion();
    }

    @Override
    public void sendRawPacket(Void aVoid, ByteBuf byteBuf) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendRawPacket(UUID uuid, ByteBuf byteBuf) throws IllegalArgumentException {
        throw new UnsupportedOperationException();
    }

    @Override
    public BossBar createBossBar(String title, BossColor color, BossStyle style) {
        return createBossBar(title, 1, color, style);
    }

    @Override
    public BossBar createBossBar(String title, float health, BossColor color, BossStyle style) {
        throw new NotImplementedException("");
    }

    @Override
    public SortedSet<Integer> getSupportedVersions() {
        return ProtocolRegistry.getSupportedVersions();
    }
}