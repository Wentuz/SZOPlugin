package wentuziak.szoplugin;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;

public class CowCommand implements CommandExecutor, TabExecutor{

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command");

            return true;
        }

        if (args.length > 1) {
            return false;
        }


        Player player = (Player) sender;
        Cow cow = player.getWorld().spawn(player.getLocation(), Cow.class);

        if (args.length == 1 && args[0].equalsIgnoreCase("baby")) {
            cow.setBaby();
        }
        
        
        
        cow.setCustomName(ChatColor.RED + "MOMMY MILKERS");
        cow.setCustomNameVisible(true);



        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }
}
