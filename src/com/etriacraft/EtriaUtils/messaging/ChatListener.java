package com.etriacraft.EtriaUtils.messaging;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import com.etriacraft.EtriaUtils.Config;
import com.etriacraft.EtriaUtils.Utils;

public class ChatListener implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		String message = e.getMessage();
		
		if (e.getPlayer().hasPermission("eu.chat.color")) {
			message = Utils.colorize(message);
		}
		
		String format = Config.message_format;
		format = format.replace("<message>", "%2$s");
		format = Utils.getFormat(e.getPlayer(), format);
		format = Utils.colorize(format);
		
		e.setFormat(format);
		e.setMessage(message);
	}
	
}
