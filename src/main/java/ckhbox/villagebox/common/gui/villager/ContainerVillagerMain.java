//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.gui.villager;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ContainerVillagerMain extends Container {
    public ContainerVillagerMain() {
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return true;
    }


}