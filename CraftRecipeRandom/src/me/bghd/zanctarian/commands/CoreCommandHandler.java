package me.bghd.zanctarian.commands;

import org.bukkit.command.CommandExecutor;

import me.bghd.zanctarian.Core;
import me.bghd.zanctarian.commands.classes.RescrambleCommand;

/**
 * <p><b>Self-explanatory command handler class. Don't worry about it.</b></p>
 * 
 * @author Zanctarian
 *
 */
public class CoreCommandHandler {
	
	Core init;
	
	public CoreCommandHandler(Core init)
	{
		this.init = init;
	}
	
	public void registerCommands()
	{
		registerCommand("rescramble", new RescrambleCommand(init));
	}
	
	void registerCommand(String command, CommandExecutor exe)
	{
		init.getServer().getPluginCommand(command).setExecutor(exe);
	}

}
