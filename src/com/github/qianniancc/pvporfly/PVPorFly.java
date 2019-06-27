package com.github.qianniancc.pvporfly;

import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PVPorFly extends JavaPlugin implements Listener {
	Player p;

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {

		try {
			p = (Player) e.getDamager();
		} catch (Exception e1) {
			return;
		}
		if (p.hasPermission("pvpandfly") || p.isOp() || p.hasPermission("*")) {
			return;
		}
		if (p.isFlying()) {
			if (e.getEntity().getType() == EntityType.PLAYER) {
				Bukkit.getServer().dispatchCommand(p, "fly " + p);
				p.setFlying(false);
				p.sendMessage("§e警告：你不能在飞行时候攻击");
				e.setCancelled(true);
			}

		}

	}
}
