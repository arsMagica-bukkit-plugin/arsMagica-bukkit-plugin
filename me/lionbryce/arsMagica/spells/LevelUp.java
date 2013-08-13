package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.ManaManager;
import me.lionbryce.arsMagica.Spell;

import org.bukkit.entity.Player;

public class LevelUp implements Spell{

    public ArsMagica plugin;
    public LevelUp(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 0;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
			
            ManaManager.Levelup(caster);
            ManaManager.manaUpdate(caster);
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}