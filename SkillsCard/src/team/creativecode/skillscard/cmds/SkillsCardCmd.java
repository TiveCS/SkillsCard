package team.creativecode.skillscard.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.manager.SkillCard;
import team.creativecode.skillscard.menu.MenuManager;
import team.creativecode.skillscard.menu.skillcard.SkillCardPlayerMenu;

public class SkillsCardCmd implements CommandExecutor {

    Main plugin = Main.getPlugin(Main.class);

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (command.getName().equalsIgnoreCase("skillscard")){
            if (commandSender instanceof Player){
                Player p = (Player) commandSender;
                if (strings.length == 0){
                	MenuManager mm = new SkillCardPlayerMenu(p, 1);
                	mm.openMenu();
                    return true;
                }
                if (strings.length == 2) {
                	if (strings[0].equalsIgnoreCase("get")) {
	                	if (p.hasPermission("skillscard.admin")) {
	                		SkillCard sc = SkillCard.skillcards.get(strings[0]);
	                		p.getInventory().addItem(sc.getSkillitem());
	                		return true;
	                	}
                	}
                }
            }
        }
        return false;
    }
}
