package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class CombatProjectileMechanics extends ConfigModules {
    public static boolean optimise = true;

    public static boolean useAccurateCollisions = true;
    public static boolean alwaysAccurateArrows = true;
    public static boolean disableMarginExpansion = true;

    public String getBasePath() {
        return EnumConfigCategory.PERF.getBaseKeyName() + ".projectiles";
    }

    @Override
    public void onLoaded() {
        final String base = getBasePath();

        optimise = config.getBoolean(
            base + ".optimise",
            optimise,
            config.pickStringRegionBased(
                """
                Applies the combat-focused projectile behavior overrides in this section.
                """,
                """
                应用此部分中的战斗导向投射物行为覆盖。
                """
            )
        );

        useAccurateCollisions = config.getBoolean(
            base + ".use-accurate-collisions",
            useAccurateCollisions,
            config.pickStringRegionBased(
                """
                Improves projectile to player collision checks to reduce passthroughs.
                """,
                """
                改进投射物与玩家的碰撞检测以减少穿透。
                """
            )
        );

        alwaysAccurateArrows = config.getBoolean(
            base + ".always-accurate-arrows",
            alwaysAccurateArrows,
            config.pickStringRegionBased(
                """
                Removes normal arrow inaccuracy so arrows always follow an exact trajectory.
                """,
                """
                移除普通箭矢的随机偏移，使其始终按精确轨迹飞行。
                """
            )
        );

        disableMarginExpansion = config.getBoolean(
            base + ".disable-margin-expansion",
            disableMarginExpansion,
            config.pickStringRegionBased(
                """
                Disables the newer projectile hit margin ramp-up and keeps a fixed hit margin instead.
                """,
                """
                禁用较新的投射物命中边距递增逻辑，并改为固定命中边距。
                """
            )
        );
    }
}
