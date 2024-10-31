package wentuziak.szoplugin.races;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WindCharge;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.TaskManager;
import wentuziak.szoplugin.customcrafting.CreateCustomItem;
import wentuziak.szoplugin.customitems.Weapons;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.customlogic.RandomLoot;
import wentuziak.szoplugin.entityevents.tagSpawnedMob;

public class RaceEffects {

    static BukkitTask dwarfSwimTask;
    static BukkitTask caraGlideTask;
    static BukkitTask fossilSwimTask;
    private static BukkitTask sanguiniteHungerTask;
    private final static Random rand = new Random();


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


    public static void witchAttackEvent(LivingEntity targetEntity){
        if (LogicHolder.critRoll(33)) {
            LogicHolder.givePotionEffect(targetEntity, "POISON", 20 * 10, rand.nextInt(2));
            LogicHolder.givePotionEffect(targetEntity, "SLOW", 20 * 10, rand.nextInt(4));
        }
    }

    public static void witchConsumptionEffect(Player player,  ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            LogicHolder.entityPotionEffectTimer(player, 1.5F, 0);
        }, 5);

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
        LogicHolder.givePotionEffect(player, "INSTANT_HEALTH", 1, 0);
        
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

    public static void miskaruCallToHunt(Player player){
        player.sendMessage("Call to hunt");

        tagSpawnedMob.callToHuntTag(player);
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
        if (LogicHolder.critRoll(10)) {
            if (projectile instanceof org.bukkit.entity.FishHook) {
                org.bukkit.entity.FishHook fishHook = (org.bukkit.entity.FishHook) projectile;
                Location bobberLocation = fishHook.getLocation();
                bobberLocation.getWorld().dropItemNaturally(bobberLocation, CreateCustomItem.createSoulEssence());
            }
        }
    }

    public static void mewchantBarterEvent(List<ItemStack> barteredItems){
        barteredItems.clear();

        barteredItems.add(getRandomBarterItem());
    }

    private static ItemStack getRandomBarterItem() {
        int roll = rand.nextInt(100);

        if (roll < 5) {
            return new ItemStack(Material.NETHERITE_SCRAP, 1);
        } else if (roll < 12) {
            ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
            RandomLoot.addRandomEnchantment(enchantedBook);
            return enchantedBook;
        } else if (roll < 20) {
            return new ItemStack(Material.ENDER_PEARL, rand.nextInt(3) + 1);
        } else if (roll < 28) {
            return new ItemStack(Material.OBSIDIAN, rand.nextInt(2) + 1);
        } else if (roll < 38) {
            return new ItemStack(Material.CRYING_OBSIDIAN, rand.nextInt(2) + 1);
        } else if (roll < 48) {
            return new ItemStack(Material.SPECTRAL_ARROW, rand.nextInt(6) + 10);
        } else if (roll < 58) {
            return new ItemStack(CreateCustomItem.createSoulEssence());
        } else if (roll < 68) {
            return new ItemStack(Material.IRON_INGOT, rand.nextInt(3) + 2);
        } else if (roll < 78) {
            return new ItemStack(Material.STRING, rand.nextInt(6) + 6);
        } else if (roll < 88) {
            return new ItemStack(Material.LEATHER, rand.nextInt(4) + 3);
        } else if (roll < 93) {
            return new ItemStack(Material.GLOWSTONE_DUST, rand.nextInt(4) + 2);
        } else if (roll < 96) {
            return new ItemStack(Material.MAGMA_CREAM, rand.nextInt(2) + 1);
        } else if (roll < 98) {
            return new ItemStack(Material.SADDLE, 1);
        } else if (roll < 99) {
            return new ItemStack(Material.GHAST_TEAR, 1);
        } else {
            return new ItemStack(Material.GOLDEN_APPLE, 1);
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
                    if (finalPlayer.isSwimming()) {
                        fossilRiptide(finalPlayer);
                    }
                    if (!LogicHolder.isPlayerInWater(finalPlayer)) {
                        stopFossilSwimTask(finalPlayer);
                        return;
                    }
                    LogicHolder.givePotionEffect(finalPlayer, "WATER_BREATHING", 20 * 10, 0);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 2);
            TaskManager.addTask(player, "fossilSwim", fossilSwimTask);
        }
    }

    public static void stopFossilSwimTask(Player player) {
        TaskManager.stopTask(player, "fossilSwim");
    }

    private static void fossilRiptide(Player player) {
        Vector direction = player.getLocation().getDirection().normalize().multiply(2); // Adjust the multiplier to control speed
            direction.setY(1);
            player.setVelocity(direction);

            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 1.0f);
            player.getWorld().spawnParticle(Particle.BUBBLE, player.getLocation(), 30);
    }


    public static void fossilSummonEvilAxolotl(Player player, ItemStack itemInOffHand, ItemStack itemInMainHand){
        if (itemInMainHand.isSimilar(CreateCustomItem.createSoulEssence()) && itemInOffHand.getType() == Material.AXOLOTL_BUCKET) {            
            Axolotl axolotl = (Axolotl) player.getWorld().spawnEntity(player.getLocation().add(0, 1, 0), EntityType.AXOLOTL);
            LogicHolder.givePotionEffect(axolotl, "STRENGTH", 20 * 60 * 10, 3);
    
            double speedMultiplier = 2;
    
            Vector direction = player.getLocation().getDirection();
            Vector velocity = direction.multiply(speedMultiplier);
        
            axolotl.setVelocity(velocity);
            
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
                    entity.getWorld().strikeLightning(entity.getLocation());
                    entity.getWorld().strikeLightning(entity.getLocation());
                }
                axolotl.getWorld().strikeLightning(axolotl.getLocation());
            }, 20 * 5);
            
            LogicHolder.removeItem(player, itemInMainHand);
            LogicHolder.removeItem(player, itemInOffHand);
        }
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
            LogicHolder.givePotionEffect(player, "SATURATION", 1, 0);
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
    
            player.setCooldown(Material.NETHER_STAR, 20 * 4);
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

            TaskManager.stopTask(player, "sanguiniteHunger");
        }
        if (!TaskManager.isRestartScheduled(player)) {
            TaskManager.setRestartScheduled(player, true);

            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                sanguiniteHunger(player);
                TaskManager.setRestartScheduled(player, false);
            }, 20 * 60 * 20);
        }

        int numberOfDrops = (int)(Math.random() * 12 + 1);

        LogicHolder.rollTreasure(3, player.getLocation(), "Mobs");
        while (numberOfDrops > 0) {
            if (LogicHolder.critRoll(80)) {player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createSoulEssence());}
            if (LogicHolder.critRoll(50)) {LogicHolder.rollTreasure(3, player.getLocation(), "Mobs");}
            if (LogicHolder.critRoll(33)) {LogicHolder.rollTreasure(3, player.getLocation(), "Fishing");}
            
            numberOfDrops--;
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
                Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                    player.removePotionEffect(PotionEffectType.HUNGER);
                    player.getWorld().playSound(player.getLocation(), Sound.ENTITY_PLAYER_BURP, 1, 1);
                }, 20 * 1);
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
    public static void elfShotEffect(LivingEntity entity, Vector velocity, String specialType, String  bowType){
        Vector direction = entity.getLocation().getDirection().normalize();
        int randomInt;

        for (int i = 0; i < 5; i++) {
            randomInt = (int) (Math.random() * 5 + 1);
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                    
                Arrow arrow = (Arrow) entity.getWorld().spawnEntity(entity.getEyeLocation(), EntityType.ARROW);
                    
                // Calculate a slight variation in the arrow's direction
                Vector arrowDirection = direction.clone();
                arrowDirection.setX(arrowDirection.getX() + (Math.random() - 0.5) * 0.2); // ±10% variation
                arrowDirection.setY(arrowDirection.getY() + (Math.random() - 0.5) * 0.2); // ±10% variation
                arrowDirection.setZ(arrowDirection.getZ() + (Math.random() - 0.5) * 0.2); // ±10% variation
                
                if (specialType == "flame") {
                    arrow.setVisualFire(true);
                    arrow.setFireTicks(20*10);
                }else if(specialType == "multishot"){
                    arrowDirection.setX(arrowDirection.getX() + (Math.random() - 0.5) * 0.8); // ±10% variation
                    arrowDirection.setZ(arrowDirection.getZ() + (Math.random() - 0.5) * 0.8); // ±10% variation
                }else if(specialType == "piercing"){
                    arrow.setPierceLevel(1);
                }
                
                
                if (bowType == "rat") {
                    arrow.getPersistentDataContainer().set(Keys.CUSTOM_RAT_BOW, PersistentDataType.STRING, "ratArrow");
                }else if (bowType == "gravity") {
                    arrow.getPersistentDataContainer().set(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.STRING, "antiGravArrow");
                    Weapons.gravityArrow(arrow);
                }else if (bowType == "bouncy") {
                    arrow.getPersistentDataContainer().set(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.STRING, "bouncyArrow");
                }else if (bowType == "dedalus") {
                    arrow.getPersistentDataContainer().set(Keys.CUSTOM_DEDALUS_BOW, PersistentDataType.STRING, "dedalusArrow");
                }
                Vector finalVelocity = arrowDirection.multiply(1).add(velocity);
                
                arrow.setVelocity(finalVelocity);
            }, randomInt);
        }
    }

    public static void elfFeedEffect(LivingEntity entity){
        EntityType type = entity.getType();

        switch (type) {
            case WOLF:
                Wolf wolf = (Wolf) entity;
                if (!wolf.isLoveMode()) {
                    LogicHolder.givePotionEffect(wolf, "REGENERATION", 20*60, 0);
                    LogicHolder.givePotionEffect(wolf, "STRENGTH", 20*60, 0);
                }
                break;
            case HORSE:
                LogicHolder.givePotionEffect(entity, "SPEED", 20*60*5, 1);
                LogicHolder.givePotionEffect(entity, "JUMP_BOOST", 20*60*5, 1);
                break;
            default:
                break;
        }
    }

    public static void elfBreedingEffect(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand){
        if (itemInMainHand.isSimilar(CreateCustomItem.createSoulEssence())) {

            Material offHandMaterial = itemInOffHand.getType();
            EntityType summonedEntityType;
            Location playerLocation = player.getLocation();

            switch (offHandMaterial) {
                case WHEAT_SEEDS:
                    LogicHolder.summonRandomParrot(playerLocation);
                    LogicHolder.removeItem(player, itemInOffHand);
                    LogicHolder.removeItem(player, itemInMainHand);
                    player.setCooldown(Material.POPPED_CHORUS_FRUIT, 20 * 60);
                    return;
                case BONE:
                    Wolf wolf = LogicHolder.summonRandomWolf(playerLocation);
                    wolf.setBaby();
                    LogicHolder.removeItem(player, itemInOffHand);
                    LogicHolder.removeItem(player, itemInMainHand);
                    player.setCooldown(Material.POPPED_CHORUS_FRUIT, 20 * 60);
                    return;
                case MUSHROOM_STEW:
                    summonedEntityType = EntityType.MOOSHROOM;
                    break;
                case COD:
                    summonedEntityType = EntityType.COD;
                    break;
                case SALMON:
                    Cat cat = LogicHolder.summonRandomCat(playerLocation);
                    cat.setBaby();
                    LogicHolder.removeItem(player, itemInOffHand);
                    LogicHolder.removeItem(player, itemInMainHand);
                    player.setCooldown(Material.POPPED_CHORUS_FRUIT, 20 * 60);
                    return;
                default:
                    return;
            }
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            player.setCooldown(Material.POPPED_CHORUS_FRUIT, 20 * 60);
            player.getWorld().spawnEntity(playerLocation, summonedEntityType);
        }
    }
}
