package wentuziak.szoplugin;


import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Silverfish;


public class Weapons {


    public static void thunderHammerFunc(int chanceForCrit, LivingEntity hitEntity)
    {
        if (LogicHolder.critRoll(chanceForCrit)) {
            LogicHolder.givePotionEffect(hitEntity, "WEAKNESS", 200, 1);
            LogicHolder.givePotionEffect(hitEntity, "SLOW", 200, 1);
            LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 200, 1);
            LogicHolder.givePotionEffect(hitEntity, "CONFUSION", 200, 1);
            hitEntity.getWorld().strikeLightning(hitEntity.getLocation());
        }
    }

    public static void explosiveSwordFunc(int chanceForCrit, LivingEntity hitEntity)
    {
        if (LogicHolder.critRoll(chanceForCrit)) 
        {
            hitEntity.getWorld().createExplosion(hitEntity.getLocation(), 2, false, false);
        }
    }

    public static void daemonSwordFunc(int chanceForCrit, LivingEntity hitEntity)
    {
        if (LogicHolder.critRoll(chanceForCrit)) 
        {
            LogicHolder.givePotionEffect(hitEntity, "HARM", 1, 0);
            LogicHolder.givePotionEffect(hitEntity, "POISON", 100, 3);
            LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 80, 1);
            hitEntity.getWorld().spawnParticle(Particle.SOUL, hitEntity.getLocation(), 40);

        }
    }

    public static void angelSwordFunc(int chanceForCrit, LivingEntity player)
    {
            if (LogicHolder.critRoll(chanceForCrit)) 
            {
                LogicHolder.givePotionEffect(player, "HEAL", 1, 0);
                LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 200, 1);
                player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 1);

            }
    }

    public static void gravityBowFunc(LivingEntity target)
    {
        LogicHolder.givePotionEffect(target, "LEVITATION", 100, 1);
        LogicHolder.givePotionEffect(target, "GLOWING", 100, 0);
        target.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, target.getLocation(), 1);
        target.getWorld().playSound(target.getLocation(), Sound.ENTITY_PHANTOM_HURT, 1, 1);

    }

    public static void ratBowFunc(Location hitLocation)
    {
        
        if (LogicHolder.critRoll(66)) {
            hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
        }
        if (LogicHolder.critRoll(66)) {
            hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
        }
        if (LogicHolder.critRoll(66)) {
            hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
        }
        if (LogicHolder.critRoll(66)) {
            hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);
        }
        hitLocation.getWorld().spawnEntity(hitLocation.add(0, 1, 0), EntityType.SILVERFISH);

    }


}
