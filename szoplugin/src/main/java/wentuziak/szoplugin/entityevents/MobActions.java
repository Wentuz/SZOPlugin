package wentuziak.szoplugin.entityevents;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityTargetEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.Keys;
import wentuziak.szoplugin.customlogic.LogicHolder;

public class MobActions {
    
    public static void onZombieAggro(LivingEntity entity, LivingEntity targetedEntity){
        if (targetedEntity instanceof Player) {
            
            return;

        }
    }

    public static void onSkeletonAggro(LivingEntity entity, LivingEntity targetedEntity){
        if (targetedEntity instanceof Player) {
            
            return;

        }
    }

}
