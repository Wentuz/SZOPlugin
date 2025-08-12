package wentuziak.szoplugin.entityevents;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

import wentuziak.szoplugin.customitems.Armour;
import wentuziak.szoplugin.customitems.Weapons;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class specialMobAttackHandler {
	
	public static void handleTaggedMobAttack(LivingEntity damager, Player player) {
		
		EntityType type = damager.getType();
				
		switch(type) {
		case EntityType.ZOMBIE:
			Weapons.bleedEffect(player, 2.0, 2);
			break;
		case EntityType.SPIDER:
			LogicHolder.givePotionEffect(player, "SLOW", 20 * 4, 0);
			break;
		case EntityType.HUSK:
			LogicHolder.givePotionEffect(player, "SLOW", 20 * 4, 1);
			LogicHolder.givePotionEffect(player, "WEAKNESS", 20 * 4, 1);
			break;
		case EntityType.PHANTOM:
			Armour.explosiveChestEffect(75, damager, player);
			break;
		}
	}
	
}
