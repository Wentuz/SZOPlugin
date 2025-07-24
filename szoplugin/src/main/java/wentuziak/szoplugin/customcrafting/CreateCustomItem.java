package wentuziak.szoplugin.customcrafting;

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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.PotionMeta;

import net.md_5.bungee.api.ChatColor;
import wentuziak.szoplugin.Keys;

public class CreateCustomItem {
    public static ItemStack createSoulFragment(){
        ItemStack soulFragment = new ItemStack(Material.AMETHYST_SHARD);
        ItemMeta soulFragmentMeta = soulFragment.getItemMeta();

        soulFragmentMeta.setDisplayName(ChatColor.DARK_PURPLE + "Life Essence");
        soulFragmentMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        soulFragmentMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        soulFragment.setItemMeta(soulFragmentMeta);

        return soulFragment;
    }

    public static ItemStack createMechanicalParts(){
        ItemStack mechanicalParts = new ItemStack(Material.NETHERITE_SCRAP);
        ItemMeta mechanicalPartsMeta = mechanicalParts.getItemMeta();
        
        mechanicalPartsMeta.setDisplayName(ChatColor.GOLD + "Mechanical Parts");
        mechanicalPartsMeta.setLore(Arrays.asList(
            "Science is just engineering that does not work"));
        mechanicalPartsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);
        mechanicalParts.setItemMeta(mechanicalPartsMeta);

        return mechanicalParts;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createAngelSword(){
        ItemStack angelSword = new ItemStack(Material.GOLDEN_SWORD);
        ItemMeta angelSwordMeta = angelSword.getItemMeta();
        
        angelSwordMeta.setDisplayName(ChatColor.YELLOW + "Seraphim Protocol 02");
        angelSwordMeta.setLore(Arrays.asList(
            ChatColor.YELLOW + "Model designation from a forgotten war"));
        angelSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_ANGEL_SWORD, PersistentDataType.BOOLEAN, true);
        AttributeModifier ArmorModifierAngelSword = new AttributeModifier(UUID.randomUUID(), "Armor", 4.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        angelSwordMeta.addAttributeModifier(Attribute.ARMOR, ArmorModifierAngelSword);
        AttributeModifier attackSpeedModifierAngelSword = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", -2.4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        angelSwordMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifierAngelSword);
        AttributeModifier attackDamageModifierAngelSword = new AttributeModifier(UUID.randomUUID(), "AttackDamage", 8.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        angelSwordMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackDamageModifierAngelSword);
        angelSword.setItemMeta(angelSwordMeta);

        return angelSword;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createDaemonSword(){
        ItemStack daemonSword = new ItemStack(Material.STONE_SWORD);
        ItemMeta daemonSwordMeta = daemonSword.getItemMeta();
        
        daemonSwordMeta.setDisplayName(ChatColor.RED + "Mk-X NullCutter");
        daemonSwordMeta.setLore(Arrays.asList(
            ChatColor.RED + "Swift & sharp"));
        daemonSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_DAEMON_SWORD, PersistentDataType.BOOLEAN, true);
        daemonSwordMeta.addEnchant(Enchantment.FIRE_ASPECT, -1, true);
        AttributeModifier attackSpeedModifierDaemonSword = new AttributeModifier(UUID.randomUUID(), "Armor", -2.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        daemonSwordMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifierDaemonSword);
        AttributeModifier attackDamageModifierDaemonSword = new AttributeModifier(UUID.randomUUID(), "AttackDamage", 10.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        daemonSwordMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackDamageModifierDaemonSword);
        daemonSword.setItemMeta(daemonSwordMeta);

        return daemonSword;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createspellSword(){
        ItemStack spellSword = new ItemStack(Material.IRON_SWORD);
        ItemMeta spellSwordMeta = spellSword.getItemMeta();
        
        spellSwordMeta.setDisplayName(ChatColor.BLUE + "Electro Stabber 06");
        spellSwordMeta.setLore(Arrays.asList(
            ChatColor.RED + "Broken energy core..."));
        spellSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_SPELL_SWORD, PersistentDataType.BOOLEAN, true);
        AttributeModifier attackSpeedModifierSpellSword = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", -1.8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        spellSwordMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifierSpellSword);
        AttributeModifier attackDamageModifierSpellSword = new AttributeModifier(UUID.randomUUID(), "AttackDamage", 7.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        spellSwordMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackDamageModifierSpellSword);
        spellSword.setItemMeta(spellSwordMeta);

        return spellSword;
    }

    public static ItemStack createPyromancerSword(){
        ItemStack pyromancerSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta pyromancerSwordMeta = pyromancerSword.getItemMeta();

        pyromancerSwordMeta.setDisplayName(ChatColor.DARK_BLUE + "Solar Flare 03");
        pyromancerSwordMeta.setLore(Arrays.asList(
            "Very unstable explosion emitter"));
        pyromancerSwordMeta.getPersistentDataContainer().set(Keys.CUSTOM_EXPLOSIVE_SWORD, PersistentDataType.BOOLEAN, true);
        pyromancerSword.setItemMeta(pyromancerSwordMeta);

        return pyromancerSword;
    }

    public static ItemStack createThunderHammer(){
        ItemStack thunderHammer = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta thunderHammerMeta = thunderHammer.getItemMeta();

        thunderHammerMeta.setDisplayName(ChatColor.DARK_BLUE + "StormBringer");
        thunderHammerMeta.setLore(Arrays.asList(
            "Old gods power"));
        thunderHammerMeta.getPersistentDataContainer().set(Keys.CUSTOM_THUNDER_HAMMER, PersistentDataType.BOOLEAN, true);
        thunderHammer.setItemMeta(thunderHammerMeta);

        return thunderHammer;
    }

    public static ItemStack createStinkyStick(){
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

    @SuppressWarnings("deprecation")
    public static ItemStack createScurvyTrident(){
        ItemStack scurvyTrident = new ItemStack(Material.TRIDENT);
        ItemMeta scurvyTridentMeta = scurvyTrident.getItemMeta();

        scurvyTridentMeta.setDisplayName(ChatColor.DARK_BLUE + "Scurvy Trident");
        scurvyTridentMeta.setLore(Arrays.asList(
            "Ahoy !"));
        AttributeModifier attackSpeedModifierScurvyTrident = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", -2.9, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifierScurvyTrident);
        AttributeModifier attackDamageModifierScurvyTrident = new AttributeModifier(UUID.randomUUID(), "AttackDamage", 12.0, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackDamageModifierScurvyTrident);
        AttributeModifier fallModifierScurvyTrident = new AttributeModifier(UUID.randomUUID(), "fall", -0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.FALL_DAMAGE_MULTIPLIER, fallModifierScurvyTrident);
        AttributeModifier flyModifierScurvyTrident = new AttributeModifier(UUID.randomUUID(), "fly", 0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.FLYING_SPEED, flyModifierScurvyTrident);

        AttributeModifier fallModifierScurvyTridentOff = new AttributeModifier(UUID.randomUUID(), "fallOff", -0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.FALL_DAMAGE_MULTIPLIER, fallModifierScurvyTridentOff);
        AttributeModifier flyModifierScurvyTridentOff = new AttributeModifier(UUID.randomUUID(), "flyOff", 0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        scurvyTridentMeta.addAttributeModifier(Attribute.FLYING_SPEED, flyModifierScurvyTridentOff);
        scurvyTrident.setItemMeta(scurvyTridentMeta);

        return scurvyTrident;
    }

    public static ItemStack createMagneticTrident(){
        ItemStack magneticTrident = new ItemStack(Material.TRIDENT);
        ItemMeta magneticTridentMeta = magneticTrident.getItemMeta();

        magneticTridentMeta.setDisplayName(ChatColor.BLUE + "Magnetic" +ChatColor.RED + " Trident");
        magneticTridentMeta.setLore(Arrays.asList(
            "YEET"));
            magneticTridentMeta.getPersistentDataContainer().set(Keys.CUSTOM_MAGNETIC_TRIDENT, PersistentDataType.BOOLEAN, true);
            magneticTrident.setItemMeta(magneticTridentMeta);

        return magneticTrident;
    }

    public static ItemStack createGravityBow(){
        ItemStack gravityBow = new ItemStack(Material.BOW);
        ItemMeta gravityBowMeta = gravityBow.getItemMeta();

        gravityBowMeta.setDisplayName(ChatColor.DARK_BLUE + "Horizon's End");
        gravityBowMeta.setLore(Arrays.asList(
            ChatColor.GREEN + "Marksman's favourite",
            ChatColor.GREEN + "VII"));
        gravityBowMeta.getPersistentDataContainer().set(Keys.CUSTOM_GRAVITY_BOW, PersistentDataType.BOOLEAN, true);
        gravityBow.setItemMeta(gravityBowMeta);

        return gravityBow;
    }

    public static ItemStack createDedalusBow(){
        ItemStack dedalusBow = new ItemStack(Material.BOW);
        ItemMeta dedalusBowMeta = dedalusBow.getItemMeta();

        dedalusBowMeta.setDisplayName(ChatColor.YELLOW + "Dedalus StormBow");
        dedalusBowMeta.setLore(Arrays.asList(
            ChatColor.BLUE + "Anti everything",
            ChatColor.BLUE + "I"));
        dedalusBowMeta.getPersistentDataContainer().set(Keys.CUSTOM_DEDALUS_BOW, PersistentDataType.BOOLEAN, true);
        dedalusBow.setItemMeta(dedalusBowMeta);

        return dedalusBow;
    }

    public static ItemStack createRatBow(){
        ItemStack ratBow = new ItemStack(Material.BOW);
        ItemMeta ratBowMeta = ratBow.getItemMeta();

        ratBowMeta.setDisplayName(ChatColor.YELLOW + "ULTIMATE RAT LAUNCHER");
        ratBowMeta.setLore(Arrays.asList(
            ChatColor.YELLOW + "SHRIEK SHRIEK"));
        ratBowMeta.getPersistentDataContainer().set(Keys.CUSTOM_RAT_BOW, PersistentDataType.BOOLEAN, true);
        ratBow.setItemMeta(ratBowMeta);

        return ratBow;
    }

    public static ItemStack createRepeaterCrossbow(){
        ItemStack repeaterCrossbow = new ItemStack(Material.CROSSBOW);
        ItemMeta repeaterCrossbowMeta = repeaterCrossbow.getItemMeta();

        repeaterCrossbowMeta.setDisplayName(ChatColor.YELLOW + "Repeater Crossbow");
        repeaterCrossbowMeta.setLore(Arrays.asList(
            ChatColor.YELLOW + "Pew pew pew",
            ChatColor.YELLOW + "IX"));
        	
        repeaterCrossbowMeta.addEnchant(Enchantment.QUICK_CHARGE, 5, true);

        repeaterCrossbow.setItemMeta(repeaterCrossbowMeta);

        return repeaterCrossbow;
    }

    public static ItemStack createBouncyCrossbow(){
        ItemStack bouncyCrossbow = new ItemStack(Material.CROSSBOW);
        ItemMeta bouncyCrossbowMeta = bouncyCrossbow.getItemMeta();

        bouncyCrossbowMeta.setDisplayName(ChatColor.RED + "Rioters Crossbow");
        bouncyCrossbowMeta.setLore(Arrays.asList(
            ChatColor.RED + "The latest breach model",
        	ChatColor.RED + "VI"));
            bouncyCrossbowMeta.getPersistentDataContainer().set(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.BOOLEAN, true);
            bouncyCrossbow.setItemMeta(bouncyCrossbowMeta);

        return bouncyCrossbow;
    }


    //
    //      ARMOR
    //
    @SuppressWarnings("deprecation")
    public static ItemStack createHermesBoots(){
        ItemStack hermesBoots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta hermesBootsMeta = hermesBoots.getItemMeta();

        hermesBootsMeta.setDisplayName(ChatColor.DARK_GREEN + "Hermes Boots");
        AttributeModifier movementSpeedModifierHermes = new AttributeModifier(UUID.randomUUID(), "Speed", 0.15, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        hermesBootsMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, movementSpeedModifierHermes);
        AttributeModifier stepModifierHermes = new AttributeModifier(UUID.randomUUID(), "Step", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        hermesBootsMeta.addAttributeModifier(Attribute.STEP_HEIGHT, stepModifierHermes);
        ((LeatherArmorMeta) hermesBootsMeta).setColor(Color.GREEN);
        hermesBoots.setItemMeta(hermesBootsMeta);

        return hermesBoots;
    }
    
    @SuppressWarnings("deprecation")
    public static ItemStack createGoldenSigilBoots(){
        ItemStack goldenSigil = new ItemStack(Material.GOLDEN_BOOTS);
        ItemMeta goldenSigilMeta = goldenSigil.getItemMeta();

        goldenSigilMeta.setDisplayName(ChatColor.YELLOW + "Golden Sigil Boots");
        goldenSigilMeta.setLore(Arrays.asList(
                ChatColor.GOLD + "Each step etched in sanctity.",
            	ChatColor.GOLD + "<3 <3"));
        AttributeModifier armorModifierGoldenSigil = new AttributeModifier(UUID.randomUUID(), "Armor", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        goldenSigilMeta.addAttributeModifier(Attribute.ARMOR, armorModifierGoldenSigil);
        AttributeModifier toughnessModifierGoldenSigil = new AttributeModifier(UUID.randomUUID(), "Toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        goldenSigilMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierGoldenSigil);
        goldenSigilMeta.getPersistentDataContainer().set(Keys.CUSTOM_GOLDEN_SIGIL, PersistentDataType.BOOLEAN, true);

        goldenSigil.setItemMeta(goldenSigilMeta);

        return goldenSigil;
    }

    public static ItemStack createRamCap(){
        ItemStack ramCap = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta ramCapMeta = ramCap.getItemMeta();

        ramCapMeta.setDisplayName(ChatColor.RED + "Barani web");
        ramCapMeta.setLore(Arrays.asList(
            "You are the battle RAM !"));
            ramCapMeta.getPersistentDataContainer().set(Keys.CUSTOM_RAM_CAP, PersistentDataType.BOOLEAN, true);
            ramCap.setItemMeta(ramCapMeta);

        return ramCap;
    }

    public static ItemStack createJetBoots(){
        ItemStack jetBoots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta jetBootsMeta = jetBoots.getItemMeta();

        jetBootsMeta.setDisplayName(ChatColor.GRAY + "Jet Boots");
        jetBootsMeta.setLore(Arrays.asList(
            "A reliable way to go up"));
        AttributeModifier fallModifierJetBoots = new AttributeModifier(UUID.randomUUID(), "Fall", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        jetBootsMeta.addAttributeModifier(Attribute.SAFE_FALL_DISTANCE, fallModifierJetBoots);
        ((LeatherArmorMeta) jetBootsMeta).setColor(Color.SILVER);
        jetBootsMeta.getPersistentDataContainer().set(Keys.CUSTOM_JET_BOOTS, PersistentDataType.BOOLEAN, true);

        jetBoots.setItemMeta(jetBootsMeta);

        return jetBoots;
    }

    public static ItemStack createMagicBoots(){
        ItemStack magicBoots = new ItemStack(Material.LEATHER_BOOTS);
        ItemMeta magicBootsMeta = magicBoots.getItemMeta();

        magicBootsMeta.setDisplayName(ChatColor.BLUE + "Old Man's Sneakers");
        magicBootsMeta.setLore(Arrays.asList(
            "Boosts old scrolls"));
        ((LeatherArmorMeta) magicBootsMeta).setColor(Color.BLUE);
        magicBootsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MAGIC_BOOTS, PersistentDataType.BOOLEAN, true);

        magicBoots.setItemMeta(magicBootsMeta);

        return magicBoots;
    }

    public static ItemStack createExplosiveChest(){
        ItemStack explosiveChest = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta explosiveChestMeta = explosiveChest.getItemMeta();

        explosiveChestMeta.setDisplayName(ChatColor.DARK_RED + "Bombardiers Vest");
        explosiveChestMeta.setLore(Arrays.asList(
            "Reactive defence mechanism 02"));
        explosiveChestMeta.getPersistentDataContainer().set(Keys.CUSTOM_EXPLOSIVE_CHEST, PersistentDataType.BOOLEAN, true);

        explosiveChest.setItemMeta(explosiveChestMeta);

        return explosiveChest;
    }

    public static ItemStack createReflectiveChestpiece(){
        ItemStack reflectiveChestpiece = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta reflectiveChestpieceMeta = reflectiveChestpiece.getItemMeta();

        reflectiveChestpieceMeta.setDisplayName(ChatColor.BLUE + "Reflective Chestpiece");
        reflectiveChestpieceMeta.setLore(Arrays.asList(
            "Stings a lot. Just don't try and lick it"));
            reflectiveChestpieceMeta.getPersistentDataContainer().set(Keys.CUSTOM_REFLECTIVE_CHESTPIECE, PersistentDataType.BOOLEAN, true);

        reflectiveChestpiece.setItemMeta(reflectiveChestpieceMeta);

        return reflectiveChestpiece;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createGolemChest(){
        ItemStack golemChest = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta golemChestMeta = golemChest.getItemMeta();

        golemChestMeta.setDisplayName(ChatColor.DARK_BLUE + "Sentinels Chestplate");
        golemChestMeta.setLore(Arrays.asList(
            "Incredible resilience while crouching"));
        AttributeModifier armorModifierGolem = new AttributeModifier(UUID.randomUUID(), "Armor", 10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        golemChestMeta.addAttributeModifier(Attribute.ARMOR, armorModifierGolem);
        AttributeModifier healthModifierGolem = new AttributeModifier(UUID.randomUUID(), "Health", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        golemChestMeta.addAttributeModifier(Attribute.MAX_HEALTH, healthModifierGolem);
        AttributeModifier speedModifierGolem = new AttributeModifier(UUID.randomUUID(), "Speed", -0.1, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST);
        golemChestMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, speedModifierGolem);
        golemChestMeta.getPersistentDataContainer().set(Keys.CUSTOM_GOLEM_CHEST, PersistentDataType.BOOLEAN, true);

        golemChest.setItemMeta(golemChestMeta);

        return golemChest;
    }

    public static ItemStack createNinjaPant(){
        ItemStack ninjaPant = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta ninjaPantMeta = ninjaPant.getItemMeta();

        ninjaPantMeta.setDisplayName(ChatColor.GRAY + "Cat Burglars");
        ninjaPantMeta.setLore(Arrays.asList(
            "reactive defence mechanism 05"));
            ninjaPantMeta.getPersistentDataContainer().set(Keys.CUSTOM_NINJA_PANT, PersistentDataType.BOOLEAN, true);

        ninjaPant.setItemMeta(ninjaPantMeta);

        return ninjaPant;
    }
    
    public static ItemStack createFastingBelt(){
        ItemStack fastingBelt = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta fastingBeltMeta = fastingBelt.getItemMeta();

        fastingBeltMeta.setDisplayName(ChatColor.RED + "Fasting Belt");
        fastingBeltMeta.setLore(Arrays.asList(
            "The hungrier one is ..."));
        AttributeModifier armorModifierFastingBelt = new AttributeModifier(UUID.randomUUID(), "Armor", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        fastingBeltMeta.addAttributeModifier(Attribute.ARMOR, armorModifierFastingBelt);
        AttributeModifier armorPercentModifierFastingBelt = new AttributeModifier(UUID.randomUUID(), "ArmorPercent", 0.15, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS);
        fastingBeltMeta.addAttributeModifier(Attribute.ARMOR, armorPercentModifierFastingBelt);
        fastingBeltMeta.getPersistentDataContainer().set(Keys.CUSTOM_FASTING_BELT, PersistentDataType.BOOLEAN, true);

        fastingBelt.setItemMeta(fastingBeltMeta);

        return fastingBelt;
    }

    public static ItemStack createCerberusChain(){
        ItemStack cerberusChain = new ItemStack(Material.CHAINMAIL_LEGGINGS);
        ItemMeta cerberusChainMeta = cerberusChain.getItemMeta();

        cerberusChainMeta.setDisplayName(ChatColor.DARK_GRAY + "Cerberus Chain");
        cerberusChainMeta.setLore(Arrays.asList(
            "Spectral guardian bound to your ..."));
            AttributeModifier armorModifierCerberusChain = new AttributeModifier(UUID.randomUUID(), "Armor", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
            cerberusChainMeta.addAttributeModifier(Attribute.ARMOR, armorModifierCerberusChain);
            AttributeModifier armorPercentModifierCerberusChain = new AttributeModifier(UUID.randomUUID(), "ArmorPercent", 0.33, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS);
            cerberusChainMeta.addAttributeModifier(Attribute.ARMOR, armorPercentModifierCerberusChain);
            AttributeModifier speedModifierCerberusChain = new AttributeModifier(UUID.randomUUID(), "Speed", 0.03, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
            cerberusChainMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, speedModifierCerberusChain);
            cerberusChainMeta.getPersistentDataContainer().set(Keys.CUSTOM_CERBERUS_CHAIN, PersistentDataType.BOOLEAN, true);

        cerberusChain.setItemMeta(cerberusChainMeta);

        return cerberusChain;
    }

    public static ItemStack createGluttonyPants(){
        ItemStack gluttonyPants = new ItemStack(Material.DIAMOND_LEGGINGS);
        ItemMeta gluttonyPantsMeta = gluttonyPants.getItemMeta();

        gluttonyPantsMeta.setDisplayName(ChatColor.DARK_GREEN + "The Gluttonous Leggings");
        gluttonyPantsMeta.setLore(Arrays.asList(
            "For the hungry sausage you are"));
        gluttonyPantsMeta.getPersistentDataContainer().set(Keys.CUSTOM_GLUTTONY_PANTS, PersistentDataType.BOOLEAN, true);

        gluttonyPants.setItemMeta(gluttonyPantsMeta);

        return gluttonyPants;
    }

    public static ItemStack createArmourPiercingAxe(){
        ItemStack armourPiercingAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta armourPiercingAxeMeta = armourPiercingAxe.getItemMeta();

        armourPiercingAxeMeta.setDisplayName(ChatColor.RED + "Jack");
        armourPiercingAxeMeta.setLore(Arrays.asList(
            "London No.1",
        		"Drops mob heads"));
        armourPiercingAxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_ARMOR_PIERCER, PersistentDataType.BOOLEAN, true);

        armourPiercingAxe.setItemMeta(armourPiercingAxeMeta);

        return armourPiercingAxe;
    }

    public static ItemStack createLongSword(){
        ItemStack longSword = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta longSwordMeta = longSword.getItemMeta();

        longSwordMeta.setDisplayName(ChatColor.AQUA + "Long Sword");

        AttributeModifier reachModifier = new AttributeModifier(UUID.randomUUID(), "reachBlock", 0.25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
        longSwordMeta.addAttributeModifier(Attribute.BLOCK_INTERACTION_RANGE, reachModifier);
        AttributeModifier reachEntityModifier = new AttributeModifier(UUID.randomUUID(), "reachEntity", 0.25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HAND);
        longSwordMeta.addAttributeModifier(Attribute.ENTITY_INTERACTION_RANGE, reachEntityModifier);
        AttributeModifier speedModifier = new AttributeModifier(UUID.randomUUID(), "Speed", -3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        longSwordMeta.addAttributeModifier(Attribute.ATTACK_SPEED, speedModifier);
        AttributeModifier attackModifier = new AttributeModifier(UUID.randomUUID(), "dmg", 10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND);
        longSwordMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackModifier);
        
        longSword.setItemMeta(longSwordMeta);

        return longSword;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createMiningHat(){
        ItemStack miningHat = new ItemStack(Material.LEATHER_HELMET);
        ItemMeta miningHatMeta = miningHat.getItemMeta();

        miningHatMeta.setDisplayName(ChatColor.YELLOW + "Mining Helmet");
        ((LeatherArmorMeta) miningHatMeta).setColor(Color.ORANGE);


        miningHatMeta.getPersistentDataContainer().set(Keys.CUSTOM_JUMP_PACK, PersistentDataType.BOOLEAN, true);
        AttributeModifier breakModifier = new AttributeModifier(UUID.randomUUID(), "breakBlock", 0.40, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        AttributeModifier reachModifier = new AttributeModifier(UUID.randomUUID(), "reachBlock", 0.50, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        miningHatMeta.addAttributeModifier(Attribute.BLOCK_BREAK_SPEED, breakModifier);
        miningHatMeta.addAttributeModifier(Attribute.BLOCK_INTERACTION_RANGE, reachModifier);
        miningHat.setItemMeta(miningHatMeta);

        return miningHat;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createIronCladHelmet(){
        ItemStack ironCladHelmet = new ItemStack(Material.IRON_HELMET);
        ItemMeta ironCladHelmetMeta = ironCladHelmet.getItemMeta();

        ironCladHelmetMeta.setDisplayName(ChatColor.GOLD + "IronClad Helmet");
        ironCladHelmetMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Enchanced target tracker and rebreather"));
        AttributeModifier attackModifierClad = new AttributeModifier(UUID.randomUUID(), "Sweep", 0.20, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.SWEEPING_DAMAGE_RATIO, attackModifierClad);
        AttributeModifier oxygenModifierClad = new AttributeModifier(UUID.randomUUID(), "Oxygen", 0.75, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.OXYGEN_BONUS, oxygenModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        ironCladHelmetMeta.addAttributeModifier(Attribute.ARMOR, armorModifierClad);
        ironCladHelmetMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        ironCladHelmet.setItemMeta(ironCladHelmetMeta);

        return ironCladHelmet;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createIronCladChestplate(){
        ItemStack ironCladChestplate = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta ironCladChestplateMeta = ironCladChestplate.getItemMeta();

        ironCladChestplateMeta.setDisplayName(ChatColor.GOLD + "IronClad Chestplate");
        ironCladChestplateMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "MK II Gladiator type"));
        AttributeModifier attackModifierClad = new AttributeModifier(UUID.randomUUID(), "Strength", 0.20, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackModifierClad);
        AttributeModifier attackSpeedModifierClad = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", 0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        ironCladChestplateMeta.addAttributeModifier(Attribute.ARMOR, armorModifierClad);
        ironCladChestplateMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        ironCladChestplate.setItemMeta(ironCladChestplateMeta);

        return ironCladChestplate;
    }
    @SuppressWarnings("deprecation")
    public static ItemStack createIronCladLeggings(){
        ItemStack ironCladLeggings = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta ironCladLeggingsMeta = ironCladLeggings.getItemMeta();

        ironCladLeggingsMeta.setDisplayName(ChatColor.GOLD + "IronClad Leggings");
        ironCladLeggingsMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Almost Exo... almost"));
        AttributeModifier movementSpeedModifierClad = new AttributeModifier(UUID.randomUUID(), "Speed", 0.02, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, movementSpeedModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier healthModifierClad = new AttributeModifier(UUID.randomUUID(), "Health", 0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.MAX_HEALTH, healthModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        ironCladLeggingsMeta.addAttributeModifier(Attribute.ARMOR, armorModifierClad);
        ironCladLeggingsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        ironCladLeggings.setItemMeta(ironCladLeggingsMeta);

        return ironCladLeggings;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createIronCladBoots(){
        ItemStack ironCladBoots = new ItemStack(Material.IRON_BOOTS);
        ItemMeta ironCladBootsMeta = ironCladBoots.getItemMeta();

        ironCladBootsMeta.setDisplayName(ChatColor.GOLD + "IronClad Stompers");
        ironCladBootsMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Mud is no longer your concern"));
        AttributeModifier stepModifierClad = new AttributeModifier(UUID.randomUUID(), "Step", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.STEP_HEIGHT, stepModifierClad);
        AttributeModifier jumpModifierClad = new AttributeModifier(UUID.randomUUID(), "Jump", 0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.JUMP_STRENGTH, jumpModifierClad);
        AttributeModifier toughnessModifierClad = new AttributeModifier(UUID.randomUUID(), "Toughness", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierClad);
        AttributeModifier armorModifierClad = new AttributeModifier(UUID.randomUUID(), "Armor", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        ironCladBootsMeta.addAttributeModifier(Attribute.ARMOR, armorModifierClad);
        ironCladBootsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        ironCladBoots.setItemMeta(ironCladBootsMeta);

        return ironCladBoots;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createMiskaHelmet(){
        ItemStack miskaHelmet = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta miskaHelmetMeta = miskaHelmet.getItemMeta();

        miskaHelmetMeta.setDisplayName(ChatColor.RED + "Miska Helmet");
        miskaHelmetMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Luck or just skill?"));

        AttributeModifier toughnessModifierMiska = new AttributeModifier(UUID.randomUUID(), "Toughness", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        miskaHelmetMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierMiska);
        AttributeModifier armorModifierMiska = new AttributeModifier(UUID.randomUUID(), "Armor", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        miskaHelmetMeta.addAttributeModifier(Attribute.ARMOR, armorModifierMiska);
        AttributeModifier luckModifierMiska = new AttributeModifier(UUID.randomUUID(), "Luck", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        miskaHelmetMeta.addAttributeModifier(Attribute.LUCK, luckModifierMiska);
        AttributeModifier knockbackModifierMiska = new AttributeModifier(UUID.randomUUID(), "Knockback", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD);
        miskaHelmetMeta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, knockbackModifierMiska);
        miskaHelmetMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        miskaHelmet.setItemMeta(miskaHelmetMeta);

        return miskaHelmet;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createMiskaChestplate(){
        ItemStack miskaChestplate = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta miskaChestplateMeta = miskaChestplate.getItemMeta();

        miskaChestplateMeta.setDisplayName(ChatColor.RED + "Miska Chestplate");
        miskaChestplateMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Mark I Thorn type"));

        AttributeModifier toughnessModifierMiska = new AttributeModifier(UUID.randomUUID(), "Toughness", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        miskaChestplateMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierMiska);
        AttributeModifier armorModifierMiska = new AttributeModifier(UUID.randomUUID(), "Armor", 10, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        miskaChestplateMeta.addAttributeModifier(Attribute.ARMOR, armorModifierMiska);
        AttributeModifier attackModifierMiska = new AttributeModifier(UUID.randomUUID(), "Attack", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        miskaChestplateMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackModifierMiska);
        AttributeModifier attackSpeedModifierMiska = new AttributeModifier(UUID.randomUUID(), "AttackSpeed", 0.2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        miskaChestplateMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackSpeedModifierMiska);
        AttributeModifier knockbackModifierMiska = new AttributeModifier(UUID.randomUUID(), "Knockback", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
        miskaChestplateMeta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, knockbackModifierMiska);
        miskaChestplateMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        miskaChestplate.setItemMeta(miskaChestplateMeta);

        return miskaChestplate;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createMiskaLeggings(){
        ItemStack miskaLeggings = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta miskaLeggingsMeta = miskaLeggings.getItemMeta();

        miskaLeggingsMeta.setDisplayName(ChatColor.RED + "Miska Leggings");
        miskaLeggingsMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Belt made from skulls"));

        AttributeModifier toughnessModifierMiska = new AttributeModifier(UUID.randomUUID(), "Toughness", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        miskaLeggingsMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierMiska);
        AttributeModifier armorModifierMiska = new AttributeModifier(UUID.randomUUID(), "Armor", 8, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        miskaLeggingsMeta.addAttributeModifier(Attribute.ARMOR, armorModifierMiska);
        AttributeModifier speedModifierMiska = new AttributeModifier(UUID.randomUUID(), "speed", 0.01, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        miskaLeggingsMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, speedModifierMiska);
        AttributeModifier knockbackModifierMiska = new AttributeModifier(UUID.randomUUID(), "knockback", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        miskaLeggingsMeta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, knockbackModifierMiska);
        miskaLeggingsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        miskaLeggings.setItemMeta(miskaLeggingsMeta);

        return miskaLeggings;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createMiskaBoots(){
        ItemStack miskaBoots = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta miskaBootsMeta = miskaBoots.getItemMeta();

        miskaBootsMeta.setDisplayName(ChatColor.RED + "Miska Boots");
        miskaBootsMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Maybe they'll catch you"));

        AttributeModifier toughnessModifierMiska = new AttributeModifier(UUID.randomUUID(), "Toughness", 3, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        miskaBootsMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifierMiska);
        AttributeModifier armorModifierMiska = new AttributeModifier(UUID.randomUUID(), "Armor", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        miskaBootsMeta.addAttributeModifier(Attribute.ARMOR, armorModifierMiska);
        AttributeModifier fallModifierMiska = new AttributeModifier(UUID.randomUUID(), "Fall", -0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.FEET);
        miskaBootsMeta.addAttributeModifier(Attribute.FALL_DAMAGE_MULTIPLIER, fallModifierMiska);
        AttributeModifier knockbackModifierMiska = new AttributeModifier(UUID.randomUUID(), "knockback", 0.1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET);
        miskaBootsMeta.addAttributeModifier(Attribute.KNOCKBACK_RESISTANCE, knockbackModifierMiska);
        miskaBootsMeta.getPersistentDataContainer().set(Keys.CUSTOM_MECHANICAL_PARTS, PersistentDataType.BOOLEAN, true);

        miskaBoots.setItemMeta(miskaBootsMeta);

        return miskaBoots;
    }

    public static ItemStack createWitchHelmet(){
        ItemStack witchHelmet = new ItemStack(Material.DIAMOND_HELMET);
        ItemMeta witchHelmetMeta = witchHelmet.getItemMeta();

        witchHelmetMeta.setDisplayName(ChatColor.GRAY + "StimHelm ver2.0");
        witchHelmetMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "A relic of ancient biotech"));
        witchHelmetMeta.getPersistentDataContainer().set(Keys.CUSTOM_WITCH_HELMET, PersistentDataType.BOOLEAN, true);
        witchHelmet.setItemMeta(witchHelmetMeta);

        return witchHelmet;
    }

    public static ItemStack createGuardingVest(){
        ItemStack guardingVest = new ItemStack(Material.GOLDEN_CHESTPLATE);
        ItemMeta guardingVestMeta = guardingVest.getItemMeta();

        guardingVestMeta.setDisplayName(ChatColor.YELLOW + "HeartCore Aegis");
        guardingVestMeta.setLore(Arrays.asList(
            ChatColor.RED + "Lifeguard protocol ... long forgotten"));
            AttributeModifier armorModifier = new AttributeModifier(UUID.randomUUID(), "Armor", 6, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            guardingVestMeta.addAttributeModifier(Attribute.ARMOR, armorModifier);
            AttributeModifier toughnessModifier = new AttributeModifier(UUID.randomUUID(), "Toughness", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            guardingVestMeta.addAttributeModifier(Attribute.ARMOR_TOUGHNESS, toughnessModifier);
            AttributeModifier healthModifier = new AttributeModifier(UUID.randomUUID(), "Health", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST);
            guardingVestMeta.addAttributeModifier(Attribute.MAX_HEALTH, healthModifier);
            guardingVestMeta.getPersistentDataContainer().set(Keys.CUSTOM_GUARDING_VEST, PersistentDataType.BOOLEAN, true);
            guardingVest.setItemMeta(guardingVestMeta);

        return guardingVest;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createStrigaVeil(){
        ItemStack strigaVeil = new ItemStack(Material.CHAINMAIL_HELMET);
        ItemMeta strigaVeilMeta = strigaVeil.getItemMeta();

        strigaVeilMeta.setDisplayName(ChatColor.DARK_RED + "Striga's veil");
        strigaVeilMeta.setLore(Arrays.asList(
            ChatColor.RED + "Siphon your foe"));
        strigaVeilMeta.getPersistentDataContainer().set(Keys.CUSTOM_STRIGA_VEIL, PersistentDataType.BOOLEAN, true);
        AttributeModifier attackModifierStriga = new AttributeModifier(UUID.randomUUID(), "Strength", 0.20, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        strigaVeilMeta.addAttributeModifier(Attribute.ATTACK_SPEED, attackModifierStriga);
        AttributeModifier speedModifierStriga = new AttributeModifier(UUID.randomUUID(), "speed", 0.10, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.HEAD);
        strigaVeilMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, speedModifierStriga);
        strigaVeil.setItemMeta(strigaVeilMeta);

        return strigaVeil;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createJumpPack(){
        ItemStack jumpPack = new ItemStack(Material.LEATHER_LEGGINGS);
        ItemMeta jumpPackMeta = jumpPack.getItemMeta();

        jumpPackMeta.setDisplayName(ChatColor.YELLOW + "Jump Pack");
        jumpPackMeta.setLore(Arrays.asList(
            "kato humpo"));
            ((LeatherArmorMeta) jumpPackMeta).setColor(Color.GRAY);


        jumpPackMeta.getPersistentDataContainer().set(Keys.CUSTOM_JUMP_PACK, PersistentDataType.BOOLEAN, true);
        AttributeModifier armorModifier = new AttributeModifier(UUID.randomUUID(), "Armor", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS);
        jumpPackMeta.addAttributeModifier(Attribute.ARMOR, armorModifier);
        AttributeModifier speedModifier = new AttributeModifier(UUID.randomUUID(), "Speed", 0.10, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS);
        jumpPackMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, speedModifier);
        jumpPack.setItemMeta(jumpPackMeta);

        return jumpPack;
    }

    public static ItemStack createWalkers(){
        ItemStack walkers = new ItemStack(Material.DIAMOND_BOOTS);
        ItemMeta walkersMeta = walkers.getItemMeta();

        walkersMeta.setDisplayName(ChatColor.BLUE + "Walkies");
        walkersMeta.setLore(Arrays.asList(
            "Stomp on " + ChatColor.RED + "lava",
            "Stomp on " + ChatColor.BLUE + "Waer"));
            walkersMeta.getPersistentDataContainer().set(Keys.CUSTOM_WALKERS, PersistentDataType.BOOLEAN, true);
            walkers.setItemMeta(walkersMeta);

        return walkers;
    }


    //
    //      MAGIC
    //
    public static ItemStack createTeleportSpell(){
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

    public static ItemStack createSpiritLeech(){
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

    public static ItemStack createWebTrap(){
        ItemStack webTrap = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta webTrapMeta = webTrap.getItemMeta();

        webTrapMeta.setDisplayName(ChatColor.DARK_PURPLE + "Web Trap");
        webTrapMeta.setLore(Arrays.asList(
            "Australian spider season"));
        webTrapMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        webTrapMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        webTrapMeta.getPersistentDataContainer().set(Keys.CUSTOM_WEB_TRAP, PersistentDataType.BOOLEAN, true);
        webTrap.setItemMeta(webTrapMeta);

        return webTrap;
    }

    public static ItemStack createObliterateSpell(){
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

    public static ItemStack createThunderSpell(){
        ItemStack thunderSpell = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta thunderSpellMeta = thunderSpell.getItemMeta();

        thunderSpellMeta.setDisplayName(ChatColor.BLUE + "Peron's Wrath");
        thunderSpellMeta.setLore(Arrays.asList(
            "Storm is comming"));
            thunderSpellMeta.addEnchant(Enchantment.CHANNELING, 1, true);
            thunderSpellMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            thunderSpellMeta.getPersistentDataContainer().set(Keys.CUSTOM_MAGIC_STORM, PersistentDataType.BOOLEAN, true);
            thunderSpell.setItemMeta(thunderSpellMeta);

        return thunderSpell;
    }

    public static ItemStack createSpiderYeetSpell(){
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
    public static ItemStack createHastyPickaxe(){
        ItemStack hastyPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta hastyPickaxeMeta = hastyPickaxe.getItemMeta();

        hastyPickaxeMeta.setDisplayName(ChatColor.YELLOW + "Hasty Pick");
        hastyPickaxeMeta.setLore(Arrays.asList(
            "Higher quality mining"));
        hastyPickaxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_HASTY_TOOL, PersistentDataType.BOOLEAN, true);
        hastyPickaxe.setItemMeta(hastyPickaxeMeta);

        return hastyPickaxe;
    }

    public static ItemStack createHastyShovel(){
        ItemStack hastyShovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta hastyShovelMeta = hastyShovel.getItemMeta();

        hastyShovelMeta.setDisplayName(ChatColor.YELLOW + "Hasty Shovel");
        hastyShovelMeta.setLore(Arrays.asList(
            "Higher quality digging"));
        hastyShovelMeta.getPersistentDataContainer().set(Keys.CUSTOM_HASTY_TOOL, PersistentDataType.BOOLEAN, true);
        hastyShovel.setItemMeta(hastyShovelMeta);

        return hastyShovel;
    }

    public static ItemStack createHastyAxe(){
        ItemStack hastyAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta hastyAxeMeta = hastyAxe.getItemMeta();

        hastyAxeMeta.setDisplayName(ChatColor.YELLOW + "Hasty Axe");
        hastyAxeMeta.setLore(Arrays.asList(
            "Higher quality chopping"));
        hastyAxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_HASTY_TOOL, PersistentDataType.BOOLEAN, true);
        hastyAxe.setItemMeta(hastyAxeMeta);

        return hastyAxe;
    }

    public static ItemStack createRichAxe(){
        ItemStack richAxe = new ItemStack(Material.DIAMOND_AXE);
        ItemMeta richAxeMeta = richAxe.getItemMeta();

        richAxeMeta.setDisplayName(ChatColor.RED + "Mourning wood Ax");
        richAxeMeta.setLore(Arrays.asList(
            "... chop chop"));
        richAxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_RICH_AX, PersistentDataType.BOOLEAN, true);
        richAxe.setItemMeta(richAxeMeta);

        return richAxe;
    }

    public static ItemStack createRichShovel(){
        ItemStack richShovel = new ItemStack(Material.DIAMOND_SHOVEL);
        ItemMeta richShovelMeta = richShovel.getItemMeta();

        richShovelMeta.setDisplayName(ChatColor.GOLD + "Booty Shovel");
        richShovelMeta.setLore(Arrays.asList(
            "... diggy diggy"));
        richShovelMeta.getPersistentDataContainer().set(Keys.CUSTOM_RICH_SHOVEL, PersistentDataType.BOOLEAN, true);
        richShovel.setItemMeta(richShovelMeta);

        return richShovel;
    }

    public static ItemStack createSuperShears(){
        ItemStack superShears = new ItemStack(Material.SHEARS);
        ItemMeta superShearsMeta = superShears.getItemMeta();

        superShearsMeta.setDisplayName(ChatColor.DARK_BLUE + "Gottish " + ChatColor.DARK_GREEN + "Shearsh");
        superShearsMeta.setLore(Arrays.asList(
            "emo."));
        superShearsMeta.getPersistentDataContainer().set(Keys.CUSTOM_SUPER_SHEARS, PersistentDataType.BOOLEAN, true);
        superShears.setItemMeta(superShearsMeta);

        return superShears;
    }

    public static ItemStack createHomecomingCompass(){
        ItemStack homecomingCompass = new ItemStack(Material.COMPASS);
        ItemMeta homecomingCompassMeta = homecomingCompass.getItemMeta();

        homecomingCompassMeta.setDisplayName(ChatColor.DARK_RED + "Homecoming Compass");
        homecomingCompassMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "It just wants you safe"));
        homecomingCompassMeta.getPersistentDataContainer().set(Keys.CUSTOM_HOMECOMING_COMPASS, PersistentDataType.BOOLEAN, true);
        homecomingCompass.setItemMeta(homecomingCompassMeta);

        return homecomingCompass;
    }

    public static ItemStack createDeathCaller(){
        ItemStack deathCaller = new ItemStack(Material.RECOVERY_COMPASS);
        ItemMeta deathCallerMeta = deathCaller.getItemMeta();

        deathCallerMeta.setDisplayName(ChatColor.DARK_BLUE + "Death Caller");
        deathCallerMeta.setLore(Arrays.asList(
            ChatColor.DARK_AQUA + "It knows how and where it happened"));
        deathCallerMeta.getPersistentDataContainer().set(Keys.CUSTOM_DEATH_CALLER, PersistentDataType.BOOLEAN, true);
        deathCaller.setItemMeta(deathCallerMeta);

        return deathCaller;
    }

    public static ItemStack createTreasureFishingRod(){
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

    public static ItemStack createDwarfPickaxe(){
        ItemStack dwarfPickaxe = new ItemStack(Material.DIAMOND_PICKAXE);
        ItemMeta dwarfPickaxeMeta = dwarfPickaxe.getItemMeta();

        dwarfPickaxeMeta.setDisplayName(ChatColor.DARK_PURPLE + "Dawi Gem Finder");
        dwarfPickaxeMeta.setLore(Arrays.asList(
            "Do not make a mistake by giving it silk..."));
        dwarfPickaxeMeta.getPersistentDataContainer().set(Keys.CUSTOM_DWARF_PICK, PersistentDataType.BOOLEAN, true);
        dwarfPickaxe.setItemMeta(dwarfPickaxeMeta);

        return dwarfPickaxe;
    }

    public static ItemStack createSuperHoe(){
        ItemStack superHoe = new ItemStack(Material.DIAMOND_HOE);
        ItemMeta superHoeMeta = superHoe.getItemMeta();

        superHoeMeta.setDisplayName(ChatColor.DARK_PURPLE + "Your mom");
        superHoeMeta.setLore(Arrays.asList(
            "Perfect hoe"));
        superHoeMeta.getPersistentDataContainer().set(Keys.CUSTOM_SUPER_HOE, PersistentDataType.BOOLEAN, true);
        superHoe.setItemMeta(superHoeMeta);

        return superHoe;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createIronBreakerShield(){
        ItemStack ironBreakerShield = new ItemStack(Material.SHIELD);
        ItemMeta ironBreakerShieldMeta = ironBreakerShield.getItemMeta();

        ironBreakerShieldMeta.setDisplayName(ChatColor.GRAY + "Iron Breaker Shield");
        ironBreakerShieldMeta.setLore(Arrays.asList(
            "Might make a difference between life and death..."));
        AttributeModifier armorModifierIronBreaker = new AttributeModifier(UUID.randomUUID(), "Armor", 0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        ironBreakerShieldMeta.addAttributeModifier(Attribute.ARMOR, armorModifierIronBreaker);
        AttributeModifier hpModifierIronBreaker = new AttributeModifier(UUID.randomUUID(), "Hp", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        ironBreakerShieldMeta.addAttributeModifier(Attribute.MAX_HEALTH, hpModifierIronBreaker);
        ironBreakerShieldMeta.getPersistentDataContainer().set(Keys.CUSTOM_IRON_BREAKER_SHIELD, PersistentDataType.BOOLEAN, true);
        ironBreakerShield.setItemMeta(ironBreakerShieldMeta);

        return ironBreakerShield;
    }

    public static ItemStack createWindBlastShield(){
        ItemStack windBlastShield = new ItemStack(Material.SHIELD);
        ItemMeta windBlastShieldMeta = windBlastShield.getItemMeta();

        windBlastShieldMeta.setDisplayName(ChatColor.AQUA + "Wind Blast Shield");
        windBlastShieldMeta.setLore(Arrays.asList(
            "Powered by wichrzyk"));
        AttributeModifier speedModifierWindBlastShield = new AttributeModifier(UUID.randomUUID(), "Speed", 0.15, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        windBlastShieldMeta.addAttributeModifier(Attribute.MOVEMENT_SPEED, speedModifierWindBlastShield);
            windBlastShieldMeta.getPersistentDataContainer().set(Keys.CUSTOM_WIND_BLAST_SHIELD, PersistentDataType.BOOLEAN, true);
            windBlastShield.setItemMeta(windBlastShieldMeta);

        return windBlastShield;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createBerserkerShield(){
        ItemStack berserkerShield = new ItemStack(Material.SHIELD);
        ItemMeta berserkerShieldMeta = berserkerShield.getItemMeta();

        berserkerShieldMeta.setDisplayName(ChatColor.RED + "Berserker Shield");
        berserkerShieldMeta.setLore(Arrays.asList(
            "Bathe in their blood !"));
        AttributeModifier dmgModifierberserkerShield = new AttributeModifier(UUID.randomUUID(), "Dmg", 0.20, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        berserkerShieldMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, dmgModifierberserkerShield);
        berserkerShieldMeta.getPersistentDataContainer().set(Keys.CUSTOM_BERSERKER_SHIELD, PersistentDataType.BOOLEAN, true);
        berserkerShield.setItemMeta(berserkerShieldMeta);

        return berserkerShield;
    }

    @SuppressWarnings("deprecation")
    public static ItemStack createEnchanterShield(){
        ItemStack enchanterShield = new ItemStack(Material.SHIELD);
        ItemMeta enchanterShieldMeta = enchanterShield.getItemMeta();

        enchanterShieldMeta.setDisplayName(ChatColor.GREEN + "Enchanter Shield");
        enchanterShieldMeta.setLore(Arrays.asList(
            "High Arrows >:3"));
        enchanterShieldMeta.getPersistentDataContainer().set(Keys.CUSTOM_ARROW_ENCHANTER, PersistentDataType.BOOLEAN, true);
        enchanterShield.setItemMeta(enchanterShieldMeta);

        return enchanterShield;
    }
    public static ItemStack createSoulEssence(){
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
    //      Throwable firework
    //
    public static ItemStack createThrowingFirework(){
        ItemStack throwingFirework = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta throwingFireworkMeta = throwingFirework.getItemMeta();

        throwingFireworkMeta.setDisplayName(ChatColor.GREEN + "Rioters Firework");
        throwingFireworkMeta.setLore(Arrays.asList(
            ChatColor.RED + "Glitter bomb :3",
            ChatColor.DARK_GRAY + "Flint'n steel in off hand to fire it dummy"));
        throwingFireworkMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        throwingFireworkMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        throwingFireworkMeta.getPersistentDataContainer().set(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.BOOLEAN, true);
        throwingFirework.setItemMeta(throwingFireworkMeta);

        return throwingFirework;
    }


    //
    //      Scatter Bomb
    //
    public static ItemStack createScatterBomb(){
        ItemStack scatterBomb = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta scatterBombMeta = scatterBomb.getItemMeta();

        scatterBombMeta.setDisplayName(ChatColor.GREEN + "Scatter Bomb");
        scatterBombMeta.setLore(Arrays.asList(
            ChatColor.DARK_GRAY + "Flint'n steel in off hand to fire it dummy"));
        scatterBombMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        scatterBombMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        scatterBombMeta.getPersistentDataContainer().set(Keys.CUSTOM_SCATTER_BOMB, PersistentDataType.BOOLEAN, true);
        scatterBomb.setItemMeta(scatterBombMeta);

        return scatterBomb;
    }


    //
    //      health charm
    //
    public static ItemStack createHealthCharm(){
        ItemStack healthCharm = new ItemStack(Material.RED_DYE);
        ItemMeta healthCharmMeta = healthCharm.getItemMeta();

        healthCharmMeta.setDisplayName(ChatColor.RED + "Health Charm");
        healthCharmMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        healthCharmMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        AttributeModifier healthModifier = new AttributeModifier(UUID.randomUUID(), "Health", 0.25, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        healthCharmMeta.addAttributeModifier(Attribute.MAX_HEALTH, healthModifier);
        healthCharm.setItemMeta(healthCharmMeta);

        return healthCharm;
    }


    //
    //      attack charm
    //
    public static ItemStack createAttackCharm(){
        ItemStack attackCharm = new ItemStack(Material.FLINT);
        ItemMeta attackCharmMeta = attackCharm.getItemMeta();

        attackCharmMeta.setDisplayName(ChatColor.GRAY + "Attack Charm");
        attackCharmMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        attackCharmMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        AttributeModifier attackModifier = new AttributeModifier(UUID.randomUUID(), "Attack", 0.2, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        attackCharmMeta.addAttributeModifier(Attribute.ATTACK_DAMAGE, attackModifier);
        attackCharm.setItemMeta(attackCharmMeta);

        return attackCharm;
    }


    //
    //      gravity charm
    //
    public static ItemStack createGravityCharm(){
        ItemStack gravityCharm = new ItemStack(Material.CHARCOAL);
        ItemMeta gravityCharmMeta = gravityCharm.getItemMeta();

        gravityCharmMeta.setDisplayName(ChatColor.GRAY + "Gravity Stone");
        gravityCharmMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        gravityCharmMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        AttributeModifier gravityModifier = new AttributeModifier(UUID.randomUUID(), "Gravity", -0.05, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.OFF_HAND);
        gravityCharmMeta.addAttributeModifier(Attribute.GRAVITY, gravityModifier);
        gravityCharm.setItemMeta(gravityCharmMeta);

        return gravityCharm;
    }


    //
    //      gravity charm
    //
    public static ItemStack createFireCharm(){
        ItemStack fireCharm = new ItemStack(Material.RESIN_CLUMP);
        ItemMeta fireCharmMeta = fireCharm.getItemMeta();

        fireCharmMeta.setDisplayName(ChatColor.RED + "Burning Stone");
        fireCharmMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        fireCharmMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        AttributeModifier fireModifier = new AttributeModifier(UUID.randomUUID(), "FireTime", -0.5, AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.OFF_HAND);
        fireCharmMeta.addAttributeModifier(Attribute.BURNING_TIME, fireModifier);
        fireCharm.setItemMeta(fireCharmMeta);

        return fireCharm;
    }


    //
    //      effect Transfuser
    //
    public static ItemStack createEffectTransfuser(){
        ItemStack effectTransfuser = new ItemStack(Material.GOLDEN_PICKAXE);
        ItemMeta effectTransfuserMeta = effectTransfuser.getItemMeta();

        effectTransfuserMeta.setDisplayName(ChatColor.GREEN + "Effect Transfuser");
        effectTransfuserMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        effectTransfuserMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        effectTransfuserMeta.getPersistentDataContainer().set(Keys.CUSTOM_EFFECT_TRANSFUSER, PersistentDataType.BOOLEAN, true);
        effectTransfuser.setItemMeta(effectTransfuserMeta);

        return effectTransfuser;
    }
    //
    //      RACE CRAFTABLES
    //

    //
    //      DWARF HONEY
    //
    public static ItemStack createDwarfHoney(){
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
    public static ItemStack createWitchSoup(){
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
    //      BREACH CHARGE
    //
    public static ItemStack createBreachCharge(){
        ItemStack breachCharge = new ItemStack(Material.FIREWORK_STAR);
        ItemMeta breachChargeMeta = breachCharge.getItemMeta();

        breachChargeMeta.setDisplayName(ChatColor.RED + "Breach Charge");
        breachChargeMeta.setLore(Arrays.asList(
            "Stay at leats 50 blocks away for safety",
            ChatColor.DARK_GRAY + "Flint'n Steel in off hand"));
            breachChargeMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        breachChargeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        breachChargeMeta.getPersistentDataContainer().set(Keys.CUSTOM_BREACH_CHARGE, PersistentDataType.BOOLEAN, true);
        breachCharge.setItemMeta(breachChargeMeta);

        return breachCharge;
    }

    //
    //      CURSED ARROW
    //
    public static ItemStack createCursedArrow(){
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
    //      PARALYZING ARROW
    //
    public static ItemStack createParalyzingArrow(){
        ItemStack paralyzingArrow = new ItemStack(Material.TIPPED_ARROW);
        PotionMeta potionMeta = (PotionMeta) paralyzingArrow.getItemMeta();

        potionMeta.setDisplayName(ChatColor.DARK_PURPLE + "Paralyzing Arrow");
        potionMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        potionMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20 * 40, 3), true);
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 40, 1), true);
        potionMeta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 40, 2), true);
        potionMeta.setColor(Color.fromRGB(128, 0, 128));
        paralyzingArrow.setItemMeta(potionMeta);

        return paralyzingArrow;
    }

    //
    //      Healing Gas
    //
    public static ItemStack createSuperHealingPot(){
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
    public static ItemStack createToxicPot(){
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
    //      Iron Hide potion
    //
    public static ItemStack createIronHide(){
        ItemStack ironHide = new ItemStack(Material.SPLASH_POTION);
        PotionMeta ironHideMeta = (PotionMeta) ironHide.getItemMeta();

        ironHideMeta.setDisplayName(ChatColor.GRAY + "Iron Hide Potion");
        ironHideMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        ironHideMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        ironHideMeta.addCustomEffect(new PotionEffect(PotionEffectType.RESISTANCE, 20 * 120, 1), true);
        ironHideMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 120, 1), true);
        ironHideMeta.setColor(Color.fromRGB(128, 128, 128));
        ironHide.setItemMeta(ironHideMeta);

        return ironHide;
    }

    //
    //      Gepard potion
    //
    public static ItemStack createGepardPotion(){
        ItemStack gepardPotion = new ItemStack(Material.SPLASH_POTION);
        PotionMeta gepardPotionMeta = (PotionMeta) gepardPotion.getItemMeta();

        gepardPotionMeta.setDisplayName(ChatColor.YELLOW + "Gepard Legs Potion");
        gepardPotionMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        gepardPotionMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        gepardPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 120, 3), true);
        gepardPotionMeta.addCustomEffect(new PotionEffect(PotionEffectType.JUMP_BOOST, 20 * 120, 1), true);
        gepardPotionMeta.setColor(Color.fromRGB(255, 255, 0));
        gepardPotion.setItemMeta(gepardPotionMeta);

        return gepardPotion;
    }

    //
    //      Instant health pot
    //
    public static ItemStack createInstantHealthPotion(){
        ItemStack healingPot = new ItemStack(Material.POTION);
        PotionMeta healingPotMeta = (PotionMeta) healingPot.getItemMeta();

        healingPotMeta.setDisplayName(ChatColor.RED + "Greater Health Potion");
        healingPotMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        healingPotMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        
        healingPotMeta.addCustomEffect(new PotionEffect(PotionEffectType.INSTANT_HEALTH, 1, 2), true);
        healingPotMeta.setColor(Color.fromRGB(255, 0, 0));
        healingPot.setItemMeta(healingPotMeta);

        return healingPot;
    }

    //
    //      Doplhin potion
    //
    public static ItemStack createDoplhinPotionPotion(){
        ItemStack dolphinPot = new ItemStack(Material.POTION);
        PotionMeta dolphinPotMeta = (PotionMeta) dolphinPot.getItemMeta();

        dolphinPotMeta.setDisplayName(ChatColor.BLUE + "Dolphin's Potion");
        dolphinPotMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        dolphinPotMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        
        dolphinPotMeta.addCustomEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 20 * 60 * 10, 0), true);
        dolphinPotMeta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 60 * 10, 0), true);
        dolphinPotMeta.addCustomEffect(new PotionEffect(PotionEffectType.DOLPHINS_GRACE, 20 * 60 * 10, 0), true);

        dolphinPotMeta.setColor(Color.fromRGB(0, 0, 255));
        dolphinPot.setItemMeta(dolphinPotMeta);

        return dolphinPot;
    }

    //
    //      Paralyzing Gas
    //
    public static ItemStack createParalyzingGas(){
        ItemStack paralyzingGas = new ItemStack(Material.LINGERING_POTION);
        PotionMeta paralyzingGasMeta = (PotionMeta) paralyzingGas.getItemMeta();

        paralyzingGasMeta.setDisplayName(ChatColor.YELLOW + "Paralyzing Gas");
        paralyzingGasMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        paralyzingGasMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);

        paralyzingGasMeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20 * 5, 4), true);
        paralyzingGasMeta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 5, 1), true);
        paralyzingGasMeta.setColor(Color.fromRGB(171, 145, 68));
        paralyzingGas.setItemMeta(paralyzingGasMeta);

        return paralyzingGas;
    }

    //
    //      Rose tea
    //
    public static ItemStack createRoseTea(){
        ItemStack roseTea = new ItemStack(Material.POTION);
        PotionMeta roseTeaMeta = (PotionMeta) roseTea.getItemMeta();

        roseTeaMeta.setDisplayName(ChatColor.RED + "Rose Tea");

        roseTeaMeta.addCustomEffect(new PotionEffect(PotionEffectType.REGENERATION, 20 * 60, 0), true);
        roseTeaMeta.setColor(Color.fromRGB(255, 0, 0));
        roseTea.setItemMeta(roseTeaMeta);

        return roseTea;
    }

    //
    //      Cocoa
    //
    public static ItemStack createCocoa(){
        ItemStack Cocoa = new ItemStack(Material.POTION);
        PotionMeta CocoaMeta = (PotionMeta) Cocoa.getItemMeta();

        CocoaMeta.setDisplayName(ChatColor.WHITE + "Cocoa");

        CocoaMeta.addCustomEffect(new PotionEffect(PotionEffectType.HASTE, 20 * 60 * 3, 0), true);
        CocoaMeta.setColor(Color.fromRGB(135, 30, 0));
        Cocoa.setItemMeta(CocoaMeta);

        return Cocoa;
    }

    //
    //      berryTea
    //
    public static ItemStack createSweetBerryTea(){
        ItemStack berryTea = new ItemStack(Material.POTION);
        PotionMeta berryTeaMeta = (PotionMeta) berryTea.getItemMeta();

        berryTeaMeta.setDisplayName(ChatColor.WHITE + "Sweet Berry Tea");

        berryTeaMeta.addCustomEffect(new PotionEffect(PotionEffectType.SPEED, 20 * 60 * 2, 0), true);
        berryTeaMeta.setColor(Color.fromRGB(230, 40, 0));
        berryTea.setItemMeta(berryTeaMeta);

        return berryTea;
    }

    //
    //      glowBerryTea
    //
    public static ItemStack createGlowBerryTea(){
        ItemStack glowBerryTea = new ItemStack(Material.POTION);
        PotionMeta glowBerryTeaMeta = (PotionMeta) glowBerryTea.getItemMeta();

        glowBerryTeaMeta.setDisplayName(ChatColor.WHITE + "Glow Berry Tea");

        glowBerryTeaMeta.addCustomEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20 * 60 * 4, 0), true);
        glowBerryTeaMeta.setColor(Color.fromRGB(240, 255, 0));
        glowBerryTea.setItemMeta(glowBerryTeaMeta);

        return glowBerryTea;
    }

    //
    //      Lucky Clock
    //
    public static ItemStack createLuckyClock(){
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
    public static ItemStack createMarkingSpyglass(){
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
    //      Battle Horn
    //
    public static ItemStack createBattleHorn(){
        ItemStack battleHorn = new ItemStack(Material.GOAT_HORN);
        ItemMeta battleHornMeta = battleHorn.getItemMeta();

        battleHornMeta.setDisplayName(ChatColor.RED + "Battle Horn");
        battleHornMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        battleHornMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        battleHornMeta.getPersistentDataContainer().set(Keys.CUSTOM_BATTLE_HORN, PersistentDataType.BOOLEAN, true);
        battleHorn.setItemMeta(battleHornMeta);

        return battleHorn;
    }

    //
    //      Essence Picker
    //
    public static ItemStack createEssencePicker(){
        ItemStack essencePicker = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta essencePickerMeta = essencePicker.getItemMeta();

        essencePickerMeta.setDisplayName(ChatColor.DARK_PURPLE + "Bootleg Netherite Pick");
        essencePickerMeta.setLore(Arrays.asList(
            "No way ^^"));
            essencePickerMeta.getPersistentDataContainer().set(Keys.CUSTOM_ESSENCE_PICKER, PersistentDataType.BOOLEAN, true);
            essencePicker.setItemMeta(essencePickerMeta);

        return essencePicker;
    }

    //
    //      Grenade
    //
    public static ItemStack createGrenade(){
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
    public static ItemStack createSmokeBomb(){
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
    public static ItemStack createAncientShell(){
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
    //      Wind Charm
    //
    public static ItemStack createWindCharm(){
        ItemStack windCharm = new ItemStack(Material.FEATHER);
        ItemMeta windCharmMeta = windCharm.getItemMeta();

        windCharmMeta.setDisplayName(ChatColor.GRAY + "Wind Charm");
        windCharmMeta.setLore(Arrays.asList(
            ChatColor.DARK_PURPLE + "Fast as wind..."));
        windCharmMeta.addEnchant(Enchantment.CHANNELING, 1, true);
        windCharmMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        windCharmMeta.getPersistentDataContainer().set(Keys.CUSTOM_WIND_CHARM, PersistentDataType.BOOLEAN, true);
        windCharm.setItemMeta(windCharmMeta);

        return windCharm;
    }

    //
    //      SanguiniteScroll
    //
    public static ItemStack createSanguiniteScroll(){
        ItemStack sanguiniteScroll = new ItemStack(Material.GLOBE_BANNER_PATTERN);
        ItemMeta sanguiniteScrollMeta = sanguiniteScroll.getItemMeta();

        sanguiniteScrollMeta.setDisplayName(ChatColor.RED + "Crimson Scroll");
        sanguiniteScrollMeta.setLore(Arrays.asList(
            ChatColor.DARK_PURPLE + "Has many uses... Trial & error"));
            sanguiniteScrollMeta.addEnchant(Enchantment.CHANNELING, 1, true);
            sanguiniteScrollMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            sanguiniteScrollMeta.getPersistentDataContainer().set(Keys.CUSTOM_SANGUINITE_SCROLL, PersistentDataType.BOOLEAN, true);
            sanguiniteScroll.setItemMeta(sanguiniteScrollMeta);

        return sanguiniteScroll;
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
            ChatColor.BOLD + "Lucky Clock\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Clock + Mechanical Part\n\n" +
            "Who Can Make It:\n" +
            "Mewchant + Dwarf"
        );
        pages.add(
            ChatColor.BOLD + "Smoke Bombs\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "4 Gunpowder + Black Dye\n\n" +
            "Who Can Make It:\n" +
            "Mewchant"
        );
        pages.add(
            ChatColor.BOLD + "Paralyzing Arrow\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Arrow + Soul Essence\n\n" +
            "Who Can Make It:\n" +
            "Mewchant"
        );
    
        // Dwarf Crafting Recipes
        pages.add(
            ChatColor.BOLD + "Throwing Bombs\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "2 Gunpowder + String\n\n" +
            "Who Can Make It:\n" +
            "Dwarf + Mechanical"
        );
        pages.add(
            ChatColor.BOLD + "Dwarf Ale\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Honey Bottle + Golden Apple\n\n" +
            "Who Can Make It:\n" +
            "Dwarf"
        );
        pages.add(
            ChatColor.BOLD + "Breach Charge\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Throwing TnT + 6 Soul Essence\n\n" +
            "Who Can Make It:\n" +
            "Dwarf + Mechanical"
        );
        pages.add(
                ChatColor.BOLD + "Scatter Bomb\n\n" +
                ChatColor.RESET + "Crafting Recipe:\n" +
                "Rioters firework + 2 Soul Essence\n\n" +
                "Who Can Make It:\n" +
                "Dwarf + Mechanical"
            );
        pages.add(
            ChatColor.BOLD + "Bootleg Netherite Pickaxe\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Stick + Mechanical Part\n\n" +
            "Who Can Make It:\n" +
            "Dwarf"
        );
        pages.add(
            ChatColor.BOLD + "6 xp Bottles\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Lapis + Soul Essence\n\n" +
            "Who Can Make It:\n" +
            "Dwarf"
        );
    
        // Witch
        pages.add(
            ChatColor.BOLD + "Most Sus Soup\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Suspicious Stew + Nether Wart\n\n" +
            "Who Can Make It:\n" +
            "Witch"
        );
    
        // Cara Crafting Recipes
        pages.add(
            ChatColor.BOLD + "Wind Charm\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Feather + Soul Essence\n\n" +
            "Who Can Make It:\n" +
            "Cara"
        );
    
        // Miskaru Crafting Recipes
        pages.add(
            ChatColor.BOLD + "Enchanted book\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Book + 8 Soul Essence\n\n" +
            "Who Can Make It:\n" +
            "Miskaru"
        );
    
        // Animated Fossil Crafting Recipes
        pages.add(
            ChatColor.BOLD + "Ancient Shell\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Nautilus Shell + Prismarine Crystal\n\n" +
            "Who Can Make It:\n" +
            "Animated Fossil"
        );
    
        // Sanguinite Crafting Recipes
        pages.add(
            ChatColor.BOLD + "Crimson Scroll\n\n" +
            ChatColor.RESET + "Crafting Recipe:\n" +
            "Soul essence x4 + Paper\n\n" +
            "Who Can Make It:\n" +
            "Sanguinite mmmm"
        );

        raceBookMeta.setPages(pages);
        raceBook.setItemMeta(raceBookMeta);

        return raceBook;
    }
}