package org.dreeam.leaf.config.modules.fixes;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class FixRightClickOffhandCooldown extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.FIXES.getBaseKeyName();
    }

    public static boolean enabled = true;

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(
            getBasePath() + ".fix-right-click-offhand-cooldown",
            enabled,
            config.pickStringRegionBased(
                "Prevents right-click actions and offhand interactions from resetting attack cooldown.",
                "防止右键操作和副手交互重置攻击冷却。"
            )
        );
    }
}
