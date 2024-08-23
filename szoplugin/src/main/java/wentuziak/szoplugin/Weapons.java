package wentuziak.szoplugin;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.AreaEffectCloud;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.PufferFish;
import org.bukkit.entity.Silverfish;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.util.Vector;


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
            hitEntity.getWorld().createExplosion(hitEntity.getLocation(), 1, false, false);
        }
    }

    public static void armorPiercerEffect(LivingEntity hitEntity, Integer damage){
        double currentHealth = hitEntity.getHealth();

        double newHealth = currentHealth - damage;

        hitEntity.setHealth(newHealth);
    }

    public static void daemonSwordEffect(int chanceForCrit, LivingEntity hitEntity)
    {
        if (LogicHolder.critRoll(chanceForCrit)) {
            hitEntity.getWorld().spawnParticle(Particle.SOUL, hitEntity.getLocation(), 40);
            LogicHolder.givePotionEffect(hitEntity, "WITHER", 20 * 10, 1);
            LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 20 * 8, 1);
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                LogicHolder.givePotionEffect(hitEntity, "HARM", 1, 0);
            }, 5L);
        }
    }

    public static void angelSwordEffect(int chanceForCrit, LivingEntity player)
    {
        if (LogicHolder.critRoll(chanceForCrit)) {
            double currentHealth = player.getHealth();
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    
            double newHealth = currentHealth + 4.0;
    
            if (newHealth > maxHealth) {
                newHealth = maxHealth;
            }
    
            player.setHealth(newHealth);
            LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 5, 0);
            player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 1);
        }
    }

    public static void gravityBowEffect(LivingEntity target)
    {
        LogicHolder.givePotionEffect(target, "LEVITATION", 20 * 5, 1);
        LogicHolder.givePotionEffect(target, "GLOWING", 20 * 5, 0);
        target.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, target.getLocation(), 1);
        target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PHANTOM_HURT, 1, 1);
    }

    public static void ratBowEffect(Location hitLocation)
    {
        for(int i = 0; i < 4; i++){            
            if (LogicHolder.critRoll(66)) {
                Silverfish silverfish = (Silverfish) hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
                LogicHolder.givePotionEffect(silverfish, "WEAVING", 20*20, 0);
            }
        }
        Silverfish silverfish = (Silverfish) hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
        LogicHolder.givePotionEffect(silverfish, "WEAVING", 20*20, 0);
    }

    public static void bouncyCrossbowGroundEffect(Arrow arrow, Vector normal) {
        if (LogicHolder.critRoll(10)) {
            Location location = arrow.getLocation();
            World world = location.getWorld();
    
            PufferFish previousPuffer = null;
            int numberOfPuffer = (int)(Math.random() * 4 + 1);
    
            for (int i = 0; i < numberOfPuffer; i++) {
                Location spawnLocation = location.clone().add(0, i, 0);
                PufferFish puffer = (PufferFish) world.spawnEntity(spawnLocation, EntityType.PUFFERFISH);
    
                if (previousPuffer != null) {
                    puffer.addPassenger(previousPuffer);
                }
    
                previousPuffer = puffer;
            }
        }
    }

    public static void bouncyCrossbowTargetEffect(Arrow arrow, LivingEntity target){
            Vector arrowVelocity = arrow.getVelocity().normalize();
            Vector knockback = arrowVelocity.multiply(1.2);
            knockback.setY(1.2);

            target.setVelocity(knockback);
    }

    public static void grenadeThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        LogicHolder.throwSnowball(player, playerContainer);
    }

    public static void grenadeEffect(Location location){
        location.getWorld().createExplosion(location, 3, false, false);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 50, 0.1, 0.1, 0.1, 0.05);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 50, 0.1, 0.1, 0.1, 0.1);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 50, 0.1, 0.2, 0.1, 0.1);
    }
    
    public static void breachThrow(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        Location playerLocation = player.getLocation();
        Vector direction = playerLocation.getDirection();

        Location tntLocation = playerLocation.add(direction.clone().multiply(1).add(new Vector(0, 1, 0)));
        TNTPrimed tnt = player.getWorld().spawn(tntLocation, TNTPrimed.class);

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
        LogicHolder.throwSnowball(player, playerContainer);
    }

    public static void fireworkEffect(Location location){
        Firework firework = LogicHolder.randomFirework(0, location);
        firework.detonate();
    }

    public static void smokeThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        LogicHolder.throwSnowball(player, playerContainer);
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
        Vector direction = player.getLocation().getDirection().normalize();
        player.setVelocity(direction.multiply(2));
    }
}
