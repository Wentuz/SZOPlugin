package wentuziak.szoplugin;

import org.bukkit.entity.LivingEntity;

public class Weapons {


    public static void thunderHammerFunc(int chanceForCrit, LivingEntity hitEntity)
    {
        chanceForCrit = 40;
        if (LogicHolder.critRoll(chanceForCrit)) {
            LogicHolder.givePotionEffect(hitEntity, "WEAKNESS", 200, 1);
            LogicHolder.givePotionEffect(hitEntity, "SLOW", 200, 1);
            LogicHolder.givePotionEffect(hitEntity, "BLINDNESS", 200, 1);
            LogicHolder.givePotionEffect(hitEntity, "CONFUSION", 200, 1);
            hitEntity.getWorld().strikeLightning(hitEntity.getLocation());
        }
    }

    public static void explosiveSwordFunc(int chanceForCrit, LivingEntity hitEntity)
    {
        chanceForCrit = 33;
            if (LogicHolder.critRoll(chanceForCrit)) 
            {
                hitEntity.getWorld().createExplosion(hitEntity.getLocation(), 2, false, false);
            }
    }


}
