//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.village.data;

import java.util.ArrayList;

import ckhbox.villagebox.common.config.VBConfig;
import ckhbox.villagebox.common.entity.villager.EntityVillager;
import ckhbox.villagebox.common.util.helper.PathHelper;
import ckhbox.villagebox.common.util.math.IntBoundary;
import ckhbox.villagebox.common.util.math.IntVec3;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraftforge.common.util.Constants.NBT;

public class DataVillage extends WorldSavedData{

	private static final String key = "villagebox.datavillage";
	
	public static DataVillage get(World world){
		//only for server
		if(world.isRemote)
			return null;
		
		DataVillage data = (DataVillage)world.getPerWorldStorage().getOrLoadData(DataVillage.class, key);
		if(data == null){
			//first time creating...
			data = new DataVillage(key);
			world.getPerWorldStorage().setData(key, data);
		}
		data.world = world;
		
		return data;
	}
	
	//----------------------------
	private World world;
	
	private ArrayList<HomeBoundary> homeList = new ArrayList<HomeBoundary>();
	private ArrayList<DeadVillager> deadVillagers = new ArrayList<DeadVillager>();
	
	public DataVillage(String name) {
		super(name);
	}

	public boolean removeHome(String owner,IntBoundary bound){
		if(bound == null)
			return false;
		
		for(int i =0;i<homeList.size();i++){
			HomeBoundary hb = homeList.get(i);
			if(hb.boundary.equalTo(bound) && hb.owner.equals(owner)){
				homeList.remove(i);
				this.markDirty();
				return true;
			}
		}
		return true;
	}
	
	/**
	 * return != null means the new boundary has contacted with a old boundary
	 */
	public String addHome(String owner, IntBoundary bound){	
				
		if(!VBConfig.oneVillagerPerRoom)
			return null;
		
		for(int i =0;i<homeList.size();i++){
			if(homeList.get(i).boundary.contact(bound)){
				return homeList.get(i).owner;
			}
		}
		homeList.add(new HomeBoundary(owner,bound));
		this.markDirty();
		return null;
	}
	
	public void addDeadVillager(EntityVillager villager){
		this.deadVillagers.add(new DeadVillager(villager));
		this.markDirty();
	}
	
	public void updateDeadVillagers(int ticks){
		DeadVillager deadVillager = null;
		for(int i =0;i<deadVillagers.size();i++){
			deadVillager = deadVillagers.get(i);
			deadVillager.ticksLeft -= ticks;
			if(deadVillager.ticksLeft <= 0){
				this.reviveVillager(deadVillager);
				deadVillagers.remove(i);
				i--;
			}
		}
		this.markDirty();
	}
	
	public void reviveVillager(DeadVillager deadVillager){
		EntityVillager villager = new EntityVillager(world, deadVillager.name, deadVillager.gender);
		villager.setProfession(deadVillager.proid);
		if(deadVillager.home == null){
			villager.setPosition(deadVillager.pos.x + 0.5D, deadVillager.pos.y + 0.1D, deadVillager.pos.z + 0.5D);
		}
		else{
			IntVec3 c = deadVillager.home.center();
			villager.setPosition(c.x + 0.5D, deadVillager.home.miny, c.z + 0.5D);
			villager.setHome(deadVillager.home);
		}
		villager.setUpgradingHistory(deadVillager.upgradingHistory);
		this.world.spawnEntityInWorld(villager);
		this.world.getMinecraftServer().getPlayerList().sendChatMsg(new TextComponentTranslation(PathHelper.full("message.villager.revived"),deadVillager.name));
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
		//home list
		NBTTagList taglist = nbt.getTagList("homes", NBT.TAG_COMPOUND);
		int count = taglist.tagCount();
		this.homeList.clear();
		for(int i =0;i<count;i++){
			this.homeList.add(new HomeBoundary(taglist.getCompoundTagAt(i)));
		}
		
		//dead villagers
		taglist = nbt.getTagList("deadvlgs",NBT.TAG_COMPOUND);
		count = taglist.tagCount();
		this.deadVillagers.clear();
		for(int i =0;i<count;i++){
			this.deadVillagers.add(new DeadVillager(taglist.getCompoundTagAt(i)));
		}
		
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound nbt) {
		
		//home list
		NBTTagList taglist = new NBTTagList();
		for(HomeBoundary homeBoundary : this.homeList){
			NBTTagCompound compound = new NBTTagCompound();
			homeBoundary.writeToNBT(compound);
			taglist.appendTag(compound);
		}
		nbt.setTag("homes", taglist);
		
		//dead villagers
		taglist = new NBTTagList();
		for(DeadVillager deadVillager : this.deadVillagers){
			NBTTagCompound compound = new NBTTagCompound();
			deadVillager.writeToNBT(compound);
			taglist.appendTag(compound);
		}
		nbt.setTag("deadvlgs", taglist);
		
		return nbt;
	}

	static class HomeBoundary{
		public String owner;
		public IntBoundary boundary;
		public HomeBoundary(String owner, IntBoundary boundary){
			this.owner = owner;
			this.boundary = boundary;
		}
		
		public HomeBoundary(NBTTagCompound nbt){
			this.readFromNBT(nbt);
		}
		
		public void writeToNBT(NBTTagCompound nbt){
			nbt.setString("owner", this.owner);
			nbt.setIntArray("bound", this.boundary.toArray());
		}
		
		public void readFromNBT(NBTTagCompound nbt){
			int[] arr = nbt.getIntArray("bound");
			this.boundary = new IntBoundary(arr);
			this.owner = nbt.getString("owner");
		}
	}
	
	static class DeadVillager{
		public int ticksLeft;
		public String name;
		public int proid;
		public boolean gender;
		public IntBoundary home;
		public IntVec3 pos;
		public int[] upgradingHistory;
		public DeadVillager(EntityVillager villager){
			this.name = villager.getName();
			this.proid = villager.getProfession().getRegID();
			this.gender = villager.isMale();
			this.home = villager.getHome();
			this.pos = this.home == null?new IntVec3(villager.getWanderCenter()):null;
			this.upgradingHistory = villager.getUpgradingHistory();
			this.ticksLeft = VBConfig.reviveTicks;
		}
		
		public DeadVillager(NBTTagCompound nbt){
			this.readFromNBT(nbt);
		}
		
		public void writeToNBT(NBTTagCompound nbt){
			nbt.setInteger("ticksleft", this.ticksLeft);
			nbt.setString("name", this.name);
			nbt.setInteger("proid", this.proid);
			nbt.setBoolean("gender", this.gender);
			nbt.setBoolean("hashome", this.home != null);
			if(this.home != null){
				nbt.setIntArray("home", this.home.toArray());
			}
			else{
				nbt.setIntArray("pos", this.pos.toArray());
			}
			if(this.upgradingHistory != null && this.upgradingHistory.length > 0){
				nbt.setIntArray("upgrades", this.upgradingHistory);
			}
		}
		
		public void readFromNBT(NBTTagCompound nbt){
			this.ticksLeft = nbt.getInteger("ticksleft");
			this.name = nbt.getString("name");
			this.proid = nbt.getInteger("proid");
			this.gender = nbt.getBoolean("gender");
			boolean hasHome = nbt.getBoolean("hashome");
			if(hasHome){
				this.home = new IntBoundary(nbt.getIntArray("home"));
				this.pos = null;
			}
			else{
				this.pos = new IntVec3(nbt.getIntArray("pos"));
				this.home = null;
			}
			this.upgradingHistory = nbt.hasKey("upgrades")?nbt.getIntArray("upgrades"):null;
		}
	}
}
