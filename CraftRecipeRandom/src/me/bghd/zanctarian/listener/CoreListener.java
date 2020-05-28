package me.bghd.zanctarian.listener;

import java.util.Map;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;

import me.bghd.zanctarian.Core;

/**
 * 
 * <p><b>Self-explanatory listener class. Don't worry about it.</b></p>
 * 
 * @author Zanctarian
 *
 */
public class CoreListener implements Listener {
	
	Core init;
	
	public CoreListener(Core init)
	{
		this.init = init;
	}
	
	//despite how it looks, LOWEST is the most prioritize priority.
	@EventHandler(priority = EventPriority.LOWEST)
	public void onCraft(PrepareItemCraftEvent e)
	{
		if(!init.getSettingsFile().getConfig().getBoolean("Enabled"))
			return;
		Map<ItemStack, ItemStack> query = init.getWrapper().get();
		ItemStack queue = e.getInventory().getResult();
		if(query.containsKey(queue) && init.getBlacklistFile().getConfig().getStringList("blacklisted").indexOf(queue.getType().toString()) == -1)
			e.getInventory().setResult(query.get(queue));
	}

}
