package me.goofyentities.particle.particles

import net.minecraft.client.particle.*
import net.minecraft.client.world.ClientWorld
import net.minecraft.particle.SimpleParticleType
import net.minecraft.util.math.random.Random

import kotlin.math.max

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
        0.0, 0.0, 0.0,
        spriteProvider.first
    ) {
        this.maxAge = max(maxAge - kotlin.random.Random.nextInt(0,2), 1)
        this.updateSprite(spriteProvider)

        this.red = red
        this.green = green
        this.blue = blue

        this.velocityMultiplier = 0.7f
        this.gravityStrength = 0.5f

        this.velocityX *= 0.1
        this.velocityY *= 0.1
        this.velocityZ *= 0.1

        this.velocityX += xSpeed * 0.4
        this.velocityY += ySpeed * 0.4
        this.velocityZ += zSpeed * 0.4
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