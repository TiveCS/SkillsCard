package team.creativecode.skillscard.manager.ability;

import org.bukkit.Effect;
import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;

public class BurnAbility extends Ability {

	public BurnAbility() {
		super("burn");
		
		addModifier("duration", 40);
		addModifier("target", "@Victim");
		addModifier("particle", true);
	}

	@Override
	public void execute() {
		try {
			
			LivingEntity entity = (LivingEntity) getModifierData("target");
			entity.setFireTicks(Integer.parseInt(getModifierData("duration").toString()));
			if (Boolean.parseBoolean(getModifierData("particle").toString())) {
				entity.getWorld().playEffect(entity.getLocation(), Effect.MOBSPAWNER_FLAMES, 5);
			}
		}catch(Exception e) {}
		
	}

}
