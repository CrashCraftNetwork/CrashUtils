package dev.whip.crashutils.Payment;

import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class TransactionRecipe {
    private UUID owner;

    private String comment;
    private double amount;

    private String transactionError;

    public TransactionRecipe(@NotNull UUID owner, @NotNull double amount, String comment) {
        this.owner = owner;
        this.comment = comment;
        this.amount = amount;
    }

    public TransactionRecipe(@NotNull UUID owner, @NotNull double amount, String comment, @NotNull String transactionError) {
        this.owner = owner;
        this.comment = comment;
        this.amount = amount;
        this.transactionError = transactionError;
    }

    public boolean transactionSuccess(){
        return transactionError == null;
    }

    public TransactionResponse getTransactionStatus(){
        return transactionError == null ? TransactionResponse.SUCCESS : TransactionResponse.ERROR;
    }

    public UUID getOwner() {
        return owner;
    }

    public String getComment() {
        return comment;
    }

    public double getAmount() {
        return amount;
    }

    public String getTransactionError() {
        return transactionError;
    }

}
