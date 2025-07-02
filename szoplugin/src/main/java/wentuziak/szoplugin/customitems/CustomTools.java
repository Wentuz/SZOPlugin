package wentuziak.szoplugin.customitems;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
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
    
    public static void hastyToolEffect(Player player, int effectStr) {
        if (!TaskManager.isTaskRunning(player, "hastyTools")) {
            final Player finalPlayer = player;
            hastyToolTask = new BukkitRunnable() {
                @Override
                public void run(){                       
                    LogicHolder.givePotionEffect(finalPlayer, "FAST_DIGGING", 20*30, effectStr);
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
                Item item = LogicHolder.rollTreasure(playerLuck/12, bobberLocation, typeOfLoot);

                Location playerLocation = player.getLocation();
                Vector direction = playerLocation.toVector().subtract(bobberLocation.toVector()).normalize();
                direction.setY(direction.getY() + 0.5);
                item.setVelocity(direction.multiply(0.6));
            }
        }
    }

    public static void dwarfPickaxeEffect(int chanceForCrit, Player player, int playerLuck, Block brokenBlock){ 
        if (!LogicHolder.critRoll(chanceForCrit * 2)) {
            return;
        }  
        Material material = Material.AIR;
        if (brokenBlock.getType() == Material.DIAMOND_ORE || brokenBlock.getType() == Material.DEEPSLATE_DIAMOND_ORE){
            material = Material.DIAMOND;
        }
        else if(brokenBlock.getType() == Material.IRON_ORE || brokenBlock.getType() == Material.DEEPSLATE_IRON_ORE){
            material = Material.RAW_IRON;
            playerLuck = playerLuck + 1;
        }
        else if(brokenBlock.getType() == Material.COPPER_ORE || brokenBlock.getType() == Material.DEEPSLATE_COPPER_ORE){
            material = Material.RAW_COPPER;
            playerLuck = playerLuck * 2 + 2;
        }
        else if(brokenBlock.getType() == Material.LAPIS_ORE || brokenBlock.getType() == Material.DEEPSLATE_LAPIS_ORE){
            material = Material.LAPIS_LAZULI;
            playerLuck = playerLuck * 2 + 1;
        }
        else if(brokenBlock.getType() == Material.COAL_ORE || brokenBlock.getType() == Material.DEEPSLATE_COAL_ORE){
            material = Material.COAL;
            playerLuck = playerLuck * 2 + 1;
        }
        else if(brokenBlock.getType() == Material.GOLD_ORE || brokenBlock.getType() == Material.DEEPSLATE_GOLD_ORE){
            material = Material.RAW_GOLD;
        }
        else if(brokenBlock.getType() == Material.REDSTONE_ORE || brokenBlock.getType() == Material.DEEPSLATE_REDSTONE_ORE){
            material = Material.REDSTONE;
            playerLuck = playerLuck * 2 + 1;

        }
        else{
            return;
        }
        int numberOfItems = (int)(Math.random() * playerLuck + 1);
        ItemStack loot = new ItemStack(material, numberOfItems);
        
        brokenBlock.getWorld().dropItemNaturally(brokenBlock.getLocation(), loot);
        return;
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
            else if (blockMaterial == Material.DARK_OAK_LOG) {
                item = new ItemStack(Material.DARK_OAK_LOG);
            }
            else if (blockMaterial == Material.PALE_OAK_LOG) {
                item = new ItemStack(Material.PALE_OAK_LOG);
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
            else if (blockMaterial == Material.DIRT) {
                if (LogicHolder.critRoll((playerLuck + 2) * 4)) {
                    item = new ItemStack(Material.ROOTED_DIRT);
                }else{
                    item = new ItemStack(Material.COARSE_DIRT);
                }
            }
            else if (blockMaterial == Material.RED_SAND) {
                item = new ItemStack(Material.RED_SAND);
            }
    
            blockLocation.getWorld().dropItemNaturally(blockLocation, item);
        }
    }

    public static void superHoeEffect(int playerLuck, Block brokenBlock){
        if (LogicHolder.critRoll((playerLuck + 1) * 20)) {
            Material blockMaterial = brokenBlock.getType();
            Location blockLocation = brokenBlock.getLocation();
            ItemStack item = new ItemStack(Material.AIR);


            if ((brokenBlock.getType() == Material.SHORT_GRASS || brokenBlock.getType() == Material.TALL_GRASS
                    || brokenBlock.getType() == Material.FERN || brokenBlock.getType() == Material.LARGE_FERN 
                    || brokenBlock.getType() == Material.BUSH || brokenBlock.getType() == Material.SHORT_DRY_GRASS
                    || brokenBlock.getType() == Material.TALL_DRY_GRASS)) {
                LogicHolder.rollTreasure(playerLuck, brokenBlock.getLocation(), "Plant");
                return;
            }
        
            if (blockMaterial == Material.WHEAT || blockMaterial == Material.CARROTS || 
            blockMaterial == Material.POTATOES || blockMaterial == Material.BEETROOTS) {
                Ageable ageable = (Ageable) brokenBlock.getBlockData();

                if (ageable.getAge() == ageable.getMaximumAge()) {                    
                    switch (blockMaterial) {
                        case WHEAT:
                            item = new ItemStack(Material.WHEAT);
                            break;
                        case CARROTS:
                            item = LogicHolder.critRoll((playerLuck + 2) * 4) ? 
                                   new ItemStack(Material.GOLDEN_CARROT) : new ItemStack(Material.CARROT);
                            break;
                        case POTATOES:
                            item = new ItemStack(Material.POTATO);
                            break;
                        case BEETROOTS:
                            item = new ItemStack(Material.BEETROOT);
                            break;
                        default:
                            return;
                    }
        
                    blockLocation.getWorld().dropItemNaturally(blockLocation, item);
                }
            }


            else if (blockMaterial == Material.OAK_LEAVES) {
                item = new ItemStack(Material.OAK_SAPLING);
                blockLocation.getWorld().dropItemNaturally(blockLocation, item);
                if (LogicHolder.critRoll((playerLuck + 2) * 2)) {
                    item = new ItemStack(Material.GOLDEN_APPLE);
                }
            }
            else if (blockMaterial == Material.BIRCH_LEAVES) {
                item = new ItemStack(Material.BIRCH_SAPLING);
            }
            else if (blockMaterial == Material.PALE_OAK_LEAVES) {
                item = new ItemStack(Material.PALE_OAK_SAPLING);
            }
            else if (blockMaterial == Material.DARK_OAK_LEAVES) {
                item = new ItemStack(Material.DARK_OAK_SAPLING);
            }
            else if (blockMaterial == Material.SPRUCE_LEAVES) {
                item = new ItemStack(Material.SPRUCE_SAPLING);
            }
            else if (blockMaterial == Material.ACACIA_LEAVES) {
                item = new ItemStack(Material.ACACIA_SAPLING);
            }
            else if (blockMaterial == Material.JUNGLE_LEAVES) {
                item = new ItemStack(Material.JUNGLE_SAPLING);
            }
            else if (blockMaterial == Material.MANGROVE_LEAVES) {
                item = new ItemStack(Material.MANGROVE_PROPAGULE);
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

	public static void battleHornEffect(Player player) {
        for (Entity entity : player.getNearbyEntities(15, 5, 15)) {
            LogicHolder.givePotionEffect((LivingEntity) entity, "RESISTANCE", 20 * 60, 0);
        }
        LogicHolder.givePotionEffect((LivingEntity) player, "RESISTANCE", 20 * 60, 0);
	}
}
