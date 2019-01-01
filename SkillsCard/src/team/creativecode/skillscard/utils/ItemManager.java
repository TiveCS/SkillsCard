package team.creativecode.skillscard.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {
	
	public static ItemStack convertToFormatted(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		if (meta.hasDisplayName()) {
			meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', meta.getDisplayName()));
		}
		
		if (meta.hasLore()) {
			List<String> lore = new ArrayList<String>(meta.getLore());
			for (int i = 0; i < lore.size(); i++) {
				lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
			}
			meta.setLore(lore);
		}
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack setLore(ItemStack item, List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createItem(ItemStack item, ItemMeta meta) {
		item.setItemMeta(meta);
		return item;
	}
	
	public static ItemStack createItem(ItemStack item, String displayname, List<String> lore) {
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack createItem(Material mat, String displayname, List<String> lore) {
		ItemStack item = new ItemStack(mat);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', displayname));
		for (int i = 0; i < lore.size(); i++) {
			lore.set(i, ChatColor.translateAlternateColorCodes('&', lore.get(i)));
		}
		meta.setLore(lore);
		item.setItemMeta(meta);
		
		return item;
	}

}
