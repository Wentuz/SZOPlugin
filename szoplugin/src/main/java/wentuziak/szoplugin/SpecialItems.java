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

        String itemUsed;
        boolean mainHandUsed;

        Collections.addAll(implementedItemList, itemsToAdd);


        if (implementedItemList.contains(usedMainItem)) {
            itemUsed = usedMainItem.toString();
            mainHandUsed = true;
        }else if (implementedItemList.contains(usedOffItem)) {
            itemUsed = usedOffItem.toString();
            mainHandUsed = false;
        }else{
            return;
        }
        switch (itemUsed) {
            case "PHANTOM_MEMBRANE":
                
                break;
            case "FIRE_CHARGE":
                LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20, 3);
                player.getWorld().createExplosion(player.getLocation(), 4F, false, false);

                if (getMainHandMaterial != Material.FIRE_CHARGE) {
                    LogicHolder.removeItem(player, itemInOffHand);
                }else{
                    LogicHolder.removeItem(player, itemInMainHand);
                }


                break;
            case "MAGMA_CREAM":
            default:
                break;
        }
    }

}
