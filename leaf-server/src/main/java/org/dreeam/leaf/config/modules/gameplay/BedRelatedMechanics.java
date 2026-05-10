package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class BedRelatedMechanics extends ConfigModules {
    public static boolean disableSleeping = false;
    public static boolean disableRespawnPointSet = false;

    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName() + ".bedlike";
    }

    @Override
    public void onLoaded() {
        final String base = getBasePath();

        disableSleeping = config.getBoolean(
            base + ".disable-sleeping",
            disableSleeping,
            config.pickStringRegionBased(
                """
                Prevents players from sleeping in beds.
                """,
                """
                阻止玩家在床上睡觉。
                """
            )
        );

        disableRespawnPointSet = config.getBoolean(
            base + ".disable-respawn-point-set",
            disableRespawnPointSet,
            config.pickStringRegionBased(
                """
                Prevents bedlikes from setting player respawn points.
                """,
                """
                阻止床设置玩家的重生点。
                """
            )
        );
    }
}
