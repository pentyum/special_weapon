package com.piggest.minecraft.bukkit.special_weapon;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Bloodsucking;
import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Immolate;
import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Repair;
import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Ruined;
import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Slowing;
import com.sucy.enchant.api.EnchantPlugin;
import com.sucy.enchant.api.EnchantmentRegistry;

import net.milkbowl.vault.economy.Economy;

public class Special_weapon extends JavaPlugin implements EnchantPlugin {
	// private final Use_weapon_listener use_weapon_listener = new
	// Use_weapon_listener(this);
	private FileConfiguration config = null;
	private Economy economy = null;
	private Immolate_manager immolate_manager = null;

	private boolean initVault() {
		boolean hasNull = false;
		RegisteredServiceProvider<Economy> economyProvider = getServer().getServicesManager()
				.getRegistration(net.milkbowl.vault.economy.Economy.class);
		if (economyProvider != null) {
			if ((economy = economyProvider.getProvider()) == null) {
				hasNull = true;
			}
		}
		return !hasNull;
	}

	@Override
	public void onEnable() {
		saveDefaultConfig();
		this.config = getConfig();

		getLogger().info("使用Vault");
		if (!initVault()) {
			getLogger().severe("初始化Vault失败,请检测是否已经安装Vault插件和经济插件");
			return;
		}
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new Get_exp_listener(), this);
		
		this.immolate_manager = new Immolate_manager(this);
		this.immolate_manager.runTaskTimerAsynchronously(this, 5, 2);
		// PluginManager pm = getServer().getPluginManager();
		// pm.registerEvents(use_weapon_listener, this);
	}

	public void registerEnchantments(EnchantmentRegistry reg) {
		reg.register(new Bloodsucking(), new Ruined(), new Immolate(), new Slowing(), new Repair());
	}

	public Immolate_manager get_immolate_manager() {
		return this.immolate_manager;
	}
}
