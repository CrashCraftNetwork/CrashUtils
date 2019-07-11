package dev.whip.crashutils.Payment.providers;

import dev.whip.crashutils.Payment.PaymentProvider;
import dev.whip.crashutils.Payment.ProviderInitializationException;
import dev.whip.crashutils.Payment.TransactionRecipe;
import dev.whip.crashutils.Payment.TransactionType;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.UUID;

public class VaultPaymentProvider implements PaymentProvider {
    private Economy economy;

    @Override
    public String getProviderIdentifier() {
        return "VaultPaymentProvider";
    }

    @Override
    public boolean checkRequirements() {
        return Bukkit.getServer().getPluginManager().getPlugin("Vault") != null;
    }

    @Override
    public void setup() throws ProviderInitializationException {
        RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);

        if (rsp == null)
            throw new ProviderInitializationException();

        economy = rsp.getProvider();
    }

    @Override
    public TransactionRecipe makeTransaction(UUID user, TransactionType type, String comment, double amount) {
        EconomyResponse response = null;

        switch (type){
            case DEPOSIT:
                response = economy.depositPlayer(Bukkit.getOfflinePlayer(user), comment, amount);
                break;
            case WITHDRAW:
                response = economy.withdrawPlayer(Bukkit.getOfflinePlayer(user), comment, amount);
                break;
        }

        if (response == null)
            return new TransactionRecipe(user, amount, comment, "Vault response is null");

        if (response.transactionSuccess()) {
            return new TransactionRecipe(user, amount, comment);
        } else {
            return new TransactionRecipe(user, amount, comment, response.errorMessage);
        }
    }

    @Override
    public double getBalance(UUID user) {
        return economy.getBalance(Bukkit.getOfflinePlayer(user));
    }
}
