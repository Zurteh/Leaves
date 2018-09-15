package me.forward.Leaves.AltManager;

import java.awt.Color;
import java.io.IOException;
import java.net.Proxy;

import org.lwjgl.input.Keyboard;

import com.mojang.authlib.Agent;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.Session;

public class Manager extends GuiScreen {
	   
    public GuiTextField character;
    public GuiTextField pw;
    public GuiScreen eventscreen;
   
    public Manager(GuiScreen event) {
        eventscreen = event;
    }
   
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.add(new GuiButton(1, width / 2 - 100, height / 4 + 96 + 12, "Log in"));
        this.buttonList.add(new GuiButton(2, width / 2 - 100, height / 4 + 120 + 12, "Cancel"));
        character = new GuiTextField(3, fontRendererObj, width / 2 - 100, 76, 200, 20);
        pw = new GuiTextField(4, fontRendererObj, width / 2 - 100, 116, 200, 20);
        character.setMaxStringLength(50);
        pw.setMaxStringLength(50);
    }
   
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
   
    public void updateScreen() {
        character.updateCursorCounter();
        pw.updateCursorCounter();
    }
   
    public void mouseClicked(int x, int y, int m) {
        character.mouseClicked(x, y, m);
        pw.mouseClicked(x, y, m);
        try {
            super.mouseClicked(x, y, m);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    protected void keyTyped(char c, int I) {
        character.textboxKeyTyped(c, I);
        pw.textboxKeyTyped(c, I);
       
        if(c == '\t') {
            if(character.isFocused()) {
                 character.setFocused(false);
                 pw.setFocused(true);
            }else{
                character.setFocused(true);
                pw.setFocused(false);
            }
        }
       
        if(c == '\r') {
            try {
                actionPerformed((GuiButton)buttonList.get(0));
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
   
    public static Color rainbow(float offset) {
              float hue = ((float) System.nanoTime() + offset) / 1.0E10F % 8.0F;
              long color = Long.parseLong(Integer.toHexString(Integer.valueOf(Color.HSBtoRGB(hue, 1.0F, 1.001F)).intValue()),16);
              Color c = new Color((int) color);
              return new Color(c.getRed() / 255.0F, c.getGreen() / 255.0F, c.getBlue() / 255.0F, c.getAlpha() / 255.0F);
    }
   
    public String status;
   
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if(status != null)
         drawString(fontRendererObj, "" + status, 0, 0, 0xFFFFFF);
         drawDefaultBackground();
         character.drawTextBox();
         pw.drawTextBox();
         super.drawScreen(mouseX, mouseY, partialTicks);
    }
   
    protected void actionPerformed(GuiButton button) throws IOException {
        if(button.id == 2) {
            mc.displayGuiScreen(eventscreen);
        }else{
            if(pw.getText().trim().isEmpty()) {
                if(!character.getText().trim().isEmpty()) {
                    mc.session = new Session (character.getText().trim(), "-", "-", "Legacy");
                    status = "Logged In";
                }else{
                    status = "Failed";
            }
        }else{
            if(character.getText().trim().isEmpty());
            YggdrasilUserAuthentication a = (YggdrasilUserAuthentication) new YggdrasilAuthenticationService(Proxy.NO_PROXY, "").createUserAuthentication(Agent.MINECRAFT);
            a.setUsername(character.getText().trim());
            a.setPassword(pw.getText().trim());
           
            try {
                a.logIn();
                mc.session = new Session(a.getSelectedProfile().getName(), a.getSelectedProfile().getId().toString(), a.getAuthenticatedToken(), "mojang");
        }catch (Exception e) {
           
            }
        }
        }
    }

}
