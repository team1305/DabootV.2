// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package frc.robot.auto_commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;



/**
 *
 */
public class Auto_Tank_Rotate_Master extends SequentialCommandGroup {

    private double m_autorotateangle;
    private double m_leftpower;
    //private double m_rightpower;
    //private double m_timeout;
    private double m_minspeed = 0.35;
    private double rampthreshold;
    //private double startingangle;

    private double shuffleduration;
    private boolean bshuffle;
    private double iloops;

    public Auto_Tank_Rotate_Master(double AutoRotateAngle, double LeftPower, boolean bshuffle, double shuffleduration ) {

        m_autorotateangle = AutoRotateAngle;
        m_leftpower = Math.abs(LeftPower);
        this.bshuffle = bshuffle;
        this.shuffleduration = shuffleduration * 50;
        //m_rightpower = Math.abs(RightPower);
        //m_timeout = TimeOut;

        iloops = 0;
        addRequirements(RobotContainer.drive);  

        //startingangle = RobotContainer.drive.gyroGetAngle();
 

    }

    // Called just before this Command runs the first time
    public void initialize() {
        iloops = 0;
        //if (RobotContainer.getdebug()) {
        //SmartDashboard.putString("Auto Rotate Init", "yes");
       // }
        
    
   // 	withTimeout(m_timeout);

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    iloops = iloops + 1;
    // New Shuffle while driving code
    if (bshuffle) {
       if (iloops <= shuffleduration) {
           RobotContainer.intake.setIntake(0.5); 
           RobotContainer.elevator.setElevator(0.4); 
           RobotContainer.shooter.setShooter(-0.3);
        } else { // turn off shuffle
           RobotContainer.intake.setIntake(0); 
           RobotContainer.elevator.setElevator(0); 
           RobotContainer.shooter.setShooter(0);
        }
    }


        if ( Math.abs( m_autorotateangle - RobotContainer.drive.gyroGetAngle()) <= 45) {
            rampthreshold = 35;
        } else { // > 45 degrees}
           rampthreshold = Math.abs( m_autorotateangle - RobotContainer.drive.gyroGetAngle()) / 2.5;
    }

        if (RobotContainer.drive.gyroGetAngle() > m_autorotateangle) { // turn left 
            if ( Math.abs(m_autorotateangle - RobotContainer.drive.gyroGetAngle()) <  rampthreshold ) {
                RobotContainer.drive.tankdrive(-m_minspeed, m_minspeed);
             
            } else {
                RobotContainer.drive.tankdrive(-m_leftpower, m_leftpower);

            }
        } else { // turn right
            if ( Math.abs(m_autorotateangle - RobotContainer.drive.gyroGetAngle()) < rampthreshold ) {
                RobotContainer.drive.tankdrive(m_minspeed, -m_minspeed);
                
            } else {
                RobotContainer.drive.tankdrive(m_leftpower, -m_leftpower);

            }

        }

    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return ( Math.abs(m_autorotateangle - RobotContainer.drive.gyroGetAngle())  < 2) ;
    }

    // Called once after isFinished returns true
    protected void end() {
        RobotContainer.drive.tankdrive(0,0);


        if (bshuffle) {
            RobotContainer.intake.setIntake(0); 
            RobotContainer.elevator.setElevator(0); 
            RobotContainer.shooter.setShooter(0);
        }
     
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
