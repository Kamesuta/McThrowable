package com.kamesuta.mc.throwable;

import com.kamesuta.mc.throwable.thrownup.EntityThrownUp;
import com.kamesuta.mc.throwable.thrownup.ItemThrownUp;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

@Mod(modid = Reference.MODID, name = Reference.NAME, version = Reference.VERSION)
public class McThrowable {
	@Instance(Reference.MODID)
	public static McThrowable instance;

	public static Item thrownup;

	@EventHandler
	public void init(FMLInitializationEvent event) {
		thrownup = new ItemThrownUp().setCreativeTab(CreativeTabs.tabTools).setUnlocalizedName("itemThrownUp")
				.setTextureName(Reference.MODID + ":itemThrownUp").setMaxStackSize(16)
				.setCreativeTab(CreativeTabs.tabMisc);
		GameRegistry.registerItem(thrownup, "itemThrownUp");
		EntityRegistry.registerModEntity(EntityThrownUp.class, "entityThrownUp", 1, this, 250, 5, true);
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownUp.class, new RenderSnowball(thrownup));

		GameRegistry.addShapelessRecipe(new ItemStack(thrownup,4),
				new ItemStack (Items.ender_pearl),
				new ItemStack (Items.slime_ball)
		 );
	}
}
