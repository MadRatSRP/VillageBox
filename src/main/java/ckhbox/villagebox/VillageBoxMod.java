//  This is an open source non-commercial project. Dear PVS-Studio, please check it.
//  PVS-Studio Static Code Analyzer for C, C++, C#, and Java: http://www.viva64.com

package ckhbox.villagebox;

import ckhbox.villagebox.common.CommonProxy;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = VillageBoxMod.MODID, name = VillageBoxMod.MODNAME, version = VillageBoxMod.VERSION)
public class VillageBoxMod {

    public static final String MODID = "villagebox";
    public static final String MODNAME = "Village Box";
    public static final String VERSION = "0.6.0";

    @Instance
    public static VillageBoxMod instance = new VillageBoxMod();

    @SidedProxy(clientSide = "ckhbox.villagebox.client.ClientProxy", serverSide = "ckhbox.villagebox.common.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        this.proxy.preInit(e);
    }

    @EventHandler
    public void init(FMLInitializationEvent e) {
        this.proxy.init(e);
    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        this.proxy.postInit(e);
    }
}
