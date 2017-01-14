package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import org.usfirst.frc.team2212.robot.commands.KeepDistanceConstantCM;
import org.usfirst.frc.team2212.robot.commands.KeepDistanceConstantPixel;
import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static Drivetrain drivetrain;
	public static OI oi;
	NetworkTable cameraInfo;
	Command autonomousCommand;
	SendableChooser chooser;
	com.spikes2212.dashboard.DashBoardController dashboard;
	public static double DistanceCM,widthPixel;
	public static final String center = "center";

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		drivetrain = new Drivetrain(RobotMap.FRONT_LEFT_PORT, RobotMap.FRONT_RIGHT_PORT, RobotMap.REAR_LEFT_PORT,
				RobotMap.REAR_RIGHT_PORT);

		// chooser.addObject("My Auto", new MyAutoCommand());

		// SmartDashboard.putDouble("KP",0);
		// SmartDashboard.putDouble("KI",0);
		// SmartDashboard.putDouble("KD",0);

		cameraInfo = NetworkTable.getTable("ImageProcessing");
		dashboard = new com.spikes2212.dashboard.DashBoardController();
		dashboard.addDouble(center,
				() -> (cameraInfo.getNumber("x", 0) + 0.5 * cameraInfo.getNumber("width", 0)) / Constants.CAMERA_WIDTH);
		dashboard.addBoolean("isRight", () -> SmartDashboard.getNumber(center, 0) > 0.5);
		dashboard.addBoolean("isLeft", () -> SmartDashboard.getNumber(center, 0) < 0.5);
		dashboard.addDouble("TurnSpeed", () -> Constants.getTurnSpeed(SmartDashboard.getNumber(center, 0.5)));
		ConstantHandler.addConstantDouble("DistanceCM", DistanceCM);
		ConstantHandler.addConstantDouble("DistancePixel", widthPixel);
		oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {
		// Constants.KP=SmartDashboard.getDouble("KP");
		// Constants.KI=SmartDashboard.getDouble("KI");
		// Constants.KD=SmartDashboard.getDouble("KD");
		SmartDashboard.putData(new DriveTank(drivetrain, () -> SmartDashboard.getNumber("TurnSpeed", 0),
				() -> -SmartDashboard.getNumber("TurnSpeed", 0)));
		SmartDashboard.putData(new KeepDistanceConstantCM(drivetrain, cameraInfo, DistanceCM));
		SmartDashboard.putData(new KeepDistanceConstantPixel(drivetrain, cameraInfo, widthPixel));
	}

	public void disabledPeriodic() {
		dashboard.update();
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.

		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		// RobotMap.Commands.pidForward.setP(SmartDashboard.getDouble("KP"));
		// RobotMap.Commands.pidForward.setI(SmartDashboard.getDouble("KI"));
		// RobotMap.Commands.pidForward.setD(SmartDashboard.getDouble("KD"));
		dashboard.update();
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
