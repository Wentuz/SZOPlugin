package wentuziak.szoplugin.customcrafting;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import wentuziak.szoplugin.Keys;


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
}
