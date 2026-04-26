package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class ItemMergeAttempts extends ConfigModules {
    public static int maxPerTick = -1;

    public String getBasePath() {
        return EnumConfigCategory.PERF.getBaseKeyName();
    }

    @Override
    public void onLoaded() {
        maxPerTick = config.getInt(
            getBasePath() + ".item-merge-attempts-per-tick",
            maxPerTick,
            config.pickStringRegionBased(
                """
                Limits the amount of item merge attempts performed per tick.
                Set to -1 to disable the limit.
                """,
                """
                限制每个 Tick 中物品合并的尝试次数。
                将其设置为 -1 可禁用该限制。
                """
            )
        );
    }
}
