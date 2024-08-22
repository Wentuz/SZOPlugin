package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class SpecialFood {


    public static void effectFoodFunc(Player player, ItemStack consumedStack){
        Material consumedMaterial = consumedStack.getType();
        String consumedItem = consumedMaterial.toString();

        Set<String> implementedFood = new HashSet<>(Arrays.asList(
            "GLOW_BERRIES", "HONEY_BOTTLE", "MUSHROOM_STEW"
        ));


        if (!implementedFood.contains(consumedItem)) {
            return;
        }
        
        if (consumedItem.equals("GLOW_BERRIES")) {
            LogicHolder.givePotionEffect(player, "GLOWING", 20 * 60 * 3, 0);
            LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * 60 & 3, 0);
        }

        //
        //      META ITEMS
        //
        if (consumedStack.getItemMeta() != null) {
            PersistentDataContainer foodContainer = consumedStack.getItemMeta().getPersistentDataContainer();

            if (consumedItem.equals("HONEY_BOTTLE") && foodContainer.has(Keys.CUSTOM_DWARF_HONEY, PersistentDataType.BYTE)) {
                LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 120, 1);
                LogicHolder.givePotionEffect(player, "DAMAGE_RESISTANCE", 20 * 120, 1);
                LogicHolder.givePotionEffect(player, "CONFUSION", 20 * 60, 0);
                LogicHolder.givePotionEffect(player, "INCREASE_DAMAGE", 20 * 120, 1);
                LogicHolder.givePotionEffect(player, "SPEED", 20 * 120, 0);
                LogicHolder.givePotionEffect(player, "FAST_DIGGING", 20 * 120, 1);
                return;
            }
            if (consumedItem.equals("MUSHROOM_STEW") && foodContainer.has(Keys.CUSTOM_WITCH_SOUP, PersistentDataType.BYTE)) {
                
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "POISON", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "REGENERATION", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "CONFUSION", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "SPEED", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "INCREASE_DAMAGE", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "FAST_DIGGING", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "GLOWING", 20 * 60, 0);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "NIGHT_VISION", 20 * 60, 0);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "BAD_OMEN", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "ABSORPTION", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "LEVITATION", 20 * 60, 3);
                }
                if (LogicHolder.critRoll(20)) {
                    LogicHolder.givePotionEffect(player, "WATER_BREATHING", 20 * 60, 0);
                }
                if (LogicHolder.critRoll(1)) {
                    LogicHolder.givePotionEffect(player, "WITHER", 20 * 60, 9);
                }
                if (LogicHolder.critRoll(10)) {
                    LogicHolder.givePotionEffect(player, "HERO_OF_THE_VILLAGE", 20 * 60, 3);
                }
                return;
            }
        }
    }
}
