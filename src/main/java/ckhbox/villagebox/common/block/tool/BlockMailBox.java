//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.block.tool;

import ckhbox.villagebox.common.block.common.BlockFacing;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.util.tool.MailGenerator;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BlockMailBox
    extends BlockFacing {

    public BlockMailBox() {
        super(Material.WOOD);
        this.setUnlocalizedName(PathHelper.full("mailbox"));
        this.setHardness(2.5F);
        this.setSoundType(SoundType.WOOD);
        this.setCreativeTab(ModItems.tabVB);
    }

    @Override
    public boolean onBlockActivated(World worldIn, @Nonnull BlockPos pos, @Nonnull IBlockState state,
                                    @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand, ItemStack heldItem,
                                    @Nonnull EnumFacing side, float hitX, float hitY, float hitZ) {

        if (!worldIn.isRemote) {
            @Nullable final ItemStack currentStackOfHeldItems = playerIn.getHeldItem(hand);
            @Nullable Item currentItem = null;

            if (currentStackOfHeldItems != null) {
                currentItem = currentStackOfHeldItems.getItem();
            }

            if (currentItem != null && currentItem == ModItems.invitation) {
                if (ExtendedPlayerProperties.get(playerIn).hasSentInvitation) {
                    playerIn.addChatMessage(new TextComponentTranslation(PathHelper.full("message.mail.invitefailed")));
                } else {
                    if (!playerIn.capabilities.isCreativeMode) {
                        @Nullable ItemStack stack = playerIn.getHeldItem(hand);

                        if (stack != null) {
                            --stack.stackSize;
                        }

                        if (stack != null && stack.stackSize <= 0) {
                            playerIn.inventory.setInventorySlotContents(
                                playerIn.inventory.currentItem, null
                            );
                        }
                    }
                    ExtendedPlayerProperties.get(playerIn).sendNewVillagerInvitation();
                    playerIn.addChatMessage(new TextComponentTranslation(PathHelper.full("message.mail.invitesuccess")));
                }
            } else {
                if (ExtendedPlayerProperties.get(playerIn).hasNewVillagerMail()) {
                    //get mail
                    ExtendedPlayerProperties.get(playerIn).receiveNewVillagerMail();
                    ItemStack mail = MailGenerator.generate();

                    //drop mail
                    double x = (double) pos.getX() + 0.5D;
                    double y = (double) pos.getY() + 0.5D;
                    double z = (double) pos.getZ() + 0.5D;
                    EntityItem entityitem = new EntityItem(worldIn, x, y, z, mail);

                    double d1 = playerIn.posX - x;
                    double d3 = playerIn.posY - y;
                    double d5 = playerIn.posZ - z;
                    double d7 = MathHelper.sqrt_double(d1 * d1 + d3 * d3 + d5 * d5);
                    double d9 = 0.08D;
                    entityitem.motionX = d1 * d9;
                    entityitem.motionY = d3 * d9 + (double) MathHelper.sqrt_double(d7) * 0.05D;
                    entityitem.motionZ = d5 * d9;

                    entityitem.setDefaultPickupDelay();
                    worldIn.spawnEntityInWorld(entityitem);
                } else {
                    playerIn.addChatMessage(new TextComponentTranslation(PathHelper.full("message.mail.nomail")));
                }
            }
        }
        return true;
    }

}
