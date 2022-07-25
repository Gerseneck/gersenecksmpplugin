package io.github.gerseneck.gersenecksmpplugin;

import io.github.gerseneck.gersenecksmpplugin.commands.CheckDurability;
import io.github.gerseneck.gersenecksmpplugin.commands.PlayerOnline;
import org.bukkit.plugin.java.JavaPlugin;

public final class GerseneckSmpPluginMain extends JavaPlugin {

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        this.getCommand("durability").setExecutor(new CheckDurability());
        this.getCommand("online-players").setExecutor(new PlayerOnline(this));

        this.getLogger().info("Plugin Enabled.");
    }
}
