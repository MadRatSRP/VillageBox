//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.renderer.villager;

import ckhbox.villagebox.client.model.villager.ModelVillager;
import ckhbox.villagebox.common.entity.villager.EntityVillager;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.lwjgl.opengl.GL11;

import javax.annotation.Nonnull;

@SideOnly(Side.CLIENT)
public class RenderVillager
        extends RenderBiped<EntityVillager> {
    private final AxisAlignedBB questMarkBoundBot = new AxisAlignedBB(
            -0.05D, 2.3D, -0.05D, 0.05D, 2.4D, 0.05D
    );

    private final AxisAlignedBB questMarkBoundTop = new AxisAlignedBB(
            -0.05D, 2.5D, -0.05D, 0.05D, 2.8D, 0.05D
    );

    private VertexBuffer vertexBuffer;

    public RenderVillager(RenderManager renderManagerIn) {
        super(renderManagerIn, new ModelVillager(), 0.5F, 1.0F);
        this.addLayer(new LayerVillagerHeldItem(this));
    }

    @Override
    protected boolean canRenderName(EntityVillager entity) {
        if (entity.previewProfession != null)
            return false;
        else
            return super.canRenderName(entity);
    }

    @Override
    public void doRender(@Nonnull EntityVillager entity, double x, double y,
                         double z, float entityYaw, float partialTicks) {
        super.doRender(entity, x, y, z, entityYaw, partialTicks);

        if (entity.previewProfession == null && entity.getCurrentQuest() != null && entity.hasHome()) {
            this.renderQuestMark(x, y, z, -entityYaw);
        }
    }

    private void renderQuestMark(double x, double y, double z, float rotY) {
        renderColorBox(this.questMarkBoundTop, x, y, z, rotY);
        renderColorBox(this.questMarkBoundBot, x, y, z, rotY);
    }

    private void renderColorBox(AxisAlignedBB boundingBox, double x, double y, double z, float rotY) {
        GlStateManager.pushMatrix();

        GlStateManager.disableTexture2D();

        Tessellator tessellator = Tessellator.getInstance();

        vertexBuffer = tessellator.getBuffer();

        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);

        GlStateManager.disableLighting();

        GlStateManager.translate(x, y, z);

        GlStateManager.rotate(rotY, 0, 1, 0);

        GlStateManager.shadeModel(GL11.GL_SMOOTH);

        vertexBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);

        // Tasks with vertex buffer
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.minY, boundingBox.minZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.minY, boundingBox.minZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.minY, boundingBox.maxZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.maxY, boundingBox.maxZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.maxY, boundingBox.minZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.minX, boundingBox.minY, boundingBox.minZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.minY, boundingBox.minZ,
                (float) 0.6);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.maxY, boundingBox.minZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.maxY, boundingBox.maxZ,
                (float) 1.0);
        doTaskWithVertexBuffer(boundingBox.maxX, boundingBox.minY, boundingBox.maxZ,
                (float) 0.6);

        tessellator.draw();

        GlStateManager.shadeModel(GL11.GL_FLAT);

        GlStateManager.enableTexture2D();

        GlStateManager.enableLighting();

        GlStateManager.popMatrix();
    }

    void doTaskWithVertexBuffer(double boundingBoxPositionFirst, double boundingBoxPositionSecond,
                                double boundingBoxPositionThird, float colorValue) {
        vertexBuffer
                .pos(boundingBoxPositionFirst, boundingBoxPositionSecond, boundingBoxPositionThird)
                .color((float) 1.0, colorValue, (float) 0.0, (float) 1.0)
                .endVertex();
    }

    @Nonnull
    @Override
    protected ResourceLocation getEntityTexture(EntityVillager entity) {
        if (entity.previewProfession != null)
            return entity.previewProfession.getTexture(entity.isMale());
        else
            return entity.getProfession().getTexture(entity.isMale());
    }

    @Override
    protected void preRenderCallback(@Nonnull EntityVillager entitylivingbaseIn, float partialTickTime) {
        float f = 0.9375F;
        GlStateManager.scale(f, f, f);
    }
}
