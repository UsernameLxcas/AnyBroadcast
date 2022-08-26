package AnyPack;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class Inicio extends JavaPlugin {
	String carpeta;
	Perms pm = new Perms();
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&eAnyBroadcast se esta activando..."));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aAnyBroadcast se ha activado."));
		Configuracion();
        getCommand("broadcast").setExecutor(this);
        getCommand("bcreload").setExecutor(this);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cAnyBroadcast se esta desactivando..."));
		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&4AnyBroadcast se ha desactivado."));
	}
	
	public void Configuracion() {
		File config = new File(this.getDataFolder(),"config.yml");
		carpeta = config.getPath();
		if(!config.exists()){
    	this.getConfig().options().copyDefaults(true);
    	saveDefaultConfig();
		}
		
		
		
	}
	
	@Override
	public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String args[]) {
		if (command.getName().equalsIgnoreCase("broadcast")) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (!player.hasPermission(pm.broad_perm)) {
				String no_perms = "no_perms_message";
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(no_perms)));
			}else {
				if(args.length > 0) {
					String broadcast = "";
			        for (int i = 0; i < args.length; i++) {
			            broadcast += args[i] + " ";
			        }
			        broadcast = broadcast.trim();
			        String broadcast_tag = "broadcast_tag";
			        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(broadcast_tag)+broadcast));
					}else {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSintaxis correcta:"));
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/broadcast <mensaje>"));
					}
				}
			} else {
				if(args.length > 0) {
					String broadcast = "";
			        for (int i = 0; i < args.length; i++) {
			            broadcast += args[i] + " ";
			        }
			        broadcast = broadcast.trim();
			        String broadcast_tag = "broadcast_tag";
			        Bukkit.broadcastMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(broadcast_tag)+broadcast));
					}else {
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSintaxis correcta:"));
						Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&a/broadcast <mensaje>"));
					}
			}
		}else if (command.getName().equalsIgnoreCase("bcreload")) {
			this.reloadConfig();
			String reload_message = "reload_message";
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', this.getConfig().getString(reload_message)));
		}
		
		return true;
	
	
	}
}
