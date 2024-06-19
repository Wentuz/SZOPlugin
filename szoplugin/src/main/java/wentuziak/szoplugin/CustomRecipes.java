package wentuziak.szoplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
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
        //      Soul Fragment Recipe
        //


        ItemStack pyromancerSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta pyromancerSwordMeta = pyromancerSword.getItemMeta();
        pyromancerSwordMeta.setDisplayName(ChatColor.DARK_BLUE + "Pyromancer Sword");
        pyromancerSwordMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        pyromancerSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pyromancerSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BOOLEAN, true);
        pyromancerSword.setItemMeta(pyromancerSwordMeta);

        ShapedRecipe pyromancerSwordRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "PyromancerSwordRecipe"), pyromancerSword);
        pyromancerSwordRecipe.shape(
            " D ",
            " D ",
            "TST");
        pyromancerSwordRecipe.setIngredient('S', Material.DIRT); // Placeholder untill souls fragment works
        pyromancerSwordRecipe.setIngredient('T', Material.TNT);
        pyromancerSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(pyromancerSwordRecipe);
    }

}
