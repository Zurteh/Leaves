package me.forward.Leaves.Module;

import java.util.ArrayList;
import java.util.List;

import me.forward.Leaves.Leaves;
import me.forward.Leaves.Module.impl.Fight.*;
import me.forward.Leaves.Module.impl.Move.*;
import me.forward.Leaves.Module.impl.Player.*;
import me.forward.Leaves.Module.impl.Visuals.*;

public class ModuleManager {
	
	public List<Module> modules = new ArrayList<Module>();
	
	public ModuleManager() {
		addModule(new Step());
		addModule(new Sprint());
		addModule(new Fullbright());
		addModule(new Antibot());
		addModule(new Flight());
		addModule(new NoFall());
		addModule(new ChestStealer());
		addModule(new Killaura());
		addModule(new Scaffold());
		Leaves.instance.logger.Info("Loaded Modules: " + modules.size());
	}
	
	public void addModule(Module module) {
		modules.add(module);
		Leaves.instance.logger.Loading("Module: " + module.getName());
	}
	
	public List<Module> getModules() {
		return modules;
	}
	
	public Module getModuleByName(String moduleName) {
		for(Module mod : modules) {
			if((mod.getName().trim().equalsIgnoreCase(moduleName)) || (mod.toString().trim().equalsIgnoreCase(moduleName.trim()))) {
				return mod;
			}
		}
		return null;
	}
	
	public Module getModule(Class <? extends Module> clazz) {
		for(Module mod : modules) {
			if(mod.getClass() == clazz) {
				return mod;
			}
		}
		return null;
	}

}
