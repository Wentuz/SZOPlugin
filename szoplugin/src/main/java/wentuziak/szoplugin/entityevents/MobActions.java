package wentuziak.szoplugin.entityevents;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customitems.Armour;
import wentuziak.szoplugin.customlogic.LogicHolder;
import wentuziak.szoplugin.races.RaceEffects;

public class MobActions implements Listener{
    
    @EventHandler
    public static void onZombieAggro(EntityTargetEvent event){
        if (!(event.getEntity() instanceof Zombie)) {
            return;
        }

        Zombie zombie = (Zombie) event.getEntity();
    
        // Handle Tagged mobs events
        if (zombie.getPersistentDataContainer().has(Keys.MOB_RIOT, PersistentDataType.BYTE)) {
            mobLeapOnPlayer(zombie);
        }
    }

    private static void mobLeapOnPlayer(LivingEntity entity){
        Player target = LogicHolder.findNearestPlayer(entity.getLocation());
        if (target == null) {
            return;
        }

        Location mobLocation = entity.getLocation();
        Location playerLocation = target.getLocation();

        Vector direction = playerLocation.toVector().subtract(mobLocation.toVector());

        direction.normalize();
        direction.setY(0.5);
        direction.multiply(1.5);

        entity.setVelocity(direction);
    }

    @EventHandler
    public void onSkeletonShootArrow(EntityShootBowEvent event) {
        if (!(event.getEntity() instanceof Skeleton) || !(event.getProjectile() instanceof Arrow)) {
            return;
        }

        Skeleton skeleton = (Skeleton) event.getEntity();
        Arrow arrow = (Arrow) event.getProjectile();
    
        // Handle Tagged mobs events
        if (skeleton.getPersistentDataContainer().has(Keys.MOB_RIOT, PersistentDataType.BYTE)) {
            if (LogicHolder.critRoll(25)) {
                Vector arrowVelocity = arrow.getVelocity();
                RaceEffects.elfShotEffect(skeleton, arrowVelocity, null, "bouncy");

            }
            arrow.getPersistentDataContainer().set(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.STRING, "bouncyArrow");
        }
    }
}
