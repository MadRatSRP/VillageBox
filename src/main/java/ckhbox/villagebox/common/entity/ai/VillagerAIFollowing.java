//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.entity.ai;

import ckhbox.villagebox.common.entity.villager.EntityVillager;
import net.minecraft.entity.ai.EntityAIBase;

public class VillagerAIFollowing
    extends EntityAIBase {
    private static final double maxDisSq = 144.0F;
    private static final double minDisSq = 9.0F;

    /**
     * The child that is following its parent.
     */
    private final EntityVillager villager;
    private final float moveSpeed;
    private int delayCounter;

    public VillagerAIFollowing(EntityVillager villager, float speed) {
        this.villager = villager;
        this.moveSpeed = speed;
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {
        if (!this.villager.isFollowing()) {
            return false;
        }

        if (!this.villager.getFollowing().isEntityAlive()) {
            this.villager.setFollowing(null);
            return false;
        }

        if (!this.inFollowingDistance()) {
            double d = this.villager.getDistanceSqToEntity(this.villager.getFollowing());
            if (d >= maxDisSq) {
                this.villager.setFollowing(null);
            }
            return false;
        }

        return true;
    }

    private boolean inFollowingDistance() {
        double d = this.villager.getDistanceSqToEntity(this.villager.getFollowing());
        return d >= minDisSq && d <= maxDisSq;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        if (!this.villager.isAirBorne) {
            return false;
        } else {
            return this.inFollowingDistance();
        }
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.delayCounter = 0;
    }

    /**
     * Resets the task
     */
    public void resetTask() {
    }

    /**
     * Updates the task
     */
    public void updateTask() {
        if (--this.delayCounter <= 0) {
            this.delayCounter = 10;
            this.villager.getNavigator().tryMoveToEntityLiving(this.villager.getFollowing(), this.moveSpeed);
        }
    }
}