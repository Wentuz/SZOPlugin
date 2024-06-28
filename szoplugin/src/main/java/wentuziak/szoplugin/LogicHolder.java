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
        int isCrit = (int)(Math.random() * 100 + 1);
        //System.out.println(isCrit);
        if (critChance <= isCrit) {
            return false;
        }   else{
            return true;
        }
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

    public static void rollTreasure(int playerLuck, Location location)
    {   
        int whatLoot = 0;
        while (playerLuck > 0) {
            whatLoot = whatLoot + (int)(Math.random() * 35 + 1);
            playerLuck--;
        }
        System.out.println(whatLoot);
        if (whatLoot >= 99) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.NETHERITE_SCRAP));
            return;
        }
        if (whatLoot >= 98) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.DIAMOND_BLOCK));
            return;
        }
        if (whatLoot >= 88) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.DIAMOND));
            return;
        }
        else if (whatLoot >= 75) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.RAW_GOLD));
            return;
        }
        else if (whatLoot >= 55) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.RAW_IRON));
            return;
        }
        else if (whatLoot >= 40) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.GUNPOWDER));
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.GUNPOWDER));
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.GUNPOWDER));
            return;
        }
        else if (whatLoot >= 19) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.RAW_COPPER));
            return;
        }
        else if (whatLoot >= 10) {
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.EMERALD));
            return;
        }
        else{
            location.getWorld().dropItemNaturally(location, new ItemStack(Material.COAL));
            return;
        }
    }

}
