package me.mimi.wincentplugin;

import me.mimi.wincentplugin.commands.*;
import org.bukkit.plugin.java.JavaPlugin;

public final class WincentPlugin extends JavaPlugin {

    private static WincentPlugin instance;

    public static WincentPlugin getInstance(){
        return instance;
    }


    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        getCommand("widownia").setExecutor(new WidowniaCommand());
        getCommand("setwidownia").setExecutor(new SetWidowniaCommand());
        getCommand("boxy").setExecutor(new BoxyCommand());
        getCommand("setboxy").setExecutor(new SetBoxyCommand());

    }

    @Override
    public void onDisable() {
        saveConfig();
        // Plugin shutdown logic
    }
}
