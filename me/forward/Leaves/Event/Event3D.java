package me.forward.Leaves.Event;

import com.darkmagician6.eventapi.events.Event;

public class Event3D implements Event{
	private float partialticks;
	
	public Event3D(float partialticks) {
		this.partialticks = partialticks;
	}

	public float getPartialticks() {
		return partialticks;
	}
	
	
}
