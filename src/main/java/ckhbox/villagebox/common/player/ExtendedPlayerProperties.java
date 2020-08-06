//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.player;

import ckhbox.villagebox.VillageBoxMod;
import ckhbox.villagebox.common.item.ModItems;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.util.math.Rand;
import ckhbox.villagebox.common.village.profession.Profession;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class ExtendedPlayerProperties {
    public static final ResourceLocation key = new ResourceLocation(VillageBoxMod.MOD_ID + ".expp");

    public static final int NewMailTimerTotal = 2000;

    public EntityPlayer player;

    // -------------------------------------------------
    // if the player has sent the invitation
    public boolean hasSentInvitation;

    // how much time left to receive a new mail
    public int newMailTimer;

    // high level will bring more gems
    public int treasureHuntLevel;

    // if the player has recived the village book
    public boolean receivedVillagebook = false;

    public Collections collections;

    public ExtendedPlayerProperties(EntityPlayer player) {
        this.player = player;

        this.collections = new Collections(player);
    }

    public static ExtendedPlayerProperties get(EntityPlayer player) {
        return player.getCapability(CapExPlayerProperties.EXTENDED_PLAYER_PROPERTIES_CAPABILITY, null);
    }

    public void sendNewVillagerInvitation() {
        if (!this.hasSentInvitation) {
            this.hasSentInvitation = true;

            this.resetMailTimer();
        }
    }

    public boolean hasNewVillagerMail() {
        return this.hasSentInvitation && this.newMailTimer <= 0;
    }

    public void receiveNewVillagerMail() {
        this.hasSentInvitation = false;
    }

    public void resetMailTimer() {
        this.newMailTimer = (int) (ExtendedPlayerProperties.NewMailTimerTotal * (Rand.get().nextFloat() * 0.5F + 0.5F));
    }

    public boolean upgradeTreasureHuntLevelTo(int level) {
        if (level > treasureHuntLevel) {
            treasureHuntLevel = level;

            return true;
        } else {
            return false;
        }
    }

    public void saveNBTData(NBTTagCompound compound) {
        compound.setBoolean("invited", this.hasSentInvitation);

        compound.setInteger("nmtimer", this.newMailTimer);

        compound.setInteger("treasurelvl", this.treasureHuntLevel);

        compound.setBoolean("receiveddvb", this.receivedVillagebook);

        // collections
        NBTTagCompound collections = new NBTTagCompound();

        this.collections.saveNBTData(collections);

        compound.setTag("collections", collections);
    }

    public void loadNBTData(NBTTagCompound compound) {
        this.hasSentInvitation = compound.getBoolean("invited");

        this.newMailTimer = compound.getInteger("nmtimer");

        this.treasureHuntLevel = compound.getInteger("treasurelvl");

        this.receivedVillagebook = compound.getBoolean("receiveddvb");

        // collections
        this.collections.loadNBTData(compound.getCompoundTag("collections"));
    }

    public void init(Entity entity, World world) {
        this.resetMailTimer();

        this.hasSentInvitation = false;

        this.treasureHuntLevel = 0;
    }

    public static class Collections {
        private final EntityPlayer player;

        //unlocked villager professions
        private final List<Integer> proIDs = new ArrayList<Integer>();

        public Collections(EntityPlayer player) {
            this.player = player;
        }

        public void addProfession(Profession profession) {
            if (profession != null && !this.proIDs.contains(profession.getRegID())) {
                this.proIDs.add(profession.getRegID());

                ItemStack villageBook = new ItemStack(ModItems.villageBook);

                this.player.addChatMessage(new TextComponentTranslation(
                        PathHelper.full("message.player.collections.addproid"),
                    new TextComponentTranslation(profession.getUnlocalizedDisplayName()),
                    new TextComponentTranslation(villageBook.getUnlocalizedName() + ".name")));
            }
        }

        public boolean hasProfession(int id) {
            return this.proIDs.contains(id);
        }

        public void loadNBTData(NBTTagCompound compound) {
            int[] proids = compound.getIntArray("proids");

            if (proids.length > 0) {
                this.proIDs.clear();

                for (int proid : proids)
                    this.proIDs.add(proid);
            }
        }

        public void saveNBTData(NBTTagCompound compound) {
            if (this.proIDs.size() > 0) {
                int[] temp = new int[this.proIDs.size()];

                for (int i = 0; i < this.proIDs.size(); i++) {
                    temp[i] = this.proIDs.get(i);
                }

                compound.setIntArray("proids", temp);
            } else {
                compound.removeTag("proids");
            }
        }
    }

}
