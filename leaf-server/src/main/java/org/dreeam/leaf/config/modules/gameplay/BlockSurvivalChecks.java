package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class BlockSurvivalChecks extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName();
    }

    public static boolean enabled = false;

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(
            getBasePath() + ".block-survival-checks",
            enabled,
            config.pickStringRegionBased(
                "Toggles block survival checks (i.e. if disabled, cactus won't break by nearby blocks).",
                "禁用方块生存检查（例如仙人掌不会因附近方块而破坏）。"
            )
        );
    }
}
