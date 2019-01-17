package team.creativecode.skillscard.menu.skillcard;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import team.creativecode.skillscard.menu.MenuManager;
import team.creativecode.skillscard.util.ItemManager;

public class MainMenu extends MenuManager{

	public MainMenu(Player mainviewer, int page) {
		super(mainviewer, page);
		setMenu(Bukkit.createInventory(null, 3*9, "Main Menu"));
	}

	@Override
	public void menuAction(Player clicker, int slot) {
		if (slot == 13) {
			SkillCardPlayerMenu scm = new SkillCardPlayerMenu(clicker, 1);
			scm.openMenu();
		}
	}

	@Override
	public void updateMenu(int page) {
		List<String> lore = new ArrayList<String>();
		for (int i = 0; i < getMenu().getSize();i++) {
			getMenu().setItem(i, ItemManager.generateItem(Material.BLACK_STAINED_GLASS_PANE, " ", new ArrayList<String>()));
		}
		
		getMenu().setItem(10, ItemManager.generateItem(Material.KNOWLEDGE_BOOK, "&e&lGuide", lore));
		getMenu().setItem(13, ItemManager.generateItem(Material.WRITABLE_BOOK, "&d&lSkill Menu", lore));
		getMenu().setItem(16, ItemManager.generateItem(Material.SIGN, "&a&lStatistic", lore));
	}

}
