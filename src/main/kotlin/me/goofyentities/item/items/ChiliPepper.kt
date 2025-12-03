package me.goofyentities.item.items

import me.goofyentities.block.ModBlock
import me.goofyentities.item.ModItems
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.registry.CompostingChanceRegistry
import net.fabricmc.fabric.api.registry.FuelRegistryEvents
import net.minecraft.component.type.ConsumableComponent
import net.minecraft.component.type.ConsumableComponents
import net.minecraft.component.type.FoodComponent
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemGroups
import net.minecraft.item.consume.ApplyEffectsConsumeEffect

object ChiliPepper {
    val CHILI_PEPPER_CONSUMABLE_COMPONENT: ConsumableComponent? = ConsumableComponents.food()
        .consumeEffect(
            ApplyEffectsConsumeEffect(
                StatusEffectInstance(StatusEffects.SPEED, 5 * 20, 0),
                1.0f
            )
        )
        .consumeSeconds(0.75f)
        .build()

    val CHILI_PEPPER_FOOD_COMPONENT: FoodComponent? = FoodComponent.Builder()
        .alwaysEdible()
        .nutrition(2)
        .saturationModifier(0.5f)
        .build()

    val CHILI_PEPPER = ModItems.registerItem(
        "chili_pepper",
        { BlockItem(ModBlock.CHILI_PEPPER_CROP, it) },
        Item.Settings()
            .food(CHILI_PEPPER_FOOD_COMPONENT, CHILI_PEPPER_CONSUMABLE_COMPONENT)
            .useItemPrefixedTranslationKey()
    )

    fun initChiliPepper() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register { it.add(CHILI_PEPPER) }

        FuelRegistryEvents.BUILD.register {
            builder, context ->  builder.add(CHILI_PEPPER, 3600)
            // make chili pepper a fuel that can burn for 3 minutes or 3 * 60 * 20 tick rates
        }

        CompostingChanceRegistry.INSTANCE.add(CHILI_PEPPER, 1.0f)
    }
}