package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.Spell;

import org.bukkit.entity.Player;

public class Pray implements Spell{

    public ArsMagica plugin;
    public Pray(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 0;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
			if (args.length == 2){
				if (target.isOnline()){
					if (args[1].equalsIgnoreCase("A")){
						target.setHealth(20.0);
						price = 900;
					}
					else if (args[1].equalsIgnoreCase("N")){
						target.setHealth(target.getHealth() + 7.0);
						price = 360;
					} else {
						target.setHealth(target.getHealth() + 3.0);
						price = 90;
					}
				}
			} else {
				if (args[1].equalsIgnoreCase("A")){
					sender.setHealth(20.0);
					price = 1000;
				}
				else if (args[1].equalsIgnoreCase("N")){
					sender.setHealth(sender.getHealth() + 7.0);
					price = 400;
				} else {
					sender.setHealth(sender.getHealth() + 3.0);
					price = 100;
				}
			}
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}