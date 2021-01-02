
package io.github.openminigameserver.arcadium.via;

import net.minestom.server.MinecraftServer;
import net.minestom.server.timer.Task;
import net.minestom.server.utils.time.TimeUnit;
import us.myles.ViaVersion.api.ViaAPI;
import us.myles.ViaVersion.api.ViaVersionConfig;
import us.myles.ViaVersion.api.command.ViaCommandSender;
import us.myles.ViaVersion.api.configuration.ConfigurationProvider;
import us.myles.ViaVersion.api.platform.TaskId;
import us.myles.ViaVersion.api.platform.ViaConnectionManager;
import us.myles.ViaVersion.api.platform.ViaPlatform;
import us.myles.viaversion.libs.gson.JsonObject;

import java.io.File;
import java.util.UUID;
import java.util.logging.Logger;

public class MinestomViaPlatform implements ViaPlatform {
    private boolean isEnabled = false;
    private MinestomViaAPI viaAPI = new MinestomViaAPI();

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    private MinestomViaConfig config = new MinestomViaConfig();

    @Override
    public Logger getLogger() {
        return Logger.getLogger(MinestomViaPlatform.class.getName());
    }

    @Override
    public String getPlatformName() {
        return "Minestom";
    }

    @Override
    public String getPlatformVersion() {
        return null;
    }

    @Override
    public String getPluginVersion() {
        return "3.3.0";
    }

    @Override
    public TaskId runAsync(Runnable runnable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TaskId runSync(Runnable runnable) {
        Task schedule = MinecraftServer.getSchedulerManager().buildTask(runnable).schedule();
        return () -> schedule;
    }

    @Override
    public TaskId runSync(Runnable runnable, Long aLong) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TaskId runRepeatingSync(Runnable runnable, Long aLong) {
        Task schedule = MinecraftServer.getSchedulerManager().buildTask(runnable).repeat(aLong, TimeUnit.TICK).schedule();
        return () -> schedule;
    }

    @Override
    public void cancelTask(TaskId taskId) {
        ((Task) taskId.getObject()).cancel();
    }

    @Override
    public ViaCommandSender[] getOnlinePlayers() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sendMessage(UUID uuid, String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean kickPlayer(UUID uuid, String s) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isPluginEnabled() {
        return isEnabled;
    }

    @Override
    public ViaAPI getApi() {
        return viaAPI;
    }

    @Override
    public ViaVersionConfig getConf() {
        return config;
    }

    @Override
    public ConfigurationProvider getConfigurationProvider() {
        throw new UnsupportedOperationException();
    }

    @Override
    public File getDataFolder() {
        return null;
    }

    @Override
    public void onReload() {}

    @Override
    public JsonObject getDump() {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isOldClientsAllowed() {
        return true;
    }

    private ViaConnectionManager connectionManager = new ViaConnectionManager();
    @Override
    public ViaConnectionManager getConnectionManager() {
        return connectionManager;
    }
}