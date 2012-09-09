package com.etriacraft.EtriaUtils;

import java.util.logging.Logger;
import org.bukkit.entity.Player;
import com.etriacraft.EtriaUtils.messaging.ChatProv;

public class Utils {

	
	public static final Logger log = Logger.getLogger("Minecraft");
	
	public static String getFormat(Player p, String format) {
        return format.replace("<prefix>", ChatProv.sp.getPlayerPrefix(p)).replace("<name>", p.getName());
    }
    
    public static String colorize(String message) {
        return message.replaceAll("(?i)&([a-fk-or0-9])", "\u00A7$1");
    }
    
    public static String buildString(String[] args, int begin) {
        StringBuilder mess = new StringBuilder();
        for (int i = begin; i < args.length; i++) {
            if (i > begin) {
                mess.append(" ");
            }
            mess.append(args[i]);
        }
        return mess.toString().trim();
    }
    
    public static String prefixName(Player p) {
        String name = ChatProv.sp.getPlayerPrefix(p);
        name += p.getName();
        return colorize(name);
    }

}