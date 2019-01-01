package team.creativecode.skillscard.cmds;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import team.creativecode.skillscard.manager.menu.MenuManager;
import team.creativecode.skillscard.manager.menu.PlayerInventory;

public class SkillsCardCmd implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("skillscard")) {
			if (sender instanceof Player) {
				Player p = (Player) sender;
				if (args.length == 0) {
					MenuManager pi = new PlayerInventory(p);
					pi.open();
					return true;
				}
				if (args.length == 1 && (args[0].equalsIgnoreCase("help") || args[0].equals("?"))) {
					return true;
				}
			}
		}
		
		return false;
	}

}
