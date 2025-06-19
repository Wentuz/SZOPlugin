package wentuziak.szoplugin.entityevents;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Spider;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Witch;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
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
        if (zombie.getPersistentDataContainer().has(Keys.MOB_RIOT, PersistentDataType.BYTE) || zombie.getPersistentDataContainer().has(Keys.MOB_MINI_BOSS, PersistentDataType.BYTE)) {
            mobLeapOnPlayer(zombie);
        }
        if (zombie.getPersistentDataContainer().has(Keys.MOB_RIOT, PersistentDataType.BYTE)) {
            zombieFireworkOnPlayer(zombie);
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
    
    private static void zombieFireworkOnPlayer(Zombie zombie) {
    	PersistentDataContainer zombieContainer = zombie.getPersistentDataContainer();

        NamespacedKey fireworkKey = Keys.CUSTOM_THROWING_FIREWORK;
        zombieContainer.set(fireworkKey, PersistentDataType.BYTE, (byte) 1);

        int seconds = (int) (Math.random() * 10 + 1);

        if(!zombie.isDead()) {
        	Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
                LogicHolder.throwSnowball(zombie, Keys.CUSTOM_THROWING_FIREWORK, 3);
            }, 20L * seconds);
        	
        	Bukkit.getScheduler().runTaskLater(SzoPlugin.getInstance(), () -> {
            	zombieFireworkOnPlayer(zombie);
            }, 20L * 5 + seconds);
        }
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
                arrow.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20 * 5, 0), true);
                arrow.addCustomEffect(new PotionEffect(PotionEffectType.BLINDNESS, 20 * 2, 0), true);
            }
            arrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOWNESS, 20 * 5, 1), true);
            arrow.setPierceLevel(1);
        }
        if (skeleton.getPersistentDataContainer().has(Keys.MOB_MINI_BOSS, PersistentDataType.BYTE)) {
            if (LogicHolder.critRoll(25)) {
                Vector arrowVelocity = arrow.getVelocity();
                RaceEffects.elfShotEffect(skeleton, arrowVelocity, "multishot", "bouncy");
            }
            arrow.getPersistentDataContainer().set(Keys.CUSTOM_BOUNCY_CROSSBOW, PersistentDataType.STRING, "bouncyArrow");
        }
    }
    
    public void onWitchRollPotion(PotionSplashEvent event) {
        
    }
}
