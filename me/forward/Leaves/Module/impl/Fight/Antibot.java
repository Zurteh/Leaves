package me.forward.Leaves.Module.impl.Fight;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;
import net.minecraft.entity.Entity;

public class Antibot extends Module {

	public Antibot() {
		super("Antibot", "Antibot", Keyboard.KEY_K, Category.FIGHT);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if(!this.isEnabled()) {
			return;
		}
		
		for(Object entity : mc.theWorld.loadedEntityList)
			if(((Entity)entity).isInvisible() && entity != mc.thePlayer) {
				mc.theWorld.removeEntity((Entity)entity);
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
