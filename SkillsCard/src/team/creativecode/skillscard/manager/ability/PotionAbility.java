package team.creativecode.skillscard.manager.ability;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import team.creativecode.skillscard.manager.Ability;

public class PotionAbility extends Ability{

	public PotionAbility() {
		super("potion");
		List<String> aliases = new ArrayList<String>();
		
		addModifier("duration", 60, aliases);
		addModifier("type", PotionEffectType.SLOW, aliases);
		addModifier("amplifier", 1, aliases);
		addModifier("target", "@Self", aliases);
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
