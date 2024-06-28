package wentuziak.szoplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class CustomTools {

    private static BukkitTask hastyToolTask;

    public static void hastyToolFunc(Player player) 
    {
        final Player finalPlayer = player;
        hastyToolTask = new BukkitRunnable() {
            @Override
            public void run(){
                    LogicHolder.givePotionEffect(finalPlayer, "FAST_DIGGING", 20*30, 0);
                }
        }.runTaskTimer(SzoPlugin.getInstance(), 1, 20*15);
    }

    public static void stopHastyToolTask() {
        if (hastyToolTask != null && !hastyToolTask.isCancelled()) {
            hastyToolTask.cancel();
            hastyToolTask = null; // Clear the reference to the cancelled task
        }
    }

    public static void treasureFishingRodFunc(int chanceForCrit, Player player, Projectile projectile, int playerLuck)
    {
        if (LogicHolder.critRoll(chanceForCrit)) {
            if (projectile instanceof org.bukkit.entity.FishHook) {
                org.bukkit.entity.FishHook fishHook = (org.bukkit.entity.FishHook) projectile;
                Location bobberLocation = fishHook.getLocation();
                LogicHolder.rollTreasure(playerLuck, bobberLocation);
            }
        }
    }

    public static void dwarfPickaxeFunc(int chanceForCrit, Player player, int playerLuck, Block brokenBlock)
    {   
        if (brokenBlock.getType() == Material.STONE || brokenBlock.getType() == Material.DEEPSLATE || brokenBlock.getType() == Material.DIAMOND_ORE || brokenBlock.getType() == Material.DEEPSLATE_DIAMOND_ORE) {
            if (brokenBlock.getType() == Material.DEEPSLATE) {
                chanceForCrit = chanceForCrit * 2;
            }
            if (brokenBlock.getType() == Material.DEEPSLATE_DIAMOND_ORE || brokenBlock.getType() == Material.DIAMOND_ORE) {
                chanceForCrit = chanceForCrit * 4;
                playerLuck = playerLuck + 2;
            }
            if (LogicHolder.critRoll(chanceForCrit)) {
                Location blockLocation = brokenBlock.getLocation();
                LogicHolder.rollTreasure(playerLuck, blockLocation);
                System.out.println("YES");
            }
        }
    }

}
