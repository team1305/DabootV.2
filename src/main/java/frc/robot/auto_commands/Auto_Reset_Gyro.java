package frc.robot.auto_commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Reset_Gyro extends SequentialCommandGroup {

    public Auto_Reset_Gyro() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	//requires(RobotContainer.drive);
    }

    // Called just before this Command runs the first time
    public void initialize() {
    //	RobotContainer.drive.gyroReset();
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        RobotContainer.drive.gyroReset();
       // SmartDashboard.putNumber("AUTO RESET", RobotContainer.drive.gyroGetAngle());
        
    }
    public boolean isFinished() {
           return true;
    }
    // Called once after isFinished returns true
    public void end() {

    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
