package me.lionbryce.arsMagica;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

public class ArsMagica extends JavaPlugin
{
	public final Logger logger = Logger.getLogger("minecraft");

	public static ArsMagica plugin;

	public static String ChatStart = (ChatColor.BLACK + "[" + ChatColor.GOLD + "ArsMagica" + ChatColor.BLACK + "] ");

    private ManaManager manaManager = new ManaManager(plugin);
    
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

		getCommand("AddMana").setExecutor(new AMCommandExecutor(this));
		getCommand("CheckMana").setExecutor(new AMCommandExecutor(this));
		getCommand("Grow").setExecutor(new AMCommandExecutor(this));
		getCommand("Jump").setExecutor(new AMCommandExecutor(this));
		getCommand("fireball").setExecutor(new AMCommandExecutor(this));
		getCommand("Levelup").setExecutor(new AMCommandExecutor(this));
		getCommand("Pray").setExecutor(new AMCommandExecutor(this));
		
	}
	public boolean onCommand (CommandSender sender, Command cmd, String Label, String[] args)
	{
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
                    sender.sendMessage(ChatStart + "/CheckMana (target)");
                    sender.sendMessage(ChatStart + "/Addmana <amount> (target)");
                    sender.sendMessage(ChatStart + "/levelUp");
                }
                else if (args[0].equalsIgnoreCase("all")){
                    sender.sendMessage(ChatStart + "  - heal: heal yourself or someone else /Pray (power) <target>");
                    sender.sendMessage(ChatStart + "  - addMana: add mana to yourself or others /addMana (amount) <target> ... admin");
                    sender.sendMessage(ChatStart + "  - checkmana: check your mana, or someone else's mana /checkmana <target>");
                    sender.sendMessage(ChatStart + "  - levelUp: Level up your mana (gain 100 mana per level) /levelup");
                    sender.sendMessage(ChatStart + "  - fireball: shoot a fireball /fireball (power)");
                    sender.sendMessage(ChatStart + "  - jump: JUMP! /jump");
                    sender.sendMessage(ChatStart + "  - grow: Grow stuff! /grow");
                }
                else if (args[0].equalsIgnoreCase("admin")){
                    sender.sendMessage(ChatStart + "  - addMana : /addMana (target) <amount>");
                }
                else if (args[0].equalsIgnoreCase("spellhelp")){
                    sender.sendMessage(ChatStart + "/(spell)(power) <spell> (target) (amount)");
                    sender.sendMessage(ChatStart + "/AM doesn't start all spells");
                    sender.sendMessage(ChatStart + "anything in <>'s are a must have");
                    sender.sendMessage(ChatStart + "anything in ()'s are custom to every spell as in you may need them, and you my not");
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
        return false;
        }
	public ManaManager getManaManager(){
        return manaManager;
    }
}
