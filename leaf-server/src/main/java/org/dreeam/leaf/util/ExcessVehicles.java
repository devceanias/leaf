package org.dreeam.leaf.util;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import org.dreeam.leaf.config.modules.opt.RemoveExcessVehicles;

import java.util.ArrayList;
import java.util.List;

public final class ExcessVehicles {
    public static void trimMinecarts(final AbstractMinecart source, final List<Entity> entities) {
        if (!RemoveExcessVehicles.removeMinecarts) {
            return;
        }

        trimExcess(source, entities, RemoveExcessVehicles.minecartLimit, AbstractMinecart.class);
    }

    public static void trimBoats(final AbstractBoat source, final List<Entity> entities) {
        if (!RemoveExcessVehicles.removeBoats) {
            return;
        }

        trimExcess(source, entities, RemoveExcessVehicles.boatLimit, AbstractBoat.class);
    }

    private static <T extends Entity> void trimExcess(
        final Entity source, final List<Entity> entities, final int limit, final Class<T> type
    ) {
        if (limit < 0) {
            return;
        }

        final List<T> excess = new ArrayList<>();

        for (final Entity entity : entities) {
            if (!type.isInstance(entity)) {
                continue;
            }

            final T vehicle = type.cast(entity);

            if (!isStacked(source, vehicle)) {
                continue;
            }

            excess.add(vehicle);
        }

        final int remaining = excess.size() - limit;

        if (remaining <= 0) {
            return;
        }

        final int removed = discardExcess(excess, entities, remaining, true);

        discardExcess(excess, entities, remaining - removed, false);
    }

    private static <T extends Entity> int discardExcess(
        final List<T> excess,
        final List<Entity> entities,
        final int remaining,
        final boolean removeEmptyOnly
    ) {
        if (remaining <= 0) {
            return 0;
        }

        int removed = 0;

        for (final T vehicle : excess) {
            if (removed >= remaining) {
                break;
            }

            if (vehicle.isRemoved()) {
                continue;
            }

            final boolean empty = !vehicle.isVehicle() && vehicle.getPassengers().isEmpty();

            if (removeEmptyOnly != empty) {
                continue;
            }

            vehicle.discard();
            entities.remove(vehicle);
            removed++;
        }

        return removed;
    }

    private static boolean isStacked(final Entity source, final Entity vehicle) {
        return vehicle.blockPosition().equals(source.blockPosition())
            || vehicle.getBoundingBox().intersects(source.getBoundingBox());
    }
}
