package frc.robot.auto_commands;



import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Finished extends SequentialCommandGroup {

    public Auto_Finished() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	
    	//requires(RobotContainer.drive);
    }

    // Called just before this Command runs the first time
    public void initialize() {
        RobotContainer.drive.DriveStop();
        RobotContainer.intake.setIntake(0);
        RobotContainer.shooter.setShooter(0);
        RobotContainer.elevator.setElevator(0);
        RobotContainer.shooter.ShooterDown();
    }

    // Called repeatedly when this Command is scheduled to run
    public void execute() {
    	//RobotContainer.drive.DriveStop();
    //	Robot.elevator.Stage1Stop();
    //	Robot.intake.intakeOff();
    //	Robot.arm.ArmUp();
    	    	
    //   	Robot.rgbledCAN.LEDoff();
    //	Robot.rgbledCAN.LEDred();
    	
   	
    	SmartDashboard.putNumber("Auto Finished", DriverStation.getMatchTime() );
    	System.out.println("Auto Finished: " + DriverStation.getMatchTime());
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
