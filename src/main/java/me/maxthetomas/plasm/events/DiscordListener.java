package me.maxthetomas.plasm.events;

import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.config.PlaceholderApplier;
import me.maxthetomas.plasm.exceptions.NullPlaceholderException;
import me.maxthetomas.plasm.executors.MinecraftExecutor;
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
            MinecraftExecutor.broadcast(e.getMessage().getContentRaw(), e.getMember());
        }
    }
}
