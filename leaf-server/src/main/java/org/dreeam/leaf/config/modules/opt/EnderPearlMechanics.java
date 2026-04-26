package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class EnderPearlMechanics extends ConfigModules {
    public static boolean retainMomentum = true;
    public static boolean preventGlitch = false;

    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName() + ".pearls";
    }

    @Override
    public void onLoaded() {
        final String base = getBasePath();

        retainMomentum = config.getBoolean(
            base + ".retain-momentum",
            retainMomentum,
            config.pickStringRegionBased(
                """
                Retains player momentum when teleporting with ender pearls.
                """,
                """
                使用末影珍珠传送时保留玩家的动量。
                """
            )
        );

        preventGlitch = config.getBoolean(
            base + ".prevent-glitch",
            preventGlitch,
            config.pickStringRegionBased(
                """
                Prevents common ender pearl glitching issues.
                """,
                """
                防止常见的末影珍珠卡位/穿墙问题。
                """
            )
        );
    }
}
