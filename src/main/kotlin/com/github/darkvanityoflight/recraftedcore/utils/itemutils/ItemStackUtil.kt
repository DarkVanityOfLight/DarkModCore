package com.github.darkvanityoflight.recraftedcore.utils.itemutils

import org.bukkit.inventory.ItemStack

fun ItemStack.setName(name: String?){
    val meta = this.itemMeta
    meta?.setDisplayName(name)
    this.itemMeta = meta
}

fun ItemStack.getName(): String?{
    return this.itemMeta?.displayName
}

fun ItemStack.addLore(vararg lore: String){
    val meta = this.itemMeta
    var currentLore= meta?.lore
    if (currentLore == null){
        currentLore = emptyList<String>().toMutableList()
    }
    for (i in lore){
        currentLore.add(i)
    }
    meta?.lore = currentLore
    this.itemMeta = meta
}

fun ItemStack.setLore(vararg lore: String){
    val meta = this.itemMeta
    meta?.lore = lore.toMutableList()
}