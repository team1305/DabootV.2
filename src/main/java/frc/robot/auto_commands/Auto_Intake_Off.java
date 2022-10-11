package frc.robot.auto_commands;



import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Intake_Off extends SequentialCommandGroup {

   
	
    public Auto_Intake_Off( ) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	 //requires(Robot.intake);
           	
    }

    // Called just before this Command runs the first time
    public void initialize() {
	
    RobotContainer.intake.intakeExtension(false);
    RobotContainer.intake.setIntake(0); 
    RobotContainer.elevator.setElevator(0);
    RobotContainer.shooter.setShooter(0);

    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
      //RobotContainer.intake.intakeExtension(false);
      //RobotContainer.intake.setIntake(0);
      //RobotContainer.elevator.setElevator(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//Robot.drive.driveStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
