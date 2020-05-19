package dev.whip.crashutils;

import dev.whip.crashutils.Payment.PaymentProvider;
import dev.whip.crashutils.Payment.ProcessorManager;
import dev.whip.crashutils.menusystem.CrashMenuController;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class CrashUtils implements Listener {
    private static JavaPlugin plugin;

    public CrashUtils(JavaPlugin javaPlugin){
        plugin = javaPlugin;
    }

    public void setupMenuSubSystem(){
        Bukkit.getServer().getPluginManager().registerEvents(new CrashMenuController(plugin), plugin);     //Gui controller
    }

    public ProcessorManager setupPaymentProvider(PaymentProvider provider){
        return new ProcessorManager(plugin, provider);
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}
