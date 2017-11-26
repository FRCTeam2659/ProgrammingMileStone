package org.usfirst.frc.team2659.robot.subsystems;

import java.util.ArrayList;

import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team2659.robot.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shooter extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	PWMSpeedController shooterSC = RobotMap.shooterSC;
	Encoder myEncoder = RobotMap.myEncoder;
	PWMSpeedController speedcontroller = RobotMap.loaderSC;
    PIDController shooterPID = RobotMap.shooterPID;
    GripPipeline vision;
	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void boom(int speed) {
    	shooterPID.setSetpoint(speed);
    	shooterPID.enable();
    }
    public double aim() {
    	CvSink sink = CameraServer.getInstance().getVideo("aim");
    	Mat frame = new Mat();
    	sink.grabFrame(frame);
    	vision.process(frame);
    	ArrayList<MatOfPoint> result = vision.filterContoursOutput();
    	
    	double centerX, centerY;
    	ArrayList<Point> center = new ArrayList<Point>();
    	if (result.isEmpty())
    		return 0;
    	else {
    		for (int i = 0; i < result.size(); i++) {
    			Rect r = Imgproc.boundingRect(result.get(i));
    			centerX = r.x + r.width / 2;
    			centerY = r.y + r.height / 2;
    			center.add(new Point(centerX, centerY));
    		}
    	}
    	SmartDashboard.putNumber("goal_center", center.get(0).x);
    	
    	centerX = centerY = 0;
    	for (int i = 0; i < center.size(); i++) {
    		centerX += center.get(i).x;
    		centerY += center.get(i).y;
    	}
    	centerX /= center.size();
    	centerY /= center.size();
    	
    	double angle;
    	if (centerX <= 320) {
    		angle = Math.atan((320-centerX)/320*Math.tan(Math.PI/6))/Math.PI*180;
    		SmartDashboard.putNumber("angle", -angle);
    		return -angle;
    	}
    	else {
    		angle = Math.atan((centerX-320)/320*Math.tan(Math.PI/6))/Math.PI*180;
    		angle += 1.5;
    		SmartDashboard.putNumber("angle", angle);
    		return angle;
    	}
    }
    public void load() {
    	speedcontroller.set(-1);
    }
    public void regret() {
    	speedcontroller.set(1);
    }
    public void stop() {
    	shooterPID.disable();
    	speedcontroller.set(0);
    }
}

