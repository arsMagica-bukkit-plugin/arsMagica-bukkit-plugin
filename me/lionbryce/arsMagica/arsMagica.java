package me.lionbryce.arsMagica;

import java.util.logging.Logger;

import net.minecraft.server.v1_5_R3.Block;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.TreeType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class arsMagica extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("minecraft");

	public static arsMagica plugin;

	public static String ChatStart = (ChatColor.BLACK + "[" + ChatColor.GOLD + "ArsMagica" + ChatColor.BLACK + "] ");

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

        if (cmd.getLabel().equalsIgnoreCase("AM")){
            if (args.length == 1){
                if (args[0].equalsIgnoreCase("B")){

                    sender.sendMessage(ChatStart + "Here are the basics for this plugin");
                    sender.sendMessage(ChatStart + "  - To gain mana you must walk around, and according to your level you will gain more and more");
                    sender.sendMessage(ChatStart + "  - To see all the 'Admin' commands type /AM admin");
                    sender.sendMessage(ChatStart + "  - To see how the spells work do /AM SpellHelp");
                    sender.sendMessage(ChatStart + "  - To Levelup type /levelup");
                    sender.sendMessage(ChatStart + "    to get to level 1 you need 1xp level, level 2 = 2xp levels, level 376 = 376xp levels");

                    sender.sendMessage(ChatStart + "these are the basic spells....they will cost no mana");
                    sender.sendMessage(ChatStart + "/AM CheckMana (target)");
                    sender.sendMessage(ChatStart + "/AM Addmana <amount> (target)");
                    sender.sendMessage(ChatStart + "/AM levelUp");
                }
                else if (args[0].equalsIgnoreCase("all")){
                    sender.sendMessage(ChatStart + "  - heal: heal yourself /am (power) heal");
                    sender.sendMessage(ChatStart + "  - healother: heal someone else /am (power) healother (target)");
                    sender.sendMessage(ChatStart + "  - addMana: add mana to yourself or others /am b addMana (amount) <target> ... admin");
                    sender.sendMessage(ChatStart + "  - checkmana: check your mana, or someone else's mana /am b checkmana <target>");
                    sender.sendMessage(ChatStart + "  - levelUp: Level up your mana (gain 100 mana per level) /AM b levelup");
                    sender.sendMessage(ChatStart + "  - fireball: shoot a fireball /AM (power) fireball");
                }
                else if (args[0].equalsIgnoreCase("admin")){
                    sender.sendMessage(ChatStart + "  - addMana : /addMana (target) <amount>");
                }
                else if (args[0].equalsIgnoreCase("spellhelp")){
                    sender.sendMessage(ChatStart + "/AM (power) <spell> (target) (amount)");
                    sender.sendMessage(ChatStart + "/AM starts all spells");
                    sender.sendMessage(ChatStart + "anything in <>'s are a must have");
                    sender.sendMessage(ChatStart + "anything in ()'s are custom to every spell as in you may need them, and you my not");
                }
                else if (args[0].equalsIgnoreCase("levelup")){
                    ManaManager.preCheck(player, 0);
                    sender.sendMessage(ChatStart + "your current mana is " + ManaManager.getManaRemaining(player));
                    sender.sendMessage(ChatStart + "your max mana is " + ManaManager.getPlayerMaxMana(player));
                }
                else if (args[0].equalsIgnoreCase("levelup")){
                    ManaManager.Levelup(player);
                    ManaManager.manaUpdate(player);
                }
                else if (args[0].equalsIgnoreCase("grow")){
                    if (ManaManager.preCheck(player, 75)){
                        if (player.getLineOfSight(null, 100).equals(Block.SAPLING)){
                            player.getWorld().generateTree((Location) player.getLineOfSight(null, 100), TreeType.TREE);
                        }
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
                else{
                    sender.sendMessage("you didn't pick a power: /na (D, N, A)");
                }
            }
                else if (args[0].equalsIgnoreCase("checkmana")){
                        if (target.isOnline()){
                            ManaManager.preCheck(target, 0);
                            sender.sendMessage(ChatStart + target.getDisplayName() + "'s current mana is " + ManaManager.getManaRemaining(target));
                            sender.sendMessage(ChatStart + target.getDisplayName() + "'s max mana is " + ManaManager.getPlayerMaxMana(target));
                        }
                    }
                else if (args[0].equalsIgnoreCase("addmana")){
                        int amount = Integer.parseInt(args[2]);
                        ManaManager.addMana(player, amount);
                }

                else if (args[0].equalsIgnoreCase("fireball")){
                    if (args[1].equalsIgnoreCase("d")){
                        if(ManaManager.preCheck(player, 100)){
                            ((Player) sender).launchProjectile(SmallFireball.class);
                        }
                    }
                    if (args[1].equalsIgnoreCase("n")){
                        if(ManaManager.preCheck(player, 500)){
                            ((Player) sender).launchProjectile(SmallFireball.class);
                            ((Player) sender).launchProjectile(Fireball.class);
                        }
                    }
                    if (args[1].equalsIgnoreCase("a")){
                        if(ManaManager.preCheck(player, 2500)){
                            ((Player) sender).launchProjectile(Fireball.class);
                            ((Player) sender).launchProjectile(Fireball.class);
                            ((Player) sender).launchProjectile(Fireball.class);
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
                        else{
                            sender.sendMessage("you didn't pick a power");
                        }
                    }
                }
                else if (args[0].equalsIgnoreCase("B")){
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
                sender.sendMessage(ChatStart + "to see all the basics type /AM B");
                sender.sendMessage(ChatStart + "to see all the spells /AM All");
                sender.sendMessage(ChatStart + "to see all the admin spells /AM admin");
            }
        }

        return false;
	}
}
