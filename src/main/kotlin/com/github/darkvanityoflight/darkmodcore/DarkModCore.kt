package com.github.darkvanityoflight.darkmodcore

import com.github.darkvanityoflight.darkmodcore.configparser.ADarkModConfigParser
import com.github.darkvanityoflight.darkmodcore.configparser.ConfigParser

class DarkModCore : ADarkMod() {
    override var configParser: ADarkModConfigParser = ConfigParser()

    override fun onEnable() {
        super.onEnable()
        info("[DarkModCore] Enabling DarkModCore")
    }
}