package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import wentuziak.szoplugin.races.UpdateAttributes;


public class SwapRaceCommand implements TabExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /swaprace [race] [player]");
            return false;
        }
        if (!sender.isOp()) {
            sender.sendMessage("Need op to use this command");
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);
        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Player not found: " + args[1]);
            return false;
        }

        String inputKey = args[0];
        NamespacedKey[] allKeys = Keys.getRaceKeys();
        boolean keyExists = Arrays.stream(allKeys).anyMatch(key -> key.getKey().equals(inputKey));


        if (!keyExists) {
            sender.sendMessage(ChatColor.RED + "The race '" + inputKey + "' does not exist.");
            return false;
        }
        String getKey = "RACE_" + args[0].toUpperCase();
        NamespacedKey setKey = Keys.getKeyByName(getKey);
        targetPlayer.getPersistentDataContainer().set(setKey, PersistentDataType.BOOLEAN, true);

        if (targetPlayer.getPersistentDataContainer().has(setKey, PersistentDataType.BOOLEAN)) {
            sender.sendMessage(ChatColor.GREEN + "The race '" + inputKey + "' has been set for the player.");
            UpdateAttributes.attributeManager(targetPlayer, false, getKey.toString());
            sender.sendMessage(ChatColor.GREEN + "Attributes updated.");
            
            return true;
        }else{
            sender.sendMessage(ChatColor.RED + "Failed to set the race '" + inputKey + "' for the player.");
            return false;
        }
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            return java.util.Arrays.asList(
                "dwarf", "celestial", "witch", "miskaru", "cara", "mewchant", "fossil",
                "zephyr", "sanguinite", "elf", "hobbit"
                );
        }
         if (args.length == 2) {
            List<String> playerNames = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()) {
                playerNames.add(player.getName());
            }
            return playerNames;
        }
        return new ArrayList<>();
    }
}