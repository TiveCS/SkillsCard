package team.creativecode.skillscard.utils;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {

	public static Object get(File file, String path) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		if (config.contains(path)) {
			return config.get(path);
		}
		return null;
	}
	
	public static boolean contains(File file, String path) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		return config.contains(path);
	}
	
	public static void input(File file, String path, Object obj) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		config.set(path, obj);
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void init(File file, String path, Object obj) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		try {
			if (config.get(path).equals(null)) {
				config.set(path, obj);
			}
		}catch(Exception e) {
			config.set(path, obj);
		}
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void saveFile(FileConfiguration config, File file) {
		try {
			config.save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void saveFile(File file) {
		try {
			getConfig(file).save(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static FileConfiguration getConfig(File file) {
		FileConfiguration config = YamlConfiguration.loadConfiguration(file);
		return config;
	}
	
	public static void createFolder(String path) {
		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
	}
	
	public static void createFile(File file) {
		if (!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void createFile(String path, String name) {
		File file = new File(path, name);
		File folder = new File(path);
		if (folder.isDirectory() && !folder.exists()) {
			folder.mkdir();
		}
		
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
