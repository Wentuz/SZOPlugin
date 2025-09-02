package wentuziak.szoplugin.customlogic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.AxolotlBucketMeta;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;

import wentuziak.szoplugin.customcrafting.CreateCustomItem;

public class RandomLoot {
    private static final Random rand = new Random();

    // Types of loot and their rarity
    
    //
    //	Ore drops
    //
    private static final HashMap<Integer, Material> oreLootCommon = new HashMap<>();
    static {
    	oreLootCommon.put(1, Material.COAL);
    	oreLootCommon.put(2, Material.RAW_IRON);
    	oreLootCommon.put(3, Material.RAW_COPPER);
    	oreLootCommon.put(4, Material.LAPIS_LAZULI);
    	oreLootCommon.put(5, Material.RAW_GOLD);
    }
    private static final HashMap<Integer, Material> oreLootRare = new HashMap<>();
    static {
    	oreLootRare.put(1, Material.QUARTZ);
    	oreLootRare.put(2, Material.DIAMOND);
    }
    private static final HashMap<Integer, Material> oreLootUnique = new HashMap<>();
    static {
    	oreLootUnique.put(1, Material.DIAMOND_BLOCK);
    	oreLootUnique.put(2, Material.NETHERITE_SCRAP);
    }
    
    //
    //	Plant drops
    //
    private static final HashMap<Integer, Material> plantLootCommon = new HashMap<>();
    static {
    	plantLootCommon.put(1, Material.BROWN_MUSHROOM);
    	plantLootCommon.put(2, Material.RED_MUSHROOM);
    	plantLootCommon.put(3, Material.POTATO);
    	plantLootCommon.put(4, Material.FERN);
    	plantLootCommon.put(5, Material.CORNFLOWER);
    	plantLootCommon.put(6, Material.OXEYE_DAISY);
    	plantLootCommon.put(7, Material.WHITE_TULIP);
    	plantLootCommon.put(8, Material.RED_TULIP);
    	plantLootCommon.put(9, Material.PINK_TULIP);
    	plantLootCommon.put(10, Material.ORANGE_TULIP);
    	plantLootCommon.put(11, Material.AZURE_BLUET);
    	plantLootCommon.put(12, Material.ALLIUM);
    	plantLootCommon.put(13, Material.BLUE_ORCHID);
    	plantLootCommon.put(14, Material.PITCHER_POD);
    	plantLootCommon.put(15, Material.TORCHFLOWER_SEEDS);
    	plantLootCommon.put(16, Material.HANGING_ROOTS);
    	
    }
    private static final HashMap<Integer, Material> plantLootRare = new HashMap<>();
    static {
    	plantLootRare.put(1, Material.LILY_OF_THE_VALLEY);
    	plantLootRare.put(2, Material.LILAC);
    	plantLootRare.put(3, Material.SUNFLOWER);
    	plantLootRare.put(4, Material.ROSE_BUSH);
    	plantLootRare.put(5, Material.PEONY);
    	plantLootRare.put(6, Material.SMALL_DRIPLEAF);
    	plantLootRare.put(7, Material.LILY_PAD);
    	plantLootRare.put(8, Material.PALE_HANGING_MOSS);
    	plantLootRare.put(9, Material.CLOSED_EYEBLOSSOM);
    	plantLootRare.put(10, Material.OPEN_EYEBLOSSOM);
    	plantLootRare.put(11, Material.CACTUS_FLOWER);
    	plantLootRare.put(12, Material.DEAD_BUSH);

    }
    private static final HashMap<Integer, Material> plantLootUnique = new HashMap<>();
    static {
    	plantLootUnique.put(1, Material.FLOWERING_AZALEA);
    	plantLootUnique.put(2, Material.TUBE_CORAL);
    	plantLootUnique.put(3, Material.BRAIN_CORAL);
    	plantLootUnique.put(4, Material.BUBBLE_CORAL);
    	plantLootUnique.put(5, Material.FIRE_CORAL);
    	plantLootUnique.put(6, Material.HORN_CORAL);
    	plantLootUnique.put(7, Material.SPORE_BLOSSOM);

    }
    
    //
    //	Mob drops
    //
    private static final HashMap<Integer, Material> mobLootCommon = new HashMap<>();
    static {                                       
    	mobLootCommon.put(1, Material.ROTTEN_FLESH);
    	mobLootCommon.put(2, Material.BONE);
    	mobLootCommon.put(3, Material.SPIDER_EYE);
    	mobLootCommon.put(4, Material.STRING);
    	mobLootCommon.put(5, Material.GUNPOWDER);
    	mobLootCommon.put(6, Material.FEATHER);
    	mobLootCommon.put(7, Material.LEATHER);
    	mobLootCommon.put(8, Material.RABBIT_HIDE);
                            
    }                                              
    private static final HashMap<Integer, Material> mobLootRare = new HashMap<>();
    static {                                        
    	mobLootRare.put(1, Material.FERMENTED_SPIDER_EYE);
    	mobLootRare.put(2, Material.BLAZE_ROD);
    	mobLootRare.put(3, Material.ENDER_PEARL);
    	mobLootRare.put(4, Material.GHAST_TEAR);
    	mobLootRare.put(5, Material.SLIME_BALL);
    	mobLootRare.put(6, Material.EXPERIENCE_BOTTLE);
                                  
    }                                               
    private static final HashMap<Integer, Material> mobLootUnique = new HashMap<>();
    static {
    	mobLootUnique.put(1, Material.EXPERIENCE_BOTTLE);
    	mobLootUnique.put(2, Material.EMERALD);
    	mobLootUnique.put(3, Material.DRAGON_BREATH);

    }
    
    //
    //	Fishing drops
    //
    private static final HashMap<Integer, Material> fishingTeasureLootCommon = new HashMap<>();
    static {                                        
    	fishingTeasureLootCommon.put(1, Material.COD);
    	fishingTeasureLootCommon.put(2, Material.SALMON);
    	fishingTeasureLootCommon.put(3, Material.PUFFERFISH);
    	fishingTeasureLootCommon.put(4, Material.TROPICAL_FISH);
    	fishingTeasureLootCommon.put(5, Material.INK_SAC);
    	fishingTeasureLootCommon.put(6, Material.STRING);
    	fishingTeasureLootCommon.put(7, Material.BOWL);
    	fishingTeasureLootCommon.put(8, Material.BAMBOO);
    	fishingTeasureLootCommon.put(9, Material.SEA_PICKLE);
                                 
    }                                               
    private static final HashMap<Integer, Material> fishingTeasureLootRare = new HashMap<>();
    static {                                        
    	fishingTeasureLootRare.put(1, Material.NAUTILUS_SHELL);
    	fishingTeasureLootRare.put(2, Material.PRISMARINE_SHARD);
    	fishingTeasureLootRare.put(3, Material.PRISMARINE_CRYSTALS);
    	fishingTeasureLootRare.put(4, Material.TROPICAL_FISH_BUCKET);
    	fishingTeasureLootRare.put(5, Material.TURTLE_SCUTE);
    	fishingTeasureLootRare.put(6, Material.EXPERIENCE_BOTTLE);
    	fishingTeasureLootRare.put(7, Material.AXOLOTL_BUCKET);
    	fishingTeasureLootRare.put(8, Material.BRAIN_CORAL);
    	fishingTeasureLootRare.put(9, Material.TUBE_CORAL);
    	fishingTeasureLootRare.put(10, Material.BUBBLE_CORAL);
    	fishingTeasureLootRare.put(11, Material.FIRE_CORAL);
    	fishingTeasureLootRare.put(11, Material.HORN_CORAL);
    	fishingTeasureLootRare.put(12, Material.TUBE_CORAL_FAN);
    	fishingTeasureLootRare.put(13, Material.BUBBLE_CORAL_FAN);
    	fishingTeasureLootRare.put(14, Material.FIRE_CORAL_FAN);
    	fishingTeasureLootRare.put(15, Material.HORN_CORAL_FAN);
    	fishingTeasureLootRare.put(16, Material.BRAIN_CORAL_FAN);

    }                                               
    private static final HashMap<Integer, Material> fishingTeasureLootUnique = new HashMap<>();
    static {
    	fishingTeasureLootUnique.put(1, Material.HEART_OF_THE_SEA);
    	fishingTeasureLootUnique.put(2, Material.ENCHANTED_BOOK);
    	fishingTeasureLootUnique.put(3, Material.ANCIENT_DEBRIS);
    	fishingTeasureLootUnique.put(4, Material.GOLDEN_APPLE);
    	fishingTeasureLootUnique.put(5, Material.TOTEM_OF_UNDYING);
    	fishingTeasureLootUnique.put(6, Material.TURTLE_SCUTE);

    }
    
    
    
    public static ItemStack getLoot(Integer lootTier, Integer numberOfItems , String type){ 
        Material material = Material.COAL;
        ItemStack item = new ItemStack(material, numberOfItems);
        
        switch (type) {
            case "Ore":                                       
                if(lootTier <= 1) material = getRandomMaterial(oreLootCommon);
                else if(lootTier == 2) material = getRandomMaterial(oreLootRare);
                else if(lootTier >= 3) material = getRandomMaterial(oreLootUnique);
                break;
            
            case "Plant":
                if(lootTier <= 1) material = getRandomMaterial(plantLootCommon);
                else if(lootTier == 2) material = getRandomMaterial(plantLootRare);
                else if(lootTier >= 3) material = getRandomMaterial(plantLootUnique);
                break;

            case "FishingTreasure":
                if(lootTier <= 1) material = getRandomMaterial(fishingTeasureLootCommon);
                else if(lootTier == 2) material = getRandomMaterial(fishingTeasureLootRare);
                else if(lootTier >= 3) material = getRandomMaterial(fishingTeasureLootUnique);
                break;
            case "Mobs":
                if(lootTier <= 1) material = getRandomMaterial(mobLootCommon);
                else if(lootTier == 2) material = getRandomMaterial(mobLootRare);
                else if(lootTier >= 3) material = getRandomMaterial(mobLootUnique);
                break;
            case "Test":
                //item = getRandomAxolotlBucket(false, item);
            default:
            	break;
        }
        item = new ItemStack(material, numberOfItems);

        if(item.getType() == Material.ENCHANTED_BOOK) addRandomEnchantment(item);
        
        //if(numberOfItems != 0) getLoot(lootTier, numberOfItems - 1 ,type);
        return item;
    }
    
    private static Material getRandomMaterial(HashMap<Integer, Material> map) {
        List<Integer> keys = new ArrayList<>(map.keySet());
        int randomIndex = new Random().nextInt(keys.size());
        return map.get(keys.get(randomIndex));
    }

    public static ItemStack getRandomAxolotlBucket(Boolean excludeBlue, ItemStack item){
        
        item = new ItemStack(Material.AXOLOTL_BUCKET);
        AxolotlBucketMeta meta = (AxolotlBucketMeta) item.getItemMeta();

        List<Axolotl.Variant> variants = List.of(
            Axolotl.Variant.LUCY,
            Axolotl.Variant.WILD,
            Axolotl.Variant.GOLD,
            Axolotl.Variant.CYAN
        );

        if (!excludeBlue) {
            variants = new ArrayList<>(variants);
            variants.add(Axolotl.Variant.BLUE);
        }

        int random = (int)(Math.random() * variants.size());
        Axolotl.Variant randomVariant = variants.get(random);

        meta.setVariant(randomVariant);
        item.setItemMeta(meta);

        return item;
    }

    private static final Map<Enchantment, Integer> maxEnchantmentLevels = new HashMap<>();

    static {
        maxEnchantmentLevels.put(Enchantment.PROTECTION, 4);
        maxEnchantmentLevels.put(Enchantment.FIRE_PROTECTION, 4);
        maxEnchantmentLevels.put(Enchantment.FEATHER_FALLING, 4);
        maxEnchantmentLevels.put(Enchantment.BLAST_PROTECTION, 4);
        maxEnchantmentLevels.put(Enchantment.PROJECTILE_PROTECTION, 4);
        maxEnchantmentLevels.put(Enchantment.RESPIRATION, 3);
        maxEnchantmentLevels.put(Enchantment.DEPTH_STRIDER, 3);
        maxEnchantmentLevels.put(Enchantment.DEPTH_STRIDER, 1);
        maxEnchantmentLevels.put(Enchantment.THORNS, 3);
        maxEnchantmentLevels.put(Enchantment.SHARPNESS, 5);
        maxEnchantmentLevels.put(Enchantment.SMITE, 5);
        maxEnchantmentLevels.put(Enchantment.BANE_OF_ARTHROPODS, 5);
        maxEnchantmentLevels.put(Enchantment.KNOCKBACK, 2);
        maxEnchantmentLevels.put(Enchantment.FIRE_ASPECT, 2);
        maxEnchantmentLevels.put(Enchantment.LOOTING, 3);
        maxEnchantmentLevels.put(Enchantment.SWEEPING_EDGE, 3);
        maxEnchantmentLevels.put(Enchantment.EFFICIENCY, 5);
        maxEnchantmentLevels.put(Enchantment.SILK_TOUCH, 1);
        maxEnchantmentLevels.put(Enchantment.UNBREAKING, 3);
        maxEnchantmentLevels.put(Enchantment.FORTUNE, 3);
        maxEnchantmentLevels.put(Enchantment.POWER, 5);
        maxEnchantmentLevels.put(Enchantment.PUNCH, 2);
        maxEnchantmentLevels.put(Enchantment.FLAME, 1);
        maxEnchantmentLevels.put(Enchantment.INFINITY, 1);
        maxEnchantmentLevels.put(Enchantment.LUCK_OF_THE_SEA, 3);
        maxEnchantmentLevels.put(Enchantment.WIND_BURST, 3);
        maxEnchantmentLevels.put(Enchantment.BREACH, 5);
        maxEnchantmentLevels.put(Enchantment.SWIFT_SNEAK, 3);
        maxEnchantmentLevels.put(Enchantment.SOUL_SPEED, 3);
        maxEnchantmentLevels.put(Enchantment.FROST_WALKER, 3);
        maxEnchantmentLevels.put(Enchantment.DENSITY, 5);
    }

    public static void addRandomEnchantment(ItemStack item) {
        // Get a random enchantment from the map
        Enchantment[] enchantments = maxEnchantmentLevels.keySet().toArray(new Enchantment[0]);
        Enchantment randomEnchantment = enchantments[rand.nextInt(enchantments.length)];

        int maxLevel = maxEnchantmentLevels.get(randomEnchantment);
        int randomLevel = rand.nextInt(maxLevel) + 1;

        // Apply the enchantment to the item
        if (item.getType() == Material.ENCHANTED_BOOK) {
            addBookEnchantment(item, randomEnchantment, randomLevel);
        }else{   
            item.addUnsafeEnchantment(randomEnchantment, randomLevel);
        }
    }   

    public static ItemStack addBookEnchantment(ItemStack item, Enchantment enchantment, int level) {
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
        meta.addStoredEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return item;
    }
}
