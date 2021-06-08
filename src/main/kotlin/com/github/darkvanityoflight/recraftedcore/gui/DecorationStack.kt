package com.github.darkvanityoflight.recraftedcore.gui

import org.bukkit.inventory.ItemStack

class DecorationStack(override val itemStack: ItemStack) : DisplayItem {
    override fun clone(): DisplayItem {
        return DecorationStack(itemStack)
    }
}