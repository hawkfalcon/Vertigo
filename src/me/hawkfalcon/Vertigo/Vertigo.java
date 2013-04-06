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
		String p = sender.getName();
		Player pl = (Player)sender;
		if ((pl.hasPermission("vertigo.use")) && (cmd.getName().equalsIgnoreCase("vertigo"))) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "You have a strange sense of vertigo");
				removePotions(p);
				addPotions(p);
			}
			if (args.length == 1) {
				if (args[0].equalsIgnoreCase("stop") && ((pl.hasPermission("vertigo.stop"))))  {
					removePotions(p);
					pl.sendMessage(ChatColor.RED + "The vertigo subsides.");
				}
				if (args[0].equalsIgnoreCase("all") && ((pl.hasPermission("vertigo.all")))) {
					for (Player pa : getServer().getOnlinePlayers()) {
						addPotions(pa.getName());
						pa.sendMessage(ChatColor.RED + "You have a strange sense of vertigo");
					}
				}
				if (args[0].equalsIgnoreCase("stopall") && ((pl.hasPermission("vertigo.all")))) {
					for (Player pa : getServer().getOnlinePlayers()) {
						removePotions(pa.getName());
						pa.sendMessage(ChatColor.RED + "The vertigo subsides.");
					}
				}
			}
			if (args.length == 2) {
				if (args[0].equalsIgnoreCase("player") && ((pl.hasPermission("vertigo.others")))) {
					if (getServer().getPlayerExact(args[1]) != null) {
						String target = args[1];
						addPotions(target);
						getServer().getPlayer(target).sendMessage(ChatColor.RED + "You have a strange sense of vertigo");
					}
					else {
						sender.sendMessage(ChatColor.RED + "You can only use this command on online players");
					}

				}
				if (args[0].equalsIgnoreCase("stop") && ((pl.hasPermission("vertigo.others")))) {
					if (getServer().getPlayer(args[1]) != null) {
						String target = args[1];
						removePotions(target);
						getServer().getPlayer(target).sendMessage(ChatColor.RED + "The vertigo subsides.");
					}
				}							
				}


			}

		

		return true;
	}
	public void addPotions(String p){
		getServer().getPlayer(p).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 20000000, 15));
		getServer().getPlayer(p).addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20000000, 15));
		getServer().getPlayer(p).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20000000, 15));
	}
	public void removePotions(String p){
		getServer().getPlayer(p).removePotionEffect(PotionEffectType.SLOW);
		getServer().getPlayer(p).removePotionEffect(PotionEffectType.CONFUSION);
		getServer().getPlayer(p).removePotionEffect(PotionEffectType.BLINDNESS);
	}
}