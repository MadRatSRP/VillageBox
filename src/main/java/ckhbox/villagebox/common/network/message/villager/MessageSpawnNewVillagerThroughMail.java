//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.network.message.villager;

import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.item.common.ItemMail;
import ckhbox.villagebox.common.util.helper.PathHelper;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSpawnNewVillagerThroughMail implements IMessage {
    public MessageSpawnNewVillagerThroughMail() {
    }

    @Override
    public void fromBytes(ByteBuf buf) {

    }

    @Override
    public void toBytes(ByteBuf buf) {

    }

    public static class Handler implements IMessageHandler<MessageSpawnNewVillagerThroughMail, IMessage> {
        /**
         * This gets called when the packet is read and received.
         */
        @Override
        public IMessage onMessage(MessageSpawnNewVillagerThroughMail message, MessageContext ctx) {
            // spawn villager
            EntityPlayer player = ctx.getServerHandler().playerEntity;

            ItemStack hold = player.getHeldItemMainhand();
            if (hold != null && hold.getItem() == ModItems.mail) {
                int mailType = ItemMail.getMailType(hold);

                if (mailType == ItemMail.MailType_NewVillagerMale || mailType == ItemMail.MailType_NewVillagerFemale) {
                    String name = ItemMail.getMailSender(hold);

                    EntityVillager villager = new EntityVillager(player.worldObj, name, mailType == ItemMail.MailType_NewVillagerMale);

                    double d = 2.0F;

                    double x = player.posX - Math.sin(player.rotationYaw / 180.0F * (float) Math.PI) * d;

                    double z = player.posZ + Math.cos(player.rotationYaw / 180.0F * (float) Math.PI) * d;

                    double y = player.posY;

                    villager.setLocationAndAngles(x, y, z, player.rotationYaw + 180, 0);

                    ctx.getServerHandler().playerEntity.worldObj.spawnEntityInWorld(villager);

                    player.addChatMessage(
                            new TextComponentTranslation(PathHelper.full("message.villager.newjoined"), villager.getName())
                    );

                    // remove hold
                    player.inventory.setInventorySlotContents(player.inventory.currentItem, null);
                }
            }
            return null;
        }
    }
}
