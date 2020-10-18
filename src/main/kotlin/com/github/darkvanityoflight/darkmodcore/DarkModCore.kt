package com.github.darkvanityoflight.darkmodcore

import com.github.darkvanityoflight.darkmodcore.commands.PluginLoader
import com.github.darkvanityoflight.darkmodcore.commands.PluginUnloader
import com.github.darkvanityoflight.darkmodcore.configparser.ConfigParser

class DarkModCore : ADarkMod() {
    var configParser: ConfigParser = ConfigParser(config)

    override fun onEnable() {
        super.onEnable()
        info("[DarkModCore] Enabling DarkModCore")

        this.getCommand("load")?.setExecutor(PluginLoader(this))
        this.getCommand("unload")?.setExecutor(PluginUnloader(this))
    }
}