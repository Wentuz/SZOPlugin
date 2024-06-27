package wentuziak.szoplugin;

import org.bukkit.Particle;
import org.bukkit.entity.LivingEntity;


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


}
