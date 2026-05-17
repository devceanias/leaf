package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class SoftImmediateRespawn extends ConfigModules {
    public static boolean enabled = false;

    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName() + ".soft-immediate-respawn";
    }

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(
            getBasePath(),
            enabled,
            config.pickStringRegionBased(
                """
                Replaces same-world immediate respawns with a seamless soft respawn path.
                """,
                """
                用无缝软重生流程替换同世界的立即重生。
                """
            )
        );
    }
}
