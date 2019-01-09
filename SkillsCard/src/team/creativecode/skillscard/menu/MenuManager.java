package team.creativecode.skillscard.menu;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public abstract class MenuManager {

	public static HashMap<Player, MenuManager> menus = new HashMap<Player, MenuManager>();
	
	Player mainviewer;
	Inventory menu;
	int page = 1;
	
	public MenuManager(Player mainviewer, int page) {
		this.mainviewer = mainviewer;
		this.page = page;
	}
	
	public void setMenu(Inventory inv) {
		this.menu = inv;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public void openMenu() {
		this.mainviewer.openInventory(this.menu);
		updateMenu(this.page);
		menus.put(mainviewer, this);
	}
	
	public Inventory getMenu() {
		return this.menu;
	}
	
	public Player getMainViewer() {
		return this.mainviewer;
	}
	
	public int getPage() {
		return this.page;
	}
	
	public abstract void menuAction(Player clicker, int slot);
	public abstract void updateMenu(int page); 
	
}
