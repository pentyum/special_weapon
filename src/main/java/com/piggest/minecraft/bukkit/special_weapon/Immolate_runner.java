package com.piggest.minecraft.bukkit.special_weapon;

import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.scheduler.BukkitRunnable;

public class Immolate_runner extends BukkitRunnable {
	private UUID user_uuid;
	private double damage;

	public Immolate_runner(UUID user_uuid, final double damage) {
		this.user_uuid = user_uuid;
		this.damage = damage;
	}

	public Entity get_user() {
		return Bukkit.getEntity(this.user_uuid);
	}

	public UUID get_user_uuid() {
		return this.user_uuid;
	}

	public void run() {
		Entity user = this.get_user();
		if (user == null) {
			return;
		}
		user.getWorld().spawnParticle(Particle.FLAME, user.getLocation(), 10, 1, 1, 1, 0.01);
		List<Entity> nearby_entities = user.getNearbyEntities(1, 1, 1);
		for (Entity entity : nearby_entities) {
			if (entity instanceof LivingEntity) {
				LivingEntity living = (LivingEntity) entity;
				//user.sendMessage("检测到" + living.getName() + "伤害" + damage);
				living.damage(damage, user);
			}
		}
	}

}
