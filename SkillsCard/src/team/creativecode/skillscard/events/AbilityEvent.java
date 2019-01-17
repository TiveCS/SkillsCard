package team.creativecode.skillscard.events;

import java.util.HashMap;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import team.creativecode.skillscard.manager.PlayerData;
import team.creativecode.skillscard.manager.SkillCard.ExecuteType;

public class AbilityEvent implements Listener {
	
	@EventHandler
	public void onDamaged(EntityDamageByEntityEvent event) {
		if (event.isCancelled() == false) {
			HashMap<String, Object> input = new HashMap<String, Object>();
			if (event.getDamager() instanceof LivingEntity && event.getEntity() instanceof Player) {
				LivingEntity victim = (LivingEntity) event.getEntity(), attacker = (LivingEntity) event.getDamager();
				input.put("damage", event.getDamage());
				input.put("self", victim);
				input.put("self-x", victim.getLocation().getX());
				input.put("self-y", victim.getLocation().getY());
				input.put("self-z", victim.getLocation().getZ());
				input.put("victim", victim);
				input.put("victim-x", victim.getLocation().getX());
				input.put("victim-y", victim.getLocation().getY());
				input.put("victim-z", victim.getLocation().getZ());
				input.put("attacker", attacker);
				input.put("attacker-x", attacker.getLocation().getX());
				input.put("attacker-y", attacker.getLocation().getY());
				input.put("attacker-z", attacker.getLocation().getZ());
				
				PlayerData pd = new PlayerData((Player) victim);
				for (int i = 0; i < 4; i++) {
					if (!pd.hasSkillCard(i + 1)) {
						continue;
					}
					if (pd.getSkillCard(i + 1).getExecuteType().equals(ExecuteType.DAMAGED) || pd.getSkillCard(i + 1).getExecuteType().equals(ExecuteType.ALL)) {
						pd.executeSkill((i + 1), input);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		
		if (event.isCancelled() == false) {
			HashMap<String, Object> input = new HashMap<String, Object>();
			if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity ) {
				LivingEntity victim = (LivingEntity) event.getEntity(), attacker = (LivingEntity) event.getDamager();
				input.put("damage", event.getDamage());
				input.put("victim", victim);
				input.put("victim-x", victim.getLocation().getX());
				input.put("victim-y", victim.getLocation().getY());
				input.put("victim-z", victim.getLocation().getZ());
				input.put("self", attacker);
				input.put("self-x", attacker.getLocation().getX());
				input.put("self-y", attacker.getLocation().getY());
				input.put("self-z", attacker.getLocation().getZ());
				
				PlayerData pd = new PlayerData((Player) event.getDamager());
				for (int i = 0; i < 4; i++) {
					if (!pd.hasSkillCard(i + 1)) {
						continue;
					}
					if (pd.getSkillCard(i + 1).getExecuteType().equals(ExecuteType.HIT) || pd.getSkillCard(i + 1).getExecuteType().equals(ExecuteType.ALL)) {
						pd.executeSkill((i + 1), input);
					}
				}
			}
		}
	}
	
	@EventHandler
	public void onProjectileHit(ProjectileHitEvent event) {
		Projectile proj  = event.getEntity();
		
		if (proj.getShooter() instanceof Player && event.getHitEntity() instanceof LivingEntity) {
			LivingEntity victim = (LivingEntity) event.getHitEntity();
			Player shooter = (Player) proj.getShooter();
			HashMap<String, Object> input = new HashMap<String, Object>();
			input.put("projectile", proj);
			input.put("projectile-x", proj.getLocation().getX());
			input.put("projectile-y", proj.getLocation().getY());
			input.put("projectile-z", proj.getLocation().getZ());
			input.put("victim", victim);
			input.put("victim-x", victim.getLocation().getX());
			input.put("victim-y", victim.getLocation().getY());
			input.put("victim-z", victim.getLocation().getZ());
			input.put("self", shooter);
			input.put("self-x", shooter.getLocation().getX());
			input.put("self-y", shooter.getLocation().getY());
			input.put("self-z", shooter.getLocation().getZ());
			
			PlayerData pd = new PlayerData(shooter);
			for (int i = 0; i < 4; i++) {
				if (!pd.hasSkillCard(i + 1)) {
					continue;
				}
				if (pd.getSkillCard(i + 1).getExecuteType().equals(ExecuteType.PROJECTILE) || pd.getSkillCard(i + 1).getExecuteType().equals(ExecuteType.ALL)) {
					pd.executeSkill((i + 1), input);
				}
			}
		}
		
	}

}
