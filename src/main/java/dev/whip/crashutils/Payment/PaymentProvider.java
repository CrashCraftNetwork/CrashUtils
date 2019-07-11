package dev.whip.crashutils.Payment;

import java.util.UUID;

public interface PaymentProvider {
    String getProviderIdentifier();

    boolean checkRequirements();

    void setup() throws ProviderInitializationException;

    TransactionRecipe makeTransaction(UUID user, TransactionType type, String comment, double amount);

    double getBalance(UUID user);
}