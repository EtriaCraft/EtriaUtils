package com.etriacraft.EtriaUtils;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import com.etriacraft.EtriaUtils.Listeners.PlayerListener;
import com.etriacraft.EtriaUtils.Listeners.SignListener;


public class EtriaUtils extends JavaPlugin {

	public static final Logger log = Logger.getLogger("Minecraft");
	public static final String PREFIX = "[EtriaUtils] ";
	
	@Override
	public void onEnable() {

		// Register Listeners
		this.getServer().getPluginManager().registerEvents(new SignListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(this), this);
		
	}
}