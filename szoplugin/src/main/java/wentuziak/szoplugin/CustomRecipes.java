package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
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
        
        ItemStack soulFragment = CreateCustomItem.createSoulFragment();
        
        ShapelessRecipe soulFragmentRecipe = new ShapelessRecipe(new NamespacedKey(SzoPlugin.getInstance(), "SoulFragmentRecipe"), soulFragment);
        soulFragmentRecipe.addIngredient(2, Material.DRAGON_BREATH);
        soulFragmentRecipe.addIngredient(1, Material.NETHER_STAR);
        Bukkit.addRecipe(soulFragmentRecipe);

        
        //
        //      Mechanical Parts Recipe
        //
        ItemStack mechanicalParts = CreateCustomItem.createMechanicalParts();

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
        //      Angel Sword Recipe
        //
        ItemStack angelSword = CreateCustomItem.createAngelSword();

        ShapedRecipe angelSwordRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "AngelSwordRecipe"), angelSword);
        angelSwordRecipe.shape(
        " D ",
            " D ",
            "GSG");
            angelSwordRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            angelSwordRecipe.setIngredient('G', Material.GOLDEN_APPLE);
            angelSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(angelSwordRecipe);

        
        //
        //      Daemon Sword Recipe
        //
        ItemStack daemonSword = CreateCustomItem.createDaemonSword();

        ShapedRecipe daemonSwordRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "DaemonSwordRecipe"), daemonSword);
        daemonSwordRecipe.shape(
            " D ",
            " D ",
            "BSB");
            daemonSwordRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            daemonSwordRecipe.setIngredient('B', Material.BLAZE_POWDER);
            daemonSwordRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(daemonSwordRecipe);


        //
        //      Pyromancer's Sword Recipe
        //
        ItemStack pyromancerSword = CreateCustomItem.createPyromancerSword();

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
        //      Þrumuhamar Recipe
        //
        ItemStack thunderHammer = CreateCustomItem.createThunderHammer();

        ShapedRecipe thunderHammerRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "ThunderHammerRecipe"), thunderHammer);
        thunderHammerRecipe.shape(
            "DS ",
            "DC ",
            " B ");
            thunderHammerRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            thunderHammerRecipe.setIngredient('B', Material.BONE);
            thunderHammerRecipe.setIngredient('C', Material.COPPER_INGOT);
            thunderHammerRecipe.setIngredient('D', Material.DIAMOND);
        Bukkit.addRecipe(thunderHammerRecipe);


        //
        //      Teleport Spell recipe
        //
        ItemStack teleportSpell = CreateCustomItem.createTeleportSpell();

        ShapedRecipe teleportSpellRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "teleportSpellRecipe"), teleportSpell);
        teleportSpellRecipe.shape(
            " P ",
            "PSP",
            " P ");
            teleportSpellRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            teleportSpellRecipe.setIngredient('P', Material.ENDER_PEARL);
        Bukkit.addRecipe(teleportSpellRecipe);


        //
        //      Spirit Leech recipe
        //
        ItemStack spiritLeech = CreateCustomItem.createSpiritLeech();
        ShapedRecipe spiritLeechRecipe = new ShapedRecipe(new NamespacedKey(SzoPlugin.getInstance(), "spiritLeechRecipe"), spiritLeech);
        spiritLeechRecipe.shape(
            " E ",
            "LSL",
            " L ");
            spiritLeechRecipe.setIngredient('S', new RecipeChoice.ExactChoice(soulFragment));
            spiritLeechRecipe.setIngredient('E', Material.ENCHANTED_GOLDEN_APPLE);
            spiritLeechRecipe.setIngredient('L', Material.LAPIS_LAZULI);
        Bukkit.addRecipe(spiritLeechRecipe);
    }
}
