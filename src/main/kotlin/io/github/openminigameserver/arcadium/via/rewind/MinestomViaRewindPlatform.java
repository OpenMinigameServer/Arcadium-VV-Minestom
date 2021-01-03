package io.github.openminigameserver.arcadium.via.rewind;

import de.gerrygames.viarewind.api.ViaRewindPlatform;
import us.myles.ViaVersion.api.Via;

import java.util.logging.Logger;

public class MinestomViaRewindPlatform implements ViaRewindPlatform {
    @Override
    public Logger getLogger() {
        return Via.getPlatform().getLogger();
    }
}
