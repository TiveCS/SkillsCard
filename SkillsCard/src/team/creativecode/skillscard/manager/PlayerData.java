package team.creativecode.skillscard.manager;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.events.InputEvent;
import team.creativecode.skillscard.util.ConfigManager;

public class PlayerData {
	
	Main plugin = Main.getPlugin(Main.class);
	
	HashMap<Integer, Integer> cooldownSlot = new HashMap<Integer, Integer>();
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
		if (ConfigManager.contains(getFile(), "cooldown")) {
			updateCooldownData();
		}
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
	
	public void executeSkill(int slot, HashMap<String, Object> modifier) {
		if (!ConfigManager.contains(getFile(), "skills." + slot)) {
			return;
		}
		if (!ConfigManager.contains(getFile(), "cooldown." + slot)) {
			ConfigManager.init(getFile(), "cooldown." + slot, 0);
			return;
		}
		SkillCard sc = this.getSkillCard(slot);
		if (Main.chance(sc.getChance()) && Integer.parseInt(ConfigManager.get(getFile(), "cooldown." + slot).toString()) <= 0) {
			executeSkill(sc.getAbilityQuery(), modifier);
			ConfigManager.input(getFile(), "cooldown." + slot, sc.getCooldown());
			InputEvent.cooldown.put(getPlayer().getUniqueId().toString() + ":" + slot, (int) sc.getCooldown());
		}
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
	
	public void generateBossBarCooldown() {
		int highest = -1, skillhighest = 0;
		UUID uuid = this.getPlayer().getUniqueId();
		for (int key : this.getCooldowns().keySet()) {
			if (this.getCooldowns().get(key) > 0) {
				if (highest == -1) {
					highest = this.getCooldowns().get(key);
					skillhighest = key;
				}
				if (highest > this.getCooldowns().get(key)) {
					highest = this.getCooldowns().get(key);
					skillhighest = key;
				}
			}
		}
		SkillCard sc = null;
		double min = 0, max = 0;
		double percentage = 0;
		try {
			sc = this.getSkillCard(skillhighest);
			min = highest;
			max = sc.getCooldown();
			percentage = min / max;
		}catch(Exception e) {
			percentage = 0;
		}
		
		BossBar bar = null;
		BarColor[] colors = BarColor.values();
		BarColor color = colors[(new Random().nextInt(colors.length - 1))];
		if (InputEvent.cooldownbar.containsKey(this.getPlayer().getUniqueId().toString()) && skillhighest > 0 && highest > 0) {
			bar = InputEvent.cooldownbar.get(this.getPlayer().getUniqueId().toString());
			if (!bar.getTitle().startsWith(ChatColor.translateAlternateColorCodes('&', "&a" + sc.getSkillname()))) {
				if (bar.getColor().equals(color)) {
					color = colors[(new Random().nextInt(colors.length - 1))];
				}
				bar.setColor(color);
			}
			bar.setTitle(ChatColor.translateAlternateColorCodes('&', "&a" + sc.getSkillname() + " &3cooldown &b" + highest + " &3seconds"));
			bar.setProgress(percentage);
			bar.setVisible(true);
			if (!bar.getPlayers().contains(this.getPlayer().getPlayer())) {
				bar.addPlayer(this.getPlayer().getPlayer());
			}
		}else {
			if (!InputEvent.cooldownbar.containsKey(this.getPlayer().getUniqueId().toString())) {
				bar = Bukkit.createBossBar(ChatColor.translateAlternateColorCodes('&', "&a" + sc.getSkillname() + " &3cooldown &b" + highest + " &3seconds"), color, BarStyle.SOLID);
				bar.setTitle(ChatColor.translateAlternateColorCodes('&', "&a" + sc.getSkillname() + " &3cooldown &b" + highest + " &3seconds"));
				bar.setProgress(percentage);
				bar.setVisible(true);
			}
			if (skillhighest == 0 || highest <= 0) {
				bar = InputEvent.cooldownbar.get(uuid.toString());
				bar.removeAll();
				bar.setVisible(false);
				InputEvent.cooldownbar.remove(this.getPlayer().getUniqueId().toString());
			}else {
				InputEvent.cooldownbar.put(this.getPlayer().getUniqueId().toString(), bar);
			}
		}
	}
	
	public void initBaseData() {
		ConfigManager.createFile(getFile());
		ConfigManager.input(getFile(), "player-name", this.player.getName());
		for (int i = 0; i < 4; i++) {
			ConfigManager.init(getFile(), "cooldown." + (i + 1), 0);
		}
	}
	
	
	public void registerCooldown() {
		for (String path : config.getConfigurationSection("cooldown").getKeys(false)) {
			if (!InputEvent.cooldown.containsKey(getPlayer().getUniqueId().toString() + ":" + path)) {
				if (getCooldowns().get(Integer.parseInt(path)) > 0) {
					InputEvent.cooldown.put(getPlayer().getUniqueId().toString() + ":" + path, getCooldowns().get(Integer.parseInt(path)));
				}
			}
		}	
	}
	
	public void updateCooldownData() {
		for (String path : config.getConfigurationSection("cooldown").getKeys(false)) {
			cooldownSlot.put(Integer.parseInt(path), config.getInt("cooldown." + path));
		}	
	}
	
	public SkillCard getSkillCard(int slot) {
		if (ConfigManager.contains(getFile(), "skills." + slot)) {
			return SkillCard.skillcards.get(ConfigManager.get(getFile(), "skills." + slot).toString());
		}else{
			return null;
		}
	}
	
	public boolean hasSkillCard(int slot) {
		return ConfigManager.contains(getFile(), "skills." + slot);
	}
	
	public void setCooldown(int slot, int set) {
		ConfigManager.input(getFile(), "cooldown." + slot, set);
		if (Integer.parseInt(ConfigManager.get(getFile(), "cooldown." + slot).toString()) <= 0) {
			ConfigManager.input(getFile(), "cooldown." + slot, 0);
			InputEvent.cooldown.put(getPlayer().getUniqueId().toString() + ":" + slot, getCooldowns().get(slot));
		}
		InputEvent.cooldown.put(getPlayer().getUniqueId().toString() + ":" + slot, getCooldowns().get(slot));
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

	public HashMap<Integer, Integer> getCooldowns(){
		return this.cooldownSlot;
	}

}
