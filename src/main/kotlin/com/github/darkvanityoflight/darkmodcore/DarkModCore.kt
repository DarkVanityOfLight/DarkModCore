package com.github.darkvanityoflight.darkmodcore

import com.github.darkvanityoflight.darkmodcore.configparser.ConfigParser

class DarkModCore : ADarkMod() {
    var configParser: ConfigParser = ConfigParser(config)

    override fun onEnable() {
        super.onEnable()
        info("[DarkModCore] Enabling DarkModCore")
    }
}