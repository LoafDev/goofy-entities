package me.goofyentities.datagen

import me.goofyentities.block.ModBlock
import me.goofyentities.item.items.ChiliPepper
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ChiliPepperCropLootTableProvider : FabricBlockLootTableProvider {
    constructor(
        dataOutput: FabricDataOutput,
        registryLookup: CompletableFuture<RegistryWrapper.WrapperLookup>
    ) : super(dataOutput, registryLookup)

    override fun generate() {
        /*addDrop(
            ModBlock.CHILI_PEPPER_CROP,
            LootTable.builder().pool(
            addSurvivesExplosionCondition(ChiliPepper.CHILI_PEPPER, LootPool.builder()
                .rolls(UniformLootNumberProvider(ConstantLootNumberProvider(1f), ConstantLootNumberProvider(1f)))
                .with(ItemEntry.builder(ChiliPepper.CHILI_PEPPER))
                )
            )
        )*/
    }
}