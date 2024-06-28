package wentuziak.szoplugin;

import org.bukkit.entity.Player;
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

}
