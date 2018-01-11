
package org.usfirst.frc3840.SingleMotor.subsystems;

import org.usfirst.frc3840.SingleMotor.RobotMap;
import org.usfirst.frc3840.SingleMotor.commands.*;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// name spaces for CAN TalonSRX
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


/**
 *  Testing Single Motor Control
 */
public class RunMotor1 extends Subsystem {

  
    private final TalonSRX talonSRX1 = RobotMap.runMotor1TalonSRX1;

  
    @Override
    public void initDefaultCommand() {
     
        setDefaultCommand(new Start());

     }
    
    
    @Override
    public void periodic() {
        // Put code here to be run every loop

    }

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void StartMotor() {
    	talonSRX1.set(ControlMode.PercentOutput,0.5);
    }
    
    public void RunMotorAtSetSpeed(XboxController driveXBoxController) {
    	double posThreshold = 0.1;  //default threshold value from xBox Controller
    	double dblPositive = driveXBoxController.getRawAxis(3);       
    
    	//display on dashboards
    	SmartDashboard.putNumber("Motor Speed: ", dblPositive);
    	
    	//Positive motor rotation
    	if(Math.abs(dblPositive) > posThreshold|| true) { 
    		talonSRX1.set(ControlMode.PercentOutput,dblPositive);
    	}

    }
    
    public void StopMotor() {
    	talonSRX1.set(ControlMode.PercentOutput, 0.0);
    }
    
}

