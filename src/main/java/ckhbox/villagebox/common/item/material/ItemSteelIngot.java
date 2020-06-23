//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.material;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.item.Item;

public class ItemSteelIngot extends Item {

    public ItemSteelIngot() {
        this.setUnlocalizedName(PathHelper.full("steelIngot"));
        this.setCreativeTab(ModItems.tabVB);
        this.setMaxStackSize(16);
    }

}
