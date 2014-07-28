package me.kapehh.WorldPlayerManager.manager;

import me.kapehh.WorldPlayerManager.WorldPlayerManager;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Karen on 28.07.2014.
 */
public class WorldPlayerManagerMain {
    private World mainWorld;
    private List<String> worlds;

    public WorldPlayerManagerMain(List<String> strWorlds, String mainWorld) {
        this.worlds = strWorlds;
        this.mainWorld = Bukkit.getWorld(mainWorld);
        if (this.mainWorld == null) {
            WorldPlayerManager.getInstance().getLogger().info("World not found " + mainWorld);
            this.mainWorld = Bukkit.getWorlds().get(0);
        }
    }

    public List<String> getWorlds() {
        return worlds;
    }

    public World getMainWorld() {
        return mainWorld;
    }

    public void worldOpen(String worldName) throws Exception {
        worlds.remove(worldName);
        if (Bukkit.getWorld(worldName) == null) {
            throw new Exception("World not found: " + worldName);
        }
    }

    public void worldClose(String worldName) throws Exception {
        if (Bukkit.getWorld(worldName) == null) {
            throw new Exception("World not found: " + worldName);
        } else if (!worlds.contains(worldName)) {
            worlds.add(worldName);
        }
    }

    public List<String> worldTpPlayer(String worldNameFrom, String worldNameTo) throws Exception {
        Permission permission = WorldPlayerManager.getPermission();
        World worldFrom = Bukkit.getWorld(worldNameFrom);
        World worldTo = Bukkit.getWorld(worldNameTo);
        if (worldFrom == null) {
            throw new Exception("World not found: " + worldNameFrom);
        }
        if (worldTo == null) {
            throw new Exception("World not found: " + worldNameTo);
        }
        ArrayList<String> teleportedPlayers = new ArrayList<String>();
        for (Player player : worldFrom.getPlayers()) {
            if (!permission.has(player, WorldPlayerManagerPermissions.PERM_ADMIN)) {
                player.teleport(worldTo.getSpawnLocation());
                teleportedPlayers.add(player.getName());
            }
        }
        return teleportedPlayers;
    }
}
