package me.goofyentities.event

import me.goofyentities.effect.ModEffects
import me.goofyentities.particle.ModParticle
import me.goofyentities.sound.ModSound
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.world.GameMode

object AttackEntityCallbackEvent {
    fun registerAttackEvent() { AttackEntityCallback.EVENT.register { player, world, hand, entity, res ->
        if (
            player.hasStatusEffect(ModEffects.CRIT_EFFECT) &&
            entity is LivingEntity &&
            player.gameMode != GameMode.SPECTATOR
        ) {
            val isCrit = player.fallDistance > 0.0f &&
                    player.getAttackCooldownProgress(0.5f) > 0.9f &&
                    !(
                        player.isOnGround &&
                        player.isClimbing &&
                        player.isTouchingWater &&
                        player.hasStatusEffect(StatusEffects.BLINDNESS) &&
                        player.isSprinting &&
                        player.hasVehicle()
                    )
            if (world is ServerWorld) {
                if (isCrit) {
                    world.spawnParticles(
                        ModParticle.BLACKFLASH_PARTICLE,
                        entity.x,
                        entity.y + entity.height / 2.0,
                        entity.z,
                        15,
                        0.0, 0.0, 0.0,
                        1.0,
                    )

                    if (!entity.entityWorld.isClient)
                        entity.playSound(ModSound.BLACK_FLASH_SOUND)
                } else {
                    world.spawnParticles(
                        ModParticle.GUARANTEECRIT_PARTICLE,
                        entity.x,
                        entity.y + entity.height / 2.0,
                        entity.z,
                        15,
                        0.0, 0.0, 0.0,
                        1.0
                    )

                    if (!entity.entityWorld.isClient)
                        entity.playSound(SoundEvents.ENTITY_PLAYER_ATTACK_CRIT)
                }
            }
        }
        ActionResult.PASS
    }}
}