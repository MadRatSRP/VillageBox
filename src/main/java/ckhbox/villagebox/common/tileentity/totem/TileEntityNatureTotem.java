//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.tileentity.totem;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import java.util.List;

public class TileEntityNatureTotem extends TileEntityTotem {
    @Override
    protected void updateTotonEffect() {
        List<EntityLivingBase> list = this.worldObj.getEntitiesWithinAABB(EntityLivingBase.class,
                this.getEffectField(16, 10));

        for (EntityLivingBase entity : list) {
            if (entity instanceof EntityMob)
                continue;
            entity.addPotionEffect(new PotionEffect(MobEffects.REGENERATION, 100, 0));
        }
    }
}
