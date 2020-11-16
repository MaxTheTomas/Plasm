package me.maxthetomas.plasm.console;

import me.maxthetomas.plasm.ThisPlugin;
import me.maxthetomas.plasm.executors.DiscordExecutor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.LogEvent;

import java.util.logging.LogRecord;

public class Logger {
    // Loggers:
    public static org.apache.logging.log4j.core.Logger logger4j = (org.apache.logging.log4j.core.Logger) LogManager.getRootLogger();
    public static java.util.logging.Logger loggerBukkit = ThisPlugin.get().getLogger();

    public static void send(LogRecord l)
    {
        DiscordExecutor.sendMessageToConsole(l.getMessage());
    }

    public static void send(LogEvent e)
    {
        DiscordExecutor.sendMessageToConsole(e.getMessage().getFormattedMessage());
    }
}
