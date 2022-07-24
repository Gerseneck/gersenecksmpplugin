package io.github.com.gerseneck.gersenecksmpplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
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
            else if (args[0].equalsIgnoreCase("offhand")) { im = inv.getItemInOffHand(); }
            else if (args[0].equalsIgnoreCase("helmet")) { im = inv.getHelmet(); }
            else if (args[0].equalsIgnoreCase("chestplate")) { im = inv.getChestplate(); }
            else if (args[0].equalsIgnoreCase("leggings")) { im = inv.getLeggings(); }
            else if (args[0].equalsIgnoreCase("boots")) { im = inv.getBoots(); }
            else {
                sender.sendMessage("§4Specify a slot to check! HAND, OFFHAND, HELMET, CHESTPLATE, LEGGINGS, BOOTS.");
                return true;
            }
            if (im == null) {
                sender.sendMessage("§3There are no items in this slot.");
                return true;
            }
            if (!(im.getItemMeta() instanceof Damageable)) {
                sender.sendMessage("§3This Item does not have any durability.");
                return true;
            }
            int maxDurability = im.getType().getMaxDurability();
            int damage = ((Damageable) im.getItemMeta()).getDamage();
            sender.sendMessage(String.format("Item has %d/%d Durability left.", maxDurability - damage, maxDurability));
        }
        return false;
    }

}
