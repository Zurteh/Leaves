package me.forward.Leaves.Event;

import com.darkmagician6.eventapi.events.Event;

public class Event2D implements Event{
	
	private float height, width;
	
	public Event2D(float height, float width) {
		this.width = width;
		this.height = height;
	}

	public float getHeight() {
		return height;
	}

	public float getWidth() {
		return width;
	}
	
	

}
