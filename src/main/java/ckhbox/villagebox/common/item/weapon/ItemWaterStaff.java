package ckhbox.villagebox.common.item.weapon;

import java.util.List;

import ckhbox.villagebox.common.entity.throwable.EntityFlameBall;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class ItemWaterStaff extends Item
{	
	public ItemWaterStaff()
    {
		this.setUnlocalizedName(PathHelper.full("waterStaff"));
        this.maxStackSize = 1;
        this.setMaxDamage(20);
        this.setCreativeTab(ModItems.tabVB);
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        
    	MovingObjectPosition movingobjectposition = this.getMovingObjectPositionFromPlayer(worldIn, playerIn, false);
    	if (movingobjectposition == null)
        {
            return itemStackIn;
        }
    	else
        {
            if (movingobjectposition.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK)
            {
                BlockPos blockpos = movingobjectposition.getBlockPos();

                if (!worldIn.isBlockModifiable(playerIn, blockpos))
                {
                    return itemStackIn;
                }

                BlockPos blockpos1 = blockpos.offset(movingobjectposition.sideHit);

                if (!playerIn.canPlayerEdit(blockpos1, movingobjectposition.sideHit, itemStackIn))
                {
                    return itemStackIn;
                }

                if (this.tryPlaceContainedLiquid(worldIn, blockpos1))
                {
                    
                    if (!playerIn.capabilities.isCreativeMode)
                    {
                    	itemStackIn.damageItem(1, playerIn);
                    	if(itemStackIn.getItemDamage() == 0){
                    		itemStackIn = new ItemStack(ModItems.staff);
                    	}
                    }
                    
                    playerIn.swingItem();

                    playerIn.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
                	
                }
            }

            return itemStackIn;
        }
    }
    
    public boolean tryPlaceContainedLiquid(World worldIn, BlockPos pos)
    {
        Material material = worldIn.getBlockState(pos).getBlock().getMaterial();
        boolean flag = !material.isSolid();

        if (!worldIn.isAirBlock(pos) && !flag)
        {
            return false;
        }
        else
        {
        	if (worldIn.provider.doesWaterVaporize())
            {
                int i = pos.getX();
                int j = pos.getY();
                int k = pos.getZ();
                worldIn.playSoundEffect((double)((float)i + 0.5F), (double)((float)j + 0.5F), (double)((float)k + 0.5F), "random.fizz", 0.5F, 2.6F + (worldIn.rand.nextFloat() - worldIn.rand.nextFloat()) * 0.8F);

                for (int l = 0; l < 8; ++l)
                {
                    worldIn.spawnParticle(EnumParticleTypes.SMOKE_LARGE, (double)i + Math.random(), (double)j + Math.random(), (double)k + Math.random(), 0.0D, 0.0D, 0.0D, new int[0]);
                }
            }
            else
            {
                if (!worldIn.isRemote && flag && !material.isLiquid())
                {
                    worldIn.destroyBlock(pos, true);
                }

                worldIn.setBlockState(pos, Blocks.flowing_water.getDefaultState(), 3);
            }

            return true;
        }
    }
    
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		super.addInformation(stack, playerIn, tooltip, advanced);
		String info = StatCollector.translateToLocal(PathHelper.full("info.item.waterStaff"));
		tooltip.add(info);
	}
    
    
}