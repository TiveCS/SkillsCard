package team.creativecode.skillscard.manager;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.util.ConfigManager;
import team.creativecode.skillscard.util.DataConverter;
import team.creativecode.skillscard.util.ItemManager;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SkillCard {

    public static Main plugin = Main.getPlugin(Main.class);
    public static File[] files = {};
    public static HashMap<String, SkillCard> skillcards = new HashMap<String, SkillCard>();

    private double chance = 100;
    private boolean correctData = false;
    private File skillfile;
    private long cooldown = 0;
    private String skillname;
    private ItemStack skillitem;
    private ExecuteType executetype = ExecuteType.ALL;
    private List<String> abilityquery = new ArrayList<String>();

    public enum ExecuteType{
    	HIT, DAMAGED, PROJECTILE, ALL;
    }
    
    public static void loadSkillCardData(){
    	File folder = new File(plugin.getDataFolder() + "/cards");
    	int maxsize = 0;
    	if (!plugin.getDataFolder().exists()) {
    		plugin.getDataFolder().mkdir();
    	}
    	if (plugin.getDataFolder().exists()) {
	    	if (!folder.exists()) {
	    		folder.mkdir();
	    	}
    	}
    	 if (folder.exists()) {
    		 if (folder.listFiles().length > 0) {
    			 files = new File(plugin.getDataFolder() + "/cards").listFiles();
    		        skillcards.clear();
    		   
    		        for (File file : files){
    		            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
    		            maxsize += config.getRoot().getKeys(false).size();
    		            for (String key : config.getRoot().getKeys(false)){
    		            	try {
    		                	skillcards.put(key, new SkillCard(file, key));
    		            	}catch(Exception e) {}
    		            }
    		        }
    		        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&cSkillsCard&9] &aLoaded &b" + files.length + " &acard files"));
    		        plugin.getServer().getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&9[&cSkillsCard&9] &aLoaded &b" + skillcards.size() + "&7/&c" + maxsize +  " &acards "));
        	 }
    	 }
    	 
    }

    public SkillCard(Player p){
        if (skillcards.size() == 0){
            return;
        }
        if (p.getInventory().getItemInMainHand().getType().equals(Material.AIR)){
            this.skillitem = null;
            this.skillfile = null;
            this.skillname = null;
            this.chance = 0;
            this.correctData = false;
            return;
        }

        for (String skill : skillcards.keySet()){
        	SkillCard sc = skillcards.get(skill);
            if (sc.getSkillitem().isSimilar(p.getInventory().getItemInMainHand())){
                this.skillname = sc.getSkillname();
                this.skillfile = sc.getSkillfile();
                this.skillitem = sc.getSkillitem();
                this.abilityquery = sc.getAbilityQuery();
                this.cooldown = sc.getCooldown();
                this.chance = sc.getChance();
                this.executetype = sc.getExecuteType();
                this.correctData = true;
                break;
            }
        }
    }

	public SkillCard(String skillname){
        this.skillname = skillname;
        if (skillcards.containsKey(skillname)){
            SkillCard sc = skillcards.get(skillname);
            this.skillname = sc.getSkillname();
            this.skillitem = sc.getSkillitem();
            this.skillfile = sc.getSkillfile();
            this.cooldown = sc.getCooldown();
            this.chance = sc.getChance();
            this.abilityquery = sc.getAbilityQuery();
            this.executetype = sc.getExecuteType();
            this.correctData = true;
        }else{
            return;
        }
    }

    public SkillCard(File file, String skillname){
        this.skillname = skillname;
        this.skillfile = file;
        this.skillitem = ItemManager.generateItemFromRaw(this.skillfile, this.skillname + ".item");
        if (ConfigManager.contains(getSkillfile(), getSkillname() + ".chance")) {
        	this.chance = Double.parseDouble(ConfigManager.get(getSkillfile(), getSkillname() + ".chance").toString());
        }
        if (ConfigManager.contains(getSkillfile(), getSkillname() + ".execute-type")) {
        	this.executetype = ExecuteType.valueOf(ConfigManager.get(getSkillfile(), getSkillname() + ".execute-type").toString().toUpperCase());
        }
        try {
        	if (ConfigManager.contains(getSkillfile(), getSkillname() + ".cooldown")) {
            	this.cooldown = Long.parseLong(ConfigManager.get(getSkillfile(), getSkillname() + ".cooldown").toString());
            }
            this.abilityquery = DataConverter.objectToList(ConfigManager.get(this.skillfile, this.skillname + ".ability"));
        }catch(Exception e){}
        this.correctData = true;
    }
    
    public boolean isCorrectData() {
    	return this.correctData;
    }
    
    public double getChance() {
    	return this.chance;
    }

    public ItemStack getSkillitem(){
        return this.skillitem;
    }
    
    public long getCooldown() {
    	return this.cooldown;
    }

    public String getSkillname(){
        return this.skillname;
    }

    public List<String> getAbilityQuery(){
        return this.abilityquery;
    }

    public File getSkillfile(){
        return this.skillfile;
    }

    public ExecuteType getExecuteType() {
		return this.executetype;
	}
}
