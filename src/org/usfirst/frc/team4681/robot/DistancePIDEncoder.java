package org.usfirst.frc.team4681.robot;
import edu.wpi.first.wpilibj.*;

public class DistancePIDEncoder implements PIDSource{
	Encoder encoder;
	double diameter;
	public DistancePIDEncoder(Encoder e, double d){
		encoder = e;
		diameter = d;
	}
	public double pidGet() {
		return encoder.getDistance()*diameter*Math.PI/250;
	}
}
