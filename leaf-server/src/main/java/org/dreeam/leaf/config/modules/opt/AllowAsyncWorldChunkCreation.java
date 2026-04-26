package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class AllowAsyncWorldChunkCreation extends ConfigModules {
    public static boolean enabled = true;

    public String getBasePath() {
        return EnumConfigCategory.PERF.getBaseKeyName() + ".allow-async-world-chunk-creation";
    }

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(getBasePath(), enabled);
    }
}
