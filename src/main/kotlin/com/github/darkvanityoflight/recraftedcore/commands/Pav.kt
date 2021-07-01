package com.github.darkvanityoflight.recraftedcore.commands

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import java.util.*

const val PAV_UUID = "064296c7-68d0-49cc-a493-da4dcf6abcac"

class Pav: CommandExecutor {

    /**
     * Executes the given command, returning its success.
     * <br></br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender Source of the command
     * @param command Command which was executed
     * @param label Alias of the command which was used
     * @param args Passed command arguments
     * @return true if a valid command, otherwise false
     */
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val pavUuid = UUID.fromString(PAV_UUID)

        val pav = Bukkit.getOfflinePlayer(pavUuid)
        if (pav.isOnline){
            pav as Player
            sender.sendMessage("${ChatColor.GREEN}Pav is online!!")
            sender.sendMessage("${ChatColor.GREEN}He is currently at X:${pav.location.blockX}, Y:${pav.location.blockY}, Z:${pav.location.blockZ}")
            sender.sendMessage("${ChatColor.GREEN}I'll greet him from you!!")

            pav.sendMessage("${ChatColor.GREEN}Player ${sender.name} wants to say Hi")
        }else{
            sender.sendMessage("${ChatColor.RED}Pav is not online right now ;(")
        }
        return true
    }
}