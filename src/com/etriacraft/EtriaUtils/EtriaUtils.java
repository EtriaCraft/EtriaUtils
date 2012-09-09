package com.etriacraft.EtriaUtils;

import java.io.File;
import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

import Listeners.PlayerListener;
import Listeners.SignListener;

public class EtriaUtils extends JavaPlugin {

	public static final Logger log = Logger.getLogger("Minecraft");
	public static final String PREFIX = "[EtriaUtils] ";
	
	@Override
	public void onEnable() {
		
		this.getServer().getPluginManager().registerEvents(new SignListener(), this);
		this.getServer().getPluginManager().registerEvents(new PlayerListener(), this);
		
	}

	public void onDisable() {
	}
	
}