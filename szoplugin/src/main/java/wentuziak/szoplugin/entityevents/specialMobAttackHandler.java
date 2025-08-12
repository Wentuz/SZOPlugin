package wentuziak.szoplugin.entityevents;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class specialMobAttackHandler {
	
	public static void handleTaggedMobAttack(LivingEntity damager, Player player) {
				
		player.sendMessage("ATTACKED BY : " + damager);
	}
	
}
