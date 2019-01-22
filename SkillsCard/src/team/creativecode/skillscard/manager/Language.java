package team.creativecode.skillscard.manager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.util.DataConverter;

public class Language {
	
	
	public static HashMap<String, Language> languages = new HashMap<String, Language>();
	public static Main plugin = Main.getPlugin(Main.class);
	public static File folder = new File(plugin.getDataFolder() + "/Language");
	public static File defFile = new File(plugin.getDataFolder() + "/Language", "en-US.yml");
	
	private HashMap<String, List<String>> msg = new HashMap<String, List<String>>();
	private File file;
	private FileConfiguration config;
	
	public static void loadLanguages() {
		for (File files : folder.listFiles()) {
			languages.put(files.getName(), new Language(files.getName()));
		}
		plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&cSkillsCard&9] &aLoaded &b" + languages.size() + " &alanguage file(s) has been loaded"));
	}
	
	public Language(String filename) {
		file = new File(plugin.getDataFolder() + "/Language", filename);
		if (!file.exists()) {
			try {
				FileUtils.copyFile(defFile, file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		config = YamlConfiguration.loadConfiguration(file);
		loadData();
	}
	
	public void loadData() {
		for (String root : config.getRoot().getKeys(false)) {
			for (String m : config.getConfigurationSection(root).getKeys(false)) {
				List<String> list = new ArrayList<String>();
				if (config.isString(root + "." + m)) {
					String mt  = ChatColor.translateAlternateColorCodes('&', config.getString(root + "." + m));
					list.add(mt);
				}
				else if (config.isList(root + "." + m)) {
					list = config.getStringList(root + "." + m);
				}
				msg.put(root + "." + m, DataConverter.colored(list));
			}
		}
	}
	
	public void sendMessage(Player p, String path) {
		for (String s : getMessages().get(path)) {
			p.sendMessage(s);
		}
	}
	
	public void placeholder(Placeholder plc) {
		
	}
	
	public HashMap<String, List<String>> getMessages(){
		return msg;
	}
	
	public File getFile() {
		return file;
	}
	
	public FileConfiguration getConfig() {
		return config;
	}
	
}
