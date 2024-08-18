package wentuziak.szoplugin;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

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
            damager.getWorld().createExplosion(damager.getLocation(), 1, false, false);
            LogicHolder.givePotionEffect(player, "REGENERATION", 20, 2);
        }
    }
    public static void reflectiveChestEffect(int chanceForCrit,int thornLvl, LivingEntity damager){
        if (LogicHolder.critRoll(chanceForCrit)){
            double currentHealth = damager.getHealth();
            double newHealth = currentHealth - thornLvl;
            damager.setHealth(newHealth);
            
            LogicHolder.givePotionEffect(damager, "HARM", 1, 0);
            LogicHolder.givePotionEffect(damager, "SLOW", 20 * 5, 2);
            damager.getLocation().getWorld().spawnParticle(Particle.ENCHANTED_HIT, damager.getLocation(), 10, 0.1, 0.1, 0.1, 0.05);
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

    public static void ramCapEffect(Player attacker ,LivingEntity hitEntity){

        Vector direction = hitEntity.getLocation().toVector().subtract(attacker.getLocation().toVector()).normalize();
        hitEntity.getWorld().spawnParticle(Particle.CLOUD, hitEntity.getLocation(), 10, 1, 1, 1, 0.015);
        double force = 8.0;
        hitEntity.setVelocity(direction.multiply(force));

    }

    public static void jumpPackEffect(Player player){

        double speedMultiplier = 1.5;

        Vector direction = player.getLocation().getDirection();
        Vector velocity = direction.multiply(speedMultiplier);
    
        player.setVelocity(velocity);
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_LAUNCH, 1, 1);
        player.getWorld().spawnParticle(Particle.CAMPFIRE_COSY_SMOKE, player.getLocation(), 50, 0.1, 0.2, 0.1, 0.1);

        player.setCooldown(Material.LEATHER_LEGGINGS, 20 * 10);
    }

    public static void strigaVeilEffect(int chanceForCrit, LivingEntity player){
        if (LogicHolder.critRoll(chanceForCrit)) {
            double currentHealth = player.getHealth();
            double maxHealth = player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
    
            double newHealth = currentHealth + 2.0;
    
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
                if (LogicHolder.critRoll(22)) {
                    event.setCancelled(true);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_VEX_CHARGE, 1.0f, 1.0f);
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

            if (!player.isFlying()) {
                    player.setAllowFlight(true);
                    player.setFlying(true);
                    player.setFlySpeed(0.03f);
            }
        } else {
            stopWalkersEffect(player);
        }
    }
    public static void stopWalkersEffect(Player player){
        player.setFlying(false);
        player.setAllowFlight(false);
        player.setFlySpeed(0.1f);
    }
}
