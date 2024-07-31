package wentuziak.szoplugin;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Armour {
    
    static BukkitTask mermaidTailTask;
    
    public static void jetBootsEffect(LivingEntity player){
        if (LogicHolder.isPlayerAboveGround(player, 0.75)) {
            LogicHolder.givePotionEffect(player, "LEVITATION", 5, 9);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
            player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation(), 10, 0, -1, 0, 0);
        }    
    }

    public static void golemChestEffect(LivingEntity player){
        LogicHolder.givePotionEffect(player, "SLOW", 20 * 2, 4);
        LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 10, 1);
        LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 5, 0);
        player.getWorld().playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 1, 1);
        player.getWorld().spawnParticle(Particle.WAX_ON, player.getLocation(), 5, 0, -1, 0, 0);
    }

    public static void explosiveChestEffect(int chanceForCrit, LivingEntity damager, LivingEntity player){
        if (LogicHolder.critRoll(chanceForCrit)){
            LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 5, 2);
            damager.getWorld().createExplosion(damager.getLocation(), 2, false, false);
            LogicHolder.givePotionEffect(player, "REGENERATION", 20, 3);
        }
    }

    public static void nightHelmetEffect(Player player){
        World world = player.getWorld();
        if (!LogicHolder.isDaytime(world)){
            LogicHolder.givePotionEffect(player, "STRENGTH", 20 * 120, 0);
            LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 120, 0);
        }
        LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * 120, 0);
    }

    public static void mermaidTailEffect(Player player){   
        if (!TaskManager.isTaskRunning(player, "mermaidTail")) {            
            final Player finalPlayer = player;
            mermaidTailTask = new BukkitRunnable() {
                @Override
                public void run(){
                    LogicHolder.givePotionEffect(finalPlayer, "DOLPHINS_GRACE", 20 * 10, 2);
                    LogicHolder.givePotionEffect(finalPlayer, "CONDUIT_POWER", 20 * 10, 1);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 5);
            TaskManager.addTask(player, "mermaidTail", mermaidTailTask);
        }

    }


    public static void stopMermaidTailTask(Player player) {
        TaskManager.stopTask(player, "mermaidTail");
    }

    public static void gluttonyPantsEffect(Player player){
        LogicHolder.givePotionEffect(player, "SPEED", 20 * 10, 0);    
        LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 10, 1);    
        player.setFoodLevel(player.getFoodLevel() + 2);
                
        if (player.getFoodLevel() > 20) {
            player.setFoodLevel(20);
        }   
    }

    public static void strigaVeilEffect(int chanceForCrit, LivingEntity player)
    {
        if (LogicHolder.critRoll(chanceForCrit)) {
            double currentHealth = player.getHealth();
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    
            double newHealth = currentHealth + 4.0;
    
            if (newHealth > maxHealth) {
                newHealth = maxHealth;
            }
    
            player.setHealth(newHealth);

            LogicHolder.givePotionEffect(player, "SPEED", 20 * 4, 0);
            LogicHolder.givePotionEffect(player, "STRENGTH", 20 * 4, 0);
            player.getWorld().spawnParticle(Particle.FLAME, player.getLocation(), 50, 0.1, 0.1, 0.1, 0.1);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_AMBIENT, 1, 1);
        }
    }

}
