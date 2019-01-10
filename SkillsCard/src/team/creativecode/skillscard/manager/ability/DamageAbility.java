package team.creativecode.skillscard.manager.ability;

import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;

public class DamageAbility extends Ability {

	public DamageAbility() {
		super("damage");
		
		addModifier("target", "@Victim");
		addModifier("amount", 2);
	}

	@Override
	public void execute() {
		try {
			LivingEntity le = (LivingEntity) this.getModifierData("target");
			le.damage(Double.parseDouble(this.getModifierData("amount").toString()));
		}catch(Exception e) {}
	}

}
