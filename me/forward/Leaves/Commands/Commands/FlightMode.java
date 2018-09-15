package me.forward.Leaves.Commands.Commands;

import me.forward.Leaves.Leaves;
import me.forward.Leaves.Commands.Command;
import me.forward.Leaves.Module.impl.Move.Flight;

public class FlightMode extends Command {
	
	public FlightMode FlightMode = this;

	public FlightMode() {
		super("Flightmode", "Changes the flight");
	}

	@Override
	public void execute(String[] args) {
		if(args.length != 1) {
			messageWithPrefix(" §cSyntax Error, try: §f " + Leaves.instance.commandmanager.Chat_Prefix + " flightmode <mode> / <list>.");
			return;
		}else {
			if(args[0].equalsIgnoreCase("Vanilla") || args[0].equalsIgnoreCase("Hypixel") || args[0].equalsIgnoreCase("AAC")) {
				Flight.mode = args[0];
				messageWithPrefix(" Set Flight mode to §9 " + args[0]);
			}else {
				messageWithoutPrefix("§cUnkown Mode.");
			}
			
			if(args[0].equalsIgnoreCase("list")) {
				messageWithPrefix(" §9Vanilla & Hypixel");
			}
		}
		
	}

}
