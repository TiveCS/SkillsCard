package team.creativecode.skillscard.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import team.creativecode.skillscard.manager.PlayerData;
import team.creativecode.skillscard.menu.MenuManager;

public class InputEvent implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player p = event.getPlayer();
		PlayerData pd = new PlayerData(p);
		pd.initBaseData();
	}
	
	@EventHandler
	public void onRightClick(PlayerInteractEvent event) {
		Player p = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
			if (!p.getInventory().getItemInMainHand().getType().toString().equals(Material.AIR.name())) {
				PlayerData pd = new PlayerData(p);
				event.setCancelled(pd.equipCard());
			}
		}
	}
	
	@EventHandler
	public void close(InventoryCloseEvent event) {
		MenuManager.menus.remove(event.getPlayer());
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		if (MenuManager.menus.containsKey(event.getWhoClicked())) {
			MenuManager mm = MenuManager.menus.get(event.getWhoClicked());
			if (event.getClickedInventory().equals(mm.getMenu())) {
				event.setCancelled(true);
				mm.menuAction((Player) event.getWhoClicked(), event.getSlot());
			}
		}
	}

}
