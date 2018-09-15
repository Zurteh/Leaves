package me.forward.Leaves.Module.impl.Player;

import org.lwjgl.input.Keyboard;

import com.darkmagician6.eventapi.EventManager;
import com.darkmagician6.eventapi.EventTarget;

import me.forward.Leaves.Event.EventUpdate;
import me.forward.Leaves.Module.Category;
import me.forward.Leaves.Module.Module;
import me.forward.Leaves.utils.TimeHelper;
import net.minecraft.inventory.ContainerChest;

public class ChestStealer extends Module {
	
	TimeHelper time = new TimeHelper();

	public ChestStealer() {
		super("ChestStealer", "ChestSteal", Keyboard.KEY_N, Category.PLAYER);
	}
	
	@EventTarget
	public void onUpdate(EventUpdate event) {
		if((mc.thePlayer.openContainer != null) && ((mc.thePlayer.openContainer instanceof ContainerChest))) {
			ContainerChest chest = (ContainerChest) mc.thePlayer.openContainer;
			for(int i = 0; i < chest.getLowerChestInventory().getSizeInventory(); i++) {
				if((chest.getLowerChestInventory().getStackInSlot(i) !=null) && (time.hasReached(40L))) {
					mc.playerController.windowClick(chest.windowId, i, 0, 1, mc.thePlayer);
					time.reset();
				}
			}
			
			if(chest.getInventory().isEmpty()) {
				this.mc.displayGuiScreen(null);
			}
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
