//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.tileentity.totem;

import net.minecraft.entity.monster.EntityMob;

import java.util.List;

public class TileEntityFireTotem extends TileEntityTotem {

    @Override
    protected void updateTotonEffect() {
        List<EntityMob> list = this.worldObj.getEntitiesWithinAABB(EntityMob.class, this.getEffectField(16, 10));
        if (list != null) {
            for (EntityMob mob : list) {
                mob.setFire(4);
            }
        }
    }
}
