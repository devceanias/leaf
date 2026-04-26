package org.dreeam.leaf.config.modules.network;

import org.dreeam.leaf.config.ConfigModules;
import org.dreeam.leaf.config.EnumConfigCategory;

public class DisableChatKick extends ConfigModules {
    public String getBasePath() {
        return EnumConfigCategory.NETWORK.getBaseKeyName();
    }

    public static boolean enabled = true;

    @Override
    public void onLoaded() {
        enabled = config.getBoolean(
            getBasePath() + ".disable-chat-kick",
            enabled,
            config.pickStringRegionBased(
                "Prevents tracking unacknowledged chat messages to avoid player kicks.",
                "禁用未确认聊天消息跟踪以避免玩家被踢出。"
            )
        );
    }
}
