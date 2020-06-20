//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.client.gui.villager;

import java.io.IOException;

import ckhbox.villagebox.client.gui.GuiHelper;
import ckhbox.villagebox.client.gui.common.GuiTrading;
import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.network.ModNetwork;
import ckhbox.villagebox.common.network.message.villager.MessageGuiSetInteracting;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.world.World;

public class GuiVillagerTrading extends GuiTrading{

    protected int villagerNameOffsetY = 6;
	private final EntityVillager villager;
    
	public GuiVillagerTrading(InventoryPlayer playerInventory, EntityVillager villager, World worldIn) {
		super(playerInventory, villager, worldIn);
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

}
