//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villager;

import ckhbox.villagebox.client.gui.GuiHelper;
import ckhbox.villagebox.client.gui.quest.GuiQuest;
import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.network.ModNetwork;
import ckhbox.villagebox.common.network.message.villager.MessageGuiCompleteQuest;
import ckhbox.villagebox.common.network.message.villager.MessageGuiSetInteracting;
import net.minecraft.entity.player.EntityPlayer;

import java.io.IOException;

public class GuiVillagerQuest extends GuiQuest{
    protected int villagerNameOffsetY = 6;

	private final EntityVillager villager;
    
	public GuiVillagerQuest(EntityPlayer entityplayer, EntityVillager villager) {
		super(entityplayer, villager);
		this.villager = villager;
	}
	
	@Override
	protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
		super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY);
		
		// int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
		
        GuiHelper.drawNameAndProfession(this.mc.fontRendererObj, villager, this.width / 2, y + villagerNameOffsetY);
		
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		
		ModNetwork.getInstance().sendToServer(new MessageGuiSetInteracting(this.villager.getEntityId(), this.villager.dimension, false));
	}
	
	@Override
	protected void keyTyped(char typedChar, int keyCode) throws IOException {
		super.keyTyped(typedChar, keyCode);
		
		if (keyCode == 1 || keyCode == this.mc.gameSettings.keyBindInventory.getKeyCode()){
			ModNetwork.getInstance().sendToServer(new MessageGuiSetInteracting(this.villager.getEntityId(), this.villager.dimension, false));
		}
	}

	@Override
	protected void onButtonCompleteClicked() {
		ModNetwork.getInstance().sendToServer(new MessageGuiCompleteQuest(this.villager.getEntityId(), this.villager.dimension));
	}

}
