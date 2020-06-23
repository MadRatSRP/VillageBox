//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villagebook;

import ckhbox.villagebox.client.gui.villagebook.page.*;
import ckhbox.villagebox.client.gui.villagebook.page.data.VillageBookData;
import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.gui.common.ContainerEmpty;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.village.profession.Profession;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.util.List;
import java.util.Stack;

public class GuiVillageBook
        extends GuiContainer {
    public static final ResourceLocation guiBookGuiTextures = new ResourceLocation(
            PathHelper.full("textures/gui/villagebook/page.png")
    );
    private final EntityVillager tempVillager = new EntityVillager(
            Minecraft.getMinecraft().thePlayer.getEntityWorld()
    );
    public VillageBookData villagebookData = null;
    public Stack<String> linkStack = new Stack<>();
    protected Page currentPage;
    protected int xSize = 176;
    protected int ySize = 180;
    protected int offsetx = 18;
    protected int offsety = 18;

    public GuiVillageBook() {
        super(new ContainerEmpty());
        if (villagebookData == null) {
            villagebookData = new VillageBookData();
        }
    }

    public int getContentLeft() {
        return (this.width - this.xSize) / 2 + offsetx;
    }

    public int getContentRight() {
        return (this.width + this.xSize) / 2 - offsetx;
    }

    public int getContentTop() {
        return (this.height - this.ySize) / 2 + offsety;
    }

    public int getContentWidth() {
        return this.xSize - offsetx * 2;
    }

    public int getContentHeight() {
        return this.ySize - offsety * 2;
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiBookGuiTextures);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);

        if (this.currentPage != null)
            this.currentPage.onDrawScreen(mouseX, mouseY);
    }

    @Override
    public void initGui() {
        super.initGui();
        this.gotoLink("home=");
    }

    @Override
    protected void mouseClicked(int mouseX, int mouseY, int mouseButton) throws IOException {
        super.mouseClicked(mouseX, mouseY, mouseButton);
        if (this.currentPage != null) {
            this.currentPage.onMouseClick(mouseX, mouseY);
        }
    }

    public void drawTexture(int x, int y, int coordx, int coordy, int width, int height) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.getTextureManager().bindTexture(guiBookGuiTextures);
        this.drawTexturedModalRect(x, y, coordx, coordy, width, height);
    }

    public void drawProHead(int x, int y, Profession pro, boolean isMale) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        this.mc.getTextureManager().bindTexture(pro.getTexture(isMale));

        drawModalRectWithCustomSizedTexture(x, y, 8,
                8, 8,
                8, 64, 32);
    }

    public void drawProEntity(int x, int y, Profession pro, boolean isMale) {
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        this.tempVillager.previewProfession = pro;
        this.tempVillager.setGender(isMale);
        this.drawEntityOnScreen(x, y, this.tempVillager);
        this.tempVillager.previewProfession = null;
    }

    private void drawEntityOnScreen(int posX, int posY, EntityLivingBase ent) {
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float) posX, (float) posY, 50.0F);
        GlStateManager.scale((float) (-20), (float) 20, (float) 20);
        GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
        float f = ent.renderYawOffset;
        float f1 = ent.rotationYaw;
        float f2 = ent.rotationPitch;
        float f3 = ent.prevRotationYawHead;
        float f4 = ent.rotationYawHead;
        GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
        //ent.renderYawOffset = -30.0F * r;
        //ent.rotationYaw = -75.0F * r;
        ent.renderYawOffset = 45.0F;
        ent.rotationYaw = 45.0F;
        ent.rotationPitch = 0;
        ent.rotationYawHead = ent.rotationYaw;
        ent.prevRotationYawHead = ent.rotationYaw;
        GlStateManager.translate(0.0F, 0.0F, 0.0F);
        RenderManager rendermanager = Minecraft.getMinecraft().getRenderManager();
        rendermanager.setPlayerViewY(180.0F);
        rendermanager.setRenderShadow(false);
        rendermanager.doRenderEntity(ent, 0.0D, 0.0D, 0.0D, 0.0F, 1.0F, false);
        rendermanager.setRenderShadow(true);
        ent.renderYawOffset = f;
        ent.rotationYaw = f1;
        ent.rotationPitch = f2;
        ent.prevRotationYawHead = f3;
        ent.rotationYawHead = f4;
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
    }

    public void gotoLink(String link) {
        if (link == null)
            return;
        boolean pushPrevLink = false;

        // start with > means pushing old link
        if (link.startsWith(">")) {
            link = link.substring(1);

            pushPrevLink = true;
        }

        //reslove links
        //format: PAGETYPE=PARAMS, examples: 1. item=staff   2.  pro=cook   3. home=

        String[] array = link.split("=");

        @Nullable Page newPage = null;

        switch (array[0]) {
            case "home":
                newPage = new PageHome(this);

                break;

            case "itemlist": {
                int pageIdx = Integer.parseInt(array[1]);

                newPage = new PageItemList(this, pageIdx);

                break;
            }

            case "prolist": {
                int pageIdx = Integer.parseInt(array[1]);

                newPage = new PageProList(this, pageIdx);

                break;
            }

            case "item":
                ItemStack itemstack = this.villagebookData.mapNamesToItemStacks.get(array[1]);

                newPage = new PageItem(this, itemstack);

                break;

            case "pro": {
                int proid = Integer.parseInt(array[1]);

                Profession pro = Profession.registry.get(proid);

                newPage = new PagePro(this, pro);

                break;
            }

            case "trading": {
                String[] ps = array[1].split(",");

                int proid = Integer.parseInt(ps[0]);

                int pageIdx = Integer.parseInt(ps[1]);

                Profession pro = Profession.registry.get(proid);

                newPage = new PageTrading(this, pro, pageIdx);

                break;
            }

            case "tutorial":
                newPage = new PageTutorial(this);

                break;

            case "back":
                this.gotoLink(this.getLastLink());

                return;
        }

        if (newPage != null) {
            newPage.currentLink = link;

            this.openPage(newPage, pushPrevLink);
        }
    }

    public void openPage(Page page, boolean pushPrevLink) {
        if (pushPrevLink && this.currentPage != null) {
            this.linkStack.push(this.currentPage.currentLink);
        }
        this.currentPage = page;
        if (this.currentPage != null) {
            this.currentPage.onInit();
        }
    }

    public String getLastLink() {
        if (this.linkStack.isEmpty()) {
            return null;
        }
        return this.linkStack.pop();
    }

    public void drawHoveringText(@Nonnull List<String> textLines, int x, int y) {
        super.drawHoveringText(textLines, x, y);
    }

    public void renderToolTip(@Nonnull ItemStack stack, int x, int y) {
        super.renderToolTip(stack, x, y);
    }

    public void drawItem(ItemStack itemstack, int x, int y) {
        GlStateManager.pushMatrix();
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.enableRescaleNormal();
        GlStateManager.enableColorMaterial();
        GlStateManager.enableLighting();
        this.itemRender.zLevel = 100.0F;
        this.itemRender.renderItemAndEffectIntoGUI(itemstack, x, y);
        this.itemRender.renderItemOverlays(this.fontRendererObj, itemstack, x, y);
        GlStateManager.popMatrix();
    }

}
