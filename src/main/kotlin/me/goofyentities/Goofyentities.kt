package me.goofyentities

import me.goofyentities.effect.ModEffects
import net.fabricmc.api.ModInitializer
import me.goofyentities.item.ModItems
import net.fabricmc.fabric.api.event.player.AttackEntityCallback
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.server.world.ServerWorld
import net.minecraft.util.ActionResult
import net.minecraft.world.GameMode
import org.slf4j.LoggerFactory
import org.slf4j.Logger

object Goofyentities : ModInitializer {
	const val MOD_ID = "goofy-entities"
	val logger: Logger? = LoggerFactory.getLogger(MOD_ID)

	override fun onInitialize() {
		logger?.info(MOD_ID)
		ModItems.registerModItems()
		ModEffects.registerModEffects()
		registerAttackEvent()
	}

	// don't know where to put this
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
					entity.damage(world, damageSource, baseDamage.toFloat())
					player.addCritParticles(entity)

					if (isCrit) { logger?.info("CRIT! $baseDamage") }
				}
			}

			ActionResult.PASS
		}
	}
}