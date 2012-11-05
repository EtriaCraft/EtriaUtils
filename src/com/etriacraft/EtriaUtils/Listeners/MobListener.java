package com.etriacraft.EtriaUtils.Listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

import com.etriacraft.EtriaUtils.Config;
import com.etriacraft.EtriaUtils.EtriaUtils;

public class MobListener implements Listener {
	
public static EtriaUtils plugin;
	
	public MobListener(EtriaUtils instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void WitherSpawnEvent(CreatureSpawnEvent event) {
		Entity e = event.getEntity();
		EntityType type = event.getEntity().getType();
		String world = e.getWorld().getName();
		if (type == EntityType.WITHER && !Config.WitherSpawnWorlds.contains(world)) {
			event.setCancelled(true);
		}
	}

}
