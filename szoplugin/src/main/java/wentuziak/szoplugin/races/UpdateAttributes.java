package wentuziak.szoplugin.races;


import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;

public class UpdateAttributes {
	
    private static double playerMovementSpeed;
    private static double playerHP;                
    private static double playerScale;             
    private static double playerMiningSpeed;       
    private static double playerAttackDamage;      
    private static double playerFireTime;          
    private static double playerGravity;            
    private static double playerFallDamage;        
    private static double playerKnockback;         
    private static double playerOxygenBonus;       
    private static double playerAttackSpeed;       
    private static double playerSafeFallRange;     
    private static double playerBlockReach;        
    private static double playerEntityReach;       
    private static double playerWaterSpeed;        
	
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
        
    	if (toRemove) { // reset to base values if "removerace"
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

        // get current player attributes
        AttributeInstance speedAttribute = player.getAttribute(Attribute.MOVEMENT_SPEED);
        AttributeInstance healthAttribute = player.getAttribute(Attribute.MAX_HEALTH);
        AttributeInstance scaleAttribute = player.getAttribute(Attribute.SCALE);
        AttributeInstance mineSpeedAttribute = player.getAttribute(Attribute.BLOCK_BREAK_SPEED);
        AttributeInstance attackAttribute = player.getAttribute(Attribute.ATTACK_DAMAGE);
        AttributeInstance burnTimeAttribute = player.getAttribute(Attribute.BURNING_TIME);
        AttributeInstance gravityAttribute = player.getAttribute(Attribute.GRAVITY);
        AttributeInstance fallDmgAttribute = player.getAttribute(Attribute.FALL_DAMAGE_MULTIPLIER);
        AttributeInstance knockbackResistAttribute = player.getAttribute(Attribute.KNOCKBACK_RESISTANCE);
        AttributeInstance oxygenAttribute = player.getAttribute(Attribute.OXYGEN_BONUS);
        AttributeInstance attackSpeedAttribute = player.getAttribute(Attribute.ATTACK_SPEED);
        AttributeInstance safeFallRangeAttribute = player.getAttribute(Attribute.SAFE_FALL_DISTANCE);
        AttributeInstance waterSpeedAttribute = player.getAttribute(Attribute.WATER_MOVEMENT_EFFICIENCY);
        AttributeInstance blockReachAttribute = player.getAttribute(Attribute.BLOCK_INTERACTION_RANGE);
        AttributeInstance entityReachAttribute = player.getAttribute(Attribute.ENTITY_INTERACTION_RANGE);
        
        // get current values
       playerMovementSpeed = speedAttribute.getValue();
       playerHP = healthAttribute.getValue();
       playerScale = scaleAttribute.getValue();
       playerMiningSpeed = mineSpeedAttribute.getValue();
       playerAttackDamage = attackAttribute.getValue();
       playerFireTime = burnTimeAttribute.getValue();
       playerGravity = gravityAttribute.getValue();
       playerFallDamage = fallDmgAttribute.getValue();
       playerKnockback = knockbackResistAttribute.getValue();
       playerOxygenBonus = oxygenAttribute.getValue();
       playerAttackSpeed = attackSpeedAttribute.getValue();
       playerSafeFallRange = safeFallRangeAttribute.getValue();
       playerBlockReach = blockReachAttribute.getValue();
       playerEntityReach = entityReachAttribute.getValue();
       playerWaterSpeed = waterSpeedAttribute.getValue();
        player.sendMessage(playerMovementSpeed + "");
        //multiply values "RACE_NAME"
        switch (raceName) {
        case "RACE_DWARF" -> handleDwarf();
        case "RACE_CELESTIAL" -> handleCelestial();
        case "RACE_WITCH" -> handleWitch();
        case "RACE_MISKARU" -> handleMiskaru();
        case "RACE_CARA" -> handleCara();
        case "RACE_MEWCHANT" -> handleMewchant();
        case "RACE_FOSSIL" -> handleFossil();
        case "RACE_ZEPHYR" -> handleZephyr();
        case "RACE_SANGUINITE" -> handleSanguinite();
        case "RACE_ELF" -> handleElf();
        case "RACE_HOBBIT" -> handleHobbit();
        default -> handleUnknownRace();
    }
        player.sendMessage(playerMovementSpeed + "");
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

    
    // Attributes of all races
    static void handleDwarf() {
    	playerMovementSpeed = playerMovementSpeed * 0.2;
        System.out.println("Handling Dwarf race..." + playerMovementSpeed);
    }

    static void handleCelestial() {
        System.out.println("Handling Celestial race...");
    }

    static void handleWitch() {
        System.out.println("Handling Witch race...");
    }

    static void handleMiskaru() {
        System.out.println("Handling Miskaru race...");
    }

    static void handleCara() {
        System.out.println("Handling Cara race...");
    }

    static void handleMewchant() {
    	playerMovementSpeed = playerMovementSpeed * 1.2;
        System.out.println("Handling Mewchant race...");
    }

    static void handleFossil() {
        System.out.println("Handling Fossil race...");
    }

    static void handleZephyr() {
        System.out.println("Handling Zephyr race...");
    }

    static void handleSanguinite() {
        System.out.println("Handling Sanguinite race...");
    }

    static void handleElf() {
        System.out.println("Handling Elf race...");
    }

    static void handleHobbit() {
        System.out.println("Handling Hobbit race...");
    }

    static void handleUnknownRace() {
        System.out.println("Unknown race. Applying default settings...");
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
        AttributeInstance attackSpeedAttribute = player.getAttribute(Attribute.ATTACK_DAMAGE);
        attackSpeedAttribute.setBaseValue(value);
    }
    public static void modifyFireTime(Player player, double value){
        AttributeInstance burnTimeAttribute = player.getAttribute(Attribute.BURNING_TIME);
        burnTimeAttribute.setBaseValue(value);
    }
    public static void modifyGravity(Player player, double value){
        AttributeInstance gravityAttribute = player.getAttribute(Attribute.GRAVITY);
        gravityAttribute.setBaseValue(value);
    }
    public static void modifyFallDmgMultiplier(Player player, double value){
        AttributeInstance fallDmgAttribute = player.getAttribute(Attribute.FALL_DAMAGE_MULTIPLIER);
        fallDmgAttribute.setBaseValue(value);
    }
    public static void modifyKnockBack(Player player, double value){
        AttributeInstance knockbackResistAttribute = player.getAttribute(Attribute.KNOCKBACK_RESISTANCE);
        knockbackResistAttribute.setBaseValue(value);
    }
    public static void modifyOxygenBonus(Player player, double value){
        AttributeInstance oxygenAttribute = player.getAttribute(Attribute.OXYGEN_BONUS);
        oxygenAttribute.setBaseValue(value);
    }
    public static void modifyAttackSpeed(Player player, double value){
        AttributeInstance attackSpeedAttribute = player.getAttribute(Attribute.ATTACK_SPEED);
        attackSpeedAttribute.setBaseValue(value);
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