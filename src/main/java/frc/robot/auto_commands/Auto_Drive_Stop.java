package frc.robot.auto_commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Drive_Stop extends SequentialCommandGroup {


	public Auto_Drive_Stop() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		 addRequirements(RobotContainer.drive);

	}

	// Called just before this Command runs the first time
	public void initialize() {
    	
	}

	// Called repeatedly when this Command is scheduled to run
	public void execute() {
       RobotContainer.drive.tankdrive(0,0);
	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
		return true;
	
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotContainer.drive.tankdrive(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
