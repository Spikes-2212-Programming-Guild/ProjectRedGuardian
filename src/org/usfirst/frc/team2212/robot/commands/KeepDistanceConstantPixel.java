package org.usfirst.frc.team2212.robot.commands;

import org.usfirst.frc.team2212.robot.subsystems.Drivetrain;

import com.spikes2212.genericsubsystems.drivetrains.commands.DriveTank;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class KeepDistanceConstantPixel extends CommandGroup{
	public KeepDistanceConstantPixel(Drivetrain dt, NetworkTable nt, double widthPixel) {
			if (nt.getNumber("width",0) > widthPixel) {
				addSequential(new DriveTank(dt, widthPixel-nt.getNumber("width",0), widthPixel-nt.getNumber("width",0)));
			}
			else if (nt.getNumber("width",0) < widthPixel) {
				addSequential(new DriveTank(dt, -widthPixel+nt.getNumber("width",0), -widthPixel+nt.getNumber("width",0)));
			}else if(nt.getNumber("width",0)== 0 || nt.getNumber("width",0)==widthPixel){
				addSequential(new DriveTank(dt, 0, 0));
			}
		
	}
}
