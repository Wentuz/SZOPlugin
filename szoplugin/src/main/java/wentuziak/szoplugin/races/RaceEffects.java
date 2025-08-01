package wentuziak.szoplugin.races;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Allay;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.WindCharge;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

import net.md_5.bungee.api.ChatColor;
import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.TaskManager;
import wentuziak.szoplugin.customcrafting.CreateCustomItem;
import wentuziak.szoplugin.customcrafting.Witch;
import wentuziak.szoplugin.customitems.Weapons;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.customlogic.RandomLoot;
import wentuziak.szoplugin.entityevents.tagSpawnedMob;

public class RaceEffects {

    static BukkitTask dwarfSwimTask;
    static BukkitTask caraGlideTask;
    static BukkitTask fossilSwimTask;
    static BukkitTask hobbitNightTask;

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
        int duration = 5;

        if (consumedItem.equals("POTION")) duration = 30;
        	
            int whatEffect = (int)(Math.random() * 100 + 1);
            
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                LogicHolder.entityPotionEffectTimer(player, 1.5F, 0);
            }, 5);

            if (whatEffect >= 90) {
                LogicHolder.givePotionEffect(player, "REGENERATION", 20 * duration, 0);
            } else if (whatEffect >= 80) {
                LogicHolder.givePotionEffect(player, "SPEED", 20 * duration, 0);
            } else if (whatEffect >= 70) {
                LogicHolder.givePotionEffect(player, "FIRE_RESISTANCE", 20 * duration, 0);
            } else if (whatEffect >= 60) {
                LogicHolder.givePotionEffect(player, "INCREASE_DAMAGE", 20 * duration, 0);
            } else if (whatEffect >= 50) {
                LogicHolder.givePotionEffect(player, "HEALTH_BOOST", 20 * duration, 0);
            } else if (whatEffect >= 40) {
                LogicHolder.givePotionEffect(player, "ABSORPTION", 20 * duration, 0);
            } else if (whatEffect >= 30) {
                LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * duration, 0);
            } else if (whatEffect >= 20) {
                LogicHolder.givePotionEffect(player, "JUMP", 20 * duration, 0);
            } else if (whatEffect >= 10) {
                LogicHolder.givePotionEffect(player, "WATER_BREATHING", 20 * duration, 0);
            } else {
                LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * duration, 0);
            }
        
    }
    
    public static ItemStack witchBrewEvent(ItemStack potion) {
        if (potion == null) return potion;

        ItemMeta meta = potion.getItemMeta();
        if (!(meta instanceof PotionMeta)) return potion;
        
        if(potion.getType() == Material.POTION) return Witch.witchGetPotion((PotionMeta) meta);
        else return Witch.witchGetSplashPotion((PotionMeta) meta);
    }


    //
    //      Celestial
    //
    public static void celestialAttackEvent(Player player,  LivingEntity targetEntity){
        if (LogicHolder.critRoll(20)) {
            LogicHolder.givePotionEffect(targetEntity, "GLOWING", 20 * 15, 0);
        }
        LogicHolder.givePotionEffect(player, "INSTANT_HEALTH", 1, 0);

        if (targetEntity.hasPotionEffect(PotionEffectType.GLOWING)) {
            LogicHolder.givePotionEffect(targetEntity, "WEAKNESS", 20 * 5, 0);
            LogicHolder.givePotionEffect(targetEntity, "SLOWNESS", 20 * 5, 0);

            if (targetEntity.getHealth() <= 4) {
                targetEntity.getWorld().createExplosion(targetEntity.getLocation(), 2, false, false);
            }
        }
    }

    public static void celestialSummonEvent(Player player){
        if (player.getFoodLevel() >= 20 && !player.hasCooldown(Material.NETHER_STAR)) {
            player.setFoodLevel(0);
            player.setCooldown(Material.NETHER_STAR, 20 * 60 * 5);
            LogicHolder.startCooldownCountdown(player, 60 * 5);

        }else{
            return;
        }

        Location playerLocation = player.getLocation();
        Allay allay = (Allay) playerLocation.getWorld().spawnEntity(playerLocation, EntityType.ALLAY);

        int x = 5;
        for(int i = 1; i <= x; i++){
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                LogicHolder.lingeringPotionDrop(PotionType.HEALING, PotionEffectType.INSTANT_HEALTH, allay);
                allay.getWorld().spawnParticle(Particle.HEART, allay.getLocation(), 10);
            }, 20 * x * i);

            if (i == x) {
                Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                    allay.setHealth(0);
                }, 20 * x * i);
            }
        }
    }
    
    public static void celestialEatEssence(Player player, ItemStack item) {
    	player.setCooldown(item, 20 * 8);

    	player.playSound(player.getLocation(), Sound.ENTITY_ALLAY_DEATH, 1, 1);
    	
    	LogicHolder.givePotionEffect(player, "HEAL", 2, 2);
    	
    	LogicHolder.removeItem(player, item);
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
        tagSpawnedMob.callToHuntTag(player);
    }
        
    //
    //      CARA
    //
    public static void caraJumpEvent(Player player){
        //speed boost
        if (player.getFoodLevel() > 0 && !player.hasCooldown(Material.NETHER_STAR)) {
            Vector direction = player.getLocation().getDirection().normalize();
            player.setVelocity(direction.multiply(1.2));
            player.setFoodLevel(player.getFoodLevel() - 1);
            
            if (player.getFoodLevel() < 0) {
                player.setFoodLevel(0);
            }
            player.setCooldown(Material.NETHER_STAR, 20 * 2);
            
            LogicHolder.startCooldownCountdown(player, 2);
        }
    }

    public static void caraGlideEvent(Player player){   
        
        // fly
        if (!TaskManager.isTaskRunning(player, "caraGlide")) {            
            final Player finalPlayer = player;
            caraGlideTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (!LogicHolder.isPlayerAboveGround(finalPlayer, 0.2) || player.isInWater()) {
                        stopCaraGlideTask(finalPlayer);
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
    
    public static void caraOnHitEffect(Player player) {
        if (LogicHolder.critRoll(5)) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createSoulEssence());
        }

        double currentHealth = player.getHealth();

        if (currentHealth <= 6) {
            for(int i = 0; i < 2; i++){
                WindCharge windCharge = (WindCharge) player.getWorld().spawnEntity(player.getLocation(), EntityType.WIND_CHARGE);
                windCharge.explode();
            }
        }
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
        if (LogicHolder.critRoll(5)) { return; }
        barteredItems.clear();

        barteredItems.add(getRandomBarterItem());
    }

    private static ItemStack getRandomBarterItem() {
        int roll = rand.nextInt(101);
        
        if (roll < 2) {
            return new ItemStack(Material.GOLDEN_APPLE, 1);
        } else if (roll < 8) {
            return new ItemStack(Material.ENDER_PEARL, rand.nextInt(3) + 1);
        } else if (roll < 15) {
            ItemStack enchantedBook = new ItemStack(Material.ENCHANTED_BOOK, 1);
            RandomLoot.addRandomEnchantment(enchantedBook);
            return enchantedBook;
        } else if (roll < 20) {
            return new ItemStack(Material.OBSIDIAN, rand.nextInt(2) + 1);
        } else if (roll < 25) {
            return new ItemStack(Material.CRYING_OBSIDIAN, rand.nextInt(2) + 1);
        } else if (roll < 30) {
            return new ItemStack(Material.SPECTRAL_ARROW, rand.nextInt(6) + 10);
        } else if (roll < 35) {
            return new ItemStack(CreateCustomItem.createSoulEssence());
        } else if (roll < 45) {
            return new ItemStack(Material.BLACKSTONE, rand.nextInt(15) + 2);
        } else if (roll < 60) {
            return new ItemStack(Material.STRING, rand.nextInt(6) + 6);
        } else if (roll < 70) {
            return new ItemStack(Material.LEATHER, rand.nextInt(4) + 3);
        } else if (roll < 80) {
            return new ItemStack(Material.GLOWSTONE_DUST, rand.nextInt(4) + 2);
        } else if (roll < 90) {
            return new ItemStack(Material.NETHER_BRICK, rand.nextInt(12) + 2);
        } else if (roll < 96) {
            return new ItemStack(Material.MAGMA_CREAM, rand.nextInt(2) + 1);
        } else if (roll < 98) {
            return new ItemStack(Material.SADDLE, 1);
        } else if (roll < 99) {
            return new ItemStack(Material.GHAST_TEAR, rand.nextInt(2) + 1);
        } else {
            return new ItemStack(Material.NETHERITE_SCRAP, 1);
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
                        fossilRiptide(finalPlayer, 1);
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

    public static void fossilRiptide(Player player, int str) {
        Vector direction = player.getLocation().getDirection().normalize().multiply(2 * str); // Adjust the multiplier to control speed
            direction.setY(str);
            player.setVelocity(direction);

            player.setRiptiding(true);
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                player.setRiptiding(false);
            }, 20);
            
            player.getWorld().playSound(player.getLocation(), Sound.ITEM_TRIDENT_RIPTIDE_3, 1.0f, 1.0f);
            
            LogicHolder.particleEmitterOnEntity(player, Particle.BUBBLE, str * 6, str * 20, 0.2, 0, 0.2, 0.5);
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
        LogicHolder.givePotionEffect(player, "SATURATION", 1, 0);
        if (LogicHolder.critRoll(50)) {
            LogicHolder.givePotionEffect(player, "REGENERATION", 20*10, 0);
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
            LogicHolder.startCooldownCountdown(player, 4);

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
                    LogicHolder.givePotionEffect(finalPlayer, "STRENGTH", 20 * 60 * 15, 0);
                }
                }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 60 * 5);
            TaskManager.addTask(player, "sanguiniteHunger", sanguiniteHungerTask);
        }
    }
    public static void stopSanguiniteHungerTask(Player player) {
        if (TaskManager.isTaskRunning(player, "sanguiniteHunger")){ 
            if (player.hasPotionEffect(PotionEffectType.HUNGER)) {
                int numberOfDrops = (int)(Math.random() * 12 + 1);

                while (numberOfDrops > 0) {
                    if (LogicHolder.critRoll(80)) {player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createSoulEssence());}
                    numberOfDrops--;
                }
                if (LogicHolder.critRoll(80)) {player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createSanguiniteScroll());}

                player.removePotionEffect(PotionEffectType.HUNGER);
                player.removePotionEffect(PotionEffectType.STRENGTH);
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
            if (specialType != "multishot") {
                randomInt = randomInt + (2 * i);
            }
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
                    LogicHolder.startCooldownCountdown(player, 60);
                    return;
                case BONE:
                    Wolf wolf = LogicHolder.summonRandomWolf(playerLocation);
                    wolf.setBaby();
                    LogicHolder.removeItem(player, itemInOffHand);
                    LogicHolder.removeItem(player, itemInMainHand);
                    player.setCooldown(Material.POPPED_CHORUS_FRUIT, 20 * 60);
                    LogicHolder.startCooldownCountdown(player, 60);

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
                    LogicHolder.startCooldownCountdown(player, 60);
                    return;
                default:
                    return;
            }
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            player.setCooldown(Material.POPPED_CHORUS_FRUIT, 20 * 60);
            LogicHolder.startCooldownCountdown(player, 60);
            player.getWorld().spawnEntity(playerLocation, summonedEntityType);
        }
    }

    //
    //      HOBBIT
    //
    public static void hobbitConsumptionEffect(Player player,  ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "ROTTEN_FLESH", "SPIDER_EYE", "PUFFER_FISH"
        ));

        if (implementedFood.contains(consumedItem)) {
            LogicHolder.givePotionEffect(player, "HARM", 20 * 1, 0);
            return;
        }else{
            int foodLevel = player.getFoodLevel();
            if (foodLevel < 18) {
                player.setFoodLevel(foodLevel + 2);
            }
        }
    }
    
    public static void hobbitNightEvent(Player player){   
        if (!TaskManager.isTaskRunning(player, "hobbitNight")) {            
            final Player finalPlayer = player;
            hobbitNightTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (LogicHolder.isDaytime(player.getWorld())) {
                        stopHobbitNightTask(finalPlayer);
                        return;
                    }
                    LogicHolder.givePotionEffect(finalPlayer, "SPEED", 20 * 4, 0);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 3);
            TaskManager.addTask(player, "hobbitNight", hobbitNightTask);
        }
    }

    public static void stopHobbitNightTask(Player player) {
        TaskManager.stopTask(player, "hobbitNight");
    }
    
    public static boolean hobbitFoodCreate(Player player, ItemStack item) {

        ItemMeta meta = item.getItemMeta();
        meta.setLore(Collections.singletonList(ChatColor.GRAY + "Gastronomic triumph"));
        meta.setEnchantmentGlintOverride(true);
        meta.getPersistentDataContainer().set(Keys.CUSTOM_BETTER_FOOD, PersistentDataType.BOOLEAN, true);

        item.setItemMeta(meta);
    	
    	return true;
    }
    
    
    public static boolean hobbitFoodEffects(Player player, ItemStack consumedItem) {
    	
    	int value = 0;
    	Material material = consumedItem.getType();
    	
    	switch (material) {
    	case COOKED_COD, COOKED_SALMON -> LogicHolder.givePotionEffect(player, "WATER_BREATHING", 20 * 60, 0);
    	case COOKED_BEEF, COOKED_PORKCHOP, COOKED_MUTTON -> LogicHolder.givePotionEffect(player, "RESISTANCE", 20 * 20, 0);
    	case COOKED_RABBIT, COOKED_CHICKEN -> LogicHolder.givePotionEffect(player, "JUMP_BOOST", 20 * 60, 1);
    	case DRIED_KELP, BAKED_POTATO -> LogicHolder.givePotionEffect(player, "HASTE", 20 * 60, 0);
    	case COOKIE, BREAD -> LogicHolder.givePotionEffect(player, "HASTE", 20 * 60, 0);
    	case BEETROOT_SOUP, MUSHROOM_STEW -> value = 1;
    	case PUMPKIN_PIE, GOLDEN_CARROT, RABBIT_STEW -> value = 2;
    	case GOLDEN_APPLE -> LogicHolder.givePotionEffect(player, "HEAL", 1, 0);
    	default -> LogicHolder.givePotionEffect(player, "SATURATION", 2, 0);
    	}
    	LogicHolder.givePotionEffect(player, "SATURATION", 2, value);
    	return true;
    }
}
