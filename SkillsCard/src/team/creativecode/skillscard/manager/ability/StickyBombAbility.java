package team.creativecode.skillscard.manager.ability;

import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

import team.creativecode.skillscard.Main;
import team.creativecode.skillscard.manager.Ability;
import team.creativecode.skillscard.util.ParticleManager;

public class StickyBombAbility extends Ability {

	public StickyBombAbility() {
		super("stickybomb");
		
		addModifier("target", "@Victim");
		addModifier("delay", 100);
		addModifier("damage", 4);
	}

	@Override
	public void execute() {
		
		try {
			
			LivingEntity entity = (LivingEntity) getModifierData("target");
			long delay = Long.parseLong(getModifierData("delay").toString());
			double damage = Double.parseDouble(getModifierData("damage").toString());
			ParticleManager.trail(entity, Particle.SMOKE_LARGE, (int) delay, 2);
			entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_FIRE_EXTINGUISH, 1, 1);
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class),new Runnable() {

				@Override
				public void run() {
					
					ParticleManager.particle(entity.getLocation(), Particle.EXPLOSION_LARGE, 1);
					entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1, 1);
					entity.damage(damage);
					
				}
				
			}, delay);
			
		}catch(Exception e) {}
		
	}
	

}
