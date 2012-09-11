package com.etriacraft.EtriaUtils;

import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;

import com.etriacraft.EtriaUtils.Listeners.PlayerListener;
import com.etriacraft.EtriaUtils.Listeners.SignListener;
import com.etriacraft.EtriaUtils.messaging.ChatProv;
import com.etriacraft.EtriaUtils.messaging.MessageCmds;


public class EtriaUtils extends JavaPlugin {
	
	ChatProv cp;
	MessageCmds mc;
	
	@Override
	public void onEnable() {

		// Metrics Stuff
		try {
		    MetricsLite metrics = new MetricsLite(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		// Load the Config
		Config.load(this);
		
		// Register Listeners
		this.getServer().getPluginManager().registerEvents(new SignListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		cp = new ChatProv(this);
		mc = new MessageCmds(this);
	}
	
}