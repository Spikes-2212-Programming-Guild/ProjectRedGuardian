package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class Constants {
	public static final double MAX_SPEED = 1;
	public static final double CAMERA_WIDTH = 640;
	public static NetworkTable cameraInfo = NetworkTable.getTable("ImageProcessing");
	public static Supplier<Double> KP = ConstantHandler.addConstantDouble("KP", 0.8);
	public static Supplier<Double> KI = ConstantHandler.addConstantDouble("KI", 0.0);
	public static Supplier<Double> KD = ConstantHandler.addConstantDouble("KD", 0.65);
	public static Supplier<Double> center = () -> (((Constants.cameraInfo.getNumber("x", 0)
			+ 0.5 * Constants.cameraInfo.getNumber("width", 0)) / CAMERA_WIDTH) - 0.5);
	public static Supplier<Double> tolerance = ConstantHandler.addConstantDouble("Tolerance", 0);
	public static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return center.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};
	public static PIDSource rightSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return center.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};

}
