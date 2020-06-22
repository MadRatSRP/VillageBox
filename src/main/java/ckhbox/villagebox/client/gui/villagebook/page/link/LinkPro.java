//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page.link;

import ckhbox.villagebox.client.gui.villagebook.page.Page;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.village.profession.Profession;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.List;

public class LinkPro
        extends Link {
	public Profession profession;
	
	private final boolean unknownPro;

	private List<String> stringUnknownPro;
	
	public LinkPro(Page page, Profession profession, String link, List<String> tooltips) {
		super(page,link,tooltips);

		this.profession = profession;

		this.width = this.mc.fontRendererObj.getStringWidth(I18n.format(this.profession.getUnloalizedDisplayName())) + 20;

		this.height = 16;

		this.unknownPro = !ExtendedPlayerProperties
                .get(page.guiVillageBook.mc.thePlayer)
                .collections
                .hasProfession(this.profession.getRegID());

		if (this.unknownPro) {
			this.link = null;
		}
	}
	
	public void onDraw(int mouseX, int mouseY) {
		GlStateManager.disableLighting();

		if (unknownPro) {
			mc.fontRendererObj.drawString("????", this.left, this.top,
                    0xFF000000, false);
		} else{
			mc.fontRendererObj.drawString(I18n.format(this.profession.getUnloalizedDisplayName()),
                    this.left + 18, this.top, 0xFF000000, false);

			this.page.guiVillageBook.drawProHead(this.left, this.top, profession, false);

			this.page.guiVillageBook.drawProHead(this.left + 8, this.top + 2, profession, true);
		}

		GlStateManager.enableLighting();
	}
	
	public void onDrawHover(int mouseX, int mouseY) {
		GlStateManager.disableLighting();

		if (unknownPro) {
			mc.fontRendererObj.drawString("????", this.left + 2,
                    this.top, 0xFF000000, false);
		} else{
			mc.fontRendererObj.drawString(I18n.format(this.profession.getUnloalizedDisplayName()),
                    this.left + 20, this.top, 0xFF555555, false);

			this.page.guiVillageBook.drawProHead(this.left, this.top + 2, profession, false);

			this.page.guiVillageBook.drawProHead(this.left + 8, this.top, profession, true);
		}

		GlStateManager.enableLighting();
	}
	
	public void onDrawToolTip(int mouseX, int mouseY) {
		if (this.unknownPro) {
			if (this.stringUnknownPro == null) {
				this.stringUnknownPro = new ArrayList<>();

				this.stringUnknownPro.add(I18n.format(PathHelper.full("villageBook.unknownpro")));
			}

			this.page.guiVillageBook.drawHoveringText(this.stringUnknownPro, mouseX, mouseY);
		}
	}
	
	/*public boolean isUnknown(){
		return this.unknownPro;
	}*/
}
