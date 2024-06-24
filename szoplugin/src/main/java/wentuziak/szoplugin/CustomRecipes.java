package wentuziak.szoplugin;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import net.md_5.bungee.api.ChatColor;

public final class CustomRecipes {

    public static void register()
    {
        //
        //      Soul Fragment Recipe
        //
        ItemStack soulFragment = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta soulFragmentMeta = soulFragment.getItemMeta();
        soulFragmentMeta.setDisplayName(ChatColor.DARK_PURPLE + "Soul Fragment");
        soulFragmentMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        soulFragmentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        soulFragment.setItemMeta(soulFragmentMeta);

        ShapelessRecipe soulFragmentRecipe = new ShapelessRecipe(new NamespacedKey(SzoPlugin.getInstance(), "SoulFragmentRecipe"), soulFragment);
        soulFragmentRecipe.addIngredient(3, Material.DRAGON_BREATH);
        Bukkit.addRecipe(soulFragmentRecipe);


        //
        //      Mechanical Parts Recipe
        //
        ItemStack mechanicalParts = new ItemStack(Material.NETHERITE_SCRAP);
        ItemMeta mechanicalPartsMeta = mechanicalParts.getItemMeta();
        mechanicalPartsMeta.setDisplayName(ChatColor.GOLD + "Mechanical Parts");
        mechanicalPartsMeta.setLore(Arrays.asList(
            "Science is just engineering that does not work"));
        mechanicalPartsMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        mechanicalPartsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        mechanicalPartsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);
        mechanicalParts.setItemMeta(mechanicalPartsMeta);

        ShapedRecipe mechanicalPartsRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "MechanicalPartsRecipe"), mechanicalParts);
        mechanicalPartsRecipe.shape(
            " R ",
            "CNC",
            "   ");
            mechanicalPartsRecipe.setIngredient('N', Material.NETHERITE_SCRAP);
            mechanicalPartsRecipe.setIngredient('R', Material.REPEATER);
            mechanicalPartsRecipe.setIngredient('C', Material.COPPER_INGOT);
        Bukkit.addRecipe(mechanicalPartsRecipe);


        //
        //      Pyromancer's Sword Recipe
        //
        ItemStack pyromancerSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta pyromancerSwordMeta = pyromancerSword.getItemMeta();
        pyromancerSwordMeta.setDisplayName(ChatColor.DARK_BLUE + "Pyromancer Sword");
        pyromancerSwordMeta.setLore(Arrays.asList(
            "Wonder what could go wrong"));
        pyromancerSwordMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        pyromancerSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pyromancerSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BOOLEAN, true);
        pyromancerSword.setItemMeta(pyromancerSwordMeta);

        ShapedRecipe pyromancerSwordRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "PyromancerSwordRecipe"), pyromancerSword);
        pyromancerSwordRecipe.shape(
            " D ",
            " D ",
            "TST");
            pyromancerSwordRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            pyromancerSwordRecipe.setIngredient('T', Material.TNT);
            pyromancerSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(pyromancerSwordRecipe);


        //
        //      Teleport Spell recipe
        //
        ItemStack teleportSpell = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta teleportSpellMeta = teleportSpell.getItemMeta();
        teleportSpellMeta.setDisplayName(ChatColor.DARK_PURPLE + "Bound Teleport");
        teleportSpellMeta.setLore(Arrays.asList(
            "Might make you sick...",
                "Or get other half of you somewhere else"));
        teleportSpellMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        teleportSpellMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        teleportSpellMeta.getPersistentDataContainer().set(Keys.CUSTOM_TELEPORT_SPELL, PersistentDataType.BOOLEAN, true);
        teleportSpell.setItemMeta(teleportSpellMeta);

        ShapedRecipe teleportSpellRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "teleportSpellRecipe"), teleportSpell);
        teleportSpellRecipe.shape(
            " P ",
            "PSP",
            " P ");
            teleportSpellRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            teleportSpellRecipe.setIngredient('P', Material.ENDER_PEARL);
        Bukkit.addRecipe(teleportSpellRecipe);
    }
}
