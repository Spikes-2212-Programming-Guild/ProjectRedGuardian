package org.usfirst.frc.team2212.robot;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTankWithPID;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick joy;
	JoystickButton forward;
	public OI() {
		joy= new Joystick(0);
		forward= new JoystickButton(joy, 2);
		forward.whenPressed(RobotMap.Commands.pidForward);
	}
}

