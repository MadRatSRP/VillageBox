//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.entity.ai;

import ckhbox.villagebox.common.entity.villager.EntityVillager;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.player.EntityPlayer;

public class VillagerAILookAtInteractPlayer extends EntityAIWatchClosest
{
    private final EntityVillager villager;

    public VillagerAILookAtInteractPlayer(EntityVillager villager)
    {
        super(villager, EntityPlayer.class, 8.0F);
        this.villager = villager;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
        if (this.villager.isInteracting())
        {
            this.closestEntity = this.villager.getInteracting();
            return true;
        }
        else
        {
            return false;
        }
    }
}