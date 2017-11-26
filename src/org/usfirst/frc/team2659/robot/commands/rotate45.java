package org.usfirst.frc.team2659.robot.commands;

import edu.wpi.first.wpilibj.command.Command;


import org.usfirst.frc.team2659.robot.Robot;
import org.usfirst.frc.team2659.robot.RobotMap;

/**
 *
 */
public class rotate45 extends Command {

	public rotate45() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.drivetrain);
		
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.drivetrain.rotate(60);
		setTimeout(3);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return RobotMap.gyro.getAngle() > 59  || isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.drivetrain.stop();
	}
}
