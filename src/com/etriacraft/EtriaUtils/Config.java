package com.etriacraft.EtriaUtils;

import java.util.List;

import org.bukkit.configuration.Configuration;

public class Config {
	
	public static Configuration config;
	
	// Chat Strings
	public static String message_format;
	public static String me_format;
	public static String say_format;
	public static String broadcast_format;
	public static String modchat_format;
	
	// join / quit messages
	public static String join_message;
	public static String leave_message;
	public static String welcome_message;
	
	// Nope
	public static List<String> WitherSpawnWorlds;
	
	public static void load(EtriaUtils plugin) {
		config = plugin.getConfig();
		
		// Chat
		config.set("chat.message_format", message_format = config.getString("chat.message_format", "<prefix><name>&6:&f <message>"));
		config.set("chat.me_format", me_format = config.getString("chat.me_format", "* <prefix><name> &f<message> *"));
		config.set("chat.say_format", say_format = config.getString("chat.say_format", "<&c*Console&f> &7<message>"));
		config.set("chat.broadcast_format", broadcast_format = config.getString("chat.broadcast_format", "&5[&7Broadcast&5] &5<message>"));
		config.set("chat.modchat_format", modchat_format = config.getString("chat.modchat_format", "&c[&aModChat&c] &c<name>&7:&a <message>"));
		// Join / Quit messages
		config.set("join_message", join_message = config.getString("join_message", "&6<name> has joined the game."));
		config.set("leave_message", leave_message = config.getString("leave_message", "&6<name> has left the game."));
		config.set("welcome_message", welcome_message = config.getString("welcome_message", "&5Welcome &6<name> &5to the server!"));
		//
		if (!config.contains("WitherSpawnWorlds")) {
			final String[] def_wpw = {"world1", "world1_nether"};
			config.set("WitherSpawnWorlds", def_wpw);
		}
		WitherSpawnWorlds = config.getStringList("WitherSpawnWorlds");
		//
		plugin.saveConfig();
	}

}
