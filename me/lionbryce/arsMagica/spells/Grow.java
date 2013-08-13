package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.Spell;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Grow implements Spell{

    public ArsMagica plugin;
    public Grow(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 75;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
			
			if (sender.getLineOfSight(null, 100).equals(Material.SAPLING)){
            	sender.getWorld().generateTree((Location) sender.getLineOfSight(null, 100), TreeType.TREE);
            }else{
        		for (LivingEntity other : sender.getWorld().getLivingEntities()){
            		if (other instanceof Animals){
                    	if (!(((Animals) other).isAdult())){
                        	if (sender.hasLineOfSight(other)){
                            	((Animals) other).setAdult();
                        	}
                    	}
                	}
            	}
        	}
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}