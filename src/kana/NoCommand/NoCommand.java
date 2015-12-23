package kana.NoCommand;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class NoCommand extends JavaPlugin implements Listener{
	
	private Logger logger = Logger.getLogger("Minecraft");
	
	public void onEnable(){
    	
    	PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new NoCommandListener(this), this);
        
		this.loadConfig();
		logger.info("[NoCommand] Plugin charger parfaitement!");
    }
	
	public void onDisable(){
            logger.info("[NoCommand] Plugin stopper...");
    }
    
	public void loadConfig(){
		this.getConfig().options().copyDefaults(true);
		this.saveConfig();
    }
	
	public boolean onCommand(CommandSender sender, Command commande, String label, String[] args){		
		Player player = null;
		if(sender instanceof Player) {
			player = (Player) sender;
		}
			
		if(commande.getName().equalsIgnoreCase("nc")){
			if(args.length == 0){
				sender.sendMessage(ChatColor.GOLD + "[NoCommand] " + ChatColor.GREEN + "Tapez /nc help.");
				return true;
			}
			else if(args.length == 1){  
				//--------------
				//---- HELP ----
				//--------------
				if(args[0].equals("help")){
					sender.sendMessage(ChatColor.YELLOW + "----------------- NoPl Help -----------------");
					sender.sendMessage(ChatColor.YELLOW + "/nc help " + ChatColor.GREEN + "Affiche l'aide de NoCommand.");
					sender.sendMessage(ChatColor.YELLOW + "/nc reload " + ChatColor.GREEN + "Recharge la configuration.");
					return true;
				}
				//----------------
				//---- RELOAD ----
				//----------------
				else if(args[0].equals("reload")){
					if(player.hasPermission("nopl.reload") || player.isOp() || player == null){
						reloadConfig();
						player.sendMessage(ChatColor.GOLD + "[NoCommand] " + ChatColor.GREEN + "Configuration rechargé.");
						return true;
					}
					else{
						player.sendMessage(ChatColor.GOLD + "[NoCommand] " + ChatColor.RED + "Vous n'avez pas la permission !");
						return true;
					}
        		}
				//--------------
				//---- NULL ----
				//--------------
				else{
					sender.sendMessage(ChatColor.GOLD + "[NoCommand] " + ChatColor.RED + "Commande introuvable !");
					sender.sendMessage(ChatColor.GOLD + "[NoCommand] " + ChatColor.GREEN + "Tapez /nc help.");
					return false;
				}
			}
			else{
				sender.sendMessage(ChatColor.GOLD + "[NoCommand] " + ChatColor.RED + "Commande introuvable !");
				sender.sendMessage(ChatColor.GOLD + "[NoCommand] " + ChatColor.GREEN + "Tapez /nc help.");
				return false;
			}
		}			
		return false;		
	}
}
