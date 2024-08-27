package wentuziak.szoplugin;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import wentuziak.szoplugin.customcrafting.CustomRecipes;
import wentuziak.szoplugin.entityevents.EntityCombat;
import wentuziak.szoplugin.entityevents.EntityListener;
import wentuziak.szoplugin.playerevents.InteractionListener;
import wentuziak.szoplugin.playerevents.PlayerCombat;
import wentuziak.szoplugin.playerevents.PlayerMining;

/*
 * szoplugin java plugin
 */
public class SzoPlugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("szoplugin");

  public void onEnable()
  {
    getServer().getPluginManager().registerEvents(new EntityListener(), this);
    getServer().getPluginManager().registerEvents(new InteractionListener(), this);
    getServer().getPluginManager().registerEvents(new PlayerMining(), this);
    getServer().getPluginManager().registerEvents(new PlayerCombat(), this);
    getServer().getPluginManager().registerEvents(new EntityCombat(), this);
    getCommand("swapRace").setExecutor(new SwapRaceCommand());
    getCommand("removeRace").setExecutor(new RemoveRaceCommand());
    
    CustomRecipes.register();

    LOGGER.info("-----------------------------");
    LOGGER.info(" ");
    LOGGER.info("        szoplugin enabled");
    LOGGER.info(" ");
    LOGGER.info("-----------------------------");
  }

  public void onDisable()
  {

  }

  public static SzoPlugin getInstance() {
    return SzoPlugin.getPlugin(SzoPlugin.class);
  }
}
