package frc.robot.auto_commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Reset_Encoders extends SequentialCommandGroup {

    public Auto_Reset_Encoders() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    public void initialize() {
    	RobotContainer.drive.resetEncoder();
    	// Robot.navX.gyroReset();
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
        RobotContainer.drive.resetEncoder();
     }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
