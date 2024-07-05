package wentuziak.szoplugin;

import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpecialFood {


    public static void effectFoodFunc(Player player, Material consumedMaterial){
        String consumedItem = consumedMaterial.toString();
        List<String> implementedFood = Collections.singletonList("GLOW_BERRIES");


        if (!implementedFood.contains(consumedItem)) {
            return;
        }
        
        if (consumedItem.equals("GLOW_BERRIES")) {
            LogicHolder.givePotionEffect(player, "GLOWING", 400, 0);
            LogicHolder.givePotionEffect(player, "NIGHT_VISION", 400, 0);
        }
    }

}
