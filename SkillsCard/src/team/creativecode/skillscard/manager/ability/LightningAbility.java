package team.creativecode.skillscard.manager.ability;

import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;

public class LightningAbility extends Ability{

	public LightningAbility() {
		super("lightning");
		
		addModifier("target", "@Victim");
		addModifier("x", 0);
		addModifier("y", 0);
		addModifier("z", 0);
		
	}

	@Override
	public void execute() {
		
		try {
			LivingEntity entity = (LivingEntity) this.getModifierData("target");
			entity.getWorld().strikeLightning(entity.getLocation());
		}catch(Exception e) {}
	}

}
