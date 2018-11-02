package com.piggest.minecraft.bukkit.special_weapon;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Immolate;
import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.Enchantments;

public class Immolate_manager extends BukkitRunnable {
	private Special_weapon plugin;
	private HashMap<UUID, Immolate_runner> runner_map = new HashMap<UUID, Immolate_runner>();

	public Immolate_manager(Special_weapon plugin) {
		this.plugin = plugin;
	}

	public void add(Immolate_runner new_runner) {
		if (this.runner_map.get(new_runner.get_user_uuid()) == null
				|| this.runner_map.get(new_runner.get_user_uuid()).isCancelled()) {
			try {
				new_runner.runTaskTimer(plugin, 0, 20);
			} catch (IllegalStateException e) {
				plugin.getLogger().info("运行错误！");
			}
			this.runner_map.put(new_runner.get_user_uuid(), new_runner);
		}
	}

	public void remove(UUID uuid) {
		if (this.runner_map.get(uuid) != null) {
			try {
				this.runner_map.get(uuid).cancel();
			} catch (IllegalStateException e) {

			}
			this.runner_map.remove(uuid);
		}
	}

	public void remove(Immolate_runner runner) {
		this.remove(runner.get_user_uuid());
	}

	public void run() {
		// plugin.getLogger().info("正在检测是否有献祭特效没有开启");
		for (Player player : Bukkit.getOnlinePlayers()) {
			ItemStack chest = player.getInventory().getChestplate();
			if (chest != null) {
				Map<CustomEnchantment, Integer> enchants = Enchantments.getCustomEnchantments(chest);
				for (CustomEnchantment enchant : enchants.keySet()) {
					if (enchant instanceof Immolate) {
						Immolate_runner new_runner = new Immolate_runner(player.getUniqueId(),
								1.0 * enchants.get(enchant));
						this.add(new_runner);
						return;
					}
				}
				this.remove(player.getUniqueId());
			}
			this.remove(player.getUniqueId());
		}
	}
}
