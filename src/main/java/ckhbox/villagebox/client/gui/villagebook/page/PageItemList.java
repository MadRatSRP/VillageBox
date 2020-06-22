//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page;

import java.util.List;

import ckhbox.villagebox.client.gui.villagebook.GuiVillageBook;
import ckhbox.villagebox.client.gui.villagebook.page.link.LinkImg;
import ckhbox.villagebox.client.gui.villagebook.page.link.LinkItem;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.client.resources.I18n;

public class PageItemList extends Page{
	
	private final int pageIdx;

    public PageItemList(GuiVillageBook guiVillageBook, int pageIdx) {
		super(guiVillageBook, I18n.format(PathHelper.full("villageBook.itemList")));
		this.pageIdx = pageIdx;
	}

	@Override
	public void onInit() {		
		List<ItemStack> itemstacks = this.guiVillageBook.villagebookData.listOfItemStacks;
        int itemsPerPage = 30;
        int from = pageIdx * itemsPerPage;
		int to = Math.min(itemstacks.size(), (pageIdx+1) * itemsPerPage);
		for(int i =from;i<to;i++){
			this.addLink(new LinkItem(this,itemstacks.get(i),">item=" + itemstacks.get(i).getUnlocalizedName(),null), false);
		}
		
		int top = this.guiVillageBook.getContentTop() + this.guiVillageBook.getContentHeight() - 16;
		if(this.pageIdx > 0){
			this.addLink(new LinkImg(this, GuiVillageBook.guiBookGuiTextures,208,0,0,16,16,16,"itemlist=" + (this.pageIdx - 1),null),top,0);
		}
		int pageCount = (itemstacks.size() - 1) / itemsPerPage + 1;
		if(this.pageIdx < pageCount - 1){
			this.addLink(new LinkImg(this, GuiVillageBook.guiBookGuiTextures,192,0,0,16,16,16,"itemlist=" + (this.pageIdx + 1),null),top,2);
		}
		
		this.addLink(new LinkImg(this, GuiVillageBook.guiBookGuiTextures,240,0,0,16,16,16,"back=",null),top,1);
	}

	@Override
	public void onDrawScreen(int mouseX, int mouseY) {
		super.onDrawScreen(mouseX, mouseY);
	}
	
}
