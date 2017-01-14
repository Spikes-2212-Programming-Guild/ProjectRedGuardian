package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	public static final double MAX_SPEED = 1;
	public static final double CAMERA_WIDTH = 640;
	public static double setpoint = 1;
	public static NetworkTable cameraInfo = NetworkTable.getTable("ImageProcessing");
	public static Supplier<Double> KP = ConstantHandler.addConstantDouble("KP", 1);
	public static Supplier<Double> KI = ConstantHandler.addConstantDouble("KI", 1);
	public static Supplier<Double> KD = ConstantHandler.addConstantDouble("KD", 1);
	public static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {

		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return cameraInfo.getNumber("center", 0.5) - 0.5;
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}
	};
	public static PIDSource rightSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {

		}

		@Override
		public double pidGet() {
			// TODO Auto-generated method stub
			return -(cameraInfo.getNumber("center", 0.5) - 0.5);
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			// TODO Auto-generated method stub
			return PIDSourceType.kDisplacement;
		}
	};

}
