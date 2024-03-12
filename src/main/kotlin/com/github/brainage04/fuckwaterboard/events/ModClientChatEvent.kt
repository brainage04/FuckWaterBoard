package com.github.brainage04.fuckwaterboard.events

import com.github.brainage04.fuckwaterboard.utils.ConfigUtils
import com.github.brainage04.fuckwaterboard.utils.SoundUtils
import net.minecraft.client.Minecraft
import net.minecraftforge.client.event.ClientChatReceivedEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import java.util.*
import kotlin.random.Random

class ModClientChatEvent {
    private val matches = arrayListOf(
        "WATERBOARD",
        "WATER BOARD",
    )

    private val prefixes = arrayListOf(
        "FUCK",
        "FUCKING",
        "FUCKING HATE",
        "FREAKING",
        "FREAKING HATE",
        "I HATE",
        "I FUCKING HATE",
        "I FREAKING HATE",
    )

    var lastPrefixIndex = -1

    var lastMessage = ""

    @SubscribeEvent
    fun tick(event: ClientChatReceivedEvent) {
        val player = Minecraft.getMinecraft().thePlayer ?: return

        val overlayValue: Byte = 2

        if (event.type == overlayValue) return

        if (ConfigUtils.mainCategory.soundTriggers.fuckWaterBoard.enabled) {
            if (ModClientTickEvent.ticksSinceWaterboard < 0) return

            val unformattedText = event.message.unformattedText

            if (unformattedText.contains(player.name) && unformattedText.endsWith(lastMessage)) return

            for (match in matches) {
                if (unformattedText.uppercase(Locale.getDefault()).contains(match)) {
                    SoundUtils.playFuckWaterBoardSound()

                    val prefixIndex = Random.nextInt(0, prefixes.size)

                    lastPrefixIndex = prefixIndex

                    var message = "${prefixes[prefixIndex]} WATERBOARD"

                    val exclamPoints = Random.nextInt(ConfigUtils.mainCategory.randomRanges.minExclamPoints, ConfigUtils.mainCategory.randomRanges.maxExclamPoints)

                    for (i in 0..exclamPoints) message += "!"

                    player.sendChatMessage(message)

                    ModClientTickEvent.ticksSinceWaterboard = 0 - ConfigUtils.mainCategory.dangerZone.waterboardCooldown
                }
            }
        }
    }
}