package me.maxthetomas.plasm.executors;

import me.maxthetomas.plasm.ThisPlugin;
import me.maxthetomas.plasm.config.Config;
import me.maxthetomas.plasm.config.PlaceholderApplier;
import me.maxthetomas.plasm.exceptions.NullPlaceholderException;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class MinecraftExecutor {
    public static boolean broadcast(@NotNull String message, @Nullable Member member) {
        try {
            Bukkit.broadcastMessage(PlaceholderApplier.applyPlaceholdersDiscord(Config.messageFormatMinecraft, message, member));
        } catch (NullPlaceholderException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void sendCommandToConsole(@NotNull String command)
    {
        Bukkit.getScheduler().runTask(ThisPlugin.get(), () -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command));
    }
}
