package wentuziak.szoplugin;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;

import java.lang.reflect.Field;


public class Keys {
    //
    //      Weapons
    //
    public static final NamespacedKey CUSTOM_EXPLOSIVE_SWORD = new NamespacedKey(SzoPlugin.getInstance(), "ExplosiveSword");
    public static final NamespacedKey CUSTOM_ANGEL_SWORD = new NamespacedKey(SzoPlugin.getInstance(), "AngelSword");
    public static final NamespacedKey CUSTOM_DAEMON_SWORD = new NamespacedKey(SzoPlugin.getInstance(), "DaemonSword");
    public static final NamespacedKey CUSTOM_THUNDER_HAMMER = new NamespacedKey(SzoPlugin.getInstance(), "ThunderHammer");
    public static final NamespacedKey CUSTOM_GRAVITY_BOW = new NamespacedKey(SzoPlugin.getInstance(), "GravityBow");
    public static final NamespacedKey CUSTOM_RAT_BOW = new NamespacedKey(SzoPlugin.getInstance(), "RatBow");
    public static final NamespacedKey CUSTOM_GRENADE = new NamespacedKey(SzoPlugin.getInstance(), "Grenade");
    public static final NamespacedKey CUSTOM_SMOKE_BOMB = new NamespacedKey(SzoPlugin.getInstance(), "SmokeBomb");
    public static final NamespacedKey CUSTOM_MAGNETIC_TRIDENT = new NamespacedKey(SzoPlugin.getInstance(), "MagneticTrident");
    public static final NamespacedKey CUSTOM_BOUNCY_CROSSBOW = new NamespacedKey(SzoPlugin.getInstance(), "BouncyCrossbow");
    public static final NamespacedKey CUSTOM_THROWING_FIREWORK = new NamespacedKey(SzoPlugin.getInstance(), "ThrowingFirework");
    public static final NamespacedKey CUSTOM_ARMOR_PIERCER = new NamespacedKey(SzoPlugin.getInstance(), "ArmorPiercer");
    public static final NamespacedKey CUSTOM_SPELL_SWORD = new NamespacedKey(SzoPlugin.getInstance(), "SpellSword");
    public static final NamespacedKey CUSTOM_DEDALUS_BOW = new NamespacedKey(SzoPlugin.getInstance(), "DedalusBow");
    
    //
    //      Armor
    //
    public static final NamespacedKey CUSTOM_JET_BOOTS = new NamespacedKey(SzoPlugin.getInstance(), "JetBoots");
    public static final NamespacedKey CUSTOM_GOLEM_CHEST = new NamespacedKey(SzoPlugin.getInstance(), "GolemChest");
    public static final NamespacedKey CUSTOM_EXPLOSIVE_CHEST = new NamespacedKey(SzoPlugin.getInstance(), "ExplosiveChest");
    public static final NamespacedKey CUSTOM_NINJA_PANT = new NamespacedKey(SzoPlugin.getInstance(), "NinjaPant");
    public static final NamespacedKey CUSTOM_GLUTTONY_PANTS = new NamespacedKey(SzoPlugin.getInstance(), "GluttonyPants");
    public static final NamespacedKey CUSTOM_WITCH_HELMET = new NamespacedKey(SzoPlugin.getInstance(), "WitchHelmet");
    public static final NamespacedKey CUSTOM_STRIGA_VEIL = new NamespacedKey(SzoPlugin.getInstance(), "StrigaVeil");
    public static final NamespacedKey CUSTOM_MAGIC_BOOTS = new NamespacedKey(SzoPlugin.getInstance(), "MagicBoots");
    public static final NamespacedKey CUSTOM_REFLECTIVE_CHESTPIECE = new NamespacedKey(SzoPlugin.getInstance(), "ReflectiveChestpiece");
    public static final NamespacedKey CUSTOM_GUARDING_VEST = new NamespacedKey(SzoPlugin.getInstance(), "GuardingVest");
    public static final NamespacedKey CUSTOM_RAM_CAP = new NamespacedKey(SzoPlugin.getInstance(), "RamCap");
    public static final NamespacedKey CUSTOM_WALKERS = new NamespacedKey(SzoPlugin.getInstance(), "Walkers");
    public static final NamespacedKey CUSTOM_JUMP_PACK = new NamespacedKey(SzoPlugin.getInstance(), "JumpPack");
    public static final NamespacedKey CUSTOM_CERBERUS_CHAIN = new NamespacedKey(SzoPlugin.getInstance(), "CerberusChain");
    
    //
    //      Spells
    //
    public static final NamespacedKey CUSTOM_TELEPORT_SPELL = new NamespacedKey(SzoPlugin.getInstance(), "TeleportSpell");
    public static final NamespacedKey CUSTOM_SPIRIT_LEECH = new NamespacedKey(SzoPlugin.getInstance(), "SpiritLeech");    
    public static final NamespacedKey CUSTOM_OBLITERATE = new NamespacedKey(SzoPlugin.getInstance(), "ObliterateSpell");    
    public static final NamespacedKey CUSTOM_SPIDER_YEET = new NamespacedKey(SzoPlugin.getInstance(), "SpiderYeet");    
    public static final NamespacedKey CUSTOM_SANGUINITE_SCROLL = new NamespacedKey(SzoPlugin.getInstance(), "SanguiniteScroll");    
    public static final NamespacedKey CUSTOM_MAGIC_STORM = new NamespacedKey(SzoPlugin.getInstance(), "MagicStorm");    
    public static final NamespacedKey CUSTOM_WEB_TRAP = new NamespacedKey(SzoPlugin.getInstance(), "WebTrap");    
    
    //
    // Loot
    //
    public static final NamespacedKey CUSTOM_SOUL_ESSENCE = new NamespacedKey(SzoPlugin.getInstance(), "SoulEssence");    
    
    //
    //      Miscalenious
    //
    public static final NamespacedKey CUSTOM_SOUL_FRAGMENT = new NamespacedKey(SzoPlugin.getInstance(), "SoulFragment");
    public static final NamespacedKey CUSTOM_MECHANICAL_PARTS = new NamespacedKey(SzoPlugin.getInstance(), "MechanicalParts");
    public static final NamespacedKey CUSTOM_DWARF_HONEY = new NamespacedKey(SzoPlugin.getInstance(), "DwarfHoney");
    public static final NamespacedKey CUSTOM_WITCH_SOUP = new NamespacedKey(SzoPlugin.getInstance(), "WitchSoup");
    public static final NamespacedKey CUSTOM_DWARF_UPGRADE = new NamespacedKey(SzoPlugin.getInstance(), "DwarfUpgrade");
    public static final NamespacedKey CUSTOM_BETTER_FOOD = new NamespacedKey(SzoPlugin.getInstance(), "BetterFood");

    
    //
    //      Tools
    //
    public static final NamespacedKey CUSTOM_MARKING_SPYGLASS = new NamespacedKey(SzoPlugin.getInstance(), "MarkingSpyglass");
    public static final NamespacedKey CUSTOM_HASTY_TOOL = new NamespacedKey(SzoPlugin.getInstance(), "HastyTool");
    public static final NamespacedKey CUSTOM_DWARF_PICK = new NamespacedKey(SzoPlugin.getInstance(), "DwarfPick");
    public static final NamespacedKey CUSTOM_TREASURE_FISHING = new NamespacedKey(SzoPlugin.getInstance(), "TreasureFishing");
    public static final NamespacedKey CUSTOM_IRON_BREAKER_SHIELD = new NamespacedKey(SzoPlugin.getInstance(), "IronBreakerShield");
    public static final NamespacedKey CUSTOM_WIND_BLAST_SHIELD = new NamespacedKey(SzoPlugin.getInstance(), "WindBlastShield");
    public static final NamespacedKey CUSTOM_BERSERKER_SHIELD = new NamespacedKey(SzoPlugin.getInstance(), "BerserkerShield");
    public static final NamespacedKey CUSTOM_LUCKY_CLOCK = new NamespacedKey(SzoPlugin.getInstance(), "LuckyClock");
    public static final NamespacedKey CUSTOM_ANCIENT_SHELL = new NamespacedKey(SzoPlugin.getInstance(), "AncientShell");
    public static final NamespacedKey CUSTOM_SUPER_SHEARS = new NamespacedKey(SzoPlugin.getInstance(), "SuperShears");
    public static final NamespacedKey CUSTOM_HOMECOMING_COMPASS = new NamespacedKey(SzoPlugin.getInstance(), "HomecomingCompass");
    public static final NamespacedKey CUSTOM_RICH_AX = new NamespacedKey(SzoPlugin.getInstance(), "RichAx");
    public static final NamespacedKey CUSTOM_RICH_SHOVEL = new NamespacedKey(SzoPlugin.getInstance(), "RichShovel");
    public static final NamespacedKey CUSTOM_WIND_CHARM = new NamespacedKey(SzoPlugin.getInstance(), "WindCharm");
    public static final NamespacedKey CUSTOM_ESSENCE_PICKER = new NamespacedKey(SzoPlugin.getInstance(), "EssencePicker");
    public static final NamespacedKey CUSTOM_BREACH_CHARGE = new NamespacedKey(SzoPlugin.getInstance(), "BreachCharge");
    public static final NamespacedKey CUSTOM_DEATH_CALLER = new NamespacedKey(SzoPlugin.getInstance(), "DeathCaller");
    public static final NamespacedKey CUSTOM_ARROW_ENCHANTER = new NamespacedKey(SzoPlugin.getInstance(), "ArrowEnchanter");
    public static final NamespacedKey CUSTOM_EFFECT_TRANSFUSER = new NamespacedKey(SzoPlugin.getInstance(), "EffectTransfuser");
    public static final NamespacedKey CUSTOM_SUPER_HOE = new NamespacedKey(SzoPlugin.getInstance(), "SuperHoe");

    //
    public static final NamespacedKey MOB_RIOT = new NamespacedKey(SzoPlugin.getInstance(), "RiotMob");
    public static final NamespacedKey MOB_MINI_BOSS = new NamespacedKey(SzoPlugin.getInstance(), "MiniBossMob");
    public static final NamespacedKey MOB_HUNT = new NamespacedKey(SzoPlugin.getInstance(), "HuntMob");
    public static final NamespacedKey MOB_PLAYER_SUMMON = new NamespacedKey(SzoPlugin.getInstance(), "PlayerSummon");

    //
    //      Races
    //
    public static final NamespacedKey RACE_DWARF = new NamespacedKey(SzoPlugin.getInstance(), "Dwarf");
    public static final NamespacedKey RACE_CELESTIAL = new NamespacedKey(SzoPlugin.getInstance(), "Celestial");
    public static final NamespacedKey RACE_WITCH = new NamespacedKey(SzoPlugin.getInstance(), "Witch");
    public static final NamespacedKey RACE_MISKARU = new NamespacedKey(SzoPlugin.getInstance(), "Miskaru");
    public static final NamespacedKey RACE_CARA = new NamespacedKey(SzoPlugin.getInstance(), "Cara");
    public static final NamespacedKey RACE_MEWCHANT = new NamespacedKey(SzoPlugin.getInstance(), "Mewchant");
    public static final NamespacedKey RACE_FOSSIL = new NamespacedKey(SzoPlugin.getInstance(), "Fossil");
    public static final NamespacedKey RACE_ZEPHYR = new NamespacedKey(SzoPlugin.getInstance(), "Zephyr");
    public static final NamespacedKey RACE_SANGUINITE = new NamespacedKey(SzoPlugin.getInstance(), "Sanguinite");
    public static final NamespacedKey RACE_ELF = new NamespacedKey(SzoPlugin.getInstance(), "Elf");
    public static final NamespacedKey RACE_HOBBIT = new NamespacedKey(SzoPlugin.getInstance(), "Hobbit");
    public static final NamespacedKey RACE_MECHANICAL = new NamespacedKey(SzoPlugin.getInstance(), "Mechanical");
    public static final NamespacedKey RACE_SPIRITBEARER = new NamespacedKey(SzoPlugin.getInstance(), "Spiritbearer");




    public static NamespacedKey[] getRaceKeys(){
        return new NamespacedKey[] {
            RACE_DWARF,
            RACE_CELESTIAL,
            RACE_WITCH,
            RACE_MISKARU,
            RACE_CARA,
            RACE_MEWCHANT,
            RACE_FOSSIL,
            RACE_ZEPHYR,
            RACE_SANGUINITE,
            RACE_ELF,
            RACE_HOBBIT,
            RACE_MECHANICAL,
            RACE_SPIRITBEARER
        };
    }
    public static NamespacedKey getKeyByName(String keyName) {
        try {
            Field field = Keys.class.getField(keyName);
            return (NamespacedKey) field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }

} 
