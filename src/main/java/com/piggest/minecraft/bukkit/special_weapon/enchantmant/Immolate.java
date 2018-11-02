package com.piggest.minecraft.bukkit.special_weapon.enchantmant;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;

public class Immolate extends CustomEnchantment {

	public Immolate() {
		super("献祭", "戴上后每秒对周围实体造成 1*附魔等级 火焰伤害");
		this.setMaxLevel(5);
		this.addNaturalItems(ItemSet.CHESTPLATES.getItems());
		this.setTableEnabled(false);
	}

	/*
	@Override
	public void applyEquip(final LivingEntity user, final int level) {
		user.sendMessage("献祭特效已经开启");
		Immolate_runner new_runner = new Immolate_runner(user.getUniqueId(), 1.0 * level);
		plugin.get_immolate_manager().add(new_runner);
	}

	@Override
	public void applyUnequip(final LivingEntity user, final int level) {
		user.sendMessage("献祭特效已经消失");
		plugin.get_immolate_manager().remove(user.getUniqueId());
	}
	*/
}
