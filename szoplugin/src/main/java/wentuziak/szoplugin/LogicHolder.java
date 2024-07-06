package wentuziak.szoplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


public class LogicHolder {
    
    public static void removeItem(Player player, ItemStack itemUsed)
    {
        if (itemUsed.getAmount() > 1) {
            itemUsed.setAmount(itemUsed.getAmount() - 1);
        } else{
            player.getInventory().removeItem(itemUsed);
        }
    }

    public static void givePotionEffect(LivingEntity player,String effect,int duration,int amplifier)
    {
        PotionEffectType typeOfEffect = PotionEffectType.getByName(effect);
        player.addPotionEffect(new PotionEffect(typeOfEffect , duration, amplifier));
    }

    public static boolean critRoll(int critChance)
    {   
        return (Math.random() * 100 + 1) <= critChance;
    }

    public static boolean isPlayerInWater(Player player)
        {
            Block block = player.getLocation().getBlock();
            Block blockAbove = player.getLocation().add(0, 1, 0).getBlock();

            return (block.getType() == Material.WATER || block.getType() == Material.KELP || 
            block.getType() == Material.WATER_CAULDRON || blockAbove.getType() == Material.WATER);

        }
    public static void throwSnowball(Player player)
    {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setVelocity(player.getLocation().getDirection().multiply(2)); // Adjust velocity as needed
        snowball.setShooter(player);
    }

    public static boolean isPlayerAboveGround(LivingEntity player, double minDistance) {
        Block blockBelow = player.getLocation().subtract(0, minDistance, 0).getBlock();
        return !blockBelow.getType().isSolid();
    }

    public static void rollTreasure(int playerLuck, Location location, String typeOfLoot) {
        int whatLoot = 0;
        while (playerLuck > 0) {
            whatLoot += (int)(Math.random() * 35 + 1);
            playerLuck--;
        }
    
        ItemStack item = new ItemStack(Material.COAL);
        switch (typeOfLoot) {
            case "Ore":
                if (whatLoot >= 99) {
                    item = new ItemStack(Material.NETHERITE_SCRAP);
                } else if (whatLoot >= 98) {
                    item = new ItemStack(Material.DIAMOND_BLOCK);
                } else if (whatLoot >= 88) {
                    item = new ItemStack(Material.DIAMOND);
                } else if (whatLoot >= 75) {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.QUARTZ, 3));
                    return;
                } else if (whatLoot >= 55) {
                    location.getWorld().dropItemNaturally(location, new ItemStack(Material.LAPIS_LAZULI, 3));
                    return; 
                } else if (whatLoot >= 40) {
                    item = new ItemStack(Material.RAW_GOLD);
                } else if (whatLoot >= 30) {
                    item = new ItemStack(Material.RAW_IRON);   
                } else if (whatLoot >= 19) {
                    item = new ItemStack(Material.RAW_COPPER);
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
        
            default:
                break;
        }

        location.getWorld().dropItemNaturally(location, item);
    }

}
