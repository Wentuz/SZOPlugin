package wentuziak.szoplugin;


import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class UpdateAttributes {
    public static void attributeManager(Player player, boolean toRemove, String raceName){
        if (toRemove) {
            modifyMovementSpeed(player, 0.1);
            modifyHealthPoints(player, 20);
            modifyScale(player, 1);
            modifyMineSpeed(player, 1);
            modifyAttackDamage(player, 1);
            modifyFireTime(player, 1);
            modifyGravity(player, 0.08);
            return;
        }
        if (raceName.equals("RACE_DWARF")) {
            modifyMovementSpeed(player, 0.08);
            modifyHealthPoints(player, 24);
            modifyScale(player, 0.8);
            modifyMineSpeed(player, 1.2);
            return;
        }
        if (raceName.equals("RACE_CELESTIAL")) {
            modifyHealthPoints(player, 40);
            modifyAttackDamage(player, 4);
            modifyFireTime(player, 4);
            modifyGravity(player, 0.04);
            modifyScale(player, 1.15);
        }
        
    }

    public static void modifyMovementSpeed(Player player, double value){
        AttributeInstance speedAttribute = player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED);
        speedAttribute.setBaseValue(value);
    }
    public static void modifyHealthPoints(Player player, double value){
        AttributeInstance healthAttribute = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        healthAttribute.setBaseValue(value);
    }
    public static void modifyScale(Player player, double value){
        AttributeInstance scaleAttribute = player.getAttribute(Attribute.GENERIC_SCALE);
        scaleAttribute.setBaseValue(value);
    }
    public static void modifyMineSpeed(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.PLAYER_BLOCK_BREAK_SPEED);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyAttackDamage(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyFireTime(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.GENERIC_BURNING_TIME);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyGravity(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.GENERIC_GRAVITY);
        mineSpeedAttribute.setBaseValue(value);
    }
}
