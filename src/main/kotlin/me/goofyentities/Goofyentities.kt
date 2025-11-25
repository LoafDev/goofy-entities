package me.goofyentities

import net.fabricmc.api.ModInitializer
import me.goofyentities.item.ModItems
import org.slf4j.LoggerFactory
import org.slf4j.Logger

object Goofyentities : ModInitializer {
	const val MOD_ID = "goofy-entities"
    val logger: Logger? = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		logger?.info(MOD_ID)
		ModItems.registerModItems()
	}
}