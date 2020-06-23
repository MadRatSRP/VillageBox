//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.block.tool;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class BlockBuildBox
        extends Block {

    private final BuildSize size;

    public BlockBuildBox(BuildSize size) {
        super(Material.WOOD);
        this.size = size;
        this.setUnlocalizedName(PathHelper.full(size.name));
        this.setHardness(1.0F);
        this.setCreativeTab(ModItems.tabVB);
        this.setSoundType(SoundType.WOOD);
    }

    @Override
    public boolean onBlockActivated(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state,
                                    @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand,
                                    ItemStack heldItem, @Nonnull EnumFacing side,
                                    float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {
            //remove buildbox
            worldIn.setBlockToAir(pos);

            build(worldIn, pos, false);
        }

        return true;
    }

    private void build(World world, BlockPos pos, boolean removeOld) {

        int xmin = pos.getX() - size.radius - 1;
        int xmax = pos.getX() + size.radius + 1;
        int zmin = pos.getZ() - size.radius - 1;
        int zmax = pos.getZ() + size.radius + 1;
        int ymin = pos.getY() - 1;
        int ymax = pos.getY() - 1 + size.height;

        IBlockState bsp = Blocks.PLANKS.getDefaultState();
        IBlockState bss = Blocks.STONEBRICK.getDefaultState();
        IBlockState bsw = Blocks.GLASS_PANE.getDefaultState();

        //	BlockPlanks.EnumType ptRoof = BlockPlanks.EnumType.DARK_OAK;
        //	BlockPlanks.EnumType ptWall = BlockPlanks.EnumType.BIRCH;
        //	BlockPlanks.EnumType ptFloor = BlockPlanks.EnumType.OAK;

        //int flags = 1 | 2;

        for (int x = xmin; x <= xmax; x++) {
            for (int z = zmin; z <= zmax; z++) {
                for (int y = ymin; y <= ymax; y++) {
                    if (y == ymin) {
                        //floor
                        if (world.isAirBlock(new BlockPos(x, y, z)) || removeOld)
                            world.setBlockState(new BlockPos(x, y, z), bsp);
                    } else if (y == ymax) {
                        // roof
                        if (world.isAirBlock(new BlockPos(x, y, z)) || removeOld)
                            world.setBlockState(new BlockPos(x, y, z), bsp);
                    } else if (x != xmin && z != zmin && x != xmax && z != zmax) {
                        //empty space
                        if (world.isAirBlock(new BlockPos(x, y, z)) || removeOld)
                            world.setBlockToAir(new BlockPos(x, y, z));
                    } else {
                        //wall
                        if ((y == ymin + 2 || y == ymin + 3) && (x == pos.getX() || z == pos.getZ())) {//window
                            if (world.isAirBlock(new BlockPos(x, y, z)) || removeOld)
                                world.setBlockState(new BlockPos(x, y, z), bsw);
                        } else if (y == ymin + 1) {
                            //walls (first layer)
                            if (world.isAirBlock(new BlockPos(x, y, z)) || removeOld)
                                world.setBlockState(new BlockPos(x, y, z), bss);
                        } else {
                            //walls (the rest)
                            if (world.isAirBlock(new BlockPos(x, y, z)) || removeOld)
                                world.setBlockState(new BlockPos(x, y, z), bsp);
                        }
                    }

                }
            }
        }

    }

    public enum BuildSize {

        ExLarge("buildboxExLarge", 5, 5),
        Large("buildboxLarge", 4, 5),
        Medium("buildboxMedium", 3, 4),
        Small("buildboxSmall", 2, 4);

        public final String name;
        public final int radius;
        public final int height;

        BuildSize(String name, int radius, int height) {
            this.name = name;
            this.radius = radius;
            this.height = height;
        }
    }
}
