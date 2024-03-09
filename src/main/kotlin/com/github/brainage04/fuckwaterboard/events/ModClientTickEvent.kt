package com.github.brainage04.fuckwaterboard.events

import com.github.brainage04.fuckwaterboard.FuckWaterBoard
import com.github.brainage04.fuckwaterboard.utils.ChatUtils
import com.github.brainage04.fuckwaterboard.utils.ConfigUtils
import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent

class ModClientTickEvent {
    companion object {
        var ticksSinceWaterboard = 0
    }

    @SubscribeEvent
    fun tick(event: ClientTickEvent) {
        if (event.phase != TickEvent.Phase.END) return

        val minecraft = Minecraft.getMinecraft() ?: return
        val player = minecraft.thePlayer ?: return

        ticksSinceWaterboard++

        

        if (ConfigUtils.tooltipsCategory.launchTooltip) {
            ChatUtils.messageToChat("This mod can be triggered by typing the word \"WATERBOARD\" (all caps no spaces) into the chat.")
            ChatUtils.messageToChat("This will play the custom sound and cause other people to say a variation of \"FUCK WATERBOARD\" and play the sound for them as well.")
            ChatUtils.messageToChat("This tooltip will now be automatically disabled (see \"Launch Tooltip\" in \"Tooltip Category\" of /fuckwaterboard).")

            ConfigUtils.tooltipsCategory.launchTooltip = false
            FuckWaterBoard.config.saveNow()
        }
    }
}