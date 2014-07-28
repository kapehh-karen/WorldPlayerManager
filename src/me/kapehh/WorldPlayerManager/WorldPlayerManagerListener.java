package me.kapehh.WorldPlayerManager;

import me.kapehh.WorldPlayerManager.manager.WorldPlayerManagerMain;
import me.kapehh.WorldPlayerManager.manager.WorldPlayerManagerPermissions;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPortalEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

/**
 * Created by Karen on 28.07.2014.
 */
public class WorldPlayerManagerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (WorldPlayerManager.getPermission().has(event.getPlayer(), WorldPlayerManagerPermissions.PERM_ADMIN)) {
            return;
        }
        WorldPlayerManagerMain managerMain = WorldPlayerManager.getWorldPlayerManagerMain();
        Player player = event.getPlayer();
        if (managerMain.getWorlds().contains(player.getWorld().getName())) {
            player.teleport(managerMain.getMainWorld().getSpawnLocation());
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        if (WorldPlayerManager.getPermission().has(event.getPlayer(), WorldPlayerManagerPermissions.PERM_ADMIN)) {
            return;
        }
        WorldPlayerManagerMain managerMain = WorldPlayerManager.getWorldPlayerManagerMain();
        String worldName = event.getTo().getWorld().getName();
        if (managerMain.getWorlds().contains(worldName)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "World '" + worldName + "' closed!");
        }
    }

    @EventHandler
    public void onPlayerPortal(PlayerPortalEvent event) {
        if (WorldPlayerManager.getPermission().has(event.getPlayer(), WorldPlayerManagerPermissions.PERM_ADMIN)) {
            return;
        }
        WorldPlayerManagerMain managerMain = WorldPlayerManager.getWorldPlayerManagerMain();
        String worldName = event.getTo().getWorld().getName();
        if (managerMain.getWorlds().contains(worldName)) {
            event.setCancelled(true);
            event.getPlayer().sendMessage(ChatColor.RED + "World '" + worldName + "' closed!");
        }
    }
}
