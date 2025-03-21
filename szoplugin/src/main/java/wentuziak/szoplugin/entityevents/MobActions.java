package wentuziak.szoplugin.entityevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.customitems.Armour;
import wentuziak.szoplugin.customitems.MagicItems;
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
    public static void onSpiderAggro(EntityTargetEvent event){
        if (!(event.getEntity() instanceof Spider)) {
            return;
        }

        Spider spider = (Spider) event.getEntity();
    
        // Handle Tagged mobs events
        if (spider.getPersistentDataContainer().has(Keys.MOB_RIOT, PersistentDataType.BYTE)) {
            PersistentDataContainer spiderContainer = spider.getPersistentDataContainer();

            NamespacedKey webTrapKey = Keys.CUSTOM_WEB_TRAP;
            spiderContainer.set(webTrapKey, PersistentDataType.BYTE, (byte) 1);

            Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                LogicHolder.throwSnowball(spider, Keys.CUSTOM_WEB_TRAP, 3);
            }, 20L * 3);

        }
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
