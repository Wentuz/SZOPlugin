package wentuziak.szoplugin;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

public class MagicItems {

    public static void teleportSpell(Player player){
        Block targetBlock = player.getTargetBlock(null, 50);

        Location blockLocation = targetBlock.getLocation();

        int blockX = blockLocation.getBlockX();
        int blockY = blockLocation.getBlockY();
        int blockZ = blockLocation.getBlockZ();

        float yaw = player.getLocation().getYaw();
        float pitch = player.getLocation().getPitch();

        Location targetLocation = new Location(player.getWorld(), blockX, blockY + 1, blockZ, yaw, pitch);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 100);
        player.teleport(targetLocation);
        player.playSound(player.getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, 10, 10);
        LogicHolder.givePotionEffect(player, "BLINDNESS", 80, 0);
        player.getWorld().spawnParticle(Particle.PORTAL, player.getLocation(), 100);

    }

    public static void spiritLeech(Player player, PersistentDataContainer playerContainer){
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 10, 10);
        LogicHolder.throwSnowball(player, playerContainer);
    }

    public static void spiritLeechEffect(LivingEntity targetEntity){
        if (targetEntity instanceof Player) {
            ((Player) targetEntity).playSound(targetEntity.getLocation(), Sound.ENTITY_ENDERMITE_DEATH, 10, 10);
        }
        LogicHolder.givePotionEffect(targetEntity, "WITHER", 20*10, 1);
        LogicHolder.givePotionEffect(targetEntity, "DARKNESS", 20*10,0);
    }

    public static void obliterate(Player player){
        Location location = player.getLocation();
        for(int i = 0; i < 15; i++){            
            if (LogicHolder.critRoll(66)) {
                int x = (int)(Math.random() * 2);
                int y = (int)(Math.random() * 2);
                int z = (int)(Math.random() * 2);
                location.getWorld().spawnEntity(location.add(x, y, z), EntityType.TNT_MINECART);
            }
        }
        location.getWorld().createExplosion(location, 5, true, true);
    }


}
