
package org.usfirst.frc3840.ArcadeDrive2018.commands;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc3840.ArcadeDrive2018.Robot;

/**
 * Sets Command to send drive joystick input to the drive train.
 */
public class DriveRobot extends Command {

    public DriveRobot() {
    	
        requires(Robot.driveTrain);

    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {
    	Robot.driveTrain.arcadeDrive(Robot.oi.driveJoyStick);
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {
    	Robot.driveTrain.StopMotion();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {
    	end();
    }
}
