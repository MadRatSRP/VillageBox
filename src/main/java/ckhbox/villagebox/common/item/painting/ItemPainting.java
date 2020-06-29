//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.painting;

import ckhbox.villagebox.common.entity.painting.EntityPainting;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class ItemPainting
    extends Item {

    public ItemPainting() {
        this.setUnlocalizedName(PathHelper.full("painting"));

        this.setCreativeTab(ModItems.tabVB);
    }

    /**
     * Called when a Block is right-clicked with this Item
     */
    @Nonnull
    public EnumActionResult onItemUse(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn,
                                      @Nonnull World worldIn, BlockPos pos, @Nonnull EnumHand hand,
                                      @Nonnull EnumFacing facing, float hitX, float hitY, float hitZ) {
        BlockPos blockpos = pos.offset(facing);

        if (facing != EnumFacing.DOWN && facing != EnumFacing.UP && playerIn.canPlayerEdit(blockpos, facing, stack)) {
            EntityHanging entityhanging = this.createEntity(worldIn, blockpos, facing);

            if (entityhanging.onValidSurface()) {
                if (!worldIn.isRemote) {
                    entityhanging.playPlaceSound();

                    worldIn.spawnEntityInWorld(entityhanging);
                }

                --stack.stackSize;
            }

            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }

    private EntityPainting createEntity(World worldIn, BlockPos pos, EnumFacing clickedSide) {
        return new EntityPainting(worldIn, pos, clickedSide);
    }
}
