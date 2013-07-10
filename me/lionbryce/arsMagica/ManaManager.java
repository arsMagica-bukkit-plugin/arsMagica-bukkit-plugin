package me.lionbryce.arsMagica;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ManaManager {
    private final static int init_mana = 10000;
    private final static int max_mana = 300000;

	public static HashMap<Player, Integer> PlayersMana = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> PlayersMaxMana = new HashMap<Player, Integer>();
	
	public static void PlayerHasJoined (Player player){
		if (!(PlayersMana.containsKey(player))){
			PlayersMana.put(player, init_mana);
			PlayersMaxMana.put(player, init_mana);
		}}
	
	public static int getMaxMana(){
		return max_mana;
	}
	
	public static Integer getManaRemaining(Player player){
		return PlayersMana.get(player);
	}
	
	public static Integer getPlayerMaxMana(Player player){
		return PlayersMana.get(player);
	}
	
	public static void setManaRemaining(Player player, Integer amount){
		PlayersMana.put(player, amount);
	}
	
	public static void addMana(Player player, Integer amount){
		setManaRemaining(player, PlayersMana.get(player) + amount);
	}
	
	public static void setMaxMana(Player player, Integer amount){
		if (amount > getMaxMana())
		{
			amount = getMaxMana();
		}
		PlayersMaxMana.put(player, amount);
	}
	
}