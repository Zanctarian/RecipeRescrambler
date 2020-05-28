package me.bghd.zanctarian.functions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.bukkit.inventory.ItemStack;

/**
 * 
 * <p><b>Wrapper Class for the Rescrambler<b></p>
 * 
 * <p>This class is used as the data holder for the rescrambled recipes.</p>
 * 
 * @author Zanctarian
 *
 */
public class RecipeRescrambleWrapper {
	
	Map<ItemStack, ItemStack> current = new HashMap<>();
	
	/*
	 * We don't need to worry about this being synch,
	 * the normal list is already volatile.
	 */
	/**
	 * <p><b> Set Method </b></p>
	 * 
	 * <p>Use this with the normal set of items and the scrambled set of items to update the list</p>
	 * 
	 * @param real - Normal list of items
	 * @param fake - Scrambled list of items
	 * 
	 * @author Zanctarian
	 */
	public void set(Set<ItemStack> real, Set<ItemStack> fake)
	{
		int size = real.size() == fake.size() ? real.size() : -1; 
		if(size > 0)
		{
			Iterator<ItemStack> iterator = fake.iterator();
			current = real.stream()
				.filter(s -> iterator.hasNext())
				.collect(Collectors.toMap(k -> k, v -> iterator.next()));
		}
	}
	
	/**
	 * <p><b> Get Method </b></p>
	 * 
	 * <p>Use this method to get the map of normal and scrambled items, used for the listener event.</p>
	 * 
	 * @return the map of <normal items, scrambled items>
	 * 
	 * @author Zanctarian
	 */
	public synchronized Map<ItemStack, ItemStack> get()
	{
		return current;
	}
}
