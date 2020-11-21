package me.maxthetomas.plasm;

import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.config.Paths;
import me.maxthetomas.plasm.console.LoggerRegisterer;
import me.maxthetomas.plasm.events.DiscordListener;
import me.maxthetomas.plasm.events.MinecraftListener;
import me.maxthetomas.plasm.exceptions.UnupdatedConfigException;
import me.maxthetomas.plasm.types.SendType;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.ShutdownEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


public final class Plasm extends JavaPlugin {

    private static final int confVer = 4;
    public static JDA jda;
    Config c = null;

    @Override
    public void onEnable() {
        ThisPlugin.constructor(this);

        try {
            configSetup();
        } catch (UnupdatedConfigException e) {
            e.printStackTrace();
        }

        if (!initBot()) {
            getLogger().warning("Bot isn't authorized! Check your token property. Shutting down...");
            Bukkit.shutdown();
        }
        Config.setupDiscord();

        setupEvents();
    }

    private boolean initBot()
    {
        if (Config.token == null)
            ThisPlugin.get().getLogger().warning("Create bot and get his token from this page: 'https://discord.com/developers/applications'");

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

    private void configSetup() throws UnupdatedConfigException {
        saveDefaultConfig();
        if (Config.configVersion != confVer) {
            throw new UnupdatedConfigException("" + Config.configVersion + " lower than current allowed config version.");
        }
    }

    private void setupEvents()
    {
        if (Config.consoleEnabled)
            LoggerRegisterer.register();

        Bukkit.getPluginManager().registerEvents(new MinecraftListener(), this);
        jda.addEventListener(new DiscordListener());
    }


    @Override
    public void onDisable()
    {
        if (jda != null) jda.getEventManager().getRegisteredListeners().forEach(listener -> jda.getEventManager().unregister(listener));
        if (jda != null) jda.getAudioManagers().forEach(AudioManager::closeAudioConnection);
        if (jda != null) {
            CompletableFuture<Void> shutdownTask = new CompletableFuture<>();
            jda.addEventListener(new ListenerAdapter() {
                @Override
                public void onShutdown(@NotNull ShutdownEvent event) {
                    shutdownTask.complete(null);
                }
            });
            jda.shutdownNow();
            jda = null;
            try {
                shutdownTask.get(5, TimeUnit.SECONDS);
            } catch (TimeoutException | InterruptedException | ExecutionException e) {
                getLogger().warning("JDA took too long to shut down, skipping");
            }
        }
    }
}
