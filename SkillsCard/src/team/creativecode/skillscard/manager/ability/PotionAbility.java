package team.creativecode.skillscard.manager.ability;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import team.creativecode.skillscard.manager.Ability;

public class PotionAbility extends Ability{

	public PotionAbility() {
		super("potion");
		addModifier("duration", 60);
		addModifier("type", "SLOW");
		addModifier("amplifier", 1);
		addModifier("target", "@Self");
	}
	
	@Override
	public void execute() {
		
		try {
			LivingEntity target = (LivingEntity) this.getModifierData("target");
			PotionEffect potion = new PotionEffect(
					PotionEffectType.getByName(this.getModifierData("type").toString().toUpperCase()),
					Integer.parseInt(this.getModifierData("duration").toString()),
					Integer.parseInt(this.getModifierData("amplifier").toString()));
			
			target.addPotionEffect(potion);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
