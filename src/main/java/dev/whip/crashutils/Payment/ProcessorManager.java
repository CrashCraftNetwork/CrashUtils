package dev.whip.crashutils.Payment;

import dev.whip.crashutils.Payment.providers.VaultPaymentProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ProcessorManager {
    private static final Logger log = Logger.getLogger("Minecraft");

    private PaymentProcessor processor;

    public ProcessorManager(JavaPlugin plugin, PaymentProvider provider){
        if (provider != null){
            processor = new PaymentProcessor(provider);
        } else if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null){    //Try and default to vault
            processor = new PaymentProcessor(new VaultPaymentProvider());
        } else {
            log.severe("[CrashUtils] Unable to locate a specified payment provider or default to vault. Are they installed?");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    public PaymentProcessor getProcessor(){
        return processor;
    }
}
