package com.piggest.minecraft.bukkit.special_weapon.enchantmant;

import com.sucy.enchant.api.CustomEnchantment;
import com.sucy.enchant.api.ItemSet;

public class Repair extends CustomEnchantment {
	public Repair() {
		super("经验修补", "每获得1点经验，修复工具level点耐久");
		this.setMaxLevel(2);
		this.addNaturalItems(ItemSet.DURABILITY.getItems());
		this.setTableEnabled(false);
	}
}
