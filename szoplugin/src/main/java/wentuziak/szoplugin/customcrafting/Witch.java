package wentuziak.szoplugin.customcrafting;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;


public class Witch {
	public static ItemStack witchGetPotion(PotionMeta potionMeta) {

        // Get base potion type (e.g., REGENERATION, SPEED)
        PotionData basePotionData = potionMeta.getBasePotionData();
        PotionType type = basePotionData.getType();

        System.out.println("Base Potion Type: " + type.name());
        ItemStack newPotion = new ItemStack(Material.POTION);
        
        switch (type) {
        case PotionType.WATER:
        	return newPotion;
        case PotionType.REGENERATION:
        	newPotion = CreateCustomItem.createSuperHealingPot();
            break;
        case PotionType.HEALING:
        	newPotion = CreateCustomItem.createInstantHealthPotion();
            break;
        case PotionType.WATER_BREATHING:
        	newPotion = CreateCustomItem.createDoplhinPotionPotion();
            break;
        case PotionType.POISON:
        	newPotion = CreateCustomItem.createToxicPot();
            break;
        case PotionType.TURTLE_MASTER:
        	newPotion = CreateCustomItem.createIronHide();
            break;
        case PotionType.SWIFTNESS:
        	newPotion = CreateCustomItem.createGepardPotion();
            break;
        case PotionType.SLOWNESS:
        	newPotion = CreateCustomItem.createParalyzingGas();
            break;
        default:
        	return null;
        }
        // Optional: Read custom potion effects (if any)
        if (!potionMeta.getCustomEffects().isEmpty()) {
            for (PotionEffect effect : potionMeta.getCustomEffects()) {
                System.out.println("Custom Effect: " + effect.getType().getName());
            }
        }
        
        return newPotion;
	}
}
