package team.creativecode.skillscard;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import team.creativecode.skillscard.cmds.SkillsCardCmd;
import team.creativecode.skillscard.utils.ConfigManager;

public class Main extends JavaPlugin {
	
	public static File languageFile = null;
	
	public void onEnable() {

		loadDefFile();
		
		getCommand("skillscard").setExecutor(new SkillsCardCmd());
		
	}
	
	public void loadDefFile() {
		
		this.getConfig().options().copyDefaults(true);
		saveConfig();
		
		languageFile = new File(this.getDataFolder() + "/language", "english_lang.yml");
		ConfigManager.createFolder(this.getDataFolder() + "/language");
		ConfigManager.createFile(languageFile);
	}

}
