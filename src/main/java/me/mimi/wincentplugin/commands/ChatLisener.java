package me.mimi.wincentplugin.commands;

import me.mimi.wincentplugin.ChatUtil;
import me.mimi.wincentplugin.WincentPlugin;
import org.bukkit.Location;
import org.bukkit.StructureType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Struct;

public class ChatLisener implements CommandExecutor {


    private WincentPlugin plugin;

    public ChatLisener() {
        this.plugin = WincentPlugin.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatUtil.color("Ta komenda dostępna jest tylko dla graczy"));
            return true;
        }

        Player player = (Player) sender;

        switch (args[0]){
            case "boxy":
                return teleportToBox(player);
            case "widownia":
                return teleportToAudience(player);
            case "setboxy":
                return setToBox(player);
            case "setwidownia":
                return setToAudience(player);
            default:
                return true;
        }
    }

    private boolean teleportToBox(Player player){
        Location spawn = plugin.getConfig().getLocation("BoxyLocation");

        if(spawn == null){
            player.sendMessage(ChatUtil.color("&e&l[&5&lEventy&e&l] &6&lWidownia nie została ustawiona, ustawisz ją za pomocą &e&l/setboxy&6&l!"));
            return true;
        }else {
            player.teleport(spawn);
            player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6&lZostałeś przeniesiony na &e&lboxy&6&l."));
            return true;
        }
    }
    private boolean teleportToAudience(Player player) {
        Location spawn = plugin.getConfig().getLocation("widowniaLocation");

        if (spawn == null) {
            player.sendMessage(ChatUtil.color("&e&l[&5&lEventy&e&l] &6&lWidownia nie została ustawiona, ustawisz ją za pomocą &e&l/setwidownia&6&l!"));
            return true;
        } else {
            player.teleport(spawn);
            player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6&lZostałeś przeniesiony na &e&lwidownie&6&l."));
            return true;
        }
    }

    private boolean setToBox(Player player) {
        if(!player.hasPermission("diamenty.cmd.setwidownia")){
            player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6&lNie posiadasz wystarczających uprawnień!"));
            return true;
        }

        Location location = player.getLocation();
        plugin.getConfig().set("BoxyLocation", location);
        plugin.saveConfig();
        player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6Ustawiono &a&lboxy &6&lna kordach: &e&l"
                + " X " + location.getBlockX()
                + " Y " + location.getBlockY()
                + " Z " + location.getBlockZ()
                + " &6&lNa świecie: &e&l " + location.getWorld().getName()

        ));

        return true;
    }

    private boolean setToAudience(Player player) {
        if (!player.hasPermission("diamenty.cmd.setwidownia")) {
            player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6&lNie posiadasz wystarczających uprawnień!"));
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
    private void showHelp(Player player) {
        player.sendMessage(ChatUtil.color("&e&l[&5&lEvent&e&l] &6&lNieznana komenda! Dostępne komendy:&c boxy &7| &cwidownia &7| &csetboxy &7| &csetwidownia"));
        return;

    }
}