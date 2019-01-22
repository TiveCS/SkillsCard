package team.creativecode.skillscard.manager;

import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;

import team.creativecode.skillscard.Main;

public class Placeholder {
	
	Main plugin = Main.getPlugin(Main.class);
	
	//Values
	private HashMap<String, String> replacer = new HashMap<String, String>(); 
	// Key
	private PlayerData pd;
	private SkillCard sc;
	private Ability ab;
	
	public Placeholder() {
		loadDefaultData();
	}
	
	public Placeholder(Object key) {
		loadDefaultData();
		if (key instanceof PlayerData) {
			pd = (PlayerData) key;
			loadPlayerData();
		}else if (key instanceof SkillCard) {
			sc = (SkillCard) key;
			loadSkillCardData();
		}else if (key instanceof Ability) {
			ab = (Ability) key;
			loadAbilityData();
		}
	}
	
	public String use(String text) {
		for (String obj : replacer.keySet()) {
			text = text.replace("%" + obj + "%", replacer.get(obj));
		}
		return text;
	}
	
	public List<String> useAsList(List<String> list){
		for (int i = 0; i < list.size(); i++) {
			list.set(i, use(list.get(i)));
		}
		return list;
	}
	
	public void inputData(String path, String obj) {
		replacer.put(path, obj);
	}
	
	private void loadDefaultData() {
		replacer.put("prefix", ChatColor.translateAlternateColorCodes('&',plugin.getConfig().getString("prefix")));
		replacer.put("online-player", plugin.getServer().getOnlinePlayers().size() + "");
		replacer.put("max-player", plugin.getServer().getMaxPlayers() + "");
		replacer.put("version", plugin.getDescription().getVersion() + "");
		replacer.put("version-server", Main.getNMSVersion() + "");
	}
	
	private void loadPlayerData() {
		replacer.put("player", pd.getPlayer().getName());
		replacer.put("player-uuid", pd.getPlayer().getUniqueId().toString());
	}

	
	private void loadSkillCardData() {
		replacer.put("card", sc.getSkillname());
		replacer.put("card-cooldown", sc.getCooldown() + "");
		replacer.put("card-chance", sc.getChance() + "");
		replacer.put("card-executetype", sc.getExecuteType().toString());
		replacer.put("card-correctstate", sc.isCorrectData() + "");
	}
	
	private void loadAbilityData() {
		replacer.put("ability", ab.getAbilityName());
		replacer.put("ability-displayname", ab.getDisplayName());
	}
	
}

