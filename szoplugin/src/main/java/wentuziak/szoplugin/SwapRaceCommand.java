package wentuziak.szoplugin;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

public class SwapRaceCommand implements TabExecutor{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onTabComplete'");
    }
}
