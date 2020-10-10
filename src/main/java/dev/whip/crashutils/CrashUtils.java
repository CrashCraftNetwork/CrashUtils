package dev.whip.crashutils;

import dev.whip.crashutils.caches.TextureCache;
import dev.whip.crashutils.menusystem.CrashMenuController;
import io.papermc.lib.PaperLib;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.awt.print.Paper;

public class CrashUtils implements Listener {
    private static JavaPlugin plugin;
    private static TextureCache textureCache;

    public CrashUtils(JavaPlugin javaPlugin){
        plugin = javaPlugin;

        PaperLib.suggestPaper(javaPlugin);
    }

    public void setupMenuSubSystem(){
        Bukkit.getServer().getPluginManager().registerEvents(new CrashMenuController(plugin), plugin);     //Gui controller
    }

    public void setupTextureCache(){
        if (textureCache == null){
            textureCache = new TextureCache();
            Bukkit.getServer().getPluginManager().registerEvents(textureCache, plugin);
        }
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
