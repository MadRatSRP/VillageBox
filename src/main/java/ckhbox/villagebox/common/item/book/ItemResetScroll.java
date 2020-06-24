//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.book;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemResetScroll
        extends Item {
    public ItemResetScroll() {
        this.setUnlocalizedName(PathHelper.full("resetScroll"));
        this.setCreativeTab(ModItems.tabVB);
        this.setMaxStackSize(16);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn,
                               @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);
        String info = I18n.format(PathHelper.full("item.resetScroll.item.info"));
        tooltip.add(info);
    }
}
