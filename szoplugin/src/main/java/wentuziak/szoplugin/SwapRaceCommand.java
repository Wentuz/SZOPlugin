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
import org.bukkit.persistence.PersistentDataType;


public class SwapRaceCommand implements TabExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /swaprace [race] [player]");
            return false;
        }

        Player targetPlayer = Bukkit.getPlayer(args[1]);

        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Player not found: " + args[1]);
            return false;
        }

        String inputKey = args[0];
        NamespacedKey[] allKeys = Keys.getRaceKeys();
        boolean keyExists = false;

        for (NamespacedKey key : allKeys) {
            if (key.getKey().equals(inputKey)) {
                sender.sendMessage("The race '" + inputKey + "' exists.");
                keyExists = true;
            }
        }

        if (!keyExists) {
            sender.sendMessage(ChatColor.RED + "The race '" + inputKey + "' does not exist.");
            return false;
        }else{
            Player player = (Player) sender;
            String getKey = "RACE_" + args[0].toUpperCase();
            NamespacedKey setKey = Keys.getKeyByName(getKey);
            player.getPersistentDataContainer().set(setKey, PersistentDataType.BOOLEAN, true);
            sender.sendMessage("Key '" + inputKey + "' has been set for the player.");

        }

        sender.sendMessage("Player argument: " + targetPlayer.getName());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            return java.util.Arrays.asList("dwarf", "celestial");
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
