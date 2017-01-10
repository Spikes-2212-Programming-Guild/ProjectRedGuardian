package org.usfirst.frc.team2212.robot.subsystems;

import com.spikes2212.genericsubsystems.drivetrains.TankDrivetrain;
import com.spikes2212.utils.DoubleSpeedcontroller;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

public class Drivetrain extends TankDrivetrain {

	private SpeedController left, right;
	private Encoder leftEncoder, rightEncoder;

	public Drivetrain(SpeedController left, SpeedController right, Encoder leftEncoder,
			Encoder rightEncoder) {
		super();
		this.left = left;
		this.right = right;
		this.leftEncoder = leftEncoder;
		this.rightEncoder = rightEncoder;
	}

	public Drivetrain(int frontLeftPort, int frontRightPort, int rearLeftPort, int rearRightPort,
			int leftEncoderChannelA, int leftEncoderChannelB, int rightEncoderChannelA, int rightEncoderChannelB) {
		this.left = new DoubleSpeedcontroller(new VictorSP(frontLeftPort), new VictorSP(rearLeftPort));
		this.left = new DoubleSpeedcontroller(new VictorSP(frontRightPort), new VictorSP(rearRightPort));
		this.leftEncoder = new Encoder(leftEncoderChannelA, leftEncoderChannelB);
		this.rightEncoder = new Encoder(rightEncoderChannelA, rightEncoderChannelB);
	}
	
	public void restEncoders(){
		leftEncoder.reset();
		rightEncoder.reset();
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
		return leftEncoder;
	}

	@Override
	public PIDSource getRightPIDSource() {
		// TODO Auto-generated method stub
		return rightEncoder;
	}

}
