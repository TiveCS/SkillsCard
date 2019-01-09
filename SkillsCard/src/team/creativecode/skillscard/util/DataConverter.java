package team.creativecode.skillscard.util;

import org.bukkit.ChatColor;

import java.util.ArrayList;
import java.util.List;

public class DataConverter {

    public static List<String> colored(List<String> list){
        for (int i = 0; i < list.size(); i++){
            list.set(i, ChatColor.translateAlternateColorCodes('&', list.get(i)));
        }
        return list;
    }

    public static List<String> objectToList(Object object){
        List<String> list = new ArrayList<String>();
        String s = object.toString();
        s = s.substring(1);
        s = s.substring(0, s.length() - 1);
        String[] split = s.split(", ");

        for (String sp : split){
            list.add(sp);
        }
        return list;
    }
    
    public static Object matchConvert(String str) {
    	try {
    		return Double.parseDouble(str);
    	}catch(Exception e) {}
    	try {
    		return Integer.parseInt(str);
    	}catch(Exception e) {}
    	
    	return str;
    }

}
