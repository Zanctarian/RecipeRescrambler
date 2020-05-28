package me.bghd.zanctarian.files;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

/**
 * 
 * <p><b>Blacklist File Class</b></p>
 * 
 * <p>This class is used for the blacklist file. Any item in this file will not be scrambled.</p>
 * 
 * @author Zanctarian
 *
 */
public class BlacklistItemsFile extends DataFile {

	@Override
	public void init() {
		init("blacklist");
	}
	
	@Override
	public void defaults()
	{
		//defaults blacklists crafting table and wood logs. 
		List<String> blacklisted = new ArrayList<>();
		blacklisted.add(Material.WORKBENCH.toString());
		blacklisted.add(Material.LOG.toString());
		blacklisted.add(Material.LOG_2.toString());
		getConfig().set("blacklisted", blacklisted);
	}

}
