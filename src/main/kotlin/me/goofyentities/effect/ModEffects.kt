package me.goofyentities.effect

import me.goofyentities.Goofyentities
import me.goofyentities.effect.effects.CritEffect
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

object ModEffects {
    val CRIT_EFFECT: RegistryEntry.Reference<StatusEffect?>? = Registry.registerReference(
        Registries.STATUS_EFFECT,
        Identifier.of(Goofyentities.MOD_ID, "crit_effect"),
        CritEffect.addAttributeModifier(
            EntityAttributes.ATTACK_DAMAGE,
            Identifier.of(Goofyentities.MOD_ID, "crit_effect"),
            1.5,
            EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
        )
    )

    fun registerModEffects() {
        Goofyentities.logger?.info("Registering ModEffects for ${Goofyentities.MOD_ID}")
    }
}