package wentuziak.szoplugin.races;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.TaskManager;
import wentuziak.szoplugin.customitems.Weapons;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class MechanicalHandler {
    static BukkitTask mechanicalHungerTask;
    
	public static void mechanicalGotHitEffect(Player player, double currentHealth) {
    	int keyAmount = LogicHolder.countKeysOnPlayer(player, Keys.CUSTOM_MECHANICAL_PARTS);
    	if(keyAmount > 4) keyAmount = 4;
    	
    	if(LogicHolder.critRoll(keyAmount * 5)) {
    		LogicHolder.givePotionEffect(player, "REGENERATION", 20 * keyAmount, keyAmount/2);
    	};
    	
    	if (currentHealth <= 6) {
        	LogicHolder.particleEmitterOnEntity(player, Particle.ELECTRIC_SPARK, 2, 2 * 20, 0.25, 1, 0.25, 0.5);
        	
        	int randInt = (int)(Math.random() * 12);
        	switch (randInt){
        		case 1 -> Weapons.grenadeEffect(player.getLocation());
        		case 2 -> Weapons.smokeEffect(player.getLocation());
        		case 3, 4, 5, 6, 7 -> mechanicalHealing(player);
        		case 8, 9 -> Weapons.fireworkEffect(player.getLocation(), 2);
        		default -> LogicHolder.particleEmitterOnEntity(player, Particle.CAMPFIRE_COSY_SMOKE, 1, 3 * 20, 0.25, 1, 0.25, 0.01);
        	}
    	}
    }
    
    public static void mechanicalHungerEvent(Player player){   
        if (!TaskManager.isTaskRunning(player, "mechanicalHunger")) {            
            final Player finalPlayer = player;
            mechanicalHungerTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (finalPlayer.getFoodLevel() > 6) {
                        stopMechanicalHungerTask(finalPlayer);
                        return;
                    }
                    LogicHolder.givePotionEffect(finalPlayer, "SLOW", 20 * 10, 0);
                    LogicHolder.givePotionEffect(finalPlayer, "WEAKNESS", 20 * 10, 0);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 5);
            TaskManager.addTask(player, "mechanicalHunger", mechanicalHungerTask);
        }
    }

    public static void stopMechanicalHungerTask(Player player) {
        TaskManager.stopTask(player, "mechanicalHunger");
    }
    
    public static void mechanicalAttack(Player player, LivingEntity hitEntity) {
    	int keyAmount = LogicHolder.countKeysOnPlayer(player, Keys.CUSTOM_MECHANICAL_PARTS);
    	if(keyAmount > 4) keyAmount = 4;

    	int randInt = (int)(Math.random() * (10 - keyAmount));
    	switch (randInt){
    		case 1 : 
    			Weapons.bleedEffect(hitEntity, 4.0, 6);
    		default: 
    			Weapons.bleedEffect(hitEntity, 2.0, 4);
    	}
    }
    
    public static void mechanicalHealing(Player player) {
    	LogicHolder.particleEmitterOnEntity(player, Particle.SNOWFLAKE, 1, 10 * 20, 0.1, 0.1, 0.1, 0.01);
    	int randInt = (int)(Math.random() * (10 - LogicHolder.countKeysOnPlayer(player, Keys.CUSTOM_MECHANICAL_PARTS )));
    	Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
        	switch (randInt){
    		case 1 :
    			Weapons.angelSwordEffect(100, player);
    			LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 45, 1);
    		default : 
    			LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 20, 1);
        	}
        }, 20 * 3);
    }
    
    public static void mechanicalSneakEvent(Player player, PersistentDataContainer playerContainer) {
    	
//    	player.sendMessage(playerContainer.getKeys() + "");
//    	switch(playerContainer.getKeys().) {
//    		case :
//    			break;
//    		default:
//    			break;
//    	}
    	//	TODO:
    	//	-- swich case for different keys
    	//	-- handle 
    }
}
