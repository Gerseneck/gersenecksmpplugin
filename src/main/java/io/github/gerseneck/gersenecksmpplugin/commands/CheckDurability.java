package io.github.gerseneck.gersenecksmpplugin.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class CheckDurability implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§3Sorry, players only.");
            return true;
        }
        PlayerInventory inv = ((Player) sender).getInventory();
        ItemStack[] im = {inv.getItemInMainHand(),
                          inv.getItemInOffHand(),
                          inv.getHelmet(),
                          inv.getChestplate(),
                          inv.getLeggings(),
                          inv.getBoots()};
        for (ItemStack item : im) {
			if (item == null) {
				continue;
			}
            if (item.getType() == Material.AIR) {
				continue;
			}
			String color;
            String itemName = item.getType().toString().replaceAll("_"," ");

            int maxDurability = item.getType().getMaxDurability();
            int damage = ((Damageable) item.getItemMeta()).getDamage();

            float percentDurability = (float) (maxDurability - damage) / maxDurability;

            if (percentDurability >= 0.66) { color = "§2"; }
            else if (percentDurability >= 0.33) { color = "§6"; }
            else { color = "§c"; }

            sender.sendMessage(String.format("§9[%s]§r: %s%d/%d§r Durability", itemName, color, maxDurability - damage, maxDurability));
        }
        return true;
    }

}
