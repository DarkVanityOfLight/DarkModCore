package com.github.darkvanityoflight.recraftedcore.commands

import com.github.darkvanityoflight.recraftedcore.ARecraftedPlugin
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.PluginCommand
import org.bukkit.command.SimpleCommandMap
import org.bukkit.event.Event
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.RegisteredListener
import java.io.IOException
import java.net.URLClassLoader
import java.util.*


class PluginUnloader(val plugin: ARecraftedPlugin) : CommandExecutor{

    private fun getPlugin(pl: String): Plugin? {
        return if (Bukkit.getPluginManager().isPluginEnabled(pl)){
            Bukkit.getPluginManager().getPlugin(pl)
        }else{
            null
        }
        
    }

    private fun unload(plugin: Plugin): Pair<String, Exception?> {
        val name = plugin.name
        val pluginManager = Bukkit.getPluginManager()
        val commandMap: SimpleCommandMap
        val plugins: MutableList<Plugin?>
        val names: MutableMap<String?, Plugin?>
        val commands: MutableMap<String?, Command>
        var listeners: Map<Event?, SortedSet<RegisteredListener>>? = null
        var reloadlisteners = true
        pluginManager.disablePlugin(plugin)
        try {
            val pluginsField = Bukkit.getPluginManager().javaClass.getDeclaredField("plugins")
            pluginsField.isAccessible = true
            plugins = pluginsField[pluginManager] as MutableList<Plugin?>
            val lookupNamesField = Bukkit.getPluginManager().javaClass.getDeclaredField("lookupNames")
            lookupNamesField.isAccessible = true
            names = lookupNamesField[pluginManager] as MutableMap<String?, Plugin?>
            try {
                val listenersField = Bukkit.getPluginManager().javaClass.getDeclaredField("listeners")
                listenersField.isAccessible = true
                listeners = listenersField[pluginManager] as Map<Event?, SortedSet<RegisteredListener>>
            } catch (e: Exception) {
                reloadlisteners = false
            }
            val commandMapField = Bukkit.getPluginManager().javaClass.getDeclaredField("commandMap")
            commandMapField.isAccessible = true
            commandMap = commandMapField[pluginManager] as SimpleCommandMap
            val knownCommandsField = SimpleCommandMap::class.java.getDeclaredField("knownCommands")
            knownCommandsField.isAccessible = true
            commands = knownCommandsField[commandMap] as MutableMap<String?, Command>
        } catch (e: NoSuchFieldException) {
            e.printStackTrace()
            return Pair(ChatColor.RED.toString() + "Could not find a required field", e)
        } catch (e: IllegalAccessException) {
            e.printStackTrace()
            return Pair(ChatColor.RED.toString() + "Some Illegal access occurred", e)
        }
        pluginManager.disablePlugin(plugin)
        if (plugins.contains(plugin)) plugins.remove(plugin)
        if (names.containsKey(name)) names.remove(name)
        if (listeners != null && reloadlisteners) {
            for (set in listeners.values) {
                val it = set.iterator()
                while (it.hasNext()) {
                    val value = it.next()
                    if (value.plugin === plugin) {
                        it.remove()
                    }
                }
            }
        }
        val it: MutableIterator<Map.Entry<String?, Command>> = commands.entries.iterator()
        while (it.hasNext()) {
            val entry = it.next()
            if (entry.value is PluginCommand) {
                val c = entry.value as PluginCommand
                if (c.plugin === plugin) {
                    c.unregister(commandMap)
                    it.remove()
                }
            }
        }

        // Attempt to close the classloader to unlock any handles on the plugin's jar file.
        val cl = plugin.javaClass.classLoader
        if (cl is URLClassLoader) {
            try {
                val pluginField = cl.javaClass.getDeclaredField("plugin")
                pluginField.isAccessible = true
                pluginField[cl] = null
                val pluginInitField = cl.javaClass.getDeclaredField("pluginInit")
                pluginInitField.isAccessible = true
                pluginInitField[cl] = null
            } catch (ex: NoSuchFieldException) {
                return Pair(ChatColor.RED.toString() + "There was a field missing", ex)
            } catch (ex: SecurityException) {
                return Pair(ChatColor.RED.toString() + "Could not unload because of security issues", ex)
            } catch (ex: IllegalArgumentException) {
                return Pair(ChatColor.RED.toString() + "Illegal Arguments were supplied", ex)
            } catch (ex: IllegalAccessException) {
                return Pair(ChatColor.RED.toString() + "Illegal Access", ex)
            }
            try {
                cl.close()
            } catch (ex: IOException) {
                return Pair(ChatColor.RED.toString() + "Some IO exception occurred", ex)
            }
        }

        // Will not work on processes started with the -XX:+DisableExplicitGC flag, but lets try it anyway.
        // This tries to get around the issue where Windows refuses to unlock jar files that were previously loaded into the JVM.
        System.gc()

        return Pair("Successfully unloaded plugin $name", null)
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if(!sender.hasPermission("darkmodcore.unload")) { sender.sendMessage("You are not allowed to unload plugins, so fuck off"); return false}
        val pl = getPlugin(args[0])
        return if (pl == null){
            sender.sendMessage(ChatColor.RED.toString() + "Plugin ${args[0]} is not enabled, this is case sensitive.")
            false
        }else{
            val retMsg = unload(pl)
            if(retMsg.second == null){
                sender.sendMessage(ChatColor.RED.toString() + "Some error occurred, read it in the logs")
                plugin.severe(retMsg.first, retMsg.second)
                false
            }else{
                sender.sendMessage(ChatColor.GREEN.toString() + "Successfully unloaded plugin ${args[0]}")
                true
            }

        }
    }


}