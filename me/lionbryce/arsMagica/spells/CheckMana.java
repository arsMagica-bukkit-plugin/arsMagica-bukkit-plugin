package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.ManaManager;
import me.lionbryce.arsMagica.Spell;

import org.bukkit.entity.Player;

public class CheckMana implements Spell{

    public ArsMagica plugin;
    public CheckMana(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 0;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
			if (args.length == 0){
				sender.sendMessage(ArsMagica.ChatStart + "your current mana is " + ManaManager.getManaRemaining(sender));
                sender.sendMessage(ArsMagica.ChatStart + "your max mana is " + ManaManager.getPlayerMaxMana(sender));
			} else {
				sender.sendMessage(ArsMagica.ChatStart + " " + target.getName() + " current mana is " + ManaManager.getManaRemaining(target));
                sender.sendMessage(ArsMagica.ChatStart + " " + target.getName() + " max mana is " + ManaManager.getPlayerMaxMana(target));
			}
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}