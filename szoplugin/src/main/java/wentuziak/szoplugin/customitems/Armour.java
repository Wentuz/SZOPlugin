package wentuziak.szoplugin.customitems;

import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.WindCharge;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class Armour {
    
	
    public static void jetBootsEffect(LivingEntity player, int strength){
    	int x = 4;
    	Vector velocity = player.getVelocity();

        while(x != 0) {
        	Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            	if (LogicHolder.isPlayerAboveGround(player, 0.75)) {
                	double upStr = (velocity.getY() > strength ) ? 0 : 0.1 * strength;
                    velocity.setY(velocity.getY() + upStr);
                    player.setVelocity(velocity);
                    
                    LogicHolder.givePotionEffect(player, "SLOW_FALLING", 2, 0);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
                    LogicHolder.particleEmitterOnEntity(player, Particle.CAMPFIRE_COSY_SMOKE, 2, 5, 0, -0.3, 0, 0.05);
                } 
            }, 2L * x);
        	x--;
        }
        
    }

    public static void golemChestEffect(LivingEntity player){
        LogicHolder.givePotionEffect(player, "SLOW", 20 * 2, 2);
        LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 20, 0);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
        LogicHolder.particleEmitterOnEntity(player, Particle.WAX_ON, 5, 20, 0, -1, 0, 0);
    }

    public static void explosiveChestEffect(int chanceForCrit, LivingEntity damager, LivingEntity player){
        if (LogicHolder.critRoll(chanceForCrit)){

            damager.getWorld().createExplosion(damager.getLocation(), 3, false, false);
            player.getWorld().spawnParticle(Particle.EXPLOSION, player.getLocation(), 3, 0, 0, 0, 0);
        }
    }
    public static void reflectiveChestEffect(int chanceForCrit, int thornLvl , LivingEntity damager){
        if (LogicHolder.critRoll(chanceForCrit)){
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {

                damager.getWorld().spawnParticle(Particle.LAVA, damager.getLocation(), 1, 0, 0, 0, 0); // Adjust particle settings as needed

                damager.damage(thornLvl * 3);
                LogicHolder.givePotionEffect(damager, "SLOW", 20 * 5, 1);
                damager.getLocation().getWorld().spawnParticle(Particle.ENCHANTED_HIT, damager.getLocation(), 10, 0.1, 0.1, 0.1, 0.05);
            }, 10L);
        }
    }

    public static void witchHelmetEffect(Player player){
        if (player.getActivePotionEffects() == null || player.hasCooldown(Material.NETHERITE_HELMET)) {
            return;
        }else{
            Collection<PotionEffect> potionCollection = player.getActivePotionEffects();
            
            LogicHolder.entityPotionEffectTimer(player, 0.75F, 1);
            player.setCooldown(Material.NETHERITE_HELMET, 20 * 20);
            
        }
    }

    public static void ninjaPantEffect(Player player){
        if (player.hasCooldown(Material.NETHERITE_LEGGINGS)) {
            return;
        }
        double currentHealth = player.getHealth();
        double maxHealth = player.getAttribute(Attribute.MAX_HEALTH).getValue();

        if (currentHealth <= maxHealth/2) {
            Weapons.smokeEffect(player.getLocation());
            LogicHolder.givePotionEffect(player, "INVISIBILITY", 20 * 30, 0);
            LogicHolder.givePotionEffect(player, "SPEED", 20 * 30, 0);
            player.setCooldown(Material.NETHERITE_LEGGINGS, 20 * 60);
        }
    }

    public static void gluttonyPantsEffect(Player player){
        LogicHolder.givePotionEffect(player, "SPEED", 20 * 10, 0);    
        player.setFoodLevel(player.getFoodLevel() + 2);
                
        if (player.getFoodLevel() > 20) {
            player.setFoodLevel(20);
        }   
    }

    public static void ramCapEffect(Player attacker ,LivingEntity hitEntity){
        WindCharge windCharge = (WindCharge) hitEntity.getWorld().spawnEntity(hitEntity.getLocation(), EntityType.WIND_CHARGE);
        windCharge.explode();
        hitEntity.getWorld().spawnParticle(Particle.CLOUD, hitEntity.getLocation(), 10, 0, -1, 0, 0.015);
        Weapons.bleedEffect(hitEntity, 2.0, 2);
    }
    public static void ramCapSprint(Player player){
        LogicHolder.givePotionEffect(player, "SPEED", 20, 0);
        player.getWorld().spawnParticle(Particle.CLOUD, player.getLocation(), 1, 0, -1, 0, 0);
    }

    public static void jumpPackEffect(LivingEntity entity, double speedMultiplier){
        if (LogicHolder.isPlayerAboveGround(entity, 0.75) && !LogicHolder.isPlayerAboveGround(entity, 1.5)) {
    
            Vector direction = entity.getLocation().getDirection();
            Vector velocity = direction.multiply(speedMultiplier);
        
            entity.setVelocity(velocity);
            entity.getWorld().playSound(entity.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
            LogicHolder.particleEmitterOnEntity(entity, Particle.VIBRATION, 10, 5, 0, 0.1, 0, 0.02);
            
            if(entity instanceof Player){
                ((HumanEntity) entity).setCooldown(Material.LEATHER_LEGGINGS, 20 * 4);
            }
        }
    }

    public static void strigaVeilEffect(int chanceForCrit, LivingEntity player){
        if (LogicHolder.critRoll(chanceForCrit)) {
            LogicHolder.givePotionEffect(player, "HEAL", 2, 1);
    

            LogicHolder.givePotionEffect(player, "SPEED", 20 * 4, 0);
            LogicHolder.givePotionEffect(player, "STRENGTH", 20 * 4, 0);
            player.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, player.getLocation(), 50, 0.1, 0.1, 0.1, 0.1);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_AMBIENT, 1, 1);
        }
    }


    public static void guardingVestEffect(Player player, EntityDamageEvent event){
        
        if (!player.hasCooldown(Material.GOLDEN_CHESTPLATE)) {
            // Check if the damage would kill the player
            if (player.getHealth() - event.getFinalDamage() <= 0) {
                player.setHealth(2);
                event.setCancelled(true);
    
                LogicHolder.givePotionEffect(player, "REGENERATION", 20*20, 1);
                LogicHolder.givePotionEffect(player, "ABSORPTION", 20*20, 1);
                
                player.getWorld().playSound(player.getLocation(), Sound.ITEM_TOTEM_USE, 1.0f, 1.0f);
                player.getWorld().spawnParticle(Particle.TOTEM_OF_UNDYING, player.getLocation(), 50, 0.1, 0.1, 0.1, 0.05);
                player.setCooldown(Material.GOLDEN_CHESTPLATE, 20 * 60 * 15);
            }else{
                if (LogicHolder.critRoll(33)) {
                    event.setDamage(event.getDamage() / 2);
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, 1.0f, 1.0f);
                    player.getWorld().spawnParticle(Particle.ENCHANT, player.getLocation(), 25, 0.1, 0.1, 0.1, 0.1);
                }
            }
        }
    }

    public static void walkersEffect(Player player){
        if (player.isInWater() || player.isSwimming()) {
            stopWalkersEffect(player);
            return;
        }
        Material blockBelow = player.getLocation().subtract(0, 0.2, 0).getBlock().getType();
        Material blockAtFeet = player.getLocation().getBlock().getType();

        if ((blockBelow == Material.WATER || blockBelow == Material.LAVA) &&
            (blockAtFeet != Material.WATER || blockAtFeet != Material.LAVA)) {
            player.setGravity(false);

        } else {
            stopWalkersEffect(player);
        }
    }
    public static void stopWalkersEffect(Player player){
        player.setGravity(true);
    }
    
    
    public static void goldenSigilBootsOnHit(Player player, double d) {
    	if(LogicHolder.critRoll((int) (50 - d))) {
    		int lvl = 0;
    		
    		if(d < 5) lvl = 2;
    		else if(d < 10) lvl = 1;
    		
    		LogicHolder.givePotionEffect(player, "ABSORPTION", (lvl + 1) * 3 * 20, lvl);
    	}
    }
    
    
    public static void fastingBeltEffect(Player player, double foodLvl) {
    		int lvl = (int) (20 - foodLvl) / 5;
    		
    		if (lvl < 0) lvl = 0;
    		if(foodLvl >= 15) LogicHolder.givePotionEffect(player, "RESISTANCE", (lvl + 1) * 45 * 20, 0);
    		else {
        		LogicHolder.entityPotionEffectTimer(player, (float) ((lvl/2) + 1), 1);
    		}
    }
}
