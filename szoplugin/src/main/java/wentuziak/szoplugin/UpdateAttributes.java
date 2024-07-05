package wentuziak.szoplugin;


import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class UpdateAttributes {
    public static void attributeManager(Player player, boolean toRemove, String raceName){
        if (raceName.equals("RACE_DWARF")) {
            if (toRemove) {
                modifyMovementSpeed(player, 0.06);
            }else{
                modifyMovementSpeed(player, 0.05);
            }
        }
    }

    public static void modifyMovementSpeed(Player player, double value){
        AttributeInstance speedAttribute = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        speedAttribute.setBaseValue(value);
    }
}
