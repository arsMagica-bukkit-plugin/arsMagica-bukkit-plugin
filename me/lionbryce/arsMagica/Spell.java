package me.lionbryce.arsMagica;

import org.bukkit.entity.Player;

public interface Spell {
	
	public void onCast(Player caster, Player target, String[] args);
	
	public int getManaCost();

}
