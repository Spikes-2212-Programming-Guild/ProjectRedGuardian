package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class Drivetrain extends TankDrivetrain {

	private SpeedController left, right;

	public Drivetrain(SpeedController left, SpeedController right) {
		super();
		this.left = left;
		this.right = right;
	}

	public Drivetrain(int frontLeftPort, int frontRightPort, int rearLeftPort, int rearRightPort) {
		this(new DoubleSpeedcontroller(new VictorSP(frontLeftPort), new VictorSP(rearLeftPort)),
				new DoubleSpeedcontroller(new VictorSP(frontRightPort), new VictorSP(rearRightPort)));
	}

	@Override
	protected void initDefaultCommand() {

	}

	@Override
	public void setLeft(double speedLeft) {
		left.set(speedLeft);
	}

	@Override
	public void setRight(double speedRight) {
		right.set(-speedRight);
	}

	@Override
	public PIDSource getLeftPIDSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PIDSource getRightPIDSource() {
		// TODO Auto-generated method stub
		return null;
	}

}
