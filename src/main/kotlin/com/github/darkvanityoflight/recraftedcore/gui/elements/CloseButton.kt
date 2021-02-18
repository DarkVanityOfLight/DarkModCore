package com.github.darkvanityoflight.recraftedcore.gui.elements

import com.github.darkvanityoflight.recraftedcore.gui.Clickable
import com.github.darkvanityoflight.recraftedcore.gui.GUIManager
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class CloseButton(itemStack: ItemStack) : Clickable(itemStack) {
    override fun onClick(player: Player) {
        GUIManager.forceClose(player)
    }
}