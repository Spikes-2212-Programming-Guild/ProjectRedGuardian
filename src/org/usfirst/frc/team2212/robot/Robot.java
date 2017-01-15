package org.usfirst.frc.team2212.robot;

import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;
import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;
import com.spikes2212.dashboard.DashBoardController;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
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
	Command autonomousCommand;
	SendableChooser chooser;
	public static DashBoardController dashboard;
	public static final String center = "center";

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		dashboard = new DashBoardController();
		drivetrain = new Drivetrain(RobotMap.FRONT_LEFT_PORT, RobotMap.FRONT_RIGHT_PORT, RobotMap.REAR_LEFT_PORT,
				RobotMap.REAR_RIGHT_PORT);
		SmartDashboard.putData("Turn Right", new DriveTank(drivetrain, -0.3, 0.3));
		SmartDashboard.putData("Turn Left", new DriveTank(drivetrain, 0.3, -0.3));
		// chooser.addObject("My Auto", new MyAutoCommand());

		// SmartDashboard.putDouble("KP",0);
		// SmartDashboard.putDouble("KI",0);
		// SmartDashboard.putDouble("KD",0);
		dashboard.addDouble(center, Constants.center);
		dashboard.addBoolean("isRight", () -> SmartDashboard.getNumber(center, 0) > 0);
		dashboard.addBoolean("isLeft", () -> SmartDashboard.getNumber(center, 0) < 0);
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
		
		dashboard.addDouble("right Source", Constants.rightSource::pidGet);
		dashboard.addDouble("left Source", Constants.leftSource::pidGet);
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
		SmartDashboard.putData(new DriveTankWithPID(drivetrain, Constants.leftSource, Constants.rightSource, 0, 0,
				Constants.KP.get(), Constants.KI.get(), Constants.KD.get(), 0.05));
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
