package org.dreeam.leaf.config.modules.misc;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class DisableServerDebug extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.MISC.getBaseKeyName() + ".disable-server-debug";
    }

    public static boolean enabled = true;

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(getBasePath(), enabled);
    }
}
