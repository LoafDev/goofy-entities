package me.goofyentities.particle.particles

import net.minecraft.client.particle.BillboardParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType
import net.minecraft.util.math.random.Random

class BlackFlashParticle : BillboardParticle {
    constructor(
        clientWorld: ClientWorld,
        x: Double, y: Double, z: Double,
        spriteProvider: SpriteProvider,
        xSpeed: Double, ySpeed: Double, zSpeed: Double
    ) : super(
        clientWorld,
        x, y, z,
        xSpeed, ySpeed, zSpeed,
        spriteProvider.first
    ) {
        this.maxAge = 10
        this.velocityMultiplier = 1.2f
        this.updateSprite(spriteProvider)

        this.red = 1.0f
        this.blue = 1.0f
        this.green = 1.0f
    }

    override fun textureSheet(): ParticleTextureSheet? = ParticleTextureSheet.SINGLE_QUADS
    override fun getRenderType(): RenderType? = RenderType.PARTICLE_ATLAS_TRANSLUCENT

    class Factory : ParticleFactory<SimpleParticleType> {
        val spriteProvider: SpriteProvider
        constructor(spriteProvider: SpriteProvider) { this.spriteProvider = spriteProvider }

        override fun createParticle(
            parameters: SimpleParticleType?,
            world: ClientWorld?,
            x: Double,
            y: Double,
            z: Double,
            velocityX: Double,
            velocityY: Double,
            velocityZ: Double,
            random: Random?
        ): Particle? = if (world is ClientWorld) BlackFlashParticle(
            world,
            x,y,z,
            this.spriteProvider,
            velocityX, velocityY,velocityZ
        ) else null
    }
}