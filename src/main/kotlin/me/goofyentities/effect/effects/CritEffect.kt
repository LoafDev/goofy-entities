package me.goofyentities.effect.effects

import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffect
import net.minecraft.entity.effect.StatusEffectCategory
import net.minecraft.entity.mob.HostileEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld

object CritEffect : StatusEffect(StatusEffectCategory.BENEFICIAL, 0xe9b8b3) {
    override fun canApplyUpdateEffect(duration: Int, amplifier: Int): Boolean = true

    override fun applyUpdateEffect(world: ServerWorld?, entity: LivingEntity?, amplifier: Int): Boolean {
        if (entity is PlayerEntity) {
            //entity.addCritParticles(entity)
        } else if (entity is HostileEntity) {
            entity.addDeathParticles()
        }

        return super.applyUpdateEffect(world, entity, amplifier)
    }
}