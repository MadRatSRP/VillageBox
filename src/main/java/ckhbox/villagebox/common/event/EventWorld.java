//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.event;

import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.village.data.DataVillage;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class EventWorld {
	
	@SubscribeEvent
	public void onWorldTick(TickEvent.WorldTickEvent event) {
		
		if(!event.world.isRemote && event.phase == TickEvent.Phase.END){
			if(event.world.getTotalWorldTime() % 1000 == 0){
				DataVillage.get(event.world).updateDeadVillagers(1000);
			}
		}		 
	}
}
