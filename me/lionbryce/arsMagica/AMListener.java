package me.lionbryce.arsMagica;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class AMListener implements Listener
{
	public ArsMagica plugin;
	public AMListener (ArsMagica plugin)
    {
        this.plugin = plugin;
    }
	
	@EventHandler
	public static void manaGain1 (PlayerMoveEvent event){
		Player player = event.getPlayer();
		int level = ManaManager.PlayersLevel.get(player);
		if (ManaManager.PlayersMaxMana.get(player) * 100 > ManaManager.PlayersMaxMana.get(player)){
			ManaManager.addMana(player, level);
		}
	}
}
