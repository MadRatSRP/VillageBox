//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page;

import ckhbox.villagebox.client.gui.villagebook.GuiVillageBook;
import ckhbox.villagebox.client.gui.villagebook.page.link.Link;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.init.SoundEvents;

import java.util.ArrayList;

public abstract class Page {
	public GuiVillageBook guiVillageBook;

	private final ArrayList<Link> listOfLinks = new ArrayList<>();

	public String currentLink;
	
	// flow layout (not a real flow layout =P)
	private int flowLeft;
	private int flowTop;
	private int flowBot;
	private String title;
	
	public Page(GuiVillageBook guiVillageBook,String title){
		this.guiVillageBook = guiVillageBook;

		this.flowLeft = this.guiVillageBook.getContentLeft();

		this.flowTop = this.guiVillageBook.getContentTop();

		this.flowBot = this.flowTop;

		if (title != null) {
			this.title = title;

			this.setFlowTop(this.guiVillageBook.getContentTop() + 16);
		}
	}
	
	public void addLink(Link link, boolean newLine){
		
		if (this.flowLeft > 0 && (newLine || this.flowLeft + link.width > this.guiVillageBook.getContentRight())) {
			this.flowLeft = this.guiVillageBook.getContentLeft();

			this.flowTop = this.flowBot;

			this.flowBot = this.flowTop + link.height;
		}
		
		link.left = this.flowLeft;

		link.top = this.flowTop;

		this.flowLeft += link.width + 4;

		this.flowBot = this.flowTop + link.height;
		
		this.listOfLinks.add(link);
	}
	
	/**
	 * @param link link
	 * @param align 0=force left, 1= force middle, 2=force right
	 */
	public void addLink(Link link, int top, int align){
	    switch (align) {
            case 2:
                link.left = this.guiVillageBook.getContentRight() - link.width;
            case 1:
                link.left = this.guiVillageBook.getContentLeft() + (this.guiVillageBook.getContentWidth() - link.width)/2;
            default:
                link.left = this.guiVillageBook.getContentLeft();
        }
	
		link.top = top;
		
		this.listOfLinks.add(link);
	}
	
	public void addLinkAt(Link link, int top, int left){	
		link.left = left;

		link.top = top;
		
		this.listOfLinks.add(link);
	}
	
	public abstract void onInit();
	
	public void setFlowTop(int top){
		this.flowTop = top;

		this.flowBot = this.flowTop;

		this.flowLeft = this.guiVillageBook.getContentLeft();
	}
	
	public void onDrawScreen(int mouseX, int mouseY){		
		// title
		if (this.title != null) {
			GlStateManager.disableLighting();

			int top = this.guiVillageBook.getContentTop();

			int left = this.guiVillageBook.getContentLeft()
                    + (this.guiVillageBook.getContentWidth() - this.guiVillageBook.mc.fontRendererObj.getStringWidth(this.title))/2;

			this.guiVillageBook.mc.fontRendererObj.drawString(this.title, left, top, 0xFF555500, false);

			GlStateManager.enableLighting();
		}

		// links
		for (Link link : this.listOfLinks) {
			if (link.isMouseHover(mouseX, mouseY)) {
				link.onDrawHover(mouseX, mouseY);
			} else{
				link.onDraw(mouseX, mouseY);
			}
		}

		// tooltips
		for (Link link : this.listOfLinks) {
			if (link.isMouseHover(mouseX, mouseY)) {
				link.onDrawToolTip(mouseX, mouseY);
			}
		}		
	}
	
	public void onMouseClick(int mouseX, int mouseY){
		for (Link link : this.listOfLinks) {
			if (link.isMouseHover(mouseX, mouseY)) {
				if (link.link != null) {
					this.guiVillageBook.mc.getSoundHandler().playSound(PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0F));

					this.guiVillageBook.gotoLink(link.link);

					break;
				}
			}
		}
	}
}
