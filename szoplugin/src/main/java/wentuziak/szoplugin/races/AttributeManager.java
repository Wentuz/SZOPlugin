package wentuziak.szoplugin.races;

public class AttributeManager {
	public enum Attribute {
		SCALE(0),
	    HEALTH(1),
	    MOVEMENT_SPEED(2),
	    MINING_SPEED(3),
	    ATTACK_DAMAGE(4),
	    FIRE_TIME(5),
	    GRAVITY(6),
	    FALL_DAMAGE_MULTIPLIER(7),
	    KNOCKBACK(8),
	    OXYGEN_BONUS(9),
	    ATTACK_SPEED(10),
	    SAFE_FALL_RANGE(11),
	    BLOCK_REACH(12),
	    ENTITY_REACH(13),
	    WATER_SPEED(14);

	    public final int index;

	    Attribute(int index) {
	        this.index = index;
	    }
	}
	private static final double[] baseAttributes = new double[15];

    static {
    	baseAttributes[Attribute.SCALE.index] = 1;
        baseAttributes[Attribute.HEALTH.index] = 20;
        baseAttributes[Attribute.MOVEMENT_SPEED.index] = 0.1;
        baseAttributes[Attribute.MINING_SPEED.index] = 1;
        baseAttributes[Attribute.ATTACK_DAMAGE.index] = 1;
        baseAttributes[Attribute.FIRE_TIME.index] = 1;
        baseAttributes[Attribute.GRAVITY.index] = 0.08;
        baseAttributes[Attribute.FALL_DAMAGE_MULTIPLIER.index] = 1;
        baseAttributes[Attribute.KNOCKBACK.index] = 0;
        baseAttributes[Attribute.OXYGEN_BONUS.index] = 0;
        baseAttributes[Attribute.ATTACK_SPEED.index] = 4;
        baseAttributes[Attribute.SAFE_FALL_RANGE.index] = 3;
        baseAttributes[Attribute.BLOCK_REACH.index] = 4.5;
        baseAttributes[Attribute.ENTITY_REACH.index] = 3;
        baseAttributes[Attribute.WATER_SPEED.index] = 0;
    }

    public static double getBaseValue(Attribute attr) {
        return baseAttributes[attr.index];
    }
}
