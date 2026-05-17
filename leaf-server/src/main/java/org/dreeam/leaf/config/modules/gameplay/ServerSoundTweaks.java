package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;
import org.jspecify.annotations.NonNull;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class ServerSoundTweaks extends ConfigModules {
    public static boolean shouldPlayCancelledHitSounds = false;

    public static Set<String> suppressed = Set.of();

    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName() + ".sounds";
    }

    @Override
    public void onLoaded() {
        final String base = getBasePath();

        shouldPlayCancelledHitSounds = config.getBoolean(
            base + ".should-play-cancelled-hit-sounds",
            shouldPlayCancelledHitSounds,
            config.pickStringRegionBased(
                """
                Allows combat hit sounds like nodamage when the damage event is cancelled.
                """,
                """
                当伤害事件被取消时，仍允许播放如 nodamage 等攻击命中音效。
                """
            )
        );

        final List<String> configured = config.getList(
            base + ".suppressed",
            List.of(),
            config.pickStringRegionBased(
                """
                Sound event ids to suppress from outgoing sound packets.
                Example: minecraft:entity.player.attack.nodamage
                """,
                """
                要从发送到客户端的声音数据包中屏蔽的声音事件 ID。
                示例：minecraft:entity.player.attack.nodamage
                """
            )
        );

        final Set<String> suppressed = new HashSet<>();

        for (final String sound : configured) {
            suppressed.add(sound.toLowerCase(Locale.ROOT));
        }

        ServerSoundTweaks.suppressed = Set.copyOf(suppressed);
    }

    public static boolean shouldSuppressSound(final @NonNull String sound) {
        return suppressed.contains(sound.toLowerCase(Locale.ROOT));
    }
}
