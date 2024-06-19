package wentuziak.szoplugin;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

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
    getCommand("szopCow").setExecutor(new CowCommand());

    CustomRecipes.register();

    LOGGER.info("szoplugin enabled");
  }

  public void onDisable()
  {

  }

  public static SzoPlugin getInstance() {
    return SzoPlugin.getPlugin(SzoPlugin.class);
  }
}
