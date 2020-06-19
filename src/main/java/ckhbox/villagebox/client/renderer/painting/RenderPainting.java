//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.renderer.painting;

import ckhbox.villagebox.common.entity.painting.EntityPainting;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@SideOnly(Side.CLIENT)
public class RenderPainting
        extends Render<EntityPainting> {
    private static final ResourceLocation KRISTOFFER_PAINTING_TEXTURE = new ResourceLocation(
            PathHelper.full("textures/painting/paintings.png")
    );

    private VertexBuffer vertexBuffer;

    public RenderPainting(RenderManager renderManagerIn)
    {
        super(renderManagerIn);
    }

    /**
     * Renders the desired {@code T} type Entity.
     */
    public void doRender(@Nonnull EntityPainting entity, double x, double y, double z,
                         float entityYaw, float partialTicks)
    {
        GlStateManager.pushMatrix();
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(180.0F - entityYaw, 0.0F, 1.0F, 0.0F);
        GlStateManager.enableRescaleNormal();
        this.bindEntityTexture(entity);
        EntityPainting.EnumArt entityPaintingEnumArt = entity.art;
        float f = 0.0625F;
        GlStateManager.scale(f, f, f);

        if (this.renderOutlines)
        {
            GlStateManager.enableColorMaterial();

            GlStateManager.enableOutlineMode(this.getTeamColor(entity));
        }

        this.renderPainting(entity, entityPaintingEnumArt.sizeX, entityPaintingEnumArt.sizeY,
                entityPaintingEnumArt.offsetX, entityPaintingEnumArt.offsetY);

        if (this.renderOutlines)
        {
            GlStateManager.disableOutlineMode();
            GlStateManager.disableColorMaterial();
        }

        GlStateManager.disableRescaleNormal();
        GlStateManager.popMatrix();
        super.doRender(entity, x, y, z, entityYaw, partialTicks);
    }

    /**
     * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
     */
    @Nonnull
    protected ResourceLocation getEntityTexture(@Nonnull EntityPainting entity)
    {
        return KRISTOFFER_PAINTING_TEXTURE;
    }

    private void renderPainting(EntityPainting painting, int width, int height, int textureU, int textureV)
    {
        float f = (float)(-width) / 2.0F;
        float f1 = (float)(-height) / 2.0F;
        float f2 = 0.5F;
        float f3 = 0.75F;
        float f4 = 0.8125F;
        float f5 = 0.0F;
        float f6 = 0.0625F;
        float f7 = 0.75F;
        float f8 = 0.8125F;
        float f9 = 0.001953125F;
        float f10 = 0.001953125F;
        float f11 = 0.7519531F;
        float f12 = 0.7519531F;
        float f13 = 0.0F;
        float f14 = 0.0625F;

        for (int i = 0; i < width / 16; ++i)
        {
            for (int j = 0; j < height / 16; ++j)
            {
                float f15 = f + (float)((i + 1) * 16);
                float f16 = f + (float)(i * 16);
                float f17 = f1 + (float)((j + 1) * 16);
                float f18 = f1 + (float)(j * 16);
                this.setLightmap(painting, (f15 + f16) / 2.0F, (f17 + f18) / 2.0F);
                float f19 = (float)(textureU + width - i * 16) / 256.0F;
                float f20 = (float)(textureU + width - (i + 1) * 16) / 256.0F;
                float f21 = (float)(textureV + height - j * 16) / 256.0F;
                float f22 = (float)(textureV + height - (j + 1) * 16) / 256.0F;

                Tessellator tessellator = Tessellator.getInstance();

                vertexBuffer = tessellator.getBuffer();

                vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX_NORMAL);

                // Tasks with VertexBuffer
                doTaskWithVertexBuffer(f15, f18, -f2, f20, f21,
                        0.0F, 0.0F, -1.0F);
                doTaskWithVertexBuffer(f16, f18, -f2, f19, f21,
                        0.0F, 0.0F, -1.0F);
                doTaskWithVertexBuffer(f16, f17, -f2, f19, f22,
                        0.0F, 0.0F, -1.0F);
                doTaskWithVertexBuffer(f15, f17, -f2, f20, f22,
                        0.0F, 0.0F, -1.0F);
                doTaskWithVertexBuffer(f15, f17, f2, f3, f5,
                        0.0F, 0.0F, 1.0F);
                doTaskWithVertexBuffer(f16, f17, f2, f4, f5,
                        0.0F, 0.0F, 1.0F);
                doTaskWithVertexBuffer(f16, f18, f2, f4, f6,
                        0.0F, 0.0F, 1.0F);
                doTaskWithVertexBuffer(f15, f18, f2, f3, f6,
                        0.0F, 0.0F, 1.0F);
                doTaskWithVertexBuffer(f15, f17, -f2, f7, f9,
                        0.0F, 1.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f17, -f2, f8, f9,
                        0.0F, 1.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f17, f2, f8, f10,
                        0.0F, 1.0F, 0.0F);
                doTaskWithVertexBuffer(f15, f17, f2, f7, f10,
                        0.0F, 1.0F, 0.0F);
                doTaskWithVertexBuffer(f15, f18, f2, f7, f9,
                        0.0F, -1.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f18, f2, f8, f9,
                        0.0F, -1.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f18, -f2, f8, f10,
                        0.0F, -1.0F, 0.0F);
                doTaskWithVertexBuffer(f15, f18, -f2, f7, f10,
                        0.0F, -1.0F, 0.0F);
                doTaskWithVertexBuffer(f15, f17, f2, f12, f13,
                        -1.0F, 0.0F, 0.0F);
                doTaskWithVertexBuffer(f15, f18, f2, f12, f14,
                        -1.0F, 0.0F, 0.0F);
                doTaskWithVertexBuffer(f15, f18, -f2, f11, f14,
                        -1.0F, 0.0F, 0.0F);
                doTaskWithVertexBuffer(f15, f17, -f2, f11, f13,
                        -1.0F, 0.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f17, -f2, f12, f13,
                        1.0F, 0.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f18, -f2, f12, f14,
                        1.0F, 0.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f18, f2, f11, f14,
                        1.0F, 0.0F, 0.0F);
                doTaskWithVertexBuffer(f16, f17, f2, f11, f13,
                        1.0F, 0.0F, 0.0F);

                tessellator.draw();
            }
        }
    }

    private void doTaskWithVertexBuffer(double firstPosition, double secondPosition, double thirdPosition,
                                        double texFirst, double texSecond, float normalFirst,
                                        float normalSecond, float normalThird) {
        vertexBuffer
                .pos(firstPosition, secondPosition, thirdPosition)
                .tex(texFirst, texSecond)
                .normal(normalFirst, normalSecond, normalThird)
                .endVertex();
    }

    private void setLightmap(EntityPainting painting, float p_77008_2_, float p_77008_3_)
    {
        int i = MathHelper.floor_double(painting.posX);

        int j = MathHelper.floor_double(painting.posY + (double)(p_77008_3_ / 16.0F));

        int k = MathHelper.floor_double(painting.posZ);

        @Nullable final EnumFacing enumfacing = painting.facingDirection;

        if (enumfacing != null) {
            if (enumfacing == EnumFacing.NORTH)
            {
                i = MathHelper.floor_double(painting.posX + (double)(p_77008_2_ / 16.0F));
            }

            if (enumfacing == EnumFacing.WEST)
            {
                k = MathHelper.floor_double(painting.posZ - (double)(p_77008_2_ / 16.0F));
            }

            if (enumfacing == EnumFacing.SOUTH)
            {
                i = MathHelper.floor_double(painting.posX - (double)(p_77008_2_ / 16.0F));
            }

            if (enumfacing == EnumFacing.EAST)
            {
                k = MathHelper.floor_double(painting.posZ + (double)(p_77008_2_ / 16.0F));
            }

            int l = this.renderManager.worldObj.getCombinedLight(new BlockPos(i, j, k), 0);

            int i1 = l % 65536;

            int j1 = l / 65536;

            OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)i1, (float)j1);

            GlStateManager.color(1.0F, 1.0F, 1.0F);
        }
    }
}
