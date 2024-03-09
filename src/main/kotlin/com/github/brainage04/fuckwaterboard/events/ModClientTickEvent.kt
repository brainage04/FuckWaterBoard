package com.github.brainage04.fuckwaterboard.events

import com.github.brainage04.fuckwaterboard.FuckWaterBoard
import com.github.brainage04.fuckwaterboard.utils.ChatUtils
import com.github.brainage04.fuckwaterboard.utils.ConfigUtils
import net.minecraft.client.Minecraft
import net.minecraft.entity.player.EntityPlayer
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent
import java.util.concurrent.ThreadLocalRandom

class ModClientTickEvent {
    companion object {
        var ticksSinceWaterboard = 0
    }

    fun villagerSoundDelay(): Int {
        return ThreadLocalRandom.current().nextInt(-ConfigUtils.mainCategory.ambientSounds.villagerSounds.maxDelay, -ConfigUtils.mainCategory.ambientSounds.villagerSounds.minDelay + 1)
    }

    private var ticksSinceVillagerSounds = arrayListOf(
        villagerSoundDelay(),
        villagerSoundDelay(),
        villagerSoundDelay(),
        villagerSoundDelay(),
        villagerSoundDelay(),
        villagerSoundDelay(),
        villagerSoundDelay(),
        villagerSoundDelay(),
    )

    private val villagerSounds = arrayListOf(
        "mob.villager.haggle",
        "mob.villager.idle",
        "mob.villager.no",
        "mob.villager.yes",
    )

    @SubscribeEvent
    fun tick(event: ClientTickEvent) {
        if (event.phase != TickEvent.Phase.END) return

        val minecraft = Minecraft.getMinecraft() ?: return
        val player = minecraft.thePlayer ?: return

        if (ConfigUtils.mainCategory.ambientSounds.villagerSounds.enabled) {
            for (i in ticksSinceVillagerSounds.indices) {
                ticksSinceVillagerSounds[i] += 1 // increment all 8 timers every tick

                if (ticksSinceVillagerSounds[i] >= 0) { // detect a random player within 16 (-1) blocks and make a villager sound emit from them
                    val playerList = player.worldObj.getEntitiesWithinAABB(EntityPlayer::class.java, player.entityBoundingBox.expand(15.0, 15.0, 15.0))
                    playerList.remove(player)

                    if (playerList.size < 1) continue // skip if user is the only player available

                    val randomPlayer = playerList[ThreadLocalRandom.current().nextInt(0, playerList.size)]
                    val randomSound = villagerSounds[ThreadLocalRandom.current().nextInt(0, villagerSounds.size)]

                    // LOGGER.info("Villager sound playing at player ${randomPlayer.name}")

                    player.worldObj.playSound(randomPlayer.posX, randomPlayer.posY, randomPlayer.posZ, randomSound, 1.0f, 1.0f, false)

                    if (ConfigUtils.mainCategory.ambientSounds.villagerSounds.minDelay >= ConfigUtils.mainCategory.ambientSounds.villagerSounds.maxDelay) { // prevents crash when user changes min delay higher than max delay
                        ConfigUtils.mainCategory.ambientSounds.villagerSounds.minDelay = ConfigUtils.mainCategory.ambientSounds.villagerSounds.maxDelay - 1
                        FuckWaterBoard.config.saveNow()
                    }

                    ticksSinceVillagerSounds[i] = villagerSoundDelay()
                }
            }
        }

        if (ConfigUtils.tooltipsCategory.launchTooltip) {
            ChatUtils.messageToChat("This mod can be triggered by typing the word \"WATERBOARD\" (all caps no spaces) into the chat.")
            ChatUtils.messageToChat("This will play the custom sound and cause other people to say a variation of \"FUCK WATERBOARD\" and play the sound for them as well.")
            ChatUtils.messageToChat("This tooltip will now be automatically disabled (see \"Launch Tooltip\" in \"Tooltip Category\" of /fuckwaterboard).")

            ConfigUtils.tooltipsCategory.launchTooltip = false
            FuckWaterBoard.config.saveNow()
        }
    }
}