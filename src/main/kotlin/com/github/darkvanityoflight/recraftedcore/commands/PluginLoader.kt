package com.github.darkvanityoflight.recraftedcore.commands

import com.github.darkvanityoflight.recraftedcore.ARecraftedPlugin
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.InvalidDescriptionException
import org.bukkit.plugin.InvalidPluginException
import org.bukkit.plugin.Plugin
import org.bukkit.plugin.UnknownDependencyException
import java.io.File


class PluginLoader(val plugin: ARecraftedPlugin) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return if (sender is Player) {
            if (sender.hasPermission("recraftedcore.loadPlugin")) {


                true
            } else {
                sender.sendMessage("You aren't allowed to load a plugin dumbass")
                false
            }
        } else {

            true
        }
    }


    fun loadPlugin(pl: String): String? {
        var targetPlugin: Plugin? = null
        var msg = ""
        val pluginDir = File("plugins")
        if (!pluginDir.isDirectory) {
            return ChatColor.RED.toString() + "Plugin directory not found!"
        }
        var pluginFile = File(pluginDir, "$pl.jar")
        // plugin.getLogger().info("Want: " + pluginFile);
        if (!pluginFile.isFile) {
            for (f in pluginDir.listFiles()) {
                try {
                    if (f.name.endsWith(".jar")) {
                        val pdf = plugin.pluginLoader.getPluginDescription(f)
                        // plugin.getLogger().info("Searching for " + pl + ": " + f + " -> " + pdf.getName());
                        if (pdf.name.equals(pl, ignoreCase = true)) {
                            pluginFile = f
                            msg = "(via search) "
                            break
                        }
                    }
                } catch (e: InvalidDescriptionException) {
                    return ChatColor.RED.toString() + "Couldn't find file and failed to search descriptions!"
                }
            }
        }
        return try {
            plugin.server.pluginManager.loadPlugin(pluginFile)
            targetPlugin = getPlugin(pl)
            targetPlugin?.onLoad()
            if (targetPlugin != null) {
                plugin.server.pluginManager.enablePlugin(targetPlugin)
                ChatColor.GREEN.toString() + "" + getPlugin(pl) + " loaded " + msg + "and enabled!"
            }else {
                ChatColor.RED.toString() + "Loaded the plugin successfully but some error occurred while enabling it"
            }
        } catch (e: UnknownDependencyException) {
            ChatColor.RED.toString() + "File exists, but is missing a dependency!"
        } catch (e: InvalidPluginException) {
            plugin.severe("Tried to load invalid Plugin.\n", e)
            ChatColor.RED.toString() + "File exists, but isn't a loadable plugin file!"
        } catch (e: InvalidDescriptionException) {
            ChatColor.RED.toString() + "Plugin exists, but has an invalid description!"
        }
    }

    fun getPlugin(p: String?): Plugin? {
        for (pl in plugin.server.pluginManager.plugins) {
            if (pl.description.name.equals(p, ignoreCase = true)) {
                return pl
            }
        }
        return null
    }

}
