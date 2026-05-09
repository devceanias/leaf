package org.dreeam.leaf.util;

import ca.spottedleaf.moonrise.patches.chunk_system.world.ChunkSystemEntityGetter;
import ca.spottedleaf.moonrise.patches.collisions.CollisionUtil;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;

import java.util.List;
import java.util.function.Predicate;

public final class VisibilityCollisions {
    public static boolean hiddenFromActor(final Entity actor, final Entity target) {
        return actor instanceof final ServerPlayer source
            && target instanceof final ServerPlayer other
            && !source.getBukkitEntity().canSee(other.getBukkitEntity());
    }

    public static <T extends Entity> Predicate<T> visibleToActor(final Entity actor) {
        return entity -> !hiddenFromActor(actor, entity);
    }

    public static <T extends Entity> Predicate<T> visibleToActor(final Entity actor, final Predicate<? super T> predicate) {
        return entity -> !hiddenFromActor(actor, entity) && predicate.test(entity);
    }

    public static boolean noEntityConflictForActor(final Level level, final Entity actor, final AABB box) {
        return level.getEntitiesOfClass(Entity.class, box, visibleToActor(actor)).isEmpty();
    }

    public static boolean noEntityCollisionForActor(
        final Level level, final Entity actor, final @Nullable Entity collision, AABB box
    ) {
        if (!level.noBlockCollision(collision, box) || !level.noBorderCollision(collision, box)) {
            return false;
        }

        box = box.inflate(
            -CollisionUtil.COLLISION_EPSILON,
            -CollisionUtil.COLLISION_EPSILON,
            -CollisionUtil.COLLISION_EPSILON
        );

        final List<Entity> entities;

        if (collision != null && collision.moonrise$isHardColliding()) {
            entities = level.getEntities(collision, box, visibleToActor(actor));
        } else {
            entities = ((ChunkSystemEntityGetter) level).moonrise$getHardCollidingEntities(
                collision, box, visibleToActor(actor)
            );
        }

        for (final Entity other : entities) {
            if (other.isSpectator()) {
                continue;
            }

            if ((collision != null || !other.canBeCollidedWith(null)) &&
                (collision == null || !collision.canCollideWith(other))
            ) {
                continue;
            }

            return false;
        }

        return true;
    }
}
