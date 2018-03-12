
package org.usfirst.frc3840.ArcadeDrive2018.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Autonomous Command code
 */
public class AutonomousCommand extends CommandGroup {

    public AutonomousCommand() {
    	// A command group will require all of the subsystems that each member would require.
    	requires(Robot.driveTrain);   	
    	
        /**
        * Example below to add a command group
        */
    	//drive to straight
	  	//addParallel(new autoHoldCubeIn());
	  	//addSequential(new LiftToTravel());
	  	//addSequential(new autoDriveStraight());   
    	
    }

}
