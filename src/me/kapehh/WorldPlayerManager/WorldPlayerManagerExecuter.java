package me.kapehh.WorldPlayerManager;

import me.kapehh.WorldPlayerManager.manager.WorldPlayerManagerMain;
import me.kapehh.WorldPlayerManager.manager.WorldPlayerManagerPermissions;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;

/**
 * Created by Karen on 28.07.2014.
 */
public class WorldPlayerManagerExecuter implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (!WorldPlayerManager.getPermission().has(commandSender, WorldPlayerManagerPermissions.PERM_ADMIN)) {
            commandSender.sendMessage(ChatColor.RED + "You don't have permissions!");
            return true;
        }

        if (args.length < 1) {
            return false;
        }
        String method = args[0];

        if (method.equalsIgnoreCase("open")) {
            if (args.length < 2) {
                return false;
            }
            String strWorld = args[1];
            try {
                WorldPlayerManager.getWorldPlayerManagerMain().worldOpen(strWorld);
                commandSender.sendMessage(ChatColor.GREEN + "World open!");
            } catch (Exception e) {
                commandSender.sendMessage(e.getMessage());
            }
        } else if (method.equalsIgnoreCase("close")) {
            if (args.length < 2) {
                return false;
            }
            String strWorld = args[1];
            try {
                WorldPlayerManager.getWorldPlayerManagerMain().worldClose(strWorld);
                commandSender.sendMessage(ChatColor.GREEN + "World close!");
            } catch (Exception e) {
                commandSender.sendMessage(e.getMessage());
            }
        } else if (method.equalsIgnoreCase("tp")) {
            if (args.length < 3) {
                return false;
            }
            String strWorldFrom = args[1];
            String strWorldTo = args[2];
            try {
                List<String> teleported = WorldPlayerManager.getWorldPlayerManagerMain().worldTpPlayer(strWorldFrom, strWorldTo);
                commandSender.sendMessage(ChatColor.GREEN + String.format("Teleported(%d): %s", teleported.size(), teleported.toString()));
            } catch (Exception e) {
                commandSender.sendMessage(e.getMessage());
            }
        } else if (method.equalsIgnoreCase("list")) {
            WorldPlayerManagerMain managerMain = WorldPlayerManager.getWorldPlayerManagerMain();
            StringBuilder stringBuilder = new StringBuilder().append(ChatColor.YELLOW).append("Worlds:\n");
            for (World world : Bukkit.getWorlds()) {
                stringBuilder.append(world.getName());
                if (managerMain.getWorlds().contains(world.getName())) {
                    stringBuilder.append(" - CLOSED");
                }
                stringBuilder.append('\n');
            }
            commandSender.sendMessage(stringBuilder.toString());
        } else {
            return false;
        }

        return true;
    }
}
