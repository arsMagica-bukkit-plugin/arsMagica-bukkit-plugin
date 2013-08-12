package me.lionbryce.arsMagica;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AMCommandExecutor implements CommandExecutor {
	
	private ArsMagica plugin;
	 
	public AMCommandExecutor(ArsMagica plugin) {
		this.plugin = plugin;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String Label, String[] args) {
		Player caster = (Player) sender;
		if(cmd.getLabel().equalsIgnoreCase("pray"))
		{
			Pray pray = new Pray(plugin)
            if(plugin.getManaManager().preCastCheck(caster, pray)){
			    pray.onCast(caster, null, args);
			    plugin.getLogger().info("Testing Prayer Stuff!");
            }
            else{
                caster.sendRawMessage(ChatColor.RED+"Not enough Mana!");
            }
		}
		return false;
	}

}
