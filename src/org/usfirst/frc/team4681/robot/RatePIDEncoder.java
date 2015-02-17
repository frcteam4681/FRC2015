package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

public class RatePIDEncoder implements PIDSource {

	double distancePerPulse = 8;
	
	// Initialize encoder
	Encoder encoder;
	
	// Constructor takes encoder and defines object
	public RatePIDEncoder(Encoder e){
		encoder = e;
		encoder.setDistancePerPulse(distancePerPulse);
	}
	
	// Gets the rate from the encoder. Used as the pidGet method for velocity encoder
	public double pidGet() {
		//System.out.println(encoder.getRate());
		return encoder.getRate();
	}
	
}
