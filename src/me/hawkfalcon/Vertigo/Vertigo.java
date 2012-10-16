package me.hawkfalcon.Vertigo;


import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class Vertigo extends JavaPlugin implements Listener{

	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}

	public void onDisable() {
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		Player p = (Player) sender;
		if ((p.hasPermission("Vertigo.use")) && (cmd.getName().equalsIgnoreCase("Vertigo"))) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "You have a strange sense of vertigo");
				p.removePotionEffect(PotionEffectType.SLOW);
				p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000000, 15));
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("stop")) {
					p.removePotionEffect(PotionEffectType.SLOW);
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("player") && ((p.hasPermission("Vertigo.others")))) {
	                if (getServer().getPlayer(args[1]) != null) {
						Player target = getServer().getPlayerExact(args[1]);
						target.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000000, 15));
						target.sendMessage(ChatColor.RED + "You have a strange sense of vertigo");
					}
					else {
						sender.sendMessage(ChatColor.RED + "You can only use this command on online players");
					}
	                
				}
				
			}
			
		}

		return true;
	}
}