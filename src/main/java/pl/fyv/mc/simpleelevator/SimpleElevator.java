package pl.fyv.mc.simpleelevator;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class SimpleElevator extends JavaPlugin implements Listener {


    private static final int SIGN_LINE = 3;
    @Override
    public void onEnable() {
        getLogger().info("Simple Elevator: onEnable is called");
        getServer().getPluginManager().registerEvents(this,this);
    }

    @Override
    public  void onDisable() {
        getLogger().info("Simple Elevator: onDisable is called");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(cmd.getName().equalsIgnoreCase("simpleelevator")) {
            sender.sendMessage("(c) fyv 2019");
            return true;
        }

        return false;
    }

    @EventHandler
    public void onPlayerClicks(PlayerInteractEvent e) {
        Player player = e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {


            if(e.getClickedBlock()!=null) {

                Block block = e.getClickedBlock();
                if (block.getType() == Material.ACACIA_WALL_SIGN ||
                    block.getType() == Material.BIRCH_WALL_SIGN ||
                    block.getType() == Material.DARK_OAK_WALL_SIGN ||
                    block.getType() == Material.JUNGLE_WALL_SIGN ||
                    block.getType() == Material.OAK_WALL_SIGN ||
                    block.getType() == Material.SPRUCE_WALL_SIGN ) {


                    Sign sign = (Sign)block.getState();
                    if(sign.getLine(0).equalsIgnoreCase("[Elevator]")) {
                        if (sign.getLine(SIGN_LINE).length() > 0)
                            try {

                                double newy = Double.parseDouble(sign.getLine(SIGN_LINE));
                                Location l = player.getLocation();

                                player.teleport(new Location(player.getWorld(), l.getX(), l.getY() + newy, l.getZ(), l.getYaw(), l.getPitch()));
                            } catch (Exception ex) {
                                player.sendMessage("4th line is not a number! Create new sign with number in 4th line");
                            }
                    }
                }
            }
        }
    }


}
