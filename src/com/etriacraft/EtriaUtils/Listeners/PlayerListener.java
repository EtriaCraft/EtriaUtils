package com.etriacraft.EtriaUtils.Listeners;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

import com.etriacraft.EtriaUtils.Config;
import com.etriacraft.EtriaUtils.EtriaUtils;
import com.etriacraft.EtriaUtils.Utils;

public class PlayerListener implements Listener{
	
	public HashMap<String, ItemStack[]> deathinventory = new HashMap<String, ItemStack[]>();
	public HashMap<String, ItemStack[]> deatharmor = new HashMap<String, ItemStack[]>();
		
	EtriaUtils plugin;
	
	public PlayerListener(EtriaUtils instance) {
		this.plugin = instance;
	}
	
	@EventHandler
    public void onPlayerDeath(PlayerDeathEvent e) {
        if (e.getEntity().hasPermission("eu.dropsave")) {
            deathinventory.put(e.getEntity().getName(), e.getEntity().getInventory().getContents());
            deatharmor.put(e.getEntity().getName(), e.getEntity().getInventory().getArmorContents());
            e.getDrops().clear();
        }
        
        if (e.getEntity().hasPermission("eu.saveexp")) {
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        }
    }
    
    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent e) {
        if (!deathinventory.containsKey(e.getPlayer().getName())) return;
        e.getPlayer().getInventory().setContents(deathinventory.get(e.getPlayer().getName()));
        e.getPlayer().getInventory().setArmorContents(deatharmor.get(e.getPlayer().getName()));
        deathinventory.remove(e.getPlayer().getName());
        deatharmor.remove(e.getPlayer().getName());
    }
    
    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerLogin(final PlayerLoginEvent e) {
    	plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
    		@Override
    		public void run() {
    			plugin.getServer().getPluginCommand("list").execute(e.getPlayer(), "list", null);
    		}
    	}, 1L);
    }
    
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
    	e.setQuitMessage(Utils.colorize(Config.leave_message).replaceAll("<name>", e.getPlayer().getName()));
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
    	Player p = e.getPlayer();
    	if (!p.hasPlayedBefore()) {
    		e.setJoinMessage(Utils.colorize(Config.welcome_message).replaceAll("<name>", e.getPlayer().getName()));
    	} else {
    		e.setJoinMessage(Utils.colorize(Config.join_message).replaceAll("<name>", e.getPlayer().getName()));
    }
    }
    
    @EventHandler
    public void onPlayerKick(PlayerKickEvent e) {
    	e.setLeaveMessage(Utils.colorize(Config.leave_message).replaceAll("<name>", e.getPlayer().getName()));
    }
    
}