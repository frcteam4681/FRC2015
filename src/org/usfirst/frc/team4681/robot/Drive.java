package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;

public class Drive {
	
	PIDMotor fr,fl,br,bl;
	RatePIDEncoder fre,fle,bre,ble;
	double p_fl = 0, i_fl = 0, d_fl = 0, f_fl = 0;
	double p_fr = 0, i_fr = 0, d_fr = 0, f_fr = 0;
	double p_bl = 0, i_bl = 0, d_bl = 0, f_bl = 0;
	double p_br = 0, i_br = 0, d_br = 0, f_br = 0;
	
	public Drive(int frontLeft, int frontRight, int backLeft, int backRight,
			Encoder frontLeftEncoder, Encoder frontRightEncoder, Encoder backLeftEncoder, Encoder backRightEncoder){
		fl = new PIDMotor(frontLeft);
		fr = new PIDMotor(frontRight);
		bl = new PIDMotor(backLeft);
		br = new PIDMotor(backRight);
		
		fle = new RatePIDEncoder(frontLeftEncoder);
		fre = new RatePIDEncoder(frontRightEncoder);
		ble = new RatePIDEncoder(backLeftEncoder);
		bre = new RatePIDEncoder(backRightEncoder);
		
		PIDController flPID = new PIDController(p_fl, i_fl, d_fl, f_fl, fle, fl);
		PIDController frPID = new PIDController(p_fr, i_fr, d_fr, f_fr, fre, fr);
		PIDController blPID = new PIDController(p_bl, i_bl, d_bl, f_bl, ble, bl);
		PIDController brPID = new PIDController(p_br, i_br, d_br, f_br, bre, br);
	}
	
	public void drive(double X1, double Y1, double X2){
		fl.set(specialSquare(-Y1 + X1 - X2));
        bl.set(specialSquare(Y1 - X1 - X2));
        fr.set(specialSquare(-(-Y1 - X1 + X2)));
        br.set(specialSquare(-(Y1 + X1 + X2)));
        
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
	
	
	
}
