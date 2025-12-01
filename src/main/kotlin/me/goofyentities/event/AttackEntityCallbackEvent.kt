package me.goofyentities.event

import me.goofyentities.effect.ModEffects
import me.goofyentities.particle.ModParticle
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.world.GameMode

object AttackEntityCallbackEvent {
    fun registerAttackEvent() {
        AttackEntityCallback.EVENT.register { player, world, hand, entity, res ->
            if (
                player.hasStatusEffect(ModEffects.CRIT_EFFECT) &&
                entity is LivingEntity &&
                player.gameMode != GameMode.SPECTATOR
            ) {
                val damageSource = player.damageSources.playerAttack(player)
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
                val baseDamage = player.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) *
                        if (isCrit) { 2.25f } else { 1.5f }

                if (world is ServerWorld) {
                    if (isCrit) {
                        world.spawnParticles(
                            ModParticle.BLACKFLASH_PARTICLE,
                            entity.x,
                            entity.y + entity.height / 2.0,
                            entity.z,
                            15,
                            0.0, 0.0, 0.0,
                            3.5,
                        )

                        world.spawnParticles(
                            ModParticle.BLACK_BLACKFLASH_PARTICLE,
                            entity.x,
                            entity.y + entity.height / 2.0,
                            entity.z,
                            10,
                            0.0, 0.0, 0.0,
                            4.0,
                        )
                    }
                    else world.spawnParticles(
                        ModParticle.GUARANTEECRIT_PARTICLE,
                        entity.x,
                        entity.y + entity.height / 2.0,
                        entity.z,
                        15,
                        0.0, 0.0, 0.0,
                        4.0
                    )

                    entity.damage(world, damageSource, baseDamage.toFloat())
                }
            }

            ActionResult.PASS
        }
    }
}