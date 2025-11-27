package me.goofyentities.item.items

import me.goofyentities.effect.ModEffects
import me.goofyentities.item.ModItems
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FuelRegistryEvents
import net.minecraft.component.type.ConsumableComponent
import net.minecraft.component.type.ConsumableComponents
import net.minecraft.component.type.FoodComponent
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.consume.ApplyEffectsConsumeEffect

object GoldenChiliPepper {
    val GOLDEN_CHILI_PEPPER_CONSUMABLE_COMPONENT: ConsumableComponent? = ConsumableComponents.food()
        .consumeEffect(
            ApplyEffectsConsumeEffect(
                listOf(
                    StatusEffectInstance(StatusEffects.SPEED, 15 * 20, 0),
                    StatusEffectInstance(ModEffects.CRIT_EFFECT, 15 * 20, 0)
                ),
                1.0f
            )
        )
        .consumeSeconds(1f)
        .build()

    val GOLDEN_CHILI_PEPPER_FOOD_COMPONENT: FoodComponent? = FoodComponent.Builder()
        .alwaysEdible()
        .nutrition(3)
        .saturationModifier(0.75f)
        .build()

    val GOLDEN_CHILI_PEPPER = ModItems.registerItem(
        "golden_chili_pepper",
        { settings: Item.Settings -> Item(settings) },
        Item.Settings()
            .food(GOLDEN_CHILI_PEPPER_FOOD_COMPONENT, GOLDEN_CHILI_PEPPER_CONSUMABLE_COMPONENT)
            .maxCount(69)
    )


    fun initGoldenChiliPepper() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register { it.add(GOLDEN_CHILI_PEPPER) }

        FuelRegistryEvents.BUILD.register {
            builder, context -> builder.add(GOLDEN_CHILI_PEPPER, 36000)
            // make golden chili pepper a fuel that can burn for 30 minutes or 30 * 60 * 20 tick rates
        }

        CompostingChanceRegistry.INSTANCE.add(GOLDEN_CHILI_PEPPER, 1.0f)
    }
}