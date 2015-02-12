package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive {
	
	double k_fr,k_fl,k_br,k_bl;
	Victor fr, fl, br, bl;
	
	RatePIDEncoder fre,fle,bre,ble;
	double p_fl = 0, i_fl = 0, d_fl = 0, f_fl = 0;
	double p_fr = 0, i_fr = 0, d_fr = 0, f_fr = 0;
	double p_bl = 0, i_bl = 0, d_bl = 0, f_bl = 0;
	double p_br = 0, i_br = 0, d_br = 0, f_br = 0;
	
	PIDController flPID, frPID, blPID, brPID;
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
	
	public void drive(double X1, double Y1, double X2){
		fl.set(k_fl*(specialSquare(-Y1 + X1 - X2)));
        bl.set(k_bl*(specialSquare(Y1 - X1 - X2)));
        fr.set(k_fr*(specialSquare(-(-Y1 - X1 + X2))));
        br.set(k_br*(specialSquare(-(Y1 + X1 + X2))));
        
        SmartDashboard.putData("Front Left", flPID);
        SmartDashboard.putData("Front Right", frPID);
        SmartDashboard.putData("Back Left", blPID);
        SmartDashboard.putData("Back Right", brPID);
        
        flPID.setSetpoint(average());
        frPID.setSetpoint(average());
        blPID.setSetpoint(average());
        brPID.setSetpoint(average());
	}
	
	public double specialSquare(double number) {
    	if(number>0)
    	{
    		return Math.pow(number, 2);
    	}
    	else {
    	return (-Math.pow(number, 2));
    	}
	}
	
	public double average(){
		return((fle.pidGet()+fre.pidGet()+ble.pidGet()+bre.pidGet())/4);
	}
	
	public void enable(){
		brPID.enable();
		flPID.enable();
		frPID.enable();
		blPID.enable();
	}
	
	public void disable(){
		brPID.disable();
		flPID.disable();
		frPID.disable();
		blPID.disable();
	}
	
}
