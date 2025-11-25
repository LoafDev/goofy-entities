package me.goofyentities.item

import me.goofyentities.Goofyentities
import me.goofyentities.item.items.ChiliPepper
import me.goofyentities.item.items.GoldenChiliPepper
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.util.Identifier

object ModItems {
    fun registerItem(
        name: String,
        itemFactory: (Item.Settings) -> Item,
        settings: Item.Settings
    ): Item {
        val itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Goofyentities.MOD_ID, name))
        val item = itemFactory(settings.registryKey(itemKey))
        Registry.register(Registries.ITEM, itemKey, item)

        return item
    }

    fun registerModItems() {
        Goofyentities.logger?.info("Registering ModItems for ${Goofyentities.MOD_ID}")
        ChiliPepper.initChiliPepper()
        GoldenChiliPepper.initGoldenChiliPepper()
    }
}