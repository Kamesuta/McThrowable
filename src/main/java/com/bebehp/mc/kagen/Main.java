package com.bebehp.mc.kagen;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class Main {
	@Instance(Reference.MODID)
	public static Main instance;

	public static Item casino;

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		casino = new Casino(11)
				.setUnlocalizedName("CASINO")
				.setTextureName(Reference.MODID + ":itemCASINO")
				.setMaxStackSize(64);
		//				.setCreativeTab(CreativeTabs.tabMisc);
		GameRegistry.registerItem(casino, "casino");

	}
}
