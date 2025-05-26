package wentuziak.szoplugin.races;


import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class UpdateAttributes {
    static double getBaseValues(int attributeID){
        double [] baseAttributes = new double[15];
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

        return baseAttributes[attributeID];
    }


    public static void attributeManager(Player player, boolean toRemove, String raceName){
        if (toRemove) { // reset to base values
            modifyMovementSpeed(player, getBaseValues(0));
            modifyHealthPoints(player, getBaseValues(1));
            modifyScale(player, getBaseValues(2));
            modifyMineSpeed(player, getBaseValues(3));
            modifyAttackDamage(player, getBaseValues(4));
            modifyFireTime(player, getBaseValues(5));
            modifyGravity(player, getBaseValues(6));
            modifyFallDmgMultiplier(player, getBaseValues(7));
            modifyKnockBack(player, getBaseValues(8));
            modifyOxygenBonus(player, getBaseValues(9));
            modifyAttackSpeed(player, getBaseValues(10));
            modifySafeFallRange(player, getBaseValues(11));
            modifyReach(player, getBaseValues(12), getBaseValues(13));
            modifyWaterSpeed(player, getBaseValues(14));
            return;
        }
//        double playerMovementSpeed = getBaseValues(0);
//        double playerHP = getBaseValues(1);
//        double playerScale = getBaseValues(2);
//        double playerMiningSpeed = getBaseValues(3);
//        double playerAttackDamage = getBaseValues(4);
//        double playerFireTime = getBaseValues(5);
//        double playerGravity = getBaseValues(6);
//        double playerFallDamage = getBaseValues(7);
//        double playerKnockback = getBaseValues(8);
//        double playerOxygenBonus = getBaseValues(9);
//        double playerAttackSpeed = getBaseValues(10);
//        double playerSafeFallRange = getBaseValues(11);
//        double playerBlockReach = getBaseValues(12);
//        double playerEntityReach = getBaseValues(13);
//        double playerWaterSpeed = getBaseValues(14);
        
        // get current player attributes
        double playerMovementSpeed = getBaseValues(0);
        double playerHP = getBaseValues(1);
        double playerScale = getBaseValues(2);
        double playerMiningSpeed = getBaseValues(3);
        double playerAttackDamage = getBaseValues(4);
        double playerFireTime = getBaseValues(5);
        double playerGravity = getBaseValues(6);
        double playerFallDamage = getBaseValues(7);
        double playerKnockback = getBaseValues(8);
        double playerOxygenBonus = getBaseValues(9);
        double playerAttackSpeed = getBaseValues(10);
        double playerSafeFallRange = getBaseValues(11);
        double playerBlockReach = getBaseValues(12);
        double playerEntityReach = getBaseValues(13);
        double playerWaterSpeed = getBaseValues(14);
        
        //get player keys through loop
        
        
        
//        AttributeInstance test = player.getAttribute(Attribute.ATTACK_DAMAGE);
//        double teee = test.getBaseValue();
        
        //add values
        
        //modify attributes
        modifyMovementSpeed(player, playerMovementSpeed);
        modifyHealthPoints(player, playerHP);
        modifyScale(player, playerScale);
        modifyMineSpeed(player, playerMiningSpeed);
        modifyAttackDamage(player, playerAttackDamage);
        modifyFireTime(player, playerFireTime);
        modifyGravity(player, playerGravity);
        modifyFallDmgMultiplier(player, playerFallDamage);
        modifyKnockBack(player, playerKnockback);
        modifyOxygenBonus(player, playerOxygenBonus);
        modifyAttackSpeed(player, playerAttackSpeed);
        modifySafeFallRange(player, playerSafeFallRange);
        modifyReach(player, playerBlockReach, playerEntityReach);
        modifyWaterSpeed(player, playerWaterSpeed);
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