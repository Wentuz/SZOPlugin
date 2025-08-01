package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.races.UpdateAttributes;

public class RemoveRaceCommand implements TabExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 0 || args.length > 2) {
            sender.sendMessage("Usage: /removerace [player]");
            return false;
        }
        if (!sender.isOp()) {
            sender.sendMessage("Need op to use this command");
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[0]);
        if (targetPlayer == null && !(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Player not found: " + args[1]);
            return false;
        }else {
        	targetPlayer = (Player) sender;
        }
    
        unLoadRace(targetPlayer);
        sender.sendMessage("Races have been removed for the player.");
        return true;
    }


    public static void unLoadRace(Player player){    
        PersistentDataContainer dataContainer = player.getPersistentDataContainer();

        NamespacedKey[] raceKeys = Keys.getRaceKeys();

        for (NamespacedKey key : raceKeys) {
            if (dataContainer.has(key, PersistentDataType.BOOLEAN)) {
                String keyString = "RACE_" + key.getKey().toUpperCase();
                player.sendMessage(ChatColor.GREEN + keyString + " Unloaded");
                UpdateAttributes.attributeManager(player, true, keyString);

                NamespacedKey setKey = Keys.getKeyByName(keyString);
                player.getPersistentDataContainer().remove(setKey);
            }
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
         if (args.length == 1) {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        }
        return new ArrayList<>();
    }
}