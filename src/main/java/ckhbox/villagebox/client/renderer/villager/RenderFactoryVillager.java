//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.renderer.villager;

import ckhbox.villagebox.common.entity.villager.EntityVillager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderFactoryVillager implements IRenderFactory<EntityVillager>{

	@Override
	public Render<? super EntityVillager> createRenderFor(RenderManager manager) {
		return new RenderVillager(manager);
	}

}
