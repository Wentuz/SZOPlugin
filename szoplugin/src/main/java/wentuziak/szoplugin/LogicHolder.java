package wentuziak.szoplugin;

import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Registry;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.data.type.Fire;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.RayTraceResult;

import java.util.Random;


public class LogicHolder {
    
    public static void removeItem(Player player, ItemStack itemUsed){
        if (itemUsed.getAmount() > 1) {
            itemUsed.setAmount(itemUsed.getAmount() - 1);
        } else{
            itemUsed.setAmount(0);
        }
    }

    public static void givePotionEffect(LivingEntity player,String effect,int duration,int amplifier){
        PotionEffectType typeOfEffect = PotionEffectType.getByName(effect);
        player.addPotionEffect(new PotionEffect(typeOfEffect , duration, amplifier));
    }

    public static boolean critRoll(int critChance){   
        return (Math.random() * 100 + 1) <= critChance;
    }

    public static boolean isPlayerInWater(Player player){
            Block block = player.getLocation().getBlock();
            Block blockAbove = player.getLocation().add(0, 1, 0).getBlock();

            return (block.getType() == Material.WATER || block.getType() == Material.KELP || 
            block.getType() == Material.WATER_CAULDRON || blockAbove.getType() == Material.WATER);

        }
    public static void throwSnowball(Player player, PersistentDataContainer playerContainer){
        Snowball snowball = player.launchProjectile(Snowball.class);
        if (playerContainer.has(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_SPIRIT_LEECH, PersistentDataType.STRING, "spiritLeech");
        }
        else if (playerContainer.has(Keys.CUSTOM_SPIDER_YEET, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_SPIDER_YEET, PersistentDataType.STRING, "spiderYeet");
        }
        else if (playerContainer.has(Keys.CUSTOM_GRENADE, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_GRENADE, PersistentDataType.STRING, "grenade");
        }
        else if (playerContainer.has(Keys.CUSTOM_SMOKE_BOMB, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_SMOKE_BOMB, PersistentDataType.STRING, "smokeBomb");
        }
        else if (playerContainer.has(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.BYTE)) {
            snowball.getPersistentDataContainer().set(Keys.CUSTOM_THROWING_FIREWORK, PersistentDataType.STRING, "throwingFirework");
        }
        snowball.setVelocity(player.getLocation().getDirection().multiply(2)); // Adjust velocity as needed
        snowball.setShooter(player);
    }

    public static boolean isPlayerAboveGround(LivingEntity player, double minDistance) {
        Block blockBelow = player.getLocation().subtract(0, minDistance, 0).getBlock();
        return !blockBelow.getType().isSolid();
    }

    public static RayTraceResult rayTrace(int maxDistance, float radius, Player player){
        RayTraceResult result = player.getWorld().rayTraceEntities(
            player.getEyeLocation(), 
            player.getEyeLocation().getDirection(), 
            maxDistance,
            radius,
            entity -> entity != player
            );
        return result;
    }
    
    public static boolean isDaytime(World world) {
        long time = world.getTime();
        return time >= 0 && time < 12000;
    }

    public static Firework randomFirework(int power, Location location){
        Firework firework = (Firework) location.getWorld().spawn(location, Firework.class);
        FireworkMeta meta = firework.getFireworkMeta();

        meta.setPower(power);

        FireworkEffect effect = FireworkEffect.builder()
            .withColor(getRandomColor())
            .withFade(getRandomColor())
            .with(FireworkEffect.Type.BALL_LARGE)
            .build();
        meta.addEffect(effect);
        firework.setFireworkMeta(meta);

        return firework;
    }
    private static Color getRandomColor() {
        int red = (int)(Math.random() * 255 + 1);
        int green = (int)(Math.random() * 255 + 1);
        int blue = (int)(Math.random() * 255 + 1);

        return Color.fromRGB(red, green, blue);
    }

    public static Item rollTreasure(int playerLuck, Location location, String typeOfLoot) {
        int whatLoot = 0;
        while (playerLuck > 0) {
            whatLoot += (int)(Math.random() * 35 + 1);
            playerLuck--;
        }
    
        int numberOfItems = (int)(Math.random() * 6 + 1);
        ItemStack item = new ItemStack(Material.COAL, numberOfItems);
        switch (typeOfLoot) {
            case "Ore":
                if (whatLoot >= 99) {
                    item = new ItemStack(Material.NETHERITE_SCRAP);
                } else if (whatLoot >= 98) {
                    item = new ItemStack(Material.DIAMOND_BLOCK);
                } else if (whatLoot >= 88) {
                    item = new ItemStack(Material.DIAMOND);
                } else if (whatLoot >= 75) {
                    item = new ItemStack(Material.QUARTZ, numberOfItems);
                } else if (whatLoot >= 55) {
                    item = new ItemStack(Material.LAPIS_LAZULI, numberOfItems);
                } else if (whatLoot >= 40) {
                    item = new ItemStack(Material.RAW_GOLD, numberOfItems);
                } else if (whatLoot >= 30) {
                    item = new ItemStack(Material.RAW_IRON);   
                } else if (whatLoot >= 19) {
                    item = new ItemStack(Material.RAW_COPPER, numberOfItems);
                } else if (whatLoot >= 10) {
                    item = new ItemStack(Material.EMERALD);
                }
                break;
            
            case "Plant":
                if (whatLoot >= 35) {
                    item = new ItemStack(Material.MYCELIUM);
                } else if (whatLoot >= 30) {
                    item = new ItemStack(Material.LILY_PAD);
                } else if (whatLoot >= 25) {
                    item = new ItemStack(Material.SUGAR_CANE);
                } else if (whatLoot >= 22) {
                    item = new ItemStack(Material.CARROT);
                } else if (whatLoot >= 19) {
                    item = new ItemStack(Material.POTATO);
                } else if (whatLoot >= 16) {
                    item = new ItemStack(Material.BEETROOT_SEEDS);
                } else if (whatLoot >= 13) {
                    item = new ItemStack(Material.MELON_SEEDS);
                } else if (whatLoot >= 10) {
                    item = new ItemStack(Material.PUMPKIN_SEEDS);
                } else if (whatLoot >= 7) {
                    item = new ItemStack(Material.RED_MUSHROOM);
                } else if (whatLoot >= 5) {
                    item = new ItemStack(Material.BROWN_MUSHROOM);
                } else if (whatLoot == 1) {
                    item = new ItemStack(Material.BAMBOO);
                } else {
                    item = new ItemStack(Material.WHEAT_SEEDS);
                }
                break;

            case "FishingTreasure":
                if (whatLoot >= 99) {
                    item = new ItemStack(Material.TOTEM_OF_UNDYING);
                } else if (whatLoot >= 98) {
                    item = new ItemStack(Material.NAUTILUS_SHELL, numberOfItems);
                } else if (whatLoot >= 95) {
                    item = new ItemStack(Material.ANCIENT_DEBRIS);
                } else if (whatLoot >= 90) {
                    item = new ItemStack(Material.HEART_OF_THE_SEA);
                } else if (whatLoot >= 85) {
                    item = new ItemStack(Material.DIAMOND);
                } else if (whatLoot >= 80) {
                    item = new ItemStack(Material.EMERALD, numberOfItems);
                } else if (whatLoot >= 75) {
                    item = new ItemStack(Material.GOLDEN_APPLE);
                } else if (whatLoot >= 70) {
                    item = new ItemStack(Material.PRISMARINE_SHARD, numberOfItems);
                } else if (whatLoot >= 65) {
                    item = new ItemStack(Material.PRISMARINE_CRYSTALS, numberOfItems);
                } else if (whatLoot >= 60) {
                    item = new ItemStack(Material.AXOLOTL_BUCKET);
                } else if (whatLoot >= 55) {
                    item = new ItemStack(Material.INK_SAC, numberOfItems);
                } else if (whatLoot >= 50) {
                    item = new ItemStack(Material.TROPICAL_FISH_BUCKET);
                } else if (whatLoot >= 45) {
                    item = new ItemStack(Material.GOLD_INGOT, numberOfItems);
                } else if (whatLoot >= 40) {
                    item = new ItemStack(Material.EXPERIENCE_BOTTLE, numberOfItems);
                } else if (whatLoot >= 35) {
                    item = CreateCustomItem.createCursedArrow();
                } else if (whatLoot >= 30) {
                    item = new ItemStack(Material.STRING, numberOfItems);
                } else if (whatLoot >= 25) {
                    item = new ItemStack(Material.BONE, numberOfItems);
                } else if (whatLoot >= 20) {
                    item = new ItemStack(Material.ROTTEN_FLESH, numberOfItems);
                } else if (whatLoot >= 15) {
                    item = new ItemStack(Material.TURTLE_SCUTE);
                } else if (whatLoot >= 10) {
                    item = new ItemStack(Material.SEA_PICKLE);
                }
                break;
            case "Mobs":
                if (whatLoot >= 103) {
                    item = CreateCustomItem.createSoulEssence();
                }else if (whatLoot >= 102) {
                    item = CreateCustomItem.createSoulFragment();
                }else if (whatLoot >= 99) {
                    item = new ItemStack(Material.GHAST_TEAR);
                } else if (whatLoot >= 95) {
                    item = new ItemStack(Material.EXPERIENCE_BOTTLE, numberOfItems);
                } else if (whatLoot >= 85) {
                    item = new ItemStack(Material.ENDER_PEARL);
                } else if (whatLoot >= 70) {
                    item = new ItemStack(Material.BLAZE_POWDER);
                } else if (whatLoot >= 55) {
                    item = new ItemStack(Material.BEEF);
                } else if (whatLoot >= 40) {
                    item = new ItemStack(Material.GUNPOWDER);
                } else if (whatLoot >= 30) {
                    item = new ItemStack(Material.BONE);
                } else if (whatLoot >= 20) {
                    item = new ItemStack(Material.RABBIT_FOOT);
                } else if (whatLoot >= 10) {
                    item = new ItemStack(Material.RABBIT_HIDE);
                } else {
                    item = new ItemStack(Material.SPIDER_EYE);
                }
                break;
            default:
                break;
        }
        //location.getWorld().dropItemNaturally(location, item);
        return location.getWorld().dropItemNaturally(location, item);
    }

}
