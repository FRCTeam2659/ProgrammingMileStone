package org.usfirst.frc.team2659.robot.subsystems;

import org.usfirst.frc.team2659.robot.RobotMap;


import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	PWMSpeedController speedcontroller = RobotMap.climberSC;
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    
    }
    
    public void climbup() {
    	speedcontroller.set(-1);
    }
    
    public void climbdown() {
    	speedcontroller.set(0.3);
    }
    
    public void stop() {
    	speedcontroller.set(0);
    }
}

