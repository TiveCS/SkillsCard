package team.creativecode.skillscard.events;

import java.util.HashMap;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import team.creativecode.skillscard.manager.PlayerData;

public class AbilityEvent implements Listener {
	
	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent event) {
		
		HashMap<String, Object> input = new HashMap<String, Object>();
		if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity ) {
			LivingEntity victim = (LivingEntity) event.getEntity(), attacker = (LivingEntity) event.getDamager();
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
				pd.executeSkill((i + 1), input);
			}
		}
	}

}
