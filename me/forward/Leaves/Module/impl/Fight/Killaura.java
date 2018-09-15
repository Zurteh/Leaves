package me.forward.Leaves.Module.impl.Fight;

import java.util.Collection;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.util.MathHelper;

public class Killaura extends Module {
	
	public float yaw;
	public float pitch;

	public Killaura() {
		super("Killaura", "Killaura", Keyboard.KEY_R, Category.FIGHT);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		for(Object e : mc.theWorld.loadedEntityList) {
			if(e instanceof EntityLivingBase) {
				if(e != mc.thePlayer) {
					EntityLivingBase p = (EntityLivingBase) e;
					
					//hits if enttiy is real

					if(mc.thePlayer.getDistanceToEntity(p) <= 4.2D && p.isEntityAlive() && p.hurtTime < 1 && !(p.getHealth() < 0 ) && (checkEntityID(p)) && (!p.isInvisible())) {
						
						
						float[] rotations = getRotationsNeeded(p);
						
						mc.playerController.attackEntity(mc.thePlayer, p);
						
						mc.thePlayer.swingItem();
						mc.thePlayer.swingItem();
						mc.thePlayer.swingItem();
						
					}
				}
			}
		}
		
		
			
	}
	
	public boolean checkEntityID(Entity entity) {
        boolean check;
        if (!(entity.getEntityId() > 1070000000 || entity.getEntityId() <= -1)) {
            check = true;
        } else {
            check = false;
        }
 
        return check;
    }
	
	public float[] getRotationsNeeded(Entity entity) {
        if (entity == null)
            return null;
        double diffX = entity.posX - Minecraft.getMinecraft().thePlayer.posX;
        double diffY;
        if ((entity instanceof EntityLivingBase)) {
            EntityLivingBase entityLivingBase = (EntityLivingBase) entity;
            diffY = entityLivingBase.posY + entityLivingBase.getEyeHeight() * 0.9D - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
        } else {
            diffY = (entity.boundingBox.minY + entity.boundingBox.maxY) / 2.0D - (Minecraft.getMinecraft().thePlayer.posY + Minecraft.getMinecraft().thePlayer.getEyeHeight());
        }
        double diffZ = entity.posZ - Minecraft.getMinecraft().thePlayer.posZ;
        double dist = MathHelper.sqrt_double(diffX * diffX + diffZ * diffZ);
        float yaw = (float) (Math.atan2(diffZ, diffX) * 180.0D / 3.141592653589793D) - 90.0F;
        float pitch = (float) -(Math.atan2(diffY, dist) * 180.0D / 3.141592653589793D);
        return new float[] { Minecraft.getMinecraft().thePlayer.rotationYaw + MathHelper.wrapAngleTo180_float(yaw - Minecraft.getMinecraft().thePlayer.rotationYaw), Minecraft.getMinecraft().thePlayer.rotationPitch + MathHelper.wrapAngleTo180_float(pitch - Minecraft.getMinecraft().thePlayer.rotationPitch) };
    }
	
	public boolean isPlayerValid(EntityLivingBase player) {
        if (player instanceof EntityPlayer) {
            Collection<NetworkPlayerInfo> playerInfos = this.mc.thePlayer.sendQueue.func_175106_d();
            for (NetworkPlayerInfo info : playerInfos) {
                if (info.func_178845_a().getName().matches(player.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
	
	public void setRotations(float yaw, float pitch) {
		sendPacket(new C03PacketPlayer.C05PacketPlayerLook(yaw, pitch, true));
	}
	
	public void sendPacket(final Packet p) {
		mc.getNetHandler().addToSendQueue(p);
	}
	
	@Override
	public void onEnable() {
		EventManager.register(this);
	}
	
	@Override
	public void onDisable() {
		EventManager.unregister(this);

	}

}
