
package org.usfirst.frc3840.SingleMotor.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3840.SingleMotor.Robot;

/**
 *  Testing of the single motor.
 */
public class Start extends Command {

    public Start() {
        requires(Robot.runMotor1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.runMotor1.RunMotorAtSetSpeed(Robot.oi.actuatorsController);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.runMotor1.StopMotor();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
