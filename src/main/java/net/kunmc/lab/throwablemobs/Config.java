package net.kunmc.lab.throwablemobs;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    private Plugin plugin;
    private FileConfiguration config;
    public Config(Plugin plugin){
        this.plugin = plugin;
    }
    public void load() {
        plugin.saveDefaultConfig();
        if (config != null) {
            plugin.reloadConfig();
        }
        config = plugin.getConfig();
        slamDamage = config.getDouble("slamDamage",4);
    }

    private double slamDamage;

    public double getSlamDamage() {
        return slamDamage;
    }
}
