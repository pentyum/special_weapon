package com.piggest.minecraft.bukkit.special_weapon.enchantmant;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;

public class Ruined extends CustomEnchantment {

	public Ruined() {
		super("破败", "对目标造成 2%*附魔等级*目标当前生命值 的额外伤害，最高为20");
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
		double add_damage = Math.max(target.getHealth() * 0.02 * enchantLevel, 20);
		event.setDamage(event.getDamage() + add_damage);
	}
}
