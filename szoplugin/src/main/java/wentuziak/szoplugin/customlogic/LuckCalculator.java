package wentuziak.szoplugin.customlogic;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.Keys;

public class LuckCalculator {
    static PersistentDataContainer playerContainer;

	public static int getPlayerLuck(Player player) {
		int luck = 0;
		
		luck = getHandLuck(player.getInventory().getItemInMainHand()) + getLuckAttribute(player) 
		+ getLuckyClock(player.getInventory().getItemInOffHand());
		
		return luck;
	}
	
	private static int getHandLuck(ItemStack itemStack) {
		int lootingLvl = itemStack.getEnchantmentLevel(Enchantment.LOOTING);
		int fortuneLvl = itemStack.getEnchantmentLevel(Enchantment.FORTUNE);
		int luckOfSeaLvl = itemStack.getEnchantmentLevel(Enchantment.LUCK_OF_THE_SEA);

		
		return lootingLvl + fortuneLvl + luckOfSeaLvl;
	}
	
	private static int getLuckAttribute(Player player) {
		AttributeInstance luckAttribute = player.getAttribute(Attribute.LUCK);
		return (int) luckAttribute.getValue();
	}
	
	private static int getLuckyClock(ItemStack itemInOffHand) {
		if(!itemInOffHand.hasItemMeta()) return 0;
		playerContainer = itemInOffHand.getItemMeta().getPersistentDataContainer();
        if (playerContainer.has(Keys.CUSTOM_LUCKY_CLOCK, PersistentDataType.BYTE)) {
        	return 1;
        }else {
        	return 0;
        }
	}
}
