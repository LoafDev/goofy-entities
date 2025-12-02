package me.goofyentities.effect

import me.goofyentities.Goofyentities
import me.goofyentities.effect.effects.CritEffect
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier

object ModEffects {
    val CRIT_EFFECT = registerEffect(
        "crit_effect",
        CritEffect,
        EntityAttributes.ATTACK_DAMAGE,
        0.5,
        EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
    )

    private fun registerEffect(
        id: String,
        effect: StatusEffect,
        attributeModifier: RegistryEntry<EntityAttribute>,
        amount: Double,
        op: EntityAttributeModifier.Operation
    ): RegistryEntry.Reference<StatusEffect> {
        val identifier = Identifier.of(Goofyentities.MOD_ID, id)
        return Registry.registerReference(
            Registries.STATUS_EFFECT,
            identifier,
            effect.addAttributeModifier(
                attributeModifier,
                identifier,
                amount,
                op
            )
        )
    }

    fun registerModEffects() {
        Goofyentities.logger?.info("Registering ModEffects for ${Goofyentities.MOD_ID}")
    }
}