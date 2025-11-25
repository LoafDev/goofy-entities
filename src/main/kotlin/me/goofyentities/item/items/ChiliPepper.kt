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

object ChiliPepper {
    val CHILI_PEPPER_CONSUMABLE_COMPONENT: ConsumableComponent? = ConsumableComponents.food()
        .consumeEffect(ApplyEffectsConsumeEffect(
            listOf(
                StatusEffectInstance(StatusEffects.STRENGTH, 5 * 20, 1),
                StatusEffectInstance(StatusEffects.HASTE, 10 * 20, 2),
                StatusEffectInstance(StatusEffects.SPEED, 5 * 20, 1)
            ),
        1.0f))
        .consumeSeconds(0f)
        .build()

    val CHILI_PEPPER_FOOD_COMPONENT: FoodComponent? = FoodComponent.Builder()
        .alwaysEdible()
        .nutrition(1)
        .saturationModifier(0f)
        .build()

    val CHILI_PEPPER = ModItems.registerItem(
        "chili_pepper",
        { settings: Item.Settings -> Item(settings) },
        Item.Settings().food(CHILI_PEPPER_FOOD_COMPONENT, CHILI_PEPPER_CONSUMABLE_COMPONENT)
    )

    fun initChiliPepper() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register { it.add(CHILI_PEPPER) }

        FuelRegistryEvents.BUILD.register {
            builder, context ->  builder.add(CHILI_PEPPER, 12000)
            // make chili pepper a fuel that can burn for 10 minutes or 10 * 60 * 20 tick rates
        }

        CompostingChanceRegistry.INSTANCE.add(CHILI_PEPPER, 1.0f)
    }
}