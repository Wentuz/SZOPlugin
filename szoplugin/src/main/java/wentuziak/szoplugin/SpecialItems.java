package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpecialItems {

    @SuppressWarnings("unchecked")
    public static void simpleItemFunc(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand)
    {
        Material getMainHandMaterial = itemInMainHand.getType();
        Material getOffHandMaterial = itemInOffHand.getType();
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
                LogicHolder.givePotionEffect(player, "SLOW_FALLING", 200, 0);
                player.playSound(player.getLocation(), Sound.ENTITY_PHANTOM_HURT, 10, 10);
                
                break;

            case "FIRE_CHARGE":
                LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20, 3);
                player.getWorld().createExplosion(player.getLocation(), 4F, false, false);
                break;

            case "MAGMA_CREAM":
                LogicHolder.givePotionEffect(player, "FIRE_RESISTANCE", 400, 0);
                LogicHolder.givePotionEffect(player, "POISON", 200, 1);

                player.playSound(player.getLocation(), Sound.ENTITY_MAGMA_CUBE_SQUISH, 10, 10);
                break;

            default:
                break;
        }
        if (mainHandUsed != true) {
            System.out.println(mainHandUsed);
            LogicHolder.removeItem(player, itemInOffHand);
            return;
        }else{
            System.out.println(mainHandUsed);
            LogicHolder.removeItem(player, itemInMainHand);
            return;
        }
    }

}
