package me.forward.Leaves.Event;

import com.darkmagician6.eventapi.events.Event;

public class EventPreMotionUpdate implements Event{
	private float yaw, pitch;
	private boolean ground;
	private double x, y, z;
	
	public EventPreMotionUpdate(float yaw, float pitch, boolean ground, double x, double y, double z) {
		this.pitch = pitch;
		this.yaw = yaw;
		this.ground = ground;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public float getYaw() {
		return yaw;
	}

	public void setYaw(float yaw) {
		this.yaw = yaw;
	}

	public float getPitch() {
		return pitch;
	}

	public void setPitch(float pitch) {
		this.pitch = pitch;
	}

	public boolean onGround() {
		return ground;
	}

	public void setGround(boolean ground) {
		this.ground = ground;
	}
	
	
	
}

