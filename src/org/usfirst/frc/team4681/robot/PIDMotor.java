package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.Victor;

public class PIDMotor implements PIDOutput {
	Victor motor;
	public PIDMotor(int port){
		motor = new Victor(port);
	}
	public void set(double speed){
		System.out.println("speed " + speed);
		motor.set(speed);
		System.out.println("PWM " + motor.getRaw());
	}
	@Override
	public void pidWrite(double output) {
		System.out.println("PID Write");
		set(output);
	}
	
}
