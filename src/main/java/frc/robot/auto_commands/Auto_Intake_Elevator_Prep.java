package frc.robot.auto_commands;



import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Intake_Elevator_Prep extends SequentialCommandGroup {

	double duration;
    double iloops;
	
    public Auto_Intake_Elevator_Prep(double duration) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        this.duration = duration * 50;
    	
         //addRequirements(RobotContainer.elevator);
         //addRequirements(RobotContainer.elevator);
         //addRequirements(RobotContainer.shooter);
             	
    }

    // Called just before this Command runs the first time
    public void initialize() {
        //RobotContainer.intake.intakeExtension(true);
        iloops = 0;

        RobotContainer.elevator.setElevator(-0.3);

      
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
       //RobotContainer.intake.setIntake(0.5); 
       //RobotContainer.elevator.setElevator(0.4);
       iloops = iloops + 1;
	
    }

    // Make this return true when this Command no longer needs to run execute()
    public boolean isFinished() {
    	if (iloops >= duration) {
            RobotContainer.elevator.setElevator(0);

            return true;
        } else {
            return false;
        }
        
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
