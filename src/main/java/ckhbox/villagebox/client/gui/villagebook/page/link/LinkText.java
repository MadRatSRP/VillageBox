//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page.link;

import ckhbox.villagebox.client.gui.villagebook.page.Page;
import net.minecraft.client.renderer.GlStateManager;

import java.util.List;

public class LinkText
    extends Link {
    public String text;

    public LinkText(Page page, String text, String link, List<String> tooltips) {
        super(page, link, tooltips);

        this.text = text;

        this.width = this.mc.fontRendererObj.getStringWidth(text);

        this.height = this.mc.fontRendererObj.FONT_HEIGHT + 2;
    }

    public void onDraw(int mouseX, int mouseY) {
        GlStateManager.disableLighting();

        mc.fontRendererObj.drawString(this.text, this.left, this.top, 0xFF000000, false);

        GlStateManager.enableLighting();
    }

    public void onDrawHover(int mouseX, int mouseY) {
        GlStateManager.disableLighting();

        mc.fontRendererObj.drawString(this.text, this.left, this.top, 0xFF555555, false);

        GlStateManager.enableLighting();
    }
}
