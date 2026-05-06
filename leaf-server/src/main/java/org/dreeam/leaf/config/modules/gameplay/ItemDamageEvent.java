package org.dreeam.leaf.config.modules.gameplay;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class ItemDamageEvent extends ConfigModules {
    public static boolean fireOnZeroDamage = true;

    public String getBasePath() {
        return EnumConfigCategory.GAMEPLAY.getBaseKeyName() + ".item-damage-event";
    }

    @Override
    public void onLoaded() {
        fireOnZeroDamage = config.getBoolean(
            getBasePath() + ".fire-on-zero-damage",
            fireOnZeroDamage
        );
    }
}
