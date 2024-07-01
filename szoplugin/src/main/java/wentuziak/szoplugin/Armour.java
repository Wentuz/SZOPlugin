package wentuziak.szoplugin;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Armour {
    
    static BukkitTask mermaidTailTask;
    
    public static void jetBootsFunc(LivingEntity player)
    {
        if (LogicHolder.isPlayerAboveGround(player, 0.75)) 
        {
            LogicHolder.givePotionEffect(player, "LEVITATION", 5, 9);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 10, 10);
            player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation(), 10, 0, -1, 0, 0);
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


    public static void mermaidTailFunc(Player player)
    {   
        final Player finalPlayer = player;
        mermaidTailTask = new BukkitRunnable() {
            @Override
            public void run(){
                if (!LogicHolder.isPlayerInWater(finalPlayer)) {
                    stopMermaidTailTask();
                    return;
                }
                LogicHolder.givePotionEffect(finalPlayer, "DOLPHINS_GRACE", 200, 2);
                LogicHolder.givePotionEffect(finalPlayer, "CONDUIT_POWER", 200, 1);
            }
        }.runTaskTimer(SzoPlugin.getInstance(), 20, 20*1);

    }


    public static void stopMermaidTailTask() {
        if (mermaidTailTask != null && !mermaidTailTask.isCancelled()) {
            mermaidTailTask.cancel();
            mermaidTailTask = null;
        }
    }

    public static void gluttonyPantsFunc(Player player)
    {
        LogicHolder.givePotionEffect(player, "SPEED", 200, 1);    
        LogicHolder.givePotionEffect(player, "REGENERATION", 200, 1);    
    }

}
