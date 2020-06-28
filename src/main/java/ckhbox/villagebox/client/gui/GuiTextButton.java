//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiButton;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class GuiTextButton extends GuiButton {

    private final Minecraft minecraft;

    private int colorNormal = 0xFFFFFF;
    private int colorHover = 0xFFFF55;
    private boolean shadow = true;

    public GuiTextButton(Minecraft minecraft, int id, int x, int y, String text) {
        super(id, x, y, minecraft.fontRendererObj.getStringWidth(text), minecraft.fontRendererObj.FONT_HEIGHT, text);
        this.minecraft = minecraft;
    }

    public void setText(String text) {
        this.displayString = text;
        this.width = this.minecraft.fontRendererObj.getStringWidth(text);
    }

    public void setShadow(boolean shadow) {
        this.shadow = shadow;
    }

    public void setColors(int normal, int hover) {
        this.colorHover = hover;
        this.colorNormal = normal;
    }

    public boolean isMouseOn() {
        return this.hovered;
    }

    @Override
    public void drawButton(@Nonnull Minecraft minecraft, int p_146112_2_, int p_146112_3_) {
        if (this.visible) {
            FontRenderer fontrenderer = minecraft.fontRendererObj;

            this.hovered = p_146112_2_ >= this.xPosition && p_146112_3_ >= this.yPosition
                && p_146112_2_ < this.xPosition + this.width && p_146112_3_ < this.yPosition + this.height;

            int k = this.getHoverState(this.hovered);

            this.mouseDragged(minecraft, p_146112_2_, p_146112_3_);

            int l = this.colorNormal;

            if (packedFGColour != 0) {
                l = packedFGColour;
            } else if (!this.enabled) {
                l = 0xAAAAAA;
            } else if (this.hovered) {
                l = this.colorHover;
            }

            fontrenderer.drawString(this.displayString, this.xPosition, this.yPosition, l, this.shadow);
        }
    }

}
