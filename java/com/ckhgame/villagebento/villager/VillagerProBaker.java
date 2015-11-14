package com.ckhgame.villagebento.villager;

import java.util.ArrayList;

import com.ckhgame.villagebento.block.ModBlocks;
import com.ckhgame.villagebento.villager.chat.VillagerChat;
import com.ckhgame.villagebento.villager.component.VillagerCompAbout;
import com.ckhgame.villagebento.villager.component.VillagerCompBuy;
import com.ckhgame.villagebento.villager.component.VillagerCompSell;
import com.ckhgame.villagebento.villager.component.VillagerComponent;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;

public class VillagerProBaker extends Villager {

	@Override
	public String getProfessionName() {
		// TODO Auto-generated method stub
		return "Professional Baker";
	}

	@Override
	public String getProfessionDescription() {
		// TODO Auto-generated method stub
		return "Advance level Baker";
	}

	@Override
	public boolean canSpawn() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void createComponents(ArrayList<VillagerComponent> components) {

        components.add(new VillagerCompAbout());
        
        VillagerCompBuy compBuy = new VillagerCompBuy();
        compBuy.addItemBuy(Items.cake, 2, 2, 0);
        compBuy.addItemBuy(Items.pumpkin_pie, 1, 1, 0);
        compBuy.addItemBuy(Items.cookie, 1, 2, 0);
        compBuy.addItemBuy(Items.baked_potato, 2, 3, 0);
        components.add(compBuy);

	}
	
	@Override
	public void initVillagerChat(VillagerChat villagerChat) {

		villagerChat.add(0, "Fresh bread!");
		villagerChat.add(0, "Are you hungury?");
		villagerChat.add(0, "Got somthing delicious!");
		
	}

	@Override
	public String getSkinName() {
		return "probaker";
	}
	
}