package com.ckhgame.villagebento.util.village;

import java.util.ArrayList;

import com.ckhgame.villagebento.config.ConfigBuilding;
import com.ckhgame.villagebento.util.data.Vec3Int;

import net.minecraft.block.Block;
import net.minecraft.world.World;

public class BlockFinder {
	
	public static Vec3Int[] findBlock(World world, int buildingX, int buildingY, int buildingZ, int buildingSizeX, int buildingSizeZ, Block block, int[] metadatas, boolean onlyFirst){
		int x1 = buildingX - buildingSizeX;
		int x2 = buildingX + buildingSizeX;
		int z1 = buildingZ - buildingSizeZ;
		int z2 = buildingZ + buildingSizeZ;
		int y1 = buildingY - ConfigBuilding.BuildingGroundWorkDepth;
		int y2 = buildingY + ConfigBuilding.BuildingMaxHeight - 1;
		return findBlock(world,x1,x2,y1,y2,z1,z2,block,metadatas,onlyFirst);
	}
	
	public static Vec3Int[] findBlock(World world,int x1,int x2,int y1,int y2,int z1,int z2, Block block, int[] metadatas, boolean onlyFirst){
		
		if(block == null || world == null || metadatas == null)
			return null;
		
		ArrayList<Vec3Int> list = new ArrayList<Vec3Int>();
		
		for(int y = y1;y <= y2;y++){ // from bottom to top
			for(int x = x1;x <= x2;x++){
				for(int z = z1;z <= z2;z++){
					if(world.getBlock(x, y, z) == block){
						int metadata = world.getBlockMetadata(x, y, z);
						for(int m : metadatas){
							if(m == metadata){
								if(onlyFirst)
									return new Vec3Int[]{Vec3Int.create(x, y, z)};
								else
									list.add(Vec3Int.create(x, y, z));
							}
						}
					}
				}
			}
		}
		
		if(list.size() == 0)
			return null;
		else
			return list.toArray(new Vec3Int[list.size()]);
	}
}
