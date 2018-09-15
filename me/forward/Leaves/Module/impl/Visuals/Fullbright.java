package me.forward.Leaves.Module.impl.Visuals;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class Fullbright extends Module {

	public Fullbright() {
		super("Fullbright", "Fullbright", Keyboard.KEY_P, Category.VISUALS);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate e) {
		mc.gameSettings.gammaSetting = 100F;
		if((mc.theWorld !=null) && (mc.thePlayer !=null)) {
			mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2125));
		}
	}
	
	@Override
	public void onEnable() {
		EventManager.register(this);
		if((mc.thePlayer !=null) && (mc.theWorld !=null)) {
			mc.thePlayer.addPotionEffect(new PotionEffect(Potion.nightVision.id, 2125));
			mc.gameSettings.gammaSetting = 100F;
		}
	}
	
	@Override
	public void onDisable() {
		EventManager.unregister(this);
		if((mc.thePlayer !=null) && (mc.theWorld !=null)) {
			mc.thePlayer.removePotionEffect(Potion.nightVision.id);
			mc.gameSettings.gammaSetting = 1.0F;
		}
	}

}
