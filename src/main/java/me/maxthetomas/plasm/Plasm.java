package me.maxthetomas.plasm;

import me.maxthetomas.plasm.types.SendTypes;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;


public final class Plasm extends JavaPlugin {

    private static final int currentConfigVersion = 0;

    public static JDA jda;
    private static String token;
    private static String activity;
    public static SendTypes sendType;

    private boolean initBot()
    {
        JDABuilder jdaBuilder = JDABuilder.createDefault(token);
        if (!activity.equals(""))
        { jdaBuilder.setActivity(Activity.playing(activity)); }
        try {
            jda = jdaBuilder.build();
        } catch (LoginException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void configSetup()
    {
        if (getConfig().getInt("config-version") != currentConfigVersion) { saveDefaultConfig(); }
        token = getConfig().getString("bot-properties.token");
        activity = getConfig().getString("bot-properties.activity");
        sendType = SendTypes.valueOf(getConfig().getString("message-properties.send-type"));
    }

    @Override
    public void onEnable() {
        ThisPlugin.constructor(this);
        configSetup();
        if (!initBot()) {
            getLogger().warning("Bot isn't authorized! Check your token property. Shutting down...");
            Bukkit.shutdown();
        }
    }

    @Override
    public void onDisable() {
        if (jda != null) jda.shutdown();
    }
}
