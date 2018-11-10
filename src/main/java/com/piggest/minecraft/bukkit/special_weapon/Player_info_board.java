package com.piggest.minecraft.bukkit.special_weapon;

import org.bukkit.entity.Player;

import com.rit.sucy.scoreboard.StatBoard;

public class Player_info_board extends StatBoard {
	public Player_info_board(String title,Player player, String plugin) {
		super(title, plugin);
		this.setPlayer(player);
	}
}
