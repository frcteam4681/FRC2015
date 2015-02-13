package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	
	// These doubles range from -1.0 to 1.0 and are what the PID controllers manipulate
	double k_fr,k_fl,k_br,k_bl;
	
	// Victor initialization. fr = front right, fl = front left, and so on
	Victor fr, fl, br, bl;
	
	// Encoder initialization. Follows naming scheme of victors
	RatePIDEncoder fre,fle,bre,ble;
	
	// PID value initialization. These need to be changed to the tuned values before competitions.
	double p_fl = 0, i_fl = 0, d_fl = 0, f_fl = 0;
	double p_fr = 0, i_fr = 0, d_fr = 0, f_fr = 0;
	double p_bl = 0, i_bl = 0, d_bl = 0, f_bl = 0;
	double p_br = 0, i_br = 0, d_br = 0, f_br = 0;
	
	// PID Controller initialization
	PIDController flPID, frPID, blPID, brPID;
		
	// Constructor - defines motors, encoders, and PID controllers. Also sets input and output ranges for controllers
	public Drive(int frontLeft, int frontRight, int backLeft, int backRight,
			Encoder frontLeftEncoder, Encoder frontRightEncoder, Encoder backLeftEncoder, Encoder backRightEncoder){
		fl = new Victor(frontLeft);
		fr = new Victor(frontRight);
		bl = new Victor(backLeft);
		br = new Victor(backRight);
		
		fle = new RatePIDEncoder(frontLeftEncoder);
		fre = new RatePIDEncoder(frontRightEncoder);
		ble = new RatePIDEncoder(backLeftEncoder);
		bre = new RatePIDEncoder(backRightEncoder);
		
		flPID = new PIDController(p_fl, i_fl, d_fl, f_fl, fle, fl);
		frPID = new PIDController(p_fr, i_fr, d_fr, f_fr, fre, fr);
		blPID = new PIDController(p_bl, i_bl, d_bl, f_bl, ble, bl);
		brPID = new PIDController(p_br, i_br, d_br, f_br, bre, br);
		
		flPID.setInputRange(-1.0, 1.0);
		flPID.setOutputRange(-1.0, 1.0);
		
		frPID.setInputRange(-1.0, 1.0);
		frPID.setOutputRange(-1.0, 1.0);
		
		blPID.setInputRange(-1.0, 1.0);
		blPID.setOutputRange(-1.0, 1.0);
		
		brPID.setInputRange(-1.0, 1.0);
		brPID.setOutputRange(-1.0, 1.0);
	}
	
	// Takes input from the joysticks and passes them to the motors. 
	// Also puts PID controllers on dashboard and sets them to the average value
	public void drive(double axis1, double axis2, double axis3){
		fl.set(k_fl*(specialSquare(-axis2 + axis1 - axis3)));
        bl.set(k_bl*(specialSquare(axis2 - axis1 - axis3)));
        fr.set(k_fr*(specialSquare(-(-axis2 - axis1 + axis3))));
        br.set(k_br*(specialSquare(-(axis2 + axis1 + axis3))));
        
        SmartDashboard.putData("Front Left", flPID);
        SmartDashboard.putData("Front Right", frPID);
        SmartDashboard.putData("Back Left", blPID);
        SmartDashboard.putData("Back Right", brPID);
        
        flPID.setSetpoint(average());
        frPID.setSetpoint(average());
        blPID.setSetpoint(average());
        brPID.setSetpoint(average());
	}
	
	// Squares input but keeps the sign of the value passed in
	public double specialSquare(double number) {
    	if(number>0)
    	{
    		return Math.pow(number, 2);
    	}
    	else {
    	return (-Math.pow(number, 2));
    	}
	}
	
	// Gets the average velocity of the four wheels
	public double average(){
		return((fle.pidGet()+fre.pidGet()+ble.pidGet()+bre.pidGet())/4);
	}
	
	// Enable all four encoders
	public void enable(){
		brPID.enable();
		flPID.enable();
		frPID.enable();
		blPID.enable();
	}
	
	// Disable all four encoders
	public void disable(){
		brPID.disable();
		flPID.disable();
		frPID.disable();
		blPID.disable();
	}
	
}
