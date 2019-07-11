package dev.whip.crashutils.menusystem;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class CrashGuiHolder implements InventoryHolder {
    final private Player player;
    final private GUI manager;

    private Inventory inventory;

    CrashGuiHolder(Player owner, GUI manager){
        this.player = owner;
        this.manager = manager;
    }

    @Override
    public Inventory getInventory() {
        return this.inventory;
    }

    public Player getPlayer(){
        return player;
    }

    public void setInventory(Inventory inventory){
        this.inventory = inventory;
    }

    public GUI getManager(){
        return manager;
    }
}
