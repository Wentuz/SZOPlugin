package wentuziak.szoplugin.entityevents;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.SzoPlugin;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class tagSpawnedMob implements Listener {
    
    private static final Random rand = new Random();
    private static ItemStack mobAirItem = new ItemStack(Material.AIR);

    public static void startTagger() {
        // Schedule the repeating task to run every 10 minutes (600 seconds)
        new BukkitRunnable() {
            @Override
            public void run() {
                tagRandomUndead();
            }
        }.runTaskTimer(SzoPlugin.getInstance(), 0L, 600 * 20L);
    }

    private static void tagRandomUndead() {
        // Loop through all worlds and entities
        for (Entity entity : Bukkit.getWorlds().get(0).getEntities()) { // Loop through all entities in the main world
            if ((entity.getType() == EntityType.SKELETON || entity.getType() == EntityType.ZOMBIE) && LogicHolder.critRoll(10)) {
                LivingEntity undead = (LivingEntity) entity;
                if(LogicHolder.critRoll(2)) createBossMob(entity);
                else if (LogicHolder.critRoll(2)) createHuntedMob(entity);
                else{
                    boolean isArmorTiered = LogicHolder.critRoll(33) ? true : false;
                    equipArmorEntity(undead, true, mobAirItem, mobAirItem, mobAirItem, mobAirItem, isArmorTiered);
                    tagSpawnedEntity(undead, Keys.MOB_RIOT);  // Tag the undead
                }
            }
        }
    }

    public static void tagSpawnedEntity(LivingEntity entity, NamespacedKey key) {
        PersistentDataContainer container = entity.getPersistentDataContainer();
        container.set(key, PersistentDataType.BYTE, (byte) 1);
    }

    public static void equipArmorEntity(LivingEntity entity, boolean isRandom, ItemStack mobHeadGear, ItemStack mobChest, ItemStack mobLegs, ItemStack mobBoots, boolean isArmorTiered){
        
        entity.getEquipment().setHelmetDropChance(0.05F);
        entity.getEquipment().setChestplateDropChance(0.05F);
        entity.getEquipment().setLeggingsDropChance(0.05F);
        entity.getEquipment().setBootsDropChance(0.05F);

        if (isRandom) {
            LogicHolder.equipRandomArmor(isArmorTiered, entity);
            return;
        }
        entity.getEquipment().setHelmet(mobHeadGear);
        entity.getEquipment().setChestplate(mobChest);
        entity.getEquipment().setLeggings(mobLegs);
        entity.getEquipment().setBoots(mobBoots);

    }

    public static void callToHuntTag(Player player){
        List<Entity> nearbyEntities = player.getNearbyEntities(100, 100, 100);

        List<Entity> validEntities = nearbyEntities.stream()
                .filter(entity -> entity instanceof Zombie || entity instanceof Skeleton)
                .collect(Collectors.toList());

        if (validEntities.isEmpty()) {
            player.sendMessage("No Zombies or Skeletons found within 100 blocks.");
            return;
        }

        // Select a random Zombie or Skeleton from the list
        Entity randomEntity = validEntities.get(rand.nextInt(validEntities.size()));

        if(LogicHolder.critRoll(100)) createBossMob(randomEntity);
        //createHuntedMob(randomEntity);

    }

    private static void createHuntedMob(Entity entity){
        tagSpawnedEntity((LivingEntity) entity, Keys.MOB_HUNT);
        tagSpawnedEntity((LivingEntity) entity, Keys.MOB_RIOT);

        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 5, 1);

        LogicHolder.equipRandomArmor(true, (LivingEntity) entity);
        ((Attributable) entity).getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.75);
        ((Attributable) entity).getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue(4);
        ((Attributable) entity).getAttribute(Attribute.MAX_HEALTH).setBaseValue(40);
        LogicHolder.givePotionEffect((LivingEntity) entity, "GLOWING", 20 * 60 * 30, 0);
    }
    private static void createBossMob(Entity entity){
        tagSpawnedEntity((LivingEntity) entity, Keys.MOB_MINI_BOSS);

        entity.getWorld().playSound(entity.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, 5, 1);

        LogicHolder.equipRandomArmor(true, (LivingEntity) entity);
        ((Attributable) entity).getAttribute(Attribute.MOVEMENT_SPEED).setBaseValue(0.60 * (Math.random() * 0.2 + 1));
        ((Attributable) entity).getAttribute(Attribute.ATTACK_DAMAGE).setBaseValue((int)(Math.random() * 4 + 4));
        ((Attributable) entity).getAttribute(Attribute.MAX_HEALTH).setBaseValue(40 + (int)(Math.random() * 40));
        LogicHolder.givePotionEffect((LivingEntity) entity, "GLOWING", 20 * 60 * 30, 0);
    }

}
