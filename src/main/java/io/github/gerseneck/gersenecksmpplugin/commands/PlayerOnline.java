package io.github.gerseneck.gersenecksmpplugin.commands;

import io.github.gerseneck.gersenecksmpplugin.GerseneckSmpPluginMain;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerOnline implements CommandExecutor {

    private final GerseneckSmpPluginMain plugin;
    public PlayerOnline(GerseneckSmpPluginMain plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.getServer().getOnlinePlayers().size() == 0) {
            sender.sendMessage("§3There are no Players online!");
            return true;
        }

        for (Player playerOnline : sender.getServer().getOnlinePlayers()) {
            if (playerOnline.getGameMode() == GameMode.SPECTATOR) {
                if (!plugin.getConfig().getBoolean("playerOnlineCommand.showSpectators")) {
                    continue;
                }
                sender.sendMessage("§3" + playerOnline.getName() + " §7(Spectator)");
                continue;
            }
            if (plugin.getConfig().getBoolean("playerOnlineCommand.showHealth")) {
                sender.sendMessage("§3" + playerOnline.getName() + " §4Health: " + playerOnline.getHealth());
                continue;
            }
            sender.sendMessage("§3" + playerOnline.getName());
        }
        return true;
    }
}
