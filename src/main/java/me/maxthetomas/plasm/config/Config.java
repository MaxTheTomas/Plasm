package me.maxthetomas.plasm.config;

import club.minnced.discord.webhook.WebhookClient;
import club.minnced.discord.webhook.WebhookClientBuilder;
import me.maxthetomas.plasm.Plasm;
import me.maxthetomas.plasm.ThisPlugin;
import me.maxthetomas.plasm.exceptions.NullPlaceholderException;
import me.maxthetomas.plasm.types.SendType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageChannel;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class Config {
    public static final FileConfiguration fileConfiguration = ThisPlugin.get().getConfig();
    public static final int configVersion = fileConfiguration.getInt(Paths.CONFIG_VERSION);

    // Discord:
    public static final String sendTypeString = fileConfiguration.getString(Paths.SEND_TYPE);
    public static SendType sendType = SendType.valueOf(sendTypeString);
    public static final String activityType = fileConfiguration.getString(Paths.ACTIVITY_TYPE);
    public static final String activity = fileConfiguration.getString(Paths.ACTIVITY);
    public static final String token = fileConfiguration.getString(Paths.TOKEN);
    public static final String guildId = fileConfiguration.getString(Paths.GUILD);
    public static final String channelId = fileConfiguration.getString(Paths.CHANNEL);
    public static Guild guild = null;
    public static MessageChannel channel = null;
    public static WebhookClient webhookClient = null;


    // Webhook:
    public static final String webhookUrl = fileConfiguration.getString(Paths.WEBHOOK_URL);
    public static final String webhookAvatar = fileConfiguration.getString(Paths.WEBHOOK_AVATAR);
    public static final String webhookNickname = fileConfiguration.getString(Paths.WEBHOOK_NICKNAME);

    // Minecraft:
    public static final String messageFormatBot = fileConfiguration.getString(Paths.MESSAGE_FORMAT_BOT);
    public static final String messageFormatWebhook = fileConfiguration.getString(Paths.MESSAGE_FORMAT_WEBHOOK);
    public static final String messageFormatJoin = fileConfiguration.getString(Paths.MESSAGE_FORMAT_JOIN);
    public static final String messageFormatLeave = fileConfiguration.getString(Paths.MESSAGE_FORMAT_LEAVE);
    public static final String messageFormatMinecraft = fileConfiguration.getString(Paths.MESSAGE_FORMAT_MINECRAFT);

    public Config() {
        new BukkitRunnable()
        {
            @Override
            public void run() {
            try {

                Plasm.jda.awaitReady();
                guild = Plasm.jda.getGuildById(guildId);
                if (guild != null)
                    channel = guild.getTextChannelById(channelId);
                if (guild == null || channel == null)
                {
                    ThisPlugin.get().getLogger().warning("Add bot to your server: 'https://discord.com/api/oauth2/authorize?client_id=" + Plasm.jda.getSelfUser().getId() + "&permissions=0&scope=bot'");
                    throw new NullPointerException();
                }
                webhookClient = new WebhookClientBuilder(Config.webhookUrl).build();
            } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
        }.runTaskLater(ThisPlugin.get(), 40);
    }
}
