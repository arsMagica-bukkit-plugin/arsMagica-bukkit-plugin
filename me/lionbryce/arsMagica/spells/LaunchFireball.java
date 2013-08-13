package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.Spell;

import org.bukkit.entity.Fireball;
import org.bukkit.entity.LargeFireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.SmallFireball;

public class LaunchFireball implements Spell{

    public ArsMagica plugin;
    public LaunchFireball(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 0;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
			
            if (args[0].equalsIgnoreCase("d")){
            	price = 100;
            	((Player) sender).launchProjectile(SmallFireball.class);
            }
            if (args[0].equalsIgnoreCase("n")){
            	price = 500;
            	((Player) sender).launchProjectile(SmallFireball.class);
               	((Player) sender).launchProjectile(Fireball.class);
            }
            if (args[0].equalsIgnoreCase("a")){
            	price = 2500;
               	((Player) sender).launchProjectile(Fireball.class);
               	((Player) sender).launchProjectile(LargeFireball.class);
               	((Player) sender).launchProjectile(Fireball.class);
            }
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}