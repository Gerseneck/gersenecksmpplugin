package io.github.gerseneck.gersenecksmpplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static io.github.gerseneck.gersenecksmpplugin.GerseneckSmpPluginMain.lastMessaged;

public class MsgCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§3Sorry, players only.");
            return true;
        }
        if (args.length < 2) {
            sender.sendMessage("§3Usage: /msg <player> <message>");
            return true;
        }
        Player target = sender.getServer().getPlayer(args[0]);
        if (target == null || !target.isOnline()) {
            sender.sendMessage("§3Player not found.");
            lastMessaged.remove(((Player) sender).getUniqueId());
            return true;
        }
        String message = String.join(" ", args).substring(args[0].length() + 1);
        sender.sendMessage(String.format("§7§oYou whisper to %s: %s", target.getName(), message));
        target.sendMessage(String.format("§7§o%s whispers to you: %s", sender.getName(), message));

        lastMessaged.put(target.getUniqueId(), ((Player) sender).getUniqueId());
        lastMessaged.put(((Player) sender).getUniqueId(), target.getUniqueId());

        return true;
    }
}
