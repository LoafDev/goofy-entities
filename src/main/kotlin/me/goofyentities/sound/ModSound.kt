package me.goofyentities.sound

import me.goofyentities.Goofyentities
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object ModSound {
    val BLACK_FLASH_SOUND = registerSound("black_flash")

    private fun registerSound(id: String): SoundEvent {
        val identifier = Identifier.of(Goofyentities.MOD_ID, id)
        return Registry.register(
            Registries.SOUND_EVENT,
            identifier,
            SoundEvent.of(identifier)
        )
    }

    fun registerSounds() {
        Goofyentities.logger?.info("registering sound for ${Goofyentities.MOD_ID}")
    }
}