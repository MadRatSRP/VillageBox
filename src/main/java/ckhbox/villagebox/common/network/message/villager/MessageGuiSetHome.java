//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.network.message.villager;

import ckhbox.villagebox.common.entity.villager.EntityVillager;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageGuiSetHome implements IMessage {

	private int dimension;
	private int entityVillagerID;
	private boolean isMoveIn;
	
	
	public MessageGuiSetHome(){
	}

	public MessageGuiSetHome(int entityVillagerID, int dimension, boolean isMoveIn){
		this.entityVillagerID = entityVillagerID;
		this.dimension = dimension;
		this.isMoveIn = isMoveIn;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) {
		this.entityVillagerID = buf.readInt();
		this.dimension = buf.readInt();
		this.isMoveIn = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(this.entityVillagerID);
		buf.writeInt(this.dimension);
		buf.writeBoolean(this.isMoveIn);
	}

	public static class Handler implements IMessageHandler<MessageGuiSetHome, IMessage> {
        /**
         * This gets called when the packet is read and received.
         */
        @Override
        public IMessage onMessage(MessageGuiSetHome message, MessageContext ctx) {
        	
        	if(ctx.getServerHandler().playerEntity.worldObj.provider.getDimension() == message.dimension){
        		//get villager
        		Entity entity = ctx.getServerHandler().playerEntity.worldObj.getEntityByID(message.entityVillagerID);
        		if(entity != null && entity instanceof EntityVillager){
        			EntityVillager villager = (EntityVillager)entity;
        			if(message.isMoveIn){
        				villager.setCurrentPosAsHome(ctx.getServerHandler().playerEntity);
        			}
        			else{
        				villager.moveOutHome(ctx.getServerHandler().playerEntity);
        			}
        		}
        	}

            return null;
        }
    }
}
