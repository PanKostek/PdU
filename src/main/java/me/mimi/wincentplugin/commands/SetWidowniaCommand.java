package me.mimi.wincentplugin.commands;

import me.mimi.wincentplugin.ChatUtil;
import me.mimi.wincentplugin.WincentPlugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetWidowniaCommand implements CommandExecutor {

    private WincentPlugin plugin;

    public SetWidowniaCommand(){
        this.plugin = WincentPlugin.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("Ta komenda dostępna jest tylko dla graczy"));
            return true;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("diamenty.cmd.setwidownia")){
            player.sendMessage(ChatUtil.color("&e&l[&5&lEventy&e&l] &6&lNie posiadasz wystarczających uprawnień!"));
            return true;
        }

        Location location = player.getLocation();
        plugin.getConfig().set("widowniaLocation", location);
        plugin.saveConfig();
        player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6&lUstawiono &a&lwidownie &6&lna kordach: &e&l"
                + " X " + location.getBlockX()
                + " Y " + location.getBlockY()
                + " Z " + location.getBlockZ()
                + " &6&lNa świecie: &e&l " + location.getWorld().getName()

            ));

        return true;
    }
}
