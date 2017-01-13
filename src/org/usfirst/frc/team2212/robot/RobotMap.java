package org.usfirst.frc.team2212.robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	public static final int FRONT_LEFT_PORT = 2;
	public static final int REAR_LEFT_PORT = 1;
	public static final int FRONT_RIGHT_PORT = 9;
	public static final int REAR_RIGHT_PORT = 8;

	public interface Commands {
	}
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
