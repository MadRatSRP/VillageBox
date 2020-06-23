//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page.link;

import ckhbox.villagebox.client.gui.villagebook.page.Page;
import net.minecraft.client.Minecraft;

import java.util.List;

public abstract class Link {
    public int left;
    public int top;
    public int width;
    public int height;
    public String link;
    public Page page;
    public Minecraft mc;
    public List<String> tooltips;

    public Link(Page page, String link, List<String> tooltips) {
        this.link = link;

        this.page = page;

        this.tooltips = tooltips;

        this.mc = this.page.guiVillageBook.mc;
    }

    public boolean isMouseHover(int mouseX, int mouseY) {
        return mouseX > this.left &&
                mouseX < this.left + this.width &&
                mouseY > this.top &&
                mouseY < this.top + this.height;
    }

    public void onDraw(int mouseX, int mouseY) {

    }

    public void onDrawHover(int mouseX, int mouseY) {

    }

    public void onDrawToolTip(int mouseX, int mouseY) {
        if (this.tooltips != null) {
            this.page.guiVillageBook.drawHoveringText(this.tooltips, mouseX, mouseY);
        }
    }
}
