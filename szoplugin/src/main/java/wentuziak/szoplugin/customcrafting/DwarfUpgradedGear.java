package wentuziak.szoplugin.customcrafting;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customlogic.LogicHolder;


public class DwarfUpgradedGear {
	public static void dwarfGearCraft(Player player ,ItemStack item) {
		if (ItemCategories.isSword(item.getType()) || ItemCategories.isAxe(item.getType()) ||
			ItemCategories.isPickaxe(item.getType()) || ItemCategories.isHelmet(item.getType()) ||
			ItemCategories.isChestplate(item.getType()) || ItemCategories.isLeggings(item.getType()) ||
			ItemCategories.isBoots(item.getType())){
						
		}
		else return;
		updateLoreOnItem(item);
	}
	
	public static void updateLoreOnItem(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		
		if (meta == null) return;

		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
		lore.add(ChatColor.YELLOW + "Master crafted");
		meta.setLore(lore);
        meta.getPersistentDataContainer().set(Keys.CUSTOM_DWARF_UPGRADE, PersistentDataType.BOOLEAN, true);
		
		item.setItemMeta(meta);
	}
	
	public static void dwarfArmorEffect(Player player) {
		int amount = LogicHolder.countKeysOnPlayer(player, Keys.CUSTOM_DWARF_UPGRADE);
		if (amount >= 4) amount = 4;
				
		if(LogicHolder.critRoll(amount * 5)) LogicHolder.givePotionEffect(player, "ABSORPTION", amount * 20, amount/-3);
	}
}
