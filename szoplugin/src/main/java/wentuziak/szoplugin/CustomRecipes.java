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
        //      Angel Sword Recipe
        //
        ItemStack angelSword = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta angelSwordMeta = angelSword.getItemMeta();
        angelSwordMeta.setDisplayName(ChatColor.YELLOW + "Angel Sword");
        angelSwordMeta.setLore(Arrays.asList(
            ChatColor.YELLOW + "Nine tailed protects !"));
        angelSwordMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        angelSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        angelSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_ANGEL_SWORD, PersistentDataType.BOOLEAN, true);
        AttributeModifier ArmorModifierAngelSword = new AttributeModifier(UUID.randomUUID(), "Armor", 4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        angelSwordMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, ArmorModifierAngelSword);
        AttributeModifier attackSpeedModifierAngelSword = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", -2.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        angelSwordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifierAngelSword);
        AttributeModifier attackDamageModifierAngelSword = new AttributeModifier(UUID.randomUUID(), "AttackDamage", 8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        angelSwordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamageModifierAngelSword);
        angelSword.setItemMeta(angelSwordMeta);

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
        ItemStack daemonSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta daemonSwordMeta = daemonSword.getItemMeta();
        daemonSwordMeta.setDisplayName(ChatColor.RED + "Daemon Sword");
        daemonSwordMeta.setLore(Arrays.asList(
            ChatColor.RED + "Blood for Hound !"));
        daemonSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        daemonSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_DAEMON_SWORD, PersistentDataType.BOOLEAN, true);
        daemonSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, -1, true);
        AttributeModifier attackSpeedModifierDaemonSword = new AttributeModifier(UUID.randomUUID(), "Armor", -2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        daemonSwordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifierDaemonSword);
        AttributeModifier attackDamageModifierDaemonSword = new AttributeModifier(UUID.randomUUID(), "AttackDamage", 10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        daemonSwordMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamageModifierDaemonSword);
        daemonSword.setItemMeta(daemonSwordMeta);

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
        //      Þrumuhamar Recipe
        //
        ItemStack thunderHammer = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta thunderHammerMeta = thunderHammer.getItemMeta();
        thunderHammerMeta.setDisplayName(ChatColor.DARK_BLUE + "Þrumuhamar");
        thunderHammerMeta.setLore(Arrays.asList(
            "Thunder warriors rise your arms !"));
        thunderHammerMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        thunderHammerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        thunderHammerMeta.getPersistentDataContainer().set(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BOOLEAN, true);
        thunderHammer.setItemMeta(thunderHammerMeta);

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


        //
        //      Spirit Leech recipe
        //
        ItemStack spiritLeech = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta spiritLeechMeta = spiritLeech.getItemMeta();
        spiritLeechMeta.setDisplayName(ChatColor.DARK_PURPLE + "Spirit Leech");
        spiritLeechMeta.setLore(Arrays.asList(
            "Siphons your foes soul...",
                "Grants you their living essence"));
        spiritLeechMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        spiritLeechMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        spiritLeechMeta.getPersistentDataContainer().set(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.BOOLEAN, true);
        spiritLeech.setItemMeta(spiritLeechMeta);

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
