package io.github.gerseneck.gersenecksmpplugin.commands;

import org.bukkit.Material;
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
            sender.sendMessage("§cSpecify a slot to check! HAND, OFFHAND, HELMET, CHESTPLATE, LEGGINGS, BOOTS.");
            return true;
        }
        if (args.length == 1) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§3Sorry, players only.");
                return true;
            }
            PlayerInventory inv = ((Player) sender).getInventory();
            ItemStack im;
            switch (args[0].toLowerCase()) {
                case "hand":
                    im = inv.getItemInMainHand();
                    break;
                case "offhand":
                    im = inv.getItemInOffHand();
                    break;
                case "helmet":
                    im = inv.getHelmet();
                    break;
                case "chestplate":
                    im = inv.getChestplate();
                    break;
                case "leggings":
                    im = inv.getLeggings();
                    break;
                case "boots":
                    im = inv.getBoots();
                    break;
                default:
                    sender.sendMessage("§cSpecify a slot to check! HAND, OFFHAND, HELMET, CHESTPLATE, LEGGINGS, BOOTS.");
                    return true;
            }
            if (im == null || im.getType() == Material.AIR) {
                sender.sendMessage("§3There are no items in this slot.");
                return true;
            }
            if (!(im.getItemMeta() instanceof Damageable)) {
                sender.sendMessage("§3This Item does not have any durability.");
                return true;
            }
            int maxDurability = im.getType().getMaxDurability();
            int damage = ((Damageable) im.getItemMeta()).getDamage();
            String color;
            float percentDura = (float) (maxDurability - damage) / (float) maxDurability;
            if (percentDura >= 0.66) { color = "§2"; }
            else if (percentDura >= 0.33) { color = "§6"; }
            else { color = "§c"; }
            sender.sendMessage(String.format("Your §9[%s]§r has %s%d/%d§r Durability left.", im.getType().toString().replaceAll("_"," "), color, maxDurability - damage, maxDurability));
            return true;
        }
        return false;
    }

}
