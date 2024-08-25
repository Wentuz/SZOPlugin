package wentuziak.szoplugin;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TaskManager {
    private static Map<UUID, Map<String, BukkitTask>> playerTasks = new HashMap<>();
    private static Map<Player, Boolean> restartScheduled = new HashMap<>();


    public static void addTask(Player player, String taskName, BukkitTask task){
        Map<String, BukkitTask> tasks = playerTasks.getOrDefault(player.getUniqueId(), new HashMap<>());
        tasks.put(taskName, task);
        playerTasks.put(player.getUniqueId(), tasks);
    }

    public static void removeTask(Player player, String taskName){
        Map<String, BukkitTask> tasks = playerTasks.get(player.getUniqueId());
        if (tasks != null) {
            tasks.remove(taskName);
            if (tasks.isEmpty()) {
                playerTasks.remove(player.getUniqueId());
            } else {
                playerTasks.put(player.getUniqueId(), tasks);
            }
        }
    }

    public static boolean isTaskRunning(Player player, String taskName){
        Map<String, BukkitTask> tasks = playerTasks.get(player.getUniqueId());
        if (tasks != null) {
            BukkitTask task = tasks.get(taskName);
            return task != null && !task.isCancelled();
        }
        return false;
    }

    public static void stopTask(Player player, String taskName){
        Map<String, BukkitTask> tasks = playerTasks.get(player.getUniqueId());
        if (tasks != null) {
            BukkitTask task = tasks.get(taskName);
            if (task != null && !task.isCancelled()) {
                task.cancel();
                tasks.remove(taskName);
                if (tasks.isEmpty()) {
                    playerTasks.remove(player.getUniqueId());
                } else {
                    playerTasks.put(player.getUniqueId(), tasks);
                }
            }
        }
    }

    public static boolean isRestartScheduled(Player player) {
        return restartScheduled.getOrDefault(player, false);
    }

    public static void setRestartScheduled(Player player, boolean status) {
        restartScheduled.put(player, status);
    }
}
