//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.network.message.player;

import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties.Collections;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class MessageSyncCollections implements IMessage {
    public NBTTagCompound tagCollections;

    public MessageSyncCollections(Collections collections) {
        this.tagCollections = new NBTTagCompound();
        collections.saveNBTData(this.tagCollections);
    }

    @Override
    public void fromBytes(ByteBuf buf) {
        this.tagCollections = ByteBufUtils.readTag(buf);
    }

    @Override
    public void toBytes(ByteBuf buf) {
        ByteBufUtils.writeTag(buf, this.tagCollections);
    }

    private static class HandlerCommon implements IMessageHandler<MessageSyncCollections, IMessage> {
        /**
         * This gets called when the packet is read and received.
         */
        @Override
        public IMessage onMessage(MessageSyncCollections message, MessageContext ctx) {
            return null;
        }
    }

    public static class Handler extends HandlerCommon {
        @Override
        @SideOnly(Side.CLIENT)
        public IMessage onMessage(MessageSyncCollections message, MessageContext ctx) {
            ExtendedPlayerProperties properties = ExtendedPlayerProperties
                    .get(Minecraft.getMinecraft().thePlayer);

            properties.collections.loadNBTData(message.tagCollections);

            return null;
        }
    }
}
