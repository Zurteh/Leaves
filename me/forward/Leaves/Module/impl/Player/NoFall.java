package me.forward.Leaves.Module.impl.Player;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;
import net.minecraft.network.play.client.C03PacketPlayer;

public class NoFall extends Module {

	public NoFall() {
		super("NoFall", "NoFall", Keyboard.KEY_M, Category.PLAYER);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if(mc.thePlayer.fallDistance > 2F) {
			mc.thePlayer.sendQueue.addToSendQueue(new C03PacketPlayer(true));
		}
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
