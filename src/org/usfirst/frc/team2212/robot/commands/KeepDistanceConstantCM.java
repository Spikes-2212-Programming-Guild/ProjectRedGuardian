package org.usfirst.frc.team2212.robot.commands;

import org.usfirst.frc.team2212.robot.Constants;
import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class KeepDistanceConstantCM extends CommandGroup {
	private static final int WIDTH_PIXEL = 640;
	private static final double OBJECT_WIDTH_CM = 68.3;
	private static final double VIEW_ANGLE = 61;
	private static double widthCentemters, distance;

	public KeepDistanceConstantCM(Drivetrain drivetrain, NetworkTable networkTables, double wantedDistance) {
		widthCentemters = (OBJECT_WIDTH_CM * WIDTH_PIXEL) / networkTables.getNumber("width", 0);
		distance = (0.5 * widthCentemters) / Math.tan(VIEW_ANGLE / 2);
		addSequential(new DriveTankWithPID(distance - wantedDistance, Constants.KPS.get(), Constants.KIS.get(),
				Constants.KDS.get(), drivetrain, Constants.TOLERANCE.get()));
	}
}
