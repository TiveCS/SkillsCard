package team.creativecode.skillscard.manager.ability;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;
import team.creativecode.skillscard.util.ParticleManager;

public class ParticleAbility extends Ability {

	public ParticleAbility() {
		super("particle");
		
		addModifier("target", "@Self");
		addModifier("x", "@Self-Z");
		addModifier("y", "@Self-Y");
		addModifier("z", "@Self-X");
		addModifier("particle", "FLAME");
		addModifier("radius", 1.5);
		addModifier("shape", "circle");
		addModifier("animation", true);
	}

	@Override
	public void execute() {
		try {
			double radius = Double.parseDouble(getModifierData("radius").toString());
			String shape = getModifierData("shape").toString().toLowerCase();
			LivingEntity entity = (LivingEntity) getModifierData("target");
			Particle particle = Particle.valueOf(getModifierData("particle").toString().toUpperCase());
			boolean animation = Boolean.parseBoolean(getModifierData("animation").toString());
			
			switch(shape) {
			case "circle":
				ParticleManager.circle(entity.getLocation(), radius, particle, animation);
			case "atomic":
				ParticleManager.atomic(entity.getLocation(), radius, particle, animation);
			}
		}catch(Exception e) {}
	}

}
