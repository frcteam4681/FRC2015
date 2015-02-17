
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
	
	CameraServer camera = CameraServer.getInstance();
	
	
	//SerialPort usbPort = new SerialPort(9600, SerialPort.Port.kUSB);
	
	// Defines the two joystick objects
	public Joystick Joy1 = new Joystick(0);
	public Joystick Joy2 = new Joystick(1);
	
	// Defines drive object, contains the encoder definitions 
	Drive drive = new Drive(0, 1, 2, 3,	new Encoder(0,1), new Encoder(2,3), new Encoder(4,5), new Encoder(6,7));
	
	// Defines elevator object
	Elevator elevator = new Elevator(4, new Encoder(8,9));
	
	// Booleans for switching encoders on and off
	boolean elevatorEnabled = false;
	boolean driveEnabled = false;
	
	// defines initial height as 0in
	double height = 0;
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // put the height slider on the dashboard
    	SmartDashboard.putNumber("Height", height);
    	camera.setQuality(50);
    	camera.startAutomaticCapture("cam0");
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
        // resets the elevator to 0. the elevator needs to be at the bottom for the system to work correctly
        elevator.reset();
    }
    
   /*
    * This function is called when the disabled button is hit.
    * You can use it to reset subsystems before shutting down.
    */
    public void disabledInit(){
    }

   //This function is called periodically during operator control
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putData("camera", camera);
        // enable/disable elevator PID by pressing Joy1 button 8
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
        
        // enable/disable drive PIDs by pressing Joy1 button 9
        if(Joy1.getRawButton(9)){
        	if(driveEnabled){
        		drive.disable();
        		driveEnabled = false;
        	}
        	else
        	{
        		drive.enable();
        		driveEnabled = true;
        		System.out.println("drivePID enabled");
        	}
        }
        
        // drives the robot
        drive.drive(Joy1.getX(), Joy1.getY(), Joy2.getX());
        heightButtons();
        byte[] data = new byte[1];
        data[0] = 43;
       // usbPort.write(data, 1);
        
        // Change the height of the elevator based on the smartDashboard value. PID must be enabled
        //height = SmartDashboard.getNumber("Height");
       // elevator.changeHeight(height);
        
        // 
        if(Joy1.getRawButton(4)){
        	elevator.raise();
        }
        else
        if(Joy1.getRawButton(5)){
        	elevator.lower();
        }
        else
        	elevator.brake();
    }
    
    
    public void heightButtons(){
    	if(Joy1.getRawButton(2)){
    		elevator.changeHeight(Elevator.BOTTOM);
    	}
    	else
        if(Joy1.getRawButton(6)){
    		System.out.println("Button works");
        	elevator.changeHeight(Elevator.RAMP);
        }
        else
       	if(Joy1.getRawButton(7)){
       		elevator.changeHeight(Elevator.ONE_TOTE);
        }
        else
        if(Joy1.getRawButton(10)){
        	elevator.changeHeight(Elevator.TWO_TOTES);
        }
        else
        if(Joy1.getRawButton(11)){
        	elevator.changeHeight(Elevator.THREE_TOTES);
        }
    }
    // This function is called periodically during test mode
     public void testPeriodic() {
        LiveWindow.run();
    }
 }

