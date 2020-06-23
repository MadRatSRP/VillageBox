//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.network.message.villager;

import ckhbox.villagebox.common.gui.villager.ContainerVillagerUpgrading;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiVillagerUpgrade implements IMessage {


    public MessageGuiVillagerUpgrade() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageGuiVillagerUpgrade, IMessage> {
        /**
         * This gets called when the packet is read and received.
         */
        @Override
        public IMessage onMessage(MessageGuiVillagerUpgrade message, MessageContext ctx) {

            //upgrade villager
            if (ctx.getServerHandler().playerEntity.openContainer instanceof ContainerVillagerUpgrading) {
                ((ContainerVillagerUpgrading) ctx.getServerHandler().playerEntity.openContainer).upgrade();
            }

            return null;
        }
    }
}
