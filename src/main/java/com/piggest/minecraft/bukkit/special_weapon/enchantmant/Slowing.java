package com.piggest.minecraft.bukkit.special_weapon.enchantmant;

import org.bukkit.Material;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;

public class Slowing extends CustomEnchantment {

	public Slowing() {
		super("减速", "对目标造成 level*15% 的减速，持续3秒");
		this.setMaxLevel(3);
		this.addNaturalItems(ItemSet.SWORDS.getItems());
		this.addNaturalItems(Material.BOW);
		this.setTableEnabled(false);
	}
	
	@Override
	public void applyOnHit(LivingEntity user, LivingEntity target, int enchantLevel, EntityDamageByEntityEvent event) {
		if (event.isCancelled() == true) {
			return;
		}
		PotionEffect effect = new PotionEffect(PotionEffectType.SLOW, 3 * 20, enchantLevel);
		target.addPotionEffect(effect ,true);
	}
}
