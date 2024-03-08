package com.github.brainage04.fuckwaterboard

import com.github.brainage04.fuckwaterboard.commands.manager.CommandManager
import com.github.brainage04.fuckwaterboard.config.manager.ConfigManager
import com.github.brainage04.fuckwaterboard.config.FuckWaterBoardConfig
import com.github.brainage04.fuckwaterboard.keybinds.ConfigKeyBind
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Loader
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import org.apache.logging.log4j.LogManager

@Mod(modid = FuckWaterBoard.MOD_ID, useMetadata = true)
class FuckWaterBoard {
    private fun registerKeyBinds(vararg keybinds: KeyBinding?) {
        for (keybind in keybinds) {
            ClientRegistry.registerKeyBinding(keybind)
        }
    }

    private fun registerEvents(vararg events: Any?) {
        for (event in events) {
            MinecraftForge.EVENT_BUS.register(event)
        }
    }

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        CommandManager()

        registerKeyBinds(
            configKeyBind
        )
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        configManager = ConfigManager()
        MinecraftForge.EVENT_BUS.register(configManager)

        registerEvents(
            configKeyBind
        )
    }

    companion object {
        lateinit var configManager: ConfigManager
        const val MOD_ID = "fuckwaterboard"
        const val MOD_NAME = "FuckWaterBoard"
        val LOGGER = LogManager.getLogger(MOD_ID)

        @JvmStatic
        val version: String
            get() = Loader.instance().indexedModList[MOD_ID]!!.version

        val config: FuckWaterBoardConfig
            get() = configManager.config ?: error("config is null")

        val configKeyBind = ConfigKeyBind()
    }
}
