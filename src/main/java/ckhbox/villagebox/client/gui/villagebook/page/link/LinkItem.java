//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page.link;

import ckhbox.villagebox.client.gui.villagebook.page.Page;
import net.minecraft.item.ItemStack;

import java.util.List;

public class LinkItem extends Link {

    public ItemStack itemStack;

    public LinkItem(Page page, ItemStack itemStack, String link, List<String> tooltips) {
        super(page, link, tooltips);

        this.itemStack = itemStack;

        this.width = 20;

        this.height = 20;
    }

    public void onDraw(int mouseX, int mouseY) {
        this.page.guiVillageBook.drawItem(this.itemStack, this.left, this.top);
    }

    public void onDrawHover(int mouseX, int mouseY) {
        this.page.guiVillageBook.drawItem(this.itemStack, this.left, this.top - 2);
    }

    public void onDrawToolTip(int mouseX, int mouseY) {
        if (this.itemStack != null) {
            this.page.guiVillageBook.renderToolTip(this.itemStack, mouseX, mouseY);
        }
    }
}
