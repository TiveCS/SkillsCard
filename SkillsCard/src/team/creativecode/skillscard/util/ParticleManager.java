package team.creativecode.skillscard.util;

import org.bukkit.Location;
import org.bukkit.Particle;

import team.creativecode.skillscard.Main;

public class ParticleManager {
	
	static Main plugin = Main.getPlugin(Main.class);
	static String version = Main.getNMSVersion();
	
	public static void particle(Location loc, double radius, Particle particle) {
		
		for (int degree = 0; degree < 360; degree+=10) {
			double radian = Math.toRadians(degree);
			double zet = radius*Math.sin(radian), ex = radius*Math.cos(radian);
			
			double x = loc.getX()+ex, y = loc.getY(), z = loc.getZ()+zet;
			
			loc.getWorld().spawnParticle(particle, x,y,z , 1, 0,0,0, 0);
		}
		
	}

}
