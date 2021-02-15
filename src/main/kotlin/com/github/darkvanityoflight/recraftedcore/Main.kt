package com.github.darkvanityoflight.recraftedcore

import com.github.darkvanityoflight.recraftedcore.commands.PluginLoader
import com.github.darkvanityoflight.recraftedcore.commands.PluginUnloader
import com.github.darkvanityoflight.recraftedcore.configparser.ConfigParser

class Main : ARecraftedPlugin() {
    var configParser: ConfigParser = ConfigParser(config)

    override fun onEnable() {
        super.onEnable()
        info("Enabling RecraftedCore")
        plugin = this

        this.getCommand("load")?.setExecutor(PluginLoader(this))
        this.getCommand("unload")?.setExecutor(PluginUnloader(this))
    }

    companion object {
        lateinit var plugin: Main
    }
}