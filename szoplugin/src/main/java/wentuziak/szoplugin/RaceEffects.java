package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class RaceEffects {

    static BukkitTask dwarfSwimTask;
    static BukkitTask caraGlideTask;
    static BukkitTask fossilSwimTask;
    private static BukkitTask sanguiniteHungerTask;


    //
    //      DWARF
    //
    public static void dwarfConsumptionEffect(Player player,  ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "HONEY_BOTTLE"
        ));

        if (!implementedFood.contains(consumedItem)) {
            return;
        }
        
        if (consumedItem.equals("HONEY_BOTTLE")) {
            LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 5, 0);
            LogicHolder.givePotionEffect(player, "SATURATION", 3, 0);
            LogicHolder.givePotionEffect(player, "CONFUSION", 20, 10);

        }
    }

        //
        //      GEAR UPGRADES
        //
        // else if ((mainHandMaterial == Material.DIAMOND_AXE && itemInOffHand.getAmount() >= 8) && itemInOffHand.isSimilar(CreateCustomItem.createSoulEssence())){
        //     LogicHolder.addAttributeToItemInHand(player);
            
        //     for(int i = 0; i < 8; i++){
        //         LogicHolder.removeItem(player, itemInOffHand);
        //     }
        //     return;
        // }

    public static void dwarfSwimEvent(Player player){   
        if (!TaskManager.isTaskRunning(player, "dwarfSwim")) {            
            final Player finalPlayer = player;
            dwarfSwimTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (!LogicHolder.isPlayerInWater(finalPlayer)) {
                        stopDwarfSwimTask(finalPlayer);
                        return;
                    }
                    LogicHolder.givePotionEffect(finalPlayer, "SLOW", 20 * 4, 2);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 3);
            TaskManager.addTask(player, "dwarfSwim", dwarfSwimTask);
        }
    }

    public static void stopDwarfSwimTask(Player player) {
        TaskManager.stopTask(player, "dwarfSwim");
    }

    //
    //          WITCH       
    //


    public static void witchAttackEvent(Player player, LivingEntity targetEntity){
        if (LogicHolder.critRoll(33)) {
            LogicHolder.givePotionEffect(targetEntity, "POISON", 20 * 10, 0);
            LogicHolder.givePotionEffect(targetEntity, "SLOW", 20 * 10, 0);
        }
    }

    public static void witchConsumptionEffect(Player player,  ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "POTION"
        ));

        if (!implementedFood.contains(consumedItem)) {
            return;
        }
        
        if (consumedItem.equals("POTION")) {
            int whatEffect = (int)(Math.random() * 100 + 1);

            if (whatEffect >= 90) {
                LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 45, 0);
            } else if (whatEffect >= 80) {
                LogicHolder.givePotionEffect(player, "SPEED", 20 * 45, 0);
            } else if (whatEffect >= 70) {
                LogicHolder.givePotionEffect(player, "FIRE_RESISTANCE", 20 * 45, 0);
            } else if (whatEffect >= 60) {
                LogicHolder.givePotionEffect(player, "INCREASE_DAMAGE", 20 * 45, 0);
            } else if (whatEffect >= 50) {
                LogicHolder.givePotionEffect(player, "HEALTH_BOOST", 20 * 45, 0);
            } else if (whatEffect >= 40) {
                LogicHolder.givePotionEffect(player, "ABSORPTION", 20 * 45, 0);
            } else if (whatEffect >= 30) {
                LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 45, 0);
            } else if (whatEffect >= 20) {
                LogicHolder.givePotionEffect(player, "JUMP", 20 * 45, 0);
            } else if (whatEffect >= 10) {
                LogicHolder.givePotionEffect(player, "WATER_BREATHING", 20 * 45, 0);
            } else {
                LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * 45, 0);
            }
        }
    }

    //
    //      Celestial
    //
    public static void celestialAttackEvent(Player player,  LivingEntity targetEntity){
        if (LogicHolder.critRoll(20)) {
            LogicHolder.givePotionEffect(targetEntity, "WEAKNESS", 20 * 10, 0);
            LogicHolder.givePotionEffect(targetEntity, "GLOWING", 20 * 10, 0);
        }
        if (LogicHolder.critRoll(20)) {
            LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 5, 0);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_GHAST_AMBIENT, 10, 10);
            player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 10);
        }
    }
    
    //
    //      MISKARU
    //
    public static void miskaruConsumptionEffect(Player player,  ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "HONEY_BOTTLE", "ROTTEN_FLESH", "SPIDER_EYE"
        ));

        if (!implementedFood.contains(consumedItem)) {
            return;
        }
        
        if (consumedItem.equals("HONEY_BOTTLE")) {
            LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 5, 0);
            LogicHolder.givePotionEffect(player, "SATURATION", 3, 0);
        }else if (consumedItem.equals("SPIDER_EYE")) {
            LogicHolder.givePotionEffect(player, "SPEED", 20 * 20, 0);
            LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * 20, 0);
        }else if (consumedItem.equals("ROTTEN_FLESH")) {
            LogicHolder.givePotionEffect(player, "STRENGTH", 20 * 20, 0);
            LogicHolder.givePotionEffect(player, "SATURATION", 3, 0);
        }
    }
        
    //
    //      CARA
    //
    public static void caraGlideEvent(Player player){   
        //speed boost
        if (player.getFoodLevel() > 0) {
            Vector direction = player.getLocation().getDirection().normalize();
            player.setVelocity(direction.multiply(1.1));
            player.setFoodLevel(player.getFoodLevel() - 1);
            
            if (player.getFoodLevel() < 0) {
                player.setFoodLevel(0);
            }
        }
        // fly
        if (!TaskManager.isTaskRunning(player, "caraGlide")) {            
            final Player finalPlayer = player;
            caraGlideTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (!LogicHolder.isPlayerAboveGround(finalPlayer, 0.2)) {
                        stopCaraGlideTask(finalPlayer);
                        LogicHolder.givePotionEffect(finalPlayer, "SLOW_FALLING", 20, 0);
                        finalPlayer.setGliding(false);
                        return;
                    }
                    finalPlayer.setGliding(true);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 0, 1);
            TaskManager.addTask(player, "caraGlide", caraGlideTask);
        }

    }

    public static void stopCaraGlideTask(Player player) {
        TaskManager.stopTask(player, "caraGlide");
    }

    //
    //      MEWCHANT
    //
    public static void mewchantConsumptionEffect(Player player,  ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "TROPICAL_FISH", "COOKED_SALMON", "COOKED_COD", "SPIDER_EYE"
        ));
        if (!implementedFood.contains(consumedItem)) {
            return;
        }
        
        if (consumedItem.equals("TROPICAL_FISH")) {
            LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 30, 1);
            player.setFoodLevel(player.getFoodLevel() + 4);
                
            if (player.getFoodLevel() > 20) {
                player.setFoodLevel(20);
            }  
        }else if (consumedItem.equals("COOKED_SALMON")) {
            player.setFoodLevel(player.getFoodLevel() + 2);
                
            if (player.getFoodLevel() > 20) {
                player.setFoodLevel(20);
            }  
        }else if (consumedItem.equals("COOKED_COD")) {
            player.setFoodLevel(player.getFoodLevel() + 2);
                
            if (player.getFoodLevel() > 20) {
                player.setFoodLevel(20);
            }  
        }else if (consumedItem.equals("SPIDER_EYE")) {
            LogicHolder.givePotionEffect(player, "SPEED", 20 * 30, 0);
            LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * 30, 0);
        }
    } 
    
    public static void mewchantFishEvent(Player player, Projectile projectile){
        if (LogicHolder.critRoll(5)) {
            if (projectile instanceof org.bukkit.entity.FishHook) {
                org.bukkit.entity.FishHook fishHook = (org.bukkit.entity.FishHook) projectile;
                Location bobberLocation = fishHook.getLocation();
                bobberLocation.getWorld().dropItemNaturally(bobberLocation, CreateCustomItem.createSoulEssence());
            }
        }
    }

    //
    //      ANIMATED FOSSIL
    //
    public static void fossilSwimEvent(Player player){   
        if (!TaskManager.isTaskRunning(player, "fossilSwim")) {            
            final Player finalPlayer = player;
            fossilSwimTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (!LogicHolder.isPlayerInWater(finalPlayer)) {
                        stopFossilSwimTask(finalPlayer);
                        return;
                    }
                    LogicHolder.givePotionEffect(finalPlayer, "WATER_BREATHING", 20 * 10, 0);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 3);
            TaskManager.addTask(player, "fossilSwim", fossilSwimTask);
        }
    }

    public static void stopFossilSwimTask(Player player) {
        TaskManager.stopTask(player, "fossilSwim");
    }

    //
    //      ZEPHYR
    //
    public static void zaphyrKnockback(Player player){
        player.getWorld().playSound(player.getLocation(), Sound.ENTITY_BREEZE_DEFLECT, 1, 1);
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            Vector direction = entity.getLocation().toVector().subtract(player.getLocation().toVector()).normalize();
            entity.getWorld().spawnParticle(Particle.CLOUD, entity.getLocation(), 10, 1, 1, 1, 0.015);
            double force = 3.0;
            entity.setVelocity(direction.multiply(force));
            LogicHolder.givePotionEffect((LivingEntity) entity, "SLOW", 20 * 10, 0);
        }
    }

    //
    //      Sanguinite
    //
    public static void sanguiniteKillEffect(Player player){
        if (LogicHolder.critRoll(50)) {
            LogicHolder.givePotionEffect(player, "ABSORPTION", 20*10, 0);
        }
        stopSanguiniteHungerTask(player);
    }

    public static void sanguiniteJumpEffect(Player player){
        if (LogicHolder.isPlayerAboveGround(player, 0.75)) {
            double speedMultiplier = 1.3;
    
            Vector direction = player.getLocation().getDirection();
            Vector velocity = direction.multiply(speedMultiplier);
        
            player.setVelocity(velocity);
            player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1, 1);
    
            player.setCooldown(Material.NETHER_STAR, 20 * 10);
        }
    }

    public static void sanguiniteHunger(Player player){
        if (!TaskManager.isTaskRunning(player, "sanguiniteHunger")){            
            final Player finalPlayer = player;
            sanguiniteHungerTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (finalPlayer.isSleeping()) {
                        stopSanguiniteHungerTask(finalPlayer);
                        return;
                    }
                    LogicHolder.givePotionEffect(finalPlayer, "HUNGER", 20 * 60 * 15, 0);
                }
                }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 60 * 5);
            TaskManager.addTask(player, "sanguiniteHunger", sanguiniteHungerTask);
        }
    }
    public static void stopSanguiniteHungerTask(Player player) {
        if (TaskManager.isTaskRunning(player, "sanguiniteHunger")){ 
            if (player.hasPotionEffect(PotionEffectType.HUNGER)) {
                player.removePotionEffect(PotionEffectType.HUNGER);
            }
    
            if (!TaskManager.isRestartScheduled(player)) {
                TaskManager.setRestartScheduled(player, true);
    
                Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                    sanguiniteHunger(player);
                    TaskManager.setRestartScheduled(player, false);
                }, 20 * 60 * 20);
            }
        }
        TaskManager.stopTask(player, "sanguiniteHunger");
    }

    public static void sanguiniteMagic(Player player, String effect){
        //Not yet implemented
        switch (effect) {
            case "1":
                
                break;
        
            default:
                break;
        }
    }

    public static void sanguiniteConsumptionEffect(Player player,  ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "ROTTEN_FLESH", "SPIDER_EYE"
        ));


        if (!implementedFood.contains(consumedItem)) {
            return;
        }

        switch (consumedItem) {
            case "ROTTEN_FLESH":
                LogicHolder.givePotionEffect(player, "REGENERATION", 20*20, 0);
                break;
            case "SPIDER_EYE":
                LogicHolder.givePotionEffect(player, "JUMP_BOOST", 20*20, 0);
                LogicHolder.givePotionEffect(player, "SPEED", 20*20, 0);
                break;
            default:
                break;
        }
    }

    //
    //      Elf
    //
    public static void elfShotEffect(Player player, Vector velocity){
        Vector direction = player.getLocation().getDirection().normalize();

        // Spawn 5 arrows with slight angle variations
        for (int i = 0; i < 5; i++) {
            // Create a new arrow entity
            Arrow arrow = (Arrow) player.getWorld().spawnEntity(player.getEyeLocation(), EntityType.ARROW);
    
            // Calculate a slight variation in the arrow's direction
            Vector arrowDirection = direction.clone();
            arrowDirection.setX(arrowDirection.getX() + (Math.random() - 0.5) * 0.2); // ±10% variation
            arrowDirection.setY(arrowDirection.getY() + (Math.random() - 0.5) * 0.2); // ±10% variation
            arrowDirection.setZ(arrowDirection.getZ() + (Math.random() - 0.5) * 0.2); // ±10% variation
    
            // Combine the arrow's direction with the provided velocity
            Vector finalVelocity = arrowDirection.multiply(1).add(velocity);
    
            // Set the arrow's velocity
            arrow.setVelocity(finalVelocity);
        }
    }
}
