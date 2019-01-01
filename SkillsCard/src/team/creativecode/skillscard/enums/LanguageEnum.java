package team.creativecode.skillscard.enums;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;

public enum LanguageEnum {
	
	// HELP
	HELP_GUIDE_ICON("&7Confuse how to use this?||&7click here to get help."),
	HELP_EQUIP_ICON("&7Right click your card while||&7the card is on your main hand.||&7or click your card and done."),
	HELP_UNEQUIP_ICON("&7Click your skill card while||&7open your card inventory."),
	
	PLAYER_EQUIP_CARD("&7You equipped &f{CARD} &7card"),
	PLAYER_UNEQUIP_CARD("&7You unequipped &f{CARD} &7"),
	PLAYER_REPLACE_CARD("&7You replaced card &f{CARD} &7with &a{CARD_FUTURE} &7card");
	
	String message;
	List<String> list = new ArrayList<String>();
	LanguageEnum(String msg){
		this.message = ChatColor.translateAlternateColorCodes('&', msg);
		try {
			String[] st = this.message.split("||");
			for (String s : st) {
				this.list.add(s);
			}
		}catch(Exception e) {
			this.list.add(this.message);
		}
	}
	
	public List<String> getListMessage(){
		return this.list;
	}
	
	public String getMessage() {
		return this.message;
	}
	
}
