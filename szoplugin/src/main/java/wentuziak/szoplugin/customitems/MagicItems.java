package wentuziak.szoplugin.customitems;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Bogged;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.TaskManager;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class MagicItems {
    static BukkitTask ancientShellTask;


    public static void teleportSpell(Player player){
        Block targetBlock = player.getTargetBlock(null, 50);

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
        LogicHolder.throwSnowball(player, playerContainer);
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
        LogicHolder.throwSnowball(player, playerContainer);
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
                    if(finalPlayer.getPersistentDataContainer().has(Keys.RACE_FOSSIL)){
                    LogicHolder.givePotionEffect(finalPlayer, "DOLPHINS_GRACE", 20 * 10, 1);
                    LogicHolder.givePotionEffect(finalPlayer, "CONDUIT_POWER", 20 * 10, 1); 
                    }else{
                    LogicHolder.givePotionEffect(finalPlayer, "DOLPHINS_GRACE", 20 * 10, 0);
                    LogicHolder.givePotionEffect(finalPlayer, "CONDUIT_POWER", 20 * 10, 0); 
                    }
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 20, 20 * 5);
            TaskManager.addTask(player, "ancientShell", ancientShellTask);
        }

    }


    public static void stopAncientShellTask(Player player) {
        TaskManager.stopTask(player, "ancientShell");
    }

    public static void windCharmEffect(Player player){
        LogicHolder.givePotionEffect(player, "SPEED", 20*60*5, 1);
        LogicHolder.givePotionEffect(player, "SLOW_FALLING", 20*60*5, 1);
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
                if (isBoosted) {
                    value = 4;
                }
                while (value >= 0) {
                    
                    Bogged skeleton = (Bogged) player.getWorld().spawnEntity(player.getLocation().add(0, 1, 0), EntityType.BOGGED);
                    LogicHolder.givePotionEffect(skeleton, "FIRE_RESISTANCE", 20 * 60, 0);

                    double speedMultiplier = 2;
    
                    Vector direction = player.getLocation().getDirection();
                    Vector velocity = direction.multiply(speedMultiplier);
                
                    skeleton.setVelocity(velocity);
                    
                    Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                        Player nearestPlayer = LogicHolder.findNearestPlayer(skeleton.getLocation());
                        if (nearestPlayer != null) {
                            skeleton.setTarget(nearestPlayer);
                        }
                    }, 20 * 2);
                    value--;
                }
                break;
            default:
                return;
        }
        LogicHolder.removeItem(player, itemInMainHand);
        LogicHolder.removeItem(player, itemInOffHand);
    }

}
