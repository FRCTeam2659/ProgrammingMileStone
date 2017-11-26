package org.usfirst.frc.team2659.robot.subsystems;


import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.RobotDrive;

import org.usfirst.frc.team2659.robot.RobotMap;
import org.usfirst.frc.team2659.robot.commands.*;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	RobotDrive myRobot = RobotMap.myRobot;
	PWMSpeedController leftSC = RobotMap.leftSC;
	PWMSpeedController rightSC = RobotMap.rightSC;
	ADXRS450_Gyro gyro = RobotMap.gyro;
	PIDController leftTurnPID = new PIDController(0.02, 0.0, 0.0, gyro, leftSC);
	PIDController rightTurnPID = new PIDController(0.02, 0.0, 0.0, gyro, rightSC);

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new drive());
	}
	
	public void warriorDrive(double y, double z) {
		myRobot.arcadeDrive(y, z);
	}
	
	public void rotate(double degree) {
		gyro.reset();
		leftTurnPID.setOutputRange(-1.0, 1.0);
		rightTurnPID.setOutputRange(-1.0, 1.0);
		leftTurnPID.setContinuous(false);
		rightTurnPID.setContinuous(false);
    	leftTurnPID.setSetpoint(degree);
    	rightTurnPID.setSetpoint(degree);
    	leftTurnPID.enable();
    	rightTurnPID.enable();
	}
	
	public void stop() {
		leftTurnPID.disable();
		rightTurnPID.disable();
		myRobot.drive(0,0);
	}
}
