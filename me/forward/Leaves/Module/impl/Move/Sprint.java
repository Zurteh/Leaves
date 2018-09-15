package me.forward.Leaves.Module.impl.Move;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;

public class Sprint extends Module{

	public Sprint() {
		super("Sprint", "Sprint", Keyboard.KEY_I, Category.MOVE);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if((mc.thePlayer.moveForward > 0) && (mc.thePlayer.getFoodStats().getFoodLevel() > 6)) {
			mc.thePlayer.setSprinting(true);
		}
	}
	
	@Override
	public void onEnable() {
		EventManager.register(this);
	}
	
	@Override
	public void onDisable() {
		mc.thePlayer.setSprinting(false);
		EventManager.unregister(this);
	}

}
