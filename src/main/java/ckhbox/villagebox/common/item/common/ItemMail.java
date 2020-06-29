//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.common;

import ckhbox.villagebox.VillageBoxMod;
import ckhbox.villagebox.common.gui.GuiIDs;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemMail
    extends Item {
    public static final int MailType_Common = 0;

    public static final int MailType_NewVillagerMale = 1;

    public static final int MailType_NewVillagerFemale = 2;

    public ItemMail() {
        this.setUnlocalizedName(PathHelper.full("mail"));

        this.setCreativeTab(ModItems.tabVB);

        this.setMaxStackSize(1);
    }

    public static ItemStack generateMail(String sender, String content, int mailtype) {
        ItemStack mail = new ItemStack(ModItems.mail);

        setMailSender(mail, sender);

        setMailContent(mail, content);

        setMailType(mail, mailtype);

        return mail;
    }

    private static boolean checkStack(ItemStack stack) {
        if (stack.getItem() == ModItems.mail) {
            if (!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            return true;
        }
        return false;
    }

    public static void setMailContent(ItemStack stack, String content) {
        if (checkStack(stack)) {
            if (stack.getTagCompound() != null) {
                stack.getTagCompound().setString("content", content);
            }
        }
    }

    public static void setMailSender(ItemStack stack, String sender) {
        if (checkStack(stack)) {
            if (stack.getTagCompound() != null) {
                stack.getTagCompound().setString("sender", sender);
            }
        }
    }

    public static void setMailType(ItemStack stack, int mailType) {
        if (checkStack(stack)) {
            if (stack.getTagCompound() != null) {
                stack.getTagCompound().setInteger("mailtype", mailType);
            }
        }
    }

    public static String getMailContent(ItemStack stack) {
        if (checkStack(stack)) {
            String content = null;

            if (stack.getTagCompound() != null) {
                content = stack.getTagCompound().getString("content");
            }

            if (content != null && !content.equals(""))
                return content;
        }
        return "???";
    }

    public static String getMailSender(ItemStack stack) {
        if (checkStack(stack)) {
            String sender = null;

            if (stack.getTagCompound() != null) {
                sender = stack.getTagCompound().getString("sender");
            }

            if (sender != null && !sender.equals(""))
                return sender;
        }
        return "???";
    }

    public static int getMailType(ItemStack stack) {
        if (checkStack(stack)) {
            if (stack.getTagCompound() != null) {
                return stack.getTagCompound().getInteger("mailtype");
            }
        }

        return MailType_Common;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStackIn, World worldIn,
                                                    @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand) {
        if (!worldIn.isRemote) {
            playerIn.openGui(VillageBoxMod.instance, GuiIDs.Mail, worldIn, 0,
                0, 0);
        }

        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn,
                               @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);

        String info = I18n.format(PathHelper.full("mail.item.info"), getMailSender(stack));

        tooltip.add(info);
    }
}
