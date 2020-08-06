//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.network.message.villager;

import ckhbox.villagebox.common.gui.villager.ContainerVillagerUpgrading;
import io.netty.buffer.ByteBuf;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiSelectUpgradeOptionIndex implements IMessage {
    private int currentUpgradeOptionIndex;

    public MessageGuiSelectUpgradeOptionIndex(int currentUpgradeOptionIndex) {
        this.currentUpgradeOptionIndex = currentUpgradeOptionIndex;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.currentUpgradeOptionIndex = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(currentUpgradeOptionIndex);
    }

    public static class Handler implements IMessageHandler<MessageGuiSelectUpgradeOptionIndex, IMessage> {
        /**
         * This gets called when the packet is read and received.
         */
        @Override
        public IMessage onMessage(MessageGuiSelectUpgradeOptionIndex message, MessageContext ctx) {
            Container container = ctx.getServerHandler().playerEntity.openContainer;

            if (container instanceof ContainerVillagerUpgrading) {
                ((ContainerVillagerUpgrading) container).setCurrentUpgradeOptionIndex(message.currentUpgradeOptionIndex);
            }

            return null;
        }
    }
}
