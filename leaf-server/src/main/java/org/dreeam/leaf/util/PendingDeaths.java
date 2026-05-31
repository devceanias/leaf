package org.dreeam.leaf.util;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public final class PendingDeaths {
    public static final float PLAYER_HEALTH_FLOOR = 1.0E-4F;

    private static final Map<UUID, Death> pending = new LinkedHashMap<>();

    public static boolean defer(final ServerPlayer player, final DamageSource source) {
        if (source.is(DamageTypeTags.BYPASSES_INVULNERABILITY)) {
            return false;
        }

        if (player.isRemoved() || player.isSpectator()) {
            return false;
        }

        if (player.getHealth() <= PLAYER_HEALTH_FLOOR) {
            player.setHealth(PLAYER_HEALTH_FLOOR);
        }

        pending.put(player.getUUID(), new Death(player, source));

        return true;
    }

    public static void process() {
        final Iterator<Death> iterator = pending.values().iterator();

        while (iterator.hasNext()) {
            final Death pending = iterator.next();

            iterator.remove();

            final ServerPlayer player = pending.player();

            if (player.isRemoved() || player.isSpectator()) {
                continue;
            }

            if (player.getHealth() > PLAYER_HEALTH_FLOOR) {
                continue;
            }

            final DamageSource source = pending.source();

            if (player.leaf$tryUseDeathProtection(source)) {
                continue;
            }

            player.setHealth(0.0F);
            player.die(source);
        }
    }

    private record Death(ServerPlayer player, DamageSource source) {}
}
