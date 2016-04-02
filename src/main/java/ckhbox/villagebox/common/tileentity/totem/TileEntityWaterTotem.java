package ckhbox.villagebox.common.tileentity.totem;

import java.util.List;

import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class TileEntityWaterTotem extends TileEntityTotem{

	@Override
	protected void updateTotonEffect() {
		List<EntityPlayer> list = this.worldObj.getEntitiesWithinAABB(EntityPlayer.class, this.getEffectField(16, 10));
		if(list != null){
			for(EntityPlayer player : list){
				player.addPotionEffect(new PotionEffect(Potion.moveSpeed.id,100,1));
			}
		}
	}
}