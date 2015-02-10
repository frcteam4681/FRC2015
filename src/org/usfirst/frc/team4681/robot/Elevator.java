package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;

public class Elevator {
	PIDMotor elevatorMotor;
	Encoder elevatorEncoder;
	double speed = 0.5;
	double diameter = 1.3;
	double p=0.1, d=0.0;
	
	PIDController elevatorController = new PIDController(p,0.0,d, new DistancePIDEncoder(elevatorEncoder, diameter), elevatorMotor, 50);
	
	public void PIDInit(){
		elevatorController.setInputRange(0,60);
		elevatorController.setOutputRange(-1,1);
		elevatorController.setAbsoluteTolerance(3);
	}
	
	public Elevator(Victor motor, Encoder encoder){
		elevatorMotor = new PIDMotor(motor);
		elevatorEncoder = encoder;
		PIDInit();
		elevatorController.enable();
	}
	
	public void raise(){
		elevatorMotor.set(speed);
	}
	
	public void lower(){
		elevatorMotor.set(-speed);
	}
	
	public void brake(){
		elevatorMotor.set(0.0);
	}
	
	public void changeHeight(double newHeight){
		elevatorController.setSetpoint(newHeight);
	}
	
	public double getHeight(){
		return elevatorEncoder.get()*diameter*Math.PI/250;
	}
	
	public void reset(){
		elevatorEncoder.reset();
	}
}
