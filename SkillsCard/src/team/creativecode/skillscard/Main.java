package team.creativecode.skillscard;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import team.creativecode.skillscard.cmds.SkillsCardCmd;
import team.creativecode.skillscard.events.AbilityEvent;
import team.creativecode.skillscard.events.InputEvent;
import team.creativecode.skillscard.manager.SkillCard;
import team.creativecode.skillscard.manager.ability.LightningAbility;
import team.creativecode.skillscard.manager.ability.PotionAbility;
import team.creativecode.skillscard.util.ConfigManager;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        loadFile();
        loadCmd();
        loadEvent();
        SkillCard.loadSkillCardData();
        loadSkillAbility();
    }

    private void loadEvent() {
    	getServer().getPluginManager().registerEvents(new InputEvent(), this);
    	getServer().getPluginManager().registerEvents(new AbilityEvent(), this);
    }

    private void loadCmd() {
        getCommand("skillscard").setExecutor(new SkillsCardCmd());
    }
    
    private void loadSkillAbility() {
    	new PotionAbility().register();
    	new LightningAbility().register();
    }

    private void loadFile() {
        getConfig().options().copyDefaults(true);
        saveConfig();

//     SkillsCard data
        ConfigManager.createFolder(this.getDataFolder() + "/cards");
        ConfigManager.createFile(new File(this.getDataFolder() + "/cards", "defaultCards.yml"));

//     PlayerData
        ConfigManager.createFolder(this.getDataFolder() + "/PlayerData");
    }

}
