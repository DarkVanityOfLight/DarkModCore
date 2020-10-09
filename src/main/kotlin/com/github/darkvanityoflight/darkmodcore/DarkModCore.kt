package com.github.darkvanityoflight.darkmodcore

import com.github.darkvanityoflight.darkmodcore.configparser.ConfigParser

class DarkModCore : ADarkMod() {
    var configParser: ConfigParser = ConfigParser()

    override fun onEnable() {
        super.onEnable()
        info("[DarkModCore] Enabling DarkModCore")
    }
}