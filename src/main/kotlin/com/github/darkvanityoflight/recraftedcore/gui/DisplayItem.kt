package com.github.darkvanityoflight.recraftedcore.gui

import org.bukkit.inventory.ItemStack

/**
 * Represents a Item that can be displayed in an [InventoryGUI]
 */
interface DisplayItem {
    val itemStack: ItemStack
}