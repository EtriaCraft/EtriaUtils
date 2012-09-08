package Listeners;

import java.util.HashSet;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.material.Button;

public class SignListener implements Listener {
	
	public static final byte[] SIGN_DRECTIONS = {5, 3, 4, 2};
    public static final BlockFace[] BLOCK_FACES = {BlockFace.NORTH, BlockFace.EAST, BlockFace.SOUTH, BlockFace.WEST};
    public static final HashSet<Integer> attachableIds = new HashSet();
    
    public static final HashSet<Integer> safeLiftIds = new HashSet();

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent event) {
        if (!event.getAction().equals(Action.LEFT_CLICK_BLOCK) && !event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) return;
        if (!(event.getClickedBlock().getState() instanceof Sign) && !event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) return;
        
        if (event.getPlayer().isSneaking()) return;
        
        Sign sign = null;
        if (event.getClickedBlock().getType().equals(Material.STONE_BUTTON)) {
            Button btn = (Button) event.getClickedBlock().getState().getData();
            Block block = event.getClickedBlock().getRelative(btn.getAttachedFace()); //Block button is on.
            for (BlockFace bf : BlockFace.values()) {
                if ((block.getRelative(bf).getState() instanceof Sign)) {
                    sign = (Sign) block.getRelative(bf).getState();
                    break;
                }
            }
            if (sign == null) return;
        } else {
            sign = (Sign)event.getClickedBlock().getState();
        }
        
        if (!sign.getLine(1).equals("[Elevator]")) return;
        
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            event.setUseItemInHand(Event.Result.DENY);
            event.setUseInteractedBlock(Event.Result.DENY);
            for (int y = (sign.getY() - 1); y >= 0; y--) {
                Block b = sign.getWorld().getBlockAt(sign.getX(), y, sign.getZ());
                if (!(b.getState() instanceof Sign)) continue;
                Sign s = (Sign) b.getState();
                if (s.getLine(1).equalsIgnoreCase("[Elevator]")) {
                    if (!teleport(event.getPlayer(), s.getWorld(), y)) return;

                    if (s.getLine(0).isEmpty()) event.getPlayer().sendMessage("§aGoing down");
                    else event.getPlayer().sendMessage("§aGoing to " + s.getLine(0));
                    return;
                }
            }
            event.getPlayer().sendMessage("§aThere was no elevator found below this");
        } else {
            for (int y = (sign.getY() + 1); y <= 256; y++) {
                Block b = sign.getWorld().getBlockAt(sign.getX(), y, sign.getZ());
                if (!(b.getState() instanceof Sign)) continue;
                Sign s = (Sign)b.getState();
                if (s.getLine(1).equalsIgnoreCase("[Elevator]")) {
                    if (!teleport(event.getPlayer(), s.getWorld(), y)) return;

                    if (s.getLine(0).isEmpty()) event.getPlayer().sendMessage("§aGoing up");
                    else event.getPlayer().sendMessage("§aGoing to " + s.getLine(0));
                    return;
                }
            }
            event.getPlayer().sendMessage("§aThere was no elevator found above this");
        }
    }
    
    private boolean teleport(Player player, World w, int y) {
        y--;
        Location loc = player.getLocation();
        if (!safeLiftIds.contains(w.getBlockAt(loc.getBlockX(), (y), loc.getBlockZ()).getType())) y++;
        //One hell of an if!
        if ((safeLiftIds.contains(w.getBlockAt(loc.getBlockX(), (y - 1), loc.getBlockZ()).getType())
                && safeLiftIds.contains(w.getBlockAt(loc.getBlockX(), (y - 2), loc.getBlockZ()).getType()))
                || (!safeLiftIds.contains(w.getBlockAt(loc.getBlockX(), (y), loc.getBlockZ()).getType())
                || !safeLiftIds.contains(w.getBlockAt(loc.getBlockX(), (y + 1), loc.getBlockZ()).getType()))) {
            player.sendMessage("§aYou can't teleport there. It isn't safe.");
            return false;
        }

        loc.setY(y);
        player.teleport(loc);
        return true;
    }

	@EventHandler
	public void onSignChange(SignChangeEvent event) {
		if (!event.getLine(1).equalsIgnoreCase("[Elevator]")) return;
		
		boolean found = false;
		for (int i = 0; i < 265; i++) {
			Block b = event.getBlock().getWorld().getBlockAt(event.getBlock().getX(),i, event.getBlock().getZ());
			if (!(b.getState() instanceof Sign)) continue;
			Sign s = (Sign)b.getState();
			if (s.getLine(1).equalsIgnoreCase("[Elevator]")) {
				found = true;
				break;
			}
		}
		
		if (found) event.getPlayer().sendMessage("§aElevator created and linked!");
		else event.getPlayer().sendMessage("§eElevator created");
		event.setLine(1, "[Elevator]");
		Sign s = (Sign) event.getBlock().getState();
		s.update();
	}
	
	static {
        attachableIds.add(Material.CHEST.getId());
        attachableIds.add(Material.ENDER_CHEST.getId());
        attachableIds.add(Material.ENCHANTMENT_TABLE.getId());
        attachableIds.add(Material.BREWING_STAND.getId());
        attachableIds.add(Material.FURNACE.getId());
        attachableIds.add(Material.BURNING_FURNACE.getId());
        attachableIds.add(Material.DISPENSER.getId());
        attachableIds.add(Material.WOOD_DOOR.getId());
        attachableIds.add(Material.IRON_DOOR.getId());
        attachableIds.add(Material.TRAP_DOOR.getId());
        
        safeLiftIds.add(Material.AIR.getId());
        safeLiftIds.add(Material.STONE_BUTTON.getId());
        safeLiftIds.add(Material.POWERED_RAIL.getId());
        safeLiftIds.add(Material.DETECTOR_RAIL.getId());
        safeLiftIds.add(Material.RAILS.getId());
        safeLiftIds.add(Material.TORCH.getId());
        safeLiftIds.add(Material.REDSTONE_WIRE.getId());
        safeLiftIds.add(Material.REDSTONE_TORCH_ON.getId());
        safeLiftIds.add(Material.REDSTONE_TORCH_OFF.getId());
        safeLiftIds.add(Material.WOOD_PLATE.getId());
        safeLiftIds.add(Material.STONE_PLATE.getId());
        safeLiftIds.add(Material.WATER_LILY.getId());
        safeLiftIds.add(Material.WATER.getId());
        safeLiftIds.add(Material.STATIONARY_WATER.getId());
        safeLiftIds.add(Material.SNOW.getId());
        safeLiftIds.add(Material.SIGN_POST.getId());
        safeLiftIds.add(Material.WALL_SIGN.getId());
        safeLiftIds.add(Material.LONG_GRASS.getId());
    }
    
}