package wentuziak.szoplugin.entityevents;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class tagSpawnedMob implements Listener {

    public static void startTagger() {
        // Schedule the repeating task to run every 10 minutes (600 seconds)
        new BukkitRunnable() {
            @Override
            public void run() {
                tagAllSkeletons();
            }
        }.runTaskTimer(SzoPlugin.getInstance(), 0L, 20 * 10);  // 600 seconds * 20 ticks per second
    }

    private static void tagAllSkeletons() {
        // Loop through all worlds and entities
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) { // Loop through all entities in the main world
            if (entity.getType() == EntityType.SKELETON) {  // Check if the entity is a skeleton
                Skeleton skeleton = (Skeleton) entity;
                LogicHolder.givePotionEffect(skeleton, "GLOWING", 20, 0);
                PersistentDataContainer container = skeleton.getPersistentDataContainer();
                container.set(Keys.MOB_RIOT, PersistentDataType.BYTE, (byte) 1);  // Tag the skeleton
            }
        }
        Bukkit.getLogger().info("Tagged all skeletons.");
    }

}
