package com.piggest.minecraft.bukkit.special_weapon.enchantmant;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;

public class Bloodsucking extends CustomEnchantment {
	public Bloodsucking() {
		super("吸血", "每次攻击回复 10%*附魔等级*实际伤害量 的血量");
		this.setMaxLevel(5);
		this.addNaturalItems(ItemSet.SWORDS.getItems());
		this.addNaturalItems(Material.BOW);
		this.setTableEnabled(false);
	}

	@Override
	public void applyOnHit(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event) {
		if (event.isCancelled() == true) {
			return;
		}
		double sucking = event.getFinalDamage() * 0.1 * enchantLevel;
		double max_health = user.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		user.setHealth(Math.min(max_health, user.getHealth() + sucking));
	}
}
