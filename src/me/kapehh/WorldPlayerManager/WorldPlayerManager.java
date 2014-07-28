package me.kapehh.WorldPlayerManager;

import me.kapehh.WorldPlayerManager.manager.WorldPlayerManagerMain;
import me.kapehh.main.pluginmanager.checker.PluginChecker;
import me.kapehh.main.pluginmanager.config.PluginConfig;
import me.kapehh.main.pluginmanager.vault.PluginVault;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by Karen on 28.07.2014.
 */
public class WorldPlayerManager extends JavaPlugin {
    private static WorldPlayerManager instance;
    private static Permission permission;
    private static PluginConfig pluginConfig;
    private static WorldPlayerManagerMain worldPlayerManagerMain;

    public static WorldPlayerManager getInstance() {
        return instance;
    }

    public static Permission getPermission() {
        return permission;
    }

    public static PluginConfig getPluginConfig() {
        return pluginConfig;
    }

    public static WorldPlayerManagerMain getWorldPlayerManagerMain() {
        return worldPlayerManagerMain;
    }

    public static void setWorldPlayerManagerMain(WorldPlayerManagerMain worldPlayerManagerMain) {
        WorldPlayerManager.worldPlayerManagerMain = worldPlayerManagerMain;
    }

    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PluginManager") == null) {
            getLogger().info("PluginManager not found!!!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        PluginChecker pluginChecker = new PluginChecker(this);
        if (!pluginChecker.check("Vault")) {
            return;
        }

        instance = this;

        permission = PluginVault.setupPermissions();

        pluginConfig = new PluginConfig(this).addEventClasses(new WorldPlayerManagerConfig()).setup(); // Initialize
        pluginConfig.loadData();

        getCommand("worldplayermanager").setExecutor(new WorldPlayerManagerExecuter());

        getServer().getPluginManager().registerEvents(new WorldPlayerManagerListener(), this);
    }

    @Override
    public void onDisable() {
        if (pluginConfig != null) {
            pluginConfig.saveData();
        }
        instance = null;
    }
}
