package com.etriacraft.EtriaUtils.messaging;

import net.milkbowl.vault.chat.Chat;
import org.bukkit.plugin.RegisteredServiceProvider;
import com.etriacraft.EtriaUtils.EtriaUtils;

public class ChatProv {
	
	public static Chat sp;
	
	EtriaUtils plugin;
	
	public ChatProv(EtriaUtils instance) {
		this.plugin = instance;
		plugin.getServer().getPluginManager().registerEvents(new ChatListener(), plugin);
		setupChat();
	}
	
	private boolean setupChat() {
		RegisteredServiceProvider<Chat> chatRsp = plugin.getServer().getServicesManager().getRegistration(Chat.class);
		sp = chatRsp.getProvider();
		return (sp != null);
	}

}