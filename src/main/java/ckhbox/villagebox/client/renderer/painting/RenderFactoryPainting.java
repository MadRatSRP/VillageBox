//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.renderer.painting;

import ckhbox.villagebox.common.entity.painting.EntityPainting;
import ckhbox.villagebox.common.entity.throwable.EntityFlameBall;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.EntityHanging;
import net.minecraft.init.Items;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFactoryPainting implements IRenderFactory<EntityPainting>{

	@Override
	public Render<? super EntityPainting> createRenderFor(RenderManager manager) {
		return new RenderPainting(manager);
	}

}
