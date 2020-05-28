package me.bghd.zanctarian.commands.classes;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.bghd.zanctarian.Core;

/**
 * 
 * <p><b>Rescramble Command Class</b></p>
 * 
 * <p>This class handles the rescramble command.</p>
 * 
 * @author Zanctarian
 *
 */
public class RescrambleCommand implements CommandExecutor {

	Core init;
	
	public RescrambleCommand(Core init)
	{
		this.init = init;
	}
	
	/*
	 * Permission: recipescramble.rescramble
	 */
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(sender instanceof Player)
		{
			Player player = (Player) sender;
			if(!player.hasPermission("recipescramble.rescramble") && !player.isOp())
			{
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', init.getSettingsFile().getConfig().getString("messages.NO_PERMISSION")));
				return true;
			}
		}
		
		init.reshuffle();
		sender.sendMessage(ChatColor.translateAlternateColorCodes('&', ChatColor.translateAlternateColorCodes('&', init.getSettingsFile().getConfig().getString("messages.SUCCESS"))));
		return true;
	}

}
