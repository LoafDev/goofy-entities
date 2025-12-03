package me.goofyentities

import me.goofyentities.block.ModBlock
import me.goofyentities.particle.ModParticle
import net.fabricmc.api.ClientModInitializer

object GoofyentitiesClient : ClientModInitializer {
    override fun onInitializeClient() {
        ModParticle.registerParticlesClient()
        ModBlock.registerBlockClient()
    }
}