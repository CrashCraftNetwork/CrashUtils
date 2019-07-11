package dev.whip.crashutils.Payment;

import java.util.UUID;

public class PaymentProcessor {
    private PaymentProvider provider;

    PaymentProcessor(PaymentProvider provider){
        if (provider == null || !provider.checkRequirements())
            throw new RuntimeException("Payment processor aws null or failed requirements");

        this.provider = provider;

        try {
            provider.setup();
        } catch (ProviderInitializationException e){
            e.printStackTrace();
        }
    }

    public TransactionRecipe makeTransaction(UUID user, TransactionType type, String comment, double amount){
        if (amount == 0){
            return new TransactionRecipe(user, amount, "Payment Processor Requirement Check", "Transaction amount cannot be 0");
        }
        return provider.makeTransaction(user, type, comment, amount);
    }

    public TransactionRecipe makeTransaction(UUID user, String comment, double amount){
        return makeTransaction(user, amount > 0 ? TransactionType.DEPOSIT : TransactionType.WITHDRAW, comment, amount);
    }

    public double getBalance(UUID user){
        return provider.getBalance(user);
    }
}
