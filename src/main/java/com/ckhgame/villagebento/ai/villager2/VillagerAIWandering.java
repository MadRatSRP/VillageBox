package com.ckhgame.villagebento.ai.villager2;

import java.util.Random;

import com.ckhgame.villagebento.config.ConfigVillager;
import com.ckhgame.villagebento.entity.villager.EntityVBVillager;
import com.ckhgame.villagebento.util.village.VBDateTime;
import com.ckhgame.villagebento.util.village.VBRandomPositionGenerator;
import com.ckhgame.villagebento.util.village.VillagerNavigator;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.util.Vec3;

public class VillagerAIWandering extends VillagerAIMoving
{
    public VillagerAIWandering(EntityVBVillager entity)
    {
    	super(entity);
    }

    /**
     * Returns whether the EntityAIBase should begin execution.
     */
    public boolean shouldExecute()
    {
    	if(this.entity.getRNG().nextInt(30) == 0){
    		
    		if(this.entity.getProfession().getTimeSchedule().isWorkTimeNow())
    			return false;
    		
    		if(this.entity.getProfession().getTimeSchedule().isSleepTimeNow())
    			return false;
    		
    		if(!VBDateTime.isDayTime() || this.entity.worldObj.isRaining()){
    			this.targetPos = VBRandomPositionGenerator.findRandomTargetInBuildingFast(this.entity);
    		}
    		else{
    			this.targetPos = VBRandomPositionGenerator.findRandomTargetNearBuildingFast(this.entity);
    		}
    		if(this.entity.isVisiting()) this.entity.cancelVisiting();
    		return true;
    		
    	}
    	return false;
    }
    
    @Override
	public void startExecuting() {
		this.entity.setDebugText("Wandering...");
	}
    
}