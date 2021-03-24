package com.github.darkvanityoflight.recraftedcore.api

import org.bukkit.Bukkit
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.entity.Player
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import java.util.*
import java.util.logging.Level

/**
 * @author DarkVanityOfLight
 */


/**
 * A wrapper for the getPlayer function from Bukkit
 * this is mainly for testing purposes
 */
open class BukkitWrapper{

    /**
     * Get a Bukkit player from a UUID
     * @param uuid The UUID of the player to get
     */
    open fun getPlayer(uuid: UUID): Player? {
        return Bukkit.getPlayer(uuid)
    }

    open fun info(message: String?) {
        Bukkit.getLogger().log(Level.INFO, message)
    }

    open fun info(message: String?, vararg vars: Any?) {
        Bukkit.getLogger().log(Level.INFO, message, vars)
    }

    open fun severe(message: String?) {
        Bukkit.getLogger().log(Level.SEVERE, message)
    }

    open fun severe(message: String?, error: Throwable?) {
        Bukkit.getLogger().log(Level.SEVERE, message, error)
    }

    open fun warning(message: String?) {
        Bukkit.getLogger().log(Level.WARNING, message)
    }

    open fun warning(message: String?, error: Throwable?) {
        Bukkit.getLogger().log(Level.WARNING, message, error)
    }

    open fun warning(message: String?, vararg vars: Any?) {
        Bukkit.getLogger().log(Level.WARNING, message, vars)
    }

    open fun notify(msg: String, color: BarColor, style: BarStyle, displayTime: Int, players: Collection<Player>){
        com.github.darkvanityoflight.recraftedcore.utils.notifyutils.notify(msg, color, style, displayTime, players)
    }

    open fun createInventory(holder: InventoryHolder?, type: InventoryType): Inventory {
        return Bukkit.createInventory(holder, type)
    }
    
    open fun createInventory(holder: InventoryHolder?, type: InventoryType, title: String): Inventory {
        return Bukkit.createInventory(holder, type, title)
    }

    open fun createInventory(holder: InventoryHolder?, size: Int): Inventory{
        return Bukkit.createInventory(holder, size)
    }

    open fun createInventory(holder: InventoryHolder?, size: Int, title: String): Inventory {
        return Bukkit.createInventory(holder, size, title)
    }

    open fun getOnlinePlayers(): Set<Player>{
        return Bukkit.getOnlinePlayers().toSet()
    }
}