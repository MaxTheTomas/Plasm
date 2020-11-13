package me.maxthetomas.plasm;

import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.config.Paths;
import me.maxthetomas.plasm.types.SendType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;


public final class Plasm extends JavaPlugin {

    private static final int currentConfigVersion = 0;
    public static JDA jda;

    private boolean initBot()
    {
        JDABuilder jdaBuilder = JDABuilder.createDefault(Config.token);
        try {
            jda = jdaBuilder.build();
            jda.getPresence().setStatus(OnlineStatus.ONLINE);
            jda.getPresence().setActivity(Activity.of(Activity.ActivityType.valueOf(Config.activityType), Config.activity));
        } catch (LoginException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private void configSetup() { if (getConfig().getInt(Paths.CONFIG_VERSION) != currentConfigVersion) { saveDefaultConfig(); } }

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
