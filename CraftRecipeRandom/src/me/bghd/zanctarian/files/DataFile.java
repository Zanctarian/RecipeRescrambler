package me.bghd.zanctarian.files;

import java.io.File;
import java.io.IOException;
 
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
 
/**
 * <p>Simplest file registration API that I have made</p>
 *
 * @author Zanctarian
 * @version 0.3 BETA
 */
public abstract class DataFile {
 
    static final String SLASH = File.separator;
   
    File f;
    FileConfiguration c;
   
    static Plugin p;
   
    /**
     * <p>This is a required method. Use this in your onEnable.</p>
     *
     * @param plugin
     */
    public static void init(Plugin plugin)
    {
        p = plugin;
    }
   
    public DataFile()
    {
        if(throwError())return;
        init();
        
        if(getConfig().getConfigurationSection("") == null || getFile().length() == 0)
        {
        	defaults();
        	save();
        }
    }
   
    void init(String filePath)
    {
        if(throwError())return;
        filePath = convertSlash(filePath);
        this.f = new File(p.getDataFolder(), filePath+".yml");
        this.c = YamlConfiguration.loadConfiguration(f);
    }
   
    /**
     * <p>The <b>only</b> required class.
     * Call the private init function to use
     * this one most effectively.</p>
     *
     * @author Zanctarian
     */
    public abstract void init();
   
    /**
     * <p>This is an optional method. If you
     * would like base values in your file,
     * simply put the sets in here</p>
     * <p></p>
     * <p><b><i>Saving is not required.</b></i></p>
     *
     * @author Zanctarian
     **/
    public void defaults() {}
   
    public File getFile()
    {
        if(throwError())return null;
        return f;
    }
   
    public FileConfiguration getConfig()
    {
        if(throwError())return null;
        return c;
    }
   
    public void save()
    {
        if(throwError())return;
        try {
            c.save(f);
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
   
    boolean throwError()
    {
        if(p == null)
        {
            Bukkit.getLogger().severe("You forgot to initialize the static method for the DataFile API before using it!");
            return true;
        }
        return false;
    }
   
    String convertSlash(String target)
    {
        target = target.replace("/", SLASH)
                .replace("\\", SLASH);
        return target;
    }
}