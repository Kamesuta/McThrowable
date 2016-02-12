package com.kamesuta.mc.throwable.thrownup;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;

public class EntityThrownUp extends EntityThrowable {
	public EntityThrownUp(World p_i1782_1_) {
		super(p_i1782_1_);
	}

	public EntityThrownUp(World p_i1783_1_, EntityLivingBase p_i1783_2_) {
		super(p_i1783_1_, p_i1783_2_);
	}

	@SideOnly(Side.CLIENT)
	public EntityThrownUp(World p_i1784_1_, double p_i1784_2_, double p_i1784_4_, double p_i1784_6_) {
		super(p_i1784_1_, p_i1784_2_, p_i1784_4_, p_i1784_6_);
	}

	/**
	 * Called when this EntityThrowable hits a block or entity.
	 */
	protected void onImpact(MovingObjectPosition p_70184_1_) {
		if (p_70184_1_.entityHit != null) {
			p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
		}

		for (int i = 0; i < 32; ++i) {
			this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ,
					this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}

		if (!this.worldObj.isRemote) {
			if (this.getThrower() != null && this.getThrower() instanceof EntityPlayerMP) {
				EntityPlayerMP entityplayermp = (EntityPlayerMP) this.getThrower();

				if (entityplayermp.playerNetServerHandler.func_147362_b().isChannelOpen()
						&& entityplayermp.worldObj == this.worldObj) {
					EnderTeleportEvent event = new EnderTeleportEvent(entityplayermp, this.posX, this.posY, this.posZ,
							5.0F);
					if (!MinecraftForge.EVENT_BUS.post(event)) { // Don't indent
																	// to lower
																	// patch
																	// size
						if (this.getThrower().isRiding()) {
							this.getThrower().mountEntity((Entity) null);
						}

						List<?> e = this.getThrower().worldObj
								.getEntitiesWithinAABB(EntityLivingBase.class,
										AxisAlignedBB
												.getBoundingBox(event.targetX, event.targetY, event.targetZ,
														event.targetX + 1D, event.targetY + 1D, event.targetZ + 1D)
												.expand(1D, 1D, 1D));
						for (Object o : e) {
							EntityLivingBase p = (EntityLivingBase) o;
							p.setPositionAndUpdate(event.targetX, event.targetY + 10D, event.targetZ);
						}
					}
				}
			}

			this.setDead();
		}
	}
}