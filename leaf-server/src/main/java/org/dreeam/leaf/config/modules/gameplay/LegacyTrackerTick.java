package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class LegacyTrackerTick extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName();
    }

    public static boolean enabled = true;

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(
            getBasePath() + ".legacy-tracker-tick",
            enabled,
            config.pickStringRegionBased(
                "Uses the older deterministic entity tracking order for more consistent updates.",
                "使用更旧的确定性实体追踪顺序以获得更一致的追踪更新。"
            )
        );
    }
}
