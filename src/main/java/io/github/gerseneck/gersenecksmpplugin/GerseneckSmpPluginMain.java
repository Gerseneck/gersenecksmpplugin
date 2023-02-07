package io.github.gerseneck.gersenecksmpplugin;

import io.github.gerseneck.gersenecksmpplugin.commands.CheckDurability;
import io.github.gerseneck.gersenecksmpplugin.commands.MsgCommand;
import io.github.gerseneck.gersenecksmpplugin.commands.PlayerOnline;
import io.github.gerseneck.gersenecksmpplugin.commands.ReplyCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class GerseneckSmpPluginMain extends JavaPlugin {

    public static HashMap<UUID, UUID> lastMessaged = new HashMap<>();

    @Override
    public void onEnable() {

        this.saveDefaultConfig();
        this.getConfig().options().copyDefaults(true);

        this.getCommand("durability").setExecutor(new CheckDurability());
        this.getCommand("online-players").setExecutor(new PlayerOnline(this));
        this.getCommand("msg").setExecutor(new MsgCommand());
        this.getCommand("reply").setExecutor(new ReplyCommand());

        this.getLogger().info("Plugin Enabled.");
    }
}
