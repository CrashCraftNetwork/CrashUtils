package dev.whip.crashutils.menusystem;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TestMenu extends GUI {
    public TestMenu(Player player) {
        super(player, "Test Menu", 54);
        setupGUI();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void loadItems() {
        inv.setItem(13, createGuiItem("test", Material.CYAN_CONCRETE));
    }

    @Override
    public void onClose() {
        System.out.println("Closing window");
    }

    @Override
    public void onClick(InventoryClickEvent event, String rawItemName) {
        System.out.println(event.getSlot() + " was clicked and rawItemName: " + rawItemName);
    }
}
