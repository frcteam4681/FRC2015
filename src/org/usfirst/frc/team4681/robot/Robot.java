
package org.usfirst.frc.team4681.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	public Joystick Joy1 = new Joystick(0);
	public Joystick Joy2 = new Joystick(1);
	
	Drive drive = new Drive(0, 1, 2, 3,	new Encoder(0,1), new Encoder(2,3), new Encoder(4,5), new Encoder(6,7));
	
	Elevator elevator = new Elevator(4, new Encoder(8,9));
	boolean elevatorEnabled = false;
	boolean driveEnabled = false;
	
	double height = 0;
	public double encoderMax = 0;
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
    	SmartDashboard.putNumber("Height", height);
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        elevator.reset();
    }
    
   /*
    * This function is called when the disabled button is hit.
    * You can use it to reset subsystems before shutting down.
    */
    public void disabledInit(){
    	System.out.println("Final maximum is " + encoderMax);
    }

   //This function is called periodically during operator control
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        if(Joy1.getRawButton(8)){
        	if(elevatorEnabled){
        		elevator.disable();
        		elevatorEnabled = false;
        	}
        	else
        	{
        		elevator.enable();
        		elevatorEnabled = true;
        	}
        	
        }
        if(Joy1.getRawButton(8)){
        	if(elevatorEnabled){
        		drive.disable();
        		driveEnabled = false;
        	}
        	else
        	{
        		drive.enable();
        		driveEnabled = true;
        	}
        }
        drive.drive(Joy1.getX(), Joy1.getY(), Joy2.getX());
        height = SmartDashboard.getNumber("Height");
        elevator.changeHeight(height);
        //elevator.tune();
        //System.out.println("current Height " + elevator.getHeight());
        if(Joy1.getRawButton(3)){
        	elevator.raise();
        }
        else
        if(Joy1.getRawButton(2)){
        	elevator.lower();
        }
        else
        	elevator.brake();
    }
    
    // This function is called periodically during test mode
     public void testPeriodic() {
        LiveWindow.run();
    }
 }

