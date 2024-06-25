package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

public class SpecialItems {

    @SuppressWarnings("unchecked")
    public static void simpleItemFunc(Player player, Material getMainHandMaterial, Material getOffHandMaterial)
    {

        String usedMainItem = getMainHandMaterial.toString();
        String usedOffItem = getOffHandMaterial.toString();
        @SuppressWarnings("rawtypes")
        List implementedItemList = new ArrayList<>();
        String[] itemsToAdd = {
            "PHANTOM_MEMBRANE", "FIRE_CHARGE", "MAGMA_CREAM"
        };

        Collections.addAll(implementedItemList, itemsToAdd);


        if (implementedItemList.contains(usedMainItem)) {
            System.out.println("Main hand" + usedMainItem);
        }else if (implementedItemList.contains(usedOffItem)) {
            System.out.println("Off hand" + usedOffItem);
        }else{
            return;
        }

    }

}
