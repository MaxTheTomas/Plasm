package me.maxthetomas.plasm.events;

import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.exceptions.NullPlaceholderException;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;

import javax.annotation.Nonnull;

public class DiscordListener extends ListenerAdapter {
    @Override
    public void onGuildMessageReceived(@Nonnull GuildMessageReceivedEvent e) {
        if (e.getAuthor().isBot()) return;
        if (e.getMessage().isWebhookMessage()) return;

        if (e.getChannel() == Config.channel)
        {
            try {
                Bukkit.broadcastMessage(Config.applyPlaceholdersDiscord(Config.messageFormatMinecraft, e.getMessage().getContentRaw(), e.getMember()));
            } catch (NullPlaceholderException nullPlaceholderException) {
                nullPlaceholderException.printStackTrace();
            }
        }
    }
}
