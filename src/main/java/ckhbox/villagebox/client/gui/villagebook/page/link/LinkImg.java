//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page.link;

import ckhbox.villagebox.client.gui.villagebook.page.Page;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.ResourceLocation;

import java.util.List;

public class LinkImg
    extends Link {
    public int coordX;

    public int coordY;

    public int hoverOffsetX;

    public int hoverOffsetY;

    public ResourceLocation resource;

    public LinkImg(Page page, ResourceLocation resource, int coordX, int coordY, int hoverOffsetX,
                   int hoverOffsetY, int width, int height, String link, List<String> tooltips) {
        super(page, link, tooltips);

        this.coordX = coordX;

        this.coordY = coordY;

        this.hoverOffsetX = hoverOffsetX;

        this.hoverOffsetY = hoverOffsetY;

        this.width = width;

        this.height = height;

        this.resource = resource;
    }

    public void onDraw(int mouseX, int mouseY) {
        GlStateManager.disableLighting();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(this.resource);

        this.page.guiVillageBook.drawTexturedModalRect(this.left, this.top, this.coordX, this.coordY, this.width, this.height);

        GlStateManager.enableLighting();
    }

    public void onDrawHover(int mouseX, int mouseY) {
        GlStateManager.disableLighting();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(this.resource);

        this.page.guiVillageBook.drawTexturedModalRect(this.left, this.top,
            this.coordX + this.hoverOffsetX,
            this.coordY + this.hoverOffsetY, this.width, this.height);

        GlStateManager.enableLighting();
    }
}
