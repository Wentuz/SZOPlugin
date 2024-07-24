package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;

import net.md_5.bungee.api.ChatColor;

public class CreateCustomItem {
    static ItemStack createSoulFragment(){
        ItemStack soulFragment = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta soulFragmentMeta = soulFragment.getItemMeta();

        soulFragmentMeta.setDisplayName(ChatColor.DARK_PURPLE + "Soul Fragment");
        soulFragmentMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        soulFragmentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        soulFragment.setItemMeta(soulFragmentMeta);

        return soulFragment;
    }

    static ItemStack createMechanicalParts(){
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

    static ItemStack createAngelSword(){
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

    static ItemStack createDaemonSword(){
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

    static ItemStack createPyromancerSword(){
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

    static ItemStack createThunderHammer(){
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

    static ItemStack createStinkyStick(){
        ItemStack stinkyStick = new ItemStack(Material.STICK);
        ItemMeta stinkyStickMeta = stinkyStick.getItemMeta();

        stinkyStickMeta.setDisplayName(ChatColor.DARK_GREEN + "Stinky Stick");
        stinkyStickMeta.setLore(Arrays.asList(
            "Eww!"));
        stinkyStickMeta.addEnchant(Enchantment.KNOCKBACK, 5, true);
        stinkyStickMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        stinkyStick.setItemMeta(stinkyStickMeta);

        return stinkyStick;
    }

    static ItemStack createScurvyTrident(){
        ItemStack scurvyTrident = new ItemStack(Material.TRIDENT);
        ItemMeta scurvyTridentMeta = scurvyTrident.getItemMeta();

        scurvyTridentMeta.setDisplayName(ChatColor.DARK_BLUE + "Scurvy Trident");
        scurvyTridentMeta.setLore(Arrays.asList(
            "Ahoy !"));
        AttributeModifier attackSpeedModifierScurvyTrident = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", -3.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifierScurvyTrident);
        AttributeModifier attackDamageModifierScurvyTrident = new AttributeModifier(UUID.randomUUID(), "AttackDamage", 12.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamageModifierScurvyTrident);
        AttributeModifier waterSpeedModifierScurvyTrident = new AttributeModifier(UUID.randomUUID(), "WaterSpeed", 1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY, waterSpeedModifierScurvyTrident);
        AttributeModifier attackSpeedModifierScurvyTridentOff = new AttributeModifier(UUID.randomUUID(), "AttackSpeedOff", -3.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifierScurvyTridentOff);
        AttributeModifier attackDamageModifierScurvyTridentOff = new AttributeModifier(UUID.randomUUID(), "AttackDamageOff", 12.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackDamageModifierScurvyTridentOff);
        AttributeModifier waterSpeedModifierScurvyTridentOff = new AttributeModifier(UUID.randomUUID(), "WaterSpeedOff", 1.5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.GENERIC_WATER_MOVEMENT_EFFICIENCY, waterSpeedModifierScurvyTridentOff);
        scurvyTrident.setItemMeta(scurvyTridentMeta);

        return scurvyTrident;
    }

    static ItemStack createGravityBow(){
        ItemStack gravityBow = new ItemStack(Material.BOW);
        ItemMeta gravityBowMeta = gravityBow.getItemMeta();

        gravityBowMeta.setDisplayName(ChatColor.DARK_BLUE + "Anti Grav Bow");
        gravityBowMeta.setLore(Arrays.asList(
            ChatColor.GREEN + "pew pew pew",
            ChatColor.GREEN + "haha :)"));
        gravityBowMeta.getPersistentDataContainer().set(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.BOOLEAN, true);
        gravityBow.setItemMeta(gravityBowMeta);

        return gravityBow;
    }

    static ItemStack createRatBow(){
        ItemStack ratBow = new ItemStack(Material.BOW);
        ItemMeta ratBowMeta = ratBow.getItemMeta();

        ratBowMeta.setDisplayName(ChatColor.YELLOW + "ULTIMATE RAT LAUNCHER");
        ratBowMeta.setLore(Arrays.asList(
            ChatColor.YELLOW + "SHRIEK SHRIEK"));
        ratBowMeta.getPersistentDataContainer().set(Keys.CUSTOM_RAT_BOW, PersistentDataType.BOOLEAN, true);
        ratBow.setItemMeta(ratBowMeta);

        return ratBow;
    }

    static ItemStack createRepeaterCrossbow(){
        ItemStack repeaterCrossbow = new ItemStack(Material.CROSSBOW);
        ItemMeta repeaterCrossbowMeta = repeaterCrossbow.getItemMeta();

        repeaterCrossbowMeta.setDisplayName(ChatColor.YELLOW + "Repeater Crossbow");
        repeaterCrossbowMeta.setLore(Arrays.asList(
            ChatColor.YELLOW + "Pew pew pew"));
        repeaterCrossbowMeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);
        repeaterCrossbowMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        repeaterCrossbow.setItemMeta(repeaterCrossbowMeta);

        return repeaterCrossbow;
    }


    //
    //      ARMOR
    //
    static ItemStack createHermesBoots(){
        ItemStack hermesBoots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta hermesBootsMeta = hermesBoots.getItemMeta();

        hermesBootsMeta.setDisplayName(ChatColor.DARK_GREEN + "Hermes Boots");
        AttributeModifier movementSpeedModifierHermes = new AttributeModifier(UUID.randomUUID(), "Speed", 0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        hermesBootsMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, movementSpeedModifierHermes);
        ((LeatherArmorMeta) hermesBootsMeta).setColor(Color.GREEN);
        hermesBoots.setItemMeta(hermesBootsMeta);

        return hermesBoots;
    }

    static ItemStack createJetBoots(){
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

    static ItemStack createExplosiveChest(){
        ItemStack explosiveChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta explosiveChestMeta = explosiveChest.getItemMeta();

        explosiveChestMeta.setDisplayName(ChatColor.DARK_RED + "Bombardiers Vest");
        explosiveChestMeta.setLore(Arrays.asList(
            "Hey you dropped something !"));
        explosiveChestMeta.getPersistentDataContainer().set(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BOOLEAN, true);

        explosiveChest.setItemMeta(explosiveChestMeta);

        return explosiveChest;
    }

    static ItemStack createGolemChest(){
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

    static ItemStack createMermaidTail(){
        ItemStack mermaidTail = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta mermaidTailMeta = mermaidTail.getItemMeta();

        mermaidTailMeta.setDisplayName(ChatColor.DARK_BLUE + "Mermaid's Tail");
        mermaidTailMeta.setLore(Arrays.asList(
            "Don't wet your pants !"));
        mermaidTailMeta.getPersistentDataContainer().set(Keys.CUSTOM_MERMAID_TAIL, PersistentDataType.BOOLEAN, true);

        mermaidTail.setItemMeta(mermaidTailMeta);

        return mermaidTail;
    }

    static ItemStack createGluttonyPants(){
        ItemStack gluttonyPants = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta gluttonyPantsMeta = gluttonyPants.getItemMeta();

        gluttonyPantsMeta.setDisplayName(ChatColor.DARK_GREEN + "The Gluttonous Leggings");
        gluttonyPantsMeta.setLore(Arrays.asList(
            "Devour !"));
        gluttonyPantsMeta.getPersistentDataContainer().set(Keys.CUSTOM_GLUTTONY_PANTS, PersistentDataType.BOOLEAN, true);

        gluttonyPants.setItemMeta(gluttonyPantsMeta);

        return gluttonyPants;
    }

    static ItemStack createIronCladHelmet(){
        ItemStack ironCladHelmet = new ItemStack(Material.IRON_HELMET);
        ItemMeta ironCladHelmetMeta = ironCladHelmet.getItemMeta();

        ironCladHelmetMeta.setDisplayName(ChatColor.GOLD + "IronClad Helmet");
        ironCladHelmetMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Enchanced target tracker and rebreather"));
        AttributeModifier attackModifierClad = new AttributeModifier(UUID.randomUUID(), "Sweep", 0.20, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.PLAYER_SWEEPING_DAMAGE_RATIO, attackModifierClad);
        AttributeModifier oxygenModifierClad = new AttributeModifier(UUID.randomUUID(), "Oxygen", 0.75, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.GENERIC_OXYGEN_BONUS, oxygenModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifierClad);
        ironCladHelmet.setItemMeta(ironCladHelmetMeta);

        return ironCladHelmet;
    }

    static ItemStack createIronCladChestplate(){
        ItemStack ironCladChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta ironCladChestplateMeta = ironCladChestplate.getItemMeta();

        ironCladChestplateMeta.setDisplayName(ChatColor.GOLD + "IronClad Chestplate");
        ironCladChestplateMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Mark II Gladiator type"));
        AttributeModifier attackModifierClad = new AttributeModifier(UUID.randomUUID(), "Strength", 0.20, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, attackModifierClad);
        AttributeModifier attackSpeedModifierClad = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", 0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackSpeedModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifierClad);
        ironCladChestplate.setItemMeta(ironCladChestplateMeta);

        return ironCladChestplate;
    }
    static ItemStack createIronCladLeggings(){
        ItemStack ironCladLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta ironCladLeggingsMeta = ironCladLeggings.getItemMeta();

        ironCladLeggingsMeta.setDisplayName(ChatColor.GOLD + "IronClad Leggings");
        ironCladLeggingsMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Almost Exo... almost"));
        AttributeModifier movementSpeedModifierClad = new AttributeModifier(UUID.randomUUID(), "Speed", 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, movementSpeedModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier healthModifierClad = new AttributeModifier(UUID.randomUUID(), "Health", 0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, healthModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifierClad);
        ironCladLeggings.setItemMeta(ironCladLeggingsMeta);

        return ironCladLeggings;
    }

    static ItemStack createIronCladBoots(){
        ItemStack ironCladBoots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ironCladBootsMeta = ironCladBoots.getItemMeta();

        ironCladBootsMeta.setDisplayName(ChatColor.GOLD + "IronClad Stompers");
        ironCladBootsMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "They feel heavy ? But not ?"));
        AttributeModifier stepModifierClad = new AttributeModifier(UUID.randomUUID(), "Step", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.GENERIC_STEP_HEIGHT, stepModifierClad);
        AttributeModifier jumpModifierClad = new AttributeModifier(UUID.randomUUID(), "Jump", 0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.GENERIC_JUMP_STRENGTH, jumpModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifierClad);
        ironCladBoots.setItemMeta(ironCladBootsMeta);

        return ironCladBoots;
    }

    static ItemStack createNightHelmet(){
        ItemStack nightHelmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta nightHelmetMeta = nightHelmet.getItemMeta();

        nightHelmetMeta.setDisplayName(ChatColor.DARK_BLUE + "Night Helmet");
        nightHelmetMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "A bat person :p"));
        nightHelmetMeta.getPersistentDataContainer().set(Keys.CUSTOM_NIGHT_HELMET, PersistentDataType.BOOLEAN, true);
        nightHelmet.setItemMeta(nightHelmetMeta);

        return nightHelmet;
    }

    static ItemStack createStrigaVeil(){
        ItemStack strigaVeil = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta strigaVeilMeta = strigaVeil.getItemMeta();

        strigaVeilMeta.setDisplayName(ChatColor.DARK_RED + "Striga's veil");
        strigaVeilMeta.setLore(Arrays.asList(
            ChatColor.RED + "Cursed with vampiric power"));
        strigaVeilMeta.getPersistentDataContainer().set(Keys.CUSTOM_STRIGA_VEIL, PersistentDataType.BOOLEAN, true);
        AttributeModifier attackModifierStriga = new AttributeModifier(UUID.randomUUID(), "Strength", 0.20, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        strigaVeilMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, attackModifierStriga);
        AttributeModifier speedModifierStriga = new AttributeModifier(UUID.randomUUID(), "speed", 0.10, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        strigaVeilMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, speedModifierStriga);
        strigaVeil.setItemMeta(strigaVeilMeta);

        return strigaVeil;
    }


    //
    //      MAGIC
    //
    static ItemStack createTeleportSpell(){
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

    static ItemStack createSpiritLeech(){
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

    static ItemStack createObliterateSpell(){
        ItemStack obliterateSpell = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta obliterateSpellMeta = obliterateSpell.getItemMeta();

        obliterateSpellMeta.setDisplayName(ChatColor.RED + "OBLITERATE");
        obliterateSpellMeta.setLore(Arrays.asList(
            "A barbaric overkill"));
        obliterateSpellMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        obliterateSpellMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        obliterateSpellMeta.getPersistentDataContainer().set(Keys.CUSTOM_OBLITERATE, PersistentDataType.BOOLEAN, true);
        obliterateSpell.setItemMeta(obliterateSpellMeta);

        return obliterateSpell;
    }

    static ItemStack createSpiderYeetSpell(){
        ItemStack spiderYeetSpell = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta spiderYeetSpellMeta = spiderYeetSpell.getItemMeta();

        spiderYeetSpellMeta.setDisplayName(ChatColor.GREEN + "Spider" + ChatColor.RED + "Yeeter");
        spiderYeetSpellMeta.setLore(Arrays.asList(
            "BURN IT !"));
        spiderYeetSpellMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        spiderYeetSpellMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        spiderYeetSpellMeta.getPersistentDataContainer().set(Keys.CUSTOM_SPIDER_YEET, PersistentDataType.BOOLEAN, true);
        spiderYeetSpell.setItemMeta(spiderYeetSpellMeta);

        return spiderYeetSpell;
    }

    //
    //      TOOLS
    //
    static ItemStack createHastyPickaxe(){
        ItemStack hastyPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta hastyPickaxeMeta = hastyPickaxe.getItemMeta();

        hastyPickaxeMeta.setDisplayName(ChatColor.YELLOW + "Hasty Pick");
        hastyPickaxeMeta.setLore(Arrays.asList(
            "Higher quality mining"));
        hastyPickaxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_HASTY_TOOL, PersistentDataType.BOOLEAN, true);
        hastyPickaxe.setItemMeta(hastyPickaxeMeta);

        return hastyPickaxe;
    }

    static ItemStack createHastyShovel(){
        ItemStack hastyShovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta hastyShovelMeta = hastyShovel.getItemMeta();

        hastyShovelMeta.setDisplayName(ChatColor.YELLOW + "Hasty Shovel");
        hastyShovelMeta.setLore(Arrays.asList(
            "Higher quality digging"));
        hastyShovelMeta.getPersistentDataContainer().set(Keys.CUSTOM_HASTY_TOOL, PersistentDataType.BOOLEAN, true);
        hastyShovel.setItemMeta(hastyShovelMeta);

        return hastyShovel;
    }

    static ItemStack createHastyAxe(){
        ItemStack hastyAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta hastyAxeMeta = hastyAxe.getItemMeta();

        hastyAxeMeta.setDisplayName(ChatColor.YELLOW + "Hasty Axe");
        hastyAxeMeta.setLore(Arrays.asList(
            "Higher quality chopping"));
        hastyAxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_HASTY_TOOL, PersistentDataType.BOOLEAN, true);
        hastyAxe.setItemMeta(hastyAxeMeta);

        return hastyAxe;
    }

    static ItemStack createTreasureFishingRod(){
        ItemStack treasureFishingRod = new ItemStack(Material.FISHING_ROD);
        ItemMeta treasureFishingRodMeta = treasureFishingRod.getItemMeta();

        treasureFishingRodMeta.setDisplayName(ChatColor.DARK_PURPLE + "Suspicious Fishing Rod");
        treasureFishingRodMeta.addEnchant(Enchantment.LURE, 5, true);
        treasureFishingRodMeta.setLore(Arrays.asList(
            "Are you sure about that ?"));
        treasureFishingRodMeta.getPersistentDataContainer().set(Keys.CUSTOM_TREASURE_FISHING, PersistentDataType.BOOLEAN, true);
        treasureFishingRod.setItemMeta(treasureFishingRodMeta);

        return treasureFishingRod;
    }

    static ItemStack createDwarfPickaxe(){
        ItemStack dwarfPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta dwarfPickaxeMeta = dwarfPickaxe.getItemMeta();

        dwarfPickaxeMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dawi Gem Finder");
        dwarfPickaxeMeta.setLore(Arrays.asList(
            "Do not make a mistake by giving it silk..."));
        dwarfPickaxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_DWARF_PICK, PersistentDataType.BOOLEAN, true);
        dwarfPickaxe.setItemMeta(dwarfPickaxeMeta);

        return dwarfPickaxe;
    }

    static ItemStack createIronBreakerShield(){
        ItemStack ironBreakerShield = new ItemStack(Material.SHIELD);
        ItemMeta ironBreakerShieldMeta = ironBreakerShield.getItemMeta();

        ironBreakerShieldMeta.setDisplayName(ChatColor.GRAY + "Iron Breaker Shield");
        ironBreakerShieldMeta.setLore(Arrays.asList(
            "Might make a difference between life and death..."));
        AttributeModifier armorModifierIronBreaker = new AttributeModifier(UUID.randomUUID(), "Armor", 0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        ironBreakerShieldMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifierIronBreaker);
        AttributeModifier dmgModifierIronBreaker = new AttributeModifier(UUID.randomUUID(), "Dmg", -0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        ironBreakerShieldMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, dmgModifierIronBreaker);
        AttributeModifier hpModifierIronBreaker = new AttributeModifier(UUID.randomUUID(), "Hp", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        ironBreakerShieldMeta.addAttributeModifier(Attribute.GENERIC_MAX_HEALTH, hpModifierIronBreaker);
        ironBreakerShieldMeta.getPersistentDataContainer().set(Keys.CUSTOM_IRON_BREAKER_SHIELD, PersistentDataType.BOOLEAN, true);
        ironBreakerShield.setItemMeta(ironBreakerShieldMeta);

        return ironBreakerShield;
    }

    static ItemStack createBerserkerShield(){
        ItemStack berserkerShield = new ItemStack(Material.SHIELD);
        ItemMeta berserkerShieldMeta = berserkerShield.getItemMeta();

        berserkerShieldMeta.setDisplayName(ChatColor.RED + "Berserker Shield");
        berserkerShieldMeta.setLore(Arrays.asList(
            "Bathe in their blood !"));
        AttributeModifier armorModifierberserkerShield = new AttributeModifier(UUID.randomUUID(), "Armor", -0.25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        berserkerShieldMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, armorModifierberserkerShield);
        AttributeModifier dmgModifierberserkerShield = new AttributeModifier(UUID.randomUUID(), "Dmg", 0.25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        berserkerShieldMeta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, dmgModifierberserkerShield);
        berserkerShieldMeta.getPersistentDataContainer().set(Keys.CUSTOM_BERSERKER_SHIELD, PersistentDataType.BOOLEAN, true);
        berserkerShield.setItemMeta(berserkerShieldMeta);

        return berserkerShield;
    }
    static ItemStack createSoulEssence(){
        ItemStack soulEssence = new ItemStack(Material.POPPED_CHORUS_FRUIT);
        ItemMeta soulEssenceMeta = soulEssence.getItemMeta();

        soulEssenceMeta.setDisplayName(ChatColor.DARK_PURPLE + "Soul Essence");
        soulEssenceMeta.setLore(Arrays.asList(
            "Unstable ? Sure. Effective ? Hell yeah"));
        soulEssenceMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        soulEssenceMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        soulEssenceMeta.getPersistentDataContainer().set(Keys.CUSTOM_SOUL_ESSENCE, PersistentDataType.BOOLEAN, true);
        soulEssence.setItemMeta(soulEssenceMeta);

        return soulEssence;
    }

    //
    //      RACE CRAFTABLES
    //

    //
    //      DWARF HONEY
    //
    static ItemStack createDwarfHoney(){
        ItemStack dwarfHoney = new ItemStack(Material.HONEY_BOTTLE);
        ItemMeta dwarfHoneyMeta = dwarfHoney.getItemMeta();

        dwarfHoneyMeta.setDisplayName(ChatColor.YELLOW + "Dwarf Honey");
        dwarfHoneyMeta.setLore(Arrays.asList(
            "Will knock out even the toughest"));
        dwarfHoneyMeta.getPersistentDataContainer().set(Keys.CUSTOM_DWARF_HONEY, PersistentDataType.BOOLEAN, true);
        dwarfHoney.setItemMeta(dwarfHoneyMeta);

        return dwarfHoney;
    }

    //
    //      WITCH SOUP
    //
    static ItemStack createWitchSoup(){
        ItemStack witchSoup = new ItemStack(Material.MUSHROOM_STEW);
        ItemMeta witchSoupMeta = witchSoup.getItemMeta();

        witchSoupMeta.setDisplayName(ChatColor.DARK_PURPLE + "The most suspicious Soup");
        witchSoupMeta.setLore(Arrays.asList(
            "Poison ? No ? Okay"));
        witchSoupMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        witchSoupMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        witchSoupMeta.getPersistentDataContainer().set(Keys.CUSTOM_WITCH_SOUP, PersistentDataType.BOOLEAN, true);
        witchSoup.setItemMeta(witchSoupMeta);

        return witchSoup;
    }

    //
    //      CURSED ARROW
    //
    static ItemStack createCursedArrow(){
        ItemStack cursedArrow = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta potionMeta = (PotionMeta) cursedArrow.getItemMeta();

        potionMeta.setDisplayName(ChatColor.GREEN + "Cursed Arrow");
        potionMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        potionMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 20 * 80, 1), true);
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 80, 1), true);
        potionMeta.setColor(Color.fromRGB(0, 255, 0));
        cursedArrow.setItemMeta(potionMeta);

        return cursedArrow;
    }

    //
    //      Healing Gas
    //
    static ItemStack createSuperHealingPot(){
        ItemStack superHealth = new ItemStack(Material.LINGERING_POTION);
        PotionMeta superHealthMeta = (PotionMeta) superHealth.getItemMeta();

        superHealthMeta.setDisplayName(ChatColor.RED + "Healing Gas");
        superHealthMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        superHealthMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        superHealthMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 80, 2), true);
        superHealthMeta.setColor(Color.fromRGB(253, 135, 233));
        superHealth.setItemMeta(superHealthMeta);

        return superHealth;
    }

    //
    //      Toxic Gas
    //
    static ItemStack createToxicPot(){
        ItemStack toxicPot = new ItemStack(Material.LINGERING_POTION);
        PotionMeta toxicPotMeta = (PotionMeta) toxicPot.getItemMeta();

        toxicPotMeta.setDisplayName(ChatColor.GREEN + "Toxic Gas");
        toxicPotMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        toxicPotMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        toxicPotMeta.addCustomEffect(new PotionEffect(PotionEffectType.POISON, 20 * 80, 3), true);
        toxicPotMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20 * 80, 3), true);
        toxicPotMeta.setColor(Color.fromRGB(140, 120, 0));
        toxicPot.setItemMeta(toxicPotMeta);

        return toxicPot;
    }

    //
    //      Lucky Clock
    //
    static ItemStack createLuckyClock(){
        ItemStack luckyClock = new ItemStack(Material.CLOCK);
        ItemMeta luckyClockMeta = luckyClock.getItemMeta();

        luckyClockMeta.setDisplayName(ChatColor.GOLD + "Lucky Clock");
        luckyClockMeta.setLore(Arrays.asList(
            "A finer booty be upon ye !"));
        luckyClockMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        luckyClockMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        luckyClockMeta.getPersistentDataContainer().set(Keys.CUSTOM_LUCKY_CLOCK, PersistentDataType.BOOLEAN, true);
        luckyClock.setItemMeta(luckyClockMeta);

        return luckyClock;
    }

    //
    //      Marking Spyglass
    //
    static ItemStack createMarkingSpyglass(){
        ItemStack markingSpyglass = new ItemStack(Material.SPYGLASS);
        ItemMeta markingSpyglassMeta = markingSpyglass.getItemMeta();

        markingSpyglassMeta.setDisplayName(ChatColor.YELLOW + "Markin' Spyglass");
        markingSpyglassMeta.setLore(Arrays.asList(
            "Target spotted !"));
        markingSpyglassMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        markingSpyglassMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        markingSpyglassMeta.getPersistentDataContainer().set(Keys.CUSTOM_MARKING_SPYGLASS, PersistentDataType.BOOLEAN, true);
        markingSpyglass.setItemMeta(markingSpyglassMeta);

        return markingSpyglass;
    }

    //
    //      Grenade
    //
    static ItemStack createGrenade(){
        ItemStack grenade = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta grenadeMeta = grenade.getItemMeta();

        grenadeMeta.setDisplayName(ChatColor.RED + "TnT Stick");
        grenadeMeta.setLore(Arrays.asList(
            ChatColor.RED + "Yeet !",
            ChatColor.DARK_GRAY + "Flint'n steel in off hand to fire it dummy"));
        grenadeMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        grenadeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        grenadeMeta.getPersistentDataContainer().set(Keys.CUSTOM_GRENADE, PersistentDataType.BOOLEAN, true);
        grenade.setItemMeta(grenadeMeta);

        return grenade;
    }

    //
    //      Smoke Bomb
    //
    static ItemStack createSmokeBomb(){
        ItemStack smokeBomb = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta smokeBombMeta = smokeBomb.getItemMeta();

        smokeBombMeta.setDisplayName(ChatColor.DARK_GRAY + "Smoke Bomb");
        smokeBombMeta.setLore(Arrays.asList(
            ChatColor.GRAY + "Confuzing",
            ChatColor.DARK_GRAY + "Flint'n steel in off hand to fire it dummy"));
        smokeBombMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        smokeBombMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        smokeBombMeta.getPersistentDataContainer().set(Keys.CUSTOM_SMOKE_BOMB, PersistentDataType.BOOLEAN, true);
        smokeBomb.setItemMeta(smokeBombMeta);

        return smokeBomb;
    }

    //
    //      Ancient shell
    //
    static ItemStack createAncientShell(){
        ItemStack ancientShell = new ItemStack(Material.NAUTILUS_SHELL);
        ItemMeta ancientShellMeta = ancientShell.getItemMeta();

        ancientShellMeta.setDisplayName(ChatColor.AQUA + "Ancient Shell");
        ancientShellMeta.setLore(Arrays.asList(
            ChatColor.DARK_AQUA + "Calls to the sea"));
        ancientShellMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        ancientShellMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        ancientShellMeta.getPersistentDataContainer().set(Keys.CUSTOM_ANCIENT_SHELL, PersistentDataType.BOOLEAN, true);
        ancientShell.setItemMeta(ancientShellMeta);

        return ancientShell;
    }

    //
    //      Race Hand Crafting Book
    //
    public static ItemStack createRaceBook() {
        ItemStack raceBook = new ItemStack(Material.WRITTEN_BOOK);
        BookMeta raceBookMeta = (BookMeta) raceBook.getItemMeta();

        // Set the title and author of the book
        raceBookMeta.setTitle(ChatColor.DARK_PURPLE + "Race hand crafting");
        raceBookMeta.setAuthor(ChatColor.DARK_PURPLE + "Wentuziak");

        List<String> pages = new ArrayList<>();

        pages.add(
            ChatColor.BOLD + "Ancient Shell\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Nautilus Shell + Prismarine Crystal\n\n" +
            "Who Can Make It:\n" +
            "Animated Fossil"
        );

        pages.add(
            ChatColor.BOLD + "Markin' Spyglass\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Spyglass + Redstone Torch\n\n" +
            "Who Can Make It:\n" +
            "Mewchant"
        );

        pages.add(
            ChatColor.BOLD + "Lucky Clock\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Clock + Mechanical Part\n\n" +
            "Who Can Make It:\n" +
            "Mewchant"
        );

        pages.add(
            ChatColor.BOLD + "Smoke Bombs\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "4 Gunpowder + Black Dye\n\n" +
            "Who Can Make It:\n" +
            "Mewchant"
        );

        pages.add(
            ChatColor.BOLD + "Throwing Bombs\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "8 Gunpowder + String\n\n" +
            "Who Can Make It:\n" +
            "Any Race"
        );

        pages.add(
            ChatColor.BOLD + "Healing Gas\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Ghast Tear + Gold Melon\n\n" +
            "Who Can Make It:\n" +
            "Witch"
        );

        pages.add(
            ChatColor.BOLD + "Toxic Gas\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Pufferfish + Fermented Spider Eye\n\n" +
            "Who Can Make It:\n" +
            "Witch"
        );

        pages.add(
            ChatColor.BOLD + "Most Sus Soup\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Suspicious Stew + Nether Wart\n\n" +
            "Who Can Make It:\n" +
            "Witch"
        );

        pages.add(
            ChatColor.BOLD + "Dwarf Ale\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Honey Bottle + Golden Apple\n\n" +
            "Who Can Make It:\n" +
            "Dwarf"
        );

        raceBookMeta.setPages(pages);
        raceBook.setItemMeta(raceBookMeta);

        return raceBook;
    }
}