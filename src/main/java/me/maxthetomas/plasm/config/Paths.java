package me.maxthetomas.plasm.config;

public class Paths {
    // Bot
    public static final String TOKEN = "bot.token";
    public static final String ACTIVITY_TYPE = "bot.activity-type";
    public static final String ACTIVITY = "bot.activity";

    // Discord channel
    public static final String SEND_TYPE = "discord.send-type";
    public static final String GUILD = "discord.server";
    public static final String CHANNEL = "discord.channel";

    // Webhook settings
    public static final String WEBHOOK_URL = "webhook.url";
    public static final String WEBHOOK_NICKNAME = "webhook.nickname";
    public static final String WEBHOOK_AVATAR = "webhook.avatar-url";

    // Message formats
    public static final String MESSAGE_FORMAT_BOT = "message-format.bot";
    public static final String MESSAGE_FORMAT_WEBHOOK = "message-format.webhook";
    public static final String MESSAGE_FORMAT_MINECRAFT = "message-format.minecraft";

    public static final String MESSAGE_FORMAT_JOIN = "message-format.join";
    public static final String MESSAGE_FORMAT_LEAVE = "message-format.leave";

    // Console
    public static final String CON_ENABLED = "console.enabled";
    public static final String CON_GUILD = "console.guild";
    public static final String CON_CHANNEL = "console.channel";
    public static final String CON_LOGGER_TYPE = "console.logger";
    
    // Tech.
    public static final String CONFIG_VERSION = "config-version";
}
