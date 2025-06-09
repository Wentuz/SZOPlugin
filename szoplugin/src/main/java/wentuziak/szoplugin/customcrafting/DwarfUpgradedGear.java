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

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;


public class DwarfUpgradedGear {
	public static void dwarfGearCraft(Player player ,ItemStack item) {
		if (ItemCategories.isSword(item.getType())) 	;
		else if (ItemCategories.isAxe(item.getType())); 
		else if (ItemCategories.isPickaxe(item.getType())) ;
		else if (ItemCategories.isHelmet(item.getType())) updateArmorValue(item, EquipmentSlot.HEAD);
		else if (ItemCategories.isChestplate(item.getType())) updateArmorValue(item, EquipmentSlot.CHEST);
		else if (ItemCategories.isLeggings(item.getType())) updateArmorValue(item, EquipmentSlot.LEGS);
		else if (ItemCategories.isBoots(item.getType())) updateArmorValue(item, EquipmentSlot.FEET);
		else return;
		updateLoreOnItem(item);
	}
	
	public static void updateLoreOnItem(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		
		if (meta == null) return;

		List<String> lore = meta.hasLore() ? meta.getLore() : new ArrayList<>();
		lore.add(ChatColor.YELLOW + "Master crafted");
		meta.setLore(lore);
		
		item.setItemMeta(meta);
	}
	
	public static void updateArmorValue(ItemStack item, EquipmentSlot slot) {
	    ItemMeta meta = item.getItemMeta();
	    if (meta == null) return;

	    // Create a new multimap with existing modifiers
//	    Multimap<Attribute, AttributeModifier> modifiers = ArrayListMultimap.create();
//	    if (meta.hasAttributeModifiers()) {
//	        modifiers.putAll(meta.getAttributeModifiers());
//	    }
	    
	    if (meta.hasAttributeModifiers()) {
	        Multimap<Attribute, AttributeModifier> modifiers = meta.getAttributeModifiers();

	        for (Attribute attribute : modifiers.keySet()) {
	            System.out.println("Attribute: " + attribute.name());

	            for (AttributeModifier modifier : modifiers.get(attribute)) {
	                System.out.println("  Modifier:");
	                System.out.println("    Name: " + modifier.getName());
	                System.out.println("    Amount: " + modifier.getAmount());
	                System.out.println("    Operation: " + modifier.getOperation());
	                System.out.println("    UUID: " + modifier.getUniqueId());
	            }
	        }
	    } else {
	        System.out.println("No AttributeModifiers found on this item.");
	    }

	    // Optional: remove existing ARMOR modifiers for this slot, to avoid stacking visually-confusing values

	    // Create new modifier
	    AttributeModifier armorModifier = new AttributeModifier(
	        UUID.randomUUID(),
	        "custom_armor_value_" + System.currentTimeMillis(),
	        0.05, // e.g. 5.0 for +5 armor
	        AttributeModifier.Operation.MULTIPLY_SCALAR_1, // ADD_NUMBER is safer for adding flat amounts
	        slot
	    );

	    //modifiers.put(Attribute.ARMOR, armorModifier);

	    // Apply back
	    //meta.setAttributeModifiers(modifiers);
	    item.setItemMeta(meta);
	}

}
