package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;

public class Elevator {
	// define PIDMotor and Encoder objects 
	PIDMotor elevatorMotor;
	Encoder elevatorEncoder;
	
	// Speed for manual raising and lowering of elevator
	final double speed = 0.5;
	
	// Diameter of pulley
	final double diameter = 1.3;
	
	// Tuned PID values
	final double p=1.5, i=0.0,  d=0.0;
	
	// define PID controller object
	PIDController elevatorController;
	
	// Minimum and maximum heights of the elevator mechanism
	final double MAX_HEIGHT = 48;
	final double MIN_HEIGHT = 0;
	
	// Constructor for elevator object
	public Elevator(int motorPort, Encoder encoder){
		elevatorMotor = new PIDMotor(motorPort);
		elevatorEncoder = encoder;
		PIDInit();
	}
	
	// Various methods to configure the PID Controller correctly
	public void PIDInit(){
		elevatorController = new PIDController(p,i,d, new DistancePIDEncoder(elevatorEncoder, diameter), elevatorMotor);
		elevatorController.setInputRange(MIN_HEIGHT,MAX_HEIGHT);
		elevatorController.setOutputRange(-1,1);
		elevatorController.setAbsoluteTolerance(3);
	}
	
	// Raise the elevator manually. Does not work if PID is enabled
	public void raise(){
		elevatorMotor.set(speed);
	}
	
	// Lower the elevator manually. Does not work if PID is enabled
	public void lower(){
		elevatorMotor.set(-speed);
	}
	
	// Stop the motor. Should only be called following raise() or lower() methods.
	public void brake(){
		elevatorMotor.set(0.0);
	}
	
	// Set the desired height for the PID controller
	public void changeHeight(double newHeight){
		System.out.println("dHeight " + newHeight);
		elevatorController.setSetpoint(newHeight);
	}
	
	// return the height of the elevator as determined by the encoder in inches
	public double getHeight(){
		return elevatorEncoder.get()*diameter*Math.PI/250;
	}
	
	// reset the encoder to 0.0in 
	public void reset(){
		elevatorEncoder.reset();
	}
	
/*	No longer relevant tuning method 
*	
*	public void tune(){
*		System.out.println("tuning");
*		tuner.dash();
*		elevatorController.setPID(tuner.getP(), tuner.getI(), tuner.getD(), 0);
*	}
*/	
	
	// enable PID controller
	public void enable(){
		elevatorController.enable();
	}
	
	// disable PID controller
	public void disable(){
		elevatorController.disable();
	}
}
