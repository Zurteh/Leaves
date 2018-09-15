package me.forward.Leaves.Module.impl.Move;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;
import me.forward.Leaves.utils.TimeHelper;
import net.minecraft.network.play.client.C03PacketPlayer;

public class Step extends Module {

	public Step() {
		super("Step", "Step", Keyboard.KEY_X, Category.MOVE);
	}
	
	TimeHelper time1 = new TimeHelper();
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		if((mc.thePlayer.isCollidedHorizontally) && ((this.mc.gameSettings.keyBindForward.pressed) || (this.mc.gameSettings.keyBindBack.pressed) || (this.mc.gameSettings.keyBindRight.pressed) || (this.mc.gameSettings.keyBindLeft.pressed)) && (mc.thePlayer.onGround) && (!this.mc.thePlayer.isOnLadder())) {
			if(this.time1.isDelayComplete(57.0F)) {
				mc.thePlayer.stepHeight = 1.2F;
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.42D, mc.thePlayer.posZ, mc.thePlayer.onGround));
				mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer.C04PacketPlayerPosition(mc.thePlayer.posX, mc.thePlayer.posY + 0.753D, mc.thePlayer.posZ, mc.thePlayer.onGround));
				time1.reset();
			}
			
		}else {
			mc.timer.timerSpeed = 1F;
			mc.thePlayer.stepHeight = 0.5F;
		}
	}
	
	@Override
	public void onDisable() {
		EventManager.unregister(this);
	}
	
	@Override 
	public void onEnable() {
		EventManager.register(this);
	}

}
