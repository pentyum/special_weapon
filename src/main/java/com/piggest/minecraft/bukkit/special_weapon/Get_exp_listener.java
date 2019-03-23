package com.piggest.minecraft.bukkit.special_weapon;

import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.inventory.ItemStack;

import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Repair;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.Enchantments;

@SuppressWarnings("deprecation")
public class Get_exp_listener implements Listener {
	@EventHandler
	public void on_get_exp(PlayerExpChangeEvent event) {
		int amount = event.getAmount();
		if (amount <= 0) {
			return;
		}
		Player player = event.getPlayer();
		// ItemStack[] armors = player.getInventory().getArmorContents();
		ItemStack hand_item = player.getItemInHand();
		final Map<CustomEnchantment, Integer> enchants = Enchantments.getCustomEnchantments(hand_item);
		for (Entry<CustomEnchantment, Integer> entry : enchants.entrySet()) {
			CustomEnchantment enchant = entry.getKey();
			int level = entry.getValue();
			if (enchant instanceof Repair) {
				short durability = (short) (hand_item.getDurability() - level);
				if (durability < 0) {
					durability = 0;
				}
				hand_item.setDurability(durability);
				player.giveExp(-amount);
				return;
			}
		}
	}
}
