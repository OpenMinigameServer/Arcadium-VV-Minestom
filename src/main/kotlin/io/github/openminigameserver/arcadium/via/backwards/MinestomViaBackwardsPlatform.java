package io.github.openminigameserver.arcadium.via.backwards;

import nl.matsv.viabackwards.api.ViaBackwardsPlatform;

import java.io.File;
import java.util.logging.Logger;

public class MinestomViaBackwardsPlatform implements ViaBackwardsPlatform {
    @Override
    public Logger getLogger() {
        return Logger.getLogger(MinestomViaBackwardsPlatform.class.getName());
    }

    @Override
    public void disable() {

    }

    @Override
    public File getDataFolder() {
        return new File("ViaBackwards");
    }
}
