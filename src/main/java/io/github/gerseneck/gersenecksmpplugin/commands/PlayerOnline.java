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
            String text = "";
            String name = playerOnline.getName();
            if (playerOnline.getGameMode() == GameMode.SPECTATOR &&
                    !plugin.getConfig().getBoolean("playerOnlineCommand.showSpectator")) {
                continue;
            }
            if (playerOnline.getGameMode() == GameMode.SPECTATOR) { text += "§oSP§r"; }
            text += name;
            if (plugin.getConfig().getBoolean("playerOnlineCommand.showHealth")) {
                int health = (int) playerOnline.getHealth();
                text += " §6" + health + "HP§r";
            }
            sender.sendMessage(text);
        }
        return true;
    }
}
