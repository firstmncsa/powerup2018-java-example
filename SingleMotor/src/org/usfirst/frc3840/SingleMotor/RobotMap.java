
package org.usfirst.frc3840.SingleMotor;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
   
    public static WPI_TalonSRX runMotor1TalonSRX1;

    public static void init() {
    
        runMotor1TalonSRX1 = new WPI_TalonSRX(1);
        LiveWindow.addActuator("Test Motor", "One", (Sendable) runMotor1TalonSRX1);
   
    }
}
