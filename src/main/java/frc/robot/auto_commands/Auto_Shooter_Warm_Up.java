package frc.robot.auto_commands;


import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 *
 */
public class Auto_Shooter_Warm_Up extends SequentialCommandGroup {

  
	
    public Auto_Shooter_Warm_Up() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	 //requires(Robot.shooter);

    // Called just before this Command runs the first time
       }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	//Robot.shooter.setShooterSpeed(0.5);

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
    	end();
    }
}
