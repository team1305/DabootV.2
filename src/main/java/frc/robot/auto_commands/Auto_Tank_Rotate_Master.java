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


import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;



/**
 *
 */
public class Auto_Tank_Rotate_Master extends SequentialCommandGroup {

    private double m_autorotateangle;
    private double m_leftpower;
    private double m_rightpower;
    private double m_timeout;
    private double m_minspeed = 0.35;
    private double rampthreshold;
    private double startingangle;

    public Auto_Tank_Rotate_Master(double AutoRotateAngle, double LeftPower, double RightPower, double TimeOut ) {

        m_autorotateangle = AutoRotateAngle;
        m_leftpower = LeftPower;
        m_rightpower = RightPower;
        m_timeout = TimeOut;

        addRequirements(RobotContainer.drive);  

        startingangle = RobotContainer.drive.gyroGetAngle();
 

    }

    // Called just before this Command runs the first time
    public void initialize() {
        SmartDashboard.putString("Auto Rotate Init", "yes");
        if ( Math.abs( m_autorotateangle - RobotContainer.drive.gyroGetAngle()) <= 45) {
            rampthreshold = 35;
        } else { // > 45 degrees}
           rampthreshold = Math.abs( m_autorotateangle - RobotContainer.drive.gyroGetAngle()) / 2.5;
    }

   // 	withTimeout(m_timeout);

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {

        



        if (RobotContainer.drive.gyroGetAngle() > m_autorotateangle) { // turn left
            if ( Math.abs(m_autorotateangle - RobotContainer.drive.gyroGetAngle()) <  rampthreshold ) {
                RobotContainer.drive.tankdrive(m_minspeed, -m_minspeed);
             
            } else {
                RobotContainer.drive.tankdrive(m_leftpower, -m_leftpower);

            }
        } else { // turn right
            if ( Math.abs(m_autorotateangle - RobotContainer.drive.gyroGetAngle()) < rampthreshold ) {
                RobotContainer.drive.tankdrive(m_minspeed, -m_minspeed);
                
            } else {
                RobotContainer.drive.tankdrive(m_leftpower, -m_leftpower);

            }

        }

        

       	//SmartDashboard.putNumber("NavX getYaw", Robot.navX.getYaw());
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return ( Math.abs(m_autorotateangle - RobotContainer.drive.gyroGetAngle())  < 2) ;
    }

    // Called once after isFinished returns true
    protected void end() {
        RobotContainer.drive.DriveStop();
        //Robot.drive.HighGear();
        //Robot.drive.setLeftSide(0);
        //Robot.drive.setRightSide(0);
       // Robot.drive.driveshighgear();
    //	SmartDashboard.putNumber("EndAngle", Robot.drive.gyroGetAngle());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
