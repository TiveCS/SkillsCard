package team.creativecode.skillscard.manager.ability;

import org.bukkit.Particle;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;
import team.creativecode.skillscard.util.ParticleManager;

public class HealAbility extends Ability{

	public HealAbility() {
		super("heal");
		
		addModifier("target", "@Self");
		addModifier("amount", 2);
		addModifier("particle", true);
	}

	@Override
	public void execute() {
		try {
			LivingEntity entity = (LivingEntity) getModifierData("target");
			double heal = Double.parseDouble(getModifierData("amount").toString());
			double health = entity.getHealth();
			double maxhealth = entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getDefaultValue();
			double diff = maxhealth - health;
			
			if (heal > diff) {
				heal = diff;
			}
			entity.setHealth(entity.getHealth() + heal);
			if (Boolean.parseBoolean(getModifierData("particle").toString())) {
				ParticleManager.circle(entity.getLocation(), 1.5, Particle.HEART);
			}
		}catch(Exception e) {e.printStackTrace();}
	}

}
