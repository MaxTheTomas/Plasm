package me.maxthetomas.plasm.config;

import me.maxthetomas.plasm.Plasm;
import me.maxthetomas.plasm.exceptions.NullPlaceholderException;
import net.dv8tion.jda.api.entities.Member;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Objects;

public class PlaceholderApplier {
    public static String applyPlaceholdersMC(@NotNull String input, @Nullable String message, @Nullable Player player) throws NullPlaceholderException {
        String output = input;

        // Player
        if (input.contains(Placeholders.PLAYER_NAME) ||
                input.contains(Placeholders.PLAYER_FACE) ||
                input.contains(Placeholders.PLAYER_HEAD))
        {
            if (player != null) {
                output = output.replaceAll(Placeholders.PLAYER_NAME, player.getName());
                output = output.replaceAll(Placeholders.PLAYER_HEAD, "https://crafatar.com/renders/head/" + player.getUniqueId().toString());
                output = output.replaceAll(Placeholders.PLAYER_FACE, "https://crafatar.com/avatars/" + player.getUniqueId().toString());
            }
            else
            {
                throw new NullPlaceholderException("Player is null");
            }
        }

        // MOTD
        if (input.contains(Placeholders.MOTD))
        {
            input = input.replaceAll(Placeholders.MOTD, Bukkit.getMotd());
        }

        // Message
        if (input.contains(Placeholders.MESSAGE))
        {
            if (message != null)
                output = output.replaceAll(Placeholders.MESSAGE, message);
            else
                throw new NullPlaceholderException("Message is null");
        }

        return output;
    }

    public static String applyPlaceholdersDiscord(@NotNull String input, @Nullable String message, @Nullable Member member) throws NullPlaceholderException {
        String output = input;

        if (input.contains(Placeholders.MESSAGE))
        {
            if (message != null)
            {
                output = output.replaceAll(Placeholders.MESSAGE, message.replaceAll("&", ""));
            }
            else
            {
                throw new NullPlaceholderException("Message is null, but placeholder found");
            }
        }

        // MOTD
        if (input.contains(Placeholders.MOTD))
        {
            input = input.replaceAll(Placeholders.MOTD, Bukkit.getMotd());
        }

        if (input.contains(Placeholders.MEMBER_ID) || input.contains(Placeholders.MEMBER_NICKNAME))
        {
            if (member != null)
            {
                output = output.replaceAll(Placeholders.MEMBER_ID, member.getId());
                //output = output.replaceAll(Placeholders.MEMBER_NICKNAME, Objects.requireNonNull(Objects.requireNonNull(Config.guild.getMemberById(member.getId())).getNickname()));
                output = output.replaceAll(Placeholders.MEMBER_NICKNAME, member.getUser().getName());
            }
            else
            {
                throw new NullPlaceholderException("Member is null, but placeholder found");
            }
        }
        if (output.contains("&"))
            output = output.replaceAll("&", String.valueOf(ChatColor.COLOR_CHAR));

        return output;
    }
}
