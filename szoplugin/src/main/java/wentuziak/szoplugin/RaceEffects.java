package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class RaceEffects {
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
        }
    }
}
