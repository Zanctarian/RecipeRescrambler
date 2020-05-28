package me.bghd.zanctarian.files;

/**
 * 
 * <p><b>Settings File Class</b></p>
 * 
 * <p>This class is used for the settings file.</p>
 * 
 * @author Zanctarian
 *
 */
public class SettingsFile extends DataFile {

	@Override
	public void init() {
		init("settings");
	}
	
	@Override
	public void defaults()
	{
		getConfig().set("Enabled", true);
		getConfig().set("ShuffleOnEnable", false);
		
		getConfig().set("messages.NO_PERMISSION", "&cI'm sorry, but you don't have permission to use this command!");
		getConfig().set("messages.SUCCESS", "&aYou have successfully scrambled the crafting recipes!");
	}

}
