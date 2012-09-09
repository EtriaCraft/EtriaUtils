package com.etriacraft.EtriaUtils;

import org.bukkit.configuration.Configuration;

public class Config {
	
	public static Configuration config;
	
	// Chat Strings
	public static String message_format;
	public static String modchat_format;
	
	// join / quit messages
	public static String join_message;
	public static String leave_message;
	public static String welcome_message;
	
	public static void load(EtriaUtils plugin) {
		config = plugin.getConfig();
		
		// Chat
		config.set("chat.message_format", message_format = config.getString("chat.message_format", "<prefix><name>&6:&f <message>"));
		config.set("chat.modchat_format", modchat_format = config.getString("chat.modchat_format", "&c[&aModChat&c] &c<name>&7:&a <message>"));
		// Join / Quit messages
		config.set("join_message", join_message = config.getString("join_message", "&6<name> has joined the game."));
		config.set("leave_message", leave_message = config.getString("leave_message", "&6<name> has left the game."));
		config.set("welcome_message", welcome_message = config.getString("welcome_message", "&5Welcome &6<name> &5to the server!"));
		
		plugin.saveConfig();
	}

}
