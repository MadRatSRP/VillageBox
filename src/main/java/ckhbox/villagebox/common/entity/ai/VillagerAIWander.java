//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.entity.ai;

import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.util.math.Rand;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.math.Vec3d;

public class VillagerAIWander extends EntityAIBase {
    private final EntityVillager villager;
    private final double speed;
    private final int executionChance;
    private double xPosition;
    private double yPosition;
    private double zPosition;

    public VillagerAIWander(EntityVillager villagerIn, double speedIn) {
        this(villagerIn, speedIn, 50);
    }

    public VillagerAIWander(EntityVillager villagerIn, double speedIn, int chance) {
        this.villager = villagerIn;
        this.speed = speedIn;
        this.executionChance = chance;
        this.setMutexBits(1);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute() {

        if (this.villager.getRNG().nextInt(this.executionChance) != 0) {
            return false;
        }

        if (this.villager.isFollowing()) {
            return false;
        }

        if (this.villager.isInteracting()) {
            return false;
        }

        Vec3d vec3;

        if (this.villager.getHome() == null) {
            vec3 = new Vec3d(
                this.villager.getWanderCenter().xCoord + Rand.get().nextDouble() * 6.0D - 3.0D,
                this.villager.getWanderCenter().yCoord,
                this.villager.getWanderCenter().zCoord + Rand.get().nextDouble() * 6.0D - 3.0D
            );
        } else {
            if (this.villager.worldObj.isDaytime() && !this.villager.worldObj.isRaining()) {
                // if now is day time and not raining, the villager will randomly walking near home
                vec3 = this.villager.getHome().extend(5, 2, 5).getRandomPosInsideBoundary();
            } else {
                // if is raining or at night, the villager will stay at home
                vec3 = this.villager.getHome().getRandomPosInsideBoundary();
            }
        }


        if (vec3 == null) {
            return false;
        } else {
            this.xPosition = vec3.xCoord;
            this.yPosition = vec3.yCoord;
            this.zPosition = vec3.zCoord;
            return true;
        }
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean continueExecuting() {
        if (this.villager.isFollowing()) {
            return false;
        }

        if (this.villager.isInteracting()) {
            return false;
        }

        return !this.villager.getNavigator().noPath();
    }


    @Override
    public void resetTask() {
        this.villager.getNavigator().clearPathEntity();
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void startExecuting() {
        this.villager.getNavigator().tryMoveToXYZ(this.xPosition, this.yPosition, this.zPosition, this.speed);
    }
}