package me.maxthetomas.plasm.config;

import me.maxthetomas.plasm.Plasm;
import me.maxthetomas.plasm.ThisPlugin;
import me.maxthetomas.plasm.types.SendTypes;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {
    public static final FileConfiguration fileConfiguration = ThisPlugin.get().getConfig();
    public static SendTypes sendType = SendTypes.valueOf(fileConfiguration.getString("message-properties.send-type"));
    public static String messageFormat = fileConfiguration.getString("message-properties.bot-msg-format");
}
