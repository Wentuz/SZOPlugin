package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;


public class SwapRaceCommand implements TabExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 2) {
            sender.sendMessage("Usage: /swaprace [race] [player]");
            return false;
        }

        String stringArg = args[0];
        Player targetPlayer = Bukkit.getPlayer(args[1]);

        if (targetPlayer == null) {
            sender.sendMessage(ChatColor.RED + "Player not found: " + args[1]);
            return false;
        }



        sender.sendMessage("String argument: " + stringArg);
        sender.sendMessage("Player argument: " + targetPlayer.getName());
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 1){
            return java.util.Arrays.asList("dwarf");
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
