package dev.whip.crashutils.menusystem.defaultmenus;

import dev.whip.crashutils.menusystem.GUI;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Function;

public class ConfirmationMenu extends GUI {
    private BiFunction<Player, Boolean, String> function;
    private Function<Player, String> onCloseFunction;
    private Player player;
    private String message;
    private ArrayList<String> description;
    private Material material;

    public ConfirmationMenu(Player player, String title, String message,
                            ArrayList<String> description, Material material,
                            BiFunction<Player, Boolean, String> function, Function<Player, String> onCloseFunction){
        super(player,title, 45);

        this.player = player;
        this.message = message;
        this.description = description;
        this.material = material;
        this.function = function;
        this.onCloseFunction = onCloseFunction;

        setupGUI();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void loadItems() {
        inv.setItem(13, createGuiItem(message,
                description, material));

        inv.setItem(29, createGuiItem(ChatColor.GOLD + "Cancel", Material.RED_CONCRETE));

        inv.setItem(33, createGuiItem(ChatColor.GOLD + "Accept", Material.GREEN_CONCRETE));
    }

    @Override
    public void onClose() {
        onCloseFunction.apply(player);
    }

    @Override
    public void onClick(InventoryClickEvent event, String rawItemName) {
        switch (rawItemName){
            case "accept":
                player.closeInventory();
                function.apply(player, true);
                break;
            case "cancel":
                player.closeInventory();
                function.apply(player, false);
                break;
        }
    }
}
