package me.forward.Leaves.Commands.Commands;

import me.forward.Leaves.Leaves;
import me.forward.Leaves.Commands.Command;
import me.forward.Leaves.Module.Module;

public class Toggle extends Command{
		
		public Toggle toggle = this;

		public Toggle() {
			super("toggle", "Toggles the module that you put.");
		}

		@Override
		public void execute(String[] args) {
			if(args.length != 1) {
				messageWithPrefix(" " + Leaves.instance.commandmanager.Chat_Prefix + "§6toggle §6<Module> §f");
				return;
			}
			
			String module = args[0];
			if(module.equalsIgnoreCase("usual")) {
				for(Module m : Leaves.instance.moduleManager.getModules()) {
					if(m.isEnabled()) {
						
					}
				}
				messageWithPrefix("Enabled toggle setup");
				return;
			}
			try
			{
				Leaves.instance.moduleManager.getModuleByName(module).toggle();
				messageWithPrefix(" " + Leaves.instance.moduleManager.getModuleByName(module).getDisplayname() + " §6was " + (Leaves.instance.moduleManager.getModuleByName(module).isEnabled() ? "§6enabled" : "§6disabled"));
						
			}
			catch(Exception e) {
				messageWithPrefix(" §cInvalid Module");
			}
		}
	}
