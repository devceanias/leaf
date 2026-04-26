package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class RemoveExcessVehicles extends ConfigModules {
    public static boolean removeMinecarts = true;
    public static boolean removeBoats = true;

    public static int minecartLimit = 10;
    public static int boatLimit = 10;

    public String getBasePath() {
        return EnumConfigCategory.PERF.getBaseKeyName() + ".remove-excess-vehicles";
    }

    @Override
    public void onLoaded() {
        final String base = getBasePath();

        removeMinecarts = config.getBoolean(
            base + ".remove-minecarts",
            removeMinecarts,
            config.pickStringRegionBased(
                """
                Removes excess minecarts during collision handling.
                """,
                """
                在碰撞处理时移除过多的矿车。
                """
            )
        );

        removeBoats = config.getBoolean(
            base + ".remove-boats",
            removeBoats,
            config.pickStringRegionBased(
                """
                Removes excess boats during collision handling.
                """,
                """
                在碰撞处理时移除过多的船只。
                """
            )
        );

        minecartLimit = config.getInt(
            base + ".minecart-limit",
            minecartLimit,
            config.pickStringRegionBased(
                """
                The maximum same-block or intersecting minecarts allowed before extras are removed.
                """,
                """
                在移除多余矿车之前，允许处于同一方块或相互重叠的矿车最大数量。
                """
            )
        );

        boatLimit = config.getInt(
            base + ".boat-limit",
            boatLimit,
            config.pickStringRegionBased(
                """
                The maximum same-block or intersecting boats allowed before extras are removed.
                """,
                """
                在移除多余船只之前，允许处于同一方块或相互重叠的船只最大数量。
                """
            )
        );
    }
}
