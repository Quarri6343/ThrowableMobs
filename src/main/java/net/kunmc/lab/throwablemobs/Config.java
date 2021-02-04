package net.kunmc.lab.throwablemobs;

import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class Config {
    private Plugin plugin;
    private FileConfiguration config;
    public Config(Plugin plugin){
        this.plugin = plugin;
        load();
    }
    public void load() {
        plugin.saveDefaultConfig();
        if (config != null) {
            plugin.reloadConfig();
        }
        config = plugin.getConfig();
        slamDamage = config.getDouble("slamDamage",4);
        enableLiftingPlayer = config.getBoolean("enableLiftingPlayer",true);
    }

    private double slamDamage;
    private boolean enableLiftingPlayer;

    public double getSlamDamage() {
        return slamDamage;
    }
    public boolean enableLiftingPlayer(){
        return enableLiftingPlayer;
    }
}
