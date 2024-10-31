package wentuziak.szoplugin.customitems;

import java.security.Key;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Bogged;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Wolf;
import org.bukkit.entity.ZombieHorse;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.TaskManager;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.entityevents.tagSpawnedMob;

public class MagicItems {
    static BukkitTask ancientShellTask;


    public static void teleportSpell(Player player, boolean isBoosted){
        int distance = isBoosted ? 150 : 50;

        Block targetBlock = player.getTargetBlock(null, distance);

        Location blockLocation = targetBlock.getLocation();

        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();

        Location targetLocation = new Location(player.getWorld(), blockX, blockY + 1, blockZ, yaw, pitch);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 100);
        player.teleport(targetLocation);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        LogicHolder.givePotionEffect(player, "BLINDNESS", 80, 0);
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 100);

    }
    public static void homeTeleport(Player player){
        Location bedLocation = player.getBedSpawnLocation();
        if (bedLocation != null) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.teleport(bedLocation);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }else{
            Location targetLocation = new Location(Bukkit.getWorld("world"), 0, 120, 0);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.teleport(targetLocation);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

        }
    }
    public static void deathTeleport(Player player){
        Location deathLocation = player.getLastDeathLocation();
        if (deathLocation != null) {
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.teleport(deathLocation);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
        }else{
            Location targetLocation = new Location(Bukkit.getWorld("world"), 0, 120, 0);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);
            player.teleport(targetLocation);
            player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 1, 1);

        }
    }

    public static void spiritLeech(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1, 1);
        LogicHolder.throwSnowball(player, playerContainer, 4);
    }

    public static void spiritLeechEffect(LivingEntity targetEntity){
        if (targetEntity instanceof Player) {
            ((Player) targetEntity).playSound(targetEntity.getLocation(), Sound.ENTITY_ENDERMITE_DEATH, 1, 1);
        }
        LogicHolder.givePotionEffect(targetEntity, "WITHER", 20*10, 1);
        LogicHolder.givePotionEffect(targetEntity, "HARM", 20*1, 0);
        LogicHolder.givePotionEffect(targetEntity, "WEAKNESS", 20*10, 1);
        LogicHolder.givePotionEffect(targetEntity, "BLINDNESS", 20*10,0);
        LogicHolder.givePotionEffect(targetEntity, "DARKNESS", 20*10,0);
    }

    public static void spiderYeet(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 1);
        LogicHolder.throwSnowball(player, playerContainer, 4);
    }

    public static void spiderYeetEffect(Location location){
        Player nearestPlayer = LogicHolder.findNearestPlayer(location);

        for(int i = 0; i < 7; i++){            
            if (LogicHolder.critRoll(66)) {
                CaveSpider caveSpider = (CaveSpider) location.getWorld().spawnEntity(location.add(0, 1, 0), EntityType.CAVE_SPIDER);
                if (nearestPlayer != null) {
                    caveSpider.setTarget(nearestPlayer);
                }
            }
            
        }
        CaveSpider caveSpider = (CaveSpider) location.getWorld().spawnEntity(location.add(0, 1, 0), EntityType.CAVE_SPIDER);
        if (nearestPlayer != null) {
            caveSpider.setTarget(nearestPlayer);
        }
    }

    public static void obliterate(Player player){
        Location location = player.getLocation();
        for(int i = 0; i < 15; i++){            
            if (LogicHolder.critRoll(66)) {
                int x = (int)(Math.random() * 2);
                int y = (int)(Math.random() * 2);
                int z = (int)(Math.random() * 2);
                location.getWorld().spawnEntity(location.add(x, y, z), EntityType.TNT_MINECART);
            }
        }
        location.getWorld().createExplosion(location, 5, true, true);
    }


    public static void ancientShellEffect(Player player){   
        if (!TaskManager.isTaskRunning(player, "ancientShell")) {            
            final Player finalPlayer = player;
            ancientShellTask = new BukkitRunnable() {
                @Override
                public void run(){
                    LogicHolder.givePotionEffect(finalPlayer, "DOLPHINS_GRACE", 20 * 10, 1);
                    LogicHolder.givePotionEffect(finalPlayer, "CONDUIT_POWER", 20 * 10, 1); 
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 5);
            TaskManager.addTask(player, "ancientShell", ancientShellTask);
        }

    }

    public static void magicStormCall(Player player, boolean isBoosted){
        int distance = isBoosted ? 100 : 50;

        Block targetBlock = player.getTargetBlock(null, distance);

        Location blockLocation = targetBlock.getLocation();

        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        Location targetLocation = new Location(player.getWorld(), blockX + 5, blockY + 25, blockZ);

        magicStormEffect(targetLocation, isBoosted);
    }

    public static void magicStormEffect(Location location, boolean isBoosted){
        Weapons.smokeEffect(location);
        int timeHalfSeconds = isBoosted ? 30 : 10;
        if (isBoosted) {
            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                Weapons.smokeEffect(location);
            }, 5 * timeHalfSeconds);
        }
        while (timeHalfSeconds > 0) {

            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                int numberOfProjectiles = (int) (Math.random() * 20 + 1);
                while (numberOfProjectiles > 0) {                
                    // Randomize the X and Z coordinates within a range of -5 to 5
                    double randomX = location.getX() + (Math.random() * 10 - 5); // Random value between -5 and 5
                    double randomZ = location.getZ() + (Math.random() * 10 - 5); // Random value between -5 and 5

                    // Create a new location with the randomized X and Z
                    Location randomizedLocation = location.clone();
                    randomizedLocation.setX(randomX);
                    randomizedLocation.setZ(randomZ);

                    Snowball snowball = (Snowball) randomizedLocation.getWorld().spawnEntity(randomizedLocation, EntityType.SNOWBALL); 
                    int critChance = isBoosted ? 10 : 20;
                    if (LogicHolder.critRoll(critChance)) {
                        if (LogicHolder.critRoll(critChance * 2)) {
                            snowball.getPersistentDataContainer().set(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.STRING, "spiritLeech");
                        }else{
                            snowball.getPersistentDataContainer().set(Keys.CUSTOM_GRENADE, PersistentDataType.STRING, "grenade");
                        }
                    }else{
                        snowball.getPersistentDataContainer().set(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.STRING, "throwingFirework");
                    }              
                    numberOfProjectiles--;
                }
            }, 10 * timeHalfSeconds);

            timeHalfSeconds--;
        }     
    }


    public static void stopAncientShellTask(Player player) {
        TaskManager.stopTask(player, "ancientShell");
    }

    public static void windCharmEffect(Player player){
        LogicHolder.givePotionEffect(player, "SPEED", 20*60*5, 1);
        LogicHolder.givePotionEffect(player, "SLOW_FALLING", 20*60*5, 1);
    }

    public static void webTrapThrow(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 1);
        LogicHolder.throwSnowball(player, playerContainer, 6);
    }

    public static void webTrapEffect(Location location){
        World world = location.getWorld();
    
        // Ensure the world is not null
        if (world == null) {
            return;
        }

        int startX = location.getBlockX();
        int startY = location.getBlockY() + 1;
        int startZ = location.getBlockZ();

        Material replaceWith = Material.COBWEB;

        // Loop over the 2x2 area horizontally (X and Z axes)
        for (int x = startX; x < startX + 2; x++) {
            for (int z = startZ; z < startZ + 2; z++) {
                Block block = world.getBlockAt(x, startY, z);

                if (block.getType() == Material.AIR) {
                    block.setType(replaceWith);
                }
            }
        }
    }

    public static Arrow arrowEnchanterEffect(Arrow arrow){
        PotionEffect poisonEffect = new PotionEffect(PotionEffectType.INSTANT_DAMAGE, 1, 0);
        arrow.addCustomEffect(poisonEffect, true);

        return arrow;
    }

    public static void crimsonMagic(Player player, ItemStack itemInOffHand, ItemStack itemInMainHand, Boolean isBoosted){
        Material type = itemInOffHand.getType();
        Integer value = 0;

        switch (type) {
            case GLISTERING_MELON_SLICE:
                RayTraceResult result = LogicHolder.rayTrace(25, 1, player);
                if (isBoosted) {
                    value = 1;
                }
                if (result != null) {
                    LivingEntity entiy = (LivingEntity) result.getHitEntity();
                    
                    LogicHolder.givePotionEffect(entiy, "REGENERATION", 20*60*2, value);
                    LogicHolder.givePotionEffect(entiy, "GLOWING", 20, 0);
                    LogicHolder.givePotionEffect(entiy, "DAMAGE_RESISTANCE", 20*60*2, value);
                    entiy.getWorld().spawnParticle(Particle.HEART, entiy.getLocation(), 20, 1, 1, 1);

                } else {
                    LogicHolder.givePotionEffect(player, "REGENERATION", 20*60*2, value);
                    LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20*60*2, value); 
                    player.getWorld().spawnParticle(Particle.HEART, player.getLocation(), 20, 1, 1, 1);
                }                
                break;
            case BONE:
                EntityType typeOfEntity = EntityType.BOGGED;
                value = 4;
                if (isBoosted) {
                    typeOfEntity = EntityType.ZOGLIN;
                }
                while (value >= 0) {
                    
                    LivingEntity mob = (LivingEntity) player.getWorld().spawnEntity(player.getLocation().add(0, 1, 0), typeOfEntity);
                    LogicHolder.givePotionEffect(mob, "FIRE_RESISTANCE", 20 * 60, 0);

                    double speedMultiplier = 2;
    
                    Vector direction = player.getLocation().getDirection();
                    Vector velocity = direction.multiply(speedMultiplier);
                
                    mob.setVelocity(velocity);
                    
                    value--;
                }
                break;
            case SADDLE:
                if (isBoosted) {
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.ZOMBIE_HORSE);
                }else{
                    player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
                }
            default:
                return;
        }
        LogicHolder.removeItem(player, itemInMainHand);
        LogicHolder.removeItem(player, itemInOffHand);
    }

    public static void effectTransfuserEffect(Player player, LivingEntity entity){
        LogicHolder.entityPotionEffectTimer(player, 0.5F, -1);

        for (PotionEffect effect : player.getActivePotionEffects()) {
            PotionEffectType effectType = effect.getType(); // Get the potion effect type
            int newAmplifier = effect.getAmplifier() + 1;
            int newDuration = (int) (effect.getDuration() * 0.5F);

            entity.removePotionEffect(effectType);

            PotionEffect newEffect = new PotionEffect(effectType, newDuration, newAmplifier);
            entity.addPotionEffect(newEffect);
        }
    }

    public static void summonCerberus(Player player){
        if (player.getPersistentDataContainer().has(Keys.MOB_PLAYER_SUMMON, PersistentDataType.BOOLEAN)) {
            return;
        }
        Wolf wolf = LogicHolder.summonRandomWolf(player.getLocation());
        tagSpawnedMob.tagSpawnedEntity(wolf, Keys.MOB_PLAYER_SUMMON);
        tagSpawnedMob.tagSpawnedEntity(player, Keys.MOB_PLAYER_SUMMON);
        
        wolf.setInvisible(true);
        wolf.setInvulnerable(true);
        wolf.setGlowing(true);
        wolf.setOwner(player);
    }

    public static void killCerberus(Player player){
        player.getLocation().getWorld().getNearbyEntities(player.getLocation(), 20, 20, 20).forEach(entity -> {
            if (entity instanceof Wolf) {
                Wolf wolf = (Wolf) entity;

                if (wolf.getOwner() != null && wolf.getOwner().equals(player) && 
                    wolf.getPersistentDataContainer().has(Keys.MOB_PLAYER_SUMMON, PersistentDataType.BOOLEAN)) {
    
                    wolf.getWorld().spawnParticle(Particle.SOUL, wolf.getLocation(), 20, 1, 1, 1);
                    
                    player.getPersistentDataContainer().remove(Keys.MOB_PLAYER_SUMMON);
                    wolf.remove();
                }
            }
        });
    }

}
