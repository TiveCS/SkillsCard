package team.creativecode.skillscard.menu.skillcard;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import team.creativecode.skillscard.manager.PlayerData;
import team.creativecode.skillscard.manager.SkillCard;
import team.creativecode.skillscard.menu.MenuManager;
import team.creativecode.skillscard.util.ConfigManager;
import team.creativecode.skillscard.util.ItemManager;

public class SkillCardPlayerMenu extends MenuManager {

	PlayerData pd;
	
	public SkillCardPlayerMenu(Player mainviewer, int page) {
		super(mainviewer, page);
		setMenu(Bukkit.createInventory(null, 3*9, getMainViewer().getName() + "'s Skill Card"));
		this.pd = new PlayerData(mainviewer);
	}

	@Override
	public void menuAction(Player clicker, int i) {
		if (getPage() == 1) {
			if (i == 10 || i == 12 || i == 14 || i == 16) {
				int num = (i / 2) - 4;
				this.pd.unequipCard(num);
			}
		}
		updateMenu(getPage());
	}

	@Override
	public void updateMenu(int page) {
		ItemStack barrier = ItemManager.generateItem(Material.BLACK_STAINED_GLASS_PANE, " ", new ArrayList<String>());
		if (page == 1) {
			for (int i = 0; i < getMenu().getSize(); i++) {
				getMenu().setItem(i, barrier);
				if (i == 10 || i == 12 || i == 14 || i == 16) {
					int num = (((i / 2) - 4) + (page - 1)*4);
					if (ConfigManager.contains(pd.getFile(), "skills." + num)) {
						try {
							SkillCard sc = SkillCard.skillcards.get(ConfigManager.get(pd.getFile(), "skills." + num));
							getMenu().setItem(i, sc.getSkillitem());
						}catch(Exception e) {
							ConfigManager.input(pd.getFile(), "skills." + num, null);
							getMenu().setItem(i, ItemManager.generateItem(Material.RED_STAINED_GLASS_PANE, "&cNo Card #" + num, new ArrayList<String>()));
						}
					}else {
						getMenu().setItem(i, ItemManager.generateItem(Material.RED_STAINED_GLASS_PANE, "&cNo Card #" + num, new ArrayList<String>()));	
					}
				}
			}
		}
	}

}
