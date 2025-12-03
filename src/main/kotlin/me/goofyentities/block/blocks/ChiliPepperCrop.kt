package me.goofyentities.block.blocks

import me.goofyentities.item.items.ChiliPepper
import net.minecraft.block.BlockState
import net.minecraft.block.CropBlock
import net.minecraft.block.ShapeContext
import net.minecraft.util.math.BlockPos
import net.minecraft.util.shape.VoxelShape
import net.minecraft.world.BlockView

class ChiliPepperCrop(
    settings: Settings
) : CropBlock(
    settings
) {
    val ageToShape = arrayOf(
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 2.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 3.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 4.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 5.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 6.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 7.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 8.0, 16.0),
        createCuboidShape(0.0, 0.0, 0.0, 16.0, 9.0, 16.0)
    )

    override fun getSeedsItem() = ChiliPepper.CHILI_PEPPER

    override fun getOutlineShape(
        state: BlockState?,
        world: BlockView?,
        pos: BlockPos?,
        context: ShapeContext?
    ): VoxelShape = ageToShape[getAge(state)]
}