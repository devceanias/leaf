package org.dreeam.leaf.config.modules.opt;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class DisableWorldSimulation extends ConfigModules {
    public static boolean blockEvents = false;

    public static boolean blockUpdateTicking = false;
    public static boolean blockEntityTicking = false;

    public static boolean fluidTicking = false;
    public static boolean chunkTicking = false;

    public String getBasePath() {
        return EnumConfigCategory.PERF.getBaseKeyName() + ".disable-world-simulation";
    }

    @Override
    public void onLoaded() {
        final String base = getBasePath();

        blockEvents = config.getBoolean(
            base + ".block-events",
            blockEvents,
            config.pickStringRegionBased(
                """
                Disables block events such as pistons and other queued mechanical block actions.
                """,
                """
                禁用方块事件，例如活塞及其他排队的机械方块动作。
                """
            )
        );

        blockUpdateTicking = config.getBoolean(
            base + ".block-update-ticking",
            blockUpdateTicking,
            config.pickStringRegionBased(
                """
                Disables scheduled block ticking.
                """,
                """
                禁用计划的方块刻更新。
                """
            )
        );

        blockEntityTicking = config.getBoolean(
            base + ".block-entity-ticking",
            blockEntityTicking,
            config.pickStringRegionBased(
                """
                Disables block entity ticking.
                """,
                """
                禁用方块实体刻更新。
                """
            )
        );

        fluidTicking = config.getBoolean(
            base + ".fluid-ticking",
            fluidTicking,
            config.pickStringRegionBased(
                """
                Disables scheduled fluid ticking.
                """,
                """
                禁用计划的流体刻更新。
                """
            )
        );

        chunkTicking = config.getBoolean(
            base + ".chunk-ticking",
            chunkTicking,
            config.pickStringRegionBased(
                """
                Disables the per-chunk world simulation path in ServerLevel.tickChunk.
                """,
                """
                禁用 ServerLevel.tickChunk 中按区块进行的世界模拟逻辑。
                """
            )
        );
    }
}
