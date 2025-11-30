package me.goofyentities

import me.goofyentities.particle.ModParticle
import net.fabricmc.api.ClientModInitializer

object GoofyentitiesClient : ClientModInitializer {
    override fun onInitializeClient() {
        ModParticle.registerParticlesClient()
    }
}