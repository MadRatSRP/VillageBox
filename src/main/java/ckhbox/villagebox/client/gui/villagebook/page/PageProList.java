//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook.page;

import ckhbox.villagebox.client.gui.villagebook.GuiVillageBook;
import ckhbox.villagebox.client.gui.villagebook.page.link.LinkImg;
import ckhbox.villagebox.client.gui.villagebook.page.link.LinkPro;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.village.profession.Profession;
import net.minecraft.client.resources.I18n;

import java.util.ArrayList;
import java.util.List;

public class PageProList extends Page {

    private final int pageIdx;

    public PageProList(GuiVillageBook guiVillageBook, int pageIdx) {
        super(guiVillageBook, I18n.format(PathHelper.full("villageBook.professions")));
        this.pageIdx = pageIdx;
    }

    @Override
    public void onInit() {
        List<Profession> pros = this.guiVillageBook.villagebookData.listOfProfessions;
        int prosPerPage = 7;
        int from = pageIdx * prosPerPage;
        int to = Math.min(pros.size(), (pageIdx + 1) * prosPerPage);
        for (int i = from; i < to; i++) {
            List<String> hovertext = new ArrayList<>();
            hovertext.add(I18n.format(pros.get(i).getUnlocalizedDescription()));
            this.addLink(new LinkPro(this, pros.get(i), ">pro=" + pros.get(i).getRegID(), hovertext), true);
        }

        int top = this.guiVillageBook.getContentTop() + this.guiVillageBook.getContentHeight() - 16;
        if (this.pageIdx > 0) {
            this.addLink(new LinkImg(this, GuiVillageBook.guiBookGuiTextures, 208, 0, 0, 16, 16, 16, "prolist=" + (this.pageIdx - 1), null), top, 0);
        }
        int pageCount = (pros.size() - 1) / prosPerPage + 1;
        if (this.pageIdx < pageCount - 1) {
            this.addLink(new LinkImg(this, GuiVillageBook.guiBookGuiTextures, 192, 0, 0, 16, 16, 16, "prolist=" + (this.pageIdx + 1), null), top, 2);
        }

        this.addLink(new LinkImg(this, GuiVillageBook.guiBookGuiTextures, 240, 0, 0, 16, 16, 16, "back=", null), top, 1);
    }

    @Override
    public void onDrawScreen(int mouseX, int mouseY) {
        super.onDrawScreen(mouseX, mouseY);
    }

}
