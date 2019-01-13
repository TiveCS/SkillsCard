package team.creativecode.skillscard.manager.ability;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.manager.Ability;

public class SoundAbility extends Ability{

	public SoundAbility() {
		super("sound");
		
		addModifier("target", "@Self");
		addModifier("x", "@Self-X");
		addModifier("y", "@Self-Y");
		addModifier("z", "@Self-Z");
		addModifier("sound", "BLOCK_LEVER_CLICK");
		addModifier("pitch", 1);
		addModifier("volume", 1);
	}

	@Override
	public void execute() {
		
		try {
			
			LivingEntity entity = (LivingEntity) getModifierData("target");
			Location loc = entity.getLocation();
			float pitch = Float.parseFloat(getModifierData("pitch").toString()), volume = Float.parseFloat(getModifierData("volume").toString());
			loc.getWorld().playSound(loc, Sound.valueOf(getModifierData("sound").toString().toUpperCase()), pitch, volume);
			
		}catch(Exception e) {}
		
	}

}
