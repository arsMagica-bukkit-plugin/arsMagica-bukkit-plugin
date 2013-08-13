package me.lionbryce.arsMagica.spells;

import me.lionbryce.arsMagica.ArsMagica;
import me.lionbryce.arsMagica.Spell;
import me.lionbryce.arsMagica.ManaManager;

import org.bukkit.entity.Player;

public class AddMana implements Spell{

    public ArsMagica plugin;
    public AddMana(ArsMagica plugin)
    {
        this.plugin = plugin;
    }
    
    public static int price = 0;
    public static int amount = 0;
    
	@Override
	public void onCast(Player caster, Player target, String[] args) {
		if (caster instanceof Player){
			Player sender = (Player) caster;
			target = sender.getServer().getPlayer(args[1]);
			if (args.length == 1){
				amount = Integer.parseInt(args[0]);
				ManaManager.PlayersMana.put(sender, ManaManager.getManaRemaining(sender) + amount);
			}
			else if (args.length == 2){
				if (target.isOnline()){
					ManaManager.PlayersMana.put(target, ManaManager.getManaRemaining(target) + amount);
				} else {
					sender.sendMessage("Sorry that player AINT online");
				}
			} else {
				sender.sendMessage("/AddMana (amount) <target>");
			}
		}
	}
	@Override
	public int getManaCost() {
		return price;
	}
    
    
	
}