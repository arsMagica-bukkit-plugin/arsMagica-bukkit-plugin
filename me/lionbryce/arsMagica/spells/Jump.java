package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.Spell;

import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Jump implements Spell{

    public ArsMagica plugin;
    public Jump(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 0;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
			if (args.length==0){
				sender.setVelocity(new Vector(sender.getVelocity().getX(), 3, sender.getVelocity().getZ()));
			} else {
				sender.sendMessage("/jump");
			}
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}