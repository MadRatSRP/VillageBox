//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.tool;

import ckhbox.villagebox.common.item.ModItems;

public class ItemShovel extends net.minecraft.item.ItemSpade{

	public ItemShovel(ToolMaterial material) {
		super(material);
		this.setCreativeTab(ModItems.tabVB);
	}

}
