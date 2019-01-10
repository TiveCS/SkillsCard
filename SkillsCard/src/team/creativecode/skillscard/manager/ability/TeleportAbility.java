package team.creativecode.skillscard.manager.ability;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;
import team.creativecode.skillscard.util.ParticleManager;

public class TeleportAbility extends Ability{

	public TeleportAbility() {
		super("teleport");
		
		addModifier("target", "@Self");
		addModifier("x", "@Self-X");
		addModifier("y", "@Self-Y");
		addModifier("z", "@Self-Z");
		addModifier("particle", true);
	}

	@Override
	public void execute() {
		
		try {
			LivingEntity entity = (LivingEntity) getModifierData("target");
			World world = entity.getWorld();
			double x = Double.parseDouble(getModifierData("x").toString()),
					y = Double.parseDouble(getModifierData("y").toString()),
					z = Double.parseDouble(getModifierData("z").toString());
			Location loc = new Location(world, x,y,z);
			entity.teleport(loc);
			
			if (Boolean.parseBoolean(getModifierData("particle").toString())) {
				ParticleManager.particle(loc, 1.5, Particle.SPELL_WITCH);
			}
		}catch(Exception e) {e.printStackTrace();}
		
	}

}
