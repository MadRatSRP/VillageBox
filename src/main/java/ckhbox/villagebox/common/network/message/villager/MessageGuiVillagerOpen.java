//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.network.message.villager;

import ckhbox.villagebox.VillageBoxMod;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiVillagerOpen implements IMessage {
    private int guiID;

    private int dimension;

    private int entityID;

    public MessageGuiVillagerOpen(int guiID, int dimension, int entityID) {
        this.guiID = guiID;

        this.dimension = dimension;

        this.entityID = entityID;
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.guiID = buf.readInt();

        this.dimension = buf.readInt();

        this.entityID = buf.readInt();
    }

    @Override
    public void toBytes(ByteBuf buf) {
        buf.writeInt(guiID);

        buf.writeInt(dimension);

        buf.writeInt(entityID);
    }

    public static class Handler implements IMessageHandler<MessageGuiVillagerOpen, IMessage> {
        /**
         * This gets called when the packet is read and received.
         */
        @Override
        public IMessage onMessage(MessageGuiVillagerOpen message, MessageContext ctx) {
            ctx.getServerHandler().playerEntity.openGui(VillageBoxMod.instance, message.guiID,
                    ctx.getServerHandler().playerEntity.worldObj, message.dimension, message.entityID, 0);

            return null;
        }
    }
}
