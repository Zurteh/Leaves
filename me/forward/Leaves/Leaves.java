package me.forward.Leaves;

import java.io.File;

import org.lwjgl.opengl.Display;

import me.forward.Leaves.Commands.CommandManager;
import me.forward.Leaves.Module.ModuleManager;
import me.forward.Leaves.utils.Logger;
import net.minecraft.client.Minecraft;

public class Leaves {
	
	public static Leaves instance;
	public String Client_Name = "Leaves ";
	public String Client_Version = "1.0.0";
	public String Client_Coder = "Echo";
	public String Client_Prefix = "§7[§6Leaves§7]";
	public Logger logger;
	public ModuleManager moduleManager;
	public CommandManager commandmanager;
	
	public File directory;
	
	public void startClient() {
		instance = this;
		Display.setTitle("" + Client_Name + Client_Version + "");
		System.out.println("Client: " + Client_Name + "Version: " + Client_Version + " Created by: " + Client_Coder);
		directory = new File(Minecraft.getMinecraft().mcDataDir, Client_Name);
			if(!directory.exists()) {
				directory.mkdirs();
			}
		
		logger = new Logger();
		moduleManager = new ModuleManager();
		commandmanager = new CommandManager();
	}

}
 