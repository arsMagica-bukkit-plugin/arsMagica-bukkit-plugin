package me.lionbryce.arsMagica;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ManaManager {
    private final static int init_mana = 100;
    private final static int max_mana = 30000;
    private final static int max_level = 30;

	public static HashMap<Player, Integer> PlayersMana = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> PlayersMaxMana = new HashMap<Player, Integer>();
	public static HashMap<Player, Integer> PlayersLevel = new HashMap<Player, Integer>();
	
	public static void PlayerHasJoined (Player player){
		if (!(PlayersMana.containsKey(player))){
			PlayersMana.put(player, init_mana);
			PlayersMaxMana.put(player, init_mana);
			PlayersLevel.put(player, 1);
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
		if (amount > getMaxMana()){
			amount = getMaxMana();
		}
		PlayersMaxMana.put(player, amount);
	}
	
	public static void setPlayerLevel(Player player, Integer amount){
		if (amount > max_level){
			amount = max_level;
		}
		PlayersLevel.put(player,  amount);
	}
	public static boolean preCheck(Player player, Integer amount){
    	int initialMana = PlayersMana.get(player.getName());
        //Make sure you can afford it
    	if (PlayersLevel.get(player.getName()) > PlayersMaxMana.get(player)){
    		PlayersMana.put(player, PlayersMaxMana.get(player));
    		
            if(initialMana <= amount){
                player.sendMessage(arsMagica.ChatStart + "not enough mana");
                return false;
            }
            //Else for clarity
            else{
               setManaRemaining(player, (initialMana-amount));
                return true;
            }
    	}
    	else{
    		if(initialMana <= amount){
                player.sendMessage(arsMagica.ChatStart + "not enough mana");
                return false;
            }
            //Else for clarity
            else{
               setManaRemaining(player, (initialMana-amount));
                return true;
            }
    	}
	}
	public static boolean Levelup(Player player){
		int initialLevel = PlayersLevel.get(player.getName());
		int XPLevel = player.getExpToLevel();
		
		if (XPLevel >= initialLevel){
			player.giveExpLevels(initialLevel * -1);
			PlayersLevel.put(player, initialLevel + 1);
			player.sendMessage(arsMagica.ChatStart + "level up: " + initialLevel +1);
			
			return true;
		}
		else{
			player.sendMessage(arsMagica.ChatStart + "not enough levels, level: " + initialLevel + " is required");
			
			return false;
		}
	}
	public static void manaUpdate(Player player){
		int Level = PlayersLevel.get(player.getName());
		if (Levelup(player)){
			PlayersMaxMana.put(player, Level * 100);
			PlayersMana.put(player, Level * 100);
			player.sendMessage(arsMagica.ChatStart + "your new max mana is: " + Level * 100);
		}
	}
	
}