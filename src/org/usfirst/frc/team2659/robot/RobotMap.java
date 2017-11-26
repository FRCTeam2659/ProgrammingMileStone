package org.usfirst.frc.team2659.robot;

import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static PWMSpeedController climberSC;
	public static PWMSpeedController intakeSC;
	public static PWMSpeedController shooterSC;
	public static PWMSpeedController loaderSC;
	public static PWMSpeedController leftSC;
	public static PWMSpeedController rightSC;
	public static Encoder myEncoder;
	public static ADXRS450_Gyro gyro;
	public static PIDController shooterPID;
	public static RobotDrive myRobot;
    
    public static void init() {
    	leftSC = new Talon(0);
    	rightSC = new Talon(1);
    	myRobot = new RobotDrive(leftSC, rightSC);
    	climberSC = new Talon(2);
    	intakeSC = new Spark(3);
    	shooterSC = new Talon(8);
    	shooterSC.setInverted(true);
    	loaderSC = new Spark(5);
    	myEncoder = new Encoder(0, 1, false, EncodingType.k1X);
    	myEncoder.setDistancePerPulse(1.0);
    	myEncoder.setPIDSourceType(PIDSourceType.kRate);
    	shooterPID = new PIDController(6.0e-8, 0.0, 9.0e-5, myEncoder, shooterSC); //2.5e-5, 0.0, 3.0e-3
    	shooterPID.setContinuous(false);
    	shooterPID.setOutputRange(-1.0, 1.0);
    	gyro = new ADXRS450_Gyro();
    	gyro.setPIDSourceType(PIDSourceType.kDisplacement);
    	gyro.reset();
    	
    }
}
