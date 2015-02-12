package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Elevator {
	PIDMotor elevatorMotor;
	Encoder elevatorEncoder;
	double speed = 0.5;
	double diameter = 1.3;
	double p=0.1, i=0.0,  d=0.0;
	PIDController elevatorController;

	PIDTuner tuner;
	public Elevator(int motorPort, Encoder encoder){
		elevatorMotor = new PIDMotor(motorPort);
		elevatorEncoder = encoder;
		System.out.println("Init PID now");
		PIDInit();
		//SmartDashboard.putNumber("p-elevator", p);
		//SmartDashboard.putNumber("d-elevator", d);
	}
	
	public void PIDInit(){
		elevatorController = new PIDController(p,0.0,d, new DistancePIDEncoder(elevatorEncoder, diameter), elevatorMotor, 0.05);
		elevatorController.setInputRange(0,60);
		elevatorController.setOutputRange(-1,1);
		elevatorController.setAbsoluteTolerance(3);
		tuner = new PIDTuner(elevatorController, "Elevator");
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
		System.out.println("dHeight " + newHeight);
		elevatorController.setSetpoint(newHeight);
		System.out.println(elevatorController.get());
		SmartDashboard.putData("Elevator", elevatorController);
		//elevatorController = SmartDashboard.getData("Elevator");
	}
	
	public double getHeight(){
		return elevatorEncoder.get()*diameter*Math.PI/250;
	}
	
	public void reset(){
		elevatorEncoder.reset();
	}
	
	public void tune(){
		//System.out.println("tuning");
		tuner.dash();
		elevatorController.setPID(tuner.getP(), tuner.getI(), tuner.getD(), 0);
	}
	
	public void enable(){
		//System.out.println("controller Enabled");
		elevatorController.enable();
	}
	
	public void disable(){
		elevatorController.disable();
	}
}
