package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.Spell;

import org.bukkit.entity.Player;

public class AAA_Default_Spell implements Spell{

    public ArsMagica plugin;
    public AAA_Default_Spell(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 0;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}