package me.maxthetomas.plasm.events;

import me.maxthetomas.plasm.Plasm;
import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.executers.DiscordExecutor;
import me.maxthetomas.plasm.types.SendTypes;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MinecraftListener implements Listener {

    @EventHandler
    public void onMessage(AsyncPlayerChatEvent e)
    {
        if (Config.sendType.equals(SendTypes.BOT)) {
            String message = Config.messageFormat;
            message = message.replaceAll("$playername", e.getPlayer().getName());
            message = message.replaceAll("$message", e.getMessage());
            DiscordExecutor.sendBotMessage(message);
        }
    }

}
