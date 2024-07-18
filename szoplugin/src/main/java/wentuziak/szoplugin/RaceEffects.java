package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RaceEffects {

    static BukkitTask dwarfSwimTask;
    static BukkitTask caraGlideTask;

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

    public static void dwarfCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.HONEY_BOTTLE && offHandMaterial == Material.GOLDEN_APPLE) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createDwarfHoney());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.SPYGLASS && offHandMaterial == Material.REDSTONE_TORCH) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createMarkingSpyglass());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.CLOCK && itemInOffHand.isSimilar(CreateCustomItem.createMechanicalParts())) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createLuckyClock());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if ((mainHandMaterial == Material.GUNPOWDER && itemInMainHand.getAmount() >= 8) && offHandMaterial == Material.STRING){
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createGrenade());
            
            LogicHolder.removeItem(player, itemInOffHand);
            for(int i = 0; i < 8; i++){
                LogicHolder.removeItem(player, itemInMainHand);
            }
            return;
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
    public static void witchCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.SUSPICIOUS_STEW && offHandMaterial == Material.NETHER_WART) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createWitchSoup());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.FERMENTED_SPIDER_EYE && offHandMaterial == Material.ARROW && itemInOffHand.getAmount() >= 4) {
            for(int i = 0; i < 4; i++){
                player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createCursedArrow());
                LogicHolder.removeItem(player, itemInOffHand);
            }
            player.sendMessage("HERE");

            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.GHAST_TEAR && offHandMaterial == Material.GLISTERING_MELON_SLICE) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createSuperHealingPot());
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }else if (mainHandMaterial == Material.PUFFERFISH && offHandMaterial == Material.FERMENTED_SPIDER_EYE) {
            player.getWorld().dropItemNaturally(player.getLocation(), CreateCustomItem.createToxicPot());
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
    }

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
        if (!TaskManager.isTaskRunning(player, "caraGlide")) {            
            final Player finalPlayer = player;
            caraGlideTask = new BukkitRunnable() {
                @Override
                public void run(){
                    if (!LogicHolder.isPlayerAboveGround(finalPlayer, 0.1)) {
                        stopCaraGlideTask(finalPlayer);
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
            LogicHolder.givePotionEffect(player, "SATURATION", 20, 0);
        }else if (consumedItem.equals("COOKED_SALMON")) {
            LogicHolder.givePotionEffect(player, "SATURATION", 3, 0);
        }else if (consumedItem.equals("COOKED_COD")) {
            LogicHolder.givePotionEffect(player, "SATURATION", 3, 0);
        }else if (consumedItem.equals("SPIDER_EYE")) {
            LogicHolder.givePotionEffect(player, "SPEED", 20 * 30, 0);
            LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * 30, 0);
        }
    }

    public static void mewchantCraftingEvent(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand ){
        Material mainHandMaterial = itemInMainHand.getType();
        Material offHandMaterial = itemInOffHand.getType();
        Location dropLocation = player.getLocation();
        if (mainHandMaterial == Material.CLOCK && itemInOffHand.isSimilar(CreateCustomItem.createMechanicalParts())) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createLuckyClock());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
        else if (mainHandMaterial == Material.SPYGLASS && offHandMaterial == Material.REDSTONE_TORCH) {
            player.getWorld().dropItem(dropLocation, CreateCustomItem.createMarkingSpyglass());
            
            LogicHolder.removeItem(player, itemInOffHand);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
    } 
}
