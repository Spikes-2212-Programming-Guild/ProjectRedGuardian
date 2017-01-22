package org.usfirst.frc.team2212.robot;

import java.util.function.Supplier;

import com.spikes2212.dashboard.ConstantHandler;
import com.spikes2212.dashboard.DashBoardController;

import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	public static double setpoint = 1;
	public static final Supplier<Double> TOLERANCE = ConstantHandler.addConstantDouble("Tolerance", 0.0);
	public static final double TOTE_WIDTH_CM = 68.3;
	public static final double VIEW_ANGLE = 61;
	public static final double MAX_SPEED = 1;
	public static final double CAMERA_WIDTH = 640;
	public static NetworkTable cameraInfo = NetworkTable.getTable("ImageProcessing");
	public static Supplier<Double> KP = ConstantHandler.addConstantDouble("KP", 0.8);
	public static Supplier<Double> KI = ConstantHandler.addConstantDouble("KI", 0.0);
	public static Supplier<Double> KD = ConstantHandler.addConstantDouble("KD", 0.65);
	public static Supplier<Double> center = () -> (((Constants.cameraInfo.getNumber("x", 0)
			+ 0.5 * Constants.cameraInfo.getNumber("width", 0)) / CAMERA_WIDTH) - 0.5);
	public static Supplier<Double> widthCentemters = () -> ((TOTE_WIDTH_CM * CAMERA_WIDTH)
			/ cameraInfo.getNumber("width", 0));

	public static Supplier<Double> distance = () -> ((0.5 * widthCentemters.get()) / Math.tan(VIEW_ANGLE / 2));

	//public static Supplier<Double> wantedDistance = ConstantHandler.addConstantDouble("Wanted Distance", 1);
	public static PIDSource leftSource = new PIDSource() {

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
		}

		@Override
		public double pidGet() {
			return distance.get();
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
			return distance.get();
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}
	};

}
