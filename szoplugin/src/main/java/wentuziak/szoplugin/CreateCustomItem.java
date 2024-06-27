package wentuziak.szoplugin;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.inventory.meta.LeatherArmorMeta;


import net.md_5.bungee.api.ChatColor;

public class CreateCustomItem {
    static ItemStack createSoulFragment()
    {
        ItemStack soulFragment = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta soulFragmentMeta = soulFragment.getItemMeta();

        soulFragmentMeta.setDisplayName(ChatColor.DARK_PURPLE + "Soul Fragment");
        soulFragmentMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        soulFragmentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        soulFragment.setItemMeta(soulFragmentMeta);

        return soulFragment;
    }

    static ItemStack createMechanicalParts()
    {
        ItemStack mechanicalParts = new ItemStack(Material.NETHERITE_SCRAP);
        ItemMeta mechanicalPartsMeta = mechanicalParts.getItemMeta();
        
        mechanicalPartsMeta.setDisplayName(ChatColor.GOLD + "Mechanical Parts");
        mechanicalPartsMeta.setLore(Arrays.asList(
            "Science is just engineering that does not work"));
        mechanicalPartsMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        mechanicalPartsMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        mechanicalPartsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);
        mechanicalParts.setItemMeta(mechanicalPartsMeta);

        return mechanicalParts;
    }

    static ItemStack createAngelSword()
    {
        ItemStack angelSword = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta angelSwordMeta = angelSword.getItemMeta();
        
        angelSwordMeta.setDisplayName(ChatColor.YELLOW + "Celestial Razor");
        angelSwordMeta.setLore(Arrays.asList(
            ChatColor.YELLOW + "The absolute leader protects his kin !"));
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

        return angelSword;
    }

    static ItemStack createDaemonSword()
    {
        ItemStack daemonSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta daemonSwordMeta = daemonSword.getItemMeta();
        
        daemonSwordMeta.setDisplayName(ChatColor.RED + "Withering Bastard Sword");
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

        return daemonSword;
    }

    static ItemStack createPyromancerSword()
    {
        ItemStack pyromancerSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta pyromancerSwordMeta = pyromancerSword.getItemMeta();

        pyromancerSwordMeta.setDisplayName(ChatColor.DARK_BLUE + "Pyromancer Sword");
        pyromancerSwordMeta.setLore(Arrays.asList(
            "Wonder what could go wrong"));
        pyromancerSwordMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        pyromancerSwordMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        pyromancerSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BOOLEAN, true);
        pyromancerSword.setItemMeta(pyromancerSwordMeta);

        return pyromancerSword;
    }

    static ItemStack createThunderHammer()
    {
        ItemStack thunderHammer = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta thunderHammerMeta = thunderHammer.getItemMeta();

        thunderHammerMeta.setDisplayName(ChatColor.DARK_BLUE + "Þrumuhamar");
        thunderHammerMeta.setLore(Arrays.asList(
            "Thunder warriors rise your arms !"));
        thunderHammerMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        thunderHammerMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        thunderHammerMeta.getPersistentDataContainer().set(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BOOLEAN, true);
        thunderHammer.setItemMeta(thunderHammerMeta);

        return thunderHammer;
    }


    //
    //      ARMOR
    //
    static ItemStack createHermesBoots()
    {
        ItemStack hermesBoots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta hermesBootsMeta = hermesBoots.getItemMeta();

        hermesBootsMeta.setDisplayName(ChatColor.DARK_GREEN + "Hermes Boots");
        AttributeModifier movementSpeedModifierHermes = new AttributeModifier(UUID.randomUUID(), "Speed", 0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        hermesBootsMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, movementSpeedModifierHermes);
        ((LeatherArmorMeta) hermesBootsMeta).setColor(Color.GREEN);
        hermesBoots.setItemMeta(hermesBootsMeta);

        return hermesBoots;
    }

    static ItemStack createJetBoots()
    {
        ItemStack jetBoots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta jetBootsMeta = jetBoots.getItemMeta();

        jetBootsMeta.setDisplayName(ChatColor.GRAY + "Jet Boots");
        jetBootsMeta.setLore(Arrays.asList(
            "A cheap way to levitate"));
        ((LeatherArmorMeta) jetBootsMeta).setColor(Color.SILVER);
        jetBootsMeta.getPersistentDataContainer().set(Keys.CUSTOM_JET_BOOTS, PersistentDataType.BOOLEAN, true);

        jetBoots.setItemMeta(jetBootsMeta);

        return jetBoots;
    }

    static ItemStack createExplosiveChest()
    {
        ItemStack explosiveChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta explosiveChestMeta = explosiveChest.getItemMeta();

        explosiveChestMeta.setDisplayName(ChatColor.DARK_RED + "Bombardiers Vest");
        explosiveChestMeta.setLore(Arrays.asList(
            "Hey you dropped something !"));
        explosiveChestMeta.getPersistentDataContainer().set(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BOOLEAN, true);

        explosiveChest.setItemMeta(explosiveChestMeta);

        return explosiveChest;
    }

    static ItemStack createGolemChest()
    {
        ItemStack golemChest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta golemChestMeta = golemChest.getItemMeta();

        golemChestMeta.setDisplayName(ChatColor.DARK_BLUE + "Sentinels Chestplate");
        golemChestMeta.setLore(Arrays.asList(
            "Incredible resilience while crouching"));
        AttributeModifier armorModifierGolem = new AttributeModifier(UUID.randomUUID(), "Armor", 10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        golemChestMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifierGolem);
        AttributeModifier healthModifierGolem = new AttributeModifier(UUID.randomUUID(), "Health", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        golemChestMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthModifierGolem);
        AttributeModifier speedModifierGolem = new AttributeModifier(UUID.randomUUID(), "Speed", -0.1, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST);
        golemChestMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, speedModifierGolem);
        golemChestMeta.getPersistentDataContainer().set(Keys.CUSTOM_GOLEM_CHEST, PersistentDataType.BOOLEAN, true);

        golemChest.setItemMeta(golemChestMeta);

        return golemChest;
    }


    //
    //      MAGIC
    //
    static ItemStack createTeleportSpell()
    {
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

        return teleportSpell;
    }

    static ItemStack createSpiritLeech()
    {
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

        return spiritLeech;
    }
}
