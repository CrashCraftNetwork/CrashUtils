package dev.whip.crashutils.Payment;

import java.util.UUID;
import java.util.function.Consumer;

public class PaymentProcessor {
    private final PaymentProvider provider;

    PaymentProcessor(PaymentProvider provider) throws ProviderInitializationException {
        if (provider == null || !provider.checkRequirements())
            throw new RuntimeException("Payment processor aws null or failed requirements");

        this.provider = provider;

        provider.setup();
    }

    public void makeTransaction(UUID user, TransactionType type, String comment, double amount, Consumer<TransactionRecipe> callback){
        if (amount == 0){
            callback.accept(new TransactionRecipe(user, amount, "Payment Processor Requirement Check", "Transaction amount cannot be 0"));
        }
        provider.makeTransaction(user, type, comment, amount, callback);
    }

    public void makeTransaction(UUID user, String comment, double amount, Consumer<TransactionRecipe> callback){
        makeTransaction(user, amount > 0 ? TransactionType.DEPOSIT : TransactionType.WITHDRAW, comment, amount, callback);
    }

    public void getBalance(UUID user, Consumer<Double> callback){
        provider.getBalance(user, callback);
    }
}
