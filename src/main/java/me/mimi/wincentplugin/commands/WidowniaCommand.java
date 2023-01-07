package me.mimi.wincentplugin.commands;

import me.mimi.wincentplugin.ChatUtil;
import me.mimi.wincentplugin.WincentPlugin;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WidowniaCommand implements CommandExecutor {

    private WincentPlugin plugin;

    public WidowniaCommand() {
        this.plugin = WincentPlugin.getInstance();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        if(!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("Ta komenda dostępna jest tylko dla graczy"));
            return true;
        }

        Player player = (Player) sender;


        Location spawn = plugin.getConfig().getLocation("widowniaLocation");

        if(spawn == null){
            player.sendMessage(ChatUtil.color("&e&l[&5&lEventy&e&l] &6&lWidownia nie została ustawiona, ustawisz ją za pomocą &e&l/setwidownia&6&l!"));
            return true;
        }else {
            player.teleport(spawn);
            player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6&lZostałeś przeniesiony na &e&lwidownie&6&l."));
            return true;
        }

    }
}
