package io.github.com.gerseneck.gersenecksmpplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class CheckDurability implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage("§4Specify a slot to check! HAND, OFFHAND, HELMET, CHESTPLATE, LEGGINGS, BOOTS.");
            return true;
        }
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§3Sorry, players only.");
                return true;
            }
            PlayerInventory inv = ((Player) sender).getInventory();
            ItemStack im = null;
            if (args[0].equalsIgnoreCase("hand")) { im = inv.getItemInHand(); }
            if (args[0].equalsIgnoreCase("offhand")) { im = inv.getItemInOffHand(); }
            if (args[0].equalsIgnoreCase("helmet")) { im = inv.getHelmet(); }
            if (args[0].equalsIgnoreCase("chestplate")) { im = inv.getChestplate(); }
            if (args[0].equalsIgnoreCase("leggings")) { im = inv.getLeggings(); }
            if (args[0].equalsIgnoreCase("boots")) { im = inv.getBoots(); }
            if (!(im.getItemMeta() instanceof Damageable)) {
                sender.sendMessage("§3This Item does not have any durability.");
                return true;
            }
            int health = (int) ((Damageable) im.getItemMeta()).getHealth();
            int totalHealth = (int) ((Damageable) im.getItemMeta()).getMaxHealth();
            sender.sendMessage(String.format("Item %s has %d Durability left. Total Durability %d", im.getItemMeta().getDisplayName(), health, totalHealth));
        }
        return false;
    }

}
