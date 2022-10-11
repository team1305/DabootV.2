package frc.robot.auto_commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.RobotContainer;

/**
 *
 */
public class Auto_Turn_To_Target_Master extends SequentialCommandGroup {

	double x;
	int isuccess;

	public Auto_Turn_To_Target_Master() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);

		//addRequirements(RobotContainer.Limelight);
        //if (RobotContainer.getdebug()) { 
		//    SmartDashboard.putString("AUTO TURN TARGET", "BASE INIT");
		//}
	}

	// Called just before this Command runs the first time
	public void initialize() {
		//Robot.drive.LowGear();
		//RobotContainer.Limelight.limelightOn();
		isuccess = 0;
		//if (RobotContainer.getdebug()) {
		//	SmartDashboard.putString("AUTO TURN TARGET", "INITIALIZED");
		//}
	}

	// Called repeatedly when this Command is scheduled to run
	public void execute() {
		x = RobotContainer.Limelight.get_Tx();
		// x = x + Robot.limelight.getOffsetRatio();
		if (RobotContainer.getdebug()) {
			//SmartDashboard.putString("AUTO TURN TARGET", "TARGETTING");
		    SmartDashboard.putNumber("x ai loop", x);
		}

		RobotContainer.drive.turnRobotToAngle_New(x);

	}

	// Make this return true when this Command no longer needs to run execute()
	public boolean isFinished() {
		if (Math.abs(x) <= 0.5) {
			isuccess = isuccess + 1;

			if (isuccess >= 4) {//5
			   //if (RobotContainer.getdebug()) {
    		   //   SmartDashboard.putString("AUTO TURN TARGET", "FINISHED");
			  // }
				return true;

			} else {
				return false;
			}
		}

		else{
			return false;
		}

	}

	// Called once after isFinished returns true
	protected void end() {
		RobotContainer.drive.tankdrive(0,0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
//		end();
	}
}
