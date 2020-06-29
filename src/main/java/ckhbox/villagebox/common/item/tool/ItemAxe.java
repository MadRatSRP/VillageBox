//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.tool;

import ckhbox.villagebox.common.item.ModItems;
import com.google.common.collect.Sets;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.Set;

public class ItemAxe
    extends net.minecraft.item.ItemTool {

    private static final Set<Block> EFFECTIVE_ON = Sets.newHashSet(
        Blocks.PLANKS, Blocks.BOOKSHELF, Blocks.LOG, Blocks.LOG2, Blocks.CHEST, Blocks.PUMPKIN, Blocks.LIT_PUMPKIN,
        Blocks.MELON_BLOCK, Blocks.LADDER, Blocks.WOODEN_BUTTON, Blocks.WOODEN_PRESSURE_PLATE
    );

    public ItemAxe(ToolMaterial material) {
        super(material, EFFECTIVE_ON);

        this.damageVsEntity = 8.0F;

        this.attackSpeed = -3.0F;

        this.setCreativeTab(ModItems.tabVB);
    }

    public float getStrVsBlock(@Nonnull ItemStack stack, IBlockState state) {
        Material material = state.getMaterial();

        return material != Material.WOOD && material != Material.PLANTS
            && material != Material.VINE ? super.getStrVsBlock(stack, state) : this.efficiencyOnProperMaterial;
    }

}
