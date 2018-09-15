package me.forward.Leaves.Commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.forward.Leaves.Leaves;
import me.forward.Leaves.Commands.Commands.*;

public class CommandManager {
	
private List<Command> commands = new ArrayList();
	
	public String Chat_Prefix = ".";
	
	public CommandManager() {
		addCommand(new FlightMode());
		addCommand(new Toggle());
	}
	
	public void addCommand(Command cmd) {
		this.commands.add(cmd);
		Leaves.instance.logger.Loading("Commands: " + cmd.getName());
	}
	
	public boolean execute(String text) {
		if(!text.startsWith(Chat_Prefix)) {
			return false;
		}
		text = text.substring(1);
		
		String[] arguments = text.split(" ");
			for(Command cmd : this.commands) {
				if(cmd.getName().equalsIgnoreCase(arguments[0])) {
					String[] args = (String[]) Arrays.copyOfRange(arguments, 1, arguments.length);
					cmd.execute(args);
					return true;
					
				}
			}
			return false;
			
		
	}

}
