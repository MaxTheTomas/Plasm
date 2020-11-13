package me.maxthetomas.plasm.events;

import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.exceptions.NullPlaceholderException;
import me.maxthetomas.plasm.executers.DiscordExecutor;
import me.maxthetomas.plasm.types.SendType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class MinecraftListener implements Listener {

    @EventHandler
    public static void onMessage(AsyncPlayerChatEvent e)
    {
        try {
            String messageBot = null;
            String messageWebhook = null;
            messageBot = Config.applyPlaceholdersMC(Config.messageFormatBot, e.getMessage(), e.getPlayer());
            messageWebhook = Config.applyPlaceholdersMC(Config.messageFormatWebhook, e.getMessage(), e.getPlayer());
            if (Config.sendType == SendType.BOT)
                DiscordExecutor.sendMessageAsBot(messageBot); // Sending as bot
            else if (Config.sendType == SendType.WEBHOOK)
                DiscordExecutor.sendMessageAsWebhook(messageWebhook, Config.applyPlaceholdersMC(Config.webhookAvatar, e.getMessage(), e.getPlayer()), Config.applyPlaceholdersMC(Config.webhookNickname, e.getMessage(), e.getPlayer()));
        } catch (NullPlaceholderException nullPlaceholderException) {
            nullPlaceholderException.printStackTrace();
        }
    }
}
