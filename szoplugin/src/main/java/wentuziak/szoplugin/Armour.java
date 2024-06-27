package wentuziak.szoplugin;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;

public class Armour {
    
    
    public static void jetBootsFunc(LivingEntity player)
    {
        if (LogicHolder.isPlayerAboveGround(player, 0.75)) 
        {
            LogicHolder.givePotionEffect(player, "LEVITATION", 5, 9);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 10, 10);
            player.getWorld().spawnParticle(Particle.EXPLOSION_NORMAL, player.getLocation(), 20, 0, -1, 0, 0);
        }    
    }

    public static void golemChestFunc(LivingEntity player)
    {
        LogicHolder.givePotionEffect(player, "SLOW", 40, 4);
        LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 200, 0);
        LogicHolder.givePotionEffect(player, "REGENERATION", 200, 1);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
        player.getWorld().spawnParticle(Particle.WAX_ON, player.getLocation(), 5, 0, -1, 0, 0);
    }

    public static void explosiveChestFunc(int chanceForCrit, LivingEntity damager, LivingEntity player)
    {
        if (LogicHolder.critRoll(chanceForCrit))
        {
            LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 5, 4);
            damager.getWorld().createExplosion(damager.getLocation(), 2, false, false);
            LogicHolder.givePotionEffect(player, "REGENERATION", 20, 4);
        }
    }


}
