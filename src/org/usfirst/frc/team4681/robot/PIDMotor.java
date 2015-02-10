package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

public class PIDMotor implements PIDOutput {
	Victor motor;
	public PIDMotor(Victor m){
		motor = m;
	}
	public void set(double speed){
		motor.set(speed);
	}
	@Override
	public void pidWrite(double output) {
		motor.set(output);
	}
	
}
