package com.ckhgame.villagebento.profession;

import java.util.ArrayList;

import com.ckhgame.villagebento.entity.villager.EntityVBGuard;
import com.ckhgame.villagebento.entity.villager.EntityVBVillager;
import com.ckhgame.villagebento.util.data.VillagerChat;
import com.ckhgame.villagebento.villagercomponent.VillagerCompAbout;
import com.ckhgame.villagebento.villagercomponent.VillagerCompWork;
import com.ckhgame.villagebento.villagercomponent.VillagerComponent;

import net.minecraft.init.Items;

public class ProfessionSoldier extends Profession {

	@Override
	public String getProfessionName() {
		
		return "Soldier";
	}

	@Override
	public String getProfessionDescription() {
		
		return "In your order!";
	}
	
	@Override
	public Class<? extends EntityVBVillager> getEntityClass(){
		return EntityVBGuard.class;
	}
	
	@Override
	public boolean canSpawn() {
		
		return true;
	}

	
	
	@Override
	public void createVillagerComponents(ArrayList<VillagerComponent> components,EntityVBVillager villager) {

        components.add(new VillagerCompAbout(villager));
        
        VillagerCompWork compWork = new VillagerCompWork(villager);
        int workIdx;
        workIdx = compWork.createWork("Protect our homeland!", "Get somthing to prove..",0 , 18, 28);
        compWork.addWorkOutput(workIdx, Items.rotten_flesh, 7, 12);
        
       
        components.add(compWork);

	}

	@Override
	public void initVillagerChat(VillagerChat villagerChat) {

		villagerChat.add(0, "Let me tell you a ture story...");
		villagerChat.add(0, "Protect our homeland!");
		
	}

	@Override
	public String getSkinName() {
		return "soldier";
	}
	
	@Override
	protected void setTimeSchedule(){	
		timeSchedule.setSleeptime(new int[]{6, 14});
	}

}