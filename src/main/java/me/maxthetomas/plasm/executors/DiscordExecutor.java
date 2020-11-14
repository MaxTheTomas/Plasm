package me.maxthetomas.plasm.executors;

import club.minnced.discord.webhook.send.WebhookMessageBuilder;
import me.maxthetomas.plasm.Plasm;
import me.maxthetomas.plasm.config.Config;

public class DiscordExecutor {
    private final net.dv8tion.jda.api.JDA Jda = Plasm.jda;

    public static void sendMessageAsBot(String message)
    {
        Config.channel.sendMessage(message).queue();
    }

    public static void sendMessageAsWebhook(String message, String avatarUrl, String nickname)
    {
        WebhookMessageBuilder messageBuilder = new WebhookMessageBuilder();
        messageBuilder.setContent(message);
        messageBuilder.setUsername(nickname);
        messageBuilder.setAvatarUrl(avatarUrl);
        Config.webhookClient.send(messageBuilder.build());
    }
}
