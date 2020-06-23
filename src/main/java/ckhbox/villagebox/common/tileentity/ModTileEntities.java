//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox.common.tileentity;

import ckhbox.villagebox.common.tileentity.totem.TileEntityFireTotem;
import ckhbox.villagebox.common.tileentity.totem.TileEntityNatureTotem;
import ckhbox.villagebox.common.tileentity.totem.TileEntityWaterTotem;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ModTileEntities {

    public static void init() {
        GameRegistry.registerTileEntity(TileEntityFireTotem.class, "fire_totem");
        GameRegistry.registerTileEntity(TileEntityWaterTotem.class, "water_totem");
        GameRegistry.registerTileEntity(TileEntityNatureTotem.class, "nature_totem");
    }
}
