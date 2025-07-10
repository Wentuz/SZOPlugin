package wentuziak.szoplugin.customitems;


import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.WindCharge;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.races.RaceEffects;


public class Weapons {


    public static void thunderHammerEffect(int chanceForCrit, LivingEntity hitEntity){
        if (LogicHolder.critRoll(chanceForCrit)) {
            LogicHolder.givePotionEffect(hitEntity, "WEAKNESS", 20 * 10, 0);
            LogicHolder.givePotionEffect(hitEntity, "SLOW", 20 * 10, 0);
            LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 20 * 5, 1);
            LogicHolder.givePotionEffect(hitEntity, "CONFUSION", 20 * 5, 1);
            hitEntity.getWorld().strikeLightning(hitEntity.getLocation());
        }
    }

    public static void explosiveSwordEffect(int chanceForCrit, LivingEntity hitEntity){
        if (LogicHolder.critRoll(chanceForCrit)) {
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                hitEntity.getWorld().createExplosion(hitEntity.getLocation(), 1, false, false);
            }, 5L);
        }
    }

    public static void bleedEffect(LivingEntity hitEntity, Double damage, Integer secondsDuration){
        hitEntity.damage(damage);
        hitEntity.playHurtAnimation(1);
        hitEntity.getWorld().playSound(hitEntity.getLocation(), Sound.ENTITY_PLAYER_HURT, 1, 1);

        if (secondsDuration >= 1) {
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                bleedEffect(hitEntity, damage, secondsDuration - 1);
            }, 20L);
        }
        LogicHolder.particleEmitterOnEntity(hitEntity, Particle.FALLING_LAVA, 20, 10, 0.5, 0.5, 0.5, 0.01);
    }

    public static void daemonSwordEffect(int chanceForCrit, LivingEntity hitEntity){
        if (LogicHolder.critRoll(chanceForCrit)) {
            hitEntity.getWorld().spawnParticle(Particle.PORTAL, hitEntity.getLocation(), 40);
            LogicHolder.givePotionEffect(hitEntity, "WITHER", 20 * 5, 0);
            LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 20 * 2, 1);

            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                LogicHolder.givePotionEffect(hitEntity, "HARM", 1, 1);
            }, 10L);
        }
    }

    public static void angelSwordEffect(int chanceForCrit, LivingEntity player){
        if (LogicHolder.critRoll(chanceForCrit)) {
            LogicHolder.givePotionEffect(player, "HEAL", 2, 0);
            
            LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 5, 0);
            player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 1);
        }
    }

    public static void spellSwordEffect(int chanceForCrit, LivingEntity target){
        if (LogicHolder.critRoll(chanceForCrit)) {
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                AreaEffectCloud cloud = (AreaEffectCloud) target.getWorld().spawnEntity(target.getLocation().add(0, 0, 0), EntityType.AREA_EFFECT_CLOUD);
    
                cloud.setBasePotionData(new PotionData(PotionType.HARMING));
                cloud.setDuration(20 * 4);
                cloud.setRadius(1.5f);
                cloud.setParticle(Particle.ELECTRIC_SPARK);
                cloud.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 1, 2), true);
                cloud.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 20 * 1, 0), true);
            }, 4);
        }
        if (LogicHolder.critRoll(chanceForCrit)) {
            PotionEffect currentEffect = target.getPotionEffect(PotionEffectType.POISON);
        
            if (currentEffect != null) {
                int newAmplifier = currentEffect.getAmplifier() + 1;
                int duration = currentEffect.getDuration();
                
                target.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, newAmplifier, false, true));
            }
        } 
    }

    public static void gravityBowEffect(LivingEntity target){
        LogicHolder.givePotionEffect(target, "LEVITATION", 20 * 5, 1);
        LogicHolder.givePotionEffect(target, "GLOWING", 20 * 5, 0);
        target.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, target.getLocation(), 1);
        target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PHANTOM_HURT, 1, 1);
    }

    public static void gravityArrow(Arrow arrow){
        arrow.setGravity(false);
        arrow.setVisibleByDefault(false);
        LogicHolder.particleEmitterOnEntity(arrow, Particle.ELECTRIC_SPARK, 5, 10 * 20, 0.2, 0.2, 0.2, 0.01);
        
        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            arrow.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, arrow.getLocation(), 1);
            arrow.getWorld().playSound(arrow.getLocation(), Sound.ENTITY_PHANTOM_HURT, 1, 1);
            arrow.remove();
        }, 20 * 10);

    }

    public static void ratBowEffect(Location hitLocation){
        for(int i = 0; i < 4; i++){            
            if (LogicHolder.critRoll(66)) {
                Silverfish silverfish = (Silverfish) hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
                LogicHolder.givePotionEffect(silverfish, "WEAVING", 20*20, 0);
            }
        }
        Silverfish silverfish = (Silverfish) hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
        LogicHolder.givePotionEffect(silverfish, "WEAVING", 20*20, 0);
    }

    public static void dedalusBowEffect(Entity hitEntity){

        if (hitEntity instanceof LivingEntity) {
            Weapons.thunderHammerEffect(100, (LivingEntity) hitEntity);
        }
        else{
            Location initialLocation = hitEntity.getLocation();
    
            Random random = new Random();
            int offsetX = random.nextInt(10) - 2;
            int offsetY = random.nextInt(5) - 2;
            int offsetZ = random.nextInt(10) - 2;
        
            Location randomLocation = initialLocation.clone().add(offsetX, offsetY, offsetZ);
        
            hitEntity.getWorld().strikeLightning(randomLocation);
        }
    }

    public static void bouncyCrossbowTargetEffect(Arrow arrow){
        Location location = arrow.getLocation();

        randomiseTimeExplosionCrossbow(location);
    }
    private static void randomiseTimeExplosionCrossbow(Location location){
        int randomInt = (int) (Math.random() * 5);

        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            WindCharge windCharge = (WindCharge) location.getWorld().spawnEntity(location, EntityType.WIND_CHARGE);
            windCharge.explode();
            fireworkEffect(location, (int)(Math.random() * 3 + 1));
        }, randomInt +2L);
        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            AreaEffectCloud cloud = (AreaEffectCloud) location.getWorld().spawnEntity(location.add(0, -(float) (Math.random() * 1), 0), EntityType.AREA_EFFECT_CLOUD);

            cloud.setBasePotionData(new PotionData(PotionType.HARMING));
            cloud.setDuration(20 * 2);
            cloud.setRadius(3.0f);
            cloud.setParticle(Particle.FLAME);
            cloud.addCustomEffect(new PotionEffect(PotionEffectType.WITHER, 20 * 5, 1), true);
        }, 4);
    }

    public static void grenadeThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        Snowball snowball = LogicHolder.throwSnowball(player, Keys.CUSTOM_GRENADE, 1);
        LogicHolder.particleEmitterOnEntity(snowball, Particle.FLAME, 1, 5 * 20, 0.1, 0.1, 0.1, 0.05);
    }

    public static void grenadeEffect(Location location){
        location.getWorld().createExplosion(location, 3, false, false);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 50, 0.1, 0.1, 0.1, 0.05);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 50, 0.1, 0.1, 0.1, 0.1);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 50, 0.1, 0.2, 0.1, 0.1);
    }
    
    public static void scatterThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        Snowball snowball = LogicHolder.throwSnowball(player, Keys.CUSTOM_SCATTER_BOMB, 1);
        LogicHolder.particleEmitterOnEntity(snowball, Particle.END_ROD, 1, 5 * 20, 0.1, 0.1, 0.1, 0.05);
    }

    public static void scatterEffect(Location location){
    	int timeQuarterSeconds = 6;
    	while (timeQuarterSeconds > 0) {

    	    Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
    	        int numberOfProjectiles = (int) (Math.random() * 5 + 1);
    	        while (numberOfProjectiles > 0) {
    	            // Randomize the X and Z coordinates within a range of -5 to 5
    	            double randomX = location.getX() + (Math.random() * 2 - 1);
    	            double randomZ = location.getZ() + (Math.random() * 2 - 1);


    	            // Create a new location with the randomized X and Z
    	            Location randomizedLocation = location.clone();
    	            randomizedLocation.setX(randomX);
    	            randomizedLocation.setZ(randomZ);
    	            randomizedLocation.setY(location.getY() + 1);


    	            Snowball snowball = (Snowball) randomizedLocation.getWorld().spawnEntity(randomizedLocation, EntityType.SNOWBALL);

    	            // Add small horizontal tilt (±10% of vertical speed)
    	            double tiltFactor = 0.3;
    	            double upwardSpeed = 0.5;
    	            double tiltX = (Math.random() * 2 - 1) * upwardSpeed * tiltFactor; // ±10%
    	            double tiltZ = (Math.random() * 2 - 1) * upwardSpeed * tiltFactor;

    	            // Apply fountain-like velocity
    	            snowball.setVelocity(new Vector(tiltX, upwardSpeed, tiltZ));

    	            // Tagging the projectile
    	            if (LogicHolder.critRoll(75)) {
    	                snowball.getPersistentDataContainer().set(Keys.CUSTOM_GRENADE, PersistentDataType.STRING, "grenade");
    	            } else {
    	                snowball.getPersistentDataContainer().set(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.STRING, "throwingFirework");
    	            }

    	            numberOfProjectiles--;
    	        }
    	    }, 10L * timeQuarterSeconds);

    	    timeQuarterSeconds--;
    	}
 

    }
    
    public static void breachThrow(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        Location playerLocation = player.getLocation();
        Vector direction = playerLocation.getDirection();

        Location tntLocation = playerLocation.add(direction.clone().multiply(1).add(new Vector(0, 1, 0)));
        TNTPrimed tnt = player.getWorld().spawn(tntLocation, TNTPrimed.class);
        
        LogicHolder.particleEmitterOnEntity(tnt, Particle.DRIPPING_LAVA, 2, 10 * 20);

        tnt.setVelocity(direction.multiply(1.0));
        tnt.setYield(20.0F);
        tnt.setFuseTicks(20 * 6);

        Location[] newTntLocation = new Location[1];
        newTntLocation[0] = tnt.getLocation(); // Initialize the location
        
        // Schedule the first task
        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            newTntLocation[0] = tnt.getLocation(); // Update the location
            tnt.getWorld().playSound(newTntLocation[0], Sound.ENTITY_CREEPER_PRIMED, 1, 1);
        }, 20 * 3);
        
        // Schedule the second task
        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            newTntLocation[0] = tnt.getLocation();
            tnt.getWorld().playSound(newTntLocation[0], Sound.ENTITY_CREEPER_PRIMED, 2, 1);
        }, 20 * 4);
        
        // Schedule the third task
        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            newTntLocation[0] = tnt.getLocation();
            tnt.getWorld().playSound(newTntLocation[0], Sound.ENTITY_TNT_PRIMED, 3, 1);
        }, 20 * 5);
        
        // Schedule the particle effects
        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            newTntLocation[0] = tnt.getLocation();
            tnt.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, newTntLocation[0], 150, 0.1, 0.1, 0.1, 0.05);
            tnt.getWorld().spawnParticle(Particle.EXPLOSION_EMITTER, newTntLocation[0], 150, 0.1, 0.1, 0.1, 0.05);
            tnt.getWorld().spawnParticle(Particle.CLOUD, newTntLocation[0], 150, 0.2, 0.2, 0.2, 0.1);
            tnt.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, newTntLocation[0], 150, 0.1, 0.2, 0.1, 0.3);
        }, 20 * 6);


    }

    public static void fireworkThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 10, 10);
        Snowball snowball = LogicHolder.throwSnowball(player, Keys.CUSTOM_THROWING_FIREWORK, 3);
        LogicHolder.particleEmitterOnEntity(snowball, Particle.FIREWORK, 4, 3 * 20);
    }

    public static void fireworkEffect(Location location, int power){
        Firework firework = LogicHolder.randomFirework(power, location);
        firework.detonate();
    }

    public static void smokeThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        LogicHolder.throwSnowball(player, Keys.CUSTOM_SMOKE_BOMB, 2);
    }

    public static void smokeEffect(Location location){
        location.getWorld().playSound(location, Sound.ENTITY_GENERIC_EXTINGUISH_FIRE, 10, 10);

        for(int i = 0; i < 12; i++){
            float number = (float)(Math.random() * 3);
            location.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 200, number, number, number, 0.005);
            location.getWorld().spawnParticle(Particle.CLOUD, location, 10, number, number, number, 0.015);
        }
        AreaEffectCloud cloud = (AreaEffectCloud) location.getWorld().spawnEntity(location.add(0, 1, 0), EntityType.AREA_EFFECT_CLOUD);

        cloud.setBasePotionData(new PotionData(PotionType.SLOWNESS));
        cloud.setDuration(20 * 10);
        cloud.setRadius(3.0f);
        cloud.setParticle(Particle.CAMPFIRE_COSY_SMOKE);
        cloud.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 5, 1), true);
    }

    public static void magneticTridentEffect(Player player){
        RaceEffects.fossilRiptide(player, 2);
    }
}
