package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpecialFood {


    @SuppressWarnings("unchecked")
    public static void effectFoodFunc(Player player, Material consumedMaterial)
    {
        String consumedItem = consumedMaterial.toString();
        List implementedFood = new ArrayList<>();
        implementedFood.add("GLOW_BERRIES");
        boolean isImplementedFood = implementedFood.contains(consumedItem);

        System.out.println(consumedItem);

        if (!(isImplementedFood)) {
            return;
        }
        
        if (consumedItem == "GLOW_BERRIES") {
            LogicHolder.givePotionEffect(player, "GLOWING", 400, 0);
            LogicHolder.givePotionEffect(player, "NIGHT_VISION", 400, 0);
            return;
        }
    }

}
