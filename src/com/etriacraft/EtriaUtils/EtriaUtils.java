package com.etriacraft.EtriaUtils;

import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;

import com.etriacraft.EtriaUtils.Listeners.BlockListener;
import com.etriacraft.EtriaUtils.Listeners.PlayerListener;
import com.etriacraft.EtriaUtils.Listeners.SignListener;
import com.etriacraft.EtriaUtils.messaging.ChatProv;
import com.etriacraft.EtriaUtils.messaging.MessageCmds;


public class EtriaUtils extends JavaPlugin {
	
	ChatProv cp;
	MessageCmds mc;
	
	@Override
	public void onEnable() {

		// Load the Config
		Config.load(this);
		
		// Register Listeners
		this.getServer().getPluginManager().registerEvents(new SignListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		this.getServer().getPluginManager().registerEvents(new BlockListener(this), this);
		cp = new ChatProv(this);
		mc = new MessageCmds(this);
	}
	
	public void castFakeLightningAtLocation(Location location) {
		location.getWorld().strikeLightningEffect(location);
	}
	
}