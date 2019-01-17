package team.creativecode.skillscard.manager.ability;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.manager.Ability;

public class TimeWarpAbility extends Ability {

	public TimeWarpAbility() {
		
		super("timewarp");

		addModifier("target", "@Victim");
		addModifier("x", "@Self-X");
		addModifier("y", "@Self-Y");
		addModifier("z", "@Self-Z");
		addModifier("delay", 60);
	}

	@Override
	public void execute() {
		
		LivingEntity target = (LivingEntity) getModifierData("target");
		double x = Double.parseDouble(getModifierData("x").toString()),
				y = Double.parseDouble(getModifierData("y").toString()),
				z = Double.parseDouble(getModifierData("z").toString());
		
		Location loc = new Location(target.getWorld(), x,y,z);
		
		Bukkit.getScheduler().runTaskLaterAsynchronously(Main.getPlugin(Main.class), new Runnable() {

			@Override
			public void run() {
				
				target.teleport(loc);
				
			}
			
		}, Long.parseLong(getModifierData("delay").toString()));
		
	}
	
	

}
