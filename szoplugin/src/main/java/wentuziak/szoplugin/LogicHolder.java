package wentuziak.szoplugin;

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
        System.out.println(isCrit);
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

}
