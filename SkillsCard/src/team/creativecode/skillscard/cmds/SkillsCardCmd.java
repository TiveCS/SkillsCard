package team.creativecode.skillscard.cmds;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.manager.PlayerData;
import team.creativecode.skillscard.manager.SkillCard;
import team.creativecode.skillscard.menu.MenuManager;
import team.creativecode.skillscard.menu.skillcard.MainMenu;
import team.creativecode.skillscard.manager.Language;

public class SkillsCardCmd implements CommandExecutor, TabCompleter {

    Main plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("skillscard")){
            if (commandSender instanceof Player){
                Player p = (Player) commandSender;
                if (strings.length == 0){
                	MenuManager mm = new MainMenu(p, 1);
                	mm.openMenu();
                    return true;
                }
                if (strings.length == 1) {
                	if (strings[0].equalsIgnoreCase("test")) {
                		PlayerData pd = new PlayerData(p);
                		System.out.println(pd.getLanguage().getMessages());
                		return true;
                	}
                	if (p.hasPermission("skillscard.admin")){
                		if (strings[0].equalsIgnoreCase("reload")) {
                			plugin.reloadConfig();
                			SkillCard.loadSkillCardData();
                			Main.loadSkillAbility();
                			Language.loadLanguages();
                			return true;
                		}
                	 }
                }
                if (strings.length == 2) {
                	if (p.hasPermission("skillscard.admin")) {
	                	if (strings[0].equalsIgnoreCase("get")) {
	                		SkillCard sc = SkillCard.skillcards.get(strings[1]);
	                		p.getInventory().addItem(sc.getSkillitem());
	                		return true;
	                	}
                	}
                }
            }
        }
        return false;
    }

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		List<String> list = new ArrayList<String>();
		if (cmd.getName().equalsIgnoreCase("skill")) {
			if (sender instanceof Player) {
				if (args.length == 0) {
					list.add("guide");
					list.add("menu");
					list.add("mainmenu");
					return list;
				}
				if (args.length == 1) {
					if (args[0].equalsIgnoreCase("get") && sender.hasPermission("skillscard.admin")) {
						for (String s : SkillCard.skillcards.keySet()) {
							list.add(s);
						}
					}
					return list;
				}
			}
		}
		return list;
	}
}
