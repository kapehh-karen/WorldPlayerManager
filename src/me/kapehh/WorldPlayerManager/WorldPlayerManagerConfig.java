package me.kapehh.WorldPlayerManager;

import me.kapehh.WorldPlayerManager.manager.WorldPlayerManagerMain;
import me.kapehh.main.pluginmanager.config.EventPluginConfig;
import me.kapehh.main.pluginmanager.config.EventType;
import org.bukkit.configuration.file.FileConfiguration;

/**
 * Created by Karen on 28.07.2014.
 */
public class WorldPlayerManagerConfig {

    @EventPluginConfig(EventType.LOAD)
    public void onLoad() {
        FileConfiguration cfg = WorldPlayerManager.getPluginConfig().getConfig();
        WorldPlayerManager.setWorldPlayerManagerMain(
            new WorldPlayerManagerMain(cfg.getStringList("closedworlds"), cfg.getString("joinafterclose"))
        );
    }

    @EventPluginConfig(EventType.SAVE)
    public void onSave() {
        FileConfiguration cfg = WorldPlayerManager.getPluginConfig().getConfig();
        cfg.set("closedworlds", WorldPlayerManager.getWorldPlayerManagerMain().getWorlds());
    }
}
