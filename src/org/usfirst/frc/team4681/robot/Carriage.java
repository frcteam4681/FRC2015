package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;

public class Carriage {
	Victor carriageMotor;
	Encoder carriageEncoder;
	double speed = 0.5;
	double diameter = 1.3;
	double p=0.1, d=0.1;
	
	PIDController carriageController = new PIDController(p,0.0,d, new DistancePIDEncoder(carriageEncoder, diameter), carriageMotor, 50);
	
	public void PIDInit(){
		carriageController.setInputRange(0,60);
		carriageController.setOutputRange(-1,1);
		carriageController.setAbsoluteTolerance(3);
	}
	
	public Carriage(Victor motor, Encoder encoder){
		carriageMotor = motor;
		carriageEncoder = encoder;
		carriageController.enable();
	}
	
	public void raise(){
		carriageMotor.set(speed);
	}
	
	public void lower(){
		carriageMotor.set(-speed);
	}
	
	public void brake(){
		carriageMotor.set(0.0);
	}
	
	public void changeHeight(double newHeight){
		carriageController.setSetpoint(newHeight);
	}
	
	public double getHeight(){
		return carriageEncoder.get()*diameter*Math.PI/250;
	}
	
	public void reset(){
		carriageEncoder.reset();
	}
	
}
