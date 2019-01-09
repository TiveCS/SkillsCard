package team.creativecode.skillscard.events;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import team.creativecode.skillscard.manager.PlayerData;
import team.creativecode.skillscard.manager.SkillCard;

public class AbilityEvent implements Listener {
	
	@EventHandler
	public void onEntityHit(EntityDamageByEntityEvent event) {
		
		HashMap<String, Object> input = new HashMap<String, Object>();
		List<String> query = new ArrayList<String>();
		if (event.getDamager() instanceof Player && event.getEntity() instanceof LivingEntity ) {
			LivingEntity victim = (LivingEntity) event.getEntity(), attacker = (LivingEntity) event.getDamager();
			PlayerData pd = new PlayerData((Player) event.getDamager());
			SkillCard card = pd.getSkillCard(1);
			query = card.getAbilityQuery();
			
			input.put("victim", victim);
			input.put("self", attacker);
			
			pd.executeSkill(query, input);
		}
	}

}
