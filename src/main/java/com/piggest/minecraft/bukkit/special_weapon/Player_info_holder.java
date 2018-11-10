package com.piggest.minecraft.bukkit.special_weapon;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import com.rit.sucy.scoreboard.StatHolder;

public class Player_info_holder implements StatHolder {
	private Player player;
	
	public Player_info_holder(Player player) {
		this.player = player;
	}
	public List<String> getNames() {
		ArrayList<String> names = new ArrayList<String>();
		names.add("血量");
		return names;
	}

	public List<Integer> getValues() {
		ArrayList<Integer> values = new ArrayList<Integer>();
		values.add((int) player.getHealth());
		return values;
	}

}
