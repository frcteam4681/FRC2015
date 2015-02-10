package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;

public class Drive {
	
	Victor fr,fl,br,bl;
	Encoder fre,fle,bre,ble;
	
	double frPID, flPID, brPID, blPID;
	
	public Drive(Victor frontLeft, Victor frontRight, Victor backLeft, Victor backRight,
			Encoder frontLeftEncoder, Encoder frontRightEncoder, Encoder backLeftEncoder, Encoder backRightEncoder){
		fl = frontLeft;
		fr = frontRight;
		bl = backLeft;
		br = backRight;
		
		fle = frontLeftEncoder;
		fre = frontRightEncoder;
		ble = backLeftEncoder;
		bre = backRightEncoder;
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
		return((fle.getRate()+fre.getRate()+ble.getRate()+bre.getRate())/4);
	}
	
	
}
