package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class SpecialItems {

    public static void simpleItemEffect(Player player, ItemStack itemInMainHand, ItemStack itemInOffHand){
        Material getMainHandMaterial = itemInMainHand.getType();
        Material getOffHandMaterial = itemInOffHand.getType();
        String mainHandItem = getMainHandMaterial.toString();
        String offHandItem = getOffHandMaterial.toString();

        Set<String> implementedItems = new HashSet<>(Arrays.asList(
                "PHANTOM_MEMBRANE", "FIRE_CHARGE", "MAGMA_CREAM", "BONE"
        ));

        String itemUsed = null;
        boolean mainHandUsed = false;

        if (implementedItems.contains(mainHandItem)) {
            itemUsed = mainHandItem;
            mainHandUsed = true;
        }else if (implementedItems.contains(offHandItem)) {
            itemUsed = offHandItem;
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

            case "BONE":
                if (player.getPersistentDataContainer().has(Keys.RACE_FOSSIL)) {
                    LogicHolder.givePotionEffect(player, "REGENERATION", 20*60, 0);
                    LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE",  20*60, 0);
                    player.playSound(player.getLocation(), Sound.ENTITY_SKELETON_HURT, 1, 1);                    
                }else{
                    return;
                }
                break;

            default:
                break;
        }
        if (mainHandUsed) {
            LogicHolder.removeItem(player, itemInMainHand);
        }else{
            LogicHolder.removeItem(player, itemInOffHand);
        }
    }

}
