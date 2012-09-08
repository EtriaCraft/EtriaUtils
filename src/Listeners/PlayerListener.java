package Listeners;

import java.util.HashMap;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerListener implements Listener{
	
	public HashMap<String, ItemStack[]> deathinventory = new HashMap<String, ItemStack[]>();
	public HashMap<String, ItemStack[]> deatharmor = new HashMap<String, ItemStack[]>();
		
	@EventHandler()
    public void onPlayerDeathEvent(PlayerDeathEvent e) {
    	if (e.getEntity().hasPermission("eu.saveitems")) {
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
	}