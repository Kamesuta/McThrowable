package com.bebehp.mc.kagen;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class Casino extends Item {

	protected IIcon[] iicon;
	private final int type;

	public Casino (final int type) {
		super ();
		this.type = type;
		setCreativeTab(CreativeTabs.tabMisc);
		setHasSubtypes(true);
		setMaxDamage(0);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(final IIconRegister register) {
		this.iicon = new IIcon[this.type];
		for (int i = 0; i < this.type; i ++) {
			this.iicon[i] =  register.registerIcon(getIconString() + "-" + i);
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(final int meta) {
		return this.iicon[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(final Item item, final CreativeTabs creativeTab, final List list) {
		for (int i = 0; i < this.type; i ++) {
			list.add(new ItemStack(this, 1, i));
		}
	}

	@Override
	public int getMetadata(final int meta) {
		return meta;
	}

	@Override
	public String getUnlocalizedName(final ItemStack itemStack) {
		return this.getUnlocalizedName() + "." + itemStack.getItemDamage();
	}

}