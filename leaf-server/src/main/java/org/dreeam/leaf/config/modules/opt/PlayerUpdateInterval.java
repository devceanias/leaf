package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class PlayerUpdateInterval extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName();
    }

    public static int interval = -1;

    @Override
    public void onLoaded() {
        interval = config.getInt(
            getBasePath() + ".player-update-interval",
            interval,
            config.pickStringRegionBased(
                """
                Overrides the tracker update interval for players only.
                Set to -1 to use the vanilla default.
                """,
                """
                仅覆盖玩家实体追踪器的更新间隔。
                设置为 -1 以使用原版默认值。
                """
            )
        );
    }
}
