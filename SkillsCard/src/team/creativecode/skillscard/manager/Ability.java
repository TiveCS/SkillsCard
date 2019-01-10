package team.creativecode.skillscard.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.util.DataConverter;

public abstract class Ability {

	public static HashMap<String, Ability> abilities = new HashMap<String, Ability>();
	Main plugin = Main.getPlugin(Main.class);
	
	String displayname;
	String ability;
	
	//Will be as default modifier and listed modifier key
	HashMap<String, Object> modifier = new HashMap<String, Object>();
	HashMap<String, Object> usedmodifier = new HashMap<String, Object>();
	HashMap<String, String> modAliases = new HashMap<String, String>();
	public Ability(String name) {
		this.ability = name.toUpperCase();
		this.displayname = name.substring(0, 1).toUpperCase() + name.substring(1);
	}
	
	// Example
	// lightning{target=@Victim;}
	public void loadModifier(String queryCode, HashMap<String, Object> modData) {
		usedmodifier = new HashMap<String, Object>();
		for (String l : this.modifier.keySet()) {
			usedmodifier.put(l, this.modifier.get(l));
		}
		queryCode = queryCode.substring(this.getDisplayName().length() + 1);
		queryCode = queryCode.substring(0, queryCode.length() - 1);
		
		HashMap<String, Object> temp = new HashMap<String, Object>();
		for (String mod : this.usedmodifier.keySet()) {
			temp.put(mod, this.usedmodifier.get(mod));
		}
		
		for (String split : queryCode.split(";")) {
			String[] sp = split.split("=");
			temp.put(sp[0], sp[1]);
		}
		
		for (String s : temp.keySet()) {
			String variable = temp.get(s).toString().toLowerCase();
			if (variable.startsWith("@")) {
				variable = variable.substring(1);
				temp.put(s, modData.get(variable));
			}
			
			this.usedmodifier.put(s, DataConverter.matchConvert(temp.get(s)));
		}
	}
	
	public void addModifier(String key, Object defaultValue) {
		modifier.put(key, defaultValue);
	}
	
	public void register() {
		abilities.put(this.ability, this);
	}
	
	public String getDisplayName() {
		return this.displayname;
	}
	
	public String getAbilityName() {
		return this.ability;
	}
	
	public HashMap<String, Object> getDefaultModifier(){
		return this.modifier;
	}
	
	public Object getDefaultModifierData(String key) {
		return this.modifier.get(key);
	}
	
	public HashMap<String, Object> getModifier(){
		return this.usedmodifier;
	}
	
	public Object getModifierData(String key) {
		return this.usedmodifier.get(key);
	}
	
	public List<String> getModifierAliases(String key){
		List<String> list = new ArrayList<String>();
		for (String s : this.modAliases.keySet()) {
			if (this.modAliases.get(s).equalsIgnoreCase(key)) {
				list.add(s);
			}
		}
		return list;
	}
	
	public HashMap<String, String> getModifierAliases(){
		return this.modAliases;
	}
	
	public abstract void execute();
	
}
