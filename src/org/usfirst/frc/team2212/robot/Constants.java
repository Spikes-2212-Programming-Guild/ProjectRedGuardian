package org.usfirst.frc.team2212.robot;

public class Constants {
	public static final double MAX_SPEED = 0.5;
	public static final double CAMERA_WIDTH=320;
	public static double setpoint=1;
	public static double KP,KI,KD;
	
	public static double getTurnSpeed(double position) {
		if(position>0)
			return MAX_SPEED*position;
		return -MAX_SPEED*position;
	}

}
