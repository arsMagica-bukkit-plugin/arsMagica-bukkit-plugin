package me.lionbryce.arsMagica;

import me.lionbryce.arsMagica.spells.Jump;
import me.lionbryce.arsMagica.spells.Pray;

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
			Pray x = new Pray(plugin);
            if(plugin.getManaManager().preCheck(caster, x)){
			    x.onCast(caster, null, args);
			    plugin.getLogger().info("Testing Prayer Stuff!");
            }
            else{
                caster.sendRawMessage(ChatColor.RED+"Not enough Mana!");
            }
		}
		else if(cmd.getLabel().equalsIgnoreCase("jump"))
		{
			Jump x = new Jump(plugin);
            if(plugin.getManaManager().preCheck(caster, x)){
			    x.onCast(caster, null, args);
			    plugin.getLogger().info("Testing Jump Stuff!");
            }
            else{
                caster.sendRawMessage(ChatColor.RED+"Not enough Mana!");
            }
		}
		return false;
	}

}
