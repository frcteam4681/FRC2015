package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

public class RatePIDEncoder implements PIDSource {

	Encoder encoder;
	public RatePIDEncoder(Encoder e){
		encoder = e;
	}
	public double pidGet() {
		return encoder.getRate();
	}
	
}
