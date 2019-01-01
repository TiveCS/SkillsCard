package team.creativecode.skillscard.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;

public class MsgManager {

	public static TranslatableComponent translate(String text) {
		TranslatableComponent com = new TranslatableComponent(ChatColor.translateAlternateColorCodes('&', text));
		return com;
	}
	
	public static TextComponent component(String text) {
		TextComponent component = new TextComponent(ChatColor.translateAlternateColorCodes('&', text));
		return component;
	}
	
	public static void sendTranslateMessage(Player p, String text) {
		p.spigot().sendMessage(translate(text));
	}
	
	public static List<String> splitStickToList(String st){
		List<String> list = new ArrayList<String>();
		String[] sp = st.split("||");
		for (String s : sp) {
			list.add(ChatColor.translateAlternateColorCodes('&', s));
		}
		return list;
	}
	
	public static void insertList(List<String> list, List<String> insertedList) {
		for (String s : insertedList) {
			list.add(ChatColor.translateAlternateColorCodes('&', "&f" + s));
		}
	}
	
	public static void insertList(List<String> list, List<String> insertedList, String prefix) {
		for (String s : insertedList) {
			list.add(ChatColor.translateAlternateColorCodes('&', "&f" + prefix + s));
		}
	}
	
	public static List<String> convertObjectToList(Object obj){
		List<String> list = new ArrayList<String>();
		try {
			String s = obj.toString();
			s = s.substring(1, s.length() - 1);
			for (String sd : s.split(", ")) {
				list.add(sd);
			}
			return list;
		}catch(Exception e) {return list;}
	}
	
}
