package dev.whip.crashutils.Payment;

public class ProviderInitializationException extends Exception{
    public ProviderInitializationException(){
        super("Unable to initialize payment provider");
    }
}
