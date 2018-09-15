package me.forward.Leaves.Commands;

import me.forward.Leaves.Leaves;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;

public abstract class Command {
	
	private String name;
	private String description;
	private Minecraft mc;
	
	public Command(String name, String description) {
		this.name =  name;
		this.description = description;
	}
	
	public abstract void execute(String[] args);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	} 
	
	public static void messageWithoutPrefix(String msg) {
		Object chat = new ChatComponentText(msg);
		if(msg != null) {
			Minecraft.getMinecraft().ingameGUI.getChatGUI().printChatMessage((IChatComponent)chat);
		}
	}
	
	public static void messageWithPrefix(String msg) {
		messageWithoutPrefix(Leaves.instance.Client_Prefix + msg);
	}
	

}
