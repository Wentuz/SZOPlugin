package wentuziak.szoplugin.races;


import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class UpdateAttributes {
    static double[] getBaseValues(){
        double [] baseAttributes = new double[13];
        baseAttributes[0] = 0.1;    //Movement speed
        baseAttributes[1] = 20;     //Health points
        baseAttributes[2] = 1;      //Scale
        baseAttributes[3] = 1;      //Mining speed
        baseAttributes[4] = 1;      //Attack damage
        baseAttributes[5] = 1;      //Fire time
        baseAttributes[6] = 0.08;   //Gravity
        baseAttributes[7] = 1;      //Fall damage multiplier
        baseAttributes[8] = 0;      //Knockback
        baseAttributes[9] = 0;      //Oxygen bonus
        baseAttributes[10] = 4;     //Attack speed
        baseAttributes[11] = 3;     //Safe fall range
        baseAttributes[12] = 4.5;   //Block reach
        baseAttributes[13] = 3;     //Entity reach
        baseAttributes[14] = 0;     //WaterSpeed

        return baseAttributes;
    }


    public static void attributeManager(Player player, boolean toRemove, String raceName){
        if (toRemove) {
            modifyMovementSpeed(player, 0.1);
            modifyHealthPoints(player, 20);
            modifyScale(player, 1);
            modifyMineSpeed(player, 1);
            modifyAttackDamage(player, 1);
            modifyFireTime(player, 1);
            modifyGravity(player, 0.08);
            modifyFallDmgMultiplier(player, 1);
            modifyKnockBack(player, 0);
            modifyOxygenBonus(player, 0);
            modifyAttackSpeed(player, 4);
            modifySafeFallRange(player, 3);
            modifyReach(player, 4.5, 3);
            modifyWaterSpeed(player, 0);
            return;
        }
        if (raceName.equals("RACE_DWARF")) {
            modifyMovementSpeed(player, 0.08);
            modifyHealthPoints(player, 24);
            modifyScale(player, 0.9);
            modifyMineSpeed(player, 1.2);
        }
        if (raceName.equals("RACE_CELESTIAL")) {
            modifyHealthPoints(player, 40);
            modifyAttackDamage(player, 4);
            modifyAttackSpeed(player, 4.2);
            modifySafeFallRange(player, 5);
            modifyFireTime(player, 4);
            modifyGravity(player, 0.04);
            modifyScale(player, 1.15);
        }
        if (raceName.equals("RACE_MISKARU")) {
            modifyHealthPoints(player, 26);
            modifyAttackDamage(player, 4);
            modifyScale(player, 1.05);
            modifyFallDmgMultiplier(player, 2.5);
            modifyKnockBack(player, 0.2);
            modifyOxygenBonus(player, 2);
            modifyAttackSpeed(player, 3.7);
        }
        if (raceName.equals("RACE_CARA")) {
            modifyScale(player, 0.75);
            modifyGravity(player, 0.07);
            modifyHealthPoints(player, 16);
            modifyMovementSpeed(player, 0.11);
            modifySafeFallRange(player, 8);
        }
        if (raceName.equals("RACE_MEWCHANT")) {
            modifyGravity(player, 0.07);
            modifyFallDmgMultiplier(player, 0.8);
            modifyHealthPoints(player, 18);
            modifyMovementSpeed(player, 0.12);
            modifyWaterSpeed(player, 0.5);
            modifySafeFallRange(player, 6);
        }
        if (raceName.equals("RACE_FOSSIL")) {
            modifyReach(player, 5.5, 4);
            modifyAttackDamage(player, 3);
            modifyScale(player, 1.25);
        }
        if (raceName.equals("RACE_ZEPHYR")) {
            modifyFireTime(player, 3);
            modifyHealthPoints(player, 24);
            modifyMovementSpeed(player, 0.1);
        }
        if (raceName.equals("RACE_ELF")) {
            modifyFallDmgMultiplier(player, 0.7);
            modifyHealthPoints(player, 18);
            modifyMovementSpeed(player, 0.12);
        }
        if (raceName.equals("RACE_HOBBIT")) {
            modifyMovementSpeed(player, 0.085);
            modifyHealthPoints(player, 16);
            modifyScale(player, 0.85);
            modifyAttackSpeed(player, 4.25);
            modifySafeFallRange(player, 5);
        }
    }

    public static void modifyMovementSpeed(Player player, double value){
        AttributeInstance speedAttribute = player.getAttribute(Attribute.MOVEMENT_SPEED);
        speedAttribute.setBaseValue(value);
    }
    public static void modifyHealthPoints(Player player, double value){
        AttributeInstance healthAttribute = player.getAttribute(Attribute.MAX_HEALTH);
        healthAttribute.setBaseValue(value);
    }
    public static void modifyScale(Player player, double value){
        AttributeInstance scaleAttribute = player.getAttribute(Attribute.SCALE);
        scaleAttribute.setBaseValue(value);
    }
    public static void modifyMineSpeed(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.BLOCK_BREAK_SPEED);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyAttackDamage(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.ATTACK_DAMAGE);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyFireTime(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.BURNING_TIME);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyGravity(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.GRAVITY);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyFallDmgMultiplier(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.FALL_DAMAGE_MULTIPLIER);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyKnockBack(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.KNOCKBACK_RESISTANCE);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyOxygenBonus(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.OXYGEN_BONUS);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifyAttackSpeed(Player player, double value){
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.ATTACK_SPEED);
        mineSpeedAttribute.setBaseValue(value);
    }
    public static void modifySafeFallRange(Player player, double value){
        AttributeInstance safeFallRangeAttribute = player.getAttribute(Attribute.SAFE_FALL_DISTANCE);
        safeFallRangeAttribute.setBaseValue(value);
    }
    public static void modifyWaterSpeed(Player player, double value){
        AttributeInstance waterSpeedAttribute = player.getAttribute(Attribute.WATER_MOVEMENT_EFFICIENCY);
        waterSpeedAttribute.setBaseValue(value);
    }
    public static void modifyReach(Player player, double valueBlockReach, double valueEntityReach){
        AttributeInstance blockReachAttribute = player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE);
        AttributeInstance entityReachAttribute = player.getAttribute(Attribute.ENTITY_INTERACTION_RANGE);
        blockReachAttribute.setBaseValue(valueBlockReach);
        entityReachAttribute.setBaseValue(valueEntityReach);
    }
}