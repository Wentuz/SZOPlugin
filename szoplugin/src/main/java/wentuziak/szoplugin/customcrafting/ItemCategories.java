package wentuziak.szoplugin.customcrafting;
import org.bukkit.Material;

import java.util.Set;
import java.util.EnumSet;

public class ItemCategories {

    public static final Set<Material> HEAD_ITEMS = EnumSet.of(
            Material.LEATHER_HELMET,
            Material.CHAINMAIL_HELMET,
            Material.IRON_HELMET,
            Material.GOLDEN_HELMET,
            Material.DIAMOND_HELMET,
            Material.NETHERITE_HELMET,
            Material.TURTLE_HELMET
    );

    public static final Set<Material> BODY_ITEMS = EnumSet.of(
            Material.LEATHER_CHESTPLATE,
            Material.CHAINMAIL_CHESTPLATE,
            Material.IRON_CHESTPLATE,
            Material.GOLDEN_CHESTPLATE,
            Material.DIAMOND_CHESTPLATE,
            Material.NETHERITE_CHESTPLATE
    );

    public static final Set<Material> LEGS_ITEMS = EnumSet.of(
            Material.LEATHER_LEGGINGS,
            Material.CHAINMAIL_LEGGINGS,
            Material.IRON_LEGGINGS,
            Material.GOLDEN_LEGGINGS,
            Material.DIAMOND_LEGGINGS,
            Material.NETHERITE_LEGGINGS
    );

    public static final Set<Material> BOOTS_ITEMS = EnumSet.of(
            Material.LEATHER_BOOTS,
            Material.CHAINMAIL_BOOTS,
            Material.IRON_BOOTS,
            Material.GOLDEN_BOOTS,
            Material.DIAMOND_BOOTS,
            Material.NETHERITE_BOOTS
    );

    public static final Set<Material> SWORDS = EnumSet.of(
            Material.WOODEN_SWORD,
            Material.STONE_SWORD,
            Material.IRON_SWORD,
            Material.GOLDEN_SWORD,
            Material.DIAMOND_SWORD,
            Material.NETHERITE_SWORD
    );

    public static final Set<Material> AXES = EnumSet.of(
            Material.WOODEN_AXE,
            Material.STONE_AXE,
            Material.IRON_AXE,
            Material.GOLDEN_AXE,
            Material.DIAMOND_AXE,
            Material.NETHERITE_AXE
    );

    public static final Set<Material> PICKAXES = EnumSet.of(
            Material.WOODEN_PICKAXE,
            Material.STONE_PICKAXE,
            Material.IRON_PICKAXE,
            Material.GOLDEN_PICKAXE,
            Material.DIAMOND_PICKAXE,
            Material.NETHERITE_PICKAXE
    );

    // Example utility method:
    public static boolean isSword(Material material) {
        return SWORDS.contains(material);
    }

    public static boolean isAxe(Material material) {
        return AXES.contains(material);
    }

    public static boolean isPickaxe(Material material) {
        return PICKAXES.contains(material);
    }

    public static boolean isHelmet(Material material) {
        return HEAD_ITEMS.contains(material);
    }

    public static boolean isChestplate(Material material) {
        return BODY_ITEMS.contains(material);
    }

    public static boolean isLeggings(Material material) {
        return LEGS_ITEMS.contains(material);
    }

    public static boolean isBoots(Material material) {
        return BOOTS_ITEMS.contains(material);
    }

}

