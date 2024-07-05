package wentuziak.szoplugin;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

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

    public static void spiritLeech(Player player){
        player.playSound(player.getLocation(), Sound.ENTITY_ENDER_DRAGON_SHOOT, 10, 10);
        LogicHolder.throwSnowball(player);
    }


}
