package me.bghd.zanctarian;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import me.bghd.zanctarian.commands.CoreCommandHandler;
import me.bghd.zanctarian.files.BlacklistItemsFile;
import me.bghd.zanctarian.files.DataFile;
import me.bghd.zanctarian.files.SettingsFile;
import me.bghd.zanctarian.functions.RecipeRescrambleWrapper;
import me.bghd.zanctarian.listener.CoreListener;

/**
 * <p>This is a free plugin created by Zanctarian of BGHDDevelopment. You may not 
 * remove this watermark under any circumstances as it'll violate the copyright
 * protection set forth under this project. Thank you.</p>
 * 
 * @Author Zanctarian
 * 
 * @version 1.0.0 RELEASE
 * 
 * @see Copyright © BGHDDevelopment
 */
public class Core extends JavaPlugin {
	
	/*
	 * This was labeled as volatile to protect is from 
	 * asynchronous calls from any 3rd party plugin.
	 * 
	 * Please keep this here or it could risk data corruption. Thank you.
	 */
	protected volatile HashSet<ItemStack> result;
	
	protected RecipeRescrambleWrapper wrapper;
	
	protected DataFile settings;
	protected DataFile blacklist;
	
	public void onEnable()
	{
		Logger logger = getLogger();
		PluginDescriptionFile desc = getDescription();
		try {
			logger.info("Starting up "+desc.getName()+" on version "+desc.getVersion());
			logger.info("Free plugin created by BGHDDevelopment -> Zanctarian");
			
			//init listener
			getServer().getPluginManager().registerEvents(new CoreListener(this), this);
			
			//Init variables
			this.result = getRecipeResults();
			this.wrapper = new RecipeRescrambleWrapper();
			
			//Init commands
			(new CoreCommandHandler(this)).registerCommands();
			
			//Init files
			DataFile.init(this);
			
			this.settings = new SettingsFile();
			this.blacklist = new BlacklistItemsFile();
			
			if(this.settings.getConfig().getBoolean("ShuffleOnEnable"))
				reshuffle();
		} catch(Exception e)
		{
			logger.log(Level.SEVERE, "This plugin has ran in to an issue!", e);
			getServer().getPluginManager().disablePlugin(this);
		} finally 
		{
			logger.info("The plugin has successfully initialized!");
		}
	}
	
	/**
	 * 
	 * <p><b>Reshuffling Method</b></p>
	 * 
	 * <p>This method is used to reshuffle the order of items in the wrapper.</p>
	 * 
	 * @author Zanctarian
	 */
	public void reshuffle()
	{
		wrapper.set(result, rescramble());
	}
	
	//Main recipe rescrambler
	HashSet<ItemStack> rescramble()
	{
		List<ItemStack> toShuffle = new ArrayList<>(result);
		Collections.shuffle(toShuffle);
		return new LinkedHashSet<>(toShuffle);
	}
	
	//Get the iterator of the recipes as a hash set.
	HashSet<ItemStack> getRecipeResults() throws Exception
	{
		HashSet<ItemStack> result = new LinkedHashSet<ItemStack>();
		Iterator<Recipe> recipes = getServer().recipeIterator();
		assert recipes == null : "This plugin isn't able to work on this version of Minecraft";
		recipes.forEachRemaining((recipe) ->
			result.add(recipe.getResult()));
		return result;
	}
	
	public RecipeRescrambleWrapper getWrapper()
	{
		return wrapper;
	}
	
	public DataFile getSettingsFile()
	{
		return settings;
	}
	
	public DataFile getBlacklistFile()
	{
		return blacklist;
	}

}
