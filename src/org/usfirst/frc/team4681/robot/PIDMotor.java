package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

public class PIDMotor implements PIDOutput {
	// Defines victor object
	Victor motor;
	
	// Constructor - initializes Victor object
	public PIDMotor(int port){
		motor = new Victor(port);
	}
	
	// Sets the victor to the give speed. This method is used only when the PIDController is disabled
	public void set(double speed){
		motor.set(speed);
	}

	// writes the PID value to the Victor
	public void pidWrite(double output) {
		set(output);
	}
	
}
