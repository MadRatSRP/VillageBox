//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.tileentity.totem;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

public class TileEntityWaterTotem extends TileEntityTotem{

	@Override
	protected void updateTotonEffect() {
		List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEffectField(16, 10));
		if(list != null){
			for(EntityPlayer player : list){
				player.addPotionEffect(new PotionEffect(MobEffects.SPEED,100,1));
			}
		}
	}
}
