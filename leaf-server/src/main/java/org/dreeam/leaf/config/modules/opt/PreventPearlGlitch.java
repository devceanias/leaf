package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class PreventPearlGlitch extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName() + ".prevent-pearl-glitch";
    }

    public static boolean enabled = false;

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(getBasePath(), enabled);
    }
}
