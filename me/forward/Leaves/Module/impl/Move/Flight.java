package me.forward.Leaves.Module.impl.Move;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Leaves;
import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Flight extends Module {
	
	public static float FlyHackSpeed = 0.1F;
	public static String mode = "Vanilla";

	public Flight() {
		super("Flight", "Flight", Keyboard.KEY_F, Category.MOVE);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		this.setDisplayname("Flight");
		if(!this.isEnabled()) {
			return;
		}
		
		if(mode.equalsIgnoreCase("Vanilla")) {
			this.setDisplayname("Flight §7Vanilla");
			Vanilla();
		}
		
		if(mode.equalsIgnoreCase("Hypixel")) {
			this.setDisplayname("Flight §7Hypixel");
			Hypixel();
		}
		
		if(mode.equalsIgnoreCase("aac")) {
			this.setDisplayname("Flight §7AAC");
			aac();
		}
	}
	
	public void Vanilla() {
		if(!this.isEnabled()) {
			return;
		}
		mc.thePlayer.capabilities.isFlying = true;
		if(mc.gameSettings.keyBindSneak.isPressed()) {
			mc.thePlayer.motionY -= 0.2;
		}
		
		if(mc.gameSettings.keyBindForward.isPressed()) {
			mc.thePlayer.capabilities.setFlySpeed(FlyHackSpeed);
		}
		
		if(mc.gameSettings.keyBindJump.isPressed()) {
			mc.thePlayer.motionY += 0.2;
		}
	}
	
	public void Hypixel() {
		if(!this.isEnabled()) {
			return;
		}
		
		double y;
		double yl;
		mc.thePlayer.motionY = 0;
		if(mc.thePlayer.ticksExisted % 3 == 0) {
			y = mc.thePlayer.posY - 1.0E-10D;
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, y, mc.thePlayer.posZ, true));
		}
		
		yl = mc.thePlayer.posY + 1.0E-10D;
		mc.thePlayer.setPosition(mc.thePlayer.posX, yl, mc.thePlayer.posZ);
	}
	
	public void aac() {
		if(!this.isEnabled()) {
			return;
		}
		if((mc.gameSettings.keyBindForward.isPressed()) || 
				(mc.gameSettings.keyBindBack.isPressed()) || 
				(mc.gameSettings.keyBindLeft.isPressed()) || 
				(mc.gameSettings.keyBindRight.isPressed()) || 
				(!mc.gameSettings.keyBindSneak.isPressed()) || 
				(!mc.thePlayer.isOnLadder())) {
				mc.thePlayer.jump();
		}
		
		if(Leaves.instance.moduleManager.getModuleByName("Sprint").isEnabled()) {
			toggle();
		}
	}
	
	@Override
	public void onEnable() {
		EventManager.register(this);
	}
	
	@Override
	public void onDisable() {
		EventManager.unregister(this);
		mc.thePlayer.capabilities.isFlying = false;
	}

}
