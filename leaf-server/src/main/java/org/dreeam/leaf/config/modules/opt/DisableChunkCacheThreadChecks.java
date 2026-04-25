package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class DisableChunkCacheThreadChecks extends ConfigModules {
    public static boolean enabled = false;

    public String getBasePath() {
        return EnumConfigCategory.PERF.getBaseKeyName() + ".disable-chunk-cache-thread-checks";
    }

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(getBasePath(), enabled);
    }
}
