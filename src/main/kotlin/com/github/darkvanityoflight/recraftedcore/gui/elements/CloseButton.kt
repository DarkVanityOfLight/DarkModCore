package com.github.darkvanityoflight.recraftedcore.gui.elements

import com.github.darkvanityoflight.recraftedcore.gui.Clickable
import com.github.darkvanityoflight.recraftedcore.gui.GUIManager
import com.github.darkvanityoflight.recraftedcore.utils.itemutils.setLore
import com.github.darkvanityoflight.recraftedcore.utils.itemutils.setName
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

object CloseButtonFactory{
    private val closeItemStack = ItemStack(Material.BARRIER)
    private val closeButton: CloseButton
    init{
        closeItemStack.setName("Close")
        closeItemStack.setLore("Close this inventory")

        closeButton = CloseButton(closeItemStack)
    }

    fun getCloseButton(): Clickable{
        return closeButton
    }
}

internal class CloseButton(itemStack: ItemStack) : Clickable(itemStack) {

    override fun onClick(player: Player) {
        GUIManager.forceClose(player)
    }
}