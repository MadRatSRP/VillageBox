//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page;

import ckhbox.villagebox.client.gui.villagebook.GuiVillageBook;
import ckhbox.villagebox.client.gui.villagebook.page.link.LinkImg;
import ckhbox.villagebox.client.gui.villagebook.page.link.LinkItem;
import ckhbox.villagebox.common.block.ModBlocks;
import ckhbox.villagebox.common.config.jsonData.JsonProfession;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.village.profession.Profession;
import net.minecraft.client.resources.I18n;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class PageTutorial extends Page {

    private Profession example = null;

    public PageTutorial(GuiVillageBook guiVillageBook) {
        super(guiVillageBook, I18n.format(PathHelper.full("villageBook.tutorial")));
        if (example == null) {
            // fake a profession data
            JsonProfession proData = new JsonProfession();
            proData.texture = "villager0";
            example = new Profession(proData);
        }
    }



    @Override
    public void onInit() {

        int top = this.guiVillageBook.getContentTop() + 16;
        int left = this.guiVillageBook.getContentLeft() + 20;
        this.addLinkAt(new LinkItem(this, new ItemStack(Items.PAPER), null, null), top, left);
        this.addLinkAt(new LinkItem(this, new ItemStack(Items.FEATHER), null, null), top, left + 20);
        this.addLinkAt(new LinkItem(this, new ItemStack(Items.DYE), null, null), top, left + 40);
        this.addLinkAt(new LinkItem(this, new ItemStack(ModItems.invitation), null, null), top, left + 80);
        this.addLinkAt(new LinkImg(this, this.guiVillageBook.guiBookGuiTextures, 192, 54, 0, 0, 16, 16, null, null), top, left + 60);
        this.addLinkAt(new LinkImg(this, this.guiVillageBook.guiBookGuiTextures, 176, 54, 0, 0, 16, 16, null, null), top + 20, left + 80);

        top = this.guiVillageBook.getContentTop() + 56;
        left = this.guiVillageBook.getContentLeft() + 20;
        this.addLinkAt(new LinkItem(this, new ItemStack(Items.PAPER), null, null), top, left + 20);
        this.addLinkAt(new LinkItem(this, new ItemStack(Blocks.PLANKS), null, null), top, left + 40);
        this.addLinkAt(new LinkItem(this, new ItemStack(ModBlocks.mailbox), null, null), top, left + 80);
        this.addLinkAt(new LinkImg(this, this.guiVillageBook.guiBookGuiTextures, 192, 54, 0, 0, 16, 16, null, null), top, left + 60);
        this.addLinkAt(new LinkImg(this, this.guiVillageBook.guiBookGuiTextures, 176, 54, 0, 0, 16, 16, null, null), top + 20, left + 80);

        top = this.guiVillageBook.getContentTop() + 96;
        left = this.guiVillageBook.getContentLeft() + 20;
        this.addLinkAt(new LinkItem(this, new ItemStack(ModItems.mail), null, null), top, left + 80);
        this.addLinkAt(new LinkImg(this, this.guiVillageBook.guiBookGuiTextures, 208, 54, 0, 0, 16, 16, null, null), top, left + 50);

        top = this.guiVillageBook.getContentTop() + this.guiVillageBook.getContentHeight() - 16;
        this.addLink(new LinkImg(this, this.guiVillageBook.guiBookGuiTextures, 240, 0, 0, 16, 16, 16, "back=", null), top, 1);
    }

    @Override
    public void onDrawScreen(int mouseX, int mouseY) {
        super.onDrawScreen(mouseX, mouseY);

        int top = this.guiVillageBook.getContentTop() + 120;
        //int left = this.guiVillageBook.getContentLeft() + 20;
        this.guiVillageBook.drawProEntity(this.guiVillageBook.getContentLeft() + 50, top, this.example, true);
    }

}
