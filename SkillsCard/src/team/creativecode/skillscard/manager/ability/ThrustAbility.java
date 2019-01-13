package team.creativecode.skillscard.manager.ability;

import org.bukkit.Location;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;

import team.creativecode.skillscard.manager.Ability;

public class ThrustAbility extends Ability{

	public ThrustAbility() {
		super("thrust");
		
		addModifier("target", "@Victim");
		addModifier("x", 0);
		addModifier("y", 1);
		addModifier("z", 0);
	}

	@Override
	public void execute() {
		
		try {
			LivingEntity entity = (LivingEntity) getModifierData("target");
			double x = Double.parseDouble(getModifierData("x").toString()),y = Double.parseDouble(getModifierData("y").toString()),z = Double.parseDouble(getModifierData("z").toString());
			
			Location loc = entity.getLocation();
			Vector vector = loc.getDirection();
			
			vector.add(new Vector(x,y,z));
			entity.setVelocity(vector);
		}catch(Exception e) {}
		
	}
	
	

}
