package io.github.gerseneck.gersenecksmpplugin.commands;

import io.github.gerseneck.gersenecksmpplugin.GerseneckSmpPluginMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.gerseneck.gersenecksmpplugin.GerseneckSmpPluginMain.lastMessaged;

public class ReplyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§3Sorry, players only.");
            return true;
        }
        if (args.length < 1) {
            sender.sendMessage("§3Usage: /reply <message>");
            return true;
        }
        if (GerseneckSmpPluginMain.lastMessaged.containsKey(((Player) sender).getUniqueId())) {
            sender.sendMessage("§3You have not messaged anyone yet.");
            return true;
        }
        Player target = sender.getServer().getPlayer(GerseneckSmpPluginMain.lastMessaged.get(((Player) sender).getUniqueId()));
        if (target == null) {
            sender.sendMessage("§3Player not found.");
            lastMessaged.remove(((Player) sender).getUniqueId());
            return true;
        }
        sender.sendMessage(String.format("§7§oYou whisper to %s: %s", GerseneckSmpPluginMain.lastMessaged.get(((Player) sender).getUniqueId()), String.join(" ", args)));
        return true;
    }
}
