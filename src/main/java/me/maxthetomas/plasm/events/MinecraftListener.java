package me.maxthetomas.plasm.events;

import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.config.PlaceholderApplier;
import me.maxthetomas.plasm.exceptions.NullPlaceholderException;
import me.maxthetomas.plasm.executors.DiscordExecutor;
import me.maxthetomas.plasm.types.SendType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class MinecraftListener implements Listener {

    @EventHandler
    public static void onMessage(AsyncPlayerChatEvent e)
    {
        try {
            String messageBot = null;
            String messageWebhook = null;
            messageBot = PlaceholderApplier.applyPlaceholdersMC(Config.messageFormatBot, e.getMessage(), e.getPlayer());
            messageWebhook = PlaceholderApplier.applyPlaceholdersMC(Config.messageFormatWebhook, e.getMessage(), e.getPlayer());
            if (Config.sendType == SendType.BOT)
                DiscordExecutor.sendMessageAsBot(messageBot); // Sending as bot
            else if (Config.sendType == SendType.WEBHOOK)
                DiscordExecutor.sendMessageAsWebhook(messageWebhook, PlaceholderApplier.applyPlaceholdersMC(Config.webhookAvatar, e.getMessage(), e.getPlayer()), PlaceholderApplier.applyPlaceholdersMC(Config.webhookNickname, e.getMessage(), e.getPlayer()));
        } catch (NullPlaceholderException nullPlaceholderException) {
            nullPlaceholderException.printStackTrace();
        }
    }

    @EventHandler
    public static void onJoin(PlayerJoinEvent e)
    {
        try {
            String messageBot = null;
            String messageWebhook = null;
            messageBot = PlaceholderApplier.applyPlaceholdersMC(Config.messageFormatJoin, e.getJoinMessage(), e.getPlayer());
            messageWebhook = PlaceholderApplier.applyPlaceholdersMC(Config.messageFormatJoin, e.getJoinMessage(), e.getPlayer());
            if (Config.sendType == SendType.BOT)
                DiscordExecutor.sendMessageAsBot(messageBot); // Sending as bot
            else if (Config.sendType == SendType.WEBHOOK)
                DiscordExecutor.sendMessageAsWebhook(messageWebhook, PlaceholderApplier.applyPlaceholdersMC(Config.webhookAvatar, e.getJoinMessage(), e.getPlayer()), PlaceholderApplier.applyPlaceholdersMC(Config.webhookNickname, e.getJoinMessage(), e.getPlayer()));
        } catch (NullPlaceholderException nullPlaceholderException) {
            nullPlaceholderException.printStackTrace();
        }
    }

    @EventHandler
    public static void onLeave(PlayerQuitEvent e)
    {
        try {
            String messageBot = null;
            String messageWebhook = null;
            messageBot = PlaceholderApplier.applyPlaceholdersMC(Config.messageFormatLeave, e.getQuitMessage(), e.getPlayer());
            messageWebhook = PlaceholderApplier.applyPlaceholdersMC(Config.messageFormatLeave, e.getQuitMessage(), e.getPlayer());
            if (Config.sendType == SendType.BOT)
                DiscordExecutor.sendMessageAsBot(messageBot); // Sending as bot
            else if (Config.sendType == SendType.WEBHOOK)
                DiscordExecutor.sendMessageAsWebhook(messageWebhook, PlaceholderApplier.applyPlaceholdersMC(Config.webhookAvatar, e.getQuitMessage(), e.getPlayer()), PlaceholderApplier.applyPlaceholdersMC(Config.webhookNickname, e.getQuitMessage(), e.getPlayer()));
        } catch (NullPlaceholderException nullPlaceholderException) {
            nullPlaceholderException.printStackTrace();
        }
    }
}
