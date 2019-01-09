package team.creativecode.skillscard.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.util.ConfigManager;

public class PlayerData {
	
	Main plugin = Main.getPlugin(Main.class);
	
	OfflinePlayer player;
	File file;
	FileConfiguration config;
	
	public PlayerData(OfflinePlayer p) {
		this.player = p;
		this.file = new File(plugin.getDataFolder() + "/PlayerData", p.getUniqueId().toString() + ".yml");
		if (file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.config = YamlConfiguration.loadConfiguration(file);
	}
	
	public boolean unequipCard(int slot) {
		if (ConfigManager.contains(getFile(), "skills." + slot)) {
			SkillCard sc = SkillCard.skillcards.get(ConfigManager.get(getFile(), "skills." + slot).toString());
			getPlayer().getPlayer().getInventory().addItem(sc.getSkillitem());
			ConfigManager.input(getFile(), "skills." + slot, null);
			return true;
		}else {
			return false;
		}
	}
	
	public boolean equipCard(int slot) {
		if (this.getPlayer().isOnline()) {
			SkillCard hand = new SkillCard(this.getPlayer().getPlayer());
			if (!hand.isCorrectData()) {
				return false;
			}
			unequipCard(slot);
			getPlayer().getPlayer().getInventory().removeItem(hand.getSkillitem());
			ConfigManager.input(getFile(), "skills." + slot, hand.getSkillname());
		}
		return true;
	}
	
	public boolean equipCard() {
		boolean checked = false;
		for (int i = 0; i < 4; i++) {
			if (!ConfigManager.contains(getFile(), "skills." + (i + 1)) && checked == false) {
				checked = true;
				return equipCard((i + 1));
			}
		}
		
		if (checked == false) {
			return equipCard(1);
		}
		return false;
	}
	
	public void executeSkill(List<String> query, HashMap<String, Object> modifier) {
		
		for (String q : query) {
			Ability ability;
			for (String ab : Ability.abilities.keySet()) {
				if (q.toLowerCase().startsWith(ab.toLowerCase())) {
					ability = Ability.abilities.get(ab);
					ability.loadModifier(q, modifier);
					ability.execute();
					break;
				}
			}
		}
		
	}
	
	public void initBaseData() {
		ConfigManager.createFile(getFile());
		ConfigManager.input(getFile(), "player-name", this.player.getName());
	}
	
	public SkillCard getSkillCard(int slot) {
		if (ConfigManager.contains(getFile(), "skills." + slot)) {
			return SkillCard.skillcards.get(ConfigManager.get(getFile(), "skills." + slot).toString());
		}else {
			return null;
		}
	}
	
	public OfflinePlayer getPlayer() {
		return this.player;
	}
	
	public File getFile() {
		return this.file;
	}
	
	public FileConfiguration getConfig() {
		return this.config;
	}


}