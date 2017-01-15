package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

public class Constants {
	public static final double MAX_SPEED = 1;
	public static final double CAMERA_WIDTH = 640;
	public static double setpoint = 1;
	public static double KP, KI, KD;
	public static final Supplier<Double> KPS = ConstantHandler.addConstantDouble("KP", 0.0);
	public static final Supplier<Double> KIS = ConstantHandler.addConstantDouble("KI", 0.0);
	public static final Supplier<Double> KDS = ConstantHandler.addConstantDouble("KD", 0.0);
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("Tolerance", 0.0);
	public static double getTurnSpeed(double position) {
		return MAX_SPEED * (position - 0.5);
	}

}
