package me.goofyentities.particle.particles

import net.minecraft.client.particle.BillboardParticle
import net.minecraft.client.particle.Particle
import net.minecraft.client.particle.ParticleFactory
import net.minecraft.client.particle.ParticleTextureSheet
import net.minecraft.client.particle.SpriteProvider
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType
import net.minecraft.util.math.random.Random

class GeneralParticle : BillboardParticle {
    constructor(
        clientWorld: ClientWorld,
        x: Double, y: Double, z: Double,
        spriteProvider: SpriteProvider,
        xSpeed: Double, ySpeed: Double, zSpeed: Double,

        red: Float, green: Float, blue: Float,
        maxAge: Int
    ) : super(
        clientWorld,
        x, y, z,
        xSpeed, ySpeed, zSpeed,
        spriteProvider.first
    ) {
        this.maxAge = maxAge
        this.updateSprite(spriteProvider)

        this.red = red
        this.green = green
        this.blue = blue
    }

    override fun textureSheet(): ParticleTextureSheet? = ParticleTextureSheet.SINGLE_QUADS
    override fun getRenderType(): RenderType? = RenderType.PARTICLE_ATLAS_TRANSLUCENT

    class Factory : ParticleFactory<SimpleParticleType> {
        val spriteProvider: SpriteProvider
        val red: Float
        val green: Float
        val blue: Float
        val maxAge: Int

        constructor(spriteProvider: SpriteProvider, red: Float = 0f, green: Float = 0f, blue: Float = 0f, maxAge: Int = 10) {
            this.spriteProvider = spriteProvider
            this.red = red
            this.green = green
            this.blue = blue
            this.maxAge = maxAge
        }

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
        ): Particle? = if (world is ClientWorld) GeneralParticle(
            world,
            x,y,z,
            this.spriteProvider,
            velocityX, velocityY,velocityZ,
            this.red, this.green, this.blue,
            this.maxAge
        ) else null
    }
}