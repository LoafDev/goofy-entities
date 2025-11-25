package me.goofyentities.item.items

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
                    StatusEffectInstance(StatusEffects.STRENGTH, 120 * 20, 2),
                    StatusEffectInstance(StatusEffects.HASTE, 180 * 20, 3),
                    StatusEffectInstance(StatusEffects.SPEED, 120 * 20, 2)
                ),
                1.0f
            )
        )
        .consumeSeconds(0f)
        .build()

    val GOLDEN_CHILI_PEPPER_FOOD_COMPONENT: FoodComponent? = FoodComponent.Builder()
        .alwaysEdible()
        .nutrition(2)
        .saturationModifier(3f)
        .build()

    val GOLDEN_CHILI_PEPPER = ModItems.registerItem(
        "golden_chili_pepper",
        { settings: Item.Settings -> Item(settings) },
        Item.Settings().food(GOLDEN_CHILI_PEPPER_FOOD_COMPONENT, GOLDEN_CHILI_PEPPER_CONSUMABLE_COMPONENT)
    )


    fun initGoldenChiliPepper() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register { it.add(GOLDEN_CHILI_PEPPER) }

        FuelRegistryEvents.BUILD.register {
            builder, context -> builder.add(GOLDEN_CHILI_PEPPER, 72000)
            // make golden chili pepper a fuel that can burn for 60 minutes or 60 * 60 * 20 tick rates
        }

        CompostingChanceRegistry.INSTANCE.add(GOLDEN_CHILI_PEPPER, 1.0f)
    }
}