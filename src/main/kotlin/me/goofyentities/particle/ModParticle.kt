package me.goofyentities.particle

import me.goofyentities.Goofyentities
import me.goofyentities.particle.particles.BlackFlashParticle
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes
import net.minecraft.particle.SimpleParticleType
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.util.Identifier

object ModParticle {
    val BLACKFLASH_PARTICLE = registerParticle("black_flash_particle", FabricParticleTypes.simple())

    private fun registerParticle(name: String, particleType: SimpleParticleType): SimpleParticleType {
        return Registry.register(
            Registries.PARTICLE_TYPE,
            Identifier.of(Goofyentities.MOD_ID, name),
            particleType
        )
    }

    fun registerParticles() {
        Goofyentities.logger?.info("Registering particles for ${Goofyentities.MOD_ID}")
    }

    fun registerParticlesClient() {
        ParticleFactoryRegistry.getInstance().register(BLACKFLASH_PARTICLE) { BlackFlashParticle.Factory(it) }
    }
}