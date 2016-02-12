package com.kamesuta.mc.throwable.thrownup;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityThrowable;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

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
        if (p_70184_1_.entityHit instanceof EntityLivingBase)
        {
        	p_70184_1_.entityHit.setPosition(this.posX, this.posY + 10D, this.posZ);
        }

		for (int i = 0; i < 32; ++i) {
			this.worldObj.spawnParticle("portal", this.posX, this.posY + this.rand.nextDouble() * 2.0D, this.posZ,
					this.rand.nextGaussian(), 0.0D, this.rand.nextGaussian());
		}

        if (!this.worldObj.isRemote)
        {
            this.setDead();
        }

		if (p_70184_1_.entityHit != null) {
			p_70184_1_.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 0.0F);
		}
	}
}