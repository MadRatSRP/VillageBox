//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.weapon;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemLightStaff
    extends Item {

    public ItemLightStaff() {
        this.setUnlocalizedName(PathHelper.full("lightStaff"));

        this.maxStackSize = 1;

        this.setMaxDamage(256);

        this.setCreativeTab(ModItems.tabVB);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn,
                               @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);

        String info = I18n.format(PathHelper.full("info.item.lightStaff"));

        tooltip.add(info);
    }

    @Nonnull
    @Override
    public EnumActionResult onItemUse(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn, @Nonnull World worldIn,
                                      @Nonnull BlockPos pos, @Nonnull EnumHand hand, @Nonnull EnumFacing facing,
                                      float hitX, float hitY, float hitZ) {
        Item itemTorch = Item.getItemFromBlock(Blocks.TORCH);

        if (!(itemTorch instanceof ItemBlock)) {
            return EnumActionResult.FAIL;
        }

        ItemBlock itemBlockTorch = (ItemBlock) itemTorch;

        IBlockState iblockstate = worldIn.getBlockState(pos);

        Block block = iblockstate.getBlock();

        if (!block.isReplaceable(worldIn, pos)) {
            pos = pos.offset(facing);
        }

        if (stack.stackSize != 0 && playerIn.canPlayerEdit(pos, facing, stack)
            && worldIn.canBlockBePlaced(Blocks.TORCH, pos, false, facing, null, stack)) {
            int i = this.getMetadata(stack.getMetadata());

            IBlockState iblockstate1 = Blocks.TORCH.onBlockPlaced(worldIn, pos, facing, hitX, hitY, hitZ, i, playerIn);

            if (itemBlockTorch.placeBlockAt(stack, playerIn, worldIn, pos, facing, hitX, hitY, hitZ, iblockstate1)) {
                SoundType soundtype = Blocks.TORCH.getSoundType();

                worldIn.playSound(playerIn, pos, soundtype.getPlaceSound(), SoundCategory.BLOCKS,
                    (soundtype.getVolume() + 1.0F) / 2.0F, soundtype.getPitch() * 0.8F);

                this.damageStaff(playerIn, stack, hand);
            }

            return EnumActionResult.SUCCESS;
        } else {
            return EnumActionResult.FAIL;
        }
    }

    public void damageStaff(EntityPlayer player, ItemStack stack, EnumHand hand) {
        if (!player.capabilities.isCreativeMode) {
            stack.damageItem(1, player);
            if (stack.getItemDamage() == 0) {
                player.setItemStackToSlot(hand == EnumHand.MAIN_HAND ? EntityEquipmentSlot.MAINHAND
                    : EntityEquipmentSlot.OFFHAND, new ItemStack(ModItems.staff));
            }
        }
    }
}