package io.github.com.gerseneck.gersenecksmpplugin;

import io.github.com.gerseneck.gersenecksmpplugin.commands.CheckDurability;
import org.bukkit.plugin.java.JavaPlugin;

public final class GersenecksmppluginMain extends JavaPlugin {

    @Override
    public void onEnable() {

        this.getCommand("durability").setExecutor(new CheckDurability());
    }
}
