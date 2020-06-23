//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.block.totem;

import ckhbox.villagebox.common.block.common.BlockFacing;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockTotemPole extends BlockFacing {

    private static final AxisAlignedBB TOTEMPOLE_AABB = new AxisAlignedBB(0.25F, 0.0F, 0.25F, 0.75F, 1.0F, 0.75F);

    public BlockTotemPole(String name) {
        super(Material.WOOD);
        this.setUnlocalizedName(PathHelper.full(name));
        this.setCreativeTab(ModItems.tabVB);
        this.setHardness(2.5F);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        return TOTEMPOLE_AABB;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    public boolean isFullCube(IBlockState state) {
        return false;
    }

}
