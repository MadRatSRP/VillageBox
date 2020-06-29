//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.item.book;

import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.player.ExtendedPlayerProperties;
import ckhbox.villagebox.common.util.helper.PathHelper;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.List;

public class ItemTreasureBook
    extends Item {
    private final int level;

    public ItemTreasureBook(int lvl) {
        this.setUnlocalizedName(PathHelper.full("treasurebook" + lvl));

        this.setCreativeTab(ModItems.tabVB);

        this.setMaxStackSize(1);

        this.level = lvl;
    }

    @Nonnull
    @Override
    public ActionResult<ItemStack> onItemRightClick(@Nonnull ItemStack itemStackIn, World worldIn,
                                                    @Nonnull EntityPlayer playerIn, @Nonnull EnumHand hand) {

        if (!worldIn.isRemote) {
            // increase treasure hunt level
            if (ExtendedPlayerProperties.get(playerIn).upgradeTreasureHuntLevelTo(this.level)) {
                playerIn.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

                playerIn.addChatMessage(new TextComponentTranslation(
                    PathHelper.full("message.player.treasurelvlupgrade.success"), this.level)
                );

                itemStackIn.stackSize -= 1;

                return new ActionResult<>(EnumActionResult.SUCCESS, itemStackIn);
            } else {
                playerIn.addChatMessage(new TextComponentTranslation(
                    PathHelper.full("message.player.treasurelvlupgrade.failed"))
                );

                return new ActionResult<>(EnumActionResult.FAIL, itemStackIn);
            }
        }

        return super.onItemRightClick(itemStackIn, worldIn, playerIn, hand);
    }

    @Override
    public void addInformation(@Nonnull ItemStack stack, @Nonnull EntityPlayer playerIn,
                               @Nonnull List<String> tooltip, boolean advanced) {
        super.addInformation(stack, playerIn, tooltip, advanced);

        String info = I18n.format(PathHelper.full("book.treasure.item.info"), this.level);

        tooltip.add(info);
    }
}
