package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PIDTuner {
	double p = 0, i = 0, d = 0, f = 0;
	PIDController controller;
	String name;
	public PIDTuner(PIDController c, String n){
		controller = c;
		name = n;
		SmartDashboard.putNumber("p-" + name, p);
		SmartDashboard.putNumber("i-" + name, i);
		SmartDashboard.putNumber("d-" + name, d);
		SmartDashboard.putNumber("f-" + name, f);
	}
	
	public double getP(){
		return p;
	}
	public double getI(){
		return i;
	}
	public double getD(){
		return d;
	}
	public double getF(){
		return f;
	}
	public void dash(){
		p = SmartDashboard.getNumber("p-" + name);
		d = SmartDashboard.getNumber("d-" + name);
		i = SmartDashboard.getNumber("i-" + name);
		f = SmartDashboard.getNumber("f-" + name);
	}
}
