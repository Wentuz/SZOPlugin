package wentuziak.szoplugin;

import org.bukkit.NamespacedKey;
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
    
    //
    //      Armor
    //
    public static final NamespacedKey CUSTOM_JET_BOOTS = new NamespacedKey(SzoPlugin.getInstance(), "JetBoots");
    public static final NamespacedKey CUSTOM_GOLEM_CHEST = new NamespacedKey(SzoPlugin.getInstance(), "GolemChest");
    public static final NamespacedKey CUSTOM_EXPLOSIVE_CHEST = new NamespacedKey(SzoPlugin.getInstance(), "ExplosiveChest");
    public static final NamespacedKey CUSTOM_MERMAID_TAIL = new NamespacedKey(SzoPlugin.getInstance(), "MermaidTail");
    public static final NamespacedKey CUSTOM_GLUTTONY_PANTS = new NamespacedKey(SzoPlugin.getInstance(), "GluttonyPants");
    
    //
    //      Spells
    //
    public static final NamespacedKey CUSTOM_TELEPORT_SPELL = new NamespacedKey(SzoPlugin.getInstance(), "TeleportSpell");
    public static final NamespacedKey CUSTOM_SPIRIT_LEECH = new NamespacedKey(SzoPlugin.getInstance(), "SpiritLeech");    
    public static final NamespacedKey CUSTOM_OBLITERATE = new NamespacedKey(SzoPlugin.getInstance(), "ObliterateSpell");    
    public static final NamespacedKey CUSTOM_SPIDER_YEET = new NamespacedKey(SzoPlugin.getInstance(), "SpiderYeet");    

    //
    //      Craftables
    //
    public static final NamespacedKey CUSTOM_SOUL_FRAGMENT = new NamespacedKey(SzoPlugin.getInstance(), "SoulFragment");
    public static final NamespacedKey CUSTOM_MECHANICAL_PARTS = new NamespacedKey(SzoPlugin.getInstance(), "MechanicalParts");
    public static final NamespacedKey CUSTOM_DWARF_HONEY = new NamespacedKey(SzoPlugin.getInstance(), "DwarfHoney");
    public static final NamespacedKey CUSTOM_WITCH_SOUP = new NamespacedKey(SzoPlugin.getInstance(), "WitchSoup");
    public static final NamespacedKey CUSTOM_MARKING_SPYGLASS = new NamespacedKey(SzoPlugin.getInstance(), "MarkingSpyglass");

    //
    //      Tools
    //
    public static final NamespacedKey CUSTOM_HASTY_TOOL = new NamespacedKey(SzoPlugin.getInstance(), "HastyTool");
    public static final NamespacedKey CUSTOM_DWARF_PICK = new NamespacedKey(SzoPlugin.getInstance(), "DwarfPick");
    public static final NamespacedKey CUSTOM_TREASURE_FISHING = new NamespacedKey(SzoPlugin.getInstance(), "TreasureFishing");
    public static final NamespacedKey CUSTOM_IRON_BREAKER_SHIELD = new NamespacedKey(SzoPlugin.getInstance(), "IronBreakerShield");
    public static final NamespacedKey CUSTOM_BERSERKER_SHIELD = new NamespacedKey(SzoPlugin.getInstance(), "BerserkerShield");
    public static final NamespacedKey CUSTOM_LUCKY_CLOCK = new NamespacedKey(SzoPlugin.getInstance(), "LuckyClock");
    
    //
    //      Races
    //
    public static final NamespacedKey RACE_DWARF = new NamespacedKey(SzoPlugin.getInstance(), "Dwarf");
    public static final NamespacedKey RACE_CELESTIAL = new NamespacedKey(SzoPlugin.getInstance(), "Celestial");
    public static final NamespacedKey RACE_WITCH = new NamespacedKey(SzoPlugin.getInstance(), "Witch");
    public static final NamespacedKey RACE_MISKARU = new NamespacedKey(SzoPlugin.getInstance(), "Miskaru");
    public static final NamespacedKey RACE_CARA = new NamespacedKey(SzoPlugin.getInstance(), "Cara");
    public static final NamespacedKey RACE_MEWCHANT = new NamespacedKey(SzoPlugin.getInstance(), "Mewchant");


    public static NamespacedKey[] getRaceKeys(){
        return new NamespacedKey[] {
            RACE_DWARF,
            RACE_CELESTIAL,
            RACE_WITCH,
            RACE_MISKARU,
            RACE_CARA,
            RACE_MEWCHANT
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
