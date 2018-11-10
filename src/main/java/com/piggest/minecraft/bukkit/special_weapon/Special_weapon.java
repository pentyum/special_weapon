package com.piggest.minecraft.bukkit.special_weapon;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Bloodsucking;
import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Immolate;
import com.piggest.minecraft.bukkit.special_weapon.enchantmant.Ruined;
import com.rit.sucy.scoreboard.Board;
import com.rit.sucy.scoreboard.BoardManager;
import com.rit.sucy.scoreboard.PlayerBoards;
import com.rit.sucy.scoreboard.StatBoard;
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

		this.immolate_manager = new Immolate_manager(this);
		this.immolate_manager.runTaskTimerAsynchronously(this, 5, 2);
		// PluginManager pm = getServer().getPluginManager();
		// pm.registerEvents(use_weapon_listener, this);
	}

	public void registerEnchantments(EnchantmentRegistry reg) {
		reg.register(new Bloodsucking(), new Ruined(), new Immolate());
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("special_weapon")) {
			if (!(sender instanceof Player)) {
				return true;
			}
			Player player = (Player) sender;
			String msg = "血量: " + player.getHealth() + '/'
					+ player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			msg += "\n攻击力: " + player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).getValue();
			msg += "\n攻击速度: " + player.getAttribute(Attribute.GENERIC_ATTACK_SPEED).getValue();
			msg += "\n护甲: " + player.getAttribute(Attribute.GENERIC_ARMOR).getValue();
			msg += "\n韧性: " + player.getAttribute(Attribute.GENERIC_ARMOR_TOUGHNESS).getValue();
			msg += "\n移速: " + player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).getValue();
			player.sendMessage(msg);
			PlayerBoards player_data = BoardManager.getPlayerBoards(player.getName());
			if (player_data != null) {
				//Player_info_board board = new Player_info_board("信息", player, this.getName());
				StatBoard statBoard = new StatBoard("信息", this.getName());
				statBoard.setPlayer(player);
				statBoard.addStats(new Player_info_holder(player));
				if (player_data.getBoard("信息") == null) {
					player_data.addBoard(statBoard);
					player_data.showBoard("信息");
				}
			}
			if (args.length == 3) {
				if (args[1].equalsIgnoreCase("animals")) {

				} else if (args[1].equalsIgnoreCase("npc")) {

				} else if (args[1].equalsIgnoreCase("player")) {

				} else if (args[1].equalsIgnoreCase("monster")) {

				}
			} else if (args.length == 1) {

			}
		}
		return true;
	}

	public Immolate_manager get_immolate_manager() {
		return this.immolate_manager;
	}
}
