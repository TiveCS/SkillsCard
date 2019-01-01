package team.creativecode.skillscard.manager.menu;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class MenuManager {

	public static HashMap<Player, MenuManager> viewer = new HashMap<Player, MenuManager>();
	
	Player player;
	Inventory menu;
	int page;
	
	public MenuManager(Player p) {
		this.player = p;
		this.page = 1;
	}
	
	public void setPlayer(Player p) {
		this.player = p;
	}
	
	public void setMenu(Inventory inv) {
		this.menu = inv;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void changePage(int page) {
		this.page = page;
		updateMenu(page);
	}
	
	public void open() {
		viewer.put(this.player, this);
		this.player.openInventory(this.menu);
		updateMenu(this.page);
	}
	
	
	public abstract void updateMenu(int page);
	public abstract void action(Player p, int clickedslot);
	
	public int getPage() {
		return this.page;
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public Inventory getMenu() {
		return this.menu;
	}
}
