package com.github.darkvanityoflight.recraftedcore.gui

import org.bukkit.inventory.ItemStack

/**
 * @author DarkVanityOfLight
 * This GUI manager and all classes of the GUI toolkit
 * are inspired by the CivModCore GUI toolkit
 */

/**
 * Represents a Item that can be displayed in an [InventoryGUI]
 */
interface DisplayItem {
    val itemStack: ItemStack
}