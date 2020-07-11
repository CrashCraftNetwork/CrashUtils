package dev.whip.crashutils.Payment;

import dev.whip.crashutils.Payment.providers.FakePaymentProvider;
import dev.whip.crashutils.Payment.providers.VaultPaymentProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public class ProcessorManager {
    private final Logger logger;

    private PaymentProcessor processor;

    public ProcessorManager(JavaPlugin plugin) throws ProviderInitializationException{
        logger = plugin.getLogger();

        ServicePriority tempPriority = null;
        PaymentProvider paymentProvider = null;

        for (RegisteredServiceProvider<PaymentProvider> provider : Bukkit.getServicesManager().getRegistrations(PaymentProvider.class)){
            if (paymentProvider == null){
                tempPriority = provider.getPriority();
                paymentProvider = provider.getProvider();
            } else if (tempPriority.ordinal() < provider.getPriority().ordinal()){
                tempPriority = provider.getPriority();
                paymentProvider = provider.getProvider();
            }
        }

        if (paymentProvider != null){
            logger.info("Using " + paymentProvider.getProviderIdentifier() + " as a payment processor");
            processor = new PaymentProcessor(paymentProvider);
        } else if (Bukkit.getServer().getPluginManager().getPlugin("Vault") != null){    //Try and default to vault
            logger.info("Using Vault as a payment processor");
            processor = new PaymentProcessor(new VaultPaymentProvider());
        } else {
            logger.severe("[CrashUtils] Unable to locate a specified payment provider or default to vault. Are they installed?");
            Bukkit.getServer().getPluginManager().disablePlugin(plugin);
        }
    }

    public PaymentProcessor getProcessor(){
        if (processor == null){
            logger.severe("No payment provider was found, payments will be reverted to a fake payment provider where all transactions will be approved.");
            try {
                return new PaymentProcessor(new FakePaymentProvider());
            } catch (ProviderInitializationException e){
                logger.severe("An error occurred while initializing the fake payment provider.");
            }
        }
        return processor;
    }

    public static void register(JavaPlugin plugin, ServicePriority priority, PaymentProvider provider){
        Bukkit.getServicesManager().register(PaymentProvider.class, provider, plugin, priority);
        plugin.getLogger().info("Registering payment provider " + provider.getProviderIdentifier());
    }
}
