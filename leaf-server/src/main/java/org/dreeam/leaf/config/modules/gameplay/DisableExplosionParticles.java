package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class DisableExplosionParticles extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName() + ".disable-explosion-particles";
    }

    public static boolean enabled = true;

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(getBasePath(), enabled);
    }
}
