package me.goofyentities.block

import me.goofyentities.Goofyentities
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap

import net.minecraft.block.AbstractBlock
import net.minecraft.block.Block
import net.minecraft.block.CropBlock
import net.minecraft.client.render.BlockRenderLayer
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.RegistryKey
import net.minecraft.registry.RegistryKeys
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.util.Identifier

object ModBlock {
    val CHILI_PEPPER_CROP = register(
        "chili_pepper_crop",
        { CropBlock(it) },
        AbstractBlock.Settings.create()
            .sounds(BlockSoundGroup.ANVIL)
            .nonOpaque()
            .noCollision()
            .ticksRandomly()
            .breakInstantly(),
        false
    )

    private fun register(
        name: String,
        blockFactory: (AbstractBlock.Settings) -> Block,
        settings: AbstractBlock.Settings,
        shouldRegisterItem: Boolean
    ): Block {
        val blockKey = keyOfBlock(name)
        val block = blockFactory(settings.registryKey(blockKey))

        if (shouldRegisterItem) {
            val itemKey = keyOfItem(name)
            val blockItem = BlockItem(block, Item.Settings().registryKey(itemKey).useBlockPrefixedTranslationKey())
            Registry.register(Registries.ITEM, itemKey, blockItem)
        }

        return Registry.register(Registries.BLOCK, blockKey, block)
    }

    private fun keyOfBlock(name: String): RegistryKey<Block> = RegistryKey.of(
        RegistryKeys.BLOCK,
        Identifier.of(Goofyentities.MOD_ID, name)
    )

    private fun keyOfItem(name: String): RegistryKey<Item> = RegistryKey.of(
        RegistryKeys.ITEM,
        Identifier.of(Goofyentities.MOD_ID, name)
    )

    fun registerBlockClient() {
        BlockRenderLayerMap.putBlocks(BlockRenderLayer.CUTOUT, CHILI_PEPPER_CROP)
    }
}