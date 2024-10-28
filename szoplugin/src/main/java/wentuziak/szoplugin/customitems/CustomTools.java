package wentuziak.szoplugin.customitems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.WindCharge;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.RayTraceResult;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.TaskManager;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class CustomTools {

    private static BukkitTask hastyToolTask;
    private static BukkitTask effectRaisedShieldTask;
    
    public static void hastyToolEffect(Player player) {
        if (!TaskManager.isTaskRunning(player, "hastyTools")) {
            final Player finalPlayer = player;
            hastyToolTask = new BukkitRunnable() {
                @Override
                public void run(){                       
                    LogicHolder.givePotionEffect(finalPlayer, "FAST_DIGGING", 20*30, 0);
                }
            }.runTaskTimer(SzoPlugin.getInstance(), 1, 20*15);
            TaskManager.addTask(player, "hastyTools", hastyToolTask);
        }
    }

    public static void stopHastyToolTask(Player player) {
        TaskManager.stopTask(player, "hastyTools");
    }

    public static void treasureFishingRodEffect(int chanceForCrit, Player player, Projectile projectile, int playerLuck, String typeOfLoot){
        if (LogicHolder.critRoll(chanceForCrit)) {
            if (projectile instanceof org.bukkit.entity.FishHook) {
                org.bukkit.entity.FishHook fishHook = (org.bukkit.entity.FishHook) projectile;
                Location bobberLocation = fishHook.getLocation();
                Item item = LogicHolder.rollTreasure(playerLuck, bobberLocation, typeOfLoot);

                Location playerLocation = player.getLocation();
                Vector direction = playerLocation.toVector().subtract(bobberLocation.toVector()).normalize();
                direction.setY(direction.getY() + 0.5);
                item.setVelocity(direction.multiply(0.6));
            }
        }
    }

    public static void dwarfPickaxeEffect(int chanceForCrit, Player player, int playerLuck, Block brokenBlock, String typeOfLoot){   
        if (brokenBlock.getType() == Material.STONE || brokenBlock.getType() == Material.DEEPSLATE 
        || brokenBlock.getType() == Material.GRANITE || brokenBlock.getType() == Material.TUFF
        || brokenBlock.getType() == Material.DIORITE || brokenBlock.getType() == Material.ANDESITE
        || brokenBlock.getType() == Material.DIAMOND_ORE || brokenBlock.getType() == Material.DEEPSLATE_DIAMOND_ORE
        || brokenBlock.getType() == Material.IRON_ORE || brokenBlock.getType() == Material.DEEPSLATE_IRON_ORE
        || brokenBlock.getType() == Material.COPPER_ORE || brokenBlock.getType() == Material.DEEPSLATE_COPPER_ORE
        || brokenBlock.getType() == Material.GOLD_ORE || brokenBlock.getType() == Material.DEEPSLATE_GOLD_ORE
        || brokenBlock.getType() == Material.REDSTONE_ORE || brokenBlock.getType() == Material.DEEPSLATE_REDSTONE_ORE
        ) {
            if (brokenBlock.getType() == Material.DEEPSLATE) {
                chanceForCrit = chanceForCrit * 2;
            }
            if (brokenBlock.getType() == Material.DEEPSLATE_DIAMOND_ORE || brokenBlock.getType() == Material.DIAMOND_ORE) {
                chanceForCrit = chanceForCrit * 4;
                playerLuck = playerLuck + 3;
            }
            if (LogicHolder.critRoll(chanceForCrit)) {
                Location blockLocation = brokenBlock.getLocation();
                LogicHolder.rollTreasure(playerLuck, blockLocation, typeOfLoot);
            }
        }
    }

    public static void richAxeEffect(int playerLuck, Block brokenBlock){
        if (LogicHolder.critRoll((playerLuck + 2) * 4)) {
            Material blockMaterial = brokenBlock.getType();
            Location blockLocation = brokenBlock.getLocation();
            ItemStack item = new ItemStack(Material.AIR);

            if (blockMaterial == Material.OAK_LOG) {
                item = new ItemStack(Material.OAK_LOG);
            }
            else if (blockMaterial == Material.BIRCH_LOG) {
                item = new ItemStack(Material.BIRCH_LOG);
            }
            else if (blockMaterial == Material.SPRUCE_LOG) {
                item = new ItemStack(Material.SPRUCE_LOG);
            }
            else if (blockMaterial == Material.JUNGLE_LOG) {
                item = new ItemStack(Material.JUNGLE_LOG);
            }
            else if (blockMaterial == Material.ACACIA_LOG) {
                item = new ItemStack(Material.ACACIA_LOG);
            }
            else if (blockMaterial == Material.CHERRY_LOG) {
                item = new ItemStack(Material.CHERRY_LOG);
            }
            else if (blockMaterial == Material.MANGROVE_LOG) {
                item = new ItemStack(Material.MANGROVE_LOG);
            }
            else if (blockMaterial == Material.DARK_OAK_LOG) {
                item = new ItemStack(Material.DARK_OAK_LOG);
            }
            else if (blockMaterial == Material.CRIMSON_STEM) {
                item = new ItemStack(Material.CRIMSON_STEM);
            }
            else if (blockMaterial == Material.WARPED_STEM) {
                item = new ItemStack(Material.WARPED_STEM);
            }
            blockLocation.getWorld().dropItemNaturally(blockLocation, item);
        }
    }
    public static void richShovelEffect(int playerLuck, Block brokenBlock){
        if (LogicHolder.critRoll((playerLuck + 2) * 40)) {
            Material blockMaterial = brokenBlock.getType();
            Location blockLocation = brokenBlock.getLocation();
            ItemStack item = new ItemStack(Material.AIR);

            if (blockMaterial == Material.SAND) {
                if (LogicHolder.critRoll((playerLuck + 2) * 4)) {
                    item = new ItemStack(Material.SUSPICIOUS_SAND);
                }else{
                    item = new ItemStack(Material.SAND);
                }
            }
            else if (blockMaterial == Material.GRAVEL) {
                if (LogicHolder.critRoll((playerLuck + 2) * 4)) {
                    item = new ItemStack(Material.SUSPICIOUS_GRAVEL);
                }else{
                    item = new ItemStack(Material.GRAVEL);
                }
            }
            else if (blockMaterial == Material.RED_SAND) {
                item = new ItemStack(Material.RED_SAND);
            }
    
            blockLocation.getWorld().dropItemNaturally(blockLocation, item);
        }
    }

    public static void effectShieldBlock(Player player, int whatShield){  
        if (whatShield == 3) {
            LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 20, 1);
        } else if (whatShield == 2) {
            LogicHolder.givePotionEffect(player, "INCREASE_DAMAGE", 20 * 10, 1);
            LogicHolder.givePotionEffect(player, "SPEED", 20 * 10, 0);
        }

    }

    public static void windBlastShieldEffect(Player player){
        for (Entity entity : player.getNearbyEntities(5, 5, 5)) {
            WindCharge windCharge = (WindCharge) entity.getWorld().spawnEntity(entity.getLocation(), EntityType.WIND_CHARGE);
            windCharge.explode();
        }
    }

    public static void markingSpyglassEffect(Player player, boolean mainHandUsed){
        RayTraceResult result = LogicHolder.rayTrace(150, 0.5f, player);

        if (result != null && result.getHitEntity() != null) {
            LivingEntity hitEntity = (LivingEntity) result.getHitEntity();
            if (mainHandUsed) {
                LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 20 * 10, 0);
                LogicHolder.givePotionEffect(hitEntity, "DARKNESS", 20 * 10, 0);
                hitEntity.getWorld().spawnParticle(Particle.ELECTRIC_SPARK, hitEntity.getLocation(), 5);
                hitEntity.getWorld().playSound(hitEntity.getLocation(), Sound.ENTITY_WARDEN_AMBIENT, 1, 1);
            }else{
                LogicHolder.givePotionEffect(hitEntity, "GLOWING", 20 * 20, 0);
            }
        }
    }
}
