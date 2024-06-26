package wentuziak.szoplugin;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;
import org.bukkit.metadata.FixedMetadataValue;

public class CowCommand implements TabExecutor{

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
        
        cow.setMetadata("SzoPlugin", new FixedMetadataValue(SzoPlugin.getInstance(), true));
        cow.setCustomName(ChatColor.RED + "MOMMY MILKERS");
        cow.setCustomNameVisible(true);

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        
        if(args.length == 1){
            return java.util.Arrays.asList("baby");
        }

        return new ArrayList<>();
    }
}
