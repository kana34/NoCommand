package kana.NoCommand;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class NoCommandListener implements Listener {
	
	public Player p;
	public List<String> com;
	public String message;
	public String messageGlobal;
	
	public NoCommand plugin;
	
	public NoCommandListener(NoCommand plugin) {		
		this.plugin = plugin;		
	}
	
	@EventHandler
	public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
		
		this.p = event.getPlayer();
		this.com = this.plugin.getConfig().getStringList("commande");
		
		for (int i=0; i<com.size(); i++) {
			if(event.getMessage().toLowerCase().endsWith("/" + com.get(i)) || event.getMessage().toLowerCase().startsWith("/" + com.get(i) + " ")){
				if(!p.hasPermission("nocommand.use") && !p.isOp()) {
					event.setCancelled(true);
					this.message = this.plugin.getConfig().getString("message." + com.get(i));
					this.messageGlobal = this.plugin.getConfig().getString("message.global");
					if(message != null){
						p.sendMessage(ChatColor. GOLD + "[NoCommand] " + ChatColor.DARK_RED + message);
					}
					else{
						p.sendMessage(ChatColor. GOLD + "[NoCommand] " + ChatColor.DARK_RED + messageGlobal);
					}
				}
			}
		}
	}
}
