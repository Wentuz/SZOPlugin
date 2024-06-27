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


}
