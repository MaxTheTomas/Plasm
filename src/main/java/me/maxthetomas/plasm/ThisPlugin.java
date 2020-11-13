package me.maxthetomas.plasm;

import org.bukkit.plugin.Plugin;

public abstract class ThisPlugin {
    public static Plugin p;

    public static void constructor(Plugin p)
    {
        ThisPlugin.p = p;
    }

    public static Plugin get() {
        return p;
    }
}
