package wentuziak.szoplugin.customlogic;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Axolotl;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.AxolotlBucketMeta;

import wentuziak.szoplugin.customcrafting.CreateCustomItem;

public class RandomLoot {
    public static ItemStack getLoot(Integer lootNum, Integer numberOfItems , String type){
        ItemStack item = new ItemStack(Material.COAL, numberOfItems);

        switch (type) {
            case "Ore":
                if (lootNum >= 99) {
                    item = new ItemStack(Material.NETHERITE_SCRAP);
                } else if (lootNum >= 98) {
                    item = new ItemStack(Material.DIAMOND_BLOCK);
                } else if (lootNum >= 88) {
                    item = new ItemStack(Material.DIAMOND);
                } else if (lootNum >= 75) {
                    item = new ItemStack(Material.QUARTZ, numberOfItems);
                } else if (lootNum >= 55) {
                    item = new ItemStack(Material.LAPIS_LAZULI, numberOfItems);
                } else if (lootNum >= 40) {
                    item = new ItemStack(Material.RAW_GOLD, numberOfItems);
                } else if (lootNum >= 30) {
                    item = new ItemStack(Material.RAW_IRON, numberOfItems);   
                } else if (lootNum >= 19) {
                    item = new ItemStack(Material.RAW_COPPER, numberOfItems);
                } else if (lootNum >= 10) {
                    item = new ItemStack(Material.EMERALD);
                }
                break;
            
            case "Plant":
                if (lootNum >= 35) {
                    item = CreateCustomItem.createSoulEssence();
                } else if (lootNum >= 33) {
                    item = new ItemStack(Material.LILY_PAD);
                } else if (lootNum >= 31) {
                    item = new ItemStack(Material.SMALL_DRIPLEAF);
                } else if (lootNum >= 29) {
                    item = new ItemStack(Material.PEONY);
                } else if (lootNum >= 27) {
                    item = new ItemStack(Material.ROSE_BUSH);
                } else if (lootNum >= 25) {
                    item = new ItemStack(Material.SUNFLOWER);
                } else if (lootNum >= 23) {
                    item = new ItemStack(Material.LILAC);
                } else if (lootNum >= 21) {
                    item = new ItemStack(Material.LILY_OF_THE_VALLEY);
                } else if (lootNum >= 19) {
                    item = new ItemStack(Material.BLUE_ORCHID);
                } else if (lootNum >= 17) {
                    item = new ItemStack(Material.ALLIUM);
                } else if (lootNum >= 15) {
                    item = new ItemStack(Material.AZURE_BLUET);
                } else if (lootNum >= 13) {
                    item = new ItemStack(Material.ORANGE_TULIP);
                } else if (lootNum >= 11) {
                    item = new ItemStack(Material.PINK_TULIP);
                } else if (lootNum >= 9) {
                    item = new ItemStack(Material.RED_TULIP);
                } else if (lootNum >= 7) {
                    item = new ItemStack(Material.WHITE_TULIP);
                } else if (lootNum >= 5) {
                    item = new ItemStack(Material.CORNFLOWER);
                } else if (lootNum >= 4) {
                    item = new ItemStack(Material.OXEYE_DAISY);
                } else if (lootNum >= 3) {
                    item = new ItemStack(Material.POTATO);
                } else if (lootNum >= 2) {
                    item = new ItemStack(Material.RED_MUSHROOM);
                } else if (lootNum >= 1) {
                    item = new ItemStack(Material.BROWN_MUSHROOM);
                } else {
                    item = new ItemStack(Material.FERN);
                }
                break;

            case "FishingTreasure":
                if (lootNum >= 99) {
                    item = new ItemStack(Material.TOTEM_OF_UNDYING);
                } else if (lootNum >= 98) {
                    item = new ItemStack(Material.NAUTILUS_SHELL, numberOfItems);
                } else if (lootNum >= 95) {
                    item = new ItemStack(Material.ANCIENT_DEBRIS);
                } else if (lootNum >= 90) {
                    item = new ItemStack(Material.HEART_OF_THE_SEA);
                } else if (lootNum >= 85) {
                    item = new ItemStack(Material.DIAMOND);
                } else if (lootNum >= 80) {
                    item = new ItemStack(Material.EMERALD, numberOfItems);
                } else if (lootNum >= 75) {
                    item = new ItemStack(Material.GOLDEN_APPLE);
                } else if (lootNum >= 70) {
                    item = new ItemStack(Material.PRISMARINE_SHARD, numberOfItems);
                } else if (lootNum >= 65) {
                    item = new ItemStack(Material.PRISMARINE_CRYSTALS, numberOfItems);
                } else if (lootNum >= 60) {
                    item = getRandomAxolotlBucket(true, item);
                } else if (lootNum >= 55) {
                    item = new ItemStack(Material.INK_SAC, numberOfItems);
                } else if (lootNum >= 50) {
                    item = new ItemStack(Material.TROPICAL_FISH_BUCKET);
                } else if (lootNum >= 45) {
                    item = new ItemStack(Material.GOLD_INGOT, numberOfItems);
                } else if (lootNum >= 40) {
                    item = new ItemStack(Material.EXPERIENCE_BOTTLE, numberOfItems);
                } else if (lootNum >= 35) {
                    item = CreateCustomItem.createCursedArrow();
                } else if (lootNum >= 30) {
                    item = new ItemStack(Material.STRING, numberOfItems);
                } else if (lootNum >= 25) {
                    item = new ItemStack(Material.COD, numberOfItems);
                } else if (lootNum >= 20) {
                    item = new ItemStack(Material.SALMON, numberOfItems);
                } else if (lootNum >= 15) {
                    item = new ItemStack(Material.TURTLE_SCUTE);
                } else if (lootNum >= 10) {
                    item = new ItemStack(Material.SEA_PICKLE, numberOfItems);
                }
                break;
            case "Mobs":
                if (lootNum >= 103) {
                    item = CreateCustomItem.createSoulEssence();
                } else if (lootNum >= 95) {
                    item = new ItemStack(Material.EXPERIENCE_BOTTLE, numberOfItems);
                } else if (lootNum >= 85) {
                    item = new ItemStack(Material.FERMENTED_SPIDER_EYE);
                } else if (lootNum >= 40) {
                    item = new ItemStack(Material.ROTTEN_FLESH);
                } else if (lootNum >= 30) {
                    item = new ItemStack(Material.BONE);
                } else if (lootNum >= 20) {
                    item = new ItemStack(Material.LEATHER);
                } else if (lootNum >= 10) {
                    item = new ItemStack(Material.RABBIT_HIDE);
                } else {
                    item = new ItemStack(Material.SPIDER_EYE);
                }
                break;
            case "Test":
                item = getRandomAxolotlBucket(false, item);
            default:
                break;
        }
        return item;
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
}