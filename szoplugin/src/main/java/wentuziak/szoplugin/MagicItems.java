package wentuziak.szoplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.CaveSpider;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

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

    public static void spiritLeech(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 1, 1);
        LogicHolder.throwSnowball(player, playerContainer);
    }

    public static void spiritLeechEffect(LivingEntity targetEntity){
        if (targetEntity instanceof Player) {
            ((Player) targetEntity).playSound(targetEntity.getLocation(), Sound.ENTITY_ENDERMITE_DEATH, 1, 1);
        }
        LogicHolder.givePotionEffect(targetEntity, "WITHER", 20*10, 1);
        LogicHolder.givePotionEffect(targetEntity, "WEAKNESS", 20*10, 1);
        LogicHolder.givePotionEffect(targetEntity, "DARKNESS", 20*10,0);
    }

    public static void spiderYeet(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_SPIDER_AMBIENT, 1, 1);
        LogicHolder.throwSnowball(player, playerContainer);
    }

    public static void spiderYeetEffect(Location location){
        for(int i = 0; i < 7; i++){            
            if (LogicHolder.critRoll(66)) {
                CaveSpider caveSpider = (CaveSpider) location.getWorld().spawnEntity(location.add(0, 1, 0), EntityType.CAVE_SPIDER);
            }
        }
        CaveSpider caveSpider = (CaveSpider) location.getWorld().spawnEntity(location.add(0, 1, 0), EntityType.CAVE_SPIDER);
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
                    if (TaskManager.isTaskRunning(player, "mermaidTail") && finalPlayer.getPersistentDataContainer().has(Keys.RACE_FOSSIL)) {            
                        LogicHolder.givePotionEffect(finalPlayer, "DOLPHINS_GRACE", 20 * 10, 4);
                        LogicHolder.givePotionEffect(finalPlayer, "CONDUIT_POWER", 20 * 10, 2);
                        LogicHolder.givePotionEffect(finalPlayer, "STRENGTH", 20 * 10, 1);
                    }
                    else if (TaskManager.isTaskRunning(player, "mermaidTail")) {            
                    LogicHolder.givePotionEffect(finalPlayer, "DOLPHINS_GRACE", 20 * 10, 3);
                    LogicHolder.givePotionEffect(finalPlayer, "CONDUIT_POWER", 20 * 10, 2);
                    LogicHolder.givePotionEffect(finalPlayer, "STRENGTH", 20 * 10, 0);

                    }else if(finalPlayer.getPersistentDataContainer().has(Keys.RACE_FOSSIL)){
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


}
