
package org.usfirst.frc3840.ArcadeDrive2018.subsystems;

import org.usfirst.frc3840.ArcadeDrive2018.RobotMap;
import org.usfirst.frc3840.ArcadeDrive2018.commands.DriveRobot;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *  Drive Train SubSystem.
 */
public class DriveTrain extends Subsystem {

    private final DifferentialDrive arcadeTrain = RobotMap.driveTrainArcadeTrain;

    @Override
    public void initDefaultCommand() {
       
        // Set the default command for a subsystem here.
    	// Set the default command for a subsystem here.
         setDefaultCommand(new DriveRobot());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
  //Arcade Drive from Drive via XboxController input   
    public void arcadeDrive(XboxController joystick1) {
    	double joyThreshold = 0.15;  //default threshold value from xBox Controller
    	double stickX = joystick1.getX();       
    	double stickY = joystick1.getY();
    	//display on dashboards
    	SmartDashboard.putNumber("Speed X: ", stickX);
    	SmartDashboard.putNumber("Speed Y: ", stickY);
    	//Checks for min joystick input
    	if(Math.abs(stickX) > joyThreshold|| Math.abs(stickY) > joyThreshold || true) { 
    		arcadeTrain.arcadeDrive(stickX, stickY, false);
    	}
    }
    
   //Stop motion for acrcade drive
    public void StopMotion() {
    	
    	arcadeTrain.stopMotor();
    }
    
}

