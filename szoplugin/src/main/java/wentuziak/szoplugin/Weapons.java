package wentuziak.szoplugin;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Silverfish;
import org.bukkit.persistence.PersistentDataContainer;


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
            hitEntity.getWorld().createExplosion(hitEntity.getLocation(), 2, false, false);
        }
    }

    public static void daemonSwordEffect(int chanceForCrit, LivingEntity hitEntity)
    {
        if (LogicHolder.critRoll(chanceForCrit)) {
            LogicHolder.givePotionEffect(hitEntity, "HARM", 1, 0);
            LogicHolder.givePotionEffect(hitEntity, "POISON", 20 * 10, 1);
            LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 20 * 8, 1);
            hitEntity.getWorld().spawnParticle(Particle.SOUL, hitEntity.getLocation(), 40);
        }
    }

    public static void angelSwordEffect(int chanceForCrit, LivingEntity player)
    {
        if (LogicHolder.critRoll(chanceForCrit)) {
            LogicHolder.givePotionEffect(player, "HEAL", 1, 0);
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

    public static void grenadeThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 10, 10);
        LogicHolder.throwSnowball(player, playerContainer);
    }

    public static void grenadeEffect(Location location){
        location.getWorld().createExplosion(location, 4, false, false);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 50, 0.1, 0.1, 0.1, 0.05);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_SIGNAL_SMOKE, location, 50, 0.1, 0.1, 0.1, 0.1);
        location.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, location, 50, 0.1, 0.2, 0.1, 0.1);
    }
}
