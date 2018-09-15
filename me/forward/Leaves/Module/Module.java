package me.forward.Leaves.Module;

import net.minecraft.client.Minecraft;

public class Module {
	
	private String name, displayname;
	private int keyBind;
	private Category category;
	public boolean visable;
	public static boolean colormode = false;
	public static Minecraft mc = Minecraft.getMinecraft();
	
	private boolean toggled;
	
	public Module(String name, String displayname, int keyBind, Category category) {
		this.name = name;
		this.displayname = displayname;
		this.keyBind = keyBind;
		this.category = category;
		this.visable = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public int getKeyBind() {
		return keyBind;
	}

	public void setKeyBind(int keyBind) {
		this.keyBind = keyBind;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public boolean isCategory(Category category) {
		return this.category == category;
	}
	
	public boolean isEnabled() {
		return toggled;
	}
	
	public void toggle() {
		if(toggled) {
			toggled = false;
			onDisable();
		}else {
			toggled = true;
			onEnable();
		}
	}
	
	public void onEnable() {}
	public void onDisable() {}

}
