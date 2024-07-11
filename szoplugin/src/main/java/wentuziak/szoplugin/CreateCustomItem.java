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
}
