package me.lionbryce.arsMagica;

import java.util.logging.Logger;

import net.minecraft.server.v1_5_R3.Block;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Animals;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class arsMagica extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("minecraft");
	public static arsMagica plugin;
	
	public static int args1;
	public static int args2;
	public static int args3;
	public static int args4;
	public static int args5;
	public static String ChatStart = (ChatColor.BLACK + "[" + ChatColor.GOLD + "ArsMagica" + ChatColor.BLACK + "] ");
	
    private ManaManager manaManager = new ManaManager();
    
	@Override
	public void onDisable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " has been Diabled");
	}
	@Override
	public void onEnable()
	{
		PluginDescriptionFile pdfFile = this.getDescription();
		this.logger.info(pdfFile.getName() + " version " + pdfFile.getVersion() + " has been Enabled");
		
	}
	public boolean onCommand (CommandSender sender, Command cmd, String Label, String[] args)
	{
		
		Player target = sender.getServer().getPlayer(args[2]);
		Player player = (Player) sender;
		
		if (sender instanceof Player){
			if (cmd.getLabel().equalsIgnoreCase("AM")){
				if (args.length == 1){
					if (args[0].equalsIgnoreCase("D")){
						sender.sendMessage(ChatStart + "the Diminished spell options are...");
						sender.sendMessage(ChatStart + "all spells /AM all");
						}
					else if (args[0].equalsIgnoreCase("N")){
						sender.sendMessage(ChatStart + "the Normal spell options are...");
						sender.sendMessage(ChatStart + "all spells /AM all");
						}
					else if (args[0].equalsIgnoreCase("A")){
						sender.sendMessage(ChatStart + "the Augmented spell options are...");
						sender.sendMessage(ChatStart + "all spells /AM all");	
					}
					else if (args[0].equalsIgnoreCase("all")){
						sender.sendMessage(ChatStart + "heal: heal yourself /am (power) heal");
						sender.sendMessage(ChatStart + "healother: heal someone else /am (power) healother (target)");
						sender.sendMessage(ChatStart + "addMana: add mana to yourself or others /am b addMana (amount) <target>");
						sender.sendMessage(ChatStart + "checkmana: check your mana, or someone elses mana /am b checkmana <target>");
						sender.sendMessage(ChatStart + "grow: grow a tree or a baby animal /am n grow");
						sender.sendMessage(ChatStart + "levelUp: Level up your mana (gain 100 mana per level) /AM b levelup");
					}
					else if (args[0].equalsIgnoreCase("B")){
						sender.sendMessage(ChatStart + "these are the basic spells....they will cost no mana");
						sender.sendMessage(ChatStart + "/AM B CheckMana (target)");
						sender.sendMessage(ChatStart + "/AM B Addmana <amount> (target)");
						sender.sendMessage(ChatStart + "/AM B levelUp");
						}
					}
				else if (args.length == 2){
					if (args[1].equalsIgnoreCase("heal")){
						if (args[0].equalsIgnoreCase("D")){
							if (ManaManager.preCheck(player, 50)){
								((Player) sender).setHealth(((Player) sender).getHealth() + 3);
							}
						}
						else if (args[0].equalsIgnoreCase("N")){
							if(ManaManager.preCheck(player, 200)){
								((Player) sender).setHealth(((Player) sender).getHealth() + 7);
							}
						}
						else if (args[0].equalsIgnoreCase("A")){
							if(ManaManager.preCheck(player, 1000)){
								((Player) sender).setHealth(20);
							}
						}
					}
					else if (args[0].equalsIgnoreCase("B"))
					{
						if (args[1].equalsIgnoreCase("checkmana")){
							sender.sendMessage(ChatStart + "your current mana is " + ManaManager.getManaRemaining(player));
							sender.sendMessage(ChatStart + "your max mana is " + ManaManager.getPlayerMaxMana(player));
						}
						else if (args[1].equalsIgnoreCase("levelup")){
							ManaManager.Levelup(player);
							ManaManager.manaUpdate(player);
						}
					}
					else if (args[1].equalsIgnoreCase("grow")){
						if (args[0].equalsIgnoreCase("n")){
							if (ManaManager.preCheck(player, 75)){
								if (player.getLineOfSight(null, 100).equals(Block.SAPLING)){
									player.getWorld().generateTree((Location) player.getLineOfSight(null, 100), TreeType.TREE);
								}
								else{
									for (LivingEntity other : player.getWorld().getLivingEntities()){
										if (other instanceof Animals){
											if (!(((Animals) other).isAdult())){
												if (player.hasLineOfSight(other)){
													((Animals) other).setAdult();
												}
											}
										}
									}
								}
							}
						}
					}
				}
				else if (args.length == 3){
					if (args[1].equalsIgnoreCase("healother")){
						if (target.isOnline()){
							if (args[0].equalsIgnoreCase("D")){
								if (ManaManager.preCheck(player, 40)){
									target.setHealth(target.getHealth() + 3);
								}
							}
							else if (args[0].equalsIgnoreCase("N")){
								if (ManaManager.preCheck(player, 160)){
									target.setHealth(target.getHealth() + 7);
								}
							}
							else if (args[0].equalsIgnoreCase("A")){
								if (ManaManager.preCheck(player, 700)){
									target.setHealth(20);
								}
							}
						}
					}
					else if (args[0].equalsIgnoreCase("B")){
						if (args[1].equalsIgnoreCase("checkmana")){
							if (target.isOnline())
							{
								sender.sendMessage(ChatStart + target.getDisplayName() + "'s current mana is " + ManaManager.getManaRemaining(target));
								sender.sendMessage(ChatStart + target.getDisplayName() + "'s max mana is " + ManaManager.getPlayerMaxMana(target));
							}
						}
						else if (args[1].equalsIgnoreCase("addmana")){
							int amount = Integer.parseInt(args[2]);
							ManaManager.addMana(player, amount);
						}
					}
				}
				else if (args.length == 4){
					if (args[0].equalsIgnoreCase("b"))
					{
						if (args[1].equalsIgnoreCase("addMana")){
							if (target.isOnline()){
								int amount = Integer.parseInt(args[3]);
								ManaManager.addMana(target, amount);
							}
						}
					}
				}
				else{
					sender.sendMessage(ChatStart + "this is the main page for the plugin ArsMagica");
					sender.sendMessage(ChatStart + "http://dev.bukkit.org/bukkit-plugins/arsmagica/");
					sender.sendMessage(ChatStart + "to see all the Diminished spell options type /AM D");
					sender.sendMessage(ChatStart + "to see all the Normal spell options type /AM N");
					sender.sendMessage(ChatStart + "to see all the Augmented spell options type /AM A");
					sender.sendMessage(ChatStart + "to see all the basic, you will need these or die, spells type /AM B");
					sender.sendMessage(ChatStart + "to see all the spells /AM All");
				}
			}
		}
		
		return false;
	}
    public ManaManager getManaManager()
    {
        return manaManager;
    }
}
