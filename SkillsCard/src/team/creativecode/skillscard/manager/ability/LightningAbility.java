package team.creativecode.skillscard.manager.ability;

import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;

public class LightningAbility extends Ability{

	public LightningAbility() {
		super("lightning");
		
		addModifier("target", "@Victim", null);
		addModifier("x", 0, null);
		addModifier("y", 0, null);
		addModifier("z", 0, null);
		
	}

	@Override
	public void execute() {
		
		try {
			LivingEntity entity = (LivingEntity) this.getModifierData("target");
			entity.getWorld().strikeLightning(entity.getLocation());
		}catch(Exception e) {}
	}

}
