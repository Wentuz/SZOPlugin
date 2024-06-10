package wentuziak.szoplugin;

import java.util.logging.Logger;
import org.bukkit.plugin.java.JavaPlugin;

/*
 * szoplugin java plugin
 */
public class Plugin extends JavaPlugin
{
  private static final Logger LOGGER=Logger.getLogger("szoplugin");

  public void onEnable()
  {
    LOGGER.info("szoplugin enabled");
  }

  public void onDisable()
  {
    LOGGER.info("szoplugin disabled");
  }
}
