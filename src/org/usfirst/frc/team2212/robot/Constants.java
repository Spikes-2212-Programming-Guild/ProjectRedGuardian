package org.usfirst.frc.team2212.robot;

public class Constants {
	public static final double MAX_SPEED = 1;
	public static final double CAMERA_WIDTH = 640;
	public static double setpoint = 1;
	public static double KP, KI, KD;

	public static double getTurnSpeed(double position) {
		return MAX_SPEED * (position - 0.5);
	}

}
