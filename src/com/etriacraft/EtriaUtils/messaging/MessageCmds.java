package com.etriacraft.EtriaUtils.messaging;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import com.etriacraft.EtriaUtils.Config;
import com.etriacraft.EtriaUtils.EtriaUtils;
import com.etriacraft.EtriaUtils.Utils;

public class MessageCmds {

	EtriaUtils plugin;
	
	public MessageCmds(EtriaUtils instance) {
		this.plugin = instance;
		init();
	}
	
	private void init() {
		PluginCommand modchat = plugin.getCommand("modchat");
		PluginCommand list = plugin.getCommand("list");
		CommandExecutor exe;
		
		exe = new CommandExecutor() {
            @Override
            public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
                if (args.length < 1) return false;
                if (!s.hasPermission("eu.chat.modchat")) {
                    s.sendMessage("§cYou don't have permission to do that!");
                    return true;
                }
                
                String format = Config.modchat_format;
                format = format.replace("<message>", Utils.buildString(args, 0)).replace("<name>", s.getName());
                format = Utils.colorize(format);
                
                for(Player player: Bukkit.getOnlinePlayers()) {
                	if ((player.hasPermission("eu.chat.modchat"))) {
                		player.sendMessage(format);
                	}
                }
                return true;
            }
        };
        modchat.setExecutor(exe);
	
	exe = new CommandExecutor() {
        @Override
        public boolean onCommand(CommandSender s, Command c, String l, String[] args) {
            whoCommand(s);
            return true;
        }
    };
    list.setExecutor(exe);
	}
	
    
public static void whoCommand(CommandSender s) {
    s.sendMessage("§aThere are§e " + Bukkit.getOnlinePlayers().length + " §aout of§e " + Bukkit.getMaxPlayers() + " §aplayers online");
    int remainingChars = 52;
    String list = "";
    for (Player p : Bukkit.getOnlinePlayers()) {
        if (remainingChars - p.getName().length() < 0) {
            list += "\n";
            remainingChars = 52;
        }
        list = list + ChatColor.WHITE;
        if (remainingChars != 52) {
            list += "  ";
        }
        
        list += Utils.prefixName(p);
        remainingChars -= (p.getName().length() + 2);
    }
    
    for(String str : list.split("\n")) {
        s.sendMessage(str);
    	}
}
	
}