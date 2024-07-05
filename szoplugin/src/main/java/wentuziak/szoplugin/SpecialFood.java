package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpecialFood {


    public static void effectFoodFunc(Player player, ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "GLOW_BERRIES"
        ));


        if (!implementedFood.contains(consumedItem)) {
            return;
        }
        
        if (consumedItem.equals("GLOW_BERRIES")) {
            LogicHolder.givePotionEffect(player, "GLOWING", 400, 0);
            LogicHolder.givePotionEffect(player, "NIGHT_VISION", 400, 0);
        }
    }
}
