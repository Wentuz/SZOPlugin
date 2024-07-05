package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class RaceEffects {

    static BukkitTask dwarfSwimTask;


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
        }
    }

    public static void dwarfSwimEvent(Player player){   
        final Player finalPlayer = player;
        dwarfSwimTask = new BukkitRunnable() {
            @Override
            public void run(){
                if (!LogicHolder.isPlayerInWater(finalPlayer)) {
                    stopDwarfSwimTask();
                    return;
                }
                LogicHolder.givePotionEffect(finalPlayer, "SLOW", 20 * 4, 2);
            }
        }.runTaskTimer(SzoPlugin.getInstance(), 20, 20);

    }


    public static void stopDwarfSwimTask() {
        if (dwarfSwimTask != null && !dwarfSwimTask.isCancelled()) {
            dwarfSwimTask.cancel();
            dwarfSwimTask = null;
        }
    }

}
