package com.github.darkvanityoflight.darkmodcore

import org.bukkit.plugin.java.JavaPlugin
import java.util.logging.Level

abstract class ADarkMod : IAmADarkMod, JavaPlugin() {

    override fun onEnable() {
        IAmADarkMod.instance = this
    }

    override fun debug(message: String?) {
        if (config.getBoolean("debug", false)) logger.log(Level.INFO, message)
    }

    override fun debug(message: String?, vararg vars: Any?) {
        if (config.getBoolean("debug", false)) logger.log(Level.INFO, message, vars)
    }

    override fun info(message: String?) {
        logger.log(Level.INFO, message)
    }

    override fun info(message: String?, vararg vars: Any?) {
        logger.log(Level.INFO, message, vars)
    }

    override fun severe(message: String?) {
        logger.log(Level.SEVERE, message)
    }

    override fun severe(message: String?, error: Throwable?) {
        logger.log(Level.SEVERE, message, error)
    }

    override fun warning(message: String?) {
        logger.log(Level.WARNING, message)
    }

    override fun warning(message: String?, error: Throwable?) {
        logger.log(Level.WARNING, message, error)
    }

    override fun warning(message: String?, vararg vars: Any?) {
        logger.log(Level.WARNING, message, vars)
    }

}