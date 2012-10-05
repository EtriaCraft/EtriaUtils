package com.etriacraft.EtriaUtils.Listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import com.etriacraft.EtriaUtils.EtriaUtils;

public class BlockListener implements Listener {

	public static EtriaUtils plugin;
	
	public BlockListener(EtriaUtils instance) {
		plugin = instance;
	}
	
	@EventHandler
	public void onBlockP(BlockPlaceEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPermission("eu.build")) {
			player.sendMessage("§You do not have permission to build");
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockB(BlockBreakEvent e) {
		Player player = e.getPlayer();
		if (!player.hasPermission("eu.build")) {
			player.sendMessage("§cYou do not have permission to build");
			e.setCancelled(true);
		}
	}
}
