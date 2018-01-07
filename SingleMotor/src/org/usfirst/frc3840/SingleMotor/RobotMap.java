
package org.usfirst.frc3840.SingleMotor;


import com.ctre.phoenix.motorcontrol.can.TalonSRX;


/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */

public class RobotMap {
   
    public static TalonSRX runMotor1TalonSRX1;


    public static void init() {
    
        runMotor1TalonSRX1 = new TalonSRX(1);
   
    }
}
