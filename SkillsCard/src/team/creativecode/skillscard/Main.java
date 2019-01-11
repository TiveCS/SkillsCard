package team.creativecode.skillscard;

import java.io.File;
import java.util.Random;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import team.creativecode.skillscard.cmds.SkillsCardCmd;
import team.creativecode.skillscard.events.AbilityEvent;
import team.creativecode.skillscard.events.InputEvent;
import team.creativecode.skillscard.manager.PlayerData;
import team.creativecode.skillscard.manager.SkillCard;
import team.creativecode.skillscard.manager.ability.BurnAbility;
import team.creativecode.skillscard.manager.ability.DamageAbility;
import team.creativecode.skillscard.manager.ability.LightningAbility;
import team.creativecode.skillscard.manager.ability.PotionAbility;
import team.creativecode.skillscard.manager.ability.TeleportAbility;
import team.creativecode.skillscard.util.ConfigManager;

public class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        loadFile();
        loadCmd();
        loadEvent();
        SkillCard.loadSkillCardData();
        loadSkillAbility();
        
       Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {

			@Override
			public void run() {

				try {
					for (String key : InputEvent.cooldown.keySet()) {
						String[] split = key.split(":");
						int cooldown = InputEvent.cooldown.get(key);
						int slot = Integer.parseInt(split[1]);
						UUID uuid = UUID.fromString(split[0]);
						PlayerData p = new PlayerData(Bukkit.getPlayer(uuid));
						p.setCooldown(slot, cooldown - 1);
						if (cooldown <= 0) {
							InputEvent.cooldown.remove(key);
						}
					}
				}catch(Exception e) {}
			}
        	
        }, 0, 20L);
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
    	new DamageAbility().register();
    	new BurnAbility().register();
    	new TeleportAbility().register();
    }
    
    public static boolean chance(double chance) {
    	return chance >= new Random().nextDouble() * 100;
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
    
    public static String getNMSVersion() {
    	String v = Bukkit.getServer().getClass().getPackage().getName();
    	v = v.substring(v.lastIndexOf('.') + 1);
    	return v;
    }

}
