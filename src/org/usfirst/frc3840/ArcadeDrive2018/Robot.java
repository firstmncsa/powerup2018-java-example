
package org.usfirst.frc3840.ArcadeDrive2018;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc3840.ArcadeDrive2018.commands.*;
import org.usfirst.frc3840.ArcadeDrive2018.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	public static OI oi;
	public static DriveTrain driveTrain;
	public String gameData;
	public double gameDataTimeout;
	private Timer gameDataTimer;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		RobotMap.init();

		driveTrain = new DriveTrain();

		// OI must be constructed after subsystems. If the OI creates Commands
		//(which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// Add commands to Autonomous Sendable Chooser
		chooser.addDefault("Autonomous Command", new AutonomousCommand());

		SmartDashboard.putData("Auto mode", chooser);

		// Create the gameDataTimer object.
		gameDataTimer = new Timer();

		// Set gameData to null so our check in autoInit works properly.
		gameData = null;

		// Set how long to wait in seconds for gameData to be received before giving up.
		// Note: Set this to be 1 second less than the difference between your longest autonomous mode 
		// and the 15 seconds in autonomous. eg; scale auto for far side takes 12 seconds, set this to 2.0
		// 15 - 12 - 1 = 2
		// Set this to be 0 if you don't want to wait
		// This is a double so you can use fractions of a second in decimal for fine grained control.
		gameDataTimeout = 0;
	}

	/**
	 * This function is called when the disabled button is hit.
	 * You can use it to reset subsystems before shutting down.
	 */
	@Override
	public void disabledInit(){
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		// To ensure we attempt at least once even if the timeout is 0.0, grab the gameData.
		gameData = DriverStation.getInstance().getGameSpecificMessage();
				
		// DS / FMS possible value discrepancy fix.
		// This will look for NULL as the text value for the gameData, if seen, it will set the gameData to be a null value.
		// This will help you test for null value handling in your code.
		// null values are possible if the DS does not receive the game data from FMS before you ask for it via getGameSpecificMessage.
		// If you have concerns about this interfering during competition, comment the following line out.
		// However, FMS should NEVER send the text string NULL, so it's safe to leave this in.
		// To test, enter NULL as text for the Game Data in the DS, this will result in a null value in gameData
		// which will cause a timeout for gameData being received and thus duplicate all possible competition scenarios.
		// NOTE!! If you are changing values on the drivers station to test, please note there is sometimes a lag between
		// the value you set and what is received via getGameSpecifcMessage. Give it 5-10 seconds before testing after changing.
		// You can monitor or verify using the NetworkTables outline viewer or shuffleboard, look at the GameSpecificMessage value
		// in FMSInfo. The following line of code is also in the loop below, comment them both out if concerned.
		gameData = ("NULL".equalsIgnoreCase(gameData)) ? null : gameData;

		// Start the timer for how long to wait for gameData to be received from FMS.
		gameDataTimer.reset();
		gameDataTimer.start();

		// We can either loop here until we get gameData that is not null or we timeout, or we can use
		// the auto periodic call to do ensure we have data. This example will document the former.
		// contact firstmn.csa@gmail.com for the latter if you want to see the difference.

		// Loop until we can valid gameData or we timeout. Use Double.compare to ensure we do double checking properly.
		while (gameData == null && Double.compare(gameDataTimer.get(), gameDataTimeout) <= 0) {
			// Try to get gameData again.
			gameData = DriverStation.getInstance().getGameSpecificMessage();

			// See DS / FMS testing note above for more details on this.
			gameData = ("NULL".equalsIgnoreCase(gameData)) ? null : gameData;
			
			// If needed, delay a bit before possibly checking again.
			if (gameData == null) {
				Timer.delay(0.02);
			}
		}

		// Check if gameData is still null, if so, then we timed out and need to either call a default autonomous command, or set gameData to something
		// that can signal we didn't receive gameData in time and act accordingly.
		if (gameData == null) {
			System.out.println("autonomousInit: Game Data not received before timeout, using autonomous command fallback: AutonoumousCommand");
			autonomousCommand = new AutonomousCommand();
		}
		else {
			// We have gameData, select the automode from the chooser, or add your auto selection code here knowing you have
			// gameData.
			autonomousCommand = chooser.getSelected();
		}
		
		// Schedule the autonomous command (example)
		if (autonomousCommand != null) autonomousCommand.start();

		// Shutdown the gameDataTimer now that we are done with it.
		gameDataTimer.stop();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}
}
