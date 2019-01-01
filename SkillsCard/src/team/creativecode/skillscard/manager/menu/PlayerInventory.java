package team.creativecode.skillscard.manager.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import team.creativecode.skillscard.utils.ItemManager;

public class PlayerInventory extends MenuManager {

	public PlayerInventory(Player p) {
		super(p);
		this.menu = Bukkit.createInventory(null, 4*9, ChatColor.translateAlternateColorCodes('&', "Skills Inventory"));
		updateMenu(1);
	}
	
	@Override
	public void updateMenu(int page) {
		List<String> lore = new ArrayList<String>();
		this.getMenu().setItem(1, ItemManager.createItem(Material.IRON_BARS, "&eToggle Slot 1", lore));
		this.getMenu().setItem(3, ItemManager.createItem(Material.IRON_BARS, "&eToggle Slot 2", lore));
		this.getMenu().setItem(5, ItemManager.createItem(Material.IRON_BARS, "&eToggle Slot 3", lore));
		this.getMenu().setItem(7, ItemManager.createItem(Material.IRON_BARS, "&eToggle Slot 4", lore));
		
	}

	@Override
	public void action(Player p, int clickedslot) {
		
	}

}
