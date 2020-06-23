//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page;

import ckhbox.villagebox.client.gui.villagebook.GuiVillageBook;
import ckhbox.villagebox.client.gui.villagebook.page.link.LinkText;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

public class PageHome
        extends Page {
    public PageHome(GuiVillageBook guiVillageBook) {
        super(guiVillageBook, I18n.format(PathHelper.full("villageBook.title")));
    }

    @Override
    public void onInit() {
        int top = this.guiVillageBook.getContentTop();
        //this.addLink(new LinkText(this,"> " + I18n.translateToLocal(PathHelper.full("villageBook.itemList")),">itemlist=0",null),top + 80,1);
        this.addLink(new LinkText(this, "> " + I18n.format(PathHelper.full("villageBook.professions")), ">prolist=0", null), top + 90, 1);
        this.addLink(new LinkText(this, "> " + I18n.format(PathHelper.full("villageBook.tutorial")), ">tutorial=", null), top + 110, 1);
    }

    @Override
    public void onDrawScreen(int mouseX, int mouseY) {
        super.onDrawScreen(mouseX, mouseY);
        int x = this.guiVillageBook.getContentLeft() + this.guiVillageBook.getContentWidth() / 2 - 74;
        int y = this.guiVillageBook.getContentTop() + 20;
        GlStateManager.disableLighting();
        this.guiVillageBook.drawTexture(x, y, 0, 180, 148, 51);
    }

}
