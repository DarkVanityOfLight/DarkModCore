package com.github.darkvanityoflight.recraftedcore.utils.notifyutils

import com.github.darkvanityoflight.recraftedcore.RecraftedPluginCore
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.boss.BarColor
import org.bukkit.boss.BarStyle
import org.bukkit.boss.BossBar
import org.bukkit.entity.Player
import org.bukkit.scheduler.BukkitRunnable

class TimerRunnable(private val bar: BossBar): BukkitRunnable() {

    override fun run() {
        bar.removeAll()
    }

}

fun notify(title : String, color : BarColor, style : BarStyle, displayTime : Int, players: Collection<Player>): BossBar{

    val bar = Bukkit.getServer().createBossBar(format(title), color, style)

    for(player in players){
        bar.addPlayer(player)
    }
    bar.isVisible = true;

    if (displayTime != 0){
        TimerRunnable(bar).runTaskLater(RecraftedPluginCore.plugin, (displayTime*20).toLong())
    }

    return bar
}

private fun format(msg: String): String {
    return ChatColor.translateAlternateColorCodes('&', msg)
}