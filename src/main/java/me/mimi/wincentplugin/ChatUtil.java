package me.mimi.wincentplugin;

import org.bukkit.ChatColor;

public class ChatUtil {

    public static String color(String message){
        return ChatColor.translateAlternateColorCodes('&', message);

    }
}
