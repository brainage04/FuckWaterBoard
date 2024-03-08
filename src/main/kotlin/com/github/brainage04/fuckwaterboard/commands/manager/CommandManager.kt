package com.github.brainage04.fuckwaterboard.commands.manager

import com.github.brainage04.fuckwaterboard.FuckWaterBoard
import com.github.brainage04.fuckwaterboard.commands.core.SimpleCommand
import com.github.brainage04.fuckwaterboard.commands.core.SimpleCommand.ProcessCommandRunnable
import net.minecraft.command.ICommandSender
import net.minecraftforge.client.ClientCommandHandler

class CommandManager {
    init {
        registerCommand(FuckWaterBoard.MOD_ID) {
            FuckWaterBoard.configManager.openConfigGui()
        }
    }

    private fun registerCommand(name: String, function: (Array<String>) -> Unit) {
        ClientCommandHandler.instance.registerCommand(SimpleCommand(name, createCommand(function)))
    }

    private fun createCommand(function: (Array<String>) -> Unit) = object : ProcessCommandRunnable() {
        override fun processCommand(sender: ICommandSender?, args: Array<String>?) {
            if (args != null) function(args.asList().toTypedArray())
        }
    }
}