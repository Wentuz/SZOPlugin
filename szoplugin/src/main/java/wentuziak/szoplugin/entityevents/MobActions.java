package wentuziak.szoplugin.entityevents;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.Keys;

public class MobActions implements Listener{
    
    public static void onZombieAggro(LivingEntity entity, LivingEntity targetedEntity){
        if (targetedEntity instanceof Player) {
            
            return;

        }
    }

    @EventHandler
    public void onSkeletonShootArrow(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Skeleton) || !(event.getProjectile() instanceof Arrow)) {
            return;
        }

        Skeleton skeleton = (Skeleton) event.getEntity();
        Arrow arrow = (Arrow) event.getProjectile();
    
        // Handle Elf Race special effects
        if (skeleton.getPersistentDataContainer().has(Keys.MOB_RIOT, PersistentDataType.BYTE)) {
            arrow.getPersistentDataContainer().set(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.STRING, "bouncyArrow");
        }
    }
}
